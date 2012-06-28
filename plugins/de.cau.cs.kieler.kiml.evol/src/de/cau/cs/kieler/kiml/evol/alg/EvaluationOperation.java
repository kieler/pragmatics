/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
/**
 *
 */
package de.cau.cs.kieler.kiml.evol.alg;

import java.util.Collections;
import java.util.Map;
import java.util.Random;

import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.ui.statushandlers.StatusManager;

import com.google.common.collect.Maps;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.kiml.IGraphLayoutEngine;
import de.cau.cs.kieler.kiml.LayoutContext;
import de.cau.cs.kieler.kiml.RecursiveGraphLayoutEngine;
import de.cau.cs.kieler.kiml.config.ILayoutConfig;
import de.cau.cs.kieler.kiml.evol.EvolPlugin;
import de.cau.cs.kieler.kiml.evol.GenomeFactory;
import de.cau.cs.kieler.kiml.evol.genetic.Genome;
import de.cau.cs.kieler.kiml.evol.genetic.Population;
import de.cau.cs.kieler.kiml.service.AnalysisService;
import de.cau.cs.kieler.kiml.service.grana.AnalysisCategory;
import de.cau.cs.kieler.kiml.service.grana.AnalysisData;

/**
 * Operation that evaluates individuals to determine their fitness.
 * 
 * @author msp
 */
public class EvaluationOperation extends AbstractAlgorithm implements IEvolutionaryOperation {
    
    /** identifier for the metric category. */
    public static final String METRIC_CATEGORY = "de.cau.cs.kieler.kiml.evol.metricCategory";
    /** identifier for the execution time metric. */
    public static final String EXEC_TIME_METRIC = "de.cau.cs.kieler.kiml.evol.executionTime";
    
    /** genome property for the layout graph created from the individual. */
    public static final IProperty<KNode> LAYOUT_GRAPH = new Property<KNode>("evol.layoutGraph");
    /** genome property for the results of layout metrics. */
    public static final IProperty<Map<String, Float>> METRIC_RESULT
            = new Property<Map<String, Float>>("evol.metricResult");

    /** population property for the weights of layout metrics. */
    public static final IProperty<Map<String, Double>> METRIC_WEIGHT
            = new Property<Map<String, Double>>("evol.metricWeight");
    
    /** the time base for execution time metric. */
    private static final float EXECTIME_TIMEBASE = 0.2f;
    /** the execution time result for the time base. */
    private static final float EXECTIME_RESULT = 0.5f;

    /** the graph layout engine used for executing configured layout on the evaluation graph. */
    private final IGraphLayoutEngine graphLayoutEngine = new RecursiveGraphLayoutEngine();
    
    /**
     * {@inheritDoc}
     */
    public void setRandom(final Random random) {
        // no random number generator is required
    }

    /**
     * {@inheritDoc}
     */
    public void process(final Population population) {
        getMonitor().begin("Evaluation", population.size());
        
        // determine fitness value for individuals that do not have one yet
        for (Genome genome : population) {
            Double fitness = genome.getProperty(Genome.FITNESS);
            if (fitness == null) {
                fitness = autoRate(genome, population, getMonitor().subTask(1));
                genome.setProperty(Genome.FITNESS, fitness);
            } else {
                getMonitor().worked(1);
            }
        }
        
        // sort the individuals by descending fitness
        Collections.sort(population);
        
        getMonitor().done();
    }
    
    /**
     * Compute an automatic rating for the given individual.
     * 
     * @param genome an individual
     * @param population the population the individual belongs to
     * @param progressMonitor a progress monitor
     * @return the automatic rating value
     */
    public double autoRate(final Genome genome, final Population population,
            final IKielerProgressMonitor progressMonitor) {
        progressMonitor.begin("Evaluation", 2);

        KNode testGraph = population.getProperty(Population.EVALUATION_GRAPH);
        ILayoutConfig layoutConfig = population.getProperty(Population.DEFAULT_CONFIG);
        LayoutContext layoutContext = population.getProperty(Population.DEFAULT_CONTEXT);

        // perform layout on the evaluation graph
        KNode graph = EcoreUtil.copy(testGraph);
        GenomeFactory.configureGraph(graph, genome, layoutConfig, layoutContext);
        double executionTime;
        try {
            IKielerProgressMonitor layoutMonitor = progressMonitor.subTask(1);
            graphLayoutEngine.layout(graph, layoutMonitor);
            executionTime = layoutMonitor.getExecutionTime();
            genome.setProperty(LAYOUT_GRAPH, graph);
        } catch (Throwable throwable) {
            // automatic layout led to an error - give the genome a bad rating!
            return 0;
        }
        
        // perform analysis on the evaluation graph
        AnalysisService analysisService = AnalysisService.getInstance();
        AnalysisCategory category = analysisService.getCategory(METRIC_CATEGORY);
        Map<String, Object> results = analysisService.analyze(graph, category.getAnalyses(),
                progressMonitor.subTask(1));
        
        Map<String, Double> metricWeights = population.getProperty(METRIC_WEIGHT);
        if (metricWeights == null) {
            metricWeights = Collections.emptyMap();
        }
        Map<String, Float> metricResults = Maps.newHashMap();
        genome.setProperty(METRIC_RESULT, metricResults);
        
        // determine the weighted mean of the analysis results
        double rating = 0;
        double totalWeight = 0;
        for (AnalysisData analysisData : category.getAnalyses()) {
            String analysisId = analysisData.getId();
            Object result = results.get(analysisId);
            if (result instanceof Float) {
                float floatResult = (Float) result;
                Double weight = metricWeights.get(analysisId);
                if (weight == null) {
                    rating += floatResult;
                    totalWeight += 1;
                } else {
                    rating += weight * floatResult;
                    totalWeight += weight;
                }
                metricResults.put(analysisId, floatResult);
            } else {
                StatusManager.getManager().handle(new Status(Status.ERROR, EvolPlugin.PLUGIN_ID,
                        "Analysis \"" + analysisData.getName() + "\" failed: " + result));
            }
        }
        
        // consider the execution time as special metric
        float execTimeResult;
        if (executionTime >= EXECTIME_TIMEBASE) {
            execTimeResult = EXECTIME_RESULT * EXECTIME_TIMEBASE / (float) executionTime;
        } else {
            execTimeResult = 1 - (float) executionTime / EXECTIME_TIMEBASE * (1 - EXECTIME_RESULT);
        }
        Double weight = metricWeights.get(EXEC_TIME_METRIC);
        if (weight == null) {
            rating += execTimeResult;
            totalWeight += 1;
        } else {
            rating += weight * execTimeResult;
            totalWeight += weight;
        }
        metricResults.put(EXEC_TIME_METRIC, execTimeResult);
        
        double result;
        if (totalWeight == 0) {
            result = 0;
        } else {
            result = rating / totalWeight;
        }
            
        progressMonitor.done();
        return result;
    }

}
