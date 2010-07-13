/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.grana;

import java.util.List;

/**
 * Analyses that derive from this class are required to provide an id, name and
 * description.
 * 
 * @author mri
 */
public abstract class AbstractInfoAnalysis implements IAnalysis,
        IDepending<String> {

    /**
     * Returns the analysis id. Has to be unique among all provided analyses.
     * 
     * @return the id
     */
    public abstract String getID();

    /**
     * Returns the analysis name.
     * 
     * @return the name
     */
    public abstract String getName();

    /**
     * Returns the analysis description.
     * 
     * @return the description
     */
    public abstract String getDescription();

    /**
     * Returns the analysis category.
     * 
     * @return the category id
     */
    public String getCategory() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public List<String> getDependencies() {
        return null;
    }
}
