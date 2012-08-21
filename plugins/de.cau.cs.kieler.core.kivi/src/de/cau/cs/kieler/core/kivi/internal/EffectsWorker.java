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

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.IProgressService;
import org.eclipse.ui.statushandlers.StatusManager;

import de.cau.cs.kieler.core.kivi.ICombination;
import de.cau.cs.kieler.core.kivi.IEffect;
import de.cau.cs.kieler.core.kivi.KiViPlugin;
import de.cau.cs.kieler.core.kivi.UndoEffect;

/**
 * Worker thread that handles the execution of effects.
 * 
 * @author mmu
 * 
 */
public class EffectsWorker extends Thread {

    private List<IEffectsListener> effectsListeners = new ArrayList<IEffectsListener>();

    private List<IEffect> effects = new ArrayList<IEffect>();
    
    private IProgressService ps = PlatformUI.getWorkbench().getProgressService();
    
    private List<IEffect> monitoredEffects = new ArrayList<IEffect>();
    
    private ICombination nextMonitoredCombination = null;
    
    /**
     * Default constructor, sets thread name as effects.
     */
    public EffectsWorker() {
        super("Effects");
    }

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
                    if (!monitoredEffects.isEmpty()) {
                        final List<IEffect> monitoredEffectsCache = new ArrayList<IEffect>(monitoredEffects);
                        monitoredEffects.clear();
                        final ICombination comCache = this.nextMonitoredCombination;
                        this.nextMonitoredCombination = null;
                        Display.getDefault().asyncExec(new Runnable() {
                           
                            public void run() {
                                try {
                                    ps.busyCursorWhile(new IRunnableWithProgress() {
                                        
                                        public void run(IProgressMonitor monitor) throws InvocationTargetException,
                                                InterruptedException {
                                            if (comCache != null) {
                                                monitor.beginTask(comCache.getName(), monitoredEffectsCache.size());
                                            } else {
                                                monitor.beginTask(" ", monitoredEffectsCache.size());
                                            }
                                            for (IEffect effect: monitoredEffectsCache) {
                                                monitor.subTask("Processing " + effect.getName());
                                                effect.execute();
                                                effects.remove(effect);
                                                monitor.worked(1);
                                            }
                                            monitor.done();
                                        }
                                    });
                                } catch (InvocationTargetException e) {
                                    e.printStackTrace();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                
                            }
                        });
                    } else {
                        effect.execute();
                    }
                    
                    synchronized (effectsListeners) {
                        for (IEffectsListener listener : effectsListeners) {
                            listener.executedEffect(effect);
                        }
                    }
                    effect = null;
                } catch (Throwable e) {
                    IStatus status = new Status(IStatus.ERROR, KiViPlugin.PLUGIN_ID,
                            "View Management effects queue caught an exception from a "
                                    + effect.getClass().getName() + " effect: " + e.getMessage(), e);
                    StatusManager.getManager().handle(status);
                }
            } catch (InterruptedException e) {
                // got interrupted
            }
        }
    }
    
    /**
     * Get the current size of the queue.
     * 
     * @return size of the queue as an integer
     */
    public int getQueueSize() {
        synchronized (effects) {
            return effects.size();
        }
    }

    /**
     * Enqueue a single effect for execution.
     * 
     * @param effect
     *            the effect to execute
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
     * @param effect
     *            the effect to undo
     */
    public void undoEffect(final IEffect effect) {
        enqueueEffect(new UndoEffect(effect));
    }

    /**
     * Add an effects listener to the worker.
     * 
     * @param listener
     *            the listener to add
     */
    public void addEffectsListener(final IEffectsListener listener) {
        synchronized (effectsListeners) {
            effectsListeners.add(listener);
        }
    }

    /**
     * Remove an effects listener from the worker.
     * 
     * @param listener
     *            the listener to remove
     */
    public void removeEffectsListener(final IEffectsListener listener) {
        synchronized (effectsListeners) {
            effectsListeners.remove(listener);
        }
    }
    
    /**
     * Enqueue effects that should be executed with a progress Monitor.
     * @param effects the effects to be executed in a monitor.
     */
    public void addMonitoredEffects(List<IEffect> effects) { 
        this.monitoredEffects.addAll(effects);
    }

    /**
     * Set the next combination whose effects will be executed monitored.
     * Mainly used to display name in the progressMonitor.
     * @param com the new monitored combination.
     */
    public void setNextMonitoredCombination(ICombination com) {
        this.nextMonitoredCombination = com;
    }
    
}
