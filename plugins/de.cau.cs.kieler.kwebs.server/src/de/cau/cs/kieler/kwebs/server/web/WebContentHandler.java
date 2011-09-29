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

package de.cau.cs.kieler.kwebs.server.web;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EClass;
import org.osgi.framework.Bundle;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import de.cau.cs.kieler.kwebs.server.Application;
import de.cau.cs.kieler.kwebs.server.logging.Logger;
import de.cau.cs.kieler.kwebs.server.logging.Logger.Severity;
import de.cau.cs.kieler.kwebs.util.Resources;

/**
 * HTTP Handler for handling the servers web interface.
 *
 * @kieler.rating  2011-05-04 red
 * @author  swe
 */
public class WebContentHandler implements HttpHandler {
    
    /** Document root inside server plug in for static web content. */
    private static final String WEBCONTENT_ROOT
        = "server/kwebs/web";
    
    /** Additional MIME types needed. */    
    private static Map<String, String> mimeTypes
        = new HashMap<String, String>();
    
    /** Initializing additional MIME types. */
    static {
        mimeTypes.put("css", "text/css");
    }

    /** Base package for classes providing dynamic web content. */
    private static final String DYNAMIC_BASEPACKAGE
        = "de.cau.cs.kieler.kwebs.server.web";

    /** Cached instances of dynamic web content providing classes. */    
    private Map<String, IDynamicWebContentProvider> dynamicWebContentProviders
        = new HashMap<String, IDynamicWebContentProvider>();
    
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
        // The context under which this handler is registered
        String context = exchange.getHttpContext().getPath();
        // The URI for the requested resource. It begins with the 
        // context under which this handler is registered
        String uri = exchange.getRequestURI().toString();
        // The relative reference, e.g. URI without the path at the beginning
        String reference = null;
        // Query parameter
        String query = null;
        // The name of the requested resource
        String name = null;
        // The extension of the requested resource
        String ext = null;
        // The MIME type of the requested resource
        String type = null;
        // Determining the above values
        try {
            reference = uri.substring(context.length());
            if (reference.startsWith("/")) {
                reference = reference.substring(1);            
            }
            if (reference.indexOf("?") > -1) {
                if (reference.indexOf("?") < reference.length() - 1) {
                    query = reference.substring(reference.indexOf("?") + 1);
                }
                reference = reference.substring(0, reference.indexOf("?"));
            }
            name = new File(reference).getName();
            type = URLConnection.guessContentTypeFromName(name);
            ext = reference.substring(reference.lastIndexOf(".") + 1);
            if (type == null) {
                type = mimeTypes.get(ext);
            }
            if (type == null) {
                type = "application/octet-stream";
            }
        } catch (Exception e) {
            Logger.log(
                Severity.WARNING, "Invalid request received for web content: " + uri, 
                e
            );   
        }
        // Do forward to index page on invalid request
        if (reference == null || reference.length() == 0) {
            Logger.log(Severity.INFO, "Forwarding request to index page");
            forward(exchange, "index.html");
            return;
        }
        // Read the resources content
        byte[] data = null;
        if (reference != null) {
            // Static content ?
            try {
                data = Resources.readFileOrPluginResourceAsByteArray(
                    Application.PLUGIN_ID, WEBCONTENT_ROOT + "/" + reference
                );
            } catch (Exception e) {
                // Ignore Exception and try to retrieve
                // dynamic content instead
            }
            // If the request points to a non existing static resource,
            // deliver dynamic content
            if (data == null) {
                String providerName = (DYNAMIC_BASEPACKAGE + "." + reference).replace("/", ".");
                if (providerName.toLowerCase().endsWith(".html")) {
                    //CHECKSTYLEOFF MagicNumber
                    providerName = providerName.substring(0, providerName.length() - 5) + "Provider";
                    //CHECKSTYLEON MagicNumber
                    if (!dynamicWebContentProviders.containsKey(providerName)) {
                        try {
                            Bundle contributor 
                                = Platform.getBundle(Application.PLUGIN_ID);
                            IDynamicWebContentProvider contentProvider = (IDynamicWebContentProvider)
                                  (contributor.loadClass(providerName).newInstance());
                            dynamicWebContentProviders.put(providerName, contentProvider);
                        } catch (Exception e) {
                            // No content provider available for the given request.
                            // Exception is not handled here, but the requestor is
                            // notified with a HTTP 404 code.
                        }
                    }
                    IDynamicWebContentProvider contentProvider = 
                        dynamicWebContentProviders.get(providerName);
                    if (contentProvider != null) {
                        try {
                            RequestData requestData = new RequestData(
                                exchange,
                                reference,
                                "text/html", //default content is HTML, possibly overridden by handler
                                queryToMap(query)
                            );
                            contentProvider.handleRequest(requestData);
                            data = requestData.getContent();
                            type = requestData.getMimetype();
                        } catch (Exception e) {
                            Logger.log(
                                Severity.FAILURE,
                                "Error occured in web content handler "
                                + contentProvider.getClass().getSimpleName()
                                + ": " + e.getMessage(),
                                e
                            );
                        }
                    }
                }
            }
            // If no static or dynamic content is available, the request is invalid
            if (data == null) {
                Logger.log(Severity.WARNING, "Invalid resource request: " + uri);
            }
        }
        // Build the HTTP response
        Headers headers = exchange.getResponseHeaders();
        int responseLength = 0;
        int responseCode = HttpURLConnection.HTTP_OK;
        if (data != null && type != null) {    
            headers.add("Content-type", type);
            if (type == "application/octet-stream") {
                headers.add("Content-Disposition", "attachment; filename=" + name);                
                headers.add("Content-Transfer-Encoding", "binary");
            }
        } else {
            data = ("<b>Resource '" + reference + "' does not exist.</b>").getBytes();
            headers.add("Content-Type", "text/html");
            responseCode = HttpURLConnection.HTTP_NOT_FOUND;            
        }    
        if (data != null) {
            responseLength = data.length;
        }
        // Send the response
        exchange.sendResponseHeaders(responseCode, responseLength);        
        OutputStream os = exchange.getResponseBody();
        os.write(data);
        os.close();
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
        String forward = exchange.getHttpContext().getPath()
                         + "/" + uri;
        Headers headers = exchange.getResponseHeaders();
        headers.add("Location", forward);
        exchange.sendResponseHeaders(HttpURLConnection.HTTP_MOVED_PERM, 0);
        exchange.getResponseBody().close();
    }
    
    // Utility methods
    
    /**
     * Returns a map into which the key/value-pairs from a HTTP-query string are mapped.
     *  
     * @param query
     *            the query string
     * @return a map into which the key/value-pairs from a HTTP-query string are mapped.
     */
    public Map<String, String> queryToMap(final String query) {
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
    
}
