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
package de.cau.cs.kieler.klay.planar.graph;

import de.cau.cs.kieler.klay.planar.graph.IEmbeddingConstraint.IConstraintTreeLeaf;

/**
 * A leaf in a constraint tree for embedding constraints.
 * 
 * @author ocl
 */
class PConstraintTreeLeaf implements IConstraintTreeLeaf {

    // ======================== Attributes =========================================================

    /** The edge associated with this constraint tree leaf. */
    private PEdge edge;

    // ======================== Constructor ========================================================

    /**
     * Create a new constraint tree leaf associated with a graph edge.
     * 
     * @param e
     *            the associated edge
     */
    PConstraintTreeLeaf(final PEdge e) {
        this.edge = e;
    }

    // ======================== Getters and Setters ================================================

    /**
     * {@inheritDoc}
     */
    public PEdge getEdge() {
        return this.edge;
    }

}
