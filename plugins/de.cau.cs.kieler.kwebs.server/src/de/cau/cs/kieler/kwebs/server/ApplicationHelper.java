/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2011 by
 * + Kiel University
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
 * @author swe
 */
public final class ApplicationHelper {

    /** */
    public static final String[] TITLE_TEXT = new String[]
    {
        "KWebS - KIELER Layout Web Service (v$VERSION$)\n",
        "",
        "Copyright 2014 by Real-Time and Embedded Systems Group, Department",
        "of Computer Science, Kiel University",
        "Published under the EPL v1.0 (see http://www.eclipse.org/legal/epl-v10.html)",
        "",
    };

    /** */
    public static final String[] HELP_TEXT = new String[]
    {
        "Command line arguments are:",
        ""
    };

    /** 
     *  Files to be read from plugin and stored in configuration folder.
     */
    public static final String[] CONFIGURATION_FILES = new String[]
    {
        "server/cmd/GenerateCertsAndKeystores.cmd",
        "server/cmd/GenerateCertsAndKeystores.sh",
        "server/cmd/ca.conf",
        "server/cmd/csr.conf",
        "server/cmd/sign.conf",
        "server/java/config/logging/logging.properties",
        "server/kwebs/security/keystores/server.jks",
        "server/kwebs/security/keystores/mserver.jks",
        "server/kwebs/config/kwebs.properties",
        "server/kwebs/web/security/client.jks",
        "server/kwebs/wsdl/layoutService.wsdl",
        "kwebs.user",
        "kwebs_start.sh",
        "kwebs_stop.sh",
        "kwebs_backup.sh",
        "kwebs_restore.sh",
        "kwebs_start.cmd",
        "kwebs_stop.cmd",
        "mclient.jks"
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
