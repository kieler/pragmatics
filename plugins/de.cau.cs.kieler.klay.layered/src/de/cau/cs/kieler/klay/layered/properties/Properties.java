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

import java.util.EnumSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.kiml.klayoutdata.KInsets;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.kiml.util.IDebugCanvas;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.p2layers.LayeringStrategy;
import de.cau.cs.kieler.klay.layered.p4nodes.LinearSegmentsNodePlacer.Region;
import de.cau.cs.kieler.klay.layered.p5edges.EdgeRoutingStrategy;

/**
 * Container for priority definitions.
 * 
 * @author msp
 * @author cds
 * @author ima
 */
public final class Properties {

    /** the original object from which a graph element was created. */
    public static final IProperty<Object> ORIGIN = new Property<Object>("origin");

    /** node type. */
    public static final IProperty<NodeType> NODE_TYPE = new Property<NodeType>("nodeType",
            NodeType.NORMAL);
    
   /** edge type.*/
    public static final IProperty<EdgeType> EDGE_TYPE = 
                                            new Property<EdgeType>("edgeType", EdgeType.NORMAL);

    /** offset for nodes in linear segments. */
    public static final IProperty<Integer> LINSEG_OFFSET = new Property<Integer>("linsegOffset", 0);

    /** owning region for node. */
    public static final IProperty<Region> REGION = new Property<Region>("region", null);

    /** flag for reversed edges. */
    public static final IProperty<Boolean> REVERSED = new Property<Boolean>("reversed", false);

    /** debug canvas. */
    public static final IProperty<IDebugCanvas> DEBUG_CANVAS = new Property<IDebugCanvas>(
            "debugCanvas");

    /** random number generator for the algorithm. */
    public static final IProperty<Random> RANDOM = new Property<Random>("random");

    /** the source port of a long edge before it was broken into multiple segments. */
    public static final IProperty<LPort> LONG_EDGE_SOURCE = new Property<LPort>("longEdgeSource",
            null);
    /** the target port of a long edge before it was broken into multiple segments. */
    public static final IProperty<LPort> LONG_EDGE_TARGET = new Property<LPort>("longEdgeTarget",
            null);

    /** edge constraints for nodes. */
    public static final IProperty<EdgeConstraint> EDGE_CONSTRAINT = new Property<EdgeConstraint>(
            "edgeConstraint", EdgeConstraint.NONE);

    /**
     * The layout unit a node belongs to. This property only makes sense for nodes. A layout unit is
     * a set of nodes between which no nodes belonging to other layout units may be placed. Nodes
     * not belonging to any layout unit may be placed arbitrarily between nodes of a layout unit.
     * Layer layout units are identified through one of their nodes.
     */
    public static final IProperty<LNode> IN_LAYER_LAYOUT_UNIT = new Property<LNode>(
            "inLayerLayoutUnit", null);

    /**
     * The in-layer constraint placed on a node. This indicates whether this node should be handled
     * like any other node, or if it must be placed at the top or bottom of a layer. This is
     * important for external port dummy nodes. Crossing minimizers are not required to respect this
     * constraint. If they don't, however, they must include a dependency on
     * {@link de.cau.cs.kieler.klay.layered.intermediate.InLayerConstraintProcessor}.
     */
    public static final IProperty<InLayerConstraint> IN_LAYER_CONSTRAINT = 
        new Property<InLayerConstraint>("inLayerConstraint", InLayerConstraint.NONE);

    /**
     * Indicates that a node {@code x} may only appear inside a layer before the node {@code y} the
     * property is set to. That is, having {@code x} appear after {@code y} would violate this
     * constraint. This property only makes sense for nodes.
     */
    public static final IProperty<LNode> IN_LAYER_SUCCESSOR_CONSTRAINT = new Property<LNode>(
            "inLayerSuccessorConstraint", null);
    
    /**
     * Crossing hint used for in-layer cross counting with northern and southern port dummies.
     */
    public static final IProperty<Integer> CROSSING_HINT = new Property<Integer>(
            "crossingHint", 0);

    /** Flags indicating the properties of a graph. */
    public static final IProperty<Set<GraphProperties>> GRAPH_PROPERTIES = 
        new Property<Set<GraphProperties>>("graphProperties", EnumSet.allOf(GraphProperties.class));

    /**
     * The side of an external port a dummy node was created for.
     */
    public static final IProperty<PortSide> EXT_PORT_SIDE = new Property<PortSide>(
            "externalPortSide", PortSide.UNDEFINED);

    /**
     * The original position or position-to-node-size ratio of an external port. This is a property
     * usually used for external port dummy nodes. There is only one coordinate since the side of
     * the port determines the other coordinate. (for eastern and western ports, the x coordinate is
     * determined automatically; for northern and southern ports, the y coordinate is determined
     * automatically)
     */
    public static final IProperty<Double> EXT_PORT_RATIO_OR_POSITION = new Property<Double>(
            "externalPortRatioOrPosition", 0.0);
    
    /**
     * A list of nodes whose barycenters should go into the barycenter calculation of the node
     * this property is set on. Nodes in this list are expected to be in the same layer as the
     * node the property is set on. This is primarily used when edges are rerouted from a node
     * to dummy nodes.
     */
    public static final IProperty<List<LNode>> BARYCENTER_ASSOCIATES = new Property<List<LNode>>(
            "barycenterAssociates", null);

    /**
     * KNode that contained the origin of this node in the KGraph.
     */
    public static final IProperty<KNode> PARENT = new Property<KNode>("parent", null);
    
    /**
     * LNode that is the left border node for the compound node the side dummy guards the side of.
     */
    public static final IProperty<LNode> SIDE_OWNER = new Property<LNode>("sideOwner", null); 

    /**
     * Flag indicating whether an LPort is set to a leave node in the inclusion tree to enable
     * connections with dummy edges for layering.
     */
    public static final IProperty<Boolean> LEAVE_DUMMY_PORT = new Property<Boolean>(
            "leaveDummyPort", false);
    
    /**
     * UPPER_BORDER_DUMMY node determining the compound node this dummy node belongs to.
     */
    public static final IProperty<LNode> COMPOUND_NODE = new Property<LNode>(
            "CompoundNode", null);
    
    /**
     * KInsets of the KNode a upper border dummy node is representing.
     */
    public static final IProperty<KInsets> ORIGINAL_INSETS = 
            new Property<KInsets>("OriginalInsets", null);

    // /////////////////////////////////////////////////////////////////////////////
    // USER INTERFACE OPTIONS

    /** default value for object spacing. */
    public static final float DEF_SPACING = 20.0f;
    /** minimal spacing between objects. */
    public static final Property<Float> OBJ_SPACING = new Property<Float>(LayoutOptions.SPACING,
            DEF_SPACING, 0.0f);
    /** spacing to the border of the drawing. */
    public static final Property<Float> BORDER_SPACING = new Property<Float>(
            LayoutOptions.BORDER_SPACING, DEF_SPACING, 0.0f);

    /** option identifier for edge spacing factor. */
    public static final String EDGE_SPACING_FACTOR_ID = 
        "de.cau.cs.kieler.klay.layered.edgeSpacingFactor";
    /** default value for edge spacing factor. */
    public static final float DEF_EDGE_SPACING_FACTOR = 0.5f;
    /** factor for minimal spacing between edges. */
    public static final Property<Float> EDGE_SPACING_FACTOR = new Property<Float>(
            EDGE_SPACING_FACTOR_ID, DEF_EDGE_SPACING_FACTOR);

    /** priority of elements. */
    public static final Property<Integer> PRIORITY = new Property<Integer>(LayoutOptions.PRIORITY,
            0);

    /** default value for aspect ratio. */
    public static final float DEF_ASPECT_RATIO = 1.6f;
    /** the aspect ratio for packing connected components. */
    public static final Property<Float> ASPECT_RATIO = new Property<Float>(
            LayoutOptions.ASPECT_RATIO, DEF_ASPECT_RATIO, 0.0f);

    /** option identifier for minimal angle. */
    public static final String MIN_EDGE_ANGLE_ID = "de.cau.cs.kieler.klay.layered.minimalAngle";
    /** defines the minimal angle a short edge may have. */
    public static final IProperty<Integer> MIN_EDGE_ANGLE = new Property<Integer>(
            MIN_EDGE_ANGLE_ID, 0);

    /** option identifier for straight edges. */
    public static final String STRAIGHT_EDGES_ID = "de.cau.cs.kieler.klay.layered.straightEdges";
    /** whether edges shall be straightened as much as possible. */
    public static final IProperty<Boolean> STRAIGHT_EDGES = new Property<Boolean>(
            STRAIGHT_EDGES_ID, false);

    /** option identifier for distribute nodes. */
    public static final String DISTRIBUTE_NODES_ID = "de.cau.cs.kieler.klay.layered.distributeNodes";
    /** whether nodes shall be distributed during layer assignment. */
    public static final IProperty<Boolean> DISTRIBUTE_NODES = new Property<Boolean>(
            DISTRIBUTE_NODES_ID, false);

    /** option identifier for edge routing. */
    public static final String EDGE_ROUTING_ID = "de.cau.cs.kieler.klay.layered.edgeRouting";
    /** property to choose an edge routing strategy. */
    public static final IProperty<EdgeRoutingStrategy> EDGE_ROUTING = new Property<EdgeRoutingStrategy>(
            EDGE_ROUTING_ID, EdgeRoutingStrategy.POLYLINE);

    /** option identifier for node layering. */
    public static final String NODE_LAYERING_ID = "de.cau.cs.kieler.klay.layered.nodeLayering";
    /** property to choose a node layering strategy. */
    public static final IProperty<LayeringStrategy> NODE_LAYERING = new Property<LayeringStrategy>(
            NODE_LAYERING_ID, LayeringStrategy.NETWORK_SIMPLEX);

    /** option identifier for thoroughness. */
    public static final String THOROUGHNESS_ID = "de.cau.cs.kieler.klay.layered.thoroughness";
    /** property that determines how much effort should be spent. */
    public static final IProperty<Integer> THOROUGHNESS = new Property<Integer>(THOROUGHNESS_ID, 5,
            0);

    /** option identifier for layer constraint. */
    public static final String LAYER_CONSTRAINT_ID = "de.cau.cs.kieler.klay.layered.layerConstraint";
    /** property to set constraints on the node layering. */
    public static final IProperty<LayerConstraint> LAYER_CONSTRAINT = new Property<LayerConstraint>(
            LAYER_CONSTRAINT_ID, LayerConstraint.NONE);

    /** option identifier for separation of connected components. */
    public static final String SEPARATE_CC_ID = "de.cau.cs.kieler.klay.layered.separateConnComp";
    /** property for choosing whether connected components are processed separately. */
    public static final IProperty<Boolean> SEPARATE_CC = new Property<Boolean>(SEPARATE_CC_ID, true);

    // /////////////////////////////////////////////////////////////////////////////
    // CONSTRUCTOR

    /**
     * Hidden default constructor.
     */
    private Properties() {
    }

}
