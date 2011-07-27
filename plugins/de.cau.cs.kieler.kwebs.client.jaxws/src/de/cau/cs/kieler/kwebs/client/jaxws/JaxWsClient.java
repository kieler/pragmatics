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

import de.cau.cs.kieler.kwebs.client.AbstractWebServiceClient;
import de.cau.cs.kieler.kwebs.client.providers.Providers.Provider;
import de.cau.cs.kieler.kwebs.logging.Logger;
import de.cau.cs.kieler.kwebs.logging.Logger.Severity;
import de.cau.cs.kieler.kwebs.service.GraphLayouterOption;
import de.cau.cs.kieler.kwebs.service.IGraphLayouterService;
import de.cau.cs.kieler.kwebs.service.LocalServiceException;
import de.cau.cs.kieler.kwebs.service.RemoteServiceException;
import de.cau.cs.kieler.kwebs.util.Uris;

/**
 * Client implementation for the JAXWS web service.
 *
 * @kieler.rating 2011-05-12 red
 *
 * @author swe
 */
public class JaxWsClient extends AbstractWebServiceClient {

    /** The service object. */
    private Service jaxWsService;

    /** The web service interface used. */
    private IGraphLayouterService jaxWsPort;

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
     * Constructs a new JAXWS web service client.
     *
     */
    public JaxWsClient() {
        super();
    }

    /**
     * Constructs a new JAXWS web service client pointing to the address of the given provider.
     *
     * @param theprovider
     *            the {@link Provider} of the layout service to be used
     */
    public JaxWsClient(final Provider theprovider) {
        super(theprovider);
    }

    // Implementation if the IWebServiceClient interface

    /**
     * {@inheritDoc}
     */
    public synchronized boolean isConnected() {
        return (jaxWsService != null && jaxWsPort != null);
    }

    /**
     * {@inheritDoc}
     */
    public synchronized void connect() {
        if (!ensureConnected()) {
            throw new LocalServiceException(
                "Could not connect to layout service at " + getProvider().getAddress()
            );
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
    protected final String graphLayoutImpl(final String serializedGraph,
        final String format, final GraphLayouterOption[] options) {
        if (ensureConnected()) {
            try {
                return jaxWsPort.graphLayout(serializedGraph, format, options);
            } catch (Exception e) {
                Logger.log(Severity.CRITICAL, "Error while calling layout service", e);
                throw new RemoteServiceException("Error while calling layout service", e);
            }
        }
        throw new LocalServiceException(
            "Could not connect to layout service at " + getProvider().getAddress()
        );
    }

    /**
     * {@inheritDoc}
     */
    protected final String getCapabilitiesImpl() {
        if (ensureConnected()) {
            try {
                return jaxWsPort.getCapabilities();
            } catch (Exception e) {
                Logger.log(Severity.CRITICAL, "Error while calling layout service", e);
                throw new RemoteServiceException("Error while calling layout service", e);
            }
        }
        throw new LocalServiceException(
            "Could not connect to layout service at " + getProvider().getAddress()
        );
    }

    /**
     * {@inheritDoc}
     */
    protected final String getVersionImpl() {
        if (ensureConnected()) {
            try {
                return jaxWsPort.getVersion();
            } catch (Exception e) {
                Logger.log(Severity.CRITICAL, "Error while calling layout service", e);
                throw new RemoteServiceException("Error while calling layout service", e);
            }
        }
        throw new LocalServiceException(
            "Could not connect to layout service at " + getProvider().getAddress()
        );
    }

    /**
     * {@inheritDoc}
     */
    protected final byte[] getPreviewImageImpl(final String previewImage) {
        if (ensureConnected()) {
            try {
                return jaxWsPort.getPreviewImage(previewImage);
            } catch (Exception e) {
                Logger.log(Severity.CRITICAL, "Error while calling layout service", e);
                throw new RemoteServiceException("Error while calling layout service", e);
            }
        }
        throw new LocalServiceException(
            "Could not connect to layout service at " + getProvider().getAddress()
        );
    }

    /**
     * {@inheritDoc}
     */
    public final synchronized void setProvider(final Provider theprovider) {
        if (super.getProvider() == null || !super.getProvider().equals(theprovider)) {
            jaxWsService = null;
            jaxWsPort = null;
            super.setProvider(theprovider);
        }
    }

    /** */
    private static final String QNAME_NS
        = "http://kieler.layout/";

    /** */
    private static final String QNAME_SERVICE
        = "LayoutService";

    /** Postfix to be added to the service address when connecting to a layout service. */
    private static final String WSDL_POSTFIX
        = "?wsdl";


    /**
     * Ensures that this client is actually connected to the layout service.
     *
     * @return whether the connection is established.
     */
    private synchronized boolean ensureConnected() {
        if (!isAvailable()) {
            throw new LocalServiceException(
                "Could not connect to layout service at " + getProvider().getAddress()
            );
        }
        if (jaxWsService == null) {
            if (Uris.isHttpsURI(getProvider().getAddress())) {
                setTruststoreProperties();
            }
            try {
                jaxWsService = Service.create(
                    new URL(super.getProvider().getAddress() + WSDL_POSTFIX),
                    new QName(QNAME_NS, QNAME_SERVICE)
                );
                jaxWsPort = jaxWsService.getPort(IGraphLayouterService.class);
            } catch (Exception e) {
                Logger.log(Severity.FAILURE, 
                    "Error while connecting to service provider: " + e.getMessage(),
                e);
                jaxWsService = null;
                jaxWsPort = null;
                restoreTruststoreProperties();
            }
        }
        return (isAvailable() && jaxWsService != null && jaxWsPort != null);
    }

    /**
     * Sets the trust store system properties for use with the current https based web service
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
        System.setProperty(TRUSTSTORE_PROPERTY, getProvider().getTruststore());
        System.setProperty(TRUSTSTOREPASS_PROPERTY, getProvider().getTruststorePass());
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
