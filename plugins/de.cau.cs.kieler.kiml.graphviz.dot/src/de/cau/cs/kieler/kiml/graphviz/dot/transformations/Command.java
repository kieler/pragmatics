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
 * Definition of available Graphviz commands.
 *
 * @author msp
 */
public enum Command {
    
    /** invalid command. */
    INVALID,
    /** command for Dot layout. */
    DOT,
    /** command for Neato layout. */
    NEATO,
    /** command for Twopi layout. */
    TWOPI,
    /** command for Fdp layout. */
    FDP,
    /** command for Circo layout. */
    CIRCO;

    /**
     * Parse the given string into a command, ignoring case.
     * 
     * @param string a string
     * @return the corresponding command, or {@code INVALID} if the string
     *     does not hold a valid command
     */
    public static Command parse(final String string) {
        try {
            return valueOf(string.toUpperCase());
        } catch (IllegalArgumentException exception) {
            return INVALID;
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
    
}
