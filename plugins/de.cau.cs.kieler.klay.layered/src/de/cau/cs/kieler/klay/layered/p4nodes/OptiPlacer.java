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
package de.cau.cs.kieler.klay.layered.p4nodes;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import com.google.common.collect.Lists;
import com.google.common.io.CharStreams;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.klay.layered.ILayoutPhase;
import de.cau.cs.kieler.klay.layered.IntermediateProcessingConfiguration;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.intermediate.IntermediateProcessorStrategy;
import de.cau.cs.kieler.klay.layered.properties.GraphProperties;
import de.cau.cs.kieler.klay.layered.properties.InternalProperties;
import de.cau.cs.kieler.klay.layered.solver.AbstractCPLEXModel;
import de.cau.cs.kieler.klay.layered.solver.ISolverModel;
import de.cau.cs.kieler.klay.layered.solver.ModelRunner;

/**
 * @author uru
 */
public class OptiPlacer implements ILayoutPhase {

    /**
     * {@inheritDoc}
     */
    public void process(final LGraph layeredGraph, final IKielerProgressMonitor progressMonitor) {
        progressMonitor.begin("Optimal Node Placement", 1);

        ISolverModel<Void, List<Integer>> solverModel = new NodePlacementSolverModel(layeredGraph);

        List<Integer> yPositions = null;
        try {
            yPositions = ModelRunner.execute(solverModel, null);

            int index = 0;
            for (Layer l : layeredGraph.getLayers()) {
                for (LNode n : l.getNodes()) {
                    n.getPosition().y = yPositions.get(index++);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        progressMonitor.done();
    }

    /** additional processor dependencies for graphs with hierarchical ports. */
    private static final IntermediateProcessingConfiguration HIERARCHY_PROCESSING_ADDITIONS =
            IntermediateProcessingConfiguration.createEmpty().addBeforePhase5(
                    IntermediateProcessorStrategy.HIERARCHICAL_PORT_POSITION_PROCESSOR);

    /**
     * {@inheritDoc}
     */
    public IntermediateProcessingConfiguration getIntermediateProcessingConfiguration(
            final LGraph graph) {
        if (graph.getProperty(InternalProperties.GRAPH_PROPERTIES).contains(
                GraphProperties.EXTERNAL_PORTS)) {
            return HIERARCHY_PROCESSING_ADDITIONS;
        } else {
            return null;
        }
    }

    /**
     * 
     * @author uru
     */
    private static class NodePlacementSolverModel extends AbstractCPLEXModel<Void, List<Integer>> {

        /** CPLEX model be executed. */
        private static final String CPLEX_NODE_PLACER = System.getenv("CPLEX_NODE_PLACER");

        /**
         * @param graph
         *            the layered graph
         */
        public NodePlacementSolverModel(final LGraph graph) {
            super(graph);

            checkForExecutable(CPLEX_NODE_PLACER, "CPLEX_NODE_PLACER");
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String getModel() {
            return CPLEX_NODE_PLACER;
        }

        /**
         * {@inheritDoc}
         */
        public String serializeSourceModel(final Void model) {
            StringBuffer sb = new StringBuffer();

            // SUPPRESS CHECKSTYLE NEXT 100 Name
            int L = layeredGraph.getLayers().size();
            int N = 0;
            int P = 0;
            for (Layer l : layeredGraph.getLayers()) {
                N += l.getNodes().size();
                for (LNode n : l.getNodes()) {
                    P += n.getPorts().size();
                }
            }

            // layer
            int[] l_start = new int[L];
            int l_cnt = 0;
            l_start[l_cnt] = 1;
            int l_start_last = 1;
            int[] l_count = new int[L];

            // nodes
            int[] n_height = new int[N];
            int n_cnt = 0;

            // ports
            int[] p = new int[P];
            int[] p_offset = new int[P];
            int p_cnt = 0;

            // edges
            int[][] e = new int[P][P];

            for (Layer l : layeredGraph.getLayers()) {
                l_count[l_cnt] = l.getNodes().size();
                if (l_cnt + 1 < L) {
                    l_start_last += l.getNodes().size();
                    l_start[l_cnt + 1] = l_start_last;
                }
                l_cnt++;

                for (LNode n : l.getNodes()) {
                    n.id = n_cnt++;
                    n_height[n.id] = (int) n.getSize().y;

                    for (LPort po : n.getPorts()) {
                        po.id = p_cnt++;
                        p[po.id] = po.getNode().id + 1;
                        p_offset[po.id] = (int) po.getPosition().y;
                    }
                }
            }

            for (Layer l : layeredGraph.getLayers()) {
                for (LNode n : l.getNodes()) {
                    for (LPort po : n.getPorts()) {
                        int[] src = e[po.id];
                        for (LEdge edge : po.getOutgoingEdges()) {
                            src[edge.getTarget().id] = 1;
                        }
                    }
                }
            }

            sb.append("N = " + N + ";");
            sb.append("\nP = " + P + ";");
            sb.append("\nL = " + L + ";");

            sb.append("\n\nn_height = " + Arrays.toString(n_height) + ";");
            sb.append("\np_offset = " + Arrays.toString(p_offset) + ";");
            sb.append("\nl_count = " + Arrays.toString(l_count) + ";");
            sb.append("\nl_start = " + Arrays.toString(l_start) + ";");

            sb.append("\n\np = " + Arrays.toString(p) + ";");

            sb.append("\n\ne = [");
            int i = 0;
            for (int[] arr : e) {
                sb.append("\n\t" + Arrays.toString(arr));
                if (i++ < P - 1) {
                    sb.append(",");
                }
            }
            sb.append("];");

            return sb.toString();
        }

        /**
         * {@inheritDoc}
         */
        public List<Integer> parseResult(final InputStream is) throws IOException {
            final List<String> lines = CharStreams.readLines(new InputStreamReader(is));

            List<Integer> yPositions = Lists.newArrayList();
            int minPos = Integer.MAX_VALUE;
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
                int yPos = Math.round(Float.valueOf(chunk));
                minPos = Math.min(minPos, yPos);
                yPositions.add(yPos);
            }

            // shift all positions such that the minimum is at 0
            for (int i = 0; i < yPositions.size(); i++) {
                yPositions.set(i, yPositions.get(i) - minPos);
            }

            // returns an arraylist
            return yPositions;
        }

    }
}
