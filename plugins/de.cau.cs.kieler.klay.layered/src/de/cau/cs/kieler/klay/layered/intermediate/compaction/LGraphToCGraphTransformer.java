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
package de.cau.cs.kieler.klay.layered.intermediate.compaction;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.kiml.options.Direction;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.klay.layered.compaction.oned.CGraph;
import de.cau.cs.kieler.klay.layered.compaction.oned.CGroup;
import de.cau.cs.kieler.klay.layered.compaction.oned.CNode;
import de.cau.cs.kieler.klay.layered.compaction.oned.CompareFuzzy;
import de.cau.cs.kieler.klay.layered.compaction.oned.ICGraphTransformer;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.Layer;

/**
 * Manages the transformation of {@link LNode}s and {@link LEdge}s from an {@link LGraph} into
 * compactable {@link CNode}s and {@link CGroup}s.
 * 
 * @author dag
 */
public final class LGraphToCGraphTransformer implements ICGraphTransformer<LGraph> {
    /** the output CGraph. */
    private CGraph cGraph;
    /** the layered graph. */
    private LGraph layeredGraph;
    /** flags the input graph if it has edges. */
    private boolean hasEdges;

    /**
     * {@inheritDoc}
     */
    @Override
    public CGraph transform(final LGraph inputGraph) {
        layeredGraph = inputGraph;
        
        // checking if the graph has edges and possibly prohibiting vertical compaction
        hasEdges = layeredGraph.getLayers().stream()
                    .flatMap(l -> l.getNodes().stream())
                    .anyMatch(n -> !Iterables.isEmpty(n.getConnectedEdges()));
        EnumSet<Direction> supportedDirections =
                EnumSet.of(Direction.UNDEFINED, Direction.LEFT, Direction.RIGHT);
        if (!hasEdges) {
            supportedDirections.add(Direction.UP);
            supportedDirections.add(Direction.DOWN);
        }
        
        
        // initializing fields
        cGraph = new CGraph(supportedDirections);
        
        // importing LGraphElements into CNodes
        readNodes();

        return cGraph;
    }

    /**
     * Collects the positions and dimensions of {@link LNode}s and vertical segments in the layered
     * graph and writes them to the {@link CNode}s.
     */
    private void readNodes() {
        List<VerticalSegment> verticalSegments = Lists.newArrayList();
        // resetting to avoid problems if this is called repeatedly
        cGraph.cNodes.clear();

        // 1. collecting positions of graph elements
        for (Layer layer : layeredGraph) {
            for (LNode node : layer) {
                // add all nodes
                CLNode cNode = new CLNode(node, layeredGraph);
                cGraph.cNodes.add(cNode);

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
                            if (!CompareFuzzy.eq(bend1.y, bend2.y)) {
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
            Collections.sort(verticalSegments);

            // merging intersecting segments in the same CLEdge
            VerticalSegment last = verticalSegments.get(0);
            CLEdge c = new CLEdge(last, layeredGraph);

            for (int i = 1; i < verticalSegments.size(); i++) {

                VerticalSegment verticalSegment = verticalSegments.get(i);

                if (verticalSegment.intersects(last)) {
                    c.addSegment(verticalSegment);
                } else {
                    cGraph.cNodes.add(c);
                    c = new CLEdge(verticalSegment, layeredGraph);
                }

                last = verticalSegment;
            }
            cGraph.cNodes.add(c);
        }

        verticalSegments.clear();

        // 3. grouping nodes with their connected north/south segments
        groupCNodes();
    }

    /**
     * Groups nodes with their connected north/south segments to keep them at the correct position
     * relative to each other.
     */
    private void groupCNodes() {
        // resetting groups from previous compaction
        cGraph.cGroups.clear();
        // necessary because of the exception in CGroup.addCNode
        for (CNode cNode : cGraph.cNodes) {
            cNode.cGroup = null;
        }

        // creating groups for independent CNodes
        for (CNode cNode : cGraph.cNodes) {
            if (cNode.parentNode == null) {
                cGraph.cGroups.add(new CGroup(cNode));
            }
        }

        // adding CNodes of north/south segments to the same group as their parent nodes
        for (CNode cNode : cGraph.cNodes) {
            if (cNode.parentNode != null) {
                cNode.parentNode.cGroup.addCNode(cNode);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void applyLayout() {
        // applying the compacted positions
        applyNodePositions();
        
        // calculating new graph size and offset
        KVector topLeft = new KVector(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        KVector bottomRight = new KVector(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY);
        for (CNode cNode : cGraph.cNodes) {
            topLeft.x = Math.min(topLeft.x, cNode.hitbox.x);
            topLeft.y = Math.min(topLeft.y, cNode.hitbox.y);
            bottomRight.x = Math.max(bottomRight.x, cNode.hitbox.x + cNode.hitbox.width);
            bottomRight.y = Math.max(bottomRight.y, cNode.hitbox.y + cNode.hitbox.height);
        }
        layeredGraph.getOffset().reset().add(topLeft.clone().negate());
        layeredGraph.getSize().reset().add(bottomRight.clone().sub(topLeft));
        
        // resetting lists
        cGraph.cGroups.clear();
        cGraph.cNodes.clear();
    }
    
    /**
     * Applies the compacted positions to the {@link LGraphElement}s represented by {@link CNode}s.
     */
    private void applyNodePositions() {
        for (CNode cNode : cGraph.cNodes) {
            cNode.applyElementPosition();
        }
    }

}
