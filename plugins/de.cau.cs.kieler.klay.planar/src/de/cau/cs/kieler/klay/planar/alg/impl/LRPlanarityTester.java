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
package de.cau.cs.kieler.klay.planar.alg.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Stack;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.klay.planar.alg.IPlanarityTester;
import de.cau.cs.kieler.klay.planar.graph.IEdge;
import de.cau.cs.kieler.klay.planar.graph.IGraph;
import de.cau.cs.kieler.klay.planar.graph.INode;
import de.cau.cs.kieler.klay.planar.graph.InconsistentGraphModelException;
import de.cau.cs.kieler.klay.planar.graph.impl.PGraph;

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
 * @see de.cau.cs.kieler.klay.planar.alg.IPlanarityTester IPlanarityTester
 * @see de.cau.cs.kieler.klay.planar.graph.IGraph IGraph
 * @see de.cau.cs.kieler.klay.planar.graph.INode INode
 * @see de.cau.cs.kieler.klay.planar.graph.IEdge IEdge
 * 
 * @author pdo
 */
public class LRPlanarityTester extends AbstractAlgorithm implements IPlanarityTester {

    // ====================== Attributes ======================================

    /** A flag in {@code planarSubgraph()} indicating, whether the graph is planar. */
    private boolean isPlanar;

    /**
     * The edges of the input graph, whose addition will cause non-planarity (i.e. the edges of the
     * input graph, that are not part of the planar subgraph of G determined by {@code
     * planarSubgraph()}). Note, that these edges are only those, that have been identified during
     * current iteration. A list of all crossing edges is saved in {@code planarSubgraph()} itself.
     */
    private LinkedList<IEdge> crossingEdges;

    /** The DFS-roots of all connected components in the graph. */
    private LinkedList<INode> roots;

    /** Source node of every edge in the DFS-oriented tree. */
    private INode[] dfsSource;

    /** Target node of every edge in the DFS-oriented tree. */
    private INode[] dfsTarget;

    /**
     * The parent tree edge of every node in the depth-first-search or {@code null}, if the node is
     * the root of a connected component.
     */
    private IEdge[] parentEdge;

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
     * The outgoing tree edge of each node, that leads into the DFS-subtree currently being
     * backtracking in {@code orientationDFS()}. If the specified node is a leaf node, this
     * attribute will be {@code null}.
     */
    private IEdge[] relatedTree;

    /**
     * The side of the port, that is connected to the {@code dfsTarget} of each edge. If {@code
     * dfsTargetSide} = {@code -1}, then the port is placed on the left side of the spanning tree.
     * If {@code dfsTargetSide} = {@code 1}, then the port is placed on the right side of the
     * spanning tree or if {@code dfsTargetSide} = {@code 0}, then the port is connected to the
     * {@code dfsTarget} of a tree edge itself.
     */
    private int[] dfsTargetSide;

    /**
     * The currently backtracking outgoing tree edge of every node in {@code orientationDFS()} or
     * {@code null}, if the node does not have an outgoing tree edge (i.e. is a leaf) or none of its
     * outgoing tree edges has been backtracking thus far.
     */
    private IEdge[] currentTree;

    /**
     * The next following edge. In {@code testingDFS()}, it defines the next lower back edge (i.e.
     * with lower or equal lowpoint) in a conflict pair. In {@code embeddingDFS()}, it refers to the
     * next edge in the adjacency list of its adjacent node. Note, that the chain of all references
     * of an edge defines a singly linked list, whose last element is the edge, whose reference is
     * {@code null}.
     */
    private IEdge[] ref;

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
    private Stack<Pair<Pair<IEdge, IEdge>, Pair<IEdge, IEdge>>> conflicts;

    /**
     * Lowest conflict pair regarding each edge in the conflicts stack or {@code null}, if all stack
     * elements are related to the edge. Note, that this has to be an arrayList, since a simple
     * array of this generic type cannot be instantiated.
     */
    private ArrayList<Pair<Pair<IEdge, IEdge>, Pair<IEdge, IEdge>>> stackBottom;

    /**
     * Back edge returning to the lowpoint of each edge (i.e. the final edge in its lowest return
     * path or the edge itself, if it's a back edge).
     */
    private IEdge[] lowptEdge;

    /**
     * Rightmost adjacent edge of each node, that belongs to the lately backtracking outgoing tree
     * edge. In the embedding phase, it indicates the dfsIndex, where incoming back edges assigned
     * to the left side of an outgoing tree edge have to be inserted.
     */
    private IEdge[] leftRef;

    /**
     * Tree edge leading into next DFS-subtree (i.e. outgoing tree edge of every node). It indicates
     * the dfsIndex, where incoming back edge assigned to the right side of the outgoing tree edge
     * have to be inserted.
     */
    private IEdge[] rightRef;

    /**
     * Leftmost outgoing tree edge respectively incoming back edge in the adjacency list of every
     * node. It indicates the initial edge in the order of all outgoing tree edges and incoming back
     * edges adjacent to a node, determined by {@code embeddingDFS()}.
     */
    private IEdge[] initialRef;

    /**
     * The dfsIndex of each incoming back edge resp. outgoing tree edge in the adjacency list of
     * their adjacent node.
     */
    private int[] leftLeftPos;

    private int[] rightLeftPos;

    private int[] leftRightPos;

    private int[] rightRightPos;

    private int[] dfsIndex;

    private int[] edgeClass;

    private boolean[] backtracking;

    /**
     * A list of all leftmost edges in the planar embedding belonging to an outgoing subtree.
     */
    private ArrayList<LinkedList<IEdge>> outgoingTrees;

    // ====================== Constructor =====================================

    /**
     * Default Constructor for {@link LRPlanarityTester}. It creates a new instance of this class.
     */
    public LRPlanarityTester() {
        super();
        roots = new LinkedList<INode>();
        crossingEdges = new LinkedList<IEdge>();
    }

    // TODO data structure needs nodeType "PORTS"
    // TODO maybe data structure needs graphType "PORTS"

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
     * 
     * @throws IllegalArgumentException
     *             if either numNodes or numEdges is less than zero.
     */
    private void initialize(final int numNodes, final int numEdges, final boolean mode) {

        // TODO improve: initialize all attributes related to port constrains separately, if needed
        roots.clear();
        crossingEdges.clear();

        if (numEdges < 0 || numNodes < 0) {
            throw new IllegalArgumentException(
                    "Either input argument numNodes or numEdges is less than zero.");
        }

        if (dfsSource == null || numEdges > dfsSource.length) {
            dfsSource = new INode[numEdges];
            dfsTarget = new INode[numEdges];
            relatedTree = new IEdge[numEdges];
            dfsTargetSide = new int[numEdges];
            Arrays.fill(dfsTargetSide, 0);
            lowpt = new int[numEdges];
            lowpt2 = new int[numEdges];
            nestingDepth = new int[numEdges];
            ref = new IEdge[numEdges];
            side = new int[numEdges];
            Arrays.fill(side, 1);
            lowptEdge = new IEdge[numEdges];
            dfsIndex = new int[numEdges];
        } else {
            Arrays.fill(dfsSource, null);
            Arrays.fill(relatedTree, null);
            Arrays.fill(dfsTargetSide, 0);
            Arrays.fill(side, 1);
            Arrays.fill(ref, null);
            Arrays.fill(dfsIndex, -1);
        }
        if (height == null || numNodes > height.length) {
            height = new int[numNodes];
            Arrays.fill(height, -1);
            currentTree = new IEdge[numNodes];
            parentEdge = new IEdge[numNodes];
            backtracking = new boolean[numNodes];
        } else {
            Arrays.fill(height, -1);
            Arrays.fill(currentTree, null);
            Arrays.fill(parentEdge, null);
            Arrays.fill(backtracking, false);
        }
        if (conflicts == null) {
            conflicts = new Stack<Pair<Pair<IEdge, IEdge>, Pair<IEdge, IEdge>>>();
        } else {
            conflicts.clear();
        }
        if (stackBottom == null || numEdges > stackBottom.size()) {
            stackBottom = new ArrayList<Pair<Pair<IEdge, IEdge>, Pair<IEdge, IEdge>>>(numEdges);
            for (int i = 0; i < numEdges; i++) {
                stackBottom.add(null);
            }
        } else {
            Collections.fill(stackBottom, null);
        }
        if (outgoingTrees == null || numNodes > outgoingTrees.size()) {
            outgoingTrees = new ArrayList<LinkedList<IEdge>>(numNodes);
            for (int i = 0; i < numEdges; i++) {
                outgoingTrees.add(new LinkedList<IEdge>());
            }
        } else {
            for (LinkedList<IEdge> list : outgoingTrees) {
                list.clear();
            }
        }
        if (mode) {
            if (initialRef == null || numNodes > initialRef.length) {
                initialRef = new IEdge[numNodes];
                leftRef = new IEdge[numNodes];
                rightRef = new IEdge[numNodes];
            } else {
                Arrays.fill(initialRef, null);
                Arrays.fill(leftRef, null);
                Arrays.fill(rightRef, null);
            }
        }
    }

    /**
     * Tests the input graph for planarity based on the Left-Right-Planarity criterion in linear
     * time. If the graph is planar, this method will return {@code true} and {@code false}
     * otherwise. Note, that the algorithm re-indexes the input graph as a side effect before
     * testing (i.e. all indices of the input graph's components will be set back to unique values
     * between {@code 0} and the individual component count).
     * 
     * @param iGraph
     *            the graph to check for planarity.
     * @return {@code true}, if the graph is planar, {@code false} otherwise
     * @throws InconsistentGraphModelException
     *             if the input graph is not consistent
     * 
     * @see de.cau.cs.kieler.klay.planar.OrthogonalLayoutProvider OrthogonalLayoutProvider
     * @see de.cau.cs.kieler.klay.planar.alg.IPlanarityTester IPlanarityTester
     * @see de.cau.cs.kieler.klay.planar.graph.IGraph IGraph
     * @see de.cau.cs.kieler.klay.planar.graph.INode INode
     * @see de.cau.cs.kieler.klay.planar.graph.IEdge IEdge
     */
    public boolean testPlanarity(final IGraph iGraph) throws InconsistentGraphModelException {

        getMonitor().begin("Test planarity", 1);
        if (iGraph == null) {
            throw new InconsistentGraphModelException("Input graph is null.");
        }

        iGraph.reindex();
        initialize(iGraph.getNodeCount(), iGraph.getEdgeCount(), false);
        isPlanar = true;

        // - orientation phase -
        for (INode node : iGraph.getNodes()) {
            if (height[node.getID()] == -1) {
                height[node.getID()] = 0;
                roots.add(node);
                orientationDFS(node);
            }
        }

        // - testing phase -
        // sort adjacency lists according to non-decreasing nesting depth
        for (INode node : iGraph.getNodes()) {
            sortAdjacencyList(node);
        }
        // test planarity
        for (INode root : roots) {
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
     * @throws InconsistentGraphModelException
     *             if {@code iGraph} is {@code null} or not consistent
     * 
     * @see de.cau.cs.kieler.klay.planar.OrthogonalLayoutProvider OrthogonalLayoutProvider
     * @see de.cau.cs.kieler.klay.planar.alg.IPlanarityTester IPlanarityTester
     * @see de.cau.cs.kieler.klay.planar.graph.IGraph IGraph
     * @see de.cau.cs.kieler.klay.planar.graph.INode INode
     * @see de.cau.cs.kieler.klay.planar.graph.IEdge IEdge
     * @see de.cau.cs.kieler.core.util.Pair Pair
     */
    public List<Pair<INode, INode>> planarSubgraph(final IGraph iGraph)
            throws InconsistentGraphModelException {

        getMonitor().begin("Planar embedding", 1);
        if (iGraph == null) {
            throw new InconsistentGraphModelException("Input graph is null.");
        }

        iGraph.reindex();
        initialize(iGraph.getNodeCount(), iGraph.getEdgeCount(), true);

        // determine planar subgraph: remove all crossing edges
        LinkedList<IEdge> deletedCrossing = new LinkedList<IEdge>();
        int iterations = -1;
        do {
            iterations++;
            isPlanar = true;

            // - orientation phase -
            for (INode node : iGraph.getNodes()) {
                if (height[node.getID()] == -1) {
                    height[node.getID()] = 0;
                    roots.add(node);
                    orientationDFS(node);
                }
            }

            // - testing phase -
            // sort adjacency lists according to non-decreasing nesting depth
            for (INode node : iGraph.getNodes()) {
                sortAdjacencyList(node);
            }
            // test planarity
            for (INode root : roots) {
                testingDFS(root, true);
            }
            // delete crossing edges
            deletedCrossing.addAll(crossingEdges);
            System.out.print("CrossingEdgeCount: " + crossingEdges.size() + "\n");
            for (IEdge edge : crossingEdges) {
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
        System.out.print("iterations: " + iterations + "\n");

        // - embedding phase -
        // determine sign of each edge and update nesting depth accordingly
        for (IEdge edge : iGraph.getEdges()) {
            nestingDepth[edge.getID()] *= sign(edge);
        }
        // sort adjacency lists according to non-decreasing nesting depth
        for (INode node : iGraph.getNodes()) {
            sortAdjacencyList(node);
        }
        // determine order for outgoing edges, incoming edges are already ordered in the desired way
        for (INode root : roots) {
            embeddingDFS(root);
        }
        // merge order of adjacent edges
        for (INode node : iGraph.getNodes()) {
            mergeEmbedding(node);
        }
        // convert crossingEdges to match return type
        LinkedList<Pair<INode, INode>> removedEdges = new LinkedList<Pair<INode, INode>>();
        for (IEdge edge : deletedCrossing) {
            removedEdges.add(new Pair<INode, INode>(edge.getSource(), edge.getTarget()));
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
     * drawing (if the graph is planar) and therefore have to be backtracking first in {@code
     * testingDFS()}).
     * 
     * @param v
     *            the root of the current DFS-subtree
     * @throws InconsistentGraphModelException
     *             if the graph is not consistent
     */
    private void orientationDFS(final INode v) throws InconsistentGraphModelException {

        // TODO this whole port stuff should only be determined, if graph has port constrained edges

        IEdge uv = parentEdge[v.getID()];
        for (IEdge vw : v.getAllEdges()) {
            if (dfsSource[vw.getID()] != null) {
                // vw has already been visited
                continue;
            }
            if (vw.getSource().equals(vw.getTarget())) {
                // vw is self-loop
                continue;
            }
            // orient vw in DFS
            INode w = v.getAdjacentNode(vw);
            dfsSource[vw.getID()] = v;
            dfsTarget[vw.getID()] = w;
            lowpt[vw.getID()] = height[v.getID()];
            lowpt2[vw.getID()] = height[v.getID()];
            if (height[w.getID()] == -1) {
                // vw is tree edge
                currentTree[v.getID()] = vw;
                parentEdge[w.getID()] = vw;
                relatedTree[vw.getID()] = vw;
                height[w.getID()] = height[v.getID()] + 1;
                orientationDFS(w);
            } else {
                // vw is back edge
                relatedTree[vw.getID()] = currentTree[w.getID()];
                lowpt[vw.getID()] = height[w.getID()];
            }
            // determine nesting depth
            nestingDepth[vw.getID()] = lowpt[vw.getID()] << 1;
            if (lowpt2[vw.getID()] < height[v.getID()]) {
                // vw has at least a second (higher) return path
                nestingDepth[vw.getID()]++;
            }
            // update lowpoints of parent edge uv
            if (uv != null) {
                if (lowpt[vw.getID()] < lowpt[uv.getID()]) {
                    lowpt2[uv.getID()] = Math.min(lowpt[uv.getID()], lowpt2[vw.getID()]);
                    lowpt[uv.getID()] = lowpt[vw.getID()];
                } else if (lowpt[vw.getID()] > lowpt[uv.getID()]) {
                    lowpt2[uv.getID()] = Math.min(lowpt2[uv.getID()], lowpt[vw.getID()]);
                } else {
                    lowpt2[uv.getID()] = Math.min(lowpt2[uv.getID()], lowpt2[vw.getID()]);
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
     * to violate the left-right-criterion during method execution will be added to {@code
     * crossingEdges} and {@code isPlanar} is set to {@code false}.
     * 
     * This method traverses the graph by a modified depth-first trying to merge all constraints
     * (indicate, whether two or more edges have to be drawn on the same or different sides of the
     * spanning tree) related to each edge according to the left-right-criterion into one
     * bipartition. While traversal, a new conflict pair is being pushed on the conflicts stack for
     * each back edge. This pair only contains this back edge on the right side. Its left side will
     * remain empty so far. Since all outgoing back edges of the same tree edge are affected by the
     * same constraints (i.e. have to be assigned to the same side), all conflict pairs related to
     * this tree edge are being merged into the right side, since they are constrained by the
     * lowpoint edge of the current backtracking tree edge. If this fails (i.e. at least two return
     * edges are forced to be on different sides due to different constraints higher in the
     * DFS-subtree), the left-right-criterion is violated and the graph is not planar. On the other
     * hand, all previous return edges have to be merged on the left side, since they are
     * constrained by the return edges of previously backtracking tree edges. If this fails, the
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
     * @throws InconsistentGraphModelException
     *             if the graph is not consistent
     */
    private void testingDFS(final INode v, final boolean mode)
            throws InconsistentGraphModelException {

        IEdge uv = parentEdge[v.getID()];
        IEdge vw1 = null;
        for (IEdge vw : v.getAllEdges()) {
            // adjacent edges of v are already ordered by nesting depth
            if (vw.getSource().equals(vw.getTarget()) || dfsTarget[vw.getID()].equals(v)) {
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
                stackBottom.set(vw.getID(), conflicts.peek());
            }
            if (vw.equals(parentEdge[dfsTarget[vw.getID()].getID()])) {
                // vw is tree edge
                testingDFS(dfsTarget[vw.getID()], mode);
            } else {
                // vw is back edge
                lowptEdge[vw.getID()] = vw;
                // push it as a new conflict pair on stack
                conflicts.push(new Pair<Pair<IEdge, IEdge>, Pair<IEdge, IEdge>>(
                        new Pair<IEdge, IEdge>(null, null), new Pair<IEdge, IEdge>(vw, vw)));
            }
            if (!(isPlanar || mode)) {
                // graph already turned out to be non-planar
                return;
            }
            // integrate new return edges
            if (lowpt[vw.getID()] < height[v.getID()]) {
                // vw has return edge
                if (vw.equals(vw1)) {
                    lowptEdge[uv.getID()] = lowptEdge[vw1.getID()];
                } else {
                    // vw1 has no constraints so far, add constraints of every other edge
                    Pair<Pair<IEdge, IEdge>, Pair<IEdge, IEdge>> p = newConflictPair();
                    // merge all edges into right side, since vw1 constrains them
                    do {
                        Pair<Pair<IEdge, IEdge>, Pair<IEdge, IEdge>> q = conflicts.pop();
                        if (q.getFirst().getFirst() != null && q.getFirst().getSecond() != null) {
                            swap(q);
                        }
                        if (q.getFirst().getFirst() != null && q.getFirst().getSecond() != null) {
                            // not planar: remove crossing edges
                            crossingEdges.addAll(removeConflicts(q, null, 0));
                            isPlanar = false;
                            if (!mode) {
                                return;
                            }
                        }
                        if (lowpt[q.getSecond().getFirst().getID()] > lowpt[uv.getID()]) {
                            // merge intervals
                            if (p.getSecond().getFirst() == null
                                    && p.getSecond().getSecond() == null) {
                                // topmost interval
                                p.getSecond().setSecond(q.getSecond().getSecond());
                            } else {
                                ref[p.getSecond().getFirst().getID()] = q.getSecond().getSecond();
                            }
                            p.getSecond().setFirst(q.getSecond().getFirst());
                        } else {
                            // align
                            ref[q.getSecond().getFirst().getID()] = lowptEdge[uv.getID()];
                        }

                    } while (!conflicts.isEmpty()
                            && !conflicts.peek().equals(stackBottom.get(vw.getID())));
                    // merge all conflicting previous return edges into left interval, since all
                    // previous vws constrain them
                    while (!conflicts.isEmpty()
                            && (conflicting(conflicts.peek().getFirst(), vw) || conflicting(
                                    conflicts.peek().getSecond(), vw))) {
                        Pair<Pair<IEdge, IEdge>, Pair<IEdge, IEdge>> q = conflicts.pop();
                        if (conflicting(q.getSecond(), vw)) {
                            swap(q);
                        }
                        if (conflicting(q.getSecond(), vw)) {
                            // not planar: remove crossing edges
                            crossingEdges.addAll(removeConflicts(q, vw, 0));
                            isPlanar = false;
                            if (!mode) {
                                return;
                            }
                        }
                        // merge intervals
                        if (p.getSecond().getFirst() != null) {
                            ref[p.getSecond().getFirst().getID()] = q.getSecond().getSecond();
                            if (q.getSecond().getFirst() != null) {
                                p.getSecond().setFirst(q.getSecond().getFirst());

                            }
                        }
                        if (p.getFirst().getFirst() == null && p.getFirst().getSecond() == null) {
                            // topmost interval
                            p.getFirst().setSecond(q.getFirst().getSecond());
                        } else {
                            ref[p.getFirst().getFirst().getID()] = q.getFirst().getSecond();
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
            INode u = dfsSource[uv.getID()];
            backtracking[u.getID()] = true;
            // drop entire conflict pairs only containing edges that return to parent u
            while (!conflicts.isEmpty() && lowest(conflicts.peek()) == height[u.getID()]) {
                Pair<Pair<IEdge, IEdge>, Pair<IEdge, IEdge>> p = conflicts.pop();
                if (p.getFirst().getFirst() != null) {
                    side[p.getFirst().getFirst().getID()] = -1;
                }
            }
            if (!conflicts.isEmpty()) {
                // final conflict pair to consider: only some edge return to parent u
                Pair<Pair<IEdge, IEdge>, Pair<IEdge, IEdge>> p = conflicts.pop();
                // remove those edges from left interval
                while (p.getFirst().getSecond() != null
                        && dfsTarget[p.getFirst().getSecond().getID()].equals(u)) {
                    p.getFirst().setSecond(ref[p.getFirst().getSecond().getID()]);
                }
                if (p.getFirst().getSecond() == null && p.getFirst().getFirst() != null) {
                    // left interval has just been emptied
                    // append edge to opposite interval (necessary to determine absolute side later)
                    ref[p.getFirst().getFirst().getID()] = p.getSecond().getFirst();
                    // indicate, that the edge belongs to a different side
                    side[p.getFirst().getFirst().getID()] = -1;
                    p.getFirst().setFirst(null);
                }
                // remove those edges from right interval
                while (p.getSecond().getSecond() != null
                        && dfsTarget[p.getSecond().getSecond().getID()].equals(u)) {
                    p.getSecond().setSecond(ref[p.getSecond().getSecond().getID()]);
                }
                if (p.getSecond().getSecond() == null && p.getSecond().getFirst() != null) {
                    // right interval has just been emptied
                    // append edge to opposite interval (necessary to determine absolute side later)
                    ref[p.getSecond().getFirst().getID()] = p.getFirst().getFirst();
                    // indicate, that the edge belongs to a different side
                    side[p.getSecond().getFirst().getID()] = -1;
                    p.getSecond().setFirst(null);
                }
                conflicts.push(p);
            }
            // set reference to the highest return edge: vw belongs to the same side as its parent
            if (lowpt[uv.getID()] < height[u.getID()]) {
                // u has return edge
                IEdge highLeft = null;
                IEdge highRight = null;
                if (!conflicts.isEmpty()) {
                    highLeft = conflicts.peek().getFirst().getSecond();
                    highRight = conflicts.peek().getSecond().getSecond();
                }
                // set reference only to determine the edges absolute side later (has no effect on
                // conflict pair itself)
                if (highLeft != null
                        && (highRight == null || lowpt[highLeft.getID()] > lowpt[highRight.getID()])) {
                    ref[uv.getID()] = highLeft;
                } else {
                    ref[uv.getID()] = highRight;
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
     * arbitrary dfsIndex in constant time. Therefore, this special handling of incoming back edges
     * is necessary to guarantee a linear time embedding phase.
     * 
     * @param v
     *            the root of the current DFS-subtree
     */
    private void embeddingDFS(final INode v) {

        for (IEdge vw : v.getAllEdges()) {
            if (vw.getSource().equals(vw.getTarget()) || !dfsSource[vw.getID()].equals(v)) {
                // vw is incoming edge or self-loop
                continue;
            }
            INode w = dfsTarget[vw.getID()];
            if (vw.equals(parentEdge[w.getID()])) {
                // vw is tree edge
                if (initialRef[v.getID()] == null) {
                    initialRef[v.getID()] = vw;
                }
                if (rightRef[v.getID()] != null) {
                    // determine rightmost adjacent edge of previously backtracking subtree
                    IEdge rightmost = rightRef[v.getID()];
                    while (ref[rightmost.getID()] != null) {
                        rightmost = ref[rightmost.getID()];
                    }
                    leftRef[v.getID()] = rightmost;
                    ref[rightmost.getID()] = vw;
                }
                rightRef[v.getID()] = vw;
                embeddingDFS(w);
            } else {
                // vw is back edge
                if (side[vw.getID()] == 1) {
                    // place vw directly after rightRef[w]
                    ref[vw.getID()] = ref[rightRef[w.getID()].getID()];
                    ref[rightRef[w.getID()].getID()] = vw;
                } else {
                    // place vw directly after leftRef[w]
                    if (leftRef[w.getID()] == null) {
                        // vw belongs to leftmost outgoing tree edge
                        ref[vw.getID()] = initialRef[w.getID()];
                        initialRef[w.getID()] = vw;
                    } else {
                        ref[vw.getID()] = ref[leftRef[w.getID()].getID()];
                        ref[leftRef[w.getID()].getID()] = vw;
                    }
                }
            }
        }
    }

    /**
     * Helper method for the planar embedding. This function computes the sign of the given edge,
     * element of {@code (-1, 1)}), indicating the edge's dfsIndex in the planar embedding. If the
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
    private int sign(final IEdge edge) {
        if (ref[edge.getID()] != null) {
            side[edge.getID()] *= sign(ref[edge.getID()]);
            ref[edge.getID()] = null;
        }
        return side[edge.getID()];
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
    private boolean conflicting(final Pair<IEdge, IEdge> interval, final IEdge edge) {
        return ((interval.getFirst() != null || interval.getSecond() != null) && lowpt[interval
                .getSecond().getID()] > lowpt[edge.getID()]);
    }

    /**
     * Helper method for the planarity test. It returns the lowpoint of the specified conflict pair
     * (i.e. the lowpoint of the lowest return edge in the conflict pair). Note, that it is
     * excluded, that all intervals of the conflict pair are empty (i.e. all references to edges are
     * {@code null}) and therefore this will not be checked.
     * 
     * @param pair
     *            the conflict pair to determine its lowpoint
     * @return the lowpoint of the conflict pair
     * 
     * @see de.cau.cs.kieler.core.util.Pair Pair
     */
    private int lowest(final Pair<Pair<IEdge, IEdge>, Pair<IEdge, IEdge>> pair) {

        if (pair.getFirst().getFirst() == null && pair.getFirst().getSecond() == null) {
            return lowpt[pair.getSecond().getFirst().getID()];
        }
        if (pair.getSecond().getFirst() == null && pair.getSecond().getSecond() == null) {
            return lowpt[pair.getFirst().getFirst().getID()];
        }
        return Math.min(lowpt[pair.getFirst().getFirst().getID()], lowpt[pair.getSecond()
                .getFirst().getID()]);
    }

    /**
     * Helper method for the planarity test. It creates a new empty conflict pair, where all
     * references of its left and right interval are set to {@code null}.
     * 
     * @return a new, empty conflict pair
     * 
     * @see de.cau.cs.kieler.core.util.Pair Pair
     */
    private Pair<Pair<IEdge, IEdge>, Pair<IEdge, IEdge>> newConflictPair() {

        Pair<IEdge, IEdge> left = new Pair<IEdge, IEdge>(null, null);
        Pair<IEdge, IEdge> right = new Pair<IEdge, IEdge>(null, null);

        return new Pair<Pair<IEdge, IEdge>, Pair<IEdge, IEdge>>(left, right);
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
    private void swap(final Pair<Pair<IEdge, IEdge>, Pair<IEdge, IEdge>> pair) {
        Pair<IEdge, IEdge> p = pair.getFirst();
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
     * @param iSide
     *            the side to consider for edge removal. If {@code intervalSide = -1}, then
     *            conflicting edges will be removed from the left interval of the conflict pair. If
     *            {@code side = 1}, then conflicting edges will only be removed from the right
     *            interval. Otherwise, if {@code side = 0}, edges will be deleted from the side,
     *            where less edges have to be removed in order to avoid edge crossings.
     * @return a LinkedList containing all deleted edges
     * 
     * @throws IllegalArgumentException
     *             if {@code iSide} is not element of {{@code -1, 0, 1}
     * 
     * @see de.cau.cs.kieler.core.util.Pair Pair
     */
    private LinkedList<IEdge> removeConflicts(
            final Pair<Pair<IEdge, IEdge>, Pair<IEdge, IEdge>> pair, final IEdge edge,
            final int iSide) {

        if (iSide > 1 || iSide < -1) {
            throw new IllegalArgumentException("Input argument iSide is neigher -1, 0 nor 1.");
        }

        LinkedList<IEdge> left = new LinkedList<IEdge>();
        LinkedList<IEdge> right = new LinkedList<IEdge>();
        IEdge currentLeft = pair.getFirst().getSecond();
        IEdge currentRight = pair.getSecond().getSecond();

        if (edge != null) {
            // determine all edges on each interval, conflicting with input edge
            while (currentLeft != null && lowpt[currentLeft.getID()] > lowpt[edge.getID()]) {
                left.add(currentLeft);
                currentLeft = ref[currentLeft.getID()];
            }
            while (currentRight != null && lowpt[currentRight.getID()] > lowpt[edge.getID()]) {
                right.add(currentRight);
                currentRight = ref[currentRight.getID()];
            }
        } else {
            // determine all edges on each interval
            while (currentLeft != null) {
                left.add(currentLeft);
                currentLeft = ref[currentLeft.getID()];
            }
            while (currentRight != null) {
                right.add(currentRight);
                currentRight = ref[currentRight.getID()];
            }
        }
        // remove edges
        if (right.size() < left.size() && iSide != -1) {
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
     * Helper method for the planarity test. It sorts the adjacency list of the specified node (i.e.
     * {@code node.getEdgeList()}) according to a non-decreasing nesting depth. Its adjacency list
     * will be completely cleared and rebuilt during sorting. Since this algorithm pursues the
     * bucket sort strategy, it guarantees a linear execution time to the number of adjacent edges.
     * 
     * @param node
     *            the node to sort its adjacency list
     */
    private void sortAdjacencyList(final INode node) {

        if (node.getEdgeList().size() > 1) {
            int minNesting = nestingDepth[node.getEdgeList().get(0).getID()];
            int maxNesting = minNesting;
            // determine highest and lowest nesting depth
            for (IEdge edge : node.getAllEdges()) {
                if (nestingDepth[edge.getID()] < minNesting) {
                    minNesting = nestingDepth[edge.getID()];
                } else if (nestingDepth[edge.getID()] > maxNesting) {
                    maxNesting = nestingDepth[edge.getID()];
                }
            }
            // create buckets
            int numberOfBuckets = maxNesting - minNesting + 1;
            ArrayList<LinkedList<IEdge>> buckets = new ArrayList<LinkedList<IEdge>>(numberOfBuckets);
            for (int i = 0; i < numberOfBuckets; i++) {
                buckets.add(new LinkedList<IEdge>());
            }
            // sort
            for (IEdge edge : node.getAllEdges()) {
                buckets.get(nestingDepth[edge.getID()] - minNesting).add(edge);
            }
            node.getEdgeList().clear();
            for (LinkedList<IEdge> bucket : buckets) {
                if (!bucket.isEmpty()) {
                    node.getEdgeList().addAll(bucket);
                }
            }

        }
    }

    /**
     * Helper method for the planar embedding. It merges the order of the incoming back edges,
     * including the outgoing tree edges, determined by {@code embeddingDFS()} with the order of
     * outgoing tree edges determined by {@code sortAdjacencyList()}. After termination, the first
     * element of the input node's adjacency list will be its incoming tree edge, unless the node is
     * the DFS-root. In case, self-loops are existing, they will be embedded right after the
     * incoming tree edge next to each other. Note, that this approach is necessary, only because no
     * common Java data structure supporting a sorting of its contained elements allows element
     * insertion at arbitrary dfsIndex in constant time. Otherwise, all edges could be moved to its
     * determined dfsIndex in linear time in only one traversal.
     * 
     * @param node
     *            the node to merge its determined edge orders
     */
    private void mergeEmbedding(final INode node) {

        List<IEdge> adjList = node.getEdgeList();
        if (adjList.size() > 1) {
            HashSet<IEdge> selfLoops = new HashSet<IEdge>();
            IEdge incTreeEdge = null;
            boolean outTreeEdgeTraversed = false;
            ListIterator<IEdge> iterator = adjList.listIterator();
            // remove all incoming edges and self-loops from adjacency list
            while (iterator.hasNext()) {
                IEdge edge = iterator.next();
                if (edge.getSource().equals(edge.getTarget())) {
                    // edge is self-loop
                    if (!selfLoops.contains(edge)) {
                        selfLoops.add(edge);
                    }
                    iterator.remove();
                } else if (!dfsSource[edge.getID()].equals(node)) {
                    // edge is incoming edge
                    if (edge.equals(parentEdge[node.getID()])) {
                        // edge is incoming tree edge
                        incTreeEdge = edge;
                    }
                    iterator.remove();
                } else if (edge.equals(parentEdge[dfsTarget[edge.getID()].getID()])) {
                    // edge is outgoing tree edge
                    if (!outTreeEdgeTraversed) {
                        // leftmost tree edge found:
                        // insert all incoming back edges and outgoing tree edges here
                        iterator.remove();
                        IEdge toAdd = initialRef[node.getID()];
                        while (toAdd != null) {
                            iterator.add(toAdd);
                            toAdd = ref[toAdd.getID()];
                        }
                        outTreeEdgeTraversed = true;
                    } else {
                        iterator.remove();
                    }
                }
            }
            // re-insert incoming tree edge and self-loops
            iterator = adjList.listIterator();
            if (incTreeEdge != null) {
                iterator.add(incTreeEdge);
            }
            for (IEdge loop : selfLoops) {
                iterator.add(loop);
                iterator.add(loop);
            }
        }
    }

    /**
     * only works, if self-loops are not interrupted by incoming tree edge and are placed directly
     * next to each other.
     * 
     * @param v
     * @param mode
     *            if {@code true}, this method will determine all crossing edges (designed for
     *            planar subgraph determination). Otherwise, this method will terminate after the
     *            first crossing edge has been found (simple planarity testing)
     * @throws InconsistentGraphModelException
     */
    private void checkAdjacencyList(final INode v, final boolean mode)
            throws InconsistentGraphModelException {

        if (v.getAdjacentEdgeCount() <= 2) {
            return;
        }
        HashMap<IEdge, IEdge> trees = new HashMap<IEdge, IEdge>();
        LinkedHashMap<IEdge, HashSet<IEdge>> cEdges = new LinkedHashMap<IEdge, HashSet<IEdge>>();
        HashSet<IEdge> selfLoops = new HashSet<IEdge>();
        HashSet<IEdge> crossing = new HashSet<IEdge>(v.getAdjacentEdgeCount());
        // previously traversed, non-self-loop edge
        IEdge prevTree = null;
        int curHeight = Integer.MIN_VALUE;

        IEdge lEdge = initialRef[v.getID()];
        IEdge lPre = null;
        while (lEdge != null) {
            // convert list of edges belonging to outgoing subtrees
            if (!trees.containsKey(relatedTree[lEdge.getID()])) {
                trees.put(relatedTree[lEdge.getID()], lEdge);
                cEdges.put(relatedTree[lEdge.getID()], new HashSet<IEdge>());
            }
            if (relatedTree[lEdge.getID()] != relatedTree[ref[lEdge.getID()].getID()]) {
                // next edge belongs to different subtree
                lPre = lEdge;
                lEdge = ref[lEdge.getID()];
                ref[lPre.getID()] = null;
            } else {
                lEdge = ref[lEdge.getID()];
            }
        }
        // check planarity
        for (IEdge edge : v.getAllEdges()) {
            if (!(mode || isPlanar)) {
                // graph already turned out to be non-planar
                return;
            }
            if (edge.getSource().equals(edge.getTarget())) {
                // self-loop
                if (selfLoops.contains(edge)) {
                    // TODO: Self.loops. Should be almost compatible with the subtree stuff
                } else {
                    selfLoops.add(edge);
                }
                continue;
            } else if ((edge.equals(parentEdge[dfsTarget[edge.getID()].getID()]) && dfsSource[edge
                    .getID()].equals(v))
                    || (dfsTarget[edge.getID()].equals(v) && parentEdge[v.getID()] != edge)) {
                // TODO check this subsection! Might contain programming errors
                // e is outgoing tree edge or incoming back edge
                IEdge plannedNext = trees.get(relatedTree[edge.getID()]);
                if (plannedNext.equals(edge)) {
                    plannedNext = ref[plannedNext.getID()];
                    while (plannedNext != null && crossing.contains(plannedNext)) {
                        plannedNext = ref[plannedNext.getID()];
                    }
                    trees.put(relatedTree[edge.getID()], plannedNext);
                    prevTree = relatedTree[edge.getID()];
                } else {
                    crossing.add(edge);
                    crossingEdges.add(edge);
                    isPlanar = false;
                    continue;
                }
                if (prevTree != relatedTree[edge.getID()]) {
                    // other subtree
                    HashSet<IEdge> ownOpen = cEdges.get(relatedTree[edge.getID()]);
                    for (IEdge open : cEdges.keySet()) {
                        // the next edge of the subtree
                        if (trees.get(open) != null) {
                            // subtree is open
                            if (ownOpen.contains(open)) {
                                // crossing edge
                                isPlanar = false;
                                this.crossingEdges.add(edge);
                                crossing.add(edge);
                                continue;
                            } else {
                                HashSet<IEdge> update = cEdges.get(open);
                                update.add(relatedTree[edge.getID()]);
                                cEdges.put(open, update);
                            }
                        }
                    }
                    prevTree = edge;
                }
            } else if (dfsSource[edge.getID()].equals(v)) {
                // e is outgoing back edge
                if (nestingDepth[edge.getID()] * dfsTargetSide[edge.getID()] < curHeight) {
                    crossingEdges.add(edge);
                    crossing.add(edge);
                    isPlanar = false;
                } else {
                    curHeight = nestingDepth[edge.getID()] * dfsTargetSide[edge.getID()];
                }
                // TODO self-loops
                // else: e is incoming tree edge: do nothing
            }
        }
    }

    /**
     * TODO Determines the dfsTargetSide of all incoming edges adjacent to this node. Note, that the
     * {@code dfsTargetSide} of all back edges returning to the root is always {@code 1}, since a
     * side cannot be defined precisely for that node. Tree edges already have dfsTargetSide = 0,
     * since the attribute is initialized with this value.
     * 
     * @param node
     *            the node to determine the targetSide of all incoming edges adjacent to this node
     * @throws InconsistentGraphModelException
     *             if the adjacency list is not consistent
     */
    private void determineDfsTargetSides(final INode node) throws InconsistentGraphModelException {

        HashSet<IEdge> traversedTrees = new HashSet<IEdge>();
        for (IEdge edge : node.getAllEdges()) {
            if (dfsSource[edge.getID()].equals(node)
                    && edge.equals(parentEdge[dfsTarget[edge.getID()].getID()])) {
                // outgoing tree edge
                traversedTrees.add(edge);
            } else if (dfsTarget[edge.getID()].equals(node)
                    && !edge.equals(parentEdge[node.getID()])) {
                // incoming back edge
                if (traversedTrees.contains(relatedTree[edge.getID()])) {
                    // tree edge already traversed -> edge is on right side
                    dfsTargetSide[edge.getID()] = 1;
                } else {
                    // tree edge still to traverse -> edge is on left side
                    dfsTargetSide[edge.getID()] = -1;
                }
            }
        }
    }

    /**
     * Helper method for the planar embedding with port constraints. It normalizes the input node's
     * adjacency list, i.e. it moves all edges up to, but not including, the incoming tree edge from
     * the front to the end of the list. After termination, the first element of the node's
     * adjacency list will be its incoming tree edge. If the node is a root, its adjacency list will
     * not be changed (i.e. a call of this method has no effect in this case). Note, that, if the
     * input node is a root of a connected component, the first edge of its adjacency list is always
     * an outgoing tree edge.
     * 
     * @param node
     *            the node to normalize its adjacency list
     * @throws InconsistentGraphModelException
     *             if the adjacency list is not consistent with the connected edges
     */
    private void normalize(final INode node) throws InconsistentGraphModelException {

        if (node.getAdjacentEdgeCount() > 1 && parentEdge[node.getID()] != null) {
            // node is not a root
            ListIterator<IEdge> iterator = node.getEdgeList().listIterator();
            LinkedList<IEdge> front = new LinkedList<IEdge>();
            while (iterator.hasNext()) {
                IEdge edge = iterator.next();
                if (!edge.equals(parentEdge[node.getID()])) {
                    // edge is not the incoming tree edge
                    front.addLast(edge);
                    iterator.remove();
                } else {
                    break;
                }
            }
            // add removed edges to the end
            node.getEdgeList().addAll(front);
        }
    }

    /**
     * 
     * @param node
     *            the node the edge is adjacent to
     * @param s
     *            the side
     * @return
     */
    private int fitsOnSide(final INode node, final int s) {

        // TODO check for both dfsSource and dfsTarget node
        if (s == -1) {
            // determine position for right side
            if (backtracking[node.getID()]) {
                return leftRightPos[node.getID()] + 1;
            } else {
                return leftLeftPos[node.getID()] - 1;
            }
        } else if (s == 1) {
            // determine position for right side
            if (backtracking[node.getID()]) {
                return rightLeftPos[node.getID()] - 1;
            } else {
                return rightRightPos[node.getID()] + 1;
            }
        } else {
            throw new IllegalArgumentException("Input argument s is neither -1 or 1.");
        }
    }

    // ------------------------- Simple main() for testing only --------------------------------

    /**
     * A simple method for testing the LRPlanarity component.
     * 
     * @param args
     *            ignored
     */
    public static void main(final String[] args) {

        // CHECKSTYLEOFF MagicNumber

        /* Number of nodes, that form the outer cycle. */
        int nodes = 5;
        int edges = 4000;
        boolean randomGraph = false;

        PGraph graph;
        HashMap<Integer, INode> map;

        IPlanarityTester lr = new LRPlanarityTester();
        try {
            // create test graph
            graph = new PGraph();
            map = new HashMap<Integer, INode>();
            for (int i = 0; i < nodes; i++) {
                INode toAdd = graph.addNode();
                map.put(i, toAdd);
            }

            /*
             * graph.addEdge(map.get(9), map.get(5)); graph.addEdge(map.get(2), map.get(5));
             * graph.addEdge(map.get(7), map.get(9)); graph.addEdge(map.get(2), map.get(8));
             * graph.addEdge(map.get(7), map.get(1)); graph.addEdge(map.get(9), map.get(1));
             * graph.addEdge(map.get(1), map.get(8)); graph.addEdge(map.get(0), map.get(9));
             * graph.addEdge(map.get(7), map.get(0)); graph.addEdge(map.get(0), map.get(8));
             */
            if (randomGraph) {
                // adding random edges
                for (int i = 0; i < edges; i++) {
                    graph.addEdge(map.get(((int) (Math.random() * 100000)) % nodes), map
                            .get(((int) (Math.random() * 100000)) % nodes));
                }
            }

            if (!randomGraph) {
                // generating cyclic graph
                for (int i = 0; i < nodes; i++) {
                    graph.addEdge(map.get(i), map.get((i + 1) % nodes));
                }
                // manual edge insertions

                graph.addEdge(map.get(0), map.get(2));
                graph.addEdge(map.get(1), map.get(4));
                // graph.addEdge(map.get(1), map.get(3));
                graph.addEdge(map.get(0), map.get(3));
                graph.addEdge(map.get(2), map.get(4));
                // graph.addEdge(map.get(2), map.get(7));
                // graph.addEdge(map.get(6), map.get(2));
                // graph.addEdge(map.get(5), map.get(7));
            }

            // execute planarity tester
            long exec = System.nanoTime();
            System.out.print("----------------------------\n" + "planarity test result: "
                    + lr.testPlanarity(graph) + "\n");
            System.out.print("----------------------------\n");
            System.out.print("\nTotal exec time: " + (System.nanoTime() - exec) + "ns\n\n");
            exec = System.nanoTime();
            System.out.print("============================\n" + "performing planar embedding\n");
            System.out.print("removed crossing edges: " + lr.planarSubgraph(graph).size() + "\n");
            System.out.print("planar? : " + lr.testPlanarity(graph) + "\n");
            System.out.print("\nTotal exec time: " + (System.nanoTime() - exec) + "ns\n\n");
            System.out.print("----------------------------\n");

        } catch (InconsistentGraphModelException e1) {
            e1.printStackTrace();
        }

    }

    // +++++++++++++++++++++++ not needed any more +++++++++++++++++++++++++++++++++++++

    // ====================== Enumerator =====================================

    /**
     * An enumerator for all edge types related to a node in a DFS-oriented tree.
     */
    private enum EdgeType {

        /** edge is incoming tree edge. */
        INCTREE,
        /** edge is outgoing tree edge. */
        OUTTREE,
        /** edge is incoming back edge. */
        INCBACK,
        /** edge is outgoing back edge. */
        OUTBACK,
        /** edge is self-loop. */
        SELFLOOP
    }

    /**
     * Helper method for the planarity test. It determines the type of the input edge (incoming tree
     * edge, outgoing tree edge, incoming back edge or outgoing back edge) regarding the specified
     * node. This method will return this type or {@code null}, if the edge is not adjacent to the
     * node.
     * 
     * @param node
     *            the node the edge type is related to
     * @param edge
     *            the edge to determine its type related to the specified node
     * @return the type of the edge
     * @throws InconsistentGraphModelException
     *             if the edge is not connected with the edge
     * 
     * @see de.cau.cs.rtprak.planarization.EdgeType.Type EdgeType
     */
    private EdgeType getEdgeType(final INode node, final IEdge edge)
            throws InconsistentGraphModelException {

        if (edge.getSource().equals(edge.getTarget())) {
            return EdgeType.SELFLOOP;
        }
        if (dfsSource[edge.getID()].equals(node)) {
            if (edge.equals(parentEdge[dfsTarget[edge.getID()].getID()])) {
                return EdgeType.OUTTREE;
            }
            return EdgeType.OUTBACK;
        }
        if (edge.equals(parentEdge[node.getID()])) {
            return EdgeType.INCTREE;
        }
        if (dfsTarget[edge.getID()].equals(node)) {
            return EdgeType.INCBACK;
        }
        throw new InconsistentGraphModelException(
                "Attempted to get adjacent node from unconnected edge");
    }

    private int index;

    private void determineDFSIndex(final INode v) {
        for (IEdge vw : v.getAllEdges()) {
            if (vw.getSource().equals(vw.getTarget()) || !dfsSource[vw.getID()].equals(v)) {
                // vw is incoming edge or self-loop
                continue;
            }
            INode w = dfsTarget[vw.getID()];
            if (vw.equals(parentEdge[w.getID()])) {
                // vw is tree edge
                dfsIndex[vw.getID()] = index++;
                determineDFSIndex(w);
            } else {
                if (dfsTargetSide[vw.getID()] == -1) {
                    dfsIndex[vw.getID()] = -index;
                    index++;
                } else {
                    dfsIndex[vw.getID()] = Integer.MAX_VALUE - index;
                    index++;
                }
            }
        }
    }

    /**
     * Helper method for the planarity test with port constraints. returns all edges to delete. If
     * side is not -1 or 1, no edge will be deleted, i.e. the returned linked list will be empty.
     * 
     * @param pair
     *            the conflict pair to delete the specified interval from or the conflict pair to
     *            delete all edges conflicting with the input edge on the specified side from
     * @param edge
     *            the edge to determine all interval edges to delete or {@code null}
     * @param intervalSide
     *            the side to consider for edge removal. If {@code intervalSide = -1}, then
     *            conflicting edges will be removed from the left interval of the conflict pair. If
     *            {@code side = 1}, then conflicting edges will only be removed from the right
     *            interval.
     * @return a LinkedList containing all removed edges
     * 
     * @throws IllegalArgumentException
     *             if {@code intervalSide} is not element if {@code -1, 1} .
     * 
     * @see de.cau.cs.kieler.core.util.Pair Pair
     */
    private LinkedList<IEdge> removeConflicts2(
            final Pair<Pair<IEdge, IEdge>, Pair<IEdge, IEdge>> pair, final IEdge edge,
            final int intervalSide) {

        LinkedList<IEdge> conflicting = new LinkedList<IEdge>();
        IEdge current = null;

        if (edge != null) {
            // remove all edges in the interval conflicting with the input edge
            if (intervalSide == 1) {
                current = pair.getSecond().getSecond();
                while (current != null && lowpt[current.getID()] > lowpt[edge.getID()]) {
                    conflicting.add(current);
                    current = ref[current.getID()];
                }
                pair.getSecond().setSecond(current);
                if (current == null) {
                    pair.getSecond().setFirst(null);
                }
            } else if (intervalSide == -1) {
                current = pair.getFirst().getSecond();
                while (current != null && lowpt[current.getID()] > lowpt[edge.getID()]) {
                    conflicting.add(current);
                    current = ref[current.getID()];
                }
                pair.getFirst().setSecond(current);
                if (current == null) {
                    pair.getFirst().setFirst(null);
                }
            } else {
                throw new IllegalArgumentException(
                        "Input argument IntervalSide is not element of {-1, 1}");
            }
        } else {
            // remove all edges in the interval
            if (intervalSide == 1) {
                current = pair.getSecond().getSecond();
                while (current != null) {
                    conflicting.add(current);
                    current = ref[current.getID()];
                }
                pair.getSecond().setSecond(null);
                pair.getSecond().setFirst(null);

            } else if (intervalSide == -1) {
                current = pair.getFirst().getSecond();
                while (current != null) {
                    conflicting.add(current);
                    current = ref[current.getID()];
                }
                pair.getFirst().setSecond(null);
                pair.getFirst().setFirst(null);
            } else {
                throw new IllegalArgumentException(
                        "Input argument IntervalSide is not element of {-1, 1}");
            }
        }
        return conflicting;
    }

}
