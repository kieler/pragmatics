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
package de.cau.cs.kieler.klay.layered.graph;

import java.util.LinkedList;
import java.util.List;

import de.cau.cs.kieler.core.math.KVector;

/**
 * An edge in a layered graph. Edges may only be connected to ports of a node, which represent the
 * point where the edge touches the node.
 * 
 * @author msp
 */
public class LEdge extends LGraphElement {

    /** the bend points. */
    private List<KVector> bendPoints = new LinkedList<KVector>();
    /** the source and target ports. */
    private LPort source, target;
    /** labels assigned to this edge. */
    private List<LLabel> labels = new LinkedList<LLabel>();

    /**
     * {@inheritDoc}
     */
    public String toString() {
        if (source != null && target != null) {
            return source.getNode() + "(" + source + ")->" + target.getNode() + "(" + target + ")";
        } else {
            return "e_" + hashCode();
        }
    }

    /**
     * Returns the source port.
     * 
     * @return the source port
     */
    public LPort getSource() {
        return source;
    }

    /**
     * Sets the source port of this edge and adds itself to the port's list of edges. If the edge
     * previously had another source, it is removed from the original port's list of edges. Be
     * careful not to use this method while iterating through the edges list of the old port nor of
     * the new port, since that could lead to {@link java.util.ConcurrentModificationException}s.
     * 
     * @param thesource
     *            the source port to set
     */
    public void setSource(final LPort thesource) {
        if (source != null) {
            source.getEdges().remove(this);
        }
        this.source = thesource;
        source.getEdges().add(this);
    }

    /**
     * Returns the target port.
     * 
     * @return the target port
     */
    public LPort getTarget() {
        return target;
    }

    /**
     * Sets the target port of this edge and adds itself to the port's list of edges. If the edge
     * previously had another target, it is removed from the original port's list of edges. Be
     * careful not to use this method while iterating through the edges list of the old port nor of
     * the new port, since that could lead to {@link java.util.ConcurrentModificationException}s.
     * 
     * @param thetarget
     *            the target port to set
     */
    public void setTarget(final LPort thetarget) {
        if (target != null) {
            target.getEdges().remove(this);
        }
        this.target = thetarget;
        target.getEdges().add(this);
    }

    /**
     * Returns the list of bend points, which is initially empty.
     * 
     * @return the bend points
     */
    public List<KVector> getBendPoints() {
        return bendPoints;
    }

    /**
     * 
     * @return all labels
     */
    public List<LLabel> getLabels() {
        return this.labels;
    }

}
