package de.cau.cs.kieler.overlapRemoval.helper

import java.util.ArrayList
import java.util.List
import java.util.Objects
import org.eclipse.xtend.lib.annotations.Accessors

class Triangle {
    @Accessors Point a;
    @Accessors Point b;
    @Accessors Point c;
    @Accessors List<Edge<Point>> edges = new ArrayList<Edge<Point>>();
    
    new (Point a, Point b, Point c) {
        this.a = a;
        this.b = b;
        this.c = c;
//        this.orderCW();
        this.createEdges();
    }
    
    new (List<Point> points) {
//        super(points);

        this.a = points.get(0);
        this.b = points.get(1);
        this.c = points.get(2);
        
//        this.orderCW();
        
        this.createEdges();
    }
    
    def createEdges() {
        val Edge<Point> AB = new DelaunayEdge(this.getA,this.getB);
        val Edge<Point> BC = new DelaunayEdge(this.getB,this.getC);
        val Edge<Point> CA = new DelaunayEdge(this.getC,this.getA);
        
        edges.add(AB);
        edges.add(BC);
        edges.add(CA);
    }
    
    def List<Point> getNodes() {
        val nodes = new ArrayList<Point>();
        
        nodes.add(this.a);
        nodes.add(this.b);
        nodes.add(this.c);
        
        return nodes;
    }
    
    def Point getCircumCenter() {
        
        // determine midpoints (average of x & y coordinates)
        val midAB = getMidPoint(this.getA, this.getB);
        val midBC = getMidPoint(this.getB, this.getC);

        // determine slope
        // we need the negative reciprocal of the slope to get the slope of the perpendicular bisector
        val slopeAB = -1 / getSlope(this.getA, this.getB);
        val slopeBC = -1 / getSlope(this.getB, this.getC);

        // y = mx + b
        // solve for b
        val bAB = midAB.YPos - slopeAB * midAB.XPos;
        val bBC = midBC.YPos - slopeBC * midBC.XPos;

        // solve for x & y
        // x = (b1 - b2) / (m2 - m1)
        val x = (bAB - bBC) / (slopeBC - slopeAB);
        val circumcenter = new Point(x,(slopeAB * x) + bAB);

        return circumcenter;
    }
    
    
    
    def Point getMidPoint(Point A, Point B) {
        return new Point((A.XPos+B.XPos)/2, (A.YPos+B.YPos)/2);
    }
    
    
    
    def double getSlope(Point from, Point to) {
        return (to.YPos - from.YPos) / (to.XPos - from.XPos);
    }
    
    def boolean IsInCircle(Point point, Point center, double radius) {
        // could also use the pythagorean theorem for this
        return point.dist(center) - radius < (1e-10);
    }
    
    def boolean inside(Point point) {
        val whPA = new DelaunayEdge(point,this.a);
        val whPB = new DelaunayEdge(point,this.b);
        val whPC = new DelaunayEdge(point,this.c);
        
        if(!whPA.sameSide(this.b, this.c) && !whPB.sameSide(this.c, this.a) && !whPC.sameSide(this.a, this.b)) {
            return true;

//        } else if(isBetween(this.a, point, this.b) || isBetween(this.a, point, this.c) || isBetween(this.c, point, this.b)) {
//            return true;
        } else {
            return false
        }
    }
    
    def boolean containsPoint(Point p) {
        return this.a.equals(p) || this.b.equals(p) || this.c.equals(p);
    }
    
    def Point getMissingPoint(Point a, Point b) {
        if((this.a.equals(a) && this.b.equals(b)) || (this.a.equals(b) && this.b.equals(a))) {
            return this.c;
        } else if((this.a.equals(a) && this.c.equals(b)) || (this.a.equals(b) && this.c.equals(a))) {
            return this.b;
        } else if((this.c.equals(a) && this.b.equals(b)) || (this.c.equals(b) && this.b.equals(a))) {
            return this.a;
        } else {
            println("was?")
            return null;
        }
    }
    
    def List<Point> getOtherNodes(Point point) {
        val points = this.getNodes();
        
        points.remove(point);
        
        return points;
    }
    
    def List<Edge<Point>> edgesOfPoint(Point point) {
        val routingEdges = new ArrayList<Edge<Point>>();
        
        for(Edge<Point> edge : this.edges) {
            if(edge.hasVertex(point)) {
                routingEdges.add(edge);
            }
        }
        
        return routingEdges;
    }
    
    def boolean hasEdge(Edge<Point> edge) {
        return this.edges.contains(edge)
            || this.edges.contains(edge.revert());
    }

    def boolean hasEdge(Point p0, Point p1) {
        val lookForEdge = new DelaunayEdge(p0,p1);
        return this.hasEdge(lookForEdge);
    }
       
    def Edge<Point> getEdge(Point p1, Point p2) {
        val index = this.getEdges.indexOf(new DelaunayEdge(p1,p2));
        return this.getEdges.get(index);
    }   
        
    override toString() {
        return "A: " + this.a + " B: "+ this.b + " C: "+this.c;
    }
    
       override int hashCode()  
   {  
      return Objects.hash(this.a, this.b, this.c);  
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
      
      val Triangle other = obj as Triangle;  
      
      return    this.containsPoint(other.a)  
             && this.containsPoint(other.b)
             && this.containsPoint(other.c); 
   } 
   
   def boolean isBetween(Point source, Point checkBetween, Point target) {
       return source.distance(checkBetween) + target.distance(checkBetween) == source.distance(target);
   }
   
   def boolean isTriangle() {
       if(this.a == null || this.b == null || this.c == null) {
           return false;
       }
       
       if(this.a.XPos == this.b.XPos && this.c.XPos == this.b.XPos) {
           return false; 
       }
       
       if(this.a.YPos == this.b.YPos && this.c.YPos == this.b.YPos) {
           return false; 
       }
       
       return true;
   }
}