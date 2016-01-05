/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2016 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klay.layered.intermediate.compaction;

/**
 * Which strategy should be used by the
 * {@link de.cau.cs.kieler.klay.layered.compaction.oned.OneDimensionalCompactor} to calculate the
 * constraint graph.
 * 
 * @author uru
 * 
 * @see de.cau.cs.kieler.klay.layered.compaction.oned.algs.QuadraticConstraintCalculation
 * @see de.cau.cs.kieler.klay.layered.compaction.oned.algs.ScanlineConstraintCalculator
 */
public enum ConstraintCalculationStrategy {

    /**
     * Determine constraints by a pair-wise comparison of all elements.
     */
    QUADRATIC,

    /** 
     * Use a scanline technique.
     */
    SCANLINE
}
