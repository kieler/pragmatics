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

import java.io.File;

import de.cau.cs.kieler.klay.layered.graph.LGraph;

/**
 * Abstract class for MiniZinc models. The class implements the execution of such models,
 * implementing classes have to provide the actual functionality.
 * 
 * @author uru
 * 
 * @param <T>
 *            type of the result
 * @param <S>
 *            type of the input
 */
public abstract class AbstractMiniZincModel<S, T> implements ISolverModel<S, T> {

    /** Home folder of the minizinc installation, ie where the 'bin' folder resides. */
    private static final String MINIZINC_INSTALL = System.getenv("MINIZINC_HOME");
    /** Folder of the minizinc model and the layering script to be executed. */
    private static final String MINIZINC_SOLVE = System.getenv("MINIZINC_SOLVE");
    /** Path to the SCIP binary. */
    private static final String SCIP_INSTALL = System.getenv("SCIP_BIN");

    // SUPPRESS CHECKSTYLE NEXT 2 VisibilityModifier
    /** The graph to be layered. */
    protected LGraph layeredGraph;

    /**
     * @param graph
     *            The graph to be layered.
     */
    public AbstractMiniZincModel(final LGraph graph) {
        this.layeredGraph = graph;

        checkForExecutables();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String[] getSolverArgs() {
        if (System.getProperty("os.name").toLowerCase().contains("windows")) {
            return new String[] { "bash", MINIZINC_SOLVE, MINIZINC_INSTALL, SCIP_INSTALL };
        } else {
            return new String[] { MINIZINC_SOLVE, MINIZINC_INSTALL, SCIP_INSTALL };
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDataFileExtension() {
        return ".dzn";
    }

    private static final String NOTE =
            "Note that system variables are loaded only once upon program startup.";

    private void checkForExecutables() {

        if (MINIZINC_INSTALL == null || !new File(MINIZINC_INSTALL).exists()) {
            throw new RuntimeException(
                    "Could not locate MiniZinc installation, make sure 'MINIZINC_HOME' is set.\n"
                            + NOTE);
        }

        if (MINIZINC_SOLVE == null || !new File(MINIZINC_SOLVE).exists()) {
            throw new RuntimeException(
                    "Could not locate MiniZinc installation, make sure 'MINIZINC_SOLVE' is set.\n"
                            + NOTE);
        }
        if (SCIP_INSTALL == null || !new File(SCIP_INSTALL).exists()) {
            throw new RuntimeException(
                    "Could not locate SCIP installation, make sure 'SCIP_INSTALL' is set.\n" + NOTE);
        }
    }

}
