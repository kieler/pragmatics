/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2011 by
 * + Kiel University
 *     + Department of Computer Science
 *         + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */

package de.cau.cs.kieler.kwebs.server.util;

/**
 *
 * Some utilities for xtend2.
 * 
 * @author swe
 * 
 */
public final class Xtend2Util {
    
    //////////
    
    /**
     * Utility class so no instantiation is required. 
     */
    private Xtend2Util() {
    }

    //////////

    /**
     * This is a workaround for the bug that in xtend2 it is not possible to instantiate
     * arrays of some kind.
     * 
     * @param size
     *           size the size of the byte array
     * @return a byte array of the specified size
     */
    public static byte[] getByteArray(
        final int size
    ) {
        return new byte[size];
    }
    
}
