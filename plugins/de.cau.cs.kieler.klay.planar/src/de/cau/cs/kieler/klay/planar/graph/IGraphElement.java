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

import de.cau.cs.kieler.klay.planar.util.IPropertyHolder;

/**
 * A generic element in the interface for a general graph data structure.
 * 
 * @see de.cau.cs.kieler.klay.planar.graph.IGraph {@code IGraph}
 * 
 * @author ocl
 */
public interface IGraphElement extends IPropertyHolder {

    /**
     * Get an ID of the element. IDs should be unique between all elements of the same type in the
     * graph (e.g. unique between all nodes, all edges, etc).
     * 
     * @return the ID of the element
     */
    int getID();

    /**
     * Get the parent graph of the element. Every graph element is part of exactly one graph, which
     * itself can again be part of other graphs. This method returns the direct parent in the graph
     * hierarchy.
     * 
     * @return the parent graph of the element
     */
    IGraph getParent();

}
