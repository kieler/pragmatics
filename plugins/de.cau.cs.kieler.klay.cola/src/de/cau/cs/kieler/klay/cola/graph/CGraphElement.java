/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2014 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.cola.graph;

import de.cau.cs.kieler.core.properties.MapPropertyHolder;

/**
 * .
 * 
 * @author uru
 */
public abstract class CGraphElement extends MapPropertyHolder {

    /**
     * 
     */
    private static final long serialVersionUID = -8909829166918218344L;

    // CHECKSTYLEOFF VisibilityModifier
    
    /** Identifier value, may be arbitrarily used by algorithms. */
    public int id;

    /**
     * Index of this element within an adaptagrams array. E.g., the index of a node in the nodes
     * array.
     */
    public int cIndex;

    /**
     * The graph in which this element is contained.
     */
    public final CGraph graph;

    /**
     * Creates a new element.
     * 
     * @param graph
     *            the graph that holds this element.
     */
    public CGraphElement(final CGraph graph) {
        this.graph = graph;
    }

    /**
     * Call this after the position, dimensions, and properties of an element are set. The method
     * should then create the internal adaptagrams elements.
     */
    public abstract void init();

}
