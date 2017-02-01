package de.cau.cs.kieler.overlapRemoval;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

import de.cau.cs.kieler.overlapRemoval.helper.Line;
import de.cau.cs.kieler.overlapRemoval.helper.Point;
 
public class QuickHull extends JPanel
{
	
	 public void paintComponent(Graphics g) {
		    super.paintComponent(g);

		    Graphics2D g2d = (Graphics2D) g;

		    g2d.setColor(Color.red);
		
		List<Point> set = new ArrayList<Point>();
		Random position = new Random();
		Point test;

		do {
			test = new Point(position.nextInt(500), position.nextInt(500));
			// xx and yy are the random number limits called from another part
			// of the code
			set.add(test);
		} while (set.size() < 17);

//		for(Point point : set) {
//			point.draw(g2d);
//		}
		
		set.sort(new Comparator<Point>() {
			@Override
			public int compare(Point a, Point b) {
				return a.compareTo(b);
			}
        });
		
//		DelaunayTriangulation dt = new DelaunayTriangulation();
//		
//		Graph del = dt.calculateTriangulation(set);
//		for(Point point : del.getVertices()) {
//		point.draw(g2d);
//	}		
////		List<Point> hull = this.quickHull(set);
//		
//		List<Line> edges = new ArrayList<Line>();
//		
//		for(Edge<Point> edge : del.getEdges()) {
//			edges.add(new Line(edge.getSource(), edge.getTarget()));
//		}
////		System.out.println(hull.size());
//		for(Line line : edges) {
//			line.draw(g2d);
//		}
		
//		final int levels = (int) Math.floor(Math.sqrt((double) set.size()));
//		KGrid grid = new KGrid(levels, new Bounds(0,0,500,500));
//		grid.createGrid(grid, levels);
		DelaunayIterative di = new DelaunayIterative(set);
		di.draw(g2d);
	  }
	
    public List<Point> quickHull(List<Point> points)
    {
        List<Point> convexHull = new ArrayList<Point>();
        List<Point> copyOfPoints = new ArrayList<Point>();
        copyOfPoints.addAll(points);
        Point A = copyOfPoints.get(0);
        Point B = copyOfPoints.get(copyOfPoints.size()-1);
        convexHull.add(A);
        convexHull.add(B);
        copyOfPoints.remove(A);
        copyOfPoints.remove(B);
 
        List<Point> leftSet = new ArrayList<Point>();
        List<Point> rightSet = new ArrayList<Point>();
        
        final Line AB = new Line(A,B);
        for (int i = 0; i < copyOfPoints.size(); i++)
        {
            Point p = copyOfPoints.get(i);
            if (p.isRightOf(AB))
                leftSet.add(p);
            else
                rightSet.add(p);
        }
        
        hullSet(A, B, rightSet, convexHull);
        hullSet(B, A, leftSet, convexHull);
 
        return convexHull;
    }
 
    public void hullSet(Point A, Point B, List<Point> set, List<Point> hull)
    {
        int insertPosition = hull.indexOf(B);
        if (set.size() == 0)
            return;
        if (set.size() == 1)
        {
            Point p = set.get(0);
            set.remove(p);
            hull.add(insertPosition, p);
            return;
        }
        
        float dist = 0f;
        int furthestPoint = -1;
        
        final Line line = new Line(A,B);
        for (int i = 0; i < set.size(); i++)
        {
            Point p = set.get(i);
            float distance = line.distanceOf(p);
            
            if (distance > dist)
            {
                dist = distance;
                furthestPoint = i;
            }
        }
        
        Point P = set.get(furthestPoint);
        set.remove(furthestPoint);
        hull.add(insertPosition, P);
 
        // Determine who's to the left of AP
        List<Point> leftSetAP = new ArrayList<Point>();
        final Line AP = new Line(A,P);
        for (int i = 0; i < set.size(); i++)
        {
            Point M = set.get(i);
            if (!M.isRightOf(AP))
            {
                leftSetAP.add(M);
            }
        }
 
        // Determine who's to the left of PB
        final Line PB = new Line(P,B);
        List<Point> leftSetPB = new ArrayList<Point>();
        for (int i = 0; i < set.size(); i++)
        {
            Point M = set.get(i);
            if (!M.isRightOf(PB))
            {
                leftSetPB.add(M);
            }
        }
        hullSet(A, P, leftSetAP, hull);
        hullSet(P, B, leftSetPB, hull);
 
    }
 
    public static void main(String args[])
    {
		List<Point> points = new ArrayList<Point>();
		Random position = new Random();
		Point test;

		do {
			test = new Point(position.nextInt(500), position.nextInt(500));
			// xx and yy are the random number limits called from another part
			// of the code
			points.add(test);
		} while (points.size() < 15);
 
        QuickHull qh = new QuickHull();


	    JFrame frame = new JFrame("Points");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.add(qh);
	    frame.setSize(600, 600);
	    frame.setLocationRelativeTo(null);
	    frame.setVisible(true);
    }
}
