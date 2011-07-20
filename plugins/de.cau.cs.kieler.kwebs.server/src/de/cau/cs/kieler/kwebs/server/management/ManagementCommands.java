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
 * Management commands the server.
 *
 * @kieler.rating  2011-05-04 red
 * @author  swe
 */
public final class ManagementCommands {


    /** The command for testing if the server is running. */
    public static final String COMMAND_ALIVE
        = "ALIVE";

    /** The command for getting the published state. */
    public static final String COMMAND_PUBLISHED
        = "PUBLISHED";

    /** The command for publishing the services. */
    public static final String COMMAND_PUBLISH
        = "PUBLISH";

    /** The command for unpublishing the services. */
    public static final String COMMAND_UNPUBLISH
        = "UNPUBLISH";

    /** The command for restarting the server. */
    public static final String COMMAND_RESTART
        = "RESTART";

    /** The command for shutting down the server. */
    public static final String COMMAND_SHUTDOWN
        = "SHUTDOWN";

    // One of the two following responses always terminates the response to a request
    // for the execution of a command.
    
    /** 
     *  The management servers response if a requested command was executed successfully. 
     */
    public static final String RESPONSE_ACK
        = "ACK";

    /** 
     *  The management servers response if a requested command could not be executed.
     */
    public static final String RESPONSE_NACK
        = "NACK";
        
    /**
     * Private constructor since this is a utility class.
     */
    private ManagementCommands() {        
    }
    
}
