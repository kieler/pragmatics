/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2009 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.core.slimgraph.alg;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.core.slimgraph.KSlimEdge;
import de.cau.cs.kieler.core.slimgraph.KSlimNode;

/**
 * Abstract superclass that can be used for implementations of cycle remover
 * algorithms which operate by reversing edges.
 * 
 * @kieler.rating 2009-12-11 proposed yellow msp
 * @author msp
 */
public abstract class AbstractCycleRemover extends AbstractAlgorithm implements ICycleRemover {

    /** list of edges that are reversed and later restored. */
    private LinkedList<KSlimEdge> reversedEdges = null;

    /**
     * {@inheritDoc}
     */
    @Override
    public void reset() {
        super.reset();
        reversedEdges = null;
    }

    /**
     * {@inheritDoc}
     */
    public void restoreGraph() {
        if (reversedEdges != null) {
            reverseEdges();
        }
    }

    /**
     * {@inheritDoc}
     */
    public List<KSlimEdge> getReversedEdges() {
        if (reversedEdges == null) {
            throw new IllegalStateException("removeCycles must be called before getReversedEdges.");
        } else {
            return reversedEdges;
        }
    }
    
    /**
     * Sets the reversed edges.
     * 
     * @param thereversedEdges the reversed edges
     */
    protected void setReversedEdges(final LinkedList<KSlimEdge> thereversedEdges) {
        this.reversedEdges = thereversedEdges;
    }

    /**
     * Switches the source and target of all edges marked in the
     * <code>reversedEdges</code> list.
     */
    protected void reverseEdges() {
        for (KSlimEdge edge : reversedEdges) {
            edge.setRank(ICycleRemover.REVERSED);
            // change incidence type at source and target
            ListIterator<KSlimNode.IncEntry> sourceIter = edge.getSource().getIterator(edge, true);
            ListIterator<KSlimNode.IncEntry> targetIter = edge.getTarget().getIterator(edge, false);
            if (sourceIter != null) {
                sourceIter.previous().setType(KSlimNode.IncEntry.Type.IN);
            }
            if (targetIter != null) {
                targetIter.previous().setType(KSlimNode.IncEntry.Type.OUT);
            }

            // reverse source and target node
            KSlimNode source = edge.getSource();
            KSlimNode.Side sourceSide = edge.getSourceSide();
            KSlimNode target = edge.getTarget();
            KSlimNode.Side targetSide = edge.getTargetSide();
            edge.setSource(target);
            edge.setSourceSide(targetSide);
            edge.setTarget(source);
            edge.setTargetSide(sourceSide);
        }
    }

}
