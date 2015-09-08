/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2015 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klay.layered.intermediate;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import com.google.common.collect.Lists;

import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.Layer;

/**
 * @author dag
 *
 */
public final class LGraphToCGraphTransformer implements ICGraphTransformer<LGraph> {
    /** the output graph. */
    private CGraph cGraph;
    /** the layered graph. */
    private LGraph layeredGraph;
    /** the list of {@link CNode}s modeling the constraints in this graph. */
    private List<CNode> cNodes;
    /** groups of elements that are supposed to stay in the configuration they are. */
    private List<CGroup> cGroups;

    /**
     * {@inheritDoc}
     */
    @Override
    public CGraph transform(final LGraph inputGraph) {
        layeredGraph = inputGraph;
        
        cGraph = new CGraph();
        cNodes = Lists.newArrayList();
        cGroups = Lists.newArrayList();
        
        readNodes();

        cGraph.layeredGraph = layeredGraph;
        cGraph.cNodes = cNodes;
        cGraph.cGroups = cGroups;
        return cGraph;
    }

    /**
     * Collects the positions and dimensions of nodes and vertical segments in the layered graph and
     * writes them to the {@link CNode}s.
     */
    private void readNodes() {
        List<VerticalSegment> verticalSegments = Lists.newArrayList();
        // resetting to avoid problems if this is called repeatedly
        cNodes.clear();

        // 1. collecting positions of graph elements
        for (Layer layer : layeredGraph) {
            for (LNode node : layer) {
                // add all nodes
                CLNode cNode = new CLNode(node, layeredGraph);
                cNodes.add(cNode);

                // add vertical edge segments
                for (LEdge edge : node.getOutgoingEdges()) {

                    Iterator<KVector> bends = edge.getBendPoints().iterator();

                    // infer vertical segments from positions of bendpoints
                    if (bends.hasNext()) {
                        KVector bend1 = bends.next();

                        // get segment of source n/s port
                        if (edge.getSource().getSide() == PortSide.NORTH
                                || edge.getSource().getSide() == PortSide.SOUTH) {

                            verticalSegments.add(new VerticalSegment(bend1, edge.getSource()
                                    .getAbsoluteAnchor(), cNode, edge));
                        }

                        // get regular segments
                        while (bends.hasNext()) {
                            KVector bend2 = bends.next();
                            if (!Comp.eq(bend1.y, bend2.y)) {
                                verticalSegments.add(new VerticalSegment(bend1, bend2, null, edge));
                            }

                            bend1 = bend2;
                        }
                    }
                }

                // same for incoming edges to get NSSegments on target side
                for (LEdge edge : node.getIncomingEdges()) {
                    if (!edge.getBendPoints().isEmpty()) {

                        // get segment of target n/s port
                        if (edge.getTarget().getSide() == PortSide.NORTH
                                || edge.getTarget().getSide() == PortSide.SOUTH) {

                            KVector bend1 = edge.getBendPoints().getLast();
                            verticalSegments.add(new VerticalSegment(bend1, edge.getTarget()
                                    .getAbsoluteAnchor(), cNode, edge));
                        }
                    }
                }
            }
        }

        // 2. combining intersecting segments in CLEdges to process them as one
        if (!verticalSegments.isEmpty()) {
            // sorting the segments by position in ascending order
            verticalSegments.sort(new Comparator<VerticalSegment>() {
                /**
                 * {@inheritDoc}
                 */
                public int compare(final VerticalSegment o1, final VerticalSegment o2) {
                    int d = Double.compare(o1.x1, o2.x1);
                    if (d == 0) {
                        return Double.compare(o1.y1, o2.y1);
                    }
                    return d;
                }
            });

            // merging intersecting segments in the same CLEdge
            VerticalSegment last = verticalSegments.get(0);
            CLEdge c = new CLEdge(last, layeredGraph);

            for (int i = 1; i < verticalSegments.size(); i++) {

                VerticalSegment verticalSegment = verticalSegments.get(i);

                if (verticalSegment.intersects(last)) {
                    c.addSegment(verticalSegment);
                } else {
                    cNodes.add(c);
                    c = new CLEdge(verticalSegment, layeredGraph);
                }

                last = verticalSegment;
            }
            cNodes.add(c);
        }

        verticalSegments.clear();

        // grouping nodes with their connected north/south segments
        groupCNodes();
    }

    /**
     * Groups nodes with their connected north/south segments to keep them at the correct position
     * to each other.
     */
    private void groupCNodes() {
        // resetting groups from previous compaction
        cGroups.clear();
        // necessary because of the exception in CGroup.addCNode
        for (CNode cNode : cNodes) {
            cNode.cGroup = null;
        }

        // creating groups for independent CNodes
        for (CNode cNode : cNodes) {
            if (cNode.parentNode == null) {
                cGroups.add(new CGroup(cNode));
            }
        }

        // adding CNodes of north/south segments to the same group as their parent nodes
        for (CNode cNode : cNodes) {
            if (cNode.parentNode != null) {
                cNode.parentNode.cGroup.addCNode(cNode);
            }
        }
    }
}
