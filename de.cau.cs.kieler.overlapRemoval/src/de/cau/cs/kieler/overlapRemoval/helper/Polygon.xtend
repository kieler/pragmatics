package de.cau.cs.kieler.overlapRemoval.helper

import java.util.List
import org.eclipse.xtend.lib.annotations.Accessors

abstract class Polygon {
    @Accessors List<Point> points;
    @Accessors List<Edge<Point>> edges;
    
    new (List<Point> points) {
        this.points.addAll(points);
    }
}