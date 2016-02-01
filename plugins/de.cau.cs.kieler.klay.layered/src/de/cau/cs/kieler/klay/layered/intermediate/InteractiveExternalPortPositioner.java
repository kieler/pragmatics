/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2015 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.layered.intermediate;

import com.google.common.base.Function;
import com.google.common.base.Optional;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.util.nodespacing.Spacing.Margins;
import de.cau.cs.kieler.klay.layered.ILayoutProcessor;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LNode.NodeType;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.properties.GraphProperties;
import de.cau.cs.kieler.klay.layered.properties.InLayerConstraint;
import de.cau.cs.kieler.klay.layered.properties.InternalProperties;
import de.cau.cs.kieler.klay.layered.properties.LayerConstraint;
import de.cau.cs.kieler.klay.layered.properties.Properties;

/**
 * Interactive layout, for instance using
 * {@link de.cau.cs.kieler.klay.layered.p1cycles.InteractiveCycleBreaker InteractiveCycleBreaker} or
 * {@link de.cau.cs.kieler.klay.layered.p2layers.InteractiveLayerer InteractiveLayerer} relies on
 * previously specified positions to determine a layout of the graph. For dummy nodes such as
 * external port dummies no positions can be specified up front. This processor performs this task
 * and assigns reasonable positions to dummy nodes. For example, westward external ports are
 * positioned left of all other nodes of the graph.
 * 
 * <dl>
 *   <dt>Precondition:</dt>
 *     <dd>a layered graph<dd>
 *   <dt>Postcondition:</dt>
 *     <dd>external ports possess reasonable positions for interactive layering.</dd>
 *   <dt>Slots:</dt>
 *     <dd>Before phase 1.</dd>
 * </dl>
 * 
 * @author uru
 */
public class InteractiveExternalPortPositioner implements ILayoutProcessor {

    /** An arbitrarily chosen spacing value to separate external port dummies from other nodes. */
    private static final int ARBITRARY_SPACING = 10;
    
    private double minX = Double.POSITIVE_INFINITY, maxX = Double.NEGATIVE_INFINITY;
    private double minY = Double.POSITIVE_INFINITY, maxY = Double.NEGATIVE_INFINITY;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void process(final LGraph layeredGraph, final IKielerProgressMonitor progressMonitor) {
     
        // if the graph does not contain any external ports ...
        if (!layeredGraph.getProperty(InternalProperties.GRAPH_PROPERTIES).contains(
                GraphProperties.EXTERNAL_PORTS)) {
            // ... nothing we can do about it
            return;
        }
        
        // find the minimum and maximum x coordinates of the graph 
        for (LNode node : layeredGraph.getLayerlessNodes()) {
            if (node.getType() == NodeType.NORMAL) {
                Margins margins = node.getProperty(LayoutOptions.MARGINS);
                minX = Math.min(minX, node.getPosition().x - margins.left);
                maxX = Math.max(maxX, node.getPosition().x + node.getSize().x + margins.right);
                minY = Math.min(minY, node.getPosition().y - margins.top);
                maxY = Math.max(maxY, node.getPosition().y + node.getSize().y + margins.bottom);
            }
        }
        
        // assign reasonable coordinates to external port dummies
        for (LNode node : layeredGraph.getLayerlessNodes()) {
            if (node.getType() != NodeType.NORMAL) {
                switch (node.getType()) {

                // SUPPRESS CHECKSTYLE NEXT 50 InnerAssignment
                case EXTERNAL_PORT:
                    LayerConstraint lc = node.getProperty(Properties.LAYER_CONSTRAINT);
                    if (lc == LayerConstraint.FIRST_SEPARATE) {
                        // it's a WEST port
                        node.getPosition().x = minX - ARBITRARY_SPACING;
                        findYCoordinate(node, (e) -> e.getTarget().getNode())
                            .transform((d) -> node.getPosition().y = d);
                        break;
                    }
                    
                    if (lc == LayerConstraint.LAST_SEPARATE) {
                        // it's a EAST port
                        node.getPosition().x = maxX + ARBITRARY_SPACING;
                        findYCoordinate(node, (e) -> e.getSource().getNode())
                            .transform((d) -> node.getPosition().y = d);
                        break;
                    }

                    InLayerConstraint ilc = node.getProperty(InternalProperties.IN_LAYER_CONSTRAINT);
                    if (ilc == InLayerConstraint.TOP) {
                        findNorthSouthPortXCoordinate(node)
                            .transform((x) -> node.getPosition().x = x + ARBITRARY_SPACING);
                        node.getPosition().y = minY - ARBITRARY_SPACING;
                        break;
                    }
                    
                    if (ilc == InLayerConstraint.BOTTOM) {
                        findNorthSouthPortXCoordinate(node)
                            .transform((x) -> node.getPosition().x = x + ARBITRARY_SPACING);
                        node.getPosition().y = maxY + ARBITRARY_SPACING;
                        break;
                    }
                   
                    break;
                default:
                    throw new IllegalArgumentException("The node type " + node.getType()
                            + " is not supported by the " + this.getClass());
                }
            }
        }
    }
    
    private Optional<Double> findYCoordinate(final LNode dummy,
            final Function<LEdge, LNode> funGetOtherNode) {
        for (LEdge e : dummy.getConnectedEdges()) {
            LNode other = funGetOtherNode.apply(e);
            return Optional.of(other.getPosition().y + other.getSize().y / 2);
        }

        return Optional.absent();
    }
    
    private Optional<Double> findNorthSouthPortXCoordinate(final LNode dummy) {
        
        // external port dummies must have exactly one port
        assert dummy.getPorts().size() == 1;
        
        LPort port = dummy.getPorts().get(0);
        
        if (!port.getOutgoingEdges().isEmpty() && !port.getIncomingEdges().isEmpty()) {
            throw new IllegalStateException("Interactive layout does not support "
                    + "NORTH/SOUTH ports with incoming _and_ outgoing edges.");
        }
        
        if (!port.getOutgoingEdges().isEmpty()) {
            // find the minimum position
            double min = Double.POSITIVE_INFINITY;
            for (LEdge e : port.getOutgoingEdges()) {
                LNode n = e.getTarget().getNode();
                Margins margins = n.getProperty(LayoutOptions.MARGINS);
                min = Math.min(min, n.getPosition().x - margins.left);
            }
            return Optional.of(min);
        }
        
        if (!port.getIncomingEdges().isEmpty()) {
            // find the maximum value
            double max = Double.NEGATIVE_INFINITY;
            for (LEdge e : port.getIncomingEdges()) {
                LNode n = e.getSource().getNode();
                Margins margins = n.getProperty(LayoutOptions.MARGINS);
                max = Math.max(max, n.getPosition().x + n.getSize().x + margins.right);
            }
            return Optional.of(max);
        }
        
        // we should never reach here
        return Optional.absent();
    }
}
