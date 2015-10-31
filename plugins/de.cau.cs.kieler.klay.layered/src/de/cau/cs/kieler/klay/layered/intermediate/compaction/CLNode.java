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
package de.cau.cs.kieler.klay.layered.intermediate.compaction;

import com.google.common.collect.Iterables;

import de.cau.cs.kieler.kiml.options.Direction;
import de.cau.cs.kieler.kiml.util.nodespacing.Rectangle;
import de.cau.cs.kieler.klay.layered.compaction.CNode;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LNode.NodeType;
import de.cau.cs.kieler.klay.layered.properties.InternalProperties;
import de.cau.cs.kieler.klay.layered.properties.Properties;

/**
 * Representation of a {@link LNode} in the constraint graph.
 * 
 * @see CNode
 * @author dag
 */
public final class CLNode extends CNode {
    /** the associated horizontal spacing. */
    private double horizontalSpacing;
    /** the associated horizontal spacing. */
    private double verticalSpacing;
    /** the node. */
    private LNode lNode;

    /**
     * Constructor, infers hitbox from position, size and margins of the {@link LNode}. Also sets
     * the {@link de.cau.cs.kieler.klay.layered.compaction.CompactionLock CompactionLock} according
     * to the ratio of incoming to outgoing {@link de.cau.cs.kieler.klay.layered.graph.LEdge LEdge}
     * s. {@link NodeType#EXTERNAL_PORT external port dummy nodes} are never locked to keep the
     * graph size minimal. They are later reset to the border position by the
     * {@link de.cau.cs.kieler.klay.layered.graph.transform.KGraphLayoutTransferrer
     * KGraphLayoutTransferrer}
     * 
     * @param lNode
     *            the node
     * @param layeredGraph
     *            the containing layered graph
     */
    public CLNode(final LNode lNode, final LGraph layeredGraph) {
        // getting the spacing properties
        horizontalSpacing = (double) layeredGraph.getProperty(InternalProperties.SPACING);
        verticalSpacing = horizontalSpacing
                        * layeredGraph.getProperty(Properties.OBJ_SPACING_IN_LAYER_FACTOR);
        
        // calculating the necessary hitbox dimensions
        this.lNode = lNode;
        hitbox =
                new Rectangle(lNode.getPosition().x - lNode.getMargin().left, lNode.getPosition().y
                        - lNode.getMargin().top, lNode.getSize().x + lNode.getMargin().left
                        + lNode.getMargin().right, lNode.getSize().y + lNode.getMargin().top
                        + lNode.getMargin().bottom);

        cGroupOffset.reset();
        
        // locking the node for directions that fewer edges are connected in
        int difference = Iterables.size(lNode.getIncomingEdges())
                       - Iterables.size(lNode.getOutgoingEdges());
        if (difference < 0) {
            lock.set(true, Direction.LEFT);
        } else if (difference > 0) {
            lock.set(true, Direction.RIGHT);
        }
        
        // excluding external port dummies
        if (lNode.getType() == NodeType.EXTERNAL_PORT) {
            lock.set(false, false, false, false);
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
     * <p>
     * If the {@link LNode} is an {@link NodeType#EXTERNAL_PORT external port} the returned spacing
     * is 0. This works because {@link de.cau.cs.kieler.klay.layered.graph.LGraphUtil
     * LGraphUtil#getExternalPortPosition(LGraph, LNode, double, double)
     * LGraphUtil.getExternalPortPosition} is called in
     * {@link de.cau.cs.kieler.klay.layered.graph.transform.KGraphLayoutTransferrer#applyLayout(LGraph)
     * KGraphLayoutTransferrer.applyLayout} and resets the position of the
     * {@link NodeType#EXTERNAL_PORT external port} dummy, which results in the correct border
     * spacing being used.
     * </p>
     */
    @Override
    public double getHorizontalSpacing() {
        if (lNode.getType() == NodeType.EXTERNAL_PORT) {
            return 0;
        }
        return horizontalSpacing;
    }

    /**
     * {@inheritDoc}
     * <p>
     * If the {@link LNode} is an {@link NodeType#EXTERNAL_PORT external port} the returned spacing
     * is 0. This works because {@link de.cau.cs.kieler.klay.layered.graph.LGraphUtil
     * LGraphUtil#getExternalPortPosition(LGraph, LNode, double, double)
     * LGraphUtil.getExternalPortPosition} is called in
     * {@link de.cau.cs.kieler.klay.layered.graph.transform.KGraphLayoutTransferrer#applyLayout(LGraph)
     * KGraphLayoutTransferrer.applyLayout} and resets the position of the
     * {@link NodeType#EXTERNAL_PORT external port} dummy, which results in the correct border
     * spacing being used.
     * </p>
     */
    @Override
    public double getVerticalSpacing() {
        if (lNode.getType() == NodeType.EXTERNAL_PORT) {
            return 0;
        }
        return verticalSpacing;
    }

    @Override
    public String toString() {
        return lNode.getProperty(InternalProperties.ORIGIN).toString();
    }
    
}
