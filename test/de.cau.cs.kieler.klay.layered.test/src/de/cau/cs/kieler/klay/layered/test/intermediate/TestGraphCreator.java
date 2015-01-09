package de.cau.cs.kieler.klay.layered.test.intermediate;

import java.util.List;

import de.cau.cs.kieler.core.properties.MapPropertyHolder;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortConstraints;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.properties.InternalProperties;
import de.cau.cs.kieler.klay.layered.properties.NodeType;

public class TestGraphCreator {
    public TestGraphCreator() {
        graph = new LGraph();
    }

    private final LGraph graph;

    public LGraph getEmptyGraph() {
        return graph;
    }

    public LGraph getTwoNodesNoConnectionGraph() {
        Layer layer = addLayerToGraph();
        addNormalNodeToLayer(layer);
        addNormalNodeToLayer(layer);
        return graph;
    }

    public LGraph getCrossFormedGraph() {

        Layer leftLayer = addLayerToGraph();
        Layer rightLayer = addLayerToGraph();

        LNode topLeft = addNormalNodeToLayer(leftLayer);
        LNode bottomLeft = addNormalNodeToLayer(leftLayer);
        LNode topRight = addNormalNodeToLayer(rightLayer);
        LNode bottomRight = addNormalNodeToLayer(rightLayer);

        addEastWestEdgeFromTo(topLeft, bottomRight);
        addEastWestEdgeFromTo(bottomLeft, topRight);
        return graph;
    }

    public LGraph getOneNodeGraph() {
        Layer layer = addLayerToGraph();
        addNormalNodeToLayer(layer);
        return graph;
    }

//    @formatter:off
    /**
     *   --* 
     *   |  
     * *-+-*-* 
     *   | 
     *   --*
     * 
     * @return graph of the form above
     */
//  @formatter:on
    public LGraph getInLayerEdgesGraph() {
        Layer leftLayer = addLayerToGraph();
        Layer middleLayer = addLayerToGraph();
        Layer rightLayer = addLayerToGraph();

        LNode leftNode = addNormalNodeToLayer(leftLayer);
        LNode[] middleNodes = addNodesToLayer(3, middleLayer);
        LNode rightNode = addNormalNodeToLayer(rightLayer);

        addEastWestEdgeFromTo(leftNode, middleNodes[1]);
        addInLayerEdge(middleNodes[0], middleNodes[2], PortSide.WEST);
        addEastWestEdgeFromTo(middleNodes[1], rightNode);
        return graph;
    }

    public LGraph getNorthSouthCrossingGraph() {
        Layer leftLayer = addLayerToGraph();
        Layer rightLayer = addLayerToGraph();

        LNode leftNode = addNormalNodeToLayer(leftLayer);
        LNode rightNode = addNormalNodeToLayer(rightLayer);

        setFixedOrderConstraint(leftNode);
        setFixedOrderConstraint(rightNode);

        LPort leftNodeLeftPort = addPortOnSide(leftNode, PortSide.NORTH);
        LPort leftNodeRightPort = addPortOnSide(leftNode, PortSide.NORTH);

        // ports are added in clockwise fashion, so add bottom port first.
        LPort rightNodeBottomPort = addPortOnSide(rightNode, PortSide.WEST);
        LPort rightNodeTopPort = addPortOnSide(rightNode, PortSide.WEST);

        addNorthEdgeBetweenPorts(leftNodeLeftPort, rightNodeBottomPort, leftLayer);
        addNorthEdgeBetweenPorts(leftNodeRightPort, rightNodeTopPort, leftLayer);
        return graph;
    }

    public LGraph getMultipleEdgesBetweenSameNodesGraph() {
        Layer leftLayer = addLayerToGraph();
        Layer rightLayer = addLayerToGraph();

        LNode topLeft = addNormalNodeToLayer(leftLayer);
        LNode bottomLeft = addNormalNodeToLayer(leftLayer);
        LNode topRight = addNormalNodeToLayer(rightLayer);
        LNode bottomRight = addNormalNodeToLayer(rightLayer);

        addEastWestEdgeFromTo(topLeft, bottomRight);
        addEastWestEdgeFromTo(topLeft, bottomRight);
        addEastWestEdgeFromTo(bottomLeft, topRight);
        addEastWestEdgeFromTo(bottomLeft, topRight);

        return graph;
    }

//  @formatter:off
  /**
   * *   * 
   *  \ / 
   * *-+-* 
   *  / \
   * *   *
   * 
   * @return graph of the form above
   */
//@formatter:on
    public LGraph getCrossWithExtraEdgeInBetweenGraph() {
        Layer leftLayer = addLayerToGraph();
        Layer rightLayer = addLayerToGraph();

        LNode[] leftNodes = addNodesToLayer(3, leftLayer);
        LNode[] rightNodes = addNodesToLayer(3, rightLayer);

        addEastWestEdgeFromTo(leftNodes[0], rightNodes[2]);
        addEastWestEdgeFromTo(leftNodes[1], rightNodes[1]);
        addEastWestEdgeFromTo(leftNodes[2], rightNodes[0]);

        return graph;
    }

    public LGraph getCrossWithManySelfLoopsGraph() {
        LGraph graph = getCrossFormedGraph();
        for (Layer layer : graph) {
            for (LNode lNode : layer) {
                addEastWestEdgeFromTo(lNode, lNode);
                addEastWestEdgeFromTo(lNode, lNode);
                addEastWestEdgeFromTo(lNode, lNode);
            }
        }
        return graph;
    }

    //@formatter:off
    /**
     * *\  --*
     *   \/ /
     * *-*===*
     *  + /
     * * * --*
     * @return
     */
    //@formatter:on
    public LGraph getMoreComplexThreeLayerGraph() {
        Layer leftLayer = addLayerToGraph();
        Layer middleLayer = addLayerToGraph();
        Layer rightLayer = addLayerToGraph();

        LNode[] leftNodes = addNodesToLayer(3, leftLayer);
        LNode[] middleNodes = addNodesToLayer(2, middleLayer);
        LNode[] rightNodes = addNodesToLayer(3, rightLayer);

        addEastWestEdgeFromTo(leftNodes[0], middleNodes[0]);
        addEastWestEdgeFromTo(leftNodes[1], middleNodes[0]);
        addEastWestEdgeFromTo(leftNodes[1], middleNodes[1]);
        addEastWestEdgeFromTo(leftNodes[2], middleNodes[0]);
        addEastWestEdgeFromTo(middleNodes[0], rightNodes[0]);
        addEastWestEdgeFromTo(middleNodes[0], rightNodes[1]);
        addEastWestEdgeFromTo(middleNodes[0], rightNodes[1]);
        addEastWestEdgeFromTo(middleNodes[1], rightNodes[0]);
        addEastWestEdgeFromTo(middleNodes[1], rightNodes[2]);

        return graph;
    }

//    @formatter:off
    /** 
     * 
     * ____  *
     * |  |\/
     * |__|/\
     *       *
     * @return
     */
//    @formatter:on
    public LGraph getFixedPortOrderGraph() {
        Layer leftLayer = addLayerToGraph();
        Layer rightLayer = addLayerToGraph();

        LNode leftNode = addNormalNodeToLayer(leftLayer);
        leftNode.setProperty(LayoutOptions.PORT_CONSTRAINTS, PortConstraints.FIXED_ORDER);

        LNode rightTopNode = addNormalNodeToLayer(rightLayer);
        LNode rightBottomNode = addNormalNodeToLayer(rightLayer);

        addEastWestEdgeFromTo(leftNode, rightBottomNode);
        addEastWestEdgeFromTo(leftNode, rightTopNode);

        return graph;
    }

    protected MapPropertyHolder setFixedOrderConstraint(final LNode leftNode) {
        return leftNode.setProperty(LayoutOptions.PORT_CONSTRAINTS, PortConstraints.FIXED_ORDER);
    }

    private void addNorthEdgeBetweenPorts(final LPort northOrigin, final LPort westTarget,
            final Layer layerWithNSNodes) {
        LNode nsNode = addNormalNodeToLayer(layerWithNSNodes);
        nsNode.setProperty(InternalProperties.NODE_TYPE, NodeType.NORTH_SOUTH_PORT);
        nsNode.setProperty(InternalProperties.ORIGIN, northOrigin.getNode());
        LPort southPort = addPortOnSide(nsNode, PortSide.SOUTH);
        LPort eastPort = addPortOnSide(nsNode, PortSide.EAST);
        addEdgeBetweenPorts(northOrigin, southPort);
        addEdgeBetweenPorts(eastPort, westTarget);
    }

    private void addInLayerEdge(final LNode nodeOne, final LNode nodeTwo, final PortSide portSide) {
        LPort portOne = addPortOnSide(nodeOne, portSide);
        LPort portTwo = addPortOnSide(nodeTwo, portSide);
        addEdgeBetweenPorts(portOne, portTwo);
    }

    private LNode[] addNodesToLayer(final int amountOfNodes, final Layer leftLayer) {
        LNode[] nodes = new LNode[amountOfNodes];
        for (int j = 0; j < amountOfNodes; j++) {
            nodes[j] = addNormalNodeToLayer(leftLayer);
        }
        return nodes;
    }

    private Layer addLayerToGraph() {
        List<Layer> layers = graph.getLayers();
        Layer layer = new Layer(graph);
        layers.add(layer);
        return layer;
    }

    private LNode addNormalNodeToLayer(final Layer leftLayer) {
        LNode node = new LNode(graph);
        node.setProperty(InternalProperties.NODE_TYPE, NodeType.NORMAL);
        node.setLayer(leftLayer);
        return node;
    }

    private void addEastWestEdgeFromTo(final LNode left, final LNode right) {
        LPort leftPort = addPortOnSide(left, PortSide.EAST);
        LPort rightPort = addPortOnSide(right, PortSide.WEST);
        addEdgeBetweenPorts(leftPort, rightPort);
    }

    private void addEdgeBetweenPorts(final LPort leftPort, final LPort rightPort) {
        LEdge edge = new LEdge(graph);
        edge.setSource(leftPort);
        edge.setTarget(rightPort);
    }

    private LPort addPortOnSide(final LNode node, final PortSide portSide) {
        LPort port = addPortTo(node);
        port.setSide(portSide);
        return port;
    }

    private LPort addPortTo(final LNode node) {
        LPort leftPort = new LPort(graph);
        leftPort.setNode(node);
        return leftPort;
    }

}
