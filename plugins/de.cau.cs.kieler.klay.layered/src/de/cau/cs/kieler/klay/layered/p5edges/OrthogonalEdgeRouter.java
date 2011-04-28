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
package de.cau.cs.kieler.klay.layered.p5edges;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.kiml.options.PortType;
import de.cau.cs.kieler.klay.layered.ILayoutPhase;
import de.cau.cs.kieler.klay.layered.IntermediateProcessingStrategy;
import de.cau.cs.kieler.klay.layered.Properties;
import de.cau.cs.kieler.klay.layered.Util;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;
import de.cau.cs.kieler.klay.layered.intermediate.IntermediateLayoutProcessor;

/**
 * Edge routing implementation that creates orthogonal bend points. Inspired by
 * <ul>
 *   <li>Georg Sander. Layout of directed hypergraphs with orthogonal hyperedges. In
 *     <i>Proceedings of the 11th International Symposium on Graph Drawing (GD '03)</i>,
 *     volume 2912 of LNCS, pp. 381-386. Springer, 2004.</li>
 *   <li>Giuseppe di Battista, Peter Eades, Roberto Tamassia, Ioannis G. Tollis,
 *     <i>Graph Drawing: Algorithms for the Visualization of Graphs</i>,
 *     Prentice Hall, New Jersey, 1999 (Section 9.4, for cycle breaking in the
 *     hyperedge segment graph)
 * </ul>
 * 
 * <dl>
 *   <dt>Precondition:</dt><dd>the graph has a proper layering with
 *     assigned node and port positions; the size of each layer is
 *     correctly set; edges connected to ports on strange sides were
 *     processed</dd>
 *   <dt>Postcondition:</dt><dd>each node is assigned a horizontal coordinate;
 *     the bend points of each edge are set; the width of the whole graph is set</dd>
 * </dl>
 *
 * @author msp
 * @author cds
 */
public class OrthogonalEdgeRouter extends AbstractAlgorithm implements ILayoutPhase {
    
    /** intermediate processing strategy for basic graphs. */
    private static final IntermediateProcessingStrategy BASELINE_PROCESSING_STRATEGY =
        new IntermediateProcessingStrategy(
                // Before Phase 1
                null,
                
                // Before Phase 2
                null,
                
                // Before Phase 3
                /* For non-free ports:
                 *  - CONSTRAINED_EXTERNAL_PORT_PROCESSOR
                 *  - NORTH_SOUTH_PORT_PREPROCESSOR
                 *  - ODD_PORT_SIDE_PROCESSOR
                 * 
                 * For self-loops:
                 *  - SELF_LOOP_PROCESSOR
                 */
                null,
                
                // Before Phase 4
                EnumSet.of(
                        IntermediateLayoutProcessor.HYPEREDGE_DUMMY_MERGER,
                        IntermediateLayoutProcessor.NODE_MARGIN_CALCULATOR),
                
                // Before Phase 5
                null,
                
                // After Phase 5
                /* For non-free ports:
                 *  - NORTH_SOUTH_PORT_POSTPROCESSOR
                 * 
                 * For external ports:
                 *  - EXTERNAL_PORT_ORTHOGONAL_EDGE_ROUTER
                 */
                null);
    
    /** additional processor dependencies for graphs with non-free ports. */
    private static final IntermediateProcessingStrategy NON_FREE_PORT_PROCESSING_ADDITIONS =
        new IntermediateProcessingStrategy(IntermediateProcessingStrategy.BEFORE_PHASE_3,
                IntermediateLayoutProcessor.ODD_PORT_SIDE_PROCESSOR);
    
    /** additional processor dependencies for graphs with northern / southern non-free ports. */
    private static final IntermediateProcessingStrategy NORTH_SOUTH_PORT_PROCESSING_ADDITIONS =
        new IntermediateProcessingStrategy(
                // Before Phase 1
                null,
                
                // Before Phase 2
                null,
                
                // Before Phase 3
                EnumSet.of(IntermediateLayoutProcessor.NORTH_SOUTH_PORT_PREPROCESSOR),
                
                // Before Phase 4
                null,
                
                // Before Phase 5
                null,
                
                // After Phase 5
                EnumSet.of(IntermediateLayoutProcessor.NORTH_SOUTH_PORT_POSTPROCESSOR));
    
    /** additional processor dependencies for graphs with external ports. */
    private static final IntermediateProcessingStrategy EXTERNAL_PORT_PROCESSING_ADDITIONS =
        new IntermediateProcessingStrategy(
                // Before Phase 1
                null,
                
                // Before Phase 2
                null,
                
                // Before Phase 3
                EnumSet.of(IntermediateLayoutProcessor.CONSTRAINED_EXTERNAL_PORT_PROCESSOR),
                
                // Before Phase 4
                null,
                
                // Before Phase 5
                null,
                
                // After Phase 5
                EnumSet.of(IntermediateLayoutProcessor.EXTERNAL_PORT_ORTHOGONAL_EDGE_ROUTER));
    
    /** additional processor dependencies for graphs with self-loops. */
    private static final IntermediateProcessingStrategy SELF_LOOP_PROCESSING_ADDITIONS =
        new IntermediateProcessingStrategy(IntermediateProcessingStrategy.BEFORE_PHASE_3,
                IntermediateLayoutProcessor.SELF_LOOP_PROCESSOR);
    
    
    /** weight penalty for conflicts of horizontal line segments. */
    private static final int CONFLICT_PENALTY = 16;

    /**
     * A hypernode used for routing a hyperedge.
     */
    private static class HyperNode implements Comparable<HyperNode> {
        /** ports represented by this hypernode. */
        private List<LPort> ports = new LinkedList<LPort>();
        /** mark value used for cycle breaking. */
        private int mark;
        /** the rank determines the horizontal distance to the preceding layer. */
        private int rank;
        /** vertical starting position of this hypernode. */
        private double start = Double.NaN;
        /** vertical ending position of this hypernode. */
        private double end = Double.NaN;
        /** positions of line segments going to the preceding layer. */
        private List<Double> sourcePosis = new LinkedList<Double>();
        /** positions of line segments going to the next layer. */
        private List<Double> targetPosis = new LinkedList<Double>();
        /** list of outgoing dependencies. */
        private List<Dependency> outgoing = new LinkedList<Dependency>();
        /** sum of the weights of outgoing dependencies. */
        private int outweight;
        /** list of incoming dependencies. */
        private List<Dependency> incoming = new LinkedList<Dependency>();
        /** sum of the weights of incoming depencencies. */
        private int inweight;
        
        /**
         * Adds the positions of the given port and all connected ports.
         * 
         * @param port a port
         * @param hyperNodeMap map of ports to existing hypernodes
         */
        void addPortPosis(final LPort port, final Map<LPort, HyperNode> hyperNodeMap) {
            hyperNodeMap.put(port, this);
            ports.add(port);
            double pos = port.getNode().getPosition().y + port.getPosition().y;
            
            // set new start position
            if (Double.isNaN(start)) {
                start = pos;
            } else {
                start = Math.min(start, pos);
            }
            
            // set new end position
            if (Double.isNaN(end)) {
                end = pos;
            } else {
                end = Math.max(end, pos);
            }
            
            // add the new port position to the respective list
            if (port.getSide() == PortSide.WEST) {
                insertSorted(targetPosis, pos);
            } else {
                insertSorted(sourcePosis, pos);
            }
            
            // add connected ports
            for (LPort otherPort : port.getConnectedPorts()) {
                if (!hyperNodeMap.containsKey(otherPort)) {
                    addPortPosis(otherPort, hyperNodeMap);
                }
            }
        }
        
        /**
         * {@inheritDoc}
         */
        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder("{");
            Iterator<LPort> portIter = ports.iterator();
            while (portIter.hasNext()) {
                LPort port = portIter.next();
                String name = port.getNode().getName();
                if (name == null) {
                    name = "n" + port.getNode().getIndex();
                }
                builder.append(name);
                if (portIter.hasNext()) {
                    builder.append(',');
                }
            }
            builder.append('}');
            return builder.toString();
        }

        /**
         * {@inheritDoc}
         */
        public int compareTo(final HyperNode other) {
            return this.mark - other.mark;
        }
    }

    /**
     * A dependency between two hypernodes.
     */
    private static final class Dependency {
        /** the source hypernode of this dependency. */
        private HyperNode source;
        /** the target hypernode of this dependency. */
        private HyperNode target;
        /** the weight of this dependency. */
        private int weight;
        
        /**
         * Creates a dependency from the given source to the given target.
         * 
         * @param thesource the dependency source
         * @param thetarget the dependency target
         * @param theweight weight of the dependency
         */
        private Dependency(final HyperNode thesource, final HyperNode thetarget,
                final int theweight) {
            this.target = thetarget;
            this.source = thesource;
            this.weight = theweight;
            source.outgoing.add(this);
            target.incoming.add(this);
        }
        
        /**
         * {@inheritDoc}
         */
        @Override
        public String toString() {
            return source + "->" + target;
        }
    }
    
    /** factor for edge spacing used to determine the conflict threshold. */
    private static final double CONFL_THRESH_FACTOR = 0.2;
    
    /** spacing between nodes and edges. */
    private double nodeSpacing;
    /** spacing between edges. */
    private double edgeSpacing;
    /** threshold at which conflicts of horizontal line segments are detected. */
    private double conflictThreshold;
    
    /**
     * {@inheritDoc}
     */
    public IntermediateProcessingStrategy getIntermediateProcessingStrategy(final LayeredGraph graph) {
        Set<Properties.GraphProperties> graphProperties = graph.getProperty(Properties.GRAPH_PROPERTIES);
        
        // Basic strategy
        IntermediateProcessingStrategy strategy = new IntermediateProcessingStrategy(
                BASELINE_PROCESSING_STRATEGY);
        
        // Additional dependencies
        if (graphProperties.contains(Properties.GraphProperties.NON_FREE_PORTS)) {
            strategy.addAll(NON_FREE_PORT_PROCESSING_ADDITIONS);

            if (graphProperties.contains(Properties.GraphProperties.NORTH_SOUTH_PORTS)) {
                strategy.addAll(NORTH_SOUTH_PORT_PROCESSING_ADDITIONS);
            }
        }

        if (graphProperties.contains(Properties.GraphProperties.EXTERNAL_PORTS)) {
            strategy.addAll(EXTERNAL_PORT_PROCESSING_ADDITIONS);
        }

        if (graphProperties.contains(Properties.GraphProperties.SELF_LOOPS)) {
            strategy.addAll(SELF_LOOP_PROCESSING_ADDITIONS);
        }
        
        return strategy;
    }
    
    /**
     * {@inheritDoc}
     */
    public void process(final LayeredGraph layeredGraph) {
        getMonitor().begin("Orthogonal edge routing", 1);
        
        nodeSpacing = layeredGraph.getProperty(Properties.OBJ_SPACING);
        edgeSpacing = nodeSpacing * layeredGraph.getProperty(Properties.EDGE_SPACING_FACTOR);
        conflictThreshold = CONFL_THRESH_FACTOR * edgeSpacing;
        boolean debug = layeredGraph.getProperty(LayoutOptions.DEBUG_MODE);
        
        float xpos = 0.0f;
        Iterator<Layer> layerIter = layeredGraph.getLayers().iterator();
        Layer leftLayer = null;
        Layer rightLayer = null;
        
        do {
            int slotsCount;
            
            // Fetch the next layer, if any
            rightLayer = layerIter.hasNext() ? layerIter.next() : null;
            
            // Place the left layer's nodes, if any
            if (leftLayer != null) {
                leftLayer.placeNodes(xpos);
                xpos += leftLayer.getSize().x + nodeSpacing;
            }
            
            // Route edges between the two layers
            slotsCount = routeEdges(layeredGraph, leftLayer, rightLayer, xpos, debug);
            if (slotsCount > 0) {
                xpos += slotsCount * edgeSpacing - edgeSpacing + nodeSpacing;
            }
            
            leftLayer = rightLayer;
        } while (rightLayer != null);
        
        layeredGraph.getSize().x = xpos;
        getMonitor().done();
    }
    
    /** denominator for edge spacing to determine when an edge shall be drawn without bends. */
    private static final int STRAIGHT_TOLERANCE = 256;
    
    /**
     * Route edges between the given layers.
     * 
     * @param layeredGraph the layered graph.
     * @param leftLayer the left layer. May be {@code null}.
     * @param rightLayer the right layer. May be {@code null}.
     * @param xpos horizontal position of the first routing slot
     * @param debug {@code true} if segment dependency graphs should be written to the
     *              debug directory.
     * @return the number of routing slots for this layer
     */
    private int routeEdges(final LayeredGraph layeredGraph, final Layer leftLayer,
            final Layer rightLayer, final double xpos, final boolean debug) {
        
        Map<LPort, HyperNode> portToHyperNodeMap = new HashMap<LPort, HyperNode>();
        List<HyperNode> hyperNodes = new LinkedList<HyperNode>();
        
        // create hypernodes for eastern output ports of the left layer and for western
        // output ports of the right layer
        createHyperNodes(leftLayer, PortSide.EAST, hyperNodes, portToHyperNodeMap);
        createHyperNodes(rightLayer, PortSide.WEST, hyperNodes, portToHyperNodeMap);
        
        // create dependencies for the hypernode ordering graph
        ListIterator<HyperNode> iter1 = hyperNodes.listIterator();
        while (iter1.hasNext()) {
            HyperNode hyperNode1 = iter1.next();
            ListIterator<HyperNode> iter2 = hyperNodes.listIterator(iter1.nextIndex());
            while (iter2.hasNext()) {
                HyperNode hyperNode2 = iter2.next();
                createDependency(hyperNode1, hyperNode2, conflictThreshold);
            }
        }
        
        // write the full dependency graph to an output file
        if (debug) {
            writeDebugGraph(layeredGraph, leftLayer, hyperNodes, "full");
        }
        
        // break cycles
        breakCycles(hyperNodes);

        // write the acyclic dependency graph to an output file
        if (debug) {
            writeDebugGraph(layeredGraph, leftLayer, hyperNodes, "acyc");
        }
        
        // assign ranks to the hypernodes
        topologicalNumbering(hyperNodes);
        
        // set bend points with appropriate coordinates
        int rankCount = -1;
        for (HyperNode node : hyperNodes) {
            // Hypernodes that are just straight lines don't take up a slot and don't need
            // bend points
            if (node.start == node.end) {
                continue;
            }
            
            rankCount = Math.max(rankCount, node.rank);
            
            // Calculate coordinates for each port's bend points
            double x = xpos + node.rank * edgeSpacing;
            for (LPort port : node.ports) {
                double sourcey = port.getNode().getPosition().y + port.getPosition().y;
                
                for (LEdge edge : port.getOutgoingEdges()) {
                    double targety = edge.getTarget().getNode().getPosition().y
                            + edge.getTarget().getPosition().y;
                    if (Math.abs(sourcey - targety) > edgeSpacing / STRAIGHT_TOLERANCE) {
                        KVector point1 = new KVector(x, sourcey);
                        edge.getBendPoints().add(point1);
                        KVector point2 = new KVector(x, targety);
                        edge.getBendPoints().add(point2);
                    }
                }
            }
        }
        return rankCount + 1;
    }

    /**
     * Creates hypernodes for the given layer.
     * 
     * @param layer the layer. May be {@code null}, in which case nothing happens.
     * @param portSide side of the output ports for whose outgoing edges hypernodes should
     *                 be created.
     * @param hyperNodes list the created hypernodes should be added to.
     * @param portToHyperNodeMap map from ports to hypernodes that should be filled.
     */
    private void createHyperNodes(final Layer layer, final PortSide portSide,
            final List<HyperNode> hyperNodes, final Map<LPort, HyperNode> portToHyperNodeMap) {
        
        if (layer != null) {
            for (LNode node : layer.getNodes()) {
                for (LPort port : node.getPorts(PortType.OUTPUT, portSide)) {
                    HyperNode hyperNode = portToHyperNodeMap.get(port);
                    if (hyperNode == null) {
                        hyperNode = new HyperNode();
                        hyperNodes.add(hyperNode);
                        hyperNode.addPortPosis(port, portToHyperNodeMap);
                    }
                }
            }
        }
    }

    /**
     * Create a dependency between the two given hypernodes, if one is needed. 
     * 
     * @param hn1 first hypernode
     * @param hn2 second hypernode
     * @param minDiff the minimal difference between horizontal line segments to avoid a conflict
     */
    private static void createDependency(final HyperNode hn1, final HyperNode hn2,
            final double minDiff) {
        
        // check if at least one of the two nodes is just a straight line; those don't
        // create dependencies since they don't take up a slot
        if (hn1.start == hn1.end || hn2.start == hn2.end) {
            return;
        }
        
        // compare number of conflicts for both variants
        int conflicts1 = countConflicts(hn1.targetPosis, hn2.sourcePosis, minDiff);
        int conflicts2 = countConflicts(hn2.targetPosis, hn1.sourcePosis, minDiff);
        
        // compare number of crossings for both variants
        int crossings1 = countCrossings(hn1.targetPosis, hn2.start, hn2.end)
                + countCrossings(hn2.sourcePosis, hn1.start, hn1.end);
        int crossings2 = countCrossings(hn2.targetPosis, hn1.start, hn1.end)
                + countCrossings(hn1.sourcePosis, hn2.start, hn2.end);
        
        int depValue1 = CONFLICT_PENALTY * conflicts1 + crossings1;
        int depValue2 = CONFLICT_PENALTY * conflicts2 + crossings2;
        
        if (depValue1 < depValue2) {
            // create dependency from first hypernode to second one
            new Dependency(hn1, hn2, depValue2 - depValue1);
        } else if (depValue1 > depValue2) {
            // create dependency from second hypernode to first one
            new Dependency(hn2, hn1, depValue1 - depValue2);
        } else if (depValue1 > 0 && depValue2 > 0) {
            // create two dependencies with zero weight
            new Dependency(hn1, hn2, 0);
            new Dependency(hn2, hn1, 0);
        }
    }
    
    /**
     * Counts the number of conflicts for the given lists of positions.
     * 
     * @param posis1 sorted list of positions
     * @param posis2 sorted list of positions
     * @param minDiff minimal difference between two positions
     * @return number of positions that overlap
     */
    private static int countConflicts(final List<Double> posis1, final List<Double> posis2,
            final double minDiff) {
        
        int conflicts = 0;
        
        if (!posis1.isEmpty() && !posis2.isEmpty()) {
            Iterator<Double> iter1 = posis1.iterator();
            Iterator<Double> iter2 = posis2.iterator();
            double pos1 = iter1.next();
            double pos2 = iter2.next();
            boolean hasMore = true;
            
            do {
                if (pos1 > pos2 - minDiff && pos1 < pos2 + minDiff) {
                    conflicts++;
                }
                
                if (pos1 <= pos2 && iter1.hasNext()) {
                    pos1 = iter1.next();
                } else if (pos2 <= pos1 && iter2.hasNext()) {
                    pos2 = iter2.next();
                } else {
                    hasMore = false;
                }
            } while (hasMore);
        }
        
        return conflicts;
    }

    /**
     * Counts the number of crossings for a given list of positions.
     * 
     * @param posis sorted list of positions
     * @param start start of the critical area
     * @param end end of the critical area
     * @return number of positions in the critical area
     */
    private static int countCrossings(final List<Double> posis, final double start, final double end) {
        int crossings = 0;
        for (double pos : posis) {
            if (pos > end) {
                break;
            } else if (pos >= start) {
                crossings++;
            }
        }
        return crossings;
    }
    
    /**
     * Breaks all cycles in the given hypernode structure by reversing or removing
     * some dependencies. This implementation assumes that the dependencies of zero
     * weight are exactly the two-cycles of the hypernode structure.
     * 
     * @param nodes list of hypernodes
     */
    private static void breakCycles(final List<HyperNode> nodes) {
        LinkedList<HyperNode> sources = new LinkedList<HyperNode>();
        LinkedList<HyperNode> sinks = new LinkedList<HyperNode>();
        
        // initialize values for the algorithm
        int nextMark = -1;
        for (HyperNode node : nodes) {
            node.mark = nextMark--;
            int inweight = 0, outweight = 0;
            
            for (Dependency dependency : node.outgoing) {
                outweight += dependency.weight;
            }
            
            for (Dependency dependency : node.incoming) {
                inweight += dependency.weight;
            }
            
            node.inweight = inweight;
            node.outweight = outweight;
            
            if (outweight == 0) {
                sinks.add(node);
            } else if (inweight == 0) {
                sources.add(node);
            }
        }
    
        // assign marks to all nodes, ignore dependencies of weight zero
        Set<HyperNode> unprocessed = new TreeSet<HyperNode>(nodes);
        int markBase = nodes.size();
        int nextRight = markBase - 1, nextLeft = markBase + 1;
        
        while (!unprocessed.isEmpty()) {
            while (!sinks.isEmpty()) {
                HyperNode sink = sinks.removeFirst();
                unprocessed.remove(sink);
                sink.mark = nextRight--;
                updateNeighbors(sink, sources, sinks);
            }
            
            while (!sources.isEmpty()) {
                HyperNode source = sources.removeFirst();
                unprocessed.remove(source);
                source.mark = nextLeft++;
                updateNeighbors(source, sources, sinks);
            }
            
            int maxOutflow = Integer.MIN_VALUE;
            HyperNode maxNode = null;
            for (HyperNode node : unprocessed) {
                int outflow = node.outweight - node.inweight;
                if (outflow > maxOutflow) {
                    maxOutflow = outflow;
                    maxNode = node;
                }
            }
            
            if (maxNode != null) {
                unprocessed.remove(maxNode);
                maxNode.mark = nextLeft++;
                updateNeighbors(maxNode, sources, sinks);
            }
        }
    
        // shift ranks that are left of the mark base
        int shiftBase = nodes.size() + 1;
        for (HyperNode node : nodes) {
            if (node.mark < markBase) {
                node.mark += shiftBase;
            }
        }
    
        // process edges that point left: remove those of zero weight, reverse the others
        for (HyperNode source : nodes) {
            ListIterator<Dependency> depIter = source.outgoing.listIterator();
            while (depIter.hasNext()) {
                Dependency dependency = depIter.next();
                HyperNode target = dependency.target;
                
                if (source.mark > target.mark) {
                    depIter.remove();
                    target.incoming.remove(dependency);
                    
                    if (dependency.weight > 0) {
                        dependency.source = target;
                        target.outgoing.add(dependency);
                        dependency.target = source;
                        source.incoming.add(dependency);
                    }
                }
            }
        }
    }
    
    /**
     * Updates in-weight and out-weight values of the neighbors of the given node,
     * simulating its removal from the graph. The sources and sinks lists are
     * also updated.
     * 
     * @param node node for which neighbors are updated
     * @param sources list of sources
     * @param sinks list of sinks
     */
    private static void updateNeighbors(final HyperNode node, final LinkedList<HyperNode> sources,
            final LinkedList<HyperNode> sinks) {
        // process following nodes
        for (Dependency dep : node.outgoing) {
            if (dep.target.mark < 0 && dep.weight > 0) {
                dep.target.inweight -= dep.weight;
                if (dep.target.inweight <= 0 && dep.target.outweight > 0) {
                    sources.add(dep.target);
                }
            }
        }
        
        // process preceding nodes
        for (Dependency dep : node.incoming) {
            if (dep.source.mark < 0 && dep.weight > 0) {
                dep.source.outweight -= dep.weight;
                if (dep.source.outweight <= 0 && dep.source.inweight > 0) {
                    sinks.add(dep.source);
                }
            }
        }
    }
    
    /**
     * Perform a topological numbering of the given hypernodes.
     * 
     * @param nodes list of hypernodes
     */
    private static void topologicalNumbering(final List<HyperNode> nodes) {
        // determine sources, targets, incoming count and outgoing count; targets are only
        // added to the list if they only connect westward ports (that is, if all their
        // horizontal segments point to the right)
        List<HyperNode> sources = new LinkedList<HyperNode>();
        List<HyperNode> rightwardTargets = new LinkedList<HyperNode>();
        for (HyperNode node : nodes) {
            node.inweight = node.incoming.size();
            node.outweight = node.outgoing.size();
            
            if (node.inweight == 0) {
                sources.add(node);
            }
            
            if (node.outweight == 0 && node.sourcePosis.size() == 0) {
                rightwardTargets.add(node);
            }
        }
        
        int maxRank = -1;
        
        // assign ranks using topological numbering
        while (!sources.isEmpty()) {
            HyperNode node = sources.remove(0);
            for (Dependency dep : node.outgoing) {
                HyperNode target = dep.target;
                target.rank = Math.max(target.rank, node.rank + 1);
                maxRank = Math.max(maxRank, target.rank);
                
                target.inweight--;
                if (target.inweight == 0) {
                    sources.add(target);
                }
            }
        }
        
        /* If we stopped here, hyper nodes that don't have any horizontal segments pointing
         * leftward would be ranked just like every other hyper node. This would move back
         * edges too far away from their target node. To remedy that, we move all hyper nodes
         * with horizontal segments only pointing rightwards as far right as possible.
         */
        if (maxRank > -1) {
            // assign all target nodes with horzizontal segments pointing to the right the
            // rightmost rank
            for (HyperNode node : rightwardTargets) {
                node.rank = maxRank;
            }
            
            // let all other segments with horizontal segments pointing rightwards move as
            // far right as possible
            while (!rightwardTargets.isEmpty()) {
                HyperNode node = rightwardTargets.remove(0);
                
                // The node only has connections to western ports
                for (Dependency dep : node.incoming) {
                    HyperNode source = dep.source;
                    if (source.sourcePosis.size() > 0) {
                        continue;
                    }
                    
                    source.rank = Math.min(source.rank, node.rank - 1);
                    
                    source.outweight--;
                    if (source.outweight == 0) {
                        rightwardTargets.add(source);
                    }
                }
            }
        }
    }
    
    /**
     * Inserts a given value into a sorted list.
     * 
     * @param list sorted list
     * @param value value to insert
     */
    private static void insertSorted(final List<Double> list, final double value) {
        ListIterator<Double> listIter = list.listIterator();
        while (listIter.hasNext()) {
            double next = listIter.next().floatValue();
            if (next == value) {
                return;
            } else if (next > value) {
                listIter.previous();
                break;
            }
        }
        listIter.add(Double.valueOf(value));
    }
    
    /**
     * Writes a debug graph for the given list of hypernodes.
     * 
     * @param layeredGraph the layered graph
     * @param layer the currently processed layer
     * @param hypernodes a list of hypernodes
     * @param label a label to append to the output files
     */
    private static void writeDebugGraph(final LayeredGraph layeredGraph, final Layer layer,
            final List<HyperNode> hypernodes, final String label) {
        
        try {
            Writer writer = createWriter(layeredGraph, layer, label);
            writer.write("digraph {\n");
            
            // Write hypernode information
            for (HyperNode hypernode : hypernodes) {
                writer.write("  " + hypernode.hashCode() + "[label=\""
                        + hypernode.toString() + "\"]\n");
            }
            
            // Write dependency information
            for (HyperNode hypernode : hypernodes) {
                for (Dependency dependency : hypernode.outgoing) {
                    writer.write("  " + hypernode.hashCode() + "->" + dependency.target.hashCode()
                            + "[label=\"" + dependency.weight + "\"]\n");
                }
            }
            
            writer.write("}\n");
            writer.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
    
    /**
     * Create a writer for debug output.
     * 
     * @param layeredGraph the layered graph
     * @param layer the currently processed layer
     * @param label a label to append to the output files
     * @return a file writer for debug output
     * @throws IOException if creating the output file fails
     */
    private static Writer createWriter(final LayeredGraph layeredGraph, final Layer layer,
            final String label) throws IOException {
        
        String path = Util.getDebugOutputPath();
        new File(path).mkdirs();
        
        int layerIndex = (layer == null) ? 0 : layer.getIndex() + 1;
        String debugFileName = Util.getDebugOutputFileBaseName(layeredGraph)
                + "l" + layerIndex + "-" + label;
        return new FileWriter(new File(path + File.separator + debugFileName + ".dot"));
    }

}
