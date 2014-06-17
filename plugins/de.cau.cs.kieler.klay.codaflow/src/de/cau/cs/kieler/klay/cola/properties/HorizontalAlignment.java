/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2014 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.cola.properties;

/**
 * @author uru
 */
public enum HorizontalAlignment {

    /**
     * Minimal spacings are calculated without consideration of other nodes that are connected to
     * the same origin.
     */
    NONE,
    /** Nodes are aligned wrt to their left border. */
    LEFT,
    /** Nodes are aligned wrt to their center. */
    CENTER,
    /** Nodes are aligned wrt to their right border. */
    RIGHT
    
}
