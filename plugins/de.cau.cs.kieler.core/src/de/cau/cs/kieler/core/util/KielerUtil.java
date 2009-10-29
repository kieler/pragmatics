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
package de.cau.cs.kieler.core.util;

import java.util.Map;
import java.util.Map.Entry;

/**
 * Static class with utility methods.
 * 
 * @author <a href="mailto:haf@informatik.uni-kiel.de">Hauke Fuhrmann</a>
 */
public final class KielerUtil {

    /**
     * Hidden default constructor to avoid instantiation.
     */
    private KielerUtil() {
    }
    
    /**
     * Prints the contents of the given map.
     * 
     * @param map the map to be printed
     * @return a string representation of the map
     */
    public static String toString(final Map<?, ?> map) {
        StringBuffer buffer = new StringBuffer();

        for (Entry<?, ?> entry : map.entrySet()) {
            Object key = entry.getKey();
            if (key == null) {
                buffer.append("null ");
            } else {
                buffer.append(key + " (" + key.hashCode() + ") ");
            }
            buffer.append(" = " + entry.getValue() + "\n");
        }

        return buffer.toString();
    }

}
