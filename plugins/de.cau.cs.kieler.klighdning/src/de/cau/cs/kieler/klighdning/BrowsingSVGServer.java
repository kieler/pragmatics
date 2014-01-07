/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighdning;

/**
 * @author uru
 *
 */
import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.websocket.WebSocket;
import org.eclipse.jetty.websocket.WebSocketHandler;
import org.osgi.framework.Bundle;

import de.cau.cs.kieler.klighdning.handler.KlighdningHTTPHandler;
import de.cau.cs.kieler.klighdning.handler.KlighdningWebSocketHandler;

/**
 * The server allows to browse a model repository and render all models for which a transformation
 * is known.
 * 
 * @author uru
 */
public class BrowsingSVGServer extends Server {

    private static BrowsingSVGServer instance;

    private static final String HTML_ROOT = "/html";

    /**
     * @param docRoot
     *            the root folder containing the models.
     * @param port
     *            the port which the server should listen to.
     */
    public BrowsingSVGServer(final String docRoot, final int port) {

        if (instance != null) {
            throw new IllegalStateException("Only one server instance allowed.");
        }
        instance = this;

        final File docRootFile = new File(docRoot);

        SelectChannelConnector connector = new SelectChannelConnector();
        connector.setPort(port);
        addConnector(connector);

        WebSocketHandler wsHandler = new WebSocketHandler() {
            public WebSocket doWebSocketConnect(final HttpServletRequest request,
                    final String protocol) {
                WebSocket ws = null;
                if ("protocol.svgws".equals(protocol)) {
                    ws = new KlighdningWebSocketHandler(docRootFile);
                }
                return ws;
            }
        };

        KlighdningHTTPHandler wcHandler = new KlighdningHTTPHandler(docRootFile);

        ResourceHandler rHandler = new ResourceHandler();
        rHandler.setDirectoriesListed(true);

        try {
            // locate the bundle during runtime
            Bundle bundle = Platform.getBundle(KlighdningPlugin.PLUGIN_ID);
            File bundleLocation = FileLocator.getBundleFile(bundle);
            File location = new File(bundleLocation, HTML_ROOT);

            System.out.println(location.getAbsolutePath());
            rHandler.setResourceBase(location.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }

        wsHandler.setHandler(rHandler);

        HandlerList hlist = new HandlerList();
        hlist.addHandler(wcHandler);
        hlist.addHandler(wsHandler);
        hlist.addHandler(rHandler);

        setHandler(hlist);

        // finally start the server
        startServer();
    }

    /**
     * @return the instance
     */
    public static BrowsingSVGServer getInstance() {
        return instance;
    }

    private static void startServer() {
        System.out.println("Starting browsing server ...");
        new Thread("Jetty") {
            public void run() {
                try {
                    instance.start();
                    instance.join();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            };
        }
        .start();

    }
}
