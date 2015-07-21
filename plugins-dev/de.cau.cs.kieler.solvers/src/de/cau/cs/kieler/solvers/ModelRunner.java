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


import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import com.google.common.collect.ObjectArrays;
import com.google.common.io.Files;

/**
 * Utility methods to execute models using solvers.
 * 
 * @author uru
 */
public final class ModelRunner {

    private static final boolean DEBUG = false;

    private ModelRunner() {
    }

    /**
     * Runs a solver with specified input and parses the output.
     * 
     * @param model
     *            the solver model knowing how to serialize and parse the model.
     * @param input
     *            the input model, e.g. an adjacency matrix.
     * @return the parsed result of the solving process.
     * @throws IOException
     *             if an error occurred while writing the temporary data file.
     * @throws InterruptedException
     *             if there was error during process, i.e. solver, execution
     * @param <R>
     *            type of the result
     * @param <I>
     *            type of the input
     */
    public static <R, I> R execute(final ISolverModel<I, R> model, final I input)
            throws IOException, InterruptedException {

        // #1 create tmp file with the data
        String data = model.serializeSourceModel(input);
        File dataFile = File.createTempFile("graph", model.getDataFileExtension());
        dataFile.deleteOnExit();
        Files.write(data.getBytes(), dataFile);

        if (DEBUG) {
            //System.out.println("Written: " + Files.readLines(dataFile, Charset.forName("utf8")));
        }

        // #2 execute the solver
        String[] args = ObjectArrays.concat(model.getSolverArgs(), dataFile.getAbsolutePath());

        if (DEBUG) {
            System.out.println("\tArgs: " + Arrays.toString(args));
        }

        Process process = Runtime.getRuntime().exec(args);
        process.waitFor();

        // #3 read input and return
        R result = model.parseResult(process.getInputStream());

        if (result == null) {
            readErrorStream(process);
        }

        return result;
    }

    /**
     * Read the error stream and throw an exception.
     * 
     * @param process
     *            the MiniZinc process
     * @throws IOException
     *             if reading from the error stream fails
     */
    private static void readErrorStream(final Process process) throws IOException {
        String line;
        StringBuilder errorBuilder = new StringBuilder();
        BufferedReader errorReader =
                new BufferedReader(new InputStreamReader(process.getErrorStream()));
        while ((line = errorReader.readLine()) != null) {
            errorBuilder.append(line);
        }
        throw new RuntimeException("Communication with solver failed: " + errorBuilder.toString());
    }

}
