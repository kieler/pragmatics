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

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.kiml.evol.genetic.Population;

/**
 * Abstract implementation of an evolutionary algorithm. Implementations of
 * evolutionary algorithms shall inherit from this class. After construction,
 * {@link #step()} can be used for stepwise execution. In this case,
 * {@link #reset()} must be called explicitly once before.
 *
 * @kieler.rating 2011-07-08 yellow reviewed by swe, ima, msp
 * @author bdu
 */
public abstract class AbstractEvolutionaryAlgorithm extends AbstractAlgorithm {
    
    /**
     * Null Operation: leaves the given {@link Population} unaltered. Use this as
     * dummy if you don't want an operation to be done.
     */
    private static class NullOperation implements IEvolutionaryOperation {
        /**
         * {@inheritDoc}
         */
        public void process(final Population population) {
            // Intentionally left empty.
        }
    }

    /** The number of the current generation. */
    private int generationNumber = 0;

    /** The current population. */
    private final Population population = new Population();

    /** The selection operation. */
    private IEvolutionaryOperation selectionOperation = new NullOperation();

    /** The mutation operation. */
    private IEvolutionaryOperation mutationOperation = new NullOperation();

    /** The cross over operation. */
    private IEvolutionaryOperation crossoverOperation = new NullOperation();

    /** The survival operation. */
    private IEvolutionaryOperation survivalOperation = new NullOperation();

    /** The evaluation operation. */
    private IEvolutionaryOperation evaluationOperation = new NullOperation();

    /**
     * Returns the generation number. The generation number is initially
     * <code>0</code>. It is increased by <code>1</code> in each step of the
     * algorithm.
     *
     * @return the generation number
     */
    public final int getGenerationNumber() {
        return generationNumber;
    }

    /**
     * Performs a single step of the algorithm by proceeding to the next
     * generation. The algorithm must be initialized before by calling
     * {@link #reset()}.
     *
     * @throws IllegalStateException
     *             if called after the stop criterion has been satisfied.
     */
    public final void step() {

        if (isDone()) {
            throw new IllegalStateException(
                    "No further steps may be performed after the stop criterion has been satisfied.");
        }

        if (generationNumber > 0) {
            survive();
        }
        generationNumber++;
        select();
        crossOver();
        mutate();
        determineFitness();
    }

    /**
     * Returns {@code true} if a stop criterion is satisfied, else returns
     * {@code false}.
     *
     * @return A boolean value that indicates if stop criterion is satisfied.
     *
     */
    public abstract boolean isDone();

    /**
     * Initializes the population. Extending classes that wish to call
     * {@link #step()} must ensure that this method is called exactly once
     * before.
     */
    @Override
    public void reset() {
        super.reset();
        generationNumber = 0;
    }

    /**
     * Returns the collection of individuals.
     *
     * @return population
     */
    public final Population getPopulation() {
        return population;
    }

    /**
     * Determines fitness values for all individuals.
     */
    protected final void determineFitness() {
        evaluationOperation.process(population);
    }

    /**
     * Selects parent individuals for recombination, depending on some strategy
     * for parent selection. This can be the entire current population or a
     * subset thereof.
     */
    protected final void select() {
        selectionOperation.process(population);
    }

    /**
     * Generates offspring by recombining selected parent individuals, depending
     * on some recombination strategy. The basic idea of recombination is to
     * build new combinations of features that exist in the population, thus
     * hopefully combining advantageous features from several individuals in a
     * single one. The genetic material of two or more parent individuals is put
     * together to produce one or more offspring. How this is done may vary
     * widely among different implementations.
     */
    protected final void crossOver() {
        crossoverOperation.process(population);
    }

    /**
     * Mutates offspring, depending on some mutation strategy. The mutation
     * operation serves to introduce new genetic material into the population by
     * altering the existing material in a random fashion.
     */
    protected final void mutate() {
        mutationOperation.process(population);
    }

    /**
     * Selects individuals that shall be preserved, and proceeds to the next
     * generation. Only the individuals selected for survival will be members of
     * the next generation. According to the implemented survivors' selection
     * strategy, only newly generated individuals or also parent individuals may
     * be considered for survival. The fitness of an individual should have a
     * major influence on its chance for survival.
     */
    protected final void survive() {
        survivalOperation.process(population);
    }

    /**
     * Returns the survival operation, which is used for {@link #survive()}.
     * 
     * @return the survival operation
     */
    protected final IEvolutionaryOperation getSurvivalOperation() {
        return this.survivalOperation;
    }

    /**
     * Sets the survival operation, which is used for {@link #survive()}.
     * 
     * @param theSurvivalOperation
     *            the survival operation to set
     */
    protected final void setSurvivalOperation(final IEvolutionaryOperation theSurvivalOperation) {
        this.survivalOperation = theSurvivalOperation;
    }

    /**
     * Returns the selection operation, which is used for {@link #select()}.
     * 
     * @return the selection operation
     */
    protected final IEvolutionaryOperation getSelectionOperation() {
        return this.selectionOperation;
    }

    /**
     * Sets the selection operation, which is used for {@link #select()}.
     * 
     * @param theSelectionOperation
     *            the selection operation to set
     */
    protected final void setSelectionOperation(final IEvolutionaryOperation theSelectionOperation) {
        this.selectionOperation = theSelectionOperation;
    }

    /**
     * Returns the mutation operation, which is used for {@link #mutate()}.
     * 
     * @return the mutation operation
     */
    protected final IEvolutionaryOperation getMutationOperation() {
        return this.mutationOperation;
    }

    /**
     * Sets the mutation operation, which is used for {@link #mutate()}.
     * 
     * @param theMutationOperation
     *            the mutation operation to set
     */
    protected final void setMutationOperation(final IEvolutionaryOperation theMutationOperation) {
        this.mutationOperation = theMutationOperation;
    }

    /**
     * Returns the crossover operation, which is used for {@link #crossOver()}.
     * 
     * @return the crossover operation
     */
    protected final IEvolutionaryOperation getCrossoverOperation() {
        return this.crossoverOperation;
    }

    /**
     * Sets the crossover operation, which is used for {@link #crossOver()}.
     * 
     * @param theCrossoverOperation
     *            the crossover operation to set
     */
    protected final void setCrossoverOperation(final IEvolutionaryOperation theCrossoverOperation) {
        this.crossoverOperation = theCrossoverOperation;
    }

    /**
     * Returns the evaluation operation, which is used for {@link #determineFitness()}.
     * 
     * @return the evaluation operation
     */
    protected final IEvolutionaryOperation getEvaluationOperation() {
        return this.evaluationOperation;
    }

    /**
     * Sets the evaluation operation, which is used for {@link #determineFitness()}.
     * 
     * @param theEvaluationOperation
     *            the evaluation operation to set
     */
    protected final void setEvaluationOperation(final IEvolutionaryOperation theEvaluationOperation) {
        this.evaluationOperation = theEvaluationOperation;
    }

}
