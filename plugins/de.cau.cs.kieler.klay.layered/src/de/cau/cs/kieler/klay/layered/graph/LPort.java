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

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.google.common.collect.Iterables;

import de.cau.cs.kieler.core.util.ICondition;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.klay.layered.properties.PortType;

/**
 * A port in a layered graph. The position of the port is relative to the upper
 * left corner of the containing node. Contrary to the usual customs, a port's
 * position denotes its center point, not its upper left corner. A port has
 * only one list of incident edges; for input ports this list must only contain
 * incoming edges, while for output ports it must only contain outgoing edges.
 * Usually all ports are required to be either input ports or output ports.
 * 
 * <p>Port must be used even if the original graph does not reveal them. In this
 * case each edge has dedicated source and target ports, which are used to
 * determine the points where the edge touches the source and target nodes.</p>
 *
 * @author msp
 */
public class LPort extends LShape {

    /** the owning node. */
    private LNode owner;
    /** the port side. */
    private PortSide side = PortSide.UNDEFINED;
    /** this port's labels. */
    private List<LLabel> labels = new LinkedList<LLabel>();
    /** the edges going into the port. */
    private List<LEdge> incomingEdges = new LinkedList<LEdge>();
    /** the edges going out of the port. */
    private List<LEdge> outgoingEdges = new LinkedList<LEdge>();
    
    /**
     * A condition that checks the type of ports. If a port has incoming edges, it is considered
     * an input port. If a port has outgoing edges, it is considered an output port. A port may
     * be both, input and output port, or none.
     */
    public static class TypeCondition implements ICondition<LPort> {
        private PortType condType;
        
        /**
         * Creates a type condition.
         * @param thetype the type of port to admit
         */
        public TypeCondition(final PortType thetype) {
            this.condType = thetype;
        }
        
        /**
         * {@inheritDoc}
         */
        public boolean evaluate(final LPort object) {
            switch (condType) {
            case INPUT:
                return !object.incomingEdges.isEmpty();
            
            case OUTPUT:
                return !object.outgoingEdges.isEmpty();
            
            default:
                return true;
            }
        }
    }
    
    /** A condition that checks the side of ports. */
    public static class SideCondition implements ICondition<LPort> {
        private PortSide condSide;
        /**
         * Creates a side condition.
         * @param theside the side of port to admit
         */
        public SideCondition(final PortSide theside) {
            this.condSide = theside;
        }
        
        /**
         * {@inheritDoc}
         */
        public boolean evaluate(final LPort object) {
            return object.side == condSide;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        String text = getName();
        if (text == null) {
            return "p_" + id;
        } else {
            return "p_" + text;
        }
    }

    /**
     * Returns the node that owns this port.
     * 
     * @return the owning node
     */
    public LNode getNode() {
        return owner;
    }

    /**
     * Sets the owning node and adds itself to the node's list of ports.
     * If the port was previously in another node, it is removed from that
     * node's list of ports. Be careful not to use this method while
     * iterating through the ports list of the old node nor of the new node,
     * since that could lead to {@link java.util.ConcurrentModificationException}s.
     * 
     * @param node the owner to set
     */
    public void setNode(final LNode node) {
        if (owner != null) {
            owner.getPorts().remove(this);
        }
        this.owner = node;
        if (owner != null) {
            owner.getPorts().add(this);
        }
    }

    /**
     * Returns the node side on which the port is drawn.
     * 
     * @return the side
     */
    public PortSide getSide() {
        return side;
    }

    /**
     * Sets the node side on which the port is drawn.
     * 
     * @param theside the side to set
     */
    public void setSide(final PortSide theside) {
        this.side = theside;
    }
    
    /**
     * Returns this port's labels.
     * 
     * @return this port's labels.
     */
    public List<LLabel> getLabels() {
        return labels;
    }
    
    /**
     * Returns the name of the port.
     * 
     * @return the name, or {@code null}
     */
    public String getName() {
        if (!labels.isEmpty()) {
            return labels.get(0).getText();
        }
        return null;
    }
    
    /**
     * Returns this port's degree, that is, the number of edges connected to it.
     * 
     * @return the number of edges connected to this port.
     */
    public int getDegree() {
        return incomingEdges.size() + outgoingEdges.size();
    }
    
    /**
     * Returns the number of incoming edges minus the number of outgoing edges. This
     * is the net flow of the port.
     * 
     * @return the port's net flow.
     */
    public int getNetFlow() {
        return incomingEdges.size() - outgoingEdges.size();
    }

    /**
     * Returns the list of edges going into this port.
     * 
     * @return the incoming edges
     */
    public List<LEdge> getIncomingEdges() {
        return incomingEdges;
    }

    /**
     * Returns the list of edges going out of this port.
     * 
     * @return the outgoing edges
     */
    public List<LEdge> getOutgoingEdges() {
        return outgoingEdges;
    }
    
    /**
     * Returns an iterable over all connected edges, both incoming and outgoing.
     * 
     * @return an iterable over all connected edges.
     */
    public Iterable<LEdge> getConnectedEdges() {
        return Iterables.concat(incomingEdges, outgoingEdges);
    }
    
    /**
     * Returns an iterable over all the port's predecessor ports.
     * 
     * @return an iterable over all predecessor ports.
     */
    public Iterable<LPort> getPredecessorPorts() {
        return new Iterable<LPort>() {
            public Iterator<LPort> iterator() {
                final Iterator<LEdge> edgesIter = incomingEdges.iterator();
                
                return new Iterator<LPort>() {
                    public boolean hasNext() {
                        return edgesIter.hasNext();
                    }
                    public LPort next() {
                        return edgesIter.next().getSource();
                    }
                    public void remove() {
                        edgesIter.remove();
                    }
                };
            }
            
        };
    }
    
    /**
     * Returns an iterable over all the port's successor ports.
     * 
     * @return an iterable over all successor ports.
     */
    public Iterable<LPort> getSuccessorPorts() {
        return new Iterable<LPort>() {
            public Iterator<LPort> iterator() {
                final Iterator<LEdge> edgesIter = outgoingEdges.iterator();
                
                return new Iterator<LPort>() {
                    public boolean hasNext() {
                        return edgesIter.hasNext();
                    }
                    public LPort next() {
                        return edgesIter.next().getTarget();
                    }
                    public void remove() {
                        edgesIter.remove();
                    }
                };
            }
            
        };
    }

    /**
     * Returns an iterable over all connected ports, both predecessors and successors.
     * 
     * @return an iterable over the connected ports
     */
    public Iterable<LPort> getConnectedPorts() {
        return Iterables.concat(getPredecessorPorts(), getSuccessorPorts());
    }
    
    /**
     * Returns the index of the port in the containing node's list of ports. Note
     * that this method has linear running time in the number of ports, so use
     * it with caution.
     * 
     * @return the index of this port, or -1 if the port has no owner
     */
    public int getIndex() {
        if (owner == null) {
            return -1;
        } else {
            return owner.getPorts().indexOf(this);
        }
    }
    
}
