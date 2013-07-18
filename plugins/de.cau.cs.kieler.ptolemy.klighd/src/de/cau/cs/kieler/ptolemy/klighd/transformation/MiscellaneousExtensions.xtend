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
import de.cau.cs.kieler.core.kgraph.KGraphData
import de.cau.cs.kieler.core.kgraph.KGraphElement
import de.cau.cs.kieler.core.kgraph.KNode
import de.cau.cs.kieler.core.kgraph.KPort
import de.cau.cs.kieler.core.krendering.KPosition
import de.cau.cs.kieler.core.krendering.KRenderingFactory
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutDataFactory
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout
import java.util.List

/**
 * Utility methods used by the Ptolemy to KGraph transformation.
 * 
 * @author cds
 * @kieler.rating yellow 2012-07-10 KI-15 cmot, grh
 */
class MiscellaneousExtensions {
    /** We're using markers for some of this stuff. */
    @Inject extension MarkerExtensions
    
    
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
        val oldTarget = edge.target
        val oldTargetPort = edge.targetPort
        
        edge.source = oldTarget
        edge.sourcePort = oldTargetPort
        edge.target = oldSource
        edge.targetPort = oldSourcePort
    }
    
    /**
     * Returns the first edge in the list that is marked as having a fixed direction.
     * 
     * @param edges list of edges to check for directed edges.
     * @return the first edge of known direction that is found or {@code null} if none could be found.
     */
    def KEdge getFirstDirectedEdge(Iterable<KEdge> edges) {
        for (edge : edges) {
            if (!edge.isMarkedAsUndirected()) {
                return edge
            }
        }
        
        return null
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
    
    /**
     * Returns the layout information of the given graph element. This method is guaranteed to not
     * return {@code null}: if the element has no layout information yet, a new layout instance is
     * created and added to the element, depending on the element's type.
     * 
     * @param element the element whose layout information to return.
     * @return layout information of the element.
     */
    def KGraphData getLayout(KGraphElement element) {
        if (element instanceof KEdge) {
            // Return the edge layout, if any
            var edgeLayout = element.getData(typeof(KEdgeLayout))
            if (edgeLayout == null) {
                edgeLayout = KLayoutDataFactory::eINSTANCE.createKEdgeLayout()
                element.data.add(edgeLayout)
            }
            return edgeLayout
        } else {
            // Return the shape layout, if any
            var shapeLayout = element.getData(typeof(KShapeLayout))
            if (shapeLayout == null) {
                shapeLayout = KLayoutDataFactory::eINSTANCE.createKShapeLayout()
                element.data.add(shapeLayout)
            }
            return shapeLayout
        }
    }
    
    
    
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    // KRendering Utility Methods
    
    /**
     * Creates a position with the given absolute x and y coordinates.
     * 
     * @param x the x coordinate.
     * @param y the y coordinate.
     * @return KPosition representing the given absolut coordinates.
     */
    def KPosition createKPosition(float x, float y) {
        val xPos = KRenderingFactory::eINSTANCE.createKLeftPosition()
        xPos.absolute = x
        
        val yPos = KRenderingFactory::eINSTANCE.createKTopPosition()
        yPos.absolute = y
        
        val pos = KRenderingFactory::eINSTANCE.createKPosition()
        pos.x = xPos
        pos.y = yPos
        
        return pos
    }
}
