/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2015 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klay.layered.networksimplex;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.common.collect.Lists;

/**
 * A node used by the {@link NetworkSimplex} algorithm. It has a set of incoming and outgoing edges.
 * 
 * @author uru
 */
public final class NNode {

    // SUPPRESS CHECKSTYLE NEXT 15 VisibilityModifier
    /** An id, internally used for indexing. */
    public int id;
    /** An object from which this edge is derived. */
    public Object origin;
    /** The type is attached as label to the debug graph. Apart from this it has no semantic meaning. */
    public String type = "";

    /**
     * The layer that this node is (currently) assigned to. May change during execution of the
     * network simplex algorithm.
     */
    public int layer;
    
    // In addition to a nodes incoming and outgoing edges we cache a list of 
    // the union of these two. To be able to tell when we have to update this list,
    // we listen (sort of) to changes to the two underlying lists. 
    private ChangeAwareArrayList<NEdge> outgoingEdges = new ChangeAwareArrayList<>();
    private int outgoingEdgesModCnt = -1;
    private ChangeAwareArrayList<NEdge> incomingEdges = new ChangeAwareArrayList<>();
    private int incomingEdgesModCnt = -1;
    
    /** Internally cached list of all edges. */
    private ArrayList<NEdge> allEdges = Lists.newArrayList();
    
    
    // SUPPRESS CHECKSTYLE NEXT 20 VisibilityModifier 
    /**
     * A flag indicating whether a specified node is part of the spanning tree determined by
     * {@code tightTree()}.
     * 
     * @see NetworkSimplex#tightTreeDFS() 
     */
    protected boolean treeNode = false;

    /**
     * A collection of edges for which cutvalues are unknown. Cutvalues are updated during every
     * iteration of the network simplex algorithm.
     * 
     * In principle, no order is required. Still, we use {@link ArrayList} because experiments
     * showed, that an {@link ArrayList} performs faster here than a {@link java.util.HashSet
     * HashSet}.
     */
    protected Collection<NEdge> unknownCutvalues = Lists.newArrayList();

    private NNode() { }
    
    /**
     * @return a {@link NNodeBuilder} to construct a new node.
     */
    public static NNodeBuilder of() {
        return new NNodeBuilder();
    }
    
    /**
     * @return the outgoingEdges
     */
    public List<NEdge> getOutgoingEdges() {
        return outgoingEdges;
    }
    
    /**
     * @return the incomingEdges
     */
    public List<NEdge> getIncomingEdges() {
        return incomingEdges;
    }
    
    /**
     * @return a list with the union of {@link #getOutgoingEdges()} and {@link #getIncomingEdges()}.
     *         The list is cached internally, subsequent calls return the same list as long as
     *         neither of the incoming and outgoing edges changes.
     */
    public List<NEdge> getConnectedEdges() {
        if (incomingEdgesModCnt != incomingEdges.getModCnt()
                || outgoingEdgesModCnt != outgoingEdges.getModCnt()) {
            
            allEdges.clear();
            allEdges.ensureCapacity(incomingEdges.size() + outgoingEdges.size());
            allEdges.addAll(incomingEdges);
            allEdges.addAll(outgoingEdges);

            incomingEdgesModCnt = incomingEdges.getModCnt();
            outgoingEdgesModCnt = outgoingEdges.getModCnt();
        }

        return allEdges;
    }
    
    /**
     * Builder class for {@link NNode}s. 
     */
    public static final class NNodeBuilder {
        
        /** The node currently being constructed. */  
        private NNode node;
        
        private NNodeBuilder() {
            node = new NNode();
        }

        /**
         * Sets the id field.
         * 
         * @param id
         *            a non-negative integer.
         * @return this builder.
         */
        public NNodeBuilder id(final int id) {
            node.id = id;
            return this;
        }

        /**
         * Sets the origin field.
         * 
         * @param origin
         *            any object.
         * @return this builder.
         */
        public NNodeBuilder origin(final Object origin) {
            node.origin = origin;
            return this;
        }

        /**
         * Sets the type field.
         * 
         * @param type
         *            any string, has no semantic meaning, can be used for debugging.
         * @return this builder.
         */
        public NNodeBuilder type(final String type) {
            node.type = type;
            return this;
        }

        /**
         * Finally creates this node. That is, the node is added to the passed {@link NGraph}.
         * 
         * @param graph
         *            the {@link NGraph} this node belongs to.
         * @return the created {@link NNode}.
         */
        public NNode create(final NGraph graph) {
            graph.nodes.add(node);
            return node;
        }

    }
    
    /**
     * Extending {@link ArrayList} to expose the {@code modCount} field. The field allows to check
     * if the instance of the array list changed.
     * 
     * @param <E>
     *            type of the elements in this collection.
     */
    private static class ChangeAwareArrayList<E> extends ArrayList<E> {
        private static final long serialVersionUID = 7137934731310022891L;

        private int getModCnt() {
            return modCount;
        }
    }
}
