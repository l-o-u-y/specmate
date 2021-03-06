package com.specmate.modelgeneration.stages;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
		// TODO MA misc: sentence unfolder
		text = generalGithubPreprocessing(text);
		SentenceUnfolderBase unfolder;
		if (language == ELanguage.DE) {
			unfolder = new GermanSentenceUnfolder(nlpService);
		} else {
			unfolder = new EnglishSentenceUnfolder(nlpService);
		}
		text = generalProcessing(text);
		List<String> t = new ArrayList<String>();
		t.add(text);
		return t;
//		return unfolder.unfold(text);
	}

	public String generalGithubPreprocessing(String text) {
		// https://regex101.com/
		// remove "Describe your problem and - if possible - how to reproduce it"
		// standard github issue text
		text = text.replaceAll("#### (.*)\\n", "");

		// remove () with content
		// triple and double brackets https://stackoverflow.com/questions/17759004/how-to-match-string-within-parentheses-nested-in-java
		text = text.replaceAll("\\(([^()]*|\\(([^()]*|\\([^()]*\\))*\\))*\\)", "");
		text = text.replaceAll("\\(([^()]*|\\([^()]*\\))*\\)", "");
		text = text.replaceAll("\\([^\\)]*\\)", "");

		// remove <!----> with content
		text = text.replaceAll("<[ ]*!--[^\\)]*--[ ]*>", "");

		// This would mess with Action rule e.g. For instance, it comes in two shapes:
		// rectangle and round.
//		text = text.replace("For instance ,", "Example :");
//		text = text.replace("for instance ,", "example :");
//		text = text.replace("For example ,", "Example :");
//		text = text.replace("for example ,", "example :");

		// remove bullet points (rows starting with - )
		text = text.replaceAll("\\r\\n- ", "\r\n");

		// replace and/or with or
		text = text.replaceAll("and / or", "or");

		// replace word1/word2 with word1 or word 2
		text = text.replaceAll("(\\w+) \\/ (\\w+)", "$1 or $2");

		// add "." before new line if not exists (for lists)
		text = text.replaceAll("(.+[^\\.\\s\\r\\n])(\\r?\\n|\\Z)", "$1.\n");

		// remove text that ends with question mark
		// this does not detect questions that falsely end with . (e.g. in list; user
		// error)
		text = text.replaceAll("([^\\.\\?!]*)\\?", "");

		// find word that starts with not alphanumeric or space (special char)
		// remove special char and make all letters uppercase
		// remove special char so that it will not be classified as punctuation (e.g.
		// "/learn" = punctuation)
		// make all upper case so that it will not be classified as verb (e.g. "learn",
		// "Learn" = verb)
		// text = text.replaceAll("\\B[^a-zA-Z\\d\\s](\\w+)", "\\U$1\\E");
		Matcher m = Pattern.compile("\\B[^a-zA-Z\\d\\s\"'`](\\w+)").matcher(text);
		StringBuilder sb = new StringBuilder();
		int last = 0;
		while (m.find()) {
			sb.append(text.substring(last, m.start()));
			sb.append(m.group(0).substring(1).toUpperCase());
			last = m.end();
		}
		sb.append(text.substring(last));
		text = sb.toString();

		// replace space with _ inside ""
		m = Pattern.compile("\"([^\"]*)\"(\\W)").matcher(text);
		sb = new StringBuilder();
		last = 0;
		while (m.find()) {
			sb.append(text.substring(last, m.start()));
			sb.append(m.group(1).replaceAll(" ", "_").replaceAll("\"", "").toUpperCase());
			sb.append(m.group(2));
			last = m.end();
		}
		sb.append(text.substring(last));
		text = sb.toString();

		// replace space with _ inside ''
		m = Pattern.compile("'([^']*)'(\\W)").matcher(text);
		sb = new StringBuilder();
		last = 0;
		while (m.find()) {
			sb.append(text.substring(last, m.start()));
			sb.append(m.group(1).replaceAll(" ", "_").replaceAll("'", "").toUpperCase());
			sb.append(m.group(2));
			last = m.end();
		}
		sb.append(text.substring(last));
		text = sb.toString();

		// replace space with _ inside `` (code snippets)
		m = Pattern.compile("`([^`]*)`(\\W)").matcher(text);
		sb = new StringBuilder();
		last = 0;
		while (m.find()) {
			sb.append(text.substring(last, m.start()));
			sb.append(m.group(1).replaceAll(" ", "_").replaceAll("`", "").toUpperCase());
			sb.append(m.group(2));
			last = m.end();
		}
		sb.append(text.substring(last));
		text = sb.toString();

		// conjunctions
		text = text.replaceAll(", and", "and");
		text = text.replaceAll(", or", "or");
		String t = text;
		int i = 0;
		text = text.replaceAll(",\\s+([^\\s]+)(\\s+(and|or))", "$2 $1 $2");
		while (!t.equals(text) && i < 5) {
			i++;
			t = text;
			text = text.replaceAll(",\\s+([^\\s]+)(\\s+(and|or))", "$2 $1 $2");
		}

		// replace multiple/special whitespaces with space
		text = text.replaceAll("\\s+", " ");

		text = text.trim();

		return text;
	}

	private String generalProcessing(String text) {
		// Add Space before punctuation.
		return text.replaceAll("[^,.!? ](?=[,.!?])", "$0 ").replaceAll("\\s+", " ");
	}
}
