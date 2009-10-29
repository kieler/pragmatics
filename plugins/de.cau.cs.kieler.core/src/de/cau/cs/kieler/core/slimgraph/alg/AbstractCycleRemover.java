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
 * @author <a href="mailto:msp@informatik.uni-kiel.de">Miro Sp&ouml;nemann</a>
 */
public abstract class AbstractCycleRemover extends AbstractAlgorithm implements ICycleRemover {

    /** list of edges that are reversed and later restored. */
    protected LinkedList<KSlimEdge> reversedEdges = null;

    /*
     * (non-Javadoc)
     * 
     * @see de.cau.cs.kieler.core.alg.AbstractAlgorithm#reset()
     */
    @Override
    public void reset() {
        super.reset();
        reversedEdges = null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.cau.cs.kieler.core.graph.alg.ICycleRemover#restoreGraph()
     */
    /** {@inheritDoc} */
    public void restoreGraph() {
        if (reversedEdges != null) {
            reverseEdges();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.cau.cs.kieler.core.graph.alg.ICycleRemover#getReversedEdges()
     */
    /** {@inheritDoc} */
    public List<KSlimEdge> getReversedEdges() {
        if (reversedEdges == null) {
            throw new IllegalStateException("removeCycles must be called before getReversedEdges.");
        } else {
            return reversedEdges;
        }
    }

    /**
     * Switches the source and target of all edges marked in the
     * <code>reversedEdges</code> list.
     */
    protected void reverseEdges() {
        for (KSlimEdge edge : reversedEdges) {
            edge.rank = ICycleRemover.REVERSED;
            // change incidence type at source and target
            ListIterator<KSlimNode.IncEntry> sourceIter = edge.source.getIterator(edge, true);
            ListIterator<KSlimNode.IncEntry> targetIter = edge.target.getIterator(edge, false);
            if (sourceIter != null) {
                sourceIter.previous().type = KSlimNode.IncEntry.Type.IN;
            }
            if (targetIter != null) {
                targetIter.previous().type = KSlimNode.IncEntry.Type.OUT;
            }

            // reverse source and target node
            KSlimNode source = edge.source;
            KSlimNode.Side sourceSide = edge.sourceSide;
            KSlimNode target = edge.target;
            KSlimNode.Side targetSide = edge.targetSide;
            edge.source = target;
            edge.sourceSide = targetSide;
            edge.target = source;
            edge.targetSide = sourceSide;
        }
    }

}
