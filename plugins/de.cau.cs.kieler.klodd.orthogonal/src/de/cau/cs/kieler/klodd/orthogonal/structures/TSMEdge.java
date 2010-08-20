/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2009 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klodd.orthogonal.structures;

import java.util.List;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutDataFactory;
import de.cau.cs.kieler.kiml.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.util.KimlUtil;
import de.cau.cs.kieler.klodd.orthogonal.structures.slimgraph.KSlimEdge;
import de.cau.cs.kieler.klodd.orthogonal.structures.slimgraph.KSlimGraph;
import de.cau.cs.kieler.klodd.orthogonal.structures.slimgraph.KSlimNode;

/**
 * An edge in the graph structure used for the topology-shape-metrics approach.
 * 
 * @author msp
 */
public class TSMEdge extends KSlimEdge {

    /** the previous edge of a split edge. */
    public TSMEdge previousEdge;
    /** the next edge of a split edge. */
    public TSMEdge nextEdge;

    /**
     * Creates an edge connecting two existing nodes, with a layout graph edge
     * as reference.
     * 
     * @param graph the graph to which the new edge shall be added
     * @param source source node
     * @param target target node
     * @param layoutEdge the layout graph edge to be contained
     */
    public TSMEdge(final KSlimGraph graph, final KSlimNode source, final KSlimNode target,
            final KEdge layoutEdge) {
        super(graph, source, target);
        this.setObject(layoutEdge);
    }

    /**
     * Creates an edge connecting two existing nodes.
     * 
     * @param graph the graph to which the new edge shall be added
     * @param source source node
     * @param target target node
     */
    public TSMEdge(final KSlimGraph graph, final KSlimNode source,
            final KSlimNode target) {
        super(graph, source, target);
    }

    /**
     * Applies the layout to the original layout edge.
     * 
     * @param offsetX x offset to be added
     * @param offsetY y offset to be added
     */
    public void applyLayout(final float offsetX, final float offsetY) {
        KEdge layoutEdge = (KEdge) getObject();
        KEdgeLayout edgeLayout = KimlUtil.getEdgeLayout(layoutEdge);
        KShapeLayout sourceLayout = KimlUtil.getShapeLayout(layoutEdge.getSource());
        KShapeLayout targetLayout = KimlUtil.getShapeLayout(layoutEdge.getTarget());
        KShapeLayout sourcePortLayout = KimlUtil.getShapeLayout(layoutEdge.getSourcePort());
        KShapeLayout targetPortLayout = KimlUtil.getShapeLayout(layoutEdge.getTargetPort());

        // find the first edge in a series of edges
        TSMEdge currentEdge = this;
        while (currentEdge.previousEdge != null) {
            currentEdge = currentEdge.previousEdge;
        }
        TSMEdge firstEdge = currentEdge, lastEdge;

        // add all bend points of the edge
        List<KPoint> bendPoints = edgeLayout.getBendPoints();
        bendPoints.clear();
        do {
            for (Bend bend : currentEdge.getBends()) {
                KPoint newPoint = KLayoutDataFactory.eINSTANCE.createKPoint();
                newPoint.setX(bend.getXpos() + offsetX);
                newPoint.setY(bend.getYpos() + offsetY);
                bendPoints.add(newPoint);
            }
            lastEdge = currentEdge;
            // mark the edge as visited
            currentEdge.setRank(1);
            currentEdge = currentEdge.nextEdge;
        } while (currentEdge != null);

        // set start point
        KPoint newPoint = KLayoutDataFactory.eINSTANCE.createKPoint();
        float xpos = sourceLayout.getXpos() + sourcePortLayout.getXpos();
        float ypos = sourceLayout.getYpos() + sourcePortLayout.getYpos();
        switch (firstEdge.getSourceSide()) {
        case NORTH:
            xpos += sourcePortLayout.getWidth() / 2;
            break;
        case EAST:
            xpos += sourcePortLayout.getWidth();
            ypos += sourcePortLayout.getHeight() / 2;
            break;
        case SOUTH:
            xpos += sourcePortLayout.getWidth() / 2;
            ypos += sourcePortLayout.getHeight();
            break;
        case WEST:
            ypos += sourcePortLayout.getHeight() / 2;
            break;
        }
        newPoint.setX(xpos);
        newPoint.setY(ypos);
        edgeLayout.setSourcePoint(newPoint);

        // set end point
        newPoint = KLayoutDataFactory.eINSTANCE.createKPoint();
        xpos = targetLayout.getXpos() + targetPortLayout.getXpos();
        ypos = targetLayout.getYpos() + targetPortLayout.getYpos();
        switch (lastEdge.getTargetSide()) {
        case NORTH:
            xpos += targetPortLayout.getWidth() / 2;
            break;
        case EAST:
            xpos += targetPortLayout.getWidth();
            ypos += targetPortLayout.getHeight() / 2;
            break;
        case SOUTH:
            xpos += targetPortLayout.getWidth() / 2;
            ypos += targetPortLayout.getHeight();
            break;
        case WEST:
            ypos += targetPortLayout.getHeight() / 2;
            break;
        }
        newPoint.setX(xpos);
        newPoint.setY(ypos);
        edgeLayout.setTargetPoint(newPoint);
    }

}
