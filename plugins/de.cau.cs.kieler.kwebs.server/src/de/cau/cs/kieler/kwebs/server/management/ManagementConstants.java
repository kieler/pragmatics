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

import java.util.Arrays;
import java.util.List;

/**
 * Management command constants used to create command instance from command line arguments.
 *
 * @kieler.rating  2011-05-04 red
 * 
 * @author  swe
 * 
 */
public final class ManagementConstants {

	//////////
	
    /** The command for testing if the server is running. */
    public static final String COMMAND_ALIVE
        = "alive";

    /** The command for getting the published state. */
    public static final String COMMAND_PUBLISHED
        = "published";

    /** The command for publishing the services. */
    public static final String COMMAND_PUBLISH
        = "publish";

    /** The command for unpublishing the services. */
    public static final String COMMAND_UNPUBLISH
        = "unpublish";

    /** The command for shutting down the server. */
    public static final String COMMAND_SHUTDOWN
        = "shutdown";
    
    /** */
    public static final List<String> VALID_COMMANDS = Arrays.<String>asList(
    	COMMAND_ALIVE, COMMAND_PUBLISHED, COMMAND_PUBLISH, COMMAND_UNPUBLISH, COMMAND_SHUTDOWN
    );
    
	//////////
    
    /** Common parameter identifier prefix. */
    private static final String PREFIX
    	= "de.cau.cs.kieler.kwebs.management.parameter.";
    
    /** Parameter for the published state of the HTTP service. */
    public static final String HTTP_PUBLISHED
        = PREFIX + "http.published";

    /** Parameter for the published state of the HTTPS service. */
    public static final String HTTPS_PUBLISHED
        = PREFIX + "https.published";

    /** Parameter for the published state of the jETI service. */
    public static final String JETI_PUBLISHED
        = PREFIX + "jeti.published";

	//////////
	
    /**
     * Private constructor since this is a utility class.
     */
    private ManagementConstants() {        
    }
    
}
