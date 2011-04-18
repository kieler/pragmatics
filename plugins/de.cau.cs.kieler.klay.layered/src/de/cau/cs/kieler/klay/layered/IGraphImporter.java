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

import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;

/**
 * Interface for importer classes for the layered graph structure.
 * 
 * <p>Graph importers are encouraged to set the {@link Properties#GRAPH_PROPERTIES}
 * property on imported graphs.</p>
 *
 * @author msp
 */
public interface IGraphImporter {
    
    /**
     * Returns the layered graph that is managed by this graph importer.
     * 
     * @return the created layered graph
     */
    LayeredGraph getGraph();
    
    /**
     * Apply the computed layout of a layered graph to the original graph.
     * 
     * <dl>
     *   <dt>Precondition:</dt><dd>the graph has all its dummy nodes and edges removed;
     *     edges that were reversed during layout have been restored to their original
     *     orientation</dd>
     *   <dt>Postcondition:</dt><dd>none</dd>
     * </dl>
     */
    void applyLayout();

}
