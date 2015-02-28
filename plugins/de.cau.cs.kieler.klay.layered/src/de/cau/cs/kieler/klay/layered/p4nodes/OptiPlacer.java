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
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.core.util.Pair;
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
import de.cau.cs.kieler.klay.layered.properties.NodeType;
import de.cau.cs.kieler.klay.layered.properties.Properties;
import de.cau.cs.kieler.klay.layered.solver.AbstractCPLEXModel;
import de.cau.cs.kieler.klay.layered.solver.ISolverModel;
import de.cau.cs.kieler.klay.layered.solver.ModelRunner;

/**
 * @author uru
 */
public class OptiPlacer implements ILayoutPhase {

    public static final IProperty<Objective> NODE_PLACEMENT_OBJECTIVE = new Property<Objective>(
            "de.cau.cs.kieler.klay.layered.nodePlacementObjective", Objective.SEGMENTS);

    public enum Objective {
        EDGES,
        SEGMENTS
    }
    static int cnt = 0;
    /**
     * {@inheritDoc}
     */
    public void process(final LGraph layeredGraph, final IKielerProgressMonitor progressMonitor) {
        progressMonitor.begin("Optimal Node Placement", 1);

        System.out.println("\tNum " + cnt++);
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
            int upperBoundHeight = 0;

            // ports
            int[] p = new int[P];
            int[] p_offset = new int[P];
            int p_cnt = 0;

            // edges
            int E = 0;
            int[][] edges = new int[P][4];
            
            int Eprime = 0;
            int[][] segments = new int[P][P];
            List<Pair<Integer, Integer>> longEdgeDummyPairs = Lists.newArrayList();

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
                    upperBoundHeight += Math.ceil(n.getSize().y);

                    for (LPort po : n.getPorts()) {
                        
                        // FIXME we don't like float port positions
                        po.getAnchor().y = 0;
                        po.getPosition().y = Math.ceil(po.getPosition().y); 
                        
                        po.id = p_cnt++;
                        p[po.id] = po.getNode().id + 1;
                        p_offset[po.id] = (int) po.getPosition().y;
                    }
                }
            }

            // for all edges that connect exactly two long edge dummies
            // we add equality constraints to keep that edge straight
            for (Layer l : layeredGraph.getLayers()) {
                for (LNode n : l.getNodes()) {
                    for (LPort po : n.getPorts()) {
                        int[] src = segments[po.id];
                        for (LEdge edge : po.getOutgoingEdges()) {
                            Eprime++;
                            src[edge.getTarget().id] = 1;
                            //System.out.println("Segment: " + edge);
                            
                            // record long edge dummies
                            if (edge.getSource().getNode().getProperty(InternalProperties.NODE_TYPE) 
                                    == NodeType.LONG_EDGE
                                && edge.getTarget().getNode().getProperty(InternalProperties.NODE_TYPE) 
                                    == NodeType.LONG_EDGE) {
                                longEdgeDummyPairs.add(
                                        Pair.of(edge.getSource().id + 1,
                                                edge.getTarget().id + 1));
                            }
                        }
                    }
                }
            }
            
            // collect real edges. That is from real node to real node
            for (Layer l : layeredGraph) {
                for (LNode n : l) {
                    if (n.getProperty(InternalProperties.NODE_TYPE) != NodeType.NORMAL
                            && n.getProperty(InternalProperties.NODE_TYPE) != NodeType.EXTERNAL_PORT) {
                        continue;
                    }
                    for (LPort po : n.getPorts()) {
                        for (LEdge edge : po.getOutgoingEdges()) {

                            // find the target
                            LPort target = edge.getTarget();
                            LEdge dummyEdge = edge;
                            while (target.getNode().getProperty(InternalProperties.NODE_TYPE) != NodeType.NORMAL
                                    && target.getNode().getProperty(InternalProperties.NODE_TYPE) != NodeType.EXTERNAL_PORT) {
                                
                                boolean found = false;
                                for (LEdge tmpEdge : target.getNode().getOutgoingEdges()) {
                                    dummyEdge = tmpEdge;
                                    target = tmpEdge.getTarget();
                                    found = true;
                                }
                                
                                if (!found) {
                                throw new IllegalStateException("Could not infer original edge. "
                                        + " Found a "
                                        + target.getNode()
                                                .getProperty(InternalProperties.NODE_TYPE));
                                }
                            }
                            
                            // SUPPRESS CHECKSTYLE NEXT 10 MagicNumber
                            int[] src = edges[E];
                            E++;
                            src[0] = edge.getSource().id + 1;
                            src[1] = edge.getTarget().id + 1;
                            src[2] = dummyEdge.getSource().id + 1;
                            src[3] = dummyEdge.getTarget().id + 1;
                            
                            // System.out.println("Edge: " + po.getNode() + " " + target.getNode());
                        }
                    }
                }
            }
            
            float nodeSpacing = layeredGraph.getProperty(Properties.OBJ_SPACING)
                    * layeredGraph.getProperty(Properties.OBJ_SPACING_IN_LAYER_FACTOR);
            float edgeSpacing = nodeSpacing * layeredGraph.getProperty(Properties.EDGE_SPACING_FACTOR);
            
            // upper bound on height
            upperBoundHeight += (N - 1) * nodeSpacing; 
            
            Objective obj = layeredGraph.getProperty(NODE_PLACEMENT_OBJECTIVE);
            //System.out.println(obj);
            
            sb.append("objective = " + (obj == Objective.SEGMENTS ? 0 : 1) + ";\n");
            
            sb.append("\nN = " + N + ";");
            sb.append("\nP = " + P + ";");
            sb.append("\nL = " + L + ";");
            
            sb.append("\nnodeSpacing = " + (int) nodeSpacing + ";");
            sb.append("\nedgeSpacing = " + (int) edgeSpacing + ";");
            sb.append("\nupperBoundHeight = " + (upperBoundHeight*3/4) + ";");

            sb.append("\n\nn_height = " + Arrays.toString(n_height) + ";");
            sb.append("\np_offset = " + Arrays.toString(p_offset) + ";");
            sb.append("\nl_count = " + Arrays.toString(l_count) + ";");
            sb.append("\nl_start = " + Arrays.toString(l_start) + ";");

            sb.append("\n\np = " + Arrays.toString(p) + ";");

            sb.append("\n\nE = " + E + ";");
            sb.append("\nedges = [");
            int i = 0;
            //for (int[] arr : edges) {
            for (int k = 0; k < E; k++) {
                int[] arr = edges[k];
                sb.append("\n\t" + Arrays.toString(arr));
                if (i++ < E - 1) {
                    sb.append(",");
                }
            }
            sb.append("];");
            
            sb.append("\n\nEprime = " + Eprime + ";");
            sb.append("\nsegments = [");
            i = 0;
            for (int[] arr : segments) {
                sb.append("\n\t" + Arrays.toString(arr));
                if (i++ < P - 1) {
                    sb.append(",");
                }
            }
            sb.append("];");
            
            
            sb.append("\n\nLD = " + longEdgeDummyPairs.size() + ";");
            sb.append("\nd = [");
            for (int ii = 0; ii < longEdgeDummyPairs.size(); ii++) {
                Pair<Integer, Integer> pa = longEdgeDummyPairs.get(ii);
                sb.append("\n\t[" + pa.getFirst() + ", " + pa.getSecond() + "]");
                if (ii < longEdgeDummyPairs.size() - 1) {
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
                // System.out.println(line);
                if (line.startsWith("Time")) {
                   System.out.println("\t" + line); 
                   continue;
                }
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
