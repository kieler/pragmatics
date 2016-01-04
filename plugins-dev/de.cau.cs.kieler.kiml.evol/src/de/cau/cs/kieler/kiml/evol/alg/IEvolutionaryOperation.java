/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2010 by
 * + Kiel University
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

import java.util.Random;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.kiml.evol.genetic.Population;

/**
 * Interface for evolutionary operations that can be performed on a {@link Population}.
 *
 * @author bdu
 * @author msp
 * @kieler.design proposed by msp
 * @kieler.rating proposed yellow by msp
 */
public interface IEvolutionaryOperation {

    /**
     * Performs the evolutionary operation on the given population.
     * 
     * @param population
     *            a population
     * @param progressMonitor
     *            the progress monitor used to track execution progress
     */
    void process(Population population, IKielerProgressMonitor progressMonitor);
    
    /**
     * Sets the random number generator to the given instance. This must be done before any
     * population can be processed.
     * 
     * @param random a random number generator
     */
    void setRandom(Random random);
    
}
