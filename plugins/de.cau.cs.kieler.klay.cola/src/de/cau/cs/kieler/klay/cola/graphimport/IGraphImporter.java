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
package de.cau.cs.kieler.klay.cola.graphimport;

import de.cau.cs.kieler.klay.cola.graph.CGraph;

/**
 * Interface for importer classes for the constrained graph structure.
 * 
 * 
 * @param <T>
 *            the type of graph that this importer can transform into a layered graph.
 * 
 * @author uru
 */
public interface IGraphImporter<T> {

    /**
     * Create a constrained graph from the given graph.
     * 
     * @param graph
     *            the graph to turn into a constrained graph.
     * @return a constrained graph, or {@code null} if the input was not recognized
     */
    CGraph importGraph(T graph);

    /**
     * Apply the computed layout of the given constrained graph to the original input graph.
     * 
     * 
     * @param constrainedGraph
     *            a graph for which layout is applied
     */
    void applyLayout(CGraph constrainedGraph);

}
