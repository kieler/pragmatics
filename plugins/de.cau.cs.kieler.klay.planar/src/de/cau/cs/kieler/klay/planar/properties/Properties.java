/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.planar.properties;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.elk.core.math.ElkPadding;
import org.eclipse.elk.core.math.KVector;
import org.eclipse.elk.core.options.CoreOptions;
import org.eclipse.elk.core.options.EdgeType;
import org.eclipse.elk.core.util.Pair;
import org.eclipse.elk.graph.properties.IProperty;
import org.eclipse.elk.graph.properties.Property;

import de.cau.cs.kieler.klay.planar.graph.PEdge;
import de.cau.cs.kieler.klay.planar.graph.PFace;
import de.cau.cs.kieler.klay.planar.graph.PGraphElement;
import de.cau.cs.kieler.klay.planar.graph.PNode;
import de.cau.cs.kieler.klay.planar.graph.PNode.NodeType;
import de.cau.cs.kieler.klay.planar.intermediate.FullAngleDummyEntry;
import de.cau.cs.kieler.klay.planar.intermediate.FullAngleDummyProcessor;
import de.cau.cs.kieler.klay.planar.intermediate.GridRepresentation;
import de.cau.cs.kieler.klay.planar.intermediate.RectShapeEdgeProperties;
import de.cau.cs.kieler.klay.planar.intermediate.SelfLoopDummyContainer;
import de.cau.cs.kieler.klay.planar.p1planar.PlanarityTestStrategy;
import de.cau.cs.kieler.klay.planar.p2ortho.OrthogonalRepresentation;
import de.cau.cs.kieler.klay.planar.p2ortho.OrthogonalRepresentation.OrthogonalAngle;
import de.cau.cs.kieler.klay.planar.p3compact.HighDegreeNodeStrategy;

/**
 * Container for property definitions.
 * 
 * @author pkl
 */
public final class Properties {

    /**
     * The original object from which a graph element was created.
     */
    public static final IProperty<Object> ORIGIN = new Property<Object>("origin");

    /** upper left corner of the graph's bounding box. */
    public static final IProperty<KVector> BB_UPLEFT = new Property<KVector>("boundingBox.upLeft");
    /** lower right corner of the graph's bounding box. */
    public static final IProperty<KVector> BB_LOWRIGHT = new Property<KVector>(
            "boundingBox.lowRight");

    /** Property to convert between a graph element and its element in the dual graph. */
    public static final Property<PGraphElement> TODUALGRAPH = new Property<PGraphElement>(
            "de.cau.cs.kieler.klay.planar.properties.todualgraph");

    /** A property assigning a coordinates to a node. */
    public static final IProperty<Pair<Integer, Integer>> COORDINATES 
        = new Property<Pair<Integer, Integer>>(
            "de.cau.cs.kieler.klay.planar.properties.coordinates", new Pair<Integer, Integer>(0, 0));

    /** A property assigning the insertables edges of a graph. */
    public static final IProperty<List<PEdge>> INSERTABLE_EDGES = new Property<List<PEdge>>(
            "de.cau.cs.kieler.klay.planar.properties.insertable_edges", new ArrayList<PEdge>());

    /** Assigns the external face of graph. */
    public static final IProperty<PFace> EXTERNAL_FACE = new Property<PFace>(
            "de.cau.cs.kieler.klay.planar.properties.externalFace");

    // =========================== NODE PROPERTIES ========================================

    /** Node type. */
    public static final IProperty<NodeType> NODE_TYPE = new Property<NodeType>("nodeType",
            NodeType.NORMAL);

    /** Identifies a rectangular shape dummy; a node or an edge. */
    public static final IProperty<Boolean> RECT_SHAPE_DUMMY = new Property<Boolean>(
            "de.cau.cs.kieler.klay.planar.rectShapeDummy");

    /** Identifies a planar dummy node. */
    public static final IProperty<Boolean> PLANAR_DUMMY_NODE = new Property<Boolean>(
            "de.cau.cs.kieler.klay.planar.planardummynode");

    /** first x and y coordinate and last x and y coordinate of a high degree node. */
    public static final IProperty<List<Integer>> HIGH_DEGREE_POSITIONS = new Property<List<Integer>>(
            "de.cau.cs.kieler.klay.planar.highdegreepositions");

    /** Property to convert a node in the flow network to a node or face in the graph. */
    public static final Property<PGraphElement> NETWORK_TO_GRAPH = new Property<PGraphElement>(
            "de.cau.cs.kieler.klay.planar.properties.networktograph");

    // =========================== EDGE PROPERTIES ========================================

    /** Stores the dummies inserted by the {@link FullAngleDummyProcessor} at an full angle node. */
    public static final IProperty<FullAngleDummyEntry> FULL_ANGLE_DUMMY 
        = new Property<FullAngleDummyEntry>(
            "de.cau.cs.kieler.klay.planar.properties.fullangledummy");

    /** Edge type. */
    public static final IProperty<EdgeType> EDGE_TYPE = new Property<EdgeType>("edgeType",
            EdgeType.NONE);

    /** Relative length of an edge. Generally used at the grid drawing. */
    public static final IProperty<Integer> RELATIVE_LENGTH = new Property<Integer>(
            "de.cau.cs.kieler.klay.planar.properties.relativeEdgeLength");

    /** A property assigning the grid position of the start endpoint. */
    public static final IProperty<Pair<Integer, Integer>> START_POSITION 
        = new Property<Pair<Integer, Integer>>(
            "de.cau.cs.kieler.klay.planar.properties.startposition");

    /** A property assigning the grid position of the target endpoint. */
    public static final IProperty<Pair<Integer, Integer>> TARGET_POSITION 
        = new Property<Pair<Integer, Integer>>(
            "de.cau.cs.kieler.klay.planar.properties.targetposition");

    // =========================== FACE PROPERTIES ========================================
    /** Face-sides, contains the bounding edges with respect to their side. */
    public static final IProperty<List<PEdge>[]> FACE_SIDES 
        = new Property<List<PEdge>[]>(
            "de.cau.cs.kieler.klay.planar.faceSides");

    /** priority of nodes or edges. */
    public static final Property<Integer> PRIORITY = new Property<Integer>(CoreOptions.PRIORITY,
            1);

    // =========================== USER INTERFACE OPTIONS =================================

    /** property for planar testing algorithm. */
    public static final IProperty<PlanarityTestStrategy> PLANAR_TESTING_ALGORITHM 
        = new Property<PlanarityTestStrategy>(
            "de.cau.cs.kieler.klay.planar.planarityTest",
            PlanarityTestStrategy.BOYER_MYRVOLD_ALGORITHM);

    /** property that indicates a high degree node strategy. */
    public static final IProperty<HighDegreeNodeStrategy> HIGH_DEGREE_NODE_STRATEGY 
        = new Property<HighDegreeNodeStrategy>(
            "de.cau.cs.kieler.klay.planar.hdstrategy", HighDegreeNodeStrategy.QUOD);

    /** the aspect ratio for packing connected components. */
    public static final Property<Float> ASPECT_RATIO = new Property<Float>(
            CoreOptions.ASPECT_RATIO.getId(), 1.6f, 0.0f);

    /** spacing to the border of the drawing. */
    public static final IProperty<ElkPadding> PADDING = CoreOptions.PADDING;

    /** default value for object spacing. */
    public static final double DEF_SPACING = 40.0;

    /** minimal spacing between objects. */
    public static final Property<Double> SPACING = new Property<Double>(CoreOptions.SPACING_NODE_NODE,
            DEF_SPACING);

    /** A property that indicates the orthogonal representation of a graph. */
    public static final IProperty<OrthogonalRepresentation> ORTHO_REPRESENTATION 
        = new Property<OrthogonalRepresentation>(
            "de.cau.cs.kieler.klay.planar.orthogonal.representation",
            new OrthogonalRepresentation());

    /** A property that indicates the grid representation of a graph. */
    public static final IProperty<GridRepresentation> GRID_REPRESENTATION 
        = new Property<GridRepresentation>(
            "de.cau.cs.kieler.klay.planar.grid");

    /** Indicates a nodes during the algorithm as a bend point. */
    public static final IProperty<OrthogonalAngle> BENDPOINT 
        = new Property<OrthogonalRepresentation.OrthogonalAngle>(
            "de.cau.cs.kieler.klay.planar.bendpoint");

    /** Stores all properties that are added while the rectangular shape processor. */
    public static final IProperty<RectShapeEdgeProperties> RECT_SHAPE_PROPERTIES 
        = new Property<RectShapeEdgeProperties>(
            "de.cau.cs.kieler.klay.planar.rect_shape_properties");

    /** A cut edge is passed in both directions and hence it needs two edge properties. */
    public static final IProperty<Pair<RectShapeEdgeProperties, RectShapeEdgeProperties>> 
        RECT_SHAPE_CUTEDGE 
        = new Property<Pair<RectShapeEdgeProperties, RectShapeEdgeProperties>>(
            "de.cau.cs.kieler.klay.planar.rect_shape_cutedge");

    /**
     * Indicates whether is the external face is a dummy or the original face. True for dummy
     * rectangle and false for a original face.
     */
    public static final IProperty<Boolean> RECT_SHAPE_TRANS_EXTERNAL = new Property<Boolean>(
            "de.cau.cs.kieler.klay.planar.rect_shape_trans_external");

    /** An edge adjacent to the set face with a node as counter clockwise corner. */
    public static final IProperty<Pair<PNode, PEdge>> FACE_DIRECTION = new Property<Pair<PNode, PEdge>>(
            "de.cau.cs.kieler.klay.planar.face_direction");

    // ---------------------- High-degree node handling ----------------------------------------
    /**
     * Saves the involved dummy nodes in a list. This property is usually given to a node that with
     * a higher degree of 4 and the dummies are the added dummies to avoid nodes with degree higer
     * 4.
     */
    public static final IProperty<List<PNode>> EXPANSION_CYCLE = new Property<List<PNode>>(
            "de.cau.cs.kieler.klay.planar.expansion_cylce");

    /**
     * Denotes a node or an edge to be a expansion cylce dummy; a dummy for avoiding node degrees
     * higher 4. Usually dummies of that cycle are equipped with this property to identify them as
     * dummies. The stored node is the original high-degree node.
     */
    public static final IProperty<PNode> EXPANSION_CYCLE_ORIGIN = new Property<PNode>(
            "de.cau.cs.kieler.klay.planar.expansion_cylce_origin");

    /**
     * Identifies an expansion cycle face.
     */
    public static final IProperty<Boolean> EXPANSION_CYCLE_FACE = new Property<Boolean>(
            "de.cau.cs.kieler.klay.planar.expansion_cylce_face");

    /**
     * Dummies which are inserted by the SelfLoopDummyProcessor are equipped with that property to
     * identify them, so that they can be removed by the SelfLoopDummyRemover.
     */
    public static final IProperty<PNode> SELFLOOP_DUMMY = new Property<PNode>(
            "de.cau.cs.kieler.klay.planar.selfloop_dummy");

    /**
     * Nodes containing self-loops are connected with dummies to avoid such loops. The relation to
     * the original node is stored in the self loop dummy container.
     */
    public static final IProperty<SelfLoopDummyContainer> SELFLOOP_DUMMIES 
        = new Property<SelfLoopDummyContainer>("de.cau.cs.kieler.klay.planar.selfloop_dummies");

    /**
     * Hidden default constructor.
     */
    private Properties() {
    }

}
