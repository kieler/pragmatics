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
 * @kieler.rating 2011-03-14 yellow
 *     reviewed by cmot, cds
 * @author msp
 */
public final class LayoutOptions {
    
    ///////   PROGRAMMATIC LAYOUT OPTIONS   ///////

    /** layout option key: comment box (boolean). */
    public static final String COMMENT_BOX_ID = "de.cau.cs.kieler.commentBox";
    /**
     * Whether the associated node is to be interpreted as a comment box. In that case its placement
     * should be similar to how labels are handled. Any edges incident to a comment box specify to
     * which graph elements the comment is related. [programmatically set]
     */
    public static final IProperty<Boolean> COMMENT_BOX = new Property<Boolean>(COMMENT_BOX_ID, false);
    
    /** layout option key: diagram type (string). */
    public static final String DIAGRAM_TYPE_ID = "de.cau.cs.kieler.diagramType";
    /**
     * The diagram type of a parent node. Diagram types are defined via extension point and are
     * given an identifier and a name. The value of this option must be one of the pre-defined
     * diagram types. [programmatically set]
     */
    public static final IProperty<String> DIAGRAM_TYPE = new Property<String>(DIAGRAM_TYPE_ID);
    
    /** layout option key: placement positions for edge labels (enum). */
    public static final String EDGE_LABEL_PLACEMENT_ID =
        "de.cau.cs.kieler.edgeLabelPlacement";
    /**
     * Where to place an edge label: at the head, center, or tail. [programmatically set]
     */
    public static final IProperty<EdgeLabelPlacement> EDGE_LABEL_PLACEMENT
            = new Property<EdgeLabelPlacement>(EDGE_LABEL_PLACEMENT_ID, EdgeLabelPlacement.UNDEFINED);
    
    /** layout option key: edge type (enum). */
    public static final String EDGE_TYPE_ID = "de.cau.cs.kieler.edgeType";
    /**
     * The type of edge. This is usually used for UML class diagrams, where associations must be
     * handled differently from generalizations. [programmatically set]
     */
    public static final IProperty<EdgeType> EDGE_TYPE = new Property<EdgeType>(
            EDGE_TYPE_ID, EdgeType.NONE);
    
    /** layout option key: font name (string). */
    public static final String FONT_NAME_ID = "de.cau.cs.kieler.fontName";
    /**
     * The name of the font that is used for a label. [programmatically set]
     */
    public static final IProperty<String> FONT_NAME = new Property<String>(FONT_NAME_ID);
    
    /** layout option key: font size (integer). */
    public static final String FONT_SIZE_ID = "de.cau.cs.kieler.fontSize";
    /**
     * The size of the font that is used for a label. [programmatically set]
     */
    public static final IProperty<Integer> FONT_SIZE = new Property<Integer>(FONT_SIZE_ID, 0);
    
    /** layout option key: hypernode (boolean). */
    public static final String HYPERNODE_ID = "de.cau.cs.kieler.hypernode";
    /**
     * Whether the associated node is to be interpreted as a hypernode. All incident
     * edges of a hypernode belong to the same hyperedge. [programmatically set]
     */
    public static final IProperty<Boolean> HYPERNODE = new Property<Boolean>(HYPERNODE_ID, false);
    
    /** layout option key: minimal height (float). */
    public static final String MIN_HEIGHT_ID = "de.cau.cs.kieler.minHeight";
    /**
     * The minimal height of a node. [programmatically set]
     */
    public static final IProperty<Float> MIN_HEIGHT = new Property<Float>(MIN_HEIGHT_ID, 0f, 0f);
    
    /** layout option key: minimal width (float). */
    public static final String MIN_WIDTH_ID = "de.cau.cs.kieler.minWidth";
    /**
     * The minimal width of a node. [programmatically set]
     */
    public static final IProperty<Float> MIN_WIDTH = new Property<Float>(MIN_WIDTH_ID, 0f, 0f);
    
    /** layout option key: no layout (boolean). */
    public static final String NO_LAYOUT_ID = "de.cau.cs.kieler.noLayout";
    /**
     * No layout is done for the associated element. This is used to mark parts of a diagram to
     * avoid their inclusion in the layout graph, or to mark parts of the layout graph to prevent
     * layout engines from processing them. [programmatically set]
     */
    public static final IProperty<Boolean> NO_LAYOUT = new Property<Boolean>(NO_LAYOUT_ID, false);
    
    /** layout option key: offset of ports on the node border (float). */
    public static final String OFFSET_ID = "de.cau.cs.kieler.offset";
    /**
     * Offset of a graph element. This is mostly used to indicate the distance of a port from its
     * node: with a positive offset the port is moved outside of the node, while with a negative
     * offset the port is moved towards the inside. [programmatically set]
     */
    public static final IProperty<Float> OFFSET = new Property<Float>(OFFSET_ID, 0f);
    
    /** layout option key: side of a port on its node's boundary (enum). */
    public static final String PORT_SIDE_ID = "de.cau.cs.kieler.portSide";
    /**
     * On which side of its corresponding node a port is situated. [programmatically set]
     */
    public static final IProperty<PortSide> PORT_SIDE = new Property<PortSide>(PORT_SIDE_ID,
            PortSide.UNDEFINED);

    
    ///////  USER INTERFACE LAYOUT OPTIONS  ///////

    /** layout option key: layout algorithm (string). */
    public static final String ALGORITHM_ID = "de.cau.cs.kieler.algorithm";
    /**
     * Which layout algorithm to use for the content of a parent node. This can be either
     * a layout algorithm identifier or a layout type identifier. In the latter case KIML
     * tries to find the most suitable layout algorithm that matches the given layout type.
     */
    public static final IProperty<String> ALGORITHM = new Property<String>(ALGORITHM_ID);
    
    /** layout option key: alignment (enum). */
    public static final String ALIGNMENT_ID = "de.cau.cs.kieler.alignment";
    /**
     * Alignment of a node. The meaning of this option depends on the specific layout algorithm.
     */
    public static final IProperty<Alignment> ALIGNMENT = new Property<Alignment>(
               ALIGNMENT_ID, Alignment.AUTOMATIC);
    
    /** layout option key: aimed aspect ratio (float). */
    public static final String ASPECT_RATIO_ID = "de.cau.cs.kieler.aspectRatio";
    /**
     * The desired aspect ratio of a parent node. The algorithm should try to arrange the graph
     * in such a way that the width / height ratio of the resulting drawing approximates the
     * given value.
     */
    public static final IProperty<Float> ASPECT_RATIO = new Property<Float>(ASPECT_RATIO_ID, 0f);
    
    /** layout option key: fixed bend points for edges (object). */
    public static final String BEND_POINTS_ID = "de.cau.cs.kieler.bendPoints";
    /**
     * The bend points of an edge. This is used by the
     * {@link de.cau.cs.kieler.kiml.FixedLayoutProvider} to specify a pre-defined routing for an
     * edge. The vector chain must include the source point, any bend points, and the target point.
     */
    public static final IProperty<KVectorChain> BEND_POINTS = new Property<KVectorChain>(
            BEND_POINTS_ID);
    
    /** layout option key: distance to border of the drawing (float). */
    public static final String BORDER_SPACING_ID = "de.cau.cs.kieler.borderSpacing";
    /**
     * Spacing of the content of a parent node to its inner border. The inner border is the node
     * border, which is given by width and height, with subtracted insets.
     */
    public static final IProperty<Float> BORDER_SPACING = new Property<Float>(
            BORDER_SPACING_ID, -1.0f);
    
    /** layout option key: debug mode (boolean). */
    public static final String DEBUG_MODE_ID = "de.cau.cs.kieler.debugMode";
    /**
     * Whether the algorithm should run in debug mode for the content of a parent node.
     */
    public static final IProperty<Boolean> DEBUG_MODE = new Property<Boolean>(DEBUG_MODE_ID, false);
    
    /** layout option key: direction of layout (enum). */
    public static final String DIRECTION_ID = "de.cau.cs.kieler.direction";
    /**
     * The overall direction of layout: right, left, down, or up.
     */
    public static final IProperty<Direction> DIRECTION = new Property<Direction>(
            DIRECTION_ID, Direction.UNDEFINED);
    
    /** layout option key: edge routing style (enum). */
    public static final String EDGE_ROUTING_ID = "de.cau.cs.kieler.edgeRouting";
    /**
     * What kind of edge routing style should be applied for the content of a parent node.
     */
    public static final IProperty<EdgeRouting> EDGE_ROUTING = new Property<EdgeRouting>(
            EDGE_ROUTING_ID, EdgeRouting.UNDEFINED);
    
    /** layout option key: expand nodes to fill their parent (boolean). */
    public static final String EXPAND_NODES_ID = "de.cau.cs.kieler.expandNodes";
    /**
     * Whether the size of contained nodes should be expanded to fill the whole area.
     */
    public static final IProperty<Boolean> EXPAND_NODES = new Property<Boolean>(
            EXPAND_NODES_ID, false);

    /** layout option key: optimize layout for user interaction (boolean). */
    public static final String INTERACTIVE_ID = "de.cau.cs.kieler.interactive";
    /**
     * Whether the algorithm should be run in debug mode for the content of a parent node.
     */
    public static final IProperty<Boolean> INTERACTIVE = new Property<Boolean>(INTERACTIVE_ID, false);
    
    /** layout option key: spacing of edge labels to edges (float). */
    public static final String LABEL_SPACING_ID = "de.cau.cs.kieler.labelSpacing";
    /**
     * Determines the amount of space to be left around the labels of the associated edge.
     */
    public static final IProperty<Float> LABEL_SPACING = new Property<Float>(LABEL_SPACING_ID, -1.0f);

    /** layout option key: layout hierarchy (boolean). */
    public static final String LAYOUT_HIERARCHY_ID
            = "de.cau.cs.kieler.layoutHierarchy";
    /**
     * Whether the whole hierarchy shall be layouted. If this option is not set, each hierarchy
     * level of the graph is processed independently, possibly by different layout algorithms,
     * beginning with the lowest level. If it is set, the algorithm is responsible to process
     * all hierarchy levels that are contained in the associated parent node.
     */
    public static final IProperty<Boolean> LAYOUT_HIERARCHY = new Property<Boolean>(
            LAYOUT_HIERARCHY_ID, false);

    /** layout option key: constraints for port positions (enum). */
    public static final String PORT_CONSTRAINTS_ID = "de.cau.cs.kieler.portConstraints";
    /**
     * What constraints on port positions are given for the associated node.
     */
    public static final IProperty<PortConstraints> PORT_CONSTRAINTS = new Property<PortConstraints>(
            PORT_CONSTRAINTS_ID, PortConstraints.UNDEFINED);
    
    /** layout option key: pre-defined position (object). */
    public static final String POSITION_ID = "de.cau.cs.kieler.position";
    /**
     * The position of a node, port, or label. This is used by the
     * {@link de.cau.cs.kieler.kiml.FixedLayoutProvider} to specify a pre-defined position.
     */
    public static final IProperty<KVector> POSITION = new Property<KVector>(POSITION_ID);
    
    /** layout option key: priority of elements (integer). */
    public static final String PRIORITY_ID = "de.cau.cs.kieler.priority";
    /**
     * The priority of a graph element. The meaning of this option depends on the specific
     * layout algorithm and the context where it is used.
     */
    public static final IProperty<Integer> PRIORITY = new Property<Integer>(PRIORITY_ID);

    /** layout option key: randomization seed (integer). */
    public static final String RANDOM_SEED_ID = "de.cau.cs.kieler.randomSeed";    
    /**
     * A pre-defined seed for pseudo-random number generators. This can be used to control
     * randomized layout algorithms. If the value is 0, the seed shall be determined
     * pseudo-randomly (e.g. from the system time).
     */
    public static final IProperty<Integer> RANDOM_SEED = new Property<Integer>(RANDOM_SEED_ID);
    
    /** layout option key: separation of connected components (boolean). */
    public static final String SEPARATE_CC_ID = "de.cau.cs.kieler.separateConnComp";
    /**
     * Property for choosing whether connected components are processed separately.
     */
    public static final IProperty<Boolean> SEPARATE_CC = new Property<Boolean>(SEPARATE_CC_ID);

    /** layout option key: constraints for node sizes (boolean). */
    public static final String SIZE_CONSTRAINT_ID = "de.cau.cs.kieler.sizeConstraint";
    /**
     * Constraint for modifying node size. If set to {@link SizeConstraint#FIXED} and the node is
     * empty, its size is not changed. Otherwise it is minimized considering some properties,
     * depending on the chosen minimization level.
     */
    public static final IProperty<SizeConstraint> SIZE_CONSTRAINT = new Property<SizeConstraint>(
            SIZE_CONSTRAINT_ID, SizeConstraint.FIXED);
    
    /** layout option key: minimal distance between elements (float). */
    public static final String SPACING_ID = "de.cau.cs.kieler.spacing";    
    /**
     * Overall spacing between elements. This is mostly interpreted as the minimal distance
     * between each two nodes and should also influence the spacing between edges.
     */
    public static final IProperty<Float> SPACING = new Property<Float>(SPACING_ID, -1f, 0f);


    /**
     * Hide constructor to avoid instantiation.
     */
    private LayoutOptions() {
    }
    
}
