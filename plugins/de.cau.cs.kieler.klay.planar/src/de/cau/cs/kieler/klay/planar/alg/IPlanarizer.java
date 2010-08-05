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

import java.util.List;

import de.cau.cs.kieler.core.alg.IAlgorithm;
import de.cau.cs.kieler.klay.planar.graph.IEdge;
import de.cau.cs.kieler.klay.planar.graph.IGraph;
import de.cau.cs.kieler.klay.planar.graph.InconsistentGraphModelException;

/**
 * Interface for planarization algorithms. Uses the Strategy design pattern to provide a common
 * interface for various implementations of algorithms for planarization.
 * 
 * @author ocl
 */
public interface IPlanarizer extends IAlgorithm {

    /**
     * Takes a planar graph and adds some more edges to the graph. These missing edges would make
     * the graph non-planar, so this algorithm adds them and inserts dummy nodes on the edge
     * crossings, the so graph stays planar.
     * 
     * @param graph
     *            the planar subgraph
     * @param edges
     *            a list the edges to be inserted into the graph
     * @throws InconsistentGraphModelException
     *             if the given graph turns out to be inconsistent
     */
    void planarize(IGraph graph, List<IEdge> edges) throws InconsistentGraphModelException;

}
