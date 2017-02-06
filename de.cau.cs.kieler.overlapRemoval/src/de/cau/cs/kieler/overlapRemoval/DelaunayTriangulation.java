package de.cau.cs.kieler.overlapRemoval;

import java.awt.Graphics2D;
import java.util.List;

import de.cau.cs.kieler.overlapRemoval.helper.Bounds;
import de.cau.cs.kieler.overlapRemoval.helper.DelaunayGraph;
import de.cau.cs.kieler.overlapRemoval.helper.Point;

public interface DelaunayTriangulation {
	public DelaunayGraph buildGraph();
	public void draw(Graphics2D g2d);
	public void initTriangulation(Bounds bounds, List<Point> set);
}
