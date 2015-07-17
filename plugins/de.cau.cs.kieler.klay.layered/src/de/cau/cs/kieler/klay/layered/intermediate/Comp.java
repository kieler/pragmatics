/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2015 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.layered.intermediate;

/**
 * Class for tolerance affected double comparisons.
 * @author dag
 */
public final class Comp {
    static final double TOLERANCE = 0.0001;
    
    private Comp() {
    }

    static boolean eq(final double d1, final double d2) {
        return Math.abs(d1 - d2) <= TOLERANCE;
    }

    static boolean gt(final double d1, final double d2) {
        return d1 - d2 > TOLERANCE;
    }

    static boolean lt(final double d1, final double d2) {
        return d2 - d1 > TOLERANCE;
    }

    static boolean ge(final double d1, final double d2) {
        return d1 - d2 >= -TOLERANCE;
    }

    static boolean le(final double d1, final double d2) {
        return d2 - d1 >= -TOLERANCE;
    }
}
