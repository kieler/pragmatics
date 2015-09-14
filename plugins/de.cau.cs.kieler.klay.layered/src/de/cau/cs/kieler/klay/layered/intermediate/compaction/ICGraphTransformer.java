/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2015 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klay.layered.intermediate.compaction;

/**
 * Interface for classes that import a {@link CGraph}.
 * 
 * @param <T> the type of data structure that is transformed into a {@link CGraph}
 * 
 * @author dag
 */
public interface ICGraphTransformer<T> {
    /**
     * Transforms the input graph into a {@link CGraph} consisting of {@link CNode}s that may
     * be grouped in {@link CGroup}s.
     * 
     * @param inputGraph
     *          the graph to transform into a {@link CGraph}
     * @return a {@link CGraph}
     */
    CGraph transform(final T inputGraph);
    
    /**
     * Updates the properties of the input graph and applies the compacted positions to the
     * elements of the input graph.
     */
    void applyLayout();
    
    /**
     * Getter for the input graph.
     * 
     * @return the input graph
     */
    T getInputGraph();
}
