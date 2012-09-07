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

import java.net.HttpURLConnection;
import java.util.Map;

import com.sun.net.httpserver.HttpExchange;

/**
 * Holder class for the exchange of data between a dynamic web content provider
 * and the dispatch implementation for a HTTP request.
 *
 * @author  swe
 */
public class ResourceProcessingExchange {

    /** The {@link HttpExchange} instance delivered by the HTTP server. */
    private HttpExchange exchange;
    
    /** The path of the resource requested. */
    private String resource;

    /** The query parameters of the HTTP request. */
    private Map<String, String> params;
    
    /** Whether the generated response content is cacheable or not. */
    private boolean isResourceCacheable
        = true;
    
    /** The result code as signaled by the provider. Defaulted to 200 (OK). */
    private int resultCode
        = HttpURLConnection.HTTP_OK;
    
    /** */
    private final ResourceInformation resourceInformation
        = new ResourceInformation();

    //////////
    
    /**
     * 
     * @param exchange
     * @param resource
     * @param params
     */
    public ResourceProcessingExchange(final HttpExchange exchange,
            final String resource, final Map<String, String> params) {
        this.exchange = exchange;
        this.resource = resource;
        this.params = params;
    }

    //////////
    
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
    public boolean isResourceCacheable() {
        return isResourceCacheable;
    }

    /**
     * Sets whether the generated content is cacheable.
     * 
     * @param isResourceCacheable
     *            whether the generated content is cacheable
     */
    public void setResourceCacheable(final boolean isResourceCacheable) {
        this.isResourceCacheable = isResourceCacheable;
    }
    
    /**
     * Returns the result code that should be transmitted in the HTTP response.
     * 
     * @return the result code that should be transmitted in the HTTP response
     */
    public int getResultCode() {
        return resultCode;
    }

    /**
     * Sets the result code that should be transmitted in the HTTP response.
     * 
     * @param resultCode 
     *            the result code that should be transmitted in the HTTP response
     */
    public void setResultCode(final int resultCode) {
        this.resultCode = resultCode;
    }
    
    /**
     * 
     * @return
     */
    public ResourceInformation getResourceInformation() {
        return resourceInformation;
    }
    
}
