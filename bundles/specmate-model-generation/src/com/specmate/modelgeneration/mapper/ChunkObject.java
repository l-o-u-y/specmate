package com.specmate.modelgeneration.mapper;

import de.tudarmstadt.ukp.dkpro.core.api.syntax.type.chunk.Chunk;

public class ChunkObject {

	Chunk chunk;
	int nodeId;
	boolean visited = false;
	
	public ChunkObject(Chunk chunk) {
		this.chunk = chunk;
	}
	
	public void setNodeId(int id) {
		nodeId = id;
	}
	
	public boolean isVisited() {
		return visited;
	}
	
	public void setVisited(boolean v) {
		visited = v;
	}
	
}
