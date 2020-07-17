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
		// TODO MA
		text = generalGithubPreprocessing(text);
		return unfolder.unfold(text);
	}
	
	private String generalGithubPreprocessing(String text) {
		// https://regex101.com/
		// remove "Describe your problem and - if possible - how to reproduce it"
		// standard github issue text
		text = text.replaceAll("Describe your problem and - if possible - how to reproduce it", "");
		
		// remove () with content
		text = text.replaceAll("\\([^\\)]*\\)", "");
		
		// replace : with .
		text = text.replaceAll(":", ".");
		
		// add "." before new line if not exists (for lists)
		text = text.replaceAll("(.+[^\\.])(\\r?\\n|\\Z)", "$1.\\n");
		
		// remove text that end with question mark
		// this does not detect questions that falsely end with . (e.g. in list; user error)
		text = text.replaceAll("([^.?!]*)\\?", "");
		
		// replace multiple/special whitespaces with space
		text = text.replaceAll("\\s+", " ");
		
		// find word that starts with not alphanumeric or space (special char)
		// remove special char and make all letters uppercase
		// remove special char so that it will not be classified as punctuation (e.g. "/learn" =  puncutation)
		// make all upper case so that it will not be classified as verb (e.g. "learn", "Learn" = verb) 
		text = text.replaceAll("\\B[^a-zA-Z\\d\\s](\\w+)", "\\U$1\\E");
		
		// replace space with _ inside ""
		text = text.replaceAll("(?<=\")(\\w+)\\s", "$1_");
		
		// replace space with _ inside ""
		text = text.replaceAll("(?<=')(\\w+)\\s", "$1_");
		
		// remove " and '
		text = text.replaceAll("\"", "");
		text = text.replaceAll("'", "");
		
		//TODO MA replace space with _ in code snippets
		
		text = text.trim();
		
		text = generalProcessing(text);

		return text;
	}

	private String generalProcessing(String text) {
		// Add Space before punctuation.
		return text.replaceAll("[^,.!? ](?=[,.!?])", "$0 ").replaceAll("\\s+", " ");
	}
}
