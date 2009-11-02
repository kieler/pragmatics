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
package de.cau.cs.kieler.core.slimgraph;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * A face in the slim graph structure.
 * 
 * @author <a href="mailto:msp@informatik.uni-kiel.de">Miro Sp&ouml;nemann</a>
 */
public class KSlimFace extends KSlimGraphElement {

    /**
     * An entry of a list associated with a face.
     */
    public static class BorderEntry {
        /** an edge bordering this face. */
        public KSlimEdge edge;
        /** indicates whether the bordering edge is traversed forward. */
        public boolean forward;

        /**
         * Creates a border entry for the given edge.
         * 
         * @param theedge edge bordering the containing face
         * @param theforward indicates whether the bordering edge is traversed
         *            forward
         */
        public BorderEntry(final KSlimEdge theedge, final boolean theforward) {
            this.edge = theedge;
            this.forward = theforward;
        }

        /**
         * Creates a border entry copying an existing one.
         * 
         * @param entry border entry to copy
         */
        public BorderEntry(final BorderEntry entry) {
            this.edge = entry.edge;
            this.forward = entry.forward;
        }

        /**
         * Returns the opposed face, as seen from the containing face.
         * 
         * @return the left face if the contained edge is forward, else the
         *         right face
         */
        public KSlimFace opposed() {
            if (forward) {
                return edge.leftFace;
            } else {
                return edge.rightFace;
            }
        }

        /**
         * Returns the first node encountered when traversing the edge in
         * forward direction.
         * 
         * @return the source if the contained edge is forward, else the target
         */
        public KSlimNode firstNode() {
            if (forward) {
                return edge.source;
            } else {
                return edge.target;
            }
        }

        /**
         * Returns the second node encountered when traversing the edge in
         * forward direction.
         * 
         * @return the target if the contained edge is forward, else the source
         */
        public KSlimNode secondNode() {
            if (forward) {
                return edge.target;
            } else {
                return edge.source;
            }
        }

        /**
         * Returns the side of the contained edge at the first encountered node.
         * 
         * @return the side at source if the contained edge is forward, else the
         *         side at target
         */
        public KSlimNode.Side firstSide() {
            if (forward) {
                return edge.sourceSide;
            } else {
                return edge.targetSide;
            }
        }

        /**
         * Returns the side of the contained edge at the second encountered
         * node.
         * 
         * @return the side at target if the contained edge is forward, else the
         *         side at source
         */
        public KSlimNode.Side secondSide() {
            if (forward) {
                return edge.targetSide;
            } else {
                return edge.sourceSide;
            }
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String toString() {
            if (forward) {
                return ">" + edge.id;
            } else {
                return "<" + edge.id;
            }
        }
    }

    /**
     * set of lists of bordering edges (can be multiple lists for the external face).
     */
    public final List<List<BorderEntry>> borders = new LinkedList<List<BorderEntry>>();

    /**
     * Creates a face and optionally adds it to the given graph.
     * 
     * @param graph graph to which the new face shall be added
     * @param addToInternal if true, the new face is added to the graph's list
     *            of internal faces
     */
    public KSlimFace(final KSlimGraph graph, final boolean addToInternal) {
        if (addToInternal) {
            graph.faces.add(this);
        }
        this.id = graph.nextFaceId++;
    }

    /**
     * Gets a list iterator with the current position at the given edge. The
     * returned iterator has its cursor directly after the edge.
     * 
     * @param edge edge at which the iterator shall point
     * @param forward is the given edge forward for this face?
     * @return iterator pointing at <code>edge</code>, or null if the edge was
     *         not found
     */
    public ListIterator<KSlimFace.BorderEntry> getIterator(final KSlimEdge edge,
            final boolean forward) {
        for (List<BorderEntry> border : borders) {
            ListIterator<BorderEntry> borderIter = border.listIterator();
            while (borderIter.hasNext()) {
                BorderEntry nextEntry = borderIter.next();
                if (nextEntry.edge.id == edge.id && nextEntry.forward == forward) {
                    return borderIter;
                }
            }
        }
        return null;
    }

}
