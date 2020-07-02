package com.specmate.modelgeneration;

import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.StringJoiner;
import java.util.Vector;

import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.eclipse.emf.common.util.URI;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import com.specmate.cause_effect_patterns.parse.DependencyParsetree;
import com.specmate.cause_effect_patterns.parse.matcher.MatchResult;
import com.specmate.cause_effect_patterns.parse.matcher.MatchRule;
import com.specmate.cause_effect_patterns.parse.matcher.MatchUtil;
import com.specmate.cause_effect_patterns.parse.wrapper.MatchResultWrapper;
import com.specmate.cause_effect_patterns.parse.wrapper.MatchResultWrapper.RuleType;
import com.specmate.cause_effect_patterns.parse.wrapper.MatchResultWrapper.SubtreeNames;
import com.specmate.cause_effect_patterns.resolve.GenerateMatcherUtil;
import com.specmate.common.exception.SpecmateException;
import com.specmate.common.exception.SpecmateInternalException;
import com.specmate.config.api.IConfigService;
import com.specmate.model.administration.ErrorCode;
import com.specmate.model.requirements.RGModel;
import com.specmate.model.requirements.RGNode;
import com.specmate.model.requirements.NodeType;
import com.specmate.model.requirements.RGConnectionType;
import com.specmate.model.requirements.CEGNode;
import com.specmate.model.requirements.NodeType;
import com.specmate.model.requirements.RGModel;
import com.specmate.nlp.api.ELanguage;
import com.specmate.nlp.api.INLPService;
import com.specmate.nlp.util.EnglishSentenceUnfolder;
import com.specmate.nlp.util.GermanSentenceUnfolder;
import com.specmate.nlp.util.NLPUtil;
import com.specmate.nlp.util.SentenceUnfolderBase;
import com.specmate.xtext.XTextException;
import com.specmate.xtext.XTextUtil;

import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Sentence;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;
import de.tudarmstadt.ukp.dkpro.core.api.syntax.type.constituent.Constituent;
import de.tudarmstadt.ukp.dkpro.core.api.syntax.type.constituent.NP;
import de.tudarmstadt.ukp.dkpro.core.api.syntax.type.dependency.NN;
import de.tudarmstadt.ukp.dkpro.core.api.syntax.type.dependency.NSUBJ;
import de.tudarmstadt.ukp.dkpro.core.api.syntax.type.dependency.NSUBJPASS;

public class PatternbasedRGGenerator implements IRGFromRequirementGenerator {
	private INLPService tagger;
	private List<MatchRule> rules;
	private RGCreation creation;
	private ELanguage lang;
	private IConfigService configService;
	
	private static final int XSTART  = 225;
	private static final int YSTART  = 225;
	
	private static final int XOFFSET = 300;
	private static final int YOFFSET = 150;
	
	
	public PatternbasedRGGenerator(ELanguage lang, INLPService tagger, IConfigService configService) throws SpecmateException {
		this.tagger = tagger;
		this.creation = new RGCreation();
		this.lang = lang;
		this.configService = configService;
		loadRessources();
	}
	
	private void loadRessources() throws SpecmateInternalException {
		String[] paths = readURIStringsFromConfig();
		String depPath = paths[0];
		String posPath = paths[1];
		String rulePath = paths[2];
		String langCode = this.lang.getLanguage().toUpperCase();
		
		try {
			URI dep = getURI(depPath,  "resources/"+langCode+"/Dep_"+langCode+".spec");
			URI pos = getURI(posPath,  "resources/"+langCode+"/Pos_"+langCode+".spec");
			URI rule = getURI(rulePath,"resources/"+langCode+"/Rule_"+langCode+".spec");
			
			this.rules = new GenerateMatcherUtil().loadXTextResources(rule, dep, pos);
		} catch (XTextException e) {
			throw new SpecmateInternalException(ErrorCode.NLP, e);
		} catch (URISyntaxException e) {
			throw new SpecmateInternalException(ErrorCode.INTERNAL_PROBLEM, e);
		} 
	}
	
	/**
	 * Read in the location paths from the configService
	 * @return A String Array of length 3 with the paths to {dependency, POS, rules} in that order. 
	 * If there is no data given for any of those paths, the specific element is null. 
	 */
	private String[] readURIStringsFromConfig() {
		Set<Entry<Object, Object>> configData = this.configService.getConfigurationProperties("generation.dsl");
		String depPath = null;
		String posPath = null;
		String rulePath = null;
		String langCode = this.lang.toString().toUpperCase();
		
		for(Entry<Object,Object> entry: configData) {
			String key = (String) entry.getKey();
			
			if(key.equals("generation.dsl."+langCode+".rule")) {
				rulePath = (String) entry.getValue();
			}
			
			if(key.equals("generation.dsl."+langCode+".dependency")) {
				depPath = (String) entry.getValue();
			}
			
			if(key.equals("generation.dsl."+langCode+".pos")) {
				posPath = (String) entry.getValue();
			}
		}
		
		String[] result = {depPath, posPath, rulePath};
		return result;
	}
	
	private URI getURI(String path, String localDefault) throws URISyntaxException {
		if(path != null) {
			return URI.createURI(path);
		}
		return getLocalFile(localDefault);
	}
	
	private URI getLocalFile(String fileName) throws URISyntaxException {
		Bundle bundle = FrameworkUtil.getBundle(PatternbasedRGGenerator.class);
		return URI.createURI(bundle.getResource(fileName).toURI().toString());
	}
	
	private String preprocessData(String text) throws SpecmateException {
		SentenceUnfolderBase unfolder;
		if(this.lang == ELanguage.DE) {
			unfolder = new GermanSentenceUnfolder();
		} else {
			unfolder = new EnglishSentenceUnfolder();
		}
		return unfolder.unfold(this.tagger, text, this.lang);
	}
	
	public RGModel createModel(RGModel model, String text) throws SpecmateException {		
		text = preprocessData(text);
		JCas tagResult = this.tagger.processText(text, this.lang);
		DependencyParsetree data = DependencyParsetree.generateFromJCas(tagResult);
		List<MatchResult> results = MatchUtil.evaluateRuleset(this.rules, data);
		LinkedList<RGNode> nodes = new LinkedList<RGNode>();
		HashSet<String> nouns = new HashSet<String>();

		boolean generatedSomething = false;

		JCasUtil.select(tagResult, Token.class).forEach(p -> {
			if (p.getPosValue().equals("NN") || p.getPosValue().equals("NNP") ) {
				nouns.add(p.getCoveredText());
			}
		});

		System.out.println(NLPUtil.printPOSTags(tagResult));
		System.out.println(NLPUtil.printChunks(tagResult));
		System.out.println(NLPUtil.printParse(tagResult));
		System.out.println(NLPUtil.printDependencies(tagResult));

		/* for (Sentence sentence : JCasUtil.select(tagResult, Sentence.class)) {
			List<Constituent> nounPhrases = NLPUtil.getNounPhrases(tagResult, sentence);
		      for (Constituent nounphrase : nounPhrases) {
	    		  nouns.add(nounphrase.getCoveredText());
		    	  for (NN nn : JCasUtil.selectCovered(tagResult, NN.class, nounphrase)) {
		    		  nouns.add(nn.toString());
		    	  }
		    	  for (NSUBJ nn : JCasUtil.selectCovered(tagResult, NSUBJ.class, nounphrase)) {
		    		  nouns.add(nn.toString());
		    	  }
		    	  for (NSUBJPASS nn : JCasUtil.selectCovered(tagResult, NSUBJPASS.class, nounphrase)) {
		    		  nouns.add(nn.toString());
		    	  }
		      }
		}*/
		
		if (nouns.size() > 0) {
			generatedSomething = true;
			int i = 0;
			for (String noun : nouns) {
				this.creation.createNodeIfNotExist(nodes, model, noun, "", 100 * (i), 100 * (i), NodeType.AND);
				i++;
			}
		}
		
		for(MatchResult result: results) {
			if(!result.isSuccessfulMatch()) {
				continue;
			}
			
			MatchResultWrapper res = new MatchResultWrapper(result);

			if (res.isInheritance()) {
				RGNode parent = this.creation.createNodeIfNotExist(nodes, model, 
						res.result.getSubmatch(SubtreeNames.PARENT).getMatchTree().getRepresentationString(true)
						, "", 0, 0, NodeType.AND);
				RGNode child = this.creation.createNodeIfNotExist(nodes, model, 
						res.result.getSubmatch(SubtreeNames.CHILD).getMatchTree().getRepresentationString(true)
						, "", 0, 0, NodeType.AND);
				this.creation.createConnection(model, parent, child, RGConnectionType.INHERITANCE, false);
			} else if(res.isComposition()) {
				generatedSomething = true;
				RGNode parent = this.creation.createNodeIfNotExist(nodes, model, 
						res.result.getSubmatch(SubtreeNames.PARENT).getMatchTree().getRepresentationString(true)
						, "", 0, 0, NodeType.AND);
				RGNode child = this.creation.createNodeIfNotExist(nodes, model, 
						res.result.getSubmatch(SubtreeNames.CHILD).getMatchTree().getRepresentationString(true)
						, "", 0, 0, NodeType.AND);
				this.creation.createConnection(model, parent, child, RGConnectionType.COMPOSITION, false);
			} else if(res.isAction()) {
				/*
				i++;
				RGNode parent = this.creation.createNodeIfNotExist(nodes, model, var+" "+(i), "", 100 * (i), 100 * (i), NodeType.OR);
				i++;
				RGNode child = this.creation.createNodeIfNotExist(nodes, model, var+" "+(i), "", 100 * (i), 100 * (i), NodeType.OR);
				this.creation.createConnection(model, parent, child, RGConnectionType.COMPOSITION, false);
				*/
			}
			
			
		}
		if(!generatedSomething) {
			throw new SpecmateInternalException(ErrorCode.NLP, "No Relationship Pair Found.");
		}
		
		return model;
	}

	private RGNode addNode(RGModel model, LinkedList<RGNode> nodes, String component, String modifier, int[] posTable, int offset, NodeType type) {
		int posX = XSTART + offset * XOFFSET;
		int posY = YSTART + posTable[offset] * YOFFSET;
		
		int oldSize = nodes.size();
		RGNode node = this.creation.createNodeIfNotExist(nodes, model, component, modifier, posX, posY, type);
		if(nodes.size() != oldSize) {
			nodes.add(node);
			posTable[offset]++;
		}
		return node;
	}
	
	private String capitalize(String str) {
		return str.substring(0, 1).toUpperCase() + str.substring(1); 
	}
	
	
	
}
