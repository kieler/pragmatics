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
package de.cau.cs.kieler.klay.layered.p2layers;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.klay.layered.ILayoutProcessor;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.properties.InternalProperties;

/**
 * @author uru
 *
 */
public class HeuristicGLayPreprocessor implements ILayoutProcessor {

    /**
     * {@inheritDoc}
     */
    public void process(final LGraph layeredGraph, final IKielerProgressMonitor progressMonitor) {
        if (layeredGraph.getLayerlessNodes().isEmpty()) {
            return;
        }
        progressMonitor.begin("Heuristic General Layering", 1);

        // create auxiliary data structure for temporary layerings
        Layering layering = new Layering(layeredGraph.getLayerlessNodes().size());

        // ---------------------
        // #1 Create an initial layering where each layer holds a single node
        // ---------------------
        constructive(layeredGraph, layering);

        // ---------------------
        // #2 Combine as many layers as possible
        // ---------------------
//        compact(layeredGraph, layering);

        // ---------------------
        // #3 Optimize
        // ---------------------

        // ---------------------
        // #4 Create LGraph layers
        // ---------------------
//        Layer[] layers = new Layer[layering.layerCount];
//        for (int i = 0; i < layers.length; i++) {
//            layers[i] = new Layer(layeredGraph);
//            layeredGraph.getLayers().add(layers[i]);
//        }
//
//        // apply the computed layer assignment
//        Iterator<LNode> nodes = layeredGraph.getLayerlessNodes().iterator();
//        while (nodes.hasNext()) {
//            LNode node = nodes.next();
//            node.setLayer(layers[node.id]);
//
//            // remove from layerless nodes
//            nodes.remove();
//        }

        // ---------------------
        // #5 Mark backward pointing edges
        // ---------------------
        for (LNode n : layeredGraph.getLayerlessNodes()) {
            for (LEdge e : Lists.newArrayList(n.getOutgoingEdges())) {
                boolean reversed = e.getTarget().getNode().id < n.id;
                if (reversed) {
                    e.reverse(layeredGraph, true);
                    layeredGraph.setProperty(InternalProperties.CYCLIC, true);
                }
            }
        }

        progressMonitor.done();
    }

    private Set<LNode> unassigned = Sets.newHashSet();
    private Set<LNode> assigned = Sets.newHashSet();

    private void constructive(final LGraph graph, final Layering layering) {

        unassigned.clear();
        assigned.clear();

        unassigned.addAll(graph.getLayerlessNodes());

        LNode u = graph.getLayerlessNodes().get(0);
        unassigned.remove(u);
        assigned.add(u);

        int index = 0;
        u.id = index;

        int lIndex = -1;
        int rIndex = 1;

        while (!unassigned.isEmpty()) {
            index++;

            // construct candidate list
            // TODO can be done incrementally
            Set<LNode> candidates = Sets.newHashSet();
            for (LNode v : assigned) {
                for (LNode w : getAdjacentNodes(v)) {
                    if (unassigned.contains(w)) {
                        candidates.add(w);
                    }
                }
            }

            // compute quality of possible solutions
            int minDegree = Integer.MAX_VALUE;
            // if < 0 place the node left, otherwise right
            int side = 0;
            LNode minDegNode = null;
            for (LNode v : candidates) {

                // incoming edges to v that have been assigned
                int incAssigned = 0;
                // outgoing edges of v that have been assigned
                int outAssigned = 0;

                // unassigned and assigned neighbors of v
                int du = 0, da = 0;

                for (LEdge we : v.getIncomingEdges()) {
                    LNode w = we.getSource().getNode();
                    if (unassigned.contains(w)) {
                        du++;
                    } else {
                        da++;
                        incAssigned++;
                    }
                }

                for (LEdge we : v.getOutgoingEdges()) {
                    LNode w = we.getTarget().getNode();
                    if (unassigned.contains(w)) {
                        du++;
                    } else {
                        da++;
                        outAssigned++;
                    }
                }

                int score = du - da;
                if (score < minDegree) {
                    minDegNode = v;
                    side = incAssigned - outAssigned;
                }
                minDegree = Math.min(minDegree, score);
            }

            // label the selected node
            // minDegNode.id = index;

            if (side < 0) {
                minDegNode.id = lIndex--;
            } else {
                minDegNode.id = rIndex++;
            }

            assigned.add(minDegNode);
            unassigned.remove(minDegNode);

        }

        // shift all node layers and assign them to layers
        for (LNode n : graph.getLayerlessNodes()) {
            n.id += (-1 * lIndex) - 1;

            layering.layers[n.id].add(n);
        }

    }

    private void compact(final LGraph graph, final Layering layering) {

        // forward sweep
        for (int i = 0; i < layering.layerCount; i++) {
            Iterator<LNode> layerNodes = layering.layers[i].iterator();
            while (layerNodes.hasNext()) {
                LNode u = layerNodes.next();
                int index = i;
                int maxPredecessor = -1;

                for (LNode w : getAdjacentNodes(u)) {
                    if (w.id < index) {
                        maxPredecessor = Math.max(maxPredecessor, w.id);
                    }
                }

                if (maxPredecessor > -1 && maxPredecessor + 1 < index) {
                    // System.out.println("Moving " + u + " to layer " + (maxPredecessor + 1));
                    layerNodes.remove();
                    u.id = maxPredecessor + 1;
                    layering.layers[u.id].add(u);
                }
            }
        }

        // remove empty layers
        int offset = 0;
        for (int i = 0; i < layering.layers.length; i++) {

            // update the node's layer information
            for (LNode n : layering.layers[i]) {
                n.id = i - offset;
            }

            // move the layer
            layering.layers[i - offset] = layering.layers[i];

            if (layering.layers[i].isEmpty()) {
                offset++;
            }
        }
        layering.layerCount -= offset;

        // backwards sweep
        int upperBound = layering.layerCount + 1;
        for (int i = layering.layerCount - 1; i >= 0; i--) {
            Iterator<LNode> layerNodes = layering.layers[i].iterator();
            while (layerNodes.hasNext()) {
                LNode u = layerNodes.next();
                int index = i;
                int minSuccessor = upperBound;

                for (LNode w : getAdjacentNodes(u)) {
                    if (w.id > index) {
                        minSuccessor = Math.min(minSuccessor, w.id);
                    }
                }

                if (minSuccessor < upperBound && index < minSuccessor - 1) {
                    // System.out.println("Moving " + u + " to layer " + (minSuccessor + 1));
                    layerNodes.remove();
                    u.id = minSuccessor - 1;
                    layering.layers[u.id].add(u);
                }
            }
        }

        // remove empty layers
        offset = 0;
        for (int i = 0; i < layering.layerCount; i++) {
            // update the node's layer information
            for (LNode n : layering.layers[i]) {
                n.id = i - offset;
            }

            // move the layer
            layering.layers[i - offset] = layering.layers[i];

            if (layering.layers[i].isEmpty()) {
                offset++;
            }
        }
        layering.layerCount -= offset;
    }

    /* -------------------------------------------------------------------------------------------------
     * Internal convenience structures and methods
     * -------------------------------------------------------------------------------------------------
     */
    
    /**
     * Internal class to hold temporary layerings.
     */
    private static class Layering {

        // SUPPRESS CHECKSTYLE NEXT 100 VisibilityModifier
        /** the current number of layers. */
        public int layerCount;
        /** the layers of the layering. */
        public List<LNode>[] layers;

        @SuppressWarnings("unchecked")
        public Layering(final int n) {
            layerCount = n;
            layers = new List[n];
            for (int i = 0; i < n; ++i) {
                layers[i] = Lists.newArrayList();
            }
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer();
            sb.append("Layering " + super.toString() + "{\n");
            sb.append("  Layercount: " + layerCount + "\n");
            for (int i = 0; i < layerCount; ++i) {
                sb.append("  Layer " + i + ": " + layers[i].toString() + "\n");
            }
            sb.append("}\n");
            return sb.toString();
        }
    }
    
    private Iterable<LNode> getPredecessors(final LNode n) {
        return Iterables.transform(n.getIncomingEdges(), new Function<LEdge, LNode>() {
            public LNode apply(final LEdge input) {
                return input.getSource().getNode();
            }
        });
    }

    private Iterable<LNode> getAdjacentNodes(final LNode n) {
        return Iterables.concat(
                Iterables.transform(n.getOutgoingEdges(), new Function<LEdge, LNode>() {
                    public LNode apply(final LEdge input) {
                        return input.getTarget().getNode();
                    }
                }), Iterables.transform(n.getIncomingEdges(), new Function<LEdge, LNode>() {
                    public LNode apply(final LEdge input) {
                        return input.getSource().getNode();
                    }
                }));
    }
    
}
