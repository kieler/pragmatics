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
import com.google.common.collect.Iterables;

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

    /** List of outgoing edges. */
    private LinkedList<TEdge> outgoingEdges = new LinkedList<TEdge>();

    /** List of incoming edges. */
    private LinkedList<TEdge> incomingEdges = new LinkedList<TEdge>();

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
     * Returns the label text of this node.
     * 
     * @return the label
     */
    public String getLabel() {
        return label;
    }

    /**
     * Returns the list of parents, creating it from outgoing edges. Parents are sources of incoming
     * edges of this node.
     * 
     * @return the list of parents
     */
    public LinkedList<TNode> getParentsCopy() {
        LinkedList<TNode> parents = new LinkedList<TNode>();
        for (TEdge iEdge : getInComingEdges()) {
            parents.add(iEdge.getSource());
        }
        return parents;
    }

    /**
     * Returns an iterable over all the parents. Parents are sources of incoming edges of this node.
     * 
     * @return an iterable over all parents.
     */
    public Iterable<TNode> getParents() {
        return new Iterable<TNode>() {
            public Iterator<TNode> iterator() {
                final Iterator<TEdge> edgesIter = getInComingEdges().iterator();

                return new Iterator<TNode>() {
                    public boolean hasNext() {
                        return edgesIter.hasNext();
                    }

                    public TNode next() {
                        return edgesIter.next().getSource();
                    }

                    public void remove() {
                        edgesIter.remove();
                    }
                };
            }

        };
    }

    /**
     * Returns the first parent, by taking it from outgoing edges.
     * 
     * @return a single parent or null if there is no
     */
    public TNode getParent() {
        return Iterables.getFirst(getParents(), null);
    }

    /**
     * Returns the list of children, creating it from outgoing edges. Children are targets of
     * outgoing edges of this node.
     * 
     * @return the list of children
     */
    public LinkedList<TNode> getChildrenCopy() {
        LinkedList<TNode> children = new LinkedList<TNode>();
        for (TEdge iEdge : getOutgoingEdges()) {
            children.add(iEdge.getTarget());
        }
        return children;
    }

    /**
     * Returns an iterable over all the children. Children are targets of outgoing edges of this
     * node.
     * 
     * @return an iterable over all children.
     */
    public Iterable<TNode> getChildren() {
        return new Iterable<TNode>() {
            public Iterator<TNode> iterator() {
                final Iterator<TEdge> edgesIter = getOutgoingEdges().iterator();

                return new Iterator<TNode>() {
                    public boolean hasNext() {
                        return edgesIter.hasNext();
                    }

                    public TNode next() {
                        return edgesIter.next().getTarget();
                    }

                    public void remove() {
                        edgesIter.remove();
                    }
                };
            }

        };
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
        if (incomingEdges == null) {
            incomingEdges = new LinkedList<TEdge>();
        }
        return incomingEdges;
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
     * Reset the list of parents to a given list, by finding all edges from the parents to this node
     * and reset the existing edges. If no edge exists a dummy edge is added.
     * 
     * @param children
     *            the new list of parents
     */
    public void setParents(LinkedList<TNode> parents) {
        LinkedList<TEdge> incomingEdges = new LinkedList<TEdge>();
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
            incomingEdges.add(iEdge);
        }

        for (TEdge tEdge : getInComingEdges()) {
            tEdge.setTarget(null);
            tEdge.getSource().getOutgoingEdges().remove(tEdge);
            tEdge.setSource(null);
        }

        this.incomingEdges.clear();
        this.incomingEdges.addAll(incomingEdges);
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

        this.outgoingEdges.clear();
        this.outgoingEdges.addAll(outgoingEdges);
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

    // ATTRIBUTES

    /**
     * Returns whether this node is a leaf.
     * 
     * @return true if node is a leaf
     */
    public boolean isLeaf() {
        return getOutgoingEdges().isEmpty();
    }
}
