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
package de.cau.cs.kieler.klay.planar.graph;

import java.io.File;
import java.io.IOException;

import de.cau.cs.kieler.core.kgraph.KNode;

/**
 * An interface for the creation of graph objects. This interface implements the factory method
 * design pattern to create instances of graphs.
 * 
 * @see de.cau.cs.kieler.klay.planar.graph.IGraph {@code IGraph}
 * 
 * @author ocl
 */
public interface IGraphFactory {

    /**
     * Create an empty graph instance. The resulting graph will not contain any edges or nodes.
     * 
     * @return an empty graph
     */
    IGraph createEmptyGraph();

    /**
     * Create a full graph. In a full graph, every node is connected to every other node via an
     * edge.
     * 
     * @param nodes
     *            the number of nodes in the graph
     * @return a full graph with the given number of nodes
     * @throws InconsistentGraphModelException
     *             if any inconsistencies occur
     */
    IGraph createFullGraph(int nodes) throws InconsistentGraphModelException;

    /**
     * Create a random graph with a given number of nodes and edges.
     * 
     * @param nodes
     *            the number of nodes in the random graph
     * @param edges
     *            the number of edges in the random graph
     * @return a random graph
     * @throws InconsistentGraphModelException
     *             if any inconsistencies occur
     */
    IGraph createRandomGraph(int nodes, int edges) throws InconsistentGraphModelException;

    /**
     * Create a graph from a {@code KGraph} instance.
     * 
     * @param kgraph
     *            a {@code KNode} to base the graph upon
     * @return a graph corresponding to {@code kgraph}
     * @throws InconsistentGraphModelException
     *             if the given {@code KGraph} is not consistent
     */
    IGraph createGraphFromKGraph(KNode kgraph) throws InconsistentGraphModelException;

    /**
     * Create a graph based on a file in the DIMACS format.
     * 
     * @param dimacs
     *            the file containing the graph info
     * @return a graph based on the DIMACS file
     * @throws IOException
     *             if problems during file reading occur
     * @throws InconsistentGraphModelException
     *             if the graph in the description file is not valid
     */
    IGraph createGraphFromDIMACS(File dimacs) throws IOException, InconsistentGraphModelException;

}
