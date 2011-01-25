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

import java.util.LinkedList;
import java.util.List;

/**
 * Data type used to store information for a layout type.
 *
 * @kieler.rating 2011-01-24 proposed yellow msp
 * @author msp
 */
public class LayoutTypeData implements ILayoutData {
    
    /** default name for layout types for which no name is given. */
    public static final String DEFAULT_TYPE_NAME = "<Unnamed Type>";
    
    /** identifier of the layout type. */
    private String id = "";
    /** user friendly name of the layout type. */
    private String name = "";
    /** detail description. */
    private String description = "";
    /** the list of layout providers that are registered for this type. */
    private List<LayoutProviderData> layouters = new LinkedList<LayoutProviderData>();
    
    /**
     * {@inheritDoc}
     */
    public boolean equals(final Object obj) {
        if (obj instanceof LayoutTypeData) {
            return this.id.equals(((LayoutTypeData) obj).id);
        }
        return false;
    }
    
    /**
     * {@inheritDoc}
     */
    public int hashCode() {
        return id.hashCode();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        if (name != null && name.length() > 0) {
            if (name.endsWith(">")) {
                return name;
            } else {
                return name + " Type";
            }
        } else {
            return "Other";
        }
    }
    
    /**
     * Returns the list of layout providers that are registered for this type.
     * 
     * @return the layouters
     */
    public List<LayoutProviderData> getLayouters() {
        return layouters;
    }

    /**
     * Returns the layout type identifier.
     * 
     * @return the layout type identifier
     */
    public String getId() {
        return id;
    }
    
    /**
     * Sets the layout type identifier.
     * 
     * @param theid the identifier to set
     */
    public void setId(final String theid) {
        assert theid != null;
        this.id = theid;
    }
    
    /**
     * Returns the name of the layout type.
     * 
     * @return the name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Sets the name of the layout type.
     * 
     * @param thename the name to set
     */
    public void setName(final String thename) {
        if (thename == null || thename.length() == 0) {
            this.name = DEFAULT_TYPE_NAME;
        } else {
            this.name = thename;
        }
    }
    
    /**
     * Returns the description.
     * 
     * @return the description
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * Sets the description.
     * 
     * @param thedescription the description to set
     */
    public void setDescription(final String thedescription) {
        if (thedescription == null) {
            this.description = "";
        } else {
            this.description = thedescription;
        }
    }

}
