/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.tree.graph;

import de.cau.cs.kieler.core.properties.MapPropertyHolder;

/**
 * Abstract superclass for the layers, nodes, ports, and edges of a tree graph.
 * 
 * @author sor
 * @author sgu
 */
public abstract class TGraphElement extends MapPropertyHolder {

    /** the serial version UID. */
    private static final long serialVersionUID = 1L;

    /** Identifier value, may be arbitrarily used by algorithms. */
    public int id;  // SUPPRESS CHECKSTYLE VisibilityModifier

    /**
     * Create a graph element.
     * 
     * @param id the element identifier
     */
    public TGraphElement(final int id) {
        super();
        this.id = id;
    }

    /**
     * Implicit super constructor.
     * 
     */
    public TGraphElement() {
    }

}
