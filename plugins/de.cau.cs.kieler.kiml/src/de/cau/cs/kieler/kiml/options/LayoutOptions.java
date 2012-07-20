/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2009 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.options;

import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.core.math.KVectorChain;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;

/**
 * Definition of layout options. Layout options are divided into programmatic options,
 * which are defined by static code, and user interface options, which are defined by
 * extension point. The former can be accessed with static variables, while the latter
 * are accessed with methods.
 * 
 * @kieler.design 2011-03-14 reviewed by cmot, cds
 * @kieler.rating proposed yellow 2012-07-10 msp
 * @author msp
 */
public final class LayoutOptions {
    
    ///////   PROGRAMMATIC LAYOUT OPTIONS   ///////

    /**
     * Whether the associated node is to be interpreted as a comment box. In that case its placement
     * should be similar to how labels are handled. Any edges incident to a comment box specify to
     * which graph elements the comment is related. [programmatically set]
     */
    public static final IProperty<Boolean> COMMENT_BOX = new Property<Boolean>(
            "de.cau.cs.kieler.commentBox", false);
    
    /**
     * The diagram type of a parent node. Diagram types are defined via extension point and are
     * given an identifier and a name. The value of this option must be one of the pre-defined
     * diagram types. [programmatically set]
     */
    public static final IProperty<String> DIAGRAM_TYPE = new Property<String>(
            "de.cau.cs.kieler.diagramType");
    
    /**
     * Where to place an edge label: at the head, center, or tail. [programmatically set]
     */
    public static final IProperty<EdgeLabelPlacement> EDGE_LABEL_PLACEMENT
            = new Property<EdgeLabelPlacement>("de.cau.cs.kieler.edgeLabelPlacement",
                    EdgeLabelPlacement.UNDEFINED);
    
    /**
     * The type of edge. This is usually used for UML class diagrams, where associations must be
     * handled differently from generalizations. [programmatically set]
     */
    public static final IProperty<EdgeType> EDGE_TYPE = new Property<EdgeType>(
            "de.cau.cs.kieler.edgeType", EdgeType.NONE);
    
    /**
     * The name of the font that is used for a label. [programmatically set]
     */
    public static final IProperty<String> FONT_NAME = new Property<String>(
            "de.cau.cs.kieler.fontName");
    
    /**
     * The size of the font that is used for a label. [programmatically set]
     */
    public static final IProperty<Integer> FONT_SIZE = new Property<Integer>(
            "de.cau.cs.kieler.fontSize", 0);
    
    /**
     * Whether the associated node is to be interpreted as a hypernode. All incident
     * edges of a hypernode belong to the same hyperedge. [programmatically set]
     */
    public static final IProperty<Boolean> HYPERNODE = new Property<Boolean>(
            "de.cau.cs.kieler.hypernode", false);
    
    /**
     * The minimal height of a node. [programmatically set]
     */
    public static final IProperty<Float> MIN_HEIGHT = new Property<Float>(
            "de.cau.cs.kieler.minHeight", 0f, 0f);
    
    /**
     * The minimal width of a node. [programmatically set]
     */
    public static final IProperty<Float> MIN_WIDTH = new Property<Float>(
            "de.cau.cs.kieler.minWidth", 0f, 0f);
    
    /**
     * No layout is done for the associated element. This is used to mark parts of a diagram to
     * avoid their inclusion in the layout graph, or to mark parts of the layout graph to prevent
     * layout engines from processing them. [programmatically set]
     */
    public static final IProperty<Boolean> NO_LAYOUT = new Property<Boolean>(
            "de.cau.cs.kieler.noLayout", false);
    
    /**
     * Offset of a graph element. This is mostly used to indicate the distance of a port from its
     * node: with a positive offset the port is moved outside of the node, while with a negative
     * offset the port is moved towards the inside. [programmatically set]
     */
    public static final IProperty<Float> OFFSET = new Property<Float>(
            "de.cau.cs.kieler.offset");
    
    /**
     * On which side of its corresponding node a port is situated. [programmatically set]
     */
    public static final IProperty<PortSide> PORT_SIDE = new Property<PortSide>(
            "de.cau.cs.kieler.portSide", PortSide.UNDEFINED);

    
    ///////  USER INTERFACE LAYOUT OPTIONS  ///////

    /**
     * Which layout algorithm to use for the content of a parent node. This can be either
     * a layout algorithm identifier or a layout type identifier. In the latter case KIML
     * tries to find the most suitable layout algorithm that matches the given layout type.
     */
    public static final IProperty<String> ALGORITHM = new Property<String>(
            "de.cau.cs.kieler.algorithm");
    
    /**
     * Alignment of a node. The meaning of this option depends on the specific layout algorithm.
     */
    public static final IProperty<Alignment> ALIGNMENT = new Property<Alignment>(
            "de.cau.cs.kieler.alignment", Alignment.AUTOMATIC);
    
    /**
     * The desired aspect ratio of a parent node. The algorithm should try to arrange the graph
     * in such a way that the width / height ratio of the resulting drawing approximates the
     * given value.
     */
    public static final IProperty<Float> ASPECT_RATIO = new Property<Float>(
            "de.cau.cs.kieler.aspectRatio", 0f);
    
    /**
     * The bend points of an edge. This is used by the
     * {@link de.cau.cs.kieler.kiml.FixedLayoutProvider} to specify a pre-defined routing for an
     * edge. The vector chain must include the source point, any bend points, and the target point.
     */
    public static final IProperty<KVectorChain> BEND_POINTS = new Property<KVectorChain>(
            "de.cau.cs.kieler.bendPoints");
    
    /**
     * Spacing of the content of a parent node to its inner border. The inner border is the node
     * border, which is given by width and height, with subtracted insets.
     */
    public static final IProperty<Float> BORDER_SPACING = new Property<Float>(
            "de.cau.cs.kieler.borderSpacing", -1.0f);
    
    /**
     * Whether the algorithm should run in debug mode for the content of a parent node.
     */
    public static final IProperty<Boolean> DEBUG_MODE = new Property<Boolean>(
            "de.cau.cs.kieler.debugMode", false);
    
    /**
     * The overall direction of layout: right, left, down, or up.
     */
    public static final IProperty<Direction> DIRECTION = new Property<Direction>(
            "de.cau.cs.kieler.direction", Direction.UNDEFINED);
    
    /**
     * What kind of edge routing style should be applied for the content of a parent node.
     */
    public static final IProperty<EdgeRouting> EDGE_ROUTING = new Property<EdgeRouting>(
            "de.cau.cs.kieler.edgeRouting", EdgeRouting.UNDEFINED);
    
    /**
     * Whether the size of contained nodes should be expanded to fill the whole area.
     */
    public static final IProperty<Boolean> EXPAND_NODES = new Property<Boolean>(
            "de.cau.cs.kieler.expandNodes", false);

    /**
     * Whether the algorithm should be run in debug mode for the content of a parent node.
     */
    public static final IProperty<Boolean> INTERACTIVE = new Property<Boolean>(
            "de.cau.cs.kieler.interactive", false);
    
    /**
     * Determines the amount of space to be left around the labels of the associated edge.
     */
    public static final IProperty<Float> LABEL_SPACING = new Property<Float>(
            "de.cau.cs.kieler.labelSpacing", -1.0f);

    /**
     * Whether the whole hierarchy shall be layouted. If this option is not set, each hierarchy
     * level of the graph is processed independently, possibly by different layout algorithms,
     * beginning with the lowest level. If it is set, the algorithm is responsible to process
     * all hierarchy levels that are contained in the associated parent node.
     * @see GraphFeature#COMPOUND
     */
    public static final IProperty<Boolean> LAYOUT_HIERARCHY = new Property<Boolean>(
            "de.cau.cs.kieler.layoutHierarchy", false);

    /**
     * What constraints on port positions are given for the associated node.
     */
    public static final IProperty<PortConstraints> PORT_CONSTRAINTS = new Property<PortConstraints>(
            "de.cau.cs.kieler.portConstraints", PortConstraints.UNDEFINED);
    
    /**
     * The position of a node, port, or label. This is used by the
     * {@link de.cau.cs.kieler.kiml.FixedLayoutProvider} to specify a pre-defined position.
     */
    public static final IProperty<KVector> POSITION = new Property<KVector>(
            "de.cau.cs.kieler.position");
    
    /**
     * The priority of a graph element. The meaning of this option depends on the specific
     * layout algorithm and the context where it is used.
     */
    public static final IProperty<Integer> PRIORITY = new Property<Integer>(
            "de.cau.cs.kieler.priority");

    /**
     * A pre-defined seed for pseudo-random number generators. This can be used to control
     * randomized layout algorithms. If the value is 0, the seed shall be determined
     * pseudo-randomly (e.g. from the system time).
     */
    public static final IProperty<Integer> RANDOM_SEED = new Property<Integer>(
            "de.cau.cs.kieler.randomSeed");
    
    /**
     * Property for choosing whether connected components are processed separately.
     */
    public static final IProperty<Boolean> SEPARATE_CC = new Property<Boolean>(
            "de.cau.cs.kieler.separateConnComp");

    /**
     * Constraint for modifying node size. If set to {@link SizeConstraint#FIXED} and the node is
     * empty, its size is not changed. Otherwise it is minimized considering some properties,
     * depending on the chosen minimization level.
     */
    public static final IProperty<SizeConstraint> SIZE_CONSTRAINT = new Property<SizeConstraint>(
            "de.cau.cs.kieler.sizeConstraint", SizeConstraint.FIXED);
    
    /**
     * Overall spacing between elements. This is mostly interpreted as the minimal distance
     * between each two nodes and should also influence the spacing between edges.
     */
    public static final IProperty<Float> SPACING = new Property<Float>(
            "de.cau.cs.kieler.spacing", -1f, 0f);


    /**
     * Hide constructor to avoid instantiation.
     */
    private LayoutOptions() {
    }
    
}
