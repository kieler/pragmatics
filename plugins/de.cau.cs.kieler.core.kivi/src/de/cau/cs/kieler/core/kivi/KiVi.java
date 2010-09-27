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
package de.cau.cs.kieler.core.kivi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.InvalidRegistryObjectException;
import org.eclipse.core.runtime.RegistryFactory;
import org.eclipse.jface.preference.IPreferenceStore;

import de.cau.cs.kieler.core.kivi.internal.CombinationsWorker;
import de.cau.cs.kieler.core.kivi.internal.EffectsWorker;

/**
 * Core controller for the view management.
 * 
 * @author mmu
 * 
 */
public class KiVi {

    private static final KiVi INSTANCE = new KiVi();

    /**
     * The property key holding the active value.
     */
    public static final String PROPERTY_ACTIVE = KiVi.class.getCanonicalName() + ".active";

    private CombinationsWorker combinationsWorker = new CombinationsWorker();

    private EffectsWorker effectsWorker = new EffectsWorker();

    private Map<Class<?>, ITriggerState> triggerStates = new HashMap<Class<?>, ITriggerState>();

    private List<Descriptor> availableCombinations = new ArrayList<Descriptor>();

    private List<Descriptor> availableEffects = new ArrayList<Descriptor>();

    private Map<ITrigger, List<ICombination>> combinationsByTrigger = new HashMap<ITrigger, List<ICombination>>();

    private boolean active = false;

    /**
     * Instantiate the singleton class.
     */
    public KiVi() {
        combinationsWorker.start();
        effectsWorker.start();
    }

    /**
     * Get the singleton view management instance.
     * 
     * @return the instance
     */
    public static KiVi getInstance() {
        return INSTANCE;
    }

    /**
     * Called on eclipse startup to do a short initialization.
     */
    public void initialize() {
        setActive(KiViPlugin.getDefault().getPreferenceStore().getBoolean(PROPERTY_ACTIVE));
        loadExtensionPoints();
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
            synchronized (combinationsByTrigger) {
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
            }
        } else if (!active && a) {
            // activate triggers
            synchronized (combinationsByTrigger) {
                for (ITrigger t : combinationsByTrigger.keySet()) {
                    t.setActive(true);
                }
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
     * Get the current trigger state of the given class.
     * 
     * @param clazz
     *            the class of trigger state to look for
     * @return the trigger state
     */
    public ITriggerState getTriggerState(final Class<?> clazz) {
        synchronized (triggerStates) {
            return triggerStates.get(clazz);
        }
    }

    /**
     * Update activity state for each combination after preference page submit.
     * 
     * TODO better method name
     */
    public void updateCombinationsByGUI() {
        List<ICombination> toActivate = new ArrayList<ICombination>();
        List<ICombination> toDeactivate = new ArrayList<ICombination>();
        // split up to avoid breaking the loops by modifying
        // combinationsByTrigger
        synchronized (combinationsByTrigger) {
            for (Descriptor d : availableCombinations) {
                if (d.isActive()) {
                    boolean found = false;
                    outer: for (List<ICombination> l : combinationsByTrigger.values()) {
                        for (ICombination c : l) {
                            if (d.getClazz().isInstance(c)) {
                                found = true;
                                break outer;
                            }
                        }
                    }
                    if (!found) {
                        try {
                            toActivate.add((ICombination) d.getClazz().newInstance());
                        } catch (InstantiationException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                } else {
                    for (List<ICombination> l : combinationsByTrigger.values()) {
                        for (ICombination c : l) {
                            if (d.getClazz().isInstance(c)) {
                                toDeactivate.add(c);
                            }
                        }
                    }
                }
            }
        }
        for (ICombination c : toDeactivate) {
            c.setActive(false);
        }
        for (ICombination c : toActivate) {
            c.setActive(true);
        }
    }

    /**
     * Get a list of all available combinations registered to the combinations extension point.
     * 
     * @return a copied list of combinations
     */
    public List<Descriptor> getAvailableCombinations() {
        return new ArrayList<Descriptor>(availableCombinations);
    }

    /**
     * Get a list of all available effects registered to the effects extension point.
     * 
     * @return a copied list of effects
     */
    public List<Descriptor> getAvailableEffects() {
        return new ArrayList<Descriptor>(availableEffects);
    }

    /**
     * Register or unregister a combination when it is activated or deactivated to enable or disable
     * reception of trigger events.
     * 
     * @param combination
     *            the newly activated or deactivated combination
     * @param register
     *            true if registering, false if unregistering
     */
    public void registerCombination(final ICombination combination, final boolean register) {
        Class<? extends ITriggerState>[] ts = combination.getTriggerStates();
        for (Class<? extends ITriggerState> t : ts) {
            if (register) {
                addCombination(t, combination);
            } else {
                removeCombination(t, combination);
            }
        }
        boolean anyActive = isCombinationClassActive(combination.getClass());
        for (Descriptor d : availableCombinations) {
            if (d.getClazz().equals(combination.getClass())) {
                d.setActive(anyActive);
                break;
            }
        }
    }

    // /**
    // * Change the trigger object used by the view management. Hack to allow KIML to create its
    // * layout listener itself at the first layout, use that new instance now instead.
    // *
    // * @param trigger the trigger to swap in
    // */
    // public void swapTrigger(final ITrigger trigger) {
    // synchronized (combinationsByTrigger) {
    // for (ITrigger t : combinationsByTrigger.keySet()) {
    // if (trigger.getClass().isInstance(t)) {
    // List<ICombination> list = combinationsByTrigger.remove(t);
    // combinationsByTrigger.put(trigger, list);
    // trigger.setActive(t.isActive());
    // return;
    // }
    // }
    // }
    // }

    /**
     * Inform the view management about an event contained in the given trigger state.
     * 
     * @param triggerState
     *            the triggerState created by the event
     */
    public void trigger(final ITriggerState triggerState) {
        combinationsWorker.trigger(triggerState);
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
     * Undo an effect.
     * 
     * @param effect
     *            the effect to be undone
     */
    public void undoEffect(final IEffect effect) {
        effectsWorker.undoEffect(effect);
    }

    /**
     * Distribute the event from the given trigger state to all combinations waiting for such a
     * trigger state class. Called from the combinations worker thread.
     * 
     * @param triggerState
     *            the triggerState that contains the event
     */
    public void distributeTriggerState(final ITriggerState triggerState) {
        synchronized (triggerStates) {
            ITriggerState previous = triggerStates.get(triggerState.getClass());
            if (previous != null) {
                triggerState.merge(previous);
            } else {
                System.err.println("no previous state found for " + triggerState.getClass());
                // FIXME error log
            }
            triggerStates.put(triggerState.getClass(), triggerState);
        }

        List<ICombination> cs = getCombinations(triggerState.getTriggerClass());
        for (ICombination c : cs) {
            List<IEffect> effects = c.trigger();
            for (IEffect effect : effects) {
                executeEffect(effect);
            }
        }
        triggerState.finish();
    }

    /**
     * Load information from the extension points registering combinations, effects etc.
     */
    private void loadExtensionPoints() {
        loadCombinations();
        // loadEffects();
    }

    /**
     * Load all information from the combinations extension point.
     */
    private void loadCombinations() {
        IConfigurationElement[] elements = RegistryFactory.getRegistry()
                .getConfigurationElementsFor("de.cau.cs.kieler.core.kivi.combinations");
        IPreferenceStore preferenceStore = KiViPlugin.getDefault().getPreferenceStore();
        for (IConfigurationElement element : elements) {
            try {
                Object o = element.createExecutableExtension("class");
                Descriptor descriptor;
                descriptor = new Descriptor(element.getAttribute("name"),
                        element.getAttribute("description"), o.getClass());
                availableCombinations.add(descriptor);
                if (preferenceStore
                        .getBoolean(descriptor.getClazz().getCanonicalName() + ".active")) {
                    ((ICombination) o).setActive(true);
                }
                CombinationParameter[] parameters = CombinationParameter
                        .getParameters(o.getClass());
                for (CombinationParameter parameter : parameters) {
                    parameter.initialize();
                }
            } catch (InvalidRegistryObjectException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (CoreException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    // TODO merge these two methods, or check if effects ep is needed at all
    /**
     * Load all information from the effects extension point.
     */
    private void loadEffects() {
        IConfigurationElement[] elements = RegistryFactory.getRegistry()
                .getConfigurationElementsFor("de.cau.cs.kieler.core.kivi.effects");
        for (IConfigurationElement element : elements) {
            try {
                Descriptor descriptor = new Descriptor(element.getAttribute("name"),
                        element.getAttribute("description"), element.createExecutableExtension(
                                "class").getClass());
                availableEffects.add(descriptor);
            } catch (InvalidRegistryObjectException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (CoreException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    /**
     * Retrieve List of availableCombinations that are active and listening to the specified class
     * of triggers.
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
     * Get the instance registered for the class if there is any.
     * 
     * @param clazz
     *            the combination class
     * @return the combination instance or null
     */
    public ICombination getCombinationInstance(final Class<?> clazz) {
        synchronized (combinationsByTrigger) {
            for (List<ICombination> list : combinationsByTrigger.values()) {
                for (ICombination combination : list) {
                    if (clazz.isInstance(combination)) {
                        return combination;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Check whether any combination of the given class is active.
     * 
     * @param clazz
     *            the combination class to look for
     * @return true if an active combination was found
     */
    private boolean isCombinationClassActive(final Class<?> clazz) {
        synchronized (combinationsByTrigger) {
            for (List<ICombination> l : combinationsByTrigger.values()) {
                for (ICombination combination : l) {
                    if (clazz.isInstance(combination)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Add a combination as a listener to a trigger state class.
     * 
     * @param triggerState
     *            the trigger state class to listen to
     * @param combination
     *            the listening combination
     */
    private void addCombination(final Class<? extends ITriggerState> clazz,
            final ICombination combination) {
        try {
            synchronized (combinationsByTrigger) {
                synchronized (triggerStates) {
                    ITriggerState triggerState = triggerStates.get(clazz);
                    if (triggerState == null) {
                        triggerState = clazz.newInstance();
                        triggerStates.put(clazz, triggerState);
                    }
                    for (Map.Entry<ITrigger, List<ICombination>> entry : combinationsByTrigger
                            .entrySet()) {
                        if (triggerState.getTriggerClass().isInstance(entry.getKey())) {
                            entry.getValue().add(combination);
                            return;
                        }
                    }
                    // trigger not found, add new list for new trigger
                    ITrigger trigger = triggerState.getTriggerClass().newInstance();
                    trigger.setActive(isActive());
                    List<ICombination> list = new ArrayList<ICombination>();
                    list.add(combination);
                    combinationsByTrigger.put(trigger, list);
                }
            }
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Remove a combination as a listener to a trigger state class.
     * 
     * @param clazz
     *            the trigger state class to listen to
     * @param combination
     *            the listening combination
     */
    private void removeCombination(final Class<? extends ITriggerState> clazz,
            final ICombination combination) {
        try {
            synchronized (combinationsByTrigger) {
                synchronized (triggerStates) {
                    ITriggerState triggerState = triggerStates.get(clazz);
                    if (triggerState == null) {
                        triggerState = clazz.newInstance();
                    }
                    Map.Entry<ITrigger, List<ICombination>> toRemove = null;
                    for (Map.Entry<ITrigger, List<ICombination>> entry : combinationsByTrigger
                            .entrySet()) {
                        if (triggerState.getTriggerClass().isInstance(entry.getKey())) {
                            entry.getValue().remove(combination);
                            if (entry.getValue().size() == 0) {
                                toRemove = entry;
                            }
                            break;
                        }
                    }
                    if (toRemove != null) {
                        combinationsByTrigger.remove(toRemove.getKey());
                        toRemove.getKey().setActive(false);
                    }
                }
            }
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
