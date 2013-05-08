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
 * A node in the T graph.
 * 
 * @author sor
 * @author sgu
 */
public class TNode {

    /** the serial version UID. */
    @SuppressWarnings("unused")
    private static final long serialVersionUID = 1L;

    // CHECKSTYLEOFF VisibilityModifier
    /** the identifier number. */
    public int id;
    // CHECKSTYLEON VisibilityModifier

    /** the node label. */
    private String label;
    /** The parent node. */
    private TNode parent;
    /** List of child nodes. */
    private List<TNode> children;
    
    /**
     * Create a new node.
     */
    public TNode() {
    }
    
    /**
     * Create a new node with given label.
     * 
     * @param label the label text
     */
    public TNode(final String label) {
        this.label = label;
    }

    /**
     * Create a new node with given parent node.
     * 
     * @param label the label text
     * @param theParent the parent node
     */
    public TNode(final String label, final TNode theParent) {
        this.label = label;
        this.parent = theParent;
    }
    
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
     * Returns the parent node.
     * 
     * @return the parent
     */
    public TNode getParent() {
        return parent;
    }

    /**
     * Returns the list of children, creating it if necessary.
     * 
     * @return the children
     */
    public List<TNode> getChildren() {
        if (children == null) {
            children = new LinkedList<TNode>();
        }
        return children;
    }

    /**
     * Returns the depth of this node in the compound hierarchy.
     * 
     * @return the depth
     */
    public int getDepth() {
        int depth = 0;
        TNode node = parent;
        while (node != null) {
            node = node.getParent();
            depth++;
        }
        return depth;
    }
    
}
