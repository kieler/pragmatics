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

import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.kiml.options.PortConstraints;
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
    
    /** default value for object spacing. */
    public static final float DEF_SPACING = 20.0f;

    /** the original object from which a graph element was created. */
    public static final Property<Object> ORIGIN = new Property<Object>("origin");
    /** minimal spacing between objects. */
    public static final Property<Float> OBJ_SPACING = new Property<Float>(
            "objectSpacing", DEF_SPACING);
    /** priority of elements. */
    public static final Property<Integer> PRIORITY = new Property<Integer>(
            "priority", 0);
    /** node type. */
    public static final Property<NodeType> NODE_TYPE = new Property<NodeType>(
            "nodeType", NodeType.NORMAL);
    /** port constraints. */
    public static final Property<PortConstraints> PORT_CONS = new Property<PortConstraints>(
            "portConstraints", PortConstraints.FREE);
    /** offset for nodes in linear segments. */
    public static final Property<Integer> LINSEG_OFFSET = new Property<Integer>(
            "linsegOffset", 0);
    /** owning region for node. */
    public static final Property<Region> REGION = new Property<Region>(
            "region", null);
    /** additional information for LinearSegmentsNodePlacer on the layeredGraph. */
    public static final Property<Boolean> STRAIGHT_EDGES = new Property<Boolean>(
            "straightEdges", false);
    /** additional information for LongestPathLayerer on the layeredGraph. */
    public static final Property<Boolean> DISTRIBUTE_NODES = new Property<Boolean>(
            "distributeNodes", false);
    /** minimal edge angle. */
    public static final Property<Integer> MINIMAL_EDGE_ANGLE = new Property<Integer>(
            "minEdgeAngle", 0);
    /** flag for reversed edges. */
    public static final Property<Boolean> REVERSED = new Property<Boolean>(
            "reversed", false);
    
    /**
     * Hidden default constructor.
     */
    private Properties() {
    }

}
