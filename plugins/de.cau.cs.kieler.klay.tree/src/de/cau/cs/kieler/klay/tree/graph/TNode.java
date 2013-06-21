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
package de.cau.cs.kieler.klay.tree.graph;

import java.util.LinkedList;
import java.util.List;

/**
 * A node in the T graph. Some properties are maybe null.
 * 
 * @author sor
 * @author sgu
 */
public class TNode extends TShape {

    /** the serial version UID. */
    private static final long serialVersionUID = 1L;

    /** the node label. */
    private String label;

    private TNode leftChild;
    private TNode rightChild;

    private TNode leftNeighbour;
    private TNode rightNeighbour;
    /** List of child nodes. */
    private LinkedList<TNode> children;

    private LinkedList<TEdge> outgoingEdges;

    private LinkedList<TEdge> incomeingEdges;

    // CONSTRUCTORS

    /**
     * Create a new node without parent and label.
     */
    public TNode(final int id, final TGraph graph) {
        super(id);
        this.rightChild = null;
        this.leftChild = null;
        this.leftNeighbour = null;
        this.rightNeighbour = null;
        this.children = new LinkedList<TNode>();
    }

    /**
     * Create a new node with given label.
     * 
     * @param label
     *            the label text
     */
    public TNode(final int id, final TGraph graph, final String label) {
        super(id);
        this.label = label;
        this.leftChild = null;
        this.rightChild = null;
        this.leftNeighbour = null;
        this.rightNeighbour = null;
        this.children = new LinkedList<TNode>();
    }

    // GETTERS

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        if (label == null || label.length() == 0) {
            return "n_" + id;
        } else {
            return "n_" + label;
        }
    }

    /**
     * Returns whether this node is a compound node.
     * 
     * @return true if compound
     */
    public boolean isCompound() {
        return children != null && children.size() > 0;
    }

    /**
     * Returns the label text of this node.
     * 
     * @return the label
     */
    public String getLabel() {
        return label;
    }

    /**
     * Get the left child. Maybe null.
     * 
     * @return the left child
     */
    public TNode getLeftChild() {
        return leftChild;
    }

    /**
     * Get the right child. Maybe null.
     * 
     * @return the right child
     */
    public TNode getRightChild() {
        return rightChild;
    }

    /**
     * Get the left neighbor. Maybe null.
     * 
     * @return the left neighbor
     */
    public TNode getLeftNeighbour() {
        return leftNeighbour;
    }

    /**
     * Get the right neighbor. Maybe null.
     * 
     * @return the right neighbor
     */
    public TNode getRightNeighbour() {
        return rightNeighbour;
    }

    /**
     * Returns the list of children, creating it if necessary.
     * 
     * @return the children
     */
    public LinkedList<TNode> getChildren() {
        if (children == null) {
            children = new LinkedList<TNode>();
        }
        return children;
    }

    /**
     * Returns the list of outgoing edges, creating it if necessary.
     * 
     * @return list of outgoing edges
     */
    public List<TEdge> getOutgoingEdges() {
        if (outgoingEdges == null) {
            outgoingEdges = new LinkedList<TEdge>();
        }
        return outgoingEdges;
    }

    /**
     * Returns the list of incoming edges, creating it if necessary.
     * 
     * @return list of incoming edge
     */
    public List<TEdge> getInComingEdges() {
        if (incomeingEdges == null) {
            incomeingEdges = new LinkedList<TEdge>();
        }
        return incomeingEdges;
    }

    // SETTERS

    /**
     * Set the label text of this node.
     * 
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * Set the left child and add it to the list of children.
     * 
     * @param leftChild
     *            the left child
     */
    public void setLeftChild(TNode leftChild) {
        this.leftChild = leftChild;
        children.add(leftChild);
    }

    /**
     * Set the right child and add it to the list of children.
     * 
     * @param rightChild
     *            the right child
     */
    public void setRightChild(TNode rightChild) {
        this.rightChild = rightChild;
        children.add(rightChild);
    }

    /**
     * Set the left neighbor.
     * 
     * @param tNode
     *            the left neighbor
     */
    public void setLeftNeighbour(TNode tNode) {
        this.leftNeighbour = tNode;
    }

    /**
     * Set the right neighbor.
     * 
     * @param tNode
     *            the right neighbor
     */
    public void setRightNeighbour(TNode tNode) {
        this.rightNeighbour = tNode;
    }

    /**
     * Reset the list of children to a given list.
     * 
     * @param children
     *            the new list of children
     */
    public void setChildren(LinkedList<TNode> children) {
        this.children = children;
    }

    /**
     * Add a node to the list of children.
     * 
     * @param child
     *            the child to be added
     */
    public void addChild(TNode child) {
        this.children.add(child);
    }
}
