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

package de.cau.cs.kieler.kwebs.client;

import java.util.List;

import de.cau.cs.kieler.kwebs.GraphLayoutOption;
import de.cau.cs.kieler.kwebs.client.providers.ServerConfig;

/**
 * Interface for a web service client. Every implementation needs to provide a
 * default constructor so that the {@link LayoutServiceClients} class can initialize it.
 *
 * @kieler.rating 2011-08-02 proposed yellow
 *     reviewed by ckru, mri, msp
 *
 * @author swe
 */
public interface ILayoutServiceClient {

    /**
     * Does the graph layout on the layout server the client is connected to.
     * 
     * @param serializedGraph
     *            the graph to do layout on in serial representation
     * @param format
     *            the format used to serialize the graph {@see Formats}
     * @param options
     *            possible layout options 
     *            
     * @return the graph which was layout done on in the same serial representation as used
     *         in the call to {@code graphLayout}
     */
    String graphLayout(String serializedGraph, String format, List<GraphLayoutOption> options);

    /**
     * Returns the meta data from the layout service.
     * 
     * @return the meta data
     */
    String getServiceData();

    /**
     * Checks whether the layout service this clients service address points to
     * is currently available.
     * 
     * @return whether the layout service is currently available
     */
    boolean isAvailable();

    /**
     * Returns whether the client is actually connected to the layout service.
     *
     * @return whether the client is actually connected to the layout service
     */
    boolean isConnected();
    
    /**
     * Connects this client to the layout service.
     */
    void connect();

    /**
     * Disconnects this client from the layout service.
     */
    void disconnect();
    
    /**
     * Returns the {@link ServerConfig} this client currently points to.
     * 
     * @return the server configuration
     */
    ServerConfig getServerConfig();

    /**
     * Sets a new {@link ServerConfig} this client points to.
     * Resets the state of this client to disconnected.
     * 
     * @param theserverConfig
     *            the new server configuration
     */
    void setServerConfig(ServerConfig theserverConfig);

}
