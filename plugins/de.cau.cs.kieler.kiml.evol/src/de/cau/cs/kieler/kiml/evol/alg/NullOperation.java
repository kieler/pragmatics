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
 * Null Operation: leaves the given {@link Population} unaltered. Use this as
 * dummy if you don't want an operation to be done.
 * 
 * @author bdu
 * 
 */
public class NullOperation implements IEvolutionaryOperation {

    /**
     * {@inheritDoc} <strong>NOTE: This implementation does nothing.</strong>
     */
    public void process(final Population population) {
        // Intentionally left empty.
    }
}
