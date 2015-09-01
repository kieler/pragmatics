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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import org.eclipse.emf.common.util.WrappedException;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.klay.layered.ILayoutPhase;
import de.cau.cs.kieler.klay.layered.IntermediateProcessingConfiguration;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.intermediate.IntermediateProcessorStrategy;
import de.cau.cs.kieler.klay.layered.properties.InternalProperties;
import de.cau.cs.kieler.klay.layered.properties.Properties;
import de.cau.cs.kieler.solvers.ISolverModel;
import de.cau.cs.kieler.solvers.ModelRunner;

/**
 * A layer assignment implementation using the external tool MiniZinc.
 * 
 * @author msp
 * @author uru
 */
public class MinizincLayerer implements ILayoutPhase {
    
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
            // # 2-4 execute the solver
            //
            // The model is expected to expect a float[][] matrix where 
            // each entry larger than 0 represents the weight of that edge 
            
            ISolverModel<Object, Pair<Integer, List<Integer>>> model;
            if (layeredGraph.getProperty(Properties.LAYERING_SOLVER) == LayeringSolver.SCIP) {
                model = new MiniZincLayeringModel(layeredGraph);
            } else if (layeredGraph.getProperty(Properties.LAYERING_SOLVER) 
                            == LayeringSolver.CPLEX_EPSILON) {
                model = new CPLEXEpsilonLayeringModel(layeredGraph);
            } else if (layeredGraph.getProperty(Properties.LAYERING_SOLVER) 
                            == LayeringSolver.CPLEX_DLP) {
                model = new CPLEXDLPModel(layeredGraph);
            } else {
                model = new CPLEXLayeringModel(layeredGraph);
            }
            
            Pair<Integer, List<Integer>> result = ModelRunner.execute(model, adj);
            int numberOfLayers = result.getFirst();
            List<Integer> assignLayers = result.getSecond();
            
            // create the layers
            Layer[] layers = new Layer[numberOfLayers];
            for (int i = 0; i < layers.length; i++) {
                layers[i] = new Layer(layeredGraph);
                layeredGraph.getLayers().add(layers[i]);
            }
            
            // apply the computed layer assignment
            Iterator<LNode> nodes = layeredGraph.getLayerlessNodes().iterator();
            while (nodes.hasNext()) {
                LNode node = nodes.next();
                node.setLayer(layers[assignLayers.get(node.id)]);

                // remove from layerless nodes
                nodes.remove();
            }
            
            // ------------------
            // #5 set edge reversal state
            for (Layer l : layers) {
                for (LNode n : l.getNodes()) {
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
