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
 * @author <a href="mailto:msp@informatik.uni-kiel.de">Miro Sp&ouml;nemann</a>
 */
public abstract class KSlimGraphElement implements Comparable<KSlimGraphElement> {

    /** identifier of this element, determined at creation time. */
    public int id;
    /** rank of this element, used by various algorithms. */
    public int rank = 0;
    /** object contained in this element, or null if there is none. */
    public Object object = null;

    /**
     * {@inheritDoc}
     */
    public int compareTo(final KSlimGraphElement other) {
        return this.id - other.id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object other) {
        return (other instanceof KSlimGraphElement && other.getClass() == this.getClass())
                && ((KSlimGraphElement) other).id == this.id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return getClass().hashCode() + id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" + id + ")";
    }

}
