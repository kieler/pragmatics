package de.cau.cs.kieler.overlapRemoval.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DelaunayGraph extends Graph<Point> {
	private Map<Point, Set<Triangle>> triangles;

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

	public DelaunayGraph(Map<Point, Set<Triangle>> pointsAndTriangles) {
		this.setVertices(new ArrayList<Point>());
		this.setEdges(new ArrayList<Edge<Point>>());
		this.setTriangles(pointsAndTriangles);
		for (Point point : pointsAndTriangles.keySet()) {
			this.addVertice(point);

			for (Triangle triangle : pointsAndTriangles.get(point)) {
				this.addEdges(triangle.getEdges());
			}
		}
	}
	
	public Map<Point, Set<Triangle>> getTriangles() {
		return triangles;
	}

	public void setTriangles(Map<Point, Set<Triangle>> triangles) {
		this.triangles = triangles;
	}
	
	public void addEdge(DelaunayEdge edge) {
		this.addEdge((Edge<Point>) edge);
	}
	
	
	
}
