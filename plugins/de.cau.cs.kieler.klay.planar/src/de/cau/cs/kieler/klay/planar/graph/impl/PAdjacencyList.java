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

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Stack;

import de.cau.cs.kieler.core.util.ICondition;
import de.cau.cs.kieler.klay.planar.graph.IAdjacencyList;
import de.cau.cs.kieler.klay.planar.graph.IAdjacencyListComponent;
import de.cau.cs.kieler.klay.planar.graph.IEdge;
import de.cau.cs.kieler.klay.planar.graph.INode;
import de.cau.cs.kieler.klay.planar.graph.IPort;
import de.cau.cs.kieler.klay.planar.graph.InconsistentGraphModelException;
import de.cau.cs.kieler.klay.planar.util.FilteredIterable;
import de.cau.cs.kieler.klay.planar.util.IFunction;
import de.cau.cs.kieler.klay.planar.util.MappedIterable;

/**
 * The list of edges on a node in a basic graph data structure for various graph theory algorithms.
 * 
 * @see de.cau.cs.kieler.klay.planar.graph.impl.PGraph {@code PGraph}
 * 
 * @author ocl
 */
public class PAdjacencyList extends PAdjacencyListComponent implements IAdjacencyList, Serializable {
    // TODO free type: remove edges removes ports? move -> addNode(edge)
    // TODO port indices

    /** Generated Version UID for Serialization. */
    private static final long serialVersionUID = -5142452621061334421L;

    // ======================== Attributes =========================================================

    /** The type of constraints on this list. */
    private AdjacencyListType type;

    /** The list of children in the constraint tree. */
    private LinkedList<IAdjacencyListComponent> children;

    // ======================== Constructor ========================================================

    /**
     * Create a new adjacency list with certain constraints.
     * 
     * @param node
     *            the parent node this adjacency list belongs to
     * @param list
     *            the parent adjacency list in the constraint tree
     * @param t
     *            the type of constraints on this list
     */
    PAdjacencyList(final INode node, final IAdjacencyList list, final AdjacencyListType t) {
        super(node, list);
        this.type = t;
        this.children = new LinkedList<IAdjacencyListComponent>();
    }

    // ======================== Getters and Setters ================================================

    /**
     * {@inheritDoc}
     */
    public AdjacencyListType getType() {
        return this.type;
    }

    /**
     * {@inheritDoc}
     */
    public int getEdgeCount() throws InconsistentGraphModelException {
        int sum = 0;
        for (IAdjacencyListComponent item : this.children) {
            if (item instanceof IAdjacencyList) {
                sum += ((IAdjacencyList) item).getEdgeCount();
            } else if (item instanceof IPort) {
                if (!((IPort) item).isEmpty()) {
                    sum++;
                }
            } else {
                throw new InconsistentGraphModelException(
                        "Attempted to count edges in adjacency list of incompatible type.");
            }
        }
        return sum;
    }

    /**
     * {@inheritDoc}
     */
    public int getPortCount() throws InconsistentGraphModelException {
        int sum = 0;
        for (IAdjacencyListComponent item : this.children) {
            if (item instanceof IAdjacencyList) {
                sum += ((IAdjacencyList) item).getEdgeCount();
            } else if (item instanceof IPort) {
                sum++;
            } else {
                throw new InconsistentGraphModelException(
                        "Attempted to count edges in adjacency list of incompatible type.");
            }
        }
        return sum;
    }

    /**
     * {@inheritDoc}
     */
    public IPort addPort() {
        return this.addPort(true);
    }

    /**
     * {@inheritDoc}
     */
    public IPort addPort(final boolean append) {
        IPort port = new PPort(this.getNode(), this);
        if (append) {
            this.children.addLast(port);
        } else {
            this.children.addFirst(port);
        }
        return port;
    }

    /**
     * {@inheritDoc}
     */
    public IAdjacencyList addList(final AdjacencyListType t) {
        return this.addList(t, true);
    }

    /**
     * {@inheritDoc}
     */
    public IAdjacencyList addList(final AdjacencyListType t, final boolean append) {
        IAdjacencyList list = new PAdjacencyList(this.getNode(), this, t);
        if (append) {
            this.children.addLast(list);
        } else {
            this.children.addFirst(list);
        }
        return list;
    }

    /**
     * Remove a list component from this adjacency list.
     * 
     * @param item
     *            the list component to remove
     */
    void removeListComponent(final IAdjacencyListComponent item) {
        this.children.remove(item);
        for (IAdjacencyListComponent child : this.children) {
            if (child instanceof PAdjacencyList) {
                ((PAdjacencyList) child).removeListComponent(item);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public boolean isAdjacent(final INode node) {
        for (IEdge e : this.edges()) {
            if (e.getSourceNode() == this.getNode() && e.getTargetNode() == node) {
                return true;
            }
            if (e.getTargetNode() == this.getNode() && e.getSourceNode() == node) {
                return true;
            }
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    public INode getAdjacentNode(final IEdge edge) throws InconsistentGraphModelException {
        if (edge.getSourceNode() == this.getNode()) {
            return edge.getTargetNode();
        } else if (edge.getTargetNode() == this.getNode()) {
            return edge.getSourceNode();
        } else {
            throw new InconsistentGraphModelException(
                    "Attempted to get adjacent node from unconnected edge");
        }
    }

    /**
     * {@inheritDoc}
     */
    public IEdge getEdge(final INode node) throws InconsistentGraphModelException {
        for (IEdge e : this.edges()) {
            if (this.getAdjacentNode(e) == node) {
                return e;
            }
        }
        throw new InconsistentGraphModelException("Attempted to get edge from non-adjacent node");
    }

    /**
     * {@inheritDoc}
     */
    PPort linkEdge(final IEdge edge, final boolean append) throws InconsistentGraphModelException {
        PPort port = null;
        if (this.type == AdjacencyListType.FREE) {
            // No port constraints: just add a new port to the list
            port = new PPort(this.getNode(), this);
            if (append) {
                this.children.addLast(port);
            } else {
                this.children.addFirst(port);
            }
        } else {
            Iterator<IPort> iter = this.freePorts().iterator();
            if (!iter.hasNext()) {
                throw new InconsistentGraphModelException(
                        "Attempted to add edge to node without free ports");
            }
            if (append) {
                while (iter.hasNext()) {
                    port = (PPort) iter.next();
                }
            } else {
                port = (PPort) iter.next();
            }
        }
        port.linkEdge(edge);
        return port;
    }

    /**
     * {@inheritDoc}
     */
    void unlinkEdge(final IEdge edge) throws InconsistentGraphModelException {
        for (IPort port : this.ports()) {
            if (port.getEdge() == edge) {
                ((PPort) port).unlinkEdge(edge);
            }
        }
    }

    // ======================== Iterators ==========================================================

    /**
     * {@inheritDoc}
     */
    public Iterable<IPort> ports() {
        final Stack<Iterator<IAdjacencyListComponent>> iterators = new Stack<Iterator<IAdjacencyListComponent>>();
        iterators.push(children.iterator());
        return new Iterable<IPort>() {
            public Iterator<IPort> iterator() {
                return new Iterator<IPort>() {

                    /** The next element, if there is such. */
                    private IPort next;

                    public boolean hasNext() {
                        if (this.next == null && !iterators.isEmpty()) {
                            if (!iterators.peek().hasNext()) {
                                iterators.pop();
                                this.hasNext();
                            } else {
                                IAdjacencyListComponent elem = iterators.peek().next();
                                if (elem instanceof IAdjacencyList) {
                                    iterators.push(((IAdjacencyList) elem).sublists().iterator());
                                    this.hasNext();
                                } else if (elem instanceof IPort) {
                                    this.next = (IPort) elem;
                                } else {
                                    return false;
                                }
                            }
                        }
                        return this.next != null;
                    }

                    public IPort next() {
                        if (this.hasNext()) {
                            IPort newNext = this.next;
                            this.next = null;
                            return newNext;
                        } else {
                            throw new NoSuchElementException();
                        }
                    }

                    public void remove() {
                        throw new UnsupportedOperationException();
                    }
                };
            }
        };
    }

    /**
     * {@inheritDoc}
     */
    public Iterable<IPort> freePorts() {
        return new FilteredIterable<IPort>(this.ports(), new ICondition<IPort>() {
            public boolean evaluate(final IPort object) {
                return !object.isEmpty();
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    public Iterable<IEdge> edges() {
        return new MappedIterable<IPort, IEdge>(new FilteredIterable<IPort>(this.ports(),
                new ICondition<IPort>() {
                    public boolean evaluate(final IPort object) {
                        return !object.isEmpty();
                    }
                }), new IFunction<IPort, IEdge>() {
            public IEdge evaluate(final IPort element) {
                return element.getEdge();
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    public Iterable<INode> adjacentNodes() {
        return new MappedIterable<IEdge, INode>(this.edges(), new IFunction<IEdge, INode>() {
            public INode evaluate(final IEdge element) {
                try {
                    return getAdjacentNode(element);
                } catch (InconsistentGraphModelException e) {
                    return null;
                }
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    public Iterable<IEdge> incomingEdges() {
        return new FilteredIterable<IEdge>(this.edges(), new ICondition<IEdge>() {
            public boolean evaluate(final IEdge object) {
                return object.isDirected() && object.getTargetNode() == getNode();
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    public Iterable<IEdge> outgoingEdges() {
        return new FilteredIterable<IEdge>(this.edges(), new ICondition<IEdge>() {
            public boolean evaluate(final IEdge object) {
                return object.isDirected() && object.getSourceNode() == getNode();
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    public Iterable<IAdjacencyListComponent> sublists() {
        return this.children;
    }

    // ======================== Miscellaneous Functions ============================================

    /**
     * {@inheritDoc}
     */
    public void mirror() {
        this.mirror(true);
    }

    /**
     * {@inheritDoc}
     */
    public void mirror(final boolean deep) {
        if (this.type == AdjacencyListType.ORDERED) {
            throw new UnsupportedOperationException();
        }
        LinkedList<IAdjacencyListComponent> reversed = new LinkedList<IAdjacencyListComponent>();
        for (IAdjacencyListComponent list : this.children) {
            if (list instanceof IAdjacencyList && deep) {
                ((IAdjacencyList) list).mirror(deep);
            }
            reversed.addFirst(list);
        }
        this.children = reversed;
    }

    /**
     * {@inheritDoc}
     */
    public void sort(final IFunction<IAdjacencyListComponent, Integer> func) {
        this.sort(func, true);
    }

    /**
     * {@inheritDoc}
     */
    // Unchecked cast used to create array of linked lists
    @SuppressWarnings("unchecked")
    public void sort(final IFunction<IAdjacencyListComponent, Integer> func, final boolean deep) {
        if (this.type == AdjacencyListType.ORDERED || this.type == AdjacencyListType.MIRROR) {
            throw new UnsupportedOperationException();
        }
        if (this.children.size() <= 1) {
            return;
        }

        // Determine minimum and maximum values
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (IAdjacencyListComponent item : this.children) {
            if (item instanceof IAdjacencyList && deep) {
                ((IAdjacencyList) item).sort(func, deep);
            }
            int value = func.evaluate(item);
            min = Math.min(value, min);
            max = Math.max(value, max);
        }

        // Sort by Bucket Sort
        LinkedList<IAdjacencyListComponent>[] buckets = new LinkedList[max - min + 1];
        for (IAdjacencyListComponent item : this.children) {
            int value = func.evaluate(item) - min;
            if (buckets[value] == null) {
                buckets[value] = new LinkedList<IAdjacencyListComponent>();
            }
            buckets[value].add(item);
        }
        this.children.clear();
        for (int i = 0; i < buckets.length; i++) {
            if (buckets[i] != null && !buckets[i].isEmpty()) {
                this.children.addAll(buckets[i]);
            }
        }
    }

    /**
     * Merge this list with another list. Appends all child entries of the given list to this list.
     * 
     * @param list
     *            the list to merge with this list
     * @throws InconsistentGraphModelException
     *             if any inconsistencies occur
     */
    void merge(final IAdjacencyList list) throws InconsistentGraphModelException {
        this.merge(list, true);
    }

    /**
     * Merge this list with another list. Adds all child entries of the given list to this list. The
     * entries are either appended or prepended.
     * 
     * @param list
     *            the list to merge with this list
     * @param append
     *            true to append the given list to this list
     * @throws InconsistentGraphModelException
     *             if any inconsistencies occur
     */
    void merge(final IAdjacencyList list, final boolean append)
            throws InconsistentGraphModelException {
        if (!(list instanceof PAdjacencyList)) {
            throw new InconsistentGraphModelException(
                    "Attempted to merge adjacency lists of incompatible type.");
        }

        LinkedList<IAdjacencyListComponent> toadd = new LinkedList<IAdjacencyListComponent>();
        for (IAdjacencyListComponent item : list.sublists()) {
            if (append) {
                toadd.addLast(item);
            } else {
                toadd.addFirst(item);
            }
        }
        for (IAdjacencyListComponent item : toadd) {
            if (!(item instanceof PAdjacencyListComponent)) {
                throw new InconsistentGraphModelException(
                        "Attempted to merge adjacency lists of incompatibel type.");
            }
            ((PAdjacencyList) list).removeListComponent(item);
            ((PAdjacencyListComponent) item).setNode(this.getNode());
            ((PAdjacencyListComponent) item).setList(this);
            if (append) {
                this.children.addLast(item);
            } else {
                this.children.addFirst(item);
            }
        }
    }

    @Override
    public String toString() {
        String res = "";
        for (IPort port : this.ports()) {
            res += ((PPort) port).toString() + "\n";
        }
        return res;

    }

}
