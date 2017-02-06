package de.cau.cs.kieler.overlapRemoval.helper

import de.cau.cs.kieler.klighd.kgraph.KNode
import java.awt.Color
import java.awt.Graphics
import java.util.Objects
import org.eclipse.xtend.lib.annotations.Accessors

class Point implements Comparable<Point> {
    @Accessors int id;
    @Accessors double xPos;
    @Accessors double yPos;
    @Accessors KNode node;
    @Accessors private static final double delta = (1e-10);

    new(double xPos, double yPos) {
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
        return new Point(this.XPos + p.XPos, this.YPos + p.YPos);
    }

    def distance(Point point) {
        return Math::sqrt(Math::pow((this.XPos - point.XPos), 2) + Math::pow((this.YPos - point.YPos), 2));
    }

    def Point moved(double x0, double y0) {
        return new Point(this.XPos + x0, this.YPos + y0);
    }

    def Point neg() {
        return new Point(-this.XPos, -this.YPos);
    }

    def Point sub(Point p) {
        return add(p.neg());
    }

    def Point mul(double k) {
        return new Point(k * this.XPos, k * this.YPos);
    }

    // Skalarprodukt
    def double dot(Point p) {
        return this.XPos * p.XPos + this.YPos * p.YPos;
    }

    // Kreuzprodukt
    def double cross(Point p) {
        return this.XPos * p.YPos - this.YPos * p.XPos;
    }

    // ---- Normen und Abstände ----
    // Betragsnorm
    def double norm1() {
        return Math::abs(this.XPos) + Math::abs(this.YPos);
    }

    // euklidische Norm
    def double norm2() {
        return Math::sqrt(Math::pow(this.XPos, 2) + Math::pow(this.YPos, 2));
    }

    // Maximumnorm
    def double norm8() {
        return Math::max(Math.abs(this.XPos), Math::abs(this.YPos));
    }

    // Manhattan-Abstand
    def double mdist(Point p) {
        return sub(p).norm1();
    }

    // euklidischer Abstand
    def double dist(Point p) {
        return sub(p).norm2();
    }

    // Kantenlänge quadratischer Bounding-Box
    def double bdist(Point p) {
        return sub(p).norm8();
    }

    def Point normalize() {
        return mul(1 / norm2());
    }

    // ---- Hilfsfunktionen für konvexe Hülle ----
    def boolean isLower(Point p) {
        return this.YPos < p.YPos || this.YPos == p.YPos && this.XPos < p.XPos;
    }

    def boolean isFurther(Point p) {
        return norm1() > p.norm1();
    }

    def boolean isBetween(Point p0, Point p1) {
        return p0.mdist(p1) - (mdist(p0) + mdist(p1)) >= delta;
    }

    def boolean isLess(Point p) {
        val f = this.cross(p);
        return f > 0 || f == 0 && isFurther(p);
    }

    def double area2(Point p0, Point p1) {
        return sub(p0).cross(sub(p1));
    }

    def double area2(Line g) {
        return area2(g.p0, g.p1);
    }

    def boolean isRightOf(Line g) {
        return area2(g.p0, g.p1) < delta;
//        var rightValue = ((g.p1.YPos - g.p0.YPos)*(this.XPos - g.p0.XPos) - (g.p1.XPos - g.p0.XPos)*(this.YPos - g.p0.YPos));
//        return rightValue < 0;
    }

    def boolean isRightOf(Edge<Point> edge) {
        return area2(edge.source, edge.target) < delta;

    }

    def boolean isConvex(Point p0, Point p1) {
        val f = area2(p0, p1);
        return f < 0 || f == 0 && !isBetween(p0, p1);
    }

    override String toString() {
        return this.XPos + " " + this.YPos;
    }

    def void draw(Graphics gr) {
        var java.awt.Point d = round();
        gr.setColor(Color.black);
        gr.fillRect((d.getX).intValue, (d.getY).intValue, 3, 3);
    }

    def java.awt.Point round() {
        return new java.awt.Point(Math::round(this.XPos).intValue, Math::round(this.YPos).intValue);
    }

    override compareTo(Point p) {
        if (this.XPos < p.XPos || (this.XPos < p.XPos && this.YPos > p.YPos)) {
            return -1;
        } else if (this.XPos > p.XPos) {
            return 1;
        } else {
            return 0;
        }
    }


    
   override int hashCode()  
   {  
      return Objects.hash(this.XPos, this.YPos);  
   }  
   
       override equals(Object obj)   
    {  
      if (obj == null)  
      {  
         return false;  
      }  
      if (getClass() != obj.getClass())  
      {  
         return false;  
      }  
      val Point other = obj as Point;  
      
      return    Objects.equals(this.XPos, other.XPos)  
             && Objects.equals(this.YPos, other.YPos); 
   } 
}
