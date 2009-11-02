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
package de.cau.cs.kieler.kiml.layout.options;

/**
 * Definition of port constraints.
 * 
 * @author <a href="mailto:msp@informatik.uni-kiel.de">Miro Sp&ouml;nemann</a>
 */
public enum PortConstraints {

    /** undefined constraints. */
    UNDEFINED,
    /** all ports are free. */
    FREE_PORTS,
    /** the side is fixed for each port. */
    FIXED_SIDE,
    /** the side is fixed for each port, and the order of ports
     * is fixed for each side.
     */
    FIXED_ORDER,
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
    
}
