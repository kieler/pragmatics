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

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import de.cau.cs.kieler.kwebs.server.layout.ServerLayoutDataService;
import de.cau.cs.kieler.kwebs.server.logging.Logger;
import de.cau.cs.kieler.kwebs.server.logging.Logger.Severity;

/**
 * HTTP Handler for delivering preview images.
 *
 * @kieler.rating  2011-05-04 red
 * @author  swe
 */
public class PreviewImageHandler implements HttpHandler {
    
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
        String index = null;
        String type = null;
        try {
            index = uri.substring(path.length() + 1);
            type = index.substring(index.lastIndexOf(".") + 1).toLowerCase();
        } catch (Exception e) {
            Logger.log(
                Severity.WARNING, "Invalid request received for preview image: " + uri, 
                e
            );
        }
        byte[] data = null;
        if (index != null && type != null) {                            
            data = ServerLayoutDataService.getInstance().getPreviewImage(index);
        }
        if (data != null) {
            exchange.getResponseHeaders().add("Content-type", "image/" + type);     
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, data.length);
        } else {
            data = ("<b>Resource '" + index + "' does not exist.</b>").getBytes();
            exchange.getResponseHeaders().add("Content-type", "text/html");     
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_NOT_FOUND, data.length);  
            
        }                        
        OutputStream os = exchange.getResponseBody();
        os.write(data);
        os.close();
    }        

}
