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
package de.cau.cs.kieler.core;

/**
 * Exception for error handling in KIELER projects.
 * 
 * @author <a href="mailto:msp@informatik.uni-kiel.de">Miro Sp&ouml;nemann</a>
 */
public class KielerException extends Exception {

    /** the serial version UID. */
    private static final long serialVersionUID = -3050809849973311868L;

    /**
     * Constructs a KIELER exception with given message.
     * 
     * @param message readable exception message
     */
    public KielerException(final String message) {
        super(message);
    }

    /**
     * Constructs a KIELER exception with given message and cause.
     * 
     * @param message readable exception message
     * @param cause exception that caused this exception
     */
    public KielerException(final String message, final Exception cause) {
        super(message, cause);
    }

}
