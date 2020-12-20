package com.specmate.modelgeneration.export;

import java.util.List;

import com.specmate.model.requirements.RGModel;
import com.specmate.model.requirements.RGNode;
import com.specmate.model.requirements.RGWord;
import com.specmate.modelgeneration.stages.TextPreProcessor;

public class RGUtils {

	public static String generateText(RGModel model) {
		TextPreProcessor preProcessor = new TextPreProcessor(null, null);
		List<RGWord> rgObjects = model.getWords();
		String string = "";
		for (int i = 0; i < rgObjects.size(); i++) {
			RGWord obj = rgObjects.get(i);
			String originalText = obj.getOriginalText();
			String processedText = obj.getProcessedText();

			RGNode node = obj.getNode();
			RGNode nextNode = i < rgObjects.size() - 1 ? rgObjects.get(i+1).getNode() : null;
			
			if (originalText != null && processedText != null && node != null) {
				String text = node.getComponent();
				while (node.equals(nextNode)) {
					i++;
					originalText = originalText + " " + rgObjects.get(i).getOriginalText();
					node = obj.getNode();
					nextNode = i < rgObjects.size() - 1 ? rgObjects.get(i+1).getNode() : null;
					
				}

				// replace node text with original text
				// if we notice that node text = process(original text)
				if (preProcessor.generalGithubPreprocessing(originalText).toLowerCase().equals(text.toLowerCase() + '.')) {
					text = originalText;
				}
				
				originalText = text;
				if (string.trim().endsWith(".") || string.equals("")) {
					originalText = originalText.substring(0, 1).toUpperCase() + originalText.substring(1);
				}
			}
			
			if (originalText != null) {
				if (originalText.matches("[\\.,\\:;\\!\\?]")) {
					string = string + originalText;
				} else {
					string = string + ' ' + originalText;
				}
			}
		}
		return string;
	}
}
