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
package de.cau.cs.kieler.klay.layered.intermediate.greedyswitch;

import java.util.List;

import com.google.common.collect.Lists;

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

/**
 * Use to create test graphs. CAUTION: Layout algorithm assumes the ports to be ordered in a
 * clockwise manner. You must think about this yourself when constructing a test graph. This means
 * that the methods for creating edges cannot be used in every case.
 * 
 * @author alan
 *
 */
public class TestGraphCreator {
    private int portId = 0;
    private int nodeId = 0;
    private final LGraph graph;
    private int edgeId = 0;

    /**
     * Makes a fancy test graph creator.
     */
    public TestGraphCreator() {
        graph = new LGraph();
    }

    /**
     * Creates empty graph.
     * 
     * @return return empty graph.
     */
    public LGraph getEmptyGraph() {
        return graph;
    }

    /**
     * Creates two nodes with no connection between them.
     * 
     * @return graph with two nodes with no connection between them.
     */
    public LGraph getTwoNodesNoConnectionGraph() {
        Layer layer = makeLayer();
        addNodeToLayer(layer);
        addNodeToLayer(layer);
        return graph;
    }

    // CHECKSTYLEOFF MagicNumber
    /**
     * <pre>
     * *  * 
     *  \/
     *  /\
     * *  *
     * .
     * </pre>
     * 
     * @return Graph of the form above.
     */
    public LGraph getCrossFormedGraph() {

        Layer leftLayer = makeLayer();
        Layer rightLayer = makeLayer();

        LNode topLeft = addNodeToLayer(leftLayer);
        LNode bottomLeft = addNodeToLayer(leftLayer);
        LNode topRight = addNodeToLayer(rightLayer);
        LNode bottomRight = addNodeToLayer(rightLayer);

        eastWestEdgeFromTo(topLeft, bottomRight);
        eastWestEdgeFromTo(bottomLeft, topRight);
        return graph;
    }

    /**
     * <pre>
     * *  *  <- this node must be ...
     *  \/
     *  /\
     * *  *  <- before this node.
     * .
     * </pre>
     * 
     * @return Graph of the form above.
     */
    public LGraph getCrossFormedGraphWithConstraintsInSecondLayer() {
        getCrossFormedGraph();
        Layer layerOne = graph.getLayers().get(1);
        LNode topNode = layerOne.getNodes().get(0);
        LNode secondNode = layerOne.getNodes().get(1);
        setInLayerOrderConstraint(topNode, secondNode);
        return graph;
    }

    /**
     * <pre>
     * this node must be.. -> *  *  <- and this node must be ...
     *                         \/
     *                         /\
     *    before this node -> *  *  <- before this node.
     * .
     * </pre>
     * 
     * @return Graph of the form above.
     */
    public LGraph getCrossFormedGraphConstraintsPreventAnySwitch() {
        Layer leftLayer = makeLayer();
        Layer rightLayer = makeLayer();

        LNode topLeft = addNodeToLayer(leftLayer);
        LNode bottomLeft = addNodeToLayer(leftLayer);
        LNode topRight = addNodeToLayer(rightLayer);
        LNode bottomRight = addNodeToLayer(rightLayer);

        eastWestEdgeFromTo(topLeft, bottomRight);
        eastWestEdgeFromTo(bottomLeft, topRight);
        setInLayerOrderConstraint(topRight, bottomRight);
        setInLayerOrderConstraint(topLeft, bottomLeft);
        return graph;
    }

    /**
     * Creates graph with only one node.
     * 
     * @return graph with only one node.
     */
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
     * .
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
        eastWestEdgeFromTo(middleNodes[1], rightNode);
        eastWestEdgeFromTo(leftNode, middleNodes[1]);
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
     * .
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
        eastWestEdgeFromTo(leftNode, rightNodes[2]);

        return graph;
    }

    /**
     * Constructs a cross formed graph with two edges between the corners
     * 
     * <pre>
     * *    * 
     *  \\//
     *  //\\
     * *    *
     * .
     * </pre>
     * 
     * @return Graph of the form above.
     */
    public LGraph getMultipleEdgesBetweenSameNodesGraph() {
        Layer leftLayer = makeLayer();
        Layer rightLayer = makeLayer();

        LNode topLeft = addNodeToLayer(leftLayer);
        LNode bottomLeft = addNodeToLayer(leftLayer);
        LNode topRight = addNodeToLayer(rightLayer);
        LNode bottomRight = addNodeToLayer(rightLayer);

        eastWestEdgeFromTo(topLeft, bottomRight);
        eastWestEdgeFromTo(topLeft, bottomRight);
        eastWestEdgeFromTo(bottomLeft, topRight);
        eastWestEdgeFromTo(bottomLeft, topRight);

        return graph;
    }

    /**
     * <pre>
     * *   * 
     *  \ / 
     * *-+-* 
     *  / \
     * *   *
     * .
     * </pre>
     * 
     * @return graph of the form above
     */
    public LGraph getCrossWithExtraEdgeInBetweenGraph() {
        Layer leftLayer = makeLayer();
        Layer rightLayer = makeLayer();

        LNode[] leftNodes = addNodesToLayer(3, leftLayer);
        LNode[] rightNodes = addNodesToLayer(3, rightLayer);

        eastWestEdgeFromTo(leftNodes[0], rightNodes[2]);
        eastWestEdgeFromTo(leftNodes[1], rightNodes[1]);
        eastWestEdgeFromTo(leftNodes[2], rightNodes[0]);

        return graph;
    }

    /**
     * Cross formed graph, but each node has three extra self loop edges.
     * 
     * <pre>
     * *  * 
     *  \/
     *  /\
     * *  *
     * .
     * </pre>
     * 
     * @return Graph of the form above.
     */
    public LGraph getCrossWithManySelfLoopsGraph() {
        LGraph selfLoopCrossGraph = getCrossFormedGraph();
        for (Layer layer : selfLoopCrossGraph) {
            for (LNode node : layer) {
                selfLoopOn(node, PortSide.EAST);
                selfLoopOn(node, PortSide.EAST);
                selfLoopOn(node, PortSide.EAST);
                selfLoopOn(node, PortSide.WEST);
                selfLoopOn(node, PortSide.WEST);
                selfLoopOn(node, PortSide.WEST);
            }
        }
        return selfLoopCrossGraph;
    }

    private void selfLoopOn(final LNode node, final PortSide side) {
        addEdgeBetweenPorts(addPortOnSide(node, side), addPortOnSide(node, side));
    }

    /**
     * <pre>
     * *\  --*
     *   \/ /
     * *-*===*
     *  + /
     * * * --*
     * .
     * </pre>
     * 
     * Port order not fixed.
     * 
     * @return Graph of the form above.
     */
    public LGraph getMoreComplexThreeLayerGraph() {
        Layer leftLayer = makeLayer();
        Layer middleLayer = makeLayer();
        Layer rightLayer = makeLayer();

        LNode[] leftNodes = addNodesToLayer(3, leftLayer);
        LNode[] middleNodes = addNodesToLayer(2, middleLayer);
        LNode[] rightNodes = addNodesToLayer(3, rightLayer);

        eastWestEdgeFromTo(leftNodes[0], middleNodes[0]);
        eastWestEdgeFromTo(leftNodes[1], middleNodes[0]);
        eastWestEdgeFromTo(leftNodes[1], middleNodes[1]);
        eastWestEdgeFromTo(leftNodes[2], middleNodes[0]);
        eastWestEdgeFromTo(middleNodes[0], rightNodes[0]);
        eastWestEdgeFromTo(middleNodes[0], rightNodes[1]);
        eastWestEdgeFromTo(middleNodes[0], rightNodes[1]);
        eastWestEdgeFromTo(middleNodes[1], rightNodes[0]);
        eastWestEdgeFromTo(middleNodes[1], rightNodes[2]);

        return graph;
    }

    /**
     * <pre>
     * ____  *
     * |  |\/
     * |__|/\
     *       *
     * .
     * </pre>
     * 
     * Port order fixed.
     * 
     * @return Graph of the form above.
     */
    public LGraph getFixedPortOrderGraph() {
        Layer leftLayer = makeLayer();
        Layer rightLayer = makeLayer();

        LNode leftNode = addNodeToLayer(leftLayer);
        leftNode.setProperty(LayoutOptions.PORT_CONSTRAINTS, PortConstraints.FIXED_ORDER);

        LNode rightTopNode = addNodeToLayer(rightLayer);
        LNode rightBottomNode = addNodeToLayer(rightLayer);

        eastWestEdgeFromTo(leftNode, rightBottomNode);
        eastWestEdgeFromTo(leftNode, rightTopNode);

        return graph;
    }

    /**
     * <pre>
     * ____  *
     * |  |\/
     * |__|/\
     *       *
     * .
     * </pre>
     * 
     * Port order not fixed
     * 
     * @return Graph of the form above.
     */
    public LGraph getGraphNoCrossingsDueToPortOrderNotFixed() {
        Layer leftLayer = makeLayer();
        Layer rightLayer = makeLayer();

        LNode leftNode = addNodeToLayer(leftLayer);

        LNode rightTopNode = addNodeToLayer(rightLayer);
        LNode rightBottomNode = addNodeToLayer(rightLayer);

        eastWestEdgeFromTo(leftNode, rightBottomNode);
        eastWestEdgeFromTo(leftNode, rightTopNode);

        return graph;
    }

    /**
     * <pre>
     * *  *---*
     *  \/
     *  /\
     * *  *---*
     * .
     * </pre>
     * 
     * @return Graph of the form above.
     */
    public LGraph getSwitchOnlyOneSided() {
        Layer[] layers = makeLayers(3);

        LNode[] leftNodes = addNodesToLayer(2, layers[0]);
        LNode[] middleNodes = addNodesToLayer(2, layers[1]);
        LNode[] rightNodes = addNodesToLayer(2, layers[2]);

        eastWestEdgeFromTo(leftNodes[0], middleNodes[1]);
        eastWestEdgeFromTo(leftNodes[1], middleNodes[0]);
        eastWestEdgeFromTo(middleNodes[0], rightNodes[0]);
        eastWestEdgeFromTo(middleNodes[1], rightNodes[1]);

        return graph;
    }

    /**
     * <pre>
     * *--*  *
     *     \/
     *     /\
     * *--*  *
     * .
     * </pre>
     * 
     * @return Graph of the form above.
     */
    public LGraph getSwitchOnlyEastOneSided() {
        Layer[] layers = makeLayers(3);

        LNode[] leftNodes = addNodesToLayer(2, layers[0]);
        LNode[] middleNodes = addNodesToLayer(2, layers[1]);
        LNode[] rightNodes = addNodesToLayer(2, layers[2]);

        eastWestEdgeFromTo(leftNodes[0], middleNodes[0]);
        eastWestEdgeFromTo(leftNodes[1], middleNodes[1]);
        eastWestEdgeFromTo(middleNodes[0], rightNodes[1]);
        eastWestEdgeFromTo(middleNodes[1], rightNodes[0]);

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
     * .
     * </pre>
     * 
     * @return Graph of the form above.
     */
    public LGraph getInLayerEdgesGraphWithCrossingsToFixedPortOrder() {
        Layer[] layers = makeLayers(2);

        LNode leftNode = addNodeToLayer(layers[0]);
        LNode[] rightNodes = addNodesToLayer(2, layers[1]);

        setPortOrderFixed(rightNodes[0]);

        // since we expect bottom up ordering of western ports, the order of adding edges is
        // important
        eastWestEdgeFromTo(leftNode, rightNodes[0]);
        addInLayerEdge(rightNodes[0], rightNodes[1], PortSide.WEST);
        eastWestEdgeFromTo(leftNode, rightNodes[0]);
        eastWestEdgeFromTo(leftNode, rightNodes[0]);
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
     * .
     * </pre>
     * 
     * With fixed Port Order.
     * 
     * @return Graph of the form above.
     */
    public LGraph getInLayerEdgesWithFixedPortOrderAndNormalEdgeCrossings() {
        Layer[] layer = makeLayers(2);
        LNode leftNode = addNodeToLayer(layer[0]);
        LNode[] rightNodes = addNodesToLayer(3, layer[1]);
        setFixedOrderConstraint(rightNodes[0]);
        eastWestEdgeFromTo(leftNode, rightNodes[0]);
        addInLayerEdge(rightNodes[0], rightNodes[2], PortSide.WEST);
        eastWestEdgeFromTo(leftNode, rightNodes[1]);

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
     * .
     * </pre>
     *
     * Port order not fixed.
     * 
     * @return Graph of the form above.
     */
    public LGraph getInLayerEdgesCrossingsButNoFixedOrder() {
        Layer[] layer = makeLayers(2);
        LNode[] leftNodes = addNodesToLayer(2, layer[0]);
        LNode[] rightNodes = addNodesToLayer(2, layer[1]);

        eastWestEdgeFromTo(leftNodes[0], rightNodes[0]);
        addInLayerEdge(rightNodes[0], rightNodes[1], PortSide.WEST);
        eastWestEdgeFromTo(leftNodes[1], rightNodes[1]);

        return graph;
    }

    /**
     * <pre>
     *      *
     *   //
     * *-++-* 
     *   || ____
     * *-++-|  |
     *    \\|  |
     *      |__|
     *     
     * .
     * </pre>
     *
     * Port order not fixed.
     * 
     * @return Graph of the form above.
     */
    public LGraph getInLayerEdgesCrossingsNoFixedOrderNoEdgeBetweenUpperAndLower() {
        Layer[] layer = makeLayers(2);
        LNode[] leftNodes = addNodesToLayer(2, layer[0]);
        LNode[] rightNodes = addNodesToLayer(3, layer[1]);

        eastWestEdgeFromTo(leftNodes[1], rightNodes[1]);
        addInLayerEdge(rightNodes[0], rightNodes[2], PortSide.WEST);
        addInLayerEdge(rightNodes[0], rightNodes[2], PortSide.WEST);
        eastWestEdgeFromTo(leftNodes[1], rightNodes[2]);

        return graph;
    }

    /**
     * <pre>
     *      *
     *     /____
     *     \|  |
     *    //|  |
     * *-++-|  |
     *   || |__|
     *   ||
     * *-++-*
     *    \\
     *      *
     * .
     * </pre>
     *
     * Port order not fixed.
     * 
     * @return Graph of the form above.
     */
    public LGraph getInLayerEdgesCrossingsNoFixedOrderNoEdgeBetweenUpperAndLowerUpsideDown() {
        Layer[] layer = makeLayers(2);
        LNode[] leftNodes = addNodesToLayer(2, layer[0]);
        LNode[] rightNodes = addNodesToLayer(4, layer[1]);

        eastWestEdgeFromTo(leftNodes[0], rightNodes[1]);
        addInLayerEdge(rightNodes[0], rightNodes[1], PortSide.WEST);
        addInLayerEdge(rightNodes[1], rightNodes[3], PortSide.WEST);
        addInLayerEdge(rightNodes[1], rightNodes[3], PortSide.WEST);
        eastWestEdgeFromTo(leftNodes[1], rightNodes[2]);

        return graph;
    }

    /**
     * <pre>
     *   --*--
     *   |   |
     * *-+-*-+-* 
     *   |   |
     *   --*--
     * .
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
        eastWestEdgeFromTo(middleNodes[1], rightNode);
        eastWestEdgeFromTo(leftNode, middleNodes[1]);
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
     * .
     * </pre>
     *
     * Port order fixed.
     * 
     * @return Graph of the form above.
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
     * .
     * </pre>
     *
     * Port order fixed.
     * 
     * @return Graph of the form above.
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
     * .
     * </pre>
     *
     * Port order fixed.
     * 
     * @return Graph of the form above.
     */
    public LGraph getMoreComplexInLayerGraph() {
        Layer[] layers = makeLayers(3);
        LNode[] leftNodes = addNodesToLayer(4, layers[0]);
        LNode[] middleNodes = addNodesToLayer(3, layers[1]);
        LNode rightNode = addNodeToLayer(layers[2]);
        setFixedOrderConstraint(middleNodes[0]);
        setFixedOrderConstraint(middleNodes[1]);

        eastWestEdgeFromTo(leftNodes[1], middleNodes[0]);

        eastWestEdgeFromTo(leftNodes[3], middleNodes[1]);
        eastWestEdgeFromTo(leftNodes[2], middleNodes[1]);
        addInLayerEdge(middleNodes[0], middleNodes[1], PortSide.WEST);
        eastWestEdgeFromTo(leftNodes[0], middleNodes[0]);
        addInLayerEdge(middleNodes[0], middleNodes[2], PortSide.WEST);

        addInLayerEdge(middleNodes[0], middleNodes[1], PortSide.EAST);
        eastWestEdgeFromTo(middleNodes[0], rightNode);

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
     * .
     * </pre>
     * 
     * Port order fixed.
     * 
     * @return Graph of the form above.
     */
    public LGraph getInLayerEdgesDownwardGraph() {
        Layer[] layers = makeLayers(2);
        LNode leftNode = addNodeToLayer(layers[0]);
        LNode[] rightNodes = addNodesToLayer(3, layers[1]);

        setFixedOrderConstraint(rightNodes[1]);

        eastWestEdgeFromTo(leftNode, rightNodes[1]);
        addInLayerEdge(rightNodes[0], rightNodes[1], PortSide.WEST);
        addInLayerEdge(rightNodes[1], rightNodes[2], PortSide.WEST);

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
     * .
     * </pre>
     * 
     * Port order not fixed.
     * 
     * @return Graph of the form above.
     */
    public LGraph getInLayerEdgesDownwardGraphNoFixedOrder() {
        Layer[] layers = makeLayers(2);
        LNode leftNode = addNodeToLayer(layers[0]);
        LNode[] rightNodes = addNodesToLayer(3, layers[1]);

        eastWestEdgeFromTo(leftNode, rightNodes[1]);
        addInLayerEdge(rightNodes[0], rightNodes[1], PortSide.WEST);
        addInLayerEdge(rightNodes[1], rightNodes[2], PortSide.WEST);

        return graph;
    }

    /**
     * <pre>
     * *==*  *
     *     \/
     *     /\
     * *==*  *
     * .
     * </pre>
     * 
     * First Layer and last layer in fixed order.
     * 
     * @return graph of the form above.
     */
    public LGraph getGraphWhichCouldBeWorsenedBySwitch() {
        Layer[] layers = makeLayers(3);
        LNode[] leftNodes = addNodesToLayer(2, layers[0]);
        LNode[] middleNodes = addNodesToLayer(2, layers[1]);
        LNode[] rightNodes = addNodesToLayer(2, layers[2]);

        setInLayerOrderConstraint(leftNodes[0], leftNodes[1]);
        setInLayerOrderConstraint(rightNodes[0], rightNodes[1]);

        eastWestEdgeFromTo(leftNodes[0], middleNodes[0]);
        eastWestEdgeFromTo(leftNodes[0], middleNodes[0]);
        eastWestEdgeFromTo(leftNodes[1], middleNodes[1]);
        eastWestEdgeFromTo(leftNodes[1], middleNodes[1]);
        eastWestEdgeFromTo(middleNodes[0], rightNodes[1]);
        eastWestEdgeFromTo(middleNodes[1], rightNodes[0]);

        return graph;
    }

    /**
     * <pre>
     *    --*
     *    |  
     *    |/*
     *   /|  
     * *+--*  <-In-layer and normal edge into port.
     *   \ 
     *    \
     *     \
     *      *
     * .
     * </pre>
     * 
     * @return Graph of the form above.
     */
    public LGraph getInLayerEdgesMultipleEdgesIntoSinglePort() {
        Layer layerTwo = makeLayer();
        LNode leftNode = addNodeToLayer(layerTwo);
        Layer layerOne = makeLayer();
        LNode[] rightNodes = addNodesToLayer(4, layerOne);

        addInLayerEdge(rightNodes[1], rightNodes[3], PortSide.WEST);

        LPort leftPort = addPortOnSide(leftNode, PortSide.EAST);
        LPort rightTopPort = addPortOnSide(rightNodes[0], PortSide.WEST);
        LPort rightMiddlePort = addPortOnSide(rightNodes[2], PortSide.WEST);
        addEdgeBetweenPorts(leftPort, rightMiddlePort);
        addEdgeBetweenPorts(rightTopPort, rightMiddlePort);
        return graph;
    }

    /**
     * <pre>
     *   --*
     *   |  
     *   |/*
     *  /|  
     * | --*
     *  \ 
     *   \
     *    \
     *     *
     * .
     * </pre>
     * 
     * @return Graph of the form above.
     */
    public LGraph getOneLayerWithInLayerCrossings() {
        Layer layer = makeLayer();
        LNode[] nodes = addNodesToLayer(4, layer);
        addInLayerEdge(nodes[0], nodes[2], PortSide.WEST);
        addInLayerEdge(nodes[1], nodes[3], PortSide.WEST);
        return graph;
    }

    /**
     * <pre>
     * |---*
     * |    
     * | --*
     * | |  
     * | --*
     *  \ 
     *   \
     *    \
     *     *
     * .
     * </pre>
     * 
     * @return Graph of the form above.
     */
    public LGraph getInLayerOneLayerNoCrossings() {
        Layer layer = makeLayer();
        LNode[] nodes = addNodesToLayer(4, layer);
        addInLayerEdge(nodes[0], nodes[3], PortSide.WEST);
        addInLayerEdge(nodes[1], nodes[2], PortSide.WEST);
        return graph;
    }

    /**
     * <pre>
     *     * <-- this ...
     *    /
     * *-+-* <-- cannot switch with this
     *  / _|__
     * *  |  |
     *    |__|
     * 
     * .
     * </pre>
     * 
     * @return graph of the form above.
     */
    public LGraph getNodesInDifferentLayoutUnitsPreventSwitch() {
        Layer[] layers = makeLayers(2);
        LNode[] leftNodes = addNodesToLayer(2, layers[0]);
        LNode[] rightNodes = addNodesToLayer(3, layers[1]);

        eastWestEdgeFromTo(leftNodes[0], rightNodes[1]);
        eastWestEdgeFromTo(leftNodes[1], rightNodes[0]);

        LPort southPort = addPortOnSide(rightNodes[1], PortSide.SOUTH);
        LPort northPort = addPortOnSide(rightNodes[2], PortSide.NORTH);

        addEdgeBetweenPorts(southPort, northPort);

        rightNodes[1].setProperty(InternalProperties.IN_LAYER_LAYOUT_UNIT, rightNodes[2]);
        rightNodes[2].setProperty(InternalProperties.IN_LAYER_LAYOUT_UNIT, rightNodes[2]);

        return graph;
    }

    /**
     * <pre>
     *  ---*
     *  |
     *  | ____
     * *+-|  |
     * *+-|  |
     *   \|__|
     * Port order not fixed.
     * .
     * </pre>
     * 
     * @return Graph of the form above.
     */
    public LGraph multipleInBetweenLayerEdgesIntoNodeWithNoFixedPortOrder() {
        Layer leftLayer = makeLayer();
        LNode[] leftNodes = addNodesToLayer(2, leftLayer);
        Layer rightLayer = makeLayer();
        LNode[] rightNodes = addNodesToLayer(2, rightLayer);

        addInLayerEdge(rightNodes[0], rightNodes[1], PortSide.WEST);
        eastWestEdgeFromTo(leftNodes[0], rightNodes[1]);
        eastWestEdgeFromTo(leftNodes[0], rightNodes[1]);
        return graph;
    }

    /**
     * <pre>
     *  ---*
     *  |
     *  | ____
     * *+-|  |
     * *+-|  |
     *  | |__|
     *   \
     *    *
     * Port order not fixed.
     * .
     * </pre>
     * 
     * @return Graph of the form above.
     */
    public LGraph multipleInBetweenLayerEdgesIntoNodeWithNoFixedPortOrderCauseCrossings() {
        Layer leftLayer = makeLayer();
        LNode[] leftNodes = addNodesToLayer(2, leftLayer);
        Layer rightLayer = makeLayer();
        LNode[] rightNodes = addNodesToLayer(3, rightLayer);

        addInLayerEdge(rightNodes[0], rightNodes[2], PortSide.WEST);
        eastWestEdgeFromTo(leftNodes[0], rightNodes[1]);
        eastWestEdgeFromTo(leftNodes[0], rightNodes[1]);
        return graph;
    }

    /**
     * <pre>
     *         ____
     *     *-->|  |
     *     |   |  |
     *   *-+-->|__|
     *   | |
     *  _|_|_
     *  |   |
     *  |___|
     *  .
     * </pre>
     * 
     * @return Graph of the form above.
     */
    public LGraph getNorthSouthUpwardCrossingGraph() {

        LNode[] leftNodes = addNodesToLayer(3, makeLayer());
        LNode rightNode = addNodeToLayer(makeLayer());
        leftNodes[0].setProperty(InternalProperties.ORIGIN, leftNodes[2]);
        leftNodes[1].setProperty(InternalProperties.ORIGIN, leftNodes[2]);
        setFixedOrderConstraint(leftNodes[2]);
        setFixedOrderConstraint(rightNode);

        LPort leftBottomNodeLeftPort = addPortOnSide(leftNodes[2], PortSide.NORTH);

        LPort leftBottomNodeRightPort = addPortOnSide(leftNodes[2], PortSide.NORTH);

        // ports are added in clockwise fashion, so add bottom port first.
        LPort leftMiddleNodeRightPort = addPortOnSide(leftNodes[1], PortSide.EAST);
        LPort rightNodeBottomPort = addPortOnSide(rightNode, PortSide.WEST);
        addEdgeBetweenPorts(leftMiddleNodeRightPort, rightNodeBottomPort);
        leftMiddleNodeRightPort.setProperty(InternalProperties.ORIGIN, leftBottomNodeLeftPort);

        LPort leftTopNodeRightPort = addPortOnSide(leftNodes[0], PortSide.EAST);
        LPort rightNodeTopPort = addPortOnSide(rightNode, PortSide.WEST);
        addEdgeBetweenPorts(leftTopNodeRightPort, rightNodeTopPort);
        leftTopNodeRightPort.setProperty(InternalProperties.ORIGIN, leftBottomNodeRightPort);

        setAsNorthSouthNode(leftNodes[0]);
        setAsNorthSouthNode(leftNodes[1]);
        leftNodes[1].setProperty(InternalProperties.ORIGIN, leftNodes[2]);

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
     * .
     * </pre>
     * 
     * @return Graph of the form above.
     */
    public LGraph getThreeLayerNorthSouthCrossingGraph() {
        LNode leftNode = addNodeToLayer(makeLayer());
        LNode[] middleNodes = addNodesToLayer(3, makeLayer());
        LNode rightNode = addNodeToLayer(makeLayer());

        setFixedOrderConstraint(middleNodes[0]);
        setFixedOrderConstraint(rightNode);

        addNorthSouthEdge(PortSide.SOUTH, middleNodes[0], middleNodes[2], rightNode, false);
        addNorthSouthEdge(PortSide.SOUTH, middleNodes[0], middleNodes[1], rightNode, false);

        eastWestEdgeFromTo(leftNode, middleNodes[0]);

        return graph;
    }

    /**
     * <pre>
     *      ______
     *  *   |____|
     *       |  |  
     *       *--+--*
     *          | 
     *          *--*
     * .
     * </pre>
     * 
     * @return Graph of the form above.
     */
    public LGraph getThreeLayerNorthSouthCrossingShouldSwitchGraph() {
        addNodeToLayer(makeLayer());
        LNode[] middleNodes = addNodesToLayer(3, makeLayer());
        LNode[] rightNodes = addNodesToLayer(2, makeLayer());

        setFixedOrderConstraint(middleNodes[0]);

        setAsNorthSouthNode(middleNodes[1]);
        setAsNorthSouthNode(middleNodes[2]);
        middleNodes[2].setProperty(InternalProperties.ORIGIN, middleNodes[0]);
        middleNodes[1].setProperty(InternalProperties.ORIGIN, middleNodes[0]);

        // ports are added in clockwise fashion, so add bottom port first.
        LPort leftBottomNodeRightPort = addPortOnSide(middleNodes[2], PortSide.EAST);
        LPort rightNodeBottomPort = addPortOnSide(rightNodes[1], PortSide.WEST);
        addEdgeBetweenPorts(leftBottomNodeRightPort, rightNodeBottomPort);
        LPort leftTopNodeRightPort = addPortOnSide(middleNodes[0], PortSide.SOUTH);
        leftBottomNodeRightPort.setProperty(InternalProperties.ORIGIN, leftTopNodeRightPort);

        LPort leftMiddleNodeRightPort = addPortOnSide(middleNodes[1], PortSide.EAST);
        LPort rightNodeTopPort = addPortOnSide(rightNodes[0], PortSide.WEST);
        addEdgeBetweenPorts(leftMiddleNodeRightPort, rightNodeTopPort);
        LPort leftTopNodeLeftPort = addPortOnSide(middleNodes[0], PortSide.SOUTH);
        leftMiddleNodeRightPort.setProperty(InternalProperties.ORIGIN, leftTopNodeLeftPort);

        return graph;
    }

    /**
     * <pre>
     *   *
     *   | 
     * *-+*--*
     *   |
     *   *---*
     * </pre>
     * 
     * .
     * 
     * @return Graph of the form above.
     */
    public LGraph getSouthernNorthSouthDummyEdgeCrossingGraph() {
        LNode leftNode = addNodeToLayer(makeLayer());
        LNode[] middleNodes = addNodesToLayer(3, makeLayer());
        LNode[] rightNodes = addNodesToLayer(2, makeLayer());

        eastWestEdgeFromTo(leftNode, middleNodes[1]);
        eastWestEdgeFromTo(middleNodes[1], rightNodes[0]);
        middleNodes[1].setProperty(InternalProperties.NODE_TYPE, NodeType.LONG_EDGE);

        addNorthSouthEdge(PortSide.SOUTH, middleNodes[0], middleNodes[2], rightNodes[1], true);
        return graph;
    }

    /**
     * <pre>
     *   *---*
     *   | 
     * *-+*--*
     *   |
     *   *
     * </pre>
     * 
     * .
     * 
     * @return Graph of the form above.
     */
    public LGraph getNorthernNorthSouthDummyEdgeCrossingGraph() {
        LNode leftNode = addNodeToLayer(makeLayer());
        LNode[] middleNodes = addNodesToLayer(3, makeLayer());
        LNode[] rightNodes = addNodesToLayer(2, makeLayer());

        eastWestEdgeFromTo(leftNode, middleNodes[1]);
        eastWestEdgeFromTo(middleNodes[1], rightNodes[1]);
        middleNodes[1].setProperty(InternalProperties.NODE_TYPE, NodeType.LONG_EDGE);

        addNorthSouthEdge(PortSide.NORTH, middleNodes[2], middleNodes[0], rightNodes[0], true);
        return graph;

    }

    /**
     * <pre>
     *    ______
     *    |     |
     *    |_p_p_|
     *  
     * *-----*-------*
     * 
     * p is port. The node in the middle is a long edge dummy.
     * </pre>
     * 
     * @return Graph of the form above.
     */
    public LGraph getLongEdgeDummyAndNormalNodeWithUnusedPortsOnSouthernSide() {
        LNode leftNode = addNodeToLayer(makeLayer());
        LNode[] middleNodes = addNodesToLayer(2, makeLayer());
        LNode rightNode = addNodeToLayer(makeLayer());

        eastWestEdgeFromTo(leftNode, middleNodes[1]);
        eastWestEdgeFromTo(middleNodes[1], rightNode);
        middleNodes[1].setProperty(InternalProperties.NODE_TYPE, NodeType.LONG_EDGE);

        addPortOnSide(middleNodes[0], PortSide.SOUTH);
        addPortOnSide(middleNodes[0], PortSide.SOUTH);
        return graph;

    }

    /**
     * <pre>
     * *-----*-------*
     *    __p_p__
     *    |     |
     *    |_____|
     *  
     * 
     * p is port. The node in the middle is a long edge dummy.
     * </pre>
     * 
     * @return Graph of the form above.
     */
    public LGraph getLongEdgeDummyAndNormalNodeWithUnusedPortsOnNorthernSide() {
        LNode leftNode = addNodeToLayer(makeLayer());
        LNode[] middleNodes = addNodesToLayer(2, makeLayer());
        LNode rightNode = addNodeToLayer(makeLayer());

        eastWestEdgeFromTo(leftNode, middleNodes[0]);
        eastWestEdgeFromTo(middleNodes[0], rightNode);
        middleNodes[0].setProperty(InternalProperties.NODE_TYPE, NodeType.LONG_EDGE);

        addPortOnSide(middleNodes[1], PortSide.NORTH);
        addPortOnSide(middleNodes[1], PortSide.NORTH);
        return graph;

    }

    /**
     * <pre>
     *   ----*
     *   |---*
     *   ||  
     * *-++--*
     *   ||
     *  ----
     *  |__|
     *   ||
     * *-++--*
     *   ||--*
     *   |---*
     * </pre>
     * 
     * .
     * 
     * @return Graph of the form above.
     */
    public LGraph getMultipleNorthSouthAndLongEdgeDummiesOnBothSides() {
        LNode[] leftNodes = addNodesToLayer(2, makeLayer());
        LNode[] middleNodes = addNodesToLayer(7, makeLayer());
        LNode[] rightNodes = addNodesToLayer(6, makeLayer());

        eastWestEdgeFromTo(leftNodes[0], middleNodes[2]);
        eastWestEdgeFromTo(leftNodes[1], middleNodes[4]);
        eastWestEdgeFromTo(middleNodes[2], rightNodes[2]);
        eastWestEdgeFromTo(middleNodes[4], rightNodes[4]);
        middleNodes[2].setProperty(InternalProperties.NODE_TYPE, NodeType.LONG_EDGE);
        middleNodes[4].setProperty(InternalProperties.NODE_TYPE, NodeType.LONG_EDGE);

        addNorthSouthEdge(PortSide.NORTH, middleNodes[3], middleNodes[0], rightNodes[0], false);
        addNorthSouthEdge(PortSide.NORTH, middleNodes[3], middleNodes[1], rightNodes[1], false);
        addNorthSouthEdge(PortSide.SOUTH, middleNodes[3], middleNodes[5], rightNodes[4], false);
        addNorthSouthEdge(PortSide.SOUTH, middleNodes[3], middleNodes[6], rightNodes[5], false);
        return graph;

    }

    /**
     * <pre>
     *     ______
     *     |____|
     *      |  |  ____
     *      *--+--|  |
     *         |  |  |
     *         *--|__|
     * .
     * </pre>
     * 
     * @return Graph of the form above.
     */
    public LGraph getNorthSouthCrossingGraph() {
        LNode[] leftNodes = addNodesToLayer(3, makeLayer());
        LNode rightNode = addNodeToLayer(makeLayer());

        setFixedOrderConstraint(leftNodes[0]);
        setFixedOrderConstraint(rightNode);

        setAsNorthSouthNode(leftNodes[1]);
        setAsNorthSouthNode(leftNodes[2]);
        leftNodes[2].setProperty(InternalProperties.ORIGIN, leftNodes[0]);
        leftNodes[1].setProperty(InternalProperties.ORIGIN, leftNodes[0]);

        // ports are added in clockwise fashion, so add bottom port first.
        LPort leftBottomNodeRightPort = addPortOnSide(leftNodes[2], PortSide.EAST);
        LPort rightNodeBottomPort = addPortOnSide(rightNode, PortSide.WEST);
        addEdgeBetweenPorts(leftBottomNodeRightPort, rightNodeBottomPort);
        LPort leftTopNodeRightPort = addPortOnSide(leftNodes[0], PortSide.SOUTH);
        leftBottomNodeRightPort.setProperty(InternalProperties.ORIGIN, leftTopNodeRightPort);

        LPort leftMiddleNodeRightPort = addPortOnSide(leftNodes[1], PortSide.EAST);
        LPort rightNodeTopPort = addPortOnSide(rightNode, PortSide.WEST);
        addEdgeBetweenPorts(leftMiddleNodeRightPort, rightNodeTopPort);
        LPort leftTopNodeLeftPort = addPortOnSide(leftNodes[0], PortSide.SOUTH);
        leftMiddleNodeRightPort.setProperty(InternalProperties.ORIGIN, leftTopNodeLeftPort);

        return graph;
    }

    /**
     * <pre>
     *     ______
     *     |____|
     *      |  |___
     *   ___|      *
     *  *
     * </pre>
     * 
     * .
     * 
     * @return Graph of the form above.
     */
    public LGraph getSouthernNorthSouthGraphEdgesFromEastAndWestNoCrossings() {
        LNode leftNode = addNodeToLayer(makeLayer());
        LNode[] middleNodes = addNodesToLayer(3, makeLayer());
        LNode rightNode = addNodeToLayer(makeLayer());

        setFixedOrderConstraint(middleNodes[0]);

        addNorthSouthEdge(PortSide.SOUTH, middleNodes[0], middleNodes[1], rightNode, false);
        addNorthSouthEdge(PortSide.SOUTH, middleNodes[0], middleNodes[2], leftNode, true);
        return graph;
    }

    /**
     * <pre>
     * *-- 
     *   | ---*
     *   | |
     *  _|_|_
     *  |   |
     *  |___|
     *  .
     * </pre>
     * 
     * @return Graph of the form above.
     */
    public LGraph getNorthernNorthSouthGraphEdgesFromEastAndWestNoCrossings() {
        LNode leftNode = addNodeToLayer(makeLayer());
        LNode[] middleNodes = addNodesToLayer(3, makeLayer());
        LNode rightNode = addNodeToLayer(makeLayer());

        setFixedOrderConstraint(middleNodes[2]);

        addNorthSouthEdge(PortSide.NORTH, middleNodes[2], middleNodes[0], leftNode, true);
        addNorthSouthEdge(PortSide.NORTH, middleNodes[2], middleNodes[1], rightNode, false);

        return graph;
    }

    /**
     * <pre>
     *     ______
     *     |____|
     *      |  |
     *   *--+--|   
     *      |_____*
     * 
     * </pre>
     * 
     * .
     * 
     * @return Graph of the form above.
     */
    public LGraph getNorthSouthEdgesFromEastAndWestAndCross() {
        LNode leftNode = addNodeToLayer(makeLayer());
        LNode[] middleNodes = addNodesToLayer(3, makeLayer());
        LNode rightNode = addNodeToLayer(makeLayer());

        setFixedOrderConstraint(middleNodes[0]);

        // ports are added in clockwise fashion!
        addNorthSouthEdge(PortSide.SOUTH, middleNodes[0], middleNodes[1], leftNode, true);
        addNorthSouthEdge(PortSide.SOUTH, middleNodes[0], middleNodes[2], rightNode, false);

        return graph;
    }

    /**
     * <pre>
     *     ______
     *     |____|
     *      |  |
     *      |  ---* 
     *      |_____*
     * 
     * </pre>
     * 
     * .
     * 
     * @return Graph of the form above.
     */
    public LGraph getSouthernNorthSouthEdgesBothToEast() {
        LNode[] middleNodes = addNodesToLayer(3, makeLayer());
        LNode[] rightNodes = addNodesToLayer(2, makeLayer());

        setFixedOrderConstraint(middleNodes[0]);

        // ports are added in clockwise fashion!
        addNorthSouthEdge(PortSide.SOUTH, middleNodes[0], middleNodes[1], rightNodes[0], false);
        addNorthSouthEdge(PortSide.SOUTH, middleNodes[0], middleNodes[2], rightNodes[1], false);

        return graph;
    }

    /**
     * <pre>
     *     ______
     *     |____|
     *      |  |
     *   *--+--|   
     *   *--|
     * 
     * </pre>
     * 
     * .
     * 
     * @return Graph of the form above.
     */
    public LGraph getNorthSouthSouthernTwoWesternEdges() {
        LNode[] leftNodes = addNodesToLayer(2, makeLayer());
        LNode[] middleNodes = addNodesToLayer(3, makeLayer());

        setFixedOrderConstraint(middleNodes[0]);

        // ports are added in clockwise fashion!
        addNorthSouthEdge(PortSide.SOUTH, middleNodes[0], middleNodes[1], leftNodes[0], true);
        addNorthSouthEdge(PortSide.SOUTH, middleNodes[0], middleNodes[2], leftNodes[1], true);

        return graph;
    }

    /**
     * <pre>
     * 
     * *---|
     *     |   
     * *-- |
     *   | |
     *  _|_|_
     *  |   |
     *  |___|
     *  .
     * </pre>
     * 
     * @return Graph of the form above.
     */
    public LGraph getNorthSouthNorthernWesternEdges() {
        LNode[] leftNodes = addNodesToLayer(2, makeLayer());
        LNode[] middleNodes = addNodesToLayer(3, makeLayer());

        setFixedOrderConstraint(middleNodes[2]);

        // ports are added in clockwise fashion!
        addNorthSouthEdge(PortSide.NORTH, middleNodes[2], middleNodes[1], leftNodes[1], true);
        addNorthSouthEdge(PortSide.NORTH, middleNodes[2], middleNodes[0], leftNodes[0], true);

        return graph;
    }

    /**
     * <pre>
     * 
     * *----
     *     |   
     *   --+--*
     *   | |
     *  _|_|_
     *  |   |
     *  |___|
     *  .
     * </pre>
     * 
     * @return Graph of the form above.
     */
    public LGraph getNorthSouthNorthernEasternPortToWestWesternPortToEast() {
        LNode leftNode = addNodeToLayer(makeLayer());
        LNode[] middleNodes = addNodesToLayer(3, makeLayer());
        LNode rightNode = addNodeToLayer(makeLayer());

        setFixedOrderConstraint(middleNodes[2]);

        // ports are added in clockwise fashion!
        addNorthSouthEdge(PortSide.NORTH, middleNodes[2], middleNodes[1], rightNode, false);
        addNorthSouthEdge(PortSide.NORTH, middleNodes[2], middleNodes[0], leftNode, true);

        return graph;
    }

    /**
     * <pre>
     *     ______
     *     |____|
     *      |  |
     *      |--+--*   
     *         |
     *   *-----|
     * 
     * </pre>
     * 
     * .
     * 
     * @return Graph of the form above.
     */
    public LGraph getNorthSouthSouthernWesternPortToEastAndEasternPortToWest() {
        LNode leftNode = addNodeToLayer(makeLayer());
        LNode[] middleNodes = addNodesToLayer(3, makeLayer());
        LNode rightNode = addNodeToLayer(makeLayer());

        setFixedOrderConstraint(middleNodes[0]);

        // ports are added in clockwise fashion!
        addNorthSouthEdge(PortSide.SOUTH, middleNodes[0], middleNodes[2], leftNode, true);
        addNorthSouthEdge(PortSide.SOUTH, middleNodes[0], middleNodes[1], rightNode, false);

        return graph;
    }

    /**
     * <pre>
     *  ______
     *  |____|
     *    |   *
     *    *-\/     
     *   *--/\
     *  _|___ *
     *  |   |
     *  |___|
     *  .
     * </pre>
     * 
     * .
     * 
     * @return Graph of the form above.
     */
    public LGraph getGraphWhereLayoutUnitPreventsSwitch() {
        LNode[] leftNodes = addNodesToLayer(4, makeLayer());
        LNode[] rightNodes = addNodesToLayer(2, makeLayer());

        setFixedOrderConstraint(leftNodes[0]);
        setFixedOrderConstraint(leftNodes[3]);

        // ports are added in clockwise fashion!
        addNorthSouthEdge(PortSide.SOUTH, leftNodes[0], leftNodes[1], rightNodes[1], false);
        addNorthSouthEdge(PortSide.NORTH, leftNodes[3], leftNodes[2], rightNodes[0], false);
        return graph;
    }

    /**
     * <pre>
     * --==*
     * | |
     * *-+-*
     *  /\ 
     * *--+* 
     *     *
     * </pre>
     * 
     * .
     * 
     * @return Graph of the form above.
     */
    public LGraph shouldSwitchThreeTimesGraph() { // TODO-alan rename.
        LNode[] leftNodes = addNodesToLayer(2, makeLayer());
        LNode[] rightNodes = addNodesToLayer(4, makeLayer());

        eastWestEdgeFromTo(leftNodes[1], rightNodes[0]);
        eastWestEdgeFromTo(leftNodes[1], rightNodes[2]);

        eastWestEdgeFromTo(leftNodes[0], rightNodes[0]);
        eastWestEdgeFromTo(leftNodes[0], rightNodes[1]);
        eastWestEdgeFromTo(leftNodes[0], rightNodes[3]);

        return graph;
    }

    /**
     * <pre>
     * *----*
     *  \\ 
     *   \--*
     *    --*
     * *---/
     *  \---*
     * </pre>
     * 
     * .
     * 
     * @return Graph of the form above.
     */
    public LGraph getSwitchedProblemGraph() { // TODO-alan rename.
        LNode[] leftNodes = addNodesToLayer(2, makeLayer());
        LNode[] rightNodes = addNodesToLayer(4, makeLayer());

        eastWestEdgeFromTo(leftNodes[1], rightNodes[2]);
        eastWestEdgeFromTo(leftNodes[1], rightNodes[3]);

        eastWestEdgeFromTo(leftNodes[0], rightNodes[0]);
        eastWestEdgeFromTo(leftNodes[0], rightNodes[1]);
        eastWestEdgeFromTo(leftNodes[0], rightNodes[2]);

        return graph;
    }

    /**
     * <pre>
     * *   *<- Into same port
     *  \//
     *  //\
     * *   *
     * </pre>
     * 
     * .
     * 
     * @return Graph of the form above.
     */
    public LGraph twoEdgesIntoSamePort() {
        Layer leftLayer = makeLayer();
        Layer rightLayer = makeLayer();

        LNode topLeft = addNodeToLayer(leftLayer);
        LNode bottomLeft = addNodeToLayer(leftLayer);
        LNode topRight = addNodeToLayer(rightLayer);
        LNode bottomRight = addNodeToLayer(rightLayer);

        eastWestEdgeFromTo(topLeft, bottomRight);
        LPort bottomLeftFirstPort = addPortOnSide(bottomLeft, PortSide.EAST);
        LPort bottomLeftSecondPort = addPortOnSide(bottomLeft, PortSide.EAST);
        LPort topRightFirstPort = addPortOnSide(topRight, PortSide.WEST);
        LPort topRightSecondPort = addPortOnSide(topRight, PortSide.WEST);

        addEdgeBetweenPorts(bottomLeftFirstPort, topRightFirstPort);
        addEdgeBetweenPorts(bottomLeftSecondPort, topRightSecondPort);
        return graph;
    }

    /**
     * <pre>
     * *---* <- Into same port
     *   /
     *  /
     * *---*
     * </pre>
     * 
     * .
     * 
     * @return Graph of the form above.
     */
    public LGraph twoEdgesIntoSamePortCrossesWhenSwitched() {
        Layer leftLayer = makeLayer();
        Layer rightLayer = makeLayer();

        LNode topLeft = addNodeToLayer(leftLayer);
        LNode bottomLeft = addNodeToLayer(leftLayer);
        LNode topRight = addNodeToLayer(rightLayer);
        LNode bottomRight = addNodeToLayer(rightLayer);

        LPort topRightPort = addPortOnSide(topRight, PortSide.WEST);
        LPort bottomLeftPort = addPortOnSide(bottomLeft, PortSide.EAST);
        addEdgeBetweenPorts(bottomLeftPort, topRightPort);

        LPort topLeftPort = addPortOnSide(topLeft, PortSide.EAST);
        addEdgeBetweenPorts(topLeftPort, topRightPort);

        eastWestEdgeFromTo(bottomLeft, bottomRight);

        return graph;
    }

    /**
     * <pre>
     * *---* <- Into same port
     *   /
     *  /
     * *---*
     * </pre>
     * 
     * .
     * 
     * @return Graph of the form above.
     */
    public LGraph twoEdgesIntoSamePortResolvesCrossingWhenSwitched() {
        Layer leftLayer = makeLayer();
        Layer rightLayer = makeLayer();

        LNode topLeft = addNodeToLayer(leftLayer);
        LNode bottomLeft = addNodeToLayer(leftLayer);
        LNode topRight = addNodeToLayer(rightLayer);
        LNode bottomRight = addNodeToLayer(rightLayer);

        LPort topLeftPort = addPortOnSide(topLeft, PortSide.EAST);
        LPort bottomLeftPort = addPortOnSide(bottomLeft, PortSide.EAST);
        LPort bottomRightPort = addPortOnSide(bottomRight, PortSide.WEST);

        addEdgeBetweenPorts(topLeftPort, bottomRightPort);
        addEdgeBetweenPorts(bottomLeftPort, bottomRightPort);

        eastWestEdgeFromTo(bottomLeft, topRight);

        return graph;
    }

    /**
     * <pre>
     * *---* <- into same port
     *   /
     *  /
     * *---*
     * ^
     * |
     * Two edges into same port.
     * </pre>
     * 
     * @return Graph of the form above.
     */
    public LGraph twoEdgesIntoSamePortFromEastWithFixedPortOrder() {
        Layer leftLayer = makeLayer();
        Layer rightLayer = makeLayer();

        LNode topLeft = addNodeToLayer(leftLayer);
        LNode bottomLeft = addNodeToLayer(leftLayer);
        LNode topRight = addNodeToLayer(rightLayer);
        LNode bottomRight = addNodeToLayer(rightLayer);

        setFixedOrderConstraint(bottomLeft);
        setFixedOrderConstraint(topRight);

        LPort topLeftPort = addPortOnSide(topLeft, PortSide.EAST);
        LPort bottomLeftPort = addPortOnSide(bottomLeft, PortSide.EAST);
        LPort topRightPort = addPortOnSide(topRight, PortSide.WEST);
        LPort bottomRightPort = addPortOnSide(bottomRight, PortSide.WEST);

        addEdgeBetweenPorts(bottomLeftPort, bottomRightPort);
        addEdgeBetweenPorts(bottomLeftPort, topRightPort);
        addEdgeBetweenPorts(topLeftPort, topRightPort);

        return graph;
    }

    /**
     * Returns the nodes in the graph as two-dimensional array of LNodes.
     * 
     * @return graph as LNode[][].
     */
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

    private void setAsNorthSouthNode(final LNode node) {
        node.setProperty(InternalProperties.NODE_TYPE, NodeType.NORTH_SOUTH_PORT);
    }

    private void addNorthSouthEdge(final PortSide side, final LNode nodeWithNSPorts,
            final LNode northSouthNode, final LNode nodeWithEastWestPorts,
            final boolean nodeWithEastWestPortsIsOrigin) {
        boolean normalNodeEastOfNsPortNode =
                nodeWithEastWestPorts.getLayer().getIndex() < nodeWithNSPorts.getLayer().getIndex();
        PortSide direction = normalNodeEastOfNsPortNode ? PortSide.WEST : PortSide.EAST;

        PortSide targetNodePortSide = direction == PortSide.WEST ? PortSide.EAST : PortSide.WEST;
        LPort normalNodePort = addPortOnSide(nodeWithEastWestPorts, targetNodePortSide);

        LPort nsNodePort = addPortOnSide(northSouthNode, direction);

        if (nodeWithEastWestPortsIsOrigin) {
            addEdgeBetweenPorts(normalNodePort, nsNodePort);
        } else {
            addEdgeBetweenPorts(nsNodePort, normalNodePort);
        }

        northSouthNode.setProperty(InternalProperties.IN_LAYER_LAYOUT_UNIT, nodeWithNSPorts);
        northSouthNode.setProperty(InternalProperties.ORIGIN, nodeWithNSPorts);

        setAsNorthSouthNode(northSouthNode);

        northSouthNode.setProperty(InternalProperties.IN_LAYER_LAYOUT_UNIT, nodeWithNSPorts);

        LPort originPort = addPortOnSide(nodeWithNSPorts, side);
        nsNodePort.setProperty(InternalProperties.ORIGIN, originPort);

        originPort.getProperty(InternalProperties.CONNECTED_NORTH_SOUTH_PORTS).add(nsNodePort);
    }

    private void setInLayerOrderConstraint(final LNode thisNode, final LNode beforeThisNode) {
        List<LNode> scndNodeAsList = Lists.newArrayList(beforeThisNode);
        thisNode.setProperty(InternalProperties.IN_LAYER_SUCCESSOR_CONSTRAINTS, scndNodeAsList);
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

    private MapPropertyHolder setFixedOrderConstraint(final LNode node) {
        return node.setProperty(LayoutOptions.PORT_CONSTRAINTS, PortConstraints.FIXED_ORDER);
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
        node.setProperty(InternalProperties.IN_LAYER_LAYOUT_UNIT, node);
        node.setLayer(layer);
        node.id = nodeId++;
        return node;
    }

    private void eastWestEdgeFromTo(final LNode left, final LNode right) {
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
