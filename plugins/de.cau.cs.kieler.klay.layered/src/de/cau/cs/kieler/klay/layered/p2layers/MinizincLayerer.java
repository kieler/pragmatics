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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import org.eclipse.emf.common.util.WrappedException;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.io.CharStreams;
import com.google.common.primitives.Floats;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.klay.layered.ILayoutPhase;
import de.cau.cs.kieler.klay.layered.IntermediateProcessingConfiguration;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.intermediate.IntermediateProcessorStrategy;
import de.cau.cs.kieler.klay.layered.properties.InternalProperties;
import de.cau.cs.kieler.klay.layered.properties.Properties;

/**
 * A layer assignment implementation using the external tool MiniZinc.
 * 
 * @author msp
 * @author uru
 */
public class MinizincLayerer implements ILayoutPhase {
    
    /** Home folder of the minizinc installation, ie where the 'bin' folder resides. */
    private static final String MINIZINC_INSTALL = System.getenv("MINIZINC_HOME");
    /** Folder of the minizinc model and the layering script to be executed. */
    private static final String MINIZINC_SOLVE = System.getenv("MINIZINC_SOLVE");
    /** Path to the SCIP binary. */ 
    private static final String SCIP_INSTALL = System.getenv("SCIP_BIN");

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
        progressMonitor.begin("MiniZinc layering", 1);
        
        try {
            checkForExecutables();    
            
            // ------------------
            // #1 running Tarjan's algorithm,
            // note that this has to run _first_ as it assigns node ids
            Set<Set<LNode>> strongComps = stronglyConnectedComponents(layeredGraph);

            // assemble the adjacency matrix with edge weights 
            float[][] adj = getAdjencencyMatrix(layeredGraph);
            
            
            
            MinizincMode mode = layeredGraph.getProperty(Properties.MINIZINC_MODE);

            if (mode == MinizincMode.STATIC_SCC) {
                // ----- SCC --------
                // edges that are part of strongly connected components
                // are penalized less
                float factor = layeredGraph.getProperty(Properties.EDGE_REVERSAL_WEIGHT_FACTOR);
                for (Set<LNode> scc : strongComps) {
                    if (scc.size() > 1) {
                        for (LNode u : scc) {
                            for (LEdge e : u.getOutgoingEdges()) {
                                LNode v = e.getTarget().getNode();
                                if (scc.contains(v)) {
                                    adj[u.id][v.id] *= factor;
                                }
                            }
                        }
                    }
                }
            }

            if (mode == MinizincMode.BETWEENNESS || mode == MinizincMode.BETWEENNESS_BOTH) {
                // ----- Betweenness --------
                int nn = layeredGraph.getLayerlessNodes().size();
                float normFactor = 2f / (nn * nn - 3f * nn + 2f); // SUPPRESS CHECKSTYLE MagicNumber
                if (nn == 1 || nn == 2) {
                    // above expression divides by 0 in case nn is 1 or two
                    normFactor = 1;
                }
                // float factor = layeredGraph.getProperty(Properties.EDGE_REVERSAL_WEIGHT);
                for (Entry<LEdge, Double> e : new Betweenness().calculate(layeredGraph).entrySet()) {
                    float val = e.getValue().floatValue() * normFactor;
                    LEdge edge = e.getKey();
                    adj[edge.getSource().getNode().id][edge.getTarget().getNode().id] = val;
                }
            }
            
            
          

            // ------------------
            // #2 create a temporary file as input to MiniZinc
            String dataFileName = getDataFile(layeredGraph, adj);
            
            // ------------------
            // #3 execute MiniZinc and read its output
            String[] args;
            if (System.getProperty("os.name").toLowerCase().contains("windows")) {
                args = new String[] { "bash", MINIZINC_SOLVE, MINIZINC_INSTALL, SCIP_INSTALL,
                                dataFileName };
            } else {
                args = new String[] { MINIZINC_SOLVE, MINIZINC_INSTALL, SCIP_INSTALL, dataFileName };
            }
            
            Process process = Runtime.getRuntime().exec(args);
            process.waitFor();
            
            // ------------------
            // #4 import results and assign layers
            final List<String> lines =
                    CharStreams.readLines(new InputStreamReader(process.getInputStream()));

            // errors?
            if (lines.isEmpty()) {
                readErrorStream(process);
            }
            
            int nodeCount = layeredGraph.getLayerlessNodes().size();
            int[] assignLayers = new int[nodeCount];
            int minLayer = Integer.MAX_VALUE;
            int maxLayer = Integer.MIN_VALUE;
            
            for (String line : lines) {
                System.out.println("Line " + line);
                String[] chunks = line.trim().split(" ");
                if (chunks.length > 1) {
                    // FIXME sometimes scip returns 0.99999999999, so we round here 
                    int id = Math.round(Float.valueOf(chunks[0]));
                    int layer = Math.round(Float.valueOf(chunks[1]));
                    
                    // minizinc starts with 1 instead of 0
                    assignLayers[id - 1] = layer - 1;
                    minLayer = Math.min(minLayer, layer - 1);
                    maxLayer = Math.max(maxLayer, layer - 1);
                } else {
                    System.out.println(Arrays.toString(chunks));
                }
            }
            
            // create the layers
            Layer[] layers = new Layer[maxLayer - minLayer + 1];
            for (int i = 0; i < layers.length; i++) {
                layers[i] = new Layer(layeredGraph);
                layeredGraph.getLayers().add(layers[i]);
            }
            
            // apply the computed layer assignment
            Iterator<LNode> nodes = layeredGraph.getLayerlessNodes().iterator();
            while (nodes.hasNext()) {

                LNode node = nodes.next();
                node.setLayer(layers[assignLayers[node.id] - minLayer]);

                // remove from layerless nodes
                nodes.remove();
            }
            
            // ------------------
            // #5 set edge reversal state
            for (Layer l : layers) {
                for (LNode n : l.getNodes()) {
                    for (LEdge e : n.getOutgoingEdges()) {
                        boolean reversed =
                                e.getTarget().getNode().getLayer().getIndex() < l.getIndex();
                        if (reversed) {
                            e.reverse(layeredGraph, true);
                            layeredGraph.setProperty(InternalProperties.CYCLIC, true);
                        }
                    }
                }
            }
            
        } catch (Exception exception) {
            throw new WrappedException(exception);
        } finally {
            progressMonitor.done();
        }
    }
    
    private float[][] getAdjencencyMatrix(final LGraph layeredGraph) {
        int n = layeredGraph.getLayerlessNodes().size();
        float[][] adj = new float[n][n];
        // float weight = layeredGraph.getProperty(Properties.EDGE_REVERSAL_WEIGHT);
        for (LNode u : layeredGraph.getLayerlessNodes()) {
            for (LEdge e : u.getOutgoingEdges()) {
                LNode v = e.getTarget().getNode();
                adj[u.id][v.id] = 1;
            }
        }
        return adj;
    }
    
    /**
     * Write the given graph as MiniZinc data file and return the absolute path of that file.
     * 
     * @param layeredGraph a layered graph
     * @return the absolute path to a data file
     * @throws IOException
     */
    private String getDataFile(final LGraph layeredGraph, final float[][] adj) throws IOException {
        File dataFile = File.createTempFile("graph", ".dzn");
        FileWriter writer = new FileWriter(dataFile);

        // edges as adjacency matrix
        writer.write("N = " + layeredGraph.getLayerlessNodes().size() + ";\n");
        
        // strongly connected components
        writer.write("e = [| ");
        for (int i = 0; i < adj.length; i++) {
            float[] row = adj[i];
            writer.write(Joiner.on(", ").join(Floats.asList(row)));
            if (i != adj.length - 1) {
                writer.write(",\n     | ");
            }
        }
        writer.write(" |];\n");
        
        // weights
        writer.write("\nW_length = " + layeredGraph.getProperty(Properties.EDGE_LENGTH_WEIGHT)
                + ";\nW_reverse = " + layeredGraph.getProperty(Properties.EDGE_REVERSAL_WEIGHT)
                + ";\n");
        
        MinizincMode mode = layeredGraph.getProperty(Properties.MINIZINC_MODE);
        boolean staticDummyWeight = mode != MinizincMode.BETWEENNESS_BOTH;
        boolean staticReverseWeight = mode == MinizincMode.STATIC;
        writer.write("static_w_length = " + staticDummyWeight + ";\nstatic_w_reverse = "
                + staticReverseWeight + ";\n");

        int maximalLayers = layeredGraph.getProperty(Properties.MAXIMAL_LAYERS);
        writer.write("L_max_param = " + maximalLayers + ";\n");
        
        writer.close();
        dataFile.deleteOnExit();
        
        // System.out.println(Joiner.on("\n").join(Files.readLines(dataFile, Charset.forName("utf8"))));
        return dataFile.getAbsolutePath();
    }
    
    /**
     * Read the error stream and throw an exception.
     * 
     * @param process the MiniZinc process
     * @throws IOException if reading from the error stream fails
     */
    private void readErrorStream(final Process process) throws IOException {
        String line;
        StringBuilder errorBuilder = new StringBuilder();
        BufferedReader errorReader = new BufferedReader(new InputStreamReader(
                process.getErrorStream()));
        while ((line = errorReader.readLine()) != null) {
            errorBuilder.append(line);
        }
        throw new RuntimeException("Communication to MiniZinc failed: "
                + errorBuilder.toString());
    }

    private static final String NOTE =
            "Note that system variables are loaded only once upon program startup.";

    private void checkForExecutables() {

        if (MINIZINC_INSTALL == null || !new File(MINIZINC_INSTALL).exists()) {
            throw new RuntimeException(
                    "Could not locate MiniZinc installation, make sure 'MINIZINC_HOME' is set.\n"
                            + NOTE);
        }

        if (MINIZINC_SOLVE == null || !new File(MINIZINC_SOLVE).exists()) {
            throw new RuntimeException(
                    "Could not locate MiniZinc installation, make sure 'MINIZINC_SOLVE' is set.\n"
                            + NOTE);
        }
        if (SCIP_INSTALL == null || !new File(SCIP_INSTALL).exists()) {
            throw new RuntimeException(
                    "Could not locate SCIP installation, make sure 'SCIP_INSTALL' is set.\n" + NOTE);
        }
    }
    
    
    private int index = 0;
    private int[] lowlink;
    private Stack<LNode> stack = new Stack<LNode>();
    private Set<Set<LNode>> sccs = Sets.newHashSet();
    
    /**
     * Tarjan's Algorithm to find strongly connected components.
     */
    private Set<Set<LNode>> stronglyConnectedComponents(final LGraph graph) {
        
        // invalidate indices
        for (LNode n : graph.getLayerlessNodes()) {
            n.id = -1;
        }
        
        int n = graph.getLayerlessNodes().size();
        index = 0;
        lowlink = new int[n];
        stack.clear();
        sccs.clear();
        
        for (LNode node : graph.getLayerlessNodes()) {
            if (node.id == -1) {
                strongConnect(node);
            }
        }
        
        return sccs;
    }

    private void strongConnect(final LNode v) {
        v.id = index;
        lowlink[v.id] = index;
        index++;
        stack.push(v);

        // successors of current node
        for (LEdge e : v.getOutgoingEdges()) {
            LNode w = e.getTarget().getNode();
            if (w.id == -1) {
                // successor not been visited
                strongConnect(w);
                lowlink[v.id] = Math.min(lowlink[v.id], lowlink[w.id]);
                        
            } else if (stack.contains(w)) {
                // successor is in the current scc
                lowlink[v.id] = Math.min(lowlink[v.id], w.id);
            }
        }

        // if current node is a root node, generate scc
        if (lowlink[v.id] == v.id) {
            Set<LNode> scc = Sets.newHashSet();
            LNode sn = null;
            do {
                sn = stack.pop();
                scc.add(sn);
            } while (sn.id != v.id);
            sccs.add(scc);
        }
    }
    
    /**
     * Calculate Edge Betweenness using Brandes' algorithm. 
     */
    private static class Betweenness {
        
        private Queue<LNode> queue = new LinkedList<LNode>();
        private Stack<LNode> stack = new Stack<LNode>();
        private int[] dist;
        private ArrayList<List<LNode>> pred = Lists.newArrayList();
        private int[] sigma;
        private double[] delta;
        
        private static final int INF = Integer.MAX_VALUE / 2;
        
        private Map<LEdge, Double> edgeBetweenness = Maps.newHashMap();
        
        public Map<LEdge, Double> calculate(final LGraph g) {
        
            int n = g.getLayerlessNodes().size();
            int[] oldIds = new int[n];
            // remember old ids
            int bindex = 0;
            for (LNode node : g.getLayerlessNodes()) {
                oldIds[bindex] = node.id;
                node.id = bindex++;
            }
            
            // reset
            edgeBetweenness.clear();
            queue.clear();
            stack.clear();
            pred.clear();
            for (int i = 0; i < n; i++) {
                pred.add(null);
            }
            dist = new int[n];
            sigma = new int[n];
            delta = new double[n];
            
            for (LNode s : g.getLayerlessNodes()) {
                // initialization
                for (LNode w : g.getLayerlessNodes()) {
                    pred.set(w.id, new LinkedList<LNode>());
                }
                for (LNode t : g.getLayerlessNodes()) {
                    dist[t.id] = INF;
                    sigma[t.id] = 0;
                }
                dist[s.id] = 0;
                sigma[s.id] = 1;
                queue.add(s);
            
                // path discovery and path counting
                while (!queue.isEmpty()) {
                    LNode v = queue.poll();
                    stack.push(v);
                    
                    for (LEdge edge : v.getOutgoingEdges()) {
                        LNode w = edge.getTarget().getNode();
                        
                        // path discovery
                        if (dist[w.id] >= INF) {
                            dist[w.id] = dist[v.id] + 1;
                            queue.add(w);
                        }
                        
                        // path counting
                        if (dist[w.id] == dist[v.id] + 1) {
                            sigma[w.id] = sigma[w.id] + sigma[v.id];
                            pred.get(w.id).add(v);
                        }
                    }
                }
                
                // accumulation
                // (edge betweenness)
                for (LNode v : g.getLayerlessNodes()) {
                    delta[v.id] = 0;
                }
                while (!stack.isEmpty()) {
                    LNode w = stack.pop();
                    for (LNode v : pred.get(w.id)) {
                        double c = (sigma[v.id] / (double) sigma[w.id]) * (1 + delta[w.id]);

                        for (LEdge edge : v.getOutgoingEdges()) {
                            if (edge.getTarget().getNode().equals(w)) {
                                Double prev = edgeBetweenness.get(edge) != null 
                                                ? edgeBetweenness.get(edge) : 0;
                                edgeBetweenness.put(edge, prev + c);
                                delta[v.id] = delta[v.id] + c;
                            }
                        }

                    }
                }
            
            }
            
            // set ids to original values
            for (LNode node : g.getLayerlessNodes()) {
                node.id = oldIds[node.id];
            }
            
            return edgeBetweenness;
        }
        
    }
}
