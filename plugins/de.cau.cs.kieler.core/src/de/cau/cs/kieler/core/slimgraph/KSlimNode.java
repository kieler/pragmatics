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
 * A node in the slim graph structure.
 * 
 * @kieler.rating 2009-12-11 proposed yellow msp
 * @author msp
 */
public class KSlimNode extends KSlimGraphElement {

    /**
     * Single entry of a incidence list.
     */
    public static class IncEntry {
        /** type of incidence entry: incoming or outgoing edge. */
        public enum Type {
            /** incoming edge. */
            IN,
            /** outgoing edge. */
            OUT
        }

        /** the edge of this incidence entry. */
        private final KSlimEdge edge;
        /** type of incidence: incoming or outgoing. */
        private Type type;

        /**
         * Creates an incidence list entry.
         * 
         * @param theedge the edge
         * @param thetype the incidence type
         */
        public IncEntry(final KSlimEdge theedge, final Type thetype) {
            this.edge = theedge;
            this.setType(thetype);
        }

        /**
         * Returns the endpoint of this incidence entry, as seen from the
         * containing node.
         * 
         * @return the source of the edge if this is an incoming type, else the
         *         target of the edge
         */
        public KSlimNode endpoint() {
            if (getType() == Type.IN) {
                return getEdge().getSource();
            } else {
                return getEdge().getTarget();
            }
        }

        /**
         * Returns the left face of this incidence entry, as seen from the
         * containing node.
         * 
         * @return the right face of the edge if this is an incoming type, else
         *         the left face of the edge
         */
        public KSlimFace leftFace() {
            if (getType() == Type.IN) {
                return getEdge().getRightFace();
            } else {
                return getEdge().getLeftFace();
            }
        }

        /**
         * Returns the side of the containing node on which this incidence entry
         * lies.
         * 
         * @return the target side of the edge if this is an incoming type, else
         *         the source side
         */
        public Side side() {
            if (getType() == Type.IN) {
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
            return getType().toString() + getEdge().getId();
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
         * Sets the type.
         *
         * @param thetype the type to set
         */
        public void setType(final Type thetype) {
            this.type = thetype;
        }

        /**
         * Returns the type.
         *
         * @return the type
         */
        public Type getType() {
            return type;
        }
    }

    /**
     * Definition of sides of a node. The order of side definitions ensures that
     * the default enumeration comparator implementation respects the node sides
     * found in clockwise order.
     */
    public static enum Side {
        /** the side is undefined. */
        UNDEFINED,
        /** north side. */
        NORTH,
        /** east side. */
        EAST,
        /** south side. */
        SOUTH,
        /** west side. */
        WEST;

        /**
         * Returns the next side in clockwise order.
         * 
         * @return the next side in clockwise order
         */
        public Side right() {
            switch (this) {
            case NORTH:
                return EAST;
            case EAST:
                return SOUTH;
            case SOUTH:
                return WEST;
            case WEST:
                return NORTH;
            default:
                return UNDEFINED;
            }
        }

        /**
         * Returns the next side in counter-clockwise order.
         * 
         * @return the next side in counter-clockwise order
         */
        public Side left() {
            switch (this) {
            case NORTH:
                return WEST;
            case EAST:
                return NORTH;
            case SOUTH:
                return EAST;
            case WEST:
                return SOUTH;
            default:
                return UNDEFINED;
            }
        }

        /**
         * Returns the opposed side.
         * 
         * @return the opposed side
         */
        public Side opposed() {
            switch (this) {
            case NORTH:
                return SOUTH;
            case EAST:
                return WEST;
            case SOUTH:
                return NORTH;
            case WEST:
                return EAST;
            default:
                return UNDEFINED;
            }
        }
    }

    /** list of incident edges. */
    private final List<IncEntry> incidence = new LinkedList<IncEntry>();
    /** concrete x coordinate position. */
    private float xpos;
    /** concrete y coordinate position. */
    private float ypos;

    /**
     * Creates a node containing the given object.
     * 
     * @param graph the graph to which the new node shall be added
     * @param obj the object to be contained
     */
    public KSlimNode(final KSlimGraph graph, final Object obj) {
        graph.getNodes().add(this);
        this.setObject(obj);
        this.setId(graph.nextNodeId());
    }

    /**
     * Creates a node containing no object.
     * 
     * @param graph the graph to which the new node shall be added
     */
    public KSlimNode(final KSlimGraph graph) {
        graph.getNodes().add(this);
        this.setObject(null);
        this.setId(graph.nextNodeId());
    }

    /**
     * Gets a list iterator for this node's incidence list, with the current
     * position at the given edge. The returned list iterator has its cursor
     * directly after the edge
     * 
     * @param edge edge at which the iterator shall point
     * @param outgoing is the given edge an outgoing edge?
     * @return iterator pointing at <code>edge</code>, or null if the edge was
     *         not found
     */
    public ListIterator<KSlimNode.IncEntry> getIterator(final KSlimEdge edge,
            final boolean outgoing) {
        ListIterator<IncEntry> edgeIter = getIncidence().listIterator();
        while (edgeIter.hasNext()) {
            IncEntry nextEntry = edgeIter.next();
            if (nextEntry.getEdge().getId() == edge.getId()
                    && (nextEntry.getType() == IncEntry.Type.OUT) == outgoing) {
                return edgeIter;
            }
        }
        return null;
    }

    /**
     * Returns the incidence.
     *
     * @return the incidence
     */
    public List<IncEntry> getIncidence() {
        return incidence;
    }

    /**
     * Sets the xpos.
     *
     * @param thexpos the xpos to set
     */
    public void setXpos(final float thexpos) {
        this.xpos = thexpos;
    }

    /**
     * Returns the xpos.
     *
     * @return the xpos
     */
    public float getXpos() {
        return xpos;
    }

    /**
     * Sets the ypos.
     *
     * @param theypos the ypos to set
     */
    public void setYpos(final float theypos) {
        this.ypos = theypos;
    }

    /**
     * Returns the ypos.
     *
     * @return the ypos
     */
    public float getYpos() {
        return ypos;
    }

}
