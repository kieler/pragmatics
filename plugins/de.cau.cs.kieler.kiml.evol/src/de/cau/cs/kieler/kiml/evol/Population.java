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
package de.cau.cs.kieler.kiml.evol;

import java.util.ArrayList;
import java.util.List;

/**
 * A population is a list of individuals.
 * 
 * @author bdu
 * 
 */
public class Population extends ArrayList<Individual> {
    /**
     * 
     */
    private static final long serialVersionUID = -5511104369758838181L;
    
    /**
     * Constructs an empty population.
     */
    public Population() {
        super();
    }
    
    /**
     * Constructs a new Population instance using the given list of individuals.
     * 
     * @param individuals
     *            List of individuals
     */
    public Population(final List<Individual> individuals) {
        this.addAll(individuals);
    }
    
    /**
     * Randomly chooses one of the individuals in the list.
     * 
     * @return an individual that is in the list, or {@code null}, if the list
     *         is empty.
     */
    public Individual pick() {
        Individual result = null;
        if (size() > 0) {
            int pos = (int) (Math.random() * size());
            result = get(pos);    
        }        
        return result;
    }
    
    @Override
    public String toString() {
        final String newLine = System.getProperty("line.separator");
        return super.toString() + newLine + " (" + size() + " individuals)";
    }
}
