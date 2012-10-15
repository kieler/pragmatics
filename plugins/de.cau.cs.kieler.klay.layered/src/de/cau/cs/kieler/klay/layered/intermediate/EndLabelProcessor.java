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

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.kiml.options.EdgeLabelPlacement;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.klay.layered.ILayoutProcessor;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LLabel;
import de.cau.cs.kieler.klay.layered.graph.LLabel.LSide;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.properties.PortLabelPlacement;
import de.cau.cs.kieler.klay.layered.properties.Properties;

/**
 * <p>This intermediate processor does the necessary calculations for an absolute positioning
 * of all end and port labels. It uses the port sides and the side choice made before to find
 * this positioning.</p>
 * 
 * <dl>
 *   <dt>Precondition:</dt><dd>a layered graph; no dummy nodes;
 *   labels are marked with a placement side; nodes have port sides.</dd>
 *   <dt>Postcondition:</dt><dd>edge and port labels have absolute coordinates.</dd>
 *   <dt>Slots:</dt><dd>After phase 5.</dd>
 *   <dt>Same-slot dependencies:</dt><dd>{@link LongEdgeJoiner}</dd>
 *                                   <dd>{@link NorthSouthPortPostProcessor}</dd>
 *                                   <dd>{@link LabelDummyRemover}</dd>
 *                                   <dd>{@link ReverseEdgeRestorer}</dd>
 * </dl>
 * 
 * @author jjc
 * @kieler.rating proposed yellow cds
 */
public class EndLabelProcessor extends AbstractAlgorithm implements ILayoutProcessor {

    /** Distance of a label to its edge. */
    private static final int LABEL_DISTANCE = 0;
    
    /** Offset introduced by GMF to ports, results have to be adjusted by this factor. */
    private static final int PORT_LABEL_DISTANCE = 3;
    
    /**
     * In case of northern ports, labels have to be stacked to avoid overlaps.
     * The necessary offset is stored here.
     */
    private HashMap<LNode, Double> northOffset; 
    
    /** The stacking offset for southern labels is stored here. */
    private HashMap<LNode, Double> southOffset;
    
    /**
     * Port labels have to be stacked on northern or southern ports as well if
     * placed outside. This offset is memorized here.
     */
    private HashMap<LPort, Double> portLabelOffsetHint;
    
    /**
     * {@inheritDoc}
     */
    public void process(final LGraph layeredGraph) {
        // Initialize the offset maps
        northOffset = new HashMap<LNode, Double>();
        southOffset = new HashMap<LNode, Double>();
        portLabelOffsetHint = new HashMap<LPort, Double>();
        
        for (Layer layer : layeredGraph.getLayers()) {
            for (LNode node : layer.getNodes()) {
                for (LEdge edge : node.getOutgoingEdges()) {
                    for (LLabel label : edge.getLabels()) {
                        // Only consider end labels
                        if (label.getProperty(LayoutOptions.EDGE_LABEL_PLACEMENT)
                                == EdgeLabelPlacement.TAIL
                                ||
                            label.getProperty(LayoutOptions.EDGE_LABEL_PLACEMENT)
                                == EdgeLabelPlacement.HEAD) {
                            
                            LPort port = edge.getSource();
                            
                            // When a tail label is present, the target port is used
                            // for orientation
                            if (label.getProperty(LayoutOptions.EDGE_LABEL_PLACEMENT)
                                == EdgeLabelPlacement.HEAD) {
                                port = edge.getTarget();
                            }
                            
                            // Calculate, how many space the end label has to keep clear for a
                            // port label
                            double portLabelOffsetX = 0.0;
                            double portLabelOffsetY = 0.0;
                            if (layeredGraph.getProperty(Properties.PORT_LABEL_PLACEMENT) 
                                    == PortLabelPlacement.OUTSIDE) {
                                for (LLabel portLabel : port.getLabels()) {
                                    portLabelOffsetX = Math.max(portLabelOffsetX, portLabel.getSize().x)
                                                                + PORT_LABEL_DISTANCE;
                                    portLabelOffsetY = Math.max(portLabelOffsetY, portLabel.getSize().y)
                                                                + PORT_LABEL_DISTANCE;
                                }
                            }
                            
                            // Initialize offset with zero if no offset was present
                            if (!northOffset.containsKey(port.getNode())) {
                                northOffset.put(port.getNode(), 0.0);
                            }
                            if (!southOffset.containsKey(port.getNode())) {
                                southOffset.put(port.getNode(), 0.0);
                            }
                            if (!portLabelOffsetHint.containsKey(port)) {
                                portLabelOffsetHint.put(port, 0.0);
                            }
                            
                            // Calculate end label position based on side choice
                            // Port side undefined can be left out, because there would be no reasonable
                            // way of handling them
                            if (label.getSide() == LSide.UP) {
                                switch (port.getSide()) {
                                case WEST:
                                    label.getPosition().x = port.getAbsoluteAnchor().x
                                                            - label.getSize().x
                                                            - portLabelOffsetX;
                                    label.getPosition().y = port.getAbsoluteAnchor().y
                                                            - label.getSize().y - LABEL_DISTANCE;
                                    break;
                                case EAST:
                                    label.getPosition().x = port.getAbsoluteAnchor().x
                                                            + portLabelOffsetX;
                                    label.getPosition().y = port.getAbsoluteAnchor().y
                                            - label.getSize().y - LABEL_DISTANCE;
                                    break;
                                case NORTH:
                                    label.getPosition().x = port.getAbsoluteAnchor().x
                                                            - label.getSize().x;
                                    label.getPosition().y = port.getAbsoluteAnchor().y
                                                            - label.getSize().y - LABEL_DISTANCE
                                                            - northOffset.get(port.getNode())
                                                            - portLabelOffsetY;
                                    portLabelOffsetHint.put(port, northOffset.get(node));
                                    northOffset.put(node,
                                            northOffset.get(port.getNode())
                                                            + label.getSize().y
                                                            + portLabelOffsetY);
                                    break;
                                case SOUTH:
                                    label.getPosition().x = port.getAbsoluteAnchor().x
                                    - label.getSize().x;
                                    label.getPosition().y = port.getAbsoluteAnchor().y
                                                            + southOffset.get(port.getNode())
                                                            + portLabelOffsetY;
                                    portLabelOffsetHint.put(port, southOffset.get(node));
                                    southOffset.put(node,
                                            southOffset.get(port.getNode())
                                                            + label.getSize().y
                                                            + portLabelOffsetY);
                                    break;
                                }
                            } else {
                                switch (port.getSide()) {
                                case WEST:
                                    label.getPosition().x = port.getAbsoluteAnchor().x
                                                            - label.getSize().x
                                                            - portLabelOffsetX;
                                    label.getPosition().y = port.getAbsoluteAnchor().y + LABEL_DISTANCE;
                                    break;
                                case EAST:
                                    label.getPosition().x = port.getAbsoluteAnchor().x
                                                            + portLabelOffsetX;
                                    label.getPosition().y = port.getAbsoluteAnchor().y + LABEL_DISTANCE;
                                    break;
                                case NORTH:
                                    label.getPosition().x = port.getAbsoluteAnchor().x;
                                    label.getPosition().y = port.getAbsoluteAnchor().y
                                                            - label.getSize().y - LABEL_DISTANCE
                                                            - northOffset.get(node)
                                                            - portLabelOffsetY;
                                    portLabelOffsetHint.put(port, northOffset.get(node));
                                    northOffset.put(node,
                                            northOffset.get(port.getNode())
                                                            + label.getSize().y
                                                            + portLabelOffsetY);
                                    break;
                                case SOUTH:
                                    label.getPosition().x = port.getAbsoluteAnchor().x;
                                    label.getPosition().y = port.getAbsoluteAnchor().y
                                                            + southOffset.get(port.getNode())
                                                            + portLabelOffsetY;
                                    portLabelOffsetHint.put(port, southOffset.get(node));
                                    southOffset.put(node,
                                            southOffset.get(port.getNode())
                                            + label.getSize().y
                                            + portLabelOffsetY);
                                    break;
                                }
                            }
                        }
                    }
                    
                    // Handle port labels of all ports of the edge
                    List<LPort> ports = new LinkedList<LPort>();
                    ports.add(edge.getSource());
                    ports.add(edge.getTarget());
                    for (LPort port : ports) {
                        for (LLabel portLabel : port.getLabels()) {
                            if (layeredGraph.getProperty(Properties.PORT_LABEL_PLACEMENT)
                                    == PortLabelPlacement.OUTSIDE) {
                                if (portLabel.getSide() == LSide.UP) {
                                    switch (port.getSide()) {
                                    case WEST:
                                        portLabel.getPosition().x = -portLabel.getSize().x;
                                        portLabel.getPosition().y = -portLabel.getSize().y
                                                                    + PORT_LABEL_DISTANCE;
                                        break;
                                    case EAST:
                                        portLabel.getPosition().x = port.getSize().x;
                                        portLabel.getPosition().y = -portLabel.getSize().y
                                                                    + PORT_LABEL_DISTANCE;
                                        break;
                                    case NORTH:
                                        portLabel.getPosition().x = -port.getSize().x / 2
                                                                    - portLabel.getSize().x;
                                        portLabel.getPosition().y = -port.getSize().y
                                                                    - portLabel.getSize().y
                                                                    - portLabelOffsetHint.get(port);
                                        break;
                                    case SOUTH:
                                        portLabel.getPosition().x = -port.getSize().x / 2
                                                                    - portLabel.getSize().x;
                                        portLabel.getPosition().y = port.getSize().y
                                                                    + portLabelOffsetHint.get(port);
                                        break;
                                    }
                                } else {
                                    switch (port.getSide()) {
                                    case WEST:
                                        portLabel.getPosition().x = -portLabel.getSize().x;
                                        portLabel.getPosition().y = port.getSize().y 
                                                                    - PORT_LABEL_DISTANCE;
                                        break;
                                    case EAST:
                                        portLabel.getPosition().x = port.getSize().x;
                                        portLabel.getPosition().y = port.getSize().y
                                                                    - PORT_LABEL_DISTANCE;
                                        break;
                                    case NORTH:
                                        portLabel.getPosition().x = port.getSize().x / 2;
                                        portLabel.getPosition().y = -port.getSize().y
                                                                    - portLabel.getSize().y
                                                                    - portLabelOffsetHint.get(port);
                                        break;
                                    case SOUTH:
                                        portLabel.getPosition().x = port.getSize().x / 2;
                                        portLabel.getPosition().y = port.getSize().y
                                                                    + portLabelOffsetHint.get(port);
                                        break;
                                    }
                                }
                            } else if (layeredGraph.getProperty(Properties.PORT_LABEL_PLACEMENT) 
                                    == PortLabelPlacement.INSIDE) {
                                switch (port.getSide()) {
                                case WEST:
                                    portLabel.getPosition().x = port.getSize().x + 1;
                                    portLabel.getPosition().y = 0;
                                    break;
                                case EAST:
                                    portLabel.getPosition().x = -portLabel.getSize().x - 1;
                                    portLabel.getPosition().y = 0;
                                    break;
                                case NORTH:
                                    portLabel.getPosition().x = -portLabel.getSize().x / 2;
                                    portLabel.getPosition().y = port.getSize().y;
                                    break;
                                case SOUTH:
                                    portLabel.getPosition().x = -portLabel.getSize().x / 2;
                                    portLabel.getPosition().y = -portLabel.getSize().y;
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}
