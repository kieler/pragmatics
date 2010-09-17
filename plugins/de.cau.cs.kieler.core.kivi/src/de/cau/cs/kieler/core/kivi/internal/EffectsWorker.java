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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import de.cau.cs.kieler.core.kivi.IEffect;

/**
 * Worker thread that handles the execution of effects.
 * 
 * @author mmu
 * 
 */
public class EffectsWorker extends Thread {

    private List<IEffect> effects = new ArrayList<IEffect>();

    @Override
    public void run() {
        IEffect effect = null;
        while (!isInterrupted()) {
            try {
                synchronized (effects) {
                    while (effects.size() == 0) {
                        effects.wait();
                    }
                    effect = effects.remove(0);
                }
                try {
                    effect.execute();
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
        IEffect toAdd = effect;
        synchronized (effects) {
            if (effect.isMergeable()) {
                for (Iterator<IEffect> iterator = effects.iterator(); iterator.hasNext();) {
                    IEffect other = iterator.next();
                    IEffect current = toAdd.merge(other);
                    if (current != null) {
                        toAdd = current;
                        iterator.remove();
                    }
                }
            }
            effects.add(toAdd);
            effects.notify();
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
    
}
