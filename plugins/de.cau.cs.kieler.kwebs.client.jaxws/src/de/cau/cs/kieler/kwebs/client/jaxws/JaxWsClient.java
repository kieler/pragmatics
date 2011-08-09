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

package de.cau.cs.kieler.kwebs.client.jaxws;

import java.io.FileInputStream;
import java.net.URL;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;
import javax.xml.namespace.QName;

import de.cau.cs.kieler.kwebs.GraphLayoutOption;
import de.cau.cs.kieler.kwebs.LocalServiceException;
import de.cau.cs.kieler.kwebs.RemoteServiceException;
import de.cau.cs.kieler.kwebs.client.AbstractLayoutServiceClient;
import de.cau.cs.kieler.kwebs.client.providers.ServerConfig;
import de.cau.cs.kieler.kwebs.jaxws.LayoutService;
import de.cau.cs.kieler.kwebs.jaxws.LayoutServicePort;

/**
 * Client implementation for the JAX-WS web service.
 *
 * @kieler.rating 2011-05-12 red
 *
 * @author swe
 */
public class JaxWsClient extends AbstractLayoutServiceClient {

    /** The service object. */
    private LayoutService layoutService;

    /** The web service interface used. */
    private LayoutServicePort layoutPort;

    /**
     * Constructs a new JAX-WS web service client.
     *
     */
    public JaxWsClient() {
        super();
    }

    /**
     * Constructs a new JAX-WS web service client pointing to the address of the given server
     * configuration.
     *
     * @param theserverConfig
     *            the {@link ServerConfig} of the layout service to be used
     */
    public JaxWsClient(final ServerConfig theserverConfig) {
        super(theserverConfig);
    }

    /**
     * {@inheritDoc}
     */
    public synchronized boolean isConnected() {
        return (layoutService != null && layoutPort != null);
    }

    /** */
    private static final String QNAME_NS
        = "http://rtsys.informatik.uni-kiel.de/layout";

    /** */
    private static final String QNAME_SERVICE
        = "LayoutService";

    /** Postfix to be added to the service address when connecting to a layout service. */
    private static final String WSDL_POSTFIX
        = "?wsdl";

    /**
     * {@inheritDoc}
     */
    public synchronized void connect() {
        if (!isAvailable()) {
            throw new LocalServiceException(
                "Could not connect to layout service at " + getServerConfig().getAddress()
            );
        }
        if (layoutService == null) {
            try {
                if (getServerConfig().getAddress().toString().toLowerCase().startsWith("https:")) {
                    initSSL(getServerConfig().getTruststore(), getServerConfig().getTruststorePass());
                }
                layoutService = new LayoutService(
                    new URL(super.getServerConfig().getAddress() + WSDL_POSTFIX),
                    new QName(QNAME_NS, QNAME_SERVICE)
                );
                layoutPort = layoutService.getLayoutServicePort();
            } catch (Exception e) {
                layoutService = null;
                layoutPort = null;
                releaseSSL();
                setLastError(e);
                e.printStackTrace();
                throw new LocalServiceException(
                    "Could not connect to layout service at " + getServerConfig().getAddress(), e
                );
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public synchronized void disconnect() {
        super.disconnect();
        layoutService = null;
        layoutPort = null;
        releaseSSL();
    }

    /**
     * {@inheritDoc}
     */
    public final String graphLayout(final String serializedGraph, final String format, 
        final List<GraphLayoutOption> options) {
        if (!isConnected()) {
            connect();
        }
        try {
            return layoutPort.graphLayout(serializedGraph, format, options);
        } catch (Exception e) {
            disconnect();
            setLastError(e);
            throw new RemoteServiceException("Error while calling layout service", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    public final String getServiceData() {
        if (!isConnected()) {
            connect();
        }
        try {
            return layoutPort.getServiceData();
        } catch (Exception e) {
            disconnect();
            setLastError(e);
            throw new RemoteServiceException("Error while calling layout service", e);
        }
    }
    
    /**
     * {@inheritDoc}
     */
    public final synchronized void setServerConfig(final ServerConfig theserverConfig) {
        if (super.getServerConfig() == null || !super.getServerConfig().equals(theserverConfig)) {
            layoutService = null;
            layoutPort = null;
            super.setServerConfig(theserverConfig);
        }
    }
    
    /** Remember the default SSL socket factory. */
    private SSLSocketFactory sslSocketFactory;

    /**
     * Initializes the SSL context needed for communication with a HTTPS based layout provider.
     * 
     * @param truststore
     *            path to the trust store file
     * @param truststorePass
     *            password for the trust store file
     * @throws Exception
     */
    private synchronized void initSSL(final String truststore, final String truststorePass) 
        throws Exception {
        if (sslSocketFactory != null) {
            throw new IllegalAccessException("SSL already initialized, call releaseSSL first");
        }
        // Create a trust manager factory
        TrustManagerFactory trustManagerFactory
            = TrustManagerFactory.getInstance(
                  TrustManagerFactory.getDefaultAlgorithm()
              );
        // Load the clients trust store
        KeyStore keyStore
            = KeyStore.getInstance("JKS");
        keyStore.load(
            new FileInputStream(truststore),
            truststorePass.toCharArray()
        );
        // Initialize the trust manager factory with the loaded trust store.
        // Here the trusted certificate from the trust store is imported. 
        trustManagerFactory.init(keyStore);
        // Initialize a SSL context with a trust manager which trusts the
        // imported certificate
        SSLContext sslContext = SSLContext.getInstance("TLS");            
        sslContext.init(
            null,
                trustManagerFactory.getTrustManagers(),
                    new SecureRandom()
        );
        // Exchange the default SSL socket factory with our own and
        // remember system default factory
        sslSocketFactory = HttpsURLConnection.getDefaultSSLSocketFactory();
        HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
    }
    
    /**
     * Sets the SSL configuration to system defaults.
     */
    private synchronized void releaseSSL() {
        if (sslSocketFactory != null) {
            // Set system SSL socket factory back to default
            HttpsURLConnection.setDefaultSSLSocketFactory(sslSocketFactory);
            sslSocketFactory = null;
        }
    }
    
}
