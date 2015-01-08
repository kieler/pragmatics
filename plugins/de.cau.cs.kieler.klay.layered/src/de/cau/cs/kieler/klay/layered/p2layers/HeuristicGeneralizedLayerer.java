/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2014 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.layered.p2layers;

import java.util.Iterator;
import java.util.Set;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.klay.layered.ILayoutPhase;
import de.cau.cs.kieler.klay.layered.IntermediateProcessingConfiguration;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.intermediate.IntermediateProcessorStrategy;
import de.cau.cs.kieler.klay.layered.properties.InternalProperties;

/**
 * @author uru
 */
public class HeuristicGeneralizedLayerer implements ILayoutPhase {

    /** intermediate processing configuration. */
    private static final IntermediateProcessingConfiguration INTERMEDIATE_PROCESSING_CONFIGURATION =
        IntermediateProcessingConfiguration.createEmpty()
            .addAfterPhase5(IntermediateProcessorStrategy.REVERSED_EDGE_RESTORER);
    
    /**
     * {@inheritDoc}
     */
    public IntermediateProcessingConfiguration getIntermediateProcessingConfiguration(
            final LGraph graph) {
        return INTERMEDIATE_PROCESSING_CONFIGURATION;
    }


    /**
     * {@inheritDoc}
     */
    public void process(final LGraph layeredGraph, final IKielerProgressMonitor progressMonitor) {
        
        if (layeredGraph.getLayerlessNodes().isEmpty()) {
            return;
        }
        
        progressMonitor.begin("Heuristic General Layering", 1);
        
        constructive(layeredGraph);
        
        // ------------------
        // #5 set edge reversal state
        for (Layer l : layeredGraph) {
            for (LNode n : l) {
                for (LEdge e : Lists.newArrayList(n.getOutgoingEdges())) {
                    boolean reversed =
                            e.getTarget().getNode().getLayer().getIndex() < l.getIndex();
                    if (reversed) {
                        e.reverse(layeredGraph, true);
                        layeredGraph.setProperty(InternalProperties.CYCLIC, true);
                    }
                }
            }
        }
        
        progressMonitor.done();
    }
    
    
    private Set<LNode> unassigned = Sets.newHashSet();
    private Set<LNode> assigned = Sets.newHashSet();
    
    private void constructive(final LGraph graph) {

        unassigned.addAll(graph.getLayerlessNodes());

        LNode u = graph.getLayerlessNodes().get(0);
        unassigned.remove(u);
        assigned.add(u);

        int index = 0;
        u.id = index;

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
            LNode minDegNode = null;
            for (LNode v : candidates) {
                int du = 0, da = 0;
                for (LNode w : getAdjacentNodes(v)) {
                    if (unassigned.contains(w)) {
                        du++;
                    } else {
                        da++;
                    }
                }

                if (du - da < minDegree) {
                    minDegNode = v;
                }
                minDegree = Math.min(minDegree, du - da);
            }

            // label the selected node
            minDegNode.id = index;
            assigned.add(minDegNode);
            unassigned.remove(minDegNode);

        }

        int numberOfLayers = graph.getLayerlessNodes().size();

        // actually create layers
        Layer[] layers = new Layer[numberOfLayers];
        for (int i = 0; i < layers.length; i++) {
            layers[i] = new Layer(graph);
            graph.getLayers().add(layers[i]);
        }

        // apply the computed layer assignment
        Iterator<LNode> nodes = graph.getLayerlessNodes().iterator();
        while (nodes.hasNext()) {
            LNode node = nodes.next();
            node.setLayer(layers[node.id]);

            // remove from layerless nodes
            nodes.remove();
        }

    }
    
    
    private Iterable<LNode> getAdjacentNodes(final LNode n) {
        return Iterables.concat(
            Iterables.transform(n.getOutgoingEdges(), new Function<LEdge, LNode>() {
                public LNode apply(final LEdge input) { return input.getTarget().getNode(); }
            }),
            Iterables.transform(n.getIncomingEdges(), new Function<LEdge, LNode>() {
                public LNode apply(final LEdge input) { return input.getSource().getNode(); }
            })
        );
    }
}
