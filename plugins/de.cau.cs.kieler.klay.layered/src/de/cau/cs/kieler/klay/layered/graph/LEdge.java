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

/**
 * An edge in a layered graph.
 *
 * @author msp
 */
public class LEdge {

    /** the bend points. */
    private List<Coord> bendPoints = new LinkedList<Coord>();
    /** the original object from which the edge was created. */
    private Object origin;
    /** the source and target ports. */
    private LPort source, target;

    /**
     * Creates an edge.
     * 
     * @param theorigin the original object for the edge
     */
    public LEdge(final Object theorigin) {
        this.origin = theorigin;
    }

    /**
     * {@inheritDoc}
     */
    public String toString() {
        if (source != null && target != null) {
            return source.getOwner() + "(" + source + ")->" + target.getOwner() + "(" + target + ")";
        } else {
            return "e_" + hashCode();
        }
    }
    
    /**
     * @return the source
     */
    public LPort getSource() {
        return source;
    }

    /**
     * Sets the source port of this edge and adds itself to the port's list
     * of edges. If the edge previously had another source, it is removed
     * from the original port's list of edges.
     * 
     * @param thesource the source to set
     */
    public void setSource(final LPort thesource) {
        if (source != null) {
            source.getEdges().remove(this);
        }
        this.source = thesource;
        source.getEdges().add(this);
    }

    /**
     * @return the target
     */
    public LPort getTarget() {
        return target;
    }

    /**
     * Sets the target port of this edge and adds itself to the port's list
     * of edges. If the edge previously had another target, it is removed from
     * the original port's list of edges.
     * 
     * @param thetarget the target to set
     */
    public void setTarget(final LPort thetarget) {
        if (target != null) {
            target.getEdges().remove(this);
        }
        this.target = thetarget;
        target.getEdges().add(this);
    }

    /**
     * @return the bendPoints
     */
    public List<Coord> getBendPoints() {
        return bendPoints;
    }

    /**
     * @return the origin
     */
    public Object getOrigin() {
        return origin;
    }

}
