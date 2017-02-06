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

import de.cau.cs.kieler.overlapRemoval.helper.Bounds;
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
		final Bounds bounds = new Bounds(0,0,700,700);
		do {
			test = new Point(position.nextInt((int) bounds.getWidth()), position.nextInt((int)bounds.getHeight()));
			set.add(test);
		} while (set.size() < 1000);

		
		set.sort(new Comparator<Point>() {
			@Override
			public int compare(Point a, Point b) {
				return a.compareTo(b);
			}
        });
		
		DelaunayIterative di = new DelaunayIterative(bounds, set);
		di.draw(g2d);
	  }
 
    public static void main(String args[])
    {
 
        QuickHull qh = new QuickHull();


	    JFrame frame = new JFrame("Points");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.add(qh);
	    frame.setSize(800, 800);
	    frame.setLocationRelativeTo(null);
	    frame.setVisible(true);
    }
}
