package de.cau.cs.kieler.overlapRemoval;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.cau.cs.kieler.overlapRemoval.helper.Bounds;
import de.cau.cs.kieler.overlapRemoval.helper.DelaunayGraph;
import de.cau.cs.kieler.overlapRemoval.helper.Edge;
import de.cau.cs.kieler.overlapRemoval.helper.KGrid;
import de.cau.cs.kieler.overlapRemoval.helper.Kite;
import de.cau.cs.kieler.overlapRemoval.helper.Point;
import de.cau.cs.kieler.overlapRemoval.helper.Triangle;

public class DelaunayIterative {
	private KGrid grid;
	private List<Point> set;
	private DelaunayGraph delaunayGraph = new DelaunayGraph();
	private Map<Point, List<Triangle>> pointTriangles = new HashMap<Point, List<Triangle>>();
	
	public DelaunayIterative(List<Point> set) {
//		this.grid = this.init(set.size(), set.get(0));
		this.grid = new KGrid(0, new Bounds(0,0,500,500));
		
		for(Point point : set) {
			this.grid.insertNodes(point);
		}
		
		this.set = grid.getOrderedList();
		
		
		this.initFirstPoint(this.set.get(0));
//		this.set.remove(0);
		for(int i = 1; i< set.size(); i++) {
//			for(int i = 1; i < 3; i++) {
			this.splitTriangles(set.get(i));
		}
		
		System.out.println("elemente: "+ this.grid.getOrderedList().size());
	}
	
	public void initFirstPoint(Point startPoint) {
		final List<Triangle> neighborTriangles = new ArrayList<Triangle>();
		final int startX = 0;
		final int startY = 0;
		final int width = 500;
		final int height = 500;
		
		final Point TLC = new Point(startX,startY);
		final Point TRC = new Point(startX,height);
		final Point BLC = new Point(width, startY);
		final Point BRC = new Point(width,height);
				
		
		neighborTriangles.add(new Triangle(TLC, TRC, startPoint));
		neighborTriangles.add(new Triangle(TLC, BLC, startPoint));
		neighborTriangles.add(new Triangle(BLC, BRC, startPoint));
		neighborTriangles.add(new Triangle(BRC, TRC, startPoint));
		
		this.addTrianglesToNeighbors(neighborTriangles);
//		List<Triangle> TLCneighbors = new ArrayList<Triangle>();
//		TLCneighbors.add(new Triangle(TLC, TRC, startPoint));
//		TLCneighbors.add(new Triangle(TLC, BLC, startPoint));
//		pointTriangles.put(TLC, TLCneighbors);
//
//		List<Triangle> TRCneighbors = new ArrayList<Triangle>();
//		TRCneighbors.add(new Triangle(TLC, TRC, startPoint));
//		TRCneighbors.add(new Triangle(BRC, TRC, startPoint));
//		pointTriangles.put(TRC, TRCneighbors);		
//		
//		List<Triangle> BLCneighbors = new ArrayList<Triangle>();
//		BLCneighbors.add(new Triangle(TLC, BLC, startPoint));
//		BLCneighbors.add(new Triangle(BLC, BRC, startPoint));
//		pointTriangles.put(BLC, BLCneighbors);		
//		
//		List<Triangle> BRCneighbors = new ArrayList<Triangle>();
//		BRCneighbors.add(new Triangle(BLC, BRC, startPoint));
//		BRCneighbors.add(new Triangle(BRC, TRC, startPoint));
//		pointTriangles.put(BRC, BRCneighbors);	
//		
//		pointTriangles.put(startPoint, neighborTriangles);
		
	}
	
	public void draw(Graphics2D g2d) {
		
		for(Point point : this.pointTriangles.keySet()) {
			this.delaunayGraph.addVertice(point);
			
			for(Triangle triangle : this.pointTriangles.get(point)) {
					delaunayGraph.addEdges(triangle.getEdges());
			}
		}
		
		for(Point point : this.delaunayGraph.getVertices()) {
			point.draw(g2d);
		}
//		
		for(Edge<Point> edge : this.delaunayGraph.getEdges()) {
			edge.draw(g2d);
		}
//		this.grid.draw(g2d, this.grid);
	}

	public void splitTriangles(Point point) {
		Point dt = findTriangle(point);

		Triangle tri = null;
		if(dt != null) {
			tri = this.getCorrespondingTriangle(dt,point);
		}
		
		if(tri != null) {
			List<Triangle> triangles = this.createTriangles(point, tri);
			this.removeTriangleFromNeighbors(tri);
			this.addTrianglesToNeighbors(triangles);
			this.swapDiagonal(point, triangles);
		}
	}
	
	private void swapDiagonal(Point point, List<Triangle> candidates) {
		List<Kite> kites = new ArrayList<Kite>();
		
		for(Triangle candidat : candidates) {
			kites.add(findKite(point, candidat));
		}
		
		for(Kite kite : kites) {
			if(kite != null) {
				System.out.println("point: "+ point +" kite: T1: " +kite.getT1() + " T2: "+kite.getT2());
				List<Triangle> newCandidates = this.swapKiteTriangles(kite);
				
				if(newCandidates.size()>0) {
					this.swapDiagonal(point, newCandidates);
				}
			}
//			System.out.println(kite.swapDiagonale());
		}
	
	}

	public Point findTriangle(Point point) {
		List<Point> dl = new ArrayList<Point>();
		for(Point dlPoint : pointTriangles.keySet()) {
			if(this.getCorrespondingTriangle(dlPoint, point) != null) {
				dl.add(dlPoint);
			}
		}

		Triangle smallestTriangle = null;
		for(int i = 0; i < dl.size(); i++) {
			if(smallestTriangle == null) {
				smallestTriangle = this.getCorrespondingTriangle(dl.get(i), point);
			} else if(smallestTriangle.getArea() > this.getCorrespondingTriangle(dl.get(i),point).getArea()) {
				
				smallestTriangle = this.getCorrespondingTriangle(dl.get(i),point);
				dl.remove(i-1);
			}
		}
		
		if(dl.size() > 0) {
			return dl.get(0);
		} else {
		return null;
		}
	}
	
	public void replaceNeighbors(Point parentPoint, Triangle triangle, Point newPoint) {
		this.pointTriangles.get(parentPoint).remove(triangle);
		for(Triangle newTriangle : this.pointTriangles.get(newPoint)) {
			if(newTriangle.containsPoint(parentPoint)) {
				this.pointTriangles.get(parentPoint).add(newTriangle);
			}
		}
	}
	
	public void replaceNeighborsAfterSwap(Triangle t1, Triangle t2, Triangle newTriangle) {
		
	}
	
	public Triangle getCorrespondingTriangle(Point startPoint, Point point) {
		for(int i = 0; i < this.pointTriangles.get(startPoint).size(); i++) {
			if(this.pointTriangles.get(startPoint).get(i).inside(point)) {
				return this.pointTriangles.get(startPoint).get(i);
			}
		}
		
		return null;
	}
	
	public List<Triangle> createTriangles(Point center, Triangle triangle) {
		final List<Triangle> neighbors = new ArrayList<Triangle>();
		
		neighbors.add(new Triangle(triangle.getA(), triangle.getB(), center));
		neighbors.add(new Triangle(triangle.getA(), triangle.getC(), center));
		neighbors.add(new Triangle(triangle.getB(), triangle.getC(), center));	
		
		return neighbors;
	}
	
	
	private Kite findKite(Point point, Triangle candidat) {
		final List<Point> pointsToTest = candidat.getOtherNodes(point);
		
		
		for(Point p : pointsToTest) {
			List<Triangle> neighbors = this.pointTriangles.get(p);
			for(Triangle neighbor : neighbors) {
				if(neighbor.containsPoint(pointsToTest.get(0)) && neighbor.containsPoint(pointsToTest.get(1)) && !neighbor.equals(candidat)) {
						return new Kite(candidat, neighbor);		
				}
			}	
		}
		
		return null;
	}
	
	private List<Triangle> swapKiteTriangles(Kite kite) {
		final List<Triangle> swapDiagonale = kite.swapDiagonale();
		
		if(swapDiagonale.size() > 0) {
			this.removeTriangleFromNeighbors(kite.getT1());
			this.removeTriangleFromNeighbors(kite.getT2());
			
			this.addTrianglesToNeighbors(swapDiagonale);
		}
		
		return swapDiagonale;
	}
	
	private void addTriangleToNeighbors(Triangle triangle) {
		if(triangle.isTriangle()) {
			for(Point node : triangle.getNodes()) {
				List<Triangle> neighbors = null;
				if(this.pointTriangles.containsKey(node)) {
					neighbors = this.pointTriangles.get(node);
				} else {
					neighbors = new ArrayList<Triangle>();
				}
				neighbors.add(triangle);
				this.pointTriangles.put(node, neighbors);
			}
		}
	}
	
	private void addTrianglesToNeighbors(List<Triangle> triangles) {
		for(Triangle triangle : triangles) {
			this.addTriangleToNeighbors(triangle);
		}
	}
	
	private void removeTriangleFromNeighbors(Triangle triangle) {
		for(Point node : triangle.getNodes()) {
			List<Triangle> neighbors = this.pointTriangles.get(node);
			neighbors.remove(triangle);
			this.pointTriangles.put(node, neighbors);
		}
	}
	
	private void removeTrianglesFromNeighbors(List<Triangle> triangles) {
		for(Triangle triangle : triangles) {
			this.removeTriangleFromNeighbors(triangle);
		}
	}
}
