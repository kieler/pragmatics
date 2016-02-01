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

package de.cau.cs.kieler.kwebs.server.service.filter;

import de.cau.cs.kieler.kwebs.server.alg.Segment;

/**
 * 
 * @author swe
 */
public abstract class LayoutFilter implements Segment<LayoutFilterData> {
    
    /** */
    public static final int DEFAULT_PRIORITY 
        = 0;
    
    /** */
    private int priority
        = DEFAULT_PRIORITY;
    
    /** */
    private String description;
    
    //////////
    
    /**
     * 
     */
    public LayoutFilter() {
    }
    
    /**
     * 
     * @param priority
     * @param description
     */
    public LayoutFilter(final int priority, final String description) {
        
        super();
        
        this.priority = priority;
        this.description = description;
        
    }

    //////////
    
    /**
     * 
     * @return
     */
    public int getPriority() {
        return priority;
    }

    /**
     * 
     * @param priority
     */
    public void setPriority(final int priority) {
        this.priority = priority;
    }

    /**
     * 
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @param description
     */
    public void setDescription(final String description) {
        this.description = description;
    }

}
