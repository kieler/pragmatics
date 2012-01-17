/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2008 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klodd.hierarchical;

import java.util.LinkedList;
import java.util.List;

import de.cau.cs.kieler.core.IKielerPreferenceStore;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KGraphData;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.kiml.AbstractLayoutProvider;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.Direction;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.util.KimlUtil;
import de.cau.cs.kieler.klodd.hierarchical.impl.BalancingLayerAssigner;
import de.cau.cs.kieler.klodd.hierarchical.impl.BalancingNodePlacer;
import de.cau.cs.kieler.klodd.hierarchical.impl.BarycenterCrossingReducer;
import de.cau.cs.kieler.klodd.hierarchical.impl.BasicNodePlacer;
import de.cau.cs.kieler.klodd.hierarchical.impl.InteractiveCrossingReducer;
import de.cau.cs.kieler.klodd.hierarchical.impl.LayerSweepCrossingReducer;
import de.cau.cs.kieler.klodd.hierarchical.impl.LongestPathLayerAssigner;
import de.cau.cs.kieler.klodd.hierarchical.impl.RectilinearEdgeRouter;
import de.cau.cs.kieler.klodd.hierarchical.impl.SortingLayerwiseEdgePlacer;
import de.cau.cs.kieler.klodd.hierarchical.impl.SortingNodewiseEdgePlacer;
import de.cau.cs.kieler.klodd.hierarchical.impl.ToponumLayerwiseEdgePlacer;
import de.cau.cs.kieler.klodd.hierarchical.modules.ICrossingReducer;
import de.cau.cs.kieler.klodd.hierarchical.modules.IEdgeRouter;
import de.cau.cs.kieler.klodd.hierarchical.modules.ILayerAssigner;
import de.cau.cs.kieler.klodd.hierarchical.modules.ILayerwiseEdgePlacer;
import de.cau.cs.kieler.klodd.hierarchical.modules.INodePlacer;
import de.cau.cs.kieler.klodd.hierarchical.modules.INodewiseEdgePlacer;
import de.cau.cs.kieler.klodd.hierarchical.structures.LayeredGraph;
import de.cau.cs.kieler.klodd.hierarchical.structures.slimgraph.KSlimEdge;
import de.cau.cs.kieler.klodd.hierarchical.structures.slimgraph.KSlimGraph;
import de.cau.cs.kieler.klodd.hierarchical.structures.slimgraph.alg.DFSCycleRemover;
import de.cau.cs.kieler.klodd.hierarchical.structures.slimgraph.alg.GreedyCycleRemover;
import de.cau.cs.kieler.klodd.hierarchical.structures.slimgraph.alg.ICycleRemover;
import de.cau.cs.kieler.klodd.hierarchical.structures.slimgraph.alg.InteractiveCycleRemover;

/**
 * Layout provider for the KLoDD hierarchical dataflow diagram layouter.
 * 
 * @kieler.rating 2009-12-11 proposed yellow msp
 * @author msp
 */
public class HierarchicalDataflowLayoutProvider extends AbstractLayoutProvider {

    /** default value for minimal distance. */
    public static final float DEF_MIN_DIST = 15.0f;
    /** preference identifier for cycle remover module. */
    public static final String PREF_CYCLE_REM = "klodd.hierarchical.cycleRem";
    /** value for DFS cycle remover module. */
    public static final String VAL_DFS_CYCLE_REM = "dfs";
    /** value for greedy cycle remover module. */
    public static final String VAL_GREEDY_CYCLE_REM = "greedy";
    /** preference identifier for layer assignment module. */
    public static final String PREF_LAYER_ASS = "klodd.hierarchical.layerAss";
    /** value for longest path layer assignment module. */
    public static final String VAL_LONGP_LAYER_ASS = "longp";
    /** value for balancing layer assignment module. */
    public static final String VAL_BAL_LAYER_ASS = "bal";
    /** preference identifier for layerwise edge placement module. */
    public static final String PREF_LAYER_EDGEROUTER = "klodd.hierarchical.layerEdge";
    /** value for sorting layerwise edge placer module. */
    public static final String VAL_SORT_LAYER_EDGEROUTER = "sort";
    /** value for topological numbering layerwise edge placer module. */
    public static final String VAL_TOPO_LAYER_EDGEROUTER = "topo";
    /** preference identifier for the number of passes for crossing reduction. */
    public static final String PREF_CROSSRED_PASSES = "klodd.hierarchical.crossRedPasses";
    /** default value for the number of passes for crossing reduction. */
    public static final int DEF_CROSSRED_PASSES = 2;
    /** preference identifier for the priority of node balancing over diagram size. */
    public static final String PREF_BALANCE_VS_SIZE = "klodd.hierarchical.balance";
    /** layout option identifier: level of interaction. */
    public static final String INTERACTIVE_ID = "de.cau.cs.kieler.klodd.interactive";
    /** level of interaction property. */
    public static final IProperty<InteractionLevel> INTERACTIVE = new Property<InteractionLevel>(
            INTERACTIVE_ID, InteractionLevel.NONE);

    /** the preference store for this layouter. */
    private static IKielerPreferenceStore preferenceStore;

    /**
     * Sets the preference store.
     * 
     * @param thepreferenceStore the preference store to set
     */
    public static void setPreferenceStore(final IKielerPreferenceStore thepreferenceStore) {
        HierarchicalDataflowLayoutProvider.preferenceStore = thepreferenceStore;
    }

    /** the graph converter module. */
    private GraphConverter graphConverter = new GraphConverter();
    /** the cycle remover module. */
    private ICycleRemover cycleRemover = null;
    /** the layer assigner module. */
    private ILayerAssigner layerAssigner = null;
    /** the crossing reducer module. */
    private ICrossingReducer crossingReducer = null;
    /** the nodewise edge placer module. */
    private INodewiseEdgePlacer nodewiseEdgePlacer = null;
    /** the node placer module. */
    private INodePlacer nodePlacer = null;
    /** the layerwise edge placer module. */
    private ILayerwiseEdgePlacer layerwiseEdgePlacer = null;
    /** the edge router module. */
    private IEdgeRouter edgeRouter = null;

    /** indicates whether node balance has priority over diagram size. */
    private boolean balanceOverSize;

    /** amount of work for a small task. */
    private static final int SMALL_TASK = 5;
    /** amount of work for a large task. */
    private static final int LARGE_TASK = 15;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void doLayout(final KNode layoutNode, final IKielerProgressMonitor progressMonitor) {
        progressMonitor.begin("Hierarchical layout",
                SMALL_TASK + SMALL_TASK + SMALL_TASK + LARGE_TASK + LARGE_TASK
                + LARGE_TASK + LARGE_TASK);
        // get the currently configured modules
        KShapeLayout parentLayout = layoutNode.getData(KShapeLayout.class);
        updateModules(parentLayout);
        // set option for minimal object spacing
        float objSpacing = parentLayout.getProperty(LayoutOptions.SPACING);
        if (objSpacing < 0) {
            objSpacing = DEF_MIN_DIST;
        }
        // set option for border spacing
        float borderSpacing = parentLayout.getProperty(LayoutOptions.BORDER_SPACING);
        if (borderSpacing < 0) {
            borderSpacing = DEF_MIN_DIST;
        }

        // perform some pre-processing
        preProcess(layoutNode);
        // create a slim graph for cycle removal
        graphConverter.reset(progressMonitor.subTask(SMALL_TASK));
        KSlimGraph slimGraph = graphConverter.convertGraph(layoutNode, true);
        // remove cycles in the input graph
        cycleRemover.reset(progressMonitor.subTask(SMALL_TASK));
        cycleRemover.removeCycles(slimGraph);
        // put each node into a layer and get a layered graph
        layerAssigner.reset(progressMonitor.subTask(LARGE_TASK));
        LayeredGraph layeredGraph = layerAssigner.assignLayers(slimGraph, layoutNode,
                objSpacing, balanceOverSize);
        if (!layeredGraph.getLayers().isEmpty()) {
            layeredGraph.createConnections(slimGraph);
            // optimize the order of nodes in each layer
            crossingReducer.reset(progressMonitor.subTask(LARGE_TASK));
            crossingReducer.reduceCrossings(layeredGraph);
            // determine a placement for all edge endpoints
            nodewiseEdgePlacer.reset(progressMonitor.subTask(LARGE_TASK));
            nodewiseEdgePlacer.placeEdges(layeredGraph);
            // determine a crosswise placement for each node
            nodePlacer.reset(progressMonitor.subTask(LARGE_TASK));
            nodePlacer.placeNodes(layeredGraph, objSpacing, borderSpacing, balanceOverSize);
            // route edges between nodes
            edgeRouter.reset(progressMonitor.subTask(LARGE_TASK));
            edgeRouter.routeEdges(layeredGraph, objSpacing, borderSpacing);
        }
        layeredGraph.applyLayout();
        restoreCycles();

        progressMonitor.done();
    }

    /**
     * Sets the internally used algorithm modules to the current configuration.
     * 
     * @param parentLayout layout data of the parent node
     */
    private void updateModules(final KShapeLayout parentLayout) {
        InteractionLevel interactionLevel = parentLayout.getProperty(INTERACTIVE);
        // choose cycle remover module
        if (interactionLevel == InteractionLevel.CYCLES
                || interactionLevel == InteractionLevel.LAYERS
                || interactionLevel == InteractionLevel.FULL) {
            if (!(cycleRemover instanceof InteractiveCycleRemover)) {
                cycleRemover = new InteractiveCycleRemover();
            }
            Direction layoutDirection = parentLayout.getProperty(LayoutOptions.DIRECTION);
            ((InteractiveCycleRemover) cycleRemover).setVertical(
                    layoutDirection == Direction.DOWN);
        } else if (preferenceStore != null
                && preferenceStore.getString(PREF_CYCLE_REM).equals(VAL_DFS_CYCLE_REM)) {
            if (!(cycleRemover instanceof DFSCycleRemover)) {
                cycleRemover = new DFSCycleRemover();
            }
        } else {
            if (!(cycleRemover instanceof GreedyCycleRemover)) {
                cycleRemover = new GreedyCycleRemover();
            }
        }

        // choose layer assignment module
        if (preferenceStore != null
                && preferenceStore.getString(PREF_LAYER_ASS).equals(VAL_LONGP_LAYER_ASS)) {
            if (!(layerAssigner instanceof LongestPathLayerAssigner)) {
                layerAssigner = new LongestPathLayerAssigner();
            }
        } else {
            if (!(layerAssigner instanceof BalancingLayerAssigner)) {
                layerAssigner = new BalancingLayerAssigner(new LongestPathLayerAssigner());
            }
        }

        if (interactionLevel == InteractionLevel.ORDERING
                || interactionLevel == InteractionLevel.FULL) {
            if (!(crossingReducer instanceof InteractiveCrossingReducer)) {
                crossingReducer = new InteractiveCrossingReducer();
            }
        } else {
            if (!(crossingReducer instanceof LayerSweepCrossingReducer)) {
                crossingReducer = new LayerSweepCrossingReducer(new BarycenterCrossingReducer());
            }
            int passes = DEF_CROSSRED_PASSES;
            if (preferenceStore != null) {
                int prefPasses = preferenceStore.getInt(PREF_CROSSRED_PASSES);
                if (prefPasses > 0) {
                    passes = prefPasses;
                }
            }
            ((LayerSweepCrossingReducer) crossingReducer).setPasses(passes);
        }

        if (nodewiseEdgePlacer == null) {
            nodewiseEdgePlacer = new SortingNodewiseEdgePlacer();
        }
        if (nodePlacer == null) {
            nodePlacer = new BalancingNodePlacer(new BasicNodePlacer());
        }

        // choose edge router module
        if (preferenceStore != null
                && preferenceStore.getString(PREF_LAYER_EDGEROUTER).equals(VAL_SORT_LAYER_EDGEROUTER)) {
            if (!(layerwiseEdgePlacer instanceof SortingLayerwiseEdgePlacer)) {
                layerwiseEdgePlacer = new SortingLayerwiseEdgePlacer();
                edgeRouter = new RectilinearEdgeRouter(layerwiseEdgePlacer);
            }
        } else {
            if (!(layerwiseEdgePlacer instanceof ToponumLayerwiseEdgePlacer)) {
                layerwiseEdgePlacer = new ToponumLayerwiseEdgePlacer();
                edgeRouter = new RectilinearEdgeRouter(layerwiseEdgePlacer);
            }
        }

        // set balance over size option
        if (preferenceStore != null) {
            balanceOverSize = preferenceStore.getBoolean(PREF_BALANCE_VS_SIZE);
        } else {
            balanceOverSize = true;
        }
    }

    /**
     * Performs some pre-processing for the elements of the given parent node.
     * 
     * @param parentNode parent layout node
     */
    private void preProcess(final KNode parentNode) {
        KGraphData parentLayout = parentNode.getData(KShapeLayout.class);
        Direction layoutDirection = parentLayout.getProperty(LayoutOptions.DIRECTION);

        for (KNode node : parentNode.getChildren()) {
            // fill port data for the child node
            KimlUtil.fillPortInfo(node, layoutDirection);

            // set node size if not fixed
            KimlUtil.resizeNode(node);
        }

        // fill port data for the parent node
        KimlUtil.fillPortInfo(parentNode, layoutDirection);
    }

    /**
     * Restores the edges that were reversed for cycle removal.
     */
    private void restoreCycles() {
        List<KSlimEdge> reversedEdges = cycleRemover.getReversedEdges();
        for (KSlimEdge slimEdge : reversedEdges) {
            KEdge layoutEdge = (KEdge) slimEdge.getObject();
            KEdgeLayout edgeLayout = layoutEdge.getData(KEdgeLayout.class);
            // reverse bend points
            List<KPoint> bendPoints = new LinkedList<KPoint>();
            for (KPoint point : edgeLayout.getBendPoints()) {
                bendPoints.add(0, point);
            }
            edgeLayout.getBendPoints().clear();
            for (KPoint point : bendPoints) {
                edgeLayout.getBendPoints().add(point);
            }
            // reverse source and target point
            KPoint sourcePoint = edgeLayout.getSourcePoint();
            edgeLayout.setSourcePoint(edgeLayout.getTargetPoint());
            edgeLayout.setTargetPoint(sourcePoint);
        }
    }

}
