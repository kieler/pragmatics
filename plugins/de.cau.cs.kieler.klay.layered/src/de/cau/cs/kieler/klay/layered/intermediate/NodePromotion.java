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

    /* Layering of the graph which is modified. */
    private List<Layer> layering;

    /** Holds all nodes of the graph that have incoming edges. */
    private List<LNode> nodesWithInEdges;

    /**
     * {@inheritDoc}
     */
    public void process(final LGraph layeredGraph, final IKielerProgressMonitor progressMonitor) {

        progressMonitor.begin("Node-promotion heuristic", 1);

        setIndexforID(layeredGraph);
        System.out.println("Vorher: " + layeredGraph.getLayers());
        java.util.Collections.reverse(layeredGraph.getLayers());
        System.out.println("Nachher: " + layeredGraph.getLayers());

        layering = Lists.newArrayList(layeredGraph.getLayers());
        nodesWithInEdges = Lists.newArrayList();

        int promotions;

        List<Layer> layeringBackUp = Lists.newArrayList(layering);

        determineNodesWithInEdges(layeredGraph);
        do {
            promotions = 0;
            // Start promotion for all nodes with incoming edges.

            debugStuff();
            for (LNode nodeWithE : nodesWithInEdges) {
                int dummydiff = promoteNode(nodeWithE, layeredGraph);
                if (dummydiff < 0) {
                    if (layering.get(0).getNodes().isEmpty()) {
                        layering = Lists.newArrayList(layeringBackUp);
                    } else {
                        promotions++;
                        layeringBackUp = Lists.newArrayList(layering);
                        System.out.println(layeredGraph.getLayers());
                    }
                } else {
                    layering = Lists.newArrayList(layeringBackUp);
                }
            }
            System.out.println("rom " + promotions);
            if (layering.get(0).getNodes().isEmpty()) {
                promotions = 0;
            }

        } while (promotions != 0);

        System.out.println("RAUS!");
        System.out.println(layering);
        List<Layer> lap = Lists.newArrayList();
        for (Layer lay : layering) {
            if (lay.getNodes().isEmpty())
                lap.add(lay);
        }
        layering.removeAll(lap);
        java.util.Collections.reverse(layering);
        layeredGraph.getLayers().clear();
        layeredGraph.getLayers().addAll(layering);
        

        System.out.println(layeredGraph.getLayers());
        progressMonitor.done();

    }

    /**
     * @param layeredGraph
     */
    private void reverseLayering(LGraph layeredGraph) {
        List<Layer> temp = Lists.newArrayList();
        java.util.Collections.reverse(layeredGraph.getLayers());
        for (Layer layer : layeredGraph.getLayers()) {
            Layer newLayer = new Layer(layeredGraph);
            for (LNode node : layer.getNodes()) {
                newLayer.id = node.getLayer().id;
            }
            temp.add(newLayer);
        }
        System.out.println("Meine layer: ");
        layeredGraph.getLayers().clear();
        for (Layer layer : temp) {
            layeredGraph.getLayers().set(layer.id, layer);
        }
        System.out.println(layeredGraph.getLayers());
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
            // System.out.println("Layerindex: " + layer.getIndex());
            // System.out.println("Layer reverse: " + index);
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
            masterLayer.id = layeredGraph.getLayers().size();
            layeredGraph.getLayers().add(masterLayer);
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
                System.out.println("Layer: " + nodey.getLayer().id + "; node: " + nodey.getName());
            }
        }

    }

}
