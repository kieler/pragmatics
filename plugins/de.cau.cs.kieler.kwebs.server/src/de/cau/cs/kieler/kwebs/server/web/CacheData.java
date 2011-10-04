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

/**
 * Holder class for caching already processed requests to the web interface of KWebS.
 *
 * @kieler.rating  2011-05-04 red
 * @author  swe
 */
public class CacheData {

    /** Name of the cached resource. Needed for sending content as downloadable attachment. */
    private String name;
    
    /** The MIME type of the resource. */
    private String mimetype;
    
    /** The content of the resource. */
    private byte[] content;
    
    /**
     * Constructs a data container intended for caching of at runtime of the layout service
     * generated web content.
     * 
     * @param thename
     *            the name of the resource
     * @param themimetype
     *            the MIME type of the resource
     * @param thecontent
     *            the content of the resource
     */
    public CacheData(final String thename, final String themimetype, final byte[] thecontent) {
        name = thename;
        mimetype = themimetype;
        content = thecontent;
    }

    /**
     * Returns the name of the cached resource.
     * 
     * @return the name of the cached resource
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the cached resource.
     * 
     * @param resource
     *            the name of the cached resource.
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Returns the MIME type of the cached resource.
     * 
     * @return the name of the cached resource
     */
    public String getMimetype() {
        return mimetype;
    }

    /**
     * Sets the MIME type of the cached resource.
     * 
     * @param mimetype
     *            the MIME type of the cached resource
     */
    public void setMimetype(final String mimetype) {
        this.mimetype = mimetype;
    }

    /**
     * Returns the content of the cached resource.
     * 
     * @return the content of the cached resource.
     */
    public byte[] getContent() {
        return content;
    }

    /**
     * Sets the content of the cached resource.
     * @param content
     *            the content of the cached resource.
     */
    public void setContent(final byte[] content) {
        this.content = content;
    }
        
}
