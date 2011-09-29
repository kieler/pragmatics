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

import java.util.Map;

import com.sun.net.httpserver.HttpExchange;

/**
 * Holder class for the exchange of data between a dynamic web content provider
 * and the dispatch implementation.
 *
 * @kieler.rating  2011-05-04 red
 * @author  swe
 */
public class RequestData {

    /** . */
    private HttpExchange exchange;
    
    /** . */
    private String reference;
        
    /** . */
    private String mimetype;
    
    /** . */
    private Map<String, String> params;
    
    /** . */
    private byte[] content;

    /**
     * 
     * @param theexchange
     * @param thereference
     * @param themimetype
     * @param theparams
     */
    public RequestData(final HttpExchange theexchange, final String thereference, final String themimetype,
        final Map<String, String> theparams) {
        exchange = theexchange;
        reference = thereference;
        mimetype = themimetype;
        params = theparams;
    }
    
    /**
     * 
     * @return
     */
    public HttpExchange getExchange() {
        return exchange;
    }

    /**
     * 
     * @param exchange
     */
    public void setExchange(HttpExchange exchange) {
        this.exchange = exchange;
    }

    /**
     * 
     * @return
     */
    public String getReference() {
        return reference;
    }

    /**
     * 
     * @param reference
     */
    public void setReference(String reference) {
        this.reference = reference;
    }

    /**
     * 
     * @return
     */
    public String getMimetype() {
        return mimetype;
    }

    /**
     * 
     * @param mimetype
     */
    public void setMimetype(String mimetype) {
        this.mimetype = mimetype;
    }

    /**
     * 
     * @return
     */
    public Map<String, String> getParams() {
        return params;
    }

    /**
     * 
     * @param params
     */
    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    /**
     * 
     * @return
     */
    public byte[] getContent() {
        return content;
    }

    /**
     * 
     * @param content
     */
    public void setContent(byte[] content) {
        this.content = content;
    }
        
}
