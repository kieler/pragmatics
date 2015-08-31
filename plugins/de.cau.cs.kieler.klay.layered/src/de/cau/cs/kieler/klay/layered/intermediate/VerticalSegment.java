/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2015 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klay.layered.intermediate;

import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.core.math.KVectorChain;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.klay.layered.graph.LEdge;

/**
 * Represents a vertical segment on a single {@link LEdge} that is merged with intersecting
 * {@link VerticalSegment}s.
 * 
 * @see CLEdge
 * @author dag
 */
public final class VerticalSegment {
    // SUPPRESS CHECKSTYLE NEXT 12 VisibilityModifier
    /** the node a north/south segment is connected to, otherwise null. */
    public CNode parentNode;
    /** offset to the parent node. */
    public double relativePosition;
    /** the bend points defining this segment. */
    public KVector bend1, bend2;
    /** the junction points of the original {@link LEdge}, that are between the bend points. */
    public KVectorChain junctionPoints = new KVectorChain();
    /** coordinates. */
    public double x, y1, y2;
    /** the parent {@link LEdge}. */
    public LEdge lEdge;

    /**
     * Creates a new instance setting all fields.
     * 
     * @param bend1
     *            first bend point
     * @param bend2
     *            second bend point
     * @param cNode
     *            parent node
     * @param lEdge
     *            parent edge
     */
    VerticalSegment(final KVector bend1, final KVector bend2, final CNode cNode, final LEdge lEdge) {
        this.bend1 = bend1;
        this.bend2 = bend2;

        // setting coordinates according to bend points
        if (bend1.y < bend2.y) {
            x = bend1.x;
            y1 = bend1.y;
            y2 = bend2.y;
        } else {
            x = bend2.x;
            y1 = bend2.y;
            y2 = bend1.y;
        }

        // adding junction points
        KVectorChain inJPs = lEdge.getProperty(LayoutOptions.JUNCTION_POINTS);
        for (KVector jp : inJPs) {
            if (Comp.eq(jp.x, bend1.x)) {
                junctionPoints.add(jp);
            }
        }

        parentNode = cNode;

        // only north/south segments have a parent node
        if (parentNode != null) {
            relativePosition = x - cNode.hitbox.x;
        }

        this.lEdge = lEdge;
    }

    /**
     * Checks whether this segment intersects another.
     * 
     * @param o
     *            the other segment
     * @return {@code true} if the segments intersect
     */
    public boolean intersects(final VerticalSegment o) {
        return Comp.eq(this.x, o.x) && Comp.ge(this.y2, o.y1) && Comp.le(this.y1, o.y2);
    }

    @Override
    public String toString() {
        return "\nb1: " + bend1 + " \tb2: " + bend2 + " \t" + junctionPoints;
    }
}
