package de.cau.cs.kieler.graphs.wizards;

import de.cau.cs.kieler.graphs.Edge;
import de.cau.cs.kieler.graphs.Graph;
import de.cau.cs.kieler.graphs.GraphsFactory;
import de.cau.cs.kieler.graphs.Node;

/**
 * Creator for graph models.
 * 
 * @author haf
 * @author msp
 */
public class RandomGraphCreator {

    private static GraphsFactory factory = GraphsFactory.eINSTANCE;

    /** probability that a new hierarchy level will be created upon the creation of a new node. */
    private float hierarchyProb;
    /** current index for new node. */
    private int nodeIndex;

    /**
     * Creates a random graph model according to given parameters.
     * 
     * @param nodes number of nodes in the model
     * @param minConnections minimal number of outgoing connections per node
     * @param maxConnections maximal number of outgoing connections per node
     * @param thehierarchyProb probability of new hierarchy level
     * @return a random graph model
     */
    public Graph createModel(final int nodes, final int minConnections, final int maxConnections,
            final float thehierarchyProb) {
        this.hierarchyProb = thehierarchyProb;
        this.nodeIndex = 0;
        Graph graph = factory.createGraph();

        createNodes(graph, nodes, minConnections, maxConnections);

        return graph;
    }

    /**
     * Creates a set of child nodes in the parent, each node will have the given amount of outgoing
     * connections. New hierarchy levels are introduced randomly.
     * 
     * @param graph the parent graph
     * @param nodesToCreate number of nodes
     * @param minConnections minimal number of outgoing connections per node
     * @param maxConnections maximal number of outgoing connections per node
     */
    private void createNodes(final Graph graph, final int nodesToCreate, final int minConnections,
            final int maxConnections) {
        if (nodesToCreate <= 0) {
            return;
        }
        int nodesLeft = nodesToCreate;
        int compoundCount = 0, totalCount = 0;
        do {
            Node node;
            if (Math.random() <= hierarchyProb) {
                node = factory.createCompoundNode();
                nodesLeft /= 2;
                compoundCount++;
            } else {
                node = factory.createNode();
                nodesLeft--;
            }
            totalCount++;
            node.setLabel("n" + nodeIndex++);
            graph.getNodes().add(node);
        } while (nodesLeft > 0);

        int nodesPerCompound = compoundCount > 0 ? (nodesToCreate - totalCount) / compoundCount : 0;
        for (Node node : graph.getNodes()) {
            if (node instanceof Graph) {
                // create new hierarchical layers by calling this recursively
                createNodes((Graph) node, nodesPerCompound, minConnections, maxConnections);
            }
            // connect this hierarchy layer
            int connectionCount = (int) Math.round(Math.random() * (maxConnections - minConnections))
                    + minConnections;
            for (int i = 0; i < connectionCount; i++) {
                connectNode(node, graph);
            }
        }
    }

    /**
     * Creates an outgoing edge from a node to another random node from the parent node. (i.e. a
     * node on the same hierarchy layer as the source node.) Will create a new output port for the
     * node and an input port for the target node.
     * 
     * @param sourceNode node to connect
     * @param graph the parent graph
     */
    private void connectNode(final Node sourceNode, final Graph graph) {
        // find a random node on same hierarchy layer
        int thisLayerSize = graph.getNodes().size();
        Node targetNode = null;
        for (int i = 0; i < 2; i++) {
            // repeat random node selection if the source node was taken
            int randomIndex = randomInt(0, thisLayerSize - 1);
            targetNode = graph.getNodes().get(randomIndex);
            if (targetNode != sourceNode) {
                break;
            }
        }

        // connect edge
        Edge edge = factory.createEdge();
        sourceNode.getOutgoing().add(edge);
        targetNode.getIncoming().add(edge);
        graph.getEdges().add(edge);
    }

    /**
     * Returns a random integer number in the given range (including the boundaries).
     * 
     * @param from minimal number
     * @param to maximal number
     * @return a random integer number
     */
    private static int randomInt(final int from, final int to) {
        return from + (int) Math.round((to - from) * Math.random());
    }

}
