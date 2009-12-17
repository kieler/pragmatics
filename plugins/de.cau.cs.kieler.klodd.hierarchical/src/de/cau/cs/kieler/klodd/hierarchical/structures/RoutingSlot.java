/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2008 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klodd.hierarchical.structures;

/**
 * A routing slot is a place in which layer connections can be routed.
 * 
 * @kieler.rating 2009-12-11 proposed yellow msp
 * @author msp
 */
public class RoutingSlot {

    /** the slot rank determines the lengthwise distance to the preceding layer. */
    private int rank = 0;
    /** crosswise starting position of this slot. */
    private float start;
    /** crosswise ending position of this slot. */
    private float end;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "slot:" + Math.round(start) + "-" + Math.round(end);
    }

    /**
     * Sets the rank.
     *
     * @param therank the rank to set
     */
    public void setRank(final int therank) {
        this.rank = therank;
    }

    /**
     * Returns the rank.
     *
     * @return the rank
     */
    public int getRank() {
        return rank;
    }

    /**
     * Sets the start.
     *
     * @param thestart the start to set
     */
    public void setStart(final float thestart) {
        this.start = thestart;
    }

    /**
     * Returns the start.
     *
     * @return the start
     */
    public float getStart() {
        return start;
    }

    /**
     * Sets the end.
     *
     * @param theend the end to set
     */
    public void setEnd(final float theend) {
        this.end = theend;
    }

    /**
     * Returns the end.
     *
     * @return the end
     */
    public float getEnd() {
        return end;
    }

}
