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
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.statushandlers.StatusManager;

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

    private Map<ITrigger, List<ICombination>> combinationsByTrigger;;

    private boolean active = false;

    /**
     * Instantiate the singleton class.
     */
    public KiVi() {
        combinationsByTrigger = new HashMap<ITrigger, List<ICombination>>();
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
        loadCombinations();
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
     */
    public void loadActiveStates() {
        List<ICombination> toActivate = new ArrayList<ICombination>();
        List<ICombination> toDeactivate = new ArrayList<ICombination>();
        // split up to avoid breaking the loops by modifying combinationsByTrigger
        synchronized (combinationsByTrigger) {
            for (CombinationDescriptor d : availableCombinations) {
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
                            toActivate.add(d.getClazz().newInstance());
                        } catch (InstantiationException e) {
                            error(e);
                        } catch (IllegalAccessException e) {
                            error(e);
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

        List<ICombination> cs = getCombinations(triggerState.getTriggerClass());
        for (ICombination c : cs) {
            List<IEffect> effects = c.trigger(triggerState);
            for (IEffect effect : effects) {
                executeEffect(effect);
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
                    ICombination c = (ICombination) o;
                    CombinationDescriptor descriptor = new CombinationDescriptor(
                            element.getAttribute("name"), element.getAttribute("description"),
                            c.getClass());
                    availableCombinations.add(descriptor);
                    if ("true".equals(element.getAttribute("active"))) {
                        preferenceStore.setDefault(descriptor.getClazz().getCanonicalName()
                                + ".active", true);
                        descriptor.setDefaultActive(true);
                    }
                    if (preferenceStore.getBoolean(descriptor.getClazz().getCanonicalName()
                            + ".active")) {
                        c.setActive(true);
                        descriptor.setActive(true);
                    }
                    CombinationParameter[] parameters = CombinationParameter.getParameters(c
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
     * Check whether any combination of the given class is active.
     * 
     * @param clazz
     *            the combination class to look for
     * @return true if an active combination was found
     */
    public boolean isCombinationClassActive(final Class<?> clazz) {
        synchronized (combinationsByTrigger) {
            for (List<ICombination> l : combinationsByTrigger.values()) {
                for (ICombination combination : l) {
                    if (clazz.isInstance(combination)) {
                        return true;
                    }
                }
            }
        }
        System.out.println("returning false");
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
            synchronized (combinationsByTrigger) {
                synchronized (triggerStates) {
                    triggerState = triggerStates.get(clazz);
                    if (triggerState == null) {
                        triggerState = clazz.newInstance();
                        triggerStates.put(clazz, triggerState);
                    }
                }
                for (Map.Entry<ITrigger, List<ICombination>> entry : combinationsByTrigger
                        .entrySet()) {
                    if (triggerState.getTriggerClass().isInstance(entry.getKey())) {
                        entry.getValue().add(combination);
                        return;
                    }
                }
                // trigger not found, add new list for new trigger
                trigger = triggerState.getTriggerClass().newInstance();
                List<ICombination> list = new ArrayList<ICombination>();
                list.add(combination);
                combinationsByTrigger.put(trigger, list);
            }
            // moved outside the synchronized to avoid deadlock by foreign trigger code
            trigger.setActive(isActive());
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
            error(e);
        } catch (IllegalAccessException e) {
            error(e);
        }
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
}
