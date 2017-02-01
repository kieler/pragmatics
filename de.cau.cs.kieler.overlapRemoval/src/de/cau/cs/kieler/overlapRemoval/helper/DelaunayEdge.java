package de.cau.cs.kieler.overlapRemoval.helper;

import java.awt.Graphics;
import java.awt.Graphics2D;

public class DelaunayEdge extends Edge<Point> {

    public DelaunayEdge(Point source, Point target) {
        super(source, target);
       
    }



	@Override
	public void draw(Graphics2D g2d) {
		 g2d.drawLine((int) this.getSource().getXPos(), (int) this.getSource().getYPos(), (int) this.getTarget().getXPos(), (int) this.getTarget().getYPos());
	}
}