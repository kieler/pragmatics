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

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import de.cau.cs.kieler.kwebs.GraphLayoutOption;
import de.cau.cs.kieler.kwebs.IGraphLayoutService;
import de.cau.cs.kieler.kwebs.LocalServiceException;
import de.cau.cs.kieler.kwebs.RemoteServiceException;
import de.cau.cs.kieler.kwebs.client.AbstractLayoutServiceClient;
import de.cau.cs.kieler.kwebs.client.providers.ServerConfig;

/**
 * Client implementation for the JAX-WS web service.
 *
 * @kieler.rating 2011-05-12 red
 *
 * @author swe
 */
public class JaxWsClient extends AbstractLayoutServiceClient {

    /** The service object. */
    private Service jaxWsService;

    /** The web service interface used. */
    private IGraphLayoutService jaxWsPort;

    /** Java system property for the trust store to be used. */
    private static final String TRUSTSTORE_PROPERTY
        = "javax.net.ssl.trustStore";

    /** Java system property for the trust store password. */
    private static final String TRUSTSTOREPASS_PROPERTY
        = "javax.net.ssl.trustStorePassword";

    /**
     *  Remember settings of the trust store property to restore after
     *  service has been used.
     */
    private String oldTruststore;

    /**
     *  Remember settings of the trust store password property to restore after
     *  service has been used.
     */
    private String oldTruststorePass;

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

    // Implementation if the IWebServiceClient interface

    /**
     * {@inheritDoc}
     */
    public synchronized boolean isConnected() {
        return (jaxWsService != null && jaxWsPort != null);
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
        if (jaxWsService == null) {
            if (getServerConfig().getAddress().toString().toLowerCase().startsWith("https:")) {
                setTruststoreProperties();
            }
            try {
                jaxWsService = Service.create(
                    new URL(super.getServerConfig().getAddress() + WSDL_POSTFIX),
                    new QName(QNAME_NS, QNAME_SERVICE)
                );
                jaxWsPort = jaxWsService.getPort(IGraphLayoutService.class);
            } catch (Exception e) {
                jaxWsService = null;
                jaxWsPort = null;
                restoreTruststoreProperties();
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
        jaxWsService = null;
        jaxWsPort = null;
        restoreTruststoreProperties();
    }

    // Implementation of the abstract methods from AbstractWebServiceClient

    /**
     * {@inheritDoc}
     */
    public final String graphLayout(final String serializedGraph, final String format, 
        final GraphLayoutOption[] options) {
        if (!isConnected()) {
            connect();
        }
        try {
            return jaxWsPort.graphLayout(serializedGraph, format, options);
        } catch (Exception e) {
            disconnect();
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
            return jaxWsPort.getServiceData();
        } catch (Exception e) {
            disconnect();
            throw new RemoteServiceException("Error while calling layout service", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    public final synchronized void setServerConfig(final ServerConfig theserverConfig) {
        if (super.getServerConfig() == null || !super.getServerConfig().equals(theserverConfig)) {
            jaxWsService = null;
            jaxWsPort = null;
            super.setServerConfig(theserverConfig);
        }
    }

    /**
     * Sets the trust store system properties for use with the current HTTPS based web service
     * and caches the old settings.
     */
    private synchronized void setTruststoreProperties() {
/* Only for debugging purposes        
        System.out.println(
            "Setting trust store properties to " 
            + TRUSTSTORE_PROPERTY + "="+ getProvider().getTruststore() + ", " 
            + TRUSTSTOREPASS_PROPERTY + "=" + getProvider().getTruststorePass()
        );
*/        
        oldTruststore = System.getProperty(TRUSTSTORE_PROPERTY);
        oldTruststorePass = System.getProperty(TRUSTSTOREPASS_PROPERTY);
        System.setProperty(TRUSTSTORE_PROPERTY, getServerConfig().getTruststore());
        System.setProperty(TRUSTSTOREPASS_PROPERTY, getServerConfig().getTruststorePass());
    }

    /**
     * Sets the trust store system properties back to the cached values.
     */
    private synchronized void restoreTruststoreProperties() {
        if (oldTruststore != null) {
            System.setProperty(TRUSTSTORE_PROPERTY, oldTruststore);
        } else {
            System.clearProperty(TRUSTSTORE_PROPERTY);
        }            
        if (oldTruststorePass != null) {
            System.setProperty(TRUSTSTOREPASS_PROPERTY, oldTruststorePass);
        } else {
            System.clearProperty(TRUSTSTOREPASS_PROPERTY);
        }
    }

}
