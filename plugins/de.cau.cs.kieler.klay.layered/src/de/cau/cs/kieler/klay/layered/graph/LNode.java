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

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.core.util.FilteredIterator;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.kiml.options.PortType;

/**
 * A node in a layered graph.
 *
 * @author msp
 */
public class LNode extends LGraphElement {
    
    /** the owning layer. */
    private Layer owner;
    /** the current position of the node. */
    private KVector pos = new KVector();
    /** the size of the node. */
    private KVector size = new KVector();
    /** the ports of the node. */
    private List<LPort> ports = new LinkedList<LPort>();
    /** name of the node. */
    private String name;

    /**
     * Creates a layer node.
     * 
     * @param thename name of the node, or {@code null}
     */
    public LNode(final String thename) {
        this.name = thename;
    }
    
    /**
     * Creates a layer node.
     */
    public LNode() {
        this(null);
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
     * Returns the name of the node.
     * 
     * @return the name, or {@code null}
     */
    public String getName() {
        return name;
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
     * Returns the current position of the node.
     * 
     * @return the position
     */
    public KVector getPos() {
        return pos;
    }

    /**
     * Returns the current size of the node.
     * 
     * @return the size
     */
    public KVector getSize() {
        return size;
    }

    /**
     * Returns the list of ports of this node. The order of ports in this list
     * corresponds to the order in which they are drawn, assuming clockwise order,
     * starting with the north side. That order is potentially affected during
     * the crossing minimization phase.
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
    
    /**
     * Sort the ports of this node using relative position values.
     * 
     * @param position an array of position values; the identifier of each port of this node must be
     *     inside the range of the {@code position} array, since it is used as index
     */
    public void sortPorts(final float[] position) {
        Collections.sort(ports, new Comparator<LPort>() {
            public int compare(final LPort port1, final LPort port2) {
                PortSide side1 = port1.getSide();
                PortType type1 = port1.getType();
                PortSide side2 = port2.getSide();
                PortType type2 = port2.getType();
                if (side1 != side2) {
                    // sort according to the node side 
                    return side1.ordinal() - side2.ordinal();
                } else if (type1 != type2) {
                    // north side: first inputs, then outputs; other sides: reverse
                    if (side1 == PortSide.NORTH) {
                        return type1.ordinal() - type2.ordinal();
                    } else {
                        return type2.ordinal() - type1.ordinal();
                    }
                } else {
                    float pos1 = position[port1.id], pos2 = position[port2.id];
                    // input ports are counter-clockwise, output ports are clockwise
                    if (type1 == PortType.INPUT) {
                        return Float.compare(pos2, pos1);
                    } else {
                        return Float.compare(pos1, pos2);
                    }
                }
            }
        });
    }

}
