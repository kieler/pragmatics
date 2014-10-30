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
package de.cau.cs.kieler.klay.layered.p1cycles;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.io.CharStreams;
import com.google.common.primitives.Floats;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.klay.layered.ILayoutPhase;
import de.cau.cs.kieler.klay.layered.IntermediateProcessingConfiguration;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.intermediate.IntermediateProcessorStrategy;
import de.cau.cs.kieler.klay.layered.properties.InternalProperties;
import de.cau.cs.kieler.klay.layered.solver.AbstractCPLEXModel;
import de.cau.cs.kieler.klay.layered.solver.ModelRunner;

/**
 * @author uru
 * 
 */
public class OptimalCycleBreaker2 implements ILayoutPhase {

    /** intermediate processing configuration. */
    private static final IntermediateProcessingConfiguration INTERMEDIATE_PROCESSING_CONFIGURATION =
            IntermediateProcessingConfiguration.createEmpty().addAfterPhase5(
                    IntermediateProcessorStrategy.REVERSED_EDGE_RESTORER);

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
    public void process(final LGraph layeredGraph, final IKielerProgressMonitor monitor) {
        monitor.begin("Optimal cycle removal", 1);

        // assign ids to the nodes
        int index = 0;
        for (LNode n : layeredGraph.getLayerlessNodes()) {
            n.id = index++;
        }

        // the model assigns layers to nodes such that the number
        // of edges that connect nodes from higher layers to lower layers
        // is minimized
        // thus every edge that points backwards has to be reversed
        CycleBreakingModel model = new CycleBreakingModel();
        try {
            List<Integer> layerAssignment = ModelRunner.execute(model, layeredGraph);
            // the layer of each node
            int n = layeredGraph.getLayerlessNodes().size();
            int[] layers = new int[n];
            Iterator<Integer> it = layerAssignment.iterator();
            for (int i = 0; i < n; i++) {
                layers[i] = it.next();
            }

            // reverse the edges
            for (LNode node : layeredGraph.getLayerlessNodes()) {
                for (LEdge e : Lists.newArrayList(node.getOutgoingEdges())) {
                    LNode target = e.getTarget().getNode();
                    if (layers[node.id] > layers[target.id]) {
                        e.reverse(layeredGraph, true);
                        layeredGraph.setProperty(InternalProperties.CYCLIC, true);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        monitor.done();
    }

    /**
     * .
     */
    private static class CycleBreakingModel extends AbstractCPLEXModel<LGraph, List<Integer>> {

        /**
         * 
         */
        public CycleBreakingModel() {
            super(null);
        }

        /**
         * {@inheritDoc}
         */
        public String getModel() {
            return "D:/Uni/ma/minizinc-layering/models/MFAS/MFAS.mod";
        }

        /**
         * {@inheritDoc}
         */
        public String serializeSourceModel(final LGraph graph) {

            StringBuffer sb = new StringBuffer();

            sb.append("N = " + graph.getLayerlessNodes().size() + ";\n");
            
            StringBuffer edgeArray = new StringBuffer();
            edgeArray.append("ES = [");
            int edges = 0;
            boolean first = true;
            for (LNode u : graph.getLayerlessNodes()) {
                for (LEdge e : u.getOutgoingEdges()) {
                    LNode v = e.getTarget().getNode();
                    edgeArray.append((first ? "" : ",\n") + "[" + (u.id+1) + "," + (v.id+1) + "]");
                    first = false;
                    edges++;
                }
            }
            edgeArray.append("\n];");

            sb.append("E = " + edges + ";\n");

            sb.append(edgeArray);
            
            return sb.toString();
        }

        /**
         * {@inheritDoc}
         */
        public List<Integer> parseResult(final InputStream is) throws IOException {

            final List<String> lines = CharStreams.readLines(new InputStreamReader(is));

            List<Integer> layers = Lists.newArrayList();

            boolean start = false;
            for (String line : lines) {
                if (line.startsWith("Done")) {
                    break;
                }
                if (!start) {
                    if (line.startsWith("Result")) {
                        start = true;
                    }
                    continue;
                }

                String chunk = line.trim();
                // FIXME sometimes scip returns 0.99999999999, so we round here
                int layer = Math.round(Float.valueOf(chunk));
                layers.add(layer);
            }
            
            System.out.println("Length: " + layers.size() + " " + layers);

            return layers;
        }
    }

}
