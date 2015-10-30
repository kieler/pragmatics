/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2010 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.layered.properties;

import java.util.EnumSet;

import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.klay.layered.intermediate.NodePromotionStrategy;
import de.cau.cs.kieler.klay.layered.intermediate.compaction.GraphCompactionStrategy;
import de.cau.cs.kieler.klay.layered.p1cycles.CycleBreakingStrategy;
import de.cau.cs.kieler.klay.layered.p2layers.LayeringStrategy;
import de.cau.cs.kieler.klay.layered.p3order.CrossingMinimizationStrategy;
import de.cau.cs.kieler.klay.layered.p4nodes.NodePlacementStrategy;
import de.cau.cs.kieler.klay.layered.p4nodes.bk.CompactionStrategy;

/**
 * Container for public property definitions. These are layout options that can be set on graph
 * elements before the algorithm is invoked.
 *
 * @author msp
 * @author cds
 * @author uru
 * @kieler.design proposed by msp
 * @kieler.rating proposed yellow by msp
 */
public final class Properties {

    /**
     * Property to enable or disable node-promotion.
     */
    public static final IProperty<NodePromotionStrategy> NODE_PROMOTION =
            new Property<NodePromotionStrategy>("de.cau.cs.kieler.klay.layered.nodePromotion",
                    NodePromotionStrategy.NONE);

    /**
     * Boundary for limiting the number of iterations of the node promotion algorithm. Delimited by
     * a percentage of the number of nodes of the graph or a percentage of how may dummy nodes may
     * be reduced in the best case (1% to 100%, 0 sets no boundary).
     */
    public static final IProperty<Integer> NODE_PROMOTION_BOUNDARY = new Property<Integer>(
            "de.cau.cs.kieler.klay.layered.nodePromotionBoundary", 0, 0, 100);
    
    /**
     * Property to switch one dimensional compaction post-processing on or off.
     */
    public static final IProperty<Boolean> ONE_DIMENSIONAL_COMPACTION = new Property<Boolean>(
            "de.cau.cs.kieler.klay.layered.oneDimensionalCompaction", false);

    /**
     * A pre-defined seed for pseudo-random number generators. We redefine the property here to set
     * its default value to 1.
     * 
     * A pre-defined seed for pseudo-random number generators.
     * We redefine the property here to set its default value to 1.
     *
     * @see de.cau.cs.kieler.kiml.options.LayoutOptions#RANDOM_SEED
     */
    public static final IProperty<Integer> RANDOM_SEED = new Property<Integer>(
            "de.cau.cs.kieler.randomSeed", 1);

    /**
     * The factor by which the in-layer spacing between objects differs from the inter-layer
     * {@link InternalProperties#SPACING}.
     */
    public static final IProperty<Float> OBJ_SPACING_IN_LAYER_FACTOR = new Property<Float>(
            "de.cau.cs.kieler.klay.layered.inLayerSpacingFactor", 1.0f, 0f);

    /**
     * Factor for minimal spacing between edges.
     */
    public static final Property<Float> EDGE_SPACING_FACTOR = new Property<Float>(
            "de.cau.cs.kieler.klay.layered.edgeSpacingFactor", 0.5f);

    /**
     * Factor for minimal spacing between an edge and a node that is close by.
     */
    public static final Property<Float> EDGE_NODE_SPACING_FACTOR = new Property<Float>(
            "de.cau.cs.kieler.klay.layered.edgeNodeSpacingFactor", 0.7f);

    /**
     * Whether nodes shall be distributed during layer assignment.
     *
     * @deprecated use the {@link #WIDE_NODES_ON_MULTIPLE_LAYERS} property instead.
     */
    @Deprecated
    public static final IProperty<Boolean> DISTRIBUTE_NODES = new Property<Boolean>(
            "de.cau.cs.kieler.klay.layered.distributeNodes", false);

    /**
     * Whether wide nodes may be be distributed over several layers.
     */
    public static final IProperty<WideNodesStrategy> WIDE_NODES_ON_MULTIPLE_LAYERS =
            new Property<WideNodesStrategy>(
                    "de.cau.cs.kieler.klay.layered.wideNodesOnMultipleLayers",
                    WideNodesStrategy.OFF);
    /**
     * Property to choose a cycle breaking strategy.
     */
    public static final IProperty<CycleBreakingStrategy> CYCLE_BREAKING =
            new Property<CycleBreakingStrategy>("de.cau.cs.kieler.klay.layered.cycleBreaking",
                    CycleBreakingStrategy.GREEDY);

    /**
     * Property to choose a node layering strategy.
     */
    public static final IProperty<LayeringStrategy> NODE_LAYERING = new Property<LayeringStrategy>(
            "de.cau.cs.kieler.klay.layered.nodeLayering", LayeringStrategy.NETWORK_SIMPLEX);

    /**
     * Property to choose a crossing minimization strategy.
     */
    public static final IProperty<CrossingMinimizationStrategy> CROSS_MIN =
            new Property<CrossingMinimizationStrategy>("de.cau.cs.kieler.klay.layered.crossMin",
                    CrossingMinimizationStrategy.LAYER_SWEEP);


    /**
     * Property to choose a greedy Crossing Minimization Strategy.
     */
    public static final IProperty<GreedySwitchType> GREEDY_SWITCH_TYPE =
            new Property<GreedySwitchType>("de.cau.cs.kieler.klay.layered.greedySwitch",
                    GreedySwitchType.TWO_SIDED);


    /**
     * Property to choose a node placement strategy.
     */
    public static final IProperty<NodePlacementStrategy> NODE_PLACER =
            new Property<NodePlacementStrategy>("de.cau.cs.kieler.klay.layered.nodePlace",
                    NodePlacementStrategy.BRANDES_KOEPF);

    /**
     * Dampening of deflections between linear segments in the linear segments node placer.
     */
    public static final IProperty<Float> LINEAR_SEGMENTS_DEFLECTION_DAMPENING =
            new Property<Float>("de.cau.cs.kieler.klay.layered.linearSegmentsDeflectionDampening",
                    0.3f, 0.0f, 1.0f);

    /**
     * Tells the BK node placer to use a certain alignment instead of taking the optimal result.
     */
    public static final IProperty<FixedAlignment> FIXED_ALIGNMENT = new Property<FixedAlignment>(
            "de.cau.cs.kieler.klay.layered.fixedAlignment", FixedAlignment.NONE);

    /**
     * Property to choose an edge label placement strategy.
     */
    public static final IProperty<EdgeLabelSideSelection> EDGE_LABEL_SIDE_SELECTION =
            new Property<EdgeLabelSideSelection>(
                    "de.cau.cs.kieler.klay.layered.edgeLabelSideSelection",
                    EdgeLabelSideSelection.ALWAYS_DOWN);

    /**
     * Property to switch debug mode on or off.
     */
    public static final IProperty<Boolean> DEBUG_MODE = new Property<Boolean>(
            "de.cau.cs.kieler.debugMode", false);

    /**
     * Property that determines how much effort should be spent.
     */
    public static final IProperty<Integer> THOROUGHNESS = new Property<Integer>(
            "de.cau.cs.kieler.klay.layered.thoroughness", 10, 1);

    /**
     * Property to set constraints on the node layering.
     */
    public static final IProperty<LayerConstraint> LAYER_CONSTRAINT =
            new Property<LayerConstraint>("de.cau.cs.kieler.klay.layered.layerConstraint",
                    LayerConstraint.NONE);

    /**
     * Property to enable or disable port merging. Merging ports is only interesting for edges
     * directly connected to nodes instead of ports. When this option is disabled, one port is
     * created for each edge directly connected to a node. When it is enabled, all such incoming
     * edges share an input port, and all outgoing edges share an output port.
     */
    public static final IProperty<Boolean> MERGE_EDGES = new Property<Boolean>(
            "de.cau.cs.kieler.klay.layered.mergeEdges", false);

    /**
     * Property to enable or disable hierarchical port merging. Merging hierarchical ports is only
     * interesting for hierarchy-crossing edges. Those are broken by the algorithm, with
     * hierarchical ports inserted as required. Usually, one such port is created for each edge at
     * each hierarchy crossing point. With this option set to {@code true}, we try to create as few
     * hierarchical ports as possible in the process. In particular, all edges that form a hyperedge
     * can share a port.
     */
    public static final IProperty<Boolean> MERGE_HIERARCHICAL_EDGES = new Property<Boolean>(
            "de.cau.cs.kieler.klay.layered.mergeHierarchyEdges", true);

    /**
     * Property that determines which point in a node determines the result of interactive phases.
     */
    public static final IProperty<InteractiveReferencePoint> INTERACTIVE_REFERENCE_POINT =
            new Property<InteractiveReferencePoint>(
                    "de.cau.cs.kieler.klay.layered.interactiveReferencePoint",
                    InteractiveReferencePoint.CENTER);

    /**
     * Whether feedback edges should be highlighted by routing around the nodes.
     */
    public static final IProperty<Boolean> FEEDBACK_EDGES = new Property<Boolean>(
            "de.cau.cs.kieler.klay.layered.feedBackEdges", false);

    /**
     * If true, each long edge dummy will contribute a bend point to its edges and
     * hierarchy-crossing edges will always get a bend point where they cross hierarchy boundaries.
     * By default, bend points are only added where an edge changes direction.
     */
    public static final IProperty<Boolean> ADD_UNNECESSARY_BENDPOINTS = new Property<Boolean>(
            "de.cau.cs.kieler.klay.layered.unnecessaryBendpoints", false);

    /**
     * Specifies how the content of compound nodes is to be aligned, e.g. top-left or center-center.
     */
    public static final IProperty<EnumSet<ContentAlignment>> CONTENT_ALIGNMENT =
            new Property<EnumSet<ContentAlignment>>(
                    "de.cau.cs.kieler.klay.layered.contentAlignment", ContentAlignment.topLeft());

    /**
     * Handles large sausages.
     */
    public static final IProperty<Boolean> SAUSAGE_FOLDING = new Property<Boolean>(
            "de.cau.cs.kieler.klay.layered.sausageFolding", false);

    /**
     * The spline-self-loop distribution method.
     */
    public static final IProperty<SelfLoopPlacement> SPLINE_SELF_LOOP_PLACEMENT =
            new Property<SelfLoopPlacement>(
                    "de.cau.cs.kieler.klay.layered.splines.selfLoopPlacement",
                    SelfLoopPlacement.NORTH_STACKED);

    /**
     * Defines a loose upper bound on the width of the MinWidth layerer.
     */
    public static final IProperty<Integer> UPPER_BOUND_ON_WIDTH = new Property<Integer>(
            "de.cau.cs.kieler.klay.layered.minWidthUpperBoundOnWidth", -1, 1);
    /**
     * Multiplied with Upper Bound On Width for defining an upper bound on the width of layers which
     * haven't been determined yet, but whose maximum width had been (roughly) estimated by the
     * MinWidth algorithm. Compensates for too high estimations.
     */
    public static final IProperty<Integer> UPPER_LAYER_ESTIMATION_SCALING_FACTOR =
            new Property<Integer>(
                    "de.cau.cs.kieler.klay.layered.minWidthUpperLayerEstimationScalingFactor", -1, 1);

    /**
     * Defines the added Scale for the upper layer Weight (wdithUp) in the condition that decides if
     * a new layer should be used or not it Ranges from 0 to 1, with 0 being very similar to a
     * one-node-layerer and 1 being the longest-path-layerer.
     */
    public static final IProperty<Float> UPPER_LAYER_SCALE = new Property<Float>(
            "de.cau.cs.kieler.klay.layered.stretchWidthUpperLayerScale",
            0.5f, 0.0f, 1.0f);

    /**
     * Specifies the compaction strategy when using the
     * {@link de.cau.cs.kieler.klay.layered.p4nodes.bk.BKNodePlacer BKNodePlacer}.
     */
    public static final IProperty<CompactionStrategy> COMPACTION_STRATEGY =
            new Property<CompactionStrategy>(
                    "de.cau.cs.kieler.klay.layered.nodeplace.compactionStrategy",
                    CompactionStrategy.CLASSIC);

    /**
     * Property set per port that specifies if ports on north/south side of a node may switch sides.
     */
    public static final IProperty<Boolean> NORTH_OR_SOUTH_PORT = new Property<Boolean>(
            "de.cau.cs.kieler.klay.layered.northOrSouthPort", false);
    
    /**
     * Property to choose a one dimensional compaction strategy.
     */
    public static final IProperty<GraphCompactionStrategy> POST_COMPACTION =
            new Property<GraphCompactionStrategy>(
                    "de.cau.cs.kieler.klay.layered.postCompaction",
                    GraphCompactionStrategy.NONE);

    // /////////////////////////////////////////////////////////////////////////////
    // CONSTRUCTOR

    /**
     * Hidden default constructor.
     */
    private Properties() {
    }

}
