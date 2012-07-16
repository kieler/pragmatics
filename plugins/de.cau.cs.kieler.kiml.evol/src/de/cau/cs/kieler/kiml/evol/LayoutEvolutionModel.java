/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.evol;

import java.util.Map;
import java.util.Random;

import com.google.common.collect.Maps;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.kiml.LayoutContext;
import de.cau.cs.kieler.kiml.config.ILayoutConfig;
import de.cau.cs.kieler.kiml.evol.alg.AbstractEvolutionaryAlgorithm;
import de.cau.cs.kieler.kiml.evol.alg.CrossoverOperation;
import de.cau.cs.kieler.kiml.evol.alg.EvaluationOperation;
import de.cau.cs.kieler.kiml.evol.alg.MutationOperation;
import de.cau.cs.kieler.kiml.evol.alg.SurvivalOperation;
import de.cau.cs.kieler.kiml.evol.genetic.Genome;
import de.cau.cs.kieler.kiml.evol.genetic.Population;
import de.cau.cs.kieler.kiml.ui.diagram.LayoutMapping;

/**
 * The main class for access to evolutionary meta layout.
 * This class is a singleton: at most one evolution model is active at any time. This is a
 * great limitation, and it should be extended in the future: it should be possible to
 * link an evolution model with specific diagrams, diagram types, or use cases. View management
 * might be a feasible approach for this.
 *
 * @author msp
 */
public final class LayoutEvolutionModel extends AbstractEvolutionaryAlgorithm {
    
    /** the initial number of individuals to create. */
    private static final int INITIAL_POPULATION = 16;
    /** the mutation boost for the initial population. */
    private static final double INITIAL_MUTATION_BOOST = 3;
    /** the metric result value for 50% weight adaption. */
    private static final float HALF_WEIGHT_METRIC = 0.7f;
    
    /** the singleton instance. */
    private static LayoutEvolutionModel instance = new LayoutEvolutionModel();
    
    /**
     * Returns the active evolution model instance.
     * 
     * @return the active instance
     */
    public static LayoutEvolutionModel getInstance() {
        return instance;
    }
    
    /** the individual that was selected for meta layout. */
    private Genome selectedIndividual;
    
    /**
     * Hidden constructor to prevent instantiation from outside this class.
     */
    private LayoutEvolutionModel() {
        setCrossoverOperation(new CrossoverOperation());
        setMutationOperation(new MutationOperation());
        setSurvivalOperation(new SurvivalOperation());
        setEvaluationOperation(new EvaluationOperation());
        setRandom(new Random());
    }
    
    /**
     * Returns the last individual that was selected for meta layout.
     * 
     * @return the last selected individual
     */
    public Genome getSelected() {
        return selectedIndividual;
    }
    
    /**
     * Sets the individual with given index as selected for meta layout.
     * 
     * @param index a genome index
     */
    public void setSelected(final int index) {
        selectedIndividual = getPopulation().get(index);
    }
    
    /**
     * Initialize the population of the evolution model.
     * 
     * @param layoutMapping a layout mapping from which to derive an initial configuration
     * @param progressMonitor a progress monitor
     */
    public void initializePopulation(final LayoutMapping<?> layoutMapping,
            final IKielerProgressMonitor progressMonitor) {
        progressMonitor.begin("Initialize population", INITIAL_POPULATION + 2);
        
        Population population = new Population(2 * INITIAL_POPULATION);
        KNode graph = layoutMapping.getLayoutGraph();
        population.setProperty(Population.EVALUATION_GRAPH, graph);
        
        // create an initial gene, the patriarch
        Pair<ILayoutConfig, LayoutContext> configPair = GenomeFactory.createConfig(layoutMapping);
        population.setProperty(Population.DEFAULT_CONFIG, configPair.getFirst());
        population.setProperty(Population.DEFAULT_CONTEXT, configPair.getSecond());
        Genome patriarch = GenomeFactory.createInitialGenome(layoutMapping, configPair.getFirst(),
                configPair.getSecond());
        population.add(patriarch);
        progressMonitor.worked(1);
        
        // mutate the patriarch to create an initial population
        MutationOperation mutationOperation = (MutationOperation) getMutationOperation();
        do {
            int mutationCount = population.size();
            for (int i = 0; i < mutationCount; i++) {
                Genome mutation = mutationOperation.mutate(population.get(i), configPair.getFirst(),
                        configPair.getSecond(), INITIAL_MUTATION_BOOST);
                population.add(mutation);
                progressMonitor.worked(1);
            }
        } while (population.size() < INITIAL_POPULATION);
        
        // reset and evaluate the population
        setPopulation(population);
        
        progressMonitor.done();
    }
    
    /**
     * Adapt the metric weights of the current population according to the selected individuals.
     * 
     * @param selected an array of selections for the individuals in the population
     */
    public void adaptMetricWeights(final boolean[] selected) {
        Population population = getPopulation();
        assert selected.length == population.size();
        
        // aggregate the sum of metric results for selected individuals
        int selectCount = 0;
        Map<String, Float> resultSum = Maps.newHashMap();
        for (int i = 0; i < selected.length; i++) {
            if (selected[i]) {
                Map<String, Float> metricsResult = population.get(i).getProperty(
                        EvaluationOperation.METRIC_RESULT);
                for (Map.Entry<String, Float> entry : metricsResult.entrySet()) {
                    Float x = resultSum.get(entry.getKey());
                    if (x == null) {
                        resultSum.put(entry.getKey(), entry.getValue());
                    } else {
                        resultSum.put(entry.getKey(), x + entry.getValue());
                    }
                }
                selectCount++;
            }
        }
        
        Map<String, Double> metricWeights = population.getProperty(EvaluationOperation.METRIC_WEIGHT);
        if (metricWeights == null) {
            metricWeights = Maps.newHashMap();
            population.setProperty(EvaluationOperation.METRIC_WEIGHT, metricWeights);
        }
        
        // determine new adapted weights from the previous values and metric results
        for (Map.Entry<String, Float> entry : resultSum.entrySet()) {
            float average = entry.getValue() / selectCount;
            double newWeight;
            if (average >= HALF_WEIGHT_METRIC) {
                double w = (1 - average) / (1 - HALF_WEIGHT_METRIC);
                newWeight = 1 - w * w / 2;
            } else {
                double w = average / HALF_WEIGHT_METRIC;
                newWeight = w * w / 2;
            }
            Double oldWeight = metricWeights.get(entry.getKey());
            if (oldWeight != null) {
                newWeight = (oldWeight + newWeight) / 2;
            }
            metricWeights.put(entry.getKey(), newWeight);
        }
    }

}
