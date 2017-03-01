/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.formats;

import org.eclipse.elk.core.data.ILayoutMetaData;

/**
 * Data type used to store information for a graph format.
 *
 * @author msp
 * @kieler.design proposed by msp
 * @kieler.rating proposed yellow 2012-07-10 msp
 */
public class GraphFormatData implements ILayoutMetaData {

    /** the identifier string. */
    private String id;
    /** a user friendly name. */
    private String name;
    /** a user friendly description. */
    private String description;
    /** the graph transformation handler used for this format. */
    private IGraphFormatHandler<?> handler;
    /** the file extensions. */
    private String[] extensions = new String[0];
    
    /**
     * {@inheritDoc}
     */
    public String getId() {
        return id;
    }

    /**
     * {@inheritDoc}
     */
    public void setId(final String theid) {
        this.id = theid;
    }

    /**
     * {@inheritDoc}
     */
    public String getName() {
        return name;
    }

    /**
     * {@inheritDoc}
     */
    public void setName(final String thename) {
        this.name = thename;
    }

    /**
     * {@inheritDoc}
     */
    public String getDescription() {
        return description;
    }

    /**
     * {@inheritDoc}
     */
    public void setDescription(final String thedescription) {
        this.description = thedescription;
    }
    
    /**
     * Returns the graph format handler for this graph format.
     * 
     * @return the graph transformation handler
     */
    public IGraphFormatHandler<?> getHandler() {
        return handler;
    }
    
    /**
     * Sets the graph transformation handler.
     * 
     * @param thehandler the graph transformation handler
     */
    public void setHandler(final IGraphFormatHandler<?> thehandler) {
        this.handler = thehandler;
    }
    
    /**
     * Returns the file extensions for this graph format.
     * 
     * @return the file extensions
     */
    public String[] getExtensions() {
        return extensions;
    }
    
    /**
     * Sets the file extensions.
     * 
     * @param theextensions the file extensions
     */
    public void setExtensions(final String[] theextensions) {
        this.extensions = theextensions;
    }

}
