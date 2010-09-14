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

    /**
     * {@inheritDoc}
     */
    public void minimizeCrossings(final LayeredGraph layeredGraph) {
        getMonitor().begin("Layer sweep crossing minimization", 1);
        int layerCount = layeredGraph.getLayers().size();
        
        if (layerCount >= 2) {
            // perform a single forth-and-back sweep
            // TODO extend to support multiple sweeps (fixed or dynamic)
            ListIterator<Layer> layerIter = layeredGraph.getLayers().listIterator();
            Layer fixedLayer = layerIter.next();
            while (layerIter.hasNext()) {
                Layer freeLayer = layerIter.next();
                minimizeCrossings(fixedLayer, freeLayer, true);
                fixedLayer = freeLayer;
            }
            layerIter.previous();
            while (layerIter.hasPrevious()) {
                Layer freeLayer = layerIter.previous();
                minimizeCrossings(fixedLayer, freeLayer, false);
                fixedLayer = freeLayer;
            }
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
     */
    public void minimizeCrossings(final Layer fixedLayer, final Layer freeLayer,
            final boolean forward) {
        // set the port ranks for the fixed layer
        if (forward) { 
            assignForwardRanks(fixedLayer);
        } else {
            assignBackwardRanks(fixedLayer);
        }
        
        float[] nodeBarycenters = new float[freeLayer.getNodes().size()];
        int nodeId = 0, portId;
        for (LNode node : freeLayer.getNodes()) {
            node.id = nodeId;
            nodeBarycenters[nodeId] = -1;
            float nodeSum = 0;
            int portCount = 0;
            float[] portBarycenters = new float[node.getPorts().size()];
            portId = 0;
            for (LPort freePort : node.getPorts()) {
                freePort.id = portId;
                portBarycenters[portId] = -1;
                if (freePort.getType() == (forward ? PortType.INPUT : PortType.OUTPUT)) {
                    int edgeCount = freePort.getEdges().size();
                    if (edgeCount > 0) {
                        int portSum = 0;
                        for (LPort fixedPort : freePort.getConnectedPorts()) {
                            portSum += fixedPort.id;
                        }
                        portBarycenters[portId] = (float) portSum / edgeCount;
                        nodeSum += portBarycenters[portId];
                        portCount++;
                    }
                }
                portId++;
            }
            if (portCount > 0) {
                PortConstraints constraints = node.getProperty(Properties.PORT_CONS);
                if (constraints == PortConstraints.FREE
                        || constraints == PortConstraints.FIXED_SIDE) {
                    sortPorts(node, portBarycenters, forward);
                }
                nodeBarycenters[nodeId] = nodeSum / portCount;
            }
            nodeId++;
        }
        sortNodes(freeLayer, nodeBarycenters);
    }
    
    /**
     * Assigns ranks to all ports in the given layer, assuming that a forward sweep is
     * being done. This means that only output ports are processed.
     * 
     * @param layer a layer
     */
    private void assignForwardRanks(final Layer layer) {
        int portId = 0;
        for (LNode node : layer.getNodes()) {
            for (LPort port : node.getPorts(PortType.OUTPUT)) {
                port.id = portId;
                portId++;
            }
        }
    }
    
    /**
     * Assigns ranks to all ports in the given layer, assuming that a backwards sweep is
     * being done. This means that only input ports are processed.
     * 
     * @param layer a layer
     */
    private void assignBackwardRanks(final Layer layer) {
        int portId = 0;
        for (LNode node : layer.getNodes()) {
            ListIterator<LPort> portIter = node.getPorts().listIterator(node.getPorts().size());
            while (portIter.hasPrevious()) {
                LPort port = portIter.previous();
                if (port.getType() == PortType.INPUT) {
                    port.id = portId;
                    portId++;
                }
            }
        }
    }
    
    /**
     * Sort the ports of the given node by their sides, position values, and input
     * or output status.
     * 
     * @param node node whose ports shall be sorted
     * @param posValues array of position values
     * @param input if true, all ports with position value greater or equal zero
     *     are assumed to be input ports and the others to be output ports; else the
     *     contrary is assumed
     */
    private void sortPorts(final LNode node, final float[] posValues, final boolean input) {
        if (node.getProperty(Properties.PORT_CONS) == PortConstraints.FREE) {
            // set input ports left, output ports right
            for (LPort port : node.getPorts()) {
                switch (port.getType()) {
                case INPUT:
                    port.setSide(PortSide.WEST);
                    break;
                case OUTPUT:
                    port.setSide(PortSide.EAST);
                }
            }
        }
        Collections.sort(node.getPorts(), new Comparator<LPort>() {
            public int compare(final LPort port1, final LPort port2) {
                PortSide side1 = port1.getSide();
                PortSide side2 = port2.getSide();
                if (side1 != side2) {
                    // sort according to the node side 
                    return side1.ordinal() - side2.ordinal();
                } else {
                    float bary1 = posValues[port1.id], bary2 = posValues[port2.id];
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
                        return port1.id - port2.id;
                    }
                }
            }
        });
    }
    
    /**
     * Sort the nodes of the given layer. A heuristic tries to find appropriate position values
     * for nodes whose position value is < 0.
     * 
     * @param layer layer whose nodes shall be sorted
     * @param posValues array of position values
     */
    private void sortNodes(final Layer layer, final float[] posValues) {
        List<LNode> nodes = layer.getNodes();
        // determine placements for nodes with undefined position value
        final double[] filteredValues = new double[nodes.size()];
        for (int index = 0; index < posValues.length; index++) {
            float value = posValues[index];
            if (value >= 0.0) {
                filteredValues[index] = value;
            } else {
                int definedRanks = 0, il = index, ir = index;
                float sum = 0.0f;
                do {
                    if (il >= 0) {
                        il--;
                        if (il < 0) {
                            definedRanks++;
                        } else if (posValues[il] >= 0.0) {
                            sum += posValues[il];
                            definedRanks++;
                        }
                    }
                    if (ir < posValues.length) {
                        ir++;
                        if (ir < posValues.length && posValues[ir] >= 0.0) {
                            sum += posValues[ir];
                            definedRanks++;
                        }
                    }
                } while (definedRanks == 0);
                filteredValues[index] = sum / definedRanks;
            }
        }

        // sort all nodes by the filtered ranks
        Collections.sort(nodes, new Comparator<LNode>() {
            public int compare(final LNode node1, final LNode node2) {
                return Double.compare(filteredValues[node1.id], filteredValues[node2.id]);
            }
        });
    }

}
