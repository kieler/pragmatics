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
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.klay.layered.ILayoutPhase;
import de.cau.cs.kieler.klay.layered.IntermediateProcessingStrategy;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;
import de.cau.cs.kieler.klay.layered.properties.NodeType;
import de.cau.cs.kieler.klay.rail.Properties;

/**
 * 
 * Edge router for routing edges "railway style".
 * 
 * @author jjc
 * 
 */
public class RailwayEdgeRouter extends AbstractAlgorithm implements ILayoutPhase {

    private float minLayerDist;
    /** the bounds of a line's slope in which it is not bent. */
    public static final double SLOPE_TOLERANCE = 0.2f;

    
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
        float borspacing = layeredGraph.getProperty(Properties.BOR_SPACING);
        minLayerDist = layeredGraph.getProperty(Properties.LAYER_DISTANCE);

        double xpos = borspacing;
        for (Layer layer : layeredGraph.getLayers()) {
            for (LNode node : layer) {
                if (node.getSize().x > layer.getSize().x) {
                    layer.getSize().x = node.getSize().x;
                }
            }
            layer.placeNodes(xpos);
            xpos += layer.getSize().x + minLayerDist;
            layeredGraph.getSize().x = xpos;
        }

        for (Layer layer : layeredGraph) {
            NodeLoop:
            for (LNode node : layer) {
                for (LPort port : node.getPorts()) {
                    for (LEdge edge : port.getConnectedEdges()) {
                        if (node.getProperty(
                                de.cau.cs.kieler.klay.layered.properties.Properties.NODE_TYPE).equals(
                                        NodeType.LONG_EDGE)) {
                            
                            edge.getBendPoints().clear();
                            continue NodeLoop;
                        }
                        if (edge.getSource() == port) {
                            KVector srcPos = new KVector(port.getPosition()).add(node.getPosition());
                            KVector trgPos = new KVector(edge.getTarget().getPosition()).add(edge
                                    .getTarget().getNode().getPosition());

                            double slope = Double.POSITIVE_INFINITY;
                            if (trgPos.x != srcPos.x) {
                                slope = (trgPos.y - srcPos.y) / (trgPos.x - srcPos.x);
                            }

                            if (Math.abs(slope) > SLOPE_TOLERANCE) {
                                double newSlope = Math.tan(Math.toRadians(layeredGraph
                                        .getProperty(Properties.BEND_ANGLE)));

                                KVector bendPoint = new KVector(Math.abs(trgPos.y - srcPos.y)
                                        / newSlope + srcPos.x, trgPos.y);

                                edge.getBendPoints().add(bendPoint);
                                if (bendPoint.x > trgPos.x) {
                                    pushBackLayers(layer.getIndex() + 1, bendPoint.x - trgPos.x
                                            + minLayerDist, layeredGraph);
                                }
                            }
                        }
                    }
                }
            }
        }

    }

    /**
     * Method to push back a layer and all that are following him by a certain amount.
     * 
     * @param startAtLayer
     *            Which layer to start at
     * @param amount
     *            How far shall it be moved
     * @param layeredGraph
     *            The graph which holds the layer
     */
    private void pushBackLayers(final int startAtLayer, final double amount,
            final LayeredGraph layeredGraph) {
        double xpos = amount
                + layeredGraph.getLayers().get(startAtLayer).getNodes().get(0).getPosition().x;
        for (int i = startAtLayer; i < layeredGraph.getLayers().size(); i++) {
            Layer layer = layeredGraph.getLayers().get(i);

            for (LNode node : layer) {
                node.getPosition().x = xpos;
            }
            xpos += layer.getSize().x + minLayerDist;
            layeredGraph.getSize().x = xpos;
        }
    }

}
