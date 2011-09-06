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

import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.core.math.KVectorChain;
import de.cau.cs.kieler.klay.layered.properties.Properties;

/**
 * An edge in a layered graph. Edges may only be connected to ports of a node, which represent the
 * point where the edge touches the node.
 * 
 * @author msp
 */
public class LEdge extends LGraphElement {

    /** the bend points. */
    private KVectorChain bendPoints = new KVectorChain();
    /** the source and target ports. */
    private LPort source, target;
    /** labels assigned to this edge. */
    private List<LLabel> labels = new LinkedList<LLabel>();

    /**
     * {@inheritDoc}
     */
    public String toString() {
        if (source != null && target != null) {
            return source.getNode() + "(" + source + ")->" + target.getNode() + "(" + target + ")";
        } else {
            return "e_" + hashCode();
        }
    }
    
    /**
     * Reverses the edge, its bendpoints and properly negates the {@code REVERSED} property. (an
     * edge that was marked as being reversed is then unmarked, and the other way around) This
     * does not change any properties on the connected ports.
     */
    public void reverse() {
        LPort oldSource = getSource();
        LPort oldTarget = getTarget();
        
        setSource(null);
        setTarget(null);
        setSource(oldTarget);
        setTarget(oldSource);
        
        boolean reversed = getProperty(Properties.REVERSED);
        setProperty(Properties.REVERSED, !reversed);
        
        bendPoints = KVectorChain.reverse(bendPoints);
    }

    /**
     * Returns the source port.
     * 
     * @return the source port
     */
    public LPort getSource() {
        return source;
    }

    /**
     * Sets the source port of this edge and adds itself to the port's list of edges. If the edge
     * previously had another source, it is removed from the original port's list of edges. Be
     * careful not to use this method while iterating through the edges list of the old port nor of
     * the new port, since that could lead to {@link java.util.ConcurrentModificationException}s.
     * 
     * @param source
     *            the source port to set
     */
    public void setSource(final LPort source) {
        if (this.source != null) {
            this.source.getOutgoingEdges().remove(this);
        }
        
        this.source = source;
        
        if (this.source != null) {
            this.source.getOutgoingEdges().add(this);
        }
    }

    /**
     * Returns the target port.
     * 
     * @return the target port
     */
    public LPort getTarget() {
        return target;
    }

    /**
     * Sets the target port of this edge and adds itself to the port's list of edges. If the edge
     * previously had another target, it is removed from the original port's list of edges. Be
     * careful not to use this method while iterating through the edges list of the old port nor of
     * the new port, since that could lead to {@link java.util.ConcurrentModificationException}s.
     * 
     * @param target
     *            the target port to set
     */
    public void setTarget(final LPort target) {
        if (this.target != null) {
            this.target.getIncomingEdges().remove(this);
        }
        
        this.target = target;
        
        if (this.target != null) {
            this.target.getIncomingEdges().add(this);
        }
    }

    /**
     * Returns the list of bend points, which is initially empty.
     * 
     * @return the bend points
     */
    public KVectorChain getBendPoints() {
        return bendPoints;
    }
    
    /**
     * Return a new vector representing the source point of the edge, which has the same
     * reference point as the bend points.
     * 
     * @return the source point
     */
    public KVector getSourcePoint() {
        return source.getNode().getPosition().sumCreate(source.getPosition());
    }
    
    /**
     * Return a new vector representing the target point of the edge, which has the same
     * reference point as the bend points.
     * 
     * @return the target point.
     */
    public KVector getTargetPoint() {
        return target.getNode().getPosition().sumCreate(target.getPosition());
    }

    /**
     * 
     * @return all labels
     */
    public List<LLabel> getLabels() {
        return this.labels;
    }

}
