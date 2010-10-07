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
package de.cau.cs.kieler.core;

/**
 * Kieler runtime exception.
 * Might be helpful to indicate erroneous behavior
 * of (overloaded) methods that are not allowed to throw exceptions.
 * 
 * @author chsch
 */
public class KielerRuntimeException extends RuntimeException {

    /** the serial version UID. */
    private static final long serialVersionUID = 5052139882180628740L;

    /**
     * Creates a KIELER runtime exception.
     * 
     * @param message a message
     */
    public KielerRuntimeException(final String message) {
        super(message);
    }
    
    /**
     * Creates a KIELER runtime exception as wrapper for another exception.
     * 
     * @param message a message
     * @param cause the original error
     */
    public KielerRuntimeException(final String message, final Throwable cause) {
        super(message, cause);
    }
    
}
