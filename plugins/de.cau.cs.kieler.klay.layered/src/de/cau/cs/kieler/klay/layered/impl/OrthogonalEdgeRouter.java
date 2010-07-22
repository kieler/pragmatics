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
import java.util.Map;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.kiml.options.PortType;
import de.cau.cs.kieler.klay.layered.Properties;
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
        /** the rank determines the horizontal distance to the preceding layer. */
        private int rank = 0;
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
        /** number of incoming dependencies in the dependency graph. */
        private int incomingCount = 0;
        /** visit marker for cycle breaking. */
        private int visit = -1;
        
        /**
         * Adds the positions of the given port and all connected ports.
         * 
         * @param port a port
         * @param hyperNodeMap map of ports to existing hypernodes
         */
        void addPortPosis(final LPort port, final Map<LPort, HyperNode> hyperNodeMap,
                final boolean isTargetLayer) {
            hyperNodeMap.put(port, this);
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
                targetPosis.add(pos);
            } else {
                sourcePosis.add(pos);
            }
            // add connected ports
            for (LPort otherPort : port.getConnectedPorts()) {
                if (!hyperNodeMap.containsKey(otherPort)) {
                    addPortPosis(otherPort, hyperNodeMap, !isTargetLayer);
                }
            }
        }
    }

    /**
     * A dependency between two hypernodes.
     */
    private static final class Dependency {
        /** the target hypernode of this dependency. */
        private HyperNode target;
        /** the weight of this dependency. */
        private int weight;
        
        /**
         * Creates a dependency to the given target.
         * 
         * @param thetarget the dependency target
         * @param theweight weight of the dependency
         */
        private Dependency(final HyperNode thetarget, final int theweight) {
            this.target = thetarget;
            this.weight = theweight;
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
        edgeSpacing = layeredGraph.getProperty(Properties.OBJ_SPACING);
        conflictThreshold = CONFL_THRESH_FACTOR * edgeSpacing;
        
        float xpos = 0.0f;
        for (Layer layer : layeredGraph.getLayers()) {
            int slotsCount = routeEdges(layer);
            xpos += layer.getSize().x + slotsCount * edgeSpacing;
        }
        
        layeredGraph.getSize().x = xpos;
    }
    
    /**
     * Route the edges from one layer to the next one.
     * 
     * @param layer the source layer
     * @return the number of routing slots for this layer
     */
    private int routeEdges(final Layer layer) {
        // create hypernodes for the ports of the source layer and the target layer
        Map<LPort, HyperNode> hyperNodeMap = new HashMap<LPort, HyperNode>();
        for (LNode node : layer.getNodes()) {
            for (LPort port : node.getPorts(PortType.OUTPUT)) {
                HyperNode hyperNode = hyperNodeMap.get(port);
                if (hyperNode == null) {
                    hyperNode = new HyperNode();
                    hyperNode.addPortPosis(port, hyperNodeMap, false);
                }
            }
        }
        
        // create dependencies for the hypernode ordering graph
        HyperNode[] hyperNodes = hyperNodeMap.values().toArray(
                new HyperNode[hyperNodeMap.values().size()]);
        for (HyperNode hyperNode1 : hyperNodes) {
            for (HyperNode hyperNode2 : hyperNodes) {
                int depValue = calcDependency(hyperNode1, hyperNode2, conflictThreshold);
                if (depValue < 0) {
                    // create dependency from first hypernode to second one
                } else if (depValue > 0) {
                    // create dependency from second hypernode to first one
                }
            }
        }
        
        int rankCount = 0;
        return rankCount;
    }

    /**
     * Calculate the weight of a possible dependency between two hypernodes.
     * If the weight is 0, then no dependency is created. 
     * 
     * @param hn1 first hypernode
     * @param hn2 second hypernode
     * @param minDiff the minimal difference between horizontal line segments to avoid a conflict
     * @return the weight of the 
     */
    private static int calcDependency(final HyperNode hn1, final HyperNode hn2,
            final double minDiff) {
        // compare number of conflicts for both variants
        int conflicts1 = countConflicts(hn1.targetPosis, hn2.sourcePosis, minDiff);
        int conflicts2 = countConflicts(hn2.targetPosis, hn1.sourcePosis, minDiff);
        // compare number of crossings for both variants
        int crossings1 = countCrossings(hn1.targetPosis, hn2.start, hn2.end)
                + countCrossings(hn2.sourcePosis, hn1.start, hn1.end);
        int crossings2 = countCrossings(hn2.targetPosis, hn1.start, hn1.end)
                + countCrossings(hn1.sourcePosis, hn2.start, hn2.end);
        return CONFLICT_PENALTY * (conflicts1 - conflicts2) + crossings1 - crossings2;
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

}
