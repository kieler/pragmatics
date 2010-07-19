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
package de.cau.cs.rtprak.planarization.graph;

/**
 * A exception for the graph model in the planarization plug-in. This exception is thrown whenever
 * the common graph model attempts to work with graph elements that are not actually part of the
 * graph.
 * 
 * @see de.cau.cs.rtprak.planarization.graph.IGraph {@code IGraph}
 * 
 * @author ocl
 */
public class InconsistentGraphModelException extends Exception {

    /**
     * Generated UID for serialization.
     */
    private static final long serialVersionUID = 831928613895541892L;

    // ====================== Constructor =====================================

    /**
     * Default Constructor.
     */
    public InconsistentGraphModelException() {
        super();
    }

    /**
     * Constructor that takes a message for the exception.
     * 
     * @param msg
     *            the exception message
     */
    public InconsistentGraphModelException(final String msg) {
        super(msg);
    }

}
