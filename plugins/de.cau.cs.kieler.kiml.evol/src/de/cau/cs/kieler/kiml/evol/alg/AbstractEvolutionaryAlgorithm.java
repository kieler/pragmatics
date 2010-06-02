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
     * Return the generation.
     * 
     * @return the generation
     */
    public int getGeneration() {
        return generation;
    }
    
    /**
     * Main loop for running the algorithm. The algorithm will run until some
     * stop criterion is satisfied.
     */
    public void run() {
        if (!isInitialized) {
            initialize();
            determineFitness();
            while (!isDone()) {
                step();
                // TODO: pause here for stepwise execution
            }
            isInitialized = false;
        }
    }
    
    /**
     * Perform a step of the algorithm by proceeding to the next generation.
     */
    public final void step() {
        if (isInitialized && !isDone()) {
            if (generation > 0) {
                survive();
            } else {
                mutate();
            }
            generation++;
            select();
            crossOver();
            mutate();
            determineFitness();
        } else {
            // do nothing
            System.out.println("Cannot perform step.");
        }
    }
    
    /**
     * Return <code>true</code> if a stop criterion is satisfied, else return
     * <code>false</code>.
     * 
     * @return A boolean value that indicates if stop criterion is satisfied.
     * 
     * **/
    public abstract boolean isDone();
    
    /** Initialize population. **/
    protected void initialize() {
        if (isInitialized) {
            System.out.println("Warning: Algorithm already initialized.");
        } else {
            generation = 0;
            isInitialized = true;
        }
    }
    
    /** Determine fitness values for all individuals. **/
    protected abstract void determineFitness();
    
    /**
     * Select parent individuals for recombination, depending on some strategy
     * for parent selection.
     **/
    protected abstract void select();
    
    /**
     * Generate offspring by recombining selected parent individuals, depending
     * on some recombination strategy.
     **/
    protected abstract void crossOver();
    
    /**
     * Mutate offspring, depending on some mutation strategy.
     **/
    protected abstract void mutate();
    
    /**
     * Select individuals that shall be preserved and proceed to next
     * generation. According to the implemented survivors' selection strategy,
     * only newly generated individuals or also parent individuals may be
     * considered.
     **/
    protected abstract void survive();
    
    // private fields
    private int generation = 0;
    private boolean isInitialized = false;
}
