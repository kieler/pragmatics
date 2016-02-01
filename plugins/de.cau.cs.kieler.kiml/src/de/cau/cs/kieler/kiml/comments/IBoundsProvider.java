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
import java.awt.geom.Rectangle2D.Double;
import java.util.Map;

import com.google.common.collect.Maps;

import de.cau.cs.kieler.core.kgraph.KNode;

/**
 * Knows how to retrieve the position and size of graph elements. The straightforward thing would be
 * to just take the bounds as defined in the shape layout, which a default bounds provider actually
 * does. However, comment attachment may need to use original bounds as preserved in an original
 * model as opposed to the KGraph version, which may have different sizes and positions.
 * 
 * <p>
 * Note that if an implementation holds state, all resources should be released once
 * {@link #cleanup()} is called.
 * </p>
 * 
 * <p>
 * If clients implementing this interface don't need any preprocessing or cleanup, this interface
 * can be used as a functional interface.
 * </p>
 * 
 * <p>
 * Bounds providers can be turned into caching bounds providers by calling {@link #cached()} on a
 * bounds provider. Cached bounds providers only calculate the bounds of nodes the first time, and
 * use cached bounds afterwards. Use cached bounds providers as proxies for computationally
 * expensive bounds providers.
 * </p>
 * 
 * @see ShapeLayoutBoundsProvider
 * @author cds
 */
@FunctionalInterface
public interface IBoundsProvider {
    
    /**
     * Returns the position and size to be used for the given node during comment attachment.
     * 
     * @param node
     *            the node whose bounds to retrieve.
     * @return the node's bounds, or {@code null} if the provider was unable to retrieve the bounds.
     */
    Rectangle2D.Double boundsFor(KNode node);
    
    /**
     * Does any preprocessing necessary. This method is called before the first invocation of
     * {@link #boundsFor(KNode)} for a given graph.
     * 
     * @implSpec
     * The default implementation does nothing.
     * 
     * @param graph
     *            the graph.
     * @param includeHierarchy
     *            if {@code true}, the preprocessing is done not only on the current hierarchy
     *            level, but also on all sub levels. Implementations may choose to behave
     *            differently depending on this value.
     */
    default void preprocess(KNode graph, boolean includeHierarchy) {
    }
    
    /**
     * Does any cleaning necessary to get the implementation ready for the next comment attachment
     * run. This method is called after the last invocation of {@link #boundsFor(KNode)} for a given
     * graph.
     * 
     * @implSpec The default implementation does nothing.
     */
    default void cleanup() {
    }
    
    /**
     * Returns a bounds provider that acts as a cache around this bounds provider.
     * 
     * @return a caching bounds provider around this bounds provider.
     */
    default IBoundsProvider cached() {
        return new IBoundsProvider() {
            
            /** Cache for bounds. */
            private final Map<KNode, Rectangle2D.Double> boundsCache = Maps.newHashMap();
            
            @Override
            public Double boundsFor(final KNode node) {
                // The cache may actually map a node to null if the bounds provider was unable to
                // retrieve bounds for the node
                if (boundsCache.containsKey(node)) {
                    return boundsCache.get(node);
                } else {
                    Rectangle2D.Double bounds = IBoundsProvider.this.boundsFor(node);
                    boundsCache.put(node, bounds);
                    return bounds;
                }
            }
            
            @Override
            public void preprocess(final KNode graph, final boolean includeHierarchy) {
                // Forward to wrapped provider
                IBoundsProvider.this.preprocess(graph, includeHierarchy);
            }

            @Override
            public void cleanup() {
                boundsCache.clear();
                
                // Forward to wrapped provider
                IBoundsProvider.this.cleanup();
            }
            
        };
    }
    
}
