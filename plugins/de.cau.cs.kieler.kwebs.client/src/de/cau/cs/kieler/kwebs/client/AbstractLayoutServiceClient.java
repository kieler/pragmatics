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

import java.util.Vector;


/**
 * Abstract base implementation of the {@link ILayoutServiceClient} interface.
 * This class provides basic infrastructure for managing the connection between a client
 * and the layout service without using a concrete web service architecture. Architecture specific
 * methods still have to be implemented by a concrete web service client.
 *
 * @kieler.rating 2011-08-02 yellow
 *     reviewed by ckru, mri, msp
 *
 * @author swe
 */
public abstract class AbstractLayoutServiceClient implements ILayoutServiceClient {

    /** The provider of the layout service. */
    private ServerConfigData serverConfig;

    /** The last error occurred. */
    private Throwable lastError;
    
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
     *            the {@link ServerConfigData} of the layout service to be used
     */
    protected AbstractLayoutServiceClient(final ServerConfigData theserverConfig) {
        serverConfig = theserverConfig;
    }

    /**
     * This base implementation does not really check for availability but always returns
     * {@code true}. The only way to check for availability at this point is to check whether the
     * given server address answers to a ping. Since many fire walls filter a ping this is not an 
     * adequate way to check for availability. Protocol specific implementations may override if 
     * they have more specific ways to check for availability. 
     * 
     * @return whether the endpoint configured in the server configuration of this client is reachable
     */
    public synchronized boolean isAvailable() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    public final synchronized ServerConfigData getServerConfig() {
        return serverConfig;
    }

    /**
     * {@inheritDoc}
     */
    public synchronized void setServerConfig(final ServerConfigData theserverConfig) {
        if (serverConfig == null || !serverConfig.equals(theserverConfig)) {
            serverConfig = theserverConfig;
            disconnect();
        }
    }

    /**
     * {@inheritDoc}
     */
    public String[] getLastErrorAsStringArray() {
        Vector<String> messages = new Vector<String>();
        Throwable error = lastError;
        while (error != null) {
            String message = error.getMessage();
            if (message == null) {
                message = "<undefined message>";
            }            
            messages.add(message);
            error = error.getCause();
        }
        return messages.toArray(new String[0]);
    }
    
    /**
     * {@inheritDoc}
     */
    public Throwable getLastError() {
        return lastError;
    }

    /**
     * {@inheritDoc}
     */
    public void setLastError(final Throwable thelastError) {
        lastError = thelastError;
    }

}
