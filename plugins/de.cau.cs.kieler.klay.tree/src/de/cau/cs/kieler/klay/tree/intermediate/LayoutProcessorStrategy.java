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
package de.cau.cs.kieler.klay.tree.intermediate;

import de.cau.cs.kieler.klay.tree.ILayoutProcessor;

/**
 * Definition of available intermediate layout processors for the tree layouter. This enumeration
 * also serves as a factory for intermediate layout processors.
 * 
 * @author sor
 * @author sgu
 */
public enum LayoutProcessorStrategy {

    /**
     * In this enumeration, intermediate layout processors are listed by the earliest slot in which
     * they can sensibly be used. The order in which they are listed is determined by the
     * dependencies on other processors.
     */

    // Before Phase 1
    /** Test processor for framework tests, can be removed after algorithm is live */
    TEST_PROCESSOR,

    // Before Phase 2
    /** Determine the root of a given graph */
    ROOT_PROC,
    /** Compute the fanout of each node in a given graph */
    FAN_PROC,
    
    // Before Phase 2 and 3
    /** Determine the local neighbors of each node in a given graph */
    NEIGHBORS_PROC,

    // Before Phase 3
    /** Determine the height of each level in the given graph */
    LEVEL_HEIGHT,

    // Before Phase 4
    /** Set the coordinates for each node in a given graph */
    COORDINATE_PROC;

    /**
     * Creates an instance of the layout processor described by this instance.
     * 
     * @return the layout processor.
     */
    public ILayoutProcessor create() {

        switch (this) {

        case TEST_PROCESSOR:
            return new TestProcessor();

        case ROOT_PROC:
            return new RootProcessor();

        case FAN_PROC:
            return new FanProcessor();

        case NEIGHBORS_PROC:
            return new NeighborsProcessor();

        case LEVEL_HEIGHT:
            return new LevelHeightProcessor();

        case COORDINATE_PROC:
            return new CoordianteProcessor();

        default:
            throw new IllegalArgumentException(
                    "No implementation is available for the layout processor " + this.toString());
        }
    }
}
