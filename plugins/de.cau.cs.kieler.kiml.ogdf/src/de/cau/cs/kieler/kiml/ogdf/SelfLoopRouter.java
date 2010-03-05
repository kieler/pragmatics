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
package de.cau.cs.kieler.kiml.ogdf;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KLayoutDataFactory;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.layout.options.EdgeLabelPlacement;
import de.cau.cs.kieler.kiml.layout.options.LayoutOptions;
import de.cau.cs.kieler.kiml.layout.options.PortSide;
import de.cau.cs.kieler.kiml.layout.util.KimlLayoutUtil;

/**
 * A router for self-loops.
 *
 * @author msp
 */
public class SelfLoopRouter {

    /** list of stored self-loops. */
    private List<Pair<KEdge, KNode>> selfLoops = new LinkedList<Pair<KEdge, KNode>>();
    
    /**
     * Removes all self-loops from the graph, as they are not supported by the planarization
     * layouter.
     * 
     * @param layoutNode the parent node
     */
    public void preProcess(final KNode layoutNode) {
        for (KNode child : layoutNode.getChildren()) {
            ListIterator<KEdge> edgeIter = child.getOutgoingEdges().listIterator();
            while (edgeIter.hasNext()) {
                KEdge edge = edgeIter.next();
                if (edge.getTarget() == child) {
                    edgeIter.remove();
                    edge.setTarget(null);
                    selfLoops.add(new Pair<KEdge, KNode>(edge, child));
                }
            }
        }
    }
    
    /** fixed distance of self loops to their node. */
    private static final float LOOP_DIST = 12.0f;
    /** starting relative position of self loops. */
    private static final float START_POS = 0.6f;
    
    /**
     * Layouts all self-loops that were removed during pre-processing.
     */
    public void postProcess() {
        // manually layout all self loops
        PortSide side = PortSide.UNDEFINED;
        float relStart = 0, relEnd = 0, distance = 0;
        KNode lastNode = null;
        for (Pair<KEdge, KNode> selfLoop : selfLoops) {
            KEdge edge = selfLoop.getFirst();
            KNode node = selfLoop.getSecond();
            if (node != lastNode) {
                side = PortSide.NORTH;
                relStart = START_POS;
                relEnd = 1 - START_POS;
                distance = LOOP_DIST;
            }
            lastNode = node;
            edge.setSource(node);
            edge.setTarget(node);
            KShapeLayout nodeLayout = KimlLayoutUtil.getShapeLayout(node);
            KEdgeLayout edgeLayout = KimlLayoutUtil.getEdgeLayout(edge);
            edgeLayout.getBendPoints().clear();
            KPoint sourcePoint = createPoint(nodeLayout, side, relStart);
            edgeLayout.setSourcePoint(sourcePoint);
            side = side.right();
            KPoint targetPoint = createPoint(nodeLayout, side, relEnd);
            edgeLayout.setTargetPoint(targetPoint);
            addBendPoints(edgeLayout, sourcePoint, targetPoint, side, distance);
            layoutLabels(edge, side, distance);
            if (side == PortSide.NORTH) {
                relEnd = relEnd / 2;
                relStart = 1 - relEnd;
                distance += LOOP_DIST;
            }
        }
    }
    
    /**
     * Creates a point on the border of the given node layout.
     * 
     * @param nodeLayout the node layout
     * @param side the side on which the point is created
     * @param relPos the relative position on the node side
     * @return a point on the given side
     */
    private KPoint createPoint(final KShapeLayout nodeLayout, final PortSide side, final float relPos) {
        KPoint point = KLayoutDataFactory.eINSTANCE.createKPoint();
        float offsetx = nodeLayout.getXpos();
        float offsety = nodeLayout.getYpos();
        switch (side) {
        case NORTH:
            point.setX(offsetx + nodeLayout.getWidth() * relPos);
            point.setY(offsety);
            break;
        case EAST:
            point.setX(offsetx + nodeLayout.getWidth());
            point.setY(offsety + nodeLayout.getHeight() * relPos);
            break;
        case SOUTH:
            point.setX(offsetx + nodeLayout.getWidth() * (1 - relPos));
            point.setY(offsety + nodeLayout.getHeight());
            break;
        case WEST:
            point.setX(offsetx);
            point.setY(offsety + nodeLayout.getHeight() * (1 - relPos));
            break;
        }
        return point;
    }

    /**
     * Add the needed bend points to the given edge layout.
     * 
     * @param edgeLayout an edge layout
     * @param sourcePoint the source point
     * @param targetPoint the target point
     * @param side the node side of the target point
     * @param distance distance to the node
     */
    private void addBendPoints(final KEdgeLayout edgeLayout, final KPoint sourcePoint,
            final KPoint targetPoint, final PortSide side, final float distance) {
        KPoint point1 = KLayoutDataFactory.eINSTANCE.createKPoint();
        KPoint point2 = KLayoutDataFactory.eINSTANCE.createKPoint();
        KPoint point3 = KLayoutDataFactory.eINSTANCE.createKPoint();
        switch (side) {
        case EAST:
            point1.setX(sourcePoint.getX());
            point1.setY(sourcePoint.getY() - distance);
            point3.setX(targetPoint.getX() + distance);
            point3.setY(targetPoint.getY());
            break;
        case SOUTH:
            point1.setX(sourcePoint.getX() + distance);
            point1.setY(sourcePoint.getY());
            point3.setX(targetPoint.getX());
            point3.setY(targetPoint.getY() + distance);
            break;
        case WEST:
            point1.setX(sourcePoint.getX());
            point1.setY(sourcePoint.getY() + distance);
            point3.setX(targetPoint.getX() - distance);
            point3.setY(targetPoint.getY());
            break;
        case NORTH:
            point1.setX(sourcePoint.getX() - distance);
            point1.setY(sourcePoint.getY());
            point3.setX(targetPoint.getX());
            point3.setY(targetPoint.getY() - distance);
            break;
        }
        if (side == PortSide.EAST || side == PortSide.WEST) {
            point2.setX(point3.getX());
            point2.setY(point1.getY());
        } else {
            point2.setX(point1.getX());
            point2.setY(point3.getY());
        }
        edgeLayout.getBendPoints().add(point1);
        edgeLayout.getBendPoints().add(point2);
        edgeLayout.getBendPoints().add(point3);
    }
    
    /**
     * Layouts the labels of the given self loop.
     * 
     * @param edge the edge whose labels are to be layouted
     * @param side the side of the target point of the self loop
     * @param distance to the node
     */
    private void layoutLabels(final KEdge edge, final PortSide side, final float distance) {
        KEdgeLayout edgeLayout = KimlLayoutUtil.getEdgeLayout(edge);
        KPoint sourcePoint = edgeLayout.getSourcePoint();
        KPoint targetPoint = edgeLayout.getTargetPoint();
        List<KLabel> centerLabels = new LinkedList<KLabel>();
        List<KLabel> tailLabels = new LinkedList<KLabel>();
        List<KLabel> headLabels = new LinkedList<KLabel>();
        float centerHeight = 0, centerWidth = 0, tailHeight = 0, tailWidth = 0,
                headHeight = 0, headWidth = 0;
        for (KLabel label : edge.getLabels()) {
            KShapeLayout labelLayout = KimlLayoutUtil.getShapeLayout(label);
            switch (LayoutOptions.getEnum(labelLayout, EdgeLabelPlacement.class)) {
            case CENTER:
                centerLabels.add(label);
                centerHeight += labelLayout.getHeight();
                centerWidth = Math.max(centerWidth, labelLayout.getWidth());
                break;
            case HEAD:
                headLabels.add(label);
                headHeight += labelLayout.getHeight();
                headWidth = Math.max(headWidth, labelLayout.getWidth());
                break;
            case TAIL:
                tailLabels.add(label);
                tailHeight += labelLayout.getHeight();
                tailWidth = Math.max(tailWidth, labelLayout.getWidth());
                break;
            }
        }
        switch (side) {
        case EAST:
            putLabels(tailLabels, sourcePoint.getX() + 2,
                    sourcePoint.getY() - tailHeight - 2);
            if (tailHeight > distance) {
                putLabels(centerLabels, sourcePoint.getX() + tailWidth,
                        sourcePoint.getY() - distance - centerHeight - 2);
            } else {
                putLabels(centerLabels, sourcePoint.getX() + 2,
                        sourcePoint.getY() - distance - centerHeight - 2);
            }
            putLabels(headLabels, targetPoint.getX() + 2,
                    targetPoint.getY() - headHeight - 2);
            break;
        case SOUTH:
            putLabels(tailLabels, sourcePoint.getX() + 2, sourcePoint.getY() + 2);
            if (tailWidth > distance) {
                putLabels(centerLabels, sourcePoint.getX() + distance + 2,
                        sourcePoint.getY() + tailHeight + 2);
            } else {
                putLabels(centerLabels, sourcePoint.getX() + distance + 2,
                        sourcePoint.getY() + 2);
            }
            putLabels(headLabels, targetPoint.getX() + 2, targetPoint.getY() + 2);
            break;
        case WEST:
            putLabels(tailLabels, sourcePoint.getX() - tailWidth - 2,
                    sourcePoint.getY() + 2);
            if (tailHeight > distance) {
                putLabels(centerLabels, sourcePoint.getX() - tailWidth - centerWidth - 2,
                        sourcePoint.getY() + distance + 2);
            } else {
                putLabels(centerLabels, sourcePoint.getX() - centerWidth - 2,
                        sourcePoint.getY() + distance + 2);
            }
            putLabels(headLabels, targetPoint.getX() - headWidth - 2,
                    targetPoint.getY() + 2);
            break;
        case NORTH:
            putLabels(tailLabels, sourcePoint.getX() - tailWidth - 2,
                    sourcePoint.getY() - tailHeight - 2);
            if (tailWidth > distance) {
                putLabels(centerLabels, sourcePoint.getX() - distance - centerWidth - 2,
                        sourcePoint.getY() - tailHeight - centerHeight - 2);
            } else {
                putLabels(centerLabels, sourcePoint.getX() - distance - centerWidth - 2,
                        sourcePoint.getY() - centerHeight - 2);
            }
            putLabels(headLabels, targetPoint.getX() - headWidth - 2,
                    targetPoint.getY() - headHeight - 2);
            break;
        }
    }
    
    /**
     * Places the given labels one on top of each other at the given position.
     * 
     * @param labels list of labels
     * @param xpos x coordinate
     * @param ypos y coordinate
     */
    private void putLabels(final List<KLabel> labels, final float xpos, final float ypos) {
        float y = ypos;
        for (KLabel label : labels) {
            KShapeLayout labelLayout = KimlLayoutUtil.getShapeLayout(label);
            labelLayout.setXpos(xpos);
            labelLayout.setYpos(y);
            y += labelLayout.getHeight();
        }
    }
    
}
