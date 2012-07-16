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

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map.Entry;
import java.util.zip.GZIPInputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;

import com.sun.net.httpserver.Filter;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

import de.cau.cs.kieler.kwebs.server.logging.Logger;
import de.cau.cs.kieler.kwebs.server.logging.Logger.Severity;

/**
 * This {@code Filter} adds support for compression for the HTTP server used for publishing the
 * layout service. Currently only compressed HTTP requests are supported due to the fact that the
 * response stream is not available before the handler of the HTTP request uses 
 * {@code sendResponseHeaders(...)}. So even if the client accepts encoded HTTP responses, the server
 * is not capable of delivering them.  
 * 
 * @author swe
 */
public class CompressionHttpFilter extends Filter {

    /**
     * {@inheritDoc}
     */
    @Override
    public String description() {
        return "This filter adds support for gzip and deflate compression.";
    }

    /** HTTP header name for accepted encodings. */
    //private static final String HTTP_ACCEPT_ENCODING 
    //    = "Accept-encoding";
    
    /** HTTP header name for encoding of the body. */
    private static final String HTTP_CONTENT_ENCODING 
        = "Content-encoding";

    /** Constant for GZip encoding. */
    private static final String GZIP_ENCODING 
        = "gzip";

    /** Constant for Deflate encoding. */
    private static final String DEFLATE_ENCODING 
        = "deflate";
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void doFilter(final HttpExchange exchange, final Chain chain) throws IOException {    
        //dumpHeaders(exchange.getRequestHeaders());
        try { 
            InputStream inStream = exchange.getRequestBody();
            Headers requestHeaders = exchange.getRequestHeaders();
            List<String> contentEncoding = requestHeaders.get(HTTP_CONTENT_ENCODING);            
            if (contentEncoding != null) {
                if (contentEncoding.contains(GZIP_ENCODING)) {
                    inStream = new GZIPInputStream(exchange.getRequestBody());
                } else if (contentEncoding.contains(DEFLATE_ENCODING)) {
                    inStream = new InflaterInputStream(exchange.getRequestBody(), new Inflater(true));
                }
            }        
            exchange.setStreams(inStream, null);
        } catch (Exception e) {
            Logger.log(
                Severity.FAILURE,
                "Error while filtering HTTP request: "
                + e.getMessage(),
                e
            );
        }
        chain.doFilter(exchange);
    }    
    
    // Debug methods
    
    /**
     * Dumps the headers to screen.
     * 
     * @param headers
     *            the headers
     */
    @SuppressWarnings("unused") // Debug method may be used temporarily
    private void dumpHeaders(final Headers headers) {
        System.out.println("\nHTTP Headers:");        
        for (Entry<String, List<String>> entry : headers.entrySet()) {
            System.out.print("  " + entry.getKey() + " : ");
            for (String string : entry.getValue()) {
                System.out.print(string + "; ");
            }
            System.out.println();
        }
        System.out.println("\n");
    }
    
}
