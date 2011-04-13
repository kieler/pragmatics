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

import de.cau.cs.kieler.core.kivi.AbstractTrigger;
import de.cau.cs.kieler.core.kivi.AbstractTriggerState;
import de.cau.cs.kieler.core.kivi.ITrigger;
import de.cau.cs.kieler.core.kivi.ITriggerState;
import de.cau.cs.kieler.core.kivi.test.TestTriggerSpammer.SpamState;

/**
 * A simple Trigger for unit tests that runs in an own thread and triggers
 * every 1ms, i.e. it spams the system with quick events. Is synchronized on the effects queue.
 * @author haf
 *
 */
public class TestTriggerSpammerSynchronized extends AbstractTrigger implements Runnable{

    /**
     * {@inheritDoc}
     */
    @Override
    public void register() {
        Thread t = new Thread(this);
        t.start();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void unregister() {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    public void run() {
        while(true){
            try {
                Thread.sleep(1);
                // block until all effects corresponding to this trigger have been executed
                this.synchronizedTrigger(new SynchronizedSpamState());
                
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static class SynchronizedSpamState extends SpamState{

        /**
         * {@inheritDoc}
         */
        public Class<? extends ITrigger> getTriggerClass() {
            return TestTriggerSpammerSynchronized.class;
        }
    }

}
