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

import de.cau.cs.kieler.core.kgraph.KNode;

/**
 * Determines if a comment is eligible for attachment based on its size. Use the methods named
 * {@code withXXX} to configure the filter.
 * 
 * @author cds
 */
public class SizeEligibilityFilter implements IEligibilityFilter {
    
    /** The bounds provider to use. */
    private IBoundsProvider boundsProvider = new ShapeLayoutBoundsProvider();
    /** The maximum area for a comment to still be eligible. */
    private double maxArea = -1;
    
    
    /////////////////////////////////////////////////////////////////////////////////////////////
    // Configuration
    
    /**
     * Configures the heuristic to consider comments up to the given are to be attachable.
     * 
     * <p>
     * If this method is not called, no comment is considered eligible for attachment.
     * </p>
     * 
     * @param area
     *            the maximum possible area.
     * @return this object for method chaining.
     */
    public SizeEligibilityFilter withMaximumArea(final double area) {
        if (area < 0) {
            throw new IllegalArgumentException("Maximum area must be >= 0.");
        }
        
        maxArea = area;
        
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
    public SizeEligibilityFilter withBoundsProvider(final IBoundsProvider provider) {
        if (provider == null) {
            throw new IllegalArgumentException("Bounds provider must not be null.");
        }
        
        this.boundsProvider = provider;
        return this;
    }
    
    
    /////////////////////////////////////////////////////////////////////////////////////////////
    // IEligibilityFilter

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean eligibleForAttachment(final KNode comment) {
        Rectangle2D.Double bounds = boundsProvider.boundsFor(comment);
        return bounds.height * bounds.width <= maxArea;
    }

}
