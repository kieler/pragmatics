package de.cau.cs.kieler.overlapRemoval.helper;

import java.util.ArrayList;
import java.util.List;

public class Polygon {
	private List<Point> points = new ArrayList<Point>();
	private List<Edge<Point>> edges = new ArrayList<Edge<Point>>();
	
	public Polygon(List<Point> points) {
		this.setPoints(points);
	}
	
	public Polygon(Triangle t1, Triangle t2) {
		this.getPoints().addAll(t1.getNodes());
		this.getPoints().addAll(t2.getNodes());
		this.getEdges().addAll(t1.getEdges());
		this.getEdges().addAll(t2.getEdges());
	}

	public List<Point> getPoints() {
		return points;
	}

	public void setPoints(List<Point> points) {
		this.points.addAll(points);
	}
	
	public boolean connected() {
		if(this.getPoints().size() != 4) {
			return false;
		} else {
			for(int i=0; i<4; i++) {
				if(i==3) {
					if(!this.getEdges().contains(new DelaunayEdge(this.getPoints().get(i), this.getPoints().get(0)))) {
						return false;
					}
				} else {
					if(!this.getEdges().contains(new DelaunayEdge(this.getPoints().get(i), this.getPoints().get(i+1)))) {
						return false;
					}
				}
			}
		}
		
		
		return true;
	}

	public List<Edge<Point>> getEdges() {
		return edges;
	}

	public void setEdges(List<Edge<Point>> edges) {
		this.edges.addAll(edges);
	}
}
