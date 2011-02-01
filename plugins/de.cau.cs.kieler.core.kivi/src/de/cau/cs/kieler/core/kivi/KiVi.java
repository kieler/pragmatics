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
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.InvalidRegistryObjectException;
import org.eclipse.core.runtime.RegistryFactory;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.statushandlers.StatusManager;

import de.cau.cs.kieler.core.KielerNotSupportedException;
import de.cau.cs.kieler.core.kivi.internal.CombinationsWorker;
import de.cau.cs.kieler.core.kivi.internal.EffectsWorker;
import de.cau.cs.kieler.core.kivi.internal.IEffectsListener;

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

    private List<CombinationDescriptor> availableCombinations = new ArrayList<CombinationDescriptor>();
    private Collection<ICombination> combinations = new ArrayList<ICombination>();

    private Map<ITrigger, Collection<ICombination>> combinationsByTrigger;
    // haf: here a mapping from Trigger*States* to Combinations is more important
    private Map<Class<? extends ITriggerState>, Collection<ICombination>> combinationsByTriggerStates;

    
    private boolean active = false;

    private boolean initialized = false;

    /**
     * Instantiate the singleton class.
     */
    public KiVi() {
        combinationsByTrigger = new HashMap<ITrigger, Collection<ICombination>>();
        combinationsByTriggerStates = new HashMap<Class<? extends ITriggerState>, Collection<ICombination>>();
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
     * Called on eclipse startup to do a short initialization. This is called either by an Eclipse
     * early startup registered call or by the KiViMenuContributionItem, when it gets first asked
     * for its menu items, depending on what happens first.
     * 
     * @author haf
     */
    public void initialize() {
        // initialize only once
        if (initialized) {
            return;
        } else {
            initialized = true;
            setActive(KiViPlugin.getDefault().getPreferenceStore().getBoolean(PROPERTY_ACTIVE));
            loadCombinations();
        }
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
                for (Collection<ICombination> l : combinationsByTrigger.values()) {
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
     */
    public void loadActiveStates() {
        List<ICombination> toActivate = new ArrayList<ICombination>();
        List<ICombination> toDeactivate = new ArrayList<ICombination>();
        // split up to avoid breaking the loops by modifying combinationsByTrigger
        synchronized (combinationsByTrigger) {
            for (CombinationDescriptor d : availableCombinations) {
                if (d.isActive()) {
                    boolean found = false;
                    outer: for (Collection<ICombination> l : combinationsByTrigger.values()) {
                        for (ICombination c : l) {
                            if (d.getClazz().isInstance(c)) {
                                found = true;
                                break outer;
                            }
                        }
                    }
                    if (!found) {
                        try {
                            toActivate.add(d.getClazz().newInstance());
                        } catch (InstantiationException e) {
                            error(e);
                        } catch (IllegalAccessException e) {
                            error(e);
                        }
                    }
                } else {
                    for (Collection<ICombination> l : combinationsByTrigger.values()) {
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
        
        printCombinations();
    }

    /**
     * Get a list of all available combinations registered to the combinations extension point.
     * 
     * @return a copied list of combinations
     */
    public List<CombinationDescriptor> getAvailableCombinations() {
        return new ArrayList<CombinationDescriptor>(availableCombinations);
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
        for (CombinationDescriptor d : availableCombinations) {
            if (d.getClazz().equals(combination.getClass())) {
                d.setActive(anyActive);
                break;
            }
        }
    }

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
            ITriggerState previous = triggerStates.get(triggerState.getKeyClass());
            if (previous != null) {
                triggerState.merge(previous);
                // } else {
                // error("no previous state found for " + triggerState.getKeyClass());
            }
            triggerStates.put(triggerState.getKeyClass(), triggerState);
        }
        if (triggerState instanceof AbstractTriggerState) {
            ((AbstractTriggerState) triggerState).setSequenceNumber();
        }

        // Collection<ICombination> cs = getCombinations(triggerState.getTriggerClass());
        Collection<ICombination> cs = getCombinationsByTriggerState(triggerState.getClass());
        for (ICombination c : cs) {

            try {
                List<IEffect> effects = c.trigger(triggerState);
                for (IEffect effect : effects) {
                    executeEffect(effect);
                }
            } catch (KielerNotSupportedException e) {
                error(c, triggerState, e);
            }
        }
        triggerState.finish();
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
                if (o instanceof ICombination) {
                    ICombination combination = (ICombination) o;
                    this.combinations.add(combination);
                    CombinationDescriptor descriptor = new CombinationDescriptor(
                            element.getAttribute("name"), element.getAttribute("description"),
                            combination.getClass());
                    availableCombinations.add(descriptor);
                    if ("true".equals(element.getAttribute("active"))) {
                        preferenceStore.setDefault(descriptor.getClazz().getCanonicalName()
                                + ".active", true);
                        descriptor.setDefaultActive(true);
                    }
                    if (preferenceStore.getBoolean(descriptor.getClazz().getCanonicalName()
                            + ".active")) {
                        combination.setActive(true);
                        descriptor.setActive(true);
                    }
                    CombinationParameter[] parameters = CombinationParameter.getParameters(combination
                            .getClass());
                    for (CombinationParameter parameter : parameters) {
                        parameter.initialize();
                    }
                }
            } catch (InvalidRegistryObjectException e) {
                error(e);
            } catch (CoreException e) {
                error(e);
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
    private Collection<ICombination> getCombinations(final Class<?> trigger) {
        synchronized (combinationsByTrigger) {
            for (ITrigger t : combinationsByTrigger.keySet()) {
                if (trigger.isInstance(t)) {
                    Collection<ICombination> list = combinationsByTrigger.get(t);
                    if (list != null) {
                        return list;
                    }
                }
            }
        }
        return new ArrayList<ICombination>();
    }

    /**
     * Retrieve List of availableCombinations that are active and listening to the specified class
     * of trigger states.
     * 
     * @author haf
     * @param trigger
     *            class of triggers
     * @param triggerState
     *            class of trigger state
     * @return list of availableCombinations
     */
    private Collection<ICombination> getCombinationsByTriggerState(
            final Class<? extends ITriggerState> triggerState) {
        synchronized (combinationsByTrigger) {
            // for (ITriggerState t : combinationsByTriggerStates.keySet()) {
            // if (triggerState.isInstance(t)) {
            Collection<ICombination> list = combinationsByTriggerStates.get(triggerState);
            if (list != null) {
                return list;
            }
            // }
            // }
        }
        return new ArrayList<ICombination>();
    }

    /**
     * Check whether any combination of the given class is active.
     * 
     * @param clazz
     *            the combination class to look for
     * @return true if an active combination was found
     */
    public boolean isCombinationClassActive(final Class<?> clazz) {
        synchronized (combinationsByTrigger) {
            for (Collection<ICombination> l : combinationsByTrigger.values()) {
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
            ITriggerState triggerState = null;
            ITrigger trigger = null;
            boolean oldList = true;
            synchronized (combinationsByTrigger) {
                synchronized (triggerStates) {
                    // haf: add an instance of the trigger state to our current trigger state cache
                    triggerState = triggerStates.get(clazz);
                    if (triggerState == null) {
                        triggerState = clazz.newInstance();
                        triggerStates.put(clazz, triggerState);
                    }
                }

                // haf: remember which combinations listen to a trigger
                // using multimap pattern here
                oldList = addToList(combinationsByTriggerStates, clazz, combination);
                if (!oldList) {
                    trigger = triggerState.getTriggerClass().newInstance();
                    addToList(combinationsByTrigger, trigger, combination);
                }

                // // first, see if there is already such map entry available and add it
                // for (Map.Entry<ITrigger, Collection<ICombination>> entry : combinationsByTrigger
                // .entrySet()) {
                // if (triggerState.getTriggerClass().isInstance(entry.getKey())) {
                // entry.getValue().add(combination);
                // return;
                // }
                // }
                // // trigger not found, add new list for new trigger
                // trigger = triggerState.getTriggerClass().newInstance();
                // Set<ICombination> set = new LinkedHashSet<ICombination>();
                // set.add(combination);
                // combinationsByTrigger.put(trigger, set);
            }
            // moved outside the synchronized to avoid deadlock by foreign trigger code
            if (!oldList) {
                // activate any trigger seen for the first time
                trigger.setActive(isActive());
            }
        } catch (InstantiationException e) {
            error(e);
        } catch (IllegalAccessException e) {
            error(e);
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
                    Map.Entry<ITrigger, Collection<ICombination>> toRemove = null;
                    for (Map.Entry<ITrigger, Collection<ICombination>> entry : combinationsByTrigger
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
                    // also remove combination from TriggerState map
                    Collection<ICombination> combs = combinationsByTriggerStates.get(clazz);
                    if(combs!=null && !combs.isEmpty()){
                        combs.remove(combination);
                    }
                }
            }
        } catch (InstantiationException e) {
            error(e);
        } catch (IllegalAccessException e) {
            error(e);
        }
    }

    /**
     * Add an item to a map, where the map uses the MultiMap pattern (see google.collections). Used
     * here to avoid using the google library. If the library is officially introduced to KIELER,
     * this could be changed.
     * 
     * @author haf
     * @param map
     * @param key
     * @param item
     * @returns initialized
     */
    private <S, T> boolean addToList(Map<S, Collection<T>> map, S key, T item) {
        // first see whether the entry already exists
        Collection<T> list = map.get(key);
        boolean initialized = true;
        if (list == null) {
            // if not, add a new entry
            list = new ArrayList<T>();
            map.put(key, list);
            initialized = false;
        }
        // now add the new item to the list
        list.add(item);
        return initialized;
    }

    /**
     * Log an error.
     * 
     * @param t
     *            the causing throwable
     */
    public static void error(final Throwable t) {
        StatusManager.getManager().handle(
                new Status(Status.ERROR, KiViPlugin.PLUGIN_ID, t.getLocalizedMessage(), t),
                StatusManager.LOG);
    }

    /**
     * Log an error in a Combination that was triggered by a specific Trigger.
     * 
     * @param responsibleCombination
     *            the Combination involved
     * @param trigger
     *            the Trigger that caused the execution of the combination
     * @param cause
     *            the original exception
     */
    public static void error(final ICombination responsibleCombination,
            final ITriggerState trigger, final Throwable cause) {
        String message = "Error in View Management Combination "
                + responsibleCombination.getClass().getName() + " triggered by "
                + trigger.getClass().getName() + " " + cause.getLocalizedMessage();
        StatusManager.getManager().handle(
                new Status(Status.ERROR, KiViPlugin.PLUGIN_ID, message, cause), StatusManager.LOG);
    }

    /**
     * Log an error.
     * 
     * @param m
     *            the error message
     */
    public static void error(final String m) {
        StatusManager.getManager().handle(new Status(Status.ERROR, KiViPlugin.PLUGIN_ID, m),
                StatusManager.LOG);

    }

    /**
     * Add an effects listener to the effects worker.
     * 
     * @param listener
     *            the listener to add
     */
    public void addEffectsListener(final IEffectsListener listener) {
        effectsWorker.addEffectsListener(listener);
    }

    /**
     * Remove an effects listener from the effects worker.
     * 
     * @param listener
     *            the listener to remove
     */
    public void removeEffectsListener(final IEffectsListener listener) {
        effectsWorker.removeEffectsListener(listener);
    }
    
    private void printCombinations(){
        for (ICombination combination : this.combinations) {
            System.out.println(combination.isActive() + " " + combination.getClass().getName());
        }
    }
}
