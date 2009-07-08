/******************************************************************************
 * KIELER - Kiel Integrated Environment for Layout for the Eclipse RCP
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
package de.cau.cs.kieler.core.util;

/**
 * Object that may contain another object, inspired by the Haskell type <i>Maybe</i>.
 * 
 * @author <a href="mailto:msp@informatik.uni-kiel.de">Miro Sp&ouml;nemann</a>
 */
public class Maybe<T> {

    /** the contained object, which may be {@code null} */
    public T object;
    
    /**
     * Creates a maybe without an object.
     */
    public Maybe() {
        this.object = null;
    }
    
    /**
     * Creates a maybe with the given object.
     * 
     * @param object the object to contain
     */
    public Maybe(T object) {
        this.object = object;
    }
    
}
