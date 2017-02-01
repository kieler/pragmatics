package de.cau.cs.kieler.overlapRemoval.helper;

import java.util.ArrayList;
import java.util.List;

public class DelaunayGraph extends Graph<Point> {
	private List<Triangle> triangles = new ArrayList<Triangle>();

	public DelaunayGraph() {
		this.setVertices(new ArrayList<Point>());
		this.setEdges(new ArrayList<Edge<Point>>());
	}
	
	public DelaunayGraph(List<Point> vertices) {
		this.setVertices(vertices);
		this.setEdges(new ArrayList<Edge<Point>>());
	}
	
	public DelaunayGraph(List<Point> vertices, List<Edge<Point>> edges) {
		this.setVertices(vertices);
		this.setEdges(edges);
	}

	public List<Triangle> getTriangles() {
		return triangles;
	}

	public void setTriangles(List<Triangle> triangles) {
		this.triangles = triangles;
	}
	
	public void addEdge(DelaunayEdge edge) {
		this.addEdge((Edge<Point>) edge);
	}
	
	
	
}
