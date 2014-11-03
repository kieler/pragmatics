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
package de.cau.cs.kieler.klay.layered.solver;

import de.cau.cs.kieler.klay.layered.graph.LGraph;

/**
 * @author uru
 * 
 * @param <T>
 *            type of the result
 * @param <S>
 *            type of the input
 */
public abstract class AbstractCPLEXModel<S, T> extends  AbstractSolverModel<S, T> {

    /** The graph to be layered. */

    // SUPPRESS CHECKSTYLE NEXT 2 VisibilityModifier
    protected LGraph layeredGraph;

    /**
     * @param graph
     *            the graph tos be layered
     */
    public AbstractCPLEXModel(final LGraph graph) {
        this.layeredGraph = graph;
    }

    /**
     * {@inheritDoc}
     */
    public String[] getSolverArgs() {
        return new String[] { "oplrun", "-deploy", getModel() };
    }

    /**
     * {@inheritDoc}
     */
    public String getDataFileExtension() {
        return ".dat";
    }

    /**
     * @return a reference to the model to be solved.
     */
    public abstract String getModel();

}
