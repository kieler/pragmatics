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
package de.cau.cs.kieler.kiml.comments;

import java.awt.geom.Rectangle2D;

import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KNode;

/**
 * A heuristic based on the alignment between comments and nodes. A comment can be left/right- or
 * top/bottom-aligned to a node. The alignment is computed as the smallest offset between any
 * perfect alignment. It usually makes sense to combine this heuristic with some sort of
 * distance-based cutoff. Use the methods named {@code withXXX} to configure the heuristic.
 * 
 * @see IBoundsProvider
 * @author cds
 */
public final class AlignmentHeuristic extends AbstractNormalizedHeuristic {
    
    /** The bounds provider to use. */
    private IBoundsProvider boundsProvider = new ShapeLayoutBoundsProvider();
    
    
    /////////////////////////////////////////////////////////////////////////////////////////////
    // Configuration
    
    /**
     * Configures the heuristic to consider the given offset as the offset at which a comment is not
     * considered to be attached to a node anymore.
     * 
     * @param offset
     *            the maximum possible offset.
     * @return this object for method chaining.
     */
    public AlignmentHeuristic withMaximumAlignmentOffset(final double offset) {
        if (offset <= 0) {
            throw new IllegalArgumentException("Maximum alignment offset must be > 0.");
        }
        
        super.withBounds(offset, 0);
        return this;
    }
    
    /**
     * Configures the heuristic to use the given bounds provider to determine the bounds of
     * comments.
     * 
     * <p>
     * If this method is not called, the {@link ShapeLayoutBoundsProvider} is used by default.
     * </p>
     * 
     * @param provider
     *            the bounds provider to use.
     * @return this object for method chaining.
     */
    public AlignmentHeuristic withBoundsProvider(final IBoundsProvider provider) {
        if (provider == null) {
            throw new IllegalArgumentException("Bounds provider must not be null.");
        }
        
        this.boundsProvider = provider;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AlignmentHeuristic withNormalizationFunction(
            final NormalizationFunction normalizationFunction) {
        
        super.withNormalizationFunction(normalizationFunction);
        return this;
    }
    
    
    /////////////////////////////////////////////////////////////////////////////////////////////
    // AbstractNormalizedHeuristic

    /**
     * {@inheritDoc}
     */
    @Override
    public double raw(final KNode comment, final KGraphElement element) {
        if (element instanceof KNode) {
            KNode node = (KNode) element;
            
            Rectangle2D.Double commentBounds = boundsProvider.boundsFor(comment);
            Rectangle2D.Double nodeBounds = boundsProvider.boundsFor(node);
            
            double alignment = alignment(commentBounds, nodeBounds);
            return alignment == -1 ? getWorstRawValue() : alignment;
        } else {
            return getWorstRawValue();
        }
    }
    
    
    /////////////////////////////////////////////////////////////////////////////////////////////
    // Alignment Calculation
    
    // Constants to easily check where points lie with respect to a rectangle
    private static final int TOP = Rectangle2D.OUT_TOP;
    private static final int LEFT = Rectangle2D.OUT_LEFT;
    private static final int RIGHT = Rectangle2D.OUT_RIGHT;
    private static final int BOTTOM = Rectangle2D.OUT_BOTTOM;
    private static final int TOP_LEFT = Rectangle2D.OUT_TOP | Rectangle2D.OUT_LEFT;
    private static final int BOTTOM_LEFT = Rectangle2D.OUT_BOTTOM | Rectangle2D.OUT_LEFT;
    private static final int TOP_RIGHT = Rectangle2D.OUT_TOP | Rectangle2D.OUT_RIGHT;
    private static final int BOTTOM_RIGHT = Rectangle2D.OUT_BOTTOM | Rectangle2D.OUT_RIGHT;
    
    /**
     * Compute the alignment offset between the two shapes defined by the given bounds. Shapes can
     * be left/right- or top/bottom-aligned. The alignment is computed as the smallest offset
     * between any perfect alignment. If the two shapes are cater-cornered, they are not considered
     * to be aligned.
     *
     * @param bounds1
     *            the first shape.
     * @param bounds2
     *            the second shape.
     * @return the smallest alignment offset, or {@code -1} if the two shapes are not aligned.
     */
    public static double alignment(final Rectangle2D.Double bounds1, final Rectangle2D.Double bounds2) {
        // Calculate where the top left and bottom right points of bounds1 lie with regard to bounds2
        int topLeftOutcode = bounds2.outcode(bounds1.x, bounds1.y);
        int bottomRightOutcode = bounds2.outcode(bounds1.x + bounds1.width, bounds1.y + bounds1.height);

        // Do not compute the alignment if the shapes are cater-cornered
        if ((bottomRightOutcode & topLeftOutcode & TOP_LEFT) == TOP_LEFT
            || (bottomRightOutcode & topLeftOutcode & TOP_RIGHT) == TOP_RIGHT
            || (bottomRightOutcode & topLeftOutcode & BOTTOM_LEFT) == BOTTOM_LEFT
            || (bottomRightOutcode & topLeftOutcode & BOTTOM_RIGHT) == BOTTOM_RIGHT) {
            
                 return -1;
        
        } else {
            // The shapes relate in some way, so we'll compute the horizontal and vertical alignment
            double horizontalAlignmentOffset = Math.min(
                Math.abs(bounds2.x - bounds1.x),
                Math.abs(bounds2.x + bounds2.width - bounds1.x - bounds1.width));
            
            double verticalAlignmentOffset = Math.min(
                Math.abs(bounds2.y - bounds1.y),
                Math.abs(bounds2.y + bounds2.height - bounds1.y - bounds1.height));
            
            
            if (bounds2.intersects(bounds1)) {
                // The shapes intersect, so use the minimum of the horizontal and vertical alignment
                return Math.min(horizontalAlignmentOffset, verticalAlignmentOffset);
                
            } else if ((bottomRightOutcode & TOP) != 0 || (topLeftOutcode & BOTTOM) != 0) {
                // The shapes are above / below, so use the vertical alignment
                return horizontalAlignmentOffset;
                
            } else if ((bottomRightOutcode & LEFT) != 0 || (topLeftOutcode & RIGHT) != 0) {
                // The shapes are left / right, so use the vertical alignment
                return verticalAlignmentOffset;
                
            } else {
                // Actually, this case shouldn't happen, but well... better be safe.
                return -1;
            }
        }
    }
    
}
