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
package de.cau.cs.kieler.klay.planar;

import de.cau.cs.kieler.core.alg.IAlgorithm;
import de.cau.cs.kieler.klay.planar.graph.PGraph;

/**
 * A layout processor processes a {@link PGraph}, performing layout related tasks on it.
 * 
 * @see PlanarLayoutProvider
 * @author pkl
 */
public interface ILayoutProcessor extends IAlgorithm {

    /**
     * Performs the phase's work on the given graph.
     * 
     * @param graph
     *            a instance of {@link PGraph}
     */
    void process(PGraph graph);

}
