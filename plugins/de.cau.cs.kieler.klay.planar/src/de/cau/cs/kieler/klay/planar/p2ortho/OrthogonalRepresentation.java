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
package de.cau.cs.kieler.klay.planar.p2ortho;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.klay.planar.graph.PEdge;
import de.cau.cs.kieler.klay.planar.graph.PNode;

/**
 * An encoding class for an orthogonal representation. Formally, an orthogonal representation is a
 * function from the set of faces of a graph, to a list of pairs containing an edge on the face and
 * an array of angles encoding bends on the edge. The last angle in the array defines the array the
 * edge forms with the next edge in the list.
 * 
 * @author ocl
 * @author pkl
 */
public class OrthogonalRepresentation {

    /**
     * Defines angles in an orthogonal representation. Since in an orthogonal layout, all angles are
     * multiples of 90 degrees, only values for 0, 90, 180 and 270 are allowed.
     */
    public enum OrthogonalAngle {
        /** A 90 degree angle, or a left turn. */
        LEFT,

        /** A 180 degree angle, or a straight line. */
        STRAIGHT,

        /** A 270 degree angle, or a right turn. */
        RIGHT,

        /** A 360 degree angle, or full circle. */
        FULL;

        /**
         * Maps ordinal to the enum.
         * 
         * @param angleInt
         *            , angle as ordinal.
         * @return angle to the given ordinal.
         */
        public static OrthogonalAngle map(final int angleInt) {
            switch (angleInt) {
            case 0:
                return LEFT;
            case 1:
                return STRAIGHT;
            case 2:
                return RIGHT;
            case 3:
                return FULL;
            default:
                throw new IllegalArgumentException();
            }
        }
    }

    // ======================== Attributes =========================================================

    /** The bends along each edge. */
    private Map<PEdge, OrthogonalAngle[]> bendData;

    /** The angles in a node. */
    private Map<PNode, List<Pair<PEdge, OrthogonalAngle>>> angleData;

    // ======================== Constructor ========================================================

    /**
     * Create a new orthogonal representation.
     */
    public OrthogonalRepresentation() {
        this.bendData = new HashMap<PEdge, OrthogonalAngle[]>();
        this.angleData = new HashMap<PNode, List<Pair<PEdge, OrthogonalAngle>>>();
    }

    // ======================== Getters and Setters ================================================

    /**
     * Get the bend points along an edge in the graph.
     * 
     * @param edge
     *            an edge in the graph
     * @return an array containing left or right bends
     */
    public OrthogonalAngle[] getBends(final PEdge edge) {
        return this.bendData.get(edge);
    }

    /**
     * Set the bend points along an edge in the graph.
     * 
     * @param edge
     *            the edge in the graph
     * @param bends
     *            an array containing left or right bends
     */
    public void setBends(final PEdge edge, final OrthogonalAngle[] bends) {
        this.bendData.put(edge, bends);
    }

    /**
     * Get the angles in a node in the graph.
     * 
     * @param node
     *            a node in the graph
     * @return a list containing the ordered list of edges on the node, together with the angle it
     *         forms with the next edge
     */
    public List<Pair<PEdge, OrthogonalAngle>> getAngles(final PNode node) {
        return this.angleData.get(node);
    }

    /**
     * Set the angles in a node in the graph.
     * 
     * @param node
     *            a node in the graph
     * @param angles
     *            a list containing the ordered list of edges on the node, together with the angle
     *            it forms with the next edge
     */
    public void setAngles(final PNode node, final List<Pair<PEdge, OrthogonalAngle>> angles) {
        this.angleData.put(node, angles);
    }

}
