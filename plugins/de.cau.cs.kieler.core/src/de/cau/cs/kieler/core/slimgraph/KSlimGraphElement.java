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

/**
 * An element of a slim graph, which can be a node, an edge or a face.
 * 
 * @kieler.rating 2009-12-11 proposed yellow msp
 * @author msp
 */
public abstract class KSlimGraphElement implements Comparable<KSlimGraphElement> {

    /** identifier of this element, determined at creation time. */
    private int id;
    /** rank of this element, used by various algorithms. */
    private int rank = 0;
    /** object contained in this element, or null if there is none. */
    private Object object = null;

    /**
     * {@inheritDoc}
     */
    public int compareTo(final KSlimGraphElement other) {
        return this.getId() - other.getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object other) {
        return (other instanceof KSlimGraphElement && other.getClass() == this.getClass())
                && ((KSlimGraphElement) other).getId() == this.getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return getClass().hashCode() + getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" + getId() + ")";
    }

    /**
     * Sets the id.
     *
     * @param theid the id to set
     */
    protected void setId(final int theid) {
        this.id = theid;
    }

    /**
     * Returns the id.
     *
     * @return the id
     */
    public int getId() {
        return id;
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
     * Sets the object.
     *
     * @param theobject the object to set
     */
    protected void setObject(final Object theobject) {
        this.object = theobject;
    }

    /**
     * Returns the object.
     *
     * @return the object
     */
    public Object getObject() {
        return object;
    }

}
