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

import java.util.Arrays;
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

    private float[] portPos;
    private float[] nodeBarycenter;
    private float[] portBarycenter;
    
    /**
     * {@inheritDoc}
     */
    public void minimizeCrossings(final LayeredGraph layeredGraph) {
        getMonitor().begin("Layer sweep crossing minimization", 1);
        int layerCount = layeredGraph.getLayers().size();
        
        if (layerCount >= 2) {
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
            portPos = new float[portCount];
            nodeBarycenter = new float[nodeCount];
            portBarycenter = new float[portCount];
            Arrays.fill(portBarycenter, -1);
            
            ListIterator<Layer> layerIter = layeredGraph.getLayers().listIterator();
            Layer fixedLayer = layerIter.next();
            assignRanks(fixedLayer);
            int previousCross, curCross = Integer.MAX_VALUE;
            do {
                previousCross = curCross;
                curCross = 0;
                while (layerIter.hasNext()) {
                    Layer freeLayer = layerIter.next();
                    curCross += minimizeCrossings(fixedLayer, freeLayer, true);
                    fixedLayer = freeLayer;
                }
                System.out.println("-> " + curCross);
                if (curCross < previousCross) {
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
            // TODO restore last ordering if crossing number has worsened
        }
        
        getMonitor().done();
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
            int portCount = 0;
            for (LPort freePort : node.getPorts()) {
                portBarycenter[freePort.id] = -1;
                if (freePort.getType() == (forward ? PortType.INPUT : PortType.OUTPUT)) {
                    int edgeCount = freePort.getEdges().size();
                    if (edgeCount > 0) {
                        float portSum = 0;
                        for (LPort fixedPort : freePort.getConnectedPorts()) {
                            portSum += portPos[fixedPort.id];
                            totalEdges++;
                        }
                        float bary = portSum / edgeCount;
                        portBarycenter[freePort.id] = bary;
                        nodeSum += bary;
                        portCount++;
                    }
                }
            }
            if (portCount > 0) {
                PortConstraints constraints = node.getProperty(Properties.PORT_CONS);
                if (constraints == PortConstraints.FREE
                        || constraints == PortConstraints.FIXED_SIDE) {
                    sortPorts(node, forward);
                }
                nodeBarycenter[node.id] = nodeSum / portCount;
            }
        }
        sortNodes(freeLayer);
        assignRanks(freeLayer);
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
    
    /** amount by which the position of free ports is drawn to the center of nodes. */
    private static final int FREE_PORTS_PENALTY = 8;
    
    /**
     * Determine positions for all ports in the given layer. Input and output ports are processed
     * separately.
     * 
     * @param layer a layer
     */
    private void assignRanks(final Layer layer) {
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
            PortConstraints cons = node.getProperty(Properties.PORT_CONS);
            // set positions for the input ports
            if (inputPorts > 0) {
                float pos = nodeIx, incr = 1.0f / inputPorts;
                if (cons == PortConstraints.FIXED_ORDER || cons == PortConstraints.FIXED_POS) {
                    incr /= FREE_PORTS_PENALTY;
                    pos += (1 - inputPorts * incr) / 2;
                }
                ListIterator<LPort> portIter = node.getPorts().listIterator(node.getPorts().size());
                while (portIter.hasPrevious()) {
                    LPort port = portIter.previous();
                    if (port.getType() == PortType.INPUT) {
                        portPos[port.id] = pos;
                        pos += incr;
                    }
                }
            }
            // set positions for the output ports
            if (outputPorts > 0) {
                float pos = nodeIx, incr = 1.0f / outputPorts;
                if (cons == PortConstraints.FIXED_ORDER || cons == PortConstraints.FIXED_POS) {
                    incr /= FREE_PORTS_PENALTY;
                    pos += (1 - outputPorts * incr) / 2;
                }
                for (LPort port : node.getPorts(PortType.OUTPUT)) {
                    portPos[port.id] = pos;
                    pos += incr;
                }
            }
            nodeIx++;
        }
    }
    
    /**
     * Sort the ports of the given node by their sides, position values, and input
     * or output status.
     * 
     * @param node node whose ports shall be sorted
     * @param input if true, all ports with position value greater or equal zero
     *     are assumed to be input ports and the others to be output ports; else the
     *     contrary is assumed
     */
    private void sortPorts(final LNode node, final boolean input) {
        Collections.sort(node.getPorts(), new Comparator<LPort>() {
            public int compare(final LPort port1, final LPort port2) {
                PortSide side1 = port1.getSide();
                PortSide side2 = port2.getSide();
                if (side1 != side2) {
                    // sort according to the node side 
                    return side1.ordinal() - side2.ordinal();
                } else {
                    float bary1 = portBarycenter[port1.id], bary2 = portBarycenter[port2.id];
                    // input ports are counter-clockwise, output ports are clockwise
                    if (bary1 >= 0 && bary2 >= 0) {
                        return input ? Float.compare(bary2, bary1)
                                : Float.compare(bary1, bary2);
                    // north side: first inputs, then outputs; other sides: reverse
                    } else if (bary1 >= 0) {
                        if (side1 == PortSide.NORTH) {
                            return input ? -1 : 1;
                        } else {
                            return input ? 1 : -1;
                        }
                    } else if (bary2 >= 0) {
                        if (side2 == PortSide.NORTH) {
                            return input ? 1 : -1;
                        } else {
                            return input ? -1 : 1;
                        }
                    // take the previous ordering
                    } else {
                        return port1.getIndex() - port2.getIndex();
                    }
                }
            }
        });
        // reset barycenter values for the ports of the current node
        for (LPort port : node.getPorts()) {
            portBarycenter[port.id] = -1;
        }
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
                nodeBarycenter[nodeid] = (lastValue + nextValue) / 2;
            }
        }

        // sort all nodes by the filtered ranks
        Collections.sort(nodes, new Comparator<LNode>() {
            public int compare(final LNode node1, final LNode node2) {
                return Float.compare(nodeBarycenter[node1.id], nodeBarycenter[node2.id]);
            }
        });
    }

}
