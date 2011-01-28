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

import de.cau.cs.kieler.core.alg.IAlgorithm;
import de.cau.cs.kieler.klay.planar.graph.IGraph;

/**
 * Interface for orthogonalization algorithms. Uses the Strategy design pattern to provide a common
 * interface for various implementations of algorithms for orthogonalization.
 * 
 * @author ocl
 */
public interface IOrthogonalizer extends IAlgorithm {

    /**
     * This takes a planar graph and computes an orthogonal representation defining the shape of the
     * orthogonal graph.
     * 
     * @param graph
     *            the graph to draw as orthogonal graph
     * @return an orthogonal representation of the graph
     */
    OrthogonalRepresentation orthogonalize(IGraph graph);

}
