/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.adaptagrams.properties;

import org.adaptagrams.ConnDirFlag;
import org.adaptagrams.Router;
import org.adaptagrams.RoutingOption;
import org.adaptagrams.RoutingParameter;

import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.IPropertyHolder;
import de.cau.cs.kieler.core.properties.Property;

/**
 * Class contains options to configure the Libavoid router.
 * 
 * @author uru
 */
public final class AvoidProperties {

    /**
     * Represents bitwise or if {@link ConnDirFlag#ConnDirDown} and {@link ConnDirFlag#ConnDirUp}.
     * 
     * Mainly for convenience, as xtend does not allow bitwise or.
     */
    public static final int CONN_DIR_UPDOWN = ConnDirFlag.ConnDirDown | ConnDirFlag.ConnDirUp;
    
    /**
     * Represents bitwise or if {@link ConnDirFlag#ConnDirLeft} and {@link ConnDirFlag#ConnDirRight}.
     * 
     * Mainly for convenience, as xtend does not allow bitwise or.
     */
    public static final int CONN_DIR_LEFTRIGHT = ConnDirFlag.ConnDirLeft | ConnDirFlag.ConnDirRight;

    private AvoidProperties() {
    }

    /*
     * General Properties
     */

    // CHECKSTYLEOFF javadoc
    // for documentation on the properties see the libavoid documentation.

    /*
     * Routing Penalties
     */
    public static final IProperty<Float> SEGMENT_PENALTY = new Property<Float>(
            "de.cau.cs.kieler.kiml.libavoid.segmentPenalty", 10f, 0f);

    public static final IProperty<Float> ANGLE_PENALTY = new Property<Float>(
            "de.cau.cs.kieler.kiml.libavoid.anglePenalty", 0f, 0f);

    /** Experimental. */
    public static final IProperty<Float> CROSSING_PENALTY = new Property<Float>(
            "de.cau.cs.kieler.kiml.libavoid.crossingPenalty", 0f, 0f);

    /** Experimental. */
    public static final IProperty<Float> CLUSTER_CROSSING_PENALTY = new Property<Float>(
            "de.cau.cs.kieler.kiml.libavoid.clusterCrossingPenalty", 4000f, 0f);

    /** Experimental. */
    public static final IProperty<Float> FIXED_SHARED_PATH_PENALTY = new Property<Float>(
            "de.cau.cs.kieler.kiml.libavoid.fixedSharedPathPenalty", 0f, 0f);

    /** Experimental. */
    public static final IProperty<Float> PORT_DIRECTION_PENALTY = new Property<Float>(
            "de.cau.cs.kieler.kiml.libavoid.portDirectionPenalty", 100f, 0f);

    public static final IProperty<Float> SHAPE_BUFFER_DISTANCE = new Property<Float>(
            "de.cau.cs.kieler.kiml.libavoid.shapeBufferDistance", 4.0f, 0f);

    public static final IProperty<Float> IDEAL_NUDGING_DISTANCE = new Property<Float>(
            "de.cau.cs.kieler.kiml.libavoid.idealNudgingDistance", 4.0f, 0f);

    /*
     * Routing Options
     */
    public static final IProperty<Boolean> NUDGE_ORTHOGONAL_SEGMENTS = new Property<Boolean>(
            "de.cau.cs.kieler.kiml.libavoid.nudgeOrthogonalSegmentsConnectedToShapes", false);

    public static final IProperty<Boolean> IMPROVE_HYPEREDGES = new Property<Boolean>(
            "de.cau.cs.kieler.kiml.libavoid.improveHyperedgeRoutesMovingJunctions", true);

    /** Experimental. */
    public static final IProperty<Boolean> PENALISE_ORTH_SHATE_PATHS = new Property<Boolean>(
            "de.cau.cs.kieler.kiml.libavoid.penaliseOrthogonalSharedPathsAtConnEnds", false);

    public static final IProperty<Boolean> NUDGE_ORTHOGONAL_COLINEAR_SEGMENTS =
            new Property<Boolean>(
                    "de.cau.cs.kieler.kiml.libavoid.nudgeOrthogonalTouchingColinearSegments", false);

    public static final IProperty<Boolean> NUDGE_PREPROCESSING = new Property<Boolean>(
            "de.cau.cs.kieler.kiml.libavoid.performUnifyingNudgingPreprocessingStep", true);

    public static final IProperty<Boolean> IMPROVE_HYPEREDGES_ADD_DELETE = new Property<Boolean>(
            "de.cau.cs.kieler.kiml.libavoid."
                    + "improveHyperedgeRoutesMovingAddingAndDeletingJunctions", true);

    
    /**
     * Transfers the layout options that are set on the passed {@code node} to the passed libavoid
     * {@code router} instance.
     * 
     * @param router
     *            the used libavoid router
     * @param node
     *            the node from which to copy the layout options
     */
    public static void transferOptions(final Router router, final IPropertyHolder propertyHolder) {

        /*
         * Penalties
         */
        float segmentPenalty = propertyHolder.getProperty(AvoidProperties.SEGMENT_PENALTY);
        router.setRoutingParameter(RoutingParameter.segmentPenalty, segmentPenalty);

        float anglePenalty = propertyHolder.getProperty(AvoidProperties.ANGLE_PENALTY);
        router.setRoutingParameter(RoutingParameter.anglePenalty, anglePenalty);

        float crossingPenalty = propertyHolder.getProperty(AvoidProperties.CROSSING_PENALTY);
        router.setRoutingParameter(RoutingParameter.crossingPenalty, crossingPenalty);

        float clusterCrossingPenalty =
                propertyHolder.getProperty(AvoidProperties.CLUSTER_CROSSING_PENALTY);
        router.setRoutingParameter(RoutingParameter.clusterCrossingPenalty, clusterCrossingPenalty);

        float fixedSharedPathPenalty =
                propertyHolder.getProperty(AvoidProperties.FIXED_SHARED_PATH_PENALTY);
        router.setRoutingParameter(RoutingParameter.fixedSharedPathPenalty, fixedSharedPathPenalty);

        float portDirectionPenalty =
                propertyHolder.getProperty(AvoidProperties.PORT_DIRECTION_PENALTY);
        router.setRoutingParameter(RoutingParameter.portDirectionPenalty, portDirectionPenalty);

        float shapeBufferDistance =
                propertyHolder.getProperty(AvoidProperties.SHAPE_BUFFER_DISTANCE);
        router.setRoutingParameter(RoutingParameter.shapeBufferDistance, shapeBufferDistance);

        float idealNudgingDistance =
                propertyHolder.getProperty(AvoidProperties.IDEAL_NUDGING_DISTANCE);
        router.setRoutingParameter(RoutingParameter.idealNudgingDistance, idealNudgingDistance);

        /*
         * Routing options
         */
        boolean nudgeOrthogonalSegmentsConnectedToShapes =
                propertyHolder.getProperty(AvoidProperties.NUDGE_ORTHOGONAL_SEGMENTS);
        router.setRoutingOption(RoutingOption.nudgeOrthogonalSegmentsConnectedToShapes,
                nudgeOrthogonalSegmentsConnectedToShapes);

        boolean improveHyperedgeRoutesMovingJunctions =
                propertyHolder.getProperty(AvoidProperties.IMPROVE_HYPEREDGES);
        router.setRoutingOption(RoutingOption.improveHyperedgeRoutesMovingJunctions,
                improveHyperedgeRoutesMovingJunctions);

        boolean penaliseOrthogonalSharedPathsAtConnEnds =
                propertyHolder.getProperty(AvoidProperties.PENALISE_ORTH_SHATE_PATHS);
        router.setRoutingOption(RoutingOption.penaliseOrthogonalSharedPathsAtConnEnds,
                penaliseOrthogonalSharedPathsAtConnEnds);

        boolean nudgeOrthogonalTouchingColinearSegments =
                propertyHolder.getProperty(AvoidProperties.NUDGE_ORTHOGONAL_COLINEAR_SEGMENTS);
        router.setRoutingOption(RoutingOption.nudgeOrthogonalTouchingColinearSegments,
                nudgeOrthogonalTouchingColinearSegments);

        boolean performUnifyingNudgingPreprocessingStep =
                propertyHolder.getProperty(AvoidProperties.NUDGE_PREPROCESSING);
        router.setRoutingOption(RoutingOption.performUnifyingNudgingPreprocessingStep,
                performUnifyingNudgingPreprocessingStep);

        boolean improveHyperedgeRoutesMovingAddingAndDeletingJunctions =
                propertyHolder.getProperty(AvoidProperties.IMPROVE_HYPEREDGES_ADD_DELETE);
        router.setRoutingOption(
                RoutingOption.improveHyperedgeRoutesMovingAddingAndDeletingJunctions,
                improveHyperedgeRoutesMovingAddingAndDeletingJunctions);

    }
}
