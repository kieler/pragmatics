/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
/**
 *
 */
package de.cau.cs.kieler.kiml.evol.alg;

import java.util.Comparator;
import java.util.Random;

import de.cau.cs.kieler.kiml.evol.genetic.Genome;
import de.cau.cs.kieler.kiml.evol.genetic.Population;

/**
 * @author bdu
 */
public class EvaluationOperation implements IEvolutionaryOperation {

    /**
     * {@inheritDoc}
     */
    public final void process(final Population population) {
        // fitness is determined by the rating value.

        // FIXME what is this? implement me!
    }

    /**
     * {@inheritDoc}
     */
    public void setRandom(final Random random) {
        // TODO Auto-generated method stub
    }

    /**
     * Descending rating comparator.
     */
    public static final Comparator<Genome> DESCENDING_RATING_COMPARATOR = new Comparator<Genome>() {
                /**
                 * Small value for floating-point comparison.
                 */
                private static final double EPSILON = 1.0E-6;

                public int compare(final Genome ind0, final Genome ind1) {
                    Double rating0 = ind0.getProperty(USER_RATING);
                    Double rating1 = ind1.getProperty(USER_RATING);
                    double v0 = rating0 == null ? 0.0 : rating0;
                    double v1 = rating1 == null ? 0.0 : rating1;

                    if (Math.abs(v0 - v1) < EPSILON) {
                        return 0;
                    } else if (v0 < v1) {
                        return 1;
                    }
                    return -1;
                }
            };

}
