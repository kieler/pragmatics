package de.cau.cs.kieler.graphs.wizards;

import java.util.LinkedList;
import java.util.List;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.graphs.Edge;
import de.cau.cs.kieler.graphs.EdgeType;
import de.cau.cs.kieler.graphs.GraphsFactory;
import de.cau.cs.kieler.graphs.Node;
import de.cau.cs.kieler.graphs.Port;

/**
 * A generator class for graph models.
 * 
 * @author haf
 * @author msp
 * @author mri
 */
public class RandomGraphGenerator {

    private static GraphsFactory factory = GraphsFactory.eINSTANCE;

    /**
     * probability that a new hierarchy level will be created upon the creation
     * of a new node.
     */
    private float hierarchyProb;
    /** propability that a hyper node will be created instead of a normal one. */
    private float hyperNodeProb;
    /** true if edges are directed, false else */
    private boolean directed;
    /** true if nodes are connected through ports, false else */
    private boolean usePorts;
    /** current index for new node. */
    private int nodeIndex;

    /**
     * Creates a random graph model according to given parameters.
     * 
     * @param nodes
     *            number of nodes in the model
     * @param minConnections
     *            minimal number of outgoing connections per node
     * @param maxConnections
     *            maximal number of outgoing connections per node
     * @param thehierarchyProb
     *            probability of new hierarchy level
     * @return a random graph model
     */
    public Node createModel(final int nodes, final int minConnections,
            final int maxConnections, final float thehierarchyProb,
            final float hyperNodeProb, final boolean directed,
            final boolean usePorts) {
        this.hierarchyProb = thehierarchyProb;
        this.hyperNodeProb = hyperNodeProb;
        this.directed = directed;
        this.usePorts = usePorts;
        this.nodeIndex = 0;
        Node graph = factory.createNode();

        createNodes(graph, nodes, minConnections, maxConnections);

        return graph;
    }

    /**
     * Creates a set of child nodes in the parent, each node will have the given
     * amount of outgoing connections. New hierarchy levels are introduced
     * randomly.
     * 
     * @param graph
     *            the parent graph
     * @param nodesToCreate
     *            number of nodes
     * @param minConnections
     *            minimal number of outgoing connections per node
     * @param maxConnections
     *            maximal number of outgoing connections per node
     */
    private void createNodes(final Node graph, final int nodesToCreate,
            final int minConnections, final int maxConnections) {
        if (nodesToCreate <= 0) {
            return;
        }
        int nodesLeft = nodesToCreate;
        int compoundCount = 0, totalCount = 0;
        List<Node> compoundNodes = new LinkedList<Node>();
        do {
            Node node = factory.createNode();
            double rand = Math.random();
            if (rand <= hierarchyProb) {
                compoundNodes.add(node);
                nodesLeft /= 2;
                compoundCount++;
            } else if (rand > hierarchyProb
                    && rand <= hierarchyProb + hyperNodeProb) {
                node.setIsHypernode(true);
                nodesLeft--;
            } else {
                nodesLeft--;
            }
            totalCount++;
            node.setNodeLabel("n" + nodeIndex++);
            graph.getChildren().add(node);
        } while (nodesLeft > 0);

        int nodesPerCompound = compoundCount > 0 ? (nodesToCreate - totalCount)
                / compoundCount : 0;

        for (KNode knode : compoundNodes) {
            Node node = (Node) knode;
            // create new hierarchical layers by calling this recursively
            createNodes(node, nodesPerCompound, minConnections, maxConnections);
        }

        for (KNode knode : graph.getChildren()) {
            Node node = (Node) knode;
            // connect this hierarchy layer
            int connectionCount = randomInt(minConnections, maxConnections);
            for (int i = 0; i < connectionCount; i++) {
                connectNode(node, graph);
            }
        }
    }

    /**
     * Creates an outgoing edge from a node to another random node from the
     * parent node. (i.e. a node on the same hierarchy layer as the source
     * node.)
     * 
     * @param sourceNode
     *            node to connect
     * @param graph
     *            the parent graph
     */
    private void connectNode(final Node sourceNode, final Node graph) {
        // find a random node on same hierarchy layer
        int thisLayerSize = graph.getChildren().size();
        Node targetNode = null;
        for (int i = 0; i < 2; i++) {
            // repeat random node selection if the source node was taken
            int randomIndex = randomInt(0, thisLayerSize - 1);
            targetNode = (Node) graph.getChildren().get(randomIndex);
            if (targetNode != sourceNode) {
                break;
            }
        }

        // connect edge
        Edge edge = factory.createEdge();
        edge.setIsDirected(directed);
        // connect through ports
        if (usePorts) {
            if (sourceNode.isIsHypernode()) {
                if (!targetNode.isIsHypernode()) {
                    Port targetPort = factory.createPort();
                    targetNode.getPorts().add(targetPort);
                    edge.setTargetPort(targetPort);
                    edge.setType(EdgeType.NODE2_PORT);
                }
            } else {
                Port sourcePort = factory.createPort();
                sourceNode.getPorts().add(sourcePort);
                edge.setSourcePort(sourcePort);
                if (targetNode.isIsHypernode()) {
                    edge.setType(EdgeType.PORT2_NODE);
                } else {
                    Port targetPort = factory.createPort();
                    targetNode.getPorts().add(targetPort);
                    edge.setTargetPort(targetPort);
                    edge.setType(EdgeType.PORT2_PORT);
                }
            }
        }
        sourceNode.getOutgoingEdges().add(edge);
        targetNode.getIncomingEdges().add(edge);
    }

    /**
     * Returns a random integer number in the given range (including the
     * boundaries).
     * 
     * @param from
     *            minimal number
     * @param to
     *            maximal number
     * @return a random integer number
     */
    private static int randomInt(final int from, final int to) {
        return from + (int) Math.round((to - from) * Math.random());
    }

}
