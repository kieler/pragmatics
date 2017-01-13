package de.cau.cs.kieler.overlapRemoval.helper

import java.awt.Graphics
import org.eclipse.xtend.lib.annotations.Accessors

class Line {
    @Accessors Point p0;
    @Accessors Point p1;

    new(Point p0, Point p1)
    {
        this.p0 = p0;
        this.p1 = p1;
    }

    def Point getVector()
    {
        return this.p1.sub(p0).normalize();
    }

    // liefert den Abstand des Punktes p zur Geraden
    def float distanceOf(Point p)
    {
        return Math::abs(p.sub(this.p0).cross(getVector()));
    }

    override String toString()
    {
        return this.p0+" "+ this.p1;
    }
    
    def void draw(Graphics gr)
    {
        var java.awt.Point q0;
        var java.awt.Point q1;        
        q0=this.getP0.round();        
        q1=this.getP1.round();        
        gr.drawLine(q0.x, q0.y, q1.x, q1.y);
    }

}