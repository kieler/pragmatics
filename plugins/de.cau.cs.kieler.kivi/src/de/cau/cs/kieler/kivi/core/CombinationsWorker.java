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
package de.cau.cs.kieler.kivi.core;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

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
    private BlockingQueue<ITrigger> triggers = new LinkedBlockingQueue<ITrigger>();

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                ITrigger trigger = triggers.take();
                try {
                    Viewmanagement.getInstance().distributeTrigger(trigger);
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
     * Insert a new trigger into the queue.
     * 
     * @param trigger the trigger to be inserted
     */
    public void trigger(final ITrigger trigger) {
        try {
            triggers.put(trigger);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
