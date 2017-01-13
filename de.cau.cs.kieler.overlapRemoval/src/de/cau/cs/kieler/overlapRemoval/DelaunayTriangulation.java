package de.cau.cs.kieler.overlapRemoval;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Lists;

import de.cau.cs.kieler.overlapRemoval.helper.DelaunayEdge;
import de.cau.cs.kieler.overlapRemoval.helper.Edge;
import de.cau.cs.kieler.overlapRemoval.helper.Graph;
import de.cau.cs.kieler.overlapRemoval.helper.Point;

public class DelaunayTriangulation {
	private List<Point> points;
	
	public DelaunayTriangulation() {
		
	}
	public DelaunayTriangulation(List<Point> points) {
		this.setPoints(points);
	}
	
	public Graph<Point> calculateTriangulation(List<Point> pPoints) {
		Graph<Point> delaunayGraph = new Graph<Point>();
		delaunayGraph.addVertices(pPoints);

		final QuickHull hull = new QuickHull();
		List<Point> copyOfPoints = new ArrayList<Point>();
		copyOfPoints.addAll(pPoints);
		List<Point> konvexHull = hull.quickHull(copyOfPoints);		
		
		if(pPoints.size() == 0 || pPoints.size() == 1) {
			return delaunayGraph;
		}

		if(pPoints.size() == 2) {
			delaunayGraph.addEdge(new DelaunayEdge(pPoints.get(0), pPoints.get(1)));
			return delaunayGraph;
		}
		
		if(pPoints.size() == 3) {
			delaunayGraph.addEdge(new DelaunayEdge(pPoints.get(0), pPoints.get(1)));
			delaunayGraph.addEdge(new DelaunayEdge(pPoints.get(1), pPoints.get(2)));
			delaunayGraph.addEdge(new DelaunayEdge(pPoints.get(0), pPoints.get(2)));
			
			return delaunayGraph;
		}
		
//		for(int i = 0; i < konvexHull.size(); i++) {
//			if(i == konvexHull.size()-1) {
//				delaunayGraph.addEdge(new DelaunayEdge(konvexHull.get(i), konvexHull.get(0)));
//			} else {
//				delaunayGraph.addEdge(new DelaunayEdge(konvexHull.get(i), konvexHull.get(i+1)));
//			}				
//		}
		
		final int median = pPoints.size()/2 +1;
		List<List<Point>> lists = Lists.partition(pPoints, median);
		
		if(lists.size() > 1) {
			List<Point> left = lists.get(0);
			List<Point> right = lists.get(1);
			
			Graph<Point> leftGraph = calculateTriangulation(left);
			Graph<Point> rightGraph = calculateTriangulation(right);
			
//			for(Point p : pPoints) {
//				for(Point khp : konvexHull) {
//					if(!konvexHull.contains(p))
//					delaunayGraph.addEdge(new Edge<Point>(p, khp));
//				}
//			}			
			
			List<Point> leftHull = hull.quickHull(leftGraph.getVertices());
			List<Point> rightHull = hull.quickHull(rightGraph.getVertices());

			for(int i = 0; i < leftHull.size(); i++) {
				if(i == leftHull.size()-1) {
					delaunayGraph.addEdge(new DelaunayEdge(leftHull.get(i), leftHull.get(0)));
				} else {
					delaunayGraph.addEdge(new DelaunayEdge(leftHull.get(i), leftHull.get(i+1)));
				}				
			}
			
			for(int i = 0; i < rightHull.size(); i++) {
				if(i == rightHull.size()-1) {
					delaunayGraph.addEdge(new DelaunayEdge(rightHull.get(i), rightHull.get(0)));
				} else {
					delaunayGraph.addEdge(new DelaunayEdge(rightHull.get(i), rightHull.get(i+1)));
				}				
			}			
			
			delaunayGraph.merge(leftGraph);
			delaunayGraph.merge(rightGraph);
			
		}		
		
		
		return delaunayGraph;
	}
	
	
	public List<Point> getPoints() {
		return points;
	}
	
	public void setPoints(List<Point> points) {
		this.points = points;
	}
}
