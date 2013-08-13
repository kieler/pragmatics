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

public class BrowsingSVGServer extends Server {

    private static BrowsingSVGServer INSTANCE;

    private static final String HTML_ROOT = "/html";

    public BrowsingSVGServer(final String docRoot, int port) {

        if (INSTANCE != null) {
            throw new IllegalStateException("Only one server instance allowed.");
        }
        INSTANCE = this;

        final File docRootFile = new File(docRoot);

        SelectChannelConnector connector = new SelectChannelConnector();
        connector.setPort(port);
        addConnector(connector);

        WebSocketHandler wsHandler = new WebSocketHandler() {
            public WebSocket doWebSocketConnect(HttpServletRequest request, String protocol) {
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

    public static BrowsingSVGServer getInstance() {
        return INSTANCE;
    }

    private static void startServer() {
        System.out.println("Starting browsing server ...");
        new Thread("Jetty") {
            public void run() {
                try {
                    INSTANCE.start();
                    INSTANCE.join();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            };
        }.start();

    }
}
