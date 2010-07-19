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
package de.cau.cs.rtprak.planarization;

import java.util.List;

import de.cau.cs.kieler.core.alg.IAlgorithm;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.rtprak.planarization.graph.IGraph;
import de.cau.cs.rtprak.planarization.graph.INode;
import de.cau.cs.rtprak.planarization.graph.InconsistentGraphModelException;

/**
 * Interface for planarity testing algorithms. Uses the Strategy design pattern to provide a common
 * interface for various implementation of algorithms for planarity testing.
 * 
 * @author ocl
 */
public interface IPlanarityTester extends IAlgorithm {

    /**
     * Test the graph for planarity. If the graph is a planar graph, this method will return {@code
     * true} and {@code false}, if the graph is non-planar.
     * 
     * @param graph
     *            the graph to test for planarity
     * @return true if the graph is planar, false otherwise
     * @throws InconsistentGraphModelException
     *             if {@code graph} is not consistent
     */
    boolean testPlanarity(IGraph graph) throws InconsistentGraphModelException;

    /**
     * Determines a planar embedding of the graph. If the Graph is fully planar, this algorithm
     * computes a complete planar embedding of the graph. If the graph is not planar, it determines
     * a planar embedding of a maximal planar subgraph and returns a list of edges, whose addition
     * will cause non-planarity and therefore could not be inserted.
     * 
     * @param graph
     *            the graph to determine its planar embedding
     * @return a list that contains pairs of source and target nodes of the missing edges
     * @throws InconsistentGraphModelException
     *             if {@code graph} is not consistent
     */
    List<Pair<INode, INode>> planarSubgraph(IGraph graph) throws InconsistentGraphModelException;

}
