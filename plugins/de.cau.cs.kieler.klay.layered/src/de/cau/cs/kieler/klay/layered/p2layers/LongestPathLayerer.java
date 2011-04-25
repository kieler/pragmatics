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
package de.cau.cs.kieler.klay.layered.p2layers;

import java.util.Collection;
import java.util.EnumSet;
import java.util.List;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.klay.layered.ILayoutPhase;
import de.cau.cs.kieler.klay.layered.IntermediateProcessingStrategy;
import de.cau.cs.kieler.klay.layered.Properties;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;
import de.cau.cs.kieler.klay.layered.intermediate.IntermediateLayoutProcessor;

/**
 * The most basic layering algorithm, which assign layers according to the
 * longest path to a sink.
 * 
 * <dl>
 *   <dt>Precondition:</dt><dd>the graph has no cycles</dd>
 *   <dt>Postcondition:</dt><dd>all nodes have been assigned a layer such that
 *     edges connect only nodes from layers with increasing indices</dd>
 * </dl>
 *
 * @author msp
 */
public class LongestPathLayerer extends AbstractAlgorithm implements ILayoutPhase {
    
    /** intermediate processing strategy. */
    private static final IntermediateProcessingStrategy INTERMEDIATE_PROCESSING_STRATEGY =
        new IntermediateProcessingStrategy(IntermediateProcessingStrategy.BEFORE_PHASE_3,
                EnumSet.of(IntermediateLayoutProcessor.LAYER_CONSTRAINT_HANDLER));

    /** the layered graph to which layers are added. */
    private LayeredGraph layeredGraph;
    /** map of nodes to their height in the layering. */
    private int[] nodeHeights;
    
    /**
     * {@inheritDoc}
     */
    public IntermediateProcessingStrategy getIntermediateProcessingStrategy(final LayeredGraph graph) {
        return INTERMEDIATE_PROCESSING_STRATEGY;
    }
    
    /**
     * {@inheritDoc}
     */
    public void process(final LayeredGraph thelayeredGraph) {
        getMonitor().begin("Longest path layering", 1);
        
        layeredGraph = thelayeredGraph;
        Collection<LNode> nodes = layeredGraph.getLayerlessNodes();
        
        // enhance layering, if requested
        LayeringEnhancer enhancer = null;
        if (layeredGraph.getProperty(Properties.ENHANCE_LAYERING)) {
            enhancer = new LayeringEnhancer();
            enhancer.preProcess(nodes);
        }
        
        // support wide nodes, if requested
        IBigNodeHandler bigNodeHandler = null;
        if (layeredGraph.getProperty(Properties.DISTRIBUTE_NODES)) {
            bigNodeHandler = new BigNodeHandler();
            bigNodeHandler.splitWideNodes(nodes, thelayeredGraph);
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
        
        // nodes with layering constraints need to be moved
        Layer firstLayer = layeredGraph.getLayers().get(0);
        for (LNode node : nodes) {
            if (node.getProperty(Properties.LAYER_CONSTRAINT) == LayerConstraint.FIRST
                    && node.getLayer().getIndex() > 0) {
                boolean hasInputs = false;
                for (LPort port : node.getPorts()) {
                    if (!port.getIncomingEdges().isEmpty()) {
                        hasInputs = true;
                        break;
                    }
                }
                if (!hasInputs) {
                    node.setLayer(firstLayer);
                }
            }
        }
        
        // segmentate layering, if requested
        if (layeredGraph.getProperty(Properties.DISTRIBUTE_NODES)
                && layeredGraph.getProperty(Properties.SEGMENTATE_LAYERING)) {
            bigNodeHandler.segmentateLayering();
        }
        
        if (enhancer != null) {
            enhancer.postProcess();
        }
        
        // empty the list of unlayered nodes
        nodes.clear();
        
        // release the created resources
        this.layeredGraph = null;
        this.nodeHeights = null;
        
        getMonitor().done();
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
            for (LPort port : node.getPorts()) {
                for (LEdge edge : port.getOutgoingEdges()) {
                    LNode targetNode = edge.getTarget().getNode();
                    
                    // Beware of self-loops
                    if (edge.getSource().getNode() == targetNode) {
                        continue;
                    }
                    
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
