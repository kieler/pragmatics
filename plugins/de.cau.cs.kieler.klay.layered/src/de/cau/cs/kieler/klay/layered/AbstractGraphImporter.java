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

import de.cau.cs.kieler.kiml.options.PortConstraints;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;
import de.cau.cs.kieler.klay.layered.p2layers.LayerConstraint;

/**
 * Abstract implementation of {@link IGraphImporter}, containing commonly used functionality.
 * Graph importers should usually subclass this class instead of implementing the interface
 * directly.
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
     * Transforms the given origin object into a layered graph.
     * 
     * @param source the object to transform.
     * @param layeredGraph the layered graph to fill.
     */
    protected abstract void transform(final T source, final LayeredGraph layeredGraph);
    
    /**
     * Returns the object from which the layered graph was created.
     * 
     * @return the origin.
     */
    public T getOrigin() {
        return origin;
    }
    
    /**
     * {@inheritDoc}
     */
    public LayeredGraph getGraph() {
        return graph;
    }
    
    
    ///////////////////////////////////////////////////////////////////////////////
    // External Ports
    
    /**
     * Creates a dummy for an external port.
     * 
     * @param port the port object the dummy will represent.
     * @param portConstraints constraints for external ports.
     * @param portSide the side of the external port.
     * @param incomingEdges number of edges coming into the external port from within the node.
     * @param outgoingEdges number of edges going out of the external port to targets within the node.
     * @return a dummy node representing the external port.
     */
    protected LNode createExternalPortDummy(final Object port, final PortConstraints portConstraints,
            final PortSide portSide, final int incomingEdges, final int outgoingEdges) {
        
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
            dummy.setProperty(Properties.LAYER_CONSTRAINT, LayerConstraint.FIRST_SEPARATE);
            dummyPort.setSide(PortSide.EAST);
            break;
        
        case EAST:
            dummy.setProperty(Properties.LAYER_CONSTRAINT, LayerConstraint.LAST_SEPARATE);
            break;
        
        case NORTH:
            // TODO: Set in-layer constraints.
            break;
        
        case SOUTH:
            // TODO: Set in-layer constraints.
            break;
        }
        
        // TODO: In case of FIXED_RATIO or FIXED_POSITION, store appropriate ratio or position values
        
        
        return dummy;
    }
    
}
