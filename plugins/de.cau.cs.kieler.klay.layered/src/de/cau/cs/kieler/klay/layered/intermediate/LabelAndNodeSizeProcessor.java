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
package de.cau.cs.kieler.klay.layered.intermediate;


import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.klay.layered.ILayoutProcessor;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.properties.PortLabelPlacement;
import de.cau.cs.kieler.klay.layered.properties.Properties;

/**
 * 
 * 
 * <dl>
 *   <dt>Precondition:</dt><dd></dd>
 *   <dt>Postcondition:</dt><dd></dd>
 *   <dt>Slots:</dt><dd>Before phase 4.</dd>
 *   <dt>Same-slot dependencies:</dt><dd></dd>
 * </dl>
 *
 * @author cds
 */
public final class LabelAndNodeSizeProcessor extends AbstractAlgorithm implements ILayoutProcessor {

    /**
     * {@inheritDoc}
     */
    public void process(final LGraph layeredGraph) {
        getMonitor().begin("Node and Port Label Placement and Node Sizing", 1);
        double spacing = layeredGraph.getProperty(Properties.OBJ_SPACING);

        // Iterate over all the graph's nodes
        for (Layer layer : layeredGraph) {
            for (LNode node : layer) {
                
                /* PHASE 1: PLACE PORT LABELS
                 * Port labels are placed and port margins are calculated. We currently only support
                 * one label per port.
                 */
                PortLabelPlacement labelPlacement = node.getProperty(Properties.PORT_LABEL_PLACEMENT);
                
                // Place port labels and calculate the margins
                for (LPort port : node.getPorts()) {
                    placePortLabels(port, labelPlacement);
                    calculateAndSetPortMargins(port);
                }
                
                
                /* PHASE 2: PLACE PORTS
                 * Ports are placed and node insets are calculated accordingly.
                 */
                placePorts(node, spacing);
                calculateAndSetNodeInsets(node);
                
                
                /* PHASE 3: RESERVE SPACE FOR NODE LABEL
                 * If the node has a label (we currently only support one), the node insets might have
                 * to be adjusted to reserve space for it, which is what this phase does.
                 */
                reserveSpaceForNodeLabels(node);
                
                
                /* PHASE 4: RESIZE NODE
                 * The node is resized, taking all node size constraints into account.
                 */
                resizeNode(node);
                
                
                /* PHASE 5: PLACE NODE LABEL
                 * With space reserved for the node label (we only support one), the label is placed.
                 */
                placeNodeLabels(node);
            }
        }
        
        getMonitor().done();
    }
    
    
    ///////////////////////////////////////////////////////////////////////////////
    // PORT LABEL PLACEMENT

    /**
     * Places the label of the given port, if any. We assume that the label is actually part of the
     * given port.
     * 
     * <p><i>Note:</i> We currently only support one label per port.</p>
     * 
     * @param port the port whose labels to place.
     * @param placement the port label placement that applies to the port.
     */
    private void placePortLabels(final LPort port, final PortLabelPlacement placement) {
        // TODO: Implement.
    }
    
    /**
     * Calculates the port's margins such that its label is part of them and sets them accordingly.
     * 
     * <p><i>Note:</i> We currently only support one label per port.</p>
     * 
     * @param port the port whose margins to calculate.
     */
    private void calculateAndSetPortMargins(final LPort port) {
        // TODO: Implement.
    }

    
    ///////////////////////////////////////////////////////////////////////////////
    // PORT PLACEMENT

    /**
     * Places the given node's ports.
     * 
     * @param node the node whose ports to place.
     * @param spacing the object spacing set for the diagram.
     */
    private void placePorts(final LNode node, final double spacing) {
        // TODO: Implement.
    }
    
    /**
     * Calculates and sets the given node's insets accordingly such that they contain all the ports
     * and port labels.
     * 
     * <p><i>Note:</i> We currently only support one label per port.</p>
     * 
     * @param node the node whose insets to calculate and to set.
     */
    private void calculateAndSetNodeInsets(final LNode node) {
        // TODO: Implement.
    }

    
    ///////////////////////////////////////////////////////////////////////////////
    // RESERVING SPACE FOR NODE LABELS

    /**
     * Adjusts the node's insets to leave enough space for the node's label. This method only modifies
     * the insets if the node's label is placed inside the node. A label placed on its outside doesn't
     * modify the insets much...
     * 
     * <p><i>Note:</i> We currently only support one label per node.</p>
     * 
     * @param node the node in question.
     */
    private void reserveSpaceForNodeLabels(final LNode node) {
        // TODO: Implement.
    }

    
    ///////////////////////////////////////////////////////////////////////////////
    // NODE RESIZING
    
    /**
     * Resizes the given node subject to the sizing constraints and options.
     * 
     * @param node the node to resize.
     */
    private void resizeNode(final LNode node) {
        // TODO: Implement.
    }

    
    ///////////////////////////////////////////////////////////////////////////////
    // PLACING NODE LABELS
    
    /**
     * Calculates the position of the node's label.
     * 
     * <p><i>Note:</i> We currently only support one label per node.</p>
     * 
     * @param node the node whose label to place.
     */
    private void placeNodeLabels(final LNode node) {
        // TODO: Implement.
    }
    
}
