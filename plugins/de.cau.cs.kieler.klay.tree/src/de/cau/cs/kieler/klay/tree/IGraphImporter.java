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
package de.cau.cs.kieler.klay.tree;

import de.cau.cs.kieler.klay.tree.graph.TGraph;

/**
 * Interface for importer classes for graph structure. Graph importers
 * should usually subclass
 * {@link de.cau.cs.kieler.klay.tree.KGraphImporter KGraphImporter}
 * instead of implementing this interface directly.
 * 
 * <p>Graph importers are encouraged to set the {@link Properties#GRAPH_PROPERTIES}
 * property on imported graphs.</p>
 *
 * @param <T> the type of graph that this importer can transform into a layered graph.
 * @author sor
 * @author sgu
 * @author msp
 */
public interface IGraphImporter<T> {
    
    /**
     * Create a t-graph from the given graph.
     * 
     * @param graph the graph to turn into a t-graph
     * @return a t-graph, or {@code null} if the input was not recognized
     */
    TGraph importGraph(T graph);
    
    /**
     * Apply the computed layout of a t-graph to the original graph.
     * 
     * @param tGraph the graph for which layout is applied
     */
    void applyLayout(TGraph tGraph);

}
