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

import com.google.common.base.Objects.ToStringHelper;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import de.cau.cs.kieler.kiml.util.nodespacing.Rectangle;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LGraph;

/**
 * Internal representation of a node in the constraint graph.
 * 
 * This class is extended to handle specific {@link LGraphElement}s.
 * 
 * @see CLNode
 * @see CLEdge
 * @author dag
 */
public abstract class CNode {
    // Variables are public for convenience reasons since this class is used internally only.
    // SUPPRESS CHECKSTYLE NEXT 22 VisibilityModifier
    /** the associated layered graph. */
    public LGraph layeredGraph;
    /** containing {@link CGroup}. */
    public CGroup cGroup;
    /** refers to the parent node of a north/south segment. */
    public CNode parentNode = null;
    
    /** representation of constraints. */
    public List<CNode> constraints = Lists.newArrayList();
    /** number of {@link CNode}s imposing a constraint on this one. */
    public int outDegree = 0;
    
    /** the area occupied by this element including margins for ports and labels. */
    public Rectangle hitbox;
    /** offset to the root position of the containing {@link CGroup} . */
    public double cGroupOffset;
    /** leftmost possible position for this {@link CNode} to be drawn. */
    public double startPos = Double.NEGATIVE_INFINITY;
    /** flags a node to be repositioned in the case of left/right balanced compaction. */
    public boolean reposition = true;
    /** a 4 tuple stating if the {@link CNode} is locked in a particular direction. */
    public CompactionLock lock;

    /**
     * Constructor.
     * 
     * @param layeredGraph
     *            the associated layered graph
     */
    public CNode(final LGraph layeredGraph) {
        this.layeredGraph = layeredGraph;
        lock = new CompactionLock();
    }

    /**
     * Returns the required spacing to the specified {@link CNode}.
     * 
     * @param other
     *            the other {@link CNode}
     * @return the spacing
     */
    public double getSpacing(final CNode other) {

        // joining north/south segments that belong to the same edge by setting their spacing to 0
        if (parentNode != null && other.parentNode != null
                // this might seem quite expensive but in most cases the sets contain only one element
                && !Sets.intersection(getOriginalEdges(), other.getOriginalEdges()).isEmpty()) {
            return 0;
        }

        // returning edge spacing if an edge is involved, otherwise object spacing
        return Math.min(getSingleSpacing(), other.getSingleSpacing());
    }

    // TODO too specific for cnode
    /**
     * Gets a set of {@link LEdge}s that belong to a segment.
     * 
     * @return the original edges
     */
    public abstract Set<LEdge> getOriginalEdges();

    /**
     * Returns object spacing for a {@link LNode} and edge spacing for a {@link LEdge}.
     * If the {@link LNode} is an {@link NodeType#EXTERNAL_PORT external port} the returned spacing is 0.
     * This works because {@link LGraphUtil#getExternalPortPosition(LGraph, LNode, double, double)
     * LGraphUtil.getExternalPortPosition} is called in 
     * {@link de.cau.cs.kieler.klay.layered.graph.transform.KGraphLayoutTransferrer#applyLayout(LGraph)
     * KGraphLayoutTransferrer.applyLayout} and resets the position of the
     * {@link NodeType#EXTERNAL_PORT external port} dummy, which results in the correct border spacing
     * being used.
     * 
     * @return the spacing
     */
    abstract double getSingleSpacing();

    /**
     * Updates the leftmost possible starting position of this {@link CNode} according to
     * outgoingCNode.
     * 
     * @param outgoingCNode
     *            the {@link CNode} imposing a constraint on this one
     */
    public void updateStartPos(final CNode outgoingCNode) {
        // determine if object or edge spacing should be used
        double spacing = getSpacing(outgoingCNode);

        // calculating rightmost position according to constraints
        double newStartPos =
                Math.max(startPos, outgoingCNode.startPos + outgoingCNode.hitbox.width + spacing);
        double currentPos = getPosition();

        outDegree --;

        // setting new position is the CNode is flagged to be repositioned
        // or if the current position doesn't comply with the required spacing
        if (reposition || newStartPos < currentPos) {
            startPos = newStartPos;
        } else {
            startPos = currentPos;
            outDegree = 0;
        }

    }

    /**
     * Getter for the position.
     * 
     * @return position of the hitbox
     */
    public double getPosition() {
        return hitbox.x;
    }

    /**
     * Applies the compacted starting position to the hitbox. Used after compaction to allow
     * reverse transformation of hitboxes.
     */
    public void applyPosition() {
        hitbox.x = startPos;
    }

    /**
     * Sets the position of the {@link LGraphElement} according to the hitbox.
     */
    public abstract void applyElementPosition();

    /**
     * Returns the position of the {@link LGraphElement}.
     * 
     * @return the position
     */
    public abstract double getElementPosition();
}
