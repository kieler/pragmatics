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

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;
import de.cau.cs.kieler.klay.layered.modules.INodePlacer;
import de.cau.cs.kieler.klay.rail.Properties;
import de.cau.cs.kieler.klay.rail.graph.RailLayer;
import de.cau.cs.kieler.klay.rail.options.NodeType;
import de.cau.cs.kieler.klay.rail.options.PortType;

/**
 * Node placer for railway layout.
 * 
 * @author jjc
 */
public class RailwayNodePlacer extends AbstractAlgorithm implements INodePlacer {

    private List<LNode> known = new LinkedList<LNode>();
    private Stack<LNode> nextNodes = new Stack<LNode>();

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

        // determine starting position for straight-first DFS
        LNode walker = null;
        known = new LinkedList<LNode>();
        nextNodes = new Stack<LNode>();
        findStart: for (Layer layer : layeredGraph.getLayers()) {
            for (LNode node : layer.getNodes()) {
                if (node.getProperty(Properties.ENTRY_POINT)) {
                    walker = node;
                    break findStart;
                }
            }
        }
        if (walker == null) {
            throw new IllegalArgumentException("No entry point found!");
        }
        if (walker.getLayer() instanceof RailLayer) {
            ((RailLayer) walker.getLayer()).getRowList().addNodeAtPosition(walker, 0);
        } else {
            throw new IllegalArgumentException("Only works with RailLayers!");
        }

        // start straight first DFS
        nextNodes.push(walker);
        known.add(walker);
        int i = 1;
        while (!nextNodes.isEmpty()) {
            walker = nextNodes.pop();
            boolean found = false;
            if (allNeighborsKnown(walker)) {
                continue;
            }
            for (LPort port : walker.getPorts()) {
                LNode target = port.getEdges().get(0).getTarget().getNode();
                if ((port.getProperty(Properties.PORT_TYPE).equals(PortType.STRAIGHT) || port
                        .getProperty(Properties.PORT_TYPE).equals(PortType.STUMP))
                        && !known.contains(target)) {
                    found = true;
                    known.add(target);
                    if (!allNeighborsKnown(walker)) {
                        nextNodes.push(walker);
                    }
                    nextNodes.push(target);
                    if (target.getLayer() instanceof RailLayer
                            && walker.getLayer() instanceof RailLayer) {
                        int position = ((RailLayer) walker.getLayer()).getRowList().getPosition(
                                walker);
                        ((RailLayer) target.getLayer()).getRowList().addNodeAtPosition(target,
                                position);
                    } else {
                        throw new IllegalArgumentException("Only works with RailLayers!");
                    }
                    break;
                }
            }
            if (!found) {
                for (LPort port : walker.getPorts()) {
                    LNode target = port.getEdges().get(0).getTarget().getNode();
                    if (port.getProperty(Properties.PORT_TYPE).equals(PortType.BRANCH)
                            && !known.contains(target)) {
                        if (!known.contains(target)) {
                            known.add(target);
                            if (!allNeighborsKnown(walker)) {
                                nextNodes.push(walker);
                            }
                            nextNodes.push(target);
                            if (target.getLayer() instanceof RailLayer
                                    && walker.getLayer() instanceof RailLayer) {
                                int position = i;
                                i++;
                                ((RailLayer) target.getLayer()).getRowList().addNodeAtPosition(
                                        target, position);
                            } else {
                                throw new IllegalArgumentException("Only works with RailLayers!");
                            }
                        }
                    }
                }
            }

        }
        for (Layer layer : layeredGraph.getLayers()) {
            if (layer instanceof RailLayer) {
                gridToAbsolutePosition((RailLayer) layer);
            } else {
                throw new IllegalArgumentException("Only works with RailLayers!");
            }
        }
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

    private boolean allNeighborsKnown(final LNode node) {
        boolean result = true;
        for (LPort port : node.getPorts()) {
            result &= known.contains(port.getEdges().get(0).getTarget().getNode());
        }
        return result;
    }

}
