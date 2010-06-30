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
package de.cau.cs.kieler.klay.layered.graph;

import java.util.LinkedList;
import java.util.List;

import de.cau.cs.kieler.core.util.FilteredIterator;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.kiml.options.PortType;
import de.cau.cs.kieler.klay.layered.impl.LinearSegmentsNodePlacer.Region;

/**
 * A node in a layered graph.
 *
 * @author msp
 */
public class LNode extends LGraphElement {
    
    /** the owning layer. */
    private Layer owner;
    /** the owning region. */
    private Region region;
    /** the current position of the node. */
    private Coord pos = new Coord();
    /** the size of the node. */
    private Coord size = new Coord();
    /** the original object from which this node was created. */
    private Object origin;
    /** the ports of the node. */
    private List<LPort> ports = new LinkedList<LPort>();
    /** name of the node. */
    private String name;

    /**
     * Creates a layer node.
     * 
     * @param theorigin the original object for the node, or {@code null}
     * @param thename name of the node, or {@code null}
     */
    public LNode(final Object theorigin, final String thename) {
        this.origin = theorigin;
        this.name = thename;
    }
    
    /**
     * Creates a layer node.
     * 
     * @param theorigin the original object for the node, or {@code null}
     */
    public LNode(final Object theorigin) {
        this(theorigin, null);
    }
    
    /**
     * Creates a layer node.
     */
    public LNode() {
        this(null, null);
    }

    /**
     * {@inheritDoc}
     */
    public String toString() {
        if (name == null) {
            return "n_" + id;
        } else {
            return "n_" + name;
        }
    }

    /**
     * Returns the layer that owns this node.
     * 
     * @return the owning layer
     */
    public Layer getLayer() {
        return owner;
    }

    /**
     * Sets the owning layer and adds itself to the layer's list of nodes.
     * If the node was previously in another layer, it is removed from
     * that layer's list of nodes. Be careful not to use this method while
     * iterating through the nodes list of the old layer nor of the new layer,
     * since that could lead to {@link java.util.ConcurrentModificationException}s.
     * 
     * @param layer the owner to set
     */
    public void setLayer(final Layer layer) {
        if (owner != null) {
            owner.getNodes().remove(this);
        }
        this.owner = layer;
        owner.getNodes().add(this);
    }

    /**
     * @param theRegion the theRegion to set
     */
    public void setRegion(final Region theRegion) {
        this.region = theRegion;
    }

    /**
     * @return the region
     */
    public Region getRegion() {
        return region;
    }

    /**
     * Returns the current position of the node.
     * 
     * @return the position
     */
    public Coord getPos() {
        return pos;
    }

    /**
     * Returns the current size of the node.
     * 
     * @return the size
     */
    public Coord getSize() {
        return size;
    }

    /**
     * Returns the original object from which the node was created.
     * 
     * @return the original object
     */
    public Object getOrigin() {
        return origin;
    }

    /**
     * Returns the list of ports of this node. The order of ports in this list
     * corresponds to the order in which they are drawn: first it contains
     * the input ports in counter-clockwise order, starting with the north side,
     * then the output ports in clockwise order, starting with the north side.
     * That order is potentially affected during the crossing minimization phase.
     * 
     * @return the ports
     */
    public List<LPort> getPorts() {
        return ports;
    }
    
    /**
     * Returns an iterable for all ports of given type.
     * 
     * @param portType a port type
     * @return an iterable for the ports of given type
     */
    public Iterable<LPort> getPorts(final PortType portType) {
        return new FilteredIterator.Iterable<LPort>(ports,
                new LPort.TypeCondition(portType));
    }
    
    /**
     * Returns an iterable for all ports of given side.
     * 
     * @param side a port side
     * @return an iterable for the ports of given side
     */
    public Iterable<LPort> getPorts(final PortSide side) {
        return new FilteredIterator.Iterable<LPort>(ports,
                new LPort.SideCondition(side));
    }
    
    /**
     * Returns the index of the node in the containing layer's list of nodes.
     * Note that this method has linear running time in the number of nodes,
     * so use it with caution.
     * 
     * @return the index of this node, or -1 if the node has no owner
     */
    public int getIndex() {
        if (owner == null) {
            return -1;
        } else {
            return owner.getNodes().indexOf(this);
        }
    }

}
