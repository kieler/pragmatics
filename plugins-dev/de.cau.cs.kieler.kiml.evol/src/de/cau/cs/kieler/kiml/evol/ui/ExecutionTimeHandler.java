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
package de.cau.cs.kieler.kiml.evol.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.statushandlers.StatusManager;

import com.google.common.collect.Maps;

import de.cau.cs.kieler.core.alg.BasicProgressMonitor;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.LayoutDataService;
import de.cau.cs.kieler.kiml.LayoutOptionData;
import de.cau.cs.kieler.kiml.evol.EvolPlugin;
import de.cau.cs.kieler.kiml.evol.LayoutEvolutionModel;
import de.cau.cs.kieler.kiml.evol.alg.EvaluationOperation;
import de.cau.cs.kieler.kiml.evol.genetic.Population;
import de.cau.cs.kieler.kiml.formats.GraphFormatsService;
import de.cau.cs.kieler.kiml.grana.AnalysisCategory;
import de.cau.cs.kieler.kiml.grana.AnalysisData;
import de.cau.cs.kieler.kiml.grana.AnalysisService;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.service.LayoutMapping;

/**
 * A command handler that measures the execution time of evolutionary layout.
 * 
 * @author msp
 */
public class ExecutionTimeHandler extends AbstractHandler {
    
    // CHECKSTYLEOFF VisibilityModifier
    
    /** the time for executing layout algorithms. */
    public static double algorithmTime;
    /** the time for executing graph analyses. */
    public static double analysisTime;
    /** the number of layout evaluations. */
    public static int evaluations;
    
    /** the list of considered layout options. */
    private List<LayoutOptionData<?>> layoutOptions;
    /** the random number generator. */
    private Random random;
    
    /**
     * {@inheritDoc}
     */
    public final Object execute(final ExecutionEvent event) throws ExecutionException {
        ISelection selection = HandlerUtil.getCurrentSelection(event);
        if (selection instanceof IStructuredSelection) {
            
            final Object[] elements = ((IStructuredSelection) selection).toArray();
            Job job = new Job("Measure Execution Time") {
                protected IStatus run(final IProgressMonitor monitor) {
                    monitor.beginTask("Measure Execution Time", elements.length);
                    initialize();
                    for (Object object : elements) {
                        if (monitor.isCanceled()) {
                            break;
                        }
                        if (object instanceof IFile) {
                            IFile inputFile = (IFile) object;
                            // measure execution time for the current input file
                            measureFile(inputFile);
                        }
                        monitor.worked(1);
                    }
                    monitor.done();
                    return Status.OK_STATUS;
                }
                
            };
            job.setUser(true);
            job.schedule();
        }
        
        return null;
    }
    
    private void initialize() {
        layoutOptions = new ArrayList<LayoutOptionData<?>>(
                LayoutDataService.getInstance().getOptionData().size());
        for (LayoutOptionData<?> data : LayoutDataService.getInstance().getOptionData()) {
            if (LayoutOptions.ALGORITHM.equals(data)
                    || (data.getTargets().contains(LayoutOptionData.Target.PARENTS)
                    && data.getVariance() > 0 && typeSupported(data.getType()))) {
                layoutOptions.add(data);
            }
        }
        random = new Random();
    }
    
    /**
     * Determine whether the given layout option type is supported by evolutionary layout.
     * 
     * @param type a layout option type
     * @return true if the type is supported
     */
    private boolean typeSupported(final LayoutOptionData.Type type) {
        switch (type) {
        case BOOLEAN:
        case ENUM:
        case INT:
        case FLOAT:
            return true;
        default:
            return false;
        }
    }
    
    /**
     * Measure execution time for the given file.
     * 
     * @param file a graph file
     */
    private void measureFile(final IFile file) {
        try {
            System.out.print(file.getName() + ", ");
            KNode[] graphs = GraphFormatsService.getInstance().loadKGraph(file);
            
            System.out.print(graphs[0].getChildren().size() + ", ");
            measureGraph(graphs[0]);
            
        } catch (CoreException exception) {
            StatusManager.getManager().handle(exception, EvolPlugin.PLUGIN_ID);
        } catch (IOException exception) {
            IStatus status = new Status(IStatus.ERROR, EvolPlugin.PLUGIN_ID,
                    "Error while loading the graph file " + file.getName(), exception);
            StatusManager.getManager().handle(status, StatusManager.SHOW | StatusManager.LOG);
        }
    }
    
    /** the number of evolution steps to perform for each graph.*/
    private static final int STEPS = 5;
    
    /**
     * Measure execution time for the given graph.
     * 
     * @param graph a graph
     */
    private void measureGraph(final KNode graph) {
        LayoutMapping<?> layoutMapping = new LayoutMapping<Object>(null);
        layoutMapping.setLayoutGraph(graph);
        LayoutEvolutionModel evolutionModel = LayoutEvolutionModel.getInstance();
        // assign random weights
        Population population = evolutionModel.getPopulation();
        Map<String, Double> metricWeights = population.getProperty(EvaluationOperation.METRIC_WEIGHT);
        if (metricWeights == null) {
            metricWeights = Maps.newHashMap();
            population.setProperty(EvaluationOperation.METRIC_WEIGHT, metricWeights);
        }
        AnalysisCategory category = AnalysisService.getInstance().getCategory(
                EvaluationOperation.METRIC_CATEGORY);
        for (AnalysisData analysisData : category.getAnalyses()) {
            metricWeights.put(analysisData.getId(), random.nextDouble());
        }
        // initialize the population
        evolutionModel.initializePopulation(layoutMapping, layoutOptions,
                null, new BasicProgressMonitor(0));
        
        // perform measurements
        double totalTime = 0;
        algorithmTime = 0;
        analysisTime = 0;
        evaluations = 0;
        for (int i = 0; i < STEPS; i++) {
            IKielerProgressMonitor progressMonitor = new BasicProgressMonitor();
            evolutionModel.step(progressMonitor);
            totalTime += progressMonitor.getExecutionTime();
        }
        System.out.println((totalTime / STEPS) + ", " + (algorithmTime / STEPS)
                + ", " + (analysisTime / STEPS) + ", " + ((double) evaluations / STEPS));
    }
   
}
