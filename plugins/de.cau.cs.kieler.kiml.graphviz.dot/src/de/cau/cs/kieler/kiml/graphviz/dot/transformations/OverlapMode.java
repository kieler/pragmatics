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
package de.cau.cs.kieler.kiml.graphviz.dot.transformations;

/**
 * Definition of overlap removal modes.
 *
 * @author msp
 */
public enum OverlapMode {
    
    /** overlaps are retained ("true" in the Graphviz options). */
    NONE,
    /** overlaps are removed by uniformly scaling in x and y. */
    SCALE,
    /** x and y are separately scaled to remove overlaps. */
    SCALEXY,
    /** Prism, a proximity graph-based algorithm, is used to remove node overlaps. */
    PRISM,
    /** the layout will be scaled down as much as possible without introducing any overlaps. */
    COMPRESS;
    
    /**
     * Parse the given string into an overlap mode, ignoring case.
     * 
     * @param string a string
     * @return the corresponding mode
     */
    public static OverlapMode parse(final String string) {
        if (string.toLowerCase().equals("true")) {
            return NONE;
        }
        return valueOf(string.toUpperCase());
    }
    
    /**
     * Return the literal value as understood by Graphviz.
     * 
     * @return the literal value
     */
    public String literal() {
        if (this == NONE) {
            return "true";
        }
        return super.toString().toLowerCase();
    }

}
