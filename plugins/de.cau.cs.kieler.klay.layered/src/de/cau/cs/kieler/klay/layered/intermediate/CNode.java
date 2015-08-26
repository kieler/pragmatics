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
import java.util.Set;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import de.cau.cs.kieler.core.math.KVectorChain;
import de.cau.cs.kieler.kiml.util.nodespacing.Rectangle;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LGraphElement;
import de.cau.cs.kieler.klay.layered.graph.LInsets;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.properties.InternalProperties;
import de.cau.cs.kieler.klay.layered.properties.Properties;

/**
 * Internal representation of a node in the constraint graph specifying a hitbox for the
 * {@link LGraphElement}.
 * @author dag
 *
 */
public final class CNode { //impl icomp
    
    // easy access of fields ;)
    //TODO better justification
    // SUPPRESS CHECKSTYLE NEXT 30 VisibilityModifier
    boolean isNode;
    // used for reversing constraints
    List<CNode> tmpIncoming = Lists.newArrayList();
    // flags a node to be repositioned
    boolean reposition = true;
    LNode lNode;// TODO LNode or just position? hmm need margins
    Rectangle hitbox;
    // specify particular vertical edge segments and affected junction points
    KVectorChain bends;
    KVectorChain juctionPoints;
    // representation of constraints
    List<CNode> incoming = Lists.newArrayList();
    int outDegree = 0;
    // TODO could there be multiple parents?
    CNode parentNode = null; //TODO parentCGroup
    double relativePositionX;
    double cGroupOffset; //TODO like relativePositionX
    int pendingNSSegments = 0;
    List<CNode> connectedNSSegments = null;
    double startX = Double.NEGATIVE_INFINITY;
    CGroup group; //TODO cGroup
    LGraph layeredGraph;
    //testing zero spacing
    Set<LEdge> lEdges = Sets.newLinkedHashSet();

    /**
     * Creates new constraint node
     * 
     * @param elem
     *            the graph element
     * @param hitbox
     *            the constraints are inferred from this box
     */
    CNode(final LNode lNode, final Rectangle hitbox, final LGraph layeredGraph) {
        this.lNode = lNode;
        this.hitbox = hitbox;
        this.isNode = true;
        this.layeredGraph = layeredGraph;
        this.relativePositionX = 0;
    }

    CNode(final VerticalSegment vSeg, final LGraph layeredGraph) {
        this.isNode = false;
        this.bends = new KVectorChain(vSeg.bend1, vSeg.bend2);
        this.juctionPoints = new KVectorChain(vSeg.junctionPoints);
        this.hitbox = new Rectangle(vSeg.x, vSeg.y1, 0, vSeg.y2 - vSeg.y1);
        this.parentNode = vSeg.parentNode; //TODO rename parentCGroup
        this.relativePositionX = vSeg.relativePositionX;
        if(vSeg.lEdge!=null)this.lEdges.add(vSeg.lEdge);
        this.layeredGraph = layeredGraph;
    }

    void addSegment(final VerticalSegment vSeg) {
        this.bends.addAll(vSeg.bend1, vSeg.bend2);
        this.juctionPoints.addAll(vSeg.junctionPoints);
        double newY1 = Math.min(this.hitbox.y, vSeg.y1);
        double newY2 = Math.max(this.hitbox.y + this.hitbox.height, vSeg.y2);
        this.hitbox.setRect(vSeg.x, newY1, 0, newY2 - newY1);
        if(vSeg.lEdge!=null)this.lEdges.add(vSeg.lEdge);
        // TODO necessary to merge ns stuff?
    }

    // TODO private boolean intersects(CNode o)
    
    private LInsets getMargin() {
        if (this.isNode) {
            return this.lNode.getMargin();
        }
        return new LInsets(0, 0, 0, 0);
    }
    
    private double getSpacing(CNode other) {
        if (this.isNode && other.isNode) {
            return (double) this.layeredGraph.getProperty(InternalProperties.SPACING);
        }
        // TODO testing zero spacing
        if (this.parentNode != null && other.parentNode != null
                && !Sets.intersection(this.lEdges, other.lEdges).isEmpty()) {
            return 0;
        }
        return this.layeredGraph.getProperty(InternalProperties.SPACING)
                * this.layeredGraph.getProperty(Properties.EDGE_SPACING_FACTOR);
    }
    
    public void updateStartX(CNode outgoingCNode) {
            // determine if object or edge spacing should be used
            // also retrieving margins around nodes
            double spacing = getSpacing(outgoingCNode);
            LInsets margin1 = outgoingCNode.getMargin();
            LInsets margin2 = this.getMargin();

            // calculating rightmost position according to constraints
            double newStartX =
                    Math.max(this.startX, outgoingCNode.startX + outgoingCNode.hitbox.width + margin1.right
                            + margin2.left + spacing);
            double currentX;
            if (this.isNode) {
                currentX = this.lNode.getPosition().x;
            } else {
                currentX = this.bends.getFirst().x;
            }
            
            this.outDegree--;
            
            if (this.reposition || newStartX < currentX) {
                this.startX = newStartX;
            } else {
                this.startX = currentX;
                //TODO would outDegree=0 work in this case?? probably!
            }

            this.outDegree--; // FIXME set 0 to prevent unnecessary loops

//            // set startNodes for the next iteration
//            if (this.outDegree == 0) {
//                startNodes.add(this);
//            }
            System.out.println("updateStartX: "+this.startX+" "+this+" out: "+outgoingCNode);
    }

    // TODO test
    @Override
    public String toString() {
        if (this.isNode) {
            return this.lNode.getName() + "(o:" + this.cGroupOffset + ")";
        } else {
            return "seg(" + this.hitbox.x + ", " + this.hitbox.y + ")" + "(o:" + this.cGroupOffset + ")";
        }
    }
}
