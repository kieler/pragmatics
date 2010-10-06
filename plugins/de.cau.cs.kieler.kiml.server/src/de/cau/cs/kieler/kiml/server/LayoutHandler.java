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
import java.io.InputStream;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

/**
 *
 * @author msp
 */
public class LayoutHandler implements HttpHandler {

    /**
     * {@inheritDoc}
     */
    public void handle(final HttpExchange exchange) throws IOException {
        System.out.println(exchange.getRequestMethod());
        System.out.println(exchange.getRequestHeaders());
        InputStream in = exchange.getRequestBody();
        int c;
        while ((c = in.read()) != -1) {
            System.out.print(Character.forDigit(c, 10));
        }
        exchange.close();
    }

}
