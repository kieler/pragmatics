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

import de.cau.cs.kieler.core.alg.IAlgorithm;
import de.cau.cs.kieler.klay.planar.graph.IEdge;
import de.cau.cs.kieler.klay.planar.graph.IGraph;
import de.cau.cs.kieler.klay.planar.graph.INode;

/**
 * Interface for orthogonalization algorithms. Uses the Strategy design pattern to provide a common
 * interface for various implementations of algorithms for orthogonalization.
 * 
 * @author ocl
 */
public interface IOrthogonalizer extends IAlgorithm {

    /**
     * An interface whose implementing classes encode an orthogonal representation of a graph. An
     * orthogonal representation defines the the angle between to edges at any node, as well as the
     * sequence of bend angles along an edge.
     * 
     * @author ocl
     */
    public interface IOrthogonalRepresentation {

        /**
         * Defines angles in an orthogonal representation. Since in an orthogonal layout, all angles
         * are multiples of 90 degrees, only values for 0, 90, 180 and 270 are allowed.
         */
        enum OrthogonalAngle {
            /** A 0 or 360 degree angle. */
            NONE,

            /** A 90 degree angle, or a right turn. */
            RIGHT,

            /** A 180 degree angle, or a straight line. */
            STRAIGHT,

            /** A 270 degree angle, or a left turn. */
            LEFT,
        }

        /**
         * Get the angle two edges form on a node.
         * 
         * @param node
         *            the node the two edges are attached to
         * @param edge1
         *            the first edge
         * @param edge2
         *            the second edge
         * @return the angle between the two edges
         */
        OrthogonalAngle getAngle(INode node, IEdge edge1, IEdge edge2);

        /**
         * Get the bends along an edge following the edge from source node to target node. The bends
         * are encoded as an array of angles. The array should contain only angles of 90 or 270
         * degrees, or right and left turns.
         * 
         * @param edge
         *            the edge to get the bends for
         * @return an array of bend angles
         */
        OrthogonalAngle[] getBends(IEdge edge);

    }

    /**
     * This takes a planar graph and computes an orthogonal representation defining the shape of the
     * orthogonal graph.
     * 
     * @param graph
     *            the graph to draw as orthogonal graph
     * @return an orthogonal representation of the graph
     */
    IOrthogonalRepresentation orthogonalize(IGraph graph);

}
