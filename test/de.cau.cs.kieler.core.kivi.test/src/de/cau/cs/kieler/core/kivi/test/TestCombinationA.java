/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.core.kivi.test;

import de.cau.cs.kieler.core.kivi.AbstractCombination;
import de.cau.cs.kieler.core.kivi.test.TestTriggerA.AState;

/**
 * Simple test combination that uses some triggers and executes effects
 * @author haf
 *
 */
public class TestCombinationA extends AbstractCombination {

    public void execute(AState state){
        schedule(new PrintEffect(" A:"+state.getCounter()+" "));
        schedule(new TestEffectA());
    }
    
}
