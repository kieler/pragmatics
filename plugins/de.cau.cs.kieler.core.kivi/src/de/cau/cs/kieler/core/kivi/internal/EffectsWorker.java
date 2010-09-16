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

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import de.cau.cs.kieler.core.kivi.IEffect;

/**
 * Worker thread that handles the execution of effects.
 * 
 * @author mmu
 * 
 */
public class EffectsWorker extends Thread {

    private BlockingQueue<IEffect> effects = new LinkedBlockingQueue<IEffect>();

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                IEffect effect = effects.take();
                try {
                    execute(effect);
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
     * Enqueue a single effect for execution.
     * 
     * @param effect the effect to execute
     */
    public void enqueueEffect(final IEffect effect) {
        try {
            effects.put(effect);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    /**
     * Enqueue a list of effects for execution preserving the order.
     * 
     * @param es list of effects to execute
     */
    public void enqueueEffect(final List<IEffect> es) {
        for (IEffect e : es) {
            enqueueEffect(e);
        }
    }
    
    /**
     * Undo a single effect.
     * 
     * @param effect the effect to undo
     */
    public void undoEffect(final IEffect effect) {
        enqueueEffect(new UndoEffect(effect));
    }
    
    /**
     * Undo a list of effects preserving the order.
     * 
     * @param es list of effects to undo
     */
    public void undoEffects(final List<IEffect> es) {
        for (IEffect e : es) {
            undoEffect(e);
        }
    }

    /**
     * Execute an effect.
     * 
     * @param effect the effect to execute
     */
    private void execute(final IEffect effect) {
        // TODO merging etc
        effect.execute();
    }
}
