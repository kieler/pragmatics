/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2014 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.layered.properties;

/**
 * TODO-alan.
 * 
 * @author alan
 *
 */
public enum GreedyType {
    /** TODO-alan. */
    ONE_SIDED_COUNTER,
    /** TODO-alan. */
    TWO_SIDED_COUNTER,
    /** TODO-alan. */
    ONE_SIDED_CROSSING_MATRIX,
    /** TODO-alan. */
    TWO_SIDED_CROSSING_MATRIX,
    /** TODO-alan. */
    ONE_SIDED_ON_DEMAND_CROSSING_MATRIX,
    /** TODO-alan. */
    TWO_SIDED_ON_DEMAND_CROSSING_MATRIX;

    /**
     * TODO-alan.
     * 
     * @return true if only considers two layers.
     */
    public boolean isOneSided() {
        return this == ONE_SIDED_COUNTER || this == ONE_SIDED_CROSSING_MATRIX
                || this == ONE_SIDED_ON_DEMAND_CROSSING_MATRIX;
    }

}
