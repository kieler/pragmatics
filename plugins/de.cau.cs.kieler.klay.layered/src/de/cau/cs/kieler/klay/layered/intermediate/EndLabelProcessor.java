/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.layered.intermediate;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.kiml.options.EdgeLabelPlacement;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.klay.layered.ILayoutProcessor;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LLabel;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.graph.LGraph;

/**
 * 
 * @author jjc
 * @kieler.design proposed by msp
 */
public class EndLabelProcessor extends AbstractAlgorithm implements ILayoutProcessor {

    // TODO: offer as layout option
    /** Distance of a label to its edge. */
    private static final int LABEL_DISTANCE = 1;

    /**
     * {@inheritDoc}
     */
    public void process(final LGraph layeredGraph) {
        for (Layer layer : layeredGraph.getLayers()) {
            for (LNode node : layer.getNodes()) {
                // Handle ports and their edges on the west side of the node.
                // Thus, tail labels will be placed.
                Iterable<LPort> westPorts = node.getPorts(PortSide.WEST);
                int westPortCount = 0;
                // When only two edges are present, the labels shall be placed
                // above and below the lines.
                // To achieve that, the "higher" port has to be determined by 
                // finding the port with the smallest y coordinate.
                LPort higherPort = null;
                double higherPortPos = Double.POSITIVE_INFINITY;
                for (LPort westPort : westPorts) {
                    westPortCount++;
                    if (westPort.getAbsoluteAnchor().y < higherPortPos) {
                        higherPort = westPort;
                        higherPortPos = westPort.getAbsoluteAnchor().y;
                    }
                }
                if (westPortCount == 2) {
                    for (LPort westPort : westPorts) {
                        for (LEdge edge : westPort.getIncomingEdges()) {
                            for (LLabel label : edge.getLabels()) {
                                if (label.getProperty(LayoutOptions.EDGE_LABEL_PLACEMENT)
                                        == EdgeLabelPlacement.TAIL) {
                                    double portPosX = westPort.getAbsoluteAnchor().x;
                                    double portPosY = westPort.getAbsoluteAnchor().y;
                                    if (westPort.equals(higherPort)) {
                                        label.getPosition().x = portPosX - label.getSize().x;
                                        label.getPosition().y = portPosY  - label.getSize().y
                                                - LABEL_DISTANCE;
                                    } else {
                                        label.getPosition().x = portPosX - label.getSize().x;
                                        label.getPosition().y = portPosY + LABEL_DISTANCE;
                                    }
                                }
                            }
                        }
                    }
                } else {
                    for (LPort westPort : westPorts) {
                        for (LEdge edge : westPort.getIncomingEdges()) {
                            for (LLabel label : edge.getLabels()) {
                                if (label.getProperty(LayoutOptions.EDGE_LABEL_PLACEMENT)
                                        == EdgeLabelPlacement.TAIL) {
                                    double portPosX = westPort.getAbsoluteAnchor().x;
                                    double portPosY = westPort.getAbsoluteAnchor().y;
                                    label.getPosition().x = portPosX - label.getSize().x;
                                    label.getPosition().y = portPosY + LABEL_DISTANCE;
                                }
                            }
                        }
                    }
                }
                
                // Handle ports and their edges on the east side of a node
                // Thus, head labels will be placed
                Iterable<LPort> eastPorts = node.getPorts(PortSide.EAST);
                int eastPortCount = 0;
                // When only two edges are present, the labels shall be placed
                // above and below the lines.
                // To achieve that, the "higher" port has to be determined by 
                // finding the port with the smallest y coordinate.
                higherPort = null;
                higherPortPos = Double.POSITIVE_INFINITY;
                for (LPort eastPort : eastPorts) {
                    eastPortCount++;
                    if (eastPort.getAbsoluteAnchor().y < higherPortPos) {
                        higherPort = eastPort;
                        higherPortPos = eastPort.getAbsoluteAnchor().y;
                    }
                }
                if (eastPortCount == 2) {
                    for (LPort eastPort : eastPorts) {
                        for (LEdge edge : eastPort.getOutgoingEdges()) {
                            for (LLabel label : edge.getLabels()) {
                                if (label.getProperty(LayoutOptions.EDGE_LABEL_PLACEMENT)
                                        == EdgeLabelPlacement.HEAD) {
                                    double portPosX = eastPort.getAbsoluteAnchor().x;
                                    double portPosY = eastPort.getAbsoluteAnchor().y;
                                    if (eastPort.equals(higherPort)) {
                                        label.getPosition().x = portPosX;
                                        label.getPosition().y = portPosY  - label.getSize().y
                                                - LABEL_DISTANCE;
                                    } else {
                                        label.getPosition().x = portPosX;
                                        label.getPosition().y = portPosY + LABEL_DISTANCE;
                                    }
                                }
                            }
                        }
                    }
                } else {
                    for (LPort eastPort : eastPorts) {
                        for (LEdge edge : eastPort.getOutgoingEdges()) {
                            for (LLabel label : edge.getLabels()) {
                                if (label.getProperty(LayoutOptions.EDGE_LABEL_PLACEMENT)
                                        == EdgeLabelPlacement.HEAD) {
                                    double portPosX = eastPort.getAbsoluteAnchor().x;
                                    double portPosY = eastPort.getAbsoluteAnchor().y;
                                    label.getPosition().x = portPosX;
                                    label.getPosition().y = portPosY + LABEL_DISTANCE;
                                }
                            }
                        }
                    }
                }
            }
        }

    }

}
