/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2015 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.layered.test.intermediate.greedyswitch;

import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LNode.NodeType;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.properties.InternalProperties;

/**
 * Creates getGraph()s for north south edge testing.
 * 
 * @author alan
 *
 */
public class NorthSouthEdgeTestGraphCreator extends TestGraphCreator {
    // CHECKSTYLEOFF MagicNumber
    /**
     * <pre>
     *     *-->*
     *     | 
     *   *-+-->*
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
        LNode[] rightNodes = addNodesToLayer(2, makeLayer());

        addNorthSouthEdge(PortSide.NORTH, leftNodes[2], leftNodes[1], rightNodes[1], false);
        addNorthSouthEdge(PortSide.NORTH, leftNodes[2], leftNodes[0], rightNodes[0], false);

        setFixedOrderConstraint(leftNodes[2]);
        return getGraph();
    }

    /**
     * <pre>
     *      ---*
     *      |
     *    --+--*
     *    | |
     *  --+-+--*
     * _|_|_|_
     * |_____|.
     * </pre>
     * 
     * 
     * @return getGraph() of the form above.
     */
    public LGraph getNorthSouthUpwardMultipleCrossingGraph() {
        LNode[] leftNodes = addNodesToLayer(4, makeLayer());
        LNode[] rightNodes = addNodesToLayer(3, makeLayer());

        addNorthSouthEdge(PortSide.NORTH, leftNodes[3], leftNodes[2], rightNodes[2], false);
        addNorthSouthEdge(PortSide.NORTH, leftNodes[3], leftNodes[1], rightNodes[1], false);
        addNorthSouthEdge(PortSide.NORTH, leftNodes[3], leftNodes[0], rightNodes[0], false);

        setFixedOrderConstraint(leftNodes[3]);
        return getGraph();
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

        return getGraph();
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

        return getGraph();
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
        setAsLongEdgeDummy(middleNodes[1]);

        addNorthSouthEdge(PortSide.SOUTH, middleNodes[0], middleNodes[2], rightNodes[1], true);
        return getGraph();
    }

    /**
     * <pre>
     *  _____
     *  |___|
     *   || 
     * *-++---*
     *   ||
     *   |----*
     *   -----*
     * </pre>
     * 
     * .
     * 
     * @return Graph of the form above.
     */
    public LGraph getSouthernNorthSouthDummyEdgeTwoCrossingGraph() {
        LNode leftNode = addNodeToLayer(makeLayer());
        LNode[] middleNodes = addNodesToLayer(4, makeLayer());
        LNode[] rightNodes = addNodesToLayer(3, makeLayer());

        eastWestEdgeFromTo(leftNode, middleNodes[1]);
        eastWestEdgeFromTo(middleNodes[1], rightNodes[0]);
        setAsLongEdgeDummy(middleNodes[1]);

        addNorthSouthEdge(PortSide.SOUTH, middleNodes[0], middleNodes[2], rightNodes[1], true);
        addNorthSouthEdge(PortSide.SOUTH, middleNodes[0], middleNodes[3], rightNodes[2], true);

        return getGraph();
    }

    /**
     * <pre>
     *  _____
     *  |___|
     *   || 
     * *-++---*
     *   -+---*
     * *--+---*
     *    |---*
     * 
     * </pre>
     * 
     * fixed PortOrder on top node.
     * 
     * @return Graph of the form above.
     */
    public LGraph getSouthernTwoDummyEdgeAndNorthSouthCrossingGraph() {
        LNode[] leftNodes = addNodesToLayer(2, makeLayer());
        LNode[] middleNodes = addNodesToLayer(5, makeLayer());
        LNode[] rightNodes = addNodesToLayer(4, makeLayer());

        eastWestEdgeFromTo(leftNodes[0], middleNodes[1]);
        eastWestEdgeFromTo(middleNodes[1], rightNodes[0]);
        setAsLongEdgeDummy(middleNodes[1]);

        eastWestEdgeFromTo(leftNodes[1], middleNodes[3]);
        eastWestEdgeFromTo(middleNodes[3], rightNodes[2]);
        setAsLongEdgeDummy(middleNodes[3]);

        setFixedOrderConstraint(middleNodes[0]);

        addNorthSouthEdge(PortSide.SOUTH, middleNodes[0], middleNodes[4], rightNodes[3], true);
        addNorthSouthEdge(PortSide.SOUTH, middleNodes[0], middleNodes[2], rightNodes[1], true);

        return getGraph();
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
        setAsLongEdgeDummy(middleNodes[1]);

        addNorthSouthEdge(PortSide.NORTH, middleNodes[2], middleNodes[0], rightNodes[0], true);
        return getGraph();

    }

    /**
     * <pre>
     * *-----*-------*
     *    ______
     *    |     |
     *    |_____|
     *       |
     * .     *-------*
     * </pre>
     * 
     * @return Graph of the form above.
     */
    public LGraph getSouthPortOnNormalNodeBelowLongEdgeDummy() {
        LNode leftNode = addNodeToLayer(makeLayer());
        LNode[] middleNodes = addNodesToLayer(3, makeLayer());
        LNode[] rightNodes = addNodesToLayer(2, makeLayer());

        eastWestEdgeFromTo(leftNode, middleNodes[0]);
        eastWestEdgeFromTo(middleNodes[0], rightNodes[0]);
        middleNodes[0].setNodeType(NodeType.LONG_EDGE);

        addNorthSouthEdge(PortSide.SOUTH, middleNodes[1], middleNodes[2], rightNodes[1], false);

        return getGraph();

    }

    /**
     * <pre>
     * .     *-------*
     *       |
     *    ___|___
     *    |     |
     *    |_____|
     *    
     * *-----*-------*
     * </pre>
     * 
     * @return Graph of the form above.
     */
    public LGraph getNorthPortOndNormalNodeAboveLongEdgeDummy() {
        LNode leftNode = addNodeToLayer(makeLayer());
        LNode[] middleNodes = addNodesToLayer(3, makeLayer());
        LNode[] rightNodes = addNodesToLayer(2, makeLayer());

        eastWestEdgeFromTo(leftNode, middleNodes[2]);
        eastWestEdgeFromTo(middleNodes[2], rightNodes[1]);
        middleNodes[2].setNodeType(NodeType.LONG_EDGE);

        addNorthSouthEdge(PortSide.NORTH, middleNodes[1], middleNodes[0], rightNodes[0], false);

        return getGraph();

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
     * Fixed port order constraint on upper node.
     * </pre>
     * 
     * @return Graph of the form above.
     */
    public LGraph getLongEdgeDummyAndNormalNodeWithUnusedPortsOnSouthernSide() {
        LNode leftNode = addNodeToLayer(makeLayer());
        LNode[] middleNodes = addNodesToLayer(2, makeLayer());
        LNode rightNode = addNodeToLayer(makeLayer());

        setFixedOrderConstraint(middleNodes[0]);

        eastWestEdgeFromTo(leftNode, middleNodes[1]);
        eastWestEdgeFromTo(middleNodes[1], rightNode);
        setAsLongEdgeDummy(middleNodes[1]);

        addPortOnSide(middleNodes[0], PortSide.SOUTH);
        addPortOnSide(middleNodes[0], PortSide.SOUTH);
        return getGraph();

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
        middleNodes[0].setNodeType(NodeType.LONG_EDGE);

        addPortOnSide(middleNodes[1], PortSide.NORTH);
        addPortOnSide(middleNodes[1], PortSide.NORTH);
        return getGraph();

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
        eastWestEdgeFromTo(middleNodes[2], rightNodes[2]);
        eastWestEdgeFromTo(leftNodes[1], middleNodes[4]);
        eastWestEdgeFromTo(middleNodes[4], rightNodes[4]);

        setAsLongEdgeDummy(middleNodes[2]);
        setAsLongEdgeDummy(middleNodes[4]);

        addNorthSouthEdge(PortSide.NORTH, middleNodes[3], middleNodes[0], rightNodes[0], false);
        addNorthSouthEdge(PortSide.NORTH, middleNodes[3], middleNodes[1], rightNodes[1], false);
        addNorthSouthEdge(PortSide.SOUTH, middleNodes[3], middleNodes[5], rightNodes[4], false);
        addNorthSouthEdge(PortSide.SOUTH, middleNodes[3], middleNodes[6], rightNodes[5], false);
        return getGraph();

    }

    /**
     * <pre>
     *     ______
     *     |____|
     *      |  |  
     *      *--+--*
     *         |  
     *         *--*
     * .
     * </pre>
     * 
     * Port order fixed on top left node.
     * 
     * @return Graph of the form above.
     */
    public LGraph getNorthSouthDownwardCrossingGraph() {
        LNode[] leftNodes = addNodesToLayer(3, makeLayer());
        LNode[] rightNodes = addNodesToLayer(2, makeLayer());

        addNorthSouthEdge(PortSide.SOUTH, leftNodes[0], leftNodes[2], rightNodes[1], false);
        addNorthSouthEdge(PortSide.SOUTH, leftNodes[0], leftNodes[1], rightNodes[0], false);

        setFixedOrderConstraint(leftNodes[0]);
        return getGraph();
    }

    /**
     * <pre>
     *     _______
     *     |_____|
     *      | | | 
     *      *-+-+-*
     *        | | 
     *        *-+-*
     *          |
     *          *-*
     * </pre>
     * 
     * Port order fixed on top left node.
     * 
     * @return Graph of the form above.
     */
    public LGraph getNorthSouthDownwardMultipleCrossingGraph() {
        LNode[] leftNodes = addNodesToLayer(4, makeLayer());
        LNode[] rightNodes = addNodesToLayer(3, makeLayer());

        addNorthSouthEdge(PortSide.SOUTH, leftNodes[0], leftNodes[3], rightNodes[2], false);
        addNorthSouthEdge(PortSide.SOUTH, leftNodes[0], leftNodes[2], rightNodes[1], false);
        addNorthSouthEdge(PortSide.SOUTH, leftNodes[0], leftNodes[1], rightNodes[0], false);

        setFixedOrderConstraint(leftNodes[0]);
        return getGraph();
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
        return getGraph();
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

        return getGraph();
    }

    /**
     * <pre>
     *     ---*
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
    public LGraph getNorthernNorthSouthGraphEdgesFromEastAndWestNoCrossingsUpperEdgeEast() {
        LNode leftNode = addNodeToLayer(makeLayer());
        LNode[] middleNodes = addNodesToLayer(3, makeLayer());
        LNode rightNode = addNodeToLayer(makeLayer());

        setFixedOrderConstraint(middleNodes[2]);

        addNorthSouthEdge(PortSide.NORTH, middleNodes[2], middleNodes[1], leftNode, true);
        addNorthSouthEdge(PortSide.NORTH, middleNodes[2], middleNodes[0], rightNode, false);

        return getGraph();
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

        return getGraph();
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

        return getGraph();
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

        return getGraph();
    }

    /**
     * <pre>
     *     _______
     *     |_____|
     *      | | |
     *   *--+-+--
     *   *--+-|   
     *   *--|
     * 
     * </pre>
     * 
     * .
     * 
     * @return Graph of the form above.
     */
    public LGraph getNorthSouthSouthernThreeWesternEdges() {
        LNode[] leftNodes = addNodesToLayer(3, makeLayer());
        LNode[] middleNodes = addNodesToLayer(4, makeLayer());

        setFixedOrderConstraint(middleNodes[0]);

        // ports are added in clockwise fashion!
        addNorthSouthEdge(PortSide.SOUTH, middleNodes[0], middleNodes[1], leftNodes[0], true);
        addNorthSouthEdge(PortSide.SOUTH, middleNodes[0], middleNodes[2], leftNodes[1], true);
        addNorthSouthEdge(PortSide.SOUTH, middleNodes[0], middleNodes[3], leftNodes[2], true);

        return getGraph();
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

        return getGraph();
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

        return getGraph();
    }

    /**
     * <pre>
     *       ---*
     * *---- |
     *     | | 
     *   --+-+--*
     *  _|_|_|__
     *  |      |
     *  |______|
     *    | | |
     *    | --+-*
     *    ----+-*
     *        |-*
     *  .
     * </pre>
     * 
     * Port order fixed on middle node.
     * 
     * @return Graph of the form above.
     */
    public LGraph getNorthSouthAllSidesMultipleCrossings() {
        LNode leftNode = addNodeToLayer(makeLayer());
        LNode[] middleNodes = addNodesToLayer(7, makeLayer());
        LNode[] rightNodes = addNodesToLayer(5, makeLayer());

        setFixedOrderConstraint(middleNodes[3]);

        // ports are added in clockwise fashion!
        addNorthSouthEdge(PortSide.NORTH, middleNodes[3], middleNodes[1], rightNodes[1], false);
        addNorthSouthEdge(PortSide.NORTH, middleNodes[3], middleNodes[2], leftNode, true);
        addNorthSouthEdge(PortSide.NORTH, middleNodes[3], middleNodes[0], rightNodes[0], false);

        addNorthSouthEdge(PortSide.SOUTH, middleNodes[3], middleNodes[6], rightNodes[4], false);
        addNorthSouthEdge(PortSide.SOUTH, middleNodes[3], middleNodes[4], rightNodes[2], false);
        addNorthSouthEdge(PortSide.SOUTH, middleNodes[3], middleNodes[5], rightNodes[3], false);

        return getGraph();
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

        return getGraph();
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
        return getGraph();
    }

    /**
     * <pre>
     *   *------*
     *   | 
     *   |     -*
     *  _|__  /
     *  |__|-+--*     
     *      /
     *    *-
     * </pre>
     * 
     * Lower north/south port dummy and normal node can't be switched.
     * 
     * @return Graph of the form above.
     */
    public LGraph getGraphLayoutUnitPreventsSwitchWithNodeWithNodeWithNorthernEdges() {
        LNode[] leftNodes = addNodesToLayer(3, makeLayer());
        LNode[] rightNodes = addNodesToLayer(3, makeLayer());

        addNorthSouthEdge(PortSide.NORTH, leftNodes[1], leftNodes[0], rightNodes[0], false);
        eastWestEdgeFromTo(leftNodes[1], rightNodes[2]);
        eastWestEdgeFromTo(leftNodes[2], rightNodes[1]);

        return getGraph();
    }

    /**
     * <pre>
     *    *-
     *  ____\  
     *  |__|-+--*     
     *    |   \
     *    |    -*
     *    *-----*
     * </pre>
     * 
     * Lower north/south port dummy and normal node can't be switched.
     * 
     * @return Graph of the form above.
     */
    public LGraph getGraphLayoutUnitPreventsSwitchWithNodeWithNodeWithSouthernEdges() {
        LNode[] leftNodes = addNodesToLayer(4, makeLayer());
        LNode[] rightNodes = addNodesToLayer(3, makeLayer());

        eastWestEdgeFromTo(leftNodes[0], rightNodes[1]);
        eastWestEdgeFromTo(leftNodes[1], rightNodes[0]);
        addNorthSouthEdge(PortSide.SOUTH, leftNodes[1], leftNodes[2], rightNodes[2], false);

        return getGraph();
    }

    /**
     * <pre>
     * *--*-   <- is long edge dummy
     *  ____\  
     *  |__|=+==*     
     *    |   \
     *    |     *
     *    *-----*
     * </pre>
     * 
     * Lower north/south port dummy and normal node can't be switched.
     * 
     * @return Graph of the form above.
     */
    public LGraph getGraphLayoutUnitDoesNotPreventSwitchWithLongEdgeDummy() {
        LNode leftNode = addNodeToLayer(makeLayer());
        LNode[] middleNodes = addNodesToLayer(4, makeLayer());
        LNode[] rightNodes = addNodesToLayer(3, makeLayer());

        setAsLongEdgeDummy(middleNodes[0]);
        eastWestEdgeFromTo(leftNode, middleNodes[0]);
        eastWestEdgeFromTo(middleNodes[0], rightNodes[1]);
        eastWestEdgeFromTo(middleNodes[1], rightNodes[0]);
        eastWestEdgeFromTo(middleNodes[1], rightNodes[0]);
        addNorthSouthEdge(PortSide.SOUTH, middleNodes[1], middleNodes[2], rightNodes[2], false);

        return getGraph();
    }
}
