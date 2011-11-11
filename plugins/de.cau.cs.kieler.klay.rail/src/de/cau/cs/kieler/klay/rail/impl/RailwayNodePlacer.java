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
import de.cau.cs.kieler.klay.layered.ILayoutPhase;
import de.cau.cs.kieler.klay.layered.IntermediateProcessingStrategy;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;
import de.cau.cs.kieler.klay.rail.IPlacement;
import de.cau.cs.kieler.klay.rail.Properties;
import de.cau.cs.kieler.klay.rail.graph.RailLayer;
import de.cau.cs.kieler.klay.rail.graph.RailRow;

/**
 * Node placer for railway layout.
 * 
 * @author jjc
 */
public class RailwayNodePlacer extends AbstractAlgorithm implements ILayoutPhase, IPlacement {

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
    public IntermediateProcessingStrategy getIntermediateProcessingStrategy(final LayeredGraph graph) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public void process(final LayeredGraph layeredGraph) {
        NodePlacerHelper helper = new NodePlacerHelper();
        getMonitor().begin("Linear segments node placement", 1);
        int layerCount = layeredGraph.getLayers().size();

        // determine starting position for straight-first DFS
        LNode walker = null;
        findStart: for (Layer layer : layeredGraph) {
            for (LNode node : layer) {
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
        for (Layer layer : layeredGraph) {
            if (layer instanceof RailLayer) {
                gridToAbsolutePosition((RailLayer) layer, getMinimalPosition(layeredGraph));
            } else {
                throw new IllegalArgumentException("Only works with RailLayers!");
            }
        }
        getMonitor().done();
    }

    /**
     * Method for putting a node in the given position of the grid.
     * 
     * @param target The node to put.
     * @param position The position to put at. Layer is given by the node's layer.
     */
    private void putTargetInGrid(final LNode target, final int position) {
        RailRow targetRow;
        if (target.getLayer() instanceof RailLayer) {
            targetRow = ((RailLayer) target.getLayer()).getRowList();
        } else {
            throw new IllegalArgumentException("Only works with RailLayers!");
        }
        targetRow.addNodeAtPosition(target, position);
    }

    /**
     * Gives the minimal position of a node in ALL layers.
     * 
     * @param graph The graph to inspect.
     * @return An integer representing the minimal grid position.
     */
    private int getMinimalPosition(final LayeredGraph graph) {
        int result = Integer.MAX_VALUE;
        for (Layer layer : graph) {
            if (layer instanceof RailLayer) {
                int i = ((RailLayer) layer).getRowList().getMinimalPosition();
                if (i < result) {
                    result = i;
                }
            } else {
                throw new IllegalArgumentException("Only works with RailLayers!");
            }
        }
        return result;
    }

    /**
     * Places nodes to their final positions given by grid.
     * 
     * @param layer Nodes of which layer shall be placed?
     * @param minPos The overall minimal position {@see #getMinimalPosition(LayeredGraph)}.
     */
    private void gridToAbsolutePosition(final RailLayer layer, final int minPos) {
        float spacing = layer.getGraph().getProperty(Properties.OBJ_SPACING);
        float borspacing = layer.getGraph().getProperty(Properties.BOR_SPACING);
        double value = borspacing;
        for (LNode node : layer.getRowList().getNodesOrderedByPosition()) {
            int position = layer.getRowList().getPosition(node);
            int offset = (-1) * minPos;
            value = offset * (node.getSize().y + spacing) + position * (node.getSize().y + spacing);
            node.getPosition().y = value;
        }
    }

    /**
     * {@inheritDoc}
     */
    public void place(final LPort targetPort, final LPort port, final int offset) {
        putTargetInGrid(targetPort.getNode(),
                NodePlacerHelper.getPositionForNode(targetPort, port, new SimplePatternsImpl())
                        + offset);
    }

}
