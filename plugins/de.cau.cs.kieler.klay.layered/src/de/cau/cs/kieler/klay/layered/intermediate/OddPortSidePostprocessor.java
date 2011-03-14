/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.layered.intermediate;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.kiml.options.PortType;
import de.cau.cs.kieler.klay.layered.ILayoutProcessor;
import de.cau.cs.kieler.klay.layered.Properties;
import de.cau.cs.kieler.klay.layered.Properties.NodeType;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;

/**
 * Removes odd port side dummy nodes.
 * 
 * <p>Odd port side dummy nodes are nodes that have their
 * {@link de.cau.cs.kieler.klay.layered.Properties#NODE_TYPE} property set to
 * {@link de.cau.cs.kieler.klay.layered.Properties.NodeType#ODD_PORT_SIDE}. These
 * are removed, the edge connected to the original node being rerouted to the long
 * edge dummy node also inserted. (see {@link OddPortSidePreprocessor}) The result
 * is a graph that's not properly layered anymore: the long edge dummy node is connected
 * to the original node, both being in the same layer.</p>
 * 
 * <dl>
 *   <dt>Precondition:</dt><dd>a layered graph.</dd>
 *   <dt>Postcondition:</dt><dd>odd port side dummy nodes have been removed; the graph may
 *     not be properly layered anymore.</dd>
 * </dl>
 * 
 * @see OddPortSidePreprocessor
 * @author cds
 */
public class OddPortSidePostprocessor extends AbstractAlgorithm implements ILayoutProcessor {

    /**
     * {@inheritDoc}
     */
    public void process(final LayeredGraph layeredGraph) {
        getMonitor().begin("Odd port side postprocessing", 1);
        
        // Iterate through the layers, looking for odd port side dummies
        Layer[] layerArray = layeredGraph.getLayers().toArray(new Layer[0]);
        for (Layer layer : layerArray) {
            // Look for odd port side dummy nodes
            LNode[] nodeArray = layer.getNodes().toArray(new LNode[0]);
            for (LNode node : nodeArray) {
                if (node.getProperty(Properties.NODE_TYPE) == Properties.NodeType.ODD_PORT_SIDE) {
                    // Restore the edges and remove the dummy node
                    processOddPortSideDummy(node);
                    node.setLayer(null);
                }
            }
        }
        
        getMonitor().done();
    }
    
    /**
     * Reroutes the edges connected to the dummy node and restores the original node's port's
     * original port type.
     * 
     * @param dummy the odd port side dummy node.
     */
    private void processOddPortSideDummy(final LNode dummy) {
        // The dummy has exactly one port with two edges connected to it
        LPort port = dummy.getPorts().get(0);
        LEdge[] edges = port.getEdges().toArray(new LEdge[2]);
        
        switch (port.getType()) {
        case INPUT:
            // Find out which one of the edges is connected to the long edge dummy node. That's
            // the edge we're going to keep
            if (edges[0].getSource().getNode().getProperty(Properties.NODE_TYPE) == NodeType.LONG_EDGE) {
                LPort edge1Source = edges[1].getSource();
                edges[0].setTarget(edge1Source);
                edge1Source.setType(PortType.INPUT);
                edges[1].setSource(null);
            } else {
                LPort edge0Source = edges[0].getSource();
                edges[1].setTarget(edge0Source);
                edge0Source.setType(PortType.INPUT);
                edges[0].setSource(null);
            }
            
            break;
        
        case OUTPUT:
            // Find out which one of the edges is connected to the long edge dummy node. That's
            // the edge we're going to keep
            if (edges[0].getTarget().getNode().getProperty(Properties.NODE_TYPE) == NodeType.LONG_EDGE) {
                LPort edge1Target = edges[1].getTarget();
                edges[0].setSource(edge1Target);
                edge1Target.setType(PortType.OUTPUT);
                edges[1].setTarget(null);
            } else {
                LPort edge0Target = edges[0].getTarget();
                edges[1].setSource(edge0Target);
                edge0Target.setType(PortType.OUTPUT);
                edges[0].setTarget(null);
            }
            
            break;
        }
    }

}
