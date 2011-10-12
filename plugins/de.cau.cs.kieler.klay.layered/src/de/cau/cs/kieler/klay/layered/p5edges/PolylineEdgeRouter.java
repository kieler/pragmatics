/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.layered.p5edges;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.klay.layered.ILayoutPhase;
import de.cau.cs.kieler.klay.layered.IntermediateProcessingStrategy;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;
import de.cau.cs.kieler.klay.layered.properties.NodeType;
import de.cau.cs.kieler.klay.layered.properties.Properties;

/**
 * Edge router module that draws edges with non-orthogonal line segments.
 * 
 * <dl>
 *   <dt>Precondition:</dt><dd>the graph has a proper layering with
 *     assigned node and port positions; the size of each layer is
 *     correctly set</dd>
 *   <dt>Postcondition:</dt><dd>each node is assigned a horizontal coordinate;
 *     the bend points of each edge are set; the width of the whole graph is set</dd>
 * </dl>
 *
 * @author msp
 */
public class PolylineEdgeRouter extends AbstractAlgorithm implements ILayoutPhase {
    
    /** maximal number of edges for which the default node spacing is taken. */
    private static final int MAX_EDGES = 3;
    
    /**
     * {@inheritDoc}
     */
    public IntermediateProcessingStrategy getIntermediateProcessingStrategy(final LayeredGraph graph) {
        return null;
    }
    
    /**
     * {@inheritDoc}
     */
    public void process(final LayeredGraph layeredGraph) {
        getMonitor().begin("Polyline edge routing", 1);
        float defspacing = layeredGraph.getProperty(Properties.OBJ_SPACING);
        
        // set horizontal positioning for each layer and add bend points
        double xpos = 0.0, spacing = 0.0;
        for (Layer layer : layeredGraph.getLayers()) {
            layer.placeNodes(xpos);
            int edgeCount = 0;
            for (LNode node : layer.getNodes()) {
                for (LPort port : node.getPorts()) {
                    edgeCount += port.getOutgoingEdges().size();
                }
                if (node.getProperty(Properties.NODE_TYPE) == NodeType.LONG_EDGE) {
                    LEdge edge = (LEdge) node.getProperty(Properties.ORIGIN);
                    if (isEndnode(node, true)) {
                        edge.getBendPoints().add(new KVector(xpos, node.getPosition().y));
                    }
                    if (isEndnode(node, false)) {
                        edge.getBendPoints().add(new KVector(xpos + layer.getSize().x,
                                node.getPosition().y));
                    }
                }
            }
            // determine placement of next layer based on the number of edges
            spacing = edgeCount <= MAX_EDGES ? defspacing
                       : defspacing * Math.sqrt(edgeCount);
            xpos += layer.getSize().x + spacing;
        }
        layeredGraph.getSize().x = xpos - spacing;
        getMonitor().done();
    }
    
    /**
     * Determines whether the given node is the first or the last node of
     * a series of aligned long edge parts, depending on the port type.
     * 
     * @param node a dummy node of a long edge
     * @param first if {@code true}, the result is true if the node is the
     *     first node of the long edge part; if {@code false}, the result is true if
     *     the node is the last node of the long edge part
     * @return true if the node is an end-node
     */
    private boolean isEndnode(final LNode node, final boolean first) {
        double bendPos = node.getPosition().y;
        for (LPort port : node.getPorts()) {
            Iterable<LPort> connectedPorts = first
                ? port.getPredecessorPorts()
                : port.getSuccessorPorts();
            
            for (LPort connectedPort : connectedPorts) {
                LNode otherNode = connectedPort.getNode();
                double otherPos = otherNode.getPosition().y + connectedPort.getPosition().y;
                if (Math.abs(otherPos - bendPos) > 1) {
                    return true;
                }
            }
        }
        return false;
    }

}
