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

import java.util.HashMap;
import java.util.Map;

import de.cau.cs.kieler.kwebs.server.management.command.server.Alive;
import de.cau.cs.kieler.kwebs.server.management.command.server.IManagementCommand;
import de.cau.cs.kieler.kwebs.server.management.command.server.Publish;
import de.cau.cs.kieler.kwebs.server.management.command.server.Published;
import de.cau.cs.kieler.kwebs.server.management.command.server.Shutdown;
import de.cau.cs.kieler.kwebs.server.management.command.server.Unpublish;

/**
 * A factory for management command instances.
 *
 * @author swe
 *
 */
public final class ManagementCommandFactory {

    //////////

    /** Singleton instance. */
    public static final ManagementCommandFactory INSTANCE
        = new ManagementCommandFactory();

    //////////

    /** A mapping from command identifiers to the implementing classes. */
    private static final Map<String, Class<? extends IManagementCommand>> COMMANDTOTYPEMAP
        = new HashMap<String, Class<? extends IManagementCommand>>();

    /**
     * Initialize the type mapping
     */
    static {
        COMMANDTOTYPEMAP.put(ManagementConstants.COMMAND_ALIVE,         Alive.class);
        COMMANDTOTYPEMAP.put(ManagementConstants.COMMAND_PUBLISHED, Published.class);
        COMMANDTOTYPEMAP.put(ManagementConstants.COMMAND_PUBLISH,     Publish.class);
        COMMANDTOTYPEMAP.put(ManagementConstants.COMMAND_UNPUBLISH, Unpublish.class);
        COMMANDTOTYPEMAP.put(ManagementConstants.COMMAND_SHUTDOWN,   Shutdown.class);
    }

    //////////

    /**
     *
     */
    private ManagementCommandFactory() {
    }

    //////////

    /**
     *
     * @param command
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public IManagementCommand create(final String command) throws InstantiationException,
        IllegalAccessException {
        String key = command.trim().toLowerCase();
        if (!COMMANDTOTYPEMAP.containsKey(key)) {
            throw new IllegalArgumentException(
                "Given command does not exist: " + command.trim()
            );
        }
        return COMMANDTOTYPEMAP.get(key).newInstance();
    }

}
