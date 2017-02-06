package de.cau.cs.kieler.overlapRemoval;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import de.cau.cs.kieler.overlapRemoval.helper.Bounds;
import de.cau.cs.kieler.overlapRemoval.helper.DelaunayGraph;
import de.cau.cs.kieler.overlapRemoval.helper.Edge;
import de.cau.cs.kieler.overlapRemoval.helper.Kite;
import de.cau.cs.kieler.overlapRemoval.helper.Point;
import de.cau.cs.kieler.overlapRemoval.helper.Triangle;

public class DelaunayIterative {
	private Bounds bounds;
	private List<Point> set;
	private DelaunayGraph delaunayGraph = new DelaunayGraph();
	private Map<Point, Set<Triangle>> pointTriangles = new HashMap<Point, Set<Triangle>>();

	public DelaunayIterative(Bounds bounds, List<Point> set) {
		this.setSet(set);
		this.setBounds(bounds);

		this.initFirstPoint(this.set.get(0));
		for (int i = 1; i < set.size(); i++) {
			this.splitTriangles(set.get(i));
		}

		System.out.println("punkte: " + this.pointTriangles.size());

	}

	public void initFirstPoint(Point startPoint) {
		final List<Triangle> neighborTriangles = new ArrayList<Triangle>();
		final double startX = this.getBounds().getXPos();
		final double startY = this.getBounds().getYPos();
		final double width = this.getBounds().getWidth();
		final double height = this.getBounds().getHeight();

		final Point TLC = new Point(startX, startY);
		final Point TRC = new Point(startX, height);
		final Point BLC = new Point(width, startY);
		final Point BRC = new Point(width, height);

		neighborTriangles.add(new Triangle(TLC, TRC, startPoint));
		neighborTriangles.add(new Triangle(TLC, BLC, startPoint));
		neighborTriangles.add(new Triangle(BLC, BRC, startPoint));
		neighborTriangles.add(new Triangle(BRC, TRC, startPoint));

		this.addTrianglesToNeighbors(neighborTriangles);
	}

	public void splitTriangles(Point point) {
		final Triangle tri = findTriangle(point);
		if (tri != null) {
			this.removeTriangleFromNeighbors(tri);
			final List<Triangle> triangles = this.createTriangles(point, tri);
			this.addTrianglesToNeighbors(triangles);
			this.swapDiagonal(point, triangles);
		}
	}

	private boolean swapDiagonal(Point point, List<Triangle> candidates) {
		for (Triangle candidat : candidates) {
			this.swapDiagonal(point, candidat);
		}

		return true;
	}

	private boolean swapDiagonal(Point point, Triangle candidate) {
		List<Kite> kites = new ArrayList<Kite>();

		kites.add(findKite(point, candidate));

		for (Kite kite : kites) {
			if (kite != null) {
				if (kite.needToSwap()) {
					List<Triangle> newCandidates = this.swapKiteTriangles(kite);

					if (newCandidates != null) {

						this.swapDiagonal(point, newCandidates);

					}
				}
			}
		}

		return true;
	}

	private Triangle findTriangle(Point point) {
		Set<Triangle> candidates = new HashSet<Triangle>();
		for (Point alreadyInsertedPoints : pointTriangles.keySet()) {
			Triangle candidate = this.getCorrespondingTriangle(alreadyInsertedPoints, point);
			if (candidate != null) {
				candidates.add(candidate);
			}
		}

		Triangle smallestTriangle = null;

		for (Triangle candidate : candidates) {
			if (smallestTriangle == null) {
				smallestTriangle = candidate;
			}
		}

		return smallestTriangle;
	}

	private Triangle getCorrespondingTriangle(Point startPoint, Point point) {
		Triangle tri = null;
		for (Triangle triangle : this.pointTriangles.get(startPoint)) {
			if (triangle.inside(point)) {
				return triangle;
			}
		}

		return tri;
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

		for (Point p : pointsToTest) {
			Set<Triangle> neighbors = this.pointTriangles.get(p);
			for (Triangle neighbor : neighbors) {
				if (pointsToTest.size() == 2) {
					if (neighbor.containsPoint(pointsToTest.get(0)) && neighbor.containsPoint(pointsToTest.get(1))
							&& !neighbor.containsPoint(point)) {
						return new Kite(candidat, neighbor);
					}
				}
			}
		}

		return null;
	}

	private List<Triangle> swapKiteTriangles(Kite kite) {
		final List<Triangle> swapDiagonale = kite.swapDiagonale();

		if (swapDiagonale.size() == 2) {
			this.removeTriangleFromNeighbors(kite.getT1());
			this.removeTriangleFromNeighbors(kite.getT2());

			this.addTrianglesToNeighbors(swapDiagonale);

			return swapDiagonale;
		}
		return null;
	}

	private void addTriangleToNeighbors(Triangle triangle) {
		for (Point node : triangle.getNodes()) {
			Set<Triangle> neighbors = null;
			if (this.pointTriangles.containsKey(node)) {
				neighbors = this.pointTriangles.get(node);
			} else {
				neighbors = new HashSet<Triangle>();
			}
			neighbors.add(triangle);
			this.pointTriangles.put(node, neighbors);
		}
	}

	private void addTrianglesToNeighbors(List<Triangle> triangles) {
		for (Triangle triangle : triangles) {
			this.addTriangleToNeighbors(triangle);
		}
	}

	private void removeTriangleFromNeighbors(Triangle triangle) {

		for (Point node : triangle.getNodes()) {
			Set<Triangle> neighbors = this.pointTriangles.get(node);

			neighbors.remove(triangle);
			this.pointTriangles.put(node, neighbors);
		}
	}

	private void setSet(List<Point> set) {
		this.set = set;
	}

	public Bounds getBounds() {
		return bounds;
	}

	public void setBounds(Bounds bounds) {
		this.bounds = bounds;
	}

	public void draw(Graphics2D g2d) {

		for (Point point : this.pointTriangles.keySet()) {
			this.delaunayGraph.addVertice(point);

			for (Triangle triangle : this.pointTriangles.get(point)) {
				delaunayGraph.addEdges(triangle.getEdges());
			}
		}

		for (Point point : this.delaunayGraph.getVertices()) {
			point.draw(g2d);
		}

		for (Edge<Point> edge : this.delaunayGraph.getEdges()) {
			edge.draw(g2d);
		}
	}
}
