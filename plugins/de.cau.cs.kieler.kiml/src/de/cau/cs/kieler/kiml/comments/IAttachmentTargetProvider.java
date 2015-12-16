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

import java.util.List;

import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KNode;

/**
 * Returns all graph elements that are possible attachment targets for a given comment. Most clients
 * will want to use the {@link SiblingAttachmentTargetProvider}. However, simply returning all
 * siblings may not be good enough in terms of performance. Other, optimized strategies can thus be
 * plugged in at will.
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
 * @see SiblingAttachmentTargetProvider
 * @author cds
 */
@FunctionalInterface
public interface IAttachmentTargetProvider {
    
    /**
     * Returns all attachment targets for the given comment. This may include other comments.
     * 
     * @param comment
     *            the comment whose possible attachment targets to provide.
     * @return attachment targets for the comment. All attachment targets need to be siblings of the
     *         comment node.
     */
    List<KGraphElement> provideAttachmentTargetsFor(KNode comment);
    
    /**
     * Does any preprocessing necessary. This method is called before the first invocation of
     * {@link #provideAttachmentTargetsFor(KNode)} for a given graph.
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
     * Does any cleaning necessary to get the implementation ready for the next comment attachment run.
     * This method is called after the last invocation of {@link #provideAttachmentTargetsFor(KNode)} for
     * a given graph.
     * 
     * @implSpec
     * The default implementation does nothing.
     */
    default void cleanup() {
    }
    
}
