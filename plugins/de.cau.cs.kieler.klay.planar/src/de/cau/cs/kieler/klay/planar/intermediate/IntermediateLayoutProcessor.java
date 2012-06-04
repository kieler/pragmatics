/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2010 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.planar.intermediate;

import de.cau.cs.kieler.klay.planar.ILayoutProcessor;

/**
 * Definition of available intermediate layout processors for the topology-shape-metrics layouter.
 * This enumeration also serves as a factory for intermediate layout processors.
 * 
 * @author cds
 * @author pkl
 */
public enum IntermediateLayoutProcessor {

    /*
     * In this enumeration, intermediate layout processors are listed by the earliest slot in which
     * they can sensibly be used. The order in which they are listed is determined by the
     * dependencies on other processors.
     */

    // Before Phase 1

    // Before Phase 2

    // Before Phase 3

    // Before Phase 4
    /** before performing the compaction, rectangular shapes of the faces are required. */
    RECT_SHAPE,

    // After Phase 4
    /** Adds coordinates to the vertices and bends in the graph to be a grid drawing. */
    GRID_DRAWING,

    /** Removes dummy-nodes which are added in the planarization phase. */
    DUMMYNODE_REMOVING_PROCESSOR;

    /**
     * Returns the enumeration value related to the given ordinal.
     * 
     * @param i
     *            ordinal value
     * @return the related enumeration value
     */
    public static IntermediateLayoutProcessor valueOf(final int i) {
        return values()[i];
    }

    /**
     * Creates an instance of the layout processor described by this instance.
     * 
     * @return the layout processor.
     */
    public ILayoutProcessor create() {
        switch (this) {
        case DUMMYNODE_REMOVING_PROCESSOR:
            return new DummyNodeRemovingProcessor();
        case GRID_DRAWING:
            return new GridDrawingProcessor();
        case RECT_SHAPE:
            return new RectShapeProcessor();
        default:
            return null;
        }
    }
}
