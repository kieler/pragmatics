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
package de.cau.cs.kieler.klay.layered.modules;

import de.cau.cs.kieler.core.alg.IAlgorithm;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;

// CHECKSTYLEOFF JavadocStyle

/**
 * Interface for crossing minimization modules.
 * <dl>
 *   <dt>Precondition:</dt><dd>the graph has a proper layering, i.e. all
 *     long edges have been splitted</dd>
 *   <dt>Postcondition:</dt><dd>the order of nodes in each layer and the order
 *     of ports in each node are optimized to yield as few edge crossings
 *     as possible</dd>
 * </dl>
 *
 * @author msp
 */
public interface ICrossingMinimizer extends IAlgorithm {

    /**
     * Minimize the number of edge crossings in the given layered graph
     * by reordering the nodes of each layer.
     * 
     * @param layeredGraph a layered graph
     */
    void minimizeCrossings(LayeredGraph layeredGraph);
    
}
