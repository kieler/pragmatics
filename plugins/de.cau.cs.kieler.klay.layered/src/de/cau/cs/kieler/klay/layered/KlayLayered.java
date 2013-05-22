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
package de.cau.cs.kieler.klay.layered;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import de.cau.cs.kieler.core.alg.BasicProgressMonitor;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.kiml.options.EdgeRouting;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.klay.layered.components.ComponentsProcessor;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.intermediate.LayoutProcessorStrategy;
import de.cau.cs.kieler.klay.layered.p1cycles.CycleBreakingStrategy;
import de.cau.cs.kieler.klay.layered.p1cycles.GreedyCycleBreaker;
import de.cau.cs.kieler.klay.layered.p1cycles.InteractiveCycleBreaker;
import de.cau.cs.kieler.klay.layered.p2layers.InteractiveLayerer;
import de.cau.cs.kieler.klay.layered.p2layers.LayeringStrategy;
import de.cau.cs.kieler.klay.layered.p2layers.LongestPathLayerer;
import de.cau.cs.kieler.klay.layered.p2layers.NetworkSimplexLayerer;
import de.cau.cs.kieler.klay.layered.p3order.CrossingMinimizationStrategy;
import de.cau.cs.kieler.klay.layered.p3order.InteractiveCrossingMinimizer;
import de.cau.cs.kieler.klay.layered.p3order.LayerSweepCrossingMinimizer;
import de.cau.cs.kieler.klay.layered.p4nodes.BKNodePlacer;
import de.cau.cs.kieler.klay.layered.p4nodes.LinearSegmentsNodePlacer;
import de.cau.cs.kieler.klay.layered.p4nodes.NodePlacementStrategy;
import de.cau.cs.kieler.klay.layered.p5edges.OrthogonalEdgeRouter;
import de.cau.cs.kieler.klay.layered.p5edges.PolylineEdgeRouter;
import de.cau.cs.kieler.klay.layered.p5edges.SplineEdgeRouter;
import de.cau.cs.kieler.klay.layered.properties.GraphProperties;
import de.cau.cs.kieler.klay.layered.properties.Properties;

/**
 * Implementation of a layered layout provider. The layered layouter works with five main phases:
 * cycle breaking, layering, crossing minimization, node placement and edge routing. Before these
 * phases and after the last phase, so called intermediate layout processors can be inserted that do
 * some kind of pre or post processing. Implementations of the different main phases specify the
 * intermediate layout processors they require, which are automatically collected and inserted
 * between the main phases. The layout provider itself also specifies some dependencies.
 * <pre>
 *           Intermediate Layout Processors
 * ---------------------------------------------------
 * |         |         |         |         |         |
 * |   ---   |   ---   |   ---   |   ---   |   ---   |
 * |   | |   |   | |   |   | |   |   | |   |   | |   |
 * |   | |   |   | |   |   | |   |   | |   |   | |   |
 *     | |       | |       | |       | |       | |
 *     | |       | |       | |       | |       | |
 *     ---       ---       ---       ---       ---
 *   Phase 1   Phase 2   Phase 3   Phase 4   Phase 5
 * </pre>
 * 
 * <p>This class provides methods for automatic unit testing, based around the concept of test runs. A
 * test run is executed as follows:</p>
 * <ol>
 *   <li>Call {@link #prepareLayoutTest(LGraph, IKielerProgressMonitor)} to start a new run. The given
 *       graph might be split into its connected components.</li>
 *   <li>Call one of the actual test methods. {@link #runLayoutTestStep(IKielerProgressMonitor)} runs
 *       the next step of the algorithm. {@link #runLayoutTestUntil(IKielerProgressMonitor, Class)} runs
 *       the algorithm until a given layout processor has finished executing. Both methods resume
 *       execution from where the algorithm has stopped previously.</li>
 *   <li>Once the test run has finished, call {@link #finalizeLayoutTest()}.</li>
 * </ol>
 * 
 * @see ILayoutPhase
 * @see ILayoutProcessor
 * 
 * @author msp
 * @author cds
 * @kieler.design 2012-08-10 chsch grh
 * @kieler.rating proposed yellow by msp
 */
public final class KlayLayered {

    // /////////////////////////////////////////////////////////////////////////////
    // Variables

    /** phase 1: cycle breaking module. */
    private ILayoutPhase cycleBreaker;
    /** phase 2: layering module. */
    private ILayoutPhase layerer;
    /** phase 3: crossing minimization module. */
    private ILayoutPhase crossingMinimizer;
    /** phase 4: node placement module. */
    private ILayoutPhase nodePlacer;
    /** phase 5: Edge routing module. */
    private ILayoutPhase edgeRouter;

    /** connected components processor. */
    private ComponentsProcessor componentsProcessor = new ComponentsProcessor();
    /** intermediate layout processor configuration. */
    private IntermediateProcessingConfiguration intermediateProcessingConfiguration 
                                         = new IntermediateProcessingConfiguration();
    /** collection of instantiated intermediate modules. */
    private Map<LayoutProcessorStrategy, ILayoutProcessor> intermediateLayoutProcessorCache 
                 = new HashMap<LayoutProcessorStrategy, ILayoutProcessor>();

    /** list of layout processors that compose the current algorithm. */
    private List<ILayoutProcessor> algorithm = new LinkedList<ILayoutProcessor>();
    /** list of graphs that are currently being laid out. */
    private List<LGraph> currentLayoutTestGraphs = null;
    /** index of the processor that is to be executed next during a layout test. */
    private int currentLayoutTestStep = 0;


    // /////////////////////////////////////////////////////////////////////////////
    // Regular Layout
    
    /**
     * Does a layout on the given graph.
     * 
     * @param lgraph the graph to layout.
     * @param progressMonitor a progress monitor to show progress information in.
     * @return layered graph with layout applied.
     */
    public LGraph doLayout(final LGraph lgraph, final IKielerProgressMonitor progressMonitor) {
        progressMonitor.begin("Layered layout", 1);

        // set special properties for the layered graph
        setOptions(lgraph);

        // update the modules depending on user options
        updateModules(lgraph);

        // split the input graph into components and perform layout on them
        List<LGraph> components = componentsProcessor.split(lgraph);
        for (LGraph comp : components) {
            layout(comp, progressMonitor.subTask(1.0f / components.size()));
        }
        LGraph result = componentsProcessor.combine(components);

        progressMonitor.done();
        
        return result;
    }


    // /////////////////////////////////////////////////////////////////////////////
    // Layout Testing
    
    /**
     * Prepares a test run of the layout algorithm. If a previous test run is still active, an exception
     * is thrown. After this method has run, call {@link #layoutTestStep()} as often as there are layout
     * processors. Once the test run is finished, call {@link #finalizeLayoutTest()}.
     * 
     * @param lgraph the input graph to initialize the test run with.
     * @param progressMonitor a progress monitor to show progress information in.
     * @throws IllegalStateException if a previous layout test run is still active.
     */
    public void prepareLayoutTest(final LGraph lgraph,
            final IKielerProgressMonitor progressMonitor) {
        
        // check if a previous layout test run is still active
        if (currentLayoutTestGraphs != null || currentLayoutTestStep != 0) {
            throw new IllegalStateException("Previous layout test run not finalized.");
        }
        
        progressMonitor.begin("Layered layout test preparations", 1);
        
        // set special properties for the layered graph
        setOptions(lgraph);

        // update the modules depending on user options
        updateModules(lgraph);

        // split the input graph into components
        currentLayoutTestGraphs = componentsProcessor.split(lgraph);
        currentLayoutTestStep = 0;
        
        progressMonitor.done();
    }
    
    /**
     * Checks if the current test run still has processors to be executed for the algorithm to finish.
     * 
     * @return {@code true} if the current test run has not finished yet. If there is no current test
     *         run, the result is undefined.
     */
    public boolean isLayoutTestFinished() {
        return currentLayoutTestGraphs == null || currentLayoutTestStep >= algorithm.size();
    }
    
    /**
     * Runs the algorithm on the current test graphs up to the point where the given phase or processor
     * has finished executing. If parts of the algorithm were already executed using this or other layout
     * test methods, execution is resumed from there. If the given phase or processor is not among those
     * processors that have not yet executed, an exception is thrown. Also, if there is no current layout
     * test run, an exception is thrown.
     * 
     * @param progressMonitor a progress monitor to show progress information in.
     * @param phase the phase or processor to stop after.
     * @return list of connected components after the execution of the given phase.
     * @throws IllegalStateException if no layout test run is currently active.
     * @throws IllegalArgumentException if the given layout processor is not part of the processors that
     *                                  are still to be executed.
     */
    public List<LGraph> runLayoutTestUntil(final IKielerProgressMonitor progressMonitor,
            final Class<? extends ILayoutProcessor> phase) {

        // check if a layout test run is active
        if (currentLayoutTestGraphs == null) {
            throw new IllegalStateException("No active layout test run.");
        }
        
        progressMonitor.begin("Layered layout test", 1);
        
        // check if the given phase exists in our current algorithm configuration
        boolean phaseExists = false;
        int phaseIndex;
        for (phaseIndex = currentLayoutTestStep; phaseIndex < algorithm.size(); phaseIndex++) {
            if (algorithm.get(phaseIndex).getClass().equals(phase)) {
                phaseExists = true;
                break;
            }
        }
        
        if (!phaseExists) {
            throw new IllegalArgumentException("Given processor not part of the remaining algorithm.");
        }
        
        // perform the layout up to and including that phase
        int phasesToBeExecuted = phaseIndex - currentLayoutTestStep + 1;
        for (; currentLayoutTestStep <= phaseIndex; currentLayoutTestStep++) {
            layoutTest(currentLayoutTestGraphs,
                    progressMonitor.subTask(1.0f / phasesToBeExecuted),
                    algorithm.get(currentLayoutTestStep));
        }

        progressMonitor.done();
        
        return currentLayoutTestGraphs;
    }
    
    /**
     * Runs the next step of the current layout test run. Throws exceptions if no layout test run is
     * currently active or if the current run has finished.
     * 
     * @param progressMonitor a progress monitor to show progress information in.
     * @return list of connected components after the execution of the next step.
     * @throws IllegalStateException if no layout test run is currently active or if the current run has
     *                               finished executing.
     */
    public List<LGraph> runLayoutTestStep(final IKielerProgressMonitor progressMonitor) {
        // check if a layout test run is active
        if (currentLayoutTestGraphs == null) {
            throw new IllegalStateException("No active layout test run.");
        }
        
        if (isLayoutTestFinished()) {
            throw new IllegalStateException("Current layout test run has finished.");
        }
        
        progressMonitor.begin("Layered layout test", 1);
        
        // perform the next layout step
        layoutTest(currentLayoutTestGraphs,
                progressMonitor.subTask(1),
                algorithm.get(currentLayoutTestStep));
        currentLayoutTestStep++;

        progressMonitor.done();
        
        return currentLayoutTestGraphs;
    }
    
    /**
     * Finalizes the current layout test run. After this method has been called, the next test run can
     * be started by calling {@link #prepareLayoutTest(LGraph, IKielerProgressMonitor)}.
     * 
     * @throws IllegalStateException if no layout test run is currently active.
     */
    public void finalizeLayoutTest() {
        // check if a layout test run is active
        if (currentLayoutTestGraphs == null) {
            throw new IllegalStateException("No active layout test run.");
        }
        
        currentLayoutTestGraphs = null;
        currentLayoutTestStep = 0;
    }
    
    /**
     * Returns the current list of layout processors that make up the algorithm. This list is only valid
     * and meaningful while a layout test is being run.
     * 
     * @return the algorithm's current configuration.
     * @throws IllegalStateException if no layout test run is currently active.
     */
    public List<ILayoutProcessor> getLayoutTestConfiguration() {
        // check if a layout test run is active
        if (currentLayoutTestGraphs == null) {
            throw new IllegalStateException("No active layout test run.");
        }
        
        return algorithm;
    }
    
    /**
     * Returns the list of test graphs associated with the current layout test run. If connected
     * components processing is active, the list will contain one {@link LGraph} instance for each
     * connected component. Otherwise, the list will contain just one {@link LGraph}.
     * 
     * @return layout test graphs.
     * @throws IllegalStateException if no layout test run is currently active.
     */
    public List<LGraph> getLayoutTestGraphs() {
        // check if a layout test run is active
        if (currentLayoutTestGraphs == null) {
            throw new IllegalStateException("No active layout test run.");
        }
        
        return currentLayoutTestGraphs;
    }
    

    // /////////////////////////////////////////////////////////////////////////////
    // Options and Modules Management

    /**
     * Set special layout options for the layered graph.
     * 
     * @param layeredGraph
     *            a new layered graph
     */
    private void setOptions(final LGraph layeredGraph) {
        // set the random number generator based on the random seed option
        Integer randomSeed = layeredGraph.getProperty(LayoutOptions.RANDOM_SEED);
        if (randomSeed != null) {
            int val = randomSeed;
            if (val == 0) {
                layeredGraph.setProperty(Properties.RANDOM, new Random());
            } else {
                layeredGraph.setProperty(Properties.RANDOM, new Random(val));
            }
        } else {
            layeredGraph.setProperty(Properties.RANDOM, new Random(1));
        }
    }

    /**
     * Update the modules depending on user options.
     * 
     * @param graph
     *            the graph to be laid out.
     */
    private void updateModules(final LGraph graph) {
        // check which cycle breaking strategy to use
        CycleBreakingStrategy cycleBreaking = graph.getProperty(Properties.CYCLE_BREAKING);
        switch (cycleBreaking) {
        case INTERACTIVE:
            if (!(cycleBreaker instanceof InteractiveCycleBreaker)) {
                cycleBreaker = new InteractiveCycleBreaker();
            }
            break;
        default:
            if (!(cycleBreaker instanceof GreedyCycleBreaker)) {
                cycleBreaker = new GreedyCycleBreaker();
            }
        }
        
        // check which layering strategy to use
        LayeringStrategy layering = graph.getProperty(Properties.NODE_LAYERING);
        switch (layering) {
        case LONGEST_PATH:
            if (!(layerer instanceof LongestPathLayerer)) {
                layerer = new LongestPathLayerer();
            }
            break;
        case INTERACTIVE:
            if (!(layerer instanceof InteractiveLayerer)) {
                layerer = new InteractiveLayerer();
            }
            break;
        default:
            if (!(layerer instanceof NetworkSimplexLayerer)) {
                layerer = new NetworkSimplexLayerer();
            }
        }
        
        // check which crossing minimization strategy to use
        CrossingMinimizationStrategy crossminStrategy = graph.getProperty(Properties.CROSS_MIN);
        switch (crossminStrategy) {
        case INTERACTIVE:
            if (!(crossingMinimizer instanceof InteractiveCrossingMinimizer)) {
                crossingMinimizer = new InteractiveCrossingMinimizer();
            }
            break;
        default:
            if (!(crossingMinimizer instanceof LayerSweepCrossingMinimizer)) {
                crossingMinimizer = new LayerSweepCrossingMinimizer();
            }
        }
        
        // check which node placement strategy to use
        NodePlacementStrategy nodePlaceStrategy = graph.getProperty(Properties.NODE_PLACER);
        switch (nodePlaceStrategy) {
        case LINEAR_SEGMENTS:
            if (!(nodePlacer instanceof LinearSegmentsNodePlacer)) {
                nodePlacer = new LinearSegmentsNodePlacer();
            }
            break;
        case BRANDES_KOEPF:
            if (!(nodePlacer instanceof BKNodePlacer)) {
                nodePlacer = new BKNodePlacer();
            }
            break;
        default:
            if (!(nodePlacer instanceof LinearSegmentsNodePlacer)) {
                nodePlacer = new LinearSegmentsNodePlacer();
            }
        }

        // check which edge router to use
        EdgeRouting routing = graph.getProperty(LayoutOptions.EDGE_ROUTING);
        switch (routing) {
        case ORTHOGONAL:
            if (!(edgeRouter instanceof OrthogonalEdgeRouter)) {
                edgeRouter = new OrthogonalEdgeRouter();
            }
            break;
        case SPLINES:
            if (!(edgeRouter instanceof SplineEdgeRouter)) {
                edgeRouter = new SplineEdgeRouter();
            }
            break;
        default:
            if (!(edgeRouter instanceof PolylineEdgeRouter)) {
                edgeRouter = new PolylineEdgeRouter();
            }
        }

        // update intermediate processor configuration
        intermediateProcessingConfiguration.clear();
        intermediateProcessingConfiguration
                .addAll(cycleBreaker.getIntermediateProcessingConfiguration(graph))
                .addAll(layerer.getIntermediateProcessingConfiguration(graph))
                .addAll(crossingMinimizer.getIntermediateProcessingConfiguration(graph))
                .addAll(nodePlacer.getIntermediateProcessingConfiguration(graph))
                .addAll(edgeRouter.getIntermediateProcessingConfiguration(graph))
                .addAll(this.getIntermediateProcessingConfiguration(graph));

        // construct the list of processors that make up the algorithm
        algorithm.clear();
        algorithm.addAll(
                getIntermediateProcessorList(IntermediateProcessingConfiguration.BEFORE_PHASE_1));
        algorithm.add(cycleBreaker);
        algorithm.addAll(
                getIntermediateProcessorList(IntermediateProcessingConfiguration.BEFORE_PHASE_2));
        algorithm.add(layerer);
        algorithm.addAll(
                getIntermediateProcessorList(IntermediateProcessingConfiguration.BEFORE_PHASE_3));
        algorithm.add(crossingMinimizer);
        algorithm.addAll(
                getIntermediateProcessorList(IntermediateProcessingConfiguration.BEFORE_PHASE_4));
        algorithm.add(nodePlacer);
        algorithm.addAll(
                getIntermediateProcessorList(IntermediateProcessingConfiguration.BEFORE_PHASE_5));
        algorithm.add(edgeRouter);
        algorithm.addAll(
                getIntermediateProcessorList(IntermediateProcessingConfiguration.AFTER_PHASE_5));
    }

    /**
     * Returns a list of layout processor instances for the given intermediate layout processing
     * slot.
     * 
     * @param slotIndex
     *            the slot index. One of the constants defined in
     *            {@link IntermediateProcessingConfiguration}.
     * @return list of layout processors.
     */
    private List<ILayoutProcessor> getIntermediateProcessorList(final int slotIndex) {
        // fetch the set of layout processors configured for the given slot
        EnumSet<LayoutProcessorStrategy> processors = intermediateProcessingConfiguration
                .getProcessors(slotIndex);
        List<ILayoutProcessor> result = new ArrayList<ILayoutProcessor>(processors.size());

        // iterate through the layout processors and add them to the result list; the EnumSet
        // guarantees that we iterate over the processors in the order in which they occur in
        // the LayoutProcessorStrategy, thereby satisfying all of their runtime order
        // dependencies without having to sort them in any way
        for (LayoutProcessorStrategy processor : processors) {
            // check if an instance of the given layout processor is already in the cache
            ILayoutProcessor processorImpl = intermediateLayoutProcessorCache.get(processor);

            if (processorImpl == null) {
                // It's not in the cache, so create it and put it in the cache
                processorImpl = processor.create();
                intermediateLayoutProcessorCache.put(processor, processorImpl);
            }

            // add the layout processor to the list of processors for this slot
            result.add(processorImpl);
        }

        return result;
    }

    /**
     * Returns an intermediate processing configuration with processors not tied to specific phases.
     * 
     * @param graph
     *            the layered graph to be processed. The configuration may vary depending on certain
     *            properties of the graph.
     * @return intermediate processing configuration. May be {@code null}.
     */
    private IntermediateProcessingConfiguration getIntermediateProcessingConfiguration(
            final LGraph graph) {
        
        Set<GraphProperties> graphProperties = graph.getProperty(Properties.GRAPH_PROPERTIES);

        // Basic configuration
        IntermediateProcessingConfiguration configuration = new IntermediateProcessingConfiguration(
                BASELINE_PROCESSING_CONFIGURATION);
        
        // port side processor, put to first slot only if requested and routing is orthogonal
        if (graph.getProperty(Properties.FEEDBACK_EDGES)
                && graph.getProperty(LayoutOptions.EDGE_ROUTING) == EdgeRouting.ORTHOGONAL) {
            configuration.addLayoutProcessor(IntermediateProcessingConfiguration.BEFORE_PHASE_1,
                    LayoutProcessorStrategy.PORT_SIDE_PROCESSOR);
        } else {
            configuration.addLayoutProcessor(IntermediateProcessingConfiguration.BEFORE_PHASE_3,
                    LayoutProcessorStrategy.PORT_SIDE_PROCESSOR);
        }
        
        // graph transformations for unusual layout directions
        switch (graph.getProperty(LayoutOptions.DIRECTION)) {
        case LEFT:
            configuration.addLayoutProcessor(IntermediateProcessingConfiguration.BEFORE_PHASE_1,
                    LayoutProcessorStrategy.LEFT_DIR_PREPROCESSOR);
            configuration.addLayoutProcessor(IntermediateProcessingConfiguration.AFTER_PHASE_5,
                    LayoutProcessorStrategy.LEFT_DIR_POSTPROCESSOR);
            break;
        case DOWN:
            configuration.addLayoutProcessor(IntermediateProcessingConfiguration.BEFORE_PHASE_1,
                    LayoutProcessorStrategy.DOWN_DIR_PREPROCESSOR);
            configuration.addLayoutProcessor(IntermediateProcessingConfiguration.AFTER_PHASE_5,
                    LayoutProcessorStrategy.DOWN_DIR_POSTPROCESSOR);
            break;
        case UP:
            configuration.addLayoutProcessor(IntermediateProcessingConfiguration.BEFORE_PHASE_1,
                    LayoutProcessorStrategy.UP_DIR_PREPROCESSOR);
            configuration.addLayoutProcessor(IntermediateProcessingConfiguration.AFTER_PHASE_5,
                    LayoutProcessorStrategy.UP_DIR_POSTPROCESSOR);
            break;
        default:
            // This is either RIGHT or UNDEFINED, which is just mapped to RIGHT. Either way, we don't
            // need any processors here
            break;
        }

        // Additional dependencies
        if (graphProperties.contains(GraphProperties.FLAT_HIERARCHICAL)) {
            configuration.addAll(FLATTENED_HIERARCHY_PROCESSING_ADDITIONS);
        }
        if (graphProperties.contains(GraphProperties.COMMENTS)) {
            configuration.addLayoutProcessor(IntermediateProcessingConfiguration.BEFORE_PHASE_1,
                    LayoutProcessorStrategy.COMMENT_PREPROCESSOR);
            configuration.addLayoutProcessor(IntermediateProcessingConfiguration.AFTER_PHASE_5,
                    LayoutProcessorStrategy.COMMENT_POSTPROCESSOR);
        }

        return configuration;
    }
    

    // /////////////////////////////////////////////////////////////////////////////
    // Layout

    /**
     * Perform the five phases of the layered layouter.
     * 
     * @param graph
     *            the graph that is to be laid out
     * @param themonitor
     *            a progress monitor, or {@code null}
     */
    private void layout(final LGraph graph, final IKielerProgressMonitor themonitor) {
        IKielerProgressMonitor monitor = themonitor;
        if (monitor == null) {
            monitor = new BasicProgressMonitor();
        }
        monitor.begin("Component Layout", algorithm.size());

        if (graph.getProperty(LayoutOptions.DEBUG_MODE)) {
            // Debug Mode!
            // Prints the algorithm configuration and outputs the whole graph to a file
            // before each slot execution

            System.out.println("KLay Layered uses the following " + algorithm.size() + " modules:");
            for (int i = 0; i < algorithm.size(); i++) {
                System.out.println("   Slot " + String.format("%1$02d", i) + ": "
                        + algorithm.get(i).getClass().getName());
            }

            // invoke each layout processor
            int slotIndex = 0;
            for (ILayoutProcessor processor : algorithm) {
                if (monitor.isCanceled()) {
                    return;
                }
                // Graph debug output
                try {
                    graph.writeDotGraph(createWriter(graph, slotIndex++));
                } catch (IOException e) {
                    // Do nothing.
                }

                processor.process(graph, monitor.subTask(1));
            }

            // Graph debug output
            try {
                graph.writeDotGraph(createWriter(graph, slotIndex++));
            } catch (IOException e) {
                // Do nothing.
            }
        } else {
            // invoke each layout processor
            for (ILayoutProcessor processor : algorithm) {
                if (monitor.isCanceled()) {
                    return;
                }
                processor.process(graph, monitor.subTask(1));
            }
        }

        monitor.done();
    }

    /**
     * Executes the given layout processor on the given list of graphs.
     * 
     * @param graphs the list of graphs to be laid out.
     * @param monitor a progress monitor.
     * @param processor processor to execute.
     */
    private void layoutTest(final List<LGraph> graphs, final IKielerProgressMonitor monitor,
            final ILayoutProcessor processor) {
        
        monitor.begin("Component Layout", graphs.size());
        
        // invoke the layout processor on each of the given graphs
        for (LGraph graph : graphs) {
            if (monitor.isCanceled()) {
                return;
            }
            
            processor.process(graph, monitor.subTask(1));
        }

        monitor.done();
    }
    

    // /////////////////////////////////////////////////////////////////////////////
    // Debug

    /**
     * Creates a writer for the given graph. The file name to be written to is assembled from the
     * graph's hash code and the slot index.
     * 
     * @param graph
     *            the graph to be written.
     * @param slotIndex
     *            the slot before whose execution the graph is written.
     * @return file writer.
     * @throws IOException
     *             if anything goes wrong.
     */
    private Writer createWriter(final LGraph graph, final int slotIndex) throws IOException {
        String path = Util.getDebugOutputPath();
        new File(path).mkdirs();

        String debugFileName = Util.getDebugOutputFileBaseName(graph) + "fulldebug-slot"
                + String.format("%1$02d", slotIndex);
        return new FileWriter(new File(path + File.separator + debugFileName + ".dot"));
    }
    
    
    // /////////////////////////////////////////////////////////////////////////////
    // Processing Configuration Constants

    /** intermediate processing configuration for basic graphs. */
    private static final IntermediateProcessingConfiguration BASELINE_PROCESSING_CONFIGURATION 
            = new IntermediateProcessingConfiguration(null, null, null,
            
            // Before Phase 4
            EnumSet.of(LayoutProcessorStrategy.NODE_MARGIN_CALCULATOR,
                    LayoutProcessorStrategy.LABEL_AND_NODE_SIZE_PROCESSOR),
            
            // Before Phase 5
            EnumSet.of(LayoutProcessorStrategy.LAYER_SIZE_AND_GRAPH_HEIGHT_CALCULATOR),
            
            null);

    /** additional processor dependencies for flattened hierarchical graphs. */
    private static final IntermediateProcessingConfiguration FLATTENED_HIERARCHY_PROCESSING_ADDITIONS 
            = new IntermediateProcessingConfiguration(
            
            // Before Phase 1
            EnumSet.of(LayoutProcessorStrategy.COMPOUND_CYCLE_PROCESSOR),
            
            // Before Phase 2
            null, 
            
            // Before Phase 3
            EnumSet.of(LayoutProcessorStrategy.COMPOUND_DUMMY_EDGE_REMOVER),
            
            // Before Phase 4
            EnumSet.of(LayoutProcessorStrategy.SUBGRAPH_ORDERING_PROCESSOR, 
                    LayoutProcessorStrategy.COMPOUND_SIDE_PROCESSOR),
            
            null,
            
            // After Phase 5
            EnumSet.of(LayoutProcessorStrategy.COMPOUND_GRAPH_RESTORER));

}
