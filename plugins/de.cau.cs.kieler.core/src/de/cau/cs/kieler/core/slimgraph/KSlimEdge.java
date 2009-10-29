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
 * @author <a href="mailto:msp@informatik.uni-kiel.de">Miro Sp&ouml;nemann</a>
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
        public Type type;
        /** the x coordinate position. */
        public float xpos;
        /** the y coordinate position. */
        public float ypos;
        /** the index of this bend. */
        public int index;

        /** the edge associated with this bend. */
        private KSlimEdge edge;

        /**
         * Creates an edge bend of given type.
         * 
         * @param theedge the edge to which the new bend is added
         * @param thetype type of edge bend
         */
        public Bend(final KSlimEdge theedge, final Type thetype) {
            this.edge = theedge;
            this.type = thetype;
            index = theedge.bends.size();
        }

        /**
         * Returns the slim edge associated with this bend.
         * 
         * @return the edge
         */
        public KSlimEdge getEdge() {
            return edge;
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString() {
            return type.toString();
        }
    }

    /** source node. */
    public KSlimNode source;
    /** target node. */
    public KSlimNode target;
    /** left face. */
    public KSlimFace leftFace;
    /** right face. */
    public KSlimFace rightFace;
    /** the bends of this edge. */
    public final List<Bend> bends = new LinkedList<Bend>();
    /** the side on which the edge leaves the source. */
    public KSlimNode.Side sourceSide = KSlimNode.Side.UNDEFINED;
    /** the side on which the edge reaches the target. */
    public KSlimNode.Side targetSide = KSlimNode.Side.UNDEFINED;

    /**
     * Creates an edge connecting two existing nodes.
     * 
     * @param graph the graph to which the new edge shall be added
     * @param thesource source node
     * @param thetarget target node
     */
    public KSlimEdge(final KSlimGraph graph, final KSlimNode thesource,
            final KSlimNode thetarget) {
        graph.edges.add(this);
        this.id = graph.nextEdgeId++;
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
        this.object = theobj;
    }

    /**
     * Connects this edge with the source and target. New incidence entries are
     * created for the incidence lists of the source and the target.
     */
    public void connectNodes() {
        source.incidence.add(new KSlimNode.IncEntry(this, KSlimNode.IncEntry.Type.OUT));
        target.incidence.add(new KSlimNode.IncEntry(this, KSlimNode.IncEntry.Type.IN));
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
        if (source.id == target.id && (sourceRank < targetRank || (sourceRank == targetRank
                && forwardSelfLoop))) {
            thetargetRank++;
        }
        source.incidence.add(sourceRank, new KSlimNode.IncEntry(this, KSlimNode.IncEntry.Type.OUT));
        target.incidence.add(thetargetRank, new KSlimNode.IncEntry(this, KSlimNode.IncEntry.Type.IN));
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
        this.sourceSide = thesourceSide;
        this.targetSide = thetargetSide;
        ListIterator<KSlimNode.IncEntry> incIter = source.incidence.listIterator();
        while (incIter.hasNext()) {
            KSlimNode.IncEntry nextEntry = incIter.next();
            KSlimNode.Side side = (nextEntry.type == KSlimNode.IncEntry.Type.OUT
                    ? nextEntry.edge.sourceSide : nextEntry.edge.targetSide);
            if (thesourceSide.compareTo(side) <= 0) {
                incIter.previous();
                break;
            }
        }
        incIter.add(new KSlimNode.IncEntry(this, KSlimNode.IncEntry.Type.OUT));
        incIter = target.incidence.listIterator();
        while (incIter.hasNext()) {
            KSlimNode.IncEntry nextEntry = incIter.next();
            KSlimNode.Side side = (nextEntry.type == KSlimNode.IncEntry.Type.OUT
                    ? nextEntry.edge.sourceSide : nextEntry.edge.targetSide);
            if (thetargetSide.compareTo(side) <= 0) {
                incIter.previous();
                break;
            }
        }
        incIter.add(new KSlimNode.IncEntry(this, KSlimNode.IncEntry.Type.IN));
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.cau.cs.kieler.klodd.orthogonal.structures.TSMGraphElement#toString()
     */
    @Override
    public String toString() {
        String baseString = super.toString();
        if (source != null && target != null) {
            return baseString + " " + source.id + ">" + target.id;
        } else {
            return baseString;
        }
    }

}
