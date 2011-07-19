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

package de.cau.cs.kieler.kwebs.transformation;

/**
 * .
 *
 * @kieler.rating  2011-05-04 red
 * @author  swe
 */
public class TransformationException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 6890836655529871596L;

    /**
     * Default constructor.
     */
    public TransformationException() {
        super();
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
    
}
