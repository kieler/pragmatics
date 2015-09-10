/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.test.config;

import de.cau.cs.kieler.core.kgraph.KNode;

/**
 * Allows to apply certain layout configurations to a graph.
 * 
 * @author uru
 */
public interface ILayoutConfigurator {

    /**
     * Add arbitrary layout options to the graph.
     * 
     * @param root
     *            the root node of the graph.
     */
    void applyConfiguration(final KNode root);

    /**
     * @return a short description that is appended to the JUnit test's name. Do <b>not</b> use
     *         parentheses, they are trimmed by JUnit.
     */
    String getDescription();

}
