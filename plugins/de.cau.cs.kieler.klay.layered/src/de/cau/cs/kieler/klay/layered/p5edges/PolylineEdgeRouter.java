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
package de.cau.cs.kieler.klay.layered.p5edges;

import java.util.EnumSet;
import java.util.Set;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.math.KielerMath;
import de.cau.cs.kieler.klay.layered.ILayoutPhase;
import de.cau.cs.kieler.klay.layered.IntermediateProcessingConfiguration;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.intermediate.LayoutProcessorStrategy;
import de.cau.cs.kieler.klay.layered.properties.GraphProperties;
import de.cau.cs.kieler.klay.layered.properties.NodeType;
import de.cau.cs.kieler.klay.layered.properties.PortType;
import de.cau.cs.kieler.klay.layered.properties.Properties;

/**
 * Edge router module that draws edges with non-orthogonal line segments.
 * 
 * <dl>
 *   <dt>Precondition:</dt><dd>the graph has a proper layering with
 *     assigned node and port positions; the size of each layer is
 *     correctly set</dd>
 *   <dt>Postcondition:</dt><dd>each node is assigned a horizontal coordinate;
 *     the bend points of each edge are set; the width of the whole graph is set</dd>
 * </dl>
 *
 * @author msp
 * @kieler.design 2012-08-10 chsch grh
 * @kieler.rating proposed yellow by msp
 */
public final class PolylineEdgeRouter implements ILayoutPhase {
    
    /* The basic processing strategy for this phase is empty. Depending on the graph features,
     * dependencies on intermediate processors are added dynamically as follows:
     * 
     * Before phase 1:
     *   - None.
     * 
     * Before phase 2:
     *   - For center edge labels:
     *     - LABEL_DUMMY_INSERTER
     * 
     * Before phase 3:
     *   - For edge labels:
     *     - LABEL_SIDE_SELECTOR
     *   
     *   - For center edge labels:
     *     - LABEL_SIDE_SELECTOR
     *     - LABEL_DUMMY_SWITCHER
     *     
     *   - For northern and southern ports:
     *     - NORTH_SOUTH_PORT_PREPROCESSOR
     * 
     * Before phase 4:
     *   - None.
     * 
     * Before phase 5:
     *   - None.
     * 
     * After phase 5:
     *   - For center edge labels:
     *     - LABEL_DUMMY_REMOVER
     *     
     *   - For end edge labels:
     *     - END_LABEL_PROCESSOR
     *     
     *   - For northern and southern ports:
     *     - NORTH_SOUTH_PORT_POSTPROCESSOR
     */
    
    /** additional processor dependencies for graphs with center edge labels. */
    private static final IntermediateProcessingConfiguration CENTER_EDGE_LABEL_PROCESSING_ADDITIONS =
        new IntermediateProcessingConfiguration(
                // Before Phase 1
                null,
                
                // Before Phase 2
                EnumSet.of(LayoutProcessorStrategy.LABEL_DUMMY_INSERTER),
                
                // Before Phase 3
                EnumSet.of(LayoutProcessorStrategy.LABEL_SIDE_SELECTOR,
                           LayoutProcessorStrategy.LABEL_DUMMY_SWITCHER),
                
                // Before Phase 4
                null,
                
                // Before Phase 5
                null,
                
                // After Phase 5
                EnumSet.of(LayoutProcessorStrategy.LABEL_DUMMY_REMOVER));
    
    /** additional processor dependencies for graphs with head or tail edge labels. */
    private static final IntermediateProcessingConfiguration END_EDGE_LABEL_PROCESSING_ADDITIONS =
        new IntermediateProcessingConfiguration(
                // Before Phase 1
                null,
                
                // Before Phase 2
                null,
                
                // Before Phase 3
                EnumSet.of(LayoutProcessorStrategy.LABEL_SIDE_SELECTOR),
                
                // Before Phase 4
                null,
                
                // Before Phase 5
                null,
                
                // After Phase 5
                EnumSet.of(LayoutProcessorStrategy.END_LABEL_PROCESSOR));
    
    /** additional processor dependencies for graphs with northern / southern non-free ports. */
    private static final IntermediateProcessingConfiguration NORTH_SOUTH_PORT_PROCESSING_ADDITIONS =
        new IntermediateProcessingConfiguration(
                // Before Phase 1
                null,
                
                // Before Phase 2
                null,
                
                // Before Phase 3
                EnumSet.of(LayoutProcessorStrategy.NORTH_SOUTH_PORT_PREPROCESSOR),
                
                // Before Phase 4
                null,
                
                // Before Phase 5
                null,
                
                // After Phase 5
                EnumSet.of(LayoutProcessorStrategy.NORTH_SOUTH_PORT_POSTPROCESSOR));
    
    /** the minimal vertical difference for creating bend points. */
    private static final double MIN_VERT_DIFF = 1.0;
    /** factor for layer spacing. */
    private static final double LAYER_SPACE_FAC = 0.2;
    
    /**
     * {@inheritDoc}
     */
    public IntermediateProcessingConfiguration getIntermediateProcessingConfiguration(
            final LGraph graph) {
        
        Set<GraphProperties> graphProperties = graph.getProperty(Properties.GRAPH_PROPERTIES);
        
        // Basic configuration
        IntermediateProcessingConfiguration configuration = new IntermediateProcessingConfiguration();
        
        // Additional dependencies
        if (graphProperties.contains(GraphProperties.CENTER_LABELS)) {
            configuration.addAll(CENTER_EDGE_LABEL_PROCESSING_ADDITIONS);
        }
        
        if (graphProperties.contains(GraphProperties.END_LABELS)) {
            configuration.addAll(END_EDGE_LABEL_PROCESSING_ADDITIONS);
        }

        if (graphProperties.contains(GraphProperties.NORTH_SOUTH_PORTS)) {
            configuration.addAll(NORTH_SOUTH_PORT_PROCESSING_ADDITIONS);
        }
        
        return configuration;
    }
    
    /**
     * {@inheritDoc}
     */
    public void process(final LGraph layeredGraph, final IKielerProgressMonitor monitor) {
        monitor.begin("Polyline edge routing", 1);
        
        float spacing = layeredGraph.getProperty(Properties.OBJ_SPACING);
        float edgeSpaceFac = layeredGraph.getProperty(Properties.EDGE_SPACING_FACTOR);
        
        double xpos = 0.0;
        double layerSpacing = 0.0;
        
        // Iterate over the layers
        for (Layer layer : layeredGraph) {
            // set horizontal coordinates for all nodes of the layer
            layer.placeNodes(xpos);
            
            double maxVertDiff = 0.0;
            
            // Iterate over the layer's nodes
            for (LNode node : layer) {
                // Calculate the maximal vertical span of output edges
                double maxOutputYDiff = 0.0;
                for (LPort sourcePort : node.getPorts(PortType.OUTPUT)) {
                    double sourcePos = sourcePort.getAbsoluteAnchor().y;
                    
                    // Iterate over the connected target ports
                    for (LPort targetPort : sourcePort.getSuccessorPorts()) {
                        // Check for vertical span if the ports are in different layers
                        if (node.getLayer() != targetPort.getNode().getLayer()) {
                            double targetPos = targetPort.getAbsoluteAnchor().y;
                            
                            maxOutputYDiff = KielerMath.maxd(
                                    maxOutputYDiff,
                                    targetPos - sourcePos,
                                    sourcePos - targetPos);
                        }
                    }
                }
                
                // If we have LONG_EDGE or LABEL dummy nodes, these must be treated differently.
                NodeType nodeType = node.getProperty(Properties.NODE_TYPE);
                if (nodeType == NodeType.LONG_EDGE) {
                    // Calculate the maximal vertical span of input edges
                    double maxInputYDiff = 0.0;
                    for (LPort targetPort : node.getPorts(PortType.INPUT)) {
                        double targetPos = targetPort.getAbsoluteAnchor().y;

                        // Iterate over the connected source ports
                        for (LPort sourcePort : targetPort.getPredecessorPorts()) {
                            // Check for vertical span if the ports are in different layers
                            if (node.getLayer() != sourcePort.getNode().getLayer()) {
                                double sourcePos = sourcePort.getAbsoluteAnchor().y;
                                
                                maxInputYDiff = KielerMath.maxd(
                                        maxInputYDiff,
                                        targetPos - sourcePos,
                                        sourcePos - targetPos);
                            }
                        }
                    }
                    
                    if (maxInputYDiff >= MIN_VERT_DIFF && maxOutputYDiff >= MIN_VERT_DIFF) {
                        // Both the incoming and the outgoing edges have significant differences. Check
                        // how large the vertical span is in relation to the layer's width and thus
                        // determine if we need to insert bend points at all
                        double layerSize = layer.getSize().x;
                        double diff = Math.max(maxInputYDiff, maxOutputYDiff);
                        double deviation = diff / (layerSize / 2.0 + spacing
                                + LAYER_SPACE_FAC * edgeSpaceFac * diff) * layerSize / 2.0;
                        
                        if (deviation >= edgeSpaceFac * spacing) {
                            // Insert for incoming and outgoing edges
                            for (LEdge incoming : node.getIncomingEdges()) {
                                incoming.getBendPoints().add(
                                        xpos, node.getPosition().y);
                            }

                            for (LEdge outgoing : node.getOutgoingEdges()) {
                                outgoing.getBendPoints().add(
                                        xpos + layer.getSize().x, node.getPosition().y);
                            }
                        } else {
                            // Insert only for incoming edges in the layer's horizontal center
                            for (LEdge incoming : node.getIncomingEdges()) {
                                incoming.getBendPoints().add(
                                        xpos + layerSize / 2.0, node.getPosition().y);
                            }
                        }
                    } else if (maxInputYDiff >= MIN_VERT_DIFF) {
                        // Only the incoming edges have significant differences
                        for (LEdge incoming : node.getIncomingEdges()) {
                            incoming.getBendPoints().add(
                                    xpos, node.getPosition().y);
                        }
                    } else if (maxOutputYDiff >= MIN_VERT_DIFF) {
                        // Only the outgoing edges have significant differences
                        for (LEdge outgoing : node.getOutgoingEdges()) {
                            outgoing.getBendPoints().add(
                                    xpos + layer.getSize().x, node.getPosition().y);
                        }
                    }
                } else if (nodeType == NodeType.LABEL) {
                    // Insert bend points left and right of the node so that the label does not
                    // overlap the edge. We assume that there's only one input and one output edge,
                    // which should be true for label dummy nodes.
                    node.getIncomingEdges().iterator().next().getBendPoints().add(
                            xpos, node.getPosition().y);
                    node.getOutgoingEdges().iterator().next().getBendPoints().add(
                            xpos + layer.getSize().x, node.getPosition().y);
                }
                
                maxVertDiff = Math.max(maxVertDiff, maxOutputYDiff);
            }
            
            // Determine placement of next layer based on the maximal vertical difference (as the
            // maximum vertical difference edges span grows, the layer grows wider to allow enough
            // space for such sloped edges to avoid too harsh angles)
            layerSpacing = spacing + LAYER_SPACE_FAC * edgeSpaceFac * maxVertDiff;
            xpos += layer.getSize().x + layerSpacing;
        }
        
        // Set the graph's horizontal size
        layeredGraph.getSize().x = xpos - layerSpacing;
        
        monitor.done();
    }

}
