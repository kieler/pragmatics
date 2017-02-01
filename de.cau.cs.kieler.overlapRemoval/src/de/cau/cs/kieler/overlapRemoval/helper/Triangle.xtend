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
        this.orderCW();
        this.createEdges();
    }
    
    new (List<Point> points) {
//        super(points);

        this.a = points.get(0);
        this.b = points.get(1);
        this.c = points.get(2);
        
        this.orderCW();
        
        this.createEdges();
    }
    
    def List<Point> getNodes() {
        val List<Point> nodes = new ArrayList<Point>();
        nodes.add(this.a);
        nodes.add(this.b);
        nodes.add(this.c);
        
        return nodes;
    }
    
    def createEdges() {
        val Edge<Point> AB = new DelaunayEdge(this.getA,this.getB);
        val Edge<Point> BC = new DelaunayEdge(this.getB,this.getC);
        val Edge<Point> CA = new DelaunayEdge(this.getC,this.getA);
        
        edges.add(AB);
        edges.add(BC);
        edges.add(CA);
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
    
    def float getSlope(Point from, Point to) {
        return (to.YPos - from.YPos) / (to.XPos - from.XPos);
    }
    
    def boolean IsInCircle(Point point, Point center, float radius) {
        // could also use the pythagorean theorem for this
        return point.dist(center) < radius;
    }
    
    def boolean inside(Point point) {
        val whPA = new Line(point,this.a);
        val whPB = new Line(point,this.b);
        val whPC = new Line(point,this.c);
        
        if(!whPA.sameSide(this.b, this.c) && !whPB.sameSide(this.a, this.c) && !whPC.sameSide(this.a, this.b)) {
            return true;
            
        } else {
            return false
        }
    }
    
    def float getHeight() {
        return 0.5f*a.dist(b)*getBaseline().distanceOf(this.c);
    }
    
    def Line getBaseline() {
        if(a.dist(b) > a.dist(c) && a.dist(b) > b.dist(c)) {
           return new Line(a,b); 
        } else if(a.dist(c) > a.dist(b) && a.dist(b) > b.dist(c)) {
            return new Line(a,c);
        } else {
            return new Line(b,c);
        }
    }
    
    def orderCW() {
        val baseline = getBaseline();
        
        if(baseline.p0.equals(a) && baseline.p1.equals(c)) {
            this.c = this.b;
            this.b = baseline.p1;
        } else if(baseline.p0.equals(b) && baseline.p1.equals(c)) {
            this.c = this.a;
            this.a = this.b;
            this.b = baseline.p1;            
        }
    }
    
    def float getArea() {
        return 0.5f*this.a.dist(this.b)*this.getHeight();
    }
    
    def boolean containsPoint(Point p) {
        return p.equals(this.a) || p.equals(this.b) || p.equals(this.c);
    }
    
    def Point getMissingPoint(Point a, Point b) {
        if((this.a.equals(a) && this.b.equals(b)) || (this.a.equals(b) && this.b.equals(a))) {
            return this.c;
        } else if((this.a.equals(a) && this.c.equals(b)) || (this.a.equals(b) && this.c.equals(a))) {
            return this.b;
        } else if((this.c.equals(a) && this.b.equals(b)) || (this.c.equals(b) && this.b.equals(a))) {
            return this.a;
        } else {
            return null;
        }
    }
    
    def List<Point> getOtherNodes(Point point) {
        val points = new ArrayList<Point>();
        
        if(this.containsPoint(point) == false) {
            return null;
        }
        
        if(!this.getA.equals(point)) {
            points.add(this.getA);
        }
        if(!this.getB.equals(point)) {
            points.add(this.getB);
        }
        if(!this.getC.equals(point)) {
            points.add(this.getC);
        }
        
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
        return this.edges.contains(edge);
    }

    def boolean hasEdge(Point p0, Point p1) {
        return this.edges.contains(new DelaunayEdge(p0,p1));
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