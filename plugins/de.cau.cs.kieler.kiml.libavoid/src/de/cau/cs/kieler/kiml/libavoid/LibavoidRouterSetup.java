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
package de.cau.cs.kieler.kiml.libavoid;

import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;

/**
 * Class contains options to configure the Libavoid router.
 * 
 * @author uru
 */
public final class LibavoidRouterSetup {

    private LibavoidRouterSetup() {
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
            "de.cau.cs.kieler.kiml.libavoid.shapeBufferDistance", 20f, 0f);

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

}
