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
package de.cau.cs.kieler.klay.planar.graph.impl;

import java.io.Serializable;

import de.cau.cs.kieler.klay.planar.graph.IAdjacencyList;
import de.cau.cs.kieler.klay.planar.graph.IEdge;
import de.cau.cs.kieler.klay.planar.graph.INode;
import de.cau.cs.kieler.klay.planar.graph.IPort;
import de.cau.cs.kieler.klay.planar.graph.InconsistentGraphModelException;

/**
 * A Port in a basic graph data structure for various graph theory algorithms.
 * 
 * @see de.cau.cs.kieler.klay.planar.graph.impl.PGraph {@code PGraph}
 * 
 * @author ocl
 */
public class PPort extends PAdjacencyListComponent implements IPort, Serializable {

    /** Generated Version UID for Serialization. */
    private static final long serialVersionUID = 7739990996504538540L;

    // ======================== Attributes =========================================================

    /** The edge attached to this port. */
    private IEdge link;

    // ======================== Constructor ========================================================

    /**
     * Create a new port.
     * 
     * @param node
     *            the node the port belongs to
     * @param list
     *            the adjacency list the port is part of
     */
    PPort(final INode node, final IAdjacencyList list) {
        super(node, list);
    }

    /**
     * Create a new port.
     * 
     * @param id
     *            the id of the port
     * @param node
     *            the node the port belongs to
     * @param list
     *            the adjacency list the port is part of
     */
    PPort(final int id, final INode node, final IAdjacencyList list) {
        super(id, node, list);
    }

    // ======================== Getters and Setters ================================================

    /**
     * {@inheritDoc}
     */
    public boolean isEmpty() {
        return this.link == null;
    }

    /**
     * {@inheritDoc}
     */
    public IEdge getEdge() {
        return this.link;
    }

    /**
     * {@inheritDoc}
     */
    PPort linkEdge(final IEdge edge, final boolean append) {
        this.link = edge;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    void unlinkEdge(final IEdge edge) throws InconsistentGraphModelException {
        if (this.link == edge) {
            this.link = null;
        } else {
            throw new InconsistentGraphModelException(
                    "Attempted to remove non-existant edge from port.");
        }
    }

    // ======================== Miscellaneous Functions ============================================

    @Override
    public String toString() {
        return "Port (" + this.getID() + ") :\n\t" + this.link;
    }

}
