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

import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.core.alg.BasicProgressMonitor;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.AbstractLayoutProvider;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortType;
import de.cau.cs.kieler.kiml.util.IDebugCanvas;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;
import de.cau.cs.kieler.klay.layered.impl.GreedyCycleBreaker;
import de.cau.cs.kieler.klay.layered.impl.LPSolveLayerer;
import de.cau.cs.kieler.klay.layered.impl.LayerSweepCrossingMinimizer;
import de.cau.cs.kieler.klay.layered.impl.LinearSegmentsNodePlacer;
import de.cau.cs.kieler.klay.layered.impl.LongestPathLayerer;
import de.cau.cs.kieler.klay.layered.impl.NetworkSimplexLayerer;
import de.cau.cs.kieler.klay.layered.impl.OrthogonalEdgeRouter;
import de.cau.cs.kieler.klay.layered.impl.SimpleSplineEdgeRouter;
import de.cau.cs.kieler.klay.layered.impl.PolylineEdgeRouter;
import de.cau.cs.kieler.klay.layered.impl.ComplexSplineEdgeRouter;
import de.cau.cs.kieler.klay.layered.modules.ICrossingMinimizer;
import de.cau.cs.kieler.klay.layered.modules.ICycleBreaker;
import de.cau.cs.kieler.klay.layered.modules.IEdgeRouter;
import de.cau.cs.kieler.klay.layered.modules.ILayerer;
import de.cau.cs.kieler.klay.layered.modules.INodePlacer;

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
    private ICrossingMinimizer crossingMinimizer = new LayerSweepCrossingMinimizer();
    /** phase 4: node placement module. */
    private INodePlacer nodePlacer = new LinearSegmentsNodePlacer();
    /** phase 5: Edge routing module. */
    private IEdgeRouter edgeRouter;

    /**
     * {@inheritDoc}
     */
    @Override
    public void doLayout(final KNode layoutNode, final IKielerProgressMonitor progressMonitor)
            throws KielerException {
        progressMonitor.begin("Layered layout", 1);
        KShapeLayout parentLayout = layoutNode.getData(KShapeLayout.class);

        // check which EdgeRouter to use
        LayeredEdgeRouting routing = parentLayout.getProperty(Properties.EDGE_ROUTING);
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
            break;
        }

        // check which nodeLayouter to use
        NodeLayering placing = parentLayout.getProperty(Properties.NODE_LAYERING);
        switch (placing) {
        case LONGEST_PATH:
            if (!(layerer instanceof LongestPathLayerer)) {
                layerer = new LongestPathLayerer();
            }
            break;
        case LP_SOLVER:
            if (!(layerer instanceof LPSolveLayerer)) {
                layerer = new LPSolveLayerer();
            }
            break;
        default:
            if (!(layerer instanceof NetworkSimplexLayerer)) {
                layerer = new NetworkSimplexLayerer();
            }
            break;
        }

        // transform the input graph
        IGraphImporter graphImporter = new KGraphImporter(layoutNode);
        LayeredGraph layeredGraph = graphImporter.getGraph();

        // set object spacing option
        float objSpacing = parentLayout.getProperty(LayoutOptions.OBJ_SPACING);
        if (objSpacing >= 0) {
            layeredGraph.setProperty(Properties.OBJ_SPACING, objSpacing);
        }
        // add information for LinearSegmentsNodePlacer
        layeredGraph.setProperty(Properties.STRAIGHT_EDGES,
                parentLayout.getProperty(Properties.STRAIGHT_EDGES));
        // add information for LinearSegmentsNodePlacer
        layeredGraph.setProperty(Properties.DISTRIBUTE_NODES,
                parentLayout.getProperty(Properties.DISTRIBUTE_NODES));
        // set minimal edge angle
        layeredGraph.setProperty(Properties.MIN_EDGE_ANGLE,
                parentLayout.getProperty(Properties.MIN_EDGE_ANGLE));
        // add information for the layering algorithm
        layeredGraph.setProperty(Properties.SEGMENTATE_LAYERING,
                parentLayout.getProperty(Properties.SEGMENTATE_LAYERING));

        // set debug mode option
        Boolean debugMode = parentLayout.getProperty(LayoutOptions.DEBUG_MODE);
        if (debugMode) {
            // get debug canvas and clear
            IDebugCanvas debugCanvas = getDebugCanvas();
            layeredGraph.setProperty(LayoutOptions.DEBUG_MODE, true);
            layeredGraph.setProperty(Properties.DEBUG_CANVAS, debugCanvas);
            float borderspacing = parentLayout.getProperty(LayoutOptions.BORDER_SPACING);
            if (borderspacing < 0) {
                borderspacing = Properties.DEF_SPACING;
            }
            debugCanvas.setOffset(layoutNode, borderspacing, borderspacing);
        }

        // perform the actual layout
        layout(graphImporter, progressMonitor.subTask(1));
        // apply the layout results to the original graph
        graphImporter.applyLayout();

        progressMonitor.done();
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

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getDefault(final String optionId) {
        if (LayoutOptions.OBJ_SPACING_ID.equals(optionId)) {
            return Properties.DEF_SPACING;
        } else if (LayoutOptions.BORDER_SPACING_ID.equals(optionId)) {
            return Properties.DEF_SPACING;
        }
        return null;
    }

}
