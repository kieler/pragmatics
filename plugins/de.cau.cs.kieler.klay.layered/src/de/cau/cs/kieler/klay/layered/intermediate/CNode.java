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
 * A {@link CNode} contains an element ?edge?node?
 * @see CLNode
 * @see CLEdge
 * @author dag
 *TODO explain a constraint etc.
 */
public abstract class CNode {
    
    // SUPPRESS CHECKSTYLE NEXT 30 VisibilityModifier
    boolean isNode; //TODO design question: should one set this bool in the constructor or implement an abstract function ?
    // flags a node to be repositioned
    boolean reposition = true;
    
    Rectangle hitbox;
    
    // representation of constraints
    List<CNode> incoming = Lists.newArrayList();
    int outDegree = 0;
    CNode parentNode = null; //this is needed for zero spacing and possibly elsewhere

    double cGroupOffset;
    
    double startPos = Double.NEGATIVE_INFINITY;
    CGroup cGroup;
    LGraph layeredGraph;
    //testing zero spacing

    public CNode(final LGraph layeredGraph) {
        this.layeredGraph = layeredGraph;
    }
    
    public double getSpacing(final CNode other) {
        if (isNode && other.isNode) {
            return (double) layeredGraph.getProperty(InternalProperties.SPACING);
        }
        // testing zero spacing
        if (parentNode != null && other.parentNode != null
                && !Sets.intersection(((CLEdge)this).originalLEdges, ((CLEdge)other).originalLEdges).isEmpty()) {
            return 0;
        }
        return layeredGraph.getProperty(InternalProperties.SPACING)
                * layeredGraph.getProperty(Properties.EDGE_SPACING_FACTOR);
    }
    
    public void updateStartPos(CNode outgoingCNode) {
            // determine if object or edge spacing should be used
            double spacing = getSpacing(outgoingCNode);

            // calculating rightmost position according to constraints
            double newStartPos =
                    Math.max(startPos, outgoingCNode.startPos + outgoingCNode.hitbox.width + spacing);
            double currentX = getPosition();
            
            outDegree -= 2; //FIXME wtf
            
            if (reposition || newStartPos < currentX) {
                startPos = newStartPos;
            } else {
                startPos = currentX;
                outDegree = 0;
            }

    }
    /**
     * get.
     * @return
     */
    public double getPosition() {
        return hitbox.x;
    }
    public void setPosition() {
        hitbox.x = startPos;
    }
    public abstract void setElementPosition();
    public abstract double getElementPosition();

    // just for debugging
    @Override
    public String toString() {
        if (isNode) {
            return ((CLNode)this).lNode.getName() + "(o:" + cGroupOffset + ")";
        } else {
            return "seg(" + hitbox.x + ", " + hitbox.y + ")" + "(o:" + cGroupOffset + ")";
        }
    }
}
