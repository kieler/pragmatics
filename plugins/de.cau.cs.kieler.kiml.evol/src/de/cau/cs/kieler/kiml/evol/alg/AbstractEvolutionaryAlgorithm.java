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
package de.cau.cs.kieler.kiml.evol.alg;

import java.util.LinkedList;
import java.util.List;

/**
 * Abstract implementation of an evolutionary algorithm. Implementations of
 * evolutionary algorithms shall inherit from this class. After construction,
 * either
 * <ul>
 * <li>{@link #run()} can be used to start the algorithm, which will initialize
 * and then continue running until {@link #isDone()} yields {@code true}, or
 *
 * <li>{@link #step()} can be used for stepwise execution. In this case,
 * {@link #initialize()} must be called explicitly once before.
 * </ul>
 *
 * @author bdu
 *
 */
public abstract class AbstractEvolutionaryAlgorithm implements Runnable {

    /**
     * Adds an evolution listener. Duplicate listeners are ignored.
     *
     * @param listener
     *            the listener to add; must not be {@code null}
     */
    public void addListener(final IEvolutionListener listener) {
        if (listener == null) {
            throw new IllegalArgumentException("Argument must not be null: listener");
        }
        if (!this.listeners.contains(listener)) {
            this.listeners.add(listener);
        }
    }

    /**
     * Removes the specified evolution listener, if present. Requests to remove
     * non-existent listeners are ignored.
     *
     * @param listener
     *            the listener to remove
     */
    public void removeListener(final IEvolutionListener listener) {
        this.listeners.remove(listener);
    }

    /**
     * Returns the generation.
     *
     * @return the generation
     */
    public int getGeneration() {
        return this.generation;
    }

    /**
     * Main loop for initializing and running the complete algorithm. The
     * algorithm will run until some stop criterion is satisfied. Since this
     * method implicitly initializes the algorithm, {@link #initialize()} must
     * not be called before.
     */
    public void run() {
        if (!this.isInitialized) {
            initialize();
            determineFitness();
            while (!isDone()) {
                step();
                // pause here for stepwise execution using an IEvolutionListener
            }
            this.isInitialized = false;
        } else {
            throw new UnsupportedOperationException(
                    "Cannot run an algorithm that has already been initialized.");
        }
    }

    /**
     * Performs a single step of the algorithm by proceeding to the next
     * generation. The algorithm must be initialized before by calling
     * {@link #initialize()}.
     */
    public final void step() {
        if (!this.isInitialized) {
            throw new UnsupportedOperationException(
                    "The algorithm must be initialized before single steps can be performed.");
        }

        if (isDone()) {
            throw new UnsupportedOperationException(
                    "No further steps may be performed after the stop criterion has been satisfied.");
        }

        beforeStep();
        if (this.generation > 0) {
            survive();
        }
        this.generation++;
        select();
        crossOver();
        mutate();
        determineFitness();
        afterStep();
    }

    /**
     * Returns {@code true} if a stop criterion is satisfied, else returns
     * {@code false}.
     *
     * @return A boolean value that indicates if stop criterion is satisfied.
     *
     * **/
    public abstract boolean isDone();

    /**
     * Initializes the population. Extending classes that wish to call
     * {@link #step()} must ensure that this method is called exactly once
     * before.
     **/
    protected void initialize() {
        if (this.isInitialized) {
            throw new UnsupportedOperationException(
                    "Algorithm already initialized: initialize() must be called only once.");
        }
        this.generation = 0;
        this.isInitialized = true;
    }

    /** Determines fitness values for all individuals. **/
    protected abstract void determineFitness();

    /**
     * Selects parent individuals for recombination, depending on some strategy
     * for parent selection.
     **/
    protected abstract void select();

    /**
     * Generates offspring by recombining selected parent individuals, depending
     * on some recombination strategy.
     **/
    protected abstract void crossOver();

    /**
     * Mutates offspring, depending on some mutation strategy.
     **/
    protected abstract void mutate();

    /**
     * Selects individuals that shall be preserved and proceeds to the next
     * generation. According to the implemented survivors' selection strategy,
     * only newly generated individuals or also parent individuals may be
     * considered.
     **/
    protected abstract void survive();

    /**
     * Informs the listeners about an upcoming step.
     */
    private void beforeStep() {
        for (final IEvolutionListener listener : this.listeners) {
            listener.beforeStep();
        }
    }

    /**
     * Informs the listeners about a completed step.
     */
    private void afterStep() {
        for (final IEvolutionListener listener : this.listeners) {
            listener.afterStep();
        }
    }

    // private fields
    /** The number of the current generation. */
    private int generation = 0;

    /**
     * Indicates whether the algorithm has been initialized. Some methods
     * require it to be initialized.
     */
    private boolean isInitialized = false;

    /** The list of listeners to the evolutionary algorithm. */
    private final List<IEvolutionListener> listeners = new LinkedList<IEvolutionListener>();
}
