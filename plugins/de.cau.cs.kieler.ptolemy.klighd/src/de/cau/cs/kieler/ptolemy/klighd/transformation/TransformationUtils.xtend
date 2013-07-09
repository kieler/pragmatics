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


import java.util.List

import de.cau.cs.kieler.core.annotations.Annotation
import de.cau.cs.kieler.core.annotations.AnnotationsFactory
import de.cau.cs.kieler.core.annotations.StringAnnotation
import de.cau.cs.kieler.core.kgraph.KEdge
import de.cau.cs.kieler.core.kgraph.KPort
import de.cau.cs.kieler.core.kgraph.KNode
import de.cau.cs.kieler.core.kgraph.KGraphElement
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout
import de.cau.cs.kieler.ptolemy.klighd.PtolemyProperties
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout
import de.cau.cs.kieler.kiml.options.LayoutOptions
import de.cau.cs.kieler.kiml.util.KimlUtil
import de.cau.cs.kieler.core.kgraph.KLabeledGraphElement
import static de.cau.cs.kieler.ptolemy.klighd.transformation.TransformationConstants.*

/**
 * Utility methods used by the Ptolemy to KAOM transformation.
 * 
 * @author cds
 * @kieler.rating yellow 2012-07-10 KI-15 cmot, grh
 */
class TransformationUtils {
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    // Element Names
    
    /**
     * Sets the name of the given element by setting the text of its first label.
     * 
     * @param element the element to be named.
     * @param name the name.
     */
    def setName(KLabeledGraphElement element, String name) {
        if (element.labels.empty) {
            KimlUtil::createInitializedLabel(element)
        }
        
        element.labels.get(0).text = name
    }
    
    /**
     * Returns the name of the given element. Its name is assumed to be the text of its first label.
     * 
     * @param element the element whose name to return.
     * @return the element's name or the empty string if none could be found.
     */
    def String getName(KLabeledGraphElement element) {
        if (element.labels.empty) {
            return ""
        } else {
            return element.labels.get(0).text
        }
    }
    
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    // Ports
    
    /**
     * Checks if the given name is a name usual for input ports.
     * 
     * @param name the name of the port to check.
     * @return {@code true} if the name is common for input ports, {@code false} otherwise.
     */
    def boolean isInputPortName(String name) {
        for (String inputPortName : PORT_NAMES_INPUT) {
            if (inputPortName.equals(name)) {
                return true
            }
        }
        
        return false
    }
    
    /**
     * Checks if the given name is a name usual for output ports.
     * 
     * @param name the name of the port to check.
     * @return {@code true} if the name is common for output ports, {@code false} otherwise.
     */
    def boolean isOutputPortName(String name) {
        for (String inputPortName : PORT_NAMES_OUTPUT) {
            if (inputPortName.equals(name)) {
                return true
            }
        }
        
        return false
    }
    
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    // Annotations
    
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
            val StringAnnotation annotation = AnnotationsFactory::eINSTANCE.createStringAnnotation();
            annotation.setName(key);
            annotation.setValue(value);
            
            getAnnotations(element).add(annotation);
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
    
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    // Markings
    
    /**
     * Marks the given KGraph element as originating from a Ptolemy model.
     * 
     * @param element the element to mark.
     */
    def void markAsPtolemyElement(KGraphElement element) {
        addStringAnnotation(element, ANNOTATION_LANGUAGE, ANNOTATION_LANGUAGE_PTOLEMY);
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
            edge.addAnnotation("undirected")
        } else {
            edge.removeAnnotation("undirected")
        }
    }
    
    /**
     * Checks if the given edge is marked as being undirected.
     * 
     * @param edge the edge to check.
     * @return {@code true} if the edge is marked as undirected, {@code false} otherwise.
     */
    def boolean isMarkedAsUndirected(KEdge edge) {
        return edge.hasAnnotation("undirected")
    }
    
    /**
     * Marks the given port as being an input port.
     * 
     * @param port the port to mark.
     */
    def void markAsInputPort(KPort port) {
        port.addAnnotation("input")
    }
    
    /**
     * Checks if the given port is marked as being an input port.
     * 
     * @param port the port to check.
     * @return {@code true} if the port is marked as being an input port, {@code false} otherwise.
     */
    def boolean isMarkedAsInputPort(KPort port) {
        return port.hasAnnotation("input")
    }
    
    /**
     * Marks the given port as being an output port.
     * 
     * @param port the port to mark.
     */
    def void markAsOutputPort(KPort port) {
        port.addAnnotation("output")
    }
    
    /**
     * Checks if the given port is marked as being an output port.
     * 
     * @param port the port to check.
     * @return {@code true} if the port is marked as being an output port, {@code false} otherwise.
     */
    def boolean isMarkedAsOutputPort(KPort port) {
        return port.hasAnnotation("output")
    }
    
    /**
     * Marks a node as formerly having been an annotation. Some nodes do not map to actors in the
     * original Ptolemy model, but to annotations. For example, the director used to execute a model
     * is persisted as an annotation in Ptolemy. In the corresponding KGraph model, we need it to be a
     * node to display it correctly.
     * 
     * @param node the node to mark.
     * @param mark {@code true} if the node should be marked, {@code false} if the marking should be
     *             removed.
     */
    def void markAsFormerAnnotationNode(KNode node, boolean mark) {
        if (mark) {
            node.addAnnotation("annotationNode")
        } else {
            node.removeAnnotation("annotationNode")
        }
    }
    
    /**
     * Checks if the given node is marked as having been created from an annotation.
     * 
     * @param node the node to check.
     * @return {@code true} if it is marked, {@code false} otherwise.
     */
    def boolean isMarkedAsFormerAnnotationNode(KNode node) {
        return node.hasAnnotation("annotationNode")
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
    
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    // Miscellaneous Utility Methods
    
    /**
     * Checks if the given port has an incident edge of unknown direction.
     * 
     * @param port the port to check.
     * @return {@code true} if the port has an incident edge of unknown direction,
     *         {@code false} otherwise.
     */
    def boolean hasUnknownIncidentEdge(KPort port) {
        for (edge : port.edges) {
            if (edge.isMarkedAsUndirected()) {
                return true
            }
        }
        
        return false
    }
    
    /**
     * Reverses the given edge's direction.
     * 
     * @param edge the edge to reverse.
     */
    def void reverseEdge(KEdge edge) {
        val oldSource = edge.source
        val oldSourcePort = edge.sourcePort
        
        edge.source = edge.target
        edge.sourcePort = edge.targetPort
        edge.target = oldSource
        edge.targetPort = oldSourcePort
    }
    
    /**
     * Checks if the given list of edges contains at least one edge of known direction.
     * 
     * @param edges list of edges to check for directed edges.
     * @return {@code true} if at least one edge is of known direction.
     */
    def boolean containsDirectedEdge(Iterable<KEdge> edges) {
        for (edge : edges) {
            if (!edge.isMarkedAsUndirected()) {
                return true
            }
        }
        
        return false
    }
    
    /**
     * Returns a list of all edges incident to the given node.
     * 
     * @param node the node whose incident edges to return.
     * @return a list of all incident edges.
     */
    def List<KEdge> getIncidentEdges(KNode node) {
        val List<KEdge> incidentEdges = newArrayList()
        
        incidentEdges.addAll(node.incomingEdges)
        incidentEdges.addAll(node.outgoingEdges)
        
        return incidentEdges
    }
}
