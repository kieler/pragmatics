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
package de.cau.cs.kieler.kiml.util;

import java.util.Comparator;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;

/**
 * A helper class to compute hash values from a layout graph.
 *
 * @author msp
 */
public class KGraphHash implements Comparable<KGraphHash>, Comparator<KNode> {
    
    /** the top level node of the layout graph. */
    private KNode topNode;
    /** the cached hashCode. */
    private int hashCode;
    
    /**
     * Creates a KGraph hash instance.
     * 
     * @param thetopNode the top level node of the layout graph
     */
    public KGraphHash(final KNode thetopNode) {
        this.topNode = thetopNode;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object object) {
        return (object instanceof KGraphHash) && object.hashCode() == this.hashCode();
    }

    /**
     * {@inheritDoc}
     */
    public int compare(final KNode node1, final KNode node2) {
        return new KGraphHash(node1).hashCode() - new KGraphHash(node2).hashCode();
    }

    /**
     * {@inheritDoc}
     */
    public int compareTo(final KGraphHash other) {
        return this.hashCode() - other.hashCode();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        if (hashCode == 0) {
            hashCode = hashCode(topNode);
        }
        return hashCode;
    }
    
    /**
     * Calculate hash code for a node.
     * 
     * @param node a node
     * @return the node's hash code
     */
    private int hashCode(final KNode node) {
        int hash = 0;
        if (node != null) {
            hash = hashCode(node.getLabel()) ^ hashCode(node.getData(KShapeLayout.class));
            for (KPort port : node.getPorts()) {
                hash += hashCode(port);
            }
            for (KNode child : node.getChildren()) {
                hash += hashCode(child);
            }
            for (KEdge edge : node.getOutgoingEdges()) {
                hash += hashCode(edge);
            }
        }
        return hash;
    }
    
    /**
     * Calculate hash code for a port.
     * 
     * @param port a port
     * @return the port's hash code
     */
    private int hashCode(final KPort port) {
        if (port != null) {
            return hashCode(port.getLabel()) ^ hashCode(port.getData(KShapeLayout.class));
        }
        return 0;
    }
    
    /**
     * Calculate hash code for an edge.
     * 
     * @param edge an edge
     * @return the edge's hash code
     */
    private int hashCode(final KEdge edge) {
        int hash = hashCode(edge.getData(KEdgeLayout.class));
        for (KLabel label : edge.getLabels()) {
            hash ^= hashCode(label);
        }
        return hash;
    }
    
    /**
     * Calculate hash code for an edge layout.
     * 
     * @param edgeLayout an edge layout
     * @return the edge layout's hash code
     */
    private int hashCode(final KEdgeLayout edgeLayout) {
        int hash = 0;
        if (edgeLayout != null) {
            hash = hashCode(edgeLayout.getSourcePoint()) ^ hashCode(edgeLayout.getTargetPoint());
            for (KPoint bendPoint : edgeLayout.getBendPoints()) {
                hash ^= hashCode(bendPoint);
            }
        }
        return hash;
    }
    
    // CHECKSTYLEOFF MagicNumber
    /** shift base for a quarter shift. */
    private static final int SHIFT_ONE = Integer.SIZE / 4;
    /** shift base for a half shift. */
    private static final int SHIFT_TWO = Integer.SIZE / 2;
    /** shift base for a three quarters shift. */
    private static final int SHIFT_THREE = Integer.SIZE * 3 / 4;
    // CHECKSTYLEON MagicNumber
    
    /**
     * Calculate hash code for a point.
     * 
     * @param point a point
     * @return the point's hash code
     */
    private int hashCode(final KPoint point) {
        if (point != null) {
            return Math.round(point.getX()) | (Math.round(point.getY()) << SHIFT_TWO);
        }
        return 0;
    }
    
    /**
     * Calculate hash code for a label.
     * 
     * @param label a label
     * @return the label's hash code
     */
    private int hashCode(final KLabel label) {
        int hash = 0;
        if (label != null) {
            if (label.getText() != null) {
                hash = label.getText().hashCode();
            }
            hash ^= hashCode(label.getData(KShapeLayout.class));
        }
        return hash;
    }
    
    /**
     * Calculate hash code for a shape layout.
     * 
     * @param shapeLayout a shape layout
     * @return the shape layout's hash code
     */
    private int hashCode(final KShapeLayout shapeLayout) {
        if (shapeLayout != null) {
            return Math.round(shapeLayout.getXpos())
                    | (Math.round(shapeLayout.getWidth()) << SHIFT_ONE)
                    | (Math.round(shapeLayout.getYpos()) << SHIFT_TWO)
                    | (Math.round(shapeLayout.getHeight()) << SHIFT_THREE);
        }
        return 0;
    }

}
