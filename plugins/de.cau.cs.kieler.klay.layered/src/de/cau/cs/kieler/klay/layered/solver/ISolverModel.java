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

import java.io.IOException;
import java.io.InputStream;

/**
 * The common interface for models that can be executed with solvers.
 * 
 * @author uru
 * 
 * @param <T>
 *            type of the result
 * @param <S>
 *            type of the input
 */
public interface ISolverModel<S, T> {

    /**
     * @return an array of string arguments. The first argument should be the binary of the solver.
     *         Following arguments are passed to the solver. The reference to the internally
     *         assembled data file is automatically appended to this argument list.
     */
    String[] getSolverArgs();

    /**
     * @return the file extension of the data files for the specific solver (including a leading
     *         ".").
     */
    String getDataFileExtension();

    /**
     * Convert the passed model to a textually specified data file for the model. It will be written
     * to a temporary file and passed to the solver binary.
     * 
     * @param model
     *            the original data
     * @return a serialized form that will be written to a temporary file.
     */
    String serializeSourceModel(S model);

    /**
     * Parse the output of the solver and return the desired result.
     * 
     * @param is
     *            the input stream to which the solver's results are written.
     * @return the parsed result of {@code null} if unsuccessful
     * @throws IOException
     *             if the {@link InputStream} cannot be read.
     */
    T parseResult(final InputStream is) throws IOException;

}
