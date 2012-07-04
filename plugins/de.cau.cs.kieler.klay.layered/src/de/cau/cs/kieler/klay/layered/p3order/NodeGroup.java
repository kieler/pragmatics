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

    /**
     * List of nodes this vertex consists of.
     */
    private final List<LNode> nodes = new LinkedList<LNode>();

    /**
     * List of outgoing constraints.
     */
    private final List<NodeGroup> outgoingConstraints = new LinkedList<NodeGroup>();

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
    private Float barycenter;

    /**
     * Constructs a new instance containing the given node.
     * 
     * @param node
     *            the node the vertex should contain
     */
    public NodeGroup(final LNode node) {
        nodes.add(node);
    }

    /**
     * Constructs a new vertex that is the concatenation of the given two vertices. The incoming
     * constraints count is set to zero, while the list of successors are merged, updating the
     * successors' incoming count appropriately if both vertices are predecessors.
     * 
     * @param nodeGroup1
     *            the first vertex
     * @param nodeGroup2
     *            the second vertex
     */
    public NodeGroup(final NodeGroup nodeGroup1, final NodeGroup nodeGroup2) {
        nodes.addAll(nodeGroup1.nodes);
        nodes.addAll(nodeGroup2.nodes);

        // Add constraints, taking care not to add any constraints to vertex1 or vertex2
        // and to decrement the incoming constraints count of those that are successors to both
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
     * Gets the incoming constraints count of the node group.
     * 
     * @return the incoming constraints count
     */
    public int getIncomingConstraintsCount() {
        return incomingConstraintsCount;
    }

    /**
     * Sets the incoming constraints count to the given value.
     * 
     * @param value
     *            The value the incoming constraints count is set to
     */
    public void setIncomingConstraintsCount(final int value) {
        this.incomingConstraintsCount = value;
    }

    /**
     * Returns the list of outgoing constraints.
     * 
     * @return the outgoing constraints list of the node group
     */
    public List<NodeGroup> getOutgoingConstraints() {
        return outgoingConstraints;
    }
    
    /**
     * Returns the list of nodes.
     * 
     * @return the nodes-list of the node group
     */
    public List<LNode> getNodes() {
        return nodes;
    }
    
    /**
     * Returns the barycenter value of the node group, or {@code null} if no barycenter was
     * assigned yet.
     * 
     * @return the barycenter value of the node group
     */
    public Float getBarycenter() {
        return barycenter;
    }
    
    /**
     * Sets the barycenter to the given value.
     * 
     * @param value
     *      value the barycenter is set to
     */
    public void setBarycenter(final Float value) {
        barycenter = value;
    }
    
    /**
     * Returns the summed weight of the node group.
     * 
     * @return the summed weight of the node group.
     */
    public float getSummedWeight() {
        return summedWeight;
    }
    
    /**
     * Sets the summed weight to the given value.
     * 
     * @param value
     *    The value summed weight is set to
     */
    public void setSummedWeight(final float value) {
        summedWeight = value;
    }
    
    /**
     * Returns the degree of the node group.
     * 
     * @return the degree of the node group
     */
    public int getDegree() {
        return degree;
    }
    
    /**
     * Sets the degree of the node group to the given value.
     * 
     * @param value
     *      The value the degree is set to
     */
    public void setDegree(final int value) {
        degree = value;
    }

    /**
     * {@inheritDoc}
     */
    public int compareTo(final NodeGroup other) {
        // Empty vertices are placed at the end of all things ((c) by J. R. R. Tolkien)
        if (nodes.isEmpty() || other.nodes.isEmpty()) {
            return other.nodes.size() - nodes.size();
        }

        return barycenter.compareTo(other.barycenter);
    }
    
}
