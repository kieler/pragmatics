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
package de.cau.cs.kieler.klay.layered.compaction.oned.algs;

import de.cau.cs.kieler.klay.layered.compaction.oned.OneDimensionalCompactor;

/**
 * An algorithm that compacts a given set of rectangles in one dimension. This can, for example,
 * be a longest path-based algorithm that compacts everything as much as possible "to the left"
 * or a network simplex based compaction algorithm that leverages some underlying graph
 * structure to keep edges as short as possible.
 * 
 * @author uru
 */
@FunctionalInterface
public interface ICompactionAlgorithm {
    /**
     * @param compactor
     *            the instance of the surrounding {@link OneDimensionalCompactor}.
     */
    void compact(final OneDimensionalCompactor compactor);
}
