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

import java.net.InetAddress;
import java.net.URI;

import de.cau.cs.kieler.kwebs.client.providers.Providers.Provider;
import de.cau.cs.kieler.kwebs.service.GraphLayouterOption;

/**
 * Abstract base implementation of the {@link IWebServiceClient} interface.
 * This class provides basic infrastructure for managing the connection between a client
 * and the layout service without using a concrete web service architecture. Architecture specific
 * methods still have to be implemented by a concrete web service client.
 *
 * @kieler.rating 2011-05-12 red
 *
 * @author swe
 */
public abstract class AbstractWebServiceClient implements IWebServiceClient {

    /** The provider of the layout service. */
    private Provider provider;

    /** Whether the service is available or not. */
    private Boolean available;

    /** The meta data from the layout service. */
    private String capabilities;

    /** The version of the layout service. */
    private String version;

    /**
     * Constructs a new jaxws web service client.
     *
     */
    protected AbstractWebServiceClient() {
    }
    
    /**
     * Creates a new instance. Only to be used by sub classes.
     * 
     * @param theprovider
     *            the {@link Provider} of the layout service to be used
     */
    protected AbstractWebServiceClient(final Provider theprovider) {
        provider = theprovider;
    }

    /**
     * {@inheritDoc}
     */
    public final String graphLayout(final String serializedGraph,
        final String format, final GraphLayouterOption[] options) {
        return graphLayoutImpl(serializedGraph, format, options);
    }

    /**
     * {@inheritDoc}
     */
    public final synchronized String getCapabilities() {
        if (capabilities == null) {
            capabilities = getCapabilitiesImpl();
        }
        return capabilities;
    }

    /**
     * {@inheritDoc}
     */
    public final synchronized String getVersion() {
        if (version == null) {
            version = getVersionImpl();
        }
        return version;
    }

    /** Default timeout when trying to ping a layout service. */
    private static final int DEFAULT_SERVICE_AVAILABILITY_TIMEOUT
        = 10000;

    /**
     * {@inheritDoc}
     */
    public final synchronized boolean isAvailable() {
        if (available != null) {
            return available;
        }
        try {
            available =
                InetAddress.getByName(new URI(provider.getAddress()).getHost()).
                    isReachable(DEFAULT_SERVICE_AVAILABILITY_TIMEOUT);
        } catch (Exception e) {
            available = false;
        }
        return available;
    }

    /**
     * {@inheritDoc}
     */
    public synchronized void disconnect() {
        available = null;
        capabilities = null;
        version = null;       
    }
    
    /**
     * {@inheritDoc}
     */
    public final synchronized Provider getProvider() {
        return provider;
    }

    /**
     * {@inheritDoc}
     */
    public synchronized void setProvider(final Provider theprovider) {
        if (provider == null || !provider.equals(theprovider)) {
            provider = theprovider;
            disconnect();
        }
    }
    
    // Abstract methods to be implemented by concrete web service clients
    
    /**
     * Does the concrete remote layout of graphs. This method has to be implemented by
     * a concrete web service client in order to provide the architecture dependent
     * functionality.
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
    protected abstract String graphLayoutImpl(final String serializedGraph,
        final String format, final GraphLayouterOption[] options);

    /**
     * Does the concrete retrieval of thelayout server meta data. This method has to be implemented by
     * a concrete web service client in order to provide the architecture dependent
     * functionality.
     *
     * @return the meta data
     */
    protected abstract String getCapabilitiesImpl();

    /**
     * Does the concrete retrieval of teh layout server version. This method has to be implemented by
     * a concrete web service client in order to provide the architecture dependent
     * functionality.
     *
     * @return the meta version
     */
    protected abstract String getVersionImpl();

}
