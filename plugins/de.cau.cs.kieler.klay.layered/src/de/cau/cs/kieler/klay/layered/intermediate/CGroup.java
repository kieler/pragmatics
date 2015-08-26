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

//TODO use /** */ remove this.
/**
 * @author dag
 *
 */
final class CGroup implements ICompactable {
    // FIXME anything that is altered here has to be reset before compactRight!
    private double startX;
    public Set<CNode> cNodes;
    // constraints pointing to CNodes not within the group
    //private Set<CNode> outgoingConstraints;
    public Set<CNode> incomingConstraints;
    public int outDegree;

    public boolean isInnerCompactable() {
        return outDegree == 0;
    }

    public void compactInnerCNodes() {
        Queue<CNode> startNodes = Lists.newLinkedList();
        for (CNode node : cNodes) {
            if (node.outDegree == 0) {
                startNodes.add(node);
                if (node.reposition) {
                    node.startX = 0;
                } else {
                    if (node.isNode) {
                        node.startX = node.lNode.getPosition().x;
                    } else {
                        node.startX = node.bends.getFirst().x;
                    }
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
        
        CNode rightmostCNode = cNodes.iterator().next();
        for (CNode node : cNodes) {
            if (node.startX - node.cGroupOffset > rightmostCNode.startX - rightmostCNode.cGroupOffset) {
                rightmostCNode = node;
            }
            //better?? this.startX = Math.max(this.startX, ode.startX - node.cGroupOffset);
        }
        this.startX = rightmostCNode.startX - rightmostCNode.cGroupOffset;
        System.out.println("\n");
        for (CNode node : cNodes) {
            node.startX = this.startX + node.cGroupOffset;
            System.out.println("new startX: " +"("+node.startX+")"+node);
        }
    }

    public List<CGroup> propagate() {
        List<CGroup> compactables = Lists.newArrayList();
        for (CNode node : cNodes) {
            for (CNode inc : node.incoming) {//need the node
                if (this.incomingConstraints.contains(inc)) {//FIXME incoming Constraints is a set but multiple nodes in the group have the same incoming so outDegree is too low
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
    
    public void addCNode(CNode node) {
        cNodes.add(node);
        node.group = this;
        //FIXME not generic
        node.cGroupOffset = node.relativePositionX;
        //TODO does this even work??
        incomingConstraints.remove(node);
//        this.outDegree += node.outDegree;//FIXME don't count constraints from within
        for (CNode inc : node.incoming) {
            if (!this.cNodes.contains(inc)) {
                this.incomingConstraints.add(inc);
            }
        }
    }

    public CGroup(CNode node /* not used double startX*/) {
        //this.startX = startX;
        this.cNodes = Sets.newLinkedHashSet();
        //this.outgoingConstraints = Sets.newHashSet();
        this.incomingConstraints = Sets.newLinkedHashSet();
        outDegree = 0;
        addCNode(node);
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
