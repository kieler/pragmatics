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
import de.cau.cs.kieler.core.kivi.test.TestTriggerSpammerSynchronized.SynchronizedSpamState;

/**
 * Test spamming of trigger states while using slow effects. This combination uses the
 * synchronized spam trigger, that will synchronize on the effects queue. So the effects
 * queue creates back pressure and blocks creation of new trigger states.
 * @author haf
 *
 */
public class TestCombinationSpammerSynchronized extends AbstractCombination {

    public void execute(SynchronizedSpamState state){
        schedule(new PrintEffect(" "+state.getCounter()+" "));
        // slow effect causes delay on the effects queue, the next spam state is 
        // synchronized to the execution of all corresponding effects on the queue and
        // hence not too many effects will queue up. This is safe.
        schedule(new TestEffectSlow(100));
    }
    
}
