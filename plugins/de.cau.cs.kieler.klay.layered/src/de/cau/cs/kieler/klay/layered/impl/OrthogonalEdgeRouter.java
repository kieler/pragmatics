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
package de.cau.cs.kieler.klay.layered.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.kiml.options.PortType;
import de.cau.cs.kieler.klay.layered.Properties;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;
import de.cau.cs.kieler.klay.layered.modules.IEdgeRouter;

/**
 *
 * @author msp
 */
public class OrthogonalEdgeRouter extends AbstractAlgorithm implements IEdgeRouter {
    
    /** weight penalty for conflicts of horizontal line segments. */
    private static final int CONFLICT_PENALTY = 16;

    /**
     * A hypernode used for routing a hyperedge.
     */
    private static class HyperNode {
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
        void addPortPosis(final LPort port, final Map<LPort, HyperNode> hyperNodeMap,
                final boolean isTargetLayer) {
            hyperNodeMap.put(port, this);
            ports.add(port);
            double pos = port.getNode().getPos().y + port.getPos().y;
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
            if (isTargetLayer) {
                insertSorted(targetPosis, pos);
            } else {
                insertSorted(sourcePosis, pos);
            }
            // add connected ports
            for (LPort otherPort : port.getConnectedPorts()) {
                if (!hyperNodeMap.containsKey(otherPort)) {
                    addPortPosis(otherPort, hyperNodeMap, !isTargetLayer);
                }
            }
        }
        
        /**
         * {@inheritDoc}
         */
        @Override
        public String toString() {
            return "hn[" + mark + "]";
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
    
    /** spacing between edge. */
    private double edgeSpacing;
    /** threshold at which conflicts of horizontal line segments are detected. */
    private double conflictThreshold;
    
    /**
     * {@inheritDoc}
     */
    public void routeEdges(final LayeredGraph layeredGraph) {
        getMonitor().begin("Orthogonal edge routing", 1);
        edgeSpacing = layeredGraph.getProperty(Properties.OBJ_SPACING);
        conflictThreshold = CONFL_THRESH_FACTOR * edgeSpacing;
        
        float xpos = 0.0f;
        for (Layer layer : layeredGraph.getLayers()) {
            for (LNode node : layer.getNodes()) {
                node.getPos().x = xpos;
            }
            xpos += layer.getSize().x + edgeSpacing;
            int slotsCount = routeEdges(layer, xpos);
            xpos += slotsCount * edgeSpacing;
        }
        
        layeredGraph.getSize().x = xpos;
        getMonitor().done();
    }
    
    /** denominator for edge spacing to determine when an edge shall be drawn without bends. */
    private static final int STRAIGHT_TOLERANCE = 256;
    
    /**
     * Route the edges from one layer to the next one.
     * 
     * @param layer the source layer
     * @param xpos the horizontal position where edges shall be routed
     * @return the number of routing slots for this layer
     */
    private int routeEdges(final Layer layer, final double xpos) {
        // create hypernodes for the ports of the source layer and the target layer
        Map<LPort, HyperNode> hyperNodeMap = new HashMap<LPort, HyperNode>();
        List<HyperNode> hyperNodes = new LinkedList<HyperNode>();
        for (LNode node : layer.getNodes()) {
            for (LPort port : node.getPorts(PortType.OUTPUT)) {
                HyperNode hyperNode = hyperNodeMap.get(port);
                if (hyperNode == null) {
                    hyperNode = new HyperNode();
                    hyperNodes.add(hyperNode);
                    hyperNode.addPortPosis(port, hyperNodeMap, false);
                }
            }
        }
        
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
        
        // break cycles
        breakCycles(hyperNodes);
        
        // assign ranks to the hypernodes
        topolNumb(hyperNodes);
        
        // set bend points with appropriate coordinates
        int rankCount = -1;
        for (HyperNode node : hyperNodes) {
            rankCount = Math.max(rankCount, node.rank);
            double x = xpos + node.rank * edgeSpacing;
            for (LPort port : node.ports) {
                double sourcey = port.getNode().getPos().y + port.getPos().y;
                if (port.getType() == PortType.OUTPUT) {
                    for (LEdge edge : port.getEdges()) {
                        double targety = edge.getTarget().getNode().getPos().y
                                + edge.getTarget().getPos().y;
                        if (Math.abs(sourcey - targety) > edgeSpacing / STRAIGHT_TOLERANCE) {
                            KVector point1 = new KVector(x, sourcey);
                            edge.getBendPoints().add(point1);
                            KVector point2 = new KVector(x, targety);
                            edge.getBendPoints().add(point2);
                        }
                    }
                }
            }
        }
        return rankCount + 1;
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
            // create two dependencies with equal weight
            new Dependency(hn1, hn2, 1);
            new Dependency(hn2, hn1, 1);
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
     * Breaks all cycles in the given hypernode structure by removing some dependencies.
     * 
     * @param nodes list of hypernodes
     */
    private static void breakCycles(final List<HyperNode> nodes) {
        LinkedList<HyperNode> sources = new LinkedList<HyperNode>();
        LinkedList<HyperNode> sinks = new LinkedList<HyperNode>();
        // initialize values for the algorithm
        for (HyperNode node : nodes) {
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
    
        // assign marks to all nodes
        int unprocessedNodes = nodes.size(), nextRight = -1, nextLeft = 1;
        while (unprocessedNodes > 0) {
            while (!sinks.isEmpty()) {
                HyperNode sink = sinks.removeFirst();
                sink.mark = nextRight--;
                updateNeighbors(sink, sources, sinks);
                unprocessedNodes--;
            }
            while (!sources.isEmpty()) {
                HyperNode source = sources.removeFirst();
                source.mark = nextLeft++;
                updateNeighbors(source, sources, sinks);
                unprocessedNodes--;
            }
            if (unprocessedNodes != 0) {
                int maxOutflow = Integer.MIN_VALUE;
                HyperNode maxNode = null;
                for (HyperNode node : nodes) {
                    if (node.mark == 0) {
                        int outflow = node.outweight - node.inweight;
                        if (outflow > maxOutflow) {
                            maxOutflow = outflow;
                            maxNode = node;
                        }
                    }
                }
                maxNode.mark = nextLeft++;
                updateNeighbors(maxNode, sources, sinks);
                unprocessedNodes--;
            }
        }
    
        // shift negative ranks
        int shiftBase = nodes.size() + 1;
        for (HyperNode node : nodes) {
            if (node.mark < 0) {
                node.mark += shiftBase;
            }
        }
    
        // remove edges that point left
        for (HyperNode node : nodes) {
            ListIterator<Dependency> depIter = node.outgoing.listIterator();
            while (depIter.hasNext()) {
                Dependency dependency = depIter.next();
                if (node.mark > dependency.target.mark) {
                    depIter.remove();
                    dependency.target.incoming.remove(dependency);
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
            if (dep.target.mark == 0) {
                dep.target.inweight -= dep.weight + 1;
                if (dep.target.inweight == 0 && dep.target.outweight != 0) {
                    sources.add(dep.target);
                }
            }
        }
        // process preceding nodes
        for (Dependency dep : node.incoming) {
            if (dep.source.mark == 0) {
                dep.source.outweight -= dep.weight + 1;
                if (dep.source.outweight == 0 && dep.source.inweight != 0) {
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
    private static void topolNumb(final List<HyperNode> nodes) {
        // determine sources and incoming count
        List<HyperNode> sources = new LinkedList<HyperNode>();
        for (HyperNode node : nodes) {
            node.inweight = node.incoming.size();
            if (node.inweight == 0) {
                sources.add(node);
            }
        }
        // assign ranks using topological numbering
        while (!sources.isEmpty()) {
            HyperNode node = sources.remove(0);
            for (Dependency dep : node.outgoing) {
                HyperNode target = dep.target;
                target.rank = Math.max(target.rank, node.rank + 1);
                target.inweight--;
                if (target.inweight == 0) {
                    sources.add(dep.target);
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

}
