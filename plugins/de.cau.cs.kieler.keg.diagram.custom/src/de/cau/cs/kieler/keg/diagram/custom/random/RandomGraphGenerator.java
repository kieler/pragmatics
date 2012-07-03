/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.keg.diagram.custom.random;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.properties.IPropertyHolder;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.keg.Edge;
import de.cau.cs.kieler.keg.EdgeType;
import de.cau.cs.kieler.keg.KEGFactory;
import de.cau.cs.kieler.keg.Node;
import de.cau.cs.kieler.keg.Port;

/**
 * The basic KEG random graph generator.
 * 
 * @author mri
 */
public class RandomGraphGenerator implements IRandomGraphGenerator {

    /** the possible graph types. */
    public static enum GraphType {
        /** any graph. */
        ANY,
        /** a tree. */
        TREE,
        /** a biconnected graph. */
        BICONNECTED,
        /** a triconnected graph. */
        TRICONNECTED,
        /** an acyclic graph without transitiv edges. */
        ACYCLIC_NO_TRANSITIV_EDGES
    }

    /** the possible ways to determine edges. */
    public enum EdgeDetermination {
        /** number of edges in the graph. */
        GRAPH_EDGES,
        /** number of outgoing edges per node. */
        OUTGOING_EDGES
    }

    // All other options depend on which GRAPH_TYPE has been selected

    /** the option for the graph type. */
    public static final Property<GraphType> GRAPH_TYPE = new Property<GraphType>("basic.graphType",
            GraphType.ANY);

    // Options for all graph types

    /** the option for the number of nodes. */
    public static final Property<Integer> NUMBER_OF_NODES = new Property<Integer>(
            "basic.numberOfNodes", 1, 1);
    /** the option for the chance of creating a compound node. */
    public static final Property<Float> HIERARCHY_CHANCE = new Property<Float>(
            "basic.hierarchyChance", 0.0f, 0.0f, 1.0f);
    /** the option for the maximum hierarchy level. */
    public static final Property<Integer> MAX_HIERARCHY_LEVEL = new Property<Integer>(
            "basic.maxHierarchyLevel", 3);
    /** the option for the factor to calculate the number of nodes in a compound node. */
    public static final Property<Float> HIERARCHY_NODES_FACTOR = new Property<Float>(
            "basic.hierarchyNodesFactor", 0.5f, 0.0f);
    /** the option for the chance of creating a hypernode. */
    public static final Property<Float> HYPERNODE_CHANCE = new Property<Float>(
            "basic.hypernodeChance", 0.0f, 0.0f, 1.0f);
    /** the option for the chance of generating a directed edge. */
    public static final Property<Float> EDGE_DIRECTED_CHANCE = new Property<Float>(
            "basic.edgeDirectedChance", 0.0f);
    /** the option for using ports to connect nodes. */
    public static final Property<Boolean> PORTS = new Property<Boolean>("basic.ports", false);
    /** the option for allowing cross-hierarchy edges. */
    public static final Property<Boolean> CROSS_HIERARCHY_EDGES = new Property<Boolean>(
            "basic.crossHierarchyEdges", false);

    // Options for GRAPH_TYPE ANY

    /** the option for specifying how to determine edges. */
    public static final Property<EdgeDetermination> EDGE_DETERMINATION =
            new Property<EdgeDetermination>("basic.edgeDetermination",
                    EdgeDetermination.GRAPH_EDGES);
    /** the option for the minimum number of outgoing edges. */
    public static final Property<Integer> MIN_OUTGOING_EDGES = new Property<Integer>(
            "basic.minOutgoingEdges", 0, 0);
    /** the option for the maximum number of outgoing edges. */
    public static final Property<Integer> MAX_OUTGOING_EDGES = new Property<Integer>(
            "basic.maxOutgoingEdges", 0, 0);
    /** the option for allowing self-loops. */
    public static final Property<Boolean> SELF_LOOPS = new Property<Boolean>("basic.selfLoops",
            true);
    /** the option for allowing multi-edges. */
    public static final Property<Boolean> MULTI_EDGES = new Property<Boolean>("basic.multiEdges",
            true);
    /** the option for allowing cycles. */
    public static final Property<Boolean> CYCLES = new Property<Boolean>("basic.cycles", true);

    // Options for GRAPH_TYPE TREE

    /** the option for the maximum tree width ('0' meaning unlimited). */
    public static final Property<Integer> MAX_WIDTH = new Property<Integer>("basic.maxWidth", 0);
    /** the option for the maximum degree ('0' meaning unlimited). */
    public static final Property<Integer> MAX_DEGREE = new Property<Integer>("basic.maxDegree", 0);

    // Options for GRAPH_TYPE ACYCLIC_NO_TRANSITIV_EDGES

    /** the option for planarity. */
    public static final Property<Boolean> PLANAR = new Property<Boolean>("basic.planar", false);

    // Options for GRAPH_TYPE ANY, BICONNECTED and ACYCLIC_NO_TRANSITIV_EDGES

    /** the option for the number of edges. */
    public static final Property<Integer> NUMBER_OF_EDGES = new Property<Integer>(
            "basic.numberOfEdges", 0, 0);

    // Unused

    // /** the option for long-edges. */
    // public static final Property<Boolean> LONG_EDGES = new Property<Boolean>("basic.longEdges",
    // true);

    /** the KEG factory. */
    private static KEGFactory factory = KEGFactory.eINSTANCE;

    /** the label counter used to generate labels. */
    private int labelCounter = 0;
    /** the chance for creating a compound node. */
    private float hierarchyChance;
    /** the maximum hierarchy level. */
    private int maxHierarchyLevel;
    /** the hierarchy nodes factor. */
    private float hierarchyNodesFactor;
    /** the chance for a hypernode to be created instead of a normal one. */
    private float hypernodeChance;
    /** whether to allow cross-hierarchy edges. */
    private boolean crossHierarchyEdges;
    /** whether to use ports to connect nodes. */
    private boolean ports;
    /** the chance for creating a directed edge. */
    private float edgeDirectedChance;
    /** whether to allow self-loops. */
    private boolean selfLoops;
    /** whether to allow multi-edges. */
    private boolean multiEdges;
    /** whether to allow cycles. */
    private boolean cycles;

    /**
     * {@inheritDoc}
     */
    public Node generate(final IPropertyHolder options) {
        // reset the generator
        labelCounter = 0;
        // initialize some options
        hierarchyChance = options.getProperty(HIERARCHY_CHANCE);
        maxHierarchyLevel = options.getProperty(MAX_HIERARCHY_LEVEL);
        hierarchyNodesFactor = options.getProperty(HIERARCHY_NODES_FACTOR);
        hypernodeChance = options.getProperty(HYPERNODE_CHANCE);
        crossHierarchyEdges = options.getProperty(CROSS_HIERARCHY_EDGES);
        ports = options.getProperty(PORTS);
        edgeDirectedChance = options.getProperty(EDGE_DIRECTED_CHANCE);
        selfLoops = options.getProperty(SELF_LOOPS);
        multiEdges = options.getProperty(MULTI_EDGES);
        cycles = options.getProperty(CYCLES);
        // generate the graph
        Node graph = factory.createNode();
        int n = options.getProperty(NUMBER_OF_NODES);
        
        switch (options.getProperty(GRAPH_TYPE)) {
        case ANY: {
            int m = options.getProperty(NUMBER_OF_EDGES);
            int minOut = options.getProperty(MIN_OUTGOING_EDGES);
            int maxOut = options.getProperty(MAX_OUTGOING_EDGES);
            switch (options.getProperty(EDGE_DETERMINATION)) {
            case GRAPH_EDGES:
                createAnyGraph(graph, n, m, 0);
                break;
            case OUTGOING_EDGES:
                createAnyGraph(graph, n, minOut, maxOut, 0);
                break;
            }
            if (crossHierarchyEdges) {
                // collect all created nodes and create edges arbitrarily
                List<Node> nodes = new LinkedList<Node>();
                LinkedList<KNode> nodeStack = new LinkedList<KNode>();
                nodeStack.add(graph);
                do {
                    KNode node = nodeStack.pop();
                    nodes.add((Node) node);
                    for (KNode child : node.getChildren()) {
                        nodeStack.push(child);
                    }
                } while (!nodeStack.isEmpty());
                
                int[] outgoingEdges;
                switch (options.getProperty(EDGE_DETERMINATION)) {
                case GRAPH_EDGES:
                    outgoingEdges = determineOutgoingEdges(nodes, m);
                    connectRandomlyAndConditional(nodes, outgoingEdges, basicCondition);
                    break;
                case OUTGOING_EDGES:
                    outgoingEdges = determineOutgoingEdges(nodes, minOut, maxOut);
                    connectRandomlyAndConditional(nodes, outgoingEdges, basicCondition);
                    break;
                }
            }
            break;
        }
        
        case TREE: {
            int maxDegree = options.getProperty(MAX_DEGREE);
            int maxWidth = options.getProperty(MAX_WIDTH);
            createTree(graph, n, maxDegree, maxWidth, 0);
            break;
        }
        
        case BICONNECTED: {
            int m = options.getProperty(NUMBER_OF_EDGES);
            createBiconnectedGraph(graph, n, m, 0);
            break;
        }
        
        case TRICONNECTED: {
            float p1 = (float) Math.random();
            float p2 = 1.0f - p1;
            createTriconnectedGraph(graph, n, p1, p2, 0);
            break;
        }
        
        case ACYCLIC_NO_TRANSITIV_EDGES: {
            int m = options.getProperty(NUMBER_OF_EDGES);
            boolean planar = options.getProperty(PLANAR);
            // FIXME: long edges option causes algorithm failure
            // boolean longEdges = options.getProperty(LONG_EDGES);
            createANTEGraph(graph, n, m, planar, false, false, 0);
            break;
        }
        
        }
        return graph;
    }

    /** the basic condition which cares for self-loops, multi-edges and cycles. */
    private final EdgeCondition basicCondition = new EdgeCondition() {
        public boolean evaluate(final Node node1, final Node node2, final boolean directed) {
            if (!selfLoops && node1 == node2) {
                return false;
            }
            if (!multiEdges && connected(node1, node2)) {
                return false;
            }
            if (!cycles && edgeCausesCycle(node1, node2, directed)) {
                return false;
            }
            return true;
        }
    };

    /**
     * Generates a random graph.
     * 
     * @param parent
     *            the parent node
     * @param n
     *            the number of nodes
     * @param m
     *            the number of edges
     * @param hierarchyLevel
     *            the current hierarchy level
     */
    private void createAnyGraph(final KNode parent, final int n, final int m,
            final int hierarchyLevel) {
        // create the nodes
        List<Node> nodes = createIndependentSet(parent, n);
        // determine the number of outgoing edges for every node
        int[] outgoingEdges = determineOutgoingEdges(nodes, m);
        // connect the nodes
        if (!crossHierarchyEdges) {
            connectRandomlyAndConditional(nodes, outgoingEdges, basicCondition);
        }
        // recursively create hierarchy if applicable
        if (hierarchyChance > 0.0f && hierarchyLevel != maxHierarchyLevel) {
            for (Node node : nodes) {
                if (!node.isHypernode() && Math.random() < hierarchyChance) {
                    // determine the number of nodes in the compound node
                    int cn = randomInt(1, (int) (hierarchyNodesFactor * n));
                    // preserve density for number of edges
                    float density = (float) m / (n * n);
                    int cm = (int) density * cn * cn;
                    createAnyGraph(node, cn, cm, hierarchyLevel + 1);
                }
            }
        }
    }

    /**
     * Generates a random graph.
     * 
     * @param parent
     *            the parent node
     * @param n
     *            the number of nodes
     * @param minOut
     *            the minimum number of outgoing edges per node
     * @param maxOut
     *            the maximum number of outgoing edges per node
     * @param hierarchyLevel
     *            the current hierarchy level
     */
    private void createAnyGraph(final KNode parent, final int n, final int minOut,
            final int maxOut, final int hierarchyLevel) {
        // create the nodes
        List<Node> nodes = createIndependentSet(parent, n);
        // determine the number of outgoing edges for every node
        int[] outgoingEdges = determineOutgoingEdges(nodes, minOut, maxOut);
        // connect the nodes
        if (!crossHierarchyEdges) {
            connectRandomlyAndConditional(nodes, outgoingEdges, basicCondition);
        }
        // recursively create hierarchy if applicable
        if (hierarchyChance > 0.0f && hierarchyLevel != maxHierarchyLevel) {
            for (Node node : nodes) {
                if (!node.isHypernode() && Math.random() < hierarchyChance) {
                    // determine the number of nodes in the compound node
                    int cn = randomInt(1, (int) (hierarchyNodesFactor * n));
                    createAnyGraph(node, cn, minOut, maxOut, hierarchyLevel + 1);
                }
            }
        }
    }

    /**
     * Generates a random tree. The implementation is based upon the one used in the OGDF library.
     * 
     * @param parent
     *            the parent node
     * @param n
     *            the number of nodes
     * @param maxDeg
     *            the maximum degree
     * @param maxWidth
     *            the maximum width
     * @param hierarchyLevel
     *            the current hierarchy level
     */
    private void createTree(final KNode parent, final int n, final int maxDeg, final int maxWidth,
            final int hierarchyLevel) {
        int max = 0;
        int nodeIdCounter = 0;
        @SuppressWarnings("unchecked")
        Pair<Node, Integer>[] possible = (Pair<Node, Integer>[]) new Pair[n];
        int[] width = new int[n + 1];
        int[] level = new int[n];
        // create the root node
        Node rootNode = createNode(parent);
        int rootId = nodeIdCounter++;
        possible[0] = new Pair<Node, Integer>(rootNode, rootId);
        level[rootId] = 0;
        // create the tree
        for (int i = 1; i < n;) {
            // get the node to append to
            int x = randomInt(0, max);
            Pair<Node, Integer> nodeInfo = possible[x];
            Node node = nodeInfo.getFirst();
            int nodeId = nodeInfo.getSecond();
            // check for the width contraint
            if (maxWidth != 0 && width[level[nodeId] + 1] == maxWidth) {
                possible[x] = possible[max--];
                continue;
            }
            // check for the out-degree contraint
            if (maxDeg != 0 && node.getOutgoingEdges().size() + 1 == maxDeg) {
                possible[x] = possible[max--];
            }
            // append a new node
            Node newNode = createNode(parent);
            int newNodeId = nodeIdCounter++;
            possible[++max] = new Pair<Node, Integer>(newNode, newNodeId);
            connect(node, newNode);
            level[newNodeId] = level[nodeId] + 1;
            ++width[level[newNodeId]];
            ++i;
        }
        // recursively create hierarchy if applicable
        if (hierarchyChance > 0.0f && hierarchyLevel != maxHierarchyLevel) {
            for (KNode knode : parent.getChildren()) {
                Node node = (Node) knode;
                if (!node.isHypernode() && Math.random() < hierarchyChance) {
                    // determine the number of nodes in the compound node
                    int cn = randomInt(1, (int) (hierarchyNodesFactor * n));
                    createTree(node, cn, maxDeg, maxWidth, hierarchyLevel + 1);
                }
            }
        }
    }

    /**
     * Generates a biconnected graph. The implementation is based upon the one used in the OGDF
     * library.
     * 
     * @param parent
     *            the parent node
     * @param n
     *            the number of nodes
     * @param m
     *            the number of edges
     * @param hierarchyLevel
     *            the current hierarchy level
     */
    // CHECKSTYLEOFF MagicNumber
    private void createBiconnectedGraph(final KNode parent, final int n, final int m,
            final int hierarchyLevel) {
        int realN = Math.max(3, n);
        int realM = Math.max(m, realN);
        // the number of split-edge operations
        int kse = realN - 3;
        // the number of add-edge operations
        int kae = realM - realN;
        Node[] nodes = new Node[realN];
        Edge[] edges = new Edge[realM];
        // start with a triangle
        nodes[0] = createNode(parent);
        nodes[1] = createNode(parent);
        nodes[2] = createNode(parent);
        edges[0] = connect(nodes[0], nodes[1]);
        edges[1] = connect(nodes[1], nodes[2]);
        edges[2] = connect(nodes[2], nodes[0]);
        int nNodes = 3;
        int nEdges = 3;
        // generate the graph
        while (kse + kae > 0) {
            int p = randomInt(1, kse + kae);
            if (p <= kse) {
                // split edge
                Edge edge = edges[randomInt(0, nEdges - 1)];
                Pair<Node, Edge> splitInfo = split(edge);
                nodes[nNodes++] = splitInfo.getFirst();
                edges[nEdges++] = splitInfo.getSecond();
                --kse;
            } else {
                // add edge
                int i = randomInt(0, nNodes - 1);
                int j = (i + randomInt(1, nNodes - 1)) % nNodes;
                edges[nEdges++] = connect(nodes[i], nodes[j]);
                --kae;
            }
        }
        // recursively create hierarchy if applicable
        if (hierarchyChance > 0.0f && hierarchyLevel != maxHierarchyLevel) {
            for (Node node : nodes) {
                if (!node.isHypernode() && Math.random() < hierarchyChance) {
                    // determine the number of nodes in the compound node
                    int cn = randomInt(1, (int) (hierarchyNodesFactor * n));
                    // preserve density for number of edges
                    float density = (float) m / (n * n);
                    int cm = (int) density * cn * cn;
                    createBiconnectedGraph(node, cn, cm, hierarchyLevel + 1);
                }
            }
        }
    }

    // CHECKSTYLEON MagicNumber

    /**
     * Generates a triconnected graph. The implementation is based upon the one used in the OGDF
     * library.
     * 
     * @param parent
     *            the parent node
     * @param n
     *            the number of nodes
     * @param p1
     *            the probability for the first additional edge to be added
     * @param p2
     *            the probability for the second additional edge to be added
     * @param hierarchyLevel
     *            the current hierarchy level
     */
    // CHECKSTYLEOFF MagicNumber
    private void createTriconnectedGraph(final KNode parent, final int n, final float p1,
            final float p2, final int hierarchyLevel) {
        int realN = Math.max(n, 4);
        // start with a clique of size 4
        List<Node> cliqueNodes = createClique(parent, 4);
        // array of all nodes
        Node[] nodes = new Node[realN];
        int i = 0;
        for (Node node : cliqueNodes) {
            nodes[i++] = node;
        }
        // array of neighbors
        Edge[] neighbors = new Edge[realN];
        // neighbor markings
        // 0 = not marked
        // 1 = marked left
        // 2 = marked right
        // 3 = marked both
        int[] marks = new int[n];
        // generate the graph
        for (; i < n; ++i) {
            Node node = nodes[randomInt(0, i - 1)];
            // create a new node to split 'node' in two
            Node newNode = createNode(parent);
            nodes[i] = newNode;
            // build array of all neighbors
            int d = node.getOutgoingEdges().size() + node.getIncomingEdges().size();
            int j = 0;
            for (KEdge edge : node.getOutgoingEdges()) {
                neighbors[j++] = (Edge) edge;
            }
            for (KEdge edge : node.getIncomingEdges()) {
                neighbors[j++] = (Edge) edge;
            }
            // mark two distinct neighbors for left
            for (j = 2; j > 0;) {
                int r = randomInt(0, d - 1);
                if ((marks[r] & 1) == 0) {
                    marks[r] |= 1;
                    --j;
                }
            }
            // mark two distinct neighbors for right
            for (j = 2; j > 0;) {
                int r = randomInt(0, d - 1);
                if ((marks[r] & 2) == 0) {
                    marks[r] |= 2;
                    --j;
                }
            }
            // perform the node-split
            for (j = 0; j < d; ++j) {
                int mark = marks[j];
                marks[j] = 0;
                // decide to with which node each neighbor is connected
                double x = Math.random();
                switch (mark) {
                case 0:
                    if (x < p1) {
                        mark = 1;
                    } else if (x < p1 + p2) {
                        mark = 2;
                    } else {
                        mark = 3;
                    }
                    break;
                case 1:
                case 2:
                    if (x >= p1 + p2) {
                        mark = 3;
                    }
                    break;
                }
                // move edge or create new one if necessary
                Edge edge = neighbors[j];
                switch (mark) {
                case 2:
                    if (node == edge.getSource()) {
                        moveSource(edge, newNode);
                    } else {
                        moveTarget(edge, newNode);
                    }
                    break;
                case 3:
                    if (node == edge.getSource()) {
                        connect(newNode, (Node) edge.getTarget());
                    } else {
                        connect(newNode, (Node) edge.getSource());
                    }
                    break;
                }
            }
            connect(node, newNode);
        }
        // recursively create hierarchy if applicable
        if (hierarchyChance > 0.0f && hierarchyLevel != maxHierarchyLevel) {
            for (KNode knode : parent.getChildren()) {
                Node node = (Node) knode;
                if (!node.isHypernode() && Math.random() < hierarchyChance) {
                    // determine the number of nodes in the compound node
                    int cn = randomInt(1, (int) (hierarchyNodesFactor * n));
                    createTriconnectedGraph(node, cn, p1, p2, hierarchyLevel + 1);
                }
            }
        }
    }

    // CHECKSTYLEON MagicNumber

    /**
     * Generates an acyclic graph without transitiv edges. The implementation is based upon the one
     * used in the OGDF library.
     * 
     * @param parent
     *            the parent node
     * @param n
     *            the number of nodes
     * @param m
     *            the number of edges
     * @param planar
     *            whether the generated graph should be planar
     * @param singleSource
     *            whether the graph is a single source graph
     * @param longEdges
     *            whether to allow transitiv edges (this causes the algorithm to fail)
     * @param hierarchyLevel
     *            the current hierarchy level
     */
    // CHECKSTYLEOFF MagicNumber
    private void createANTEGraph(final KNode parent, final int n, final int m,
            final boolean planar, final boolean singleSource, final boolean longEdges,
            final int hierarchyLevel) {
        KNode[] nnr = new KNode[3 * n];
        int[] vrt = new int[3 * n];
        int[] fst = new int[n + 1];
        List<HierarchyEdge> startEdges = new LinkedList<HierarchyEdge>();
        HierarchyEdge actEdge, nextEdge;
        int act, next, n1, n2, idc = 0;
        boolean connected;
        // create the nodes
        for (int i = 0; i < n; ++i) {
            createNode(parent);
        }
        int numberOfLayers = 0, totNumber = 0, realCount = 0;
        fst[0] = 0;
        for (KNode node : parent.getChildren()) {
            if (longEdges && numberOfLayers != 0) {
                vrt[totNumber++] = 1;
            }
            nnr[totNumber] = node;
            vrt[totNumber++] = 0;
            realCount++;
            float r = (float) Math.random();
            if (totNumber == 1 && singleSource || realCount == n || r * r * n < 1) {
                if (longEdges && numberOfLayers != 0) {
                    vrt[totNumber++] = 1;
                }
                fst[++numberOfLayers] = totNumber;
            }
        }
        // determine allowed neighbours
        int[] leftN = new int[totNumber];
        int[] rightN = new int[totNumber];
        for (int l = 1; l < numberOfLayers; l++) {
            if (planar) {
                n1 = fst[l - 1];
                n2 = fst[l];
                leftN[n2] = n1;
                while (n1 < fst[l] && n2 < fst[l + 1]) {
                    float r = (float) Math.random();
                    if (n1 != fst[l] - 1
                            && (n2 == fst[l + 1] - 1 || r < (float) (fst[l] - fst[l - 1])
                                    / (float) (fst[l + 1] - fst[l - 1]))) {
                        n1++;
                    } else {
                        rightN[n2] = n1;
                        if (++n2 < fst[l + 1]) {
                            leftN[n2] = n1;
                        }
                    }
                }
            } else {
                for (n2 = fst[l]; n2 < fst[l + 1]; n2++) {
                    leftN[n2] = fst[l - 1];
                    rightN[n2] = fst[l] - 1;
                }
            }
        }
        // insert edges
        @SuppressWarnings("unchecked")
        List<HierarchyEdge>[] edgeIn = new LinkedList[totNumber];
        @SuppressWarnings("unchecked")
        List<HierarchyEdge>[] edgeOut = new LinkedList[totNumber];
        for (int i = 0; i < totNumber; ++i) {
            edgeIn[i] = new LinkedList<HierarchyEdge>();
            edgeOut[i] = new LinkedList<HierarchyEdge>();
        }
        if (numberOfLayers != 0) {
            float x1 = m;
            float x2 = 0;
            for (n2 = fst[1]; n2 < totNumber; n2++) {
                if (vrt[n2] == 0) {
                    x2 += rightN[n2] - leftN[n2] + 1;
                }
            }
            for (n2 = fst[1]; n2 < totNumber; n2++) {
                if (vrt[n2] == 0) {
                    connected = !singleSource;
                    for (n1 = leftN[n2]; n1 <= rightN[n2] || !connected; n1++) {
                        float r = (float) Math.random();
                        if (r < x1 / x2 || n1 > rightN[n2]) {
                            next = (n1 <= rightN[n2] ? n1 : randomInt(leftN[n2], rightN[n2]));
                            act = n2;
                            nextEdge = new HierarchyEdge(next, act, idc++);
                            while (vrt[next] != 0) {
                                act = next;
                                next = randomInt(leftN[act], rightN[act]);
                                edgeOut[act].add(nextEdge);
                                nextEdge = new HierarchyEdge(next, act, idc++);
                                edgeIn[act].add(nextEdge);
                            }
                            startEdges.add(nextEdge);
                            connected = true;
                            x1 -= 1;
                        }
                        if (n1 <= rightN[n2]) {
                            x2 -= 1;
                        }
                    }
                }
            }
        }
        if (planar) {
            for (act = 0; act < totNumber; act++) {
                Collections.sort(edgeIn[act], new TailComparator());
                Collections.sort(edgeOut[act], new HeadComparator());
            }
        }
        for (act = 0; act < totNumber; act++) {
            List<HierarchyEdge> hedges = edgeIn[act];
            for (HierarchyEdge hedge : hedges) {
                nextEdge = hedge;
                nextEdge.setNext(edgeOut[act].remove(0));
            }
        }
        for (HierarchyEdge hedge : startEdges) {
            actEdge = hedge;
            nextEdge = actEdge;
            while (vrt[nextEdge.getHead()] != 0) {
                nextEdge = nextEdge.getNext();
            }
            connect((Node) nnr[actEdge.getTail()], (Node) nnr[nextEdge.getHead()]);
        }
        // recursively create hierarchy if applicable
        if (hierarchyChance > 0.0f && hierarchyLevel != maxHierarchyLevel) {
            for (KNode knode : parent.getChildren()) {
                Node node = (Node) knode;
                if (!node.isHypernode() && Math.random() < hierarchyChance) {
                    // determine the number of nodes in the compound node
                    int cn = randomInt(1, (int) (hierarchyNodesFactor * n));
                    // preserve density for number of edges
                    float density = (float) m / (n * n);
                    int cm = (int) density * cn * cn;
                    createBiconnectedGraph(node, cn, cm, hierarchyLevel + 1);
                }
            }
        }
    }

    // CHECKSTYLEON MagicNumber

    /**
     * A helper class for creating hierarchical graphs.
     */
    private static class HierarchyEdge {

        /** the head, tail and id. */
        private int head, tail, id;

        /** the next edge. */
        private HierarchyEdge next;

        /**
         * Constructs a HierarchyEdge.
         * 
         * @param head
         *            the head
         * @param tail
         *            the tail
         * @param id
         *            the id
         */
        public HierarchyEdge(final int head, final int tail, final int id) {
            this.head = head;
            this.tail = tail;
            this.id = id;
        }

        /**
         * Returns the head.
         * 
         * @return the head
         */
        public int getHead() {
            return head;
        }

        /**
         * Returns the tail.
         * 
         * @return the tail
         */
        public int getTail() {
            return tail;
        }

        /**
         * Returns the id.
         * 
         * @return the id
         */
        public int getId() {
            return id;
        }

        /**
         * Returns the next edge.
         * 
         * @return the next edge
         */
        public HierarchyEdge getNext() {
            return next;
        }

        /**
         * Sets the next edge.
         * 
         * @param next
         *            the next edge
         */
        public void setNext(final HierarchyEdge next) {
            this.next = next;
        }
    }

    /**
     * Compares hierarchy edges by the id attribute.
     * 
     * @param edge1
     *            the first edge
     * @param edge2
     *            the second edge
     * @return the result of the comparison
     */
    private static int compareId(final HierarchyEdge edge1, final HierarchyEdge edge2) {
        return edge1.getId() < edge2.getId() ? -1 : (edge1.getId() > edge2.getId() ? 1 : 0);
    }

    /**
     * A helper class for comparing hierarchy edges by the head attribute.
     */
    private static class HeadComparator implements Comparator<HierarchyEdge> {

        /**
         * {@inheritDoc}
         */
        public int compare(final HierarchyEdge edge1, final HierarchyEdge edge2) {
            return edge1.getHead() < edge2.getHead() ? -1 : (edge1.getHead() > edge2.getHead() ? 1
                    : compareId(edge1, edge2));
        }
    }

    /**
     * A helper class for comparing hierarchy edges by the tail attribute.
     */
    private static class TailComparator implements Comparator<HierarchyEdge> {

        /**
         * {@inheritDoc}
         */
        public int compare(final HierarchyEdge edge1, final HierarchyEdge edge2) {
            return edge1.getTail() < edge2.getTail() ? -1 : (edge1.getTail() > edge2.getTail() ? 1
                    : compareId(edge1, edge2));
        }
    }

    /**
     * Creates an independent set of nodes.
     * 
     * @param parent
     *            the parent node
     * @param n
     *            the number of nodes
     * @return the list of created nodes
     */
    private List<Node> createIndependentSet(final KNode parent, final int n) {
        List<Node> nodes = new ArrayList<Node>(n);
        for (int i = 0; i < n; ++i) {
            Node node = createNode(parent);
            nodes.add(node);
        }
        return nodes;
    }

    /**
     * Creates a clique.
     * 
     * @param parent
     *            the parent node
     * @param n
     *            the number of nodes
     * @return the list of created nodes
     */
    private List<Node> createClique(final KNode parent, final int n) {
        List<Node> nodes = createIndependentSet(parent, n);
        for (int i = 0; i < n - 1; ++i) {
            for (int j = i + 1; j < n; ++j) {
                connect(nodes.get(i), nodes.get(j));
            }
        }
        return nodes;
    }

    /**
     * Creates a node.
     * 
     * @param parent
     *            the parent node
     * @return the node
     */
    private Node createNode(final KNode parent) {
        Node node = factory.createNode();
        if (hypernodeChance > 0.0f && Math.random() < hypernodeChance) {
            node.setHypernode(true);
        } else {
            // hypernodes are not labeled
            node.setNodeLabel(generateLabel());
        }
        parent.getChildren().add(node);
        return node;
    }

    /**
     * Connects two nodes with an edge.
     * 
     * @param source
     *            the source node
     * @param target
     *            the target node
     * @param directed
     *            whether the edge should be directed or undirected
     * @return the edge
     */
    private Edge connect(final Node source, final Node target, final boolean directed) {
        Edge edge = factory.createEdge();
        edge.setSource(source);
        edge.setTarget(target);
        edge.setDirected(directed);
        if (ports) {
            if (!source.isHypernode()) {
                Port sourcePort = factory.createPort();
                source.getPorts().add(sourcePort);
                edge.setSourcePort(sourcePort);
                sourcePort.getEdges().add(edge);
            }
            if (!target.isHypernode()) {
                Port targetPort = factory.createPort();
                target.getPorts().add(targetPort);
                edge.setTargetPort(targetPort);
                targetPort.getEdges().add(edge);
            }
            // set the correct edge type
            edge.setType(determineEdgeType(source, target));
        } else {
            edge.setType(EdgeType.NODE2_NODE);
        }
        return edge;
    }

    /**
     * Connects two nodes with an edge. The edge is of a random direction.
     * 
     * @param source
     *            the source node
     * @param target
     *            the target node
     * @return the edge
     */
    private Edge connect(final Node source, final Node target) {
        boolean directed;
        if (edgeDirectedChance > 0.0f) {
            if (edgeDirectedChance == 1.0f) {
                directed = true;
            } else {
                directed = Math.random() < edgeDirectedChance;
            }
        } else {
            directed = false;
        }
        return connect(source, target, directed);
    }

    /**
     * Connects two nodes with an edge if the given condition is evaluated to true.
     * 
     * @param source
     *            the source node
     * @param target
     *            the target node
     * @param condition
     *            the condition
     * @return whether the nodes have been connected
     */
    private boolean connectConditional(final Node source, final Node target,
            final EdgeCondition condition) {
        boolean directed;
        if (edgeDirectedChance > 0.0f) {
            if (edgeDirectedChance == 1.0f) {
                directed = true;
            } else {
                directed = Math.random() < edgeDirectedChance;
            }
        } else {
            directed = false;
        }
        if (condition.evaluate(source, target, directed)) {
            connect(source, target, directed);
            return true;
        }
        return false;
    }

    /**
     * Changes the source of a given edge to a given node.
     * 
     * @param edge
     *            the edge
     * @param node
     *            the new source node
     */
    private void moveSource(final Edge edge, final Node node) {
        if (ports) {
            if (edge.getType() == EdgeType.PORT2_NODE || edge.getType() == EdgeType.PORT2_PORT) {
                edge.getSource().getPorts().remove(edge.getSourcePort());
            }
            if (!node.isHypernode()) {
                Port newPort = factory.createPort();
                node.getPorts().add(newPort);
                edge.setSourcePort(newPort);
                newPort.getEdges().add(edge);
            }
            edge.setType(determineEdgeType(node, (Node) edge.getTarget()));
        }
        edge.setSource(node);
    }

    /**
     * Changes the target of a given edge to a given node.
     * 
     * @param edge
     *            the edge
     * @param node
     *            the new target node
     */
    private void moveTarget(final Edge edge, final Node node) {
        if (ports) {
            if (edge.getType() == EdgeType.NODE2_PORT || edge.getType() == EdgeType.PORT2_PORT) {
                edge.getTarget().getPorts().remove(edge.getTargetPort());
            }
            if (!node.isHypernode()) {
                Port newPort = factory.createPort();
                node.getPorts().add(newPort);
                edge.setTargetPort(newPort);
                newPort.getEdges().add(edge);
            }
            edge.setType(determineEdgeType((Node) edge.getSource(), node));
        }
        edge.setTarget(node);
    }

    /**
     * Splits an edge by inserting a new node and a new edge.
     * 
     * @param edge
     *            the edge
     * @return a pair containing the new node and the new edge
     */
    private Pair<Node, Edge> split(final Edge edge) {
        Node newNode = createNode(edge.getSource().getParent());
        Edge newEdge = connect(newNode, (Node) edge.getTarget());
        moveTarget(edge, newNode);
        return new Pair<Node, Edge>(newNode, newEdge);
    }

    /**
     * Connects a source node a number of times to randomly selected nodes of a given list if the
     * condition evaluates to true for the selected node.
     * 
     * @param source
     *            the source node
     * @param targets
     *            the target nodes
     * @param number
     *            the number of times the node has to be connected to random nodes
     * @param condition
     *            the condition
     * @return the number of edges which could be inserted
     */
    private int connectRandomlyAndConditional(final Node source, final List<Node> targets,
            final int number, final EdgeCondition condition) {
        Node[] targetBuffer = targets.toArray(new Node[0]);
        int edges = 0;
        int bufferEnd = targetBuffer.length - 1;
        // try connecting the source to up to 'number' nodes randomly
        while (edges < number && bufferEnd >= 0) {
            int i = randomInt(0, bufferEnd);
            Node target = targetBuffer[i];
            if (connectConditional(source, target, condition)) {
                ++edges;
            } else {
                // the current node does not fulfill the condition so replace it with an element
                // from the end of the buffer
                targetBuffer[i] = targetBuffer[bufferEnd];
                --bufferEnd;
            }

        }
        return edges;
    }

    /**
     * Connects every node in a list of nodes with random nodes of the same list for the specified
     * number of times.
     * 
     * @param nodes
     *            the list of nodes
     * @param outgoingEdges
     *            the number of outgoing edges for every node
     * @param condition
     *            the condition
     * @return the number of edges which could be inserted
     */
    private int connectRandomlyAndConditional(final List<Node> nodes, final int[] outgoingEdges,
            final EdgeCondition condition) {
        // connect every node to the specified number of other nodes
        int edges = 0;
        for (int i = 0; i < nodes.size(); ++i) {
            Node source = nodes.get(i);
            edges += connectRandomlyAndConditional(source, nodes, outgoingEdges[i], condition);
        }
        return edges;
    }

    /**
     * Randomly calculates a number of outgoing edges for every node in a list.
     * 
     * @param nodes
     *            the list of nodes
     * @param minOut
     *            the minimum number of outgoing edges per node
     * @param maxOut
     *            the maximum number of outgoing edges per node
     * @return the number of outgoing edges for every node
     */
    private int[] determineOutgoingEdges(final List<Node> nodes, final int minOut, final int maxOut) {
        // determine the number of outgoing edges for every node
        int n = nodes.size();
        int[] numberOfEdges = new int[n];
        for (int i = 0; i < n; ++i) {
            int c = randomInt(minOut, maxOut);
            numberOfEdges[i] = c;
        }
        return numberOfEdges;
    }

    /**
     * Randomly calculates a number of outgoing edges for every node in a list until a given number
     * of edges have been inserted.
     * 
     * @param nodes
     *            the list of nodes
     * @param m
     *            the number of edges
     * @return the number of outgoing edges for every node
     */
    private int[] determineOutgoingEdges(final List<Node> nodes, final int m) {
        // determine the number of outgoing edges for every node
        int[] outgoingEdges = new int[nodes.size()];
        for (int c = 0; c < m; ++c) {
            int i = randomInt(0, nodes.size() - 1);
            ++outgoingEdges[i];
        }
        return outgoingEdges;
    }

    /**
     * Calculates the required edge type for an edge between two nodes.
     * 
     * @param source
     *            the source node
     * @param target
     *            the target node
     * @return the edge type
     */
    private EdgeType determineEdgeType(final Node source, final Node target) {
        if (source.isHypernode()) {
            if (target.isHypernode()) {
                return EdgeType.NODE2_NODE;
            } else {
                return EdgeType.NODE2_PORT;
            }
        } else {
            if (target.isHypernode()) {
                return EdgeType.PORT2_NODE;
            } else {
                return EdgeType.PORT2_PORT;
            }
        }
    }

    /**
     * Returns whether inserting an edge between two given nodes causes a cycle in the graph.
     * 
     * @param source
     *            the source node
     * @param target
     *            the target node
     * @param directed
     *            whether the edge has to be treated as directed
     * @return whether inserting the edge causes a cycle in the graph
     */
    // PRECONDITION: the graph containing the nodes has to be acyclic! If that condition is not met
    // the method is likely to loop infinitly!
    private boolean edgeCausesCycle(final Node source, final Node target, final boolean directed) {
        // DFS to find the source node
        if (findNodeWithDFS(target, source)) {
            return true;
        }
        // for an undirected edge the other direction has to be checked as well
        if (!directed) {
            return findNodeWithDFS(source, target);
        }
        return false;
    }

    /**
     * Returns whether a node can be reached from another node.
     * 
     * @param start
     *            the start node
     * @param end
     *            the end node
     * @return whether the end node can be reached from the start node
     */
    // PRECONDITION: the graph containing the nodes has to be acyclic! If that condition is not met
    // the method is likely to loop infinitly!
    private boolean findNodeWithDFS(final Node start, final Node end) {
        Set<Edge> visitedUndirectedEdges = new HashSet<Edge>();
        Queue<KNode> nodes = new LinkedList<KNode>();
        nodes.add(start);
        while (!nodes.isEmpty()) {
            KNode node = nodes.poll();
            if (node == end) {
                return true;
            }
            for (KEdge edge : node.getOutgoingEdges()) {
                KNode target = edge.getTarget();
                nodes.add(target);
            }
            for (KEdge kedge : node.getIncomingEdges()) {
                Edge edge = (Edge) kedge;
                if (!edge.isDirected()) {
                    // avoid taking the same undirected edge more then once
                    if (visitedUndirectedEdges.contains(edge)) {
                        continue;
                    }
                    visitedUndirectedEdges.add(edge);
                    KNode source = edge.getSource();
                    nodes.add(source);
                }
            }
        }
        return false;
    }

    /**
     * Returns whether two nodes are connected.
     * 
     * @param source
     *            the source node
     * @param target
     *            the target node
     * @return whether the two nodes are connected
     */
    private boolean connected(final KNode source, final KNode target) {
        for (KEdge edge : source.getOutgoingEdges()) {
            if (edge.getTarget() == target) {
                return true;
            }
        }
        for (KEdge edge : target.getOutgoingEdges()) {
            if (edge.getTarget() == source && !((Edge) edge).isDirected()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Generates the next label used for labeling nodes.
     * 
     * @return the label
     */
    private String generateLabel() {
        return "N" + String.valueOf(labelCounter++);
    }

    /**
     * Returns a random integer number in the given range (including the boundaries).
     * 
     * @param from
     *            the minimal number
     * @param to
     *            the maximal number
     * @return a random integer number
     */
    private static int randomInt(final int from, final int to) {
        return from + (int) Math.round((to - from) * Math.random());
    }

    /**
     * An interface for expressing conditions for creating an edge between two nodes.
     */
    private interface EdgeCondition {

        /**
         * Returns whether the condition is met for an edge from the first to the second node.
         * 
         * @param node1
         *            the first node
         * @param node2
         *            the second node
         * @return true if the condition for the edge is met; false else
         */
        boolean evaluate(final Node node1, final Node node2, final boolean directed);
    }
}
