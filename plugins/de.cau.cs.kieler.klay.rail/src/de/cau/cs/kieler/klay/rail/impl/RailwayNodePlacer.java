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

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;
import de.cau.cs.kieler.klay.layered.p4nodes.INodePlacer;
import de.cau.cs.kieler.klay.rail.IPlacement;
import de.cau.cs.kieler.klay.rail.Properties;
import de.cau.cs.kieler.klay.rail.graph.RailLayer;
import de.cau.cs.kieler.klay.rail.graph.RailRow;

/**
 * Node placer for railway layout.
 * 
 * @author jjc
 */
public class RailwayNodePlacer extends AbstractAlgorithm implements INodePlacer, IPlacement {

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
        NodePlacerHelper helper = new NodePlacerHelper();
        getMonitor().begin("Linear segments node placement", 1);
        int layerCount = layeredGraph.getLayers().size();

        // determine starting position for straight-first DFS
        LNode walker = null;
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

        helper.placeNodesDFS(walker, layerCount, this);

        // apply grid to graph
        for (Layer layer : layeredGraph.getLayers()) {
            if (layer instanceof RailLayer) {
                gridToAbsolutePosition((RailLayer) layer, getMinimalPosition(layeredGraph));
            } else {
                throw new IllegalArgumentException("Only works with RailLayers!");
            }
        }
        getMonitor().done();
    }

    private void putTargetInGrid(final LNode target, final int position) {
        RailRow targetRow;
        if (target.getLayer() instanceof RailLayer) {
            targetRow = ((RailLayer) target.getLayer()).getRowList();
        } else {
            throw new IllegalArgumentException("Only works with RailLayers!");
        }
        targetRow.addNodeAtPosition(target, position);
    }

    private int getMinimalPosition(final LayeredGraph graph) {
        int result = Integer.MAX_VALUE;
        for (Layer layer : graph.getLayers()) {
            if (layer instanceof RailLayer) {
                int i = ((RailLayer) layer).getRowList().getMinimalPosition();
                if (i < result) {
                    result = i;
                }
            } else {
                // TODO: maybe validate once, gets annoying
            }
        }
        return result;
    }

    private void gridToAbsolutePosition(final RailLayer layer, final int minPos) {
        float spacing = layer.getGraph().getProperty(Properties.OBJ_SPACING);
        float borspacing = layer.getGraph().getProperty(Properties.BOR_SPACING);
        double value = borspacing;
        for (LNode node : layer.getRowList().getNodesOrderedByPosition()) {
            int position = layer.getRowList().getPosition(node);
            int offset = (-1) * minPos;
            value = offset * (node.getSize().y + spacing) + position * (node.getSize().y + spacing);
            node.getPos().y = value;
            System.out.println("Setting y of " + node.toString() + " to " + value);
            System.out.println("Position was " + position);
        }
    }

    public void place(LPort targetPort, LPort port, int offset) {
        putTargetInGrid(targetPort.getNode(),
                NodePlacerHelper.getPositionForNode(targetPort, port, new SimplePatternsImpl())
                        + offset);
    }

}
