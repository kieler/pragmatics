/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.planar.intermediate;

import de.cau.cs.kieler.klay.planar.graph.PEdge;
import de.cau.cs.kieler.klay.planar.graph.PNode;

/**
 * Container that stores the dummy node, dummy edge and the dummy edge arise by splitting the a edge
 * with the dummy node at the {@link FullAngleDummyProcessor}.
 * 
 * @author pkl
 */
public class FullAngleDummyEntry {

    /** The inserted dummy edge. */
    private PNode dummyNode;

    /** The inserted dummy edges. */
    private PEdge dummyEdge;

    /** The arising dummy edge at splitting an edge with the dummy edge. */
    private PEdge edgeSplitDummy;

    /**
     * Creates a new full angle dummy entry with dummy node, dummy edge and edge split dummy.
     * 
     * @param dummyNode
     *            created dummy node
     * @param dummyEdge
     *            created dummy edge
     * @param edgeSplitDummy
     *            arised dummy edge
     */
    public FullAngleDummyEntry(final PNode dummyNode, final PEdge dummyEdge,
            final PEdge edgeSplitDummy) {
        this.dummyNode = dummyNode;
        this.dummyEdge = dummyEdge;
        this.edgeSplitDummy = edgeSplitDummy;
    }

    /**
     * @return the dummyNode
     */
    public PNode getDummyNode() {
        return dummyNode;
    }

    /**
     * @return the dummyEdge
     */
    public PEdge getDummyEdge() {
        return dummyEdge;
    }

    /**
     * @return the edgeSplitDummy
     */
    public PEdge getEdgeSplitDummy() {
        return edgeSplitDummy;
    }
}
