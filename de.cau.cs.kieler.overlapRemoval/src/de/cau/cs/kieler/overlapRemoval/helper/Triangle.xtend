package de.cau.cs.kieler.overlapRemoval.helper

import java.util.Arrays
import java.util.List
import org.eclipse.xtend.lib.annotations.Accessors

class Triangle extends Polygon {
    @Accessors Point a;
    @Accessors Point b;
    @Accessors Point c;
    
    new (Point a, Point b, Point c) {
        super(Arrays::asList(a,b,c));
        
        this.a = a;
        this.b = b;
        this.c = c;
        
        this.createEdges();
    }
    
    new (List<Point> points) {
        super(points);
        
        this.a = points.get(0);
        this.b = points.get(1);
        this.c = points.get(2);
        
        this.createEdges();
    }
    
    def createEdges() {
        val Edge<Point> AB = new Edge(a,b);
        val Edge<Point> BC = new Edge(b,c);
        val Edge<Point> AC = new Edge(a,c);
        
        edges.add(AB);
        edges.add(BC);
        edges.add(AC);
    }
    
    
}