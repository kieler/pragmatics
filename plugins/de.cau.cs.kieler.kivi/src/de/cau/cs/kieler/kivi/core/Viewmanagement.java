/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2008 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kivi.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.InvalidRegistryObjectException;
import org.eclipse.core.runtime.Platform;

import de.cau.cs.kieler.kivi.examples.SelectionTrigger;
import de.cau.cs.kieler.kivi.examples.TestCombination;

/**
 * Core controller for the view management.
 * 
 * @author mmu
 * 
 */
public class Viewmanagement {

    private static final Viewmanagement INSTANCE = new Viewmanagement();

    private CombinationsWorker combinationsWorker = new CombinationsWorker();

    private EffectsWorker effectsWorker = new EffectsWorker();

    private List<Class<?>> availableCombinations = new ArrayList<Class<?>>();

    private Map<ITrigger, List<ICombination>> combinationsByTrigger = new HashMap<ITrigger, List<ICombination>>();

    private boolean active = false;

    /**
     * Instantiate the singleton class.
     */
    public Viewmanagement() {
        combinationsWorker.start();
        effectsWorker.start();
        loadExtensionPoints();
    }

    /**
     * Get the singleton view management INSTANCE.
     * 
     * @return the INSTANCE
     */
    public static Viewmanagement getInstance() {
        return INSTANCE;
    }

    /**
     * Called on eclipse startup to do a short initialization.
     */
    public void initialize() {
        // TODO whatever initialization requires
        // check preferences, load default/selected combinations?

        // FIXME remove after testing
        new TestCombination().setActive(true);
    }

    /**
     * Activate or deactivate the entire view management.
     * 
     * @param a
     *            true if activating
     */
    public void setActive(final boolean a) {
        if (active && !a) {
            // deactivate triggers
            for (ITrigger t : combinationsByTrigger.keySet()) {
                t.setActive(false);
            }
            // undo combinations
            Set<ICombination> cs = new HashSet<ICombination>();
            for (List<ICombination> l : combinationsByTrigger.values()) {
                cs.addAll(l);
            }
            for (ICombination c : cs) {
                c.undo();
            }
        } else if (!active && a) {
            // activate triggers
            for (ITrigger t : combinationsByTrigger.keySet()) {
                t.setActive(true);
            }
        }
        active = a;
    }

    /**
     * Checks whether view management is active or not.
     * 
     * @return true if active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Register or unregister a combination when it is activated or deactivated
     * to enable or disable reception of trigger events.
     * 
     * @param combination
     *            the newly activated or deactivated combination
     * @param register
     *            true if registering, false if unregistering
     */
    public void registerCombination(final ICombination combination, final boolean register) {
        List<Class<?>> ts = combination.getTriggers();
        for (Class<?> t : ts) {
            if (register) {
                addCombination(t, combination);
            } else {
                removeCombination(t, combination);
            }
        }
    }

    /**
     * Inform the view management about an event received by the given trigger.
     * 
     * @param trigger
     *            the trigger receiving the event
     */
    public void trigger(final ITrigger trigger) {
        combinationsWorker.trigger(trigger);
    }

    /**
     * Tell the view management to execute an effect.
     * 
     * @param effect
     *            the effect to execute
     */
    public void executeEffect(final IEffect effect) {
        effectsWorker.enqueueEffect(effect);
    }

    /**
     * Tell the view management to execute a list of effects.
     * 
     * @param effects
     *            the effects to execute
     */
    public void executeEffects(final List<IEffect> effects) {
        effectsWorker.enqueueEffect(effects);
    }

    /**
     * Undo an effect.
     * 
     * @param effect
     *            the effect to be undone
     */
    public void undoEffect(final IEffect effect) {
        effectsWorker.undoEffect(effect);
    }

    /**
     * Undo a list of effects.
     * 
     * @param effects
     *            the effects to be undone
     */
    public void undoEffects(final List<IEffect> effects) {
        effectsWorker.undoEffects(effects);
    }

    /**
     * Distribute the event from the given trigger to all availableCombinations
     * waiting for it. Called from the availableCombinations worker thread.
     * 
     * @param trigger
     *            the trigger that received the event
     */
    protected void distributeTrigger(final ITrigger trigger) {
        List<ICombination> cs = getCombinations(trigger.getClass());
        for (ICombination c : cs) {
            c.trigger(trigger);
        }
    }

    /**
     * Load information from the extension points registering
     * availableCombinations etc.
     */
    private void loadExtensionPoints() {
        loadCombinations();
    }

    /**
     * Load information from the availableCombinations extension point.
     */
    private void loadCombinations() {
        IConfigurationElement[] elements = Platform.getExtensionRegistry()
                .getConfigurationElementsFor("de.cau.cs.kieler.kivi.combinations");
        for (IConfigurationElement element : elements) {
            try {
                Class<?> combination = Class.forName(element.getAttribute("class"));
                availableCombinations.add(combination);
            } catch (InvalidRegistryObjectException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    // TODO move these three methods elsewhere?

    /**
     * Retrieve List of availableCombinations that are active and listening to
     * the specified class of triggers.
     * 
     * @param trigger
     *            class of triggers
     * @return list of availableCombinations
     */
    private List<ICombination> getCombinations(final Class<?> trigger) {
        synchronized (combinationsByTrigger) {
            for (ITrigger t : combinationsByTrigger.keySet()) {
                if (trigger.isInstance(t)) {
                    List<ICombination> list = combinationsByTrigger.get(t);
                    if (list != null) {
                        return list;
                    }
                }
            }
        }
        return new ArrayList<ICombination>();
    }

    /**
     * Add a combination as a listener to a trigger class.
     * 
     * @param trigger
     *            the trigger class to listen to
     * @param combination
     *            the listening combination
     */
    private void addCombination(final Class<?> trigger, final ICombination combination) {
        synchronized (combinationsByTrigger) {
            List<ICombination> list = combinationsByTrigger.get(trigger);
            if (list == null) {
                list = new ArrayList<ICombination>();
                // create trigger
                try {
                    ITrigger t = (ITrigger) trigger.newInstance();
                    t.setActive(isActive());
                    combinationsByTrigger.put(t, list);
                } catch (InstantiationException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            list.add(combination);
        }
    }

    /**
     * Remove a combination as a listener to a trigger class.
     * 
     * @param trigger
     *            the trigger class to listen to
     * @param combination
     *            the listening combination
     */
    private void removeCombination(final Class<?> trigger, final ICombination combination) {
        synchronized (combinationsByTrigger) {
            for (ITrigger t : combinationsByTrigger.keySet()) {
                if (trigger.isInstance(t)) {
                    List<ICombination> list = combinationsByTrigger.get(t);
                    if (list != null) {
                        list.remove(combination);
                        // check if trigger is still required
                        if (list.size() == 0) {
                            combinationsByTrigger.remove(list);
                            t.setActive(false);
                        }
                    }
                }
            }
        }
    }
}
