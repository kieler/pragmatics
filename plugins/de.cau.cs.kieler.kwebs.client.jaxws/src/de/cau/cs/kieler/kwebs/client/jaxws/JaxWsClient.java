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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.MessageContext;

import de.cau.cs.kieler.kwebs.GraphLayoutOption;
import de.cau.cs.kieler.kwebs.LocalServiceException;
import de.cau.cs.kieler.kwebs.RemoteServiceException;
import de.cau.cs.kieler.kwebs.client.HttpBasedLayoutServiceClient;
import de.cau.cs.kieler.kwebs.client.ServerConfig;
import de.cau.cs.kieler.kwebs.jaxws.LayoutService;
import de.cau.cs.kieler.kwebs.jaxws.LayoutServicePort;

/**
 * Client implementation for the JAX-WS web service.
 *
 * @kieler.rating 2011-05-12 red
 *
 * @author swe
 */
public class JaxWsClient extends HttpBasedLayoutServiceClient {

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
//* Adding support for GZip and Deflate encoding                
                Map<String, List<String>> httpHeaders = new HashMap<String, List<String>>();
                List<String> acceptList = new ArrayList<String>();
                acceptList.add("gzip");
                acceptList.add("deflate");
                httpHeaders.put("Accept-Encoding", acceptList);
                List<String> contentList = new ArrayList<String>();
                contentList.add("gzip");
                httpHeaders.put("Content-Encoding", contentList);
                Map<String, Object> context = ((BindingProvider) layoutPort).getRequestContext();  
                context.put(MessageContext.HTTP_REQUEST_HEADERS, httpHeaders);
//*/                 
            } catch (Exception e) {
                layoutService = null;
                layoutPort = null;
                releaseSSL();
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
        if (layoutService != null) {
            layoutService = null;
            layoutPort = null;
            releaseSSL();
        }
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
            return layoutPort.graphLayout(serializedGraph, format, null, options);
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
    public final byte[] getPreviewImage(final String previewImage) {
        if (!isConnected()) {
            connect();
        }
        try {
            return layoutPort.getPreviewImage(previewImage);
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
    
}
