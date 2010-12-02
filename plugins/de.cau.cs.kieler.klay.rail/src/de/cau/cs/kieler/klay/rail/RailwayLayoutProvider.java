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
package de.cau.cs.kieler.klay.rail;

import java.util.List;

import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.core.alg.BasicProgressMonitor;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.AbstractLayoutProvider;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.klay.layered.IGraphImporter;
import de.cau.cs.kieler.klay.layered.KGraphImporter;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;
import de.cau.cs.kieler.klay.layered.impl.GreedyCycleBreaker;
import de.cau.cs.kieler.klay.layered.impl.LayerSweepCrossingMinimizer;
import de.cau.cs.kieler.klay.layered.impl.LinearSegmentsNodePlacer;
import de.cau.cs.kieler.klay.layered.impl.NetworkSimplexLayerer;
import de.cau.cs.kieler.klay.layered.impl.SimpleSplineEdgeRouter;
import de.cau.cs.kieler.klay.layered.modules.ICrossingMinimizer;
import de.cau.cs.kieler.klay.layered.modules.ICycleBreaker;
import de.cau.cs.kieler.klay.layered.modules.IEdgeRouter;
import de.cau.cs.kieler.klay.layered.modules.ILayerer;
import de.cau.cs.kieler.klay.layered.modules.INodePlacer;

/**
 *
 * Provider class for railway layout.
 *
 * @author jjc
 *
 */
public class RailwayLayoutProvider extends AbstractLayoutProvider {

    // Strategies for the 5 phases of layered layout, assuming this
    // will use layered layout
    // This is still open, though

    /** phase 1: cycle breaking module. */
    private ICycleBreaker cycleBreaker = new GreedyCycleBreaker();
    /** phase 2: layering module. */
    private ILayerer layerer = new NetworkSimplexLayerer();
    /** phase 3: crossing minimization module. */
    private ICrossingMinimizer crossingMinimizer = new LayerSweepCrossingMinimizer();
    /** phase 4: node placement module. */
    private INodePlacer nodePlacer = new LinearSegmentsNodePlacer();
    /** phase 5: Edge routing module. */
    private IEdgeRouter edgeRouter = new SimpleSplineEdgeRouter();
    
    /**
     * Initialize default values for options.
     */
    public RailwayLayoutProvider() {
        setProperty(LayoutOptions.BORDER_SPACING, Properties.DEF_BORDER);
        setProperty(LayoutOptions.OBJ_SPACING, Properties.DEF_SPACING);
    }

    @Override
    public void doLayout(final KNode layoutNode,
            final IKielerProgressMonitor progressMonitor)
            throws KielerException {
        progressMonitor.begin("Railway layout", 1);
        KShapeLayout parentLayout = layoutNode.getData(KShapeLayout.class);

        // transform the input graph
        IGraphImporter graphImporter = new KGraphImporter(layoutNode);
        LayeredGraph layeredGraph = graphImporter.getGraph();
        
        setOptions(layeredGraph, layoutNode, parentLayout);
        
        // perform the actual layout
        layout(graphImporter, progressMonitor.subTask(1));
        // apply the layout results to the original graph
        graphImporter.applyLayout();
        
        progressMonitor.done();
    }

    /**
     * Set layout options for the layered graph.
     * 
     * @param layeredGraph a new layered graph
     * @param parent the original parent node
     * @param parentLayout the layout data for the parent node
     */
    private void setOptions(final LayeredGraph layeredGraph, final KNode parent,
            final KShapeLayout parentLayout) {
        // set object spacing option
        float objSpacing = parentLayout.getProperty(LayoutOptions.OBJ_SPACING);
        if (objSpacing >= 0) {
            layeredGraph.setProperty(Properties.OBJ_SPACING, objSpacing);
        }
        
        // set border spacing option
        float borSpacing = parentLayout.getProperty(LayoutOptions.BORDER_SPACING);
        if (borSpacing >= 0) {
            layeredGraph.setProperty(Properties.BOR_SPACING, borSpacing);
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
