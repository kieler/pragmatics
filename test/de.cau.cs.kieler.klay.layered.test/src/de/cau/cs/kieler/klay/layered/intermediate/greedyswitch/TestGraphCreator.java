package de.cau.cs.kieler.klay.layered.intermediate.greedyswitch;

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
    private int portId = 0;
    private int nodeId = 0;
    private final LGraph graph;
    private int edgeId = 0;

    public TestGraphCreator() {
        graph = new LGraph();
    }

    public LGraph getEmptyGraph() {
        return graph;
    }

    public LGraph getTwoNodesNoConnectionGraph() {
        Layer layer = makeLayer();
        addNodeToLayer(layer);
        addNodeToLayer(layer);
        return graph;
    }

    public LGraph getCrossFormedGraph() {

        Layer leftLayer = makeLayer();
        Layer rightLayer = makeLayer();

        LNode topLeft = addNodeToLayer(leftLayer);
        LNode bottomLeft = addNodeToLayer(leftLayer);
        LNode topRight = addNodeToLayer(rightLayer);
        LNode bottomRight = addNodeToLayer(rightLayer);

        addEastWestEdgeFromTo(topLeft, bottomRight);
        addEastWestEdgeFromTo(bottomLeft, topRight);
        return graph;
    }

    public LGraph getOneNodeGraph() {
        Layer layer = makeLayer();
        addNodeToLayer(layer);
        return graph;
    }

    /**
     * <pre>
     *   --* 
     *   |  
     * *-+-*-* 
     *   | 
     *   --*
     * </pre>
     * 
     * @return graph of the form above
     */
    public LGraph getInLayerEdgesGraph() {
        Layer leftLayer = makeLayer();
        Layer middleLayer = makeLayer();
        Layer rightLayer = makeLayer();

        LNode leftNode = addNodeToLayer(leftLayer);
        LNode[] middleNodes = addNodesToLayer(3, middleLayer);
        LNode rightNode = addNodeToLayer(rightLayer);

        // add east side ports first to get expected port ordering
        addEastWestEdgeFromTo(middleNodes[1], rightNode);
        addEastWestEdgeFromTo(leftNode, middleNodes[1]);
        addInLayerEdge(middleNodes[0], middleNodes[2], PortSide.WEST);
        return graph;
    }

    /**
     * <pre>
     *   --* 
     *   |  
     *   --* 
     *    
     *  *--*
     * </pre>
     * 
     * @return graph of the form above
     */
    public LGraph getInLayerEdgesGraphWhichResultsInCrossingsWhenSwitched() {
        Layer leftLayer = makeLayer();
        Layer rightLayer = makeLayer();

        LNode leftNode = addNodeToLayer(leftLayer);
        LNode[] rightNodes = addNodesToLayer(3, rightLayer);

        addInLayerEdge(rightNodes[0], rightNodes[1], PortSide.WEST);
        addEastWestEdgeFromTo(leftNode, rightNodes[2]);

        return graph;
    }

    public LGraph getNorthSouthCrossingGraph() {
        Layer leftLayer = makeLayer();
        Layer rightLayer = makeLayer();

        LNode leftNode = addNodeToLayer(leftLayer);
        LNode rightNode = addNodeToLayer(rightLayer);

        setFixedOrderConstraint(leftNode);
        setFixedOrderConstraint(rightNode);

        LPort leftNodeLeftPort = addPortOnSide(leftNode, PortSide.SOUTH);
        LPort leftNodeRightPort = addPortOnSide(leftNode, PortSide.SOUTH);

        // ports are added in clockwise fashion, so add bottom port first.
        LPort rightNodeBottomPort = addPortOnSide(rightNode, PortSide.WEST);
        LPort rightNodeTopPort = addPortOnSide(rightNode, PortSide.WEST);
        rightNodeTopPort.id = 1;

        addSouthEdgeBetweenPorts(leftNodeLeftPort, rightNodeTopPort, leftLayer);
        addSouthEdgeBetweenPorts(leftNodeRightPort, rightNodeBottomPort, leftLayer);
        return graph;
    }

    /**
     * <pre>
     *     ______
     * *---|____|
     *      |  |  ____
     *      *--+--|  |
     *         |  |  |
     *         *--|__|
     * </pre>
     * 
     * @return
     */
    public LGraph getThreeLayerNorthSouthCrossingGraph() {
        // add left Node
        Layer leftLayer = makeLayer();
        LNode leftNode = addNodeToLayer(leftLayer);

        // add N/S graph
        getNorthSouthCrossingGraph();

        // connect
        Layer secondLayer = graph.getLayers().get(1);
        LNode nsNode = secondLayer.getNodes().get(0);

        addEastWestEdgeFromTo(leftNode, nsNode);

        return graph;
    }

    public LGraph getMultipleEdgesBetweenSameNodesGraph() {
        Layer leftLayer = makeLayer();
        Layer rightLayer = makeLayer();

        LNode topLeft = addNodeToLayer(leftLayer);
        LNode bottomLeft = addNodeToLayer(leftLayer);
        LNode topRight = addNodeToLayer(rightLayer);
        LNode bottomRight = addNodeToLayer(rightLayer);

        addEastWestEdgeFromTo(topLeft, bottomRight);
        addEastWestEdgeFromTo(topLeft, bottomRight);
        addEastWestEdgeFromTo(bottomLeft, topRight);
        addEastWestEdgeFromTo(bottomLeft, topRight);

        return graph;
    }

    /**
     * <pre>
     * *   * 
     *  \ / 
     * *-+-* 
     *  / \
     * *   *
     * </pre>
     * 
     * @return graph of the form above
     */
    public LGraph getCrossWithExtraEdgeInBetweenGraph() {
        Layer leftLayer = makeLayer();
        Layer rightLayer = makeLayer();

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

    /**
     * <pre>
     * *\  --*
     *   \/ /
     * *-*===*
     *  + /
     * * * --*
     * </pre>
     * 
     * Port order not fixed.
     * 
     * @return
     */
    public LGraph getMoreComplexThreeLayerGraph() {
        Layer leftLayer = makeLayer();
        Layer middleLayer = makeLayer();
        Layer rightLayer = makeLayer();

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

    /**
     * <pre>
     * ____  *
     * |  |\/
     * |__|/\
     *       *
     * </pre>
     * 
     * @return
     */
    public LGraph getFixedPortOrderGraph() {
        Layer leftLayer = makeLayer();
        Layer rightLayer = makeLayer();

        LNode leftNode = addNodeToLayer(leftLayer);
        leftNode.setProperty(LayoutOptions.PORT_CONSTRAINTS, PortConstraints.FIXED_ORDER);

        LNode rightTopNode = addNodeToLayer(rightLayer);
        LNode rightBottomNode = addNodeToLayer(rightLayer);

        addEastWestEdgeFromTo(leftNode, rightBottomNode);
        addEastWestEdgeFromTo(leftNode, rightTopNode);

        return graph;
    }

    /**
     * <pre>
     * *  *---*
     *  \/
     *  /\
     * *  *---*
     * </pre>
     * 
     * @return
     */
    public LGraph getSwitchOnlyOneSided() {
        Layer[] layers = makeLayers(3);

        LNode[] leftNodes = addNodesToLayer(2, layers[0]);
        LNode[] middleNodes = addNodesToLayer(2, layers[1]);
        LNode[] rightNodes = addNodesToLayer(2, layers[2]);

        addEastWestEdgeFromTo(leftNodes[0], middleNodes[1]);
        addEastWestEdgeFromTo(leftNodes[1], middleNodes[0]);
        addEastWestEdgeFromTo(middleNodes[0], rightNodes[0]);
        addEastWestEdgeFromTo(middleNodes[1], rightNodes[1]);

        return graph;
    }

    /**
     * <pre>
     * ____   _____
     * |  |---|   |
     * |  |---|   |
     * |  |   |   |
     * |  |  /|   |
     * |__|--+|___|
     *       |
     *       \
     *        *
     * </pre>
     * 
     * @return
     */
    public LGraph getInLayerEdgesGraphWithCrossingsToFixedPortOrder() {
        Layer[] layers = makeLayers(2);

        LNode leftNode = addNodeToLayer(layers[0]);
        LNode[] rightNodes = addNodesToLayer(2, layers[1]);

        setPortOrderFixed(rightNodes[0]);

        // since we expect bottom up ordering of western ports, the order of adding edges is
        // important
        addEastWestEdgeFromTo(leftNode, rightNodes[0]);
        addInLayerEdge(rightNodes[0], rightNodes[1], PortSide.WEST);
        addEastWestEdgeFromTo(leftNode, rightNodes[0]);
        addEastWestEdgeFromTo(leftNode, rightNodes[0]);
        return graph;
    }

    /**
     * <pre>
     *       ___
     *    ---| |
     *    |  | |  <- switch this
     * ---+--|_|
     * |  | 
     * *--|--*  <- with this
     *    |
     *    ---*
     * </pre>
     * 
     * With fixed Port Order.
     * 
     * @return
     */
    public LGraph getInLayerEdgesWithFixedPortOrderAndNormalEdgeCrossings() {
        Layer[] layer = makeLayers(2);
        LNode leftNode = addNodeToLayer(layer[0]);
        LNode[] rightNodes = addNodesToLayer(3, layer[1]);
        setFixedOrderConstraint(rightNodes[0]);
        addEastWestEdgeFromTo(leftNode, rightNodes[0]);
        addInLayerEdge(rightNodes[0], rightNodes[2], PortSide.WEST);
        addEastWestEdgeFromTo(leftNode, rightNodes[1]);

        return graph;
    }

    /**
     * <pre>
     *     ____
     *    /|  |
     * *-+-|__|
     *   | ____
     * *-+-|  |
     *    \|__|
     * </pre>
     *
     * Port order not fixed.
     * 
     * @return
     */
    public LGraph getInLayerEdgesCrossingsButNoFixedOrder() {
        Layer[] layer = makeLayers(2);
        LNode[] leftNodes = addNodesToLayer(2, layer[0]);
        LNode[] rightNodes = addNodesToLayer(2, layer[1]);

        addEastWestEdgeFromTo(leftNodes[0], rightNodes[0]);
        addInLayerEdge(rightNodes[0], rightNodes[1], PortSide.WEST);
        addEastWestEdgeFromTo(leftNodes[1], rightNodes[1]);

        return graph;
    }

    /**
     * <pre>
     *     ____
     *    /|  |
     * *-+-|__|
     *   | 
     * *-+-*
     *    \
     *     *
     * </pre>
     *
     * Port order not fixed.
     * 
     * @return
     */
    public LGraph getInLayerEdgesCrossingsButNoFixedOrderNoEdgeBetweenUpperAndLower() {
        Layer[] layer = makeLayers(2);
        LNode[] leftNodes = addNodesToLayer(2, layer[0]);
        LNode[] rightNodes = addNodesToLayer(3, layer[1]);

        addEastWestEdgeFromTo(leftNodes[0], rightNodes[0]);
        addInLayerEdge(rightNodes[0], rightNodes[2], PortSide.WEST);
        addEastWestEdgeFromTo(leftNodes[1], rightNodes[1]);

        return graph;
    }

    /**
     * <pre>
     *   --*--
     *   |   |
     * *-+-*-+-* 
     *   |   |
     *   --*--
     * </pre>
     * 
     * @return graph of the form above
     */

    public LGraph getInLayerCrossingsOnBothSides() {
        Layer[] layers = makeLayers(3);
        LNode leftNode = addNodeToLayer(layers[0]);
        LNode[] middleNodes = addNodesToLayer(3, layers[1]);
        LNode rightNode = addNodeToLayer(layers[2]);

        addInLayerEdge(middleNodes[0], middleNodes[2], PortSide.EAST);
        addInLayerEdge(middleNodes[0], middleNodes[2], PortSide.WEST);
        addEastWestEdgeFromTo(middleNodes[1], rightNode);
        addEastWestEdgeFromTo(leftNode, middleNodes[1]);
        return graph;
    }

    /**
     * <pre>
     * ____
     * |  |----
     * |__|\  |
     * ____ | |
     * |  |/  |
     * |__|---|
     * </pre>
     *
     * Port order fixed.
     * 
     * @return
     */
    public LGraph getFixedPortOrderInLayerEdgesDontCrossEachOther() {
        Layer layer = makeLayer();
        LNode[] nodes = addNodesToLayer(2, layer);
        setFixedOrderConstraint(nodes[0]);
        setFixedOrderConstraint(nodes[1]);
        // must add ports and edges manually, due to clockwise port ordering
        LPort upperPortUpperNode = addPortOnSide(nodes[0], PortSide.EAST);
        LPort lowerPortUpperNode = addPortOnSide(nodes[0], PortSide.EAST);
        LPort upperPortLowerNode = addPortOnSide(nodes[1], PortSide.EAST);
        LPort lowerPortLowerNode = addPortOnSide(nodes[1], PortSide.EAST);
        addEdgeBetweenPorts(upperPortUpperNode, lowerPortLowerNode);
        addEdgeBetweenPorts(lowerPortUpperNode, upperPortLowerNode);
        return graph;
    }

    /**
     * <pre>
     * ____
     * |  |----
     * |__|\  |
     * ____ | |
     * |  |-+--
     * |__|-|
     * </pre>
     *
     * Port order fixed.
     * 
     * @return
     */
    public LGraph getFixedPortOrderInLayerEdgesWithCrossings() {
        Layer layer = makeLayer();
        LNode[] nodes = addNodesToLayer(2, layer);
        setFixedOrderConstraint(nodes[0]);
        setFixedOrderConstraint(nodes[1]);
        addInLayerEdge(nodes[0], nodes[1], PortSide.EAST);
        addInLayerEdge(nodes[0], nodes[1], PortSide.EAST);

        return graph;
    }

    /**
     * <pre>
     *      ____
     *    / |  |
     * *-+--|  |\
     *   | /|  |-+-*
     * *-++-|__| |
     *   ||      |
     *   || ___  |
     *   | \| | /
     * *-+--| |/
     * *-+--|_|
     *    \
     *     \
     *      *
     * </pre>
     *
     * Port order fixed. ||||
     * 
     * @return
     */
    public LGraph getMoreComplexInLayerGraph() {
        Layer[] layers = makeLayers(3);
        LNode[] leftNodes = addNodesToLayer(4, layers[0]);
        LNode[] middleNodes = addNodesToLayer(3, layers[1]);
        LNode rightNode = addNodeToLayer(layers[2]);
        setFixedOrderConstraint(middleNodes[0]);
        setFixedOrderConstraint(middleNodes[1]);

        addEastWestEdgeFromTo(leftNodes[1], middleNodes[0]);

        addEastWestEdgeFromTo(leftNodes[3], middleNodes[1]);
        addEastWestEdgeFromTo(leftNodes[2], middleNodes[1]);
        addInLayerEdge(middleNodes[0], middleNodes[1], PortSide.WEST);
        addEastWestEdgeFromTo(leftNodes[0], middleNodes[0]);
        addInLayerEdge(middleNodes[0], middleNodes[2], PortSide.WEST);

        addInLayerEdge(middleNodes[0], middleNodes[1], PortSide.EAST);
        addEastWestEdgeFromTo(middleNodes[0], rightNode);

        return graph;

    }

    /**
     * <pre>
     * 
     *   --*
     *   | ____
     *   |/|  |
     *   /\|  |
     *   | |  |
     * *-+-|__|
     *   |
     *    \
     *     *
     * </pre>
     * 
     * @return
     */
    public LGraph getInLayerEdgesDownwardGraph() {
        Layer[] layers = makeLayers(2);
        LNode leftNode = addNodeToLayer(layers[0]);
        LNode[] rightNodes = addNodesToLayer(3, layers[1]);

        setFixedOrderConstraint(rightNodes[1]);

        addEastWestEdgeFromTo(leftNode, rightNodes[1]);
        addInLayerEdge(rightNodes[0], rightNodes[1], PortSide.WEST);
        addInLayerEdge(rightNodes[1], rightNodes[2], PortSide.WEST);

        return graph;
    }

    public LNode[][] getCurrentOrder() {
        LNode[][] nodeOrder = new LNode[graph.getLayers().size()][];
        List<Layer> layers = graph.getLayers();
        for (int i = 0; i < layers.size(); i++) {
            Layer layer = layers.get(i);
            List<LNode> nodes = layer.getNodes();
            nodeOrder[i] = new LNode[nodes.size()];
            for (int j = 0; j < nodes.size(); j++) {
                nodeOrder[i][j] = nodes.get(j);
            }
        }
        return nodeOrder;
    }

    private void setPortOrderFixed(final LNode node) {
        node.setProperty(LayoutOptions.PORT_CONSTRAINTS, PortConstraints.FIXED_ORDER);
    }

    private Layer[] makeLayers(final int amount) {
        Layer[] layers = new Layer[amount];
        for (int i = 0; i < layers.length; i++) {
            layers[i] = makeLayer();
        }
        return layers;
    }

    private MapPropertyHolder setFixedOrderConstraint(final LNode leftNode) {
        return leftNode.setProperty(LayoutOptions.PORT_CONSTRAINTS, PortConstraints.FIXED_ORDER);
    }

    private void addSouthEdgeBetweenPorts(final LPort southOrigin, final LPort westTarget,
            final Layer layerWithNSNodes) {
        LNode dummyNSNode = addNodeToLayer(layerWithNSNodes);
        dummyNSNode.setProperty(InternalProperties.NODE_TYPE, NodeType.NORTH_SOUTH_PORT);
        dummyNSNode.setProperty(InternalProperties.ORIGIN, southOrigin.getNode());
        LPort northPortDummyNode = addPortOnSide(dummyNSNode, PortSide.NORTH);
        LPort eastPortDummyNode = addPortOnSide(dummyNSNode, PortSide.EAST);
        northPortDummyNode.setProperty(InternalProperties.ORIGIN, southOrigin);
        addEdgeBetweenPorts(southOrigin, northPortDummyNode);
        addEdgeBetweenPorts(eastPortDummyNode, westTarget);
    }

    private void addInLayerEdge(final LNode nodeOne, final LNode nodeTwo, final PortSide portSide) {
        LPort portOne = addPortOnSide(nodeOne, portSide);
        LPort portTwo = addPortOnSide(nodeTwo, portSide);
        addEdgeBetweenPorts(portOne, portTwo);
    }

    private LNode[] addNodesToLayer(final int amountOfNodes, final Layer leftLayer) {
        LNode[] nodes = new LNode[amountOfNodes];
        for (int j = 0; j < amountOfNodes; j++) {
            nodes[j] = addNodeToLayer(leftLayer);
        }
        return nodes;
    }

    private Layer makeLayer() {
        List<Layer> layers = graph.getLayers();
        Layer layer = new Layer(graph);
        layers.add(layer);
        return layer;
    }

    private LNode addNodeToLayer(final Layer layer) {
        LNode node = new LNode(graph);
        node.setProperty(InternalProperties.NODE_TYPE, NodeType.NORMAL);
        node.setLayer(layer);
        node.id = nodeId++;
        return node;
    }

    private void addEastWestEdgeFromTo(final LNode left, final LNode right) {
        LPort leftPort = addPortOnSide(left, PortSide.EAST);
        LPort rightPort = addPortOnSide(right, PortSide.WEST);
        addEdgeBetweenPorts(leftPort, rightPort);
    }

    private void addEdgeBetweenPorts(final LPort from, final LPort to) {
        LEdge edge = new LEdge(graph);
        edge.setSource(from);
        edge.setTarget(to);
        edge.id = edgeId++;
    }

    private LPort addPortOnSide(final LNode node, final PortSide portSide) {
        LPort port = addPortTo(node);
        port.setSide(portSide);
        return port;
    }

    private LPort addPortTo(final LNode node) {
        LPort port = new LPort(graph);
        port.setNode(node);
        port.id = portId++;
        return port;
    }

}
