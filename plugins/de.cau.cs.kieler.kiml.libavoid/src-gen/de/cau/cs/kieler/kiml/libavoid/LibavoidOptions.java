package de.cau.cs.kieler.kiml.libavoid;

import de.cau.cs.kieler.kiml.libavoid.LibavoidLayoutProvider;
import de.cau.cs.kieler.kiml.libavoid.LibavoidMetaDataProvider;
import org.eclipse.elk.core.data.ILayoutMetaDataProvider;
import org.eclipse.elk.core.data.LayoutAlgorithmData;
import org.eclipse.elk.core.options.CoreOptions;
import org.eclipse.elk.core.options.Direction;
import org.eclipse.elk.core.options.EdgeRouting;
import org.eclipse.elk.core.util.AlgorithmFactory;
import org.eclipse.elk.graph.properties.IProperty;
import org.eclipse.elk.graph.properties.Property;

@SuppressWarnings("all")
public class LibavoidOptions implements ILayoutMetaDataProvider {
  /**
   * Property constant to access Direction from within the layout algorithm code.
   */
  public final static IProperty<Direction> DIRECTION = CoreOptions.DIRECTION;
  
  /**
   * Default value for {@link #EDGE_ROUTING} with algorithm "Libavoid".
   */
  private final static EdgeRouting EDGE_ROUTING_DEFAULT = EdgeRouting.ORTHOGONAL;
  
  /**
   * Property constant to access Edge Routing from within the layout algorithm code.
   */
  public final static IProperty<EdgeRouting> EDGE_ROUTING = new Property<EdgeRouting>(
                                CoreOptions.EDGE_ROUTING,
                                EDGE_ROUTING_DEFAULT);
  
  /**
   * Property constant to access Segment Penalty from within the layout algorithm code.
   */
  public final static IProperty<Float> SEGMENT_PENALTY = LibavoidMetaDataProvider.SEGMENT_PENALTY;
  
  /**
   * Property constant to access Angle Penalty from within the layout algorithm code.
   */
  public final static IProperty<Float> ANGLE_PENALTY = LibavoidMetaDataProvider.ANGLE_PENALTY;
  
  /**
   * Property constant to access Crossing Penalty from within the layout algorithm code.
   */
  public final static IProperty<Float> CROSSING_PENALTY = LibavoidMetaDataProvider.CROSSING_PENALTY;
  
  /**
   * Property constant to access Cluster Crossing Penalty from within the layout algorithm code.
   */
  public final static IProperty<Float> CLUSTER_CROSSING_PENALTY = LibavoidMetaDataProvider.CLUSTER_CROSSING_PENALTY;
  
  /**
   * Property constant to access Fixed Shared Path Penalty from within the layout algorithm code.
   */
  public final static IProperty<Float> FIXED_SHARED_PATH_PENALTY = LibavoidMetaDataProvider.FIXED_SHARED_PATH_PENALTY;
  
  /**
   * Property constant to access Port Direction Penalty from within the layout algorithm code.
   */
  public final static IProperty<Float> PORT_DIRECTION_PENALTY = LibavoidMetaDataProvider.PORT_DIRECTION_PENALTY;
  
  /**
   * Property constant to access Shape Buffer Distance from within the layout algorithm code.
   */
  public final static IProperty<Float> SHAPE_BUFFER_DISTANCE = LibavoidMetaDataProvider.SHAPE_BUFFER_DISTANCE;
  
  /**
   * Property constant to access Ideal Nudging Distance from within the layout algorithm code.
   */
  public final static IProperty<Float> IDEAL_NUDGING_DISTANCE = LibavoidMetaDataProvider.IDEAL_NUDGING_DISTANCE;
  
  /**
   * Property constant to access Nudge Orthogonal Segments from within the layout algorithm code.
   */
  public final static IProperty<Boolean> NUDGE_ORTHOGONAL_SEGMENTS_CONNECTED_TO_SHAPES = LibavoidMetaDataProvider.NUDGE_ORTHOGONAL_SEGMENTS_CONNECTED_TO_SHAPES;
  
  /**
   * Property constant to access Improve Hyperedge Routes from within the layout algorithm code.
   */
  public final static IProperty<Boolean> IMPROVE_HYPEREDGE_ROUTES_MOVING_JUNCTIONS = LibavoidMetaDataProvider.IMPROVE_HYPEREDGE_ROUTES_MOVING_JUNCTIONS;
  
  /**
   * Property constant to access Shape Buffer Distance from within the layout algorithm code.
   */
  public final static IProperty<Boolean> PENALISE_ORTHOGONAL_SHARED_PATHS_AT_CONN_ENDS = LibavoidMetaDataProvider.PENALISE_ORTHOGONAL_SHARED_PATHS_AT_CONN_ENDS;
  
  /**
   * Property constant to access Shape Buffer Distance from within the layout algorithm code.
   */
  public final static IProperty<Boolean> NUDGE_ORTHOGONAL_TOUCHING_COLINEAR_SEGMENTS = LibavoidMetaDataProvider.NUDGE_ORTHOGONAL_TOUCHING_COLINEAR_SEGMENTS;
  
  /**
   * Property constant to access Shape Buffer Distance from within the layout algorithm code.
   */
  public final static IProperty<Boolean> PERFORM_UNIFYING_NUDGING_PREPROCESSING_STEP = LibavoidMetaDataProvider.PERFORM_UNIFYING_NUDGING_PREPROCESSING_STEP;
  
  /**
   * Property constant to access Improve Hyperedge Routes Add Delete from within the layout algorithm code.
   */
  public final static IProperty<Boolean> IMPROVE_HYPEREDGE_ROUTES_MOVING_ADDING_AND_DELETING_JUNCTIONS = LibavoidMetaDataProvider.IMPROVE_HYPEREDGE_ROUTES_MOVING_ADDING_AND_DELETING_JUNCTIONS;
  
  public void apply(final ILayoutMetaDataProvider.Registry registry) {
    registry.register(new LayoutAlgorithmData(
        "de.cau.cs.kieler.kiml.libavoid",
        "Libavoid",
        "libavoid is a cross-platform C++ library providing fast, object-avoiding orthogonal and polyline connector routing for use in interactive diagram editors.",
        new AlgorithmFactory(LibavoidLayoutProvider.class, ""),
        "de.cau.cs.kieler.kiml.libavoid.edge",
        "Libavoid Connector Routing",
        null,
        null
    ));
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.libavoid",
        "org.eclipse.elk.direction",
        DIRECTION.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.libavoid",
        "org.eclipse.elk.edgeRouting",
        EDGE_ROUTING_DEFAULT
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.libavoid",
        "de.cau.cs.kieler.kiml.libavoid.segmentPenalty",
        SEGMENT_PENALTY.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.libavoid",
        "de.cau.cs.kieler.kiml.libavoid.anglePenalty",
        ANGLE_PENALTY.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.libavoid",
        "de.cau.cs.kieler.kiml.libavoid.crossingPenalty",
        CROSSING_PENALTY.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.libavoid",
        "de.cau.cs.kieler.kiml.libavoid.clusterCrossingPenalty",
        CLUSTER_CROSSING_PENALTY.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.libavoid",
        "de.cau.cs.kieler.kiml.libavoid.fixedSharedPathPenalty",
        FIXED_SHARED_PATH_PENALTY.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.libavoid",
        "de.cau.cs.kieler.kiml.libavoid.portDirectionPenalty",
        PORT_DIRECTION_PENALTY.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.libavoid",
        "de.cau.cs.kieler.kiml.libavoid.shapeBufferDistance",
        SHAPE_BUFFER_DISTANCE.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.libavoid",
        "de.cau.cs.kieler.kiml.libavoid.idealNudgingDistance",
        IDEAL_NUDGING_DISTANCE.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.libavoid",
        "de.cau.cs.kieler.kiml.libavoid.nudgeOrthogonalSegmentsConnectedToShapes",
        NUDGE_ORTHOGONAL_SEGMENTS_CONNECTED_TO_SHAPES.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.libavoid",
        "de.cau.cs.kieler.kiml.libavoid.improveHyperedgeRoutesMovingJunctions",
        IMPROVE_HYPEREDGE_ROUTES_MOVING_JUNCTIONS.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.libavoid",
        "de.cau.cs.kieler.kiml.libavoid.penaliseOrthogonalSharedPathsAtConnEnds",
        PENALISE_ORTHOGONAL_SHARED_PATHS_AT_CONN_ENDS.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.libavoid",
        "de.cau.cs.kieler.kiml.libavoid.nudgeOrthogonalTouchingColinearSegments",
        NUDGE_ORTHOGONAL_TOUCHING_COLINEAR_SEGMENTS.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.libavoid",
        "de.cau.cs.kieler.kiml.libavoid.performUnifyingNudgingPreprocessingStep",
        PERFORM_UNIFYING_NUDGING_PREPROCESSING_STEP.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.libavoid",
        "de.cau.cs.kieler.kiml.libavoid.improveHyperedgeRoutesMovingAddingAndDeletingJunctions",
        IMPROVE_HYPEREDGE_ROUTES_MOVING_ADDING_AND_DELETING_JUNCTIONS.getDefault()
    );
  }
}
