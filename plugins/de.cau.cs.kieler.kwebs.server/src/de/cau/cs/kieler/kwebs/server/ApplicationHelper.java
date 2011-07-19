/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2008 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */

package de.cau.cs.kieler.kwebs.server;

/**
 * This class provides support for the console application for running tests on the
 * KIELER web service.
 *
 * @kieler.rating 2011-05-20 red
 * @author swe
 */
public final class ApplicationHelper {

    /** */
    public static final String[] TITLE_TEXT = new String[]
    {
        "KWebS - KIELER Layout Web Service (v$VERSION$)\n",
        "",
        "Published by Christian-Albrechts-Universität zu Kiel",
        "Published under the EPL v1.0 (see http://www.eclipse.org/legal/epl-v10.html)"
    };

    /** */
    public static final String[] HELP_TEXT = new String[]
    {
        "Command line arguments are:",
        ""
    };

    /** 
     *  Files to be read from plugin and stored in configuration folder identified
     *  by their property ids form the default config.
     */
    public static final String[] CONFIGURATION_FILES = new String[]
    {
        "de.cau.cs.kieler.kwebs.httpsKeystore.jks.path",
        "de.cau.cs.kieler.kwebs.jeti.toolxml",
        "de.cau.cs.kieler.kwebs.jeti.log4j.config"
    };
    
    /**
     * Converts a given string array in console displayable form.
     *  
     * @param arr
     *            the string array to be converted in console displayable form
     * @return a string to display
     */
    public static String toDisplayable(final String[] arr) {
        String text = "";
        if (arr != null && arr.length > 0) {
            for (String line : arr) {
                text += line + "\n";
            }
        }
        return text;
    }

    /**
     * Private constructor for utility class.
     */
    private ApplicationHelper() {
    }

}
