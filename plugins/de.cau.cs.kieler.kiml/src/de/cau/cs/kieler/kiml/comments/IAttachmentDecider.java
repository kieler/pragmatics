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

/**
 * An attachment decider has the final say on which graph element to attach a comment to, if any. The
 * decision is based on the normalized heuristic values produced by the heuristics for each possible
 * graph element the comment can be attached to.
 * 
 * @author cds
 */
@FunctionalInterface
public interface IAttachmentDecider {
    
    /** Result that indicates that the comment shouldn't be attached to anything. */
    int NO_ATTACHMENT = -1;  // Implicitly static and final
    
    
    /**
     * Decides which graph element to attach a comment to, if any.
     * 
     * @param normalizedHeuristics
     *            the normalized heuristic results for each possible attachment target. The first
     *            dimension describes the attachment targets, the second dimension contains the
     *            normalized heuristic results for the comment and the respective attachment target.
     *            Attachment deciders may assume that this is neither {@code null}, nor empty. If it
     *            was empty, the comment attacher wouldn't call the decider in the first place.
     * @return index of the selected attachment target, or {@link #NO_ATTACHMENT} if the comment
     *         should be left unattached.
     */
    int makeAttachmentDecision(double[][] normalizedHeuristics);
    
}
