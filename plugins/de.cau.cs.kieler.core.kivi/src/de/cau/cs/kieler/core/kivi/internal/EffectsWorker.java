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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.statushandlers.StatusManager;

import de.cau.cs.kieler.core.kivi.IEffect;
import de.cau.cs.kieler.core.kivi.KiViPlugin;
import de.cau.cs.kieler.core.kivi.UndoEffect;
import de.cau.cs.kieler.core.util.Maybe;

/**
 * Worker thread that handles the execution of effects.
 * 
 * @author mmu
 * @author ckru
 * @author msp
 */
public class EffectsWorker extends Thread {

    /** list of listeners to executed effects. */
    private List<IEffectsListener> effectsListeners = new ArrayList<IEffectsListener>();
    /** queue of effects that are executed by this worker thread. */
    private List<IEffect> effects = new ArrayList<IEffect>();
    /** map of monitor context markers, used to track effects with a progress bar. */
    private Map<IEffect, MonitorContext> monitorMap = new HashMap<IEffect, MonitorContext>();
    
    /**
     * Default constructor, sets thread name as effects.
     */
    public EffectsWorker() {
        super("Effects");
    }

    /**
     * {@inheritDoc}
     */
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
                    
                    MonitorContext context;
                    synchronized (monitorMap) {
                        context = monitorMap.get(effect);
                    }
                    if (context != null) {
                        // execute all marked effects with a progress monitor
                        executeWithMonitor(effect, context);
                        
                    } else {
                        // directly execute only the new effect
                        effect.execute();
                        
                        // notify effect listeners
                        synchronized (effectsListeners) {
                            for (IEffectsListener listener : effectsListeners) {
                                listener.executedEffect(effect);
                            }
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
     * Execute all effects that were marked with the given monitor context using a new
     * progress monitor dialog. Contexts are identified by their name. The dialog is closed
     * as soon as an effect with zero remaining effects in its context is executed.
     * These effects are executed in the UI thread and not in the effects worker thread.
     * 
     * @param startEffect the first effect of the monitor context
     * @param context the monitor context
     * @throws Throwable if something nasty happens in the UI thread
     */
    private void executeWithMonitor(final IEffect startEffect, final MonitorContext context)
            throws Throwable {
        final Maybe<Throwable> exception = new Maybe<Throwable>();
        Display.getDefault().syncExec(new Runnable() {
            public void run() {
                try {
                    PlatformUI.getWorkbench().getProgressService().run(
                            false, false, new IRunnableWithProgress() {
                        public void run(final IProgressMonitor monitor)
                                throws InvocationTargetException,
                                InterruptedException {
                            monitor.beginTask(context.name, context.remainingEffects + 1);
                            IEffect effect = startEffect;
                            boolean finished = false;
                            do {
                                
                                // execute the current effect
                                monitor.subTask("Processing " + effect.getName());
                                effect.execute();
                                
                                // notify effect listeners
                                synchronized (effectsListeners) {
                                    for (IEffectsListener listener : effectsListeners) {
                                        listener.executedEffect(effect);
                                    }
                                }
                                
                                // check the monitor context of the current effect
                                synchronized (monitorMap) {
                                    MonitorContext mc = monitorMap.remove(effect);
                                    if (mc != null && mc.name.equals(context.name)) {
                                        monitor.worked(1);
                                        if (mc.remainingEffects == 0) {
                                            finished = true;
                                        }
                                    }
                                }
                                if (!finished) {
                                    // retrieve the next effect from the queue
                                    synchronized (effects) {
                                        while (effects.size() == 0) {
                                            effects.wait();
                                        }
                                        effect = effects.remove(0);
                                    }
                                }
                            } while (!finished);
                            monitor.done();
                        }
                    });
                } catch (InvocationTargetException e) {
                    exception.set(e.getCause());
                } catch (InterruptedException e) {
                    // the user interface thread was interrupted -- ignore this
                }
            }
        });
        if (exception.get() != null) {
            throw exception.get();
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
     * @param effect the effect to execute
     */
    public void enqueueEffect(final IEffect effect) {
        IEffect toAdd = effect;
        synchronized (effects) {
            if (effect.isMergeable()) {
                Iterator<IEffect> iterator = effects.iterator();
                while (iterator.hasNext()) {
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
     * Enqueue a list of effects for execution.
     * 
     * @param effList a list of effects to execute
     */
    public void enqueueEffects(final List<IEffect> effList) {
        synchronized (effects) {
            for (IEffect effect : effList) {
                if (effect.isMergeable()) {
                    Iterator<IEffect> iterator = effects.iterator();
                    while (iterator.hasNext()) {
                        IEffect other = iterator.next();
                        IEffect current = effect.merge(other);
                        if (current != null) {
                            effect = current;
                            iterator.remove();
                        }
                    }
                }
                effects.add(effect);
            }
            effects.notify();
        }
    }

    /**
     * Enqueue a list of effects for execution, activating a progress monitor for the given
     * effects. Those effects are executed in the UI thread instead of the effects worker thread.
     * 
     * @param effList a list of effects to execute
     * @param name name to display on the progress monitor dialog
     */
    public void enqueueMonitoredEffects(final List<IEffect> effList, final String name) {
        synchronized (effects) {
            synchronized (monitorMap) {
                ListIterator<IEffect> inputIter = effList.listIterator();
                while (inputIter.hasNext()) {
                    IEffect effect = inputIter.next();
                    
                    // check whether the effect can be merged
                    if (effect.isMergeable()) {
                        Iterator<IEffect> mergeIter = effects.iterator();
                        while (mergeIter.hasNext()) {
                            IEffect other = mergeIter.next();
                            IEffect current = effect.merge(other);
                            if (current != null) {
                                effect = current;
                                mergeIter.remove();
                                monitorMap.remove(other);
                            }
                        }
                    }

                    // assign a mark for monitoring
                    monitorMap.put(effect, new MonitorContext(name,
                            effList.size() - inputIter.nextIndex()));
                    
                    // enqueue the effect
                    effects.add(effect);
                }
            }
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
     * A context object used to track effect execution with a progress monitor.
     */
    private static class MonitorContext {
        /** the name of the monitor context, displayed in a dialog. */
        private String name;
        /** the number of effects that remain to be executed. */
        private int remainingEffects;
        
        /**
         * Creates a monitor context.
         * 
         * @param name the name of the monitor context, displayed in a dialog
         * @param remainingEffects the number of effects that remain to be executed
         */
        MonitorContext(final String name, final int remainingEffects) {
            if (name == null) {
                // assign a global context if no context name was given
                this.name = "global";
            } else {
                this.name = name;
            }
            this.remainingEffects = remainingEffects;
        }
    }
    
}
