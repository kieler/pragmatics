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
import java.util.LinkedList;
import java.util.List;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.kiml.options.PortType;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;
import de.cau.cs.kieler.klay.layered.modules.INodePlacer;
import de.cau.cs.kieler.klay.rail.Properties;
import de.cau.cs.kieler.klay.rail.graph.RailLayer;
import de.cau.cs.kieler.klay.rail.options.NodeType;

/**
 * Node placer for railway layout.
 * 
 * @author jjc
 */
public class RailwayNodePlacer extends AbstractAlgorithm implements INodePlacer {

    /**
     * {@inheritDoc}
     */
    @Override
    public void reset() {
        super.reset();
    }

    /**
     * {@inheritDoc}
     */
    public void placeNodes(final LayeredGraph layeredGraph) {
        getMonitor().begin("Linear segments node placement", 1);

        float spacing = layeredGraph.getProperty(Properties.OBJ_SPACING);
        float borspacing = layeredGraph.getProperty(Properties.BOR_SPACING);
        int layerCount = layeredGraph.getLayers().size();
        double maximalMoveUp = Double.NEGATIVE_INFINITY;
        
        for (Layer layer : layeredGraph.getLayers()) {
            if (layer instanceof RailLayer) {
                System.out.println("Raillayer");
                RailLayer rl = (RailLayer) layer;
                int i = 0;
                for (LNode node : rl.getNodes()) {
                    rl.getRowList().addNodeAtPosition(node, i);
                    i++;
                }
                gridToAbsolutePosition(rl);
            }
        }
        /*for (int i = 0; i < layerCount - 1; i++) {
            List<KVector> occupiedSpots;
            for (int j = 0; j < layeredGraph.getLayers().get(i).getNodes().size(); j++) {
                LNode currentNode = layeredGraph.getLayers().get(i).getNodes().get(j);
                if (currentNode.getProperty(Properties.NODE_TYPE).equals(NodeType.SWITCH_LEFT)) {
                    Iterator<LPort> theTwoPorts = currentNode.getPorts(PortType.OUTPUT).iterator();
                    LPort port = theTwoPorts.next();
                    if (theTwoPorts.hasNext()) {
                        LPort port2 = theTwoPorts.next();
                        double moveUp = 0;
                        if (port.getPos().y < port2.getPos().y) {
                            port2.getEdges().get(0).getTarget().getNode().getPos().y = currentNode
                                    .getPos().y;

                            moveUp = currentNode.getPos().y
                                    - port.getEdges().get(0).getTarget().getNode().getSize().y
                                    - spacing;
                            port.getEdges().get(0).getTarget().getNode().getPos().y = moveUp;
                        } else {
                            port.getEdges().get(0).getTarget().getNode().getPos().y = currentNode
                                    .getPos().y;
                            moveUp = currentNode.getPos().y
                                    - port2.getEdges().get(0).getTarget().getNode().getSize().y
                                    - spacing;
                            port2.getEdges().get(0).getTarget().getNode().getPos().y = moveUp;
                        }
                        if (Math.abs(moveUp) > maximalMoveUp) {
                            maximalMoveUp = moveUp;
                        }
                    } else {
                        port.getEdges().get(0).getTarget().getNode().getPos().y = currentNode
                                .getPos().y;
                    }
                } else if (currentNode.getProperty(Properties.NODE_TYPE).equals(
                        NodeType.SWITCH_RIGHT)) {
                    Iterator<LPort> theTwoPorts = currentNode.getPorts(PortType.OUTPUT).iterator();
                    LPort port = theTwoPorts.next();
                    if (theTwoPorts.hasNext()) {
                        LPort port2 = theTwoPorts.next();
                        double moveDown = 0;
                        if (port.getPos().y < port2.getPos().y) {
                            port.getEdges().get(0).getTarget().getNode().getPos().y = currentNode
                                    .getPos().y;
                            moveDown = currentNode.getPos().y
                                    + port.getEdges().get(0).getTarget().getNode().getSize().y
                                    + spacing;
                            port2.getEdges().get(0).getTarget().getNode().getPos().y = moveDown;
                        } else {
                            port2.getEdges().get(0).getTarget().getNode().getPos().y = currentNode
                                    .getPos().y;
                            moveDown = currentNode.getPos().y
                                    + port2.getEdges().get(0).getTarget().getNode().getSize().y
                                    + spacing;
                            port.getEdges().get(0).getTarget().getNode().getPos().y = moveDown;
                        }
                    } else {
                        port.getEdges().get(0).getTarget().getNode().getPos().y = currentNode
                        .getPos().y;
                    }
                } else {
                    if (currentNode.getPorts().get(0).getType().equals(PortType.OUTPUT)) {
                        currentNode.getPorts().get(0).getEdges().get(0).getTarget().getNode()
                                .getPos().y = currentNode.getPos().y;
                    }
                }
                layeredGraph.getLayers().get(i).getSize().x = Math.max(layeredGraph.getLayers()
                        .get(i).getSize().x, currentNode.getSize().x);
            }
        }

        for (Layer layer : layeredGraph.getLayers()) {
            for (LNode node : layer.getNodes()) {
                node.getPos().y += maximalMoveUp + borspacing;
            }
        }

        KVector graphSize = layeredGraph.getSize();
        for (Layer layer : layeredGraph.getLayers()) {
            graphSize.y = Math.max(graphSize.y, layer.getSize().y);
        }*/
        getMonitor().done();
    }
    
    private void gridToAbsolutePosition(final RailLayer layer) {
        float spacing = layer.getGraph().getProperty(Properties.OBJ_SPACING);
        float borspacing = layer.getGraph().getProperty(Properties.BOR_SPACING);
        float value = borspacing;
        for (LNode node : layer.getRowList().getNodesOrderedByPosition()) {
            node.getPos().y = value;
            System.out.println("Setting y of " + node.toString() + " to " + value);
            value += spacing + node.getSize().y;
        }
    }

}
