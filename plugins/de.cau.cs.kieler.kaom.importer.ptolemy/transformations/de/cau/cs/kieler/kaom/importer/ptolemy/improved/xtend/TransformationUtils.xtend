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
package de.cau.cs.kieler.kaom.importer.ptolemy.improved.xtend


import java.util.List

import de.cau.cs.kieler.core.annotations.Annotatable
import de.cau.cs.kieler.core.annotations.Annotation
import de.cau.cs.kieler.core.annotations.AnnotationsFactory
import de.cau.cs.kieler.core.annotations.StringAnnotation
import de.cau.cs.kieler.kaom.Entity
import de.cau.cs.kieler.kaom.Link
import de.cau.cs.kieler.kaom.Linkable
import de.cau.cs.kieler.kaom.Port


/**
 * Utility methods used by the Ptolemy to KAOM transformation.
 * 
 * @author cds
 */
class TransformationUtils {
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    // Annotations
    
    /**
     * Checks if the given annotatable object has an annotation of the given key.
     * 
     * @param annotatable the annotatable object.
     * @param key key of the annotation to look for.
     * @return {@code true} if such an annotation exists, {@code false} otherwise.
     */
    def boolean hasAnnotation(Annotatable annotatable, String key) {
        return annotatable.getAnnotation(key) != null
    }
    
    /**
     * Adds an annotation with the given key to the given annotatable object, if no annotation with that
     * key already exists.
     * 
     * @param annotatable object to add the annotation to.
     * @param key the annotation's key.
     */
    def void addAnnotation(Annotatable annotatable, String key) {
        if (!hasAnnotation(annotatable, key)) {
            val Annotation annotation = AnnotationsFactory::eINSTANCE.createAnnotation();
            annotation.setName(key);
            
            annotatable.getAnnotations().add(annotation);
        }
    }
    
    /**
     * Removes the annotation with the given key from the given annotatable object, if any exists.
     * 
     * @param annotatable object to remove the annotation from.
     * @param key the annotation's key.
     */
    def void removeAnnotation(Annotatable annotatable, String key) {
        val annotation = annotatable.getAnnotation(key)
        
        if (annotation != null) {
            annotatable.annotations.remove(annotation)
        }
    }

    /**
     * Adds an annotation with the given key and value to the given annotatable object, if no annotation
     * with that key already exists.
     * 
     * @param annotatable object to add the annotation to.
     * @param key the annotation's key.
     * @param value the annotation's value.
     */
    def void addStringAnnotation(Annotatable annotatable, String key, String value) {
        if (!hasAnnotation(annotatable, key)) {
            val StringAnnotation annotation = AnnotationsFactory::eINSTANCE.createStringAnnotation();
            annotation.setName(key);
            annotation.setValue(value);
            
            annotatable.getAnnotations().add(annotation);
        }
    }
    
    /**
     * Returns the value of the string annotation with the given key, if any.
     * 
     * @param annotatable the annotatable object to fetch the annotation from.
     * @param key the annotation's key.
     * @return the annotation's value, if it exists, or the empty string if it doesn't.
     */
    def String getStringAnnotationValue(Annotatable annotatable, String key) {
        val annotation = annotatable.getAnnotation(key)
        
        if (annotation instanceof StringAnnotation) {
            (annotation as StringAnnotation).value
        } else {
            ""
        }
    }
    
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    // Markings
    
    /**
     * Marks the given link as being undirected. This means that the link's direction has not been
     * inferred yet.
     * 
     * @param link the link to mark.
     * @param mark {@code true} if the link should be marked as being undirected, {@code false} if the
     *             marking should be removed.
     */
    def void markAsUndirected(Link link, boolean mark) {
        if (mark) {
            link.addAnnotation("undirected")
        } else {
            link.removeAnnotation("undirected")
        }
    }
    
    /**
     * Checks if the given link is marked as being undirected.
     * 
     * @param link the link to check.
     * @return {@code true} if the link is marked as undirected, {@code false} otherwise.
     */
    def boolean isMarkedAsUndirected(Link link) {
        link.hasAnnotation("undirected")
    }
    
    /**
     * Marks the given port as being an input port.
     * 
     * @param port the port to mark.
     */
    def void markAsInputPort(Port port) {
        port.addAnnotation("input")
    }
    
    /**
     * Checks if the given port is marked as being an input port.
     * 
     * @param port the port to check.
     * @return {@code true} if the port is marked as being an input port, {@code false} otherwise.
     */
    def boolean isMarkedAsInputPort(Port port) {
        port.hasAnnotation("input")
    }
    
    /**
     * Marks the given port as being an output port.
     * 
     * @param port the port to mark.
     */
    def void markAsOutputPort(Port port) {
        port.addAnnotation("output")
    }
    
    /**
     * Checks if the given port is marked as being an output port.
     * 
     * @param port the port to check.
     * @return {@code true} if the port is marked as being an output port, {@code false} otherwise.
     */
    def boolean isMarkedAsOutputPort(Port port) {
        port.hasAnnotation("output")
    }
    
    /**
     * Marks an entity as formerly having been an annotation. Some KAOM entities do not map to actors in
     * the original Ptolemy model, but to annotations. For example, the director used to execute a model
     * is persisted as an annotation in Ptolemy. In the corresponding KAOM model, we need it to be an
     * entity to display it correctly.
     * 
     * @param entity the entity to mark.
     * @param mark {@code true} if the entity should be marked, {@code false} if the marking should be
     *             removed.
     */
    def void markAsFormerAnnotationEntity(Entity entity, boolean mark) {
        if (mark) {
            entity.addAnnotation("annotationNode")
        } else {
            entity.removeAnnotation("annotationNode")
        }
    }
    
    /**
     * Checks if the given entity is marked as having been created from an annotation.
     * 
     * @param entity the entity to check.
     * @return {@code true} if it is marked, {@code false} otherwise.
     */
    def boolean isMarkedAsFormerAnnotationEntity(Entity entity) {
        entity.hasAnnotation("annotationNode")
    }
    
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    // Miscellaneous Utility Methods
    
    /**
     * Checks if the given linkable object has an incident link of unknown direction.
     * 
     * @param linkable the linkable object to check.
     * @return {@code true} if the linkable object has an incident link of unknown direction,
     *         {@code false} otherwise.
     */
    def boolean hasUnknownIncidentLink(Linkable linkable) {
        // Check incoming links
        for (link : linkable.incomingLinks) {
            if (link.isMarkedAsUndirected()) {
                return true
            }
        }
        
        // Check outgoing links
        for (link : linkable.outgoingLinks) {
            if (link.isMarkedAsUndirected()) {
                return true
            }
        }
        
        // No unknown link was found
        false
    }
    
    /**
     * Reverses the given link's direction.
     * 
     * @param link the link to reverse.
     */
    def void reverseLink(Link link) {
        val oldSource = link.source
        
        link.source = link.target
        link.target = oldSource
    }
    
    /**
     * Checks if the given list of links contains at least one link of known direction.
     * 
     * @param links list of links to check for directed links.
     * @return {@code true} if at least one link is of known direction.
     */
    def boolean containsDirectedLink(List<Link> links) {
        for (link : links) {
            if (!link.isMarkedAsUndirected()) {
                return true
            }
        }
        
        false
    }
    
    /**
     * Returns a list of all links incident to the given linkable object.
     * 
     * @param linkable the object whose incident links to return.
     * @return a list of all incident links.
     */
    def List<Link> getIncidentLinks(Linkable linkable) {
        val List<Link> incidentLinks = newArrayList()
        
        incidentLinks.addAll(linkable.incomingLinks)
        incidentLinks.addAll(linkable.outgoingLinks)
        
        incidentLinks
    }
}
