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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
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
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.statushandlers.StatusManager;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import de.cau.cs.kieler.core.alg.BasicProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.IGraphLayoutEngine;
import de.cau.cs.kieler.kiml.LayoutAlgorithmData;
import de.cau.cs.kieler.kiml.LayoutDataService;
import de.cau.cs.kieler.kiml.LayoutOptionData;
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
import de.cau.cs.kieler.kiml.evol.genetic.TypeInfo.GeneType;
import de.cau.cs.kieler.kiml.formats.GraphFormatsService;
import de.cau.cs.kieler.kiml.grana.AnalysisData;
import de.cau.cs.kieler.kiml.grana.AnalysisService;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.util.BoxLayoutProvider;
import de.cau.cs.kieler.kiml.util.FixedLayoutProvider;

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
    private Collection<LayoutOptionData<?>> layoutOptions;
    /** the layout algorithms to consider for creating genomes. */
    private List<LayoutAlgorithmData> layoutAlgorithms;
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
                    Writer[] writers = new Writer[metrics.size()];
                    try {
                        // create the output files
                        ListIterator<AnalysisData> metricIter = metrics.listIterator();
                        while (metricIter.hasNext()) {
                            int metricIndex = metricIter.nextIndex();
                            AnalysisData metric = metricIter.next();
                            int lastDotIndex = metric.getId().lastIndexOf('.');
                            String metricId;
                            if (lastDotIndex < 0 && lastDotIndex < metric.getId().length() - 1) {
                                metricId = metric.getId();
                            } else {
                                metricId = metric.getId().substring(lastDotIndex + 1);
                            }
                            writers[metricIndex] = new FileWriter(System.getProperty("user.home")
                                    + File.separator + metricId + ".csv");
                        }
                        
                        // process the input files
                        for (Object object : elements) {
                            if (monitor.isCanceled()) {
                                break;
                            }
                            if (object instanceof IFile) {
                                IFile inputFile = (IFile) object;
                                
                                // perform tests on the current input file
                                float[][] result = testFile(inputFile, random);
                                
                                // write the test results to the output files
                                for (int i = 0; i < writers.length; i++) {
                                    writers[i].write(inputFile.getName());
                                    writers[i].write(",");
                                    for (int j = 0; j < result[i].length; j++) {
                                        writers[i].write(Float.toString(result[i][j]));
                                        writers[i].write(",");
                                    }
                                    writers[i].write('\n');
                                }
                            }
                            monitor.worked(1);
                        }
                    } catch (IOException exception) {
                        IStatus status = new Status(IStatus.ERROR, EvolPlugin.PLUGIN_ID,
                                "Error while writing an output file.", exception);
                        StatusManager.getManager().handle(status,
                                StatusManager.SHOW | StatusManager.LOG);
                    } finally {
                        // close the output files
                        for (int i = 0; i < writers.length; i++) {
                            if (writers[i] != null) {
                                try {
                                    writers[i].close();
                                } catch (IOException exception) {
                                    // ignore this exception
                                }
                            }
                        }
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
    
    /** list of layout algorithms that are excluded from the process. */
    private static final List<String> EXCLUDED_ALGOS = Lists.newArrayList(
            FixedLayoutProvider.ID, BoxLayoutProvider.ID,
            "de.cau.cs.kieler.kiml.ogdf.tree", "de.cau.cs.kieler.kiml.ogdf.radialTree",
            "de.cau.cs.kieler.kiml.ogdf.gem", "de.cau.cs.kieler.kiml.ogdf.stressMajorization");
        
    /**
     * Initialize the testing process.
     */
    private void initialize() {
        // determine the sequence of graph layout analyses to evaluate
        AnalysisService analysisService = AnalysisService.getInstance();
        metrics = analysisService.getCategory(EvaluationOperation.METRIC_CATEGORY).getAnalyses();
        analysisSequence = analysisService.getExecutionOrder(metrics);
        
        // gather the layout options
        layoutOptions = Collections2.filter(LayoutDataService.getInstance().getOptionData(),
                new Predicate<LayoutOptionData<?>>() {
                    public boolean apply(final LayoutOptionData<?> data) {
                        return data.getTargets().contains(LayoutOptionData.Target.PARENTS)
                                && data.getVariance() > 0 && typeSupported(data.getType());
                    }
                });
        
        // gather the layout algorithms
        layoutAlgorithms = new ArrayList<LayoutAlgorithmData>(
                Collections2.filter(LayoutDataService.getInstance().getAlgorithmData(),
                new Predicate<LayoutAlgorithmData>() {
                    public boolean apply(final LayoutAlgorithmData data) {
                        return !EXCLUDED_ALGOS.contains(data.getId());
                    }
            }));
        
        // create the executor service
        executorService = Executors.newCachedThreadPool();
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
     * @return test result matrix: rows correspond to layout metrics, columns to algorithm executions
     */
    private float[][] testFile(final IFile file, final Random random) {
        float[][] result = null;
        try {
            System.out.print("Testing " + file.getName());
            long startTime = System.nanoTime();
            
            KNode[] graphs = GraphFormatsService.getInstance().loadKGraph(file);
            result = testGraph(graphs[0], random);
            
            // SUPPRESS CHECKSTYLE NEXT MagicNumber
            System.out.println("\n  " + (System.nanoTime() - startTime) * 1e-9 + " s");
        } catch (CoreException exception) {
            StatusManager.getManager().handle(exception, EvolPlugin.PLUGIN_ID);
        } catch (IOException exception) {
            IStatus status = new Status(IStatus.ERROR, EvolPlugin.PLUGIN_ID,
                    "Error while loading the graph file " + file.getName(), exception);
            StatusManager.getManager().handle(status, StatusManager.SHOW | StatusManager.LOG);
        }
        return result;
    }
    
    /** the number of layouts to perform for each graph. */
    private static final int NUMBER_OF_LAYOUTS = 100;
    
    /** the maximal number of milliseconds to wait for a layout. */
    private static final long LAYOUT_TIMEOUT = 8000;
    
    /** the maximal number of attempts to layout a graph. */
    private static final int MAX_ATTEMPTS = 5;
    
    /**
     * Perform tests on the given graph.
     * 
     * @param originalGraph a graph
     * @param random a random number generator
     * @return test result matrix: rows correspond to layout metrics, columns to algorithm executions
     */
    private float[][] testGraph(final KNode originalGraph, final Random random) {
        // set minimal sizes for all nodes of the graph
        setGraphMetrics(originalGraph, random);
        
        final IGraphLayoutEngine graphLayoutEngine = new RecursiveGraphLayoutEngine();
        float[][] result = new float[metrics.size()][NUMBER_OF_LAYOUTS];
        for (int i = 0; i < metrics.size(); i++) {
            Arrays.fill(result[i], -1);
        }
        
        for (int j = 0; j < NUMBER_OF_LAYOUTS; j++) {
            KNode layoutGraph = null;
            int attemptNo = 0;
            Genome genome;
            do {
                // create a random genome
                genome = createRandomGenome(originalGraph, random);
                
                // create a copy of the evaluation graph
                EcoreUtil.Copier copier = new EcoreUtil.Copier();
                final KNode graph = (KNode) copier.copy(originalGraph);
                copier.copyReferences();
                GenomeFactory.configureGraph(genome, copier);
        
                Future<?> future = null;
                try {
                    // perform layout on the evaluation graph
                    future = executorService.submit(new Runnable() {
                        public void run() {
                            graphLayoutEngine.layout(graph, new BasicProgressMonitor(0));
                        }
                    });
                    future.get(LAYOUT_TIMEOUT, TimeUnit.MILLISECONDS);
                    if (checkLayout(graph)) {
                        // the layout algorithm was successful
                        layoutGraph = graph;
                    } else {
                        attemptNo++;
                        System.out.print("x");
                    }
                } catch (Exception exception) {
                    // automatic layout led to an error or timed out -- try again
                    attemptNo++;
                    System.out.print("x");
                    future.cancel(true);
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
                        if (!Float.isNaN(x) && x >= 0 && x <= 1) {
                            result[metricIndex][j] = x;
                        }
                    }
                }
                System.out.print(".");
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
     * @param random
     */
    private void setGraphMetrics(final KNode graph, final Random random) {
        for (KNode node : graph.getChildren()) {
            KShapeLayout nodeLayout = node.getData(KShapeLayout.class);
            if (nodeLayout.getWidth() < MIN_NODE_SIZE) {
                nodeLayout.setWidth(MIN_NODE_SIZE);
            }
            if (nodeLayout.getHeight() < MIN_NODE_SIZE) {
                nodeLayout.setHeight(MIN_NODE_SIZE);
            }
            // assign a random position in order to satisfy preconditions of some layouters
            nodeLayout.setXpos(random.nextFloat());
            nodeLayout.setYpos(random.nextFloat());
            if (!node.getChildren().isEmpty()) {
                setGraphMetrics(node, random);
            }
        }
    }
    
    /** minimal number of nodes with different positions. */
    private static final int MIN_DIFF_NODES = 3;
    
    /**
     * Check the layout of the given graph.
     * 
     * @param graph a graph
     * @return true if the layout is ok, false otherwise
     */
    private boolean checkLayout(final KNode graph) {
        KShapeLayout parentLayout = graph.getData(KShapeLayout.class);
        if (parentLayout.getWidth() <= 0 || parentLayout.getHeight() <= 0) {
            return false;
        }
        // nodes must have different positions
        int n = graph.getChildren().size();
        HashSet<Float> horPositions = Sets.newHashSetWithExpectedSize(n);
        HashSet<Float> verPositions = Sets.newHashSetWithExpectedSize(n);
        for (KNode node : graph.getChildren()) {
            KShapeLayout nodeLayout = node.getData(KShapeLayout.class);
            horPositions.add(nodeLayout.getXpos());
            verPositions.add(nodeLayout.getYpos());
            if (horPositions.size() >= MIN_DIFF_NODES && verPositions.size() >= MIN_DIFF_NODES) {
                return true;
            }
        }
        return false;
    }
    
    /** mutation probability for layout options. */
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
        LayoutAlgorithmData algorithmData = getRandomElem(layoutAlgorithms, random);
        context.setProperty(DefaultLayoutConfig.CONTENT_ALGO, algorithmData);

        // create gene for the layout algorithm
        TypeInfo<Integer> algoTypeInfo = new TypeInfo<Integer>(LayoutOptions.ALGORITHM.getId(),
                GeneType.LAYOUT_ALGO, 0, layoutAlgorithms.size() - 1, layoutAlgorithms, 1, 1);
        Gene<Integer> algorithmGene = Gene.create(layoutAlgorithms.indexOf(algorithmData),
                algoTypeInfo, true);
        genome.getGenes(context).add(algorithmGene);
        
        // create genes for the other layout options
        for (LayoutOptionData<?> optionData : layoutOptions) {
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
