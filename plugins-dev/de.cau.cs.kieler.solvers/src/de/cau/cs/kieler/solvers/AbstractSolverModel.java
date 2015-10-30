package de.cau.cs.kieler.solvers;
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

import java.io.File;

/**
 * Abstract implementation of the {@link ISolverModel} interface. Only adds two convenience methods for
 * subclasses to check if an executable exists.
 * 
 * @param <I>
 *            type of the input
 * @param <R>
 *            type of the result
 * @author uru
 */
public abstract class AbstractSolverModel<I, R> implements ISolverModel<I, R> {

    /**
     * Checks if the given path exists in the file system. This is the same as calling
     * {@link #checkIfPathExists(String, String)} with an empty description.
     * 
     * @param path
     *            the path to check.
     * @throws RuntimeException
     *             if the path does not exist.
     */
    protected void checkIfPathExists(final String path) {
        checkIfPathExists(path, "");
    }

    /**
     * Checks if the given path exists.
     * 
     * @param path
     *            the path to check.
     * @param description
     *            a short description what the path represents, e.g. "cplex layering".
     * @throws RuntimeException
     *             if the path does not exist.
     */
    protected void checkIfPathExists(final String path, final String description) {
        if (path == null || !new File(path).exists()) {
            throw new RuntimeException("Could not locate " + description + " at '" + path + "'.");
        }
    }

}
