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

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import de.cau.cs.kieler.core.alg.BasicProgressMonitor;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.kiml.options.Direction;
import de.cau.cs.kieler.kiml.options.EdgeRouting;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortConstraints;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.kiml.options.SizeConstraint;
import de.cau.cs.kieler.kiml.options.SizeOptions;
import de.cau.cs.kieler.kiml.util.KimlUtil;
import de.cau.cs.kieler.klay.layered.components.ComponentsProcessor;
import de.cau.cs.kieler.klay.layered.compound.CompoundGraphPostprocessor;
import de.cau.cs.kieler.klay.layered.compound.CompoundGraphPreprocessor;
import de.cau.cs.kieler.klay.layered.graph.LGraphUtil;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LInsets;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.intermediate.LayoutProcessorStrategy;
import de.cau.cs.kieler.klay.layered.p1cycles.GreedyCycleBreaker;
import de.cau.cs.kieler.klay.layered.p1cycles.InteractiveCycleBreaker;
import de.cau.cs.kieler.klay.layered.p2layers.InteractiveLayerer;
import de.cau.cs.kieler.klay.layered.p2layers.LongestPathLayerer;
import de.cau.cs.kieler.klay.layered.p2layers.NetworkSimplexLayerer;
import de.cau.cs.kieler.klay.layered.p3order.InteractiveCrossingMinimizer;
import de.cau.cs.kieler.klay.layered.p3order.LayerSweepCrossingMinimizer;
import de.cau.cs.kieler.klay.layered.p4nodes.BKNodePlacer;
import de.cau.cs.kieler.klay.layered.p4nodes.LinearSegmentsNodePlacer;
import de.cau.cs.kieler.klay.layered.p4nodes.SimpleNodePlacer;
import de.cau.cs.kieler.klay.layered.p5edges.OrthogonalEdgeRouter;
import de.cau.cs.kieler.klay.layered.p5edges.PolylineEdgeRouter;
import de.cau.cs.kieler.klay.layered.p5edges.SplineEdgeRouter;
import de.cau.cs.kieler.klay.layered.properties.GraphProperties;
import de.cau.cs.kieler.klay.layered.properties.NodeType;
import de.cau.cs.kieler.klay.layered.properties.Properties;

/**
 * Implementation of a layered layout provider. The layered layouter works with five main phases:
 * cycle breaking, layering, crossing minimization, node placement and edge routing. Before these
 * phases and after the last phase, so called intermediate layout processors can be inserted that do
 * some kind of pre or post processing. Implementations of the different main phases specify the
 * intermediate layout processors they require, which are automatically collected and inserted
 * between the main phases. The layout provider itself also specifies some dependencies.
 * 
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
 * <p>
 * This class provides methods for automatic unit testing, based around the concept of test runs. A
 * test run is executed as follows:
 * </p>
 * <ol>
 *   <li>Call {@link #prepareLayoutTest(LGraph, IKielerProgressMonitor)} to start a new run. The given
 *     graph might be split into its connected components.</li>
 *   <li>Call one of the actual test methods. {@link #runLayoutTestStep(IKielerProgressMonitor)} runs
 *     the next step of the algorithm. {@link #runLayoutTestUntil(IKielerProgressMonitor, Class)} runs
 *     the algorithm until a given layout processor has finished executing. Both methods resume
 *     execution from where the algorithm has stopped previously.</li>
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

    /** connected components processor. */
    private ComponentsProcessor componentsProcessor;
    /** compound graph preprocessor. */
    private CompoundGraphPreprocessor compoundGraphPreprocessor;
    /** compound graph postprocessor. */
    private CompoundGraphPostprocessor compoundGraphPostprocessor;
    /** cache of instantiated layout phases. */
    private final Map<Class<? extends ILayoutPhase>, ILayoutPhase> phaseCache = Maps.newHashMap();
    /** cache of instantiated intermediate modules. */
    private final Map<LayoutProcessorStrategy, ILayoutProcessor> intermediateLayoutProcessorCache =
            Maps.newHashMap();

    /** list of graphs that are currently being laid out. */
    private List<LGraph> currentLayoutTestGraphs;
    /** index of the processor that is to be executed next during a layout test. */
    private int currentLayoutTestStep;

    // /////////////////////////////////////////////////////////////////////////////
    // Regular Layout

    /**
     * Does a layout on the given graph. The returned graph may be a different instance than
     * the one given as argument.
     * 
     * @param lgraph
     *            the graph to layout
     * @param monitor
     *            a progress monitor to show progress information in, or {@code null}
     * @return a layered graph with layout applied
     */
    public LGraph doLayout(final LGraph lgraph, final IKielerProgressMonitor monitor) {
        IKielerProgressMonitor theMonitor = monitor;
        if (theMonitor == null) {
            theMonitor = new BasicProgressMonitor(0);
        }
        theMonitor.begin("Layered layout", 1);

        // Set special properties for the layered graph
        setOptions(lgraph);

        // Update the modules depending on user options
        updateModules(lgraph);
        if (componentsProcessor == null) {
            componentsProcessor = new ComponentsProcessor();
        }

        // Split the input graph into components and perform layout on them
        List<LGraph> components = componentsProcessor.split(lgraph);
        if (components.size() == 1) {
            // Execute layout on the sole component using the top-level progress monitor
            layout(components.get(0), theMonitor);
        } else {
            // Execute layout on each component using a progress monitor subtask
            float compWork = 1.0f / components.size();
            for (LGraph comp : components) {
                if (monitor.isCanceled()) {
                    return lgraph;
                }
                layout(comp, theMonitor.subTask(compWork));
            }
        }
        LGraph result = componentsProcessor.combine(components);
        
        // Resize the resulting graph, according to minimal size constraints and such
        resizeGraph(result);

        theMonitor.done();
        return result;
    }

    // /////////////////////////////////////////////////////////////////////////////
    // Compound Graph Layout

    /**
     * Does a layout on the given compound graph. Connected components processing is
     * currently not supported.
     * 
     * @param lgraph the graph to layout
     * @param monitor a progress monitor to show progress information in, or {@code null}
     * @return a layered graph with layout applied
     */
    public LGraph doCompoundLayout(final LGraph lgraph, final IKielerProgressMonitor monitor) {
        IKielerProgressMonitor theMonitor = monitor;
        if (theMonitor == null) {
            theMonitor = new BasicProgressMonitor(0);
        }
        theMonitor.begin("Layered layout", 3); // SUPPRESS CHECKSTYLE MagicNumber
        
        // Preprocess the compound graph by splitting cross-hierarchy edges
        if (compoundGraphPreprocessor == null) {
            compoundGraphPreprocessor = new CompoundGraphPreprocessor();
        }
        compoundGraphPreprocessor.process(lgraph, theMonitor.subTask(1));

        // Apply the layout algorithm recursively
        recursiveLayout(lgraph, theMonitor.subTask(1));
        
        // Postprocess the compound graph by combining split cross-hierarchy edges
        if (compoundGraphPostprocessor == null) {
            compoundGraphPostprocessor = new CompoundGraphPostprocessor();
        }
        compoundGraphPostprocessor.process(lgraph, theMonitor.subTask(1));

        theMonitor.done();
        return lgraph;
    }
    
    /**
     * Do a recursive compound graph layout.
     * 
     * @param graph the graph
     * @param monitor a progress monitor to show progress information
     */
    private void recursiveLayout(final LGraph graph, final IKielerProgressMonitor monitor) {
        monitor.begin("Recursive layout", 2);
        if (!graph.getLayerlessNodes().isEmpty()) {
            // Process all contained nested graphs recursively
            float workPerSubgraph = 1.0f / graph.getLayerlessNodes().size();
            for (LNode node : graph.getLayerlessNodes()) {
                LGraph nestedGraph = node.getProperty(Properties.NESTED_LGRAPH);
                if (nestedGraph != null) {
                    recursiveLayout(nestedGraph, monitor.subTask(workPerSubgraph));
                    graphLayoutToNode(node, nestedGraph);
                }
            }
            
            // Set special properties for the layered graph
            setOptions(graph);
    
            // Update the modules depending on user options
            updateModules(graph);
    
            // Perform the layout algorithm
            layout(graph, monitor);
        }
        
        // Resize the resulting graph, according to minimal size constraints and such
        resizeGraph(graph);

        monitor.done();
    }
    
    // /////////////////////////////////////////////////////////////////////////////
    // Layout Testing

    /**
     * Prepares a test run of the layout algorithm. If a previous test run is still active, an
     * exception is thrown. After this method has run, call {@link #layoutTestStep()} as often as
     * there are layout processors. Once the test run is finished, call
     * {@link #finalizeLayoutTest()}.
     * 
     * @param lgraph
     *            the input graph to initialize the test run with.
     * @throws IllegalStateException
     *             if a previous layout test run is still active.
     */
    public void prepareLayoutTest(final LGraph lgraph) {

        // check if a previous layout test run is still active
        if (currentLayoutTestGraphs != null || currentLayoutTestStep != 0) {
            throw new IllegalStateException("Previous layout test run not finalized.");
        }

        // set special properties for the layered graph
        setOptions(lgraph);

        // update the modules depending on user options
        updateModules(lgraph);

        // split the input graph into components
        currentLayoutTestGraphs = componentsProcessor.split(lgraph);
        currentLayoutTestStep = 0;
    }

    /**
     * Checks if the current test run still has processors to be executed for the algorithm to finish.
     * 
     * @return {@code true} if the current test run has not finished yet. If there is no current
     *         test run, the result is undefined.
     */
    public boolean isLayoutTestFinished() {
        if (currentLayoutTestGraphs == null) {
            return true;
        }
        LGraph graph = currentLayoutTestGraphs.get(0);
        List<ILayoutProcessor> algorithm = graph.getProperty(Properties.PROCESSORS);
        return algorithm != null && currentLayoutTestStep >= algorithm.size();
    }

    /**
     * Runs the algorithm on the current test graphs up to the point where the given phase or
     * processor has finished executing. If parts of the algorithm were already executed using this
     * or other layout test methods, execution is resumed from there. If the given phase or
     * processor is not among those processors that have not yet executed, an exception is thrown.
     * Also, if there is no current layout test run, an exception is thrown.
     * 
     * @param phase
     *            the phase or processor to stop after.
     * @param inclusive
     *            {@code true} if the specified phase should be executed as well
     * @return list of connected components after the execution of the given phase.
     * @throws IllegalStateException
     *             if no layout test run is currently active.
     * @throws IllegalArgumentException
     *             if the given layout processor is not part of the processors that are still to be
     *             executed.
     */
    public List<LGraph> runLayoutTestUntil(final Class<? extends ILayoutProcessor> phase,
            final boolean inclusive) {

        // check if a layout test run is active
        if (currentLayoutTestGraphs == null) {
            throw new IllegalStateException("No active layout test run.");
        }
        List<ILayoutProcessor> algorithm = currentLayoutTestGraphs.get(0).getProperty(
                Properties.PROCESSORS);

        // check if the given phase exists in our current algorithm configuration
        boolean phaseExists = false;
        int phaseIndex;
        for (phaseIndex = currentLayoutTestStep; phaseIndex < algorithm.size(); phaseIndex++) {
            if (algorithm.get(phaseIndex).getClass().equals(phase)) {
                phaseExists = true;
                if (inclusive) {
                    phaseIndex++;
                }
                break;
            }
        }

        if (!phaseExists) {
            throw new IllegalArgumentException(
                    "Given processor not part of the remaining algorithm.");
        }

        // perform the layout up to and including that phase
        for (; currentLayoutTestStep < phaseIndex; currentLayoutTestStep++) {
            layoutTest(currentLayoutTestGraphs, algorithm.get(currentLayoutTestStep));
        }

        return currentLayoutTestGraphs;
    }

    /**
     * Performs the {@link #runLayoutTestUntil(Class, boolean)} methods with {@code inclusive} set
     * to {@code true}.
     * 
     * @param phase
     *            the phase or processor to stop after.
     * 
     * @return list of connected components after the execution of the given phase.
     * 
     * @see KlayLayered#runLayoutTestUntil(Class, boolean)
     */
    public List<LGraph> runLayoutTestUntil(final Class<? extends ILayoutProcessor> phase) {
        return runLayoutTestUntil(phase, true);
    }

    /**
     * Runs the next step of the current layout test run. Throws exceptions if no layout test run is
     * currently active or if the current run has finished.
     * 
     * @return list of connected components after the execution of the next step.
     * @throws IllegalStateException
     *             if no layout test run is currently active or if the current run has finished
     *             executing.
     */
    public List<LGraph> runLayoutTestStep() {
        // check if a layout test run is active
        if (currentLayoutTestGraphs == null) {
            throw new IllegalStateException("No active layout test run.");
        }

        if (isLayoutTestFinished()) {
            throw new IllegalStateException("Current layout test run has finished.");
        }

        // perform the next layout step
        List<ILayoutProcessor> algorithm = currentLayoutTestGraphs.get(0).getProperty(
                Properties.PROCESSORS);
        layoutTest(currentLayoutTestGraphs, algorithm.get(currentLayoutTestStep));
        currentLayoutTestStep++;

        return currentLayoutTestGraphs;
    }

    /**
     * Finalizes the current layout test run. After this method has been called, the next test run
     * can be started by calling {@link #prepareLayoutTest(LGraph, IKielerProgressMonitor)}.
     * 
     * @throws IllegalStateException
     *             if no layout test run is currently active.
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
     * Returns the current list of layout processors that make up the algorithm. This list is only
     * valid and meaningful while a layout test is being run.
     * 
     * @return the algorithm's current configuration.
     * @throws IllegalStateException
     *             if no layout test run is currently active.
     */
    public List<ILayoutProcessor> getLayoutTestConfiguration() {
        // check if a layout test run is active
        if (currentLayoutTestGraphs == null) {
            throw new IllegalStateException("No active layout test run.");
        }
        List<ILayoutProcessor> algorithm = currentLayoutTestGraphs.get(0).getProperty(
                Properties.PROCESSORS);
        return algorithm;
    }

    /**
     * Returns the list of test graphs associated with the current layout test run. If connected
     * components processing is active, the list will contain one {@link LGraph} instance for each
     * connected component. Otherwise, the list will contain just one {@link LGraph}.
     * 
     * @return layout test graphs.
     * @throws IllegalStateException
     *             if no layout test run is currently active.
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
    
    /** the minimal spacing between edges, so edges won't overlap. */
    private static final float MIN_EDGE_SPACING = 2.0f;

    /**
     * Set special layout options for the layered graph.
     * 
     * @param layeredGraph
     *            a new layered graph
     */
    private void setOptions(final LGraph layeredGraph) {
        // check the bounds of some layout options
        layeredGraph.checkProperties(Properties.OBJ_SPACING, Properties.BORDER_SPACING,
                Properties.THOROUGHNESS, Properties.ASPECT_RATIO);
        float spacing = layeredGraph.getProperty(Properties.OBJ_SPACING);
        if (layeredGraph.getProperty(Properties.EDGE_SPACING_FACTOR) * spacing < MIN_EDGE_SPACING) {
            // Edge spacing is determined by the product of object spacing and edge spacing factor.
            // Make sure the resulting edge spacing is at least 2 in order to avoid overlapping edges.
            layeredGraph.setProperty(Properties.EDGE_SPACING_FACTOR, MIN_EDGE_SPACING / spacing);
        }
        Direction direction = layeredGraph.getProperty(LayoutOptions.DIRECTION);
        if (direction == Direction.UNDEFINED) {
            // The default layout direction is right.
            direction = Direction.RIGHT;
            layeredGraph.setProperty(LayoutOptions.DIRECTION, direction);
        }
        
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
        ILayoutPhase cycleBreaker;
        switch (graph.getProperty(Properties.CYCLE_BREAKING)) {
        case INTERACTIVE:
            cycleBreaker = phaseCache.get(InteractiveCycleBreaker.class);
            if (cycleBreaker == null) {
                cycleBreaker = new InteractiveCycleBreaker();
                phaseCache.put(InteractiveCycleBreaker.class, cycleBreaker);
            }
            break;
        default: // GREEDY
            cycleBreaker = phaseCache.get(GreedyCycleBreaker.class);
            if (cycleBreaker == null) {
                cycleBreaker = new GreedyCycleBreaker();
                phaseCache.put(GreedyCycleBreaker.class, cycleBreaker);
            }
        }
        
        // check which layering strategy to use
        ILayoutPhase layerer;
        switch (graph.getProperty(Properties.NODE_LAYERING)) {
        case LONGEST_PATH:
            layerer = phaseCache.get(LongestPathLayerer.class);
            if (layerer == null) {
                layerer = new LongestPathLayerer();
                phaseCache.put(LongestPathLayerer.class, layerer);
            }
            break;
        case INTERACTIVE:
            layerer = phaseCache.get(InteractiveLayerer.class);
            if (layerer == null) {
                layerer = new InteractiveLayerer();
                phaseCache.put(InteractiveLayerer.class, layerer);
            }
            break;
        default: // NETWORK_SIMPLEX
            layerer = phaseCache.get(NetworkSimplexLayerer.class);
            if (layerer == null) {
                layerer = new NetworkSimplexLayerer();
                phaseCache.put(NetworkSimplexLayerer.class, layerer);
            }
        }
        
        // check which crossing minimization strategy to use
        ILayoutPhase crossingMinimizer;
        switch (graph.getProperty(Properties.CROSS_MIN)) {
        case INTERACTIVE:
            crossingMinimizer = phaseCache.get(InteractiveCrossingMinimizer.class);
            if (crossingMinimizer == null) {
                crossingMinimizer = new InteractiveCrossingMinimizer();
                phaseCache.put(InteractiveCrossingMinimizer.class, crossingMinimizer);
            }
            break;
        default: // LAYER_SWEEP
            crossingMinimizer = phaseCache.get(LayerSweepCrossingMinimizer.class);
            if (crossingMinimizer == null) {
                crossingMinimizer = new LayerSweepCrossingMinimizer();
                phaseCache.put(LayerSweepCrossingMinimizer.class, crossingMinimizer);
            }
        }

        // check which node placement strategy to use
        ILayoutPhase nodePlacer;
        switch (graph.getProperty(Properties.NODE_PLACER)) {
        case SIMPLE:
            nodePlacer = phaseCache.get(SimpleNodePlacer.class);
            if (nodePlacer == null) {
                nodePlacer = new SimpleNodePlacer();
                phaseCache.put(SimpleNodePlacer.class, nodePlacer);
            }
            break;
        case LINEAR_SEGMENTS:
            nodePlacer = phaseCache.get(LinearSegmentsNodePlacer.class);
            if (nodePlacer == null) {
                nodePlacer = new LinearSegmentsNodePlacer();
                phaseCache.put(LinearSegmentsNodePlacer.class, nodePlacer);
            }
            break;
        default: // BRANDES_KOEPF
            nodePlacer = phaseCache.get(BKNodePlacer.class);
            if (nodePlacer == null) {
                nodePlacer = new BKNodePlacer();
                phaseCache.put(BKNodePlacer.class, nodePlacer);
            }
        }

        // check which edge router to use
        ILayoutPhase edgeRouter;
        switch (graph.getProperty(LayoutOptions.EDGE_ROUTING)) {
        case ORTHOGONAL:
            edgeRouter = phaseCache.get(OrthogonalEdgeRouter.class);
            if (edgeRouter == null) {
                edgeRouter = new OrthogonalEdgeRouter();
                phaseCache.put(OrthogonalEdgeRouter.class, edgeRouter);
            }
            break;
        case SPLINES:
            edgeRouter = phaseCache.get(SplineEdgeRouter.class);
            if (edgeRouter == null) {
                edgeRouter = new SplineEdgeRouter();
                phaseCache.put(SplineEdgeRouter.class, edgeRouter);
            }
            break;
        default: // POLYLINE
            edgeRouter = phaseCache.get(PolylineEdgeRouter.class);
            if (edgeRouter == null) {
                edgeRouter = new PolylineEdgeRouter();
                phaseCache.put(PolylineEdgeRouter.class, edgeRouter);
            }
        }

        // determine intermediate processor configuration
        IntermediateProcessingConfiguration intermediateProcessingConfiguration
                = new IntermediateProcessingConfiguration();
        graph.setProperty(Properties.CONFIGURATION, intermediateProcessingConfiguration);
        intermediateProcessingConfiguration
                .addAll(cycleBreaker.getIntermediateProcessingConfiguration(graph))
                .addAll(layerer.getIntermediateProcessingConfiguration(graph))
                .addAll(crossingMinimizer.getIntermediateProcessingConfiguration(graph))
                .addAll(nodePlacer.getIntermediateProcessingConfiguration(graph))
                .addAll(edgeRouter.getIntermediateProcessingConfiguration(graph))
                .addAll(this.getIntermediateProcessingConfiguration(graph));

        // construct the list of processors that make up the algorithm
        List<ILayoutProcessor> algorithm = Lists.newLinkedList();
        graph.setProperty(Properties.PROCESSORS, algorithm);
        algorithm.addAll(getIntermediateProcessorList(intermediateProcessingConfiguration,
                        IntermediateProcessingConfiguration.BEFORE_PHASE_1));
        algorithm.add(cycleBreaker);
        algorithm.addAll(getIntermediateProcessorList(intermediateProcessingConfiguration,
                        IntermediateProcessingConfiguration.BEFORE_PHASE_2));
        algorithm.add(layerer);
        algorithm.addAll(getIntermediateProcessorList(intermediateProcessingConfiguration,
                        IntermediateProcessingConfiguration.BEFORE_PHASE_3));
        algorithm.add(crossingMinimizer);
        algorithm.addAll(getIntermediateProcessorList(intermediateProcessingConfiguration,
                        IntermediateProcessingConfiguration.BEFORE_PHASE_4));
        algorithm.add(nodePlacer);
        algorithm.addAll(getIntermediateProcessorList(intermediateProcessingConfiguration,
                        IntermediateProcessingConfiguration.BEFORE_PHASE_5));
        algorithm.add(edgeRouter);
        algorithm.addAll(getIntermediateProcessorList(intermediateProcessingConfiguration,
                        IntermediateProcessingConfiguration.AFTER_PHASE_5));
    }

    /**
     * Returns a list of layout processor instances for the given intermediate layout processing slot.
     * 
     * @param configuration
     *            the intermediate processing configuration
     * @param slotIndex
     *            the slot index. One of the constants defined in
     *            {@link IntermediateProcessingConfiguration}.
     * @return list of layout processors.
     */
    private List<ILayoutProcessor> getIntermediateProcessorList(
            final IntermediateProcessingConfiguration configuration, final int slotIndex) {
        // fetch the set of layout processors configured for the given slot
        EnumSet<LayoutProcessorStrategy> processors = configuration.getProcessors(slotIndex);
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
        IntermediateProcessingConfiguration configuration =
                new IntermediateProcessingConfiguration(BASELINE_PROCESSING_CONFIGURATION);

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
            // This is either RIGHT or UNDEFINED, which is just mapped to RIGHT. Either way, we
            // don't
            // need any processors here
            break;
        }

        // Additional dependencies
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
     * @param monitor
     *            a progress monitor
     */
    private void layout(final LGraph graph, final IKielerProgressMonitor monitor) {
        boolean monitorStarted = monitor.isRunning();
        if (!monitorStarted) {
            monitor.begin("Component Layout", 1);
        }
        List<ILayoutProcessor> algorithm = graph.getProperty(Properties.PROCESSORS);
        float monitorProgress = 1.0f / algorithm.size();

        if (graph.getProperty(LayoutOptions.DEBUG_MODE)) {
            // Debug Mode!
            // Print the algorithm configuration and output the whole graph to a file
            // before each slot execution

            System.out.println("KLay Layered uses the following " + algorithm.size() + " modules:");
            for (int i = 0; i < algorithm.size(); i++) {
                System.out.println("   Slot " + String.format("%1$02d", i) + ": "
                        + algorithm.get(i).getClass().getName());
            }

            // Invoke each layout processor
            int slotIndex = 0;
            for (ILayoutProcessor processor : algorithm) {
                if (monitor.isCanceled()) {
                    return;
                }
                // Graph debug output
                DebugUtil.writeDebugGraph(graph, slotIndex++);

                processor.process(graph, monitor.subTask(monitorProgress));
            }

            // Graph debug output
            DebugUtil.writeDebugGraph(graph, slotIndex++);
        } else {
            
            // Invoke each layout processor
            for (ILayoutProcessor processor : algorithm) {
                if (monitor.isCanceled()) {
                    return;
                }
                processor.process(graph, monitor.subTask(monitorProgress));
            }
        }
        
        // Move all nodes away from the layers
        for (Layer layer : graph) {
            graph.getLayerlessNodes().addAll(layer.getNodes());
        }
        graph.getLayers().clear();

        if (!monitorStarted) {
            monitor.done();
        }
    }

    /**
     * Executes the given layout processor on the given list of graphs.
     * 
     * @param graphs
     *            the list of graphs to be laid out.
     * @param monitor
     *            a progress monitor.
     * @param processor
     *            processor to execute.
     */
    private void layoutTest(final List<LGraph> graphs, final ILayoutProcessor processor) {

        // invoke the layout processor on each of the given graphs
        for (LGraph graph : graphs) {
            processor.process(graph, new BasicProgressMonitor());
        }
    }

    // /////////////////////////////////////////////////////////////////////////////
    // Graph Postprocessing (Size and External Ports)
    
    /**
     * Sets the size of the given graph such that size constraints are adhered to.
     * Furthermore, the border spacing is added to the graph size and the graph offset.
     * Afterwards, the border spacing property is reset to 0.
     * 
     * <p>Major parts of this method are adapted from
     * {@link KimlUtil#resizeNode(de.cau.cs.kieler.core.kgraph.KNode, float, float, boolean)}.</p>
     * 
     * <p>Note: This method doesn't care about labels of compound nodes since those labels are not
     * attached to the graph.</p>
     * 
     * @param graph the graph to resize.
     */
    private void resizeGraph(final LGraph graph) {
        Set<SizeConstraint> sizeConstraint = graph.getProperty(LayoutOptions.SIZE_CONSTRAINT);
        Set<SizeOptions> sizeOptions = graph.getProperty(LayoutOptions.SIZE_OPTIONS);
        float borderSpacing = graph.getProperty(Properties.BORDER_SPACING);
        
        // add the border spacing to the graph size and graph offset
        graph.getOffset().x += borderSpacing;
        graph.getOffset().y += borderSpacing;
        graph.getSize().x += 2 * borderSpacing;
        graph.getSize().y += 2 * borderSpacing;
        
        // the graph size now contains the border spacing, so clear it in order to keep
        // graph.getActualSize() working properly
        graph.setProperty(Properties.BORDER_SPACING, 0f);
        
        // calculate the new size
        if (sizeConstraint.contains(SizeConstraint.MINIMUM_SIZE)) {
            // remember the graph's old size (including border spacing and insets)
            KVector oldSize = graph.getActualSize();
            
            float minWidth = graph.getProperty(LayoutOptions.MIN_WIDTH);
            float minHeight = graph.getProperty(LayoutOptions.MIN_HEIGHT);
            
            // if minimum width or height are not set, maybe default to default values
            if (sizeOptions.contains(SizeOptions.DEFAULT_MINIMUM_SIZE)) {
                if (minWidth <= 0) {
                    minWidth = KimlUtil.DEFAULT_MIN_WIDTH;
                }
                
                if (minHeight <= 0) {
                    minHeight = KimlUtil.DEFAULT_MIN_HEIGHT;
                }
            }
            
            // apply new size including border spacing
            double newWidth = Math.max(oldSize.x, minWidth);
            double newHeight = Math.max(oldSize.y, minHeight);
            LInsets insets = graph.getInsets();
            graph.getSize().x = newWidth - insets.left - insets.right;
            graph.getSize().y = newHeight - insets.top - insets.bottom;
            
            // correct the position of eastern and southern hierarchical ports, if necessary
            if (graph.getProperty(Properties.GRAPH_PROPERTIES).contains(GraphProperties.EXTERNAL_PORTS)
                    && (newWidth > oldSize.x || newHeight > oldSize.y)) {
                
                // iterate over the graph's nodes, looking for eastern / southern external ports
                // (at this point, the graph's nodes are not divided into layers anymore)
                for (LNode node : graph.getLayerlessNodes()) {
                    // we're only looking for external port dummies
                    if (node.getProperty(Properties.NODE_TYPE) == NodeType.EXTERNAL_PORT) {
                        // check which side the external port is on
                        PortSide extPortSide = node.getProperty(Properties.EXT_PORT_SIDE);
                        if (extPortSide == PortSide.EAST) {
                            node.getPosition().x += newWidth - oldSize.x;
                        } else  if (extPortSide == PortSide.SOUTH) {
                            node.getPosition().y += newHeight - oldSize.y;
                        }
                    }
                }
            }
        }
    }
    
    /**
     * Transfer the layout of the given graph to the given associated node.
     * 
     * @param node a compound node
     * @param graph the graph nested in the compound node
     */
    private void graphLayoutToNode(final LNode node, final LGraph graph) {
        // Process external ports
        for (LNode childNode : graph.getLayerlessNodes()) {
            Object origin = childNode.getProperty(Properties.ORIGIN);
            if (origin instanceof LPort) {
                LPort port = (LPort) origin;
                KVector portPosition = LGraphUtil.getExternalPortPosition(graph, childNode,
                        port.getSize().x, port.getSize().y);
                port.getPosition().x = portPosition.x;
                port.getPosition().y = portPosition.y;
                port.setSide(childNode.getProperty(Properties.EXT_PORT_SIDE));
            }
        }
        
        // Setup the parent node
        KVector actualGraphSize = graph.getActualSize();
        if (graph.getProperty(Properties.GRAPH_PROPERTIES).contains(
                GraphProperties.EXTERNAL_PORTS)) {
            // Ports have positions assigned
            node.setProperty(LayoutOptions.PORT_CONSTRAINTS, PortConstraints.FIXED_POS);
            LGraphUtil.resizeNode(node, actualGraphSize, false, true);
        } else {
            // Ports have not been positioned yet - leave this for next layouter
            LGraphUtil.resizeNode(node, actualGraphSize, true, true);
        }
    }

    // /////////////////////////////////////////////////////////////////////////////
    // Processing Configuration Constants

    /** intermediate processing configuration for basic graphs. */
    private static final IntermediateProcessingConfiguration BASELINE_PROCESSING_CONFIGURATION =
            new IntermediateProcessingConfiguration(null, null, null,

                    // Before Phase 4
                    EnumSet.of(LayoutProcessorStrategy.NODE_MARGIN_CALCULATOR,
                            LayoutProcessorStrategy.LABEL_AND_NODE_SIZE_PROCESSOR),

                    // Before Phase 5
                    EnumSet.of(LayoutProcessorStrategy.LAYER_SIZE_AND_GRAPH_HEIGHT_CALCULATOR),

                    null);

}
