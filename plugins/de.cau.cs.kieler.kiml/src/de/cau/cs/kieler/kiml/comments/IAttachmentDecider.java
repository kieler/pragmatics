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

import java.util.Map;

import de.cau.cs.kieler.core.kgraph.KGraphElement;

/**
 * An attachment decider has the final say on which graph element to attach a comment to, if any. The
 * decision is based on the normalized heuristic values produced by the heuristics for each possible
 * graph element the comment can be attached to.
 * 
 * @author cds
 */
@FunctionalInterface
public interface IAttachmentDecider {
    
    /**
     * Decides which graph element to attach a comment to, if any.
     * 
     * @param normalizedHeuristics
     *            maps possible attachment targets to a map from heuristic implementations to the
     *            normalized heuristic values they produced.
     * @return the selected attachment target, or {@code null} if the comment should be left
     *         unattached.
     */
    KGraphElement makeAttachmentDecision(
            Map<KGraphElement, Map<Class<? extends IHeuristic>, Double>> normalizedHeuristics);
    
}
