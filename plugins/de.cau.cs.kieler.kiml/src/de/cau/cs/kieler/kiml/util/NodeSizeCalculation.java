/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2014 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.util;

import java.awt.geom.Rectangle2D;
import java.util.EnumSet;
import java.util.List;

import com.google.common.collect.Lists;

import de.cau.cs.kieler.core.alg.BasicProgressMonitor;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.kiml.klayoutdata.KInsets;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.NodeLabelPlacement;
import de.cau.cs.kieler.kiml.options.PortConstraints;
import de.cau.cs.kieler.kiml.options.PortLabelPlacement;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.kiml.options.SizeConstraint;
import de.cau.cs.kieler.kiml.options.SizeOptions;

/**
 * 
 * TODO check all write actions ... !!!
 * 
 * @author uru
 * 
 */
public class NodeSizeCalculation {

    /*
     * The following variables provide context information for the different phases of the
     * algorithm. The information are usually computed by some phase to be made available to later
     * phases. This would probably be better kept in some kind of a context object, but is kept here
     * for performance reasons to avoid having to create new context objects all the time.
     */

    /**
     * Node insets required by port labels inside the node. This is always set, but not always taken
     * into account to calculate the node size.
     * 
     * <p>
     * <i>Note:</i> This is only valid for the currently processed node!
     * </p>
     */
    private Rectangle2D.Double requiredPortLabelSpace = new Rectangle2D.Double();

    // TODO exchange rectangle ... atm left=x top=y right=width bottom=height
    
    /**
     * Node insets required by node labels placed inside the node. This is always set, but not
     * always taken into account to calculate the node size.
     * 
     * <p>
     * <i>Note:</i> This is only valid for the currently processed node!
     * </p>
     */
    private Rectangle2D.Double requiredNodeLabelSpace = new Rectangle2D.Double();

    /**
     * Space required by the node labels if stacked vertically.
     * 
     * <p>
     * <i>Note:</i> This is only valid for the currently processed node!
     * </p>
     */
    private KVector nodeLabelsBoundingBox = new KVector();

    /**
     * Number of ports on the western side. Only used if port constraints are not
     * {@link PortConstraints#FIXED_RATIO} or {@link PortConstraints#FIXED_POS}.
     */
    private int westPortsCount = 0;

    /**
     * Height of the ports on the western side. If port labels are accounted for, the height
     * includes the relevant port margins too. Only used if port constraints are not
     * {@link PortConstraints#FIXED_RATIO} or {@link PortConstraints#FIXED_POS}.
     */
    private double westPortsHeight = 0.0;

    /**
     * Number of ports on the eastern side.Only used if port constraints are not
     * {@link PortConstraints#FIXED_RATIO} or {@link PortConstraints#FIXED_POS}.
     */
    private int eastPortsCount = 0;

    /**
     * Height of the ports on the eastern side. If port labels are accounted for, the height
     * includes the relevant port margins too. Only used if port constraints are not
     * {@link PortConstraints#FIXED_RATIO} or {@link PortConstraints#FIXED_POS}.
     */
    private double eastPortsHeight = 0.0;

    /**
     * Number of ports on the northern side.Only used if port constraints are not
     * {@link PortConstraints#FIXED_RATIO} or {@link PortConstraints#FIXED_POS}.
     */
    private int northPortsCount = 0;

    /**
     * Width of the ports on the northern side. If port labels are accounted for, the height
     * includes the relevant port margins too. Only used if port constraints are not
     * {@link PortConstraints#FIXED_RATIO} or {@link PortConstraints#FIXED_POS}.
     */
    private double northPortsWidth = 0.0;

    /**
     * Number of ports on the southern side.Only used if port constraints are not
     * {@link PortConstraints#FIXED_RATIO} or {@link PortConstraints#FIXED_POS}.
     */
    private int southPortsCount = 0;

    /**
     * Width of the ports on the southern side. If port labels are accounted for, the height
     * includes the relevant port margins too. Only used if port constraints are not
     * {@link PortConstraints#FIXED_RATIO} or {@link PortConstraints#FIXED_POS}.
     */
    private double southPortsWidth = 0.0;

    /**
     * Resets the fields providing context information to the algorithm.
     */
    private void resetContext() {
        requiredPortLabelSpace.setRect(0.0, 0.0, 0.0, 0.0);
        requiredNodeLabelSpace.setRect(0.0, 0.0, 0.0, 0.0);
        
        westPortsCount = 0;
        westPortsHeight = 0.0;
        eastPortsCount = 0;
        eastPortsHeight = 0.0;
        northPortsCount = 0;
        northPortsWidth = 0.0;
        southPortsCount = 0;
        southPortsWidth = 0.0;
    }
       
    
    /**
     * TODO this is an example ...
     * 
     * @param graph
     * @param fac
     */
    public <T> void calculateNodeSizes(T graph, SizeCalculationAdapterFactory<T> fac) {
        GraphAdapter<?> graphAdapter = fac.getGraphAdapter(graph);
        
        process(graphAdapter, new BasicProgressMonitor());
    }
    
    public <T> void calculateNodeSizes(final KNode root) {
        GraphAdapter<?> graphAdapter = new KGraphSizeCalculationAdapterFactory().getGraphAdapter(root);
        
        process(graphAdapter, new BasicProgressMonitor());
    }
    
    /*
     * Entry point
     */
    /**
     * {@inheritDoc}
     */
    public void process(final GraphAdapter<?> layeredGraph, final IKielerProgressMonitor monitor) {
        monitor.begin("Node and Port Label Placement and Node Sizing", 1);
        
        // FIXME 
        //double objectSpacing = layeredGraph.getProperty(Properties.OBJ_SPACING);
        double objectSpacing = 20;
        double labelSpacing = layeredGraph.getProperty(LayoutOptions.LABEL_SPACING);

        // Iterate over all the graph's nodes
            for (NodeAdapter<?> node : layeredGraph.getNodes()) {
                /* Note that, upon Miro's request, each phase of the algorithm was given a code name. */
                
                /* PREPARATIONS
                 * Reset stuff, fill the port information fields, and remember the node's old size.
                 */
                resetContext();
                calculatePortInformation(node, node.getProperty(LayoutOptions.SIZE_CONSTRAINT).contains(
                        SizeConstraint.PORT_LABELS));
                KVector originalNodeSize = new KVector(node.getSize());
                
                
                /* PHASE 1 (SAD DUCK): PLACE PORT LABELS
                 * Port labels are placed and port margins are calculated. We currently only support
                 * one label per port.
                 */
                PortLabelPlacement labelPlacement = node.getProperty(LayoutOptions.PORT_LABEL_PLACEMENT);
                
                // FIXME 
                //boolean compoundNodeMode = node.getProperty(Properties.COMPOUND_NODE);
                boolean compoundNodeMode = false;
                
                // Place port labels and calculate the margins
                for (PortAdapter<?> port : node.getPorts()) {
                    placePortLabels(port, labelPlacement, compoundNodeMode, labelSpacing);
                    calculateAndSetPortMargins(port);
                }
                
                
                /* PHASE 2 (DYNAMIC DONALD): CALCULATE INSETS
                 * We know the sides the ports will be placed at and we know where node labels are to
                 * be placed. Calculate the node's insets accordingly. Also compute the amount of space
                 * the node labels will need if stacked vertically. Note that we don't have to know the
                 * final port or label positions to calculate all this stuff.
                 */
                calculateRequiredPortLabelSpace(node);
                calculateRequiredNodeLabelSpace(node, labelSpacing);
                
                
                /* PHASE 3 (DANGEROUS DUCKLING): RESIZE NODE
                 * If the node has labels, the node insets might have to be adjusted to reserve space
                 * for them, which is what this phase does.
                 */
                resizeNode(node, objectSpacing, labelSpacing);
                
                
                /* PHASE 4 (DUCK AND COVER): PLACE PORTS
                 * The node is resized, taking all node size constraints into account. The port spacing
                 * is not required for port placement since the placement will be based on the node's
                 * size (if it is not fixed anyway).
                 */
                placePorts(node, originalNodeSize);
                
                
                /* PHASE 5 (HAPPY DUCK): PLACE NODE LABELS
                 * With space reserved for the node labels, the labels are placed.
                 */
                placeNodeLabels(node, labelSpacing);
                
                
                /* CLEANUP (THANKSGIVING): SET NODE INSETS
                 * Set the node insets to include space required for port and node labels. If the labels
                 * were not taken into account when calculating the node's size, this may result in
                 * insets that, taken together, are larger than the node's actual size.
                 */
                // LInsets nodeInsets = node.getInsets();
                Rectangle2D.Double nodeInsets = new Rectangle2D.Double();
                nodeInsets.x = requiredNodeLabelSpace.x + requiredPortLabelSpace.x;
                nodeInsets.width = requiredNodeLabelSpace.width + requiredPortLabelSpace.width;
                nodeInsets.y = requiredNodeLabelSpace.y + requiredPortLabelSpace.y;
                nodeInsets.height = requiredNodeLabelSpace.height + requiredPortLabelSpace.height;
                node.setInsets(nodeInsets);
            }
        }
        
    
    /*
     * Implementation
     */
    /**
     * Calculates the width of ports on the northern and southern sides, and the height of ports on the
     * western and eastern sides of the given node. The information are stored in the class fields and
     * are used later on when calculating the minimum node size and when placing ports.
     * 
     * @param node the node to calculate the port information for.
     * @param accountForLabels if {@code true}, the port labels will be taken into account
     *                         when calculating the port information.
     */
    private void calculatePortInformation(final NodeAdapter<?> node, final boolean accountForLabels) {
        // Iterate over the ports
        for (PortAdapter<?> port : node.getPorts()) {
            switch (port.getSide()) {
            case WEST:
                westPortsCount++;
                westPortsHeight += port.getSize().y
                    + (accountForLabels ? port.getMargin().height + port.getMargin().y : 0.0);
                break;
            case EAST:
                eastPortsCount++;
                eastPortsHeight += port.getSize().y
                    + (accountForLabels ? port.getMargin().height + port.getMargin().y : 0.0);
                break;
            case NORTH:
                northPortsCount++;
                northPortsWidth += port.getSize().x
                    + (accountForLabels ? port.getMargin().x + port.getMargin().width : 0.0);
                break;
            case SOUTH:
                southPortsCount++;
                southPortsWidth += port.getSize().x
                    + (accountForLabels ? port.getMargin().x + port.getMargin().width : 0.0);
                break;
            }
        }
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
     * @param compoundNodeMode {@code true} if the node contains further nodes in the original graph.
     *                         This influences the inner port label placement.
     * @param labelSpacing spacing between labels and other objects.
     */
    private void placePortLabels(final PortAdapter<?> port, final PortLabelPlacement placement,
            final boolean compoundNodeMode, final double labelSpacing) {
        
        // Get the port's label, if any
        if (!port.getLabels().isEmpty()) {
            // We use different implementations based on whether port labels are to be placed
            // inside or outside the node
            if (placement.equals(PortLabelPlacement.INSIDE)) {
                placePortLabelsInside(port, port.getLabels().get(0), compoundNodeMode, labelSpacing);
            } else if (placement.equals(PortLabelPlacement.OUTSIDE)) {
                placePortLabelsOutside(port, port.getLabels().get(0), labelSpacing);
            }
        }
    }

    /**
     * Places the label of the given port on the inside of the port's node.
     * 
     * @param port the port whose label to place.
     * @param label the label to place.
     * @param compoundNodeMode {@code true} if the node contains further nodes in the original graph. In
     *                         this case, port labels are not placed next to ports, but a little down as
     *                         well to avoid edge-label-crossings.
     * @param labelSpacing spacing between labels and other objects.
     */
    private void placePortLabelsInside(final PortAdapter<?> port, final LabelAdapter<?> label,
            final boolean compoundNodeMode, final double labelSpacing) {
        
        KVector position = new KVector(port.getPosition());
        switch (port.getSide()) {
        case WEST:
            position.x = port.getSize().x + labelSpacing;
            position.y = compoundNodeMode
                    ? port.getSize().y + labelSpacing
                    : (port.getSize().y - label.getSize().y) / 2.0;
            break;
        case EAST:
            position.x = -label.getSize().x - labelSpacing;
            position.y = compoundNodeMode
                    ? port.getSize().y + labelSpacing
                    : (port.getSize().y - label.getSize().y) / 2.0;
            break;
        case NORTH:
            position.x = -label.getSize().x / 2;
            position.y = port.getSize().y + labelSpacing;
            break;
        case SOUTH:
            position.x = -label.getSize().x / 2;
            position.y = -label.getSize().y - labelSpacing;
            break;
        }
        label.setPosition(position);
    }
    
    /**
     * Places the label of the given port on the outside of the port's node.
     * 
     * @param port the port whose label to place.
     * @param label the label to place.
     * @param labelSpacing spacing between labels and other objects.
     */
    private void placePortLabelsOutside(final PortAdapter<?> port, final LabelAdapter<?> label,
            final double labelSpacing) {
        
        // TODO move label side to kiml ... 
        KVector position = new KVector(label.getPosition());
//        if (label.getSide() == LabelSide.ABOVE) {
            // Place label "above" edges
            switch (port.getSide()) {
            case WEST:
                position.x = -label.getSize().x - labelSpacing;
                position.y = -label.getSize().y - labelSpacing;
                break;
            case EAST:
                position.x = port.getSize().x + labelSpacing;
                position.y = -label.getSize().y - labelSpacing;
                break;
            case NORTH:
                position.x = -label.getSize().x - labelSpacing;
                position.y = -label.getSize().y - labelSpacing;
                break;
            case SOUTH:
                position.x = -label.getSize().x - labelSpacing;
                position.y = port.getSize().y + labelSpacing;
                break;
            }
//        } else {
//            // Place label "below" edges
//            switch (port.getSide()) {
//            case WEST:
//                label.getPosition().x = -label.getSize().x - labelSpacing;
//                label.getPosition().y = port.getSize().y + labelSpacing;
//                break;
//            case EAST:
//                label.getPosition().x = port.getSize().x + labelSpacing;
//                label.getPosition().y = port.getSize().y + labelSpacing;
//                break;
//            case NORTH:
//                label.getPosition().x = port.getSize().x + labelSpacing;
//                label.getPosition().y = -label.getSize().y - labelSpacing;
//                break;
//            case SOUTH:
//                label.getPosition().x = port.getSize().x + labelSpacing;
//                label.getPosition().y = port.getSize().y + labelSpacing;
//                break;
//            }
//        }
            label.setPosition(position);
    }

    /**
     * Calculates the port's margins such that its label is part of them and sets them accordingly.
     * 
     * <p><i>Note:</i> We currently only support one label per port.</p>
     * 
     * @param port the port whose margins to calculate.
     */
    private void calculateAndSetPortMargins(final PortAdapter<?> port) {
        // Get the port's label, if any
        if (!port.getLabels().isEmpty()) {
            Rectangle2D.Double portBox = new Rectangle2D.Double(
                    0.0,
                    0.0,
                    port.getSize().x,
                    port.getSize().y);
            
            // We only support one label, so retrieve it
            LabelAdapter<?> label = port.getLabels().get(0);
            Rectangle2D.Double labelBox = new Rectangle2D.Double(
                    label.getPosition().x,
                    label.getPosition().y,
                    label.getSize().x,
                    label.getSize().y);
            
            // Calculate the union of the two bounding boxes and calculate the margins
            Rectangle2D.union(portBox, labelBox, portBox);

            // Rectangle2D.Double margin = port.getMargin();
            Rectangle2D.Double margin = new Rectangle2D.Double();
            margin.y = -portBox.y;
            margin.height = portBox.getMaxY() - port.getSize().y;
            margin.x = -portBox.x;
            margin.width = portBox.getMaxX() - port.getSize().x;
            port.setMargin(margin);
        }
    }

    
    ///////////////////////////////////////////////////////////////////////////////
    // INSETS CALCULATION
    
    /**
     * Calculates the space required to accommodate all port labels and sets
     * {@link #requiredPortLabelSpace}. Also counts the number of ports on
     * each side of the node.
     * 
     * <p><i>Note:</i> We currently only support one label per port.</p>
     * 
     * @param node the node whose insets to calculate and to set.
     */
    private void calculateRequiredPortLabelSpace(final NodeAdapter<?> node) {
        // Iterate over the ports and look at their margins
        for (PortAdapter<?> port : node.getPorts()) {
            switch (port.getSide()) {
            case WEST:
                requiredPortLabelSpace.x =
                    Math.max(requiredPortLabelSpace.x, port.getMargin().width);
                break;
            case EAST:
                requiredPortLabelSpace.width =
                    Math.max(requiredPortLabelSpace.width, port.getMargin().x);
                break;
            case NORTH:
                requiredPortLabelSpace.y =
                    Math.max(requiredPortLabelSpace.y, port.getMargin().height);
                break;
            case SOUTH:
                requiredPortLabelSpace.height =
                    Math.max(requiredPortLabelSpace.height, port.getMargin().y);
                break;
            }
        }
    }

    /**
     * Calculates the space required to accommodate the node labels (if any) and sets
     * {@link #requiredNodeLabelSpace} as well as {@link #nodeLabelsBoundingBox}. If the labels are
     * placed at the top or at the bottom, the top or bottom insets are set. If it is centered
     * vertically, the left or right insets are set if the labels are horizontally aligned leftwards
     * or rightwards. If they are centered in both directions, no insets are set. If they are placed
     * outside the node, no insets are set.
     * 
     * @param node
     *            the node in question.
     * @param labelSpacing
     *            spacing between labels and other objects.
     */
    private void calculateRequiredNodeLabelSpace(final NodeAdapter<?> node,
            final double labelSpacing) {
        // Check if there are any labels
        if (node.getLabels().isEmpty()) {
            return;
        }

        // Compute a bounding box for all labels stacked vertically
        nodeLabelsBoundingBox.x = 0;
        nodeLabelsBoundingBox.y = 0;

        for (LabelAdapter<?> label : node.getLabels()) {
            nodeLabelsBoundingBox.x = Math.max(nodeLabelsBoundingBox.x, label.getSize().x);
            nodeLabelsBoundingBox.y += label.getSize().y + labelSpacing;
        }
        nodeLabelsBoundingBox.y -= labelSpacing;

        // Retrieve label placement policy
        EnumSet<NodeLabelPlacement> nodeLabelPlacement =
                node.getProperty(LayoutOptions.NODE_LABEL_PLACEMENT);

        // This method only sets insets if the node label is to be placed on the inside
        if (nodeLabelPlacement.contains(NodeLabelPlacement.INSIDE)) {
            // The primary distinction criterion is the vertical placement
            if (nodeLabelPlacement.contains(NodeLabelPlacement.V_TOP)) {
                requiredNodeLabelSpace.y = nodeLabelsBoundingBox.y + labelSpacing;
            } else if (nodeLabelPlacement.contains(NodeLabelPlacement.V_BOTTOM)) {
                requiredNodeLabelSpace.height = nodeLabelsBoundingBox.y + labelSpacing;
            } else if (nodeLabelPlacement.contains(NodeLabelPlacement.V_CENTER)) {
                // Check whether the label will be placed left or right
                if (nodeLabelPlacement.contains(NodeLabelPlacement.H_LEFT)) {
                    requiredNodeLabelSpace.x = nodeLabelsBoundingBox.x + labelSpacing;
                } else if (nodeLabelPlacement.contains(NodeLabelPlacement.H_RIGHT)) {
                    requiredNodeLabelSpace.width = nodeLabelsBoundingBox.x + labelSpacing;
                }
            }
        }
    }

    
    ///////////////////////////////////////////////////////////////////////////////
    // NODE RESIZING
    
    /**
     * Resizes the given node subject to the sizing constraints and options.
     * 
     * @param node the node to resize.
     * @param portSpacing the amount of space to leave between ports.
     * @param labelSpacing the amount of space to leave between labels and other objects.
     */
    private void resizeNode(final NodeAdapter<?> node, final double portSpacing, 
            final double labelSpacing) {
        KVector nodeSize = node.getSize();
        KVector originalNodeSize = new KVector(nodeSize);
        EnumSet<SizeConstraint> sizeConstraint = node.getProperty(LayoutOptions.SIZE_CONSTRAINT);
        EnumSet<SizeOptions> sizeOptions = node.getProperty(LayoutOptions.SIZE_OPTIONS);
        PortConstraints portConstraints = node.getProperty(LayoutOptions.PORT_CONSTRAINTS);
        boolean accountForLabels = sizeConstraint.contains(SizeConstraint.PORT_LABELS);
        
        // If the size constraint is empty, we can't do anything
        if (sizeConstraint.isEmpty()) {
            return;
        }
        
        // It's not empty, so we will change the node size; we start by resetting the size to zero
        nodeSize.x = 0.0;
        nodeSize.y = 0.0;
        
        // Find out how large the node will have to be to accommodate all ports. If port
        // constraints are set to FIXED_RATIO, we can't do anything smart, really; in this
        // case we will just assume the original node size to be the minumum for ports
        KVector minSizeForPorts = null;
        switch (portConstraints) {
        case FREE:
        case FIXED_SIDE:
        case FIXED_ORDER:
            // Calculate the space necessary to accomodate all ports
            minSizeForPorts = calculatePortSpaceRequirements(node, portSpacing, accountForLabels);
            break;
        
        case FIXED_RATIO:
            // Keep original node size
            minSizeForPorts = new KVector(originalNodeSize);
            break;
        
        case FIXED_POS:
            // Find the maximum position of ports
            minSizeForPorts = calculateMaxPortPositions(node, accountForLabels);
            break;
        }
        
        // If the node size should take port space requirements into account, adjust it accordingly
        if (sizeConstraint.contains(SizeConstraint.PORTS)) {
            // Check if we have a minimum size required for all ports
            if (minSizeForPorts != null) {
                nodeSize.x = Math.max(nodeSize.x, minSizeForPorts.x);
                nodeSize.y = Math.max(nodeSize.y, minSizeForPorts.y);
            }
            
            // If we account for labels, we need to have the size account for labels placed on the
            // inside of the node
            if (accountForLabels) {
                nodeSize.x = Math.max(nodeSize.x,
                        requiredPortLabelSpace.x + requiredPortLabelSpace.width + portSpacing);
                nodeSize.y = Math.max(nodeSize.y,
                        requiredPortLabelSpace.y + requiredPortLabelSpace.height + portSpacing);
            }
        }
        
        // If the node label is to be accounted for, add its required space to the node size
        if (sizeConstraint.contains(SizeConstraint.NODE_LABELS) && !node.getLabels().isEmpty()) {
            EnumSet<NodeLabelPlacement> nodeLabelPlacement =
                    node.getProperty(LayoutOptions.NODE_LABEL_PLACEMENT);
            
            // Check if the label is to be placed inside or outside the node
            if (nodeLabelPlacement.contains(NodeLabelPlacement.INSIDE)) {
                nodeSize.x += requiredNodeLabelSpace.x + requiredNodeLabelSpace.width;
                nodeSize.y += requiredNodeLabelSpace.y + requiredNodeLabelSpace.height;

                // For center placement, the insets don't cover everything
                if (nodeLabelPlacement.contains(NodeLabelPlacement.V_CENTER)) {
                    nodeSize.y += nodeLabelsBoundingBox.y + 2 * labelSpacing;
                }

                if (nodeLabelPlacement.contains(NodeLabelPlacement.H_CENTER)) {
                    nodeSize.x += nodeLabelsBoundingBox.x + 2 * labelSpacing;
                }
            } else if (nodeLabelPlacement.contains(NodeLabelPlacement.OUTSIDE)) {
                // The node must be at least as high or wide as the label
                if (nodeLabelPlacement.contains(NodeLabelPlacement.V_TOP)
                        || nodeLabelPlacement.contains(NodeLabelPlacement.V_BOTTOM)) {
                    
                    nodeSize.x = Math.max(nodeSize.x, nodeLabelsBoundingBox.x);
                } else if (nodeLabelPlacement.contains(NodeLabelPlacement.V_CENTER)) {
                    nodeSize.y = Math.max(nodeSize.y, nodeLabelsBoundingBox.y);
                }
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
                    nodeSize.x = Math.max(nodeSize.x,
                            minWidth
                            + requiredPortLabelSpace.x
                            + requiredPortLabelSpace.width);
                }
                
                if (minHeight > 0) {
                    nodeSize.y = Math.max(nodeSize.y,
                            minHeight
                            + requiredPortLabelSpace.y
                            + requiredPortLabelSpace.height);
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
        // TODO new
        // apply the calculated node size back to the wrapped node
        node.setSize(nodeSize);
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
    private KVector calculatePortSpaceRequirements(final NodeAdapter<?> node, final double portSpacing,
            final boolean accountForLabels) {
        
        // Calculate the maximum width and height, taking the necessary spacing between (and around)
        // the ports into consideration as well
        double maxWidth = Math.max(
                northPortsCount > 0 ? (northPortsCount + 1) * portSpacing + northPortsWidth : 0.0,
                southPortsCount > 0 ? (southPortsCount + 1) * portSpacing + southPortsWidth : 0.0);
        double maxHeight = Math.max(
                westPortsCount > 0 ? (westPortsCount + 1) * portSpacing + westPortsHeight : 0.0,
                eastPortsCount > 0 ? (eastPortsCount + 1) * portSpacing + eastPortsHeight : 0.0);
        
        return new KVector(maxWidth, maxHeight);
    }
    
    /**
     * For fixed node positions, returns the minimum size of the node to contain all ports.
     * 
     * @param node the node to calculate the minimum size for.
     * @param accountForLabels
     * @return
     */
    private KVector calculateMaxPortPositions(final NodeAdapter<?> node, 
            final boolean accountForLabels) {
        KVector result = new KVector();
        
        // Iterate over the ports
        for (PortAdapter<?> port : node.getPorts()) {
            switch (port.getSide()) {
            case WEST:
            case EAST:
                result.y = Math.max(result.y,
                        port.getPosition().y
                        + port.getSize().y
                        + (accountForLabels ? port.getMargin().height : 0.0));
                break;
                
            case NORTH:
            case SOUTH:
                result.x = Math.max(result.x,
                        port.getPosition().x
                        + port.getSize().x
                        + (accountForLabels ? port.getMargin().width : 0.0));
                break;
            }
        }
        
        return result;
    }

    
    ///////////////////////////////////////////////////////////////////////////////
    // PORT PLACEMENT

    /**
     * Places the given node's ports. If the node wasn't resized at all and port constraints are set
     * to either {@link PortConstraints#FIXED_RATIO} or {@link PortConstraints#FIXED_POS}, the port
     * positions are not touched.
     * 
     * @param node the node whose ports to place.
     * @param originalNodeSize the node's size before it was (possibly) resized.
     */
    private void placePorts(final NodeAdapter<?> node, final KVector originalNodeSize) {
        PortConstraints portConstraints = node.getProperty(LayoutOptions.PORT_CONSTRAINTS);
        
        if (portConstraints == PortConstraints.FIXED_POS) {
            // Fixed Position
            placeFixedPosNodePorts(node);
        } else if (portConstraints == PortConstraints.FIXED_RATIO) {
            // Fixed Ratio
            placeFixedRatioNodePorts(node);
        } else {
            // Free, Fixed Side, Fixed Order
            if (node.getProperty(LayoutOptions.HYPERNODE)
                    || (node.getSize().x == 0 && node.getSize().y == 0)) {
                
                placeHypernodePorts(node);
            } else {
                placeNodePorts(node);
            }
        }
    }
    
    /**
     * Places the ports of a node assuming that the port constraints are set to fixed port positions.
     * Ports still need to be placed, though, because the node may have been resized.
     * 
     * @param node the node whose ports to place.
     */
    private void placeFixedPosNodePorts(final NodeAdapter<?> node) {
        KVector nodeSize = node.getSize();

        for (PortAdapter<?> port : node.getPorts()) {
            //float portOffset = port.getProperty(Properties.OFFSET);
            float portOffset = 0;
            
            System.out.println("Port pos: " + port.getPosition());
            KVector position = new KVector(port.getPosition());
            switch (port.getSide()) {
            case WEST:
                position.x = -port.getSize().x - portOffset;
                break;
            case EAST:
                position.x = nodeSize.x + portOffset;
                break;
            case NORTH:
                position.y = -port.getSize().y - portOffset;
                break;
            case SOUTH:
                position.y = nodeSize.y + portOffset;
                break;
            }
            System.out.println("Port pos2: " + position);
            port.setPosition(position);
        }
    }

    /**
     * Places the ports of a node keeping the ratio between their position and the length of their
     * respective side intact.
     * 
     * @param node the node whose ports to place.
     */
    private void placeFixedRatioNodePorts(final NodeAdapter<?> node) {
        KVector nodeSize = node.getSize();
        
        // Adjust port positions depending on port side. Eastern ports have to have their x coordinate
        // set to the node's current width; the same goes for the y coordinate of southern ports
        for (PortAdapter<?> port : node.getPorts()) {
            // FIXME as above
//            float portOffset = port.getProperty(Properties.OFFSET);
            float portOffset = 0;
            
            // FIXME property not available
//            switch (port.getSide()) {
//            case WEST:
//                port.getPosition().y = nodeSize.y * port.getProperty(Properties.PORT_RATIO_OR_POSITION);
//                port.getPosition().x = -port.getSize().x - portOffset;
//                break;
//            case EAST:
//                port.getPosition().y = nodeSize.y * port.getProperty(Properties.PORT_RATIO_OR_POSITION);
//                port.getPosition().x = nodeSize.x + portOffset;
//                break;
//            case NORTH:
//                port.getPosition().x = nodeSize.x * port.getProperty(Properties.PORT_RATIO_OR_POSITION);
//                port.getPosition().y = -port.getSize().y - portOffset;
//                break;
//            case SOUTH:
//                port.getPosition().x = nodeSize.x * port.getProperty(Properties.PORT_RATIO_OR_POSITION);
//                port.getPosition().y = nodeSize.y + portOffset;
//                break;
//            }
        }
    }
    
    /**
     * Places the ports of a node, assuming that the ports are not fixed in their position or ratio.
     * 
     * @param node the node whose ports to place.
     */
    private void placeNodePorts(final NodeAdapter<?> node) {
        KVector nodeSize = node.getSize();
        boolean accountForLabels =
                node.getProperty(LayoutOptions.SIZE_CONSTRAINT).contains(SizeConstraint.PORT_LABELS);
        
        // Compute the space to be left between the ports
        // Note: If the size constraints of this node are empty, the height and width of the ports
        // on each side are zero. That is intentional: if this wasn't the case, bad things would
        // happen if the ports would actually need more size than the node at its current (unchanged)
        // size would be able to provide.
        double westDelta = (nodeSize.y - westPortsHeight) / (westPortsCount + 1);
        double westY = nodeSize.y - westDelta;
        double eastDelta = (nodeSize.y - eastPortsHeight) / (eastPortsCount + 1);
        double eastY = eastDelta;
        double northDelta = (nodeSize.x - northPortsWidth) / (northPortsCount + 1);
        double northX = northDelta;
        double southDelta = (nodeSize.x - southPortsWidth) / (southPortsCount + 1);
        double southX = nodeSize.x - southDelta;
        
        // Arrange the ports
        for (PortAdapter<?> port : node.getPorts()) {
            // FIXME as above
            //float portOffset = port.getProperty(Properties.OFFSET);
            float portOffset = 0;
            KVector portSize = port.getSize();
            Rectangle2D.Double portMargins = port.getMargin();
            
            KVector position = new KVector(port.getPosition());
            switch (port.getSide()) {
            case WEST:
                position.x = -portSize.x - portOffset;
                position.y = westY - portSize.y
                        - (accountForLabels ? portMargins.height : 0.0);
                westY -= westDelta + portSize.y
                        + (accountForLabels ? portMargins.y + portMargins.height : 0.0);
                break;
            case EAST:
                position.x = nodeSize.x + portOffset;
                position.y = eastY
                        + (accountForLabels ? portMargins.y : 0.0);
                eastY += eastDelta + portSize.y
                        + (accountForLabels ? portMargins.y + portMargins.height : 0.0);
                break;
            case NORTH:
                position.x = northX
                        + (accountForLabels ? portMargins.x : 0.0);
                position.y = -port.getSize().y - portOffset;
                northX += northDelta + portSize.x
                        + (accountForLabels ? portMargins.x + portMargins.width : 0.0);
                break;
            case SOUTH:
                position.x = southX - portSize.x
                        - (accountForLabels ? portMargins.width : 0.0);
                position.y = nodeSize.y + portOffset;
                southX -= southDelta + portSize.x
                        + (accountForLabels ? portMargins.x + portMargins.width : 0.0);
                break;
            }
            // TODO new
            port.setPosition(position);
        }
    }
    
    /**
     * Places the ports of a hypernode.
     * 
     * @param node the hypernode whose ports to place.
     */
    private void placeHypernodePorts(final NodeAdapter<?> node) {
        for (PortAdapter<?> port : node.getPorts()) {
            KVector position = new KVector(port.getPosition());
            switch (port.getSide()) {
            case WEST:
                position.x = 0;
                position.y = node.getSize().y / 2;
                break;
            case EAST:
                position.x = node.getSize().x;
                position.y = node.getSize().y / 2;
                break;
            case NORTH:
                position.x = node.getSize().x / 2;
                position.y = 0;
                break;
            case SOUTH:
                position.x = node.getSize().x / 2;
                position.y = node.getSize().y;
                break;
            }
            // TODO new
            port.setPosition(position);
        }
    }

    
    ///////////////////////////////////////////////////////////////////////////////
    // PLACING NODE LABELS
    
    /**
     * Calculates the position of the node's labels.
     * 
     * @param node the node whose labels to place.
     * @param labelSpacing spacing between labels and other objects.
     */
    private void placeNodeLabels(final NodeAdapter<?> node, final double labelSpacing) {
        // Retrieve the first node label, if any
        if (node.getLabels().isEmpty()) {
            return;
        }
        
        // Top left position where the node labels will start to be placed in a vertical stack
        KVector labelGroupPos = new KVector();
        
        // Retrieve label placement policy
        EnumSet<NodeLabelPlacement> nodeLabelPlacement =
                node.getProperty(LayoutOptions.NODE_LABEL_PLACEMENT);
        
        // This method only sets insets if the node label is to be placed on the inside
        if (nodeLabelPlacement.contains(NodeLabelPlacement.INSIDE)) {
            // Y coordinate
            if (nodeLabelPlacement.contains(NodeLabelPlacement.V_TOP)) {
                labelGroupPos.y = requiredPortLabelSpace.y + labelSpacing;
            } else if (nodeLabelPlacement.contains(NodeLabelPlacement.V_CENTER)) {
                labelGroupPos.y = (node.getSize().y - nodeLabelsBoundingBox.y) / 2.0;
            } else if (nodeLabelPlacement.contains(NodeLabelPlacement.V_BOTTOM)) {
                labelGroupPos.y = node.getSize().y - requiredPortLabelSpace.height
                        - nodeLabelsBoundingBox.y - labelSpacing;
            }
            
            // X coordinate
            if (nodeLabelPlacement.contains(NodeLabelPlacement.H_LEFT)) {
                labelGroupPos.x = requiredPortLabelSpace.x + labelSpacing;
            } else if (nodeLabelPlacement.contains(NodeLabelPlacement.H_CENTER)) {
                labelGroupPos.x = (node.getSize().x - nodeLabelsBoundingBox.x) / 2.0;
            } else if (nodeLabelPlacement.contains(NodeLabelPlacement.H_RIGHT)) {
                labelGroupPos.x = node.getSize().x - requiredPortLabelSpace.width
                        - nodeLabelsBoundingBox.x - labelSpacing;
            }
        } else if (nodeLabelPlacement.contains(NodeLabelPlacement.OUTSIDE)) {
            // TODO: Outside placement doesn't take ports and port labels into account yet.
            
            // Different placement logic depending on whether horizontal or vertical placement
            // is prioritized
            if (nodeLabelPlacement.contains(NodeLabelPlacement.H_PRIORITY)) {
                boolean leftOrRight = false;
                
                // X coordinate
                if (nodeLabelPlacement.contains(NodeLabelPlacement.H_LEFT)) {
                    labelGroupPos.x = -(nodeLabelsBoundingBox.x + labelSpacing);
                    leftOrRight = true;
                } else if (nodeLabelPlacement.contains(NodeLabelPlacement.H_CENTER)) {
                    labelGroupPos.x = (node.getSize().x - nodeLabelsBoundingBox.x) / 2.0;
                } else if (nodeLabelPlacement.contains(NodeLabelPlacement.H_RIGHT)) {
                    labelGroupPos.x = node.getSize().x + labelSpacing;
                    leftOrRight = true;
                }
                
                // Y coordinate
                if (nodeLabelPlacement.contains(NodeLabelPlacement.V_TOP)) {
                    if (leftOrRight) {
                        labelGroupPos.y = 0;
                    } else {
                        labelGroupPos.y = -(nodeLabelsBoundingBox.y + labelSpacing);
                    }
                } else if (nodeLabelPlacement.contains(NodeLabelPlacement.V_CENTER)) {
                    labelGroupPos.y = (node.getSize().y - nodeLabelsBoundingBox.y) / 2.0;
                } else if (nodeLabelPlacement.contains(NodeLabelPlacement.V_BOTTOM)) {
                    if (leftOrRight) {
                        labelGroupPos.y = node.getSize().y - nodeLabelsBoundingBox.y;
                    } else {
                        labelGroupPos.y = node.getSize().y + labelSpacing;
                    }
                }
            } else {
                boolean topOrBottom = false;
                
                // Y coordinate
                if (nodeLabelPlacement.contains(NodeLabelPlacement.V_TOP)) {
                    labelGroupPos.y = -(nodeLabelsBoundingBox.y + labelSpacing);
                    topOrBottom = true;
                } else if (nodeLabelPlacement.contains(NodeLabelPlacement.V_CENTER)) {
                    labelGroupPos.y = (node.getSize().y - nodeLabelsBoundingBox.y) / 2.0;
                } else if (nodeLabelPlacement.contains(NodeLabelPlacement.V_BOTTOM)) {
                    labelGroupPos.y = node.getSize().y + labelSpacing;
                    topOrBottom = true;
                }
                
                // X coordinate
                if (nodeLabelPlacement.contains(NodeLabelPlacement.H_LEFT)) {
                    if (topOrBottom) {
                        labelGroupPos.x = 0;
                    } else {
                        labelGroupPos.x = -(nodeLabelsBoundingBox.x + labelSpacing);
                    }
                } else if (nodeLabelPlacement.contains(NodeLabelPlacement.H_CENTER)) {
                    labelGroupPos.x = (node.getSize().x - nodeLabelsBoundingBox.x) / 2.0;
                } else if (nodeLabelPlacement.contains(NodeLabelPlacement.H_RIGHT)) {
                    if (topOrBottom) {
                        labelGroupPos.x = node.getSize().x - nodeLabelsBoundingBox.x;
                    } else {
                        labelGroupPos.x = node.getSize().x + labelSpacing;
                    }
                }
            }
        }
        
        // Place labels
        applyNodeLabelPositions(node, labelGroupPos, labelSpacing);
    }
    
    /**
     * Places the given node's labels in a vertical stack, starting at the given position.
     * 
     * @param node the node whose labels are to be placed.
     * @param startPosition coordinates where the first label is to be placed.
     * @param labelSpacing space to be left between the labels.
     */
    private void applyNodeLabelPositions(final NodeAdapter<?> node, final KVector startPosition,
            final double labelSpacing) {
        
        // The horizontal alignment depends on where the labels are placed exactly
        EnumSet<NodeLabelPlacement> nodeLabelPlacement =
                node.getProperty(LayoutOptions.NODE_LABEL_PLACEMENT);
        NodeLabelPlacement horizontalPlacement = NodeLabelPlacement.H_CENTER;
        
        if (nodeLabelPlacement.contains(NodeLabelPlacement.INSIDE)) {
            // Inside placement
            if (nodeLabelPlacement.contains(NodeLabelPlacement.H_LEFT)) {
                horizontalPlacement = NodeLabelPlacement.H_LEFT;
            } else if (nodeLabelPlacement.contains(NodeLabelPlacement.H_RIGHT)) {
                horizontalPlacement = NodeLabelPlacement.H_RIGHT;
            }
        } else {
            // Outside placement; alignment is reversed
            if (nodeLabelPlacement.contains(NodeLabelPlacement.H_LEFT)) {
                horizontalPlacement = NodeLabelPlacement.H_RIGHT;
            } else if (nodeLabelPlacement.contains(NodeLabelPlacement.H_RIGHT)) {
                horizontalPlacement = NodeLabelPlacement.H_LEFT;
            }
        }
        
        // We have to keep track of the current y coordinate
        double currentY = startPosition.y;
        
        // Place all labels
        for (LabelAdapter<?> label : node.getLabels()) {
            KVector position = new KVector(label.getPosition());
            // Apply y coordinate
            //label.getPosition().y = currentY;
            position.y = currentY;
            
            // The x coordinate depends on the H_xxx constants
            if (horizontalPlacement == NodeLabelPlacement.H_LEFT) {
                position.x = startPosition.x;
            } else if (horizontalPlacement == NodeLabelPlacement.H_CENTER) {
                position.x = startPosition.x
                        + (nodeLabelsBoundingBox.x - label.getSize().x) / 2.0; 
            } else if (horizontalPlacement == NodeLabelPlacement.H_RIGHT) {
                position.x = startPosition.x + nodeLabelsBoundingBox.x - label.getSize().x;
            }
            
            // TODO new
            label.setPosition(position);
            
            // Update y position
            currentY += label.getSize().y + labelSpacing;
        }
    }

    /*
     * Interfaces
     */

    public interface SizeCalculationAdapterFactory<G> {
        
        GraphAdapter<G> getGraphAdapter(G graph);
        
    }
    
    
    /**
     * .
     */
    public interface GraphElementAdapter<T> {

        KVector getSize();
        
        void setSize(final KVector size);

        KVector getPosition();
        
        void setPosition(final KVector pos);
        
        <P> P getProperty(final IProperty<P> prop);
    }
    
    /**
     * .
     */
    public interface GraphAdapter<T> extends GraphElementAdapter<T>{
        
        List<NodeAdapter<?>> getNodes();
        
    }

    /**
     * .
     */
    public interface NodeAdapter<T> extends GraphElementAdapter<T> {

        List<LabelAdapter<?>> getLabels();

        List<PortAdapter<?>> getPorts();
        
        void setInsets(final Rectangle2D.Double insets);
    }

    /**
     * .
     */
    public interface PortAdapter<T> extends GraphElementAdapter<T> {

        PortSide getSide();

        Rectangle2D.Double getMargin();
        
        void setMargin(final Rectangle2D.Double margin);

        List<LabelAdapter<?>> getLabels();
    }

    /**
     * .
     */
    public interface LabelAdapter<T> extends GraphElementAdapter<T> {

    }
    
    
    /*
     * KGraph adapters
     */
    
    public class KGraphSizeCalculationAdapterFactory implements SizeCalculationAdapterFactory<KNode> {

        /**
         * {@inheritDoc}
         */
        public GraphAdapter<KNode> getGraphAdapter(final KNode graph) {
            return new KGraphAdapter(graph);
        }

    }

    /**
     * .
     */
    public abstract static class AbstractKGraphElementAdapter<T extends KGraphElement> implements
            GraphElementAdapter<T> {

        // CHECKSTYLEOFF 
        protected T element;
        protected KShapeLayout layout;

        /**
         * @param element
         *  .
         */
        public AbstractKGraphElementAdapter(final T element) {
            this.element = element;
            try {
                layout = element.getData(KShapeLayout.class);
            } catch (ClassCastException cce) {
                throw new RuntimeException(
                        "Graph adapters are only supported for shape-full types.");
            }
        }

        /**
         * {@inheritDoc}
         */
        public <P> P getProperty(final IProperty<P> prop) {
            return layout.getProperty(prop);
        }

        /**
         * {@inheritDoc}
         */
        public KVector getPosition() {
            return new KVector(layout.getXpos(), layout.getYpos());
        }

        /**
         * {@inheritDoc}
         */
        public KVector getSize() {
            return new KVector(layout.getWidth(), layout.getHeight());
        }

        /**
         * {@inheritDoc}
         */
        public void setSize(final KVector size) {
            layout.setWidth((float) size.x);
            layout.setHeight((float) size.y);
        }

        /**
         * {@inheritDoc}
         */
        public void setPosition(final KVector pos) {
            layout.setXpos((float) pos.x);
            layout.setYpos((float) pos.y);
        }
    }

    public class KGraphAdapter extends AbstractKGraphElementAdapter<KNode> implements GraphAdapter<KNode> {
        /**
         * @param node
         *  .
         */
        public KGraphAdapter(final KNode node) {
            super(node);
        }
        
        /**
         * {@inheritDoc}
         */
        public List<NodeAdapter<?>> getNodes() {
            List<NodeAdapter<?>> children = Lists.newLinkedList();
            for(KNode n : element.getChildren()) {
                children.add(new KNodeAdapter(n));
            }
            return children;
        }
    }
    
    /**
     * .
     */
    public static class KNodeAdapter extends AbstractKGraphElementAdapter<KNode> implements
            NodeAdapter<KNode> {

        /**
         * @param node
         *  .
         */
        public KNodeAdapter(final KNode node) {
            super(node);
        }

        /**
         * {@inheritDoc}
         */
        public List<LabelAdapter<?>> getLabels() {
            List<LabelAdapter<?>> labelAdapters = Lists.newLinkedList();
            for (KLabel l : element.getLabels()) {
                labelAdapters.add(new KLabelAdapter(l));
            }
            return labelAdapters;
        }

        /**
         * {@inheritDoc}
         */
        public List<PortAdapter<?>> getPorts() {
            List<PortAdapter<?>> portAdapters = Lists.newLinkedList();
            for (KPort p : element.getPorts()) {
                portAdapters.add(new KPortAdapter(p));
            }
            return portAdapters;
        }

        /**
         * {@inheritDoc}
         */
        public void setInsets(final Rectangle2D.Double insets) {
            layout.getInsets().setLeft((float) insets.x);
            layout.getInsets().setTop((float) insets.y);
            layout.getInsets().setRight((float) insets.width);
            layout.getInsets().setBottom((float) insets.height);
        }

    }

    /**
     * .
     */
    private static class KLabelAdapter extends AbstractKGraphElementAdapter<KLabel> implements
            LabelAdapter<KLabel> {

        /**
         * 
         */
        public KLabelAdapter(final KLabel label) {
            super(label);
        }
    }

    /**
     * .
     */
    private static class KPortAdapter extends AbstractKGraphElementAdapter<KPort> implements
            PortAdapter<KPort> {

        /**
         * 
         */
        public KPortAdapter(final KPort port) {
            super(port);
        }

        /**
         * {@inheritDoc}
         */
        public PortSide getSide() {
            return layout.getProperty(LayoutOptions.PORT_SIDE); 
        }

        /**
         * {@inheritDoc}
         */
        public Rectangle2D.Double getMargin() {
            // FIXME is this the same as insets??
            KInsets insets = layout.getInsets();
            return new Rectangle2D.Double(insets.getLeft(), insets.getTop(), 
                    insets.getRight(), insets.getBottom());
        }

        /**
         * {@inheritDoc}
         */
        public void setMargin(final Rectangle2D.Double margin) {
            // FIXME as above
            KInsets insets = layout.getInsets();
            insets.setLeft((float) margin.x);
            insets.setTop((float) margin.y);
            insets.setRight((float) margin.width);
            insets.setBottom((float) margin.height);
        }

        /**
         * {@inheritDoc}
         */
        public List<LabelAdapter<?>> getLabels() {
            List<LabelAdapter<?>> labelAdapters = Lists.newLinkedList();
            for (KLabel l : element.getLabels()) {
                labelAdapters.add(new KLabelAdapter(l));
            }
            return labelAdapters;
        }

    }
}