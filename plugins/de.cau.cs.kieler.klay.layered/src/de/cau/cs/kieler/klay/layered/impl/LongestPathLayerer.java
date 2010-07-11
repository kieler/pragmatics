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
package de.cau.cs.kieler.klay.layered.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.kiml.options.PortType;
import de.cau.cs.kieler.klay.layered.LayeredLayoutProvider;
import de.cau.cs.kieler.klay.layered.Properties;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;
import de.cau.cs.kieler.klay.layered.modules.ILayerer;

/**
 * The most basic layering algorithm, which assign layers according to the
 * longest path to a sink.
 *
 * @author msp
 */
public class LongestPathLayerer extends AbstractAlgorithm implements ILayerer {

    /** the layered graph to which layers are added. */
    private LayeredGraph layeredGraph;
    /** map of nodes to their height in the layering. */
    private int[] nodeHeights;
    /** minimal spacing between objects. */
    private float spacing = LayeredLayoutProvider.DEF_SPACING;
    
    /**
     * {@inheritDoc}
     */
    public void setSpacing(final float theSpacing) {
        this.spacing = theSpacing;
    }
    
    /**
     * {@inheritDoc}
     */
    public void layer(final Collection<LNode> nodes, final LayeredGraph thelayeredGraph) {
        getMonitor().begin("Longest path layering", 1);

        layeredGraph = thelayeredGraph;
        
        if (layeredGraph.getProperty(Properties.DISTRIBUTE_NODES)) {
            distributeBigNodes(nodes);
        }
        
        nodeHeights = new int[nodes.size()];
        int index = 0;
        for (LNode node : nodes) {
            node.id = index;
            nodeHeights[index] = -1;
            index++;
        }
        
        // process all nodes
        for (LNode node : nodes) {
            visit(node);
        }
        getMonitor().done();
    }
    
    /**
     * @param nodes Nodes of the Graph
     */
    public void distributeBigNodes(final Collection<LNode> nodes) {
        float avgWidth = 0.0f;
        // Compute average width of Nodes
        for (Iterator<LNode> nodeIter = nodes.iterator(); nodeIter.hasNext();) {
            LNode node = (LNode) nodeIter.next();
            float width = node.getSize().x;
            avgWidth += width;
        }
        avgWidth /= nodes.size();

        // List of dummy nodes to be inserted in the graph
        Collection<LNode> addNodes = new ArrayList<LNode>();
        for (Iterator<LNode> nodeIter = nodes.iterator(); nodeIter.hasNext();) {
            LNode node = (LNode) nodeIter.next();
            float width = node.getSize().x;
            float treshold = (2 * avgWidth) + spacing;
            int numDummys = 0;
            while (treshold <= width) {
                numDummys++;
                treshold += avgWidth + spacing;
            } 

            // All nodes in the segment get the same width (temporarily)
            float newWidth = (width - (numDummys * spacing)) / (numDummys + 1);
            node.getSize().x = newWidth;
            
            for (int d = 0; d < numDummys; d++) {
                // expand node by one dummynode
                LNode addNode = new LNode();
                addNode.getSize().y = node.getSize().y;
                addNode.getSize().x = newWidth;
                
                // Reassign ports
                Iterator<LPort> portIter = node.getPorts(PortType.OUTPUT).iterator();
                List<LPort> ports = new ArrayList<LPort>();
                while (portIter.hasNext()) {
                    LPort lPort = (LPort) portIter.next();
                    ports.add(lPort);
                }
                for (LPort port : ports) {
                    port.setNode(addNode);
                }
                
                // Add edge
                LPort outPort = new LPort(PortType.OUTPUT);
                LPort inPort = new LPort(PortType.INPUT);
                outPort.setNode(node);
                inPort.setNode(addNode);
                LEdge edge = new LEdge();
                edge.setSource(outPort);
                edge.setTarget(inPort);
                addNodes.add(addNode);
            }
        }
        nodes.addAll(addNodes);
    }

    /**
     * Visit a node: if not already visited, find the longest path to a sink.
     * 
     * @param node node to visit
     * @return height of the given node in the layered graph
     */
    private int visit(final LNode node) {
        int height = nodeHeights[node.id];
        if (height >= 0) {
            // the node was already visited
            return height;
        } else {
            int maxHeight = 1;
            for (LPort port : node.getPorts(PortType.OUTPUT)) {
                for (LEdge edge : port.getEdges()) {
                    LNode targetNode = edge.getTarget().getNode();
                    int targetHeight = visit(targetNode) + 1;
                    maxHeight = Math.max(maxHeight, targetHeight);
                }
            }
            putNode(node, maxHeight);
            return maxHeight;
        }
    }
    
    /**
     * Puts the given node into the layered graph.
     * 
     * @param node a node
     * @param height height of the layer where the node shall be added
     */
    private void putNode(final LNode node, final int height) {
        List<Layer> layers = layeredGraph.getLayers();
        for (int i = layers.size(); i < height; i++) {
            layers.add(0, new Layer(layeredGraph));
        }
        node.setLayer(layers.get(layers.size() - height));
        nodeHeights[node.id] = height;
    }

}
