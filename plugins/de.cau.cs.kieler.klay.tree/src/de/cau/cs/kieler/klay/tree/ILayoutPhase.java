/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.tree;

import de.cau.cs.kieler.klay.tree.graph.TGraph;

/**
 * A layout phase is a special kind of layout processor that encapsulates an implementation of one
 * of the algorithm's four main phases. A layout phase also specifies a configuration for the
 * intermediate layout processors that it wants to have executed in between layout phases (remember
 * possible dependencies).
 * 
 * @author sor
 * @author sgu
 * @author cds
 */
public interface ILayoutPhase extends ILayoutProcessor {

    /**
     * Returns the intermediate layout processors this phase depends on.
     * 
     * @param tGraph
     *            the tree graph to be processed. The configuration may vary depending on certain
     *            properties of the graph.
     * @return intermediate processing configuration. May be {@code null}.
     */
    IntermediateProcessingConfiguration getIntermediateProcessingConfiguration(final TGraph tGraph);

}
