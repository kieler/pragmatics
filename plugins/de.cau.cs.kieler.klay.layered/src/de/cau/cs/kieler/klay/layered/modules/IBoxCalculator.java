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
package de.cau.cs.kieler.klay.layered.modules;

import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;

import de.cau.cs.kieler.core.math.BezierSpline;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;

/**
 * @author car
 * @author uru
 */
public interface IBoxCalculator {

    /**
     * initialize the box calculator for a given graph.
     * 
     * @param graph
     *            the graph to operate on
     */
    void initialize(final LayeredGraph graph);

    /**
     * compute a box array for a given edge. compute_bboxes computes the space actually taken up by
     * the curve. It computes the array BB0 , . . . , BBm, where BBi is the narrowest sub-box of Bi
     * containing the curve.
     * 
     * @param edge
     *            the edge to find a array for
     * @return the box array between q and s
     */
    LinkedList<Rectangle2D.Double> getBoxes(final LEdge edge);

    /**
     * calculate intersection between boxes. compute_L_array computes the array L0 , . . . , Lm + 1
     * where Li is the line segment that is the intersection of box Bi - 1 with box Bi.
     * 
     * lArray.size = boxArray.size - 1 ALWAYS
     * 
     * @param boxes
     *            the boxes to intersect
     * @return the intersecting lines
     */
    LinkedList<Line2D.Double> getLines(final LinkedList<Rectangle2D.Double> boxes);

    /**
     * add an edge that takes space.
     * 
     * @param spline
     *            the spline to add
     * @return true if success, false otherwise
     */
    boolean addEdge(final BezierSpline spline);

    /**
     * add an egde that takes linear space with maybe bending points.
     * 
     * @param edge
     *            the edge to add
     * @return true if success, false otherwise
     */
    boolean addEdge(final LEdge edge);

    /**
     * Add a node to the box calculator.
     * 
     * @param node
     *            the LNode to add
     */
    void addNode(final LNode node);

}
