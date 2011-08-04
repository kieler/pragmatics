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

import de.cau.cs.kieler.kwebs.client.providers.ServerConfig;

/**
 * Abstract base implementation of the {@link ILayoutServiceClient} interface.
 * This class provides basic infrastructure for managing the connection between a client
 * and the layout service without using a concrete web service architecture. Architecture specific
 * methods still have to be implemented by a concrete web service client.
 *
 * @kieler.rating 2011-08-02 proposed yellow
 *     reviewed by ckru, mri, msp
 *
 * @author swe
 */
public abstract class AbstractLayoutServiceClient implements ILayoutServiceClient {

    /** The provider of the layout service. */
    private ServerConfig serverConfig;

    /** Whether the service is available or not. */
    private Boolean available;

    /**
     * Protected constructor.
     *
     */
    protected AbstractLayoutServiceClient() {
    }
    
    /**
     * Creates a new instance. Only to be used by sub classes.
     * 
     * @param theserverConfig
     *            the {@link ServerConfig} of the layout service to be used
     */
    protected AbstractLayoutServiceClient(final ServerConfig theserverConfig) {
        serverConfig = theserverConfig;
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
                InetAddress.getByName(serverConfig.getAddress().getHost()).
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
    }
    
    /**
     * {@inheritDoc}
     */
    public final synchronized ServerConfig getServerConfig() {
        return serverConfig;
    }

    /**
     * {@inheritDoc}
     */
    public synchronized void setServerConfig(final ServerConfig theserverConfig) {
        if (serverConfig == null || !serverConfig.equals(theserverConfig)) {
            serverConfig = theserverConfig;
            disconnect();
        }
    }
    
}
