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
package de.cau.cs.kieler.klay.rail.impl;

import java.util.Iterator;
import java.util.ListIterator;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.kiml.options.PortType;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;
import de.cau.cs.kieler.klay.layered.modules.ICrossingMinimizer;
import de.cau.cs.kieler.klay.rail.Properties;
import de.cau.cs.kieler.klay.rail.options.NodeType;

/**
 * Simple crossing removal for railway layout. Since railway plans are crossing-free by contract,
 * this tries to find an arrangement which is in fact free of crossings.
 * 
 * @author jjc
 */
public class RailwayCrossingMinimizer extends AbstractAlgorithm implements ICrossingMinimizer {

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

        LNode[][] order = new LNode[layerCount][];
        int currentLayer = 0;
        order[currentLayer] = new LNode[layeredGraph.getLayers().get(0).getNodes().size()];
        order[currentLayer][0] = layeredGraph.getLayers().get(0).getNodes().get(0);
        for (int i = 0; i < layerCount - 1; i++) {
            Layer nextLayer = layeredGraph.getLayers().get(i + 1);
            order[currentLayer + 1] = new LNode[nextLayer.getNodes().size()];
            int currentPosition = 0;
            for (int j = 0; j < order[currentLayer].length; j++) {
                LNode currentNode = order[currentLayer][j];
                if (currentNode.getProperty(Properties.NODE_TYPE).equals(NodeType.SWITCH_LEFT)) {
                    Iterator<LPort> theTwoPorts = currentNode.getPorts(PortType.OUTPUT).iterator();
                    LPort port = theTwoPorts.next();
                    LPort port2 = theTwoPorts.next();
                    if (port.getPos().y < port2.getPos().y) {
                        order[currentLayer + 1][currentPosition] = port.getEdges().get(0)
                                .getTarget().getNode();
                        order[currentLayer + 1][currentPosition + 1] = port2.getEdges().get(0)
                                .getTarget().getNode();
                    } else {
                        order[currentLayer + 1][currentPosition] = port2.getEdges().get(0)
                                .getTarget().getNode();
                        order[currentLayer + 1][currentPosition + 1] = port.getEdges().get(0)
                                .getTarget().getNode();
                    }
                    currentPosition += 2;
                } else if (currentNode.getProperty(Properties.NODE_TYPE).equals(
                        NodeType.SWITCH_RIGHT)) {
                    Iterator<LPort> theTwoPorts = currentNode.getPorts(PortType.OUTPUT).iterator();
                    LPort port = theTwoPorts.next();
                    LPort port2 = theTwoPorts.next();
                    if (port.getPos().y < port2.getPos().y) {
                        order[currentLayer + 1][currentPosition] = port2.getEdges().get(0)
                                .getTarget().getNode();
                        order[currentLayer + 1][currentPosition + 1] = port.getEdges().get(0)
                                .getTarget().getNode();
                    } else {
                        order[currentLayer + 1][currentPosition] = port.getEdges().get(0)
                                .getTarget().getNode();
                        order[currentLayer + 1][currentPosition + 1] = port2.getEdges().get(0)
                                .getTarget().getNode();
                    }
                    currentPosition += 2;
                } else {
                    if (currentNode.getPorts().get(0).getType().equals(PortType.OUTPUT)) {
                        order[currentLayer + 1][currentPosition] = currentNode.getPorts().get(0)
                                .getEdges().get(0).getTarget().getNode();
                        currentPosition++;
                    }
                }
            }
            currentLayer++;
        }

        // apply the ordering to the original layered graph
        ListIterator<Layer> layerIter = layeredGraph.getLayers().listIterator();
        while (layerIter.hasNext()) {
            Layer layer = layerIter.next();
            LNode[] nodes = order[layerIter.previousIndex()];
            ListIterator<LNode> nodeIter = layer.getNodes().listIterator();
            while (nodeIter.hasNext()) {
                nodeIter.next();
                nodeIter.set(nodes[nodeIter.previousIndex()]);
            }
        }

        getMonitor().done();

    }

}
