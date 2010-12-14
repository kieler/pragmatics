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

import java.util.LinkedList;
import java.util.List;

import de.cau.cs.kieler.core.util.Dependency;
import de.cau.cs.kieler.core.util.IDepending;
import de.cau.cs.kieler.core.util.Pair;

/**
 * Analyses that derive from this class are required to provide an id, name and
 * description. A category, strong and weak dependencies are optional.
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
    public abstract String getId();

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
     * Returns whether this analysis is a helper analysis.
     * 
     * @return true if this analysis is a helper analysis
     */
    public boolean isHelper() {
        return false;
    }

    /**
     * Returns the analysis category.
     * 
     * @return the category id
     */
    public String getCategory() {
        return null;
    }

    /**
     * Returns the components, in the form of name/abbreviation pairs, the
     * results of this analysis consist of.
     * 
     * @return the components
     */
    public List<Pair<String, String>> getComponents() {
        return new LinkedList<Pair<String, String>>();
    }

    /**
     * {@inheritDoc}
     */
    public List<Dependency<String>> getDependencies() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public String toString() {
        return getName();
    }
}
