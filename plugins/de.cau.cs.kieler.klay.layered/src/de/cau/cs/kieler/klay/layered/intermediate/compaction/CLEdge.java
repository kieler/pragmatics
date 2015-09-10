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
package de.cau.cs.kieler.klay.layered.intermediate.compaction;

import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.collect.Sets;

import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.core.math.KVectorChain;
import de.cau.cs.kieler.kiml.options.Direction;
import de.cau.cs.kieler.kiml.util.nodespacing.Rectangle;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.properties.InternalProperties;
import de.cau.cs.kieler.klay.layered.properties.Properties;

/**
 * Representation of a vertical {@link LEdge} segment in the constraint graph.
 * 
 * @see CNode
 * 
 * @author dag
 */
public final class CLEdge extends CNode {
    // SUPPRESS CHECKSTYLE NEXT 6 VisibilityModifier
    /** specifies the bend points belonging to this vertical edge segment. */
    public KVectorChain bends;
    /** junction points affected by manipulation of this segment. */
    public KVectorChain juctionPoints;
    /** referring to the {@link LEdge}s this segment is a part of. */
    public Set<LEdge> originalLEdges = Sets.newLinkedHashSet();

    /**
     * The constructor adds a {@link VerticalSegment} to the list and appends its bend and junction
     * points.
     * 
     * @param vSeg
     *            a single {@link VerticalSegment}
     * @param layeredGraph
     *            the containing layered graph
     */
    public CLEdge(final VerticalSegment vSeg, final LGraph layeredGraph) {
        super(layeredGraph);
        bends = new KVectorChain();
        juctionPoints = new KVectorChain();
        hitbox = new Rectangle(vSeg.x1, vSeg.y1, 0, vSeg.y2 - vSeg.y1);
        parentNode = vSeg.parentNode;
        cGroupOffset = vSeg.relativePosition;
        
        addSegment(vSeg);
    }

    /**
     * Adds an intersecting {@link VerticalSegment} to the {@link CLEdge}.
     * 
     * @param vSeg
     *            the single vertical segment
     */
    public void addSegment(final VerticalSegment vSeg) {
        bends.addAll(vSeg.bend1, vSeg.bend2);
        juctionPoints.addAll(vSeg.junctionPoints);

        // updating the hitbox to span over the new segment
        double newY1 = Math.min(hitbox.y, vSeg.y1);
        double newY2 = Math.max(hitbox.y + hitbox.height, vSeg.y2);
        hitbox.setRect(vSeg.x1, newY1, 0, newY2 - newY1);

        originalLEdges.add(vSeg.lEdge);
    
        // edges are locked by default TODO only selfloops? 
        if (vSeg.lEdge.getSource().getNode() == vSeg.lEdge.getTarget().getNode()) {
            lock.set(true, true, true, true);
        }
        // segments belonging to multiple edges should be locked in the direction of fewer different nodes
        Set<LPort> inc = Sets.newLinkedHashSet(originalLEdges.stream()
                                                .map(e -> e.getSource())
                                                .collect(Collectors.toSet()));
        Set<LPort> out = Sets.newLinkedHashSet(originalLEdges.stream()
                                                .map(e -> e.getTarget())
                                                .collect(Collectors.toSet()));
        int difference = inc.size() - out.size();
        if (difference < 0) {
            lock.set(true, Direction.LEFT);
            lock.set(false, Direction.RIGHT);
        } else if (difference > 0) {
            lock.set(false, Direction.LEFT);
            lock.set(true, Direction.RIGHT);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void applyElementPosition() {
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

    /**
     * {@inheritDoc}
     */
    @Override
    public double getSingleHorizontalSpacing() {
        return layeredGraph.getProperty(InternalProperties.SPACING)
                * layeredGraph.getProperty(Properties.EDGE_SPACING_FACTOR);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public double getSingleVerticalSpacing() {
        return layeredGraph.getProperty(InternalProperties.SPACING)
                * layeredGraph.getProperty(Properties.EDGE_SPACING_FACTOR)
                * layeredGraph.getProperty(Properties.OBJ_SPACING_IN_LAYER_FACTOR);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public double getHorizontalSpacing(final CNode other) {
        
      //joining north/south segments that belong to the same edge by setting their spacing to 0
      if (parentNode != null && other.parentNode != null
              && other.getClass() == CLEdge.class
              // this might seem quite expensive but in most cases the sets contain only one element
              && !Sets.intersection(originalLEdges, ((CLEdge) other).originalLEdges).isEmpty()) {
          return 0;
      }

      // returning edge spacing if an edge is involved, otherwise object spacing
      return Math.min(getSingleHorizontalSpacing(), other.getSingleHorizontalSpacing());
    }
    
    @Override
    public String toString() {
        return originalLEdges.toString();
    }
}
