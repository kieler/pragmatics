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
package de.cau.cs.kieler.kiml.evol.genetic;

import java.util.ArrayList;

/**
 * A population has a list of individuals (genomes).
 *
 * @author msp
 */
public class Population extends ArrayList<Genome> {

    /** The serial version UID. */
    private static final long serialVersionUID = -8591635951271498010L;

    /**
     * Constructs an empty population.
     * 
     * @param size the initial size
     */
    public Population(final int size) {
        super(size);
    }
    
}
