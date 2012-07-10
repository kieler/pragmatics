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
package de.cau.cs.kieler.klay.layered;

import de.cau.cs.kieler.core.alg.IAlgorithm;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;

/**
 * A layout processor processes a {@link de.cau.cs.kieler.klay.layered.graph.LayeredGraph},
 * performing layout related tasks on it.
 *
 * @see LayeredLayoutProvider
 * @author cds
 * @kieler.rating 2012-07-10 proposed yellow msp
 */
public interface ILayoutProcessor extends IAlgorithm {
    
    /**
     * Performs the phase's work on the given graph.
     * 
     * @param layeredGraph a layered graph
     */
    void process(LayeredGraph layeredGraph);
    
}
