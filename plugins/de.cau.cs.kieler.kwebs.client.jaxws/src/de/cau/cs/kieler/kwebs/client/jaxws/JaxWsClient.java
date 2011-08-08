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
import java.util.List;

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

    // Implementation if the ILayoutServiceClient interface

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
            if (getServerConfig().getAddress().toString().toLowerCase().startsWith("https:")) {
                setTruststoreProperties();
            }
            try {
                layoutService = new LayoutService(
                    new URL(super.getServerConfig().getAddress() + WSDL_POSTFIX),
                    new QName(QNAME_NS, QNAME_SERVICE)
                );
                layoutPort = layoutService.getLayoutServicePort();
            } catch (Exception e) {
                layoutService = null;
                layoutPort = null;
                restoreTruststoreProperties();
                setLastError(e);
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
        restoreTruststoreProperties();
    }

    // Implementation of the abstract methods from AbstractWebServiceClient

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

    /**
     * Sets the trust store system properties for use with the current HTTPS based web service
     * and caches the old settings.
     */
    private synchronized void setTruststoreProperties() {
/* Only for debugging purposes        
        System.out.println(
            "Setting trust store properties to " 
            + TRUSTSTORE_PROPERTY + "=" + getServerConfig().getTruststore() + ", " 
            + TRUSTSTOREPASS_PROPERTY + "=" + getServerConfig().getTruststorePass()
        );
*/        
        oldTruststore = System.getProperty(TRUSTSTORE_PROPERTY);
        oldTruststorePass = System.getProperty(TRUSTSTOREPASS_PROPERTY);
/* Only for debugging purposes        
        System.out.println(
            "Prior trust store properties are " 
            + TRUSTSTORE_PROPERTY + "=" + oldTruststore + ", " 
            + TRUSTSTOREPASS_PROPERTY + "=" + oldTruststorePass
        );
*/        
        System.setProperty(TRUSTSTORE_PROPERTY, getServerConfig().getTruststore());
        System.setProperty(TRUSTSTOREPASS_PROPERTY, getServerConfig().getTruststorePass());
/* Only for debugging purposes        
        System.out.println(
            "Trust store properties are " 
            + TRUSTSTORE_PROPERTY + "=" + System.getProperty(TRUSTSTORE_PROPERTY) + ", " 
            + TRUSTSTOREPASS_PROPERTY + "=" + System.getProperty(TRUSTSTOREPASS_PROPERTY)
        );
*/        
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
