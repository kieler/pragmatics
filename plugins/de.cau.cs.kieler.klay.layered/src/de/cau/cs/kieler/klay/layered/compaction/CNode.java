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
package de.cau.cs.kieler.klay.layered.compaction;

import java.util.List;

import com.google.common.collect.Lists;

import de.cau.cs.kieler.kiml.util.nodespacing.Rectangle;

/**
 * Internal representation of a node in the constraint graph.
 * 
 * For instance his class is extended to handle specific {@link LGraphElement}s.
 * 
 * @see CLNode
 * @see CLEdge
 * @author dag
 */
public abstract class CNode {
    // Variables are public for convenience reasons since this class is used internally only.
    // SUPPRESS CHECKSTYLE NEXT 22 VisibilityModifier
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
    /** leftmost possible position for this {@link CNode} to be drawn. 
     *  This position can be intermediate and is increased to its final value by updateStartPos(). */
    public double startPos = Double.NEGATIVE_INFINITY;
    /** flags a {@link CNode} to be repositioned in the case of left/right balanced compaction. */
    public boolean reposition = true;
    /** a 4 tuple stating if the {@link CNode} should locked in a particular direction based on
     *  conditions defined in an extended class. */
    public CompactionLock lock = new CompactionLock();

    /**
     * Returns the required horizontal spacing to the specified {@link CNode}.
     * 
     * @param other
     *            the other {@link CNode}
     * @return the spacing
     */
    public abstract double getHorizontalSpacing(final CNode other);
    
    /**
     * Returns the required vertical spacing to the specified {@link CNode}.
     * 
     * @param other
     *            the other {@link CNode}
     * @return the spacing
     */
    public double getVerticalSpacing(final CNode other) {
        // returning edge spacing if an edge is involved, otherwise object spacing
        return Math.min(getSingleVerticalSpacing(), other.getSingleVerticalSpacing());
    }

    /**
     * Returns the vertical spacing that is associated with the contained {@link LGraphElement}.
     * 
     * @return the spacing
     */
    public abstract double getSingleHorizontalSpacing();
    
    /**
     * Returns the vertical spacing that is associated with the contained {@link LGraphElement}.
     * 
     * @return the spacing
     */
    public abstract double getSingleVerticalSpacing();

    /**
     * Updates the leftmost possible starting position of this {@link CNode} according to
     * the constraint that outgoingCNode imposes on this {@link CNode}.
     * 
     * @param outgoingCNode
     *            the {@link CNode} imposing a constraint on this one
     */
    public void updateStartPos(final CNode outgoingCNode) {
        // determine if object or edge spacing should be used
        double spacing = getHorizontalSpacing(outgoingCNode);

        // calculating rightmost position according to constraints
        double newStartPos =
                Math.max(startPos, outgoingCNode.startPos + outgoingCNode.hitbox.width + spacing);
        double currentPos = getPosition();

        // decrementing the number of constraints that still have to be processed for this CNode
        outDegree--;

        // setting new position if the CNode is flagged to be repositioned
        if (reposition) {
            startPos = newStartPos;
        } else {
            startPos = currentPos;
            // preventing unnecessary iterations if CNode is locked
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
