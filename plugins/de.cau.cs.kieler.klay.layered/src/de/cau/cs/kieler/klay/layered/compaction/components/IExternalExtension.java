/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2015 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klay.layered.compaction.components;

import de.cau.cs.kieler.kiml.options.Direction;
import de.cau.cs.kieler.kiml.util.nodespacing.Rectangle;

/**
 * Container class for edges that represent external edges, i.e. edges that connect to ports that
 * span hierarchy boundaries.
 * 
 * @author uru
 *
 * @param <E>
 *            the type of the element that is being represented by this extension.
 */
public interface IExternalExtension<E> {

    /**
     * @return for instance the underlying edge this extension represents.
     */
    E getRepresentative();

    /**
     * @return the rectangle that represents the external extension.
     */
    Rectangle getRepresentor();
    
    /**
     * @return an optional placeholder along the original diagram's boundary.
     */
    default Rectangle getPlaceholder() {
        return null;
    }
    
    /**
     * @return the rectangle to which this extension (conceptually) connects.
     */
    Rectangle getParent();

    /**
     * @return the direction into which this extension points.
     */
    Direction getDirection();

}
