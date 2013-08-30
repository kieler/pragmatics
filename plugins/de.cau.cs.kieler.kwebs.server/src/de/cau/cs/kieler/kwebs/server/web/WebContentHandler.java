/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
 *     + Department of Computer Science
 *         + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */

package de.cau.cs.kieler.kwebs.server.web;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import de.cau.cs.kieler.kwebs.server.Application;
import de.cau.cs.kieler.kwebs.server.configuration.Configuration;
import de.cau.cs.kieler.kwebs.server.logging.Logger;
import de.cau.cs.kieler.kwebs.server.logging.Logger.Severity;
import de.cau.cs.kieler.kwebs.util.Resources;

/**
 * HTTP Handler for handling the servers web interface.
 *
 * @author swe
 */
//ToDo: condider result code from providers
public class WebContentHandler implements HttpHandler {

    /** Document root inside server plug in for static web content. */
    private static final String WEBCONTENT_ROOT
        = "server/kwebs/web";

    /** Base package for classes providing dynamic web content. */
    private static final String DYNAMIC_BASEPACKAGE
        = "de.cau.cs.kieler.kwebs.server.web";

    /** Cached instances of dynamic web content providing classes. */
    private Map<String, IDynamicWebContentProvider> dynamicWebContentProviders
        = new HashMap<String, IDynamicWebContentProvider>();

    /** Cache of already generated, cacheable resources. */
    private Map<URI, ResourceInformation> cachedResources
        = new HashMap<URI, ResourceInformation>();

    /** Whether resources can be cached or not. */
    private final boolean cachingEnabled
        = !Configuration.INSTANCE.getConfigPropertyAsBoolean(
            Configuration.FRONTEND_DISABLE_CACHING, false
        );

    /**
     * This method handles requests for static and dynamic contents of the servers web page. At first it
     * tries to resolve the request to a statically linked resource within the servers plug in. If no
     * such resource exists, a suitable handler for dynamic creation of the content is searched. If it
     * can not be found either, the request fails and a HTTP 404 (not found) code is returned to the
     * requestor.
     *
     * @param exchange
     *            the {@link HttpExchange} to be handled
     * @throws IOException
     *             when an exception occurs in handling the exchange
     */

    public void handle(final HttpExchange exchange) throws IOException {
        
        URI                 uri                 = exchange.getRequestURI();
        ResourceInformation resourceInformation = null;

        // Trying to get already cached content. If none available, call 
        // the appropriate request handler and cache the result, if allowed.

        if (cachingEnabled) {
            resourceInformation = cachedResources.get(uri);
        }
        
        // This request has not been processed before or caching is disabled
        if (resourceInformation == null) {

            ResourceProcessingExchange processingExchange 
                = buildResourceProcessingExchange(exchange);
            
            // Do forward to index page on invalid request
            if (processingExchange.getResource() == null 
                    || processingExchange.getResource().length() == 0) {
            
                Logger.log(Severity.INFO, "Forwarding request to index page");
                
                forward(exchange, "index.html");
                
                return;
                    
            }
            
            if (handleStaticResource(processingExchange) 
                    || handleGeneratedResource(processingExchange)) {
                                    
                // Do caching only if the generated response is valid, 
                // meaning the HTTP result code is 200 (OK)
                
                if (processingExchange.getResultCode() == HttpURLConnection.HTTP_OK) { 
                
                    resourceInformation = processingExchange.getResourceInformation();
                    
                    if (processingExchange.isResourceCacheable()) {
                        cachedResources.put(uri, resourceInformation);
                    }
                    
                }
                
            } else {
                
                Logger.log(Severity.INFO, "Invalid request: " + uri.toString());
                
                notfound(exchange);
                
                return;
                
            }
                
        }
        
        // Check if we actually have a resource
        
        if (resourceInformation == null) {
            
            notfound(exchange);
            
            return;
            
        }
        
        // Build the response
        
        byte[]  content  = resourceInformation.getContent();
        String  mimetype = resourceInformation.getMimetype();
        String  charset  = resourceInformation.getCharset();
        Headers headers  = exchange.getResponseHeaders();
        
        int responseLength = 0;
        int responseCode   = HttpURLConnection.HTTP_OK;
        
        if (content != null && mimetype != null) {
        
            headers.add(
                "Content-type",
                mimetype
                + (charset != null ? ";charset=" + charset : "")
            );
            
            if (mimetype == "application/octet-stream") {
                
                headers.add(
                    "Content-Disposition", "attachment; filename=" + resourceInformation.getName()
                );
                
                headers.add("Content-Transfer-Encoding", "binary");
                
            }
            
            headers.putAll(resourceInformation.getAdditionalHeaders());
            
            responseLength = content.length;
            
        } else {
            
            notfound(exchange);
            
            return;
            
        }
                
        // Send the response
                
        exchange.sendResponseHeaders(responseCode, responseLength);
        
        OutputStream os = exchange.getResponseBody();

        os.write(content);
        os.close();
                
    }

    /**
     *
     * @param exchange
     * @return
     */
    private ResourceProcessingExchange buildResourceProcessingExchange(final HttpExchange exchange) {
        
        // The context under which this handler is registered
        String context = exchange.getHttpContext().getPath();
        
        // The URI for the requested resource. It begins with the
        // context under which this handler is registered
        String uri = exchange.getRequestURI().toString();
        
        // The requested resource, e.g. URI without the context at the beginning
        String resource = null;
        
        // Query parameter
        String query = null;
        
        // The name of the requested resource
        String name = null;
        
        // The MIME type of the requested resource
        String mimetype = null;
        
        // Determining the above values
        try {
            resource = uri.substring(context.length());
            if (resource.startsWith("/")) {
                resource = resource.substring(1);
            }
            if (resource.indexOf("?") > -1) {
                if (resource.indexOf("?") < resource.length() - 1) {
                    query = resource.substring(resource.indexOf("?") + 1);
                }
                resource = resource.substring(0, resource.indexOf("?"));
            }
            name = resource;
            if (name.lastIndexOf("/") > -1 && name.lastIndexOf("/") < name.length() - 1) {
                name = name.substring(name.lastIndexOf("/") + 1);
            }
            mimetype = guessMimeType(resource);
        } catch (Exception e) {
            Logger.log(
                Severity.WARNING, "Invalid request received for web content: " + uri,
                e
            );
        }
        
        final ResourceProcessingExchange processingExchange 
            = new ResourceProcessingExchange(exchange, resource, queryToMap(query));
        
        processingExchange.getResourceInformation().setName(name);
        processingExchange.getResourceInformation().setMimetype(mimetype);
        
        return processingExchange;
        
    }

    /**
     *
     * @param processingExchange
     * @return
     */
    private boolean handleStaticResource(final ResourceProcessingExchange processingExchange) {
        
        final ResourceInformation resourceInformation 
            = processingExchange.getResourceInformation();
        
        resourceInformation.setContent(null);
        
        try {
            resourceInformation.setContent(
                Resources.readFileOrPluginResourceAsByteArray(
                    Application.PLUGIN_ID, WEBCONTENT_ROOT + "/" + processingExchange.getResource()
                )
            );
            resourceInformation.setMimetype(guessMimeType(processingExchange.getResource()));
        } catch (Exception e) {
            // Ignore Exception, invalid resource is signaled by method result and handled by caller
        }
        
        return (resourceInformation.getContent() != null);
        
    }

    /**
     *
     * @param processingExchange
     * @return
     */
    private boolean handleGeneratedResource(final ResourceProcessingExchange processingExchange) {
        if (processingExchange.getResource() == null) {
            return false;
        }
        String pkg = processingExchange.getResource();
        String cls = processingExchange.getResource();
        String ext = "";
        int lastIndex = pkg.lastIndexOf("/");
        if (lastIndex > -1 && lastIndex < pkg.length() - 1) {
            cls = pkg.substring(lastIndex + 1);
            pkg = pkg.substring(0, lastIndex);
        } else {
            pkg = "";
        }
        int extIndex = cls.lastIndexOf(".");
        if (extIndex > -1 && extIndex < cls.length() - 1) {
            ext = cls.substring(extIndex + 1).toLowerCase();
            cls = cls.substring(0, extIndex);
        }
        if (!ext.equalsIgnoreCase("htm") && !ext.equalsIgnoreCase("html")) {
            return false;
        }
        pkg = (DYNAMIC_BASEPACKAGE + (pkg.length() > 0 ? "." : "") + pkg).replace("/", ".");
        cls = cls.substring(0, 1).toUpperCase() + cls.substring(1).toLowerCase() + "Provider";
        String providerName = pkg + "." + cls;
        if (!dynamicWebContentProviders.containsKey(providerName)) {
            try {
                Bundle contributor
                    = Platform.getBundle(Application.PLUGIN_ID);
                IDynamicWebContentProvider contentProvider = (IDynamicWebContentProvider)
                      (contributor.loadClass(providerName).newInstance());
                dynamicWebContentProviders.put(providerName, contentProvider);
            } catch (Exception e) {
                return false;
            }
        }
        IDynamicWebContentProvider contentProvider =
            dynamicWebContentProviders.get(providerName);
        if (contentProvider != null) {
            try {
                contentProvider.handleRequest(processingExchange);
                return true;
            } catch (Exception e) {
                Logger.log(
                    Severity.FAILURE,
                    "Error occured in web content handler "
                    + contentProvider.getClass().getSimpleName()
                    + ": " + e.getMessage(),
                    e
                );
                return false;
            }
        }
        return false;
    }

    /**
     * Forwards a request.
     *
     * @param exchange
     *            the HTTP exchange object
     * @param uri
     *            the address the request is to be forwarded to
     * @throws IOException
     *            if an exception occurs
     */
    private void forward(final HttpExchange exchange, final String uri) throws IOException {
        String forward = exchange.getHttpContext().getPath() + uri;
        Headers headers = exchange.getResponseHeaders();
        headers.add("Location", forward);
        exchange.sendResponseHeaders(HttpURLConnection.HTTP_MOVED_PERM, 0);
        exchange.getResponseBody().close();
    }

    /**
     * Reports a non existing resource.
     *
     * @param exchange
     *            the HTTP exchange object
     * @throws IOException
     *            if an exception occurs
     */
    private void notfound(final HttpExchange exchange) throws IOException {
        byte[] data = (
                          "<b>Resource '"
                          + exchange.getRequestURI().toString()
                          + "' does not exist.</b>"
                      ).getBytes();
        exchange.getResponseHeaders().add("Content-Type", "text/html");
        exchange.sendResponseHeaders(HttpURLConnection.HTTP_NOT_FOUND, data.length);
        OutputStream os = exchange.getResponseBody();
        os.write(data);
        os.close();
    }

    // Utility methods

    /**
     * Returns a map into which the key/value-pairs from a HTTP-query string are mapped.
     *
     * @param query
     *            the query string
     * @return a map into which the key/value-pairs from a HTTP-query string are mapped.
     */
    private Map<String, String> queryToMap(final String query) {
        Map<String, String> params = new HashMap<String, String>();
        if (query != null) {
            StringTokenizer st = new StringTokenizer(query, "&");
            while (st.hasMoreElements()) {
                try {
                    String element = st.nextToken();
                    String key = element.substring(0, element.indexOf("="));
                    String value = element.substring(element.indexOf("=") + 1);
                    params.put(key, value);
                } catch (Exception e) {
                    // Ignore invalid entries
                }
            }
        }
        return params;
    }

    /** Additional MIME types needed. */
    private static Map<String, String> mimeTypes
        = new HashMap<String, String>();

    /** Initializing additional MIME types. */
    static {
        mimeTypes.put("css", "text/css");
        mimeTypes.put("svg", "image/svg+xml");
    }

    /**
     * Method which guesses the MIME type of a resource. If no concrete MIME type could be guessed,
     * "application/octet-stream" is returned.
     *
     * @param resource
     *            resource for which the MIME type is to be guessed.
     * @return the MIME type.
     */
    public static String guessMimeType(final String resource) {
        return guessMimeType(resource, "application/octet-stream");
    }
    
    /**
     * Method which guesses the MIME type of a resource. If no concrete MIME type could be guessed,
     * the given default MIME type is returned.
     *
     * @param resource
     *            resource for which the MIME type is to be guessed.
     * @param defauld
     *            the default to be returned if no MIME type could be guessed.
     * @return the MIME type.
     */
    public static String guessMimeType(final String resource, final String defauld) {
        String type = null;
        try {
            String path = resource.replaceAll("\\\\", "/");
            if (path.lastIndexOf("#") > -1 && path.lastIndexOf("#") < path.length() - 1) {
                path = path.substring(0, path.lastIndexOf("#"));
            }
            if (path.lastIndexOf("?") > -1 && path.lastIndexOf("?") < path.length() - 1) {
                path = path.substring(0, path.lastIndexOf("?"));
            }
            String file = path;
            if (file.lastIndexOf("/") > -1 && file.lastIndexOf("/") < file.length() - 1) {
                file = file.substring(file.lastIndexOf("/") + 1);
            }
            String ext = "";
            if (file.lastIndexOf(".") > -1 && file.lastIndexOf(".") < file.length() - 1) {
                ext = file.substring(file.lastIndexOf(".") + 1);
            }
            type = URLConnection.guessContentTypeFromName(file);
            if (type == null) {
                type = mimeTypes.get(ext);
            }
        } catch (Exception e) {
            // Ignore, return default MIME type
        }
        return (type != null ? type : defauld);
    }

}
