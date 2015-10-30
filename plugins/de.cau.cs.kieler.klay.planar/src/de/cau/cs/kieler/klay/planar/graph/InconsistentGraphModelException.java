/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.planar.graph;

/**
 * A exception for the graph model in the planarization plug-in. This exception is thrown whenever
 * the common graph data structure detects inconsistencies in the graph model.
 * 
 * @see de.cau.cs.kieler.klay.planar.graph.IGraph {@code IGraph}
 * 
 * @author ocl
 * @kieler.rating proposed yellow by pkl
 */
public class InconsistentGraphModelException extends RuntimeException {

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
