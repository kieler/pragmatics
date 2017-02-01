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
	
	public Point findPointToTest() {
		for(Point node : this.getT1().getNodes()) {
			if(!this.getT2().containsPoint(node)) {
				return node;
			}
		}
		
		return null;
	}
	
	public Edge<Point> getDiagonale() {
		if(this.t2.hasEdge(this.t1.getA(), this.t1.getB())) {
			return new DelaunayEdge(this.t1.getA(), this.t1.getB());
		} else if(this.t2.hasEdge(this.t1.getA(), this.t1.getC())) {
			return new DelaunayEdge(this.t1.getA(), this.t1.getC());
		} else {
			return new DelaunayEdge(this.t1.getB(), this.t1.getC());
		}	
	}
	
	private boolean testCircumcircleProperty(Triangle triangle, Point K) {

		if(triangle.containsPoint(K)) {
			return false;
		}

		final Point center = triangle.getCircumCenter();

		return triangle.IsInCircle(K, center, center.dist(triangle.getA()));
	}
	
	public List<Triangle> swapDiagonale() {
		final List<Triangle> newTriangles = new ArrayList<Triangle>();
		
		final Edge<Point> diagonale = this.getDiagonale();
		
		final Point testPointT1 = this.t1.getMissingPoint(diagonale.getSource(), diagonale.getTarget());
		final Point testPointT2 = this.t2.getMissingPoint(diagonale.getSource(), diagonale.getTarget());
		
		if(testCircumcircleProperty(this.getT2(), testPointT1) == true) {

			Triangle swapedTriangle = new Triangle(diagonale.getSource(), testPointT1, testPointT2);
			Triangle secondSwapedTriangle = new Triangle(diagonale.getTarget(), testPointT1, testPointT2);
			
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
}
