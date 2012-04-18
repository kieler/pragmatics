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

import de.cau.cs.kieler.core.properties.MapPropertyHolder;

/**
 * Any generic graph element in a basic graph data structure for various graph theory algorithms.
 * 
 * @see de.cau.cs.kieler.klay.planar.graph.PGraph {@code PGraph}
 * 
 * @author ocl
 */
public abstract class PGraphElement extends MapPropertyHolder implements Comparable<PGraphElement> {

    // ======================== Attributes =========================================================

    // CHECKSTYLEOFF VisibilityModifier
    /**
     * Identifier value, may be arbitrarily used by algorithms. the unique ID of the graph element
     * in a given graph or -1 if unassigned.
     */
    public int id;
    // CHECKSTYLEON VisibilityModifier

    /** The parent graph this element belongs to. */
    private PGraph parent;

    // ======================== Constructor ========================================================

    /**
     * Create a new graph element based on an id and a parent graph.
     * 
     * @param id
     *            the id to assign to the element
     * @param p
     *            the graph this element belongs to
     */
    public PGraphElement(final int id, final PGraph p) {
        this.id = id;
        this.parent = p;
    }

    // ======================== Getters and Setters ================================================

    /**
     * Get the parent graph of the element. Every graph element is part of exactly one graph, which
     * itself can again be part of other graphs. This method returns the direct parent in the graph
     * hierarchy.
     * 
     * @return the parent graph of the element
     */
    public PGraph getParent() {
        return this.parent;
    }

    // ======================== Miscellaneous Functions ============================================

    @Override
    public String toString() {
        String res = "Graph Element (" + this.id + ")";
        return res;
    }

    @Override
    public int hashCode() {
        return this.id;
    }

    @Override
    public boolean equals(final Object obj) {
        return (obj instanceof PGraphElement) && (((PGraphElement) obj).id == this.id);
    }

    /**
     * {@inheritDoc}
     */
    public int compareTo(final PGraphElement other) {
        return this.id - other.id;
    }

}
