package de.cau.cs.kieler.overlapRemoval.helper

import java.awt.Graphics
import java.util.Objects
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

    def Line revert() {
        return new Line(p1, p0);
    }

    // liefert den Abstand des Punktes p zur Geraden
//    def float distanceOf(Point p)
//    {
//        return Math::abs(p.sub(this.p0).cross(getVector()));
//    }

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
    
    def boolean sameSide(Point a, Point b) {
        return a.isRightOf(this) && b.isRightOf(this);
    }
    
       override int hashCode()  
   {  
      return Objects.hash(this.p0, this.p1);  
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
      val Line other = obj as Line;  
      
      return    (Objects.equals(this, other) || Objects.equals(this, other.revert())); 
   } 

}