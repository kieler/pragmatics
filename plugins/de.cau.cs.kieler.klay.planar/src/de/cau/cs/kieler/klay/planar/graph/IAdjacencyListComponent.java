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
 * This interface uses the composite design pattern to build up a tree of adjacency lists for every
 * node, to easily manage port- and other edge constraints on nodes.
 * 
 * @see de.cau.cs.kieler.klay.planar.graph.IAdjacencyList {@code IAdjacencyList}
 * @see de.cau.cs.kieler.klay.planar.graph.IPort {@code IPort}
 * 
 * @author ocl
 */
public interface IAdjacencyListComponent extends IGraphElement {

    /**
     * Get the parent adjacency list in the constraint tree.
     * 
     * @return the list this component is a part of
     */
    IAdjacencyList getList();

    /**
     * Get the node the adjacency list component is associated with.
     * 
     * @return the node of the list component
     */
    INode getNode();

}
