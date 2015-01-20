/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2014 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.layered.intermediate;

/**
 * Exception thrown on faulty Input.
 * 
 * @author alan
 *
 */
public class SwitchDeciderException extends Exception {

    private static final long serialVersionUID = 2423454474886943208L;

    /**
     * @param message
     *            Error spec.
     */
    public SwitchDeciderException(final String message) {
        super(message);
    }

}
