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
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.kiml.layout.options.PortType;
import de.cau.cs.kieler.klay.layered.graph.Coord;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;
import de.cau.cs.kieler.klay.layered.modules.INodePlacer;

/**
 * Node placement implementation that aligns long edges using linear segments.
 * Inspired by Section 4 of
 * <ul>
 *   <li>Georg Sander. A fast heuristic for hierarchical manhattan layout. In
 *     <i>Proceedings of the Symposium on Graph Drawing (GD '95)</i>, pp. 447-458,
 *     Springer, 1996.
 * </ul>
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
            return "ls_" + id;
        }
        
        /**
         * {@inheritDoc}
         */
        public int compareTo(final LinearSegment other) {
            return other.id - this.id;
        }
        
    }
    
    /** minimal spacing between objects. */
    private float objSpacing;
    /** array of sorted linear segments. */
    private LinearSegment[] linearSegments;

    /**
     * @param theobjSpacing the object spacing to set
     */
    public void setObjSpacing(final float theobjSpacing) {
        this.objSpacing = theobjSpacing;
    }

    /**
     * {@inheritDoc}
     */
    public void placeNodes(final LayeredGraph layeredGraph) {
        getMonitor().begin("Linear segments node placement", 1);

//        this.lastElements = new LayerElement[layeredGraph.getLayers().size()
//                + layeredGraph.getLayers().get(0).getRank()];

        // sort the linear segments of the layered graph
        sortLinearSegments(layeredGraph);
        // create an unbalanced placement from the sorted segments
        createUnbalancedPlacement();

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
     * Sorts the linear segments of the given layered graph by finding a
     * topological ordering in the corresponding segment ordering graph.
     * 
     * @param layeredGraph layered graph to process
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
     * Put a node into the given linear segment and check for following parts of
     * a long edge.
     * 
     * @param node the node to put into the linear segment
     * @param segment a linear segment
     */
    private void fillSegment(final LNode node, final LinearSegment segment) {
        node.id = segment.id;
        if (node.getType() == LNode.Type.LONG_EDGE) {
            for (LPort sourcePort : node.getPorts(PortType.OUTPUT)) {
                for (LPort targetPort : sourcePort.getConnectedPorts()) {
                    LNode targetNode = targetPort.getOwner();
                    if (targetNode.getType() == LNode.Type.LONG_EDGE) {
                        fillSegment(targetNode, segment);
                    }
                }
            }
        }

    }

    /**
     * Creates an unbalanced placement for the sorted linear segments.
     */
    private void createUnbalancedPlacement() {
        for (LinearSegment segment : linearSegments) {
            // determine the uppermost placement for the linear segment
            float uppermostPlace = 0.0f;
            for (LNode node : segment.getNodes()) {
                uppermostPlace = Math.max(uppermostPlace, node.getOwner().getSize().y);
            }
            // apply the uppermost placement to all elements
            float newPos = uppermostPlace == 0 ? 0.0f : uppermostPlace + objSpacing;
            for (LNode node : segment.getNodes()) {
                Layer layer = node.getOwner();
                node.getPos().y = newPos;
                float height = node.getSize().y;
                layer.getSize().y = newPos + height;
                layer.getSize().x = Math.max(layer.getSize().x, node.getSize().x);
            }
        }
    }

}
