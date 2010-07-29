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

/**
 * A Port in the interface for a general graph data structure.
 * 
 * @see de.cau.cs.kieler.klay.planar.graph.IGraph {@code IGraph}
 * @see de.cau.cs.kieler.klay.planar.graph.IAdjacencyListComponent {@code IAdjacencyListComponent}
 * 
 * @author ocl
 */
public interface IPort extends IAdjacencyListComponent {

    /**
     * Check if the port is empty or whether it has an edge attached.
     * 
     * @return true if no edge is attached to the port
     */
    boolean isEmpty();

    /**
     * Get the edge that is attached to the port. Return null if the port is still free.
     * 
     * @return the edge attached to the port
     */
    IEdge getEdge();

}
