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
package de.cau.cs.kieler.klay.force;

import de.cau.cs.kieler.klay.force.graph.FGraph;

/**
 * Interface for importer classes for the force graph structure.
 *
 * @author msp
 */
public interface IGraphImporter {
    
    /**
     * Returns the force graph that is managed by this graph importer.
     * 
     * @return the created force graph
     */
    FGraph getGraph();
    
    /**
     * Apply the computed layout of a force graph to the original graph.
     */
    void applyLayout();

}
