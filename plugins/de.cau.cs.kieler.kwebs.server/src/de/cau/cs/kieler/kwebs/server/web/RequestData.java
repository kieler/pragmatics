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

import javax.activation.MimeType;

import com.sun.net.httpserver.HttpExchange;

/**
 * Holder class for the exchange of data between a dynamic web content provider
 * and the dispatch implementation.
 *
 * @kieler.rating  2011-05-04 red
 * @author  swe
 */
public class RequestData extends CacheData {

    /** . */
    private HttpExchange exchange;
    
    /** . */
    private String resource;

    /** . */
    private Map<String, String> params;
    
    /** . */
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
    public void setExchange(final HttpExchange exchange) {
        this.exchange = exchange;
    }

    /**
     * 
     * @return
     */
    public String getResource() {
        return resource;
    }

    /**
     * 
     * @param resource
     */
    public void setResource(final String resource) {
        this.resource = resource;
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
    public void setParams(final Map<String, String> params) {
        this.params = params;
    }


    /**
     * @return the cacheable
     */
    public boolean getCacheable() {
        return cacheable;
    }

    /**
     * @param cacheable the cacheable to set
     */
    public void setCacheable(final boolean cacheable) {
        this.cacheable = cacheable;
    }
        
    /**
     * 
     * @return
     */
    public CacheData toCacheData() {
        return new CacheData(getName(), getMimetype(), getContent());
    }
    
}
