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
package de.cau.cs.kieler.klay.planar.alg.orthogonal;

import java.util.List;

import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.klay.planar.graph.IEdge;
import de.cau.cs.kieler.klay.planar.graph.INode;

/**
 * An encoding class for an orthogonal representation. Formally, an orthogonal representation is a
 * function from the set of faces of a graph, to a list of pairs containing an edge on the face and
 * an array of angles encoding bends on the edge. The last angle in the array defines the array the
 * edge forms with the next edge in the list.
 * 
 * @author ocl
 */
public interface IOrthogonalRepresentation {

    /**
     * Defines angles in an orthogonal representation. Since in an orthogonal layout, all angles are
     * multiples of 90 degrees, only values for 0, 90, 180 and 270 are allowed.
     */
    public enum OrthogonalAngle {
        /** A 90 degree angle, or a right turn. */
        RIGHT,

        /** A 180 degree angle, or a straight line. */
        STRAIGHT,

        /** A 270 degree angle, or a left turn. */
        LEFT,

        /** A 360 degree angle, or full circle. */
        FULL,
    }

    /**
     * Get the bend points along an edge in the graph.
     * 
     * @param edge
     *            an edge in the graph
     * @return an array containing left or right bends.
     */
    OrthogonalAngle[] getBends(IEdge edge);

    /**
     * Get the angles in a node in the graph.
     * 
     * @param node
     *            a node in the graph
     * @return a list containing the ordered list of edges on the node, together with the angle it
     *         forms with the next edge
     */
    List<Pair<IEdge, OrthogonalAngle>> getAngle(INode node);

}
