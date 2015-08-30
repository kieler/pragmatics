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

import de.cau.cs.kieler.kiml.util.nodespacing.Rectangle;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LNode;

/**
 * @see CNode
 * @author dag
 */
public final class CLNode extends CNode {
    LNode lNode;
    
    /**
     * Creates new constraint node.
     * @param elem
     *            the graph element
     * @param hitbox
     *            the constraints are inferred from this box
     */
    public CLNode(final LNode lNode, final LGraph layeredGraph) {
        super(layeredGraph);
        this.lNode = lNode;
        hitbox = new Rectangle(lNode.getPosition().x - lNode.getMargin().left,
                                    lNode.getPosition().y - lNode.getMargin().top,
                                    lNode.getSize().x + lNode.getMargin().left + lNode.getMargin().right,
                                   lNode.getSize().y + lNode.getMargin().top + lNode.getMargin().bottom);
        isNode = true;
        cGroupOffset = 0;// lNode.getMargin().left;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setElementPosition() {
        lNode.getPosition().x = hitbox.x + lNode.getMargin().left;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getElementPosition() {
        return lNode.getPosition().x;
    }
}
