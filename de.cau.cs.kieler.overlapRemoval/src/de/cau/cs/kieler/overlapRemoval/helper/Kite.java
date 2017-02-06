package de.cau.cs.kieler.overlapRemoval.helper;

import java.util.ArrayList;
import java.util.List;

public class Kite {
	private Triangle t1;
	private Triangle t2;
	
	public Kite(Triangle t1, Triangle t2) {
		this.setT1(t1);
		this.setT2(t2);
	}

	public Triangle getT1() {
		return t1;
	}

	public void setT1(Triangle t1) {
		this.t1 = t1;
	}

	public Triangle getT2() {
		return t2;
	}

	public void setT2(Triangle t2) {
		this.t2 = t2;
	}
	
	public Edge<Point> getDiagonale() {
		List<Point> pointsDiagonale = new ArrayList<Point>();
		for(Point nodeT1 : this.t1.getNodes()){
			if(this.t2.containsPoint(nodeT1)) {
				pointsDiagonale.add(nodeT1);
			}
		}
		
		return new DelaunayEdge(pointsDiagonale.get(0), pointsDiagonale.get(1));
	}
	
	private boolean testCircumcircleProperty(Triangle triangle, Point K) {

		if(triangle.containsPoint(K)) {
			return false;
		}

		final Point center = triangle.getCircumCenter();

		return triangle.IsInCircle(K, center, center.distance(triangle.getA()));
	}
	
	public List<Triangle> swapDiagonale() {
		final List<Triangle> newTriangles = new ArrayList<Triangle>();
		
		final Edge<Point> diagonale = this.getDiagonale();
		
		final Point testPointT1 = this.t1.getMissingPoint(diagonale.getSource(), diagonale.getTarget());
		final Point testPointT2 = this.t2.getMissingPoint(diagonale.getSource(), diagonale.getTarget());
		
		if(testCircumcircleProperty(this.getT1(), testPointT2) == true) {

			Triangle swapedTriangle = new Triangle(testPointT1, testPointT2, diagonale.getSource());
			Triangle secondSwapedTriangle = new Triangle(testPointT1, testPointT2, diagonale.getTarget());
			
			newTriangles.add(swapedTriangle);
			newTriangles.add(secondSwapedTriangle);
		}
		
		return newTriangles;
	}
	
	public List<Point> getAllNodes() {
		List<Point> nodes = this.getT1().getNodes();
		nodes.addAll(this.getT2().getNodes());
		
		return nodes;
	}
	
	public boolean needToSwap() {
		final Edge<Point> diagonale = this.getDiagonale();
		
		if(diagonale == null) {
			return false;
		}
		final Point testPointT2 = this.t2.getMissingPoint(diagonale.getSource(), diagonale.getTarget());

		if(testCircumcircleProperty(this.getT1(), testPointT2) == true) {

			return true;
		}
		
		return false;		
	}
}
