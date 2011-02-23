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

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.core.KielerRuntimeException;
import de.cau.cs.kieler.core.alg.BasicProgressMonitor;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.AbstractLayoutProvider;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.util.IDebugCanvas;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;
import de.cau.cs.kieler.klay.layered.intermediate.EdgeSplitter;
import de.cau.cs.kieler.klay.layered.intermediate.IntermediateLayoutProcessor;
import de.cau.cs.kieler.klay.layered.intermediate.StrangePortSideProcessor;
import de.cau.cs.kieler.klay.layered.p1cycles.GreedyCycleBreaker;
import de.cau.cs.kieler.klay.layered.p2layers.LPSolveLayerer;
import de.cau.cs.kieler.klay.layered.p2layers.LongestPathLayerer;
import de.cau.cs.kieler.klay.layered.p2layers.NetworkSimplexLayerer;
import de.cau.cs.kieler.klay.layered.p2layers.LayeringStrategy;
import de.cau.cs.kieler.klay.layered.p3order.CrossingMinimizationStrategy;
import de.cau.cs.kieler.klay.layered.p3order.LPSolveCrossingMinimizer;
import de.cau.cs.kieler.klay.layered.p3order.LayerSweepCrossingMinimizer;
import de.cau.cs.kieler.klay.layered.p4nodes.LinearSegmentsNodePlacer;
import de.cau.cs.kieler.klay.layered.p5edges.ComplexSplineEdgeRouter;
import de.cau.cs.kieler.klay.layered.p5edges.EdgeRoutingStrategy;
import de.cau.cs.kieler.klay.layered.p5edges.OrthogonalEdgeRouter;
import de.cau.cs.kieler.klay.layered.p5edges.PolylineEdgeRouter;
import de.cau.cs.kieler.klay.layered.p5edges.SimpleSplineEdgeRouter;

/**
 * Layout provider to connect the layered layouter to the Eclipse based layout services.
 * 
 * <p>
 * The layered layouter works with five main phases: cycle breaking, layering, crossing
 * minimization, node placement and edge routing. Before these phases and after the last
 * phase, so called intermediate layout processors can be inserted that do some kind of
 * pre or post processing. Implementations of the different main phases specify the
 * intermediate layout processors they require, which are automatically collected and
 * inserted between the main phases.
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
 * @see ILayoutPhase
 * @see ILayoutProcessor
 * 
 * @author msp
 * @author cds
 */
public class LayeredLayoutProvider extends AbstractLayoutProvider {

    /** phase 1: cycle breaking module. */
    private ILayoutPhase cycleBreaker = new GreedyCycleBreaker();
    /** phase 2: layering module. */
    private ILayoutPhase layerer;
    /** phase 3: crossing minimization module. */
    private ILayoutPhase crossingMinimizer;
    /** phase 4: node placement module. */
    private ILayoutPhase nodePlacer = new LinearSegmentsNodePlacer();
    /** phase 5: Edge routing module. */
    private ILayoutPhase edgeRouter;
    /** intermediate layout processor strategy. */
    private IntermediateProcessingStrategy intermediateProcessingStrategy =
        new IntermediateProcessingStrategy();
    /** collection of instantiated intermediate modules. */
    private Map<IntermediateLayoutProcessor, ILayoutProcessor> intermediateLayoutProcessorCache =
        new HashMap<IntermediateLayoutProcessor, ILayoutProcessor>();
    /** list of layout processors that compose the current algorithm. */
    private List<ILayoutProcessor> algorithm = new LinkedList<ILayoutProcessor>();
    
    
    /**
     * Initialize default options of the layout provider.
     */
    public LayeredLayoutProvider() {
        setProperty(LayoutOptions.SPACING, Properties.DEF_SPACING);
        setProperty(LayoutOptions.BORDER_SPACING, Properties.DEF_SPACING);
        setProperty(LayoutOptions.RANDOM_SEED, 1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doLayout(final KNode layoutNode, final IKielerProgressMonitor progressMonitor)
            throws KielerException {
        progressMonitor.begin("Layered layout", 1);

        // update the modules depending on user options
        updateModules(layoutNode.getData(KShapeLayout.class));

        // transform the input graph
        IGraphImporter graphImporter = new KGraphImporter(layoutNode);
        LayeredGraph layeredGraph = graphImporter.getGraph();

        // set special properties for the layered graph
        setOptions(layeredGraph, layoutNode);

        // perform the actual layout
        layout(graphImporter, progressMonitor.subTask(1));
        // apply the layout results to the original graph
        graphImporter.applyLayout();

        progressMonitor.done();
    }
    
    /**
     * Update the modules depending on user options.
     * 
     * @param parentLayout the parent layout data
     */
    private void updateModules(final KShapeLayout parentLayout) {
        // check which layering strategy to use
        LayeringStrategy placing = parentLayout.getProperty(Properties.NODE_LAYERING);
        switch (placing) {
        case LONGEST_PATH:
            if (!(layerer instanceof LongestPathLayerer)) {
                layerer = new LongestPathLayerer();
            }
            break;
        case LP_SOLVER:
            if (!(layerer instanceof LPSolveLayerer)) {
                try {
                    layerer = new LPSolveLayerer();
                } catch (Throwable throwable) {
                    // this will only occur if the required LpSolve classes can't be loaded
                    throw new KielerRuntimeException("The LpSolve plug-in is not installed."
                            + " Please choose another layering method.", throwable); 
                }
            }
            break;
        default:
            if (!(layerer instanceof NetworkSimplexLayerer)) {
                layerer = new NetworkSimplexLayerer();
            }
        }
        
        // check which crossing minimization strategy to use
        CrossingMinimizationStrategy crossMin = parentLayout.getProperty(Properties.CROSS_MIN);
        switch (crossMin) {
        case LP_SOLVER:
            if (!(crossingMinimizer instanceof LPSolveCrossingMinimizer)) {
                crossingMinimizer = new LPSolveCrossingMinimizer();
            }
            break;
        default:
            if (!(crossingMinimizer instanceof LayerSweepCrossingMinimizer)) {
                crossingMinimizer = new LayerSweepCrossingMinimizer();
            }
        }

        // check which edge router to use
        EdgeRoutingStrategy routing = parentLayout.getProperty(Properties.EDGE_ROUTING);
        switch (routing) {
        case ORTHOGONAL:
            if (!(edgeRouter instanceof OrthogonalEdgeRouter)) {
                edgeRouter = new OrthogonalEdgeRouter();
            }
            break;
        case SIMPLE_SPLINES:
            if (!(edgeRouter instanceof SimpleSplineEdgeRouter)) {
                edgeRouter = new SimpleSplineEdgeRouter();
            }
            break;
        case COMPLEX_SPLINES:
            if (!(edgeRouter instanceof ComplexSplineEdgeRouter)) {
                edgeRouter = new ComplexSplineEdgeRouter();
            }
            break;
        default:
            if (!(edgeRouter instanceof PolylineEdgeRouter)) {
                edgeRouter = new PolylineEdgeRouter();
            }
        }
        
        // update intermediate processor strategy
        intermediateProcessingStrategy.clear();
        intermediateProcessingStrategy.addAll(cycleBreaker.getIntermediateProcessingStrategy())
            .addAll(layerer.getIntermediateProcessingStrategy())
            .addAll(crossingMinimizer.getIntermediateProcessingStrategy())
            .addAll(nodePlacer.getIntermediateProcessingStrategy())
            .addAll(edgeRouter.getIntermediateProcessingStrategy());
        
        // construct the list of processors that make up the algorithm
        algorithm.clear();
        algorithm.addAll(getIntermediateProcessorList(IntermediateProcessingStrategy.BEFORE_PHASE_1));
        algorithm.add(cycleBreaker);
        algorithm.addAll(getIntermediateProcessorList(IntermediateProcessingStrategy.BEFORE_PHASE_2));
        algorithm.add(layerer);
        algorithm.addAll(getIntermediateProcessorList(IntermediateProcessingStrategy.BEFORE_PHASE_3));
        algorithm.add(crossingMinimizer);
        algorithm.addAll(getIntermediateProcessorList(IntermediateProcessingStrategy.BEFORE_PHASE_4));
        algorithm.add(nodePlacer);
        algorithm.addAll(getIntermediateProcessorList(IntermediateProcessingStrategy.BEFORE_PHASE_5));
        algorithm.add(edgeRouter);
        algorithm.addAll(getIntermediateProcessorList(IntermediateProcessingStrategy.AFTER_PHASE_5));
    }
    
    /**
     * Returns a list of layout processor instances for the given intermediate layout
     * processing slot.
     * 
     * @param slotIndex the slot index. One of the constants defined in
     *                  {@link IntermediateProcessingStrategy}.
     * @return list of layout processors.
     */
    private List<ILayoutProcessor> getIntermediateProcessorList(final int slotIndex) {
        // fetch the set of layout processors configured for the given slot
        List<ILayoutProcessor> result = new LinkedList<ILayoutProcessor>();
        Set<IntermediateLayoutProcessor> processors =
            intermediateProcessingStrategy.getProcessors(slotIndex);
        
        // iterate through the layout processors and add them to the result list
        for (IntermediateLayoutProcessor processor : processors) {
            // check if an instance of the given layout processor is already in the cache
            ILayoutProcessor processorImpl = intermediateLayoutProcessorCache.get(processor);
            
            if (processorImpl == null) {
                // instantiate the layout processor and add it to the cache
                switch (processor) {
                case EDGE_SPLITTER:
                    processorImpl = new EdgeSplitter();
                    break;
                
                case STRANGE_PORT_SIDE_PROCESSOR:
                    processorImpl = new StrangePortSideProcessor();
                    break;
                }
                
                intermediateLayoutProcessorCache.put(processor, processorImpl);
            }
            
            result.add(processorImpl);
        }
        
        return result;
    }
    
    /**
     * Set special layout options for the layered graph.
     * 
     * @param layeredGraph a new layered graph
     * @param parent the original parent node
     */
    private void setOptions(final LayeredGraph layeredGraph, final KNode parent) {
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

        // set the debug canvas based on the debug mode option
        Boolean debugMode = layeredGraph.getProperty(LayoutOptions.DEBUG_MODE);
        if (debugMode) {
            IDebugCanvas debugCanvas = getDebugCanvas();
            layeredGraph.setProperty(Properties.DEBUG_CANVAS, debugCanvas);
            float borderSpacing = layeredGraph.getProperty(LayoutOptions.BORDER_SPACING);
            debugCanvas.setOffset(parent, borderSpacing, borderSpacing);
            debugCanvas.setBuffered(true);
        }
    }

    /**
     * Perform the five phases of the layered layouter.
     * 
     * @param importer
     *            the graph importer
     * @param themonitor
     *            a progress monitor, or {@code null}
     */
    public void layout(final IGraphImporter importer, final IKielerProgressMonitor themonitor) {
        IKielerProgressMonitor monitor = themonitor;
        if (monitor == null) {
            monitor = new BasicProgressMonitor();
        }
        monitor.begin("Layered layout phases", algorithm.size());
        LayeredGraph layeredGraph = importer.getGraph();
        
        // TODO BEGIN DEBUG CODE
        System.out.println("LAYOUT ALGORITHM:");
        for (Object o : algorithm) {
            System.out.println(o.getClass().getName());
        }
        // END DEBUG CODE

        // invoke each layout processor
        for (ILayoutProcessor processor : algorithm) {
            processor.reset(monitor.subTask(1));
            processor.process(layeredGraph);
        }

        monitor.done();
    }

}
