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

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.statushandlers.StatusManager;

import de.cau.cs.kieler.core.alg.BasicProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.IGraphLayoutEngine;
import de.cau.cs.kieler.kiml.LayoutAlgorithmData;
import de.cau.cs.kieler.kiml.LayoutDataService;
import de.cau.cs.kieler.kiml.LayoutOptionData;
import de.cau.cs.kieler.kiml.LayoutTypeData;
import de.cau.cs.kieler.kiml.RecursiveGraphLayoutEngine;
import de.cau.cs.kieler.kiml.config.DefaultLayoutConfig;
import de.cau.cs.kieler.kiml.config.ILayoutConfig;
import de.cau.cs.kieler.kiml.config.LayoutContext;
import de.cau.cs.kieler.kiml.evol.EvolPlugin;
import de.cau.cs.kieler.kiml.evol.GenomeFactory;
import de.cau.cs.kieler.kiml.evol.alg.EvaluationOperation;
import de.cau.cs.kieler.kiml.evol.alg.MutationOperation;
import de.cau.cs.kieler.kiml.evol.genetic.Gene;
import de.cau.cs.kieler.kiml.evol.genetic.Genome;
import de.cau.cs.kieler.kiml.evol.genetic.TypeInfo;
import de.cau.cs.kieler.kiml.formats.GraphFormatsService;
import de.cau.cs.kieler.kiml.grana.AnalysisData;
import de.cau.cs.kieler.kiml.grana.AnalysisService;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.LayoutOptions;

/**
 * A command handler that tests the available layout metrics.
 * 
 * @author msp
 */
public class TestMetricsHandler extends AbstractHandler {
    
    private static final int I_NUMBER = 0;
    private static final int I_SUM = 1;
    private static final int I_DIFFSUM = 2;
    private static final int I_MIN = 3;
    private static final int I_MAX = 4;
    private static final String[] COL_HEADERS = new String[] {
        "Number", "Sum", "Dev. Sum", "Min.", "Max."
    };
    
    /** the graph layout metrics used for evaluation. */
    private List<AnalysisData> metrics;
    /** the sequence of analyses to execute for evaluating graph layout metrics. */
    private List<AnalysisData> analysisSequence;
    /** the layout options to consider for creating genomes. */
    private Collection<LayoutOptionData<?>> options;
    /** the executor service for running layout algorithms. */
    private ExecutorService executorService;
    
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
                    Random random = new Random();
                    try {
                        Writer writer = new FileWriter(System.getProperty("user.home")
                                + File.separator + "layout-metrics.csv");
                        writer.write("File,");
                        for (AnalysisData metric : metrics) {
                            for (int j = 0; j <= I_MAX; j++) {
                                writer.write(metric.getName());
                                writer.write(": ");
                                writer.write(COL_HEADERS[j]);
                                writer.write(",");
                            }
                        }
                        writer.write('\n');
                        
                        for (Object object : elements) {
                            if (monitor.isCanceled()) {
                                break;
                            }
                            if (object instanceof IFile) {
                                IFile inputFile = (IFile) object;
                                
                                float[][] result = testFile(inputFile, random);
                                
                                writer.write(inputFile.getName());
                                writer.write(",");
                                for (int i = 0; i < result.length; i++) {
                                    for (int j = 0; j < result[i].length; j++) {
                                        writer.write(Float.toString(result[i][j]));
                                        writer.write(",");
                                    }
                                }
                                writer.write('\n');
                            }
                            monitor.worked(1);
                        }
                        writer.close();
                    } catch (IOException exception) {
                        IStatus status = new Status(IStatus.ERROR, EvolPlugin.PLUGIN_ID,
                                "Error while writing the output file.", exception);
                        StatusManager.getManager().handle(status,
                                StatusManager.SHOW | StatusManager.LOG);
                    } finally {
                        executorService.shutdown();
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
        
    /**
     * Initialize the testing process.
     */
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
        
        // create the executor service
        executorService = Executors.newSingleThreadExecutor();
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
     * @param random the random number generator
     * @return test result matrix: rows correspond to layout metrics, columns are the values
     */
    private float[][] testFile(final IFile file, final Random random) {
        float[][] result = new float[metrics.size()][I_MAX + 1];
        for (int j = 0; j < metrics.size(); j++) {
            result[j][I_MIN] = 1;
        }
        try {
            System.out.print("Testing " + file.getName());
            long startTime = System.nanoTime();
            
            KNode[] graphs = GraphFormatsService.getInstance().loadKGraph(file);
            for (KNode graph : graphs) {
                float[][] graphResult = testGraph(graph, random);
                for (int j = 0; j < metrics.size(); j++) {
                    result[j][I_NUMBER] += graphResult[j][I_NUMBER];
                    result[j][I_SUM] += graphResult[j][I_SUM];
                    result[j][I_DIFFSUM] += graphResult[j][I_DIFFSUM];
                    result[j][I_MIN] = Math.min(result[j][I_MIN], graphResult[j][I_MIN]);
                    result[j][I_MAX] = Math.max(result[j][I_MAX], graphResult[j][I_MAX]);
                }
            }
            
            // SUPPRESS CHECKSTYLE NEXT MagicNumber
            System.out.println("\n  " + (System.nanoTime() - startTime) * 1e-9 + " s");
        } catch (Exception exception) {
            IStatus status = new Status(IStatus.ERROR, EvolPlugin.PLUGIN_ID,
                    "Error while loading the graph file " + file.getName(), exception);
            StatusManager.getManager().handle(status, StatusManager.SHOW | StatusManager.LOG);
        }
        return result;
    }
    
    /** the number of layouts to perform for each graph. */
    private static final int NUMBER_OF_LAYOUTS = 100;
    
    /** the maximal number of milliseconds to wait for a layout. */
    private static final long LAYOUT_TIMEOUT = 10000;
    
    /** the maximal number of attempts to layout a graph. */
    private static final int MAX_ATTEMPTS = 5;
    
    /**
     * Perform tests on the given graph.
     * 
     * @param originalGraph a graph
     * @param random a random number generator
     * @return test result matrix: rows correspond to layout metrics, columns are the values
     */
    private float[][] testGraph(final KNode originalGraph, final Random random) {
        // set minimal sizes for all nodes of the graph
        setGraphMetrics(originalGraph);
        
        final IGraphLayoutEngine graphLayoutEngine = new RecursiveGraphLayoutEngine();
        float[][] result = new float[metrics.size()][I_MAX + 1];
        for (int j = 0; j < metrics.size(); j++) {
            result[j][I_MIN] = 1;
        }
        
        for (int i = 0; i < NUMBER_OF_LAYOUTS; i++) {
            System.out.print(".");
            KNode layoutGraph = null;
            int attemptNo = 0;
            do {
                // create a random genome
                Genome genome = createRandomGenome(originalGraph, random);
                
                // create a copy of the evaluation graph
                EcoreUtil.Copier copier = new EcoreUtil.Copier();
                final KNode graph = (KNode) copier.copy(originalGraph);
                copier.copyReferences();
                GenomeFactory.configureGraph(genome, copier);
        
                try {
                    // perform layout on the evaluation graph
                    Future<?> future = executorService.submit(new Runnable() {
                        public void run() {
                            graphLayoutEngine.layout(graph, new BasicProgressMonitor(0));
                        }
                    });
                    future.get(LAYOUT_TIMEOUT, TimeUnit.MILLISECONDS);
                    layoutGraph = graph;
                } catch (Throwable throwable) {
                    // automatic layout led to an error or timed out -- try again
                    attemptNo++;
                    System.out.print("x");
                }
            } while (layoutGraph == null && attemptNo < MAX_ATTEMPTS);
            
            if (layoutGraph != null) {
                // perform analysis on the evaluation graph
                Map<String, Object> analysisResults = AnalysisService.getInstance().analyzePresorted(
                        layoutGraph, analysisSequence, new BasicProgressMonitor(0));
                ListIterator<AnalysisData> metricIter = metrics.listIterator();
                while (metricIter.hasNext()) {
                    int metricIndex = metricIter.nextIndex();
                    AnalysisData metric = metricIter.next();
                    Object value = analysisResults.get(metric.getId());
                    if (value instanceof Float) {
                        float x = (Float) value;
                        if (Float.isNaN(x)) {
                            System.out.print(metric.getName() + " => NaN / ");
                        }
                        result[metricIndex][I_NUMBER]++;
                        result[metricIndex][I_SUM] += x;
                        // SUPPRESS CHECKSTYLE NEXT MagicNumber
                        result[metricIndex][I_DIFFSUM] += (x - 0.5) * (x - 0.5);
                        result[metricIndex][I_MIN] = Math.min(result[metricIndex][I_MIN], x);
                        result[metricIndex][I_MAX] = Math.max(result[metricIndex][I_MAX], x);
                    }
                }
            }
        }
        return result;
    }
    
    /** the minimal node size. */
    private static final float MIN_NODE_SIZE = 20;
    
    /**
     * Set minimal sizes for all nodes of the given graph.
     * 
     * @param graph a graph
     */
    private void setGraphMetrics(final KNode graph) {
        for (KNode node : graph.getChildren()) {
            KShapeLayout nodeLayout = node.getData(KShapeLayout.class);
            if (nodeLayout.getWidth() < MIN_NODE_SIZE) {
                nodeLayout.setWidth(MIN_NODE_SIZE);
            }
            if (nodeLayout.getHeight() < MIN_NODE_SIZE) {
                nodeLayout.setHeight(MIN_NODE_SIZE);
            }
            if (!node.getChildren().isEmpty()) {
                setGraphMetrics(node);
            }
        }
    }
    
    private static final float MUTATION_PROB = 0.7f;
    
    /**
     * Create a genome with random values.
     * 
     * @param graph the graph for which to create the genome
     * @param random a random number generator
     * @return a genome filled with genes
     */
    private Genome createRandomGenome(final KNode graph, final Random random) {
        LayoutDataService dataService = LayoutDataService.getInstance();
        MutationOperation mutationOp = new MutationOperation();
        mutationOp.setRandom(random);
        
        Genome genome = new Genome();
        
        // create layout context for the parent node
        ILayoutConfig config = new DefaultLayoutConfig();
        LayoutContext context = GenomeFactory.createContext(graph, null, config);
        genome.addContext(context, dataService.getOptionData().size() + 1);
        LayoutTypeData typeData = getRandomElem(dataService.getTypeData(), random);
        LayoutAlgorithmData algorithmData = getRandomElem(GenomeFactory.getAlgoList(typeData), random);
        context.setProperty(DefaultLayoutConfig.CONTENT_ALGO, algorithmData);

        if (options.contains(dataService.getOptionData(LayoutOptions.ALGORITHM.getId()))) {
            // create genes for the layout type and algorithm
            genome.getGenes(context).add(GenomeFactory.createLayoutTypeGene(typeData));
            genome.getGenes(context).add(GenomeFactory.createAlgorithmGene(typeData, algorithmData));
        }
        
        // create genes for the other layout options
        for (LayoutOptionData<?> optionData : options) {
            TypeInfo<?> typeInfo = GenomeFactory.createTypeInfo(optionData);
            if (typeInfo != null) {
                Gene<?> gene;
                if (algorithmData.knowsOption(optionData)) {
                    gene = GenomeFactory.createDefaultGene(algorithmData, optionData, typeInfo, config,
                            context);
                    while (random.nextFloat() < MUTATION_PROB) {
                        gene = mutationOp.mutate(gene);
                    }
                } else {
                    gene = Gene.create(null, typeInfo, false);
                }
                genome.getGenes(context).add(gene);
            }
        }
            
        return genome;
    }
    
    /**
     * Returns a random element of the given collection.
     * 
     * @param collection a collection
     * @param random a random number generator
     * @return a random element
     */
    private static <T> T getRandomElem(final Collection<? extends T> collection, final Random random) {
        int index = random.nextInt(collection.size());
        Iterator<? extends T> iterator = collection.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            T next = iterator.next();
            if (i == index) {
                return next;
            }
            i++;
        }
        return null;
    }
    
}
