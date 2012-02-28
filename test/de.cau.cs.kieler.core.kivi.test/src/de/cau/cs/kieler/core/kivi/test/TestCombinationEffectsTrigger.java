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

/**
 * A combination that takes an effect as a trigger parameter. It will be called, whenever
 * such effect has been executed on the effects queue.
 * @author haf
 *
 */
public class TestCombinationEffectsTrigger extends AbstractCombination {

    public void execute(TestEffectA effect){
        schedule(new PrintEffect("saw A"));
    }
    
}
