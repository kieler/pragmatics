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
 * A combination that explicitly calls undoOldEffects.
 * @author haf
 */
public class TestCombinationUndo extends AbstractCombination {

    int counter = 0;

    /**
     * Execute method listening to some arbitrary trigger.
     * @param trigger
     */
    public void execute(BState trigger) {
        // putting undo here will undo directly the next step after something happened
        undoRecordedEffects();

        // only do something rarely
        if (counter == 0) {
            // putting undo here will undo only next time we run into this condition
            //undoOldEffects();
            
            // this is the effect that will be undone some later step
            schedule(new TestEffectA());
        }

        // repeat counting from 0 to 9
        counter = (counter + 1) % 10;
    }

}
