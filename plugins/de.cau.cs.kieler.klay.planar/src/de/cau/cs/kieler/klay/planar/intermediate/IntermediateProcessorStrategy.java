/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2010 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.planar.intermediate;

import de.cau.cs.kieler.klay.planar.ILayoutProcessor;

/**
 * Definition of available intermediate layout processors for the layered layouter. This enumeration
 * also serves as a factory for intermediate layout processors.
 * 
 * @author pkl
 */
public enum IntermediateProcessorStrategy {

    /*
     * In this enumeration, intermediate layout processors are listed by the earliest slot in which
     * they can sensibly be used. The order in which they are listed is determined by the
     * dependencies on other processors.
     */

    // Before Phase 1
    /** Inserts dummies to avoid self loop edges. */
    SELF_LOOP,
    // Before Phase 2

    // Before Phase 3
    /** The external face has to be determined for the Tamassia orthogonalization . */
    EXT_FACE,

    /** Creates dummy cages for full angles. */
    FULL_ANGLE,

    /** Removes dummy cages for full angles. */
    FULL_ANGLE_REMOVER,

    /**
     * This processor adds dummies if needed to ensure a maximal node degree of four by creation of
     * an expansion cylce.
     */
    EXPANSION_CYCLE,

    // Before Phase 4
    /**
     * Before performing the rectangular shape processor all bends have to dummy nodes, because
     * bends are treated as nodes in that processor.
     */
    BEND_DUMMY,

    /** Before performing the Tamassia compaction, rectangular shapes of the faces are required. */
    RECT_SHAPE_DUMMY,

    /** The sides of each face have to be calculated before the compaction can be computed. */
    FACE_SIDES,

    // After Phase 4
    /** Adds coordinates to the vertices and bends in the graph to be a grid drawing. */
    GRID_DRAWING,

    /** Removes rect shape dummies which are added by the {@link RectShapeDummyProcessor} processor. */
    RECT_SHAPE_DUMMY_REMOVER,

    /** Removes bend dummy nodes which are added by the {@link BendDummyProcessor} processor. */
    BEND_DUMMY_REMOVER,

    /** Removes the dummies inserted by the GIOTTO high degree processor. */
    GIOTTO_DUMMY_REMOVER,

    /** Removes the dummies inserted by the Quod high degree processor. */
    QUOD_DUMMY_REMOVER,

    /** Removes planar dummy nodes which are added by the {@link EdgeInsertionPlanarization} phase. */
    PLANAR_DUMMY_REMOVER,

    /** Removes all selfloop dummies. */
    SELFLOOP_DUMMY_REMOVER;

    /**
     * Returns the enumeration value related to the given ordinal.
     * 
     * @param i
     *            ordinal value
     * @return the related enumeration value
     */
    public static IntermediateProcessorStrategy valueOf(final int i) {
        return values()[i];
    }

    /**
     * Creates an instance of the layout processor described by this instance.
     * 
     * @return the layout processor.
     */
    public ILayoutProcessor create() {
        switch (this) {
        case EXT_FACE:
            return new ExternalFaceProcessor();
        case SELF_LOOP:
            return new SelfLoopDummyProcessor();
        case EXPANSION_CYCLE:
            return new ExpansionCycleProcessor();
        case FULL_ANGLE:
            return new FullAngleDummyProcessor();
        case FULL_ANGLE_REMOVER:
            return new FullAngleDummyRemover();
        case BEND_DUMMY:
            return new BendDummyProcessor();
        case RECT_SHAPE_DUMMY:
            return new RectShapeDummyProcessor();
        case FACE_SIDES:
            return new FaceSidesProcessor();
        case GRID_DRAWING:
            return new GridDrawingProcessor();
        case RECT_SHAPE_DUMMY_REMOVER:
            return new RectShapeDummyRemover();
        case BEND_DUMMY_REMOVER:
            return new BendDummyRemover();
        case GIOTTO_DUMMY_REMOVER:
            return new GiottoDummyRemover();
        case QUOD_DUMMY_REMOVER:
            return new QuodDummyRemover();
        case PLANAR_DUMMY_REMOVER:
            return new PlanarDummyRemover();
        case SELFLOOP_DUMMY_REMOVER:
            return new SelfLoopDummyRemover();
        default:
            return null;
        }
    }
}
