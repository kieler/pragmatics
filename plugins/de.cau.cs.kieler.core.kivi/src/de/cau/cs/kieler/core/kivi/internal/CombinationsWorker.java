/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.core.kivi.internal;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import de.cau.cs.kieler.core.kivi.ITriggerState;
import de.cau.cs.kieler.core.kivi.KiVi;

/**
 * Worker thread that handles the evaluation and execution of all combinations.
 * 
 * @author mmu
 * 
 */
public class CombinationsWorker extends Thread {

    /**
     * Queue of triggers containing events that need to be handled.
     */
    private BlockingQueue<ITriggerState> triggerStates = new LinkedBlockingQueue<ITriggerState>();

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                ITriggerState triggerState = triggerStates.take();
                try {
                    KiVi.getInstance().distributeTriggerState(triggerState);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    
    /**
     * Insert a new trigger state into the queue.
     * 
     * @param triggerState the trigger state to be inserted
     */
    public void trigger(final ITriggerState triggerState) {
        try {
            triggerStates.put(triggerState);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
