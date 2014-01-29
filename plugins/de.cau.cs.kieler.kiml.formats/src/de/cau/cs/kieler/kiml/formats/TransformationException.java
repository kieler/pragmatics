/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2008 by
 * + Christian-Albrechts-University of Kiel
 *     + Department of Computer Science
 *         + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */

package de.cau.cs.kieler.kiml.formats;

/**
 * An exception for graph transformation errors.
 *
 * @author swe
 * @kieler.design proposed by msp
 * @kieler.rating proposed yellow 2012-07-10 msp
 */
public class TransformationException extends RuntimeException {

    /** the serial version UID. */
    private static final long serialVersionUID = 6890836655529871596L;

    /**
     * Default constructor.
     */
    public TransformationException() {
        super();
    }

    /**
     * Constructor with a message.
     * 
     * @param message
     *            the message describing the exception
     */
    public TransformationException(final String message) {
        super(message);
    }
    
    /**
     * Constructor for enclosing another throwable.
     * 
     * @param throwable
     *            a throwable to be enclosed by this exception
     */
    public TransformationException(final Throwable throwable) {
        super(throwable);
    }
    
    /**
     * Constructor with a message for enclosing another throwable.
     * 
     * @param message
     *            the message describing the exception
     * @param throwable
     *            a throwable to be enclosed by this exception
     */
    public TransformationException(final String message, final Throwable throwable) {
        super(message, throwable);
    }
    
}
