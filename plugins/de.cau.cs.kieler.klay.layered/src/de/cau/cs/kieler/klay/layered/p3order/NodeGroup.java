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
package de.cau.cs.kieler.klay.layered.p3order;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import de.cau.cs.kieler.klay.layered.graph.LNode;

/**
 * A NodeGroup contains one or more nodes. NodeGroups are used to model sets of nodes that are
 * placed next to each other. They are also used to model a compound node enveloping all child
 * nodes. A NodeGroup contains methods to calculate its barycenter value, to merge with another
 * vertex and to generally do cool stuff.
 * 
 * @author cds
 * @author ima
 */
public class NodeGroup implements Comparable<NodeGroup> {

    /** the random number generator. */
    private Random random;

    /**
     * List of nodes this vertex consists of.
     */
    private List<LNode> nodes = new LinkedList<LNode>();

    /**
     * List of outgoing constraints.
     */
    private List<NodeGroup> outgoingConstraints = new LinkedList<NodeGroup>();

    /**
     * The number of incoming constraints.
     */
    private int incomingConstraintsCount = 0;

    /**
     * The sum of the node weights. Each node weight is the sum of the weights of the ports the
     * node's ports are connected to.
     */
    private float summedWeight = 0.0f;

    /**
     * The number of ports relevant to the barycenter calculation.
     */
    private int degree = 0;

    /**
     * This vertex' barycenter value. (summedWeight / degree)
     */
    private float barycenter = -1.0f;

    /**
     * Constructs a new instance containing the given node.
     * 
     * @param node
     *            the node the vertex should contain.
     * 
     * @param graphRandom
     *            The random number generator of the graph to be laid out.
     */
    public NodeGroup(final LNode node, final Random graphRandom) {
        nodes.add(node);
        random = graphRandom;
    }

    /**
     * Constructs a new vertex that is the concatenation of the given two vertices. The incoming
     * constraints count is set to zero, while the list of successors are merged, updating the
     * successors' incoming count appropriately if both vertices are predecessors.
     * 
     * @param nodeGroup1
     *            the first vertex.
     * @param nodeGroup2
     *            the second vertex.
     * @param graphRandom
     *            The random number generator of the graph to be laid out.
     */
    public NodeGroup(final NodeGroup nodeGroup1, final NodeGroup nodeGroup2,
            final Random graphRandom) {
        nodes.addAll(nodeGroup1.nodes);
        nodes.addAll(nodeGroup2.nodes);

        random = graphRandom;

        // Add constraints, taking care not to add any constraints to vertex1 or vertex2
        // and to decrement the incoming constraints count of those that are successors
        // to both
        outgoingConstraints.addAll(nodeGroup1.outgoingConstraints);
        outgoingConstraints.remove(nodeGroup2);
        for (NodeGroup candidate : nodeGroup2.outgoingConstraints) {
            if (candidate == nodeGroup1) {
                continue;
            } else if (outgoingConstraints.contains(candidate)) {
                // The candidate was in both vertice's successor list
                candidate.setIncomingConstraintsCount(candidate.getIncomingConstraintsCount() - 1);
            } else {
                outgoingConstraints.add(candidate);
            }
        }

        summedWeight = nodeGroup1.summedWeight + nodeGroup2.summedWeight;
        degree = nodeGroup1.degree + nodeGroup2.degree;

        if (degree > 0) {
            barycenter = summedWeight / degree;
        }
    }
    
    /**
     * Gets the incomingConstraintsCount of the NodeGroup.
     * 
     * @return
     *    Returns the incomingConstraintsCount.
     */
    public int getIncomingConstraintsCount() {
        return incomingConstraintsCount;
    }
    
    /**
     * Sets the incomingConstraintsCount to the given value.
     * 
     * @param value
     *      The value the incomingConstraintsCount is set to.
     */
    public void setIncomingConstraintsCount(final int value) {
        incomingConstraintsCount = value;
    }

    /**
     * {@inheritDoc}
     */
    public int compareTo(final NodeGroup other) {
        // Empty vertices are placed at the end of all things ((c) by J. R. R. Tolkien)
        if (nodes.isEmpty() || other.nodes.isEmpty()) {
            return other.nodes.size() - nodes.size();
        }

        // Nodes with equal barycenter values are randomized. This is not a stable
        // comparison, but we don't care.
        if (barycenter == other.barycenter) {
            return random.nextBoolean() ? 1 : -1;
        } else {
            return Float.compare(barycenter, other.barycenter);
        }
    }
}
