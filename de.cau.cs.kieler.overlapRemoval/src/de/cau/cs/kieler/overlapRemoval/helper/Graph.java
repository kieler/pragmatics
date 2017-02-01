package de.cau.cs.kieler.overlapRemoval.helper;

import java.util.HashMap;
import java.util.List;

public abstract class Graph<V> {
	
	private List<V> vertices;
	private List<Edge<V>> edges;
	private HashMap<V, List<V>> neighbors;
	
	public List<V> getVertices() {
		return vertices;
	}
	
	public void setVertices(List<V> vertices) {
		this.vertices = vertices;
	}
	
	public List<Edge<V>> getEdges() {
		return edges;
	}
	
	public void setEdges(List<Edge<V>> edges) {
		this.edges = edges;
	}

	public HashMap<V, List<V>> getNeighbors() {
		return neighbors;
	}

	public void setNeighbors(HashMap<V, List<V>> neighbors) {
		this.neighbors = neighbors;
	}
	
	public void addVertice(V vertice) {
		if(!this.getVertices().contains(vertice)) {
			this.vertices.add(vertice);
		}
	}
	
	public void addVertices(List<V> vertices) {
		for(V vertice : vertices) {
			this.addVertice(vertice);
		}
	}
	
	public void addEdges(List<Edge<V>> edges) {
		for(Edge<V> edge : edges) {
			this.addEdge(edge);
		}
	}
	
	public void addEdge(Edge<V> edge) {
		if(!this.getEdges().contains(edge)) {
			this.getEdges().add(edge);
		}
	}
	
	
}
