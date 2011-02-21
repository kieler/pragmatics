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

import java.util.List;
import java.util.Random;

import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.core.KielerRuntimeException;
import de.cau.cs.kieler.core.alg.BasicProgressMonitor;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.AbstractLayoutProvider;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.util.IDebugCanvas;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;
import de.cau.cs.kieler.klay.layered.p1cycles.GreedyCycleBreaker;
import de.cau.cs.kieler.klay.layered.p1cycles.ICycleBreaker;
import de.cau.cs.kieler.klay.layered.p2layers.ILayerer;
import de.cau.cs.kieler.klay.layered.p2layers.LPSolveLayerer;
import de.cau.cs.kieler.klay.layered.p2layers.LongestPathLayerer;
import de.cau.cs.kieler.klay.layered.p2layers.NetworkSimplexLayerer;
import de.cau.cs.kieler.klay.layered.p2layers.LayeringStrategy;
import de.cau.cs.kieler.klay.layered.p3order.CrossingMinimizationStrategy;
import de.cau.cs.kieler.klay.layered.p3order.ICrossingMinimizer;
import de.cau.cs.kieler.klay.layered.p3order.LPSolveCrossingMinimizer;
import de.cau.cs.kieler.klay.layered.p3order.LayerSweepCrossingMinimizer;
import de.cau.cs.kieler.klay.layered.p4nodes.INodePlacer;
import de.cau.cs.kieler.klay.layered.p4nodes.LinearSegmentsNodePlacer;
import de.cau.cs.kieler.klay.layered.p5edges.ComplexSplineEdgeRouter;
import de.cau.cs.kieler.klay.layered.p5edges.IEdgeRouter;
import de.cau.cs.kieler.klay.layered.p5edges.EdgeRoutingStrategy;
import de.cau.cs.kieler.klay.layered.p5edges.OrthogonalEdgeRouter;
import de.cau.cs.kieler.klay.layered.p5edges.PolylineEdgeRouter;
import de.cau.cs.kieler.klay.layered.p5edges.SimpleSplineEdgeRouter;

/**
 * Layout provider to connect the layered layouter to the Eclipse based layout services.
 * 
 * @author msp
 */
public class LayeredLayoutProvider extends AbstractLayoutProvider {

    /** phase 1: cycle breaking module. */
    private ICycleBreaker cycleBreaker = new GreedyCycleBreaker();
    /** phase 2: layering module. */
    private ILayerer layerer;
    /** phase 3: crossing minimization module. */
    private ICrossingMinimizer crossingMinimizer;
    /** phase 4: node placement module. */
    private INodePlacer nodePlacer = new LinearSegmentsNodePlacer();
    /** phase 5: Edge routing module. */
    private IEdgeRouter edgeRouter;
    
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
        monitor.begin("Layered layout phases", 1 + 1 + 1 + 1 + 1);
        LayeredGraph layeredGraph = importer.getGraph();
        List<LNode> nodes = importer.getImportedNodes();

        // phase 1: cycle breaking
        cycleBreaker.reset(monitor.subTask(1));
        cycleBreaker.breakCycles(nodes);
        // phase 2: layering
        layerer.reset(monitor.subTask(1));
        layerer.layer(nodes, layeredGraph);
        layeredGraph.splitEdges();
        // phase 3: crossing minimization
        crossingMinimizer.reset(monitor.subTask(1));
        crossingMinimizer.minimizeCrossings(layeredGraph);
        // phase 4: node placement
        nodePlacer.reset(monitor.subTask(1));
        nodePlacer.placeNodes(layeredGraph);
        // phase 5: edge routing
        edgeRouter.reset(monitor.subTask(1));
        edgeRouter.routeEdges(layeredGraph);

        monitor.done();
    }

}
