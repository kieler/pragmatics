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
package de.cau.cs.kieler.core.kivi;

/**
 * A container for extension point descriptions. Contains class, name, description attributes.
 * 
 * @author mmu
 */
public class Descriptor {

    private String name;

    private String description;

    private Class<?> clazz;

    private boolean active = false;

    /**
     * Create a new descriptor.
     * 
     * @param n
     *            the name
     * @param d
     *            the description
     * @param c
     *            the class
     */
    public Descriptor(final String n, final String d, final Class<?> c) {
        name = n;
        description = d;
        clazz = c;
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
     * Get the description.
     * 
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Get the class.
     * 
     * @return the class
     */
    public Class<?> getClazz() {
        return clazz;
    }

    /**
     * Set the element described as active or inactive.
     * 
     * @param a
     *            true if activating
     */
    public void setActive(final boolean a) {
        active = a;
    }

    /**
     * Get the active state of the element described.
     * 
     * @return true if activated
     */
    public boolean isActive() {
        return active;
    }
    
}
