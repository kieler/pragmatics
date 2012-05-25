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

import org.eclipse.emf.ecore.util.EcoreUtil;

import de.cau.cs.kieler.core.alg.BasicProgressMonitor;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.kiml.IGraphLayoutEngine;
import de.cau.cs.kieler.kiml.LayoutContext;
import de.cau.cs.kieler.kiml.RecursiveGraphLayoutEngine;
import de.cau.cs.kieler.kiml.config.ILayoutConfig;
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
public class EvaluationOperation implements IEvolutionaryOperation {
    
    /** identifier for the metric category. */
    public static final String METRIC_CATEGORY = "de.cau.cs.kieler.kiml.evol.metricCategory";
    
    /** genome property for the layout graph created from the individual. */
    public static final IProperty<KNode> LAYOUT_GRAPH = new Property<KNode>("evol.layoutGraph");

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
        KNode testGraph = population.getProperty(Population.EVALUATION_GRAPH);
        ILayoutConfig layoutConfig = population.getProperty(Population.DEFAULT_CONFIG);
        LayoutContext layoutContext = population.getProperty(Population.DEFAULT_CONTEXT);
        
        // determine fitness value for individuals that do not have one yet
        for (Genome genome : population) {
            Double fitness = genome.getProperty(Genome.FITNESS);
            if (fitness == null) {
                Double autoRating = genome.getProperty(Genome.AUTO_RATING);
                if (autoRating == null) {
                    autoRating = autoRate(genome, testGraph, layoutConfig, layoutContext,
                            new BasicProgressMonitor());
                    genome.setProperty(Genome.AUTO_RATING, autoRating);
                }
                double userRating = genome.getProperty(Genome.USER_RATING);
                double userWeight = genome.getProperty(Genome.USER_WEIGHT);
                fitness = userRating * userWeight + autoRating * (1 - userWeight);
                genome.setProperty(Genome.FITNESS, fitness);
            }
        }
        
        // sort the individuals by descending fitness
        Collections.sort(population);
    }
    
    /**
     * Compute an automatic rating for the given individual.
     * 
     * @param genome an individual
     * @param testGraph the graph used for evaluation
     * @param layoutConfig the layout configuration used to obtain default values
     * @param layoutContext the layout context used to obtain default values
     * @param progressMonitor a progress monitor
     * @return the automatic rating value
     */
    public double autoRate(final Genome genome, final KNode testGraph, final ILayoutConfig layoutConfig,
            final LayoutContext layoutContext, final IKielerProgressMonitor progressMonitor) {
        progressMonitor.begin("Evaluation", 2);

        // perform layout on the evaluation graph
        KNode graph = EcoreUtil.copy(testGraph);
        GenomeFactory.configureGraph(graph, genome, layoutConfig, layoutContext);
        graphLayoutEngine.layout(graph, progressMonitor.subTask(1));
        genome.setProperty(LAYOUT_GRAPH, graph);
        
        // perform analysis on the evaluation graph
        AnalysisService analysisService = AnalysisService.getInstance();
        AnalysisCategory category = analysisService.getCategory(METRIC_CATEGORY);
        Map<String, Object> results = analysisService.analyze(graph, category.getAnalyses(),
                progressMonitor.subTask(1));
        
        // determine the weighted mean of the analysis results
        float rating = 0;
        float totalWeight = category.getAnalyses().size();
        for (AnalysisData analysisData : category.getAnalyses()) {
            rating += (Float) results.get(analysisData.getId());
        }
        
        progressMonitor.done();
        return rating / totalWeight;
    }

}
