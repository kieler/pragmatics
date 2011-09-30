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

    /** . */
    private String name;
    
    /** . */
    private String mimetype;
    
    /** . */
    private byte[] content;
    
    /**
     * 
     * @param theexchange
     * @param thereference
     * @param themimetype
     * @param theparams
     */
    public CacheData(final String thename, final String themimetype, final byte[] thecontent) {
        name = thename;
        mimetype = themimetype;
        content = thecontent;
    }

    /**
     * 
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param resource
     */
    public void setName(final String name) {
        this.name = name;
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
    public void setMimetype(final String mimetype) {
        this.mimetype = mimetype;
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
    public void setContent(final byte[] content) {
        this.content = content;
    }
        
}
