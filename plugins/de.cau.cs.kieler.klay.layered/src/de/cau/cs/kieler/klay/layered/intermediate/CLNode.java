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


import java.util.Set;

import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;

import de.cau.cs.kieler.kiml.options.Direction;
import de.cau.cs.kieler.kiml.util.nodespacing.Rectangle;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LNode.NodeType;
import de.cau.cs.kieler.klay.layered.properties.InternalProperties;

/**
 * Representation of a {@link LNode} in the constraint graph.
 * 
 * @see CNode
 * @author dag
 */
public final class CLNode extends CNode {
    // SUPPRESS CHECKSTYLE NEXT 2 VisibilityModifier
    /** the node. */
    public LNode lNode;

    /**
     * Constructor, infers hitbox from position, size and margins of the node.
     * 
     * @param lNode
     *            the node
     * @param layeredGraph
     *            the containing layered graph
     */
    public CLNode(final LNode lNode, final LGraph layeredGraph) {
        super(layeredGraph);
        this.lNode = lNode;
        hitbox =
                new Rectangle(lNode.getPosition().x - lNode.getMargin().left, lNode.getPosition().y
                        - lNode.getMargin().top, lNode.getSize().x + lNode.getMargin().left
                        + lNode.getMargin().right, lNode.getSize().y + lNode.getMargin().top
                        + lNode.getMargin().bottom);

        cGroupOffset = 0;
        
        // locking the node for directions that no edges are connected in
        // at the moment this applies only to left/right compaction
        if (Iterables.isEmpty(lNode.getIncomingEdges())) {
            lock.set(true, Direction.LEFT);
        }
        if (Iterables.isEmpty(lNode.getOutgoingEdges())) {
            lock.set(true, Direction.RIGHT);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void applyElementPosition() {
        lNode.getPosition().x = hitbox.x + lNode.getMargin().left;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getElementPosition() {
        return lNode.getPosition().x;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getSingleSpacing() {
        if (lNode.getNodeType() == NodeType.EXTERNAL_PORT) {
            return 0;
        }
        return (double) layeredGraph.getProperty(InternalProperties.SPACING);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<LEdge> getOriginalEdges() {
        return Sets.newLinkedHashSet();
    }
    
    @Override
    public String toString() {
        return lNode.toString();
    }
}
