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

package de.cau.cs.kieler.kwebs.client.jeti;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import de.cau.cs.kieler.kwebs.client.AbstractWebServiceClient;
import de.cau.cs.kieler.kwebs.client.providers.Providers.Provider;
import de.cau.cs.kieler.kwebs.logging.Logger;
import de.cau.cs.kieler.kwebs.logging.Logger.Severity;
import de.cau.cs.kieler.kwebs.service.GraphLayouterOption;
import de.cau.cs.kieler.kwebs.service.LocalServiceException;
import de.cau.cs.kieler.kwebs.service.RemoteServiceException;
import de.unido.ls5.eti.client.ByteArrayVirtualFile;
import de.unido.ls5.eti.client.EtiConnection;
import de.unido.ls5.eti.client.EtiConnectionSepp;
import de.unido.ls5.eti.client.VirtualFile;

/**
 * Client implementation for the jeti web service.
 *
 * @kieler.rating 2011-05-12 red
 *
 * @author swe
 */
public final class JetiClient extends AbstractWebServiceClient {

    /** The jETI connection used. */
    private EtiConnection etiCon;

    /**
     * Constructs a new jaxws web service client.
     *
     */
    public JetiClient() {
        super();
    }
    
    /**
     * Constructs a jeti based web service client pointing to the address of the given provider.
     *
     * @param theprovider
     *            the {@link Provider} of the layout service to be used
     */
    public JetiClient(final Provider theprovider) {
        super(theprovider);
    }

    // Implementation if the IWebServiceClient interface

    /**
     * {@inheritDoc}
     */
    public boolean isConnected() {
        return (etiCon != null);
    }

    /**
     * {@inheritDoc}
     */
    public void connect() {
        if (!ensureConnected()) {
            throw new LocalServiceException(
                "Could not connect to layout service at " + getProvider().getAddress()
            );
        }
    }

    /**
     * {@inheritDoc}
     */
    public void disconnect() {
        super.disconnect();
        etiCon = null;
    }

    // Implementation of the abstract methods from AbstractWebServiceClient

    /** The buffer size for data transfer between server and client. */
    private static final int BUFFER_SIZE
        = 20480;

    /**
     * {@inheritDoc}
     */
    @Override
    protected String graphLayoutImpl(final String serializedGraph, final String format,
        final GraphLayouterOption[] options) {
        if (ensureConnected()) {
            try {
                Map<String, String> etiParams = new HashMap<String, String>();
                etiParams.put(
                    "INPUT_GRAPH",
                    "graph.in"
                );
                etiParams.put(
                    "OUTPUT_GRAPH",
                    "graph.out"
                );
                etiParams.put(
                    "INPUT_FORMAT",
                    format
                );
                if (options != null && options.length > 0) {
                    etiParams.put(
                        "INPUT_OPTIONS",
                        GraphLayouterOption.arrayToString(options)
                    );
                }
                byte[] byteGraph = serializedGraph.getBytes();
                ByteArrayVirtualFile vfOut = new ByteArrayVirtualFile(
                    new ByteArrayInputStream(byteGraph),
                    "graph.in"
                );
                etiCon.store(vfOut);
                etiCon.exec("graphLayout", etiParams);
                VirtualFile vfIn = etiCon.retrieve("graph.out");
                StringBuffer sb = new StringBuffer();
                InputStream in = vfIn.getInputStream();
                byte[] buf = new byte[BUFFER_SIZE];
                int read = 0;
                while ((read = in.read(buf)) > 0) {
                    sb.append(new String(buf, 0, read));
                }
                return sb.toString();
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
    @Override
    protected String getCapabilitiesImpl() {
        if (ensureConnected()) {
            try {
                Map<String, String> etiParams = new HashMap<String, String>();
                etiParams.put(
                    "OUTPUT_CAPABILITIES",
                    "capabilities.out"
                );
                etiCon.exec("getCapabilities", etiParams);
                VirtualFile vfIn = etiCon.retrieve("capabilities.out");
                StringBuffer sb = new StringBuffer();
                InputStream in = vfIn.getInputStream();
                byte[] buf = new byte[BUFFER_SIZE];
                int read = 0;
                while ((read = in.read(buf)) > 0) {
                    sb.append(new String(buf, 0, read));
                }
                return sb.toString();
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
    @Override
    protected String getVersionImpl() {
        if (ensureConnected()) {
            try {
                Map<String, String> etiParams = new HashMap<String, String>();
                etiParams.put(
                    "OUTPUT_VERSION",
                    "version.out"
                );
                etiCon.exec("getVersion", etiParams);
                VirtualFile vfIn = etiCon.retrieve("version.out");
                StringBuffer sb = new StringBuffer();
                InputStream in = vfIn.getInputStream();
                byte[] buf = new byte[BUFFER_SIZE];
                int read = 0;
                while ((read = in.read(buf)) > 0) {
                    sb.append(new String(buf, 0, read));
                }
                return sb.toString();
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
            etiCon = null;
            super.setProvider(theprovider);
        }
    }

    /**
     * Ensures that this client is actually connected to the layout service.
     *
     * @return whether the connection is established.
     */
    private synchronized boolean ensureConnected() {
        if (isAvailable()) {
            if (etiCon == null) {
                try {
                    etiCon = new EtiConnectionSepp(new URI(getProvider().getAddress()));
                    // just dummy login, security currently
                    // not implemented in jeti toolserver
                    etiCon.login("foo", "bar");
                } catch (Exception e) {
                    etiCon = null;
                }
            }
        }
        return (isAvailable() && etiCon != null);
    }

}
