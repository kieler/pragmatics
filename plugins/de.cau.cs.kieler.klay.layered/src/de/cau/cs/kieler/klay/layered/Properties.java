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

import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortConstraints;
import de.cau.cs.kieler.kiml.util.IDebugCanvas;
import de.cau.cs.kieler.klay.layered.impl.LinearSegmentsNodePlacer.Region;

/**
 * Container for priority definitions.
 * 
 * @author msp
 */
public final class Properties {

    /** Definition of node types used in the layered approach. */
    public enum NodeType {
        /** a normal node is created from a node of the original graph. */
        NORMAL,
        /** a dummy node created to split a long edge. */
        LONG_EDGE;
    }

    /** the original object from which a graph element was created. */
    public static final IProperty<Object> ORIGIN = new Property<Object>("origin");
    /** node type. */
    public static final IProperty<NodeType> NODE_TYPE = new Property<NodeType>("nodeType",
            NodeType.NORMAL);
    /** offset for nodes in linear segments. */
    public static final IProperty<Integer> LINSEG_OFFSET = new Property<Integer>("linsegOffset", 0);
    /** owning region for node. */
    public static final IProperty<Region> REGION = new Property<Region>("region", null);
    /** flag for reversed edges. */
    public static final IProperty<Boolean> REVERSED = new Property<Boolean>("reversed", false);
    /** debug canvas. */
    public static final IProperty<IDebugCanvas> DEBUG_CANVAS = new Property<IDebugCanvas>(
            "debugCanvas");

    // / USER INTERFACE OPTIONS

    /** default value for object spacing. */
    public static final float DEF_SPACING = 20.0f;
    /** minimal spacing between objects. */
    public static final Property<Float> OBJ_SPACING = new Property<Float>(
            LayoutOptions.OBJ_SPACING, DEF_SPACING);

    /** priority of elements. */
    public static final Property<Integer> PRIORITY = new Property<Integer>(LayoutOptions.PRIORITY,
            0);

    /** port constraints. */
    public static final Property<PortConstraints> PORT_CONS = new Property<PortConstraints>(
            LayoutOptions.PORT_CONSTRAINTS, PortConstraints.FREE);

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
    
    /** option identifier for layering segmentation. */
    public static final String SEGMENTATE_LAYERING_ID =
        "de.cau.cs.kieler.klay.layered.segmentateLayering";
    /** indicates, whether the layering shall be arranged in segments. */
    public static final IProperty<Boolean> SEGMENTATE_LAYERING = new Property<Boolean>(
            SEGMENTATE_LAYERING_ID, false);
    
    /** option identifier for layering enhancement. */
    public static final String ENHANCE_LAYERING_ID = "de.cau.cs.kieler.klay.layered.enhanceLayering";
    /** whether the layering shall be enhanced. */
    public static final IProperty<Boolean> ENHANCE_LAYERING = new Property<Boolean>(
            ENHANCE_LAYERING_ID, false);

    /** option identifier for distribute nodes. */
    public static final String DISTRIBUTE_NODES_ID = "de.cau.cs.kieler.klay.layered.distributeNodes";
    /** whether nodes shall be distributed during layer assignment. */
    public static final IProperty<Boolean> DISTRIBUTE_NODES = new Property<Boolean>(
            DISTRIBUTE_NODES_ID, false);

    /** option identifier for edge routing. */
    public static final String EDGE_ROUTING_ID = "de.cau.cs.kieler.klay.layered.edgeRouting";
    /** property to choose an edge routing strategy. */
    public static final IProperty<LayeredEdgeRouting> EDGE_ROUTING = new Property<LayeredEdgeRouting>(
            EDGE_ROUTING_ID, LayeredEdgeRouting.POLYLINE);

    /** option identifier for node layering. */
    public static final String NODE_LAYERING_ID = "de.cau.cs.kieler.klay.layered.nodeLayering";
    /** property to choose a node layering strategy. */
    public static final IProperty<NodeLayering> NODE_LAYERING = new Property<NodeLayering>(
            NODE_LAYERING_ID, NodeLayering.NETWORK_SIMPLEX);

    /**
     * Hidden default constructor.
     */
    private Properties() {
    }

}
