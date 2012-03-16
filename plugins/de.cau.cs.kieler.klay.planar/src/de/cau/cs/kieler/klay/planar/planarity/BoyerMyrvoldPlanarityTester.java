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
package de.cau.cs.kieler.klay.planar.planarity;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.klay.planar.graph.IEdge;
import de.cau.cs.kieler.klay.planar.graph.IGraph;
import de.cau.cs.kieler.klay.planar.graph.INode;
import de.cau.cs.kieler.klay.planar.util.ManuallyIterable;
import de.cau.cs.kieler.klay.planar.util.ManuallyIterable.Direction;
import de.cau.cs.kieler.klay.planar.util.ManuallyIterable.ManualIterator;

/**
 * An algorithm for planar testing by John M. Boyer and Wendy J. Myrvold. Based on the paper "On the
 * Cutting Edge: Simplified O(n) Planarity by Edge Addition". The algorithm is based on the idea of
 * copying the nodes of the whole graph, and then insert the edges one by as long as the graph is
 * planar. This modified version tried to do this in-place, by removing all edges during
 * preprocessing, and re-inserting them one by one. The algorithm also uses virtual root nodes,
 * images of a node that are inserted for every child node of the parent, to mark biconnected
 * components in the graph. Every virtual root in the graph is the root point of a biconnected
 * component, and it is removed as soon as biconnected components are merged into larger ones. After
 * preprocessing the graph with a DFS to create the virtual roots, build a DFS tree and determine
 * some initial values of the nodes, the graph is rebuild from the leaves up to the roots of the DFS
 * tree (by traversing the nodes in reversed order of their DFI). For every node, the following
 * steps are performed: First, the tree edges are embedded. Then, the subgraph is traversed from the
 * endpoint of a backedge up to the current node, marking all encountered virtual roots on the way.
 * Lastly, the subgraph is traversed from the current node downwards, jumping into new biconnected
 * components if marked roots are encountered, until the node marked for a backedge is found. Then
 * all biconnected components traversed on the way are merged into one and the backedge is embedded.
 * 
 * @author ocl
 */
public class BoyerMyrvoldPlanarityTester extends AbstractAlgorithm implements IPlanarityTester {

    // ======================== Attributes =========================================================

    /** The work graph. */
    private IGraph graph;

    /** The list of edges not yet in the planar subgraph. */
    private final LinkedList<IEdge> missingEdges = new LinkedList<IEdge>();

    /** The list of graph nodes in reversed DFI order. */
    private final LinkedList<INode> reversedNodes = new LinkedList<INode>();

    /** The result of the planarity testing algorithm. */
    private boolean planar = true;

    /** The external face of the graph. */
    private ManuallyIterable<INode> externalFace;

    // ======================== Node Properties ====================================================

    /**
     * The Depth First Search index for every node. Indicates the order in which the nodes were
     * traversed during DFS.
     */
    private int[] dfi;

    /**
     * The lowpoint of every node. Indicates the lowest DFI a node can reach by any amount of tree
     * edges and one back edge.
     */
    private int[] lowpoint;

    /**
     * Virtual state of every node. Defines if a node is a virtual root node (algorithm-specific
     * dummy node) or not.
     */
    private boolean[] isVirtual;

    /**
     * The sign of every node. Whenever a biconnected component is flipped, the sign of its root
     * node is inverted. A planar embedding can be recovered in post processing with this
     * information.
     */
    private boolean[] flipped;

    /**
     * The least ancestor of every node. The least ancestor is the lowest DFI of a node that can
     * directly be accessed by a back edge.
     */
    private int[] ancestor;

    /**
     * The visited flag of every node. A boolean that indicates if a node has been visited by the
     * walkup method. The boolean value is dependent on the currently traversed root node, so it is
     * true concerning the root node, if the value of the visited flag is equal to the root node.
     */
    private INode[] visited;

    /**
     * The parent node of every node. The parent node is the parent in the DFS tree, it is also the
     * node with a DFI of the node + 1.
     */
    private INode[] parent;

    /**
     * The list of children of every node. The children are the children of a node in the DFS tree.
     * This is not the same as the separated children. For virtual root nodes, this list contains
     * exactly one element (since a virtual root is created for every child of a node).
     */
    private LinkedList<INode>[] children;

    /**
     * The list of separated children in of every node. The children are the children of a node in
     * the DFS tree. This list always contains the children of a node that are in different
     * biconnected components. If biconnected components are merged, entries are deleted from this
     * list. A LinkedHashSet is used to guarantee constant time addition and deletion from the list,
     * without losing its order.
     */
    private LinkedHashSet<INode>[] separatedChildren;

    /**
     * The list of back edges on every node.
     */
    private LinkedList<IEdge>[] backedges;

    /**
     * The pertinent back edges of every node. Indicates the back edges that should be embedded
     * between the node and the currently processed node.
     */
    private LinkedList<IEdge>[] pertinentBackedges;

    /**
     * The list of root nodes of every node. The root nodes of a node are virtual nodes that are
     * inserted to perform as an image of the node to the attached biconnected component. If
     * biconnected components are merged, the respective virtual node is removed from this list. A
     * LinkedHashSet is used to guarantee constant time addition and deletion from the list.
     */
    private LinkedHashSet<INode>[] roots;

    /**
     * The list of pertinent roots of every node. The pertinent roots of a node are determined
     * during the {@code walkup}. They are the root nodes that belong to the pertinent subgraph,
     * i.e. all root nodes that will be important during the {@code walkdown}. For the pertinent
     * root itself, this list contains the node to which the root is pertinent (i.e. the currently
     * processed node).
     */
    private LinkedList<INode>[] pertinentRoots;

    /**
     * {@inheritDoc}
     */
    @Override
    public void reset() {
        super.reset();
        graph = null;
        missingEdges.clear();
        reversedNodes.clear();
        planar = true;
    }

    // ======================== Algorithm Interface ================================================

    /**
     * {@inheritDoc} This guarantees to check for graph planarity in time linear to the number of
     * nodes in the graph.
     */
    public boolean testPlanarity(final IGraph g) {
        getMonitor().begin("Planarity Testing", 1);
        this.graph = g;
        this.planarity();
        getMonitor().done();
        return this.planar;
    }

    /**
     * {@inheritDoc} This guarantees to find a planar embedding for a subgraph in time linear to the
     * number of nodes in the graph.
     */
    public List<IEdge> planarSubgraph(final IGraph g) {
        getMonitor().begin("Planarity Testing", 1);
        this.graph = g;
        this.planarity();
        getMonitor().done();
        return this.missingEdges;
    }

    // ======================== Boyer-Myrvold-Algorithm ============================================

    /**
     * The main part of the Boyer-Myrvold algorithm for graph planarity. The algorithm starts with
     * preprocessing the graph by using a DFS. During the preprocessing, various properties of graph
     * nodes are set, all edges are removed from the graph, and a virtual root node is inserted for
     * every child of a node in the DFS tree. During the main part of the algorithm, the nodes are
     * traversed in reversed order of their DFI, building up the graph from the leafs to the roots.
     * For every traversed node, the tree edges are embedded, then the {@code walkup} method in
     * invoked for every node that is connected to the traversed node by a backedge. The {@code
     * walkup} marks all virtual root nodes on the path between these two nodes. Then, the {@code
     * walkup} digests this information, by traversing the graph over all marked nodes, until it
     * finds the node where the backedge should be embedded. At this point, all traversed virtual
     * roots are merged into their parent nodes and the backedge is embedded. To keep all endpoints
     * of future backedges on the external face, the biconnnected components containing these nodes
     * may have to be flipped. This is done my marking the nodes to flip during {@code walkup}.
     * After the whole graph is processed, {@code postprocessing} method is invoked, that flips all
     * nodes according to the information from {@code walkdown} and removes any leftover virtual
     * roots from the graph.
     * 
     * @param inputGraph
     *            the input graph to test]
     */
    // Initialization of arrays for node properties require unchecked casts
    @SuppressWarnings("unchecked")
    private void planarity() {
        this.missingEdges.clear();
        this.reversedNodes.clear();
        this.externalFace = new ManuallyIterable<INode>(this.graph.getNodeCount());

        // Initialize all node properties
        // Reserve enough space for nodes and virtual node
        this.graph.reindex();
        int size = (2 * this.graph.getNodeCount()) + 1;
        this.dfi = new int[size];
        this.isVirtual = new boolean[size];
        this.flipped = new boolean[size];
        this.lowpoint = new int[size];
        this.ancestor = new int[size];
        this.visited = new INode[size];
        this.parent = new INode[size];
        this.children = new LinkedList[size];
        this.separatedChildren = new LinkedHashSet[size];
        this.backedges = new LinkedList[size];
        this.pertinentBackedges = new LinkedList[size];
        this.roots = new LinkedHashSet[size];
        this.pertinentRoots = new LinkedList[size];

        // Find the DFS roots (i.e. nodes in separated connected components)
        LinkedList<INode> dfsRoots = new LinkedList<INode>();
        for (INode root : this.graph.getNodes()) {
            if (this.visited[root.getID()] == null) {
                dfsRoots.add(root);
                this.findRoots(root);
            }
        }
        Arrays.fill(this.visited, null);

        // Set node properties via DFS
        List<INode>[] buckets = new LinkedList[size];
        int index = 0;
        for (INode root : dfsRoots) {
            index = this.preProcessing(root, null, index + 1, buckets);
        }

        // Sort child lists with bucket sort
        for (int i = 0; i < buckets.length; i++) {
            if (buckets[i] != null) {
                for (INode node : buckets[i]) {
                    INode parentNode = this.parent[node.getID()];
                    if (parentNode != null) {
                        this.separatedChildren[parentNode.getID()].add(node);
                    }
                }
            }
        }

        // Traverse nodes in reversed DFI order
        for (INode node : this.reversedNodes) {
            int iNode = node.getID();

            // Find pertinent Nodes for all back edges
            for (IEdge backedge : this.backedges[iNode]) {
                INode neighbor = node.getAdjacentNode(backedge);
                if (this.dfi[neighbor.getID()] > this.dfi[iNode]) {
                    this.pertinentBackedges[neighbor.getID()].add(backedge);
                    this.walkup(node, neighbor);
                }
            }

            // Embed back edges
            for (INode root : this.roots[iNode]) {
                if (this.walkdown(root, Direction.FWD)) {
                    this.walkdown(root, Direction.REV);
                }
            }

            // Check if all edges are embedded
            for (IEdge backedge : this.backedges[iNode]) {
                INode neighbor = node.getAdjacentNode(backedge);
                LinkedList<IEdge> missing = this.pertinentBackedges[neighbor.getID()];
                for (IEdge e : missing) {
                    this.graph.removeEdge(e);
                    this.missingEdges.add(e);
                    this.planar = false;
                }
                missing.clear();
            }
        }

        // Clean up the graph
        Arrays.fill(this.visited, null);
        for (INode root : dfsRoots) {
            this.postProcessing(root, null, false);
        }
    }

    /**
     * Perform a quick Depth First Search to find the first node in every connected component.
     * 
     * @param node
     *            the currently processed node
     */
    private void findRoots(final INode node) {
        this.visited[node.getID()] = node;
        for (INode child : node.adjacentNodes()) {
            if (this.visited[child.getID()] == null) {
                this.findRoots(child);
            }
        }
    }

    /**
     * Perform the preprocessing of the graph. This is a Depth First Search, that initializes all
     * properties for nodes. It also marks all edges for removal (they are removed later, so the
     * iterators are not broken), and inserts virtual root nodes, for every child node in the graph.
     * Virtual root nodes are images of parent nodes to a specific child of the node and each mark a
     * separated biconnected component. The preprocessing also calculates properties like lowpoint
     * and least ancestor for all nodes.
     * 
     * @param current
     *            the currently processed node
     * @param parentNode
     *            the previously processed node
     * @param i
     *            the next DFS index
     * @param map
     *            a map used to map input graph nodes to working graph nodes
     * @return the next DFS index
     */
    private int preProcessing(final INode node, final INode parentNode, final int i,
            final List<INode>[] buckets) {
        int index = i;
        int iNode = node.getID();
        this.reversedNodes.addFirst(node);

        // Initialize node properties
        this.dfi[iNode] = index;
        this.lowpoint[iNode] = index;
        this.ancestor[iNode] = index;
        this.isVirtual[iNode] = false;
        this.parent[iNode] = parentNode;
        this.separatedChildren[iNode] = new LinkedHashSet<INode>();
        this.children[iNode] = new LinkedList<INode>();
        this.backedges[iNode] = new LinkedList<IEdge>();
        this.pertinentBackedges[iNode] = new LinkedList<IEdge>();
        this.roots[iNode] = new LinkedHashSet<INode>();
        this.pertinentRoots[iNode] = new LinkedList<INode>();

        // Recurse over all adjacent nodes
        LinkedList<Pair<IEdge, INode>> tomove = new LinkedList<Pair<IEdge, INode>>();
        for (IEdge edge : node.adjacentEdges()) {
            INode child = node.getAdjacentNode(edge);
            int iChild = child.getID();

            if (child == node) {
                // Found self loop: ignore
                continue;

            } else if (child == parentNode) {
                // Found tree edge: ignore
                continue;

            } else if (this.children[iChild] != null) {
                // Found already visited node: mark for back edge
                this.backedges[iNode].add(edge);

                // Re-Calculate the least ancestor and lowpoint
                if (this.dfi[iChild] < this.ancestor[iNode]) {
                    this.ancestor[iNode] = this.dfi[iChild];
                }
                if (this.lowpoint[iChild] < this.lowpoint[iNode]) {
                    this.lowpoint[iNode] = this.lowpoint[iChild];
                }

            } else {
                // Found new child node: add virtual root node and recurse
                INode bicomp = this.graph.addNode();
                int iBicomp = bicomp.getID();
                this.dfi[iBicomp] = this.dfi[iNode];
                this.lowpoint[iBicomp] = this.lowpoint[iNode];
                this.ancestor[iBicomp] = this.ancestor[iNode];
                this.isVirtual[iBicomp] = true;
                this.parent[iBicomp] = node;
                this.children[iBicomp] = new LinkedList<INode>();
                this.roots[iBicomp] = new LinkedHashSet<INode>();
                this.pertinentRoots[iBicomp] = new LinkedList<INode>();

                this.externalFace.setSuccessor(child, bicomp, Direction.FWD);
                this.externalFace.setSuccessor(child, bicomp, Direction.REV);
                this.externalFace.setSuccessor(bicomp, child, Direction.FWD);
                this.externalFace.setSuccessor(bicomp, child, Direction.REV);

                this.children[iNode].add(child);
                this.children[iBicomp].addFirst(node);
                this.roots[iNode].add(bicomp);

                index = this.preProcessing(child, node, index + 1, buckets);
                tomove.add(new Pair<IEdge, INode>(edge, bicomp));

                // Re-Calculate the lowpoint
                if (this.lowpoint[iChild] < this.lowpoint[iNode]) {
                    this.lowpoint[iNode] = this.lowpoint[iChild];
                }
            }
        }
        for (Pair<IEdge, INode> pair : tomove) {
            pair.getFirst().move(node, pair.getSecond());
        }

        // Add node to lowpoint bucket for later sorting
        if (buckets[this.lowpoint[iNode]] == null) {
            buckets[this.lowpoint[iNode]] = new LinkedList<INode>();
        }
        buckets[this.lowpoint[iNode]].add(node);

        // Copy least ancestor and lowpoint values to virtual roots
        for (INode root : this.roots[iNode]) {
            int iRoot = root.getID();
            this.ancestor[iRoot] = this.ancestor[iNode];
            this.lowpoint[iRoot] = this.lowpoint[iNode];
        }

        return index;
    }

    /**
     * Determine the pertinent subgraph. The {@code walkup} starts at a node that is the endpoint of
     * a backedge flag to the currently processd node in the main algorithm. It traverses the
     * external face up to that node, and registers all virtual root nodes it encounters on the way
     * in the pertinent root list of their parent node. This way, only nodes pertinent to the
     * currently processed nodes have to be traversed in later steps of the main algorithm.
     * 
     * @param node
     *            the currently processed node
     * @param childNode
     *            the node on the other side of the back edge
     */
    private void walkup(final INode node, final INode childNode) {

        // Set up the two traversal classes
        ManualIterator<INode> left = this.externalFace.iterator(node, Direction.FWD);
        left.setCurrent(childNode);
        ManualIterator<INode> right = this.externalFace.iterator(node, Direction.REV);
        right.setCurrent(childNode);

        // Iterate over external face
        while (left.hasNext()) {
            int iLeft = left.getCurrent().getID();
            int iRight = right.getCurrent().getID();

            // Break on already visited nodes
            if (this.visited[iLeft] == node || this.visited[iRight] == node) {
                break;
            }
            this.visited[iLeft] = node;
            this.visited[iRight] = node;

            // Check if we found a root node
            INode n = null;
            if (this.isVirtual[left.getCurrent().getID()]) {
                n = left.getCurrent();
            } else if (this.isVirtual[right.getCurrent().getID()]) {
                n = right.getCurrent();
            }

            // Add root nodes to pertinent nodes list
            if (n != null) {
                INode parentNode = this.parent[n.getID()];
                if (parentNode != node) {
                    if (this.lowpoint[parentNode.getID()] < this.dfi[node.getID()]) {
                        this.pertinentRoots[parentNode.getID()].addLast(n);
                    } else {
                        this.pertinentRoots[parentNode.getID()].addFirst(n);
                    }
                    this.pertinentRoots[n.getID()].addFirst(node);
                }
                left.setCurrent(parentNode);
                right.setCurrent(parentNode);

            } else {
                left.next();
                right.next();
            }
        }
    }

    /**
     * Embed a back edge in the new graph. The {@code walkdown} traverses the external face of a
     * biconnected component, starting on a root node of the currently processed node in the main
     * algorithm. Whenever it encounters a root node, that is marked as pertinent by the {@code
     * walkup} method, it jumps to the corresponding biconnected component, and resumes the
     * traversal there. Eventually the method will reach one of the node marked for a backedge. Then
     * all biconnected components that were traversed along the way are merged into one biconnected
     * component, all virtual roots are removed, and the backedge is embedded. Whenever the endpoint
     * of a future backedge is encountered, a shortcut on the external face is established, that
     * prevents traversal beyond this node, since any further additions of backedges will remove
     * that node from the external face and the graph is not planar any more.
     * 
     * @param root
     *            the root node of the traversed graph section
     * @param direction
     *            the direction of the face traversal
     * @return true if the embedding was successful
     */
    private boolean walkdown(final INode root, final Direction direction) {

        LinkedList<ManualIterator<INode>> merge = new LinkedList<ManualIterator<INode>>();
        INode node = this.parent[root.getID()];

        // Traverse external face of biconnected component
        ManualIterator<INode> iter = this.externalFace.iterator(root, direction);
        iter.next();
        while (iter.hasNext()) {
            int iIter = iter.getCurrent().getID();

            // Back edge node found, merge
            if (!this.pertinentBackedges[iIter].isEmpty()) {
                this.mergeBicomp(merge);
                iter.setDirection(direction);
                for (IEdge edge : this.pertinentBackedges[iIter]) {
                    this.backedges[node.getID()].remove(edge);
                    edge.move(node, root);
                    switch (direction) {
                    case FWD:
                        root.moveToStart(edge);
                        iter.getCurrent().moveToEnd(edge);
                        break;
                    case REV:
                        root.moveToEnd(edge);
                        iter.getCurrent().moveToStart(edge);
                        break;
                    }
                }
                this.pertinentBackedges[iIter].clear();
                Direction opp = (direction == Direction.FWD) ? Direction.REV : Direction.FWD;
                this.externalFace.setSuccessor(root, iter.getCurrent(), direction);
                this.externalFace.setSuccessor(iter.getCurrent(), root, opp);

            } else if (this.isPertinent(iter.getCurrent(), node)) {
                // Found a new pertinent biconnected component, jump there
                merge.push(iter);

                ManualIterator<INode> temp = this.externalFace.iterator(root, Direction.FWD);
                for (INode r : this.pertinentRoots[iIter]) {
                    if (this.pertinentRoots[r.getID()].getFirst() == node) {
                        temp.setCurrent(r);
                        break;
                    }
                }

                ManualIterator<INode> left = this.externalFace.iterator(root, Direction.FWD);
                left.setCurrent(temp.getCurrent());
                do {
                    left.next();
                } while (!isPertinent(left.getCurrent(), node)
                        && !isExternallyActive(left.getCurrent(), node));

                ManualIterator<INode> right = this.externalFace.iterator(root, Direction.REV);
                right.setCurrent(temp.getCurrent());
                do {
                    right.next();
                } while (!isPertinent(right.getCurrent(), node)
                        && !isExternallyActive(right.getCurrent(), node));

                if (this.isPertinent(left.getCurrent(), node)
                        && !this.isExternallyActive(left.getCurrent(), node)) {
                    iter = left;
                    temp.setDirection(Direction.REV);
                } else if (this.isPertinent(right.getCurrent(), node)
                        && !this.isExternallyActive(right.getCurrent(), node)) {
                    iter = right;
                    temp.setDirection(Direction.FWD);
                } else if (this.isPertinent(left.getCurrent(), node)) {
                    iter = left;
                    temp.setDirection(Direction.REV);
                } else {
                    iter = right;
                    temp.setDirection(Direction.FWD);
                }

                merge.push(temp);

            } else if (!this.isPertinent(iter.getCurrent(), root)
                    && !this.isExternallyActive(iter.getCurrent(), node)) {
                // Traverse to next vertex
                iter.next();

            } else {
                // Node is stopping vertex: stop
                int iChild = this.children[root.getID()].getFirst().getID();
                int iParent = this.parent[root.getID()].getID();
                if (this.lowpoint[iChild] < this.dfi[iParent] && merge.isEmpty()) {
                    Direction dir = iter.getDirection();
                    Direction opp = (dir == Direction.FWD) ? Direction.REV : Direction.FWD;
                    this.externalFace.setSuccessor(root, iter.getCurrent(), dir);
                    this.externalFace.setSuccessor(iter.getCurrent(), root, opp);
                }
                break;
            }
        }
        return merge.isEmpty();
    }

    /**
     * Merge all biconnected components on a merge stack. This merges all biconnected component,
     * that were encountered during the {@code walkdown} so far into a single biconnected component.
     * First, the direction of the traversal before and after jumping to a new biconnected component
     * is checked, to determine if the component has to be flipped. If that is the case, the
     * corresponding node is marked (flipping itself is handled in postprocessing). Then, all
     * virtual roots on the way are merged with their parent nodes.
     * 
     * @param stack
     *            the merge stack
     */
    private void mergeBicomp(final LinkedList<ManualIterator<INode>> stack) {
        while (!stack.isEmpty()) {
            // The virtual root of node
            ManualIterator<INode> root = stack.pop();
            int iRoot = root.getCurrent().getID();
            // The pertinent node on the external face
            ManualIterator<INode> node = stack.pop();
            int iNode = node.getCurrent().getID();

            // Flipping
            if (root.getDirection() == node.getDirection()) {
                // Invert the adjacency list of the root node and mark bicomp as flipped
                root.getCurrent().mirror();
                int iChild = this.children[iRoot].getFirst().getID();
                this.flipped[iChild] = !this.flipped[iChild];

                // Swap external successors on the whole external face
                ManualIterator<INode> flip = this.externalFace.iterator(root.getCurrent(),
                        Direction.FWD);

                do {
                    INode current = flip.getCurrent();
                    flip.next();
                    INode successor = this.externalFace.getSuccessor(current, Direction.FWD);
                    INode predecessor = this.externalFace.getSuccessor(current, Direction.REV);
                    this.externalFace.setSuccessor(current, predecessor, Direction.FWD);
                    this.externalFace.setSuccessor(current, successor, Direction.REV);
                } while (flip.hasNext());
            }

            // Remove virtual root from graph
            LinkedList<IEdge> edges = new LinkedList<IEdge>();
            for (IEdge edge : root.getCurrent().adjacentEdges()) {
                switch (node.getDirection()) {
                case FWD:
                    edges.addLast(edge);
                    break;
                case REV:
                    edges.addFirst(edge);
                    break;
                }
            }
            for (IEdge edge : edges) {
                edge.move(root.getCurrent(), node.getCurrent());
                if (node.getDirection() == Direction.REV) {
                    node.getCurrent().moveToStart(edge);
                }
            }
            this.graph.removeNode(root.getCurrent());
            this.separatedChildren[iNode].remove(this.children[iRoot].getFirst());
            this.roots[iNode].remove(root.getCurrent());
            for (Iterator<INode> rs = this.pertinentRoots[iNode].iterator(); rs.hasNext();) {
                if (rs.next() == root.getCurrent()) {
                    rs.remove();
                }
            }

            // Re-link external successors
            Direction dir = node.getDirection();
            Direction opp = (dir == Direction.FWD) ? Direction.REV : Direction.FWD;

            INode nodeOpp = this.externalFace.getSuccessor(node.getCurrent(), opp);
            INode rootDir = this.externalFace.getSuccessor(root.getCurrent(), dir);
            INode rootOpp = this.externalFace.getSuccessor(root.getCurrent(), opp);

            this.externalFace.setSuccessor(rootOpp, node.getCurrent(), dir);
            this.externalFace.setSuccessor(node.getCurrent(), rootOpp, opp);
            this.externalFace.setSuccessor(nodeOpp, rootDir, dir);
            this.externalFace.setSuccessor(rootDir, nodeOpp, opp);
        }
    }

    /**
     * Check if a node is pertinent concerning a check node. A node is pertinent if it has either
     * its back edge flag set to the check node, or his list of pertinent roots is not empty.
     * Virtual roots are never pertinent.
     * 
     * @param node
     *            the node to check
     * @param check
     *            the node to check against
     * @return true if the node is pertinent
     */
    private boolean isPertinent(final INode node, final INode check) {
        // Virtual roots are never pertinent
        if (this.isVirtual[node.getID()]) {
            return false;
        }
        // Node has back edge to check node
        if (!pertinentBackedges[node.getID()].isEmpty()) {
            return true;
        }
        // Node has pertinent roots
        for (INode root : pertinentRoots[node.getID()]) {
            if (pertinentRoots[root.getID()].getFirst() == check) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if a node is externally active concerning a check node. A node is externally active
     * concerning another node, if its least ancestor has a lower DFI than the other node, or the
     * lowpoint of his first child in the list is lower than the DFI of the other node. Virtual
     * roots are never externally active.
     * 
     * @param node
     *            the node to check
     * @param check
     *            the node to check against
     * @return true if the node is externally active
     */
    private boolean isExternallyActive(final INode node, final INode check) {
        if (this.isVirtual[node.getID()]) {
            return false;
        }
        if (ancestor[node.getID()] < dfi[check.getID()]) {
            return true;
        }
        Iterator<INode> iterator = separatedChildren[node.getID()].iterator();
        if (iterator.hasNext() && lowpoint[iterator.next().getID()] < dfi[check.getID()]) {
            return true;
        }
        return false;
    }

    /**
     * Performs a Depth First Search on the graph to clean up. All virtual nodes are merged with
     * their parents and removed; The adjacency list of a node is reversed if the number of flipped
     * flags along the tree path is odd.
     * 
     * @param current
     *            the currently processed node
     * @param parentNode
     *            the previously processed node
     * @param sign
     *            the sign of the tree path up to now]
     */
    private void postProcessing(final INode current, final INode parentNode, final boolean sign) {
        int iCurrent = current.getID();
        this.visited[iCurrent] = current;
        boolean s = sign ^ this.flipped[iCurrent];
        this.flipped[iCurrent] = s;

        // Remove virtual roots from graph
        for (INode root : this.roots[iCurrent]) {
            LinkedList<IEdge> edges = new LinkedList<IEdge>();
            for (IEdge edge : root.adjacentEdges()) {
                edges.add(edge);
            }
            for (IEdge edge : edges) {
                edge.move(root, current);
            }
            this.graph.removeNode(root);
        }

        // Reverse adjacency list if necessary
        if (s) {
            current.mirror();
        }

        // Recurse over all tree children
        for (INode childNode : this.children[iCurrent]) {
            if (this.visited[childNode.getID()] == null) {
                this.postProcessing(childNode, current, s);
            }
        }
    }

}
