package com.specmate.modelgeneration.stages;

import java.util.List;

import com.specmate.common.exception.SpecmateException;
import com.specmate.nlp.api.ELanguage;
import com.specmate.nlp.api.INLPService;
import com.specmate.nlp.util.EnglishSentenceUnfolder;
import com.specmate.nlp.util.GermanSentenceUnfolder;
import com.specmate.nlp.util.SentenceUnfolderBase;

public class TextPreProcessor {
	private final ELanguage language;
	private final INLPService nlpService;

	public TextPreProcessor(ELanguage language, INLPService nlpService) {
		this.language = language;
		this.nlpService = nlpService;
	}

	public List<String> preProcess(String text) throws SpecmateException {
		SentenceUnfolderBase unfolder;
		if (language == ELanguage.DE) {
			unfolder = new GermanSentenceUnfolder(nlpService);
		} else {
			unfolder = new EnglishSentenceUnfolder(nlpService);
		}
		text = generalGithubPreprocessing(text);
		return unfolder.unfold(text);
	}
	
	private String generalGithubPreprocessing(String text) {
		// remove "Describe your problem and - if possible - how to reproduce it"
		
		// standard github issue text
		text = text.replaceAll("Describe your problem and - if possible - how to reproduce it", "");
		
		// remove () with content
		text = text.replaceAll("\\s*\\([^\\)]*\\)\\s*", " ");
		
		// replace : with .
		text = text.replaceAll(":", ".");
		
		// TODO add "." after lists.
		// TODO remove questions. ggf. detect if it starts with verb -> question.
		
		// replace multiple/special whitespaces with space
		text = text.replaceAll("\\s+", " ");
		
		// find word that starts with /, remove / and make all letters uppercase
		text = text.replaceAll("\b/(\\\\S+)", "\\U$1\\E");
		
		

		//TODO replace space with _ in code snippets
		//TODO replace space with _ inside ""
		//TODO replace space with _ inside ''
		
		text = text.trim();
		
		text = generalProcessing(text);

		return text;
	}

	private String generalProcessing(String text) {
		// Add Space before punctuation.
		return text.replaceAll("[^,.!? ](?=[,.!?])", "$0 ").replaceAll("\\s+", " ");
	}
}
