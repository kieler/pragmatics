/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.codaflow.graphimport;


/**
 * Interface for importer classes for the constrained graph structure.
 * 
 * @param <S> 
 *      the type of graph that this importer can transform into a graph of type T.
 * @param <T>
 *      the type of graph that this importer outputs.
 * 
 * @author uru
 */
public interface IGraphImporter<S, T> {

    /**
     * Create a constrained graph from the given graph.
     * 
     * @param graph
     *            the graph to turn into a constrained graph.
     * @return a constrained graph, or {@code null} if the input was not recognized
     */
    T importGraph(S graph);

    /**
     * Apply the computed layout of the given constrained graph to the original input graph.
     * 
     * 
     * @param constrainedGraph
     *            a graph for which layout is applied
     */
    void applyLayout(T constrainedGraph);

}
