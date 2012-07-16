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

import java.io.UnsupportedEncodingException;

/**
 * This class implements a web content provider for displaying the service meta data in HTML format.
 *
 * @author swe
 */
public class IndexProvider implements IDynamicWebContentProvider {

    //CHECKSTYLEOFF LineLength
    
    /** Prefix of the HTML content. */
    private static final String STATIC_PAGE
        = "<html>\n"
        + "<head>\n"
        + "<link rel='stylesheet' type='text/css' href='styles/styles.css'/>\n"
        + "</head>\n"
        + "<body>\n"
        + "<div align='center'>\n"
        + "<p>\n"
        + "<img src='images/kwebs.jpg'/>\n"
        + "</p>\n"
        + "<p>\n"
        + "<b><font size='+1'>KWebS - KIELER Web Service for Layout</font></b>\n"
        + "</p>\n"
        + "<p>\n"
        + "Please choose from the following actions what you want to do!\n"
        + "</p>\n"
        + "<p>\n"
        + "<a href='security/client.jks'>Download the client trust store for using HTTPS based layout</a>\n"
        + "</p>\n"
        + "<p>\n"
        + "<a href='^HttpServiceDescription^'>View the WSDL definition of the layout service</a>\n"
        + "</p>\n"
        + "<p>\n"
        + "<a href='/ServiceData.html'>Study the provided layout</a>\n"
        + "</p>\n"
        + "<p>\n"
        + "<a href='http://rtsys.informatik.uni-kiel.de/trac/kieler/wiki/Projects/KWebS'>Visit the project home page of KWebS at the KIELER trac</a>\n"
        + "</p>\n"
        + "</div>\n"
        + "</body>\n"
        + "</html>\n";
    
    //CHECKSTYLEON LineLength

    /**
     * Handles a HTTP request generating the index page.
     * 
     * @param requestData
     *            the request data holder
     */
    public void handleRequest(final RequestData requestData) {  
        try {            
            requestData.setContent(
                ValuesForPlaceholders.replacePlaceholders(STATIC_PAGE).getBytes("UTF-8")
            );
            requestData.setCharset("utf-8");
        } catch (UnsupportedEncodingException e) {
            // Nothing to do since exception is never thrown due to the fact
            // that utf-8 is required on every java runtime
        }
    }

}
