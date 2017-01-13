package de.cau.cs.kieler.overlapRemoval.helper

import org.eclipse.xtend.lib.annotations.Accessors

class DelaunayEdge extends Edge<Point> {
    @Accessors boolean isDelaunayEdge;
    
    new (Point a, Point b) {
        super(a,b);
        this.isDelaunayEdge = true;
    }
}