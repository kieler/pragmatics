package de.cau.cs.kieler.overlapRemoval.helper

import java.awt.Graphics2D
import java.util.Objects
import org.eclipse.xtend.lib.annotations.Accessors

abstract class Edge<V> {
	@Accessors var V source
	@Accessors var V target
	@Accessors var float weight
    @Accessors var boolean undirected
    
	new(V pSource, V pTarget) {
		this.source = pSource
		this.target = pTarget
		this.weight = 1
		this.undirected = true
	}
	
	new(V pSource, V pTarget, int pWeight) {
		this.source = pSource
		this.target = pTarget
		this.weight = pWeight
	}
	
	def boolean hasVertex(V vertex) {
		return (source.equals(vertex) || target.equals(vertex))
	}
	
	
	def V getPath(V pSource) {
		if(source.equals(pSource)) {
			return target
		} else if (target.equals(pSource)) {
			return source
		}
		
		return null
	}
	
	def void increase() {
		weight = weight + 1 
	}
	
	def void decrease() {
		weight = weight - 1 
	}
	
//	def Edge<V> swapVertices() {
//		return new Edge<V>(target, source)
//	}
 	
	override String toString() {
		return "Source: " + source + " Target: " + target
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
      val Edge<V> other = obj as Edge<V>;  
      

      return    Objects.equals(this, other) 
            || Objects.equals(this, other.revert()); 
   }  
   
      override int hashCode()  
   {  
      return Objects.hash(this.source, this.target);  
   } 
   
//   def revert() {
//       return new Edge<V>(this.getTarget, this.getSource);
//   }
    def abstract boolean sameSide(Point a, Point b);
   
   def abstract void draw(Graphics2D g2d);
   def abstract Edge<V> revert();
}
