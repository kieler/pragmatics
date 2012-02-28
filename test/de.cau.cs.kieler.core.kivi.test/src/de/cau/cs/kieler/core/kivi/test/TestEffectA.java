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
public class TestEffectA extends AbstractEffect {

    // random ID
    int id = (int)(Math.random() * 10000);
    
    boolean done = false;
    boolean undone = false;
    
    /**
     * {@inheritDoc}
     */
    public void execute() {
        System.out.println("A("+id+")");
        if(done){
            throw new UnsupportedOperationException("TestEffectA: was executed already. Tried to execute same effect again!");
        }
        done = true;
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void undo() {
        System.out.print("uA("+id+")");
        if(undone){
            throw new UnsupportedOperationException("TestEffectA: was undone already. Tried to undo again!");
        }
        undone = true;
    }
    
    boolean getDone(){
        return done;
    }

    boolean getUnDone(){
        return undone;
    }
}
