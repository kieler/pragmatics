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
import de.cau.cs.kieler.kiml.ui.util.DebugCanvas;
import de.cau.cs.kieler.kiml.ui.util.DebugCanvas.DrawingMode;
import de.cau.cs.kieler.kiml.util.KimlLayoutUtil;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;
import de.cau.cs.kieler.klay.layered.impl.GreedyCycleBreaker;
import de.cau.cs.kieler.klay.layered.impl.LayerSweepCrossingMinimizer;
import de.cau.cs.kieler.klay.layered.impl.LinearSegmentsNodePlacer;
import de.cau.cs.kieler.klay.layered.impl.LongestPathLayerer;
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

    /** Option to choose edge router. */
    public static final String OPT_EDGE_ROUTING = "de.cau.cs.kieler.klay.layered.edgeRouting";
    /** option to choose if debug info is shown. */
    public static final String OPT_DEBUG_INFO = "de.cau.cs.kieler.klay.layered.debugInfo";
    /** option defines the minimal angle a short edge may have. */
    public static final String OPT_MINIMAL_EDGE_ANGLE = "de.cau.cs.kieler.klay.layered.minimalAngle";
    /** option to choose if edges with priority > 0 are drawn straight. */
    public static final String OPT_STRAIGHT_EDGES = "de.cau.cs.kieler.klay.layered.straightEdges";
    /** option to choose if big nodes should be distributed. */
    public static final String OPT_DISTRIBUTE_NODES = "de.cau.cs.kieler.klay.layered.distributeNodes";

    /** phase 1: cycle breaking module. */
    private ICycleBreaker cycleBreaker = new GreedyCycleBreaker();
    /** phase 2: layering module. */
    private ILayerer layerer = new LongestPathLayerer();
    /** phase 3: crossing minimization module. */
    private ICrossingMinimizer crossingMinimizer = new LayerSweepCrossingMinimizer();
    /** phase 4: node placement module. */
    private INodePlacer nodePlacer = new LinearSegmentsNodePlacer();
    /** phase 5: Edge routing module. */
    private IEdgeRouter edgeRouter;

    /** the DebugCanvas to use for debug drawings. **/
    private DebugCanvas debugCanvas;
    
    /** constructor registering the new enum option. */
    public LayeredLayoutProvider() {
        LayoutOptions.registerEnum(OPT_EDGE_ROUTING, LayeredEdgeRouting.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doLayout(final KNode layoutNode, final IKielerProgressMonitor progressMonitor)
            throws KielerException {
        progressMonitor.begin("Layered layout", 1);
        KShapeLayout parentLayout = KimlLayoutUtil.getShapeLayout(layoutNode);

        // check which EdgeRouter to use
        LayeredEdgeRouting routing = LayoutOptions.getEnum(parentLayout, LayeredEdgeRouting.class);
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
        default:
            if (!(edgeRouter instanceof PolylineEdgeRouter)) {
                edgeRouter = new PolylineEdgeRouter();
            }
            break;
        }

        // debug mode
        Boolean debug = LayoutOptions.getBoolean(parentLayout, OPT_DEBUG_INFO);
        if (debug) {
            // get debug canvas and clear
            debugCanvas = new DebugCanvas(layoutNode, DrawingMode.IMMEDIATE);
            float borderspacing = LayoutOptions
                    .getFloat(parentLayout, LayoutOptions.BORDER_SPACING);
            if (Float.isNaN(borderspacing) || borderspacing < 0) {
                borderspacing = Properties.DEF_SPACING;
            }
            debugCanvas.setCustomXOffset(borderspacing);
            debugCanvas.setCustomYOffset(borderspacing);
            debugCanvas.clear();
        } else {
            if (debugCanvas != null) {
                debugCanvas.clear();
            }
            debugCanvas = null;
        }

        // transform the input graph
        IGraphImporter graphImporter = new KGraphImporter(layoutNode);
        LayeredGraph layeredGraph = graphImporter.getGraph();
        
        // set object spacing option
        float objSpacing = LayoutOptions.getFloat(parentLayout, LayoutOptions.MIN_SPACING);
        if (!Float.isNaN(objSpacing) && objSpacing > 0) {
            layeredGraph.setProperty(Properties.OBJ_SPACING, objSpacing);
        }
        // add information for LinearSegmentsNodePlacer
        Boolean straightEdges = LayoutOptions.getBoolean(parentLayout, OPT_STRAIGHT_EDGES);
        layeredGraph.setProperty(Properties.STRAIGHT_EDGES, straightEdges);
        // add information for LinearSegmentsNodePlacer
        Boolean distributeNodes = LayoutOptions.getBoolean(parentLayout, OPT_DISTRIBUTE_NODES);
        layeredGraph.setProperty(Properties.DISTRIBUTE_NODES, distributeNodes);
        // set minimal edge angle
        int edgeAngle = LayoutOptions.getInt(parentLayout, OPT_MINIMAL_EDGE_ANGLE);
        if (edgeAngle >= 0) {
            layeredGraph.setProperty(Properties.MINIMAL_EDGE_ANGLE, edgeAngle);
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
     * @param importer the graph importer
     * @param themonitor a progress monitor, or {@code null}
     */
    public void layout(final IGraphImporter importer,
            final IKielerProgressMonitor themonitor) {
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
        if (edgeRouter instanceof ComplexSplineEdgeRouter) {
            ((ComplexSplineEdgeRouter) edgeRouter).routeEdges(layeredGraph, debugCanvas);
        } else {
            edgeRouter.routeEdges(layeredGraph);
        }

        monitor.done();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getDefault(final String optionId) {
        if (LayoutOptions.MIN_SPACING.equals(optionId)) {
            return Properties.DEF_SPACING;
        } else if (LayoutOptions.BORDER_SPACING.equals(optionId)) {
            return Properties.DEF_SPACING;
        } else if (OPT_MINIMAL_EDGE_ANGLE.equals(optionId)) {
            return 0;
        } else if (OPT_EDGE_ROUTING.equals(optionId)) {
            return LayeredEdgeRouting.POLYLINE;
        } else {
            return super.getDefault(optionId);
        }
    }

}
