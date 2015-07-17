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

import java.util.Set;

import com.google.common.collect.Sets;

import de.cau.cs.kieler.core.math.KVectorChain;
import de.cau.cs.kieler.kiml.util.nodespacing.Rectangle;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LNode;

/**
 * Internal representation of a node in the constraint graph specifying a hitbox for the
 * {@link LGraphElement}.
 * @author dag
 *
 */
public final class CNode {
    
    // easy access of fields ;)
    //TODO better justification
    // SUPPRESS CHECKSTYLE NEXT 30 VisibilityModifier
    boolean isNode;
    // used for reversing constraints
    Set<CNode> tmpIncoming = Sets.newHashSet();
    // flags a node to be repositioned
    boolean reposition = true;
    LNode lNode;// TODO LNode or just position? hmm need margins
    Rectangle hitbox;
    // specify particular vertical edge segments and affected junction points
    KVectorChain bends;
    KVectorChain juctionPoints;
    // representation of constraints
    Set<CNode> incoming = Sets.newHashSet();// Lists.newArrayList();
    int outDegree = 0;
    // TODO could there be multiple parents?
    CNode parentNode = null;
    double relativePositionX;
    int pendingNSSegments = 0;
    Set<CNode> connectedNSSegments = null;
    double startX = Double.NEGATIVE_INFINITY;
    //testing zero spacing
    Set<LEdge> lEdges = Sets.newHashSet();

    /**
     * Creates new constraint node
     * 
     * @param elem
     *            the graph element
     * @param hitbox
     *            the constraints are inferred from this box
     */
    CNode(final LNode lNode, final Rectangle hitbox) {
        this.lNode = lNode;
        this.hitbox = hitbox;
        this.isNode = true;
    }

    CNode(final VerticalSegment vSeg) {
        this.isNode = false;
        this.bends = new KVectorChain(vSeg.bend1, vSeg.bend2);
        this.juctionPoints = new KVectorChain(vSeg.junctionPoints);
        this.hitbox = new Rectangle(vSeg.x, vSeg.y1, 0, vSeg.y2 - vSeg.y1);
        this.parentNode = vSeg.parentNode;
        this.relativePositionX = vSeg.relativePositionX;
        if(vSeg.lEdge!=null)this.lEdges.add(vSeg.lEdge);
    }

    void addSegment(final VerticalSegment vSeg) { // TODO check this!!
        this.bends.addAll(vSeg.bend1, vSeg.bend2);
        this.juctionPoints.addAll(vSeg.junctionPoints);
        double newY1 = Math.min(this.hitbox.y, vSeg.y1);
        double newY2 = Math.max(this.hitbox.y + this.hitbox.height, vSeg.y2);
        this.hitbox.setRect(vSeg.x, newY1, 0, newY2 - newY1);
        if(vSeg.lEdge!=null)this.lEdges.add(vSeg.lEdge);
        // TODO necessary to merge ns stuff?
    }

    // TODO private boolean intersects(CNode o)

    // TODO test
    @Override
    public String toString() {
        if (this.isNode) {
            return this.lNode.getName();
        } else {
            return "seg(" + this.hitbox.x + ", " + this.hitbox.y + ")";
        }
    }
}
