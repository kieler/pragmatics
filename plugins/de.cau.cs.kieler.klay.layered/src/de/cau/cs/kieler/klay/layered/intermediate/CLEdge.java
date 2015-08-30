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

import java.util.Set;

import com.google.common.collect.Sets;

import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.core.math.KVectorChain;
import de.cau.cs.kieler.kiml.util.nodespacing.Rectangle;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LGraph;

/**
 * @author dag
 *
 */
public final class CLEdge extends CNode {
    // specify particular vertical edge segments and affected junction points
    public KVectorChain bends;
    public KVectorChain juctionPoints;
    public Set<LEdge> originalLEdges = Sets.newLinkedHashSet();

    public CLEdge(final VerticalSegment vSeg, final LGraph layeredGraph) {
        super(layeredGraph);
        isNode = false;
        bends = new KVectorChain(vSeg.bend1, vSeg.bend2);
        juctionPoints = new KVectorChain(vSeg.junctionPoints);
        hitbox = new Rectangle(vSeg.x, vSeg.y1, 0, vSeg.y2 - vSeg.y1);
        parentNode = vSeg.parentNode;
        cGroupOffset = vSeg.relativePosition;
        if (vSeg.lEdge != null)
            originalLEdges.add(vSeg.lEdge);
    }

    public void addSegment(final VerticalSegment vSeg) {
        bends.addAll(vSeg.bend1, vSeg.bend2);
        juctionPoints.addAll(vSeg.junctionPoints);
        double newY1 = Math.min(hitbox.y, vSeg.y1);
        double newY2 = Math.max(hitbox.y + hitbox.height, vSeg.y2);
        hitbox.setRect(vSeg.x, newY1, 0, newY2 - newY1);
        if (vSeg.lEdge != null)
            originalLEdges.add(vSeg.lEdge);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setElementPosition() {
        for (KVector b : bends) {
            b.x = hitbox.x;
        }
        for (KVector j : juctionPoints) {
            j.x = hitbox.x;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getElementPosition() {
        return bends.getFirst().x;
    }
}
