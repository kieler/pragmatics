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
package de.cau.cs.kieler.kiml.graphviz.layouter;

/**
 * Represents errors that occurs while calling the Graphviz command-line tool for
 * automatic layout..
 *
 * @author msp
 */
public class GraphvizException extends RuntimeException {

    /** the serial version UID. */
    private static final long serialVersionUID = 8555047018449649407L;

    /**
     * Create a Graphviz exception with a message.
     * 
     * @param message a message
     */
    public GraphvizException(final String message) {
        super(message);
    }
    
    /**
     * Create a Graphviz exception with a message and a cause.
     * 
     * @param message a message
     * @param cause a cause
     */
    public GraphvizException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
