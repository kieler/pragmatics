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
 * Enumeration of available ranking modules for Sugiyama layout.
 * 
 * @author msp
 * @kieler.design proposed by msp
 * @kieler.rating proposed yellow by msp
 */
public enum RankingModule {
    
    /** the longest path ranking module. */
    LONGEST_PATH,
    /** the Coffman-Graham ranking module. */
    COFFMAN_GRAHAM,
    /** the optimal ranking module. */
    OPTIMAL;

}
