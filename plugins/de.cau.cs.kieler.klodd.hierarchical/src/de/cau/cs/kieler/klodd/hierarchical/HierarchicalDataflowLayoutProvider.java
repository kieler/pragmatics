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
import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.slimgraph.KSlimEdge;
import de.cau.cs.kieler.core.slimgraph.KSlimGraph;
import de.cau.cs.kieler.core.slimgraph.alg.DFSCycleRemover;
import de.cau.cs.kieler.core.slimgraph.alg.GreedyCycleRemover;
import de.cau.cs.kieler.core.slimgraph.alg.ICycleRemover;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KLayoutData;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.layout.options.LayoutDirection;
import de.cau.cs.kieler.kiml.layout.options.LayoutOptions;
import de.cau.cs.kieler.kiml.layout.options.PortConstraints;
import de.cau.cs.kieler.kiml.layout.services.AbstractLayoutProvider;
import de.cau.cs.kieler.kiml.layout.util.GraphConverter;
import de.cau.cs.kieler.kiml.layout.util.KimlLayoutUtil;
import de.cau.cs.kieler.klodd.hierarchical.impl.BalancingLayerAssigner;
import de.cau.cs.kieler.klodd.hierarchical.impl.BalancingNodePlacer;
import de.cau.cs.kieler.klodd.hierarchical.impl.BarycenterCrossingReducer;
import de.cau.cs.kieler.klodd.hierarchical.impl.BasicNodePlacer;
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

/**
 * Layout provider for the KLoDD hierarchical dataflow diagram layouter.
 * 
 * @author <a href="mailto:msp@informatik.uni-kiel.de">Miro Sp&ouml;nemann</a>
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

    /**
     * {@inheritDoc}
     */
    @Override
    public void doLayout(final KNode layoutNode, final IKielerProgressMonitor progressMonitor)
            throws KielerException {
        progressMonitor.begin("Hierarchical layout", 75);
        // get the currently configured modules
        updateModules();
        // set option for minimal distance
        float minDist = LayoutOptions.getMinSpacing(KimlLayoutUtil.getShapeLayout(layoutNode));
        if (Float.isNaN(minDist)) {
            minDist = DEF_MIN_DIST;
        }

        // perform some pre-processing
        preProcess(layoutNode);
        // create a slim graph for cycle removal
        graphConverter.reset(progressMonitor.subTask(5));
        KSlimGraph slimGraph = graphConverter.convertGraph(layoutNode, true);
        // remove cycles in the input graph
        cycleRemover.reset(progressMonitor.subTask(5));
        cycleRemover.removeCycles(slimGraph);
        // put each node into a layer and get a layered graph
        layerAssigner.reset(progressMonitor.subTask(10));
        LayeredGraph layeredGraph = layerAssigner.assignLayers(slimGraph, layoutNode, balanceOverSize);
        if (!layeredGraph.getLayers().isEmpty()) {
            layeredGraph.createConnections(slimGraph);
            // optimize the order of nodes in each layer
            crossingReducer.reset(progressMonitor.subTask(15));
            crossingReducer.reduceCrossings(layeredGraph);
            // determine a placement for all edge endpoints
            nodewiseEdgePlacer.reset(progressMonitor.subTask(10));
            nodewiseEdgePlacer.placeEdges(layeredGraph);
            // determine a crosswise placement for each node
            nodePlacer.reset(progressMonitor.subTask(10));
            nodePlacer.placeNodes(layeredGraph, minDist, balanceOverSize);
            // route edges between nodes
            edgeRouter.reset(progressMonitor.subTask(10));
            edgeRouter.routeEdges(layeredGraph, minDist);
        }
        layeredGraph.applyLayout();
        restoreCycles();

        progressMonitor.done();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getDefault(final String optionId) {
        if (LayoutOptions.FIXED_SIZE.equals(optionId)) {
            return false;
        } else if (LayoutOptions.LAYOUT_DIRECTION.equals(optionId)) {
            return LayoutDirection.HORIZONTAL;
        } else if (LayoutOptions.MIN_SPACING.equals(optionId)) {
            return DEF_MIN_DIST;
        } else if (LayoutOptions.PORT_CONSTRAINTS.equals(optionId)) {
            return PortConstraints.UNDEFINED;
        } else {
            return null;
        }
    }

    /**
     * Sets the internally used algorithm modules to the current configuration.
     */
    private void updateModules() {
        // choose cycle remover module
        if (preferenceStore != null
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

        if (crossingReducer == null) {
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
        KLayoutData parentLayout = KimlLayoutUtil.getShapeLayout(parentNode);
        LayoutDirection layoutDirection = LayoutOptions.getLayoutDirection(parentLayout);

        for (KNode node : parentNode.getChildren()) {
            // fill port data for the child node
            KimlLayoutUtil.fillPortInfo(node, layoutDirection);

            // set node size if not fixed
            if (node.getChildren().isEmpty()
                    && !LayoutOptions.isFixedSize(KimlLayoutUtil.getShapeLayout(node))) {
                KimlLayoutUtil.resizeNode(node);
            }
        }

        // fill port data for the parent node
        KimlLayoutUtil.fillPortInfo(parentNode, layoutDirection);
    }

    /**
     * Restores the edges that were reversed for cycle removal.
     */
    private void restoreCycles() {
        List<KSlimEdge> reversedEdges = cycleRemover.getReversedEdges();
        for (KSlimEdge slimEdge : reversedEdges) {
            KEdge layoutEdge = (KEdge) slimEdge.object;
            KEdgeLayout edgeLayout = KimlLayoutUtil.getEdgeLayout(layoutEdge);
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
