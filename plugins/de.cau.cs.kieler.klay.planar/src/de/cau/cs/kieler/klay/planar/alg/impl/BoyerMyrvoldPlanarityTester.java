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

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.klay.planar.alg.IPlanarityTester;
import de.cau.cs.kieler.klay.planar.graph.IEdge;
import de.cau.cs.kieler.klay.planar.graph.IGraph;
import de.cau.cs.kieler.klay.planar.graph.INode;
import de.cau.cs.kieler.klay.planar.graph.InconsistentGraphModelException;
import de.cau.cs.kieler.klay.planar.graph.INode.NodeType;

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
    private LinkedList<Pair<INode, INode>> missingEdges;

    /** The result of the planarity testing algorithm. */
    private boolean planar;

    /** The list of graph nodes in reversed DFI order. */
    private LinkedList<INode> reversedNodes;

    /** The list of roots of DFS trees in the graph. */
    private LinkedList<INode> dfsRoots;

    /** Set to remember to-be-removed edges temporarily. */
    private HashSet<IEdge> tempEdges;

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
     * The back edge flag of every node. Indicates the number of back edges that should be embedded
     * between the node and the currently processed node.
     */
    private int[] backedgeFlag;

    /**
     * The visited flag of every node. A boolean that indicates if a node has been visited by the
     * walkup method. The boolean value is dependent on the currently traversed root node, so it is
     * true concerning the root node, if the value of the visited flag is equal to the root node.
     */
    private INode[] visited;

    /**
     * The clockwise external successor of every node. This is the direct successor on the external
     * face of a node in clockwise direction. This is used to traverse the external face of a
     * biconnected component.
     */
    private INode[] extSuccessorCW;

    /**
     * The counterclockwise external successor of every node. This is the direct successor on the
     * external face of a node in counter- clockwise direction. This is used to traverse the
     * external face of a biconnected component.
     */
    private INode[] extSuccessorCCW;

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
     * The list of neighbors of every node. A neighbor of a node is a node that is connected to the
     * node, but neither the parent nor a child. Hence, the neighbors define where back edges have
     * to be embedded in the tree.
     */
    private LinkedList<INode>[] neighbors;

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

    // ======================== Helper Classes =====================================================

    /**
     * A helper class for traversing the external face of a biconnected component. This class
     * combines a node on the external face with a direction, and can then be used to traverse the
     * external face of a biconnected component in that direction. This class is also able to
     * determine the activity of the traversed node.
     * 
     * @author ocl
     */
    private class FaceIterator implements Iterator<INode>, Cloneable {

        // -------------------------- Constants ----------------------------------------------------

        /** A constant for clockwise direction. */
        public static final boolean CW = true;

        /** A constant for counterclockwise direction. */
        public static final boolean CCW = false;

        // -------------------------- Attributes ---------------------------------------------------

        /** The first node on the external face. */
        private INode start;

        /** The current node on the external face. */
        private INode node;

        /** The direction in which the face is traversed. */
        private boolean direction;

        // -------------------------- Constructor --------------------------------------------------

        /**
         * Create a new face iterator to traverse an external face.
         * 
         * @param n
         *            the starting node for traversal
         * @param dir
         *            the direction of the traversal
         */
        public FaceIterator(final INode n, final boolean dir) {
            this.start = n;
            this.node = n;
            this.direction = dir;
        }

        // ----------------------- Getters and Setters ------------------------

        /**
         * Get the node that is currently traversed.
         * 
         * @return the current node
         */
        public INode getNode() {
            return this.node;
        }

        /**
         * Set the current node of the face runner manually.
         * 
         * @param n
         *            the current node
         */
        public void setNode(final INode n) {
            this.node = n;
        }

        /**
         * Get the next node on the external face. This operation does not change the iterator.
         * 
         * @return the next node
         */
        private INode getNext() {
            int iNode = this.node.getID();
            return this.direction ? extSuccessorCW[iNode] : extSuccessorCCW[iNode];
        }

        /**
         * Get the direction of the face runner.
         * 
         * @return the direction
         */
        public boolean getDirection() {
            return this.direction;
        }

        /**
         * Set the direction of the face runner manually.
         * 
         * @param dir
         *            the direction
         */
        public void setDirection(final boolean dir) {
            this.direction = dir;
        }

        // -------------------------- Iterator Functions -------------------------------------------

        /**
         * Check if the face iterator has reached the start node. Note that this will only check if
         * the currently traversed node is the start node. So this will return false if the iterator
         * has not yet moved to another node. Also, it is possible to continue iterating over the
         * external face after the start node is reached.
         * 
         * @return false if the current node is the start node
         */
        public boolean hasNext() {
            return (this.node != this.start) && (this.getNext() != null);
        }

        /**
         * Move the face iterator to the next node on the external face.
         * 
         * @return the next node
         */
        public INode next() {
            this.node = this.getNext();
            return this.node;
        }

        /**
         * {@inheritDoc}
         */
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

    // ======================== Constructor ========================================================

    /**
     * Default Constructor.
     */
    public BoyerMyrvoldPlanarityTester() {
        super();
        this.missingEdges = new LinkedList<Pair<INode, INode>>();
        this.reversedNodes = new LinkedList<INode>();
        this.dfsRoots = new LinkedList<INode>();
        this.tempEdges = new HashSet<IEdge>();
        this.planar = true;
    }

    @Override
    public void reset() {
        this.graph = null;
        this.missingEdges.clear();
        this.reversedNodes.clear();
        this.dfsRoots.clear();
        this.tempEdges.clear();
        this.planar = true;
    }

    // ======================== Algorithm Interface ================================================

    /**
     * {@inheritDoc} This guarantees to check for graph planarity in time linear to the number of
     * nodes in the graph.
     * 
     * @throws InconsistentGraphModelException
     *             if the graph is inconsistent
     */
    public boolean testPlanarity(final IGraph g) throws InconsistentGraphModelException {
        getMonitor().begin("Planarity Testing", 1);
        this.graph = g;
        this.planarity();
        getMonitor().done();
        return this.planar;
    }

    /**
     * {@inheritDoc} This guarantees to find a planar embedding for a subgraph in time linear to the
     * number of nodes in the graph.
     * 
     * @throws InconsistentGraphModelException
     *             if the graph is inconsistent
     */
    public List<Pair<INode, INode>> planarSubgraph(final IGraph g)
            throws InconsistentGraphModelException {
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
     *            the input graph to test
     * @throws InconsistentGraphModelException
     *             if the graph is inconsistent
     */
    // Initialization of arrays for node properties require unchecked casts
    @SuppressWarnings("unchecked")
    private void planarity() throws InconsistentGraphModelException {
        this.missingEdges.clear();
        this.reversedNodes.clear();
        this.dfsRoots.clear();
        this.tempEdges.clear();

        // Initialize all node properties
        // Reserve enough space for nodes and virtual node
        this.graph.reindex();
        int size = (2 * this.graph.getNodeCount()) + 1;
        this.dfi = new int[size];
        this.flipped = new boolean[size];
        this.lowpoint = new int[size];
        this.ancestor = new int[size];
        this.backedgeFlag = new int[size];
        this.visited = new INode[size];
        this.extSuccessorCW = new INode[size];
        this.extSuccessorCCW = new INode[size];
        this.parent = new INode[size];
        this.children = new LinkedList[size];
        this.separatedChildren = new LinkedHashSet[size];
        this.neighbors = new LinkedList[size];
        this.roots = new LinkedHashSet[size];
        this.pertinentRoots = new LinkedList[size];

        // Find the DFS roots (i.e. nodes in separated connected components)
        for (INode root : this.graph.getNodes()) {
            if (this.visited[root.getID()] == null) {
                this.dfsRoots.add(root);
                this.findRoots(root);
            }
        }

        // Set node properties via DFS
        List<INode>[] buckets = new LinkedList[size];
        int index = 0;
        for (INode root : this.dfsRoots) {
            if (this.children[root.getID()] == null) {
                index = this.preProcessing(root, null, index + 1, buckets);
            }
        }

        // Remove edges
        for (IEdge edge : this.tempEdges) {
            this.graph.removeEdge(edge);
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

            // Embed tree edges
            for (INode root : this.roots[iNode]) {
                INode child = this.children[root.getID()].getFirst();
                this.neighbors[child.getID()].remove(node);
                this.graph.addEdge(root, child);
            }

            // Find pertinent Nodes for all back edges
            for (INode neighbor : this.neighbors[iNode]) {
                if (this.dfi[neighbor.getID()] > this.dfi[iNode]) {
                    this.walkup(node, neighbor);
                }
            }

            // Embed back edges
            for (INode root : this.roots[iNode]) {
                if (this.walkdown(root, FaceIterator.CW)) {
                    this.walkdown(root, FaceIterator.CCW);
                }
            }

            // Check if all edges are embedded
            for (INode neighbor : this.neighbors[iNode]) {
                while (this.backedgeFlag[neighbor.getID()] > 0) {
                    this.missingEdges.add(new Pair<INode, INode>(node, neighbor));
                    this.planar = false;
                    this.backedgeFlag[neighbor.getID()]--;
                }
            }
        }

        // Clean up the graph
        Arrays.fill(this.visited, null);
        for (INode root : this.dfsRoots) {
            if (this.visited[root.getID()] == null) {
                this.postProcessing(root, null, false);
            }
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
        for (INode child : node.getAdjacencyList().adjacentNodes()) {
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
     * @throws InconsistentGraphModelException
     *             if the copied graph turns out to be inconsistent
     */
    private int preProcessing(final INode node, final INode parentNode, final int i,
            final List<INode>[] buckets) throws InconsistentGraphModelException {
        int index = i;
        int iNode = node.getID();
        this.reversedNodes.addFirst(node);

        // Initialize node properties
        this.dfi[iNode] = index;
        this.lowpoint[iNode] = index;
        this.ancestor[iNode] = index;
        this.extSuccessorCW[iNode] = node;
        this.extSuccessorCCW[iNode] = node;
        this.parent[iNode] = parentNode;
        this.separatedChildren[iNode] = new LinkedHashSet<INode>();
        this.children[iNode] = new LinkedList<INode>();
        this.neighbors[iNode] = new LinkedList<INode>();
        this.roots[iNode] = new LinkedHashSet<INode>();
        this.pertinentRoots[iNode] = new LinkedList<INode>();

        // Add a virtual root node as image of the parent node
        INode bicomp = null;
        if (parentNode != null) {
            bicomp = this.graph.addNode(NodeType.OTHER);
            int iParent = parentNode.getID();
            int iBicomp = bicomp.getID();
            this.dfi[iBicomp] = this.dfi[iParent];
            this.lowpoint[iBicomp] = this.lowpoint[iParent];
            this.ancestor[iBicomp] = this.ancestor[iParent];
            this.extSuccessorCW[iBicomp] = node;
            this.extSuccessorCCW[iBicomp] = node;
            this.parent[iBicomp] = parentNode;
            this.children[iBicomp] = new LinkedList<INode>();
            this.neighbors[iBicomp] = new LinkedList<INode>();
            this.roots[iBicomp] = new LinkedHashSet<INode>();
            this.pertinentRoots[iBicomp] = new LinkedList<INode>();

            this.extSuccessorCW[iNode] = bicomp;
            this.extSuccessorCCW[iNode] = bicomp;
            this.children[iParent].add(node);
            this.children[iBicomp].addFirst(node);
            this.roots[iParent].add(bicomp);
        }

        // Recurse over all adjacent nodes
        for (IEdge edge : node.getAdjacencyList().edges()) {
            INode childNode = node.getAdjacencyList().getAdjacentNode(edge);
            int iChild = childNode.getID();

            if (childNode == node) {
                // Found self loop: ignore
                continue;

            } else if (this.children[iChild] != null) {
                // Found already visited node: mark for back edge
                this.neighbors[iNode].add(childNode);

                // Re-Calculate the least ancestor
                if (this.dfi[iChild] < this.ancestor[iNode]) {
                    this.ancestor[iNode] = this.dfi[iChild];
                }

            } else {
                // Found new child node: recurse
                index = this.preProcessing(childNode, node, index + 1, buckets);
            }
            this.tempEdges.add(edge);

            // Re-Calculate the lowpoint
            if (this.lowpoint[iChild] < this.lowpoint[iNode]) {
                this.lowpoint[iNode] = this.lowpoint[iChild];
            }
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
        this.backedgeFlag[childNode.getID()]++;
        FaceIterator left = new FaceIterator(node, FaceIterator.CW);
        left.setNode(childNode);
        FaceIterator right = new FaceIterator(node, FaceIterator.CCW);
        right.setNode(childNode);

        // Iterate over external face
        while (left.hasNext()) {
            int iLeft = left.getNode().getID();
            int iRight = right.getNode().getID();

            // Break on already visited nodes
            if (this.visited[iLeft] == node || this.visited[iRight] == node) {
                break;
            }
            this.visited[iLeft] = node;
            this.visited[iRight] = node;

            // Check if we found a root node
            INode n = null;
            if (left.getNode().getType() == NodeType.OTHER) {
                n = left.getNode();
            } else if (right.getNode().getType() == NodeType.OTHER) {
                n = right.getNode();
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
                left.setNode(parentNode);
                right.setNode(parentNode);

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
     * @return true if the embedding was successful
     * @throws InconsistentGraphModelException
     *             if the graph is inconsistent
     */
    private boolean walkdown(final INode root, final boolean direction)
            throws InconsistentGraphModelException {

        LinkedList<FaceIterator> merge = new LinkedList<FaceIterator>();
        INode node = this.parent[root.getID()];

        // Traverse external face of biconnected component
        FaceIterator iter = new FaceIterator(root, direction);
        iter.next();
        while (iter.hasNext()) {
            int iIter = iter.getNode().getID();

            // Back edge node found, merge
            if (this.backedgeFlag[iIter] > 0) {
                this.mergeBicomp(merge);
                iter.setDirection(direction);
                while (this.backedgeFlag[iIter] > 0) {
                    this.graph.addEdge(root, !direction, iter.getNode(), iter.getDirection());
                    this.backedgeFlag[iIter]--;
                }
                if (iter.getDirection()) {
                    this.extSuccessorCW[root.getID()] = iter.getNode();
                    this.extSuccessorCCW[iIter] = root;
                } else {
                    this.extSuccessorCCW[root.getID()] = iter.getNode();
                    this.extSuccessorCW[iIter] = root;
                }

            } else if (this.isPertinent(iter.getNode(), node)) {
                // Found a new pertinent biconnected component, jump there
                merge.push(iter);

                FaceIterator temp = new FaceIterator(root, FaceIterator.CW);
                for (INode r : this.pertinentRoots[iIter]) {
                    if (this.pertinentRoots[r.getID()].getFirst() == node) {
                        temp.setNode(r);
                        break;
                    }
                }

                FaceIterator left = new FaceIterator(root, FaceIterator.CW);
                left.setNode(temp.getNode());
                do {
                    left.next();
                } while (!isPertinent(left.getNode(), node)
                        && !isExternallyActive(left.getNode(), node));

                FaceIterator right = new FaceIterator(root, FaceIterator.CCW);
                right.setNode(temp.getNode());
                do {
                    right.next();
                } while (!isPertinent(right.getNode(), node)
                        && !isExternallyActive(right.getNode(), node));

                if (this.isPertinent(left.getNode(), node)
                        && !this.isExternallyActive(left.getNode(), node)) {
                    iter = left;
                    temp.setDirection(FaceIterator.CCW);
                } else if (this.isPertinent(right.getNode(), node)
                        && !this.isExternallyActive(right.getNode(), node)) {
                    iter = right;
                    temp.setDirection(FaceIterator.CW);
                } else if (this.isPertinent(left.getNode(), node)) {
                    iter = left;
                    temp.setDirection(FaceIterator.CCW);
                } else {
                    iter = right;
                    temp.setDirection(FaceIterator.CW);
                }

                merge.push(temp);

            } else if (!this.isPertinent(iter.getNode(), root)
                    && !this.isExternallyActive(iter.getNode(), node)) {
                // Traverse to next vertex
                iter.next();

            } else {
                // Node is stopping vertex: stop
                int iChild = this.children[root.getID()].getFirst().getID();
                int iParent = this.parent[root.getID()].getID();
                if (this.lowpoint[iChild] < this.dfi[iParent] && merge.isEmpty()) {
                    if (iter.getDirection()) {
                        this.extSuccessorCW[root.getID()] = iter.getNode();
                        this.extSuccessorCCW[iIter] = root;
                    } else {
                        this.extSuccessorCCW[root.getID()] = iter.getNode();
                        this.extSuccessorCW[iIter] = root;
                    }
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
     * @param direction
     *            the direction in which the backedge will be embedded
     * @throws InconsistentGraphModelException
     *             if the edges in the graph are not correctly linked
     */
    private void mergeBicomp(final LinkedList<FaceIterator> stack)
            throws InconsistentGraphModelException {
        while (!stack.isEmpty()) {
            // The virtual root of node
            FaceIterator root = stack.pop();
            int iRoot = root.getNode().getID();
            // The pertinent node on the external face
            FaceIterator node = stack.pop();
            int iNode = node.getNode().getID();

            // Flipping
            if (root.getDirection() == node.getDirection()) {
                // Invert the adjacency list of the root node and mark bicomp as flipped
                root.getNode().getAdjacencyList().mirror();
                int iChild = this.children[iRoot].getFirst().getID();
                this.flipped[iChild] = !this.flipped[iChild];

                // Swap external successors on the whole external face
                FaceIterator flip = new FaceIterator(root.getNode(), FaceIterator.CW);

                do {
                    int i = flip.getNode().getID();
                    flip.next();
                    INode temp = this.extSuccessorCW[i];
                    this.extSuccessorCW[i] = this.extSuccessorCCW[i];
                    this.extSuccessorCCW[i] = temp;

                } while (flip.hasNext());
            }

            // Remove virtual root from graph
            node.getNode().merge(root.getNode(), node.getDirection());
            this.separatedChildren[iNode].remove(this.children[iRoot].getFirst());
            this.roots[iNode].remove(root.getNode());
            for (Iterator<INode> rs = this.pertinentRoots[iNode].iterator(); rs.hasNext();) {
                if (rs.next() == root.getNode()) {
                    rs.remove();
                }
            }

            // Re-link external successors
            INode rootCW = this.extSuccessorCW[iRoot];
            INode rootCCW = this.extSuccessorCCW[iRoot];
            INode nodeCW = this.extSuccessorCW[iNode];
            INode nodeCCW = this.extSuccessorCCW[iNode];
            if (node.getDirection()) {
                this.extSuccessorCCW[iNode] = rootCCW;
                this.extSuccessorCW[rootCCW.getID()] = node.getNode();
                this.extSuccessorCCW[rootCW.getID()] = nodeCCW;
                this.extSuccessorCW[nodeCCW.getID()] = rootCW;
            } else {
                this.extSuccessorCW[iNode] = rootCW;
                this.extSuccessorCCW[rootCW.getID()] = node.getNode();
                this.extSuccessorCW[rootCCW.getID()] = nodeCW;
                this.extSuccessorCCW[nodeCW.getID()] = rootCCW;
            }
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
        if (node.getType() == NodeType.OTHER) {
            return false;
        }
        // Node has backedge to check node
        if (backedgeFlag[node.getID()] > 0) {
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
        if (node.getType() == NodeType.OTHER) {
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
     *            the sign of the tree path up to now
     * @throws InconsistentGraphModelException
     *             if the graph turns out to be inconsistent
     */
    private void postProcessing(final INode current, final INode parentNode, final boolean sign)
            throws InconsistentGraphModelException {
        int iCurrent = current.getID();
        this.visited[iCurrent] = current;
        boolean s = sign ^ this.flipped[iCurrent];
        this.flipped[iCurrent] = s;

        // Remove virtual roots from graph
        for (INode root : this.roots[iCurrent]) {
            current.merge(root, true);
        }

        // Reverse adjacency list if necessary
        if (s) {
            current.getAdjacencyList().mirror();
        }

        // Recurse over all tree children
        for (INode childNode : this.children[iCurrent]) {
            if (this.visited[childNode.getID()] == null) {
                this.postProcessing(childNode, current, s);
            }
        }
    }

}
