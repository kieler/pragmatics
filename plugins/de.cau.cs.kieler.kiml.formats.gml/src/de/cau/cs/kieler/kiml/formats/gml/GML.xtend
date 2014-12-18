/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2014 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.formats.gml

import java.util.List
import org.eclipse.emf.ecore.impl.EObjectImpl
import org.eclipse.xtend.lib.annotations.Data

/**
 * @author uru
 */
 
 class GMLModel extends EObjectImpl {
    List<Element> elements = newArrayList
    def getElements() { return elements }
    
    override toString() {
        "[\n" + elements.map[it.toString].join(",\n") + "\n]"
    }
 }
 
 @Data abstract class Element {
    Element container 
    String key
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
 
 
@Data class StringElement extends Element {
    String value
}

@Data class NumberElement extends Element {
    Number number
    override def String getValue() { return number.toString }
}

@Data class CollectionElement extends Element {
    new(Element parent, String key) {
        super(parent, key)
    }
    override def String getValue() { return null }
    List<Element> elements = newArrayList
}