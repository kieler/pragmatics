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
import de.cau.cs.kieler.kiml.options.PortConstraints;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;

/**
 * Abstract implementation of {@link IGraphImporter}, containing commonly used functionality.
 * Graph importers should usually subclass this class instead of implementing the interface
 * directly.
 * 
 * <p>Subclasses must implement {@link #transform(Object, LayeredGraph)} to transform a
 * graph into a layered graph.</p>
 * 
 * <p>When a graph importer supports external ports, it must create dummies for those ports by calling
 * {@link #createExternalPortDummy(Object, PortConstraints, PortSide, int, int, KInsets, KVector)}.
 * The correct position of those ports can later be retrieved by calling TODO.</p>
 * 
 * @param <T> the type of graph that this importer can transform into a layered graph.
 * @author cds
 */
public abstract class AbstractGraphImporter<T> implements IGraphImporter {
    
    /**
     * The object from which the layered graph was created.
     */
    private T origin = null;
    
    /**
     * The converted layered graph.
     */
    private LayeredGraph graph = null;
    
    
    /**
     * Creates a new importer, transforming the given object into a layered graph.
     * 
     * @param origin the object to be transformed.
     */
    public AbstractGraphImporter(final T origin) {
        this.origin = origin;
        
        graph = new LayeredGraph();
        graph.setProperty(Properties.ORIGIN, origin);
        
        transform(origin, graph);
    }
    
    
    /**
     * Returns the object from which the layered graph was created.
     * 
     * @return the origin.
     */
    public final T getOrigin() {
        return origin;
    }
    
    /**
     * {@inheritDoc}
     */
    public final LayeredGraph getGraph() {
        return graph;
    }
    
    
    ///////////////////////////////////////////////////////////////////////////////
    // Graph Transformation
    
    /**
     * Transforms the given origin object into a layered graph.
     * 
     * @param source the object to transform.
     * @param layeredGraph the layered graph to fill.
     */
    protected abstract void transform(final T source, final LayeredGraph layeredGraph);
    
    
    ///////////////////////////////////////////////////////////////////////////////
    // Layout Application
    
    /**
     * {@inheritDoc}
     */
    public final void applyLayout() {
        // Set positions for external ports
        
        // Apply the layout
        applyLayout(getGraph(), getOrigin());
    }
    
    /**
     * Applies the layout data to the original graph.
     * 
     * @param layeredGraph the laid out layered graph.
     * @param target the original graph to apply the layout to.
     */
    protected abstract void applyLayout(final LayeredGraph layeredGraph, final T target);
    
    
    ///////////////////////////////////////////////////////////////////////////////
    // External Ports
    
    /**
     * Creates a dummy for an external port. The dummy will have just one port. The port is on
     * the eastern side for western external ports, and on the western side for all other ports.
     * 
     * @param port the port object the dummy will represent.
     * @param portConstraints constraints for external ports.
     * @param portSide the side of the external port.
     * @param incomingEdges number of edges coming into the external port from within the node.
     * @param outgoingEdges number of edges going out of the external port to targets within the node.
     * @param portNodeSize The size of the node the port belongs to. Only relevant if the port
     *                     constraints are {@code FIXED_RATIO}.
     * @param portPosition the current port position. Only relevant if the port constraints are
     *                     {@code FIXED_ORDER}, {@code FIXED_RATIO} or {@code FIXED_POSITION}.
     * @return a dummy node representing the external port.
     */
    protected LNode createExternalPortDummy(final Object port, final PortConstraints portConstraints,
            final PortSide portSide, final int incomingEdges, final int outgoingEdges,
            final KVector portNodeSize, final KVector portPosition) {
        
        PortSide finalPortSide = portSide;
        
        // Create the dummy with one port
        LNode dummy = new LNode();
        dummy.setProperty(Properties.NODE_TYPE, Properties.NodeType.EXTERNAL_PORT);
        dummy.setProperty(Properties.ORIGIN, port);
        dummy.setProperty(Properties.PORT_CONS, PortConstraints.FIXED_POS);
        
        LPort dummyPort = new LPort();
        dummyPort.setSide(PortSide.WEST);
        dummyPort.setNode(dummy);
        
        // If the port constraints are free, we need to determine where to put the dummy (and its port)
        if (portConstraints == PortConstraints.FREE || portConstraints == PortConstraints.UNDEFINED) {
            if (incomingEdges > outgoingEdges) {
                finalPortSide = PortSide.EAST;
            } else {
                finalPortSide = PortSide.WEST;
            }
        }
        
        // With the port side at hand, set the necessary properties
        switch (finalPortSide) {
        case WEST:
            dummy.setProperty(Properties.LAYER_CONSTRAINT, Properties.LayerConstraint.FIRST_SEPARATE);
            dummyPort.setSide(PortSide.EAST);
            break;
        
        case EAST:
            dummy.setProperty(Properties.LAYER_CONSTRAINT, Properties.LayerConstraint.LAST_SEPARATE);
            break;
        
        case NORTH:
            dummy.setProperty(Properties.IN_LAYER_CONSTRAINT, Properties.InLayerConstraint.TOP);
            break;
        
        case SOUTH:
            dummy.setProperty(Properties.IN_LAYER_CONSTRAINT, Properties.InLayerConstraint.BOTTOM);
            break;
        }
        
        // From FIXED_ORDER onwards, we need to save the port position or ratio
        if (portConstraints.isOrderFixed()) {
            double positionOrRatio = 0;
            
            switch (finalPortSide) {
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
        dummy.setProperty(Properties.EXT_PORT_SIDE, finalPortSide);
        
        return dummy;
    }
    
}
