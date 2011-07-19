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

import java.net.URI;

/**
 * Utilities for testing user defined uris for correctness.
 *
 * @kieler.rating  2011-05-04 red
 * @author  swe
 */
public final class Uris {

    /**
     * Private Constructor. Util class must not
     * be instantiated.
     */
    private Uris() {
    }

    /**
     * Tests an user given uri for correctness.
     * 
     * @param uri 
     *            the uri to be tested
     * @return true if valid, false otherwise
     */
    public static boolean isValidURI(final String uri) {
        String tmp = uri;
        if (tmp != null && tmp.length() > 0) {
            tmp = tmp.trim();
            if (!tmp.toLowerCase().startsWith("http:")
                && !tmp.toLowerCase().startsWith("https:")
                && !tmp.toLowerCase().startsWith("eti:")) {
                return false;
            }
            try {
                new URI(tmp);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    /**
     * Tests an user given uri for being jETI.
     * 
     * @param uri 
     *            the uri to be tested
     * @return true if valid, false otherwise
     */
    public static boolean isJetiURI(final String uri) {
        String tmp = uri;
        return (isValidURI(tmp) && tmp.trim().toLowerCase().startsWith("eti:"));
    }

    /**
     * Tests an user given uri for being HTTP.
     * 
     * @param uri 
     *            the uri to be tested
     * @return true if valid, false otherwise
     */
    public static boolean isHttpURI(final String uri) {
        String tmp = uri;
        return (isValidURI(tmp) && tmp.trim().toLowerCase().startsWith("http:"));
    }

    /**
     * Tests an user given uri for being HTTPS.
     * @param uri the uri to be tested
     * @return true if valid, false otherwise
     */
    public static boolean isHttpsURI(final String uri) {
        String tmp = uri;
        return (isValidURI(tmp) && tmp.trim().toLowerCase().startsWith("https:"));
    }

    /**
     * Returns the host from an uri.
     * 
     * @param uri
     *            the uri
     * @return the host
     */
    public static String getHost(final String uri) {

        //CHECKSTYLEOFF EmptyBlock

        if (isValidURI(uri)) {
            try {
                return new URI(uri).getHost();
            } catch (Exception e) {
            }
        }

        //CHECKSTYLEON EmptyBlock

        return null;
    }

    /**
     * Returns the path from an uri.
     * 
     * @param uri
     *            the uri
     * @return the path
     */
    public static String getPath(final String uri) {
        //CHECKSTYLEOFF EmptyBlock
        if (isValidURI(uri)) {
            try {
                return new URI(uri).getPath();
            } catch (Exception e) {
            }
        }
        //CHECKSTYLEON EmptyBlock
        return null;
    }
    
    /**
     * Returns the port from an uri.
     * 
     * @param uri
     *            the uri
     * @return the port
     */
    public static int getPort(final String uri) {
        //CHECKSTYLEOFF EmptyBlock
        if (isValidURI(uri)) {
            try {
                return new URI(uri).getPort();
            } catch (Exception e) {
            }
        }
        //CHECKSTYLEON EmptyBlock
        return -1;
    }

}
