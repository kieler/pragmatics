/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.tree.properties;

/**
 * Enumeration of treeifying order options.
 *
 * @author msp
 */
public enum TreeifyingOrder {
    
    /** treeify in depth-first order. */
    DFS,
    /** treeify in breadth-first order. */
    BFS;

}
