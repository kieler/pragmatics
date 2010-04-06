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
import java.util.ListIterator;

import de.cau.cs.kieler.core.util.FilteredIterator;
import de.cau.cs.kieler.kiml.layout.options.PortSide;
import de.cau.cs.kieler.kiml.layout.options.PortType;

/**
 * A node in a layered graph.
 *
 * @author msp
 */
public class LNode {
    
    /** the owning layer. */
    private Layer owner;
    /** the current position of the node. */
    private Coord pos = new Coord();
    /** the size of the node. */
    private Coord size = new Coord();
    /** the original object from which this node was created. */
    private Object origin;
    /** the ports of the node. */
    private List<LPort> ports = new LinkedList<LPort>();
    
    /**
     * Creates a layer node.
     * 
     * @param theorigin the original object for the node
     */
    private LNode(final Object theorigin) {
        this.origin = theorigin;
    }

    /**
     * @return the owner
     */
    public Layer getOwner() {
        return owner;
    }

    /**
     * Sets the owning layer and adds itself to the layer's list of nodes.
     * If the node was previously in another layer, it is removed from
     * that layer's list of nodes.
     * 
     * @param theowner the owner to set
     */
    public void setOwner(final Layer theowner) {
        if (owner != null) {
            owner.getNodes().remove(this);
        }
        this.owner = theowner;
        owner.getNodes().add(this);
    }

    /**
     * @return the pos
     */
    public Coord getPos() {
        return pos;
    }

    /**
     * @return the size
     */
    public Coord getSize() {
        return size;
    }

    /**
     * @return the origin
     */
    public Object getOrigin() {
        return origin;
    }

    /**
     * @return the ports
     */
    public List<LPort> getPorts() {
        return ports;
    }
    
    /**
     * Returns a list iterator for all ports of given type.
     * 
     * @param type a port type
     * @return an iterator for the ports of given type
     */
    public ListIterator<LPort> portsIterator(final PortType type) {
        return new FilteredIterator<LPort>(ports.listIterator(), new LPort.TypeCondition(type));
    }
    
    /**
     * Returns a list iterator for all ports of given side.
     * 
     * @param side a port side
     * @return an iterator for the ports of given side
     */
    public ListIterator<LPort> portsIterator(final PortSide side) {
        return new FilteredIterator<LPort>(ports.listIterator(), new LPort.SideCondition(side));
    }
    
    /**
     * @return the index of this node
     */
    public int getIndex() {
        return owner.getNodes().indexOf(this);
    }

}
