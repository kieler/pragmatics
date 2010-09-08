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
package de.cau.cs.kieler.kivi.core;

/**
 * A parameter passed to the view management through the combination extension point.
 * 
 * TODO better typing?
 * 
 * @author mmu
 * 
 */
public class CombinationParameter {
    
    private String name;
    
    private String defaultValue;
    
    private String type;
    
    /**
     * Create a new combinations parameter.
     * 
     * @param n the name of the parameter
     * @param t the type of the parameter
     * @param d its default value
     */
    public CombinationParameter(final String n, final String t, final String d) {
        name = n;
        type = t;
        defaultValue = d;
    }

    /**
     * Get the name.
     * 
     * @return the name
     */
    public String getName() {
        return name;
    }

    /** 
     * Get the default value.
     * 
     * @return the default value
     */
    public String getDefaultValue() {
        return defaultValue;
    }
    
    /**
     * Get the type of the parameter.
     * 
     * @return the type
     */
    public String getType() {
        return type;
    }

}
