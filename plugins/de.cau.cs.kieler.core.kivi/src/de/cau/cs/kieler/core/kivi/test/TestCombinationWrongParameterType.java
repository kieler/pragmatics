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
 * Test combination with an execute method which has no expected type, i.e. only
 * ITriggerState or IEffect are allowed.
 * @author haf
 */
public class TestCombinationWrongParameterType extends AbstractCombination{

    public void execute(String notAllowedType){
        schedule(new PrintEffect("this should never be printed"));
    }
    
}
