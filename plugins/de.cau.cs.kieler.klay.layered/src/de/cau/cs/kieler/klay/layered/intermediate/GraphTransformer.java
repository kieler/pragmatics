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

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.klay.layered.ILayoutProcessor;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LLabel;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;

/**
 * A layout processor that is able to perform transformations on the coordinates of a graph.
 *
 * @author msp
 * @kieler.rating 2012-07-10 proposed yellow msp
 */
public class GraphTransformer extends AbstractAlgorithm implements ILayoutProcessor {

    /** definition of transformation modes. */
    public enum Mode {
        /** mirror the x coordinates of the graph. */
        MIRROR,
        /** transpose by switching x and y coordinates. */
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
    public void process(final LayeredGraph layeredGraph) {
        getMonitor().begin("Graph transformation (" + mode + ")", 1);
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
        getMonitor().done();
    }
    
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
                    for (KVector bendPoint : edge.getBendPoints()) {
                        mirror(bendPoint, maxx);
                    }
                    for (LLabel label : edge.getLabels()) {
                        mirror(label.getPosition(), maxx - label.getSize().x);
                    }
                }
                for (LLabel label : port.getLabels()) {
                    mirror(label.getPosition(), -label.getSize().x);
                }
            }
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
        switch (port.getSide()) {
        case NORTH:
            port.setSide(PortSide.SOUTH);
            break;
        
        case SOUTH:
            port.setSide(PortSide.NORTH);
            break;
        
        case EAST:
            port.setSide(PortSide.WEST);
            break;
        
        case WEST:
            port.setSide(PortSide.EAST);
            break;
        }
    }
    
    /**
     * Transpose the x and y coordinates of the given graph.
     * 
     * @param nodes the nodes of the graph to transpose
     */
    private void transpose(final List<LNode> nodes) {
        for (LNode node : nodes) {
            transpose(node.getPosition());
            transpose(node.getSize());
            for (LPort port : node.getPorts()) {
                transpose(port.getPosition());
                transpose(port.getAnchor());
                transpose(port.getSize());
                transposePortSide(port);
                for (LEdge edge : port.getOutgoingEdges()) {
                    for (KVector bendPoint : edge.getBendPoints()) {
                        transpose(bendPoint);
                    }
                    for (LLabel label : edge.getLabels()) {
                        transpose(label.getPosition());
                        transpose(label.getSize());
                    }
                }
                for (LLabel label : port.getLabels()) {
                    transpose(label.getPosition());
                    transpose(label.getSize());
                }
            }
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
        switch (p.getSide()) {
        case NORTH:
            p.setSide(PortSide.WEST);
            break;
        
        case WEST:
            p.setSide(PortSide.NORTH);
            break;
        
        case SOUTH:
            p.setSide(PortSide.EAST);
            break;
        
        case EAST:
            p.setSide(PortSide.SOUTH);
            break;
        }
    }

}
