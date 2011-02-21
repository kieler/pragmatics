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
import de.cau.cs.kieler.kiml.klayoutdata.KInsets;

/**
 * Definition of layout options. Layout options are divided into programmatic options,
 * which are defined by static code, and user interface options, which are defined by
 * extension point. The former can be accessed with static variables, while the latter
 * are accessed with methods.
 * 
 * @kieler.rating 2009-12-11 proposed yellow msp
 * @author msp
 */
public final class LayoutOptions {
    
    ///////   PROGRAMMATIC LAYOUT OPTIONS   ///////

    /** layout option key: comment box (boolean). */
    public static final String COMMENT_BOX_ID = "de.cau.cs.kieler.commentBox";
    /** comment box property. */
    public static final IProperty<Boolean> COMMENT_BOX = new Property<Boolean>(COMMENT_BOX_ID, false);
    
    /** layout option key: diagram type (string). */
    public static final String DIAGRAM_TYPE_ID = "de.cau.cs.kieler.diagramType";
    /** diagram type property. */
    public static final IProperty<String> DIAGRAM_TYPE = new Property<String>(DIAGRAM_TYPE_ID);
    
    /** layout option key: placement positions for edge labels (enum). */
    public static final String EDGE_LABEL_PLACEMENT_ID =
        "de.cau.cs.kieler.edgeLabelPlacement";
    /** edge label placement property. */
    public static final IProperty<EdgeLabelPlacement> EDGE_LABEL_PLACEMENT
            = new Property<EdgeLabelPlacement>(EDGE_LABEL_PLACEMENT_ID, EdgeLabelPlacement.UNDEFINED);
    
    /** layout option key: edge type (enum). */
    public static final String EDGE_TYPE_ID = "de.cau.cs.kieler.edgeType";
    /** edge type property. */
    public static final IProperty<EdgeType> EDGE_TYPE = new Property<EdgeType>(
            EDGE_TYPE_ID, EdgeType.NONE);
    
    /** layout option key: font name (string). */
    public static final String FONT_NAME_ID = "de.cau.cs.kieler.fontName";
    /** font name property. */
    public static final IProperty<String> FONT_NAME = new Property<String>(FONT_NAME_ID);
    
    /** layout option key: font size (integer). */
    public static final String FONT_SIZE_ID = "de.cau.cs.kieler.fontSize";
    /** font size property. */
    public static final IProperty<Integer> FONT_SIZE = new Property<Integer>(FONT_SIZE_ID, 0);
    
    /** layout option key: hypernode (boolean). */
    public static final String HYPERNODE_ID = "de.cau.cs.kieler.hypernode";
    /** hypernode property. */
    public static final IProperty<Boolean> HYPERNODE = new Property<Boolean>(HYPERNODE_ID, false);
    
    /** layout option key: distance of node contents to the boundary (float). */
    public static final String INSETS_ID = "de.cau.cs.kieler.insets";
    /** insets property. */
    public static final IProperty<KInsets> INSETS = new Property<KInsets>(INSETS_ID);
    
    /** layout option key: minimal height (float). */
    public static final String MIN_HEIGHT_ID = "de.cau.cs.kieler.minHeight";
    /** minimal height property. */
    public static final IProperty<Float> MIN_HEIGHT = new Property<Float>(MIN_HEIGHT_ID, 0f, 0f);
    
    /** layout option key: minimal width (float). */
    public static final String MIN_WIDTH_ID = "de.cau.cs.kieler.minWidth";
    /** minimal width property. */
    public static final IProperty<Float> MIN_WIDTH = new Property<Float>(MIN_WIDTH_ID, 0f, 0f);
    
    /** layout option key: no layout (boolean). */
    public static final String NO_LAYOUT_ID = "de.cau.cs.kieler.noLayout";
    /** 'no layout' property. */
    public static final IProperty<Boolean> NO_LAYOUT = new Property<Boolean>(NO_LAYOUT_ID, false);
    
    /** layout option key: offset of ports on the node border (float). */
    public static final String OFFSET_ID = "de.cau.cs.kieler.offset";
    /** offset property. */
    public static final IProperty<Float> OFFSET = new Property<Float>(OFFSET_ID, 0f);
    
    /** layout option key: side of a port on its node's boundary (enum). */
    public static final String PORT_SIDE_ID = "de.cau.cs.kieler.portSide";
    /** port side property. */
    public static final IProperty<PortSide> PORT_SIDE = new Property<PortSide>(PORT_SIDE_ID,
            PortSide.UNDEFINED);
    
    /** layout option key: rank of a port (integer). */
    public static final String PORT_RANK_ID = "de.cau.cs.kieler.portRank";
    /** port rank property. */
    public static final IProperty<Integer> PORT_RANK = new Property<Integer>(PORT_RANK_ID, -1);
    
    /** layout option key: shape of a node (enum). */
    public static final String SHAPE_ID = "de.cau.cs.kieler.shape";
    /** node shape property. */
    public static final IProperty<Shape> SHAPE = new Property<Shape>(SHAPE_ID, Shape.UNDEFINED);

    
    ///////  USER INTERFACE LAYOUT OPTIONS  ///////

    /** layout option key: layout algorithm (string). */
    public static final String ALGORITHM_ID = "de.cau.cs.kieler.algorithm";
    /** layout algorithm or type property. */
    public static final IProperty<String> ALGORITHM = new Property<String>(ALGORITHM_ID);
    
    /** layout option key: alignment (enum). */
    public static final String ALIGNMENT_ID = "de.cau.cs.kieler.alignment";
    /** alignment property. */
    public static final IProperty<Alignment> ALIGNMENT = new Property<Alignment>(
               ALIGNMENT_ID, Alignment.AUTOMATIC);
    
    /** layout option key: aimed aspect ratio (float). */
    public static final String ASPECT_RATIO_ID = "de.cau.cs.kieler.aspectRatio";
    /** aspect ratio property. */
    public static final IProperty<Float> ASPECT_RATIO = new Property<Float>(ASPECT_RATIO_ID, 0f);
    
    /** layout option key: fixed bend points for edges (object). */
    public static final String BEND_POINTS_ID = "de.cau.cs.kieler.bendPoints";
    /** bend points property. */
    public static final IProperty<KVectorChain> BEND_POINTS = new Property<KVectorChain>(
            BEND_POINTS_ID);
    
    /** layout option key: distance to border of the drawing (float). */
    public static final String BORDER_SPACING_ID = "de.cau.cs.kieler.borderSpacing";
    /** border spacing property. */
    public static final IProperty<Float> BORDER_SPACING = new Property<Float>(
            BORDER_SPACING_ID, -1.0f, 0.0f);
    
    /** layout option key: debug mode (boolean). */
    public static final String DEBUG_MODE_ID = "de.cau.cs.kieler.debugMode";
    /** debug mode property. */
    public static final IProperty<Boolean> DEBUG_MODE = new Property<Boolean>(DEBUG_MODE_ID, false);
    
    /** layout option key: direction of layout (enum). */
    public static final String DIRECTION_ID = "de.cau.cs.kieler.direction";
    /** layout direction property. */
    public static final IProperty<Direction> DIRECTION = new Property<Direction>(
            DIRECTION_ID, Direction.UNDEFINED);
    
    /** layout option key: edge routing style (enum). */
    public static final String EDGE_ROUTING_ID = "de.cau.cs.kieler.edgeRouting";
    /** edge routing property. */
    public static final IProperty<EdgeRouting> EDGE_ROUTING = new Property<EdgeRouting>(
            EDGE_ROUTING_ID, EdgeRouting.UNDEFINED);
    
    /** layout option key: expand nodes to fill their parent (boolean). */
    public static final String EXPAND_NODES_ID = "de.cau.cs.kieler.expandNodes";
    /** expand nodes property. */
    public static final IProperty<Boolean> EXPAND_NODES = new Property<Boolean>(
            EXPAND_NODES_ID, false);

    /** layout option key: size constraint for nodes (boolean). */
    public static final String FIXED_SIZE_ID = "de.cau.cs.kieler.fixedSize";
    /** fixed size property. */
    public static final IProperty<Boolean> FIXED_SIZE = new Property<Boolean>(FIXED_SIZE_ID, true);

    /** layout option key: optimize layout for user interaction (boolean). */
    public static final String INTERACTIVE_ID = "de.cau.cs.kieler.interactive";
    /** interaction property. */
    public static final IProperty<Boolean> INTERACTIVE = new Property<Boolean>(INTERACTIVE_ID, false);
    
    /** layout option key: spacing of edge labels to edges (float). */
    public static final String LABEL_SPACING_ID = "de.cau.cs.kieler.labelSpacing";
    /** label spacing property. */
    public static final IProperty<Float> LABEL_SPACING = new Property<Float>(LABEL_SPACING_ID, -1.0f);

    /** layout option key: layout hierarchy (boolean). */
    public static final String LAYOUT_HIERARCHY_ID
            = "de.cau.cs.kieler.layoutHierarchy";
    /** layout hierarchy property. */
    public static final IProperty<Boolean> LAYOUT_HIERARCHY = new Property<Boolean>(
            LAYOUT_HIERARCHY_ID, false);

    /** layout option key: constraints for port positions (enum). */
    public static final String PORT_CONSTRAINTS_ID = "de.cau.cs.kieler.portConstraints";
    /** port constraints property. */
    public static final IProperty<PortConstraints> PORT_CONSTRAINTS = new Property<PortConstraints>(
            PORT_CONSTRAINTS_ID, PortConstraints.UNDEFINED);
    
    /** layout option key: pre-defined position (object). */
    public static final String POSITION_ID = "de.cau.cs.kieler.position";
    /** position property. */
    public static final IProperty<KVector> POSITION = new Property<KVector>(POSITION_ID);
    
    /** layout option key: priority of elements (integer). */
    public static final String PRIORITY_ID = "de.cau.cs.kieler.priority";
    /** priority property. */
    public static final IProperty<Integer> PRIORITY = new Property<Integer>(PRIORITY_ID);

    /** layout option key: randomization seed (integer). */
    public static final String RANDOM_SEED_ID = "de.cau.cs.kieler.randomSeed";    
    /** randomization seed property. */
    public static final IProperty<Integer> RANDOM_SEED = new Property<Integer>(RANDOM_SEED_ID);
    
    /** layout option key: minimal distance between elements (float). */
    public static final String SPACING_ID = "de.cau.cs.kieler.spacing";    
    /** overall object spacing property. */
    public static final IProperty<Float> SPACING = new Property<Float>(SPACING_ID, -1f, 0f);


    /**
     * Hide constructor to avoid instantiation.
     */
    private LayoutOptions() {
    }
    
}
