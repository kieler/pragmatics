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
public enum GreedySwitchType {
    /** TODO-alan. */
    ONE_SIDED_COUNTER(true, false, false),
    /** TODO-alan. */
    TWO_SIDED_COUNTER(false, false, false),
    /** TODO-alan. */
    ONE_SIDED_CROSSING_MATRIX(true, false, false),
    /** TODO-alan. */
    TWO_SIDED_CROSSING_MATRIX(false, false, false),
    /** TODO-alan. */
    ONE_SIDED_ON_DEMAND_CROSSING_MATRIX(true, false, false),
    /** TODO-alan. */
    TWO_SIDED_ON_DEMAND_CROSSING_MATRIX(false, false, false),
    /** TODO-alan. */
    ONE_SIDED_ON_DEMAND_CROSSING_MATRIX_BEST_OF_UP_OR_DOWN(true, true, false),
    /** TODO-alan. */
    TWO_SIDED_ON_DEMAND_CROSSING_MATRIX_BEST_OF_UP_OR_DOWN(false, true, false),
    /** TODO-alan. */
    ONE_SIDED_ON_DEMAND_CROSSING_MATRIX_BEST_OF_UP_OR_DOWN_ORTHOGONAL_HYPEREDGES(true, true, true),
    /** TODO-alan. */
    TWO_SIDED_ON_DEMAND_CROSSING_MATRIX_BEST_OF_UP_OR_DOWN_ORTHOGONAL_HYPEREDGES(false, true, true),
    /** TODO-alan */
    ONE_SIDED_ON_DEMAND_CROSSING_MATRIX_ORTHOGONAL_HYPEREDGES(true, false, true),
    /** TODO-alan. */
    OFF(false, false, false);

    private final boolean isOneSided;
    private final boolean useBestOfUpOrDown;
    private final boolean useHperedgeCounter;

    private GreedySwitchType(final boolean isOneSided, final boolean useBestOfUpOrDown,
            final boolean useOrthogonalCounter) {
        this.isOneSided = isOneSided;
        this.useBestOfUpOrDown = useBestOfUpOrDown;
        useHperedgeCounter = useOrthogonalCounter;
    }

    /**
     * TODO-alan.
     * 
     * @return true if only considers two layers.
     */
    public boolean isOneSided() {
        return isOneSided;
    }

    public boolean useBestOfUpOrDown() {
        return useBestOfUpOrDown;
    }

    public boolean useHyperedgeCounter() {
        return useHperedgeCounter;
    }

}
