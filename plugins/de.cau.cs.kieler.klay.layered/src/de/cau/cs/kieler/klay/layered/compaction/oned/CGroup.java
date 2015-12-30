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
package de.cau.cs.kieler.klay.layered.compaction.oned;

import java.util.List;
import java.util.Queue;
import java.util.Set;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import de.cau.cs.kieler.kiml.options.Direction;

/**
 * Represents a group of {@link CNode}s whose relative distances to each other are preserved.
 * For instance, when compacting a layered graph, CGroups are used to ensure that vertical edge segments,
 * that are connected to north/south ports, are kept at the position of the port.
 * 
 * @see HorizontalGraphCompactor
 * 
 * @author dag
 */
public final class CGroup {
    // Variables are public for convenience reasons since this class is used internally only.
    // SUPPRESS CHECKSTYLE NEXT 30 VisibilityModifier
    /** root position of the {@link CGroup}. */
    public double startPos = Double.NEGATIVE_INFINITY;
    /**
     * The field can be used to determine whether a group has moved during compaction. It has to be
     * reset externally and is updated during
     * {@link #compactInnerCNodes(double, ISpacingsHandler, Direction)}
     */
    public double delta = 0;
    /**
     * Similar to {@link #delta} with the difference that it does not represent the direction-less
     * sum of a this group's movements but instead considers the compaction direction. Thus, if a
     * node moves back and forth the same distance this field's value will be zero.
     */
    public double deltaNormalized = 0;
    /** grouped {@link CNode}s. */
    public Set<CNode> cNodes;
    /** constraints pointing from within the {@link CGroup} to CNodes outside the {@link CGroup}. */
    public Set<CNode> incomingConstraints;
    /** number of constraints originating from within the {@link CGroup}. */
    public int outDegree;
    /** the reference node of this group, i.e. the reference for the group offset of other nodes. */
    public CNode reference; 
    /** An id for public use. There is no warranty, use at your own risk. */
    public int id;
    /** flags this group to be repositioned in the case of left/right balanced compaction. */
    public boolean reposition = true;

    /**
     * The constructor for a {@link CGroup} receives {@link CNode}s to group.
     * 
     * @param inputCNodes
     *            the {@link CNode}s to add
     */
    public CGroup(final CNode... inputCNodes) {
        cNodes = Sets.newLinkedHashSet();
        incomingConstraints = Sets.newHashSet();
        outDegree = 0;
        for (CNode cNode : inputCNodes) {
            if (reference == null) {
                reference = cNode;
            }
            addCNode(cNode);
        }
    }

    /**
     * Checks whether all outgoing constraints of the {@link CGroup} are processed.
     * 
     * @return {@code true} if the {@link CNode}s inside this group are ready to be compacted.
     */
    public boolean isInnerCompactable() {
        return outDegree == 0;
    }

    /**
     * Compacts the grouped {@link CNode}s while maintaining their relative positions.
     * 
     * @param minStartPos
     *            {@link CNode}s that are not constrained are set to this position.
     * @param spacingHandler
     *            {@link ISpacingsHandler} that can be queried for the desired spacing between a
     *            pair of nodes.
     * @param compactionDirection
     *            the actual, untransformed direction of the currently performed compaction.
     */
    public void compactInnerCNodes(final double minStartPos,
            final ISpacingsHandler<? super CNode> spacingHandler,
            final Direction compactionDirection) {

        // find the sinks of the constraint graph
        Queue<CNode> startNodes = Lists.newLinkedList();
        for (CNode cNode : cNodes) {
            // a CNode is a sink if all outgoing constraints have been processed
            if (cNode.outDegree == 0) {
                startNodes.add(cNode);

                // CNodes can be locked in place to avoid pulling clusters apart
                if (cNode.reposition) {
                    // cNodes also become inner sinks after they were updated by propagation
                    // in that case they are not reset to minStartPos
                    if (cNode.startPos == Double.NEGATIVE_INFINITY) {
                        cNode.startPos = minStartPos;
                    }
                } else {
                    cNode.startPos = cNode.getPosition();
                }
            }
        }

        // processing startNodes and deriving start positions of incoming CNodes from constraints
        while (!startNodes.isEmpty()) {
            CNode cNode = startNodes.poll();
            for (CNode inc : cNode.constraints) {
                // process constraints from within the CGroup only
                if (!incomingConstraints.contains(inc)) {
                    inc.updateStartPos(cNode, spacingHandler, compactionDirection);

                    // adding new sinks to the queue
                    if (inc.outDegree == 0) {
                        startNodes.add(inc);
                    }
                }
            }
        }

        // finding the required root position for the CGroup that satisfies the constraints of each
        // member
        startPos = Double.NEGATIVE_INFINITY;
        for (CNode cNode : cNodes) {
            startPos = Math.max(startPos, cNode.startPos - cNode.cGroupOffset.x);
        }
        
        if ((Math.abs(startPos) <= Double.MAX_VALUE)) {
            throw new IllegalStateException(
                    "Couldn't determine initial position during compaction."
                            + " Maybe the specified spacing is too large.");
        }
        
        // record the movement of this group during the current compaction
        // this has to be recorded _before_ the nodes' positions are updated
        // and care has to be taken about the compaction direction. In certain 
        // scenarios nodes may move "back-and-forth". To detect this, we associate
        // a negative delta with two of the compaction directions.
        double diff = reference.hitbox.x - startPos;
        delta += diff;
        if (compactionDirection == Direction.RIGHT || compactionDirection == Direction.DOWN) {
            deltaNormalized += diff;
        } else {
            deltaNormalized -= diff;
        }
        
        // setting the positions of the CGroups members according to their specified offset
        for (CNode cNode : cNodes) {
            cNode.startPos = startPos + cNode.cGroupOffset.x;
        }
    }

    /**
     * Propagates starting positions according to the {@link CGroup}s incoming constraints. This
     * method may be called after {@link #compactInnerCNodes()}.
     * 
     * @param spacingHandler
     *            {@link ISpacingsHandler} that can be queried for the desired spacing between a
     *            pair of nodes.
     * @param compactionDirection
     *            the actual, untransformed direction of the currently performed compaction.
     * @return a list of {@link CGroup}s that became {@link #isInnerCompactable()} compactable.
     * 
     */
    public List<CGroup> propagate(final ISpacingsHandler<? super CNode> spacingHandler,
            final Direction compactionDirection) {
        List<CGroup> compactables = Lists.newArrayList();

        for (CNode cNode : cNodes) {
            for (CNode inc : cNode.constraints) {
                // processing constraints that refer to CNodes outside of this CGroup
                if (incomingConstraints.contains(inc)) {
                    // updating the starting position of an incoming constraint
                    inc.updateStartPos(cNode, spacingHandler, compactionDirection);
                    inc.cGroup.outDegree--;
                    // collecting CGroups of incoming constraints that became compactable
                    if (inc.cGroup.isInnerCompactable()) {
                        compactables.add(inc.cGroup);
                    }
                }
            }
        }

        return compactables;
    }

    /**
     * Adds a {@link CNode} to the {@link CGroup} and updates the incoming constraints.
     * 
     * @param cNode
     *            the {@link CNode} to add
     */
    public void addCNode(final CNode cNode) {
        cNodes.add(cNode);
        if (cNode.cGroup != null) {
            throw new RuntimeException("CNode belongs to another CGroup.");
        }
        cNode.cGroup = this;
    }
    
    /**
     * Removes the passed {@link CNode} from this group and sets the {@link CNode#cGroup} field to
     * null (if it belongs to this group).
     * 
     * @param cNode
     *            the {@link CNode} to remove.
     * @return true if this {@link CGroup} actually contained the passed {@link CNode}, false
     *         otherwise.
     */
    public boolean removeCNode(final CNode cNode) {
        boolean removed = cNodes.remove(cNode);
        if (removed) {
            cNode.cGroup = null;
        }
        return removed;
    }
}
