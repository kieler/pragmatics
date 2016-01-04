/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2014 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.statistics;

/**
 * Exception indicating that an authentication failed.
 * 
 * @author uru
 */
public class AuthenticationException extends Exception {

    /**
     * .
     */
    private static final long serialVersionUID = -6348437960004670986L;

    /**
     * Creates a new exception with the passed msg.
     * 
     * @param msg
     *            a good message.
     */
    public AuthenticationException(final String msg) {
        super(msg);
    }
}
