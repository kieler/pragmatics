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
package de.cau.cs.kieler.keg.diagram.custom.random;

import de.cau.cs.kieler.core.properties.IPropertyHolder;
import de.cau.cs.kieler.keg.Node;

/**
 * The interface for KEG random graph generators.
 * 
 * @author mri
 * @kieler.ignore (excluded from review process)
 */
public interface IRandomGraphGenerator {

    /**
     * Generates a graph using the specified options.
     * 
     * @param options
     *            the options
     * @return the graph
     */
    Node generate(final IPropertyHolder options);
}
