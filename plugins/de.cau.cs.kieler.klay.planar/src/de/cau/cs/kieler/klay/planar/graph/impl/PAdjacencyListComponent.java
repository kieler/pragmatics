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
package de.cau.cs.kieler.klay.planar.graph.impl;

import de.cau.cs.kieler.klay.planar.graph.IAdjacencyList;
import de.cau.cs.kieler.klay.planar.graph.IAdjacencyListComponent;
import de.cau.cs.kieler.klay.planar.graph.IEdge;
import de.cau.cs.kieler.klay.planar.graph.INode;
import de.cau.cs.kieler.klay.planar.graph.InconsistentGraphModelException;

/**
 * This interface uses the composite design pattern to build up a tree of adjacency lists for every
 * node, to easily manage port- and other edge constraints on nodes.
 * 
 * @see de.cau.cs.kieler.klay.planar.graph.impl.PAdjacencyList {@code PAdjacencyList}
 * 
 * @author ocl
 */
public abstract class PAdjacencyListComponent extends PGraphElement implements
        IAdjacencyListComponent {

    // ======================== Attributes =========================================================

    /** The parent list in the constraint tree. */
    private IAdjacencyList parentList;

    /** The node the adjacency list belongs to. */
    private INode rootNode;

    // ======================== Constructor ========================================================

    /**
     * Create a new abstract list component, associated with a root node.
     * 
     * @param node
     *            the node the adjacency list belongs to
     * @param list
     *            the parent list in the constraint tree
     */
    PAdjacencyListComponent(final INode node, final IAdjacencyList list) {
        this(-1, node, list);
    }

    /**
     * Create a new abstract list component, associated with a root node.
     * 
     * @param id
     *            the id of the list component
     * @param node
     *            the node the adjacency list belongs to
     * @param list
     *            the parent list in the constraint tree
     */
    PAdjacencyListComponent(final int id, final INode node, final IAdjacencyList list) {
        super(id, node.getParent());
        this.rootNode = node;
        this.parentList = list;
    }

    // ======================== Getters and Setters ================================================

    /**
     * {@inheritDoc}
     */
    public IAdjacencyList getList() {
        return this.parentList;
    }

    /**
     * Set the parent list in the constraint tree.
     * 
     * @param list
     *            the new parent list
     */
    void setList(final IAdjacencyList list) {
        this.parentList = list;
    }

    /**
     * {@inheritDoc}
     */
    public INode getNode() {
        return this.rootNode;
    }

    /**
     * Set the node the list component belongs to.
     * 
     * @param node
     *            the new node
     */
    void setNode(final INode node) {
        this.rootNode = node;
    }

    /**
     * Connect an edge to this adjacency list component. The edge is added to the first free port
     * from the end of the adjacency list.
     * 
     * @param edge
     *            the edge to add
     * @return the port the edge was linked to
     * @throws InconsistentGraphModelException
     *             if there are no free ports in the adjacency list
     */
    PPort linkEdge(final IEdge edge) throws InconsistentGraphModelException {
        return this.linkEdge(edge, true);
    }

    /**
     * Connect an edge to this adjacency list component. The edge is added to the first free port in
     * the adjacency list. The parameter {@code append} determines if the list is searched for free
     * ports from the start (if {@code false}), or from the end of the list (if {@code true}).
     * 
     * @param edge
     *            the edge to add
     * @param append
     *            append edge to list if true, prepend if false
     * @return the port the edge was linked to
     * @throws InconsistentGraphModelException
     *             if there are no free ports in the adjacency list
     */
    abstract PPort linkEdge(IEdge edge, boolean append) throws InconsistentGraphModelException;

    /**
     * Remove an edge from this adjacency list component. Note that this does not remove the edge
     * from the graph, but only the reference to it in this component.
     * 
     * @param edge
     *            the edge to remove
     * @throws InconsistentGraphModelException
     *             if the given edge {@code e} is not connected to this node
     */
    abstract void unlinkEdge(IEdge edge) throws InconsistentGraphModelException;

}
