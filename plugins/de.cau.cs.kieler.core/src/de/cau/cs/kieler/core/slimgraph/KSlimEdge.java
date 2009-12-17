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
 * An edge in the slim graph structure.
 * 
 * @kieler.rating 2009-12-11 proposed yellow msp
 * @author msp
 */
public class KSlimEdge extends KSlimGraphElement {

    /**
     * Definition of an edge bend for orthogonal drawing.
     */
    public static class Bend {
        /** Type of edge bend, from the perspective of the source node. */
        public enum Type {
            /** undefined. */
            UNDEFINED,
            /** left bend. */
            LEFT,
            /** right bend. */
            RIGHT
        }

        /** the type of edge bend. */
        private final Type type;
        /** the x coordinate position. */
        private float xpos;
        /** the y coordinate position. */
        private float ypos;
        /** the index of this bend. */
        private final int index;
        /** the edge associated with this bend. */
        private final KSlimEdge edge;

        /**
         * Creates an edge bend of given type.
         * 
         * @param theedge the edge to which the new bend is added
         * @param thetype type of edge bend
         */
        public Bend(final KSlimEdge theedge, final Type thetype) {
            this.edge = theedge;
            this.type = thetype;
            this.index = theedge.getBends().size();
        }

        /**
         * Returns the bend type.
         * 
         * @return the type
         */
        public Type getType() {
            return type;
        }

        /**
         * Sets the x position.
         * 
         * @param thexpos the x position to set
         */
        public void setXpos(final float thexpos) {
            this.xpos = thexpos;
        }

        /**
         * Returns the x position.
         * 
         * @return the x position
         */
        public float getXpos() {
            return xpos;
        }

        /**
         * @param theypos the ypos to set
         */
        public void setYpos(final float theypos) {
            this.ypos = theypos;
        }

        /**
         * Returns the y position.
         * 
         * @return the y position
         */
        public float getYpos() {
            return ypos;
        }

        /**
         * Returns the index.
         * 
         * @return the index
         */
        public int getIndex() {
            return index;
        }

        /**
         * Returns the slim edge associated with this bend.
         * 
         * @return the edge
         */
        public KSlimEdge getEdge() {
            return edge;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String toString() {
            return getType().toString();
        }
    }

    /** source node. */
    private KSlimNode source;
    /** target node. */
    private KSlimNode target;
    /** left face. */
    private KSlimFace leftFace;
    /** right face. */
    private KSlimFace rightFace;
    /** the bends of this edge. */
    private final List<Bend> bends = new LinkedList<Bend>();
    /** the side on which the edge leaves the source. */
    private KSlimNode.Side sourceSide = KSlimNode.Side.UNDEFINED;
    /** the side on which the edge reaches the target. */
    private KSlimNode.Side targetSide = KSlimNode.Side.UNDEFINED;

    /**
     * Creates an edge connecting two existing nodes.
     * 
     * @param graph the graph to which the new edge shall be added
     * @param thesource source node
     * @param thetarget target node
     */
    public KSlimEdge(final KSlimGraph graph, final KSlimNode thesource,
            final KSlimNode thetarget) {
        graph.getEdges().add(this);
        this.setId(graph.nextEdgeId());
        this.source = thesource;
        this.target = thetarget;
    }

    /**
     * Creates an edge connecting two existing nodes, with an object to be
     * contained.
     * 
     * @param graph the graph to which the new edge shall be added
     * @param thesource source node
     * @param thetarget target node
     * @param theobj object to be contained
     */
    public KSlimEdge(final KSlimGraph graph, final KSlimNode thesource,
            final KSlimNode thetarget, final Object theobj) {
        this(graph, thesource, thetarget);
        this.setObject(theobj);
    }

    /**
     * Connects this edge with the source and target. New incidence entries are
     * created for the incidence lists of the source and the target.
     */
    public void connectNodes() {
        getSource().getIncidence().add(new KSlimNode.IncEntry(this, KSlimNode.IncEntry.Type.OUT));
        getTarget().getIncidence().add(new KSlimNode.IncEntry(this, KSlimNode.IncEntry.Type.IN));
    }

    /**
     * Connects this edge with the source and target with given ranks. New
     * incidence entries are created for the incidence lists of the source and
     * the target.
     * 
     * @param sourceRank rank of the edge at source
     * @param targetRank rank of the edge at target
     * @param forwardSelfLoop for self-loops: is the target rank greater than
     *            the source rank?
     */
    public void connectNodes(final int sourceRank, final int targetRank,
            final boolean forwardSelfLoop) {
        int thetargetRank = targetRank;
        if (getSource().getId() == getTarget().getId()
                && (sourceRank < targetRank || (sourceRank == targetRank
                && forwardSelfLoop))) {
            thetargetRank++;
        }
        getSource().getIncidence().add(sourceRank, new KSlimNode.IncEntry(this,
                KSlimNode.IncEntry.Type.OUT));
        getTarget().getIncidence().add(thetargetRank, new KSlimNode.IncEntry(this,
                KSlimNode.IncEntry.Type.IN));
    }

    /**
     * Connects this edge with the source and target respecting the order of
     * incidence according to the given node sides.
     * 
     * @param thesourceSide port side at the source node
     * @param thetargetSide port side at the target node
     */
    public void connectNodes(final KSlimNode.Side thesourceSide,
            final KSlimNode.Side thetargetSide) {
        this.setSourceSide(thesourceSide);
        this.setTargetSide(thetargetSide);
        ListIterator<KSlimNode.IncEntry> incIter = getSource().getIncidence().listIterator();
        while (incIter.hasNext()) {
            KSlimNode.IncEntry nextEntry = incIter.next();
            KSlimNode.Side side = (nextEntry.getType() == KSlimNode.IncEntry.Type.OUT
                    ? nextEntry.getEdge().getSourceSide() : nextEntry.getEdge().getTargetSide());
            if (thesourceSide.compareTo(side) <= 0) {
                incIter.previous();
                break;
            }
        }
        incIter.add(new KSlimNode.IncEntry(this, KSlimNode.IncEntry.Type.OUT));
        incIter = getTarget().getIncidence().listIterator();
        while (incIter.hasNext()) {
            KSlimNode.IncEntry nextEntry = incIter.next();
            KSlimNode.Side side = (nextEntry.getType() == KSlimNode.IncEntry.Type.OUT
                    ? nextEntry.getEdge().getSourceSide() : nextEntry.getEdge().getTargetSide());
            if (thetargetSide.compareTo(side) <= 0) {
                incIter.previous();
                break;
            }
        }
        incIter.add(new KSlimNode.IncEntry(this, KSlimNode.IncEntry.Type.IN));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        String baseString = super.toString();
        if (getSource() != null && getTarget() != null) {
            return baseString + " " + getSource().getId() + ">" + getTarget().getId();
        } else {
            return baseString;
        }
    }

    /**
     * Sets the source.
     *
     * @param thesource the source to set
     */
    public void setSource(final KSlimNode thesource) {
        this.source = thesource;
    }

    /**
     * Returns the source.
     *
     * @return the source
     */
    public KSlimNode getSource() {
        return source;
    }

    /**
     * Sets the target.
     *
     * @param thetarget the target to set
     */
    public void setTarget(final KSlimNode thetarget) {
        this.target = thetarget;
    }

    /**
     * Returns the target.
     *
     * @return the target
     */
    public KSlimNode getTarget() {
        return target;
    }

    /**
     * Sets the leftFace.
     *
     * @param theleftFace the leftFace to set
     */
    public void setLeftFace(final KSlimFace theleftFace) {
        this.leftFace = theleftFace;
    }

    /**
     * Returns the left face.
     *
     * @return the left face
     */
    public KSlimFace getLeftFace() {
        return leftFace;
    }

    /**
     * Sets the right face.
     *
     * @param therightFace the right face to set
     */
    public void setRightFace(final KSlimFace therightFace) {
        this.rightFace = therightFace;
    }

    /**
     * Returns the rightFace.
     *
     * @return the rightFace
     */
    public KSlimFace getRightFace() {
        return rightFace;
    }

    /**
     * Returns the bends.
     *
     * @return the bends
     */
    public List<Bend> getBends() {
        return bends;
    }

    /**
     * Sets the source side.
     *
     * @param thesourceSide the source side to set
     */
    public void setSourceSide(final KSlimNode.Side thesourceSide) {
        this.sourceSide = thesourceSide;
    }

    /**
     * Returns the source side.
     *
     * @return the source side
     */
    public KSlimNode.Side getSourceSide() {
        return sourceSide;
    }

    /**
     * Sets the target side.
     *
     * @param thetargetSide the target side to set
     */
    public void setTargetSide(final KSlimNode.Side thetargetSide) {
        this.targetSide = thetargetSide;
    }

    /**
     * Returns the target side.
     *
     * @return the target side
     */
    public KSlimNode.Side getTargetSide() {
        return targetSide;
    }

}
