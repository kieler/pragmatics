/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.planar.properties;

import java.util.ArrayList;
import java.util.List;

import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.kiml.options.EdgeType;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.klay.planar.graph.PEdge;
import de.cau.cs.kieler.klay.planar.graph.PFace;
import de.cau.cs.kieler.klay.planar.graph.PGraphElement;
import de.cau.cs.kieler.klay.planar.graph.PNode;
import de.cau.cs.kieler.klay.planar.graph.PNode.NodeType;
import de.cau.cs.kieler.klay.planar.intermediate.GridRepresentation;
import de.cau.cs.kieler.klay.planar.p1planar.PlanarityTestStrategy;
import de.cau.cs.kieler.klay.planar.p2ortho.OrthogonalRepresentation;
import de.cau.cs.kieler.klay.planar.p2ortho.OrthogonalRepresentation.OrthogonalAngle;

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
    public static final IProperty<Pair<Integer, Integer>> COORDINATES = new Property<Pair<Integer, Integer>>(
            "de.cau.cs.kieler.klay.planar.properties.coordinates", new Pair<Integer, Integer>(0, 0));

    /** A property assigning the insertables edges of a graph. */
    public static final IProperty<List<PEdge>> INSERTABLE_EDGES = new Property<List<PEdge>>(
            "de.cau.cs.kieler.klay.planar.properties.insertable_edges", new ArrayList<PEdge>());

    public static final IProperty<PFace> EXTERNAL_FACE = new Property<PFace>("externalFace");

    // =========================== NODE PROPERTIES ========================================

    /** Node type. */
    public static final IProperty<NodeType> NODE_TYPE = new Property<NodeType>("nodeType",
            NodeType.NORMAL);

    public static final IProperty<Boolean> RECT_SHAPE_DUMMY = new Property<Boolean>(
            "rectShapeDummy");

    public static final IProperty<Boolean> PLANAR_DUMMY_NODE = new Property<Boolean>(
            "de.cau.cs.kieler.klay.planar.planardummynode");

    // =========================== EDGE PROPERTIES ========================================

    /** Edge type. */
    public static final IProperty<EdgeType> EDGE_TYPE = new Property<EdgeType>("edgeType",
            EdgeType.NONE);

    /** Relative length of an edge.*/
    public static final IProperty<Integer> RELATIVE_LENGTH = new Property<Integer>(
            "relativeEdgeLength");

    // =========================== FACE PROPERTIES ========================================

    /** Face-sides, contains the bounding edges with respect to their side. */
    public static final IProperty<List<PEdge>[]> FACE_SIDES = new Property<List<PEdge>[]>(
            "faceSides");

    /** priority of nodes or edges. */
    public static final Property<Integer> PRIORITY = new Property<Integer>(LayoutOptions.PRIORITY,
            1);

    // =========================== USER INTERFACE OPTIONS =================================

    /** property for planar testing algorithm. */
    public static final IProperty<PlanarityTestStrategy> PLANAR_TESTING_ALGORITHM = new Property<PlanarityTestStrategy>(
            "de.cau.cs.kieler.klay.planar.planarityTest",
            PlanarityTestStrategy.BOYER_MYRVOLD_ALGORITHM);

    /** the aspect ratio for packing connected components. */
    public static final Property<Float> ASPECT_RATIO = new Property<Float>(
            LayoutOptions.ASPECT_RATIO, 1.6f, 0.0f);

    /** spacing to the border of the drawing. */
    public static final Property<Float> BORDER_SPACING = new Property<Float>(
            LayoutOptions.BORDER_SPACING, 20.0f, 0.0f);

    /** default value for object spacing. */
    public static final float DEF_SPACING = 40.0f;

    /** minimal spacing between objects. */
    public static final Property<Float> SPACING = new Property<Float>(LayoutOptions.SPACING,
            DEF_SPACING);

    /** A property that indicates the orthogonal representation of a graph. */
    public static final IProperty<OrthogonalRepresentation> ORTHO_REPRESENTATION = new Property<OrthogonalRepresentation>(
            "de.cau.cs.kieler.klay.planar.orthogonal.representation",
            new OrthogonalRepresentation());

    /** A property that indicates the grid representation of a graph. */
    public static final IProperty<GridRepresentation> GRID_REPRESENTATION = new Property<GridRepresentation>(
            "de.cau.cs.kieler.klay.planar.grid");

    /** Indicates a nodes during the algorithm as a bend point. */
    public static final IProperty<OrthogonalAngle> BENDPOINT = new Property<OrthogonalRepresentation.OrthogonalAngle>(
            "de.cau.cs.kieler.klay.planar.bendpoint");

    /** Next edge of an edge connected by the corner of the currentEdge. */
    public static final IProperty<PEdge> RECT_SHAPE_NEXT = new Property<PEdge>(
            "de.cau.cs.kieler.klay.planar.rect_shape_next");

    /** Corner is the next node of a edge while walking around the face edges. */
    public static final IProperty<PNode> RECT_SHAPE_CORNER = new Property<PNode>(
            "de.cau.cs.kieler.klay.planar.rect_shape_corner");

    /** Turn of an edge during the rectangular shape processor. */
    public static final IProperty<Integer> RECT_SHAPE_TURN = new Property<Integer>(
            "de.cau.cs.kieler.klay.planar.rect_shape_turn");

    /** Front edge of a edge. */
    public static final IProperty<PEdge> RECT_SHAPE_FRONT = new Property<PEdge>(
            "de.cau.cs.kieler.klay.planar.rect_shape_front");

    /** Face side index; 0 left, 1 top, 2 right, 3 bottom. */
    public static final IProperty<Integer> RECT_SHAPE_SIDE_INDEX = new Property<Integer>(
            "de.cau.cs.kieler.klay.planar.rect_shape_side_index");

    /** Path length to the front of a edge. */
    public static final IProperty<Integer> RECT_SHAPE_PATH_LENGTH = new Property<Integer>(
            "de.cau.cs.kieler.klay.planar.rect_shape_path_length");

    /** Indicates whether is the external face is a dummy or the original face. */
    public static final IProperty<Boolean> RECT_SHAPE_TRANS_EXTERNAL = new Property<Boolean>(
            "de.cau.cs.kieler.klay.planar.rect_shape_trans_external");

    /** A edge adjacent to the set face with a node as counter clockwise corner. */
    public static final IProperty<Pair<PNode, PEdge>> FACE_DIRECTION = new Property<Pair<PNode, PEdge>>(
            "de.cau.cs.kieler.klay.planar.face_direction");

    /**
     * Hidden default constructor.
     */
    private Properties() {
    }

}
