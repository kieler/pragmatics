/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.planar.graph;

import de.cau.cs.kieler.core.math.KVector;

/**
 * Abstract superclass for {@link PGraphElement}s that can have a position and a size.
 * 
 * @author pkl
 */
public abstract class PShape extends PGraphElement {

    /** the serial version UID. */
    private static final long serialVersionUID = -5722626533071201367L;
    
    /** the current position of the element. */
    private KVector pos = new KVector();
    /** the size of the element. */
    private KVector size = new KVector();

    /**
     * Create a new graph element based on an id and a parent graph.
     * 
     * @param id
     *            the id to assign to the element
     * @param p
     *            the graph this element belongs to
     */
    public PShape(final int id, final PGraph p) {
        super(id, p);
    }

    /**
     * Returns the element's current position. Unless noted otherwise, this is the coordinate of the
     * element's upper left corner. May be modified.
     * 
     * @return the position
     */
    public KVector getPosition() {
        return pos;
    }

    /**
     * Returns the element's current size. May be modified.
     * 
     * @return the size
     */
    public KVector getSize() {
        return size;
    }

}
