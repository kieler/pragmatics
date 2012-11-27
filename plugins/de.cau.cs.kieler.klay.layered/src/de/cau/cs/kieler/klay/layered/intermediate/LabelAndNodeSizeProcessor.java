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

import java.awt.geom.Rectangle2D;
import java.util.EnumSet;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortConstraints;
import de.cau.cs.kieler.kiml.options.SizeConstraint;
import de.cau.cs.kieler.kiml.options.SizeOptions;
import de.cau.cs.kieler.kiml.util.KimlUtil;
import de.cau.cs.kieler.klay.layered.ILayoutProcessor;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LInsets;
import de.cau.cs.kieler.klay.layered.graph.LLabel;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.graph.LLabel.LSide;
import de.cau.cs.kieler.klay.layered.properties.PortLabelPlacement;
import de.cau.cs.kieler.klay.layered.properties.Properties;

/**
 * 
 * 
 * <dl>
 *   <dt>Precondition:</dt><dd></dd>
 *   <dt>Postcondition:</dt><dd></dd>
 *   <dt>Slots:</dt><dd>Before phase 4.</dd>
 *   <dt>Same-slot dependencies:</dt><dd>{@link LabelSideSelector}</dd>
 * </dl>
 *
 * @author cds
 */
public final class LabelAndNodeSizeProcessor extends AbstractAlgorithm implements ILayoutProcessor {
    
    /** Distance between labels and ports or edges. */
    public static final int LABEL_DISTANCE = 3;

    /**
     * {@inheritDoc}
     */
    public void process(final LGraph layeredGraph) {
        getMonitor().begin("Node and Port Label Placement and Node Sizing", 1);
        double spacing = layeredGraph.getProperty(Properties.OBJ_SPACING);

        // Iterate over all the graph's nodes
        for (Layer layer : layeredGraph) {
            for (LNode node : layer) {
                
                /* PHASE 1 (SAD DUCK): PLACE PORT LABELS
                 * Port labels are placed and port margins are calculated. We currently only support
                 * one label per port.
                 */
                PortLabelPlacement labelPlacement = node.getProperty(Properties.PORT_LABEL_PLACEMENT);
                
                // Place port labels and calculate the margins
                for (LPort port : node.getPorts()) {
                    placePortLabels(port, labelPlacement);
                    calculateAndSetPortMargins(port);
                }
                
                
                /* PHASE 2 (DYNAMIC DONALD): CALCULATE INSETS
                 * We know the sides the ports will be placed at and we know where node labels are to
                 * be placed. Calculate the node's insets accordingly. Note that we don't have to
                 * know the final port positions to calculate the insets.
                 */
                calculateAndSetNodeInsets(node);
                reserveSpaceForNodeLabels(node);
                
                
                /* PHASE 3 (DANGEROUS DUCKLING): RESIZE NODE
                 * If the node has a label (we currently only support one), the node insets might have
                 * to be adjusted to reserve space for it, which is what this phase does.
                 */
                resizeNode(node, spacing);
                
                
                /* PHASE 4 (DUCK AND COVER): PLACE PORTS
                 * The node is resized, taking all node size constraints into account.
                 */
                placePorts(node, spacing);
                
                
                /* PHASE 5 (HAPPY DUCK): PLACE NODE LABEL
                 * With space reserved for the node label (we only support one), the label is placed.
                 */
                placeNodeLabels(node);
                
                // TODO: Implement node insets handling
                // Up to now, the node insets contained only those labels that were to be accounted
                // for in node resizing. If the insets are to be saved in the node later when the
                // layout is to be applied, the insets have to include all labels, or else the value
                // won't make much sense.
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
        // Get the port's label, if any
        if (!port.getLabels().isEmpty()) {
            // We use different implementations based on whether port labels are to be placed
            // inside or outside the node
            if (placement.equals(PortLabelPlacement.INSIDE)) {
                placePortLabelsInside(port, port.getLabels().get(0));
            } else if (placement.equals(PortLabelPlacement.OUTSIDE)) {
                placePortLabelsOutside(port, port.getLabels().get(0));
            }
        }
    }

    /**
     * Places the label of the given port on the inside of the port's node.
     * 
     * @param port the port whose label to place.
     * @param label the label to place.
     */
    private void placePortLabelsInside(final LPort port, final LLabel label) {
        switch (port.getSide()) {
        case WEST:
            label.getPosition().x = port.getSize().x + LABEL_DISTANCE;
            label.getPosition().y = (port.getSize().y - label.getSize().y) / 2.0;
            break;
        case EAST:
            label.getPosition().x = -label.getSize().x - LABEL_DISTANCE;
            label.getPosition().y = (port.getSize().y - label.getSize().y) / 2.0;
            break;
        case NORTH:
            label.getPosition().x = -label.getSize().x / 2;
            label.getPosition().y = port.getSize().y + LABEL_DISTANCE;
            break;
        case SOUTH:
            label.getPosition().x = -label.getSize().x / 2;
            label.getPosition().y = -label.getSize().y - LABEL_DISTANCE;
            break;
        }
    }
    
    /**
     * Places the label of the given port on the outside of the port's node.
     * 
     * @param port the port whose label to place.
     * @param label the label to place.
     */
    private void placePortLabelsOutside(final LPort port, final LLabel label) {
        if (label.getSide() == LSide.UP) {
            // Place label "above" edges
            switch (port.getSide()) {
            case WEST:
                label.getPosition().x = -label.getSize().x - LABEL_DISTANCE;
                label.getPosition().y = -label.getSize().y - LABEL_DISTANCE;
                break;
            case EAST:
                label.getPosition().x = port.getSize().x + LABEL_DISTANCE;
                label.getPosition().y = -label.getSize().y - LABEL_DISTANCE;
                break;
            case NORTH:
                label.getPosition().x = -label.getSize().x - LABEL_DISTANCE;
                label.getPosition().y = -label.getSize().y - LABEL_DISTANCE;
                break;
            case SOUTH:
                label.getPosition().x = -label.getSize().x - LABEL_DISTANCE;
                label.getPosition().y = port.getSize().y + LABEL_DISTANCE;
                break;
            }
        } else {
            // Place label "below" edges
            switch (port.getSide()) {
            case WEST:
                label.getPosition().x = -label.getSize().x - LABEL_DISTANCE;
                label.getPosition().y = port.getSize().y + LABEL_DISTANCE;
                break;
            case EAST:
                label.getPosition().x = port.getSize().x + LABEL_DISTANCE;
                label.getPosition().y = port.getSize().y + LABEL_DISTANCE;
                break;
            case NORTH:
                label.getPosition().x = port.getSize().x + LABEL_DISTANCE;
                label.getPosition().y = -label.getSize().y - LABEL_DISTANCE;
                break;
            case SOUTH:
                label.getPosition().x = port.getSize().x + LABEL_DISTANCE;
                label.getPosition().y = port.getSize().y + LABEL_DISTANCE;
                break;
            }
        }
    }

    /**
     * Calculates the port's margins such that its label is part of them and sets them accordingly.
     * 
     * <p><i>Note:</i> We currently only support one label per port.</p>
     * 
     * @param port the port whose margins to calculate.
     */
    private void calculateAndSetPortMargins(final LPort port) {
        // Get the port's label, if any
        if (!port.getLabels().isEmpty()) {
            Rectangle2D.Double portBox = new Rectangle2D.Double(
                    port.getPosition().x,
                    port.getPosition().y,
                    port.getSize().x,
                    port.getSize().y);
            
            // We only support one label, so retrieve it
            LLabel label = port.getLabels().get(0);
            Rectangle2D.Double labelBox = new Rectangle2D.Double(
                    label.getPosition().x,
                    label.getPosition().y,
                    label.getSize().x,
                    label.getSize().y);
            
            // Calculate the union of the two bounding boxes and calculate the margins
            Rectangle2D.union(portBox, labelBox, portBox);

            LInsets.Double margin = port.getMargin();
            margin.top = port.getPosition().y - portBox.y;
            margin.bottom = portBox.getMaxY() - (port.getPosition().y + port.getSize().y);
            margin.left = port.getPosition().x - portBox.x;
            margin.right = portBox.getMaxX() - (port.getPosition().x + port.getSize().x);
        }
    }

    
    ///////////////////////////////////////////////////////////////////////////////
    // INSETS CALCULATION
    
    /**
     * Calculates and sets the given node's insets accordingly such that they contain all the ports
     * and port labels. This method requires that the port margins are set to include the port
     * labels.
     * 
     * <p><i>Note:</i> We currently only support one label per port.</p>
     * 
     * @param node the node whose insets to calculate and to set.
     */
    private void calculateAndSetNodeInsets(final LNode node) {
        // Get the insets. We don't reset them here, which might or might not become a problem.
        // Currently, it is not.
        LInsets.Double nodeInsets = node.getInsets();
        
        // TODO: Should port labels always count towards the insets calculation?
        // TODO: We don't support fixed port positions yet
        
        // Iterate over the ports and look at their margins
        for (LPort port : node.getPorts()) {
            switch (port.getSide()) {
            case WEST:
                nodeInsets.left = Math.max(nodeInsets.left, port.getMargin().right);
                break;
            case EAST:
                nodeInsets.right = Math.max(nodeInsets.right, port.getMargin().left);
                break;
            case NORTH:
                nodeInsets.top = Math.max(nodeInsets.top, port.getMargin().bottom);
                break;
            case SOUTH:
                nodeInsets.bottom = Math.max(nodeInsets.bottom, port.getMargin().top);
                break;
            }
        }
    }

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
        // To implement this, we will also have to implement label position support in applyLayout
        // and add the appropriate layout options to support node label placement.
    }

    
    ///////////////////////////////////////////////////////////////////////////////
    // NODE RESIZING
    
    /**
     * Resizes the given node subject to the sizing constraints and options.
     * 
     * @param node the node to resize.
     * @param portSpacing the amount of space to leave between ports.
     */
    private void resizeNode(final LNode node, final double portSpacing) {
        KVector nodeSize = node.getSize();
        LInsets.Double nodeInsets = node.getInsets();
        EnumSet<SizeConstraint> sizeConstraint = node.getProperty(LayoutOptions.SIZE_CONSTRAINT);
        EnumSet<SizeOptions> sizeOptions = node.getProperty(LayoutOptions.SIZE_OPTIONS);
        
        // If the size constraint is empty, we can't do anything
        if (sizeConstraint.isEmpty()) {
            return;
        }
        
        // It's not empty, so we will change the node size. Initialize it to the current insets
        // (this already ensures that the node will be large enough to accommodate the node label)
        nodeSize.x = nodeInsets.left + nodeInsets.right;
        nodeSize.y = nodeInsets.top + nodeInsets.bottom;
        
        // Check how much space the ports need
        if (sizeConstraint.contains(SizeConstraint.PORTS)) {
            PortConstraints portConstraints = node.getProperty(LayoutOptions.PORT_CONSTRAINTS);
            boolean accountForLabels = sizeConstraint.contains(SizeConstraint.PORT_LABELS);
            
            // Find out how large the node will have to be to accommodate all ports. If port
            // constraints are set to FIXED_RATIO, we can't do anything actually
            KVector minSizeForPorts = null;
            switch (portConstraints) {
            case FREE:
            case FIXED_SIDE:
            case FIXED_ORDER:
                // Calculate the space necessary to accomodate all ports
                minSizeForPorts = calculatePortSpaceRequirements(node, portSpacing, accountForLabels);
                break;
            
            case FIXED_POS:
                // Find the maximum position of ports
                minSizeForPorts = calculateMaxPortPositions(node, accountForLabels);
                break;
            }
            
            // Check if we have a minimum size required for all ports
            if (minSizeForPorts != null) {
                nodeSize.x = Math.max(nodeSize.x, minSizeForPorts.x);
                nodeSize.y = Math.max(nodeSize.y, minSizeForPorts.y);
            }
        }
        
        // Respect minimum size
        if (sizeConstraint.contains(SizeConstraint.MINIMUM_SIZE)) {
            double minWidth = node.getProperty(LayoutOptions.MIN_WIDTH);
            double minHeight = node.getProperty(LayoutOptions.MIN_HEIGHT);
            
            // If we are to use default minima, check if the values are properly set
            if (sizeOptions.contains(SizeOptions.DEFAULT_MINIMUM_SIZE)) {
                if (minWidth <= 0) {
                    minWidth = KimlUtil.DEFAULT_MIN_WIDTH;
                }
                
                if (minHeight <= 0) {
                    minHeight = KimlUtil.DEFAULT_MIN_HEIGHT;
                }
            }
            
            // We might have to take the insets into account
            if (sizeOptions.contains(SizeOptions.MINIMUM_SIZE_ACCOUNTS_FOR_INSETS)) {
                if (minWidth > 0) {
                    nodeSize.x = Math.max(nodeSize.x, minWidth + nodeInsets.left + nodeInsets.right);
                }
                
                if (minHeight > 0) {
                    nodeSize.y = Math.max(nodeSize.y, minHeight + nodeInsets.top + nodeInsets.bottom);
                }
            } else {
                if (minWidth > 0) {
                    nodeSize.x = Math.max(nodeSize.x, minWidth);
                }
                
                if (minHeight > 0) {
                    nodeSize.y = Math.max(nodeSize.y, minHeight);
                }
            }
        }
    }
    
    /**
     * Calculate how much space the ports will need if they can be freely distributed on their
     * respective node side. The x coordinate of the returned vector will be the minimum width
     * required by the ports on the northern and southern side. The y coordinate, in turn, will
     * be the minimum height required by the ports on the western and eastern side. This means
     * that 
     * 
     * @param node the node to calculate the minimum size for.
     * @param portSpacing the amount of space to leave between ports.
     * @param accountForLabels if {@code true}, the port labels will be taken into account
     *                         when calculating the space requirements.
     * @return minimum size.
     */
    private KVector calculatePortSpaceRequirements(final LNode node, final double portSpacing,
            final boolean accountForLabels) {
        
        // Width and height required by the ports on the different sides
        double westPortsHeight = portSpacing;
        double eastPortsHeight = portSpacing;
        double northPortsWidth = portSpacing;
        double southPortsWidth = portSpacing;
        
        // Iterate over the ports
        for (LPort port : node.getPorts()) {
            switch (port.getSide()) {
            case WEST:
                westPortsHeight += portSpacing
                    + port.getSize().y
                    + (accountForLabels ? port.getMargin().bottom + port.getMargin().top : 0.0);
                break;
            case EAST:
                eastPortsHeight += portSpacing
                    + port.getSize().y
                    + (accountForLabels ? port.getMargin().bottom + port.getMargin().top : 0.0);
                break;
            case NORTH:
                northPortsWidth += portSpacing
                    + port.getSize().x
                    + (accountForLabels ? port.getMargin().left + port.getMargin().right : 0.0);
                break;
            case SOUTH:
                southPortsWidth += portSpacing
                    + port.getSize().x
                    + (accountForLabels ? port.getMargin().left + port.getMargin().right : 0.0);
                break;
            }
        }
        
        // Reset unused sides to a width / height of 0
        westPortsHeight = (westPortsHeight == portSpacing ? 0.0 : westPortsHeight);
        eastPortsHeight = (eastPortsHeight == portSpacing ? 0.0 : eastPortsHeight);
        northPortsWidth = (northPortsWidth == portSpacing ? 0.0 : northPortsWidth);
        southPortsWidth = (southPortsWidth == portSpacing ? 0.0 : southPortsWidth);
        
        return new KVector(
                Math.max(northPortsWidth, southPortsWidth),
                Math.max(westPortsHeight, eastPortsHeight));
    }
    
    /**
     * For fixed node positions, returns the minimum size of the node to contain all ports.
     * 
     * @param node the node to calculate the minimum size for.
     * @param accountForLabels
     * @return
     */
    private KVector calculateMaxPortPositions(final LNode node, final boolean accountForLabels) {
        KVector result = new KVector();
        
        // Iterate over the ports
        for (LPort port : node.getPorts()) {
            switch (port.getSide()) {
            case WEST:
            case EAST:
                result.y = Math.max(result.y,
                        port.getPosition().y
                        + port.getSize().y
                        + (accountForLabels ? port.getMargin().bottom : 0.0));
                break;
                
            case NORTH:
            case SOUTH:
                result.x = Math.max(result.x,
                        port.getPosition().x
                        + port.getSize().x
                        + (accountForLabels ? port.getMargin().right : 0.0));
                break;
            }
        }
        
        return result;
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
        // To implement this, we will also have to implement label position support in applyLayout
        // and add the appropriate layout options to support node label placement.
    }
    
}
