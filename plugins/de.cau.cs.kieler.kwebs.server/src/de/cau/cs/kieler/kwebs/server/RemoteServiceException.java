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

package de.cau.cs.kieler.kwebs.server;

/**
 * .
 *
 * @kieler.design 2011-08-02 reviewed by ckru, mri, msp
 * @author swe
 */
public class RemoteServiceException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = -2474790715932256343L;

    /**
     * Default constructor.
     */
    public RemoteServiceException() {
        super();
    }

    /**
     * Constructor with message.
     *
     * @param message
     *            the message
     */
    public RemoteServiceException(final String message) {
        super(message);
    }

    /**
     * Constructor for enclosing another throwable.
     *
     * @param throwable
     *            a throwable to be enclosed by this exception
     */
    public RemoteServiceException(final Throwable throwable) {
        super(throwable);
    }

    /**
     * Constructor for enclosing another throwable with an additional message.
     *
     * @param message
     *            the message to be added.
     * @param throwable
     *            a throwable to be enclosed by this exception
     */
    public RemoteServiceException(final String message, final Throwable throwable) {
        super(message, throwable);
    }

}
