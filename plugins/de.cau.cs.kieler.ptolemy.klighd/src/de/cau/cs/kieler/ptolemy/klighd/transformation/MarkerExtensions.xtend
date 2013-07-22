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

import com.google.inject.Inject
import de.cau.cs.kieler.core.kgraph.KEdge
import de.cau.cs.kieler.core.kgraph.KGraphElement
import de.cau.cs.kieler.core.kgraph.KNode
import de.cau.cs.kieler.core.kgraph.KPort
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout
import de.cau.cs.kieler.kiml.options.LayoutOptions

import static de.cau.cs.kieler.ptolemy.klighd.transformation.TransformationConstants.*

/**
 * Utility methods used to mark elements by the Ptolemy to KGraph transformation.
 * 
 * @author cds
 * @kieler.rating yellow 2012-07-10 KI-15 cmot, grh
 */
class MarkerExtensions {
    /** We make use of annotations to mark stuff. */
    @Inject extension AnnotationExtensions
    
    
    /**
     * Marks the given KGraph element as originating from a Ptolemy model.
     * 
     * @param element the element to mark.
     */
    def void markAsPtolemyElement(KGraphElement element) {
        addAnnotation(element, ANNOTATION_LANGUAGE, ANNOTATION_LANGUAGE_PTOLEMY)
    }
    
    /**
     * Marks the given edge as being undirected. This means that the link's direction has not been
     * inferred yet.
     * 
     * @param edge the edge to mark.
     * @param mark {@code true} if the edge should be marked as being undirected, {@code false} if the
     *             marking should be removed.
     */
    def void markAsUndirected(KEdge edge, boolean mark) {
        if (mark) {
            edge.addAnnotation("_undirected")
        } else {
            edge.removeAnnotation("_undirected")
        }
    }
    
    /**
     * Checks if the given edge is marked as being undirected.
     * 
     * @param edge the edge to check.
     * @return {@code true} if the edge is marked as undirected, {@code false} otherwise.
     */
    def boolean isMarkedAsUndirected(KEdge edge) {
        return edge.hasAnnotation("_undirected")
    }
    
    /**
     * Marks the given port as being an input port.
     * 
     * @param port the port to mark.
     */
    def void markAsInputPort(KPort port) {
        port.addAnnotation("_input")
    }
    
    /**
     * Checks if the given port is marked as being an input port.
     * 
     * @param port the port to check.
     * @return {@code true} if the port is marked as being an input port, {@code false} otherwise.
     */
    def boolean isMarkedAsInputPort(KPort port) {
        return port.hasAnnotation("_input")
    }
    
    /**
     * Marks the given port as being an output port.
     * 
     * @param port the port to mark.
     */
    def void markAsOutputPort(KPort port) {
        port.addAnnotation("_output")
    }
    
    /**
     * Checks if the given port is marked as being an output port.
     * 
     * @param port the port to check.
     * @return {@code true} if the port is marked as being an output port, {@code false} otherwise.
     */
    def boolean isMarkedAsOutputPort(KPort port) {
        return port.hasAnnotation("_output")
    }
    
    /**
     * Marks a node as formerly having been an annotation. Some nodes do not map to actors in the
     * original Ptolemy model, but to annotations. For example, the director used to execute a model
     * is persisted as an annotation in Ptolemy. In the corresponding KGraph model, we need it to be a
     * node to display it correctly.
     * 
     * @param node the node to mark.
     */
    def void markAsFormerAnnotationNode(KNode node) {
        node.addAnnotation("_annotationNode")
    }
    
    /**
     * Checks if the given node is marked as having been created from an annotation.
     * 
     * @param node the node to check.
     * @return {@code true} if it is marked, {@code false} otherwise.
     */
    def boolean isMarkedAsFormerAnnotationNode(KNode node) {
        return node.hasAnnotation("_annotationNode")
    }
    
    /**
     * Marks the given node as being a hypernode.
     * 
     * @param node the node to be marked.
     */
    def void markAsHypernode(KNode node) {
        val shapeLayout = node.getData(typeof(KShapeLayout))
        shapeLayout.setProperty(LayoutOptions::HYPERNODE, true)
    }
    
    /**
     * Checks if the given node is marked as being a hypernode.
     * 
     * @param node the node to check.
     * @return {@code true} if the node is marked as being a hypernode, {@code false} otherwise.
     */
    def boolean isMarkedAsHypernode(KNode node) {
        val shapeLayout = node.getData(typeof(KShapeLayout))
        return shapeLayout.getProperty(LayoutOptions::HYPERNODE)
    }
    
    /**
     * Marks the given node as being a comment node.
     * 
     * @param node the node to be marked.
     */
    def void markAsComment(KNode node) {
        val shapeLayout = node.getData(typeof(KShapeLayout))
        shapeLayout.setProperty(LayoutOptions::COMMENT_BOX, true)
    }
    
    /**
     * Checks if the given node is marked as being a comment node.
     * 
     * @param node the node to check.
     * @return {@code true} if the node is marked as being a comment node, {@code false} otherwise.
     */
    def boolean isMarkedAsComment(KNode node) {
        val shapeLayout = node.getData(typeof(KShapeLayout))
        return shapeLayout.getProperty(LayoutOptions::COMMENT_BOX)
    }
    
    /**
     * Marks the given node as containing a state machine.
     * 
     * @param node the node to be marked.
     */
    def void markAsStateMachineContainer(KNode node) {
        node.addAnnotation("_stateMachineContainer")
    }
    
    /**
     * Checks if the given node is marked as containing a state machine.
     * 
     * @param node the node to check.
     * @return {@code true} if the node is marked as containing a state machine, {@code false}
     *         otherwise.
     */
    def boolean isMarkedAsStateMachineContainer(KNode node) {
        return node.hasAnnotation("_stateMachineContainer")
    }
    
    /**
     * Marks the given node as being a director node.
     * 
     * @param node the node to be marked.
     */
    def void markAsDirector(KNode node) {
        node.addAnnotation("_director")
    }
    
    /**
     * Checks if the given node is marked as being a director node.
     * 
     * @param node the node to check.
     * @return {@code true} if the node is a director node.
     */
    def boolean isMarkedAsDirector(KNode node) {
        return node.hasAnnotation("_director")
    }
    
    /**
     * Checks if the given node is a Ptolemy Const actor.
     * 
     * @param node the node to check.
     * @return {@code true} if the node represents a Const actor.
     */
    def boolean isMarkedAsConstActor(KNode node) {
        val propertyValue = node.getAnnotationValue(ANNOTATION_PTOLEMY_CLASS)
        if (propertyValue == null) {
            return false
        } else {
            return propertyValue.equals("ptolemy.actor.lib.Const")
                || propertyValue.equals("ptolemy.actor.lib.StringConst")
        }
    }
    
    /**
     * Checks if the given node is a Ptolemy state machine model state.
     * 
     * @param node the node to check.
     * @return {@code true} if the node represents a state.
     */
    def boolean isMarkedAsState(KNode node) {
        val propertyValue = node.getAnnotationValue(ANNOTATION_PTOLEMY_CLASS)
        if (propertyValue == null) {
            return false
        } else {
            return propertyValue.equals("ptolemy.domains.modal.kernel.State")
        }
    }
    
}
