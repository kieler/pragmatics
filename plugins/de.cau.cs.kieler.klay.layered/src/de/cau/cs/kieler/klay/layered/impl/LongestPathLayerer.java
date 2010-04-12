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

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.kiml.layout.options.PortType;
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
    private Map<LNode, Integer> heightMap;
    
    /** load factor for hash map. */
    private static final float HASH_LOAD = 0.9f;
    
    /**
     * {@inheritDoc}
     */
    public void layer(final Collection<LNode> nodes, final LayeredGraph thelayeredGraph) {
        getMonitor().begin("Longest path layering", 1);
        heightMap = new HashMap<LNode, Integer>((int) (nodes.size() / HASH_LOAD) + 1, HASH_LOAD);
        layeredGraph = thelayeredGraph;
        
        // process all nodes
        for (LNode node : nodes) {
            visit(node);
        }

        getMonitor().done();
    }

    /**
     * Visit a node: if not already visited, find the longest path to a sink.
     * 
     * @param node node to visit
     * @return height of the given node in the layered graph
     */
    private int visit(final LNode node) {
        Integer height = heightMap.get(node);
        if (height != null) {
            // the node was already visited
            return height;
        } else {
            int maxHeight = 1;
            for (LPort port : node.getPorts(PortType.OUTPUT)) {
                for (LEdge edge : port.getEdges()) {
                    LNode targetNode = edge.getTarget().getOwner();
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
        layers.get(layers.size() - height).getNodes().add(node);
    }

}
