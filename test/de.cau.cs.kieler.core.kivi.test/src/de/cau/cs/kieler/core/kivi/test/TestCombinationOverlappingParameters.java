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
package de.cau.cs.kieler.core.kivi.test;

import de.cau.cs.kieler.core.kivi.AbstractCombination;
import de.cau.cs.kieler.core.kivi.test.TestTriggerA.AState;
import de.cau.cs.kieler.core.kivi.test.TestTriggerB.BState;

/**
 * Simple test combination that has overlapping parameters. This should throw a runtime exception.
 * 
 * @author haf
 */
public class TestCombinationOverlappingParameters extends AbstractCombination {

    public void execute(final AState state) {
        schedule(new PrintEffect("first extecute"));
    }

    public void execute(final BState state, final AState state2) {
        schedule(new PrintEffect("second extecute"));
    }

}
