/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2008 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klodd.hierarchical.modules;

import de.cau.cs.kieler.core.alg.IAlgorithm;
import de.cau.cs.kieler.klodd.hierarchical.structures.Layer;

/**
 * Interface for algorithms that offer heuristics for the 2-layer and 3-layer
 * crossing reduction problem.
 * 
 * @kieler.rating 2009-12-11 proposed yellow msp
 * @author msp
 */
public interface ISingleLayerCrossingReducer extends IAlgorithm {

    /**
     * Reduce crossings by changing the order of nodes in the given layer. Only
     * one other layer is considered for crossings reduction.
     * 
     * @param layer layer to be ordered
     * @param forward if true, connections go to the given layer, else from the
     *            given layer
     */
    void reduceCrossings(Layer layer, boolean forward);

    /**
     * Reduce crossings by changing the order of nodes in the given layer. Both
     * the preceding and the succeeding layers are considered for crossings
     * reduction.
     * 
     * @param layer layer to be ordered
     */
    void reduceCrossings(Layer layer);

}
