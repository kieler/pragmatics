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
package de.cau.cs.kieler.klay.planar.orthogonal;

import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.klay.planar.graph.IGraph;

/**
 * Interface for compaction algorithms. Uses the Strategy design pattern to provide a common
 * interface for various implementations of algorithms for compaction.
 * 
 * @author ocl
 */
public interface ICompactor {

    /**
     * A property assigning a coordinates to a node.
     */
    Property<Pair<Integer, Integer>> COORDINATES = new Property<Pair<Integer, Integer>>(
            "de.cau.cs.kieler.klay.planar.properties.coordinates", new Pair<Integer, Integer>(0, 0));

    /**
     * TODO javadoc.
     * 
     * @param graph
     *            the graph to compact
     * @param orthogonal
     *            the orthogonal representation that restricts the graph
     */
    void compact(IGraph graph, OrthogonalRepresentation orthogonal);

}
