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

/**
 * Analyses that derive from this class can define dependencies without using
 * the Dependency class.
 * 
 * @author mri
 */
public abstract class AbstractSimpleInfoAnalysis extends AbstractInfoAnalysis {

    /**
     * Returns a list of dependency ids which are interpreted as strong
     * dependencies.
     * 
     * @return the list of dependency ids
     */
    public abstract List<String> getDependencyIds();
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Dependency<String>> getDependencies() {
        List<Dependency<String>> dependencies = new LinkedList<Dependency<String>>();
        for (String id : getDependencyIds()) {
            dependencies.add(new Dependency<String>(id));
        }
        return dependencies;
    }
}
