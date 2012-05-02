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

import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortConstraints;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.klay.layered.graph.LInsets;
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
     * the eastern side for western external ports, on the western side for eastern external ports,
     * on the southern side for northern external ports, and on the northern side for southern
     * external ports.
     * 
     * <p>The returned dummy node is decorated with some properties:</p>
     * <ul>
     *   <li>Its {@link Properties#NODE_TYPE} is set to {@link NodeType#EXTERNAL_PORT}.</li>
     *   <li>Its {@link Properties#ORIGIN} is set to the external port object.</li>
     *   <li>The {@link LayoutOptions#PORT_CONSTRAINTS} are set to
     *     {@link PortConstraints#FIXED_POS}.</li>
     *   <li>For western and eastern port dummies, the {@link Properties#LAYER_CONSTRAINT} is set to
     *     {@link LayerConstraint#FIRST_SEPARATE} and {@link LayerConstraint#LAST_SEPARATE},
     *     respectively.</li>
     *   <li>For northern and southern port dummies, the {@link Properties#IN_LAYER_CONSTRAINT} is set to
     *     {@link InLayerConstraint#TOP} and {@link InLayerConstraint#BOTTOM}, respectively.</li>
     *   <li>For eastern dummies, the {@link Properties#EDGE_CONSTRAINT} is set to
     *     {@link EdgeConstraint#OUTGOING_ONLY}; for western dummies, it is set to
     *     {@link EdgeConstraint#INCOMING_ONLY}; for all other dummies, it is left unset.</li>
     *   <li>{@link Properties#EXT_PORT_SIDE} is set to the side of the external port represented.</li>
     *   <li>If the port constraints of the original port's node are set to
     *     {@link PortConstraints#FIXED_RATIO} or {@link PortConstraints#FIXED_POS}, the dummy node's
     *     {@link Properties#EXT_PORT_RATIO_OR_POSITION} property is set to the port's original position,
     *     defined relative to the original node's origin. (as opposed to relative to the node's content
     *     area)</li>
     *   <li>The {@link Properties#EXT_PORT_SIZE} property is set to the size of the external port the
     *     the dummy represents, while the size of the dummy itself is set to {@code (0, 0)}.</li>
     * </ul>
     * 
     * @param port the port object the dummy will represent.
     * @param portConstraints constraints for external ports.
     * @param portSide the side of the external port.
     * @param netFlow the number of incoming minus the number of outgoing edges.
     * @param portNodeSize the size of the node the port belongs to. Only relevant if the port
     *                     constraints are {@code FIXED_RATIO}.
     * @param portPosition the current port position. Only relevant if the port constraints are
     *                     {@code FIXED_ORDER}, {@code FIXED_RATIO} or {@code FIXED_POSITION}.
     * @param portSize size of the port. Depending on the port's side, the created dummy will
     *                 have the same width or height as the port, with the other dimension set
     *                 to zero.
     * @return a dummy node representing the external port.
     */
    protected LNode createExternalPortDummy(final KPort port, final PortConstraints portConstraints,
            final PortSide portSide, final int netFlow, final KVector portNodeSize,
            final KVector portPosition, final KVector portSize) {
        
        PortSide finalExternalPortSide = portSide;
        
        // Create the dummy with one port
        LNode dummy = new LNode();
        dummy.setProperty(Properties.NODE_TYPE, NodeType.EXTERNAL_PORT);
        dummy.setProperty(Properties.ORIGIN, port);
        dummy.setProperty(Properties.EXT_PORT_SIZE, portSize);
        dummy.setProperty(LayoutOptions.PORT_CONSTRAINTS, PortConstraints.FIXED_POS);
        dummy.setProperty(Properties.OFFSET, port.getData(KShapeLayout.class)
                .getProperty(LayoutOptions.OFFSET));
        
        LPort dummyPort = new LPort();
        dummyPort.setNode(dummy);
        
        // If the port constraints are free, we need to determine where to put the dummy (and its port)
        if (!portConstraints.isSideFixed()) {
            if (netFlow > 0) {
                finalExternalPortSide = PortSide.EAST;
            } else {
                finalExternalPortSide = PortSide.WEST;
            }
        }
        
        // With the port side at hand, set the necessary properties and place the dummy's port
        // at the dummy's center
        switch (finalExternalPortSide) {
        case WEST:
            dummy.setProperty(Properties.LAYER_CONSTRAINT, LayerConstraint.FIRST_SEPARATE);
            dummy.setProperty(Properties.EDGE_CONSTRAINT, EdgeConstraint.OUTGOING_ONLY);
            dummy.getSize().y = portSize.y;
            dummyPort.setSide(PortSide.EAST);
            dummyPort.getPosition().y = portSize.y / 2.0;
            break;
        
        case EAST:
            dummy.setProperty(Properties.LAYER_CONSTRAINT, LayerConstraint.LAST_SEPARATE);
            dummy.setProperty(Properties.EDGE_CONSTRAINT, EdgeConstraint.INCOMING_ONLY);
            dummy.getSize().y = portSize.y;
            dummyPort.setSide(PortSide.WEST);
            dummyPort.getPosition().y = portSize.y / 2.0;
            break;
        
        case NORTH:
            dummy.setProperty(Properties.IN_LAYER_CONSTRAINT, InLayerConstraint.TOP);
            dummy.getSize().x = portSize.x;
            dummyPort.setSide(PortSide.SOUTH);
            dummyPort.getPosition().x = portSize.x / 2.0;
            break;
        
        case SOUTH:
            dummy.setProperty(Properties.IN_LAYER_CONSTRAINT, InLayerConstraint.BOTTOM);
            dummy.getSize().x = portSize.x;
            dummyPort.setSide(PortSide.NORTH);
            dummyPort.getPosition().x = portSize.x / 2.0;
            break;
        
        default:
            // Should never happen!
            assert false : finalExternalPortSide;
        }
        
        // From FIXED_ORDER onwards, we need to save the port position or ratio
        if (portConstraints.isOrderFixed()) {
            double positionOrRatio = 0;
            
            switch (finalExternalPortSide) {
            case WEST:
            case EAST:
                positionOrRatio = portPosition.y;
                if (portConstraints.isRatioFixed()) {
                    positionOrRatio /= portNodeSize.y;
                }
                
                break;
                
            case NORTH:
            case SOUTH:
                positionOrRatio = portPosition.x;
                if (portConstraints.isRatioFixed()) {
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
        portPosition.x += portDummy.getSize().x / 2.0;
        portPosition.y += portDummy.getSize().y / 2.0;
        float portOffset = portDummy.getProperty(LayoutOptions.OFFSET);
        
        // Get some properties of the graph
        KVector graphSize = graph.getSize();
        LInsets.Double insets = graph.getInsets();
        float borderSpacing = graph.getProperty(Properties.BORDER_SPACING);
        KVector graphOffset = graph.getOffset();
        
        // The exact coordinates depend on the port's side...
        switch (portDummy.getProperty(Properties.EXT_PORT_SIDE)) {
        case NORTH:
            portPosition.x += insets.left + borderSpacing + graphOffset.x - (portWidth / 2.0);
            portPosition.y = -portHeight - portOffset;
            portDummy.getPosition().y = -(insets.top + borderSpacing + portOffset + graphOffset.y);
            break;
        
        case EAST:
            portPosition.x = graphSize.x + insets.left + insets.right + 2 * borderSpacing + portOffset;
            portPosition.y += insets.top + borderSpacing + graphOffset.y - (portHeight / 2.0);
            portDummy.getPosition().x = graphSize.x + insets.right + borderSpacing + portOffset
                    - graphOffset.x;
            break;
        
        case SOUTH:
            portPosition.x += insets.left + borderSpacing + graphOffset.x - (portWidth / 2.0);
            portPosition.y = graphSize.y + insets.top + insets.bottom + 2 * borderSpacing + portOffset;
            portDummy.getPosition().y = graphSize.y + insets.bottom + borderSpacing + portOffset
                    - graphOffset.y;
            break;
        
        case WEST:
            portPosition.x = -portWidth - portOffset;
            portPosition.y += insets.top + borderSpacing + graphOffset.y - (portHeight / 2.0);
            portDummy.getPosition().x = -(insets.left + borderSpacing + portOffset + graphOffset.x);
            break;
        }
        
        return portPosition;
    }
    
}
