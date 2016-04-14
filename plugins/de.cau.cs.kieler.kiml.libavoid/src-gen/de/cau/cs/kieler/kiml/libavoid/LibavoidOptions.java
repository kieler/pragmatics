/**
 * Copyright (c) 2016 Kiel University and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    Kiel University - initial API and implementation
 */
package de.cau.cs.kieler.kiml.libavoid;

import de.cau.cs.kieler.kiml.libavoid.LibavoidLayoutProvider;
import java.util.EnumSet;
import org.eclipse.elk.core.data.ILayoutMetaDataProvider;
import org.eclipse.elk.core.data.LayoutAlgorithmData;
import org.eclipse.elk.core.data.LayoutCategoryData;
import org.eclipse.elk.core.data.LayoutOptionData;
import org.eclipse.elk.core.options.EdgeRouting;
import org.eclipse.elk.core.util.AlgorithmFactory;
import org.eclipse.elk.graph.properties.IProperty;
import org.eclipse.elk.graph.properties.Property;

@SuppressWarnings("all")
public class LibavoidOptions implements ILayoutMetaDataProvider {
  /**
   * Default value for {@link #SEGMENT_PENALTY}.
   */
  private final static float SEGMENT_PENALTY_DEFAULT = 10;
  
  /**
   * This penalty is applied for each segment in the connector path beyond the first.
   * This should always normally be set when doing orthogonal routing to prevent
   * step-like connector paths.
   */
  public final static IProperty<Float> SEGMENT_PENALTY = new Property<Float>(
            "de.cau.cs.kieler.kiml.libavoid.segmentPenalty",
            SEGMENT_PENALTY_DEFAULT,
            null,
            null);
  
  /**
   * Default value for {@link #ANGLE_PENALTY}.
   */
  private final static float ANGLE_PENALTY_DEFAULT = 0;
  
  /**
   * This penalty is applied in its full amount to tight acute bends in the connector path.
   * A smaller portion of the penalty is applied for slight bends, i.e., where the bend is close
   * to 180 degrees. This is useful for polyline routing where there is some evidence that tighter
   * corners are worse for readability, but that slight bends might not be so bad,
   * especially when smoothed by curves.
   */
  public final static IProperty<Float> ANGLE_PENALTY = new Property<Float>(
            "de.cau.cs.kieler.kiml.libavoid.anglePenalty",
            ANGLE_PENALTY_DEFAULT,
            null,
            null);
  
  /**
   * Default value for {@link #CROSSING_PENALTY}.
   */
  private final static float CROSSING_PENALTY_DEFAULT = 0;
  
  /**
   * This penalty is applied whenever a connector path crosses another connector path.
   * It takes shared paths into consideration and the penalty is only applied
   * if there is an actual crossing.
   */
  public final static IProperty<Float> CROSSING_PENALTY = new Property<Float>(
            "de.cau.cs.kieler.kiml.libavoid.crossingPenalty",
            CROSSING_PENALTY_DEFAULT,
            null,
            null);
  
  /**
   * Default value for {@link #CLUSTER_CROSSING_PENALTY}.
   */
  private final static float CLUSTER_CROSSING_PENALTY_DEFAULT = 4000;
  
  /**
   * This penalty is applied whenever a connector path crosses a cluster boundary.
   */
  public final static IProperty<Float> CLUSTER_CROSSING_PENALTY = new Property<Float>(
            "de.cau.cs.kieler.kiml.libavoid.clusterCrossingPenalty",
            CLUSTER_CROSSING_PENALTY_DEFAULT,
            null,
            null);
  
  /**
   * This penalty is applied whenever a connector path shares some segments with an immovable
   * portion of an existing connector route (such as the first or last segment of a connector).
   */
  public final static IProperty<Float> FIXED_SHARED_PATH_PENALTY = new Property<Float>(
            "de.cau.cs.kieler.kiml.libavoid.fixedSharedPathPenalty");
  
  /**
   * Default value for {@link #PORT_DIRECTION_PENALTY}.
   */
  private final static float PORT_DIRECTION_PENALTY_DEFAULT = 100;
  
  /**
   * This penalty is applied to port selection choice when the other end of the connector
   * being routed does not appear in any of the 90 degree visibility cones centered on the
   * visibility directions for the port.
   */
  public final static IProperty<Float> PORT_DIRECTION_PENALTY = new Property<Float>(
            "de.cau.cs.kieler.kiml.libavoid.portDirectionPenalty",
            PORT_DIRECTION_PENALTY_DEFAULT,
            null,
            null);
  
  /**
   * Default value for {@link #SHAPE_BUFFER_DISTANCE}.
   */
  private final static float SHAPE_BUFFER_DISTANCE_DEFAULT = 4;
  
  /**
   * This parameter defines the spacing distance that will be added to the sides of each
   * shape when determining obstacle sizes for routing. This controls how closely connectors
   * pass shapes, and can be used to prevent connectors overlapping with shape boundaries.
   * By default, this distance is set to a value of 4.
   */
  public final static IProperty<Float> SHAPE_BUFFER_DISTANCE = new Property<Float>(
            "de.cau.cs.kieler.kiml.libavoid.shapeBufferDistance",
            SHAPE_BUFFER_DISTANCE_DEFAULT,
            null,
            null);
  
  /**
   * Default value for {@link #IDEAL_NUDGING_DISTANCE}.
   */
  private final static float IDEAL_NUDGING_DISTANCE_DEFAULT = 4;
  
  /**
   * This parameter defines the spacing distance that will be used for nudging apart
   * overlapping corners and line segments of connectors. By default,
   * this distance is set to a value of 4.
   */
  public final static IProperty<Float> IDEAL_NUDGING_DISTANCE = new Property<Float>(
            "de.cau.cs.kieler.kiml.libavoid.idealNudgingDistance",
            IDEAL_NUDGING_DISTANCE_DEFAULT,
            null,
            null);
  
  /**
   * Default value for {@link #NUDGE_ORTHOGONAL_SEGMENTS_CONNECTED_TO_SHAPES}.
   */
  private final static boolean NUDGE_ORTHOGONAL_SEGMENTS_CONNECTED_TO_SHAPES_DEFAULT = false;
  
  /**
   * This option causes the final segments of connectors, which are attached to shapes,
   * to be nudged apart. Usually these segments are fixed, since they are considered to be
   * attached to ports. This option is not set by default.
   */
  public final static IProperty<Boolean> NUDGE_ORTHOGONAL_SEGMENTS_CONNECTED_TO_SHAPES = new Property<Boolean>(
            "de.cau.cs.kieler.kiml.libavoid.nudgeOrthogonalSegmentsConnectedToShapes",
            NUDGE_ORTHOGONAL_SEGMENTS_CONNECTED_TO_SHAPES_DEFAULT,
            null,
            null);
  
  /**
   * Default value for {@link #IMPROVE_HYPEREDGE_ROUTES_MOVING_JUNCTIONS}.
   */
  private final static boolean IMPROVE_HYPEREDGE_ROUTES_MOVING_JUNCTIONS_DEFAULT = true;
  
  /**
   * This option causes hyperedge routes to be locally improved fixing obviously bad paths.
   * As part of this process libavoid will effectively move junctions, setting new ideal positions
   * ( JunctionRef::recommendedPosition() ) for each junction.
   */
  public final static IProperty<Boolean> IMPROVE_HYPEREDGE_ROUTES_MOVING_JUNCTIONS = new Property<Boolean>(
            "de.cau.cs.kieler.kiml.libavoid.improveHyperedgeRoutesMovingJunctions",
            IMPROVE_HYPEREDGE_ROUTES_MOVING_JUNCTIONS_DEFAULT,
            null,
            null);
  
  /**
   * Default value for {@link #PENALISE_ORTHOGONAL_SHARED_PATHS_AT_CONN_ENDS}.
   */
  private final static boolean PENALISE_ORTHOGONAL_SHARED_PATHS_AT_CONN_ENDS_DEFAULT = false;
  
  /**
   * This option penalises and attempts to reroute orthogonal shared connector paths terminating
   * at a common junction or shape connection pin. When multiple connector paths enter or leave
   * the same side of a junction (or shape pin), the router will attempt to reroute these to
   * different sides of the junction or different shape pins. This option depends on the
   * fixedSharedPathPenalty penalty having been set.
   */
  public final static IProperty<Boolean> PENALISE_ORTHOGONAL_SHARED_PATHS_AT_CONN_ENDS = new Property<Boolean>(
            "de.cau.cs.kieler.kiml.libavoid.penaliseOrthogonalSharedPathsAtConnEnds",
            PENALISE_ORTHOGONAL_SHARED_PATHS_AT_CONN_ENDS_DEFAULT,
            null,
            null);
  
  /**
   * Default value for {@link #NUDGE_ORTHOGONAL_TOUCHING_COLINEAR_SEGMENTS}.
   */
  private final static boolean NUDGE_ORTHOGONAL_TOUCHING_COLINEAR_SEGMENTS_DEFAULT = false;
  
  /**
   * This option can be used to control whether colinear line segments that touch just at
   * their ends will be nudged apart. The overlap will usually be resolved in the other dimension,
   * so this is not usually required and is not set by default.
   */
  public final static IProperty<Boolean> NUDGE_ORTHOGONAL_TOUCHING_COLINEAR_SEGMENTS = new Property<Boolean>(
            "de.cau.cs.kieler.kiml.libavoid.nudgeOrthogonalTouchingColinearSegments",
            NUDGE_ORTHOGONAL_TOUCHING_COLINEAR_SEGMENTS_DEFAULT,
            null,
            null);
  
  /**
   * Default value for {@link #PERFORM_UNIFYING_NUDGING_PREPROCESSING_STEP}.
   */
  private final static boolean PERFORM_UNIFYING_NUDGING_PREPROCESSING_STEP_DEFAULT = true;
  
  /**
   * This option can be used to control whether the router performs a preprocessing step before
   * orthogonal nudging where is tries to unify segments and centre them in free space.
   * This generally results in better quality ordering and nudging.
   */
  public final static IProperty<Boolean> PERFORM_UNIFYING_NUDGING_PREPROCESSING_STEP = new Property<Boolean>(
            "de.cau.cs.kieler.kiml.libavoid.performUnifyingNudgingPreprocessingStep",
            PERFORM_UNIFYING_NUDGING_PREPROCESSING_STEP_DEFAULT,
            null,
            null);
  
  /**
   * Default value for {@link #IMPROVE_HYPEREDGE_ROUTES_MOVING_ADDING_AND_DELETING_JUNCTIONS}.
   */
  private final static boolean IMPROVE_HYPEREDGE_ROUTES_MOVING_ADDING_AND_DELETING_JUNCTIONS_DEFAULT = false;
  
  /**
   * This option causes hyperedge routes to be locally improved fixing obviously bad paths.
   */
  public final static IProperty<Boolean> IMPROVE_HYPEREDGE_ROUTES_MOVING_ADDING_AND_DELETING_JUNCTIONS = new Property<Boolean>(
            "de.cau.cs.kieler.kiml.libavoid.improveHyperedgeRoutesMovingAddingAndDeletingJunctions",
            IMPROVE_HYPEREDGE_ROUTES_MOVING_ADDING_AND_DELETING_JUNCTIONS_DEFAULT,
            null,
            null);
  
  /**
   * Default value for {@link #EDGE_ROUTING} with algorithm "Libavoid".
   */
  private final static EdgeRouting LIBAVOID_SUP_EDGE_ROUTING = EdgeRouting.ORTHOGONAL;
  
  public void apply(final ILayoutMetaDataProvider.Registry registry) {
    registry.register(new LayoutOptionData(
        "de.cau.cs.kieler.kiml.libavoid.segmentPenalty",
        "",
        "Segment Penalty",
        "This penalty is applied for each segment in the connector path beyond the first. This should always normally be set when doing orthogonal routing to prevent step-like connector paths.",
        SEGMENT_PENALTY_DEFAULT,
        null,
        null,
        LayoutOptionData.Type.FLOAT,
        float.class,
        EnumSet.of(LayoutOptionData.Target.PARENTS),
        LayoutOptionData.Visibility.VISIBLE
    ));
    registry.register(new LayoutOptionData(
        "de.cau.cs.kieler.kiml.libavoid.anglePenalty",
        "",
        "Angle Penalty",
        "This penalty is applied in its full amount to tight acute bends in the connector path. A smaller portion of the penalty is applied for slight bends, i.e., where the bend is close to 180 degrees. This is useful for polyline routing where there is some evidence that tighter corners are worse for readability, but that slight bends might not be so bad, especially when smoothed by curves.",
        ANGLE_PENALTY_DEFAULT,
        null,
        null,
        LayoutOptionData.Type.FLOAT,
        float.class,
        EnumSet.of(LayoutOptionData.Target.PARENTS),
        LayoutOptionData.Visibility.VISIBLE
    ));
    registry.register(new LayoutOptionData(
        "de.cau.cs.kieler.kiml.libavoid.crossingPenalty",
        "",
        "Crossing Penalty",
        "This penalty is applied whenever a connector path crosses another connector path. It takes shared paths into consideration and the penalty is only applied if there is an actual crossing.",
        CROSSING_PENALTY_DEFAULT,
        null,
        null,
        LayoutOptionData.Type.FLOAT,
        float.class,
        EnumSet.of(LayoutOptionData.Target.PARENTS),
        LayoutOptionData.Visibility.ADVANCED
    ));
    registry.register(new LayoutOptionData(
        "de.cau.cs.kieler.kiml.libavoid.clusterCrossingPenalty",
        "",
        "Cluster Crossing Penalty",
        "This penalty is applied whenever a connector path crosses a cluster boundary.",
        CLUSTER_CROSSING_PENALTY_DEFAULT,
        null,
        null,
        LayoutOptionData.Type.FLOAT,
        float.class,
        EnumSet.of(LayoutOptionData.Target.PARENTS),
        LayoutOptionData.Visibility.ADVANCED
    ));
    registry.register(new LayoutOptionData(
        "de.cau.cs.kieler.kiml.libavoid.fixedSharedPathPenalty",
        "",
        "Fixed Shared Path Penalty",
        "This penalty is applied whenever a connector path shares some segments with an immovable portion of an existing connector route (such as the first or last segment of a connector).",
        null,
        null,
        null,
        LayoutOptionData.Type.FLOAT,
        float.class,
        EnumSet.of(LayoutOptionData.Target.PARENTS),
        LayoutOptionData.Visibility.ADVANCED
    ));
    registry.register(new LayoutOptionData(
        "de.cau.cs.kieler.kiml.libavoid.portDirectionPenalty",
        "",
        "Port Direction Penalty",
        "This penalty is applied to port selection choice when the other end of the connector being routed does not appear in any of the 90 degree visibility cones centered on the visibility directions for the port.",
        PORT_DIRECTION_PENALTY_DEFAULT,
        null,
        null,
        LayoutOptionData.Type.FLOAT,
        float.class,
        EnumSet.of(LayoutOptionData.Target.PARENTS),
        LayoutOptionData.Visibility.ADVANCED
    ));
    registry.register(new LayoutOptionData(
        "de.cau.cs.kieler.kiml.libavoid.shapeBufferDistance",
        "",
        "Shape Buffer Distance",
        "This parameter defines the spacing distance that will be added to the sides of each shape when determining obstacle sizes for routing. This controls how closely connectors pass shapes, and can be used to prevent connectors overlapping with shape boundaries. By default, this distance is set to a value of 4.",
        SHAPE_BUFFER_DISTANCE_DEFAULT,
        null,
        null,
        LayoutOptionData.Type.FLOAT,
        float.class,
        EnumSet.of(LayoutOptionData.Target.PARENTS),
        LayoutOptionData.Visibility.VISIBLE
    ));
    registry.register(new LayoutOptionData(
        "de.cau.cs.kieler.kiml.libavoid.idealNudgingDistance",
        "",
        "Ideal Nudging Distance",
        "This parameter defines the spacing distance that will be used for nudging apart overlapping corners and line segments of connectors. By default, this distance is set to a value of 4.",
        IDEAL_NUDGING_DISTANCE_DEFAULT,
        null,
        null,
        LayoutOptionData.Type.FLOAT,
        float.class,
        EnumSet.of(LayoutOptionData.Target.PARENTS),
        LayoutOptionData.Visibility.VISIBLE
    ));
    registry.register(new LayoutOptionData(
        "de.cau.cs.kieler.kiml.libavoid.nudgeOrthogonalSegmentsConnectedToShapes",
        "",
        "Nudge Orthogonal Segments",
        "This option causes the final segments of connectors, which are attached to shapes, to be nudged apart. Usually these segments are fixed, since they are considered to be attached to ports. This option is not set by default.",
        NUDGE_ORTHOGONAL_SEGMENTS_CONNECTED_TO_SHAPES_DEFAULT,
        null,
        null,
        LayoutOptionData.Type.BOOLEAN,
        boolean.class,
        EnumSet.of(LayoutOptionData.Target.PARENTS),
        LayoutOptionData.Visibility.VISIBLE
    ));
    registry.register(new LayoutOptionData(
        "de.cau.cs.kieler.kiml.libavoid.improveHyperedgeRoutesMovingJunctions",
        "",
        "Improve Hyperedge Routes",
        "This option causes hyperedge routes to be locally improved fixing obviously bad paths. As part of this process libavoid will effectively move junctions, setting new ideal positions ( JunctionRef::recommendedPosition() ) for each junction.",
        IMPROVE_HYPEREDGE_ROUTES_MOVING_JUNCTIONS_DEFAULT,
        null,
        null,
        LayoutOptionData.Type.BOOLEAN,
        boolean.class,
        EnumSet.of(LayoutOptionData.Target.PARENTS),
        LayoutOptionData.Visibility.VISIBLE
    ));
    registry.register(new LayoutOptionData(
        "de.cau.cs.kieler.kiml.libavoid.penaliseOrthogonalSharedPathsAtConnEnds",
        "",
        "Shape Buffer Distance",
        "This option penalises and attempts to reroute orthogonal shared connector paths terminating at a common junction or shape connection pin. When multiple connector paths enter or leave the same side of a junction (or shape pin), the router will attempt to reroute these to different sides of the junction or different shape pins. This option depends on the fixedSharedPathPenalty penalty having been set.",
        PENALISE_ORTHOGONAL_SHARED_PATHS_AT_CONN_ENDS_DEFAULT,
        null,
        null,
        LayoutOptionData.Type.BOOLEAN,
        boolean.class,
        EnumSet.of(LayoutOptionData.Target.PARENTS),
        LayoutOptionData.Visibility.ADVANCED
    ));
    registry.register(new LayoutOptionData(
        "de.cau.cs.kieler.kiml.libavoid.nudgeOrthogonalTouchingColinearSegments",
        "",
        "Shape Buffer Distance",
        "This option can be used to control whether colinear line segments that touch just at their ends will be nudged apart. The overlap will usually be resolved in the other dimension, so this is not usually required and is not set by default.",
        NUDGE_ORTHOGONAL_TOUCHING_COLINEAR_SEGMENTS_DEFAULT,
        null,
        null,
        LayoutOptionData.Type.BOOLEAN,
        boolean.class,
        EnumSet.of(LayoutOptionData.Target.PARENTS),
        LayoutOptionData.Visibility.VISIBLE
    ));
    registry.register(new LayoutOptionData(
        "de.cau.cs.kieler.kiml.libavoid.performUnifyingNudgingPreprocessingStep",
        "",
        "Shape Buffer Distance",
        "This option can be used to control whether the router performs a preprocessing step before orthogonal nudging where is tries to unify segments and centre them in free space. This generally results in better quality ordering and nudging.",
        PERFORM_UNIFYING_NUDGING_PREPROCESSING_STEP_DEFAULT,
        null,
        null,
        LayoutOptionData.Type.BOOLEAN,
        boolean.class,
        EnumSet.of(LayoutOptionData.Target.PARENTS),
        LayoutOptionData.Visibility.ADVANCED
    ));
    registry.register(new LayoutOptionData(
        "de.cau.cs.kieler.kiml.libavoid.improveHyperedgeRoutesMovingAddingAndDeletingJunctions",
        "",
        "Improve Hyperedge Routes Add Delete",
        "This option causes hyperedge routes to be locally improved fixing obviously bad paths.",
        IMPROVE_HYPEREDGE_ROUTES_MOVING_ADDING_AND_DELETING_JUNCTIONS_DEFAULT,
        null,
        null,
        LayoutOptionData.Type.BOOLEAN,
        boolean.class,
        EnumSet.of(LayoutOptionData.Target.PARENTS),
        LayoutOptionData.Visibility.ADVANCED
    ));
    registry.register(new LayoutCategoryData(
        "de.cau.cs.kieler.kiml.libavoid.edge",
        "Edge Routing",
        "Only route the edges without touching the node&apos;s positions."
    ));
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
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.libavoid",
        "org.eclipse.elk.edgeRouting",
        LIBAVOID_SUP_EDGE_ROUTING
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.libavoid",
        "de.cau.cs.kieler.kiml.libavoid.segmentPenalty",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.libavoid",
        "de.cau.cs.kieler.kiml.libavoid.anglePenalty",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.libavoid",
        "de.cau.cs.kieler.kiml.libavoid.crossingPenalty",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.libavoid",
        "de.cau.cs.kieler.kiml.libavoid.clusterCrossingPenalty",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.libavoid",
        "de.cau.cs.kieler.kiml.libavoid.fixedSharedPathPenalty",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.libavoid",
        "de.cau.cs.kieler.kiml.libavoid.portDirectionPenalty",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.libavoid",
        "de.cau.cs.kieler.kiml.libavoid.shapeBufferDistance",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.libavoid",
        "de.cau.cs.kieler.kiml.libavoid.idealNudgingDistance",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.libavoid",
        "de.cau.cs.kieler.kiml.libavoid.nudgeOrthogonalSegmentsConnectedToShapes",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.libavoid",
        "de.cau.cs.kieler.kiml.libavoid.improveHyperedgeRoutesMovingJunctions",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.libavoid",
        "de.cau.cs.kieler.kiml.libavoid.penaliseOrthogonalSharedPathsAtConnEnds",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.libavoid",
        "de.cau.cs.kieler.kiml.libavoid.nudgeOrthogonalTouchingColinearSegments",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.libavoid",
        "de.cau.cs.kieler.kiml.libavoid.performUnifyingNudgingPreprocessingStep",
        null
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.libavoid",
        "de.cau.cs.kieler.kiml.libavoid.improveHyperedgeRoutesMovingAddingAndDeletingJunctions",
        null
    );
  }
}
