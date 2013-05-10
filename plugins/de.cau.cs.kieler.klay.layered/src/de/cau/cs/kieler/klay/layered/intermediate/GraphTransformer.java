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
        /** mirror the coordinates of the graph. */
        MIRROR_X,
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
        case MIRROR_X:
            mirrorX(nodes);
            break;
        case TRANSPOSE:
            transpose(nodes);
            transpose(layeredGraph.getOffset());
            transpose(layeredGraph.getSize());
            break;
        case MIRROR_AND_TRANSPOSE:
            mirrorX(nodes);
            mirrorY(nodes);
            transpose(nodes);
            transpose(layeredGraph.getOffset());
            transpose(layeredGraph.getSize());
            break;
        }
        monitor.done();
    }
    
    
    ///////////////////////////////////////////////////////////////////////////////
    // Mirror Horizontally
    
    /**
     * Mirror the x coordinates of the given graph.
     * 
     * @param nodes the nodes of the graph to transpose
     */
    private void mirrorX(final List<LNode> nodes) {
        // determine the greatest x coordinate
        double maxx = 0;
        for (LNode node : nodes) {
            maxx = Math.max(maxx, node.getPosition().x + node.getSize().x);
        }
        
        // mirror all nodes, ports, edges, and labels
        for (LNode node : nodes) {
            mirrorX(node.getPosition(), maxx - node.getSize().x);
            KVector nodeSize = node.getSize();
            for (LPort port : node.getPorts()) {
                mirrorX(port.getPosition(), nodeSize.x - port.getSize().x);
                mirrorX(port.getAnchor(), port.getSize().x);
                mirrorPortSideX(port);
                for (LEdge edge : port.getOutgoingEdges()) {
                    // Mirror bend points
                    for (KVector bendPoint : edge.getBendPoints()) {
                        mirrorX(bendPoint, maxx);
                    }
                    
                    // Mirror junction points
                    KVectorChain junctionPoints = edge.getProperty(LayoutOptions.JUNCTION_POINTS);
                    if (junctionPoints != null) {
                        for (KVector jp : junctionPoints) {
                            mirrorX(jp, maxx);
                        }
                    }
                    
                    // Mirror edge label positions
                    for (LLabel label : edge.getLabels()) {
                        mirrorX(label.getPosition(), maxx - label.getSize().x);
                    }
                }
                
                // Mirror port label positions
                for (LLabel label : port.getLabels()) {
                    mirrorX(label.getPosition(), -label.getSize().x);
                }
            }
            
            // External port dummy?
            if (node.getProperty(Properties.NODE_TYPE) == NodeType.EXTERNAL_PORT) {
                mirrorExternalPortSideX(node);
            }
            
            // Mirror node label positions
            for (LLabel label : node.getLabels()) {
                mirrorX(label.getPosition(), nodeSize.x - label.getSize().x);
            }
        }
    }
    
    /**
     * Mirror the x coordinate of the given vector and add an offset.
     * 
     * @param v a vector
     * @param offset offset for the x coordinate
     */
    private void mirrorX(final KVector v, final double offset) {
        v.x = offset - v.x;
    }
    
    /**
     * Mirror the side of the given port. Undefined port sides are left untouched.
     * 
     * @param port the port.
     */
    private void mirrorPortSideX(final LPort port) {
        port.setSide(getMirroredPortSideX(port.getSide()));
    }
    
    /**
     * Mirror the side of the external port represented by the given external port dummy.
     * 
     * @param node external port dummy node.
     */
    private void mirrorExternalPortSideX(final LNode node) {
        node.setProperty(Properties.EXT_PORT_SIDE,
                getMirroredPortSideX(node.getProperty(Properties.EXT_PORT_SIDE)));
    }
    
    /**
     * Returns the port side that is horizontally mirrored from the given side.
     * 
     * @param side the side whose horizontal opposite to return.
     * @return horizontal opposite of the given side.
     */
    private PortSide getMirroredPortSideX(final PortSide side) {
        switch (side) {
        case EAST:
            return PortSide.WEST;
            
        case WEST:
            return PortSide.EAST;
            
        default:
            return side;
        }
    }
    
    
    ///////////////////////////////////////////////////////////////////////////////
    // Mirror Vertically
    
    /**
     * Mirror the y coordinates of the given graph.
     * 
     * @param nodes the nodes of the graph to transpose
     */
    private void mirrorY(final List<LNode> nodes) {
        // determine the greatest y coordinate
        double maxy = 0;
        for (LNode node : nodes) {
            maxy = Math.max(maxy, node.getPosition().y + node.getSize().y);
        }
        
        // mirror all nodes, ports, edges, and labels
        for (LNode node : nodes) {
            mirrorY(node.getPosition(), maxy - node.getSize().y);
            KVector nodeSize = node.getSize();
            for (LPort port : node.getPorts()) {
                mirrorY(port.getPosition(), nodeSize.y - port.getSize().y);
                mirrorY(port.getAnchor(), port.getSize().y);
                mirrorPortSideY(port);
                for (LEdge edge : port.getOutgoingEdges()) {
                    // Mirror bend points
                    for (KVector bendPoint : edge.getBendPoints()) {
                        mirrorY(bendPoint, maxy);
                    }
                    
                    // Mirror junction points
                    KVectorChain junctionPoints = edge.getProperty(LayoutOptions.JUNCTION_POINTS);
                    if (junctionPoints != null) {
                        for (KVector jp : junctionPoints) {
                            mirrorY(jp, maxy);
                        }
                    }
                    
                    // Mirror edge label positions
                    for (LLabel label : edge.getLabels()) {
                        mirrorY(label.getPosition(), maxy - label.getSize().y);
                    }
                }
                
                // Mirror port label positions
                for (LLabel label : port.getLabels()) {
                    mirrorY(label.getPosition(), -label.getSize().y);
                }
            }
            
            // External port dummy?
            if (node.getProperty(Properties.NODE_TYPE) == NodeType.EXTERNAL_PORT) {
                mirrorExternalPortSideY(node);
            }
            
            // Mirror node label positions
            for (LLabel label : node.getLabels()) {
                mirrorY(label.getPosition(), nodeSize.y - label.getSize().y);
            }
        }
    }
    
    /**
     * Mirror the y coordinate of the given vector and add an offset.
     * 
     * @param v a vector
     * @param offset offset for the x coordinate
     */
    private void mirrorY(final KVector v, final double offset) {
        v.y = offset - v.y;
    }
    
    /**
     * Mirror the side of the given port. Undefined port sides are left untouched.
     * 
     * @param port the port.
     */
    private void mirrorPortSideY(final LPort port) {
        port.setSide(getMirroredPortSideY(port.getSide()));
    }
    
    /**
     * Mirror the side of the external port represented by the given external port dummy.
     * 
     * @param node external port dummy node.
     */
    private void mirrorExternalPortSideY(final LNode node) {
        node.setProperty(Properties.EXT_PORT_SIDE,
                getMirroredPortSideY(node.getProperty(Properties.EXT_PORT_SIDE)));
    }
    
    /**
     * Returns the port side that is vertically mirrored from the given side.
     * 
     * @param side the side whose vertical opposite to return.
     * @return vertical opposite of the given side.
     */
    private PortSide getMirroredPortSideY(final PortSide side) {
        switch (side) {
        case NORTH:
            return PortSide.SOUTH;
            
        case SOUTH:
            return PortSide.NORTH;
            
        default:
            return side;
        }
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
