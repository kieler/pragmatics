package de.cau.cs.kieler.overlapRemoval.helper

import de.cau.cs.kieler.klighd.kgraph.KNode
import java.awt.Graphics
import org.eclipse.xtend.lib.annotations.Accessors

class Point implements Comparable<Point> {
    @Accessors int id;
    @Accessors float xPos;
    @Accessors float yPos;
    @Accessors KNode node;
    @Accessors private static final float delta= (1e-10).floatValue;
    new(float xPos, float yPos) {
        this.XPos = xPos;
        this.YPos = yPos;
    }

    new(KNode node) {
        this.node = node;
        this.XPos = node.xpos;
        this.YPos = node.ypos;
    }

    new(Point point) {
        this.XPos = point.XPos;
        this.YPos = point.YPos;
    }

    def add(Point p) {
        return new Point(this.XPos+p.XPos, this.YPos+p.YPos);
    }
    def distance(Point point) {
        return Math::sqrt(Math::pow((this.XPos - point.XPos), 2) + Math::pow((this.YPos - point.YPos), 2));
    }

    def Point moved(float x0, float y0)
    {
        return new Point(this.XPos+x0, this.YPos+y0);
    }

    def Point neg()
    {
        return new Point(-this.XPos, -this.YPos);
    }

    def Point sub(Point p)
    {
        return add(p.neg());
    }

    def Point mul(float k)
    {
        return new Point(k*this.XPos, k*this.YPos);
    }

    // Verschieben eines Punktes
    def void offset(float x0, float y0)
    {
        this.XPos = this.XPos + x0;
        this.YPos = this.YPos + y0;
    }

    // Skalarprodukt
    def float dot(Point p)
    {
        return this.XPos*p.XPos+this.YPos*p.YPos;
    }

    // Kreuzprodukt
    def float cross(Point p)
    {
        return this.XPos*p.YPos-this.YPos*p.XPos;
    }

    // ---- Normen und Abstände ----

    // Betragsnorm
    def float norm1()
    {
        return Math::abs(this.XPos)+Math::abs(this.YPos);
    }

    // euklidische Norm
    def float norm2()
    {
        return Math::sqrt(Math::pow(this.XPos, 2) + Math::pow(this.YPos, 2)).floatValue;
    }

    // Maximumnorm
    def float norm8()
    {
        return Math::max(Math.abs(this.XPos), Math::abs(this.YPos));
    }

    // Manhattan-Abstand
    def float mdist(Point p)
    {
        return sub(p).norm1();
    }

    // euklidischer Abstand
    def float dist(Point p)
    {
        return sub(p).norm2();
    }

    // Kantenlänge quadratischer Bounding-Box
    def float bdist(Point p)
    {
        return sub(p).norm8();
    }

    def Point normalize()
    {
        return mul(1/norm2());
    }

    // ---- Hilfsfunktionen für konvexe Hülle ----

    def boolean isLower(Point p)
    {
        return this.YPos < p.YPos  || this.YPos == p.YPos && this.XPos < p.XPos;
    }

    def boolean isFurther(Point p)
    {
        return norm1()>p.norm1();
    }

    def boolean isBetween(Point p0, Point p1)
    {
        return p0.mdist(p1)>=mdist(p0)+mdist(p1);
    }

    def boolean isLess(Point p)
    {
        val f = this.cross(p);
        return f > 0 || f == 0 && isFurther(p);
    }

    def float area2(Point p0, Point p1)
    {
        return sub(p0).cross(sub(p1));
    }
    
    def float area2(Line g)
    {
        return area2(g.p0, g.p1);
    }

    def boolean isRightOf(Line g)
    {
        return area2(g.p0, g.p1)<0;
    }

    def boolean isConvex(Point p0, Point p1)
    {
        val f=area2(p0, p1);
        return f<0 || f==0 && !isBetween(p0, p1);
    }

    // ---- allgemeine Methoden ----

    def boolean isEqual(float a, float b)
    {
        return Math.abs(a-b) < delta;
    }

    def boolean equals(Point p)
    {
        return isEqual(this.XPos, p.XPos) && isEqual(this.YPos, p.YPos);
    }

    override String toString()
    {
        return this.XPos+" "+this.YPos;
    }
    
    def void draw(Graphics gr)
    {
        var java.awt.Point d = round();
        gr.fillRect((d.getX).intValue, (d.getY).intValue, 3, 3);
    }

    def java.awt.Point round()
    {
        return new java.awt.Point(Math::round(this.XPos).intValue, Math::round(this.YPos).intValue);
    }
    
    override compareTo(Point p) {
        if (this.XPos < p.XPos || (this.XPos < p.XPos && this.YPos < p.YPos)) {
            return -1;
        }
        else if (this.XPos > p.XPos) {
            return 1;
        }
        else {
            return 0;
        }
    }
   
}
