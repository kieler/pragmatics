/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml;

/**
 * Interface for data holder classes that describe the layout infrastructure.
 *
 * @kieler.design 2011-02-01 reviewed by cmot, soh
 * @kieler.rating proposed yellow 2012-07-10 msp
 * @author msp
 */
public interface ILayoutData {
    
    /**
     * Returns the identifier.
     * 
     *  @return the identifier
     */
    String getId();
    
    /**
     * Sets the identifier.
     *
     * @param id the identifier to set
     */
    void setId(String id);
    
    /**
     * Returns the name.
     *
     * @return the name
     */
    String getName();
    
    /**
     * Sets the name.
     *
     * @param name the name to set
     */
    void setName(String name);
    
    /**
     * Returns the description.
     *
     * @return the description
     */
    String getDescription();
    
    /**
     * Sets the description.
     *
     * @param description the description to set
     */
    void setDescription(String description);

}
