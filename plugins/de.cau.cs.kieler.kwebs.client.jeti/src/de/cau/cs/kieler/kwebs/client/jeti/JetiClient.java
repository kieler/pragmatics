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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.cau.cs.kieler.kwebs.GraphLayoutOption;
import de.cau.cs.kieler.kwebs.LocalServiceException;
import de.cau.cs.kieler.kwebs.RemoteServiceException;
import de.cau.cs.kieler.kwebs.client.AbstractLayoutServiceClient;
import de.cau.cs.kieler.kwebs.client.ServerConfigData;
import de.cau.cs.kieler.kwebs.util.Resources;
import de.unido.ls5.eti.client.ByteArrayVirtualFile;
import de.unido.ls5.eti.client.EtiConnection;
import de.unido.ls5.eti.client.EtiConnectionSepp;
import de.unido.ls5.eti.client.VirtualFile;

/**
 * Client implementation for the jETI web service.
 *
 * @author swe
 */
public final class JetiClient extends AbstractLayoutServiceClient {

    /** The jETI connection used. */
    private EtiConnection etiCon;

    /**
     * Constructs a new jETI web service client.
     *
     */
    public JetiClient() {
        super();
    }
    
    /**
     * Constructs a jETI based web service client pointing to the address of the given provider.
     *
     * @param theserverConfig
     *            the {@link ServerConfigData} of the layout service to be used
     */
    public JetiClient(final ServerConfigData theserverConfig) {
        super(theserverConfig);
    }

    // Implementation if the IWebServiceClient interface

    /**
     * {@inheritDoc}
     */
    public synchronized boolean isConnected() {        
        if (etiCon != null) {
            try {
                // Just dummy login, security currently
                // not implemented in jETI tool server
                etiCon.login("foo", "bar");
                return true;
            } catch (Exception e) {
                etiCon = null;
            }            
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    public synchronized void connect() {
        if (!isAvailable()) {
            throw new LocalServiceException(
                "Could not connect to layout service at " + getServerConfig().getAddress()
            );
        }
        if (etiCon == null) {
            try {
                // Create a new connection to the jETI server
                etiCon = new EtiConnectionSepp(getServerConfig().getAddress());
            } catch (Exception e) {
                etiCon = null;
                throw new LocalServiceException(
                    "Could not connect to layout service at " + getServerConfig().getAddress(), e
                );
            }
        }
        if (etiCon != null) {
            try {
                // Just dummy login, security currently
                // not implemented in jETI tool server
                etiCon.login("foo", "bar");
            } catch (Exception e) {
                etiCon = null;
                setLastError(e);
                throw new LocalServiceException(
                    "Could not login on layout service at " + getServerConfig().getAddress(), e
                );
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public synchronized void disconnect() {
        etiCon = null;
    }

    // Implementation of the abstract methods from AbstractWebServiceClient

    /** The buffer size for data transfer between server and client. */
    private static final int BUFFER_SIZE
        = 20480;

    /**
     * {@inheritDoc}
     */
    public String graphLayout(final String serializedGraph, final String format,
        final List<GraphLayoutOption> options) {
        if (!isConnected()) {
            connect();
        }
        try {
            Map<String, String> etiParams = new HashMap<String, String>();
            etiParams.put(
                "INPUT_GRAPH",
                "graph.in"
                //serializedGraph
            );
            etiParams.put(
                "OUTPUT_GRAPH",
                "graph.out"
            );
            etiParams.put(
                "INPUT_FORMAT",
                format
            );
            if (options != null && options.size() > 0) {
                etiParams.put(
                    "INPUT_OPTIONS",
                    GraphLayoutOption.listToString(options)
                );
            }
//* File upload operations are unstable on windows platforms            
            byte[] byteGraph = serializedGraph.getBytes("UTF-8");
            ByteArrayVirtualFile vfOut = new ByteArrayVirtualFile(
                new ByteArrayInputStream(byteGraph),
                "graph.in"
            );
            etiCon.store(vfOut);
//*/            
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
            disconnect();
            setLastError(e);
            throw new RemoteServiceException("Error while calling layout service", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    public String getServiceData() {
        if (!isConnected()) {
            connect();
        }
        try {
            Map<String, String> etiParams = new HashMap<String, String>();
            etiParams.put(
                "OUTPUT_SERVICEDATA",
                "serviceData.out"
            );
            etiCon.exec("getServiceData", etiParams);
            VirtualFile vfIn = etiCon.retrieve("serviceData.out");
            StringBuffer sb = new StringBuffer();
            InputStream in = vfIn.getInputStream();
            byte[] buf = new byte[BUFFER_SIZE];
            int read = 0;
            while ((read = in.read(buf)) > 0) {
                sb.append(new String(buf, 0, read));
            }
            return sb.toString();
        } catch (Exception e) {
            disconnect();
            setLastError(e);
            throw new RemoteServiceException("Error while calling layout service", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    public byte[] getPreviewImage(final String previewImage) {
        if (!isConnected()) {
            connect();
        }
        try {
            Map<String, String> etiParams = new HashMap<String, String>();
            etiParams.put(
                "INPUT_PREVIEWIMAGE",
                previewImage
            );
            etiParams.put(
                "OUTPUT_PREVIEWIMAGE",
                "previewImage.out"
            );
            etiCon.exec("getPreviewImage", etiParams);
            VirtualFile vfIn = etiCon.retrieve("previewImage.out");
            return Resources.readStreamAsByteArray(vfIn.getInputStream());
        } catch (Exception e) {
            disconnect();
            setLastError(e);
            throw new RemoteServiceException("Error while calling layout service", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    public synchronized void setServerConfig(final ServerConfigData theserverConfig) {
        if (super.getServerConfig() == null || !super.getServerConfig().equals(theserverConfig)) {
            etiCon = null;
            super.setServerConfig(theserverConfig);
        }
    }

}
