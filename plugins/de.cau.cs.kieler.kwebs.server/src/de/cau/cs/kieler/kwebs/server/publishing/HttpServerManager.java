/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *     + Department of Computer Science
 *         + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */

package de.cau.cs.kieler.kwebs.server.publishing;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.xml.ws.Endpoint;

import de.cau.cs.kieler.kwebs.server.configuration.Configuration;
import de.cau.cs.kieler.kwebs.server.logging.Logger;
import de.cau.cs.kieler.kwebs.server.logging.Logger.Severity;
import de.cau.cs.kieler.kwebs.server.service.JaxWsService;

/**
 * Manager for publishing a service object over HTTP.
 * Concurrent safety has to be provided by using instance.
 * 
 * @kieler.rating  2011-08-25 proposed yellow
 *      reviewed by ckru, msp, mri
 *      
 * @author swe
 *
 */
class HttpServerManager extends AbstractServerManager {

    /** Default host for HTTP connections. */
    private static final String HTTP_DEFAULTHOST = "0.0.0.0";

    /** Default port for HTTP connections. */
    private static final int HTTP_DEFAULTPORT = 80;
    
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
    public synchronized void publish(final Object serviceObject) {
        if (serviceObject == null) {
            throw new NoServiceObjectException();
        }
        if (endpoint != null) {
            throw new AlreadyPublishedException();
        }
        try {
            createServer();
            createContext();
            ExecutorService executor = Executors.newFixedThreadPool(poolSize);
            if (serviceObject instanceof JaxWsService) {
                endpoint = Endpoint.create(serviceObject);
                // Sets the executor of the end point. The newly created thread pool
                // makes the end point handle the incoming requests concurrently.
                endpoint.setExecutor(executor);
                endpoint.publish(context);
            } else if (serviceObject instanceof HttpHandler) {
                context.setHandler((HttpHandler) serviceObject);
                server.setExecutor(executor);
            }
            server.start();
        } catch (Exception e) {
            Logger.log(
                Severity.CRITICAL, 
                "HTTP server could not be published: '" + e.getMessage() + "'", 
                e
            );
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
    public synchronized void unpublish() {
        if (endpoint != null) {
            endpoint.stop();
            endpoint = null;
        }
        if (server != null) {
            server.stop(0);
            server = null;
        }
        if (context != null) {
            context = null;
        }
    }

    /**
     * Returns whether this managers server is published or not.
     * 
     * @return whether this managers server is published or not
     */
    public synchronized boolean isPublished() {
        return (endpoint != null && endpoint.isPublished());
    }

    /**
     * Creates the {@code HttpServer} instance configured to listen on the host and port specified by
     * the property {@code Configuration.HTTP_ADDRESS}. It is not allowed to call this method when a 
     * server instance has already been created. This attempt will cause a 
     * {@code IllegalStateException} to be thrown.
     * 
     * @throws Exception
     *             if the configured server address is not valid or the server instance
     *             creation failed.
     */
    protected void createServer() throws Exception {
        if (server != null) {
            throw new IllegalStateException("Server has already been created");
        }
        String host = address.getHost();
        if (host == null) {
            Logger.log(Severity.WARNING, 
                "The host you specified for the HTTP server is invalid."
                + " Using default host " + HTTP_DEFAULTHOST + "."
            );
            host = HTTP_DEFAULTHOST;
        }
        int port = address.getPort();
        if (port == -1) {
            Logger.log(Severity.WARNING, 
                "The port you specified for the HTTP server is invalid."
                + " Using default port " + HTTP_DEFAULTPORT + "."
            );
            port = HTTP_DEFAULTPORT;
        }
        server = HttpServer.create(
            new InetSocketAddress(host, port),
            Integer.parseInt(config.getConfigProperty(Configuration.SERVER_BACKLOG))
        );
    }

    /**
     * Creates the {@code HttpContext} under which this server is publishing the serviced object. A 
     * context instance can only be created when the server instance has been created and no other 
     * context instance so far. Any attempt to do otherwise will cause a {@code IllegalStateException} 
     * to be thrown. 
     * 
     * @throws Exception
     *             if the configured server address is not valid or does not contain a valid path or 
     *             the context instance creation failed.
     *            
     */
    protected void createContext() throws Exception {
        if (server == null) {
            throw new IllegalStateException("Server has not been created");
        }
        if (context != null) {
            throw new IllegalStateException("Context has already been created");
        }
        //URI address = new URI(config.getConfigProperty(Configuration.HTTP_ADDRESS));
        String path = address.getPath();
        if (path == null) {
            Logger.log(Severity.FAILURE, 
                "The path you specified for the HTTP server is invalid."
            );                
            throw new ContextNotCreatedException();
        }
        context = server.createContext(path);
        // Add support for HTTP compression
        context.getFilters().add(new CompressionHttpFilter());
    }

}
