/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.layered.intermediate;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.klay.layered.ILayoutProcessor;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LNode;

/**
 * 
 * The width of the resulting layout is bound by the required number of layers, i.e., the longest
 * path of the created directed acyclic graph. This processor finds the longest path and decides if
 * it is feasible to reverse additional edges.
 * 
 * <dl>
 * <dt>Precondition:</dt>
 *      <dd>A directed, acyclic graph.</dd>
 * <dt>Postcondition:</dt>
 *      <dd>The same graph, possibly with some additionally reverted edges.</dd>
 * <dt>Slots:</dt>
 *      <dd>After phase 1.</dd>
 * <dt>Same-slot dependencies:</dt>
 *      <dd>None.</dd>
 * </dl>
 * 
 * @author uru
 */
public class AdditionalEdgeReverser implements ILayoutProcessor {

    private List<LNode> topOrdering = Lists.newLinkedList();
    private Set<LNode> unmarkedNodes = Sets.newHashSet();

    private int index = 0;
    private int[] pathLengths;
    private boolean[] sinks;

    /*---------------------------
     * Configuration Parameters
     * -------------------------- */
    /**
     * Determines the length difference to the longest path for which the slightly shorter path is
     * also handled.
     */
    private int pathLengthDelta = 1;
    /** The threshold after which long path's edges are tried to be reversed. */
    private int pathLengthThresh = 1 + 1 + 1 + 1;

    // experimental
    private boolean useWeigtedPaths = false;

    /**
     * {@inheritDoc}
     */
    public void process(final LGraph layeredGraph, final IKielerProgressMonitor monitor) {
        monitor.begin("Additional Edge Reverser", 1);

        // get configuration
        // TODO
        // pathLengthThresh = layeredGraph.getProperty(Properties.MAXIMAL_PATH_LENGTH);
        // pathLengthDelta = layeredGraph.getProperty(Properties.MAXIMAL_PATH_LENGTH_DELTA);

        // calculate the long paths
        List<List<LEdge>> longPaths = getLongestPath(layeredGraph);

        // remember edges that we already reverted
        Set<LEdge> alreadyReverted = Sets.newHashSet();

        // handle edge long path
        for (List<LEdge> longestPath : longPaths) {
            System.out.println("Long Path: " + longestPath);

            if (longestPath.size() > pathLengthThresh) {
                // select a candidate
                int pos = longestPath.size() / 2;

                LEdge candidate = longestPath.get(pos);

                while (!isValidCandidate(candidate)) {
                    if (pos > 2) {
                        candidate = longestPath.get(pos);
                    } else {
                        System.out.println("No candidate found!");
                        candidate = null;
                        break;
                    }
                    pos--;
                }

                System.out.println("Selected cadidate " + candidate);
                if (candidate != null) {
                    LNode source = candidate.getSource().getNode();
                    LNode target = candidate.getTarget().getNode();
                    // reverse ALL edges between the edge's two nodes
                    List<LEdge> toReverse = Lists.newLinkedList();
                    for (LEdge out : source.getOutgoingEdges()) {
                        if (out.getTarget().getNode().equals(target)) {
                            toReverse.add(out);
                            System.out.println("reverting " + out);
                        }
                    }

                    for (LEdge e : toReverse) {

                        // check if the edge is a direct neighbour of an edge
                        // that we already reverted
                        boolean isNeighbour = true;
                        for (LEdge before : alreadyReverted) {
                            // as we reverted the 'before' edge already
                            // we have to check source-source and target-target of the edges
                            if (e.getSource().getNode().equals(before.getSource().getNode())
                                    || before.getTarget().getNode().equals(e.getTarget().getNode())) {
                                isNeighbour = false;
                            }
                        }

                        // don't revert edges that are neighbours
                        // don't revert edges that we already reverted
                        if (isNeighbour && !alreadyReverted.contains(e)) {
                            e.reverse(layeredGraph, true);
                            alreadyReverted.add(e);
                        }

                    }
                }
            }
        }

        dispose();
        monitor.done();
    }

    private boolean isValidCandidate(final LEdge candidate) {

        // does the source node have any outgoing edges with a target different than the original
        // one?
        for (LEdge outs : candidate.getSource().getOutgoingEdges()) {
            if (!outs.getTarget().getNode().equals(candidate.getTarget().getNode())) {
                return false;
            }
        }

        // analog: target node, incoming edges
        for (LEdge ins : candidate.getTarget().getIncomingEdges()) {
            if (!ins.getSource().getNode().equals(candidate.getSource().getNode())) {
                return false;
            }
        }

        return true;

    }

    /**
     * Release all created resources so the GC can reap them.
     */
    private void dispose() {
        pathLengths = null;
        topOrdering.clear();
        unmarkedNodes.clear();
    }

    private List<List<LEdge>> getLongestPath(final LGraph layeredGraph) {

        index = 0;
        topOrdering.clear();
        unmarkedNodes.clear();
        unmarkedNodes.addAll(layeredGraph.getLayerlessNodes());

        while (!unmarkedNodes.isEmpty()) {
            visit(unmarkedNodes.iterator().next());
        }

        pathLengths = new int[layeredGraph.getLayerlessNodes().size()];
        sinks = new boolean[layeredGraph.getLayerlessNodes().size()];
        Arrays.fill(pathLengths, 0);
        Arrays.fill(sinks, false);

        // System.out.println(layeredGraph.getLayerlessNodes().size() + " " + index);
        // System.out.println(Arrays.toString(pathLengths));

        int overallMax = -1;

        // calculate path lengths ending at each node
        for (LNode n : topOrdering) {
            int max = -1;
            boolean hasIncoming = false;
            for (LEdge incoming : n.getIncomingEdges()) {
                hasIncoming = true;
                LNode source = incoming.getSource().getNode();
                max = Math.max(max, pathLengths[source.id]);
            }
            // assign 0 if no incoming edges, else max + path weight
            // the weight depends on whether we consider weighted edges
            int pathWeight = useWeigtedPaths ? ((int) n.getSize().x) : 1;
            pathLengths[n.id] = hasIncoming ? max + pathWeight : 0;
            sinks[n.id] = Iterables.isEmpty(n.getOutgoingEdges());

            // remember the currently longest path
            if (pathLengths[n.id] > overallMax) {
                overallMax = pathLengths[n.id];
            }
        }

        // get all paths that are within the margin
        List<LNode> longPathEnds = Lists.newLinkedList();
        for (LNode n : topOrdering) {
            // make sure that no subpaths are selected by only considering sinks
            if (sinks[n.id] && pathLengths[n.id] >= (overallMax - pathLengthDelta)) {
                longPathEnds.add(n);
            }
        }

        // backtrack the long paths
        List<List<LEdge>> longPaths = Lists.newLinkedList();
        for (LNode longPath : longPathEnds) {
            longPaths.add(backtrack(longPath));
        }

        return longPaths;
    }

    private List<LEdge> backtrack(final LNode sink) {
        List<LEdge> longestPath = Lists.newArrayList();
        LNode currentNode = sink;
        while (true) {
            int max = -1;
            LEdge maxEdge = null;
            for (LEdge incoming : currentNode.getIncomingEdges()) {
                LNode source = incoming.getSource().getNode();
                if (pathLengths[source.id] > max) {
                    max = pathLengths[source.id];
                    maxEdge = incoming;
                }
            }

            // if no edge is found the path is finished
            if (maxEdge == null) {
                break;
            } else {
                // add at head to start at the beginning
                longestPath.add(0, maxEdge);
                currentNode = maxEdge.getSource().getNode();
            }
        }

        return longestPath;
    }

    private void visit(final LNode node) {
        if (!unmarkedNodes.contains(node)) {
            return;
        }

        // visit all outgoing edges
        for (LEdge edge : node.getOutgoingEdges()) {
            LNode target = edge.getTarget().getNode();
            visit(target);
        }
        // mark the node and add it to the topological ordering
        unmarkedNodes.remove(node);
        topOrdering.add(0, node);

        // assign an index to the node
        node.id = index++;
    }

}
