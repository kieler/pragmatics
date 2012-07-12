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
 * and the dispatch implementation for a HTTP request.
 *
 * @author swe
 */
public class RequestData extends CacheData {

    /** The {@link HttpExchange} instance delivered by the HTTP server. */
    private HttpExchange exchange;
    
    /** The path of the resource requested. */
    private String resource;

    /** The query parameters of the HTTP request. */
    private Map<String, String> params;
    
    /** Whether the generated response content is cacheable or not. */
    private boolean cacheable
        = true;
    
    /**
     * 
     * @param theexchange
     * @param theresource
     * @param thename
     * @param themimetype
     * @param theparams
     */
    public RequestData(final HttpExchange theexchange, final String theresource, final String thename, 
        final String themimetype, final Map<String, String> theparams) {
        super(thename, themimetype, null);
        exchange = theexchange;
        resource = theresource;
        setMimetype(themimetype);
        params = theparams;
    }
    
    /**
     * Returns the {@link HttpExchange} associated with the request.
     * 
     * @return the {@link HttpExchange} associated with the request
     */
    public HttpExchange getExchange() {
        return exchange;
    }

    /**
     * Sets the {@link HttpExchange} associated with the request.
     * 
     * @param exchange
     *            the {@link HttpExchange} associated with the request
     */
    public void setExchange(final HttpExchange exchange) {
        this.exchange = exchange;
    }

    /**
     * Returns the path of the resource associated with the request.
     * 
     * @return the path of the resource associated with the request
     */
    public String getResource() {
        return resource;
    }

    /**
     * Sets the path of the resource associated with the request.
     * 
     * @param resource
     *            the path of the resource associated with the request  
     */   
    public void setResource(final String resource) {
        this.resource = resource;
    }

    /**
     * Returns the query parameters of the resource associated with the request.
     * 
     * @return the query parameters of the resource associated with the request
     */
    public Map<String, String> getParams() {
        return params;
    }

    /**
     * Sets the query parameters of the resource associated with the request.
     * 
     * @param params
     *            the query parameters of the resource associated with the request
     */
    public void setParams(final Map<String, String> params) {
        this.params = params;
    }


    /**
     * Returns whether the generated content is cacheable.
     * 
     * @return whether the generated content is cacheable
     */
    public boolean getCacheable() {
        return cacheable;
    }

    /**
     * Sets whether the generated content is cacheable.
     * 
     * @param cacheable
     *            whether the generated content is cacheable
     */
    public void setCacheable(final boolean cacheable) {
        this.cacheable = cacheable;
    }
        
    /**
     * Returns a cacheable representation of this holder instance.
     * 
     * @return a cacheable representation of this holder instance
     */
    public CacheData toCacheData() {
        return new CacheData(getName(), getMimetype(), getContent());
    }
    
}
