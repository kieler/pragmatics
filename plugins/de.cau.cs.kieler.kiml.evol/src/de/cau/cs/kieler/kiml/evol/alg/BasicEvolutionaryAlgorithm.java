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

package de.cau.cs.kieler.kiml.evol.alg;

import java.util.Random;

/**
 * Implementation of an evolutionary algorithm with standard operations.
 *
 * @author bdu
 * @author msp
 */
public class BasicEvolutionaryAlgorithm extends AbstractEvolutionaryAlgorithm {

    /**
     * Constructs an evolutionary algorithm with standard operations.
     */
    public BasicEvolutionaryAlgorithm() {
        Random random = new Random();
        setCrossoverOperation(new CrossoverOperation());
        getCrossoverOperation().setRandom(random);
        setMutationOperation(new MutationOperation());
        getMutationOperation().setRandom(random);
        setSurvivalOperation(new SurvivalOperation());
        getSurvivalOperation().setRandom(random);
        setEvaluationOperation(new EvaluationOperation());
        getEvaluationOperation().setRandom(random);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean isDone() {
        // no stop criterion here -- algorithm shall be able run forever
        return false;
    }

}
