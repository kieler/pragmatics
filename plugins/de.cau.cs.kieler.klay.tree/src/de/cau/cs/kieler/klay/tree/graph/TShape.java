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
package de.cau.cs.kieler.klay.tree.graph;

import de.cau.cs.kieler.core.math.KVector;

/**
 * Abstract superclass for {@link TGraphElement}s that can have a position and a size.
 * 
 * @author sor
 * @author sgu
 */
public abstract class TShape extends TGraphElement {

    /** the serial version UID. */
    private static final long serialVersionUID = 1L;

    /** the current position of the element. */
    private final KVector pos = new KVector();
    /** the size of the element. */
    private final KVector size = new KVector();

    /**
     * Creates a shape in the context of the given graph.
     * 
     * @param id the identifier
     */
    public TShape(final int id) {
        super(id);
    }

    /**
     * Implicit super constructor.
     */
    public TShape() {
    }

    /**
     * Returns the element's current position. For nodes this is the coordinates of their center
     * point, for other elements the coordinates of the upper left corner.
     * 
     * @return the position
     */
    public KVector getPosition() {
        return pos;
    }

    /**
     * Returns the element's current size.
     * 
     * @return the size
     */
    public KVector getSize() {
        return size;
    }
}
