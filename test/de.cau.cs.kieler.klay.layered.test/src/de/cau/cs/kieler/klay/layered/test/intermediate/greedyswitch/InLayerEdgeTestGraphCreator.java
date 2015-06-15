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
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;

/**
 * @author alan
 *
 */
public class InLayerEdgeTestGraphCreator extends TestGraphCreator {
    // CHECKSTYLEOFF MagicNumber
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
    public LGraph getInLayerEdgesGraphWithCrossingsToBetweenLayerEdgeWithFixedPortOrder() {
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
        return getGraph();
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
     * With fixed Port PortOrder.
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
    
        return getGraph();
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
    
        return getGraph();
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
    
        return getGraph();
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
    
        return getGraph();
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
     * @return getGraph() of the form above
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
        return getGraph();
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
    public LGraph getInLayerEdgesFixedPortOrderInLayerAndInBetweenLayerCrossing() {
        Layer[] layers = makeLayers(2);
        LNode leftNode = addNodeToLayer(layers[0]);
        LNode[] rightNodes = addNodesToLayer(3, layers[1]);
    
        setFixedOrderConstraint(rightNodes[1]);
    
        eastWestEdgeFromTo(leftNode, rightNodes[1]);
        addInLayerEdge(rightNodes[0], rightNodes[1], PortSide.WEST);
        addInLayerEdge(rightNodes[1], rightNodes[2], PortSide.WEST);
    
        return getGraph();
    }

    /**
     * <pre>
     * 
     *   --*
     *   | ____
     *   |/|  |
     *   /\|  |
     *   | |  |
     *   | |__|
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
    public LGraph getInLayerEdgesFixedPortOrderInLayerCrossing() {
        LNode[] nodes = addNodesToLayer(3, makeLayer());
    
        setFixedOrderConstraint(nodes[1]);
    
        addInLayerEdge(nodes[0], nodes[1], PortSide.WEST);
        addInLayerEdge(nodes[1], nodes[2], PortSide.WEST);
    
        return getGraph();
    }

    /**
     * <pre>
     *     ____
     *    /|  |
     *   / |  |
     * --+-|  |
     * | | |__|
     * | |
     * \  \ 
     *  \  *
     *   \
     *     *
     * .
     * </pre>
     * 
     * Port order fixed.
     * 
     * @return Graph of the form above.
     */
    public LGraph getFixedPortOrderTwoInLayerEdgesCrossEachOther() {
        LNode[] nodes = addNodesToLayer(3, makeLayer());
    
        setFixedOrderConstraint(nodes[0]);
    
        addInLayerEdge(nodes[0], nodes[2], PortSide.WEST);
        addInLayerEdge(nodes[0], nodes[1], PortSide.WEST);
    
        return getGraph();
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
    
        return getGraph();
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
        return getGraph();
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
        return getGraph();
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
        return getGraph();
    }

}
