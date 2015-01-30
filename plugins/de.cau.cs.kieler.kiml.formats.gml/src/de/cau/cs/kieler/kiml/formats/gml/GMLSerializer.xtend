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

/**
 * Serializer for GML models.
 * 
 * @author uru
 * @see GMLModel
 * @see GMLParser
 */
final class GMLSerializer {
    
    static def serialize(GMLModel model) {
        return '''
            «model.elements.map[it.serializeInt].join("\n")»'''
    }
    
    static def serialize(CollectionElement root) {
        return '''
            «root.elements.map[it.serializeInt].join("\n")»'''     
    }
    
    static def CharSequence serializeInt(Element e) {
        switch(e) {
            CollectionElement:
                return '''  
                «e.key» [
                    «e.elements.map[serializeInt(it)].join("\n")»
                ]'''
            StringElement: 
                return '''«e.key» "«e.value»"'''
            default: 
                return '''«e.key» «e.value»'''
        }
    }
    
}