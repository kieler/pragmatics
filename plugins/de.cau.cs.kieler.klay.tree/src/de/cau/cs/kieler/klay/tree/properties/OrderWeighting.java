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
package de.cau.cs.kieler.klay.tree.properties;

/**
 * Layout option for the choice of the weighting for the node order.
 *
 * @author sor
 * @author sgu
 */
public enum OrderWeighting {
    
    /** Chooses the number of descendants for the weighting. */
    DESCENDANTS,
    /** Chooses maximal number of descendants at the same level. */
    FAN;
    
}
