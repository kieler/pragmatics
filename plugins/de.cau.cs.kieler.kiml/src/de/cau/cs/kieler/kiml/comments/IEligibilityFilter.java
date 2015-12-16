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

import de.cau.cs.kieler.core.kgraph.KNode;

/**
 * Determines if a given comment is eligible for attachment to a graph element. There are types of
 * comments that can very well stand on their own, without any need for attachment. An eligibility
 * filter identifies such comments.
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
 * @author cds
 */
@FunctionalInterface
public interface IEligibilityFilter {
    
    /**
     * Checks if the given comment can be attached to a graph element.
     * 
     * @param comment
     *            the comment.
     * @return {@code true} if the comment can be attached to graph elements, {@code false} if the
     *         comment is meant to be standalone.
     */
    boolean eligibleForAttachment(KNode comment);
    
    /**
     * Does any preprocessing necessary. This method is called before the first invocation of
     * {@link #eligibleForAttachment(KNode)} for a given graph.
     * 
     * @implSpec The default implementation does nothing.
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
     * run. This method is called after the last invocation of {@link #eligibleForAttachment(KNode)}
     * for a given graph.
     * 
     * @implSpec The default implementation does nothing.
     */
    default void cleanup() {
    }
    
}
