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
package de.cau.cs.kieler.klay.layered.properties;

import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.klay.layered.p1cycles.CycleBreakingStrategy;
import de.cau.cs.kieler.klay.layered.p2layers.LayeringStrategy;
import de.cau.cs.kieler.klay.layered.p2layers.MinizincMode;
import de.cau.cs.kieler.klay.layered.p3order.CrossingMinimizationStrategy;
import de.cau.cs.kieler.klay.layered.p4nodes.NodePlacementStrategy;

/**
 * Container for public property definitions. These are layout options that can be set on graph
 * elements before the algorithm is invoked.
 * 
 * @author msp
 * @author cds
 * @kieler.design proposed by msp
 * @kieler.rating proposed yellow by msp
 */
public final class Properties {

    /**
     * Minimal spacing between objects.
     */
    public static final Property<Float> OBJ_SPACING = new Property<Float>(LayoutOptions.SPACING,
            20.0f, 1.0f);
    
    /**
     * The factor by which the in-layer spacing between objects differs from the inter-layer
     * {@link Properties#OBJ_SPACING}.
     */
    public static final IProperty<Float> OBJ_SPACING_IN_LAYER_FACTOR = new Property<Float>(
            "de.cau.cs.kieler.klay.layered.inLayerSpacingFactor", 1.0f, 0f);
    
    /**
     * Spacing to the border of the drawing.
     */
    public static final Property<Float> BORDER_SPACING = new Property<Float>(
            LayoutOptions.BORDER_SPACING, 12.0f, 0.0f);

    /**
     * Factor for minimal spacing between edges.
     */
    public static final Property<Float> EDGE_SPACING_FACTOR = new Property<Float>(
            "de.cau.cs.kieler.klay.layered.edgeSpacingFactor", 0.5f);

    /**
     * Priority of elements. controls how much single edges are emphasized.
     */
    public static final Property<Integer> PRIORITY = new Property<Integer>(LayoutOptions.PRIORITY, 0);

    /**
     * The aspect ratio for packing connected components.
     */
    public static final Property<Float> ASPECT_RATIO = new Property<Float>(
            LayoutOptions.ASPECT_RATIO, 1.6f, 0.0f);

    /**
     * Whether nodes shall be distributed during layer assignment.
     */
    public static final IProperty<Boolean> DISTRIBUTE_NODES = new Property<Boolean>(
            "de.cau.cs.kieler.klay.layered.distributeNodes", false);

    /**
     * Property to choose a cycle breaking strategy.
     */
    public static final IProperty<CycleBreakingStrategy> CYCLE_BREAKING 
        = new Property<CycleBreakingStrategy>(
            "de.cau.cs.kieler.klay.layered.cycleBreaking", CycleBreakingStrategy.GREEDY);

    /**
     * Property to choose a node layering strategy.
     */
    public static final IProperty<LayeringStrategy> NODE_LAYERING = new Property<LayeringStrategy>(
            "de.cau.cs.kieler.klay.layered.nodeLayering", LayeringStrategy.NETWORK_SIMPLEX);

    /**
     * Property to choose a crossing minimization strategy.
     */
    public static final IProperty<CrossingMinimizationStrategy> CROSS_MIN 
        = new Property<CrossingMinimizationStrategy>(
            "de.cau.cs.kieler.klay.layered.crossMin", CrossingMinimizationStrategy.LAYER_SWEEP);

    /**
     * Property to choose a node placement strategy.
     */
    public static final IProperty<NodePlacementStrategy> NODE_PLACER
            = new Property<NodePlacementStrategy>("de.cau.cs.kieler.klay.layered.nodePlace",
                    NodePlacementStrategy.BRANDES_KOEPF);
    
    /**
     * Dampening of deflections between linear segments in the linear segments node placer.
     */
    public static final IProperty<Float> LINEAR_SEGMENTS_DEFLECTION_DAMPENING = new Property<Float>(
            "de.cau.cs.kieler.klay.layered.linearSegmentsDeflectionDampening", 0.3f, 0.0f, 1.0f);
    
    /**
     * Tells the BK node placer to use a certain alignment instead of taking the optimal result.
     */
    public static final IProperty<FixedAlignment> FIXED_ALIGNMENT = new Property<FixedAlignment>(
            "de.cau.cs.kieler.klay.layered.fixedAlignment", FixedAlignment.NONE);
    
    /**
     * Property to choose an edge label placement strategy.
     */
    public static final IProperty<EdgeLabelSideSelection> EDGE_LABEL_SIDE_SELECTION =
            new Property<EdgeLabelSideSelection>("de.cau.cs.kieler.klay.layered.edgeLabelSideSelection",
                                                         EdgeLabelSideSelection.SMART);

    /**
     * Property to switch debug mode on or off.
     */
    public static final IProperty<Boolean> DEBUG_MODE = new Property<Boolean>(
            "de.cau.cs.kieler.debugMode", false);

    /**
     * Property that determines how much effort should be spent.
     */
    public static final IProperty<Integer> THOROUGHNESS = new Property<Integer>(
            "de.cau.cs.kieler.klay.layered.thoroughness", 7, 1);

    /**
     * Property to set constraints on the node layering.
     */
    public static final IProperty<LayerConstraint> LAYER_CONSTRAINT = new Property<LayerConstraint>(
            "de.cau.cs.kieler.klay.layered.layerConstraint", LayerConstraint.NONE);

    /**
     * Property to enable or disable port merging. Merging ports is only interesting for edges
     * directly connected to nodes instead of ports. When this option is disabled, one port is
     * created for each edge directly connected to a node. When it is enabled, all such incoming
     * edges share an input port, and all outgoing edges share an output port.
     */
    public static final IProperty<Boolean> MERGE_PORTS = new Property<Boolean>(
            "de.cau.cs.kieler.klay.layered.mergePorts", false);

    /**
     * Property to enable or disable hierarchical port merging. Merging hierarchical ports is only
     * interesting for hierarchy-crossing edges. Those are broken by the algorithm, with hierarchical
     * ports inserted as required. Usually, one such port is created for each edge at each hierarchy
     * crossing point. With this option set to {@code true}, we try to create as few hierarchical ports
     * as possible in the process. In particular, all edges that form a hyperedge can share a port.
     */
    public static final IProperty<Boolean> MERGE_HIERARCHICAL_PORTS = new Property<Boolean>(
            "de.cau.cs.kieler.klay.layered.mergeHierarchyPorts", true);

    /**
     * Property that determines which point in a node determines the result of interactive phases.
     */
    public static final IProperty<InteractiveReferencePoint> INTERACTIVE_REFERENCE_POINT 
        = new Property<InteractiveReferencePoint>(
            "de.cau.cs.kieler.klay.layered.interactiveReferencePoint",
            InteractiveReferencePoint.CENTER);
    
    /**
     * Whether feedback edges should be highlighted by routing around the nodes.
     */
    public static final IProperty<Boolean> FEEDBACK_EDGES = new Property<Boolean>(
            "de.cau.cs.kieler.klay.layered.feedBackEdges", false);
    
    /**
     * The weight for edge lengths in the MiniZinc layerer.
     */
    public static final IProperty<Float> EDGE_LENGTH_WEIGHT = new Property<Float>(
            "de.cau.cs.kieler.klay.layered.edgeLengthWeight", 0.8f);

    /**
     * The weight for edge reversals in the MiniZinc layerer.
     */
    public static final IProperty<Float> EDGE_REVERSAL_WEIGHT = new Property<Float>(
            "de.cau.cs.kieler.klay.layered.edgeReversalWeight", 1.0f);

    /**
     * A factor by which the {@link #EDGE_REVERSAL_WEIGHT} is multiplied for 
     * edges that are part of strongly connected components.
     * Thus, a value lower than 1.0 means these edges are penalized less. 
     */
    public static final IProperty<Float> EDGE_REVERSAL_WEIGHT_FACTOR = new Property<Float>(
            "de.cau.cs.kieler.klay.layered.edgeReversalWeightFactor", 0.5f);

    /**
     * The maximal number of layers allowed.
     */
    public static final IProperty<Integer> MAXIMAL_LAYERS = new Property<Integer>(
            "de.cau.cs.kieler.klay.layered.maximalNoOfLayers", 0);
    
    /**
     * The mode in which the minizinc layering should be executed.
     */
    public static final IProperty<MinizincMode> MINIZINC_MODE = new Property<MinizincMode>(
            "de.cau.cs.kieler.klay.layered.minizincMode", MinizincMode.STATIC);

    // /////////////////////////////////////////////////////////////////////////////
    // CONSTRUCTOR

    /**
     * Hidden default constructor.
     */
    private Properties() {
    }
}
