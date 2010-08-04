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
package de.cau.cs.kieler.klay.layered;

/**
 * Definition of node layering styles for the layered layouter.
 * 
 * @author pdo
 */
public enum LayeredNodeLayering {

    /** All nodes will be layered according to the longest path. */
    LONGEST_PATH,
    /** All nodes will be layered with minimal edge length. */
    NETWORK_SIMPLEX;

    /**
     * Returns the enumeration value related to the given ordinal.
     * 
     * @param i
     *            ordinal value
     * @return the related enumeration value
     */
    public static LayeredNodeLayering valueOf(final int i) {
        return values()[i];
    }

}
