/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.layered.p3order;

import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortConstraints;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.kiml.options.PortType;
import de.cau.cs.kieler.klay.layered.ILayoutPhase;
import de.cau.cs.kieler.klay.layered.IntermediateProcessingStrategy;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;
import de.cau.cs.kieler.klay.layered.intermediate.IntermediateLayoutProcessor;
import de.cau.cs.kieler.klay.layered.properties.NodeType;
import de.cau.cs.kieler.klay.layered.properties.Properties;

/**
 * Crossing minimization module that performs one or more sweeps over the layers
 * while applying a two-layer crossing minimization heuristic on each pair of layers.
 * Inspired by
 * <ul>
 *   <li>Michael Forster. A Fast and Simple Heuristic for Constrained Two-Level
 *     Crossing Reduction. In <i>Graph Drawing</i>, volume 3383 of LNCS,
 *     pp. 206-216. Springer, 2005.</li>
 * </ul>
 * 
 * <dl>
 *   <dt>Precondition:</dt><dd>the graph has a proper layering, i.e. all
 *     long edges have been splitted; all nodes have at least fixed port
 *     sides.</dd>
 *   <dt>Postcondition:</dt><dd>the order of nodes in each layer and the order
 *     of ports in each node are optimized to yield as few edge crossings
 *     as possible</dd>
 * </dl>
 *
 * @author msp
 * @author cds
 */
public class LayerSweepCrossingMinimizer extends AbstractAlgorithm implements ILayoutPhase {
    
    /**
     * A vertex contains one or more nodes. Vertices are used to model sets of nodes that
     * are placed next to each other. A vertex contains methods to calculate its barycenter
     * value, to merge with another vertex and to generally do cool stuff.
     * 
     * @author cds
     */
    private class Vertex implements Comparable<Vertex> {
        /**
         * List of nodes this vertex consists of.
         */
        private List<LNode> nodes = new LinkedList<LNode>();
        
        /**
         * List of outgoing constraints.
         */
        private List<Vertex> outgoingConstraints = new LinkedList<Vertex>();
        
        /**
         * The number of incoming constraints.
         */
        private int incomingConstraintsCount = 0;
        
        /**
         * The sum of the node weights. Each node weight is the sum of the weights of the
         * ports the node's ports are connected to.
         */
        private float summedWeight = 0.0f;
        
        /**
         * The number of ports relevant to the barycenter calculation.
         */
        private int degree = 0;
        
        /**
         * This vertex' barycenter value. (summedWeight / degree)
         */
        private float barycenter = -1.0f;
        
        
        /**
         * Constructs a new instance containing the given node.
         * 
         * @param node the node the vertex should contain.
         */
        public Vertex(final LNode node) {
            nodes.add(node);
        }
        
        /**
         * Constructs a new vertex that is the concatenation of the given two vertices. The
         * incoming constraints count is set to zero, while the list of successors are merged,
         * updating the successors' incoming count appropriately if both vertices are
         * predecessors.
         * 
         * @param vertex1 the first vertex.
         * @param vertex2 the second vertex.
         */
        public Vertex(final Vertex vertex1, final Vertex vertex2) {
            nodes.addAll(vertex1.nodes);
            nodes.addAll(vertex2.nodes);
            
            // Add constraints, taking care not to add any constraints to vertex1 or vertex2
            // and to decrement the incoming constraints count of those that are successors
            // to both
            outgoingConstraints.addAll(vertex1.outgoingConstraints);
            outgoingConstraints.remove(vertex2);
            for (Vertex candidate : vertex2.outgoingConstraints) {
                if (candidate == vertex1) {
                    continue;
                } else if (outgoingConstraints.contains(candidate)) {
                    // The candidate was in both vertice's successor list
                    candidate.incomingConstraintsCount--;
                } else {
                    outgoingConstraints.add(candidate);
                }
            }
            
            summedWeight = vertex1.summedWeight + vertex2.summedWeight;
            degree = vertex1.degree + vertex2.degree;
            
            if (degree > 0) {
                barycenter = summedWeight / degree;
            }
        }

        /**
         * {@inheritDoc}
         */
        public int compareTo(final Vertex other) {
            // Empty vertices are placed at the end of all things ((c) by J. R. R. Tolkien)
            if (nodes.isEmpty() || other.nodes.isEmpty()) {
                return other.nodes.size() - nodes.size();
            }
            
            // Nodes with equal barycenter values are randomized. This is not a stable
            // comparison, but we don't care.
            if (barycenter == other.barycenter) {
                return random.nextBoolean() ? 1 : -1;
            } else {
                return Float.compare(barycenter, other.barycenter);
            }
        }
    }
    
    /** intermediate processing strategy. */
    private static final IntermediateProcessingStrategy INTERMEDIATE_PROCESSING_STRATEGY =
        new IntermediateProcessingStrategy(
                // Before Phase 1
                null,
                // Before Phase 2
                null,
                // Before Phase 3
                EnumSet.of(IntermediateLayoutProcessor.LONG_EDGE_SPLITTER),
                // Before Phase 4
                EnumSet.of(IntermediateLayoutProcessor.IN_LAYER_CONSTRAINT_PROCESSOR),
                // Before Phase 5
                null,
                // After Phase 5
                EnumSet.of(IntermediateLayoutProcessor.LONG_EDGE_JOINER));
    
    /** barycenter values for ports. */
    private float[] portBarycenter;
    /** port position array. */
    private float[] portPos;
    /** A map associating layout units with their respective members. */
    private Multimap<LNode, LNode> layoutUnits;
    /**
     * A map of single-node vertices for each layer. This provides the vertices used at the
     * beginning of each crossing reduction run.
     */
    private Map<LNode, Vertex>[] singleNodeVertices;
    /** the random number generator. */
    private Random random;
    /** barycenter associate factor. */
    private float barycenterAssociateFactor = 1.0f;
    
    /**
     * {@inheritDoc}
     */
    public IntermediateProcessingStrategy getIntermediateProcessingStrategy(final LayeredGraph graph) {
        return INTERMEDIATE_PROCESSING_STRATEGY;
    }
    
    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public void process(final LayeredGraph layeredGraph) {
        getMonitor().begin("Layer sweep crossing minimization", 1);
        
        // Find the number of layers. If there's only one, no crossing minimization
        // is necessary.
        int layerCount = layeredGraph.getLayers().size();
        if (layerCount < 2) {
            getMonitor().done();
            return;
        }
        
        // Remember the best, current and previous sweep; they basically save the node oder
        // per layer for the different sweeps of the algorithm
        LNode[][] bestSweep = new LNode[layerCount][];
        LNode[][] curSweep = new LNode[layerCount][];
        LNode[][] prevSweep = new LNode[layerCount][];
        int bestSweepCrossings = Integer.MAX_VALUE;
        int curSweepCrossings = Integer.MAX_VALUE;
        int prevSweepCrossings = Integer.MAX_VALUE;
        
        layoutUnits = HashMultimap.create();
        singleNodeVertices = new HashMap[layerCount];
        
        int nodeCount = 0;
        int portCount = 0;

        // Iterate through the layers, initializing port and node IDs, collecting
        // the nodes into the current sweep and building the layout unit map
        ListIterator<Layer> layerIter = layeredGraph.getLayers().listIterator();
        while (layerIter.hasNext()) {
            Layer layer = layerIter.next();
            
            int layerIndex = layerIter.previousIndex();
            int layerNodeCount = layer.getNodes().size();
            
            // Initialize this layer's node arrays in the different sweeps
            bestSweep[layerIndex] = new LNode[layerNodeCount];
            prevSweep[layerIndex] = new LNode[layerNodeCount];
            curSweep[layerIndex] = new LNode[layerNodeCount];
            
            singleNodeVertices[layerIndex] = new HashMap<LNode, Vertex>();
            
            ListIterator<LNode> nodeIter = layer.getNodes().listIterator();
            while (nodeIter.hasNext()) {
                LNode node = nodeIter.next();
                
                curSweep[layerIndex][nodeIter.previousIndex()] = node;
                node.id = nodeCount++;
                layoutUnits.put(node.getProperty(Properties.IN_LAYER_LAYOUT_UNIT), node);
                singleNodeVertices[layerIndex].put(node, new Vertex(node));
                
                for (LPort port : node.getPorts()) {
                    port.id = portCount++;
                }
            }
        }
        
        // Initialize the position arrays
        portBarycenter = new float[portCount];
        portPos = new float[portCount];
        
        // Fetch the graph's randomizer and determine the requested number of runs
        random = layeredGraph.getProperty(Properties.RANDOM);
        int runCount = layeredGraph.getProperty(Properties.THOROUGHNESS);
        barycenterAssociateFactor = layeredGraph.getProperty(Properties.BARYCENTER_ASSOCIATE_FACTOR);
        
        // Perform the requested number of runs, each consisting of several sweeps
        // (alternating between forward and backward sweeps)
        for (int run = 0; run < runCount; run++) {
            // Each run is randomly determined to be a forward or a backward run
            boolean forward = random.nextBoolean();
            int fixedLayerIndex = forward ? 0 : layerCount - 1;
            LNode[] fixedLayer = curSweep[fixedLayerIndex];
            
            // The fixed layer is randomized
            int totalEdges = 0;
            minimizeCrossings(fixedLayer, fixedLayerIndex, forward, false, true);
            
            // Reset last and current run crossing counters
            prevSweepCrossings = Integer.MAX_VALUE;
            curSweepCrossings = Integer.MAX_VALUE;
            
            // Do the forward and backward sweeps until the number of crossings
            // increases with respect to the previous sweep
            boolean firstSweep = true;
            do {
                // The formerly current sweep is now the previous sweep
                copySweep(curSweep, prevSweep);
                prevSweepCrossings = curSweepCrossings;
                curSweepCrossings = 0;

                if (forward) {
                    // Perform a forward sweep
                    for (int layerIndex = 1; layerIndex < layerCount; layerIndex++) {
                        LNode[] freeLayer = curSweep[layerIndex];
                        
                        totalEdges = minimizeCrossings(freeLayer, layerIndex, true, !firstSweep, false);
                        curSweepCrossings += countCrossings(fixedLayer, freeLayer, totalEdges)
                            + countCrossings(freeLayer);
                        
                        fixedLayer = freeLayer;
                    }
                } else {
                    // Perform a backward sweep
                    for (int layerIndex = layerCount - 2; layerIndex >= 0; layerIndex--) {
                        LNode[] freeLayer = curSweep[layerIndex];
                        
                        totalEdges = minimizeCrossings(freeLayer, layerIndex, false, !firstSweep, false);
                        curSweepCrossings += countCrossings(freeLayer, fixedLayer, totalEdges)
                            + countCrossings(freeLayer);
                        
                        fixedLayer = freeLayer;
                    }
                }
                
                // Switch the sweep direction
                firstSweep = false;
                forward = !forward;
            } while (curSweepCrossings < prevSweepCrossings);
            
            // Compare the current result with the best one
            if (curSweepCrossings < bestSweepCrossings || prevSweepCrossings < bestSweepCrossings) {
                // Restore the previous sweep's ordering if it has become worse
                if (curSweepCrossings < prevSweepCrossings) {
                    copySweep(curSweep, bestSweep);
                    bestSweepCrossings = curSweepCrossings;
                } else {
                    copySweep(prevSweep, bestSweep);
                    bestSweepCrossings = prevSweepCrossings;
                }
            }
        }
        
        // Distribute the ports of all nodes with free port constraints
        distributePorts(bestSweep);
        
        // Apply the ordering to the original layered graph
        layerIter = layeredGraph.getLayers().listIterator();
        while (layerIter.hasNext()) {
            Layer layer = layerIter.next();
            LNode[] nodes = bestSweep[layerIter.previousIndex()];
            ListIterator<LNode> nodeIter = layer.getNodes().listIterator();
            while (nodeIter.hasNext()) {
                nodeIter.next();
                nodeIter.set(nodes[nodeIter.previousIndex()]);
            }
        }
        
        dispose();
        getMonitor().done();
    }
    
    /**
     * Release all created resources so the GC can reap them.
     */
    private void dispose() {
        this.portBarycenter = null;
        this.portPos = null;
        this.layoutUnits = null;
        this.singleNodeVertices = null;
    }
    
    
    ///////////////////////////////////////////////////////////////////////////////
    // Crossing Minimization

    /**
     * Minimize the number of crossings for the edges between the given layer and either
     * its predecessor or its successor. Currently the barycenter heuristic is used here.
     * 
     * @param layer the free layer whose nodes are reordered.
     * @param layerIndex the free layer's index.
     * @param forward whether the free layer is after the fixed layer.
     * @param preOrdered whether the nodes have been ordered in a previous run.
     * @param randomize {@code true} if this layer's node order should just be randomized. In that
     *                  case, {@code preOrdered} is assumed to be {@code false} and the return value
     *                  is {@code 0}.
     * @return the total number of edges going either in or out of the given layer.
     */
    private int minimizeCrossings(final LNode[] layer, final int layerIndex, final boolean forward,
            final boolean preOrdered, final boolean randomize) {
        
        // Ignore empty free layers
        if (layer.length == 0) {
            return 0;
        }
        
        /* We start with the list of single-node vertices. For each vertex, we calculate
         * the new barycenter value. We then sort the vertices using that value and
         * build a map of successor constraints using the nodes' individual successor
         * constraints as well as information about the layout units. Once that is
         * done, we look for violated constraints and handle them until we don't find
         * any anymore.
         */
        
        List<Vertex> vertices = new LinkedList<Vertex>();
        for (LNode node : layer) {
            vertices.add(singleNodeVertices[layerIndex].get(node));
        }
        
        // Barycenters!
        int totalEdges = 0;
        if (randomize) {
            // Randomize barycenters (we don't need to update the edge count in this case;
            // there are no edges of interest since we're only concerned with this one
            // layer anyway)
            randomizeBarycenters(vertices);
        } else {
            // Calculate barycenters and assign barycenters to barycenterless vertices
            totalEdges = calculateBarycenters(vertices, singleNodeVertices[layerIndex], forward);
            fillInUnknownBarycenters(vertices, preOrdered);
        }
        
        // Sort the vertices according to their barycenters
        Collections.sort(vertices);
        
        // Build the constraints graph
        buildConstraintsGraph(vertices, singleNodeVertices[layerIndex]);
        
        // Find violated vertices
        Pair<Vertex, Vertex> violatedConstraint = null;
        while ((violatedConstraint = findViolatedConstraint(vertices)) != null) {
            handleViolatedConstraint(violatedConstraint, vertices);
        }
        
        // Sort the vertices again, apply the node order to the free layer array and
        // assign port positions
        applyVertexOrderingToNodeArray(vertices, layer);
        assignPortPos(layer);
        
        return totalEdges;
    }
    
    /**
     * Randomize the order of nodes for the given layer.
     * 
     * @param vertices a layer
     */
    private void randomizeBarycenters(final List<Vertex> vertices) {
        for (Vertex vertex : vertices) {
            vertex.barycenter = random.nextFloat();
            vertex.summedWeight = vertex.barycenter;
            vertex.degree = 1;
        }
    }

    /**
     * Calculates the barycenters of the given vertices.
     * 
     * @param vertices the vertices.
     * @param layerVertices map of the layer's nodes to their single-node vertices.
     * @param forward {@code true} if the current sweep moves forward.
     * @return the total number of encountered edges.
     */
    private int calculateBarycenters(final List<Vertex> vertices, final Map<LNode, Vertex> layerVertices,
            final boolean forward) {
        
        Set<Vertex> workingSet = new HashSet<Vertex>();
        
        int totalEdges = 0;
        for (Vertex vertex : vertices) {
            // Calculate the vertex's new barycenter (may be -1)
            calculateBarycenter(vertex, layerVertices, forward, workingSet);
            totalEdges += vertex.degree;
        }
        
        return totalEdges;
    }
    
    /**
     * Calculates the barycenter of the given single-node-vertex.
     * 
     * @param vertex the vertex, consisting of a single node.
     * @param layerVertices map of the layer's nodes to their single-node vertices.
     * @param forward {@code true} if the current sweep moves forward.
     * @param workingSet a set where vertices whose values are being computed are put into.
     *                   When this method is called on a vertex that's already in the set,
     *                   it immediately returns.
     * @return a pair containing the summed port positions of the connected ports as the
     *         first, and the number of connected edges as the second entry.
     */
    private void calculateBarycenter(final Vertex vertex, final Map<LNode, Vertex> layerVertices,
            final boolean forward, final Set<Vertex> workingSet) {

        // Check if the vertex's barycenter was already computed
        if (workingSet.contains(vertex)) {
            return;
        } else {
            workingSet.add(vertex);
        }
        
        vertex.degree = 0;
        vertex.summedWeight = 0.0f;
        vertex.barycenter = -1.0f;
        LNode node = vertex.nodes.get(0);
        
        for (LPort freePort : node.getPorts(forward ? PortType.INPUT : PortType.OUTPUT)) {
            for (LPort fixedPort : freePort.getConnectedPorts()) {
                // If the node the fixed port belongs to is part of the free layer (thus, if
                // we have an in-layer edge), use that node's barycenter calculation instead
                LNode fixedNode = fixedPort.getNode();
                
                if (fixedNode.getLayer() == node.getLayer()) {
                    // Find the fixed node's vertex and calculate its barycenter
                    Vertex fixedVertex = layerVertices.get(fixedNode);
                    calculateBarycenter(fixedVertex, layerVertices, forward, workingSet);
                    
                    // Update this vertex's values
                    vertex.degree += Math.max(0, fixedVertex.degree - 1);
                    vertex.summedWeight += fixedVertex.summedWeight;
                } else {
                    vertex.summedWeight += portPos[fixedPort.id];
                }
            }
            
            vertex.degree += freePort.getDegree();
        }
        
        // Iterate over the node's barycenter associates
        List<LNode> barycenterAssociates = node.getProperty(Properties.BARYCENTER_ASSOCIATES);
        if (barycenterAssociates != null) {
            for (LNode associate : barycenterAssociates) {
                // Make sure the associate is in the same layer as this node
                if (node.getLayer() != associate.getLayer()) {
                    continue;
                }
                
                // Find the associate's vertex and calculate its barycenter
                Vertex associateVertex = layerVertices.get(associate);
                calculateBarycenter(associateVertex, layerVertices, forward, workingSet);
                
                // Update this vertex's values
                vertex.degree += barycenterAssociateFactor * Math.max(0, associateVertex.degree);
                vertex.summedWeight += barycenterAssociateFactor * associateVertex.summedWeight;
            }
        }
        
        if (vertex.degree > 0) {
            vertex.barycenter = vertex.summedWeight / vertex.degree;
        }
    }
    
    /**
     * Try to find appropriate barycenter values for vertices whose barycenter is < 0.
     * 
     * @param vertices the vertices to fill in barycenters for.
     * @param preOrdered whether the vertices have been ordered in a previous run.
     */
    private void fillInUnknownBarycenters(final List<Vertex> vertices, final boolean preOrdered) {
        // Determine placements for vertices with undefined position value
        if (preOrdered) {
            float lastValue = -1;
            ListIterator<Vertex> vertexIterator = vertices.listIterator();
            
            while (vertexIterator.hasNext()) {
                Vertex vertex = vertexIterator.next();
                float value = vertex.barycenter;
                
                if (value < 0) {
                    float nextValue = lastValue + 1;
                    
                    if (vertexIterator.hasNext()) {
                        Iterator<Vertex> nextVertexIterator =
                            vertices.listIterator(vertexIterator.nextIndex());
                        
                        while (nextVertexIterator.hasNext()) {
                            float x = nextVertexIterator.next().barycenter;
                            if (x >= 0) {
                                nextValue = x;
                                break;
                            }
                        }
                    }
                    
                    value = (lastValue + nextValue) / 2;
                    vertex.barycenter = value;
                }
                
                lastValue = value;
            }
        } else {
            // No previous ordering - determine random placement for new nodes
            float maxBary = 0;
            for (Vertex vertex : vertices) {
                maxBary = Math.max(maxBary, vertex.barycenter);
            }
            
            maxBary += 2;
            for (Vertex vertex : vertices) {
                if (vertex.barycenter < 0) {
                    vertex.barycenter = random.nextFloat() * maxBary - 1;
                }
            }
        }
    }

    /**
     * Builds the vertex graph for the given vertices.
     * 
     * @param vertices the array of single-node vertices sorted by their barycenter values.
     * @param layerVertices map mapping the nodes in the current layer to their vertices.
     */
    private void buildConstraintsGraph(final List<Vertex> vertices,
            final Map<LNode, Vertex> layerVertices) {
        
        // Reset the constraint fields
        for (Vertex vertex : vertices) {
            vertex.outgoingConstraints.clear();
            vertex.incomingConstraintsCount = 0;
        }
        
        // Iterate through the vertices, adding the necessary constraints
        LNode lastNonDummyNode = null;
        for (Vertex vertex : vertices) {
            LNode vertexNode = vertex.nodes.get(0);
            
            // Add the constraints given by the vertex's node
            LNode successor = vertexNode.getProperty(Properties.IN_LAYER_SUCCESSOR_CONSTRAINT);
            if (successor != null) {
                Vertex successorVertex = layerVertices.get(successor);
                vertex.outgoingConstraints.add(successorVertex);
                successorVertex.incomingConstraintsCount++;
            }
            
            // Check if we're processing a a normal, none-dummy node
            if (vertexNode.getProperty(Properties.NODE_TYPE) == NodeType.NORMAL) {
                // If we already processed another normal, non-dummy node, we need to add
                // constraints from all of that other node's layout unit's vertices to this
                // node's layout unit's vertices
                if (lastNonDummyNode != null) {
                    for (LNode lastUnitNode : layoutUnits.get(lastNonDummyNode)) {
                        Vertex lastUnitVertex = layerVertices.get(lastUnitNode);
                        
                        for (LNode currentUnitNode : layoutUnits.get(vertexNode)) {
                            Vertex currentUnitVertex = layerVertices.get(currentUnitNode);
                            lastUnitVertex.outgoingConstraints.add(currentUnitVertex);
                            currentUnitVertex.incomingConstraintsCount++;
                        }
                    }
                }
                
                lastNonDummyNode = vertexNode;
            }
        }
    }
    
    /**
     * Returns a violated constraint, if any is left.
     * 
     * @param vertices list of vertices.
     * @return the two vertices whose constraint is violated, or {@code null} if none
     *         could be found. The two vertices are returned in the order they should
     *         appear in, not in the order that violates their constraint.
     */
    private Pair<Vertex, Vertex> findViolatedConstraint(final List<Vertex> vertices) {
        List<Vertex> activeVertices = new LinkedList<Vertex>();
        Map<Vertex, List<Vertex>> incoming = new HashMap<Vertex, List<Vertex>>();
        
        // Iterate through the constrained vertices
        for (Vertex vertex : vertices) {
            // Ignore unconstrained vertices
            if (vertex.incomingConstraintsCount == 0 && vertex.outgoingConstraints.isEmpty()) {
                continue;
            }
            
            incoming.put(vertex, new LinkedList<Vertex>());
            if (vertex.incomingConstraintsCount == 0) {
                activeVertices.add(vertex);
            }
        }
        
        // Iterate through the active vertices to find one with violated constraints
        while (!activeVertices.isEmpty()) {
            Vertex vertex = activeVertices.remove(0);
            
            // See if we can find a violated constraint
            for (Vertex predecessor : incoming.get(vertex)) {
                if (predecessor.barycenter >= vertex.barycenter) {
                    return new Pair<Vertex, Vertex>(predecessor, vertex);
                }
            }
            
            // No violated constraints; add outgoing constraints to the respective incoming list
            for (Vertex successor : vertex.outgoingConstraints) {
                List<Vertex> successorIncomingList = incoming.get(successor);
                successorIncomingList.add(0, vertex);
                
                if (successor.incomingConstraintsCount == successorIncomingList.size()) {
                    activeVertices.add(successor);
                }
            }
        }
        
        // No violated constraints found
        return null;
    }

    /**
     * Handles the case of a violated constraint.
     * 
     * @param violatedConstraint the violated constraint.
     * @param vertices the array of vertices.
     */
    private void handleViolatedConstraint(final Pair<Vertex, Vertex> violatedConstraint,
            final List<Vertex> vertices) {
        
        Vertex firstVertex = violatedConstraint.getFirst();
        Vertex secondVertex = violatedConstraint.getSecond();
        
        // Create a new vertex from the two constrain-violating vertices; this also
        // automatically calculates the new vertex's barycenter value
        Vertex newVertex = new Vertex(violatedConstraint.getFirst(), violatedConstraint.getSecond());
        
        // Iterate through the vertices. Remove the old vertices. Insert the new one
        // according to the barycenter value, thereby keeping the list sorted. Along
        // the way, constraint relationships will be updated
        ListIterator<Vertex> vertexIterator = vertices.listIterator();
        boolean alreadyInserted = false;
        while (vertexIterator.hasNext()) {
            Vertex vertex = vertexIterator.next();
            
            if (vertex == firstVertex || vertex == secondVertex) {
                // If the vertex is either the first or the second vertex, remove it
                vertexIterator.remove();
            } else if (!alreadyInserted && vertex.barycenter > newVertex.barycenter) {
                // If we haven't inserted the new vertex into the list already, do that now. Note:
                // we're not calling next() again. This means that during the next iteration, we
                // will again be looking at the current vertex. But then, alreadyInserted will be
                // true and we can look at vertex's outgoing constraints.
                vertexIterator.previous();
                vertexIterator.add(newVertex);
                
                alreadyInserted = true;
            } else {
                // Check if the vertex has any constraints with the former two vertices
                boolean firstVertexConstraint = vertex.outgoingConstraints.remove(firstVertex);
                boolean secondVertexConstraint = vertex.outgoingConstraints.remove(secondVertex);
                
                if (firstVertexConstraint || secondVertexConstraint) {
                    vertex.outgoingConstraints.add(newVertex);
                    newVertex.incomingConstraintsCount++;
                }
            }
        }
        
        // If we haven't inserted the new vertex already, do that now
        if (!alreadyInserted) {
            vertices.add(newVertex);
        }
    }
    
    /**
     * Apply the node order as determined by the sorted list of vertices to the free layer
     * array.
     * 
     * @param vertices sorted array of vertices.
     * @param freeLayer array of nodes to apply the ordering to.
     */
    private void applyVertexOrderingToNodeArray(final List<Vertex> vertices, final LNode[] freeLayer) {
        int index = 0;
        
        for (Vertex vertex : vertices) {
            for (LNode node : vertex.nodes) {
                freeLayer[index++] = node;
            }
        }
    }
    
    
    ///////////////////////////////////////////////////////////////////////////////
    // Cross Counting
    
    /**
     * Calculate the number of crossings between the two given layers.
     * 
     * Taken from W. Barth , M. Juenger, P. Mutzel: <em>Simple and efficient bilayer
     * cross counting</em>, LNCS 2528, pp. 331-360, 2002.
     * 
     * @param leftLayer the left layer
     * @param rightLayer the right layer
     * @param edgeCount the total number of edges in the layer
     * @return the number of edge crossings
     */
    private int countCrossings(final LNode[] leftLayer, final LNode[] rightLayer, final int edgeCount) {
        Map<LPort, Integer> targetMap = new HashMap<LPort, Integer>();
        
        // Assign index values to the ports of the right layer
        int targetCount = 0;
        for (LNode node : rightLayer) {
            if (node.getProperty(LayoutOptions.PORT_CONSTRAINTS).isOrderFixed()) {
                List<LPort> inputPorts = getSortedInputPorts(node);
                ListIterator<LPort> portIter = inputPorts.listIterator(inputPorts.size());
                while (portIter.hasPrevious()) {
                    targetMap.put(portIter.previous(), targetCount++);
                }
            } else {
                for (LPort port : node.getPorts(PortType.INPUT)) {
                    targetMap.put(port, targetCount);
                }
                targetCount++;
            }
        }
        
        // Determine the sequence of edge target positions sorted by source and target index
        int[] southSequence = new int[edgeCount];
        int i = 0;
        for (LNode node : leftLayer) {
            if (node.getProperty(LayoutOptions.PORT_CONSTRAINTS).isOrderFixed()) {
                for (LPort port : node.getPorts(PortType.OUTPUT)) {
                    int start = i;
                    for (LEdge edge : port.getOutgoingEdges()) {
                        Integer pos = targetMap.get(edge.getTarget());
                        if (pos != null) {
                            insert(southSequence, start, i++, pos);
                        }
                    }
                }
            } else {
                int start = i;
                for (LPort port : node.getPorts(PortType.OUTPUT)) {
                    for (LEdge edge : port.getOutgoingEdges()) {
                        Integer pos = targetMap.get(edge.getTarget());
                        if (pos != null) {
                            insert(southSequence, start, i++, pos);
                        }
                    }
                }
            }
        }
        
        // Build the accumulator tree
        int firstIndex = 1;
        while (firstIndex < targetCount) {
            firstIndex *= 2;
        }
        int treeSize = 2 * firstIndex - 1;
        firstIndex -= 1;
        int[] tree = new int[treeSize];
        
        // Count the crossings
        int crossCount = 0;
        for (int k = 0; k < edgeCount; k++) {
            int index = southSequence[k] + firstIndex;
            tree[index]++;
            while (index > 0) {
                if (index % 2 > 0) {
                    crossCount += tree[index + 1];
                }
                index = (index - 1) / 2;
                tree[index]++;
            }
        }
        
        return crossCount;
    }
    
    /* The algorithm used to count crossings within a layer implemented in the
     * following method has two parts:
     * 
     * Part 1
     * A normal node cannot be connected to another normal node in the same layer due
     * to how layering is performed. It remains that at least one of the two nodes must
     * be a dummy node. Currently, that can only happen due to odd port side handling.
     * Because of the way dummies are created, there are only two cases:
     * 
     *  - An eastern port can be connected to another eastern port.
     *  - A western port can be connected to another western port.
     * 
     * The algorithm now works by assigning numbers to eastern ports top-down, and to
     * western ports bottom-up, all the time dependent on their number of incident
     * edges. (the link direction is not important) Then we traverse the ports. If we
     * find an eastern port connected to another eastern port, the difference of their
     * indices tells us how many other ports with incident edges lie between them and
     * can cause crossings.
     * 
     * Part 2
     * Additional crossings can happen due to nodes being placed between a node and
     * its north / south dummies. The idea here is to use the first node sweep from
     * part 1 to count the number of northern and southern North/South dummies for
     * each node. Each north dummy is assigned a position in the list of northern
     * dummies for its node. South dummies are treated accordingly.
     * 
     * In a second sweep, for each non-north/south dummy, the most recently encountered
     * north/south dummy or normal node is retrieved. Its index is subtracted from the
     * number of northern or southern dummies of its node. The result gives the number
     * of crossings caused by the node being placed between a node and its north/south
     * dummies.
     * 
     * Note that part two relies on information about layer layout units. If we find
     * that they have not been set, we can skip this part.
     */
    
    /**
     * Calculates the worst case for the number of crossings caused by in-layer edges in
     * the given layer. The actual number of crossings may be lower.
     * 
     * @param layer the layer whose in-layer crossings to estimate.
     * @return the worst possible number of crossings.
     */
    private int countCrossings(final LNode[] layer) {
        int crossings = 0;
        
        // Number of north/south dummies and indices
        Map<LNode, Pair<Integer, Integer>> northSouthCrossingHints =
            new HashMap<LNode, Pair<Integer, Integer>>();
        Map<LNode, Integer> dummyIndices = new HashMap<LNode, Integer>();
        
        // Assign numbers to the layer's eastern and western ports
        Map<LPort, Integer> easternPortNumbers = new HashMap<LPort, Integer>();
        Map<LPort, Integer> westernPortNumbers = new HashMap<LPort, Integer>();
        
        numberEastWestPorts(layer, easternPortNumbers, westernPortNumbers);
        
        // Iterate through the nodes
        LNode currentNormalNode = null;
        int northMaxCrossingHint = 0;
        int southMaxCrossingHint = 0;
        boolean northernSide = true;
        boolean layerLayoutUnitsSet = true;
        
        for (LNode node : layer) {
            // Part 1 of the crossing counting algorithm
            for (LPort port : node.getPorts()) {
                switch (port.getSide()) {
                case EAST:
                    crossings += countInLayerCrossings(port, easternPortNumbers);
                    break;
                
                case WEST:
                    crossings += countInLayerCrossings(port, westernPortNumbers);
                    break;
                }
            }

            // First sweep of part 2 of the crossing counting algorithm
            NodeType nodeType = node.getProperty(Properties.NODE_TYPE);
            if (layerLayoutUnitsSet && (nodeType == NodeType.NORMAL
                    || nodeType == NodeType.NORTH_SOUTH_PORT)) {
                
                LNode newNormalNode = node.getProperty(Properties.IN_LAYER_LAYOUT_UNIT);
                if (newNormalNode == null) {
                    // Layer layout units don't seem to have been set
                    layerLayoutUnitsSet = false;
                    continue;
                }
                
                // Check if this node belongs to a new normal node
                if (currentNormalNode != newNormalNode) {
                    // Save the old normal node's values
                    if (currentNormalNode != null) {
                        northSouthCrossingHints.put(currentNormalNode, new Pair<Integer, Integer>(
                                northMaxCrossingHint, southMaxCrossingHint));
                    }
                    
                    // Reset the counters
                    currentNormalNode = newNormalNode;
                    northMaxCrossingHint = 0;
                    southMaxCrossingHint = 0;
                    northernSide = true;
                }
                
                // If the node is the normal node, we're entering its south side
                if (node == currentNormalNode) {
                    northernSide = false;
                }
                
                // Update and save crossing hints
                if (northernSide) {
                    northMaxCrossingHint += node.getProperty(Properties.CROSSING_HINT);
                    dummyIndices.put(node, northMaxCrossingHint);
                } else {
                    southMaxCrossingHint += node.getProperty(Properties.CROSSING_HINT);
                    dummyIndices.put(node, southMaxCrossingHint);
                }
            }
        }
        
        // Remember to save the values for the last normal node
        if (currentNormalNode != null) {
            northSouthCrossingHints.put(currentNormalNode, new Pair<Integer, Integer>(
                    northMaxCrossingHint, southMaxCrossingHint));
        }
        
        // Second sweep of Part 2 of the algorithm
        if (layerLayoutUnitsSet) {
            LNode lastDummyNormalNode = null;
            int lastDummyIndex = 0;
            int dummyCount = 0;
            
            for (LNode node : layer) {
                NodeType nodeType = node.getProperty(Properties.NODE_TYPE);
                
                switch (nodeType) {
                case NORMAL:
                    lastDummyIndex = dummyIndices.get(node);
                    
                    lastDummyNormalNode = node;
                    dummyCount = northSouthCrossingHints.get(node).getSecond();
                    break;
                    
                case NORTH_SOUTH_PORT:
                    lastDummyIndex = dummyIndices.get(node);
                    
                    LNode newNormalNode = node.getProperty(Properties.IN_LAYER_LAYOUT_UNIT);
                    if (newNormalNode != lastDummyNormalNode) {
                        dummyCount = northSouthCrossingHints.get(newNormalNode).getFirst();
                        lastDummyNormalNode = newNormalNode;
                    }
                    break;
                    
                default:
                    crossings += dummyCount - lastDummyIndex;
                }
            }
        }
        
        return crossings;
    }
    
    /**
     * Assigns numbers to the eastern ports of a layer, and to the western ports of a layer. A
     * number is assigned to a port if it has incident edges. Eastern ports are numbered top-down,
     * while western ports are numbered bottom-up.
     * 
     * @param layer the layer whose ports to index.
     * @param easternMap map to put the eastern ports' indices in.
     * @param westernMap map to put the western ports' indices in.
     */
    private void numberEastWestPorts(final LNode[] layer, final Map<LPort, Integer> easternMap,
            final Map<LPort, Integer> westernMap) {

        int currentEasternNumber = 0;
        int currentWesternNumber = 0;
        
        // Assign numbers to eastern ports, top-down
        for (int nodeIndex = 0; nodeIndex < layer.length; nodeIndex++) {
            LNode node = layer[nodeIndex];

            if (node.getProperty(LayoutOptions.PORT_CONSTRAINTS).isOrderFixed()) {
                for (LPort easternPort : node.getPorts(PortSide.EAST)) {
                    if (easternPort.getDegree() > 0) {
                        currentEasternNumber += easternPort.getDegree();
                        easternMap.put(easternPort, currentEasternNumber);
                    }
                }
            } else {
                // Find the number of edges incident to eastern ports
                for (LPort easternPort : node.getPorts(PortSide.EAST)) {
                    currentEasternNumber += easternPort.getDegree();
                }
                
                // Assign the eastern number to all eastern ports
                for (LPort easternPort : node.getPorts(PortSide.EAST)) {
                    if (easternPort.getDegree() > 0) {
                        easternMap.put(easternPort, currentEasternNumber);
                    }
                }
            }
        }
        
        // Assign indices to western ports, bottom-up
        for (int nodeIndex = layer.length - 1; nodeIndex >= 0; nodeIndex--) {
            LNode node = layer[nodeIndex];

            if (node.getProperty(LayoutOptions.PORT_CONSTRAINTS).isOrderFixed()) {
                for (LPort westernPort : node.getPorts(PortSide.WEST)) {
                    if (westernPort.getDegree() > 0) {
                        currentWesternNumber += westernPort.getDegree();
                        westernMap.put(westernPort, currentWesternNumber);
                    }
                }
            } else {
                // Find the number of edges incident to western ports
                for (LPort westernPort : node.getPorts(PortSide.WEST)) {
                    currentWesternNumber += westernPort.getDegree();
                }
                
                // Assign the western number to all western ports
                for (LPort westernPort : node.getPorts(PortSide.WEST)) {
                    if (westernPort.getDegree() > 0) {
                        westernMap.put(westernPort, currentWesternNumber);
                    }
                }
            }
        }
    }
    
    /**
     * Counts the crossings caused by in-layer edges connected to the given port. An edge
     * is only counted once.
     * 
     * @param port the port whose edge crossings to count.
     * @param portIndices map of ports to their respective indices as calculated by
     *                    {@link #numberEastWestPorts(LNode[], Map, Map)}.
     * @return the maximum number of crossings for this port.
     */
    private int countInLayerCrossings(final LPort port, final Map<LPort, Integer> portIndices) {
        int maxCrossings = 0;
        
        // Find this port's index
        Integer portIndex = portIndices.get(port);
        if (portIndex == null) {
            return 0;
        }
        
        // Find the maximum distance between two connected ports
        Integer connectedPortIndex = null;
        for (LEdge edge : port.getConnectedEdges()) {
            if (edge.getSource() == port) {
                connectedPortIndex = portIndices.get(edge.getTarget());
            } else {
                connectedPortIndex = portIndices.get(edge.getSource());
            }
            
            // Check if the edge is connected to another port in the same layer
            if (connectedPortIndex != null) {
                // Only count the edge once
                if (portIndex.intValue() > connectedPortIndex.intValue()) {
                    maxCrossings = Math.max(maxCrossings,
                            portIndex.intValue() - connectedPortIndex.intValue() - 1);
                }
            }
        }
        
        return maxCrossings;
    }
    

    ///////////////////////////////////////////////////////////////////////////////
    // Port Position Assignment
    
    /**
     * Determine positions for all ports in the given layer. Input and output ports are processed
     * separately.
     * 
     * @param layer a layer
     */
    private void assignPortPos(final LNode[] layer) {
        int nodeIx = 0;
        for (LNode node : layer) {
            // count the input and output ports
            int inputPorts = 0, outputPorts = 0;
            for (LPort port : node.getPorts()) {
                if (port.getNetFlow() < 0) {
                    outputPorts++;
                } else {
                    inputPorts++;
                }
            }
            
            // set positions for the input ports
            if (inputPorts > 0) {
                assignPortPos(node, nodeIx, PortType.INPUT, inputPorts);
            }
            
            // set positions for the output ports
            if (outputPorts > 0) {
                assignPortPos(node, nodeIx, PortType.OUTPUT, outputPorts);
            }
            
            nodeIx++;
        }
    }
    
    /**
     * Assign port positions for the input or output ports of the given node.
     * 
     * @param node a node
     * @param nodeIx the node's index in its layer
     * @param type the type of ports to process
     * @param count the number of ports of that type
     */
    private void assignPortPos(final LNode node, final int nodeIx,
            final PortType type, final int count) {
        
        if (node.getProperty(LayoutOptions.PORT_CONSTRAINTS).isOrderFixed()) {
            float incr = 1.0f / count;
            
            if (type == PortType.INPUT) {
                // Start at the top right port, going counter-clockwise
                List<LPort> portList = getSortedInputPorts(node);
                float pos = nodeIx + 1 - incr;
                
                for (LPort port : portList) {
                    portPos[port.id] = pos;
                    pos -= incr;
                }
                
            } else {
                // Start at the top left port, going clockwise
                float pos = nodeIx;
                
                for (LPort port : node.getPorts(type)) {
                    portPos[port.id] = pos;
                    pos += incr;
                }
            }
        } else {
            for (LPort port : node.getPorts(type)) {
                portPos[port.id] = nodeIx + getPortIncr(type, port.getSide());
            }
        }
    }

    /**
     * Returns a list of input ports, beginning at the top right port of the eastern
     * side, going clockwise.
     * 
     * @param node the node whose input ports to return.
     * @return list of input ports.
     */
    private List<LPort> getSortedInputPorts(final LNode node) {
        List<LPort> northPorts = new LinkedList<LPort>();
        List<LPort> portList = new LinkedList<LPort>();
        
        for (LPort port : node.getPorts(PortType.INPUT)) {
            if (port.getSide() == PortSide.NORTH) {
                northPorts.add(port);
            } else {
                portList.add(port);
            }
        }
        
        // Append the list of northern ports to the other ports
        portList.addAll(northPorts);
        return portList;
    }
    
    private static final float INCR_ONE = 0.3f;
    private static final float INCR_TWO = 0.5f;
    private static final float INCR_THREE = 0.7f;
    private static final float INCR_FOUR = 0.9f;
    
    /**
     * Return an increment value for the position of a port with given type and side.
     * 
     * @param type the port type
     * @param side the port side
     * @return a position increment for the port
     */
    private static float getPortIncr(final PortType type, final PortSide side) {
        switch (type) {
        case INPUT:
            switch (side) {
            case NORTH:
                return INCR_ONE;
            case WEST:
                return INCR_TWO;
            case SOUTH:
                return INCR_THREE;
            case EAST:
                return INCR_FOUR;
            }
            break;
        case OUTPUT:
            switch (side) {
            case NORTH:
                return INCR_ONE;
            case EAST:
                return INCR_TWO;
            case SOUTH:
                return INCR_THREE;
            case WEST:
                return INCR_FOUR;
            }
            break;
        }
        return 0;
    }
    
    
    ///////////////////////////////////////////////////////////////////////////////
    // Port Distribution
    
    /**
     * Distribute the ports of the layered graph depending on the port constraints.
     * 
     * @param layeredGraph a layered graph
     */
    private void distributePorts(final LNode[][] layeredGraph) {
        for (int l = 0; l < layeredGraph.length; l++) {
            if (l + 1 < layeredGraph.length) {
                assignPortPos(layeredGraph[l + 1]);
            }
            LNode[] layer = layeredGraph[l];
            for (int i = 0; i < layer.length; i++) {
                LNode node = layer[i];
                if (!node.getProperty(LayoutOptions.PORT_CONSTRAINTS).isOrderFixed()) {
                    // the order of ports on each side is variable, so distribute the ports
                    distributePorts(node);
                    node.setProperty(LayoutOptions.PORT_CONSTRAINTS, PortConstraints.FIXED_ORDER);
                    int outputPortCount = 0;
                    for (LPort port : node.getPorts()) {
                        if (port.getNetFlow() < 0) {
                            outputPortCount++;
                        }
                    }
                    if (outputPortCount > 0) {
                        assignPortPos(node, i, PortType.OUTPUT, outputPortCount);
                    }
                }
            }
        }
    }
    
    /**
     * Distribute the ports of the given node by their sides, connected ports, and input
     * or output type.
     * 
     * @param node node whose ports shall be sorted
     */
    private void distributePorts(final LNode node) {
        // calculate barycenter values for the ports of the node
        for (LPort port : node.getPorts()) {
            float sum = 0;
            for (LPort connectedPort : port.getConnectedPorts()) {
                sum += portPos[connectedPort.id];
            }
            if (port.getDegree() == 0) {
                portBarycenter[port.id] = -1;
            } else {
                portBarycenter[port.id] = sum / port.getDegree();
            }
        }
        // sort the ports by considering the side, type, and barycenter values
        node.sortPorts(portBarycenter);
    }
    
    
    ///////////////////////////////////////////////////////////////////////////////
    // Utility Methods

    /**
     * Copy the content of the source node array to the target node array.
     * 
     * @param source a layered graph
     * @param dest a node array to copy the graph into
     */
    private static void copySweep(final LNode[][] source, final LNode[][] dest) {
        for (int i = 0; i < dest.length; i++) {
            for (int j = 0; j < dest[i].length; j++) {
                dest[i][j] = source[i][j];
            }
        }
    }
    
    /**
     * Insert a number into a sorted range of an array.
     * 
     * @param array an integer array
     * @param start the start index of the search range (inclusive)
     * @param end the end index of the search range (exclusive)
     * @param n the number to insert
     */
    private static void insert(final int[] array, final int start, final int end, final int n) {
        int insx = binarySearch(array, start, end, n);
        if (insx < 0) {
            insx = -insx - 1;
        }
        for (int j = end - 1; j >= insx; j--) {
            array[j + 1] = array[j];
        }
        
        if (insx >= array.length) {
            System.out.println("BLA!");
        } else {
            array[insx] = n;
        }
    }
    
    /**
     * Searches a range of the specified array of ints for the specified value using the
     * binary search algorithm. The range must be sorted prior to making this call.
     *
     * @param a the array to be searched
     * @param fromIndex the index of the first element (inclusive) to be searched
     * @param toIndex the index of the last element (exclusive) to be searched
     * @param key the value to be searched for
     * @return index of the search key
     */
    private static int binarySearch(final int[] a, final int fromIndex,
            final int toIndex, final int key) {
        int low = fromIndex;
        int high = toIndex - 1;

        while (low <= high) {
            int mid = (low + high) >>> 1;
            int midVal = a[mid];

            if (midVal < key) {
                low = mid + 1;
            } else if (midVal > key) {
                high = mid - 1;
            } else {
                return mid; // key found
            }
        }
        return -(low + 1); // key not found
    }
}
