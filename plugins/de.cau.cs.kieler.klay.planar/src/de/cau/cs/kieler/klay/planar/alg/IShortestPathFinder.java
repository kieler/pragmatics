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

import de.cau.cs.kieler.core.util.Property;
import de.cau.cs.kieler.klay.planar.graph.IEdge;
import de.cau.cs.kieler.klay.planar.graph.INode;

/**
 * Interface for algorithms to find a shortest path in a graph. Uses the Strategy design pattern to
 * provide a common interface for implementations of path finding algorithms.
 * 
 * @author ocl
 */
public interface IShortestPathFinder {

    /** Property to add a cost to an edge. */
    Property<Integer> EDGECOST = new Property<Integer>(
            "de.cau.cs.kieler.klay.planar.properties.edgecost");

    /**
     * Find the shortest path in a between two nodes. The algorithm uses the {@code EDGECOST}
     * property assigned to edges to compute costs on edges. Edges without the property have a cost
     * of 1.
     * 
     * @param source
     *            the source node of the path
     * @param target
     *            the target node of the path
     * @return the list of edges defining the shortest path between {@code source} and {@code
     *         target}
     */
    List<IEdge> findShortestPath(INode source, INode target);

}
