/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.planar.p1planar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.klay.planar.ILayoutPhase;
import de.cau.cs.kieler.klay.planar.IntermediateProcessingStrategy;
import de.cau.cs.kieler.klay.planar.graph.PEdge;
import de.cau.cs.kieler.klay.planar.graph.PGraph;
import de.cau.cs.kieler.klay.planar.graph.PNode;
import de.cau.cs.kieler.klay.planar.properties.Properties;
import de.cau.cs.kieler.klay.planar.util.IFunction;

/**
 * The main class for the LRPlanarity (left-right-planarity) unit of the planarization plug-in. It
 * provides a linear time algorithm for planarity testing and a linear time algorithm for
 * determining an embedding of a planar subgraph (and the subgraph itself) by the
 * left-right-planarity criterion developed by H. de Fraysseix, P. Rosenstiehl and P.O.Mendes, which
 * says, that a graph is planar, if and only if it admits a bipartition, where for every fork u -> v
 * with u -> v tree edge and e1, e2 outgoing edges of v (1) all return edges of e1 ending strictly
 * higher than the lowpoint of e2 belong to one class and (2) all return edges of e2 ending strictly
 * higher than the lowpoint of e1 belong to the other class. Consider, that here the lowpoint is
 * defined as the height of the lowest return point of an edge by a tree path of arbitrary length
 * followed by one final back edge and therefore differs from the usual definition of a lowpoint.
 * Implementation based on U. Brandes: "The Left-Right-Planarity test" (unpublished, retrieved May,
 * 10th 2010). For an overview of the algorithm and further explanations, see also Daniel Kaiser:
 * "Das Links Rechts Planaritätskriterium" (seminar paper, German, downloadable from:
 * {@linkplain www.inf.uni-konstanz.de/algo/lehre/ws08/projekt/ausarbeitungen/kaiser.pdf}).
 * 
 * @see de.cau.cs.kieler.klay.planar.p1planar.rtprak.planarization.IPlanarityTester IPlanarityTester
 * @see de.cau.cs.kieler.klay.planar.graph.impl.IGraph IGraph
 * @see de.cau.cs.kieler.klay.planar.graph.impl.PNode PNode
 * @see de.cau.cs.kieler.klay.planar.graph.impl.PEdge PEdge
 * 
 * @author pdo
 */
public class LRPlanarSubgraphBuilder extends AbstractAlgorithm implements ILayoutPhase {

    // ====================== Attributes ======================================

    /** A flag in {@code planarSubgraph()} indicating, whether the graph is planar. */
    private boolean isPlanar;

    /**
     * The edges of the input graph, whose addition will cause non-planarity (i.e. the edges of the
     * input graph, that are not part of the planar subgraph of G determined by
     * {@code planarSubgraph()}). Note, that these edges are only those, that have been identified
     * during current iteration. A list of all crossing edges is saved in {@code planarSubgraph()}
     * itself.
     */
    private LinkedList<PEdge> crossingEdges;

    /** The DFS-roots of all connected components in the graph. */
    private LinkedList<PNode> roots;

    /** Source node of every edge in the DFS-oriented tree. */
    private PNode[] dfsSource;

    /** Target node of every edge in the DFS-oriented tree. */
    private PNode[] dfsTarget;

    /**
     * The parent tree edge of every node in the depth-first-search or {@code null}, if the node is
     * the root of a connected component.
     */
    private PEdge[] parentEdge;

    /**
     * The tree path distance of all nodes to the root of the DFS-tree in the current connected
     * component.
     */
    private int[] height;

    /**
     * Height of lowest return point of all edges (i.e. the height of the lowest node reachable by
     * an arbitrary sequence of tree edges followed by one final back edge). Note, that this
     * definition differs from the usual definition of the lowpoint, since here, we define the
     * lowpoint for edges and interpret it as a distance to the DFS-root.
     */
    private int[] lowpt;

    /**
     * Height of next-to-lowest return point of all edges (i.e. the height of the lowest node
     * reachable by an arbitrary sequence of tree edges followed by one final back edge). Note, that
     * this definition differs from the usual definition of the second lowpoint, since here, we
     * define the lowpoint for edges and interpret it as a distance to the DFS-root.
     */
    private int[] lowpt2;

    /**
     * Representative for nesting order of each edge defined by 2 times its lowpoint (+ 1, if the
     * back edge has at least another higher return point). Note, that this immediately indicates
     * the order of outgoing back edges in the adjacency list in the planar embedding (in case, the
     * graph is planar).
     */
    private int[] nestingDepth;

    /**
     * The next following edge. In {@code testingDFS()}, it defines the next lower back edge (i.e.
     * with lower or equal lowpoint) in a conflict pair. In {@code embeddingDFS()}, it refers to the
     * next edge in the adjacency list of its adjacent node. Note, that the chain of all references
     * of an edge defines a singly linked list, whose last element is the edge, whose reference is
     * {@code null}.
     */
    private PEdge[] ref;

    /**
     * If one edge's reference {@code ref} is not {@code null}, it indicates, whether these two
     * edges are assigned to the same side of the spanning tree. If {@code side} = {@code 1}, then
     * both edges are assigned to the same side, and, if {@code side} = {@code -1}, the edges belong
     * to different sides. If the reference is {@code null}, it directly indicate the edges side in
     * the planar embedding (if the graph is planar). If {@code side} = {@code 1}, the edge will be
     * assigned to the left side, and, if {@code side} = {@code -1}, the edge is assigned to the
     * right side of the spanning tree. Note, that in this case, the side is only defined for back
     * edges. For all tree edges, this value is always {@code 1}.
     */
    private int[] side;

    /**
     * Stack of conflicting pairs formed by return edges. Each conflict pair consists of a left and
     * right interval representing the edges assigned to different side of the spanning tree. Each
     * interval is defined by a lowest and highest return edge. All edges, that are contained in an
     * interval can be obtained by incrementally invoking the reference {@code ref} of the previous
     * edge beginning at its highest return edge (second element of the interval) until the lowest
     * edge (first element of the interval) has been reached. The reference of the lowest edge will
     * be {@code null}. If the interval only contains one edge, both the highest and lowest edge
     * points at this edge. The following invariant applies: Return edges forming an interval are
     * represented in singly-linked lists ordered from highest to lowest return point. The lowest
     * return point of a conflict pair is not lower than the highest return point in any conflict
     * pair deeper in the stack.
     */
    private Stack<Pair<Pair<PEdge, PEdge>, Pair<PEdge, PEdge>>> conflicts;

    /**
     * Lowest conflict pair regarding each edge in the conflicts stack or {@code null}, if all stack
     * elements are related to the edge. Note, that this has to be an arrayList, since a simple
     * array of this generic type cannot be instantiated.
     */
    private ArrayList<Pair<Pair<PEdge, PEdge>, Pair<PEdge, PEdge>>> stackBottom;

    /**
     * Back edge returning to the lowpoint of each edge (i.e. the final edge in its lowest return
     * path or the edge itself, if it's a back edge).
     */
    private PEdge[] lowptEdge;

    /**
     * Rightmost adjacent edge of each node, that belongs to the lately traversed outgoing tree
     * edge. In the embedding phase, it indicates the position, where incoming back edges assigned
     * to the left side of an outgoing tree edge have to be inserted.
     */
    private PEdge[] leftRef;

    /**
     * Tree edge leading into next DFS-subtree (i.e. outgoing tree edge of every node). It indicates
     * the position, where incoming back edge assigned to the right side of the outgoing tree edge
     * have to be inserted.
     */
    private PEdge[] rightRef;

    /**
     * Leftmost outgoing tree edge respectively incoming back edge in the adjacency list of every
     * node. It indicates the initial edge in the order of all outgoing tree edges and incoming back
     * edges adjacent to a node, determined by {@code embeddingDFS()}.
     */
    private PEdge[] initialRef;

    // ====================== Constructor =====================================

    /**
     * Default Constructor for {@link LRPlanarSubgraphBuilder}. It creates a new instance of this class.
     */
    public LRPlanarSubgraphBuilder() {
        super();
        roots = new LinkedList<PNode>();
        crossingEdges = new LinkedList<PEdge>();
    }

    // ====================== Methods =====================================

    /**
     * Helper method for the planarity test. It instantiates all necessary attributes of this class
     * respectively for the planarity test and planar embedding and initializes them with their
     * default values. If the attributes already exist (i.e. they were created by a previous
     * function call) and if their size fits for the nodes and edges of the current graph, then the
     * old instances will be reused as far as possible to save memory space.
     * 
     * @param numNodes
     *            the number of nodes in the graph
     * @param numEdges
     *            the number of edges in the graph
     * @param mode
     *            the mode of attribute instantiation. If {@code mode = false}, then only the
     *            attributes required for the {@code testPlanarity()} will be created. Otherwise,
     *            all attributes necessary for {@code planarSubgraph()} will be instantiated.
     */
    private void initialize(final int numNodes, final int numEdges, final boolean mode) {

        roots.clear();
        crossingEdges.clear();

        if (dfsSource == null || numEdges > dfsSource.length) {
            dfsSource = new PNode[numEdges];
            dfsTarget = new PNode[numEdges];
            lowpt = new int[numEdges];
            lowpt2 = new int[numEdges];
            nestingDepth = new int[numEdges];
            ref = new PEdge[numEdges];
            side = new int[numEdges];
            Arrays.fill(side, 1);
            lowptEdge = new PEdge[numEdges];
        } else {
            Arrays.fill(dfsSource, null);
            Arrays.fill(side, 1);
            Arrays.fill(ref, null);
        }
        if (height == null || numNodes > height.length) {
            height = new int[numNodes];
            Arrays.fill(height, -1);
            parentEdge = new PEdge[numNodes];
        } else {
            Arrays.fill(height, -1);
            Arrays.fill(parentEdge, null);
        }
        if (conflicts == null) {
            conflicts = new Stack<Pair<Pair<PEdge, PEdge>, Pair<PEdge, PEdge>>>();
        } else {
            conflicts.clear();
        }
        if (stackBottom == null || numEdges > stackBottom.size()) {
            stackBottom = new ArrayList<Pair<Pair<PEdge, PEdge>, Pair<PEdge, PEdge>>>(numEdges);
            for (int i = 0; i < numEdges; i++) {
                stackBottom.add(null);
            }
        } else {
            Collections.fill(stackBottom, null);
        }
        if (mode) {
            if (initialRef == null || numNodes > initialRef.length) {
                initialRef = new PEdge[numNodes];
                leftRef = new PEdge[numNodes];
                rightRef = new PEdge[numNodes];
            } else {
                Arrays.fill(initialRef, null);
                Arrays.fill(leftRef, null);
                Arrays.fill(rightRef, null);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public IntermediateProcessingStrategy getIntermediateProcessingStrategy(final PGraph graph) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public void process(final PGraph pgraph) {

        getMonitor().begin("Planar embedding", 1);
        if (pgraph == null) {
            throw new NullPointerException("Input graph is null.");
        }

        pgraph.reindex();
        initialize(pgraph.getNodeCount(), pgraph.getEdgeCount(), true);

        // determine planar subgraph: remove all crossing edges
        LinkedList<PEdge> deletedCrossing = new LinkedList<PEdge>();
        do {
            isPlanar = true;

            // - orientation phase -
            for (PNode node : pgraph.getNodes()) {
                if (height[node.id] == -1) {
                    height[node.id] = 0;
                    roots.add(node);
                    orientationDFS(node);
                }
            }

            // - testing phase -
            // sort adjacency lists according to non-decreasing nesting depth
            for (PNode node : pgraph.getNodes()) {
                sortAdjacencyList(node);
            }
            // test planarity
            for (PNode root : roots) {
                testingDFS(root, true);
            }

            // delete crossing edges
            deletedCrossing.addAll(crossingEdges);
            for (PEdge edge : crossingEdges) {
                pgraph.removeEdge(edge);
            }
            crossingEdges.clear();

            // initialize attributes for next iteration
            if (!isPlanar) {
                roots.clear();
                Arrays.fill(dfsSource, null);
                Arrays.fill(height, -1);
                Arrays.fill(parentEdge, null);
                Arrays.fill(side, 1);
                Arrays.fill(ref, null);
                conflicts.clear();
            }
        } while (!isPlanar);

        // - embedding phase -
        // determine sign of each edge and update nesting depth accordingly
        for (PEdge edge : pgraph.getEdges()) {
            nestingDepth[edge.id] *= sign(edge);
        }
        // sort adjacency lists according to non-decreasing nesting depth
        for (PNode node : pgraph.getNodes()) {
            sortAdjacencyList(node);
        }
        // determine order for outgoing edges, incoming edges are already ordered in the desired way
        for (PNode root : roots) {
            embeddingDFS(root);
        }
        // merge order of adjacent edges
        for (PNode node : pgraph.getNodes()) {
            mergeEmbedding(node);
        }
        // convert crossingEdges to match return type
        LinkedList<PEdge> removedEdges = new LinkedList<PEdge>();
        for (PEdge edge : deletedCrossing) {
            removedEdges.add(edge);
        }

        getMonitor().done();
        pgraph.setProperty(Properties.INSERTABLE_EDGES, removedEdges);
    }

    /**
     * Tests the input graph for planarity based on the Left-Right-Planarity criterion in linear
     * time. If the graph is planar, this method will return {@code true} and {@code false}
     * otherwise. Note, that the algorithm re-indexes the input graph as a side effect before
     * testing (i.e. all indices of the input graph's components will be set back to unique values
     * between {@code 0} and the individual component count).
     * 
     * 
     * @param pgraph
     *            the graph to check for planarity.
     * @return {@code true}, if the graph is planar, {@code false} otherwise
     * 
     * @see de.cau.cs.rtprak.planarization.OrthogonalLayoutProvider OrthogonalLayoutProvider
     * @see de.cau.cs.kieler.klay.planar.p1planar.alg.planarity.rtprak.planarization.IPlanarityTester
     *      IPlanarityTester
     * @see de.cau.cs.rtprak.planarization.graph.IGraph IGraph
     * @see de.cau.cs.rtprak.planarization.graph.PNode PNode
     * @see de.cau.cs.rtprak.planarization.graph.PEdge PEdge
     * 
     * 
     */
    public boolean testPlanarity(final PGraph pgraph) {

        getMonitor().begin("Test planarity", 1);
        if (pgraph == null) {
            throw new NullPointerException("Input graph is null.");
        }

        pgraph.reindex();
        initialize(pgraph.getNodeCount(), pgraph.getEdgeCount(), false);
        isPlanar = true;

        // - orientation phase -
        for (PNode node : pgraph.getNodes()) {
            if (height[node.id] == -1) {
                height[node.id] = 0;
                roots.add(node);
                orientationDFS(node);
            }
        }

        // - testing phase -
        // sort adjacency lists according to non-decreasing nesting depth
        for (PNode node : pgraph.getNodes()) {
            sortAdjacencyList(node);
        }
        // test planarity
        for (PNode root : roots) {
            testingDFS(root, false);
            if (!isPlanar) {
                getMonitor().done();
                return false;
            }
        }

        getMonitor().done();
        return true;
    }

    /**
     * Reduces the input graph to a planar subgraph and embeds it without any edge crossings. If the
     * graph is fully planar, the algorithm executes a complete planar embedding of the entire
     * graph. If the graph is not planar, it reduces it to planar subgraph by removing all edges
     * from the input graph, that turned out to cause edge crossings. A planar embedding of the
     * subgraph will be performed. All removed edges will be added to {@code crossingEdges}. The
     * algorithm is based on the Left-Right-Planarity criterion developed by H. de Fraysseix, P.
     * Rosenstiehl and P.O.Mendes and guarantees a linear execution time to the size of the input
     * graph. The computed planar embedding is defined by the order of edges in the adjacency list
     * of every node in clockwise order.
     * 
     * @param iGraph
     *            the graph to reduce to a planar subgraph and determine its planar embedding
     * @return a list of edges of the input graph, that are not part of the determined subgraph and
     *         have been removed therefore (empty, if fully planar).
     * 
     * @see de.cau.cs.rtprak.planarization.OrthogonalLayoutProvider OrthogonalLayoutProvider
     * @see de.cau.cs.kieler.klay.planar.p1planar.alg.planarity.rtprak.planarization.IPlanarityTester
     *      IPlanarityTester
     * @see de.cau.cs.rtprak.planarization.graph.IGraph IGraph
     * @see de.cau.cs.rtprak.planarization.graph.PNode PNode
     * @see de.cau.cs.rtprak.planarization.graph.PEdge PEdge
     * @see de.cau.cs.kieler.core.util.Pair Pair
     */
    public List<PEdge> planarSubgraph(final PGraph iGraph) {

        getMonitor().begin("Planar embedding", 1);
        if (iGraph == null) {
            throw new NullPointerException("Input graph is null.");
        }

        iGraph.reindex();
        initialize(iGraph.getNodeCount(), iGraph.getEdgeCount(), true);

        // determine planar subgraph: remove all crossing edges
        LinkedList<PEdge> deletedCrossing = new LinkedList<PEdge>();
        do {
            isPlanar = true;

            // - orientation phase -
            for (PNode node : iGraph.getNodes()) {
                if (height[node.id] == -1) {
                    height[node.id] = 0;
                    roots.add(node);
                    orientationDFS(node);
                }
            }

            // - testing phase -
            // sort adjacency lists according to non-decreasing nesting depth
            for (PNode node : iGraph.getNodes()) {
                sortAdjacencyList(node);
            }
            // test planarity
            for (PNode root : roots) {
                testingDFS(root, true);
            }

            // delete crossing edges
            deletedCrossing.addAll(crossingEdges);
            for (PEdge edge : crossingEdges) {
                iGraph.removeEdge(edge);
            }
            crossingEdges.clear();

            // initialize attributes for next iteration
            if (!isPlanar) {
                roots.clear();
                Arrays.fill(dfsSource, null);
                Arrays.fill(height, -1);
                Arrays.fill(parentEdge, null);
                Arrays.fill(side, 1);
                Arrays.fill(ref, null);
                conflicts.clear();
            }
        } while (!isPlanar);

        // - embedding phase -
        // determine sign of each edge and update nesting depth accordingly
        for (PEdge edge : iGraph.getEdges()) {
            nestingDepth[edge.id] *= sign(edge);
        }
        // sort adjacency lists according to non-decreasing nesting depth
        for (PNode node : iGraph.getNodes()) {
            sortAdjacencyList(node);
        }
        // determine order for outgoing edges, incoming edges are already ordered in the desired way
        for (PNode root : roots) {
            embeddingDFS(root);
        }
        // merge order of adjacent edges
        for (PNode node : iGraph.getNodes()) {
            mergeEmbedding(node);
        }
        // convert crossingEdges to match return type
        LinkedList<PEdge> removedEdges = new LinkedList<PEdge>();
        for (PEdge edge : deletedCrossing) {
            removedEdges.add(edge);
        }

        getMonitor().done();
        return removedEdges;
    }

    /**
     * This method determines all fundamental characteristics of all nodes and edges in this graph
     * necessary for the planarity test. It traverses the input graph by a modified
     * depth-first-search orienting all edges according to DFS-traversal order, differing between
     * tree edges (such edges, that belong to the spanning tree) and back edges (edges, that return
     * to a lower node in the DFS-tree). Furthermore, it determines lowpoints, respectively
     * next-to-lowest lowpoints (indicate the lowest / next-to-lowest return point of each node) and
     * nesting depth (define, which edges in the adjacency list are the outermost in a planar
     * drawing (if the graph is planar) and therefore have to be traversed first in
     * {@code testingDFS()}).
     * 
     * @param v
     *            the root of the current DFS-subtree
     */
    private void orientationDFS(final PNode v) {

        PEdge uv = parentEdge[v.id];
        for (PEdge vw : v.adjacentEdges()) {
            if (dfsSource[vw.id] != null || vw.getSource().equals(vw.getTarget())) {
                // vw has already been visited or is self-loop
                continue;
            }
            // orient vw in DFS
            PNode w = v.getAdjacentNode(vw);
            dfsSource[vw.id] = v;
            dfsTarget[vw.id] = w;
            lowpt[vw.id] = height[v.id];
            lowpt2[vw.id] = height[v.id];
            if (height[w.id] == -1) {
                // vw is tree edge
                parentEdge[w.id] = vw;
                height[w.id] = height[v.id] + 1;
                orientationDFS(w);
            } else {
                // vw is back edge
                lowpt[vw.id] = height[w.id];
            }
            // determine nesting depth
            nestingDepth[vw.id] = lowpt[vw.id] << 1;
            if (lowpt2[vw.id] < height[v.id]) {
                // vw has at least a second (higher) return path
                nestingDepth[vw.id]++;
            }
            // update lowpoints of parent edge uv
            if (uv != null) {
                if (lowpt[vw.id] < lowpt[uv.id]) {
                    lowpt2[uv.id] = Math.min(lowpt[uv.id], lowpt2[vw.id]);
                    lowpt[uv.id] = lowpt[vw.id];
                } else if (lowpt[vw.id] > lowpt[uv.id]) {
                    lowpt2[uv.id] = Math.min(lowpt2[uv.id], lowpt[vw.id]);
                } else {
                    lowpt2[uv.id] = Math.min(lowpt2[uv.id], lowpt2[vw.id]);
                }
            }
        }
    }

    /**
     * This method directly determines whether the given graph is planar according to the
     * left-right-criterion, respectively determines a planar subgraph, if not. If {@code mode =
     * true}, this method will identify all edges, that cause edge crossings (and therefore
     * determines a planar subgraph). If {@code false}, it will only run until one crossing edge is
     * identified (planarity testing). Independently of the chosen mode, all edges that turned out
     * to violate the left-right-criterion during method execution will be added to
     * {@code crossingEdges} and {@code isPlanar} is set to {@code false}.
     * 
     * This method traverses the graph by a modified depth-first trying to merge all constraints
     * (indicate, whether two or more edges have to be drawn on the same or different sides of the
     * spanning tree) related to each edge according to the left-right-criterion into one
     * bipartition. While traversal, a new conflict pair is being pushed on the conflicts stack for
     * each back edge. This pair only contains this back edge on the right side. Its left side will
     * remain empty so far. Since all outgoing back edges of the same tree edge are affected by the
     * same constraints (i.e. have to be assigned to the same side), all conflict pairs related to
     * this tree edge are being merged into the right side, since they are constrained by the
     * lowpoint edge of the current traversed tree edge. If this fails (i.e. at least two return
     * edges are forced to be on different sides due to different constraints higher in the
     * DFS-subtree), the left-right-criterion is violated and the graph is not planar. On the other
     * hand, all previous return edges have to be merged on the left side, since they are
     * constrained by the return edges of previously traversed tree edges. If this fails, the
     * criterion is violated and the graph is not planar as well. Note, that when backtracking over
     * a tree edge, all back edges returning to its DFS-target are being removed from the top
     * conflict pairs on the stack. They are not part of any constraints deeper in the stack and are
     * not required any more. During traversal, the following invariants applies: The additional
     * conflict pairs accumulated at the top of the stack between traversing a tree edge and
     * backtracking over it it represents a partial bipartition satisfying all non-crossing
     * constraints associated with that edge. If the graph is planar, the determined bipartition is
     * implicitly given by the reference {@code ref} and {@code side} array.
     * 
     * REMARK: For an unknown reason, it sometimes takes more than one iteration to identify all
     * crossing edges of the input graph. Almost independently of its size, one to three (very
     * rarely more) iterations are required. Paradoxically, the number of undetected crossing edges
     * in the first iteration also appears to be independent of the size of the graph. Regularly,
     * one or two edges remain undetected. One explanation might be that after a crossing lowpoint
     * edge had been deleted from a conflict pair (because it turned out to be a crossing edge) also
     * the lowpoint and especially the nesting depth of all related edges lower in the tree has
     * changed. Since the nesting depth defines the traversal order and is relevant to determine all
     * constraints associated with their related tree edge, not all constraints and therefore edge
     * crossings might be determined. However, the traversal order cannot be changed during testing
     * the graph, but it seems kind of contradicting anyways, that the amount of undetected crossing
     * edges almost does not scale when increasing the size of the input graph.
     * 
     * @param v
     *            the root of the the current DFS-subtree.
     * @param mode
     *            if {@code true}, this method will identify all edges, that cause edge crossings
     *            (designed for planar subgraph determination). If {@code false}, it will only run
     *            until one crossing edge is identified (designed for simple planarity testing)
     */
    private void testingDFS(final PNode v, final boolean mode) {

        PEdge uv = parentEdge[v.id];
        PEdge vw1 = null;
        for (PEdge vw : v.adjacentEdges()) {
            // adjacent edges of v are already ordered by nesting depth
            if (vw.getSource().equals(vw.getTarget()) || dfsTarget[vw.id].equals(v)) {
                // vw is incoming edge or self-loop
                continue;
            }
            if (vw1 == null) {
                // vw1 = most outer back edge (i.e. with lowest nesting depth)
                vw1 = vw;
            }
            if (!conflicts.isEmpty()) {
                // define stack interval related to vw:
                // stackBottom[vw] is first conflict pair not belonging to vw
                stackBottom.set(vw.id, conflicts.peek());
            }
            if (vw.equals(parentEdge[dfsTarget[vw.id].id])) {
                // vw is tree edge
                testingDFS(dfsTarget[vw.id], mode);
            } else {
                // vw is back edge
                lowptEdge[vw.id] = vw;
                // push it as a new conflict pair on stack
                conflicts.push(new Pair<Pair<PEdge, PEdge>, Pair<PEdge, PEdge>>(
                        new Pair<PEdge, PEdge>(null, null), new Pair<PEdge, PEdge>(vw, vw)));
            }
            if (!(isPlanar || mode)) {
                // graph already turned out to be non-planar
                return;
            }
            // integrate new return edges
            if (lowpt[vw.id] < height[v.id]) {
                // vw has return edge
                if (vw.equals(vw1)) {
                    lowptEdge[uv.id] = lowptEdge[vw1.id];
                } else {
                    // vw1 has no constraints so far, add constraints of every other edge
                    Pair<Pair<PEdge, PEdge>, Pair<PEdge, PEdge>> p = newConflictPair();
                    // merge all edges into right side, since vw1 constrains them
                    do {
                        Pair<Pair<PEdge, PEdge>, Pair<PEdge, PEdge>> q = conflicts.pop();
                        if (q.getFirst().getFirst() != null && q.getFirst().getSecond() != null) {
                            swap(q);
                        }
                        if (q.getFirst().getFirst() != null && q.getFirst().getSecond() != null) {
                            // not planar: remove crossing edges
                            crossingEdges.addAll(removeConflicts(q, null));
                            isPlanar = false;
                            if (!mode) {
                                return;
                            }
                        }
                        if (lowpt[q.getSecond().getFirst().id] > lowpt[uv.id]) {
                            // merge intervals
                            if (p.getSecond().getFirst() == null
                                    && p.getSecond().getSecond() == null) {
                                // topmost interval
                                p.getSecond().setSecond(q.getSecond().getSecond());
                            } else {
                                ref[p.getSecond().getFirst().id] = q.getSecond().getSecond();
                            }
                            p.getSecond().setFirst(q.getSecond().getFirst());
                        } else {
                            // align
                            ref[q.getSecond().getFirst().id] = lowptEdge[uv.id];
                        }

                    } while (!conflicts.isEmpty()
                            && !conflicts.peek().equals(stackBottom.get(vw.id)));
                    // merge all conflicting previous return edges into left interval, since all
                    // previous vws constrain them
                    while (!conflicts.isEmpty()
                            && (conflicting(conflicts.peek().getFirst(), vw) || conflicting(
                                    conflicts.peek().getSecond(), vw))) {
                        Pair<Pair<PEdge, PEdge>, Pair<PEdge, PEdge>> q = conflicts.pop();
                        if (conflicting(q.getSecond(), vw)) {
                            swap(q);
                        }
                        if (conflicting(q.getSecond(), vw)) {
                            // not planar: remove crossing edges
                            crossingEdges.addAll(removeConflicts(q, vw));
                            isPlanar = false;
                            if (!mode) {
                                return;
                            }
                        }
                        // merge intervals
                        if (p.getSecond().getFirst() != null) {
                            ref[p.getSecond().getFirst().id] = q.getSecond().getSecond();
                            if (q.getSecond().getFirst() != null) {
                                p.getSecond().setFirst(q.getSecond().getFirst());

                            }
                        }
                        if (p.getFirst().getFirst() == null && p.getFirst().getSecond() == null) {
                            // topmost interval
                            p.getFirst().setSecond(q.getFirst().getSecond());
                        } else {
                            ref[p.getFirst().getFirst().id] = q.getFirst().getSecond();
                        }
                        p.getFirst().setFirst(q.getFirst().getFirst());
                    }
                    if ((p.getFirst().getFirst() != null && p.getFirst().getSecond() != null)
                            || (p.getSecond().getFirst() != null && p.getSecond().getSecond() != null)) {
                        // conflict pair is not empty
                        conflicts.push(p);
                    }
                }
            }
        }
        // remove back edges ending at parent u
        // They are not part of any constraints deeper in the stack.
        if (uv != null) {
            // v is not the root
            PNode u = dfsSource[uv.id];
            // drop entire conflict pairs only containing edges that return to parent u
            while (!conflicts.isEmpty() && lowest(conflicts.peek()) == height[u.id]) {
                Pair<Pair<PEdge, PEdge>, Pair<PEdge, PEdge>> p = conflicts.pop();
                if (p.getFirst().getFirst() != null) {
                    side[p.getFirst().getFirst().id] = -1;
                }
            }
            if (!conflicts.isEmpty()) {
                // final conflict pair to consider: only some edge return to parent u
                Pair<Pair<PEdge, PEdge>, Pair<PEdge, PEdge>> p = conflicts.pop();
                // remove those edges from left interval
                while (p.getFirst().getSecond() != null
                        && dfsTarget[p.getFirst().getSecond().id].equals(u)) {
                    p.getFirst().setSecond(ref[p.getFirst().getSecond().id]);
                }
                if (p.getFirst().getSecond() == null && p.getFirst().getFirst() != null) {
                    // left interval has just been emptied
                    // append edge to opposite interval (necessary to determine absolute side later)
                    ref[p.getFirst().getFirst().id] = p.getSecond().getFirst();
                    // indicate, that the edge belongs to a different side
                    side[p.getFirst().getFirst().id] = -1;
                    p.getFirst().setFirst(null);
                }
                // remove those edges from right interval
                while (p.getSecond().getSecond() != null
                        && dfsTarget[p.getSecond().getSecond().id].equals(u)) {
                    p.getSecond().setSecond(ref[p.getSecond().getSecond().id]);
                }
                if (p.getSecond().getSecond() == null && p.getSecond().getFirst() != null) {
                    // right interval has just been emptied
                    // append edge to opposite interval (necessary to determine absolute side later)
                    ref[p.getSecond().getFirst().id] = p.getFirst().getFirst();
                    // indicate, that the edge belongs to a different side
                    side[p.getSecond().getFirst().id] = -1;
                    p.getSecond().setFirst(null);
                }
                conflicts.push(p);
            }
            // set reference to the highest return edge: vw belongs to the same side as its parent
            if (lowpt[uv.id] < height[u.id]) {
                // u has return edge
                PEdge highLeft = null;
                PEdge highRight = null;
                if (!conflicts.isEmpty()) {
                    highLeft = conflicts.peek().getFirst().getSecond();
                    highRight = conflicts.peek().getSecond().getSecond();
                }
                // set reference only to determine the edges absolute side later (has no effect on
                // conflict pair itself)
                if (highLeft != null
                        && (highRight == null || lowpt[highLeft.id] > lowpt[highRight.id])) {
                    ref[uv.id] = highLeft;
                } else {
                    ref[uv.id] = highRight;
                }
            }
        }
    }

    /**
     * This method determines the order of all outgoing tree edges along with their surrounding
     * incoming back edges. The algorithm traverses the graph by a modified depth-first-search
     * placing all back edges around the outgoing tree edge leading into the subtree they return
     * from. The first and therefore leftmost edge of their thereby defined order is indicated by
     * this node's initial reference {@code initialRef}. All following edges can be obtained by
     * incrementally invoking the reference {@code ref} of the previous edge. The last (and
     * therefore rightmost) edge is that one, whose reference is {@code null}. A singly linked list
     * of all these edges is defined thereby. Note, that this order does not contain any incoming
     * edges of self-loops. They are ignored during traversal. Unfortunately, no common Java data
     * structure supporting a sorting of its contained elements allows element insertion at
     * arbitrary position in constant time. Therefore, this special handling of incoming back edges
     * is necessary to guarantee a linear time embedding phase.
     * 
     * @param v
     *            the root of the current DFS-subtree
     */
    private void embeddingDFS(final PNode v) {

        for (PEdge vw : v.adjacentEdges()) {
            if (vw.getSource().equals(vw.getTarget()) || !dfsSource[vw.id].equals(v)) {
                // vw is incoming edge or self-loop
                continue;
            }
            PNode w = dfsTarget[vw.id];
            if (vw.equals(parentEdge[w.id])) {
                // vw is tree edge
                if (initialRef[v.id] == null) {
                    initialRef[v.id] = vw;
                }
                if (rightRef[v.id] != null) {
                    // determine rightmost adjacent edge of previously traversed subtree
                    PEdge rightmost = rightRef[v.id];
                    while (ref[rightmost.id] != null) {
                        rightmost = ref[rightmost.id];
                    }
                    leftRef[v.id] = rightmost;
                    ref[rightmost.id] = vw;
                }
                rightRef[v.id] = vw;
                embeddingDFS(w);
            } else {
                // vw is back edge
                if (side[vw.id] == 1) {
                    // place vw directly after rightRef[w]
                    ref[vw.id] = ref[rightRef[w.id].id];
                    ref[rightRef[w.id].id] = vw;
                } else {
                    // place vw directly after leftRef[w]
                    if (leftRef[w.id] == null) {
                        // vw belongs to leftmost outgoing tree edge
                        ref[vw.id] = initialRef[w.id];
                        initialRef[w.id] = vw;
                    } else {
                        ref[vw.id] = ref[leftRef[w.id].id];
                        ref[leftRef[w.id].id] = vw;
                    }
                }
            }
        }
    }

    /**
     * Helper method for the planar embedding. This function computes the sign of the given edge,
     * element of {@code (-1, 1)}), indicating the edge's position in the planar embedding. If the
     * sign is {@code -1}, the edge will be drawn on the left side or, if the sign is {@code -1} ,
     * it will be drawn on the right side of the spanning tree in the planar embedding. Since the
     * {@code side} of an edge is directly defined, if its reference is {@code null} or otherwise
     * indicates whether the edge is assigned to the same side as its reference edge, all edges in
     * the singly linked list of references just have to be multiplied with each other to define the
     * absolute side of every edge.
     * 
     * @param edge
     *            the edge to compute its sign
     * @return the sign of the input edge
     */
    private int sign(final PEdge edge) {
        if (ref[edge.id] != null) {
            side[edge.id] *= sign(ref[edge.id]);
            ref[edge.id] = null;
        }
        return side[edge.id];
    }

    /**
     * Helper method for the planarity test. It determines, whether the specified edge conflicts
     * with the interval of a conflict pair (i.e. it cannot be drawn on the same side of the
     * spanning tree without edge crossings). An interval conflicts with a given edge, if it is not
     * empty and at least one edge returns higher than the edge itself.
     * 
     * @param interval
     *            a pair of edges given by highest and lowest return edge, where first element of
     *            pair is the lowest return edge and second element is the highest return edge of
     *            the interval
     * @param edge
     *            an edge of this graph
     * @return {@code true}, if the interval conflicts with the interval, and {@code false}
     *         otherwise
     * 
     * @see de.cau.cs.kieler.core.util.Pair Pair
     */
    private boolean conflicting(final Pair<PEdge, PEdge> interval, final PEdge edge) {
        return ((interval.getFirst() != null || interval.getSecond() != null) && lowpt[interval
                .getSecond().id] > lowpt[edge.id]);
    }

    /**
     * Helper method for the planarity test. It returns the lowpoint of the specified conflict pair
     * (i.e. the lowpoint of the lowest return edge in the conflict pair). Note, that it is
     * excluded, that all intervals of the conflict pair are empty (i.e. all references to edges are
     * {@code null}), and therefore, this will not be checked.
     * 
     * @param pair
     *            the conflict pair to determine its lowpoint
     * @return the lowpoint of the conflict pair
     * 
     * @see de.cau.cs.kieler.core.util.Pair Pair
     */
    private int lowest(final Pair<Pair<PEdge, PEdge>, Pair<PEdge, PEdge>> pair) {

        if (pair.getFirst().getFirst() == null && pair.getFirst().getSecond() == null) {
            return lowpt[pair.getSecond().getFirst().id];
        }
        if (pair.getSecond().getFirst() == null && pair.getSecond().getSecond() == null) {
            return lowpt[pair.getFirst().getFirst().id];
        }
        return Math
                .min(lowpt[pair.getFirst().getFirst().id], lowpt[pair.getSecond().getFirst().id]);
    }

    /**
     * Helper method for the planarity test. It creates a new empty conflict pair, where all
     * references of its left and right interval are set to {@code null}.
     * 
     * @return a new empty conflict pair
     * 
     * @see de.cau.cs.kieler.core.util.Pair Pair
     */
    private Pair<Pair<PEdge, PEdge>, Pair<PEdge, PEdge>> newConflictPair() {

        Pair<PEdge, PEdge> left = new Pair<PEdge, PEdge>(null, null);
        Pair<PEdge, PEdge> right = new Pair<PEdge, PEdge>(null, null);

        return new Pair<Pair<PEdge, PEdge>, Pair<PEdge, PEdge>>(left, right);
    }

    /**
     * Helper method for the planarity test. It swaps the two elements (i.e. left and right
     * interval) of a conflict pair.
     * 
     * @param pair
     *            the conflict pair to swap its elements
     * 
     * @see de.cau.cs.kieler.core.util.Pair Pair
     */
    private void swap(final Pair<Pair<PEdge, PEdge>, Pair<PEdge, PEdge>> pair) {
        Pair<PEdge, PEdge> p = pair.getFirst();
        pair.setFirst(pair.getSecond());
        pair.setSecond(p);
    }

    /**
     * Helper method for the planarity test. If the input edge is {@code null}, it deletes all edges
     * of the shorter interval (i.e. the interval, that contains less edges) from the conflict pair.
     * If the edge is not {@code null}, it determines in which interval less edges conflict with the
     * input edge and removes them from the interval. All deleted edges are contained in the linked
     * list, which will be returned. If needed, the intervals will be swapped to match the pair's
     * desired orientation in {@code testingDFS()}.
     * 
     * @param pair
     *            the conflict pair to delete the shorter interval from or the conflict pair to
     *            delete all edges conflicting with the input edge from
     * @param edge
     *            the edge to determine all interval edges to delete or {@code null}
     * @return a LinkedList containing all deleted edges
     * 
     * @see de.cau.cs.kieler.core.util.Pair Pair
     */
    private LinkedList<PEdge> removeConflicts(
            final Pair<Pair<PEdge, PEdge>, Pair<PEdge, PEdge>> pair, final PEdge edge) {

        LinkedList<PEdge> left = new LinkedList<PEdge>();
        LinkedList<PEdge> right = new LinkedList<PEdge>();
        PEdge currentLeft = pair.getFirst().getSecond();
        PEdge currentRight = pair.getSecond().getSecond();

        if (edge != null) {
            // determine all edges on each interval, conflicting with input edge
            while (currentLeft != null && lowpt[currentLeft.id] > lowpt[edge.id]) {
                left.add(currentLeft);
                currentLeft = ref[currentLeft.id];
            }
            while (currentRight != null && lowpt[currentRight.id] > lowpt[edge.id]) {
                right.add(currentRight);
                currentRight = ref[currentRight.id];
            }
        } else {
            // determine all edges on each interval
            while (currentLeft != null) {
                left.add(currentLeft);
                currentLeft = ref[currentLeft.id];
            }
            while (currentRight != null) {
                right.add(currentRight);
                currentRight = ref[currentRight.id];
            }
        }
        // remove edges
        if (right.size() < left.size()) {
            pair.getSecond().setSecond(currentRight);
            if (currentRight == null) {
                pair.getSecond().setFirst(null);
            }
            if (edge == null) {
                // align
                swap(pair);
            }
            return right;
        } else {
            pair.getFirst().setSecond(currentLeft);
            if (currentLeft == null) {
                pair.getFirst().setFirst(null);
            }
            if (edge != null) {
                // align
                swap(pair);
            }
            return left;
        }
    }

    /**
     * Helper method for the planarity test. It sorts the adjacency list of the specified node
     * according to a non-decreasing nesting depth. Nested adjacency list will be sorted by the
     * average nesting depth.
     * 
     * @param node
     *            the node to sort its adjacency list
     */
    private void sortAdjacencyList(final PNode node) {
        node.sort(new IFunction<PEdge, Integer>() {
            public Integer evaluate(final PEdge element) {
                return nestingDepth[element.id];
            }
        });
    }

    /**
     * Helper method for the planar embedding. It merges the order of the incoming back edges,
     * including the outgoing tree edges, determined by {@code embeddingDFS()} with the order of
     * outgoing tree edges determined by {@code sortAdjacencyList()}. After termination, the first
     * element of the input node's adjacency list will be its incoming tree edge, unless the node is
     * the DFS-root. In case, self-loops are existing, they will be embedded right after the
     * incoming tree edge next to each other. Note, that this approach is necessary, only because no
     * common Java data structure supporting a sorting of its contained elements allows element
     * insertion at arbitrary position in constant time. Otherwise, all edges could be moved to its
     * determined position in linear time in only one traversal.
     * 
     * @param node
     *            the node to merge its determined edge orders
     */
    private void mergeEmbedding(final PNode node) {

        // Nothing needs to be done for small lists
        if (node.getAdjacentEdgeCount() <= 1) {
            return;
        }

        PEdge treeEdge = null;
        HashSet<PEdge> loops = new HashSet<PEdge>();
        LinkedHashSet<PEdge> rest = new LinkedHashSet<PEdge>();
        boolean insertionPoint = false;
        for (PEdge edge : node.adjacentEdges()) {
            if (edge.getSource() == edge.getTarget()) {
                // Remember self loops
                loops.add(edge);

            } else if (!(node == dfsSource[edge.id]) && (edge == parentEdge[node.id])) {
                // Remember incoming tree edge
                treeEdge = edge;

            } else if ((edge == parentEdge[dfsTarget[edge.id].id]) && !insertionPoint) {
                // Mark leftmost outgoing tree edge as insertion point
                insertionPoint = true;

            } else if (insertionPoint) {
                // Remember all edges after the insertion point
                rest.add(edge);
            }
        }

        // Move self loops to the beginning of the list
        for (PEdge loop : loops) {
            node.moveToStart(loop);
        }

        // Move incoming tree edge to beginning of the list
        if (treeEdge != null) {
            node.moveToStart(treeEdge);
        }

        // Move outgoing back edges and incoming tree edges to the end of the list
        PEdge toAdd = initialRef[node.id];
        while (toAdd != null) {
            node.moveToEnd(toAdd);
            rest.remove(toAdd);
            toAdd = ref[toAdd.id];
        }

        // Move all edges after insertion point to the end of the list
        for (PEdge edge : rest) {
            node.moveToEnd(edge);
        }
    }

}
