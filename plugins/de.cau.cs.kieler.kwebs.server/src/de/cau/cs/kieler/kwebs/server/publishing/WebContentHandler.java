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

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import de.cau.cs.kieler.kwebs.server.Application;
import de.cau.cs.kieler.kwebs.server.logging.Logger;
import de.cau.cs.kieler.kwebs.server.logging.Logger.Severity;
import de.cau.cs.kieler.kwebs.util.Resources;

/**
 * HTTP Handler for delivering preview images.
 *
 * @kieler.rating  2011-05-04 red
 * @author  swe
 */
public class WebContentHandler implements HttpHandler {
    
    /** */
    private static final String WEBCONTENT_ROOT
        = "server/kwebs/web";
    
    /** */    
    private static Map<String, String> mimeTypes
        = new HashMap<String, String>();
    
    static {
        mimeTypes.put("css", "text/css");
    }
    
    /**
     * Handles requests for preview images.
     * 
     * @param exchange
     *            the {@link HttpExchange} to be handled
     * @throws IOException
     *             when an exception occurs in handling the exchange
     */
    public void handle(final HttpExchange exchange) throws IOException {
        String path = exchange.getHttpContext().getPath();
        String uri = exchange.getRequestURI().toString();
        String reference = null;
        String name = null;
        String ext = null;
        String type = null;
        try {
            reference = uri.substring(path.length() + 1);
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
        if (reference == null || reference.length() == 0) {
            Logger.log(Severity.INFO, "Forwarding request to index page");
            forward(exchange, "index.html");
            return;
        }
        byte[] data = null;
        if (reference != null) {          
            data = Resources.readStreamAsByteArray(
                Resources.getResourceStream(Application.PLUGIN_ID, WEBCONTENT_ROOT + "/" + reference)
            );
        }
        /*System.out.println(
            reference 
            + " : " + type 
            + " (" + (data != null ? data.length : "null") + ")"
        );*/   
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
    
}
