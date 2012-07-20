/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.options;

/**
 * Definition of size constraints.
 *
 * @kieler.rating proposed yellow 2012-01-17 msp
 * @author msp
 */
public enum SizeConstraint {
    
    /**
     * The size shall not be changed (except if the node has a nested subgraph).
     */
    FIXED,
    /**
     * Minimize the size, but consider ports in calculation. The sum of port widths and heights
     * is a lower bound for the node size, and the default minimal size and corresponding layout
     * options are also considered (see {@link #MIN_DEFAULT}).
     */
    MIN_PORTS,
    /**
     * Minimize the size to a default value. The minimal value given by layout options
     * (see {@link #MIN_OPTION}) are also considered.
     */
    MIN_DEFAULT,
    /**
     * Minimize the size to the value given by the layout options {@link LayoutOptions#MIN_WIDTH}
     * and {@link LayoutOptions#MIN_HEIGHT}. These values are usually determined automatically
     * and depend on the specific diagram viewer.
     */
    MIN_OPTION;
    
    /**
     * Returns the enumeration value related to the given ordinal.
     * 
     * @param i ordinal value
     * @return the related enumeration value
     */
    public static SizeConstraint valueOf(final int i) {
        return values()[i];
    }
    
    /**
     * Whether the node size shall be minimized and ports are considered when calculating
     * the minimal size.
     * 
     * @return true if ports are considered
     */
    public boolean arePortsConsidered() {
        return this == MIN_PORTS;
    }
    
    /**
     * Whether the node size shall be minimized and the default minimal size value is considered
     * when calculating the minimal size.
     * 
     * @return true if the default minimal size is considered
     */
    public boolean isDefSizeConsidered() {
        return this == MIN_PORTS || this == MIN_DEFAULT;
    }

}
