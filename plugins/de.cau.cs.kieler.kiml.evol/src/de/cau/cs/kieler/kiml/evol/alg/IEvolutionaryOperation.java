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
/**
 *
 */
package de.cau.cs.kieler.kiml.evol.alg;

import de.cau.cs.kieler.kiml.evol.genetic.Population;

/**
 * Interface for evolutionary operations that can be performed on a
 * {@link Population}.
 *
 * @author bdu
 *
 */
public interface IEvolutionaryOperation {

    /**
     * Performs the evolutionary operation on the given population.
     *
     * @param population
     *            a population
     */
    void process(Population population);
}
