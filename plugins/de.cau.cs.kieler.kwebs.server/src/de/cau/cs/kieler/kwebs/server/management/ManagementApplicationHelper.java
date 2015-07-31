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

package de.cau.cs.kieler.kwebs.server.management;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Helper for layout server management console application.
 *
 * @author swe
 *
 */
public final class ManagementApplicationHelper {

    //////////

    /** Display a help message. */
    public static final String[] HELP
        = { "help", "-h" };

    /** */
    public static final String[] COMMAND
        = { "command", "-c" };

    /** */
    public static final String[] PORT
        = { "port", "-pt" };

    /** */
    public static final String[] VERBOSE
        = { "verbose", "-v" };

    /** */
    public static final String[] KEYSTORE_LOCATION
        = { "keystore", "-kl" };

    /** */
    public static final String[] KEYSTORE_PASSWORD
        = { "password", "-kp" };

    //////////

    /** */
    private static final String[][] KNOWN_PARAMS = {
        HELP, COMMAND, PORT, VERBOSE, KEYSTORE_LOCATION, KEYSTORE_PASSWORD
    };

    /** The introductory help text. */
    private static final String[] HELP_INTRO = new String[] {
            "Management Client for the KIELER Layout Web Service (KWebS)",
            "",
            "Copyright 2012 by Real-Time and Embedded Systems Group,",
            "Department of Computer Science, Kiel University",
            "",
            "The management client is used to control the server from the command",
            "line. You can only use it from the server itself, meaning the management",
            "service the client communicates with only accepts connections from",
            "localhost. The connection itself is secured with TLS so you need a valid",
            "certificate and password for client authentication. How you can obtain",
            "these required artifacts is subject to the provider of the layout service.",
            ""
        };

    /** The usage help text. */
    private static final String[] HELP_USAGE = new String[] {
        "Available arguments:",
        "    " + HELP[0] + " or " + HELP[1],
        "        Display supported arguments.",
        "    " + COMMAND[0] + "=<cmd> or " + COMMAND[1] + "=<cmd>",
        "        Execute the given command where <cmd> is one of",
        "            alive:     Check whether the server is alive.",
        "            published: Check whether the layout service is currently published.",
        "            publish:   Publish the layout service according to the servers",
        "                       configuration.",
        "            unpublish: Unpublish the layout service.",
        "            shutdown:  Shut down the server.",
        "    " + PORT[0] + " or " + PORT[1],
        "        The port the management service listens on.",
        "    " + VERBOSE[0] + " or " + VERBOSE[1],
        "        Be verbose if an error occurrs, e.g. print full stack trace on",
        "        exceptions.",
        "    " + KEYSTORE_LOCATION[0] + "=<path> or " + KEYSTORE_LOCATION[1] + "=<path>",
        "        Use the keystore located at the given location.",
        "    " + KEYSTORE_PASSWORD[0] + "=<string> or " + KEYSTORE_PASSWORD[1] + "=<string>",
        "        The password for the keystore.",
        "",
        "All command line arguments that begin with '--' are supposed to be parameters",
        " that belong to the command you execute. For example, the argument",
        "--param=value sets the parameter with name 'param' to the value 'value'.",
        "Whether this parameter is recognized by the server is not verified, in general",
        "the server ignores unknown command parameters."
    };

    //////////

    /** map of tool parameters read from the command line. */
    private final Map<String, String> toolParameters
        = new HashMap<String, String>();

    /** map of command parameters read from the command line. */
    private final Map<String, String> commandParameters
        = new HashMap<String, String>();

    /** */
    private boolean invalidArgumentOccured
        = false;

    //////////

    /**
     * Parse the command line arguments and stores the key/value pairs for later use.
     *
     * @param arguments Array of type {@code String} containing the command line arguments
     */
    public ManagementApplicationHelper(final String[] arguments) {
        for (String argument : arguments) {
            Map<String, String> argMap     = null;
            String              key;
            String              value;
            int                 keyIndex   = 0;
            int                 valueIndex = 0;
            if (argument.startsWith("--")) {
                argMap = commandParameters;
                keyIndex = 2;
            } else {
                argMap = toolParameters;
            }
            valueIndex = argument.indexOf('=');
            if (valueIndex < 0) {
                key   = argument.substring(keyIndex);
                // options with no value are interpreted as boolean flags that are set to true
                value = "true";
            } else {
                key   = argument.substring(keyIndex, valueIndex);
                value = argument.substring(valueIndex + 1);
                if (value.startsWith("\"") && value.endsWith("\"") && value.length() >= 2) {
                    value = value.substring(1, value.length() - 1).trim();
                } else if (value.startsWith("'") && value.endsWith("'") && value.length() >= 2) {
                    value = value.substring(1, value.length() - 1).trim();
                }
            }
            if (argMap.containsKey(key)) {
                System.out.println(
                    "WARNING: Multiply defined argument '" + key + "' overrides prior definition."
                );
            }
            if (argMap == toolParameters && key.length() != 0 && isknownParameter(key)
                || argMap == commandParameters
            ) {
                argMap.put(key, value);
            } else {
                invalidArgumentOccured = true;
            }
        }
    }

    //////////

    /**
     *
     * @return
     */
    public boolean getInvalidArgumentOccured() {
        return invalidArgumentOccured;
    }

    /**
     *
     * @param invalidArgumentOccured
     */
    public void setInvalidArgumentOccured(final boolean invalidArgumentOccured) {
        this.invalidArgumentOccured = invalidArgumentOccured;
    }

    //////////

    /**
     * Display intro.
     */
    public static void intro() {
        for (String line : HELP_INTRO) {
            System.out.println(line);
        }
    }

    /**
     * Display help on tool usage.
     */
    public static void help() {
        for (String line : HELP_USAGE) {
            System.out.println(line);
        }
    }

    /**
     * Display an error message and terminate program with the given exit code.
     *
     * @param message
     *            the message to be displayed
     * @param exitCode
     *            the exit code to be returned
     */
    public static void error(final String[] message, final int exitCode) {
        for (String line : message) {
            System.out.println(line);
        }
        System.exit(exitCode);
    }

    /**
     * Returns whether a given parameter is supported.
     *
     * @param parameter
     *            the parameter to test
     *
     * @return whether a given parameter is supported
     */
    public static boolean isknownParameter(final String parameter) {
        for (String[] parameterSet : KNOWN_PARAMS) {
            for (String p : parameterSet) {
                if (p.equals(parameter)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Return whether a value for the the given key set is set.
     *
     * @param keyset a parameter key set
     *
     * @return whether a value for the the given key set is set
     */
    public boolean hasParam(final String[] keyset) {
        for (String key : keyset) {
            if (toolParameters.containsKey(key)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Return the parameter value for the given key set.
     *
     * @param keyset a parameter key set
     *
     * @return the corresponding value, or the empty string if no value was specified,
     *     or {@code null} if the parameter is not present
     */
    public String getParam(final String[] keyset) {
        for (String key : keyset) {
            String value = toolParameters.get(key);
            if (value != null) {
                return value;
            }
        }
        return null;
    }

    /**
     * Set the parameter value for the given key set.
     *
     * @param keyset
     *            a parameter key set
     * @param value
     *            the value to be set for the given key set
     */
    public void setParam(final String[] keyset, final String value) {
        for (String key : keyset) {
            toolParameters.remove(key);
        }
        toolParameters.put(keyset[0], value);
    }

    /**
     *
     * @return
     */
    public Iterable<Entry<String, String>> getCommandParameters() {
        return Collections.unmodifiableSet(commandParameters.entrySet());
    }

}
