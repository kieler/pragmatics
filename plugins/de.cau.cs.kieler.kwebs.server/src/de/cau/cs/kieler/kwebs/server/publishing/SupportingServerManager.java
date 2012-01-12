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

import java.net.InetSocketAddress;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.Executors;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import de.cau.cs.kieler.kwebs.server.configuration.Configuration;
import de.cau.cs.kieler.kwebs.server.logging.Logger;
import de.cau.cs.kieler.kwebs.server.logging.Logger.Severity;

/**
 * This class implements a HTTP server which can be used for different kinds of support for
 * the layout service. A {@link HttpHandler} can be registered via extension point.
 *
 * @kieler.rating  2011-08-25 yellow
 *      reviewed by ckru, msp, mri
 *      
 * @author  swe
 */
public class SupportingServerManager extends AbstractServerManager {

    /** Default host for preview images. */
    private static final String SUPPORTINGSERVER_DEFAULTHOST
        = "0.0.0.0";

    /** Default port for preview images. */
    private static final int SUPPORTINGSERVER_DEFAULTPORT
        = 8444;

    /** The HTTP server instance which provides the images. */
    private HttpServer server;
    
    /** The contexts under which the diverse support handlers are published. */
    private Vector<HttpContext> contexts;
    
    /** The diverse handlers to be published indexed by their paths under which they get published. */
    private Map<String, HttpHandler> handlers
        = new HashMap<String, HttpHandler>();
    
    /** The extension point ID. */
    public static final String EXTENSIONPOINT_ID
        = "de.cau.cs.kieler.kwebs.server.configuration";
    
    /** The name of supportHandler element. */
    public static final String ELEMENT_SUPPORTHANDLER
        = "supportHandler";

    /** The name of the path attribute. */
    public static final String ATTRIBUTE_PATH
        = "path";

    /** The name of the implementation attribute. */
    public static final String ATTRIBUTE_IMPLEMENTATION
        = "implementation";

    /** The name of the publish attribute. */
    public static final String ATTRIBUTE_PUBLISH
        = "publish";
    
    /**
     * Constructs a new manager for the support server. Reads all registered handlers
     * from the extension point.
     */
    public SupportingServerManager() {
        String path = null;
        String implementation = null;        
        String publish = null;
        Bundle contributor = null;
        for (IConfigurationElement element : getHandlerConfigurationElements()) {
            if (element.getName().equals(ELEMENT_SUPPORTHANDLER)) {
                path = element.getAttribute(ATTRIBUTE_PATH);
                implementation = element.getAttribute(ATTRIBUTE_IMPLEMENTATION);
                publish = element.getAttribute(ATTRIBUTE_PUBLISH);
                if (publish == null || publish.equalsIgnoreCase("true")) {
                    if (path != null && path.length() > 0) {
                        if (implementation != null && implementation.length() > 0) {
                            try {
                                contributor = Platform.getBundle(element.getContributor().getName());
                                HttpHandler handler = contributor.loadClass(implementation).
                                    asSubclass(HttpHandler.class).newInstance();
                                if (!path.startsWith("/")) {
                                    path = "/" + path;
                                }
                                handlers.put(path, handler);
                            } catch (Exception e) {
                                Logger.log(
                                    Severity.FAILURE, 
                                    "Handler class could not be instantiated: " + implementation,
                                    e
                                );
                            }
                        } else {
                            Logger.log(
                                Severity.WARNING, 
                                "Implementation attribute of support handler invalid, ignoring handler."
                            );
                        }
                    } else {
                        Logger.log(
                            Severity.WARNING, 
                            "Path attribute of support handler not valid, ignoring handler."
                        );
                    }
                }
            }
        }
    }
    
    /**
     * Returns all extension elements responsible for registering a handler to the support server.
     * 
     * @return all extension elements responsible for registering a handler to the support server.
     */
    public static IConfigurationElement[] getHandlerConfigurationElements() {
        IExtensionRegistry registry = Platform.getExtensionRegistry();
        return registry.getConfigurationElementsFor(EXTENSIONPOINT_ID);
    }
    
    /**
     * Publishes the support server. The given service object is not used in the support server.
     * 
     * @param serviceObject
     *            the published service object
     */
    public synchronized void publish(final Object serviceObject) {
        try {
            createServer();
            createContexts();
            server.start();
        } catch (Exception e) {
            Logger.log(Severity.CRITICAL, "Support server could not be published", e);
            if (server != null) {
                server.stop(1);
            }
            server = null;
            clearContexts();
            throw new NotPublishedException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    public synchronized void unpublish() {
        if (server != null) {
            server.stop(0);
        }
        server = null;
        clearContexts();
    }

    /**
     * Returns whether this managers server is published or not.
     * 
     * @return whether this managers server is published or not
     */
    public synchronized boolean isPublished() {
        return (server != null);
    }

    /**
     * 
     */
    private synchronized void createServer() {
        if (server != null) {
            throw new IllegalStateException("Support server has already been created");
        }
        try {
            URI address = new URI(Configuration.getInstance().
                getConfigProperty(Configuration.SUPPORTINGSERVER_ADDRESS));
            String host = address.getHost();
            if (host == null) {
                Logger.log(Severity.WARNING, 
                    "The host you specified for the support server is invalid."
                    + " Using default host " + SUPPORTINGSERVER_DEFAULTHOST + "."
                );
                host = SUPPORTINGSERVER_DEFAULTHOST;
            }
            int port = address.getPort();
            if (port == -1) {
                Logger.log(Severity.WARNING, 
                    "The port you specified for the support server is invalid."
                    + " Using default port " + SUPPORTINGSERVER_DEFAULTPORT + "."
                );
                port = SUPPORTINGSERVER_DEFAULTPORT;
            }
            server = HttpServer.create(
                new InetSocketAddress(host, port),
                Integer.parseInt(
                    Configuration.getInstance().getConfigProperty(Configuration.SERVER_BACKLOG)
                )
            );
            server.setExecutor(Executors.newFixedThreadPool(
                Integer.parseInt(config.getConfigProperty(Configuration.SERVER_POOLSIZE))
            ));
        } catch (Exception e) {
            Logger.log(Severity.CRITICAL, "Support server could not be created", e);
            if (server != null) {
                server.stop(0);
            }
            server = null;
            throw new ServerNotCreatedException(e);
        }
    }

    /**
     * 
     */
    private synchronized void createContexts() {
        if (server == null) {
            throw new IllegalStateException("Support server has not been created");
        }
        if (contexts != null) {
            throw new IllegalStateException("Contexts have already been created");
        }
        contexts = new Vector<HttpContext>();        
        HttpHandler httpHandler = null;
        for (String path : handlers.keySet()) {
            try {
                httpHandler = handlers.get(path); 
                Logger.log(
                    Severity.INFO, 
                    "Adding support context '" 
                    + path 
                    + "' for implementation '" 
                    + httpHandler.getClass().getSimpleName()
                    + "'"
                );
                contexts.add(server.createContext(path, httpHandler));
            } catch (Exception e) {
                Logger.log(
                    Severity.FAILURE, 
                    "Error while adding support handler '" 
                    + httpHandler.getClass().getName()
                    + "': " + e.getMessage(),
                    e
                );
            }
        }        
    }

    /**
     * 
     */
    private synchronized void clearContexts() {
        if (server != null && contexts != null) {
            for (HttpContext context : contexts) {
                server.removeContext(context);            
            }
            contexts = null;
        }
    }
    
}
