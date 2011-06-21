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
package net.ogdf.bin;

/**
 * An exception that is thrown when execution of the OGDF server process fails.
 *
 * @author msp
 */
public class OgdfServerException extends RuntimeException {

    /** the serial version UID. */
    private static final long serialVersionUID = 8631325556340011630L;
    
    /**
     * Constructs a new server exception.
     */
    public OgdfServerException() {
        super();
    }
    
    /**
     * Constructs a new server exception with a detail message.
     * 
     * @param message a detail message
     */
    public OgdfServerException(final String message) {
        super(message);
    }
    
    /**
     * Constructs a new server exception with a detail message and a cause.
     * 
     * @param message a detail message
     * @param cause the cause for this exception
     */
    public OgdfServerException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
