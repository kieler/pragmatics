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
 * evolutionary algorithms shall inherit from this class.
 *
 *
 * @author bdu
 *
 */
public abstract class AbstractEvolutionaryAlgorithm implements Runnable {

    /**
     * Adds an evolution listener.
     *
     * @param listener
     *            the listener to add
     */
    public void addListener(final IEvolutionListener listener) {
        this.listeners.add(listener);
    }

    /**
     * Removes the specified evolution listener.
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
     * Main loop for running the complete algorithm. The algorithm will run
     * until some stop criterion is satisfied.
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
        }
    }

    /**
     * Performs a step of the algorithm by proceeding to the next generation.
     * The algorithm must be initialized before by calling {@code initialize()}.
     */
    public final void step() {
        beforeStep();
        if (this.isInitialized && !isDone()) {
            if (this.generation > 0) {
                survive();
            }
            this.generation++;
            select();
            crossOver();
            mutate();
            determineFitness();
        } else {
            // do nothing
            System.out.println("Cannot perform step.");
        }
        afterStep();
    }

    /**
     * Inform the listeners about a completed step.
     */
    private void afterStep() {
        for (final IEvolutionListener listener : this.listeners) {
            listener.afterStep();
        }
    }

    /**
     * Inform the listeners about an upcoming step.
     */
    private void beforeStep() {
        for (final IEvolutionListener listener : this.listeners) {
            listener.beforeStep();
        }

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
     * Initialize population. Extending classes must ensure that this is called
     * exactly once before using {@code step()}.
     **/
    protected void initialize() {
        if (this.isInitialized) {
            System.err.println("Warning: Algorithm already initialized.");
        } else {
            this.generation = 0;
            this.isInitialized = true;
        }
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
     * Selects individuals that shall be preserved and proceed to next
     * generation. According to the implemented survivors' selection strategy,
     * only newly generated individuals or also parent individuals may be
     * considered.
     **/
    protected abstract void survive();

    // private fields
    private int generation = 0;
    private boolean isInitialized = false;
    private final List<IEvolutionListener> listeners = new LinkedList<IEvolutionListener>();
}
