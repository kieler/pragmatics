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
package de.cau.cs.kieler.klay.layered.intermediate;

import java.util.List;
import java.util.Queue;
import java.util.Set;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * Represents a group of {@link CNode}s whose relative distances to each other are preserved.
 * CGroups are used to ensure that vertical edge segments, that are connected to north/south ports,
 * are kept at the position of the port.
 * 
 * @see OneDimensionalCompactor
 * 
 * @author dag
 */
public final class CGroup {
    /** root position of the {@link CGroup}. */
    private double startPos;
    // Variables are public for convenience reasons since this class is used internally only.
    // SUPPRESS CHECKSTYLE NEXT 6 VisibilityModifier
    /** grouped {@link CNode}s. */
    public Set<CNode> cNodes;
    /** constraints pointing to CNodes not within the {@link CGroup}. */
    public Set<CNode> incomingConstraints;
    /** number of constraints originating from within the {@link CGroup}. */
    public int outDegree;

    /**
     * The constructor for a {@link CGroup} receives a {@link CNode} since a {@link CGroup} has to
     * contain at least one.
     * @param node the {@link CNode} to add
     */
    public CGroup(final CNode node) {
        cNodes = Sets.newLinkedHashSet();
        incomingConstraints = Sets.newLinkedHashSet();
        outDegree = 0;
        addCNode(node);
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
     */
    public void compactInnerCNodes() {

        // adding CNodes that are initially sinks to startNodes
        Queue<CNode> startNodes = Lists.newLinkedList();
        for (CNode node : cNodes) {
            // a CNode is a sink if all outgoing constraints are processed
            if (node.outDegree == 0) {
                startNodes.add(node);

                // CNodes can be locked in place to avoid pulling clusters apart
                if (node.reposition) {
                    node.startPos = 0;
                } else {
                    node.startPos = node.getPosition();
                }
            }
        }

        // processing startNodes and deriving start positions of incoming CNodes from constraints
        while (!startNodes.isEmpty()) {
            CNode node = startNodes.poll();
            for (CNode inc : node.incoming) {
                // process constraints from within the CGroup only
                if (!incomingConstraints.contains(inc)) {
                    inc.updateStartPos(node);

                    // adding new sinks to the queue
                    if (inc.outDegree == 0) {
                        startNodes.add(inc);
                    }
                }
            }
        }

        // finding the required root position for the CGroup that satisfies the constraints of each
        // member
        CNode firstCNode = cNodes.iterator().next();
        startPos = firstCNode.startPos - firstCNode.cGroupOffset;
        for (CNode node : cNodes) {
            startPos = Math.max(startPos, node.startPos - node.cGroupOffset);
        }
        // setting the positions of the CGroups members according to their specified offset
        for (CNode node : cNodes) {
            node.startPos = startPos + node.cGroupOffset;
        }
    }

    /**
     * Propagates starting positions according to the {@link CGroup}s incoming constraints.
     * This method may be called after {@link #compactInnerCNodes()}.
     * @return a list of {@link CGroup}s that became {@link #isInnerCompactable() compactable}
     */
    public List<CGroup> propagate() {
        List<CGroup> compactables = Lists.newArrayList();
        
        for (CNode node : cNodes) {
            for (CNode inc : node.incoming) {
                // processing constraints that refer to CNodes outside of this CGroup
                if (incomingConstraints.contains(inc)) {
                    // updating the starting position of an incoming constraint
                    inc.updateStartPos(node);
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
     * Adding a {@link CNode} to the {@link CGroup} and updating the incoming constraints.
     * @param node the {@link CNode} to add
     */
    public void addCNode(final CNode node) {
        cNodes.add(node);
        node.cGroup = this;
        // ensuring that only incoming constraints are added, that refer to CNodes outside of this CGroup
        incomingConstraints.remove(node);
        for (CNode inc : node.incoming) {
            if (!cNodes.contains(inc)) {
                incomingConstraints.add(inc);
            }
        }
    }
}
