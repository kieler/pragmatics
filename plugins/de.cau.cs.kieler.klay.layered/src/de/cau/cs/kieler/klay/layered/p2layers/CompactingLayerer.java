/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.layered.p2layers;

import java.util.EnumSet;
import java.util.List;

import com.google.common.collect.Lists;

import de.cau.cs.kieler.core.alg.BasicProgressMonitor;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.klay.layered.ILayoutPhase;
import de.cau.cs.kieler.klay.layered.IntermediateProcessingConfiguration;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.intermediate.LayoutProcessorStrategy;
import de.cau.cs.kieler.klay.layered.properties.Properties;

/**
 * @author uru
 * 
 */
public class CompactingLayerer implements ILayoutPhase {

    /** intermediate processing configuration. */
    private static final IntermediateProcessingConfiguration BASELINE_PROCESSING_CONFIGURATION =
            new IntermediateProcessingConfiguration(
            // Before Phase 1
                    EnumSet.of(LayoutProcessorStrategy.EDGE_AND_LAYER_CONSTRAINT_EDGE_REVERSER),

                    // Before Phase 2
                    null,

                    // Before Phase 3
                    EnumSet.of(LayoutProcessorStrategy.LAYER_CONSTRAINT_PROCESSOR),

                    // Before Phase 4
                    null,

                    // Before Phase 5
                    null,

                    // After Phase 5
                    null);

    /** additional processor dependencies for handling big nodes. */
    private static final IntermediateProcessingConfiguration BIG_NODES_PROCESSING_ADDITIONS =
            new IntermediateProcessingConfiguration(
            // Before Phase 1
                    null,

                    // Before Phase 2
                    EnumSet.of(LayoutProcessorStrategy.BIG_NODES_PREPROCESSOR),

                    // Before Phase 3
                    EnumSet.of(LayoutProcessorStrategy.BIG_NODES_INTERMEDIATEPROCESSOR),

                    // Before Phase 4
                    null,

                    // Before Phase 5
                    null,

                    // After Phase 5
                    EnumSet.of(LayoutProcessorStrategy.BIG_NODES_POSTPROCESSOR));

    /**
     * {@inheritDoc}
     */
    public IntermediateProcessingConfiguration getIntermediateProcessingConfiguration(
            final LGraph graph) {
        // Basic strategy
        IntermediateProcessingConfiguration strategy =
                new IntermediateProcessingConfiguration(BASELINE_PROCESSING_CONFIGURATION);

        // Additional dependencies
        if (graph.getProperty(Properties.DISTRIBUTE_NODES)) {
            strategy.addAll(BIG_NODES_PROCESSING_ADDITIONS);
        }
        return strategy;
    }

    /**
     * {@inheritDoc}
     */
    public void process(final LGraph layeredGraph, final IKielerProgressMonitor progressMonitor) {

        NetworkSimplexLayerer nslayerer = new NetworkSimplexLayerer();

        nslayerer.process(layeredGraph, new BasicProgressMonitor());

        List<Integer> layerSizes = Lists.newLinkedList();
        List<Integer> spanningEdges = Lists.newLinkedList();
        for (Layer l : layeredGraph.getLayers()) {
            layerSizes.add(l.getNodes().size());

            int edges = 0;

        }

        System.out.println(layerSizes);

        if (layeredGraph.getLayers().size() > 7) {
//            revertLayerOuts(layeredGraph, 14);
            revertLayerOuts(layeredGraph, 7);
        }

        removeLayering(layeredGraph);

        nslayerer = new NetworkSimplexLayerer();
        nslayerer.process(layeredGraph, progressMonitor);

    }

    private void removeLayering(final LGraph layeredGraph) {
        for (Layer l : layeredGraph) {
            List<LNode> nodes = Lists.newLinkedList(l.getNodes());
            for (LNode n : nodes) {
                layeredGraph.getLayerlessNodes().add(n);
                n.setLayer(null);
            }
            l.getNodes().clear();
        }
        layeredGraph.getLayers().clear();
    }

    private void revertLayerOuts(final LGraph layeredGraph, final int layer) {
        Layer select = layeredGraph.getLayers().get(layer);
        for (LNode n : select.getNodes()) {
            List<LEdge> edges = Lists.newLinkedList(n.getOutgoingEdges());
            for (LEdge e : edges) {
                e.reverse(layeredGraph, true);
            }
        }
    }

}
