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
package de.cau.cs.kieler.klay.layered.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.kiml.options.PortConstraints;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.kiml.options.PortType;
import de.cau.cs.kieler.klay.layered.Properties;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;
import de.cau.cs.kieler.klay.layered.modules.ICrossingMinimizer;

/**
 * Crossing minimization module that performs one or more sweeps over the layers
 * while applying a two-layer crossing minimization heuristic on each pair of layers.
 *
 * @author msp
 */
public class LayerSweepCrossingMinimizer extends AbstractAlgorithm implements ICrossingMinimizer {

    /** barycenter values for nodes. */
    private float[] portBarycenter;
    /** port position array. */
    private float[] portPos;
    /** barycenter values for nodes. */
    private float[] nodeBarycenter;
    /** node ordering from previous run. */
    private int[] nodeIndex;
    
    /**
     * {@inheritDoc}
     */
    public void minimizeCrossings(final LayeredGraph layeredGraph) {
        getMonitor().begin("Layer sweep crossing minimization", 1);
        int layerCount = layeredGraph.getLayers().size();
        if (layerCount < 2) {
            getMonitor().done();
            return;
        }
        
        // determine the total number of nodes and ports in the graph
        int nodeCount = 0, portCount = 0;
        for (Layer layer : layeredGraph.getLayers()) {
            for (LNode node : layer.getNodes()) {
                node.id = nodeCount++;
                boolean freePorts = node.getProperty(Properties.PORT_CONS) == PortConstraints.FREE;
                for (LPort port : node.getPorts()) {
                    port.id = portCount++;
                    if (freePorts) {
                        // set input ports left, output ports right
                        switch (port.getType()) {
                        case INPUT:
                            port.setSide(PortSide.WEST);
                            break;
                        case OUTPUT:
                            port.setSide(PortSide.EAST);
                        }
                    }
                }
            }
        }
        portBarycenter = new float[portCount];
        portPos = new float[portCount];
        nodeBarycenter = new float[nodeCount];
        nodeIndex = new int[nodeCount];
        
        ListIterator<Layer> layerIter = layeredGraph.getLayers().listIterator();
        Layer fixedLayer = layerIter.next();
        assignPortPos(fixedLayer);
        int previousCross, curCross = Integer.MAX_VALUE;
        do {
            // perform a forward sweep
            previousCross = curCross;
            curCross = 0;
            while (layerIter.hasNext()) {
                Layer freeLayer = layerIter.next();
                curCross += minimizeCrossings(fixedLayer, freeLayer, true);
                fixedLayer = freeLayer;
            }
            System.out.println("-> " + curCross);
            if (curCross < previousCross) {
                // perform a backwards sweep
                previousCross = curCross;
                curCross = 0;
                layerIter.previous();
                while (layerIter.hasPrevious()) {
                    Layer freeLayer = layerIter.previous();
                    curCross += minimizeCrossings(fixedLayer, freeLayer, false);
                    fixedLayer = freeLayer;
                }
                System.out.println("<- " + curCross);
                layerIter.next();
            }
        } while (curCross < previousCross);
        
        // restore last ordering if it has become worse
        if (curCross > previousCross) {
            restoreOrder(layeredGraph);
        }
        
        // distribute the ports of all nodes with free port constraints
        distributePorts(layeredGraph);
        
        getMonitor().done();
    }
    
    /**
     * Restores the node order for the whole layered graph as it was present before the last
     * forward or backwards sweep.
     * 
     * @param layeredGraph a layered graph
     */
    private void restoreOrder(final LayeredGraph layeredGraph) {
        for (Layer layer : layeredGraph.getLayers()) {
            LNode[] sortedNodes = new LNode[layer.getNodes().size()];
            for (LNode node : layer.getNodes()) {
                sortedNodes[nodeIndex[node.id]] = node;
            }
            // rearrange the nodes in the list
            ListIterator<LNode> nodeIter = layer.getNodes().listIterator();
            while (nodeIter.hasNext()) {
                nodeIter.next();
                nodeIter.set(sortedNodes[nodeIter.previousIndex()]);
            }
            // reassign port position values
            assignPortPos(layer);
        }
    }

    /**
     * Minimize the number of crossings for the edges between the two given layers.
     * Currently the barycenter heuristic is used for this.
     * 
     * @param fixedLayer the fixed layer
     * @param freeLayer the free layer whose nodes are reordered
     * @param forward whether the free layer is after the fixed layer
     * @return the new number of crossings between the two layers
     */
    public int minimizeCrossings(final Layer fixedLayer, final Layer freeLayer,
            final boolean forward) {        
        int totalEdges = 0;
        for (LNode node : freeLayer.getNodes()) {
            nodeBarycenter[node.id] = -1;
            float nodeSum = 0;
            int edgeCount = 0;
            for (LPort freePort : node.getPorts(forward ? PortType.INPUT : PortType.OUTPUT)) {
                for (LPort fixedPort : freePort.getConnectedPorts()) {
                    nodeSum += portPos[fixedPort.id];
                }
                edgeCount += freePort.getEdges().size();
            }
            if (edgeCount > 0) {
                nodeBarycenter[node.id] = nodeSum / edgeCount;
                totalEdges += edgeCount;
            }
        }
        sortNodes(freeLayer);
        assignPortPos(freeLayer);
        Layer leftLayer = forward ? fixedLayer : freeLayer;
        return countCrossings(leftLayer, totalEdges);
    }
    
    /**
     * Calculate the number of crossings between the given layer and the following one.
     * 
     * @param leftLayer the left layer
     * @param edgeCount the total number of edges in the layer
     * @return the number of edge crossings
     */
    private int countCrossings(final Layer leftLayer, final int edgeCount) {
        LEdge[] edges = new LEdge[edgeCount];
        int i = 0;
        for (LNode node : leftLayer.getNodes()) {
            for (LPort port : node.getPorts(PortType.OUTPUT)) {
                for (LEdge edge : port.getEdges()) {
                    edges[i++] = edge;
                }
            }
        }
        
        int crossings = 0;
        for (i = 0; i < edgeCount; i++) {
            for (int j = i + 1; j < edgeCount; j++) {
                float source1pos = portPos[edges[i].getSource().id];
                float target1pos = portPos[edges[i].getTarget().id];
                float source2pos = portPos[edges[j].getSource().id];
                float target2pos = portPos[edges[j].getTarget().id];
                if (source1pos < source2pos && target1pos > target2pos
                        || source1pos > source2pos && target1pos < target2pos) {
                    crossings++;
                }
            }
        }
        return crossings;
    }
    
    /**
     * Determine positions for all ports in the given layer. Input and output ports are processed
     * separately.
     * 
     * @param layer a layer
     */
    private void assignPortPos(final Layer layer) {
        int nodeIx = 0;
        for (LNode node : layer.getNodes()) {
            // count the input and output ports
            int inputPorts = 0, outputPorts = 0;
            for (LPort port : node.getPorts()) {
                switch (port.getType()) {
                case INPUT:
                    inputPorts++;
                    break;
                case OUTPUT:
                    outputPorts++;
                    break;
                }
            }
            // set positions for the input ports
            if (inputPorts > 0) {
                assignPortPos(node, nodeIx, PortType.INPUT, inputPorts);
            }
            // set positions for the output ports
            if (outputPorts > 0) {
                assignPortPos(node, nodeIx, PortType.OUTPUT, outputPorts);
            }
            nodeIx++;
        }
    }
    
    /**
     * Assign port positions for the input or output ports of the given node.
     * 
     * @param node a node
     * @param nodeIx the node's index in its layer
     * @param type the type of ports to process
     * @param count the number of ports of that type
     */
    private void assignPortPos(final LNode node, final int nodeIx,
            final PortType type, final int count) {
        float pos = nodeIx;
        PortConstraints cons = node.getProperty(Properties.PORT_CONS);
        switch (cons) {
        case FREE:
            pos += 1.0f / 2;
            for (LPort port : node.getPorts(type)) {
                portPos[port.id] = pos;
            }
            break;
        case FIXED_SIDE:
            for (LPort port : node.getPorts(type)) {
                portPos[port.id] = getPortIncr(type, port.getSide());
            }
            break;
        default:
            float incr = 1.0f / count;
            if (type == PortType.INPUT) {
                incr = -incr;
                pos = pos + 1 + incr;
            }
            for (LPort port : node.getPorts(type)) {
                portPos[port.id] = pos;
                pos += incr;
            }
        }
    }
    
    private static final float INCR_ONE = 0.25f;
    private static final float INCR_TWO = 0.5f;
    private static final float INCR_THREE = 0.75f;
    
    /**
     * Return an increment value for the position of a port with given type and side.
     * 
     * @param type the port type
     * @param side the port side
     * @return a position increment for the port
     */
    private static float getPortIncr(final PortType type, final PortSide side) {
        switch (type) {
        case INPUT:
            switch (side) {
            case WEST:
                return INCR_ONE;
            case SOUTH:
                return INCR_TWO;
            case EAST:
                return INCR_THREE;
            }
            break;
        case OUTPUT:
            switch (side) {
            case EAST:
                return INCR_ONE;
            case SOUTH:
                return INCR_TWO;
            case WEST:
                return INCR_THREE;
            }
            break;
        }
        return 0;
    }
    
    /**
     * Sort the nodes of the given layer. A heuristic tries to find appropriate position values
     * for nodes whose position value is < 0.
     * 
     * @param layer layer whose nodes shall be sorted
     */
    private void sortNodes(final Layer layer) {
        List<LNode> nodes = layer.getNodes();
        // determine placements for nodes with undefined position value
        ListIterator<LNode> iter1 = nodes.listIterator(0);
        float lastValue = -1;
        while (iter1.hasNext()) {
            int nodeid = iter1.next().id;
            float value = nodeBarycenter[nodeid];
            if (value < 0) {
                ListIterator<LNode> iter2 = nodes.listIterator(iter1.nextIndex());
                float nextValue = lastValue + 1;
                while (iter2.hasNext()) {
                    float x = nodeBarycenter[iter2.next().id];
                    if (x >= 0) {
                        nextValue = x;
                        break;
                    }
                }
                value = (lastValue + nextValue) / 2;
                nodeBarycenter[nodeid] = value;
            }
            lastValue = value;
            // store previous ordering for the node
            nodeIndex[nodeid] = iter1.previousIndex();
        }

        // sort all nodes by the filtered ranks
        Collections.sort(nodes, new Comparator<LNode>() {
            public int compare(final LNode node1, final LNode node2) {
                return Float.compare(nodeBarycenter[node1.id], nodeBarycenter[node2.id]);
            }
        });
    }
    
    /**
     * Distribute the ports of the layered graph depending on the port constraints.
     * 
     * @param layeredGraph a layered graph
     */
    private void distributePorts(final LayeredGraph layeredGraph) {
        for (Layer layer : layeredGraph.getLayers()) {
            int nodeIx = 0;
            for (LNode node : layer.getNodes()) {
                PortConstraints cons = node.getProperty(Properties.PORT_CONS);
                if (cons == PortConstraints.FREE || cons == PortConstraints.FIXED_SIDE) {
                    distributePorts(node);
                    node.setProperty(Properties.PORT_CONS, PortConstraints.FIXED_ORDER);
                    int portCount = 0;
                    for (LPort port : node.getPorts()) {
                        if (port.getType() == PortType.OUTPUT) {
                            portCount++;
                        }
                    }
                    if (portCount > 0) {
                        assignPortPos(node, nodeIx, PortType.OUTPUT, portCount);
                    }
                }
                nodeIx++;
            }
        }
    }
    
    /**
     * Distribute the ports of the given node by their sides, connected ports, and input
     * or output type.
     * 
     * @param node node whose ports shall be sorted
     */
    private void distributePorts(final LNode node) {
        // calculate barycenter values for the ports of the node
        for (LPort port : node.getPorts()) {
            float sum = 0;
            for (LPort connectedPort : port.getConnectedPorts()) {
                sum += portPos[connectedPort.id];
            }
            if (port.getEdges().isEmpty()) {
                portBarycenter[port.id] = -1;
            } else {
                portBarycenter[port.id] = sum / port.getEdges().size();
            }
        }
        // sort the ports by considering the side, type, and barycenter values
        Collections.sort(node.getPorts(), new Comparator<LPort>() {
            public int compare(final LPort port1, final LPort port2) {
                PortSide side1 = port1.getSide();
                PortType type1 = port1.getType();
                PortSide side2 = port2.getSide();
                PortType type2 = port2.getType();
                if (side1 != side2) {
                    // sort according to the node side 
                    return side1.ordinal() - side2.ordinal();
                } else if (type1 != type2) {
                    // north side: first inputs, then outputs; other sides: reverse
                    if (side1 == PortSide.NORTH) {
                        return type1.ordinal() - type2.ordinal();
                    } else {
                        return type2.ordinal() - type1.ordinal();
                    }
                } else {
                    float bary1 = portBarycenter[port1.id], bary2 = portBarycenter[port2.id];
                    // input ports are counter-clockwise, output ports are clockwise
                    if (type1 == PortType.INPUT) {
                        return Float.compare(bary2, bary1);
                    } else {
                        return Float.compare(bary1, bary2);
                    }
                }
            }
        });
    }

}
