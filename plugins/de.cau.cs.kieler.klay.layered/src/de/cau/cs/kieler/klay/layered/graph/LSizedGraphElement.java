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
package de.cau.cs.kieler.klay.layered.graph;

import de.cau.cs.kieler.core.math.KVector;

/**
 * Abstract superclass for {@link LGraphElement}s that can have a position and a size.
 * 
 * @author cds
 */
public abstract class LSizedGraphElement extends LGraphElement {
    
    /** the current position of the node. */
    private KVector pos = new KVector();
    /** the size of the node. */
    private KVector size = new KVector();


    /**
     * Returns the element's current position. Unless noted otherwise, this is the coordinate
     * of the element's upper left corner. May be modified.
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
