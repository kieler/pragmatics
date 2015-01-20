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
package de.cau.cs.kieler.klay.layered.intermediate;

import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.intermediate.SwitchDecider.SweepDirection;
import de.cau.cs.kieler.klay.layered.properties.GreedyType;

public class SwitchDeciderFactory {

    private final GreedyType greedyType;

    public SwitchDeciderFactory(final GreedyType greedyType) {
        this.greedyType = greedyType;
    }

    public SwitchDecider getNewOneSidedSwitchDecider(final int freeLayerIndex,
            final LNode[][] currentNodeOrder, final SweepDirection forward)
            throws SwitchDeciderException {
        switch (greedyType) {
        case ONE_SIDED_COUNTER:
            return new CounterOneSidedSwitchDecider(freeLayerIndex, currentNodeOrder, forward);
        }
        throw new UnsupportedOperationException("Not implemented yet");
    }

}
