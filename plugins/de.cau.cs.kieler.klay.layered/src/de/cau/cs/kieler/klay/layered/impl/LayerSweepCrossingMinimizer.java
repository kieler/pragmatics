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
import java.util.ListIterator;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.kiml.layout.options.PortType;
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
     * 
     * @param fixedLayer the fixed layer
     * @param freeLayer the free layer whose nodes are reordered
     * @param forward whether the free layer is after the fixed layer
     */
    public void minimizeCrossings(final Layer fixedLayer, final Layer freeLayer,
            final boolean forward) {
        // set the port ranks for the fixed layer
        int portId = 0;
        for (LNode node : fixedLayer.getNodes()) {
            for (LPort port : node.getPorts()) {
                port.id = portId;
                portId++;
            }
        }
        
        float[] nodeBarycenters = new float[freeLayer.getNodes().size()];
        int nodeId = 0;
        for (LNode node : freeLayer.getNodes()) {
            node.id = nodeId;
            float nodeSum = 0;
            int portCount = 0;
            float[] portBarycenters = new float[node.getPorts().size()];
            Arrays.fill(portBarycenters, -1);
            portId = 0;
            for (LPort freePort : node.getPorts()) {
                freePort.id = portId;
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
                // TODO make this dependent on the type of port constraints
                sortPorts(node, portBarycenters);
                nodeBarycenters[nodeId] = nodeSum / portCount;
            }
            nodeId++;
        }
        sortNodes(freeLayer, nodeBarycenters);
    }
    
    void sortPorts(final LNode node, final float[] portBarycenters) {
        
    }
    
    void sortNodes(final Layer layer, final float[] nodeBarycenters) {
        
    }

}
