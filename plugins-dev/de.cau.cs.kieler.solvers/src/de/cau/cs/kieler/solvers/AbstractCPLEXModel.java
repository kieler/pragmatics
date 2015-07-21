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
 * Abstract class for CPLEX models. The class implements the execution of such models, implementing
 * classes have to implement the methods that serialize a model and deserialize its solution. This class
 * requires the {@code oplrun} executable to be on the path. Clients have to implement
 * {@link #getModel()}, which returns the file that defines the CPLEX model.
 * 
 * @author uru
 * 
 * @param <I>
 *            type of the input
 * @param <R>
 *            type of the result
 */
public abstract class AbstractCPLEXModel<I, R> extends  AbstractSolverModel<I, R> {

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
