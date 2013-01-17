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
        
        return configuration;
    }
    
    /**
     * {@inheritDoc}
     */
    public void process(final LGraph layeredGraph, final IKielerProgressMonitor monitor) {
        monitor.begin("Polyline edge routing", 1);
        float spacing = layeredGraph.getProperty(Properties.OBJ_SPACING);
        float edgeSpaceFac = layeredGraph.getProperty(Properties.EDGE_SPACING_FACTOR);
        
        double xpos = 0.0, layerSpacing = 0.0;
        for (Layer layer : layeredGraph) {
            // set horizontal coordinates for all nodes of the layer
            layer.placeNodes(xpos);
            
            double maxVertDiff = 0;
            for (LNode node : layer) {
                // count the maximal vertical difference of output edges
                double nodeMaxOutputDiff = 0;
                for (LPort port : node.getPorts(PortType.OUTPUT)) {
                    double sourcePos = port.getNode().getPosition().y
                            + port.getPosition().y + port.getAnchor().y;
                    for (LPort targetPort : port.getSuccessorPorts()) {
                        if (targetPort.getNode().getLayer() != node.getLayer()) {
                            double targetPos = targetPort.getNode().getPosition().y
                                    + targetPort.getPosition().y + targetPort.getAnchor().y;
                            nodeMaxOutputDiff = KielerMath.maxd(nodeMaxOutputDiff,
                                    targetPos - sourcePos, sourcePos - targetPos);
                        }
                    }
                }
                
                if (node.getProperty(Properties.NODE_TYPE) == NodeType.LONG_EDGE) {
                    // count the maximal vertical difference of input edges
                    double nodeMaxInputDiff = 0;
                    for (LPort port : node.getPorts(PortType.INPUT)) {
                        double targetPos = port.getNode().getPosition().y
                                + port.getPosition().y + port.getAnchor().y;
                        for (LPort sourcePort : port.getPredecessorPorts()) {
                            double sourcePos = sourcePort.getNode().getPosition().y
                                    + sourcePort.getPosition().y + sourcePort.getAnchor().y;
                            nodeMaxInputDiff = KielerMath.maxd(nodeMaxInputDiff,
                                    targetPos - sourcePos, sourcePos - targetPos);
                        }
                    }
                    
                    LEdge edge = (LEdge) node.getProperty(Properties.ORIGIN);
                    if (nodeMaxInputDiff >= MIN_VERT_DIFF && nodeMaxOutputDiff >= MIN_VERT_DIFF) {
                        // both the incoming and the outgoing edge have significant difference
                        double layerSize = layer.getSize().x;
                        double diff = Math.max(nodeMaxInputDiff, nodeMaxOutputDiff);
                        double deviation = diff / (layerSize / 2 + spacing
                                + LAYER_SPACE_FAC * edgeSpaceFac * diff) * layerSize / 2;
                        if (deviation >= edgeSpaceFac * spacing) {
                            // insert two bend points, one left and one right
                            edge.getBendPoints().add(xpos, node.getPosition().y);
                            edge.getBendPoints().add(xpos + layerSize, node.getPosition().y);
                        } else {
                            // insert only one bend point in the middle
                            edge.getBendPoints().add(xpos + layerSize / 2, node.getPosition().y);
                        }
                        
                    } else if (nodeMaxInputDiff >= MIN_VERT_DIFF) {
                        // only the incoming edge has significant difference
                        edge.getBendPoints().add(xpos, node.getPosition().y);
                        
                    } else if (nodeMaxOutputDiff >= MIN_VERT_DIFF) {
                        // only the outgoing edge has significant difference
                        edge.getBendPoints().add(xpos + layer.getSize().x, node.getPosition().y);
                    }
                }
                
                maxVertDiff = Math.max(maxVertDiff, nodeMaxOutputDiff);
            }
            
            // determine placement of next layer based on the maximal vertical difference
            layerSpacing = spacing + LAYER_SPACE_FAC * edgeSpaceFac * maxVertDiff;
            xpos += layer.getSize().x + layerSpacing;
        }
        layeredGraph.getSize().x = xpos - layerSpacing;
        
        monitor.done();
    }

}
