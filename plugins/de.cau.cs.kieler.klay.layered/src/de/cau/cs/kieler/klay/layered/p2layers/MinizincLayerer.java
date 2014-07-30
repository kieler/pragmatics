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
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import org.eclipse.emf.common.util.WrappedException;

import com.google.common.base.Joiner;
import com.google.common.collect.Sets;
import com.google.common.io.CharStreams;
import com.google.common.primitives.Ints;

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
            int[][] adj = getAdjencencyMatrix(layeredGraph);
            
            // edges that are part of strongly connected components
            // are penalized less
            int factor = layeredGraph.getProperty(Properties.EDGE_REVERSAL_WEIGHT_FACTOR).intValue();
            for (Set<LNode> scc : strongComps) {
                if (scc.size() > 1) {
                    for (LNode u : scc) {
                        for (LEdge e : u.getOutgoingEdges()) {
                            LNode v = e.getTarget().getNode();
                            if (scc.contains(v)) {
                                adj[u.id][v.id] /= factor;
                            }
                        }                        
                    }
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
                String[] chunks = line.trim().split(" ");
                int id = Integer.valueOf(chunks[0]);
                int layer = Integer.valueOf(chunks[1]);
                
                // minizinc starts with 1 instead of 0
                assignLayers[id - 1] = layer - 1;
                minLayer = Math.min(minLayer, layer - 1);
                maxLayer = Math.max(maxLayer, layer - 1);
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
    
    private int[][] getAdjencencyMatrix(final LGraph layeredGraph) {
        int n = layeredGraph.getLayerlessNodes().size();
        int[][] adj = new int[n][n];
        int weight =
                layeredGraph.getProperty(Properties.EDGE_REVERSAL_WEIGHT).intValue()
                        * layeredGraph.getProperty(Properties.EDGE_REVERSAL_WEIGHT_FACTOR)
                                .intValue();
        for (LNode u : layeredGraph.getLayerlessNodes()) {
            for (LEdge e : u.getOutgoingEdges()) {
                LNode v = e.getTarget().getNode();
                adj[u.id][v.id] = weight;
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
    private String getDataFile(final LGraph layeredGraph, final int[][] adj) throws IOException {
        File dataFile = File.createTempFile("graph", ".dzn");
        FileWriter writer = new FileWriter(dataFile);

        // edges as adjacency matrix
        writer.write("N = " + layeredGraph.getLayerlessNodes().size() + ";\n");
        
        // strongly connected components
        writer.write("e = [| ");
        for (int i = 0; i < adj.length; i++) {
            int[] row = adj[i];
            writer.write(Joiner.on(", ").join(Ints.asList(row)));
            if (i != adj.length - 1) {
                writer.write(",\n     | ");
            }
        }
        writer.write(" |];\n");
        
        // weights
        writer.write("\nW_length = " + layeredGraph.getProperty(Properties.EDGE_LENGTH_WEIGHT)
                + ";\nW_reverse = " + layeredGraph.getProperty(Properties.EDGE_REVERSAL_WEIGHT)
                + ";\n");
        
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
    
}
