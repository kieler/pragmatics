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

package de.cau.cs.kieler.kwebs.server.publishing;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpServer;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import javax.xml.ws.Endpoint;

import de.cau.cs.kieler.kwebs.logging.Logger;
import de.cau.cs.kieler.kwebs.logging.Logger.Severity;
import de.cau.cs.kieler.kwebs.server.configuration.Configuration;
import de.cau.cs.kieler.kwebs.util.Uris;

/**
 * Manager for publishing a service object over HTTP.
 * Concurrent safety has to be provided by using instance.
 *
 * @author swe
 *
 */
class HttpServerManager extends AbstractServerManager {

    /** Default host for HTTP connections. */
    private static final String HTTP_DEFAULTHOST
        = "0.0.0.0";

    /** Default port for HTTP connections. */
    private static final int HTTP_DEFAULTPORT
        = 80;
    
    // The class {@code HttpServerManager} and its descendants are package private
    // so no need for getters and setters here.
    
    //CHECKSTYLEOFF VisibilityModifier
    
    /** The {@code HttpServer} instance. */
    protected HttpServer server;

    /** The {@code HttpContext} instance. */
    protected HttpContext context;

    /** The {@code Endpoint} which is used for publishing the serviced object. */
    protected Endpoint endpoint;
    
    //CHECKSTYLEON VisibilityModifier

    /**
     * Publishes this managers server and its associated serviced object.
     * 
     * @param serviceObject
     *            the service object to be published
     */
    public void publish(final Object serviceObject) {
        if (serviceObject == null) {
            throw new NoServiceObjectException();
        }
        if (endpoint != null) {
            throw new AlreadyPublishedException();
        }
        try {
            createServer();
            createContext();
            server.start();
            endpoint = Endpoint.create(serviceObject);
            // Sets the executor of the endpoint. The newly created thread pool
            // makes the endpoint handle the incoming requests concurrently.
            endpoint.setExecutor(Executors.newFixedThreadPool(
                Integer.parseInt(Configuration.getConfigProperty(Configuration.SERVER_POOLSIZE))
            ));
            endpoint.publish(context);
        } catch (Exception e) {
            Logger.log(Severity.CRITICAL, "HTTP server could not be published", e);
            if (server != null) {
                server.removeContext(context);
                server.stop(1);
            }
            server = null;
            context = null;
            endpoint = null;
            throw new NotPublishedException(e);
        }
    }

    /**
     * Unpublished this managers server.
     */
    public void unpublish() {
        if (endpoint != null) {
            endpoint.stop();
            endpoint = null;
            server.stop(0);
            server = null;
            context = null;
        }
    }

    /**
     * Returns whether this managers server is published or not.
     * 
     * @return whether this managers server is published or not
     */
    public boolean isPublished() {
        return (endpoint != null && endpoint.isPublished());
    }

    /**
     * Creates the {@code HttpServer} instance configured to listen on the host and port specified by
     * the property {@code Configuration.HTTP_ADDRESS}.
     */
    protected void createServer() {
        if (server != null) {
            throw new IllegalStateException("Server has already been created");
        }
        try {
            String address = Configuration.getConfigProperty(Configuration.HTTP_ADDRESS);
            String host = Uris.getHost(address);
            if (host == null) {
                Logger.log(Severity.WARNING, 
                    "The host you specified for the HTTP server is invalid."
                    + " Using default host " + HTTP_DEFAULTHOST + "."
                );
                host = HTTP_DEFAULTHOST;
            }
            int port = Uris.getPort(address);
            if (port == -1) {
                Logger.log(Severity.WARNING, 
                    "The port you specified for the HTTP server is invalid."
                    + " Using default port " + HTTP_DEFAULTPORT + "."
                );
                port = HTTP_DEFAULTPORT;
            }
            server = HttpServer.create(
                new InetSocketAddress(host, port),
                Integer.parseInt(Configuration.getConfigProperty(Configuration.SERVER_BACKLOG))
            );
        } catch (Exception e) {
            Logger.log(Severity.CRITICAL, "HTTP server could not be created", e);
            throw new ServerNotCreatedException(e);
        }
    }

    /**
     * Creates the {@code HttpContext} under which this server is publishing the serviced object.
     */
    protected void createContext() {
        if (server == null) {
            throw new IllegalStateException("Server has not been created");
        }
        if (context != null) {
            throw new IllegalStateException("Context has already been created");
        }
        try {
            String address = Configuration.getConfigProperty(Configuration.HTTP_ADDRESS);
            String path = Uris.getPath(address);
            if (path == null) {
                Logger.log(Severity.FAILURE, 
                    "The path you specified for the HTTP server is invalid."
                    + " Using default port 80."
                );                
                throw new ContextNotCreatedException();
            }
            context = server.createContext(path);
        } catch (Exception e) {
            Logger.log(Severity.CRITICAL, "HTTP server context could not be created", e);
            throw new ContextNotCreatedException(e);
        }
    }

}
