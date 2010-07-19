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
package de.cau.cs.rtprak.planarization.graph.impl;

import java.util.HashMap;
import java.util.Iterator;

import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.rtprak.planarization.graph.IGraph;
import de.cau.cs.rtprak.planarization.graph.IGraphElement;

/**
 * Any generic graph element in a basic graph data structure for various graph theory algorithms.
 * 
 * @see de.cau.cs.rtprak.planarization.graph.impl.PGraph {@code PGraph}
 * 
 * @author ocl
 */
public abstract class PGraphElement implements IGraphElement {

    // ======================== Attributes =========================================================

    /** The unique ID of the graph element in a given graph or -1 if unassigned. */
    private int identifier;

    /** The parent graph this element belongs to. */
    private IGraph parent;

    /** The map containing the attached data items to the element. */
    private HashMap<String, Object> data;

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
        this.data = new HashMap<String, Object>();
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

    /**
     * {@inheritDoc}
     */
    public Iterable<Pair<String, Object>> getData() {
        // Create anonymous iterator
        return new Iterable<Pair<String, Object>>() {
            public Iterator<Pair<String, Object>> iterator() {
                return new Iterator<Pair<String, Object>>() {
                    private final Iterator<String> ids = data.keySet().iterator();

                    public boolean hasNext() {
                        return ids.hasNext();
                    }

                    public Pair<String, Object> next() {
                        String id = ids.next();
                        Object item = data.get(id);
                        return new Pair<String, Object>(id, item);
                    }

                    public void remove() {
                        ids.remove();
                    }
                };
            }
        };
    }

    /**
     * {@inheritDoc}
     */
    public <T> T getData(final String id) {
        // TODO is this possible without unchecked cast?
        T item = (T) this.data.get(id);
        return item;
    }

    /**
     * {@inheritDoc}
     */
    public <T> void addData(final String id, final T item) {
        this.data.put(id, item);
    }

    /**
     * {@inheritDoc}
     */
    public void copyData(final IGraphElement element) {
        for (Pair<String, Object> pair : element.getData()) {
            this.addData(pair.getFirst(), pair.getSecond());
        }
    }

    // ======================== Miscellaneous Functions ============================================

    @Override
    public String toString() {
        String res = "Graph Element (" + this.getID() + ")";
        return res;
    }

}
