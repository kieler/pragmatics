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
import de.cau.cs.kieler.klay.planar.graph.PGraphElement;
import de.cau.cs.kieler.klay.planar.graph.PNode.NodeType;
import de.cau.cs.kieler.klay.planar.p1planar.PlanarityTestStrategy;
import de.cau.cs.kieler.klay.planar.p2ortho.OrthogonalRepresentation;

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
    public static final Property<Pair<Integer, Integer>> COORDINATES = new Property<Pair<Integer, Integer>>(
            "de.cau.cs.kieler.klay.planar.properties.coordinates", new Pair<Integer, Integer>(0, 0));

    /** A property assigning the insertables edges of a graph. */
    public static final Property<List<PEdge>> INSERTABLE_EDGES = new Property<List<PEdge>>(
            "de.cau.cs.kieler.klay.planar.properties.insertable_edges", new ArrayList<PEdge>());

    /** Node type. */
    public static final IProperty<NodeType> NODE_TYPE = new Property<NodeType>("nodeType",
            NodeType.NORMAL);

    /** Edge type. */
    public static final IProperty<EdgeType> EDGE_TYPE = new Property<EdgeType>("edgeType",
            EdgeType.NONE);

    /** Facesides, contains the bounding edges with respect to their side. */
    @SuppressWarnings("rawtypes")
    public static final IProperty<ArrayList[]> FACESIDES = new Property<ArrayList[]>("faceSides");

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
    public static final float DEF_SPACING = 80.0f;
    /** minimal spacing between objects. */
    public static final Property<Float> SPACING = new Property<Float>(LayoutOptions.SPACING,
            DEF_SPACING);

    public static final IProperty<OrthogonalRepresentation> ORTHO_REPRESENTATION = new Property<OrthogonalRepresentation>(
            "de.cau.cs.kieler.klay.planar.orthogonal.representation",
            new OrthogonalRepresentation());

    /**
     * Hidden default constructor.
     */
    private Properties() {
    }

}
