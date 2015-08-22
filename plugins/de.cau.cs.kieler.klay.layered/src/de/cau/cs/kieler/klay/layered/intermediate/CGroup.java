/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2015 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.layered.intermediate;

import java.util.List;
import java.util.Queue;
import java.util.Set;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import de.cau.cs.kieler.klay.layered.graph.LInsets;

/**
 * @author dag
 *
 */
final class CGroup implements ICompactable {
    // FIXME anything that is altered here has to be reset before compactRight!
    private double startX;
    private List<CNode> cNodes;
    // constraints pointing to CNodes not withhin the group
    private Set<CNode> outgoingConstraints;
    private Set<CNode> incomingConstraints;
    //TODO use,  make private?
    public int inDegree, outDegree;

    public boolean isInnerCompactable() {
        return outDegree == 0;
//        return outgoingConstraints.isEmpty();
    }

    public void compactInnerCNodes() {
        Queue<CNode> startNodes = Lists.newLinkedList();
        for (CNode node : cNodes) {
            if (node.outDegree == 0) {
                startNodes.add(node);
                if (node.isNode) {
                    node.startX = node.lNode.getPosition().x;
                } else {
                    node.startX = node.bends.getFirst().x;
                }
            }
        }

        while (!startNodes.isEmpty()) {
            CNode node = startNodes.poll();
            for (CNode inc : node.incoming) {
                if (!this.incomingConstraints.contains(inc)) {
                    inc.updateStartX(node);
                    if (inc.outDegree == 0) {
                        startNodes.add(inc);
                    }
                }
            }
        }
        
        CNode rightmostCNode = cNodes.get(0);
        for (CNode node : cNodes) {
            if (node.startX > rightmostCNode.startX) {
                rightmostCNode = node;
            }
        }
        this.startX = rightmostCNode.startX - rightmostCNode.cGroupOffset;
        for (CNode node : cNodes) {
            node.startX = this.startX + node.cGroupOffset;
        }
    }

    public List<CGroup> propagate() {
        List<CGroup> compactables = Lists.newArrayList();
        for (CNode node : cNodes) {
            for (CNode inc : node.incoming) {
                if (this.incomingConstraints.contains(inc)) {
                    inc.updateStartX(node);
                    inc.group.outDegree--;
                    if (inc.group.isInnerCompactable()) {
                        compactables.add(inc.group);
                    }
                }
            }
        }
        return compactables;
    }

    public CGroup(double startX) {
        this.startX = startX;
        this.cNodes = Lists.newArrayList();
        this.outgoingConstraints = Sets.newHashSet();
        this.incomingConstraints = Sets.newHashSet();
    }

    // TODO interface inherited
    /**
     * {@inheritDoc}
     */
    @Override
    public List<CNode> getCNodes() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getPendingPlacements() {
        // TODO Auto-generated method stub
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setordecpendingplacements() {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getOffset(CNode cNode) {
        // TODO Auto-generated method stub
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getStartX() {
        return startX;
    }

}
