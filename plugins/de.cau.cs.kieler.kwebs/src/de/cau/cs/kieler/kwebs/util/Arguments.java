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

import java.util.HashMap;

/**
 * Utilities for parsing command line arguments.
 * 
 * @kieler.rating  2011-05-04 red
 * @author  swe
 */
public final class Arguments {

    /**
     * Parses the command line arguments and stores the key/value
     * pairs in a {@code HashMap}.
     *
     * @param args
     *            Array of type {@code String} containing
     *            the command line arguments
     * @return a {@code HashMap<String, String>} containing key/value pairs generated
     *         from the command line arguments
     */
    public static HashMap<String, String> parseArgs(final String[] args) {
        HashMap<String, String> result = new HashMap<String, String>();
        int index = 0;
        String key = null;
        String value = null;
        for (String arg : args) {
            if (arg.charAt(0) == '/' || arg.charAt(0) == '-') {
                arg = arg.substring(1);
            }
            value = null;
            index = arg.indexOf("=");
            if (index > -1) {
                key = arg.substring(0, index);
                value = arg.substring(index + 1);
                if (value.startsWith("\"")
                    && value.endsWith("\"")) {
                    value = value.substring(1, value.length() - 1).trim();
                }
                if (value.startsWith("'")
                    && value.endsWith("'")) {
                    value = value.substring(1, value.length() - 1).trim();
                }
            }
            if (key.length() > 0 && value != null) {
                result.put(key, value);
            }
        }
        return result;
    }

    /**
     * Private Constructor. Utility class must not
     * be instantiated.
     */
    private Arguments() {
    }

}
