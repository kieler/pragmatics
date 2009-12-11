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
 * @kieler.rating 2009-12-11 proposed yellow msp
 * @author <a href="mailto:msp@informatik.uni-kiel.de">Miro Sp&ouml;nemann</a>
 */
public class KSlimFace extends KSlimGraphElement {

    /**
     * An entry of a list associated with a face.
     */
    public static class BorderEntry {
        /** an edge bordering this face. */
        private final KSlimEdge edge;
        /** indicates whether the bordering edge is traversed forward. */
        private final boolean forward;

        /**
         * Creates a border entry for the given edge.
         * 
         * @param theedge edge bordering the containing face
         * @param theforward indicates whether the bordering edge is traversed forward
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
            this.edge = entry.getEdge();
            this.forward = entry.isForward();
        }

        /**
         * Returns the opposed face, as seen from the containing face.
         * 
         * @return the left face if the contained edge is forward, else the
         *         right face
         */
        public KSlimFace opposed() {
            if (isForward()) {
                return getEdge().getLeftFace();
            } else {
                return getEdge().getRightFace();
            }
        }

        /**
         * Returns the first node encountered when traversing the edge in
         * forward direction.
         * 
         * @return the source if the contained edge is forward, else the target
         */
        public KSlimNode firstNode() {
            if (isForward()) {
                return getEdge().getSource();
            } else {
                return getEdge().getTarget();
            }
        }

        /**
         * Returns the second node encountered when traversing the edge in
         * forward direction.
         * 
         * @return the target if the contained edge is forward, else the source
         */
        public KSlimNode secondNode() {
            if (isForward()) {
                return getEdge().getTarget();
            } else {
                return getEdge().getSource();
            }
        }

        /**
         * Returns the side of the contained edge at the first encountered node.
         * 
         * @return the side at source if the contained edge is forward, else the
         *         side at target
         */
        public KSlimNode.Side firstSide() {
            if (isForward()) {
                return getEdge().getSourceSide();
            } else {
                return getEdge().getTargetSide();
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
            if (isForward()) {
                return getEdge().getTargetSide();
            } else {
                return getEdge().getSourceSide();
            }
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String toString() {
            if (isForward()) {
                return ">" + getEdge().getId();
            } else {
                return "<" + getEdge().getId();
            }
        }

        /**
         * Returns the edge.
         *
         * @return the edge
         */
        public KSlimEdge getEdge() {
            return edge;
        }

        /**
         * Returns whether the bordering edge is traversed forward.
         *
         * @return the forward
         */
        public boolean isForward() {
            return forward;
        }
    }

    /**
     * set of lists of bordering edges (can be multiple lists for the external face).
     */
    private final List<List<BorderEntry>> borders = new LinkedList<List<BorderEntry>>();

    /**
     * Creates a face and optionally adds it to the given graph.
     * 
     * @param graph graph to which the new face shall be added
     * @param addToInternal if true, the new face is added to the graph's list
     *            of internal faces
     */
    public KSlimFace(final KSlimGraph graph, final boolean addToInternal) {
        if (addToInternal) {
            graph.getFaces().add(this);
        }
        this.setId(graph.nextFaceId());
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
        for (List<BorderEntry> border : getBorders()) {
            ListIterator<BorderEntry> borderIter = border.listIterator();
            while (borderIter.hasNext()) {
                BorderEntry nextEntry = borderIter.next();
                if (nextEntry.getEdge().getId() == edge.getId() && nextEntry.isForward() == forward) {
                    return borderIter;
                }
            }
        }
        return null;
    }

    /**
     * Returns the borders.
     *
     * @return the borders
     */
    public List<List<BorderEntry>> getBorders() {
        return borders;
    }

}
