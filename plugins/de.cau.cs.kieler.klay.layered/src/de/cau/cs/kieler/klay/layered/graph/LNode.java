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
import de.cau.cs.kieler.kiml.layout.options.PortSide;
import de.cau.cs.kieler.kiml.layout.options.PortType;

/**
 * A node in a layered graph.
 *
 * @author msp
 */
public class LNode implements Comparable<LNode> {
    
    /** Definition of node types used in the layered approach. */
    public enum Type {
        /** a normal node is created from a node of the original graph. */
        NORMAL,
        /** a dummy node created to split a long edge. */
        LONG_EDGE;
    }
    
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
    /** name of the node. */
    private String name;
    /** type of the node. */
    private Type type;
    
    // CHECKSTYLEOFF VisibilityModifier
    /** Identifier value, may be arbitrarily used by algorithms. */
    public int id;
    // CHECKSTYLEON VisibilityModifier

    /**
     * Creates a layer node.
     * 
     * @param theorigin the original object for the node, or {@code null}
     * @param thename name of the node, or {@code null}
     * @param thetype type of the node
     */
    public LNode(final Object theorigin, final String thename, final Type thetype) {
        this.origin = theorigin;
        this.name = thename;
        this.type = thetype;
    }
    
    /**
     * Creates a layer node.
     * 
     * @param theorigin the original object for the node, or {@code null}
     * @param thename name of the node, or {@code null}
     */
    public LNode(final Object theorigin, final String thename) {
        this(theorigin, thename, Type.NORMAL);
    }
    
    /**
     * Creates a layer node.
     * 
     * @param theorigin the original object for the node, or {@code null}
     */
    public LNode(final Object theorigin) {
        this(theorigin, null, Type.NORMAL);
    }
    
    /**
     * Creates a layer node.
     */
    public LNode() {
        this(null, null, Type.NORMAL);
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
     * {@inheritDoc}
     */
    public int compareTo(final LNode other) {
        return other.id - this.id;
    }

    /**
     * @return the owning layer
     */
    public Layer getLayer() {
        return owner;
    }

    /**
     * Sets the owning layer and adds itself to the layer's list of nodes.
     * If the node was previously in another layer, it is removed from
     * that layer's list of nodes.
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
     * @return the type
     */
    public Type getType() {
        return type;
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
     * @return the index of this node
     */
    public int getIndex() {
        if (owner == null) {
            return -1;
        } else {
            return owner.getNodes().indexOf(this);
        }
    }

}
