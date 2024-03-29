/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.ogdf.options;

/**
 * Enumeration of available edge insertion modules for planarization layout.
 * 
 * @author msp
 */
public enum EdgeInsertionModule {
    
    /** fixed embedding edge insertion module. */
    FIXED_EMB,
    /** variable embedding edge insertion module. */
    VARIABLE_EMB,
    /** multi-edge approximation edge insertion module. */
    MULTIEDGE_APPROX;

}
