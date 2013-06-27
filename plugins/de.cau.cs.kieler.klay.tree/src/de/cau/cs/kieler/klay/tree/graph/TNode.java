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

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import de.cau.cs.kieler.klay.tree.properties.Properties;

/**
 * A node in the T graph. Some properties are maybe null.
 * 
 * @author sor
 * @author sgu
 */
public class TNode extends TShape {

    /** the serial version UID. */
    private static final long serialVersionUID = 1L;

    /** the graph it belongs to. */
    private TGraph graph;

    /** the node label. */
    private String label;

    /** lefthand node . */
    private TNode leftNeighbour;

    /** righthand node . */
    private TNode rightNeighbour;

    /** List of outgoing edges. */
    private LinkedList<TEdge> outgoingEdges;

    /** List of incoming edges. */
    private LinkedList<TEdge> incomeingEdges;

    // CONSTRUCTORS

    /**
     * Create a new node with given label.
     * 
     * @param id
     *            a unique identification
     * @param graph
     *            the graph it belong s to
     * @param label
     *            the label
     */
    public TNode(final int id, final TGraph graph, final String label) {
        this(id, graph);
        this.label = label;
    }

    /**
     * Create a new node a label.
     * 
     * @param id
     *            a unique identification
     * @param graph
     *            the graph it belong s to
     */
    public TNode(final int id, final TGraph graph) {
        super(id);
        this.graph = graph;
        this.leftNeighbour = null;
        this.rightNeighbour = null;
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
        LinkedList<TNode> children = getChildren();
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
     * Returns the list of parents, creating it if necessary.
     * 
     * @return the list of parents
     */
    public LinkedList<TNode> getParents() {
        LinkedList<TNode> parents = new LinkedList<TNode>();
        for (TEdge iEdge : getInComingEdges()) {
            parents.add(iEdge.getSource());
        }
        return parents;
    }

    /**
     * Returns the first parent, creating it from outgoing edges.
     * 
     * @return a single parent
     */
    public TNode getParent() {
        return getParents().getFirst();
    }

    /**
     * Returns the list of children, creating it from outgoing edges.
     * 
     * @return the list of children
     */
    public LinkedList<TNode> getChildren() {
        LinkedList<TNode> children = new LinkedList<TNode>();
        for (TEdge iEdge : getOutgoingEdges()) {
            children.add(iEdge.getTarget());
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
     * Reset the list of parents to a given list, by finding all edges from the parents to this node
     * and reset the existing edges. If no edge exists a dummy edge is added.
     * 
     * @param children
     *            the new list of parents
     */
    public void setParents(LinkedList<TNode> parents) {
        LinkedList<TEdge> incomeingEdges = new LinkedList<TEdge>();
        for (TNode parent : parents) {
            List<TEdge> outgoingEdges = parent.getOutgoingEdges();
            Iterator<TEdge> it = outgoingEdges.iterator();
            TEdge iEdge = null;
            while (it.hasNext() && iEdge == null) {
                TEdge oEdge = (TEdge) it.next();
                if (oEdge.getTarget() == this) {
                    iEdge = oEdge;
                }
            }
            if (iEdge == null) {
                iEdge = new TEdge(parent, this);
                iEdge.setProperty(Properties.DUMMY, true);
                graph.getEdges().add(iEdge);
                parent.getOutgoingEdges().add(iEdge);
            }
            incomeingEdges.add(iEdge);
        }

        for (TEdge tEdge : getInComingEdges()) {
            tEdge.setTarget(null);
            tEdge.getSource().getOutgoingEdges().remove(tEdge);
            tEdge.setSource(null);
        }

        this.incomeingEdges = incomeingEdges;
    }

    /**
     * Add a node to the list of parents, by adding a appropriate dummy edge to the graph.
     * 
     * @param child
     *            the child to be added
     */
    public void addPartent(TNode parent) {
        TEdge newEdge = new TEdge(parent, this);
        newEdge.setProperty(Properties.DUMMY, true);
        graph.getEdges().add(newEdge);
        getInComingEdges().add(newEdge);
        parent.getOutgoingEdges().add(newEdge);
    }

    /**
     * Reset the list of children to a given list, by finding all edges from the children to this
     * node and reset the existing edges. If no edge exists a dummy edge is added.
     * 
     * @param children
     *            the new list of children
     */
    public void setChildren(LinkedList<TNode> children) {
        LinkedList<TEdge> outgoingEdges = new LinkedList<TEdge>();
        for (TNode child : children) {
            List<TEdge> incomingEdges = child.getInComingEdges();
            Iterator<TEdge> it = incomingEdges.iterator();
            TEdge oEdge = null;
            while (it.hasNext() && oEdge == null) {
                TEdge iEdge = (TEdge) it.next();
                if (iEdge.getTarget() == this) {
                    oEdge = iEdge;
                }
            }
            if (oEdge == null) {
                oEdge = new TEdge(this, child);
                oEdge.setProperty(Properties.DUMMY, true);
                graph.getEdges().add(oEdge);
                child.getInComingEdges().add(oEdge);
            }
            outgoingEdges.add(oEdge);
        }

        for (TEdge tEdge : getOutgoingEdges()) {
            tEdge.setSource(null);
            tEdge.getTarget().getInComingEdges().remove(tEdge);
            tEdge.setTarget(null);
        }

        this.outgoingEdges = outgoingEdges;
    }

    /**
     * Add a node to the list of children, by adding a appropriate dummy edge to the graph.
     * 
     * @param child
     *            the child to be added
     */
    public void addChild(TNode child) {
        TEdge newEdge = new TEdge(this, child);
        newEdge.setProperty(Properties.DUMMY, true);
        graph.getEdges().add(newEdge);
        getOutgoingEdges().add(newEdge);
        child.getInComingEdges().add(newEdge);
    }
}
