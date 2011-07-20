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

package de.cau.cs.kieler.kwebs.server.management;

/**
 * Main layout server management console application.
 *
 * @kieler.rating  2011-05-04 red
 * @author  swe
 */
public final class ManagementApplication {

    /**
     * Private constructor.
     */
    private ManagementApplication() {        
    }
    
    /** The port used to connect to the management service. */
    private static int port
        = ManagementService.DEFAULT_MANAGEMENTPORT;
    
    /**
     * The main application entry point.
     * 
     * @param args
     *            command line arguments
     */
    public static void main(final String[] args) {
        System.out.println("KWebS Management application\n");
        if (args.length == 0) {
            System.out.println("ERROR: No command specified");
            return;
        }
        ManagementClient client = null;
        try {
            client = new ManagementClient(port);
            if (args[0].equalsIgnoreCase(ManagementCommands.COMMAND_ALIVE)) {
                System.out.println("Server is alive: " + client.alive());
            } else if (args[0].equalsIgnoreCase(ManagementCommands.COMMAND_SHUTDOWN)) {
                System.out.println("Server is shutting down: " + client.shutdown());
            }
        } catch (Exception e) {
            System.out.println("ERROR: Communication error");
            return;
        }
        
    }
        
}
