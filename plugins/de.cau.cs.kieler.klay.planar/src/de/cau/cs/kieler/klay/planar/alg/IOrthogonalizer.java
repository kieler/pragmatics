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
package de.cau.cs.kieler.klay.planar.alg;

import de.cau.cs.kieler.klay.planar.graph.IGraph;

/**
 * Interface for orthogonalization algorithms. Uses the Strategy design pattern to provide a common
 * interface for various implementations of algorithms for orthogonalization.
 * 
 * @author ocl
 */
public interface IOrthogonalizer {

    /**
     * This takes a planar graph (eventually with dummy nodes on edge crossings) and draws the graph
     * so that all edges are either horizontal or vertical.
     * 
     * @param graph
     *            the graph to draw as orthogonal graph
     */
    void orthogonalize(IGraph graph);

}
