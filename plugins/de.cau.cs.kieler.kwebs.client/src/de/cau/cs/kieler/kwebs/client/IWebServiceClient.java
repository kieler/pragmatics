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

import de.cau.cs.kieler.kwebs.client.providers.Providers.Provider;
import de.cau.cs.kieler.kwebs.service.GraphLayouterOption;

/**
 * Interface for a wweb service client.
 *
 * @kieler.rating 2011-05-03 red
 *
 * @author swe
 */
public interface IWebServiceClient {

    /**
     * Does the graph layout on the layout server the client is connected to.
     * 
     * @param serializedGraph
     *            the graph to be layouted in serial representation
     * @param format
     *            the format used to serialize the graph {@see Formats}
     * @param options
     *            possible layout options 
     *            
     * @return the layouted graph in the same serial representation as used
     *         in the call to {@code graphLayout}
     */
    String graphLayout(final String serializedGraph, final String format,
            final GraphLayouterOption[] options);

    /**
     * Returns the meta data from the layout service.
     * 
     * @return the meta data
     */
    String getCapabilities();

    /**
     * Returns the version of the layout service.
     *
     * @return the version
     */
    String getVersion();

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
     * Returns the {@link Provider} this client currently points to.
     * 
     * @return the provider
     */
    Provider getProvider();

    /**
     * Sets a new {@link Provider} this client points to.
     * Resets the state of this client to disconnected.
     * 
     * @param theprovider
     *            the new provider
     */
    void setProvider(final Provider theprovider);

}
