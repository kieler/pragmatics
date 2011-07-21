/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2008 by
 * + Christian-Albrechts-University of Kiel
 *     + Department of Computer Science
 *         + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */

package de.cau.cs.kieler.kwebs.util;

import java.util.Map;

/**
 * Debugging utilities, mostly for displaying objects.
 * 
 * @kieler.rating  2011-05-04 red
 * @author  swe
 */
public final class Debug {

    /**
     * Displays contents of a map.
     * 
     * @param map
     *            the map from which the key/value pairs are to be displayed
     */
    public static void printMap(final Map<?, ?> map) {
        if (map != null) {
            StackTraceElement element = Thread.currentThread().getStackTrace()[2];
            String caller = element.getClassName() 
                            + ":" + element.getMethodName()
                            + ":" + Integer.toString(element.getLineNumber());
            String clas = map.getClass().getCanonicalName();            
            String content = map.toString();
            content = "   " + content.substring(1);
            content = content.substring(0, content.length() - 1);
            content = content.replace(",", "\n  ");
            System.out.println(
                "CALLER: " + caller + "\nCLASS OF MAP: " + clas + "\nCONTENT OF MAP:\n" + content + "\n"
            );
        }
    }

    /**
     * Private Constructor. Utility class must not
     * be instantiated.
     */
    private Debug() {
    }

}
