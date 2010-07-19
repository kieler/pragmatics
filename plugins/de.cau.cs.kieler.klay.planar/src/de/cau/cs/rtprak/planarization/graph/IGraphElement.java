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
package de.cau.cs.rtprak.planarization.graph;

import de.cau.cs.kieler.core.util.Pair;

/**
 * A generic element in the interface for a general graph data structure.
 * 
 * @see de.cau.cs.rtprak.planarization.graph.IGraph {@code IGraph}
 * @see de.cau.cs.rtprak.planarization.graph.IGraphElement {@code IGraphElement}
 * 
 * @author ocl
 */
public interface IGraphElement {

    /**
     * Get an ID of the element. IDs should be unique between all elements of the same type in the
     * graph (e.g. unique between all nodes, all edges, etc).
     * 
     * @return the ID of the element
     */
    int getID();

    /**
     * Get the parent graph of the element. Every graph element is part of exactly one graph, which
     * itself can again be part of other graphs. This method returns the direct parent in the graph
     * hierarchy.
     * 
     * @return the parent graph of the element
     */
    IGraph getParent();

    /**
     * Attach data to the graph element. This gives the possibility to attach any kind of data to
     * the graph element. The data is identified by an id string. Adding data with an already
     * existing id will overwrite the previous data with the same id.
     * 
     * @param <T>
     *            the type of the data object
     * @param id
     *            the unique id string of the data
     * @param item
     *            the data to add to the element
     */
    <T> void addData(String id, T item);

    /**
     * Get data from the graph element. This retrieves any previously stored data on the element,
     * that is identified by the given id string.
     * 
     * @param <T>
     *            the type of the data object
     * @param id
     *            the unique id string of the data
     * @return the data stored on the element
     */
    <T> T getData(String id);

    /**
     * Get all the data attached to the graph element. This returns an iterable over pairs
     * containing the identifiers of the data on the one hand, and the data objects on the other
     * hand.
     * 
     * @return iterable over pairs of identifier and data
     */
    Iterable<Pair<String, Object>> getData();

    /**
     * Copies all data on the given element to this element.
     * 
     * @param element
     *            the element to copy from
     */
    void copyData(IGraphElement element);

}
