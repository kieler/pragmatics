/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.layered.components;

import java.util.List;

import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;

/**
 * Takes a list of layered graphs and combines them into a single graph, placing them according to some
 * algorithm.
 * 
 * @author cds
 */
interface IGraphPlacer {
    
    /**
     * Computes a proper placement for the given graphs and combines them into a single graph.
     * 
     * @param components the graphs to be combined.
     * @return a single graph containing the components.
     */
    LayeredGraph combine(final List<LayeredGraph> components);
    
}
