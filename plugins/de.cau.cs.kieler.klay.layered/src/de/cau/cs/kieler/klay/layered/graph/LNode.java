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

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.google.common.collect.Iterables;

import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.core.util.CompoundCondition;
import de.cau.cs.kieler.core.util.FilteredIterator;
import de.cau.cs.kieler.core.util.ICondition;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.kiml.options.PortType;
import de.cau.cs.kieler.klay.layered.properties.Properties;

/**
 * A node in a layered graph.
 *
 * @author msp
 */
public class LNode extends LShape {
    
    /** the owning layer. */
    private Layer owner;
    /** the ports of the node. */
    private List<LPort> ports = new LinkedList<LPort>();
    /** this node's label, if any. */
    private LLabel label = null;
    /** this node's insets. */
    private LInsets.Double margin = new LInsets.Double();

    /**
     * {@inheritDoc}
     */
    public String toString() {
        String name = getName();
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
        if (label != null) {
            return label.getText();
        }
        return null;
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
        
        if (owner != null) {
            owner.getNodes().add(this);
        }
    }
    
    /**
     * Sets the owning layer and adds itself to the layer's list of nodes at the
     * specified position. If the node was previously in another layer, it is
     * removed from that layer's list of nodes. Be careful not to use this method
     * while iterating through the nodes list of the old layer nor of the new layer,
     * since that could lead to {@link java.util.ConcurrentModificationException}s.
     * 
     * @param index where the node should be inserted in the layer. Must be {@code >= 0}
     *              and {@code <= layer.getNodes().size()}.
     * @param layer the owner to set.
     */
    public void setLayer(final int index, final Layer layer) {
        if (layer != null && (index < 0 || index > layer.getNodes().size())) {
            throw new IllegalArgumentException("index must be >= 0 and <= layer node count");
        }
        
        if (owner != null) {
            owner.getNodes().remove(this);
        }
        
        this.owner = layer;
        
        if (owner != null) {
            owner.getNodes().add(index, this);
        }
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
     * Returns an iterable for all ports of a given type and side.
     * 
     * @param portType a port type.
     * @param side a port side.
     * @return an iterable for the ports of the given type and side.
     */
    public Iterable<LPort> getPorts(final PortType portType, final PortSide side) {
        List<ICondition<LPort>> conditions = new ArrayList<ICondition<LPort>>();
        conditions.add(new LPort.TypeCondition(portType));
        conditions.add(new LPort.SideCondition(side));
        
        return new FilteredIterator.Iterable<LPort>(ports, new CompoundCondition<LPort>(conditions));
    }
    
    /**
     * Returns an iterable for all inomcing edges.
     * 
     * @return an iterable for all incoming edges.
     */
    public Iterable<LEdge> getIncomingEdges() {
        List<Iterable<LEdge>> iterables = new LinkedList<Iterable<LEdge>>();
        for (LPort port : ports) {
            iterables.add(port.getIncomingEdges());
        }
        
        return Iterables.concat(iterables);
    }
    
    /**
     * Returns an iterable for all outgoing edges.
     * 
     * @return an iterable for all outgoing edges.
     */
    public Iterable<LEdge> getOutgoingEdges() {
        List<Iterable<LEdge>> iterables = new LinkedList<Iterable<LEdge>>();
        for (LPort port : ports) {
            iterables.add(port.getOutgoingEdges());
        }
        
        return Iterables.concat(iterables);
    }
    
    /**
     * Returns an iterable for all connected edges, both incoming and outgoing.
     * 
     * @return an iterable for all connected edges.
     */
    public Iterable<LEdge> getConnectedEdges() {
        List<Iterable<LEdge>> iterables = new LinkedList<Iterable<LEdge>>();
        for (LPort port : ports) {
            iterables.add(port.getConnectedEdges());
        }
        
        return Iterables.concat(iterables);
    }
    
    /**
     * Sets this node's label.
     * 
     * @param label the new label. May be {@code null}.
     */
    public void setLabel(final LLabel label) {
        this.label = label;
    }
    
    /**
     * Returns this node's label, if any.
     * 
     * @return this node's label.
     */
    public LLabel getLabel() {
        return label;
    }
    
    /**
     * Returns the node's margin. The margin is the space around the node that is to be reserved
     * for ports and labels.
     * 
     * <p>The margin is not automatically updated. Rather, the margin has to be calculated once
     * the port and label positions are fixed. Usually this is right before the node placement
     * starts.</p>
     *  
     * @return the node's margin. May be modified.
     */
    public LInsets.Double getMargin() {
        return margin;
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
     * Converts the position of this node from coordinates relative to the hierarchical node
     * border to coordinates relative to that node's content area. The content area is the
     * hierarchical node minus insets minus border spacing minus offset.
     * 
     * @param horizontal if {@code true}, the x coordinate will be translated.
     * @param vertical if {@code true}, the y coordinate will be translated.
     * @throws IllegalStateException if the node is not assigned to a layer in a layered graph.
     */
    public void borderToContentAreaCoordinates(final boolean horizontal, final boolean vertical) {
        if (owner == null || owner.getGraph() == null) {
            throw new IllegalStateException("node is not assigned to a layer in a graph.");
        }
        
        LayeredGraph graph = owner.getGraph();
        
        LInsets.Double insets = graph.getInsets();
        float borderSpacing = graph.getProperty(Properties.BORDER_SPACING);
        KVector offset = graph.getOffset();
        KVector pos = getPosition();
        
        if (horizontal) {
            pos.x = pos.x - insets.left - borderSpacing - offset.x;
        }
        
        if (vertical) {
            pos.y = pos.y - insets.top - borderSpacing - offset.y;
        }
    }

    /**
     * Returns the position of this node's anchor point. This position depends on the graph's
     * {@link de.cau.cs.kieler.klay.layered.properties.Properties#INTERACTIVE_LAYOUT_ANCHOR} property.
     * 
     * @param graph the layered graph.
     * @return the anchor point position.
     */
    public KVector getAnchorPointPosition(final LayeredGraph graph) {
        switch (graph.getProperty(Properties.INTERACTIVE_LAYOUT_ANCHOR)) {
        case CENTER:
            KVector nodePos = getPosition();
            KVector nodeSize = getSize();
            
            return new KVector(nodePos.x + nodeSize.x / 2.0, nodePos.y + nodeSize.y / 2.0);
        
        case TOP_LEFT:
            return new KVector(getPosition());
        
        default:
            // This shouldn't happen.
            return null;
        }
    }

}
