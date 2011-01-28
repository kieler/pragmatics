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
import de.cau.cs.kieler.kiml.options.PortType;
import de.cau.cs.kieler.klay.layered.Properties;
import de.cau.cs.kieler.klay.layered.Properties.NodeType;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;

/**
 * Edge router module that draws edges with non-orthogonal line segments.
 *
 * @author msp
 */
public class PolylineEdgeRouter extends AbstractAlgorithm implements IEdgeRouter {
    
    /** maximal number of edges for which the default node spacing is taken. */
    private static final int MAX_EDGES = 3;
    
    /**
     * {@inheritDoc}
     */
    public void routeEdges(final LayeredGraph layeredGraph) {
        getMonitor().begin("Polyline edge routing", 1);
        float defspacing = layeredGraph.getProperty(Properties.OBJ_SPACING);
        
        // set horizontal positioning for each layer and add bend points
        double xpos = 0.0, spacing = 0.0;
        for (Layer layer : layeredGraph.getLayers()) {
            layer.placeNodes(xpos);
            int edgeCount = 0;
            for (LNode node : layer.getNodes()) {
                for (LPort port : node.getPorts(PortType.OUTPUT)) {
                    edgeCount += port.getEdges().size();
                }
                if (node.getProperty(Properties.NODE_TYPE) == NodeType.LONG_EDGE) {
                    LEdge edge = (LEdge) node.getProperty(Properties.ORIGIN);
                    if (isEndnode(node, PortType.INPUT)) {
                        edge.getBendPoints().add(new KVector(xpos, node.getPos().y));
                    }
                    if (isEndnode(node, PortType.OUTPUT)) {
                        edge.getBendPoints().add(new KVector(xpos + layer.getSize().x,
                                node.getPos().y));
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
     * @param portType if {@code INPUT}, the result is true if the node is the
     *     first node of the long edge part; if {@code OUTPUT}, the result is true if
     *     the node is the last node of the long edge part
     * @return true if the node is an end-node
     */
    private boolean isEndnode(final LNode node, final PortType portType) {
        for (LPort port : node.getPorts(portType)) {
            for (LPort connectedPort : port.getConnectedPorts()) {
                LNode otherNode = connectedPort.getNode();
                if (otherNode.getProperty(Properties.NODE_TYPE) == NodeType.LONG_EDGE
                        && node.getPos().y == otherNode.getPos().y) {
                    return false;
                }
            }
        }
        return true;
    }

}
