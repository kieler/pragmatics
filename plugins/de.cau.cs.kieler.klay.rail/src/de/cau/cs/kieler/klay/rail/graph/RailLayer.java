/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse Rich Client
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.rail.graph;

import java.util.LinkedList;
import java.util.List;

import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;

/**
 * 
 * Extended layer class for railway layout.
 * 
 * Using not only columns (layers) but also rows to determine the y-position of a node.
 * 
 * @author jjc
 */
public class RailLayer extends Layer {

    /**
     * A list representing the vertical order of the nodes. May contain null for a dummy empty space
     * in some rows. The front of the list is to be placed at the top of the page.
     */
    private RailRow row = new RailRow();

    /**
     * Constructor similar to the one of the original layer.
     * 
     * @param graph
     *            the owning layered graph
     */
    public RailLayer(final LayeredGraph graph) {
        super(graph);
    }

    /**
     * Getter for the row list. It is a list representing the vertical order of the nodes. May
     * contain null for a dummy empty space in some rows. The front of the list is to be placed at
     * the top of the page.
     * 
     * @return the row list as described above
     */
    public RailRow getRowList() {
        return row;
    }

}
