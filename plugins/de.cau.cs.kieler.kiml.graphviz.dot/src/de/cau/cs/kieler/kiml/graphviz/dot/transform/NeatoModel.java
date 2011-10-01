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
package de.cau.cs.kieler.kiml.graphviz.dot.transform;

/**
 * Definition of Neato distance computation models.
 *
 * @author msp
 */
public enum NeatoModel {
    
    /** Use length of the shortest path. */
    SHORTPATH,
    /** Use the circuit resistance model. */
    CIRCUIT,
    /** Use the subset model. */
    SUBSET;
    
    /**
     * Parse the given string into a Neato model, ignoring case.
     * 
     * @param string a string
     * @return the corresponding model
     */
    public static NeatoModel parse(final String string) {
        return valueOf(string.toUpperCase());
    }
    
    /**
     * Return the literal value as understood by Graphviz.
     * 
     * @return the literal value
     */
    public String literal() {
        return super.toString().toLowerCase();
    }
    
}
