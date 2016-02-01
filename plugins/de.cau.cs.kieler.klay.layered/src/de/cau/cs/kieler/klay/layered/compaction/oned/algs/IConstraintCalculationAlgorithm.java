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
 * An algorithm that calculates separation constraints in one dimension that are induced by a
 * set of rectangles in the plane.
 * 
 * @author uru
 */
@FunctionalInterface
public interface IConstraintCalculationAlgorithm {
    /**
     * @param compactor
     *            the instance of the surrounding {@link OneDimensionalCompactor}.
     */
    void calculateConstraints(final OneDimensionalCompactor compactor);
}