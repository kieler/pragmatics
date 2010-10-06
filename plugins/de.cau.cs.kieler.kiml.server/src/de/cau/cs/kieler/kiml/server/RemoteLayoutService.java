/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import com.sun.net.httpserver.HttpServer;

/**
 * Layout service that can be used remotely.
 *
 * @author msp
 */
public class RemoteLayoutService {
    
    /** the server instance. */
    private HttpServer server;
    
    /**
     * Creates and activates a remote layout service.
     */
    public RemoteLayoutService() {
        try {
            InetSocketAddress address = new InetSocketAddress("effert.local", 80);
            server = HttpServer.create(address, 0);
            server.createContext("/", new LayoutHandler());
            server.start();
            System.out.println("Server running!");
        } catch (IOException exception) {
            // TODO Auto-generated catch block
            exception.printStackTrace();
        } catch (SecurityException exception) {
            // TODO Auto-generated catch block
            exception.printStackTrace();
        }
    }
    
    /**
     * Deactivates and disposes the layout service.
     */
    public void dispose() {
        server.stop(1);
    }

}
