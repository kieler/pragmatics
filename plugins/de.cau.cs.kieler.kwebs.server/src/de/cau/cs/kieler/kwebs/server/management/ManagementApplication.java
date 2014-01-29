/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *     + Department of Computer Science
 *         + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */

package de.cau.cs.kieler.kwebs.server.management;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;
import java.util.StringTokenizer;

import de.cau.cs.kieler.kwebs.server.management.command.client.IManagementExchange;
import de.cau.cs.kieler.kwebs.server.management.command.client.ManagementExchange;

/**
 * Main layout server management console application.
 *
 * @kieler.rating  2011-05-04 red
 *
 * @author  swe
 *
 */
public final class ManagementApplication {

    //////////

    /** */
    private static final String DEFAULT_TRUSTSTORE_LOCATION
        = "mclient.jks";

    //////////

    /** */
    private static ManagementApplicationHelper helper;

    //////////

    /**
     * Private constructor.
     */
    private ManagementApplication() {
    }

    /**
     * The main application entry point.
     *
     * @param args
     *            command line arguments
     */
    public static void main(final String[] args) {

        ManagementApplicationHelper.intro();

        helper = new ManagementApplicationHelper(args);

        // Help command line argument overrides any other arguments like commands, etc.
        if (helper.hasParam(ManagementApplicationHelper.HELP)) {
            ManagementApplicationHelper.help();
            return;
        }

        if (!helper.hasParam(ManagementApplicationHelper.PORT)) {
            helper.setParam(
                ManagementApplicationHelper.PORT,
                Integer.toString(ManagementService.DEFAULT_MANAGEMENTPORT)
            );
        }
        if (!helper.hasParam(ManagementApplicationHelper.KEYSTORE_LOCATION)) {
            helper.setParam(
                ManagementApplicationHelper.KEYSTORE_LOCATION,
                DEFAULT_TRUSTSTORE_LOCATION
            );
        }
        if (!helper.hasParam(ManagementApplicationHelper.COMMAND)) {
            ManagementApplicationHelper.error(
                new String[] {
                    "ERROR: You did not specify any command to be executed."
                },
                1
            );
        }
        final String command = helper.getParam(ManagementApplicationHelper.COMMAND);
        if (!ManagementConstants.VALID_COMMANDS.contains(command.toLowerCase())) {
            ManagementApplicationHelper.error(
                new String[] {
                    "ERROR: The command you specified ('" + command + "') is not valid."
                },
                //CHECKSTYLEOFF MagicNumber
                3
                //CHECKSTYLEON MagicNumber
            );
        }

        ManagementClient client = null;

        try {

            IManagementExchange exchange = new ManagementExchange();

            exchange.setCommand(command);

            for (Entry<String, String> parameter : helper.getCommandParameters()) {
                exchange.setParameter(parameter.getKey(), parameter.getValue());
            }

            client = new ManagementClient();

            client.setPort(Integer.parseInt(helper.getParam(ManagementApplicationHelper.PORT)));
            client.setTruststoreLocation(helper.getParam(ManagementApplicationHelper.KEYSTORE_LOCATION));
            client.setTruststorePassword(helper.getParam(ManagementApplicationHelper.KEYSTORE_PASSWORD));

            exchange = client.execute(exchange);
            Serializable response = exchange.getResponse();

            if (response instanceof Throwable) {
                Throwable throwable = ((Throwable) response);
                ManagementApplicationHelper.error(
                    getMessage(
                        new String[] {
                            "A server side exception occurred while executing the command:",
                            "    " + throwable.getMessage()
                        },
                        throwable
                    ),
                    2
                );
            }

            System.out.println(response);

        } catch (Exception e) {
            ManagementApplicationHelper.error(
                getMessage(
                    new String[] {
                        "A communication error occurred: " + e.getMessage()
                    },
                    e
                ),
                //CHECKSTYLEOFF MagicNumber
                4
                //CHECKSTYLEON MagicNumber
            );
            return;
        }

    }

    //////////

    /**
     *
     * @param message
     * @param throwable
     * @return
     */
    private static String[] getMessage(final String[] message, final Throwable throwable) {
        List<String> lines = new ArrayList<String>(Arrays.<String>asList(message));
        if (helper.hasParam(ManagementApplicationHelper.VERBOSE)) {
            lines.add("Full stack trace is:");
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            PrintWriter           prn = new PrintWriter(out);
            throwable.printStackTrace(prn);
            prn.flush();
            prn.close();
            StringTokenizer       st  = new StringTokenizer(out.toString(), "\r\n");
            while (st.hasMoreTokens()) {
                lines.add(st.nextToken());
            }
        }
        return lines.toArray(new String[0]);
    }

}
