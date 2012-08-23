/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */

package de.cau.cs.kieler.kwebs.client.util;

import java.net.URI;

/**
 * Utilities for HTML.
 * 
 * @kieler.rating 2012-07-15 red
 *
 * @author swe
 */
public final class Html {

    /**
     * Private constructor to prevent instantiation of this utility class. 
     */
    private Html() {
    }
    
    /**
     * Builds anchor tags around URLs in the given String.
     * 
     * @param unescaped
     *            the string to be escaped
     * @return
     *            the string with anchor tags built around URLs
     */
    public static String escapeUrls(final String unescaped) {
        if (unescaped == null) {
            return null;
        }
        StringBuilder result    = new StringBuilder();
        StringBuilder potential = null;
        String        uri       = null;
        Integer       index     = 0;
        Integer       find      = 0;
        Integer       length    = unescaped.length();
        Character     chr       = null;
        Character     tmp       = null;
        Boolean       detected  = false;
        for (; index < length; index++) {
            chr = unescaped.charAt(index);
            // Look for sub strings that do not contain white space and contain
            // a ':' that is not positioned at the beginning of the sub string.
            if (!Character.isWhitespace(chr)) {
                detected  = false;
                potential = new StringBuilder();
                potential.append(chr);
                for (find = index + 1; find < length; find++) {
                    tmp       = unescaped.charAt(find);
                    if (Character.isWhitespace(tmp)) {
                        break;
                    }
                    detected |= tmp == ':';
                    potential.append(tmp);
                }
                uri = potential.toString();
                // Test whether an URI can be derived from the detected candidate
                try {
                    URI.create(uri);
                } catch (Exception e) {
                    detected = false;
                }
                if (detected) {
                    result.append("<a href='" + uri + "'>" + uri + "</a>");
                } else {
                    result.append(uri);
                }
                if (Character.isWhitespace(tmp)) {
                    result.append(tmp);
                }
                index = find;
            } else {
                result.append(chr);
            }
        }
        return result.toString();
    }
    
}
