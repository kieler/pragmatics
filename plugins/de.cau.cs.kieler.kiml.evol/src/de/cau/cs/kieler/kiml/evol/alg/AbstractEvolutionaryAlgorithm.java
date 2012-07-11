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

import java.util.Random;

import de.cau.cs.kieler.core.alg.BasicProgressMonitor;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.kiml.evol.genetic.Population;

/**
 * Abstract implementation of an evolutionary algorithm. Implementations of
 * evolutionary algorithms shall inherit from this class. After construction,
 * {@link #step()} can be used for stepwise execution. In this case,
 * {@link #reset()} must be called explicitly once before.
 *
 * @kieler.design 2011-07-08 reviewed by swe, ima, msp
 * @author bdu
 */
public abstract class AbstractEvolutionaryAlgorithm {
    
    /**
     * Null Operation: leaves the given {@link Population} unaltered. Use this as
     * dummy if you don't want an operation to be done.
     */
    private static final IEvolutionaryOperation NULL_OPERATION = new IEvolutionaryOperation() {
            public void process(final Population thepopulation) {
                // Intentionally left empty.
            }
            public void setRandom(final Random random) { }
            public void reset() { }
            public void reset(final IKielerProgressMonitor monitor) { }
            public void setProgressMonitor(final IKielerProgressMonitor monitor) { }
    };

    /** The number of the current generation. */
    private int generationNumber = 0;

    /** The current population. */
    private Population population;

    /** The mutation operation. */
    private IEvolutionaryOperation mutationOperation = NULL_OPERATION;

    /** The cross over operation. */
    private IEvolutionaryOperation crossoverOperation = NULL_OPERATION;

    /** The survival operation. */
    private IEvolutionaryOperation survivalOperation = NULL_OPERATION;

    /** The evaluation operation. */
    private IEvolutionaryOperation evaluationOperation = NULL_OPERATION;

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
     * Performs a single step of the algorithm by proceeding to the next generation.
     * Before a step can be performed, a population must be set using
     * {@link #setPopulation(Population)}. Furthermore, a random number generator needs
     * to be set using {@link #setRandom(Random)}.
     * 
     * @param progressMonitor a progress monitor
     */
    public final void step(final IKielerProgressMonitor progressMonitor) {
        generationNumber++;
        progressMonitor.begin("Evolution cycle " + generationNumber, 1 + 1 + 1 + 1);
        
        crossover(progressMonitor.subTask(1));
        
        mutate(progressMonitor.subTask(1));
        
        evaluate(progressMonitor.subTask(1));
        
        survive(progressMonitor.subTask(1));
        
        progressMonitor.done();
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
     * Sets the population, evaluates it, and resets the generation number.
     * 
     * @param p the population
     */
    public final void setPopulation(final Population p) {
        generationNumber = 0;
        this.population = p;
        evaluate(new BasicProgressMonitor());
    }

    /**
     * Determines fitness values for all individuals and sorts the individuals by this fitness,
     * with highest fitness first.
     * 
     * @param monitor a progress monitor
     */
    protected final void evaluate(final IKielerProgressMonitor monitor) {
        evaluationOperation.reset(monitor);
        evaluationOperation.process(population);
    }

    /**
     * Generates offspring by recombining selected parent individuals, depending
     * on some recombination strategy. The basic idea of recombination is to
     * build new combinations of features that exist in the population, thus
     * hopefully combining advantageous features from several individuals in a
     * single one. The genetic material of two or more parent individuals is put
     * together to produce one or more offspring. How this is done may vary
     * widely among different implementations.
     * 
     * @param monitor a progress monitor
     */
    protected final void crossover(final IKielerProgressMonitor monitor) {
        crossoverOperation.reset(monitor);
        crossoverOperation.process(population);
    }

    /**
     * Mutates offspring, depending on some mutation strategy. The mutation
     * operation serves to introduce new genetic material into the population by
     * altering the existing material in a random fashion.
     * 
     * @param monitor a progress monitor
     */
    protected final void mutate(final IKielerProgressMonitor monitor) {
        mutationOperation.reset(monitor);
        mutationOperation.process(population);
    }

    /**
     * Selects individuals that shall be preserved, and proceeds to the next
     * generation. Only the individuals selected for survival will be members of
     * the next generation. According to the implemented survivors' selection
     * strategy, only newly generated individuals or also parent individuals may
     * be considered for survival. The fitness of an individual should have a
     * major influence on its chance for survival.
     * 
     * @param monitor a progress monitor
     */
    protected final void survive(final IKielerProgressMonitor monitor) {
        survivalOperation.reset(monitor);
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
     * Returns the crossover operation, which is used for {@link #crossover()}.
     * 
     * @return the crossover operation
     */
    protected final IEvolutionaryOperation getCrossoverOperation() {
        return this.crossoverOperation;
    }

    /**
     * Sets the crossover operation, which is used for {@link #crossover()}.
     * 
     * @param theCrossoverOperation
     *            the crossover operation to set
     */
    protected final void setCrossoverOperation(final IEvolutionaryOperation theCrossoverOperation) {
        this.crossoverOperation = theCrossoverOperation;
    }

    /**
     * Returns the evaluation operation, which is used for {@link #evaluate()}.
     * 
     * @return the evaluation operation
     */
    protected final IEvolutionaryOperation getEvaluationOperation() {
        return this.evaluationOperation;
    }

    /**
     * Sets the evaluation operation, which is used for {@link #evaluate()}.
     * 
     * @param theEvaluationOperation
     *            the evaluation operation to set
     */
    protected final void setEvaluationOperation(final IEvolutionaryOperation theEvaluationOperation) {
        this.evaluationOperation = theEvaluationOperation;
    }
    
    /**
     * Set the given random number generator for the contained evolutionary operations.
     * 
     * @param random a random number generator
     */
    public void setRandom(final Random random) {
        getCrossoverOperation().setRandom(random);
        getMutationOperation().setRandom(random);
        getEvaluationOperation().setRandom(random);
        getSurvivalOperation().setRandom(random);
    }

}
