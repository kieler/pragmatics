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
package de.cau.cs.kieler.klay.tree.graph;

import de.cau.cs.kieler.core.properties.MapPropertyHolder;

/**
 * @author sor
 * @author sgu
 *
 */
public abstract class TGraphElement extends MapPropertyHolder implements Comparable<TGraphElement> {

    /** the serial version UID. */
    private static final long serialVersionUID = 1L;
    
    // CHECKSTYLEOFF VisibilityModifier

    /** Identifier value, may be arbitrarily used by algorithms. */
    public int id;
    
    // CHECKSTYLEON VisibilityModifier
    
    /** The parent graph this element belongs to. */
    private TGraph parent;

    /**
     * Create a graph element.
     * @param p 
     * @param id
     * 
     */
    public TGraphElement(int id, TGraph p) {
        this.id = id;
        this.parent = p;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean equals(final Object object) {
//        if (object instanceof TGraphElement) {
//            TGraphElement other = (TGraphElement) object;
//            return this.hashCode == other.hashCode;
//        }
        return false;
    }
    
    
    /**
     * TODO find implementation and use case
     * 
     * {@inheritDoc}
     */
    public final int compareTo(final TGraphElement other) {
        return this.hashCode() - other.hashCode();
    }
    
}
