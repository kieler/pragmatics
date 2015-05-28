/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2015 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.layered.intermediate;

import java.util.List;

import com.google.common.collect.Lists;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.klay.layered.ILayoutProcessor;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.Layer;

/**
 * Approch of implementing the node-promotion heuristic of Nikola S. Nikolov and Alexandre Tarassov.
 * Goal is to achieve a layering with less dummy nodes. For this purpose the original graph nodes
 * are promoted recursively and the promotion is applied, if and only if this reduces the determined
 * count of dummy nodes.
 * 
 * @author amf
 *
 */
public class NodePromotion implements ILayoutProcessor {

    /** Layering of the graph which is modified. */
    private List<Layer> layering;

    /** Holds all nodes of the graph that have incoming edges. */
    private List<LNode> nodesWithInEdges;

    /**
     * {@inheritDoc}
     */
    public void process(final LGraph layeredGraph, final IKielerProgressMonitor progressMonitor) {

        progressMonitor.begin("Node-promotion heuristic", 1);

        setIndexforID(layeredGraph);
        
        layering = Lists.newArrayList(layeredGraph.getLayers());
        debugStuff();
        nodesWithInEdges = Lists.newArrayList();

        int promotions;

        List<Layer> layeringBackUp = Lists.newArrayList(layering);

        determineNodesWithInEdges(layeredGraph);
        int var = 0;
        do {
            promotions = 0;
            // Start promotion for all nodes with incoming edges.

            if (var < 3) {
                debugStuff();
                var++;
            }
            for (LNode nodeWithE : nodesWithInEdges) {
                if (promoteNode(nodeWithE, layeredGraph) < 0) {
                    promotions++;
                    layeringBackUp.clear();
                    layeringBackUp.addAll(layering);
                } else {
                    layering.clear();
                    layering.addAll(layeringBackUp);
                }
            }

        } while (promotions != 0);

        progressMonitor.done();

    }

    /**
     * @param layeredGraph
     */
    private void setIndexforID(final LGraph layeredGraph) {
        // for l in layers
        // l.id = index
        // index++
        // int revId = layers.size - l.id
        
        int index = layeredGraph.getLayers().size() - 1;
        for (Layer layer : layeredGraph.getLayers()) {
//            System.out.println("Layerindex: " + layer.getIndex());
//            System.out.println("Layer reverse: " + index);
            layer.id = index;
            index--;
        }
    }

    /**
     * @param layeredGraph
     */
    private void determineNodesWithInEdges(final LGraph layeredGraph) {
        for (Layer layer : layeredGraph.getLayers()) {
            for (LNode node : layer.getNodes()) {
                int degree = countEdges(node.getIncomingEdges());
                if (degree > 0) {
                    nodesWithInEdges.add(node);
                }
            }
        }
    }

    /**
     * Node-promotion heuristic for doing stuff.
     * 
     * @param node
     * @return
     */
    private int promoteNode(final LNode node, final LGraph layeredGraph) {

        int dummydiff = 0;
        // Calculate layernumber for promoted node.
        int nodeNewLayerPos = node.getLayer().id + 1;
        Layer masterLayer;

        for (LEdge edge : node.getIncomingEdges()) {
            LNode masterNode = edge.getSource().getNode();
            if (masterNode.getLayer().id == nodeNewLayerPos) {
                dummydiff = dummydiff + promoteNode(masterNode, layeredGraph);
            }
        }

        if (nodeNewLayerPos < layering.size()) {
            masterLayer = layering.get(nodeNewLayerPos);
        } else {
            masterLayer = new Layer(layeredGraph);
        }

        node.setLayer(masterLayer);
        int incomingCount = countEdges(node.getIncomingEdges());
        int outgoingCount = countEdges(node.getOutgoingEdges());
        dummydiff = dummydiff - incomingCount + outgoingCount;

        return dummydiff;
    }

    /**
     * Method for determining the size of a given Iterable.
     * 
     * @param incomingEdges
     * @return count of edges
     */
    private int countEdges(final Iterable<LEdge> edges) {
        int count = 0;
        for (LEdge edge : edges) {
            count++;
        }
        return count;
    }

    // To be deleted.
    private void debugStuff() {
        System.out.println("doobeedoo");
        for (Layer layer : layering) {
            for (LNode nodey : layer.getNodes()) {
                System.out.println("Layer: " + nodey.getLayer().id + "; node: "
                        + nodey.getName());
            }
        }

    }

}
