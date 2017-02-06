package de.cau.cs.kieler.overlapRemoval.helper;

import java.awt.Graphics2D;
import java.util.Objects;

public class DelaunayEdge extends Edge<Point> {

    public DelaunayEdge(Point source, Point target) {
        super(source, target, (int) source.distance(target), true);
    }

	@Override
	public void draw(Graphics2D g2d) {
		 g2d.drawLine((int) this.getSource().getXPos(), (int) this.getSource().getYPos(), (int) this.getTarget().getXPos(), (int) this.getTarget().getYPos());
	}
	
	@Override
	public boolean equals(Object obj)   
    {  
      if (obj == null)  
      {  
         return false;  
      }  
      if (getClass() != obj.getClass())  
      {  
         return false;  
      }  
      final DelaunayEdge other = (DelaunayEdge) obj;  
      
       return    ((Objects.equals(this.getSource(), other.getSource())        
    		  && (Objects.equals(this.getTarget(), other.getTarget())))
    	|| 		 (Objects.equals(this.getSource(), other.getTarget())        
    		  && (Objects.equals(this.getTarget(), other.getSource()))));     

   }  
   
	@Override
    public int hashCode()  
   {  
      return Objects.hash(this.getSource(), this.getTarget());  
   } 
	
	public DelaunayEdge revert() {
	    return new DelaunayEdge(this.getTarget(), this.getSource());
	}
	
	@Override
    public boolean sameSide(Point a, Point b) {
        return a.isRightOf(this) && b.isRightOf(this);
    }
}