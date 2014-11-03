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

/**
 * 
 * @param <T>
 *            type of the result
 * @param <S>
 *            type of the input
 * @author uru
 */
public abstract class AbstractSolverModel<S, T> implements ISolverModel<S, T> {

    /**
     * @param path
     *            the path of the executable to check
     */
    protected void checkForExecutable(final String path) {
        checkForExecutable(path, "");
    }

    /**
     * @param path
     *            the path of the executable to check
     * @param description
     *            a short description what the executable represents, e.g. "cplex layering".
     */
    protected void checkForExecutable(final String path, final String description) {
        if (path == null || !new File(path).exists()) {
            throw new RuntimeException("Could not locate " + description + " executable at path '"
                    + path + "'.");
        }
    }

}
