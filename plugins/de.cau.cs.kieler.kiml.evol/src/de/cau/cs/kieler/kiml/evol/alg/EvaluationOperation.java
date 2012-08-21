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
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.ui.statushandlers.StatusManager;

import com.google.common.collect.Maps;

import de.cau.cs.kieler.core.WrappedException;
import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.core.alg.BasicProgressMonitor;
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
    public static final IProperty<KNode> LAYOUT_GRAPH
            = new Property<KNode>("evol.layoutGraph");
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
    /** the executor service used to perform evaluations. */
    private final ExecutorService executorService;
    /** the graph layout metrics used for evaluation. */
    private final List<AnalysisData> metrics;
    /** the sequence of analyses to execute for evaluating graph layout metrics. */
    private final List<AnalysisData> analysisSequence;
    
    /**
     * Create a single-threaded evaluation operation.
     */
    public EvaluationOperation() {
        this(null);
    }
    
    /**
     * Create a possibly multi-threaded evaluation operation.
     * 
     * @param executorService the executor service for multi-threaded execution, or {@code null}
     *          if all evaluations shall be done in the calling thread
     */
    public EvaluationOperation(final ExecutorService executorService) {
        this.executorService = executorService;
        
        // determine the sequence of graph layout analysis to evaluate
        AnalysisService analysisService = AnalysisService.getInstance();
        metrics = analysisService.getCategory(METRIC_CATEGORY).getAnalyses();
        analysisSequence = analysisService.getExecutionOrder(metrics);
    }
    
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
        
        if (executorService == null) {
            // single-threaded execution
            for (Genome genome : population) {
                Double fitness = genome.getProperty(Genome.FITNESS);
                if (fitness == null) {
                    fitness = autoRate(genome, population, getMonitor().subTask(1));
                    genome.setProperty(Genome.FITNESS, fitness);
                } else {
                    getMonitor().worked(1);
                }
            }
            
        } else {
            // multi-threaded execution: submit tasks to the executor service
            LinkedList<Future<?>> futures = new LinkedList<Future<?>>();
            for (final Genome genome : population) {
                if (genome.getProperty(Genome.FITNESS) == null) {
                    Future<?> future = executorService.submit(new Runnable() {
                        public void run() {
                            double fitness = autoRate(genome, population, new BasicProgressMonitor(0));
                            genome.setProperty(Genome.FITNESS, fitness);
                        }
                    });
                    futures.addLast(future);
                } else {
                    getMonitor().worked(1);
                }
            }
            
            // wait for all tasks to finish execution
            while (!futures.isEmpty()) {
                try {
                    // this call waits if necessary for the computation to complete
                    futures.removeFirst().get();
                    getMonitor().worked(1);
                } catch (Exception exception) {
                    throw new WrappedException(exception);
                }
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
        Map<String, Object> results = AnalysisService.getInstance().analyzePresorted(
                graph, analysisSequence, progressMonitor.subTask(1));
        
        Map<String, Double> metricWeights = population.getProperty(METRIC_WEIGHT);
        if (metricWeights == null) {
            metricWeights = Collections.emptyMap();
        }
        Map<String, Float> metricResults = Maps.newHashMap();
        genome.setProperty(METRIC_RESULT, metricResults);
        
        // determine the weighted mean of the analysis results
        double rating = 0;
        double totalWeight = 0;
        for (AnalysisData analysisData : metrics) {
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
