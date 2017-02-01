package de.cau.cs.kieler.overlapRemoval.helper;

import java.util.ArrayList;
import java.util.List;

public class DelaunayTriangle {
	private Point center;
	
	private List<Triangle> neighbors = new ArrayList<Triangle>();
	
	public DelaunayTriangle(Point center, List<Triangle> triangles) {
		this.setCenter(center);
		this.setNeighbors(triangles);
	}
	
	public DelaunayTriangle(Point center, Triangle triangle) {
		this.setCenter(center);
		
		this.getNeighbors().add(new Triangle(triangle.getA(), triangle.getB(), center));
		this.getNeighbors().add(new Triangle(triangle.getA(), triangle.getC(), center));
		this.getNeighbors().add(new Triangle(triangle.getB(), triangle.getC(), center));
	}
	
	public DelaunayTriangle(Point center, Bounds bounds) {
		this.setCenter(center);
		this.createTriangles(center, bounds);
	}
	
	public void createTriangles(Point center, Bounds bounds) {
		Point tlc = new Point(bounds.getXPos(), bounds.getYPos());
		Point trc = new Point(bounds.getXPos()+bounds.getWidth(), bounds.getYPos());
		Point blc = new Point(bounds.getXPos(), bounds.getYPos()+bounds.getHeight());
		Point brc = new Point(bounds.getXPos()+bounds.getWidth(), bounds.getYPos()+bounds.getHeight());
				
		this.getNeighbors().add(new Triangle(tlc, trc, center));
		this.getNeighbors().add(new Triangle(tlc, blc, center));
		this.getNeighbors().add(new Triangle(blc, brc, center));
		this.getNeighbors().add(new Triangle(brc, trc, center));		
	}
	
	public Point getCenter() {
		return center;
	}

	public void setCenter(Point center) {
		this.center = center;
	}

	public List<Triangle> getNeighbors() {
		return neighbors;
	}

	public void setNeighbors(List<Triangle> triangles) {
		this.neighbors.addAll(triangles);
	}
	
	public Triangle getCorrespondingTriangle(Point point) {
		Triangle temp = null;
		for(int i = 0; i < this.getNeighbors().size(); i++) {
//			if(this.getNeighbors().get(i).IsInCircle(point, circumCenter, circumCenter.dist(point))) {
//				return this.getNeighbors().get(i);
//			}
			if(this.getNeighbors().get(i).inside(point)) {
				return this.getNeighbors().get(i);
			}
		}
		
		return null;
	}
	
}
