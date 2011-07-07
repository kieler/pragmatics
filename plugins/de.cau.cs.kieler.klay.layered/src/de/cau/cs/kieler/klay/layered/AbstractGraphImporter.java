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
package de.cau.cs.kieler.klay.layered;

import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortConstraints;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.klay.layered.graph.Insets;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;
import de.cau.cs.kieler.klay.layered.properties.EdgeConstraint;
import de.cau.cs.kieler.klay.layered.properties.InLayerConstraint;
import de.cau.cs.kieler.klay.layered.properties.LayerConstraint;
import de.cau.cs.kieler.klay.layered.properties.NodeType;
import de.cau.cs.kieler.klay.layered.properties.Properties;

/**
 * Abstract implementation of {@link IGraphImporter}, containing commonly used functionality.
 * Graph importers may subclass this class instead of implementing the interface directly.
 * 
 * <p>When a graph importer supports external ports, it must create dummies for those ports by calling
 * {@link #createExternalPortDummy(Object, PortConstraints, PortSide, int, int, KInsets, KVector)}.
 * The correct position of those ports can later be retrieved by calling
 * {@link #getExternalPortPosition(LNode, double, double)}.</p>
 * 
 * @param <T> the type of graph that this importer can transform into a layered graph.
 * @author cds
 */
public abstract class AbstractGraphImporter<T> implements IGraphImporter<T> {
    
    ///////////////////////////////////////////////////////////////////////////////
    // External Ports
    
    /**
     * Creates a dummy for an external port. The dummy will have just one port. The port is on
     * the eastern side for western external ports, and on the western side for all other ports.
     * 
     * <p>The returned dummy node is decorated with some properties. Its {@link Properties#NODE_TYPE}
     * is set to {@link NodeType#EXTERNAL_PORT}. Its {@link Properties#ORIGIN} is set
     * to the external port object. The {@link LayoutOptions#PORT_CONSTRAINTS} are set to
     * {@link PortConstraints#FIXED_POS}. For western and eastern port dummies, the
     * {@link Properties#LAYER_CONSTRAINT} is set to{@link LayerConstraint#FIRST_SEPARATE}
     * and {@link LayerConstraint#LAST_SEPARATE}, respectively. For northern and southern
     * port dummies, the {@link Properties#IN_LAYER_CONSTRAINT} is set to
     * {@link InLayerConstraint#TOP} and {@link InLayerConstraint#BOTTOM},
     * respectively. For eastern dummies, the {@link Properties#EDGE_CONSTRAINT} is set to
     * {@link EdgeConstraint#OUTGOING_ONLY}; for all other dummies, it is set to
     * {@link EdgeConstraint#INCOMING_ONLY}. {@link Properties#EXT_PORT_SIDE} is
     * set to the side of the external port represented. If the port constraints of the original
     * port's node are set to {@link PortConstraints#FIXED_RATIO} or {@link PortConstraints#FIXED_POS},
     * the dummy node's {@link Properties#EXT_PORT_RATIO_OR_POSITION} property is set to the port's
     * original position, defined relative to the original node's origin. (as opposed to relative to
     * the node's content area)</p>
     * 
     * @param port the port object the dummy will represent.
     * @param portConstraints constraints for external ports.
     * @param portSide the side of the external port.
     * @param netFlow the number of incoming minus the number of outgoing edges.
     * @param portNodeSize the size of the node the port belongs to. Only relevant if the port
     *                     constraints are {@code FIXED_RATIO}.
     * @param portPosition the current port position. Only relevant if the port constraints are
     *                     {@code FIXED_ORDER}, {@code FIXED_RATIO} or {@code FIXED_POSITION}.
     * @return a dummy node representing the external port.
     */
    protected LNode createExternalPortDummy(final Object port, final PortConstraints portConstraints,
            final PortSide portSide, final int netFlow, final KVector portNodeSize,
            final KVector portPosition) {
        
        PortSide finalExternalPortSide = portSide;
        
        // Create the dummy with one port
        LNode dummy = new LNode();
        dummy.setProperty(Properties.NODE_TYPE, NodeType.EXTERNAL_PORT);
        dummy.setProperty(Properties.ORIGIN, port);
        dummy.setProperty(LayoutOptions.PORT_CONSTRAINTS, PortConstraints.FIXED_POS);
        
        LPort dummyPort = new LPort();
        dummyPort.setSide(PortSide.WEST);
        dummyPort.setNode(dummy);
        
        // If the port constraints are free, we need to determine where to put the dummy (and its port)
        if (!portConstraints.isSideFixed()) {
            if (netFlow > 0) {
                finalExternalPortSide = PortSide.EAST;
            } else {
                finalExternalPortSide = PortSide.WEST;
            }
        }
        
        // With the port side at hand, set the necessary properties
        switch (finalExternalPortSide) {
        case WEST:
            dummy.setProperty(Properties.LAYER_CONSTRAINT, LayerConstraint.FIRST_SEPARATE);
            dummy.setProperty(Properties.EDGE_CONSTRAINT, EdgeConstraint.OUTGOING_ONLY);
            dummyPort.setSide(PortSide.EAST);
            break;
        
        case EAST:
            dummy.setProperty(Properties.LAYER_CONSTRAINT, LayerConstraint.LAST_SEPARATE);
            dummy.setProperty(Properties.EDGE_CONSTRAINT, EdgeConstraint.INCOMING_ONLY);
            break;
        
        case NORTH:
            dummy.setProperty(Properties.IN_LAYER_CONSTRAINT, InLayerConstraint.TOP);
            dummy.setProperty(Properties.EDGE_CONSTRAINT, EdgeConstraint.INCOMING_ONLY);
            break;
        
        case SOUTH:
            dummy.setProperty(Properties.IN_LAYER_CONSTRAINT, InLayerConstraint.BOTTOM);
            dummy.setProperty(Properties.EDGE_CONSTRAINT, EdgeConstraint.INCOMING_ONLY);
            break;
        }
        
        // From FIXED_ORDER onwards, we need to save the port position or ratio
        if (portConstraints.isOrderFixed()) {
            double positionOrRatio = 0;
            
            switch (finalExternalPortSide) {
            case WEST:
            case EAST:
                positionOrRatio = portPosition.y;
                if (portConstraints.isRatioFixed() && !portConstraints.isPosFixed()) {
                    positionOrRatio /= portNodeSize.y;
                }
                
                break;
                
            case NORTH:
            case SOUTH:
                positionOrRatio = portPosition.x;
                if (portConstraints.isRatioFixed() && !portConstraints.isPosFixed()) {
                    positionOrRatio /= portNodeSize.x;
                }
                
                break;
            }
            
            dummy.setProperty(Properties.EXT_PORT_RATIO_OR_POSITION, positionOrRatio);
        }
        
        // Set the port side of the dummy
        dummy.setProperty(Properties.EXT_PORT_SIDE, finalExternalPortSide);
        
        return dummy;
    }
    
    /**
     * Calculates the position of the external port's top left corner from the position of the
     * given dummy node that represents the port. The position is relative to the graph node's
     * top left corner.
     * 
     * @param graph the graph for which ports shall be placed
     * @param portDummy the dummy node representing the external port.
     * @param portWidth the external port's width.
     * @param portHeight the external port's height.
     * @return the external port's position.
     */
    protected KVector getExternalPortPosition(final LayeredGraph graph, final LNode portDummy,
            final double portWidth, final double portHeight) {
        
        KVector portPosition = new KVector(portDummy.getPosition());
        float portOffset = portDummy.getProperty(LayoutOptions.OFFSET);
        
        // Get some properties of the graph
        KVector size = graph.getSize();
        Insets.Double insets = graph.getInsets();
        float borderSpacing = graph.getProperty(Properties.BORDER_SPACING);
        KVector offset = graph.getOffset();
        
        // The exact coordinates depend on the port's side...
        switch (portDummy.getProperty(Properties.EXT_PORT_SIDE)) {
        case NORTH:
            portPosition.x += insets.left + borderSpacing + offset.x - (portWidth / 2.0);
            portPosition.y = -portHeight - portOffset;
            break;
        
        case EAST:
            portPosition.x = size.x + insets.left + insets.right + 2 * borderSpacing + portOffset;
            portPosition.y += insets.top + borderSpacing + offset.y - (portHeight / 2.0);
            break;
        
        case SOUTH:
            portPosition.x += insets.left + borderSpacing + offset.x - (portWidth / 2.0);
            portPosition.y = size.y + insets.top + insets.bottom + 2 * borderSpacing + portOffset;
            break;
        
        case WEST:
            portPosition.x = -portWidth - portOffset;
            portPosition.y += insets.top + borderSpacing + offset.y - (portHeight / 2.0);
            break;
        }
        
        return portPosition;
    }
    
}
