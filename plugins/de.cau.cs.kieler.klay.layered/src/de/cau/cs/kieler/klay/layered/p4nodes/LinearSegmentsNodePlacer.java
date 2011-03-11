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
package de.cau.cs.kieler.klay.layered.p4nodes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterators;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.kiml.options.PortType;
import de.cau.cs.kieler.klay.layered.ILayoutPhase;
import de.cau.cs.kieler.klay.layered.IntermediateProcessingStrategy;
import de.cau.cs.kieler.klay.layered.Properties;
import de.cau.cs.kieler.klay.layered.Properties.NodeType;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;
import de.cau.cs.kieler.klay.layered.intermediate.IntermediateLayoutProcessor;

/**
 * Node placement implementation that aligns long edges using linear segments. Inspired by Section 4
 * of
 * <ul>
 * <li>Georg Sander. A fast heuristic for hierarchical manhattan layout. In <i>Proceedings of the
 * Symposium on Graph Drawing (GD '95)</i>, pp. 447-458, Springer, 1996.</li>
 * </ul>
 * 
 * <dl>
 *   <dt>Precondition:</dt><dd>the graph has a proper layering with
 *     optimized nodes ordering; ports are properly arranged</dd>
 *   <dt>Postcondition:</dt><dd>each node is assigned a vertical coordinate
 *     such that no two nodes overlap; the size of each layer is set according
 *     to the area occupied by contained nodes; the height of the graph is set
 *     to the maximal layer height</dd>
 * </dl>
 * 
 * @author msp
 * @author grh
 */
public class LinearSegmentsNodePlacer extends AbstractAlgorithm implements ILayoutPhase {
    /**
     * A linear segment contains a single regular node or all dummy nodes of a long edge.
     */
    public static class LinearSegment implements Comparable<LinearSegment> {

        /** nodes of the linear segment. */
        private List<LNode> nodes = new LinkedList<LNode>();

        /**
         * @return the nodes
         */
        public List<LNode> getNodes() {
            return nodes;
        }

        // CHECKSTYLEOFF VisibilityModifier
        /**
         * Identifier value, may be arbitrarily used by algorithms.
         */
        public int id;
        
        /**
         * Index in the previous layer. Used for cycle avoidance.
         */
        public int indexInLastLayer = -1;
        
        /**
         * The last layer where a node belonging to this segment was discovered. Used for
         * cycle avoidance.
         */
        public int lastLayer = -1;
        // CHECKSTYLEON VisibilityModifier
        
        /**
         * Splits this linear segment before the given node. The returned segment contains
         * all nodes from the given node onward, with their ID set to the new segment's ID.
         * Those nodes are removed from this segment.
         * 
         * @param node the node to split the segment at.
         * @param newId the new segment's id.
         * @return new linear segment with ID {@code -1} and all nodes from {@code node}
         *         onward.
         */
        public LinearSegment split(final LNode node, final int newId) {
            int nodeIndex = nodes.indexOf(node);
            
            // Prepare the new segment
            LinearSegment newSegment = new LinearSegment();
            newSegment.id = newId;
            
            // Move nodes to the new segment
            ListIterator<LNode> iterator = nodes.listIterator(nodeIndex);
            while (iterator.hasNext()) {
                LNode movedNode = iterator.next();
                movedNode.id = newId;
                newSegment.nodes.add(movedNode);
                iterator.remove();
            }
            
            return newSegment;
        }

        /**
         * {@inheritDoc}
         */
        public String toString() {
            return "ls" + nodes.toString();
        }

        /**
         * {@inheritDoc}
         */
        public int compareTo(final LinearSegment other) {
            return this.id - other.id;
        }

    }

    /**
     * A Region contains Nodes or LinearSegments, that are touching and therefor need to be moved as
     * a unit.
     */
    public class Region {
        /** The nodes that forms the region. */
        private List<LNode> nodes = new LinkedList<LNode>();
        /** The accumulated force of the contained nodes. */
        private double force = 0;

        /** Constructor. */
        public Region() {
            regions.add(this);
        }

        /** @return list of nodes contained in the region */
        public List<LNode> getNodes() {
            return nodes;
        }

        /**
         * @param other the other region to be unified
         */
        public void union(final Region other) {
            // Keep the Region with lower index
            if (regions.indexOf(this) > regions.indexOf(other)) {
                // Add all nodes of this region to the other region
                other.getNodes().addAll(this.getNodes());
                for (LNode node : this.getNodes()) {
                    node.setProperty(Properties.REGION, other);
                }
                regions.remove(this);
            } else {
                // Add all nodes of other region to this region
                this.getNodes().addAll(other.getNodes());
                for (LNode node : other.getNodes()) {
                    node.setProperty(Properties.REGION, this);
                }
                regions.remove(other);
            }
        }
    }
    
    /** intermediate processing strategy. */
    private static final IntermediateProcessingStrategy INTERMEDIATE_PROCESSING_STRATEGY =
        new IntermediateProcessingStrategy(
                IntermediateProcessingStrategy.BEFORE_PHASE_4,
                EnumSet.of(IntermediateLayoutProcessor.PORT_ARRANGER));

    /** array of sorted linear segments. */
    private LinearSegment[] linearSegments;
    /** list of regions. */
    private List<Region> regions = new LinkedList<Region>();
    
    /**
     * {@inheritDoc}
     */
    public IntermediateProcessingStrategy getIntermediateProcessingStrategy() {
        return INTERMEDIATE_PROCESSING_STRATEGY;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void reset() {
        super.reset();
        regions.clear();
    }

    /**
     * {@inheritDoc}
     */
    public void process(final LayeredGraph layeredGraph) {
        getMonitor().begin("Linear segments node placement", 1);

        // sort the linear segments of the layered graph
        sortLinearSegments(layeredGraph);
        
        // create an unbalanced placement from the sorted segments
        createUnbalancedPlacement(layeredGraph);
        
        // balance the placement
        IKielerProgressMonitor monitor = getMonitor().subTask(1);
        monitor.begin("Balance Placement", 1);
        balancePlacement(layeredGraph);
        monitor.done();

        // set the proper height for the whole graph
        KVector graphSize = layeredGraph.getSize();
        for (Layer layer : layeredGraph.getLayers()) {
            graphSize.y = Math.max(graphSize.y, layer.getSize().y);
        }

        // release the created resources
        this.linearSegments = null;
        getMonitor().done();
    }

    /**
     * @return the linear segments
     */
    public LinearSegment[] getLinearSegments() {
        return linearSegments;
    }

    /**
     * Sorts the linear segments of the given layered graph by finding a topological ordering in the
     * corresponding segment ordering graph.
     * 
     * @param layeredGraph
     *            layered graph to process
     * @return a sorted array of linear segments
     */
    private LinearSegment[] sortLinearSegments(final LayeredGraph layeredGraph) {
        // reset all node IDs
        List<LinearSegment> segmentList = new LinkedList<LinearSegment>();
        for (Layer layer : layeredGraph.getLayers()) {
            for (LNode node : layer.getNodes()) {
                node.id = -1;
            }
        }

        // create linear segments for the layered graph, ignoring odd port side dummies
        int nextLinearSegmentID = 0;
        for (Layer layer : layeredGraph.getLayers()) {
            for (LNode node : layer.getNodes()) {
                if (node.id < 0
                        && node.getProperty(Properties.NODE_TYPE) != Properties.NodeType.ODD_PORT_SIDE) {
                    
                    LinearSegment segment = new LinearSegment();
                    segment.id = nextLinearSegmentID++;
                    fillSegment(node, segment);
                    segmentList.add(segment);
                }
            }
        }

        // create and initialize segment ordering graph
        List<List<LinearSegment>> outgoingList = new ArrayList<List<LinearSegment>>(segmentList.size());
        List<Integer> incomingCountList = new ArrayList<Integer>(segmentList.size());
        for (int i = 0; i < segmentList.size(); i++) {
            outgoingList.add(new LinkedList<LinearSegment>());
            incomingCountList.add(0);
        }
        
        createDependencyGraphEdges(layeredGraph, segmentList, outgoingList, incomingCountList);
        
        // Turn lists into arrays
        LinearSegment[] segments = segmentList.toArray(new LinearSegment[segmentList.size()]);
        
        @SuppressWarnings("unchecked")
        List<LinearSegment>[] outgoing =
            outgoingList.toArray(new List[outgoingList.size()]);
        
        int[] incomingCount = new int[incomingCountList.size()];
        for (int i = 0; i < incomingCount.length; i++) {
            incomingCount[i] = incomingCountList.get(i);
        }

        // find a topological ordering of the segment ordering graph
        int nextRank = 0;
        List<LinearSegment> noIncoming = new LinkedList<LinearSegment>();
        for (int i = 0; i < segments.length; i++) {
            if (incomingCount[i] == 0) {
                noIncoming.add(segments[i]);
            }
        }
        
        int[] newRanks = new int[segments.length];
        while (!noIncoming.isEmpty()) {
            LinearSegment segment = noIncoming.remove(0);
            newRanks[segment.id] = nextRank++;
            
            while (!outgoing[segment.id].isEmpty()) {
                LinearSegment target = outgoing[segment.id].remove(0);
                incomingCount[target.id]--;
                
                if (incomingCount[target.id] == 0) {
                    noIncoming.add(target);
                }
            }
        }

        // apply the new ordering to the array of linear segments
        linearSegments = new LinearSegment[segments.length];
        for (int i = 0; i < segments.length; i++) {
            assert outgoing[i].isEmpty();
            linearSegments[newRanks[i]] = segments[i];
        }
        
        return linearSegments;
    }

    /**
     * Fills the dependency graph with dependencies. If a dependency would introduce a cycle,
     * the offending linear segment is split into two linear segments.
     * 
     * @param layeredGraph the layered graph.
     * @param segmentList the list of segments. Updated to include the newly created linear
     *                    segments.
     * @param outgoingList the lists of outgoing dependencies for each segment. This
     *                     essentially encodes the edges of the dependency graph.
     * @param incomingCountList the number of incoming dependencies for each segment.
     */
    private void createDependencyGraphEdges(final LayeredGraph layeredGraph,
            final List<LinearSegment> segmentList, final List<List<LinearSegment>> outgoingList,
            final List<Integer> incomingCountList) {
        
        // create edges in the segment ordering graph
        int nextLinearSegmentID = segmentList.size();
        int layerIndex = 0;
        for (Layer layer : layeredGraph.getLayers()) {
            List<LNode> nodes = layer.getNodes();
            if (nodes.isEmpty()) {
                // Ignore empty layers
                continue;
            }
            
            Iterator<LNode> nodeIter = nodes.iterator();
            int indexInLayer = 0;
            
            // We carry the next-to-last node with us
            LNode node0 = null;
            
            // Get the layer's first node
            LNode node1 = nodeIter.next();
            LinearSegment segment1 = null;
            
            while (nodeIter.hasNext()) {
                // Get the current node's segment
                if (node1.id == -1) {
                    // Can happen for odd port side dummies
                    node1 = nodeIter.next();
                    continue;
                }
                segment1 = segmentList.get(node1.id);
                
                // Check if we can have a cycle. That's the case if we find a node after the
                // current node whose segment appeared in the last layer as well, and whose
                // index than came before node1's segment
                Iterator<LNode> cycleNodesIter = layer.getNodes().listIterator(indexInLayer + 1);
                LinearSegment cycleSegment = null;
                
                while (cycleNodesIter.hasNext()) {
                    LNode cycleNode = cycleNodesIter.next();
                    if (cycleNode.id != -1) {
                        cycleSegment = segmentList.get(cycleNode.id);
                        
                        if (cycleSegment.lastLayer == segment1.lastLayer
                                && cycleSegment.indexInLastLayer < segment1.indexInLastLayer) {
                            
                            break;
                        } else {
                            cycleSegment = null;
                        }
                    }
                }
                
                // If we have found a cycle segment, we need to split the current linear
                // segment
                if (cycleSegment != null) {
                    // Update the current segment before it's split
                    if (node0 != null) {
                        incomingCountList.set(node1.id, incomingCountList.get(node1.id) - 1);
                        outgoingList.get(node0.id).remove(segment1);
                    }
                    
                    segment1 = segment1.split(node1, nextLinearSegmentID++);
                    segmentList.add(segment1);
                    outgoingList.add(new LinkedList<LinearSegment>());
                    
                    if (node0 != null) {
                        outgoingList.get(node0.id).add(segment1);
                        incomingCountList.add(1);
                    } else {
                        incomingCountList.add(0);
                    }
                }
                
                // Now add a dependency to the next node we find with id != -1
                LNode node2 = null;
                while (nodeIter.hasNext()) {
                    node2 = nodeIter.next();
                    if (node2.id == -1) {
                        node2 = null;
                    } else {
                        LinearSegment segment2 = segmentList.get(node2.id);
                        
                        outgoingList.get(node1.id).add(segment2);
                        incomingCountList.set(node2.id, incomingCountList.get(node2.id) + 1);
                        
                        break;
                    }
                }
                
                // Update segment's layer information
                segment1.lastLayer = layerIndex;
                segment1.indexInLastLayer = indexInLayer++;
                
                // Cycle nodes
                node0 = node1;
                node1 = node2;
            }
            
            // Assign indices to the layer's last node's segment, if any
            if (segment1 != null) {
                segment1.lastLayer = layerIndex;
                segment1.indexInLastLayer = indexInLayer;
            }
            
            layerIndex++;
        }
    }

    /**
     * Put a node into the given linear segment and check for following parts of a long edge.
     * 
     * @param node
     *            the node to put into the linear segment
     * @param segment
     *            a linear segment
     */
    private void fillSegment(final LNode node, final LinearSegment segment) {
        if (node.id >= 0) {
            return;
        }
        node.id = segment.id;
        segment.nodes.add(node);

        // Check if outgoing edge has priority > 0
        LEdge highestPrioEdge = null;
        int highestPrio = 0;
        for (LPort sourcePort : node.getPorts(PortType.OUTPUT)) {
            for (LEdge targetEdge : sourcePort.getEdges()) {
                int prio = targetEdge.getProperty(Properties.PRIORITY);
                if (prio > highestPrio) {
                    highestPrioEdge = targetEdge;
                    highestPrio = prio;
                }
            }
        }
        
        // At least one of the outgoing edges has priority > 0
        NodeType nodeType = node.getProperty(Properties.NODE_TYPE);
        if (highestPrioEdge != null) {
            LNode nextNode = highestPrioEdge.getTarget().getNode();
            
            // calculate/set offset
            int offset = (int) Math.round(highestPrioEdge.getSource().getPos().y
                    - (highestPrioEdge.getTarget().getPos().y)
                    + node.getProperty(Properties.LINSEG_OFFSET));
            nextNode.setProperty(Properties.LINSEG_OFFSET, offset);
            
            // Fill segment
            fillSegment(nextNode, segment);
        } else if (nodeType == NodeType.LONG_EDGE || nodeType == NodeType.NORTH_SOUTH_PORT) {
            // Long Edge and North South Port dummies can be part of a linear segment
            for (LPort sourcePort : node.getPorts(PortType.OUTPUT)) {
                for (LPort targetPort : sourcePort.getConnectedPorts()) {
                    LNode targetNode = targetPort.getNode();
                    NodeType targetNodeType = targetNode.getProperty(Properties.NODE_TYPE);
                    
                    if (targetNodeType == NodeType.LONG_EDGE
                            || targetNodeType == NodeType.NORTH_SOUTH_PORT) {
                        
                        fillSegment(targetNode, segment);
                    }
                }
            }
        }
    }

    /**
     * Creates an unbalanced placement for the sorted linear segments.
     * 
     * @param layeredGraph the layered graph to create an unbalanced placement for.
     */
    private void createUnbalancedPlacement(final LayeredGraph layeredGraph) {
        float spacing = layeredGraph.getProperty(Properties.OBJ_SPACING);
        int[] nodeCount = new int[layeredGraph.getLayers().size()];
        boolean straightEdges = layeredGraph.getProperty(Properties.STRAIGHT_EDGES);
        for (LinearSegment segment : linearSegments) {
            // determine minimal offset of 'segment'
            float minOffset = 0;
            double maxSize = 0.0f;
            for (LNode node : segment.getNodes()) {
                float offset = (float) node.getProperty(Properties.LINSEG_OFFSET);
                if (offset < minOffset) {
                    minOffset = offset;
                }
                if (node.getSize().y > maxSize) {
                    maxSize = node.getSize().y;
                }
            }

            // determine the uppermost placement for the linear segment
            double uppermostPlace = 0.0f;
            int nodeCountSum = 0;
            for (LNode node : segment.getNodes()) {
                uppermostPlace = Math.max(uppermostPlace, node.getLayer().getSize().y);
                int layerIndex = node.getLayer().getIndex();
                nodeCountSum += nodeCount[layerIndex];
                nodeCount[layerIndex]++;
            }
            // apply the uppermost placement to all elements
            double newPos = uppermostPlace;
            if (nodeCountSum > 0) {
                newPos += spacing;
            }
            for (LNode node : segment.getNodes()) {
                double offset = 0.0f;
                if (straightEdges) {
                    // add node offset - minimal offset
                    offset = node.getProperty(Properties.LINSEG_OFFSET) - minOffset;
                } else {
                    offset = maxSize / 2 - node.getSize().y / 2;
                }
                Layer layer = node.getLayer();
                node.getPos().y = newPos + offset;
                layer.getSize().y = newPos + offset + node.getSize().y;
                layer.getSize().x = Math.max(layer.getSize().x, node.getSize().x);
            }
        }
    }

    /**
     * Balance the initial placement by force-based movement of regions.
     * 
     * @param layeredGraph a layered graph
     */
    private void balancePlacement(final LayeredGraph layeredGraph) {
        float spacing = layeredGraph.getProperty(Properties.OBJ_SPACING);
        // Initialize Regions = Linear segments
        for (int s = 0; s < linearSegments.length; s++) {
            LinearSegment segment = linearSegments[s];
            Region region = new Region();
            for (LNode node : segment.getNodes()) {
                region.nodes.add(node);
                node.setProperty(Properties.REGION, region);
            }
        }

        // Iterate the balancing
        boolean ready = false;
        int finalIterations = 0;
        int iterations = 0;

        while (!ready || finalIterations > 0) {
            finalIterations--;
            iterations++;
            
            // Iterate regions alternating forward or backward
            Collections.reverse(regions);
            
            for (Region region : regions) {
                // Calculate force for every node
                calculateForce(region);

                // Test for all nodes, if they can be moved that far.
                checkMovability(region, spacing);
                
                // Move nodes
                for (LNode node : region.getNodes()) {
                    node.getPos().y += region.force;
                }
            }

            // First 2 iterations are not unioning regions
            if (iterations > 2) {
                // Test for touching neighbors
                ready = noNewTouchingRegions(layeredGraph);
            }
            
            // If ready, 2 more iterations are performed
            if (ready && finalIterations < 0) {
                finalIterations = 2;
            }
        }
    }

    /**
     * Calculates the force acting on the given region.
     * 
     * @param region the region whose force to be calculated
     */
    private void calculateForce(final Region region) {
        // Reset force for region
        region.force = 0.0f;
        for (LNode node : region.getNodes()) {
            float sum = 0.0f;
            int numEdges = 0;
            // Calculate force for every port/edge
            for (LPort port : node.getPorts()) {
                if (port.getType() == PortType.OUTPUT) {
                    for (LEdge edge : port.getEdges()) {
                        LPort otherPort = edge.getTarget();
                        LNode otherNode = otherPort.getNode();
                        sum += (otherNode.getPos().y + otherPort.getPos().y)
                                - (node.getPos().y + port.getPos().y);
                        numEdges++;
                    }
                } else if (port.getType() == PortType.INPUT) {
                    for (LEdge edge : port.getEdges()) {
                        LPort otherPort = edge.getSource();
                        LNode otherNode = otherPort.getNode();
                        sum += (otherNode.getPos().y + otherPort.getPos().y)
                                - (node.getPos().y + port.getPos().y);
                        numEdges++;
                    }
                }
            }
            // Avoid division by zero
            if (numEdges > 0) {
                sum /= numEdges;
            } else {
                sum = 0.0f;
            }
            region.force += sum / region.getNodes().size();
        }
    }

    /**
     * TODO: Document.
     * 
     * @param region the region to be checked
     * @param spacing the object spacing
     */
    private void checkMovability(final Region region, final float spacing) {
        for (LNode node : region.getNodes()) {
            if (region.force < 0.0f) {
                // Force is directed upward
                if (node.getIndex() > 0) {
                    // Node is not topmost node
                    LNode neighbor = node.getLayer().getNodes().get(node.getIndex() - 1);
                    if (node.getProperty(Properties.REGION) 
                            != neighbor.getProperty(Properties.REGION)
                            && neighbor.getPos().y + neighbor.getSize().y + spacing 
                            > node.getPos().y + region.force) {
                        
                        // Set force on region to the max possible force
                        region.force = node.getPos().y
                                - (neighbor.getPos().y + neighbor.getSize().y + spacing);
                    }
                } else {
                    // Node is topmost node
                    if (node.getPos().y + region.force < 0.0f) {
                        // Node woult like to go out of Frame
                        region.force = -node.getPos().y;
                    }
                }
            } else if (region.force > 0.0f && node.getIndex() < node.getLayer().getNodes().size() - 1) {
                // Force is directed downward and the node is not lowermost
                LNode neighbor = node.getLayer().getNodes().get(node.getIndex() + 1);
                if (node.getProperty(Properties.REGION) 
                        != neighbor.getProperty(Properties.REGION)
                        && node.getPos().y + node.getSize().y + spacing + region.force
                            > neighbor.getPos().y) {
                    
                    // Set force on region to the max possible force
                    region.force = neighbor.getPos().y
                            - (node.getPos().y + node.getSize().y + spacing);
                }
            }
        }
    }

    /**
     * Checks if any regions are now touching each other that weren't touching each other
     * during the last call.
     * 
     * @param layeredGraph the layered graph
     * @return {@code false} if regions are newly touching
     */
    private boolean noNewTouchingRegions(final LayeredGraph layeredGraph) {
        boolean ready = true;
        float spacing = layeredGraph.getProperty(Properties.OBJ_SPACING);
        
        for (Layer layer : layeredGraph.getLayers()) {
            List<LNode> nodes = layer.getNodes();
            
            // Iterator that iterates over nodes belonging to regions
            Iterator<LNode> nodeIter = Iterators.filter(nodes.iterator(), new Predicate<LNode>() {
                public boolean apply(final LNode node) {
                    return node.getProperty(Properties.REGION) != null;
                }
            });
            
            if (!nodeIter.hasNext()) {
                // Ignore "empty" layers
                continue;
            }
            
            // Get the first node
            LNode node1 = nodeIter.next();
            
            // While there are still nodes following the current node
            while (nodeIter.hasNext()) {
                // Test if nodes have different regions
                LNode node2 = nodeIter.next();
                
                if (node1.getProperty(Properties.REGION)
                        != node2.getProperty(Properties.REGION)) {
                    
                    // Test if the nodes are touching
                    if (node1.getPos().y + node1.getSize().y + spacing > node2.getPos().y - 1.0f) {
                        double overlay = node1.getPos().y + node1.getSize().y
                            + spacing - node2.getPos().y;
                        
                        // Adjust position for every member of the neighbors region
                        for (LNode toAdjust : node2.getProperty(Properties.REGION).getNodes()) {
                            toAdjust.getPos().y = toAdjust.getPos().y + overlay;
                        }
                        
                        // Union the regions of the neighbors
                        node1.getProperty(Properties.REGION).union(
                                node2.getProperty(Properties.REGION));
                        ready = false;
                    }
                }
                
                node1 = node2;
            }
        }
        
        return ready;
    }

}
