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


/**
 * Abstract class for MiniZinc models. The class implements the execution of such models, implementing
 * classes have to implement the methods that serialize a model and deserialize its solution. This class
 * requires three environment variables to be set:
 * <ol>
 *   <li><code>MINIZINC_HOME</code> points at the MiniZinc installation directory.
 *   <li><code>MINIZINC_SOLVE</code> points at the directory that contains the model to be solved and
 *       the script to be executed.</li>
 *   <li><code>SCIP_BIN</code> points at the directory that contains the SCIP binary.
 * </ol>
 * 
 * @author uru
 * 
 * @param <I>
 *            type of the input
 * @param <R>
 *            type of the result
 */
public abstract class AbstractMiniZincModel<I, R> extends AbstractSolverModel<I, R> {

    /** Home folder of the minizinc installation, ie where the 'bin' folder resides. */
    private static final String MINIZINC_INSTALL = System.getenv("MINIZINC_HOME");
    /** Folder of the minizinc model and the layering script to be executed. */
    private static final String MINIZINC_SOLVE = System.getenv("MINIZINC_SOLVE");
    /** Path to the SCIP binary. */
    private static final String SCIP_INSTALL = System.getenv("SCIP_BIN");

    
    /**
     * Creates a new instance and checks if all the required paths exist.
     * 
     * @throws RuntimeException
     *             if one of the required paths could not be found.
     */
    public AbstractMiniZincModel() {
        checkIfPathExists(MINIZINC_INSTALL, "MiniZinc installation folder");
        checkIfPathExists(MINIZINC_SOLVE, "MiniZinc model folder");
        checkIfPathExists(SCIP_INSTALL, "SCIP binary folder");
    }
    

    /**
     * {@inheritDoc}
     */
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
    public String getDataFileExtension() {
        return ".dzn";
    }
    
}
