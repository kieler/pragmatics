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

package de.cau.cs.kieler.kwebs.client.kiml;

import java.util.Vector;

import de.cau.cs.kieler.kwebs.client.ServerConfig;

/**
 * This interface defines an API for storage management of the server configuration list.
 *
 * @kieler.rating 2011-05-03 red
 *
 * @author swe
 */
public interface IServerConfigsStorageManager {
    
    /**
     * Import a server configuration list from some sort of storage.
     * 
     */
    void readServerConfigs();
    
    /**
     * Store a server configuration list to some sort of storage.
     * 
     * @param serverConfigList
     *            the list to be stored
     */
    void storeServerConfigs(final Vector<ServerConfig> serverConfigList);
    
}
