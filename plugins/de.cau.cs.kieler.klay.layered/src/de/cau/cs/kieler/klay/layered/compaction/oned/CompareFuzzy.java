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
package de.cau.cs.kieler.klay.layered.compaction.oned;

import com.google.common.math.DoubleMath;

/**
 * Internal Class for tolerance affected double comparisons.
 * 
 * @author dag
 */
public final class CompareFuzzy {
    static final double TOLERANCE = 0.0001;
    
    private CompareFuzzy() {
    }

    // SUPPRESS CHECKSTYLE NEXT 20 Javadoc
    public static boolean eq(final double d1, final double d2) {
        return DoubleMath.fuzzyEquals(d1, d2, TOLERANCE);
    }

    public static boolean gt(final double d1, final double d2) {
        return DoubleMath.fuzzyCompare(d1, d2, TOLERANCE) > 0;
    }

    public static boolean lt(final double d1, final double d2) {
        return DoubleMath.fuzzyCompare(d1, d2, TOLERANCE) < 0;
    }

    public static boolean ge(final double d1, final double d2) {
        return DoubleMath.fuzzyCompare(d1, d2, TOLERANCE) >= 0;
    }

    public static boolean le(final double d1, final double d2) {
        return DoubleMath.fuzzyCompare(d1, d2, TOLERANCE) <= 0;
    }
}
