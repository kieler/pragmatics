/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2014 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.formats.gml

import java.util.List
import org.eclipse.emf.ecore.impl.EObjectImpl
import org.eclipse.xtend.lib.annotations.Data

/**
 * Root element of GML models. Inherits from {@link EObjectImpl} 
 * such that it can be used within an EMF context.
 */
 class GMLModel extends EObjectImpl {
    List<Element> elements = newArrayList
    def getElements() { return elements }
    
    override toString() {
        "[\n" + elements.map[it.toString].join(",\n") + "\n]"
    }
 }
 
 /**
  * Abstract class for GML Elements. Every element 
  * consists of a key and either a value or a collection of
  * further elements.
  * All valued elements have to implement a method that 
  * returns the value in form of a String.
  */
 @Data abstract class Element {
    public Element container 
    public String key
    abstract def String getValue()
    

    /**
     * Two element instances with the same key and 
     * the same container are still different objects. 
     */    
      override def hashCode() {
        return super.hashCode
    }

    /**
     * Two element instances with the same key and 
     * the same container are still different objects. 
     */   
    override equals(Object obj) {
        return super.equals(obj)
    }
}
 
/**
 * A StringElement has a String as value.
 */
@Data class StringElement extends Element {
    String value
}

/**
 * A NumberElement has either an integer 
 * or double as value.
 */
@Data class NumberElement extends Element {
    Number number
    override def String getValue() { return number.toString }
}

/**
 * A CollectionElement comprises of a key and 
 * a collection of arbitrary elements.
 */
@Data class CollectionElement extends Element {
    new(Element parent, String key) {
        super(parent, key)
    }
    override def String getValue() { return null }
    List<Element> elements = newArrayList
}