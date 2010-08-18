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

import de.cau.cs.kieler.core.util.MapPropertyHolder;
import de.cau.cs.kieler.klay.planar.graph.IGraph;
import de.cau.cs.kieler.klay.planar.graph.IGraphElement;

/**
 * Any generic graph element in a basic graph data structure for various graph theory algorithms.
 * 
 * @see de.cau.cs.kieler.klay.planar.graph.impl.PGraph {@code PGraph}
 * 
 * @author ocl
 */
abstract class PGraphElement extends MapPropertyHolder implements IGraphElement {

    // ======================== Attributes =========================================================

    /** The unique ID of the graph element in a given graph or -1 if unassigned. */
    private int identifier;

    /** The parent graph this element belongs to. */
    private IGraph parent;

    // ======================== Constructor ========================================================

    /**
     * Create a new graph element based on an id and a parent graph.
     * 
     * @param id
     *            the id to assign to the element
     * @param p
     *            the graph this element belongs to
     */
    public PGraphElement(final int id, final IGraph p) {
        this.identifier = id;
        this.parent = p;
    }

    // ======================== Getters and Setters ================================================

    /**
     * {@inheritDoc}
     */
    public int getID() {
        return this.identifier;
    }

    /**
     * Set the ID of the graph element.
     * 
     * @param id
     *            the id of the element
     */
    public void setID(final int id) {
        this.identifier = id;
    }

    /**
     * {@inheritDoc}
     */
    public IGraph getParent() {
        return this.parent;
    }

    // ======================== Miscellaneous Functions ============================================

    @Override
    public String toString() {
        String res = "Graph Element (" + this.getID() + ")";
        return res;
    }

}
