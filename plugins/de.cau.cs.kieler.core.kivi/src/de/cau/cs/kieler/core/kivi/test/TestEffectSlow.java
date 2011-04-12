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

import de.cau.cs.kieler.core.kivi.AbstractEffect;

/**
 * Simple Test effect that prints onto the console and waits 50ms.
 * @author haf
 *
 */
public class TestEffectSlow extends AbstractEffect {

    long delay = 100;
    
    public TestEffectSlow(long delay) {
        this.delay = delay;
    }
    
    /**
     * {@inheritDoc}
     */
    public void execute() {
        System.out.print("S ");
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void undo() {
        System.out.print("uS ");
    }

}
