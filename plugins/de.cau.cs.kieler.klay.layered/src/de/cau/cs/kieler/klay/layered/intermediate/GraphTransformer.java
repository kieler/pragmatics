/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.layered.intermediate;

import java.util.LinkedList;
import java.util.List;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.core.math.KVectorChain;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.klay.layered.ILayoutProcessor;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LLabel;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.properties.NodeType;
import de.cau.cs.kieler.klay.layered.properties.Properties;

/**
 * A layout processor that is able to perform transformations on the coordinates of a graph.
 *
 * @author msp
 * @kieler.design 2012-08-10 chsch grh
 * @kieler.rating proposed yellow by msp
 */
public final class GraphTransformer implements ILayoutProcessor {

    /** definition of transformation modes. */
    public enum Mode {
        /** mirror the x coordinates of the graph. */
        MIRROR,
        /** transpose by swapping x and y coordinates. */
        TRANSPOSE,
        /** mirror and then transpose the graph. */
        MIRROR_AND_TRANSPOSE;
    }
    
    
    /** the configured mode of the graph transformer. */
    private final Mode mode;
    
    
    /**
     * Creates a graph transformer with the given mode.
     * 
     * @param themode the transformation mode
     */
    public GraphTransformer(final Mode themode) {
        this.mode = themode;
    }
    
    
    /**
     * {@inheritDoc}
     */
    public void process(final LGraph layeredGraph, final IKielerProgressMonitor monitor) {
        monitor.begin("Graph transformation (" + mode + ")", 1);
        List<LNode> nodes = new LinkedList<LNode>(layeredGraph.getLayerlessNodes());
        for (Layer layer : layeredGraph.getLayers()) {
            nodes.addAll(layer.getNodes());
        }
        
        switch(mode) {
        case MIRROR:
            mirror(nodes);
            break;
        case TRANSPOSE:
            transpose(nodes);
            transpose(layeredGraph.getOffset());
            transpose(layeredGraph.getSize());
            break;
        case MIRROR_AND_TRANSPOSE:
            mirror(nodes);
            transpose(nodes);
            transpose(layeredGraph.getOffset());
            transpose(layeredGraph.getSize());
            break;
        }
        monitor.done();
    }
    
    
    ///////////////////////////////////////////////////////////////////////////////
    // Mirror
    
    /**
     * Mirror the x coordinates of the given graph.
     * 
     * @param nodes the nodes of the graph to transpose
     */
    private void mirror(final List<LNode> nodes) {
        // determine the greatest x coordinate
        double maxx = 0;
        for (LNode node : nodes) {
            maxx = Math.max(maxx, node.getPosition().x + node.getSize().x);
        }
        
        // mirror all nodes, ports, edges, and labels
        for (LNode node : nodes) {
            mirror(node.getPosition(), maxx - node.getSize().x);
            KVector nodeSize = node.getSize();
            for (LPort port : node.getPorts()) {
                mirror(port.getPosition(), nodeSize.x);
                mirror(port.getAnchor(), port.getSize().x);
                mirrorPortSide(port);
                for (LEdge edge : port.getOutgoingEdges()) {
                    // Mirror bend points
                    for (KVector bendPoint : edge.getBendPoints()) {
                        mirror(bendPoint, maxx);
                    }
                    
                    // Mirror junction points
                    KVectorChain junctionPoints = edge.getProperty(LayoutOptions.JUNCTION_POINTS);
                    if (junctionPoints != null) {
                        for (KVector jp : junctionPoints) {
                            mirror(jp, maxx);
                        }
                    }
                    
                    // Mirror edge label positions
                    for (LLabel label : edge.getLabels()) {
                        mirror(label.getPosition(), maxx - label.getSize().x);
                    }
                }
                
                // Mirror port label positions
                for (LLabel label : port.getLabels()) {
                    mirror(label.getPosition(), -label.getSize().x);
                }
            }
            
            // External port dummy?
            if (node.getProperty(Properties.NODE_TYPE) == NodeType.EXTERNAL_PORT) {
                mirrorExternalPortSide(node);
            }
            
            // Mirror node label positions
            for (LLabel label : node.getLabels()) {
                mirror(label.getPosition(), nodeSize.x - label.getSize().x);
            }
        }
    }
    
    /**
     * Mirror the x coordinate of the given vector and add an offset.
     * 
     * @param v a vector
     * @param offset offset for the x coordinate
     */
    private void mirror(final KVector v, final double offset) {
        v.x = offset - v.x;
    }
    
    /**
     * Mirror the side of the given port. Undefined port sides are left untouched.
     * 
     * @param port the port.
     */
    private void mirrorPortSide(final LPort port) {
        port.setSide(port.getSide().opposed());
    }
    
    /**
     * Mirror the side of the external port represented by the given external port dummy.
     * 
     * @param node external port dummy node.
     */
    private void mirrorExternalPortSide(final LNode node) {
        node.setProperty(Properties.EXT_PORT_SIDE, node.getProperty(Properties.EXT_PORT_SIDE).opposed());
    }
    
    
    ///////////////////////////////////////////////////////////////////////////////
    // Transpose
    
    /**
     * Transpose the x and y coordinates of the given graph.
     * 
     * @param nodes the nodes of the graph to transpose
     */
    private void transpose(final List<LNode> nodes) {
        // Transpose nodes
        for (LNode node : nodes) {
            transpose(node.getPosition());
            transpose(node.getSize());
            
            // Transpose ports
            for (LPort port : node.getPorts()) {
                transpose(port.getPosition());
                transpose(port.getAnchor());
                transpose(port.getSize());
                transposePortSide(port);
                
                // Transpose edges
                for (LEdge edge : port.getOutgoingEdges()) {
                    // Transpose bend points
                    for (KVector bendPoint : edge.getBendPoints()) {
                        transpose(bendPoint);
                    }
                    
                    // Transpose junction points
                    KVectorChain junctionPoints = edge.getProperty(LayoutOptions.JUNCTION_POINTS);
                    if (junctionPoints != null) {
                        for (KVector jp : junctionPoints) {
                            transpose(jp);
                        }
                    }
                    
                    // Transpose edge labels
                    for (LLabel label : edge.getLabels()) {
                        transpose(label.getPosition());
                        transpose(label.getSize());
                    }
                }
                
                // Transpose port labels
                for (LLabel label : port.getLabels()) {
                    transpose(label.getPosition());
                    transpose(label.getSize());
                }
            }
            
            // External port dummy?
            if (node.getProperty(Properties.NODE_TYPE) == NodeType.EXTERNAL_PORT) {
                transposeExternalPortSide(node);
            }
            
            // Transpose node labels
            for (LLabel label : node.getLabels()) {
                transpose(label.getPosition());
                transpose(label.getSize());
            }
        }
    }
    
    /**
     * Transpose the x and y coordinate of the given vector.
     * 
     * @param v a vector
     */
    private void transpose(final KVector v) {
        double temp = v.x;
        v.x = v.y;
        v.y = temp;
    }
    
    /**
     * Transpose the side of the given port. Undefined port sides are left untouched.
     * 
     * @param p the port.
     */
    private void transposePortSide(final LPort p) {
        p.setSide(transposePortSide(p.getSide()));
    }
    
    /**
     * Returns the transposed side of the given port side.
     * 
     * @param side the side to transpose.
     * @return transposed port side.
     */
    private PortSide transposePortSide(final PortSide side) {
        switch (side) {
        case NORTH:
            return PortSide.WEST;
        
        case WEST:
            return PortSide.NORTH;
        
        case SOUTH:
            return PortSide.EAST;
        
        case EAST:
            return PortSide.SOUTH;
            
        default:
            return PortSide.UNDEFINED;    
        }
    }

    /**
     * Transpose the side of the external port represented by the given external port dummy.
     * 
     * @param node external port dummy node.
     */
    private void transposeExternalPortSide(final LNode node) {
        node.setProperty(Properties.EXT_PORT_SIDE,
                transposePortSide(node.getProperty(Properties.EXT_PORT_SIDE)));
    }

}
