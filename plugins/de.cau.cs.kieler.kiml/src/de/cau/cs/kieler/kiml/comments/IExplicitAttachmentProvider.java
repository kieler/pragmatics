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

import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KNode;

/**
 * Knows which graph elements comments were explicitly attached to by the user. This of course only
 * makes sense for comment attachment in languages whose editors allow users to explicitly attach
 * comments to things.
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
public interface IExplicitAttachmentProvider {
    
    /**
     * Finds and returns the {@link KGraphElement} the given comment was explicitly attached to by
     * the user, if any.
     * 
     * @param comment
     *            the comment whose explicit attachment to find.
     * @return the graph element the comment is explicitly attached to, or {@code null} if there is
     *         none. If the graph element is not {@code null}, it must be in the same hierarchy
     *         level as the comment node.
     */
    KGraphElement findExplicitAttachment(KNode comment);
    
    /**
     * Does any preprocessing necessary. This method is called before the first invocation of
     * {@link #findExplicitAttachment(KNode)} for a given graph.
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
    
}
