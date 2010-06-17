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

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.kiml.layout.options.PortType;
import de.cau.cs.kieler.klay.layered.LayeredLayoutProvider;
import de.cau.cs.kieler.klay.layered.Properties;
import de.cau.cs.kieler.klay.layered.Properties.NodeType;
import de.cau.cs.kieler.klay.layered.graph.Coord;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;
import de.cau.cs.kieler.klay.layered.modules.INodePlacer;

/**
 * Node placement implementation that aligns long edges using linear segments. Inspired by Section 4
 * of
 * <ul>
 * <li>Georg Sander. A fast heuristic for hierarchical manhattan layout. In <i>Proceedings of the
 * Symposium on Graph Drawing (GD '95)</i>, pp. 447-458, Springer, 1996.
 * </ul>
 * 
 * TODO implement balancing of the placement
 * 
 * @author msp
 */
public class LinearSegmentsNodePlacer extends AbstractAlgorithm implements INodePlacer {

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
        /** Identifier value, may be arbitrarily used by algorithms. */
        public int id;

        // CHECKSTYLEON VisibilityModifier

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
    public static class Region {
        /** The nodes that forms the region. */
        private List<LNode> nodes = new LinkedList<LNode>();
        /** The accumulated force of the contained nodes. */
        private float force = 0;
        
        /** Contructor. */
        public Region() {
            regions.add(this);
        }
        
        /** @return List of Nodes contained in the region */
        public List<LNode> getNodes() {
            return nodes;
        }
        
        /** @param other The other Region to be 'unioned' */
        public void union(final Region other) {
            // Keep the Region with lower index
            if (regions.indexOf(this) > regions.indexOf(other)) {
                // Add all nodes of this region to the other region
                other.getNodes().addAll(this.getNodes());
                for (LNode node : this.getNodes()) {
                    node.setRegion(other);
                }
                regions.remove(this);
            } else {
                // Add all nodes of other region to this region
                this.getNodes().addAll(other.getNodes());
                for (LNode node : other.getNodes()) {
                    node.setRegion(this);
                }
                regions.remove(other);
            }
        }
    }

    /** minimal spacing between objects. */
    private float spacing = LayeredLayoutProvider.DEF_SPACING;
    /** array of sorted linear segments. */
    private LinearSegment[] linearSegments;
    /** List of regions. */
    private static List<Region> regions = new LinkedList<Region>();

    /**
     * {@inheritDoc}
     */
    public void setSpacing(final float theSpacing) {
        this.spacing = theSpacing;
    }

    /**
     * {@inheritDoc}
     */
    public void placeNodes(final LayeredGraph layeredGraph) {
        getMonitor().begin("Linear segments node placement", 1);

        // this.lastElements = new LayerElement[layeredGraph.getLayers().size()
        // + layeredGraph.getLayers().get(0).getRank()];

        // arrange port positions
        arrangePorts(layeredGraph);
        // sort the linear segments of the layered graph
        sortLinearSegments(layeredGraph);
        // create an unbalanced placement from the sorted segments
        createUnbalancedPlacement(layeredGraph);
        // balance the placement
        balancePlacement(layeredGraph);

        // set the proper height for the whole graph
        Coord graphSize = layeredGraph.getSize();
        for (Layer layer : layeredGraph.getLayers()) {
            graphSize.y = Math.max(graphSize.y, layer.getSize().y);
        }

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
        // create linear segments for the layered graph
        List<LinearSegment> segmentList = new LinkedList<LinearSegment>();
        for (Layer layer : layeredGraph.getLayers()) {
            for (LNode node : layer.getNodes()) {
                node.id = -1;
            }
        }
        int index = 0;
        for (Layer layer : layeredGraph.getLayers()) {
            for (LNode node : layer.getNodes()) {
                if (node.id < 0) {
                    LinearSegment segment = new LinearSegment();
                    segment.id = index++;
                    fillSegment(node, segment);
                    segmentList.add(segment);
                }
            }
        }
        linearSegments = segmentList.toArray(new LinearSegment[segmentList.size()]);

        // create and initialize segment ordering graph
        @SuppressWarnings("unchecked")
        List<LinearSegment>[] outgoing = new List[linearSegments.length];
        int[] incomingCount = new int[linearSegments.length];
        for (int i = 0; i < linearSegments.length; i++) {
            outgoing[i] = new LinkedList<LinearSegment>();
        }

        // create edges in the segment ordering graph
        for (Layer layer : layeredGraph.getLayers()) {
            Iterator<LNode> nodeIter = layer.getNodes().iterator();
            LNode node1 = nodeIter.next();
            while (nodeIter.hasNext()) {
                LNode node2 = nodeIter.next();
                LinearSegment segment2 = linearSegments[node2.id];
                outgoing[node1.id].add(segment2);
                incomingCount[node2.id]++;
                node1 = node2;
            }
        }

        // find a topological ordering of the segment ordering graph
        int nextRank = 0;
        List<LinearSegment> noIncoming = new LinkedList<LinearSegment>();
        for (int i = 0; i < linearSegments.length; i++) {
            if (incomingCount[i] == 0) {
                noIncoming.add(linearSegments[i]);
            }
        }
        int[] newRanks = new int[linearSegments.length];
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
        for (int i = 0; i < linearSegments.length; i++) {
            assert outgoing[i].isEmpty();
            linearSegments[i].id = newRanks[i];
        }
        Arrays.sort(linearSegments);
        return linearSegments;
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
        node.id = segment.id;
        segment.nodes.add(node);
        if (node.getProperty(Properties.NODE_TYPE) == NodeType.LONG_EDGE) {
            for (LPort sourcePort : node.getPorts(PortType.OUTPUT)) {
                for (LPort targetPort : sourcePort.getConnectedPorts()) {
                    LNode targetNode = targetPort.getNode();
                    if (targetNode.getProperty(Properties.NODE_TYPE) == NodeType.LONG_EDGE) {
                        fillSegment(targetNode, segment);
                    }
                }
            }
        } else {
            // Check if outgoing edge has priority > 0
            LEdge highestPrioEdge = null;
            int highestPrio = 0;
            for (LPort sourcePort : node.getPorts(PortType.OUTPUT)) {
                for (LEdge targetEdge : sourcePort.getEdges()) {
                    if (targetEdge.getProperty(Properties.PRIORITY) > highestPrio) {
                        highestPrioEdge = targetEdge;
                    }
                }
            }
            // At least one of the outgoing edges has priority > 0
            if (highestPrioEdge != null) {
                fillSegment(highestPrioEdge.getTarget().getNode(), segment);
            }
        }

    }

    /**
     * Creates an unbalanced placement for the sorted linear segments.
     */
    private void createUnbalancedPlacement(final LayeredGraph layeredGraph) {
        int[] nodeCount = new int[layeredGraph.getLayers().size()];
        for (LinearSegment segment : linearSegments) {
            // determine the uppermost placement for the linear segment
            float uppermostPlace = 0.0f;
            int nodeCountSum = 0;
            for (LNode node : segment.getNodes()) {
                uppermostPlace = Math.max(uppermostPlace, node.getLayer().getSize().y);
                int layerIndex = node.getLayer().getIndex();
                nodeCountSum += nodeCount[layerIndex];
                nodeCount[layerIndex]++;
            }
            // apply the uppermost placement to all elements
            float newPos = uppermostPlace;
            if (nodeCountSum > 0) {
                newPos += spacing;
            }
            for (LNode node : segment.getNodes()) {
                Layer layer = node.getLayer();
                node.getPos().y = newPos;
                float height = node.getSize().y;
                layer.getSize().y = newPos + height;
                layer.getSize().x = Math.max(layer.getSize().x, node.getSize().x);
            }
        }
    }

    private void balancePlacement(final LayeredGraph layeredGraph) {
        // Initialize Regions = Linear segments
        for (int s = 0; s < linearSegments.length; s++) {
            LinearSegment segment = linearSegments[s];
            Region region = new Region();
            for (LNode node : segment.getNodes()) {
                region.nodes.add(node);
                node.setRegion(region);
            }
        }
        
        // Iterate the balancing
        boolean ready = false;
        boolean forward = true;
        int finalIterations = 0;

        while (!ready || finalIterations > 0) {
            ready = true;
            finalIterations--;
            // Iterate regions alternating forward or backward
            Collections.reverse(regions);
            for (Region region : regions) {
                // Reset force for region
                region.force = 0.0f;
                
                // Calculate force for every node
                for (LNode node : region.getNodes()) {
                    float sum = 0.0f;
                    int numEdges = 0;
                    // Calculate force for every port/edge
                    for (LPort port : node.getPorts()) {
                        for (LEdge edge : port.getEdges()) {
                            LPort gegenPort;
                            if (port.getType() == PortType.OUTPUT) {
                                gegenPort = edge.getTarget();
                                LNode gegenNode = gegenPort.getNode();
                                sum += (gegenNode.getPos().y + gegenPort.getPos().y)
                                        - (node.getPos().y + port.getPos().y);
                                numEdges++;
                            } else if (port.getType() == PortType.INPUT) {
                                gegenPort = edge.getSource();
                                LNode gegenNode = gegenPort.getNode();
                                sum += (gegenNode.getPos().y + gegenPort.getPos().y)
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

                // Test for all nodes, if they can be moved that far.
                // Test only if the neighbor is of another region.
                for (LNode node : region.getNodes()) {
                    if (region.force < 0.0f) {
                        // Force is directed upward
                        if (node.getIndex() > 0) {
                            // Node is not topmost node
                            LNode neighbor = node.getLayer().getNodes().get(node.getIndex() - 1);
                            if (node.getRegion() != neighbor.getRegion()
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
                    } else if (region.force > 0.0f
                            && node.getIndex() < node.getLayer().getNodes().size() - 1) {
                        // Force is directed downward and the node is not lowermost
                        LNode neighbor = node.getLayer().getNodes().get(node.getIndex() + 1);
                        if (node.getRegion() != neighbor.getRegion()
                                && node.getPos().y + node.getSize().y + spacing + region.force 
                                > neighbor.getPos().y) {
                            // Set force on region to the max possible force
                            region.force = neighbor.getPos().y
                                - (node.getPos().y + node.getSize().y + spacing);
                        }
                    }
                }
                // Move nodes
                for (LNode node : region.getNodes()) {
                    node.getPos().y += region.force;
                }
            }

            // Test for touching neighbors
            for (Layer layer : layeredGraph.getLayers()) {
                List<LNode> nodes = layer.getNodes();
                for (LNode node : nodes) {
                    if (node.getIndex() < nodes.size() - 1) {
                        // Test if nodes have different regions
                        LNode neighbor = nodes.get(node.getIndex() + 1);
                        if (node.getRegion() != neighbor.getRegion()) {
                            // Test if the nodes are touching
                            if (node.getPos().y + node.getSize().y + spacing
                                    > neighbor.getPos().y - 1.0f) {
                                // Union the regions of the neighbors
                                node.getRegion().union(neighbor.getRegion());
                                ready = false;
                            } 
                        }
                    }
                }
            }
            if (ready && finalIterations < 0) {
                finalIterations = 2;
            }
            forward = !forward;
        }
    }

    /**
     * Arrange the ports of all nodes in the layered graph.
     * 
     * @param layeredGraph
     *            a layered graph
     */
    private void arrangePorts(final LayeredGraph layeredGraph) {
        for (Layer layer : layeredGraph.getLayers()) {
            for (LNode node : layer.getNodes()) {
                int inputCount = 1, outputCount = 1;
                for (LPort port : node.getPorts()) {
                    if (port.getType() == PortType.INPUT) {
                        inputCount++;
                    } else {
                        outputCount++;
                    }
                }
                float inputDelta = node.getSize().y / inputCount;
                float outputDelta = node.getSize().y / outputCount;
                float inputY = inputDelta, outputY = outputDelta;
                for (LPort port : node.getPorts()) {
                    if (port.getType() == PortType.INPUT) {
                        port.getPos().x = 0;
                        port.getPos().y = inputY;
                        inputY += inputDelta;
                    } else {
                        port.getPos().x = node.getSize().x;
                        port.getPos().y = outputY;
                        outputY += outputDelta;
                    }
                }
            }
        }
    }

}
