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

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.statushandlers.StatusManager;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.LayoutAlgorithmData;
import de.cau.cs.kieler.kiml.LayoutDataService;
import de.cau.cs.kieler.kiml.LayoutOptionData;
import de.cau.cs.kieler.kiml.LayoutTypeData;
import de.cau.cs.kieler.kiml.config.DefaultLayoutConfig;
import de.cau.cs.kieler.kiml.config.LayoutContext;
import de.cau.cs.kieler.kiml.evol.EvolPlugin;
import de.cau.cs.kieler.kiml.evol.alg.EvaluationOperation;
import de.cau.cs.kieler.kiml.evol.genetic.Gene;
import de.cau.cs.kieler.kiml.evol.genetic.Genome;
import de.cau.cs.kieler.kiml.evol.genetic.TypeInfo;
import de.cau.cs.kieler.kiml.grana.AnalysisData;
import de.cau.cs.kieler.kiml.grana.AnalysisService;
import de.cau.cs.kieler.kiml.options.LayoutOptions;

/**
 * A command handler that tests the available layout metrics.
 * 
 * @author msp
 */
public class TestMetricsHandler extends AbstractHandler {
    
    /** the graph layout metrics used for evaluation. */
    private List<AnalysisData> metrics;
    /** the sequence of analyses to execute for evaluating graph layout metrics. */
    private List<AnalysisData> analysisSequence;
    /** the layout options to consider for creating genomes. */
    private Collection<LayoutOptionData<?>> options;
    
    /**
     * {@inheritDoc}
     */
    public final Object execute(final ExecutionEvent event) throws ExecutionException {
        ISelection selection = HandlerUtil.getCurrentSelection(event);
        if (selection instanceof IStructuredSelection) {
            initialize();
            
            final Object[] elements = ((IStructuredSelection) selection).toArray();
            Job job = new Job("Test Layout Metrics") {
                protected IStatus run(final IProgressMonitor monitor) {
                    monitor.beginTask("Test Layout Metrics", elements.length);
                    for (Object object : elements) {
                        if (monitor.isCanceled()) {
                            break;
                        }
                        if (object instanceof IFile) {
                            testFile((IFile) object);
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
        // determine the sequence of graph layout analyses to evaluate
        AnalysisService analysisService = AnalysisService.getInstance();
        metrics = analysisService.getCategory(EvaluationOperation.METRIC_CATEGORY).getAnalyses();
        analysisSequence = analysisService.getExecutionOrder(metrics);
        
        // gather the layout options
        options = new LinkedList<LayoutOptionData<?>>(LayoutDataService.getInstance().getOptionData());
        Iterator<LayoutOptionData<?>> optionIter = options.iterator();
        while (optionIter.hasNext()) {
            LayoutOptionData<?> optionData = optionIter.next();
            boolean accept = LayoutOptions.ALGORITHM.equals(optionData)
                        || (optionData.getTargets().contains(LayoutOptionData.Target.PARENTS)
                        && optionData.getVariance() > 0 && typeSupported(optionData.getType()));
            if (!accept) {
                optionIter.remove();
            }
        }
    }
    
    /**
     * Determine whether the given layout option type is supported by evolutionary layout.
     * 
     * @param type a layout option type
     * @return true if the type is supported
     */
    private static boolean typeSupported(final LayoutOptionData.Type type) {
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
     * Process a source file.
     * 
     * @param file a source file
     */
    private void testFile(final IFile file) {
        try {
            // Create a resource set.
            ResourceSet resourceSet = new ResourceSetImpl();
            // Demand load the resource for this file.
            Resource resource = resourceSet.getResource(URI.createPlatformResourceURI(
                    file.getFullPath().toString(), false), true);
            if (!resource.getContents().isEmpty()) {
                EObject content = resource.getContents().get(0);
                if (content instanceof KNode) {
                    testGraph((KNode) content);
                }
            }
        } catch (Exception exception) {
            IStatus status = new Status(IStatus.ERROR, EvolPlugin.PLUGIN_ID,
                    "Error while converting the selected graph.", exception);
            StatusManager.getManager().handle(status, StatusManager.SHOW | StatusManager.LOG);
        }
    }
    
    private void testGraph(final KNode graph) {
        
    }
    
    /**
     * Create a genome with random values.
     * 
     * @return a genome filled with genes
     */
    private Genome createRandomGenome() {
        LayoutDataService dataService = LayoutDataService.getInstance();
        LayoutOptionData<?> algoOptionData = dataService.getOptionData(
                LayoutOptions.ALGORITHM.getId());
        LayoutOptionData<?> diagTypeData = dataService.getOptionData(
                LayoutOptions.DIAGRAM_TYPE.getId());
        
        Genome genome = new Genome();
        
        // create layout context for the parent node
        LayoutContext context = createContext(parentNode, layoutMapping, config);
        genome.addContext(context, dataService.getOptionData().size() + 1);
        String algorithmId = (String) config.getValue(algoOptionData, context);
        String diagramType = (String) config.getValue(diagTypeData, context);
        LayoutAlgorithmData algorithmData = DefaultLayoutConfig.getLayouterData(
                algorithmId, diagramType);

        if (options.contains(algoOptionData)) {
            // create genes for the layout type and algorithm
            LayoutTypeData typeData = dataService.getTypeData(algorithmData.getType());
            genome.getGenes(context).add(createLayoutTypeGene(typeData));
            genome.getGenes(context).add(createAlgorithmGene(typeData, algorithmData));
        }
        
        // create genes for the other layout options (the algorithm option is excluded)
        for (LayoutOptionData<?> optionData : options) {
            TypeInfo<?> typeInfo = createTypeInfo(optionData);
            if (typeInfo != null) {
                Gene<?> gene;
                if (algorithmData.knowsOption(optionData)) {
                    gene = createDefaultGene(algorithmData, optionData, typeInfo, config,
                            context);
                } else {
                    gene = Gene.create(null, typeInfo, false);
                }
                genome.getGenes(context).add(gene);
            }
        }
            
        return genome;
    }
    
}
