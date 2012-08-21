/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2009 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.options;

/**
 * Definition of port constraints.
 * 
 * @kieler.design 2011-03-14 reviewed by cmot, cds
 * @kieler.rating proposed yellow 2012-07-10 msp
 * @author msp
 */
public enum PortConstraints {

    /** undefined constraints. */
    UNDEFINED,
    /** all ports are free. */
    FREE,
    /** the side is fixed for each port. */
    FIXED_SIDE,
    /** the side is fixed for each port, and the order of ports is fixed for each side. */
    FIXED_ORDER,
    /**
     * the side is fixed for each port, the order or ports is fixed for each side and
     * the position of each port must preserve the ratio defined by the two segments the
     * port divides the side into.
     */
    FIXED_RATIO,
    /** the exact position is fixed for each port. */
    FIXED_POS;
    
    /**
     * Returns the enumeration value related to the given ordinal.
     * 
     * @param i ordinal value
     * @return the related enumeration value
     */
    public static PortConstraints valueOf(final int i) {
        return values()[i];
    }
    
    /**
     * Returns whether the position of the ports is fixed. Note that this is not true
     * if port ratios are fixed.
     * 
     * @return true if the position is fixed
     */
    public boolean isPosFixed() {
        return this == FIXED_POS;
    }
    
    /**
     * Returns whether the ratio of port positions is fixed. Note that this is not true
     * if the port positions are fixed.
     * 
     * @return true if the ratio is fixed
     */
    public boolean isRatioFixed() {
        return this == FIXED_RATIO;
    }
    
    /**
     * Returns whether the order of ports is fixed.
     * 
     * @return true if the order of ports is fixed
     */
    public boolean isOrderFixed() {
        return this == FIXED_ORDER || this == FIXED_RATIO || this == FIXED_POS;
    }
    
    /**
     * Returns whether the sides of ports are fixed.
     * 
     * @see PortSide
     * @return true if the port sides are fixed
     */
    public boolean isSideFixed() {
        return this != FREE && this != UNDEFINED;
    }
    
}
