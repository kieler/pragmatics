/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.ptolemy.klighd.transformation

import de.cau.cs.kieler.core.annotations.Annotation
import de.cau.cs.kieler.core.annotations.AnnotationsFactory
import de.cau.cs.kieler.core.annotations.StringAnnotation
import de.cau.cs.kieler.core.annotations.TypedStringAnnotation
import de.cau.cs.kieler.core.kgraph.KEdge
import de.cau.cs.kieler.core.kgraph.KGraphElement
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout
import de.cau.cs.kieler.ptolemy.klighd.PtolemyProperties
import java.util.List

/**
 * Utility methods regarding annotations used by the Ptolemy to KGraph transformation.
 * 
 * @author cds
 * @kieler.rating yellow 2012-07-10 KI-15 cmot, grh
 */
class AnnotationExtensions {
    
    /**
     * Returns the annotations stored in the Ptolemy annotations property of the given KGraph element.
     * This method looks for the property in the element's KShapeLayout.
     * 
     * @param element the KGraph element.
     * @return the annotations list or {@code null} if none was found.
     */
    def dispatch List<Annotation> getAnnotations(KGraphElement element) {
        return element.getData(typeof(KShapeLayout))?.getProperty(PtolemyProperties::PT_ANNOTATIONS);
    }
    
    /**
     * Returns the annotations stored in the Ptolemy annotations property of the given KGraph edge.
     * This method looks for the property in the edge's KEdgeLayout.
     * 
     * @param element the KGraph edge.
     * @return the annotations list or {@code null} if none was found.
     */
    def dispatch List<Annotation> getAnnotations(KEdge element) {
        return element.getData(typeof(KEdgeLayout))?.getProperty(PtolemyProperties::PT_ANNOTATIONS);
    }
    
    /**
     * Returns the annotation with the given key from the given KGraph element.
     * 
     * @param element the KGraph element.
     * @param key the key of the annotation to return.
     * @return the annotation or {@code null} if there is none with the given key.
     */
    def Annotation getAnnotation(KGraphElement element, String key) {
        return getAnnotations(element).findFirst([a | a.name.equals(key)])
    }
    
    /**
     * Checks if the given KGraph element has a Ptolemy annotation of the given key. This method queries
     * the shape layout of the given element.
     * 
     * @param element the KGraph element.
     * @param key key of the annotation to look for.
     * @return {@code true} if such an annotation exists, {@code false} otherwise.
     */
    def boolean hasAnnotation(KGraphElement element, String key) {
        return getAnnotation(element, key) != null
    }
    
    /**
     * Adds an annotation with the given key to the given KGraph element, if no annotation with that
     * key already exists.
     * 
     * @param element the KGraph element.
     * @param key the annotation's key.
     */
    def void addAnnotation(KGraphElement element, String key) {
        if (!hasAnnotation(element, key)) {
            val Annotation annotation = AnnotationsFactory::eINSTANCE.createAnnotation();
            annotation.setName(key);
            
            getAnnotations(element).add(annotation);
        }
    }
    
    /**
     * Removes the annotation with the given key, if any exists, from the given KGraph element.
     * 
     * @param element the KGraph element.
     * @param key the annotation's key.
     */
    def void removeAnnotation(KGraphElement element, String key) {
        val annotations = getAnnotations(element)
        val annotation = getAnnotation(element, key)
        if (annotation != null) {
            annotations.remove(annotation);
        }
    }

    /**
     * Adds an annotation with the given key and value to the given KGraph element, if no annotation
     * with that key already exists.
     * 
     * @param element the KGraph element.
     * @param key the annotation's key.
     * @param value the annotation's value.
     */
    def void addStringAnnotation(KGraphElement element, String key, String value) {
        if (!hasAnnotation(element, key)) {
            val StringAnnotation annotation = AnnotationsFactory::eINSTANCE.createStringAnnotation()
            annotation.setName(key)
            annotation.setValue(value)
            
            getAnnotations(element).add(annotation)
        }
    }
    
    /**
     * Returns the value of the string annotation with the given key, if any.
     * 
     * @param element the KGraph element to fetch the annotation from.
     * @param key the annotation's key.
     * @return the annotation's value, if it exists, or the empty string if it doesn't.
     */
    def String getStringAnnotationValue(KGraphElement element, String key) {
        val annotation = getAnnotation(element, key)
        
        if (annotation instanceof StringAnnotation) {
            return (annotation as StringAnnotation).value
        } else {
            return ""
        }
    }

    /**
     * Adds an annotation with the given key, type, and value to the given KGraph element, if no
     * annotation with that key already exists.
     * 
     * @param element the KGraph element.
     * @param key the annotation's key.
     * @param type the annotation's tyape.
     * @param value the annotation's value.
     */
    def void addTypedStringAnnotation(KGraphElement element, String key, String type, String value) {
        if (!hasAnnotation(element, key)) {
            val TypedStringAnnotation annotation =
                AnnotationsFactory::eINSTANCE.createTypedStringAnnotation()
            annotation.name = key
            annotation.type = type
            annotation.value = value
            
            getAnnotations(element).add(annotation)
        }
    }
    
}
