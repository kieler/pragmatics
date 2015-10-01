/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2015 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klay.layered.intermediate.compaction;

/**
 * Definition of strategies for one dimensional compaction.
 * 
 * @author dag
 */
public enum GraphCompactionStrategy {
    /** doesn't apply compaction. */
    NONE,
    /** compacts to the left. */
    LEFT,
    /** compacts to the right. */
    RIGHT,
    /** compacts left, locks {@link CNode}s that are not constrained, compacts right. */
    LEFT_RIGHT_CONSTRAINT_LOCKING,
    /** compacts left, locks {@link CNode}s based on their {@link CompactionLock}, compacts right.
     *  Yields better results for average edge length because {@link CNode}s are locked in the
     *  direction of fewer connections. */
    LEFT_RIGHT_CONNECTION_LOCKING;
}
