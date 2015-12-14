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

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutData;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.util.KimlUtil;

/**
 * GENERAL DESCRIPTION.
 * 
 * DETAILS ON WHAT COMPONENTS THERE ARE TO COMMENT ATTACHMENT.
 * 
 * CONFIGURATION.
 * 
 * MINIMAL LIST OF CONFIGURATION METHODS YOU PROBABLY WANT TO CALL.
 * {@link #withAttachmentDecider(IAttachmentDecider)}
 * {@link #addEligibilityFilter(IEligibilityFilter)}
 * {@link #addHeuristic(IHeuristic)}
 * 
 * DETAILS ON EXPLICIT HEURISTICS.
 * 
 * @author cds
 */
public final class CommentAttacher {
    
    /** Whether to go down on the graph or not. */
    private boolean includeHierarchy = true;
    /** Whether the presence of explicit attachments disables the heuristic attachments. */
    private boolean explicitAttachmentsDisableHeuristics = true;
    /** Retrieves graph elements explicitly attached to a comment by the user. */
    private IExplicitAttachmentProvider explicitAttachmentProvider = (a) -> null;
    /** The bounds provider to be used. */
    private IBoundsProvider boundsProvider = new ShapeLayoutBoundsProvider();
    /** The attachment target provider. */
    private IAttachmentTargetProvider targetProvider = new SiblingAttachmentTargetProvider();
    /** List of eligibility filters. */
    private List<IEligibilityFilter> eligibilityFilters = Lists.newArrayList();
    /** List of attachment heuristics. */
    private List<IHeuristic> heuristics = Lists.newArrayList();
    /** The attachment decider. */
    private IAttachmentDecider attachmentDecider = (a) -> null;
    
    
    /////////////////////////////////////////////////////////////////////////////////////////////
    // Configuration
    
    /**
     * Configures comment attachment to only attach comments on the current hierarchy level.
     * 
     * <p>
     * If this method is not called, comments on all hierarchy levels below the one passed to the
     * attacher are attached.
     * </p>
     * 
     * @return this comment attacher for method chaining.
     */
    public CommentAttacher limitToCurrentHierarchyLevel() {
        includeHierarchy = false;
        return this;
    }
    
    /**
     * Configures comment attachment to keep using heuristics to attach comments even if explicit
     * attachments are found.
     * 
     * <p>
     * If this method is not called, heuristics are disabled if explicit attachments are found.
     * </p>
     * 
     * @return this comment attacher for method chaining.
     */
    public CommentAttacher keepHeuristicsEnabledWithExplicitAttachments() {
        explicitAttachmentsDisableHeuristics = false;
        return this;
    }
    
    /**
     * Configures comment attachment to use the given explicit attachment provider. See the class
     * documentation for more information on how explicit attachments are handled.
     * 
     * <p>
     * If this method is not called, no explicit attachments are recognized by default.
     * </p>
     * 
     * @param provider
     *            the explicit attachment provider. Can be {@code null} to disable explicit
     *            attachments.
     * @return this comment attacher for method chaining.
     */
    public CommentAttacher withExplicitAttachmentProvider(final IExplicitAttachmentProvider provider) {
        if (provider == null) {
            explicitAttachmentProvider = (a) -> null;
        } else {
            explicitAttachmentProvider = provider;
        }
        
        return this;
    }
    
    /**
     * Configures comment attachment to use the given bounds provider. See the class documentation
     * for more information on bounds providers.
     * 
     * <p>
     * If this method is not called, the {@link ShapeLayoutBoundsProvider} is used by default.
     * </p>
     * 
     * @param provider
     *            the non-{@code null} bounds provider.
     * @return this comment attacher for method chaining.
     * @throws IllegalArgumentException
     *             if {@code provider == null}.
     */
    public CommentAttacher withBoundsProvider(final IBoundsProvider provider) {
        if (provider == null) {
            throw new IllegalArgumentException("The bounds provider must not be null.");
        } else {
            boundsProvider = provider;
        }
        
        return this;
    }
    
    /**
     * Configures comment attachment to use the given attachment target provider. See the class
     * documentation for more information on attachment target providers.
     * 
     * <p>
     * If this method is not called, the {@link SiblingAttachmentTargetProvider} is used by default,
     * configured such that other comments are not considered valid attachment targets.
     * </p>
     * 
     * @param provider
     *            the non-{@code null} attachment target provider.
     * @return this comment attacher for method chaining.
     * @throws IllegalArgumentException
     *             if {@code provider == null}.
     */
    public CommentAttacher withAttachmentTargetProvider(final IAttachmentTargetProvider provider) {
        if (provider == null) {
            throw new IllegalArgumentException("The attachment target provider must not be null.");
        } else {
            targetProvider = provider;
        }
        
        return this;
    }
    
    /**
     * Adds the given eligibility filter to the list of eligiblity filters to be used. See the class
     * documentation for more information on eligibility filters.
     * 
     * <p>
     * If this method is not called, all comments are considered eligible for attachment.
     * </p>
     * 
     * @param filter
     *            the non-{@code null} eligibility filter.
     * @return this comment attacher for method chaining.
     * @throws IllegalArgumentException
     *             if {@code filter == null}.
     */
    public CommentAttacher addEligibilityFilter(final IEligibilityFilter filter) {
        if (filter == null) {
            throw new IllegalArgumentException("The eligibility filter must not be null.");
        } else {
            eligibilityFilters.add(filter);
        }
        
        return this;
    }
    
    /**
     * Adds the given attachment heuristic to the list of heuristics to be used. See the class
     * documentation for more information on eligibility filters.
     * 
     * <p>
     * If this method is not called, no comment will be heuristically attached to anything.
     * </p>
     * 
     * @param heuristic
     *            the non-{@code null} attachment heuristic.
     * @return this comment attacher for method chaining.
     * @throws IllegalArgumentException
     *             if {@code heuristic == null}.
     */
    public CommentAttacher addHeuristic(final IHeuristic heuristic) {
        if (heuristic == null) {
            throw new IllegalArgumentException("The attachment heuristic must not be null.");
        } else {
            heuristics.add(heuristic);
        }
        
        return this;
    }
    
    /**
     * Configures comment attachment to use the given attachment decider. See the class
     * documentation for more information on attachment deciders.
     * 
     * <p>
     * If this method is not called, no comments are ever heuristically attached to anything. This is
     * probably not what you want.
     * </p>
     * 
     * @param decider
     *            the non-{@code null} attachment decider.
     * @return this comment attacher for method chaining.
     * @throws IllegalArgumentException
     *             if {@code decider == null}.
     */
    public CommentAttacher withAttachmentDecider(final IAttachmentDecider decider) {
        if (decider == null) {
            throw new IllegalArgumentException("The attachment target provider must not be null.");
        } else {
            attachmentDecider = decider;
        }
        
        return this;
    }
    
    
    /////////////////////////////////////////////////////////////////////////////////////////////
    // Actual Logic
    
    /**
     * Runs the comment attachment heuristic with its current configuration on the graph rooted in
     * the given node. After running the heuristic, edges may have been added to the graph. All such
     * edges are returned by this method for further processing by the caller.
     * 
     * @param graph
     *            the graph to run comment attachment on.
     * @return collection of all edges created to attach comments to nodes. These may need to be
     *         processed further by the caller.
     */
    public Collection<KEdge> attachComments(final KNode graph) {
        // Do necessary preprocessing
        preprocess(graph);
        
        // We keep track of all comment->graph element pairs we find
        Collection<Pair<KNode, KGraphElement>> explicitAttachments = Lists.newArrayList();
        Collection<Pair<KNode, KGraphElement>> heuristicAttachments = Lists.newArrayList();
        
        // Iterate over all nodes we should iterate over
        Queue<KNode> processingQueue = Lists.newLinkedList(graph.getChildren());
        while (!processingQueue.isEmpty()) {
            KNode node = processingQueue.poll();
            
            // Only comments need to be processed
            if (isComment(node)) {
                // Find explicit attachment
                KGraphElement explicitAttachment =
                        explicitAttachmentProvider.findExplicitAttachment(node);
                
                if (explicitAttachment != null) {
                    // We'll use the explicit attachment
                    explicitAttachments.add(Pair.of(node, explicitAttachment));
                    
                } else if (isEligibleForHeuristicAttachment(node)
                        && (explicitAttachments.isEmpty() || !explicitAttachmentsDisableHeuristics)) {
                    
                    // We don't even bother calculating the heuristics if they are disabled by explicit
                    // attachments anyway. If that's not the case, let's roll!
                    KGraphElement heuristicAttachment = findHeuristicAttachment(node);
                    
                    if (heuristicAttachment != null) {
                        heuristicAttachments.add(Pair.of(node, heuristicAttachment));
                    }
                }
            }
            
            // If we include hierarchy, check if the current node has children
            if (includeHierarchy) {
                processingQueue.addAll(node.getChildren());
            }
        }
        
        // Tell everyone to clean up after themselves
        cleanup();
        
        // Turn the attachments into edges and return them
        Collection<KEdge> createdEdges = edgeifyFoundAttachments(
                explicitAttachments, heuristicAttachments);
        return createdEdges;
    }

    /**
     * Gives everything a chance to preprocess stuff.
     * 
     * @param graph
     *            the graph to attach comments on.
     */
    private void preprocess(final KNode graph) {
        explicitAttachmentProvider.preprocess(graph, includeHierarchy);
        boundsProvider.preprocess(graph, includeHierarchy);
        targetProvider.preprocess(graph, includeHierarchy);
        eligibilityFilters.stream().forEach((f) -> f.preprocess(graph, includeHierarchy));
        heuristics.stream().forEach((h) -> h.preprocess(graph, includeHierarchy));
    }

    /**
     * Checks whether the given node is a comment.
     * 
     * @param node
     *            the node to check.
     * @return {@code true} if the node is a comment.
     */
    private boolean isComment(final KNode node) {
        KLayoutData layoutData = node.getData(KLayoutData.class);
        return layoutData.getProperty(LayoutOptions.COMMENT_BOX);
    }

    /**
     * Checks whether the given comment is eligible for heuristically finding attachment targets.
     * 
     * @param comment
     *            the comment to check.
     * @return {@code true} if the comment may be attached to things.
     */
    private boolean isEligibleForHeuristicAttachment(final KNode comment) {
        return eligibilityFilters.stream().allMatch((f) -> f.eligibleForAttachment(comment));
    }
    
    /**
     * Runs the attachment heuristics on the given comment and returns the graph element it is to be
     * attached to, if any.
     * 
     * @param comment
     *            the comment to run the attachment heuristics on.
     * @return the graph element the comment is to be attached to, or {@code null} if there isn't
     *         any.
     */
    private KGraphElement findHeuristicAttachment(final KNode comment) {
        // If there are no heuristics, return nothing...
        if (heuristics.isEmpty()) {
            return null;
        }
        
        // Collect attachment target candidates
        List<KGraphElement> candidates = targetProvider.provideAttachmentTargetsFor(comment);
        if (candidates == null || candidates.isEmpty()) {
            return null;
        }
        
        // Collect the heuristic results in this map, indexed by attachment target, then indexed by
        // the heuristic
        Map<KGraphElement, Map<IHeuristic, Double>> results = Maps.newHashMap();
        
        for (KGraphElement candidate : candidates) {
            Map<IHeuristic, Double> candidateResults = Maps.newHashMap();
            results.put(candidate, candidateResults);
            
            // Run the normalized heuristics and collect their results in an array
            for (IHeuristic heuristic : heuristics) {
                candidateResults.put(heuristic, heuristic.normalized(comment, candidate));
            }
        }
        
        // Decide which attachment target to attach the comment to
        return attachmentDecider.makeAttachmentDecision(results);
    }
    
    /**
     * Introduces edges into the graph to apply the found attachments. The created edges are
     * returned for further processing by the caller. If there are explicit attachments, the
     * heuristic attachments are only applies if the presence of explicit attachments doesn't
     * disable the heuristics.
     * 
     * @implNote Attachments are currently only applied if the attachment target is a node.
     * 
     * @param explicitAttachments
     *            collection of explicit attachments from comment nodes to graph elements.
     * @param heuristicAttachments
     *            collection of heuristic attachments from comment nodes to graph elements.
     * @return collection of created edges.
     */
    private Collection<KEdge> edgeifyFoundAttachments(
            final Collection<Pair<KNode, KGraphElement>> explicitAttachments,
            final Collection<Pair<KNode, KGraphElement>> heuristicAttachments) {
        
        Collection<KEdge> createdEdges = Lists.newArrayListWithCapacity(
                explicitAttachments.size() + heuristicAttachments.size());
        
        // Explicit attachments
        createdEdges.addAll(edgeifyFoundAttachments(explicitAttachments));
        
        // Only apply heuristic attachments if they are still enabled
        if (explicitAttachments.isEmpty() || !explicitAttachmentsDisableHeuristics) {
            createdEdges.addAll(edgeifyFoundAttachments(heuristicAttachments));
        }
        
        return createdEdges;
    }
    
    /**
     * Introduces edges into the graph to apply the found attachments. The created edges are
     * returned for further processing by the caller.
     * 
     * @implNote Attachments are currently only applied if the attachment target is a node.
     * 
     * @param attachments
     *            collection of attachments from comment nodes to graph elements.
     * @return collection of created edges.
     */
    private Collection<KEdge> edgeifyFoundAttachments(
            final Collection<Pair<KNode, KGraphElement>> attachments) {
        
        Collection<KEdge> createdEdges = Lists.newArrayListWithCapacity(attachments.size());
        
        for (Pair<KNode, KGraphElement> attachment : attachments) {
            // We currently only allow nodes as attachment targets
            if (!(attachment.getSecond() instanceof KNode)) {
                continue;
            }
            
            KNode comment = attachment.getFirst();
            KNode target = (KNode) attachment.getSecond();
            
            KEdge edge = KimlUtil.createInitializedEdge();
            edge.setSource(comment);
            edge.setTarget(target);
            
            createdEdges.add(edge);
        }
        
        return createdEdges;
    }
    
    /**
     * Gives everything a chance to cleanup.
     */
    private void cleanup() {
        explicitAttachmentProvider.cleanup();
        boundsProvider.cleanup();
        targetProvider.cleanup();
        eligibilityFilters.stream().forEach((f) -> f.cleanup());
        heuristics.stream().forEach((h) -> h.cleanup());
    }
    
}
