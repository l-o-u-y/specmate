package com.specmate.modelgeneration.mapper;

import de.tudarmstadt.ukp.dkpro.core.api.syntax.type.chunk.Chunk;

public class RGObject {
	String originalText;
	String processedText;
	ChunkObject chunk;
//	int nodeId;
	
	public RGObject(String originalText) {
		this.originalText = originalText;
	}

	public void setProcessedText() {
		processedText = originalText;
	}
	public void setProcessedText(String text) {
		processedText = text;
	}
	public void setChunk(ChunkObject c) {
		chunk = c;
	}
//	public void setNodeId(int id) {
//		nodeId = id;
//	}
	/* public String getOriginalText() {
		return originalText;
	} */
	public String getProcessedText() {
		return processedText;
	}
	public String getText() {
		// TODO MA
//		if (nodeId != null) {
//			return "TODO";
//		}
		return originalText;
	}
	

}
