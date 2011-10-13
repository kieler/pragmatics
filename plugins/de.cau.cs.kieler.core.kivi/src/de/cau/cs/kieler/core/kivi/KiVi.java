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
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.InvalidRegistryObjectException;
import org.eclipse.core.runtime.RegistryFactory;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.statushandlers.StatusManager;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;

import de.cau.cs.kieler.core.kivi.internal.CombinationsWorker;
import de.cau.cs.kieler.core.kivi.internal.EffectsWorker;
import de.cau.cs.kieler.core.kivi.internal.IEffectsListener;
import de.cau.cs.kieler.core.kivi.triggers.EffectTrigger.EffectTriggerState;
import de.cau.cs.kieler.core.ui.UnsupportedPartException;

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

    /**
     * Worker Thread that distributes incoming TriggerStates.
     */
    private CombinationsWorker combinationsWorker = new CombinationsWorker();
    /**
     * Worker Thread that controls the execution of effects.
     */
    private EffectsWorker effectsWorker = new EffectsWorker();

    /**
     * Storage for all Trigger instances in form of a map to guarantee there's only one Trigger for
     * a Trigger class. Similar to singleton pattern.
     */
    private Map<Class<?>, ITrigger> triggers = new HashMap<Class<?>, ITrigger>();

    /**
     * Storage for all TriggerState instances in form of a map to guarantee there's only one
     * TriggerState for a TriggerState class. Similar to singleton pattern.
     */
    private Map<Class<?>, ITriggerState> triggerStates = new HashMap<Class<?>, ITriggerState>();

    /**
     * List of all available contributions registered via the extension point.
     */
    private List<CombinationDescriptor> availableCombinations = new ArrayList<CombinationDescriptor>();

    /**
     * List of all combinations that have been seen by KiVi, hence also combos that have been
     * programmatically used via the registerCombination method.
     */
    private Collection<ICombination> combinations = new HashSet<ICombination>();

    /**
     * A mapping of ITriggerState classes to active Combinations that listen to that TriggerStates.
     * Is updated when combinations are de-/activated.
     */
    private Multimap<Class<? extends ITriggerState>, ICombination> triggerStates2Combinations = 
        HashMultimap.create();

    /**
     * View Management can be disabled at a whole.
     */
    private boolean active = false;

    /**
     * KiVi should only be initialized once.
     */
    private boolean initialized = false;

    /**
     * debug flag to get detailed kivi internal outputs.
     */
    private boolean debug = false;

    /**
     * Whether the View Management is currently in debug mode.
     * 
     * @return the debug mode status
     */
    public boolean isDebug() {
        return debug;
    }

    /**
     * Set the View Management debug mode status.
     * 
     * @param debug
     *            the debug mode status
     */
    public void setDebug(final boolean debug) {
        this.debug = debug;
    }

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
        synchronized (triggerStates2Combinations) {
            if (active && !a) {
                // deactivate all triggers
                for (ITrigger t : triggers.values()) {
                    t.setActive(false);
                }
                // undo combinations
                for (ICombination c : triggerStates2Combinations.values()) {
                    c.undo();
                }
            } else if (!active && a) {
                // activate only triggers that are used by an active combination
                // active combos are in the triggerStates2Combinations map
                for (Class<? extends ITriggerState> stateClass : triggerStates2Combinations
                        .keySet()) {
                    // search the corresponding trigger
                    ITriggerState state = triggerStates.get(stateClass);
                    ITrigger trigger = triggers.get(state);
                    // cmot: fix null pointer exception when trying to activate KIVi
                    if (trigger != null) {
                        trigger.setActive(true);
                    }
                }
            }
            active = a;
        } 
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
        synchronized (combinations) {
            for (CombinationDescriptor d : availableCombinations) {
                // iterate ALL combinations KiVi is aware of
                for (ICombination combination : combinations) {
                    if (d.getClazz().isInstance(combination)) {
                        combination.setActive(d.isActive());
                    }
                }
            }

        }
        if (debug) {
            printCombinations();
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
     * Get the current size of the effects queue.
     * @return size of the effects queue
     */
    public int getEffectsQueueSize() {
        return effectsWorker.getQueueSize();
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

        Collection<ICombination> relevantCombos;
        // get a copy of the list to avoid race conditions and deadlock
        synchronized (triggerStates2Combinations) {
            relevantCombos = Lists.newArrayList(triggerStates2Combinations.get(triggerState
                    .getClass()));
        }
        if (debug && !(triggerState instanceof EffectTriggerState)) {
            System.out.println(triggerState);
        }
        for (ICombination combo : relevantCombos) {
            try {
                combo.handle(triggerState);
                List<IEffect> effects = combo.getEffects();
                for (IEffect effect : effects) {
                    executeEffect(effect);
                }
            } catch (UnsupportedPartException e) {
                error(combo, triggerState, e);
            }

        }
        triggerState.finish();
    }

    /**
     * Load all information from the combinations extension point.
     */
    private void loadCombinations() {
        // read extension point
        IConfigurationElement[] elements = RegistryFactory.getRegistry()
                .getConfigurationElementsFor("de.cau.cs.kieler.core.kivi.combinations");
        // get preferences
        IPreferenceStore preferenceStore = KiViPlugin.getDefault().getPreferenceStore();
        for (IConfigurationElement element : elements) {
            try {
                // instanciate new combination
                ICombination combination = (ICombination) element.createExecutableExtension("class");
                combinations.add(combination);
                // create descriptor for a combination
                CombinationDescriptor descriptor = new CombinationDescriptor(
                        element.getAttribute("name"), element.getAttribute("description"),
                        combination.getClass());
                availableCombinations.add(descriptor);
                // store the default activity value in preferences
                if ("true".equals(element.getAttribute("active"))) {
                    preferenceStore.setDefault(
                            descriptor.getClazz().getCanonicalName() + ".active", true);
                    descriptor.setDefaultActive(true);
                }
                // initially activate combination if is active
                if (preferenceStore
                        .getBoolean(descriptor.getClazz().getCanonicalName() + ".active")) {
                    combination.setActive(true);
                    descriptor.setActive(true);
                }
                CombinationParameter[] parameters = CombinationParameter.getParameters(combination
                        .getClass());
                for (CombinationParameter parameter : parameters) {
                    parameter.initialize();
                }
            } catch (InvalidRegistryObjectException e) {
                error(e);
            } catch (CoreException e) {
                error(e);
            } catch (ClassCastException e) {
                error(e);
            }
        }
    }

    /**
     * Check whether any combination of the given class is active.
     * 
     * @param clazz
     *            the combination class to look for
     * @return true if an active combination was found
     */
    public boolean isCombinationClassActive(final Class<?> clazz) {
        synchronized (triggerStates2Combinations) {
            for (ICombination combination : triggerStates2Combinations.values()) {
                if (clazz.isInstance(combination)) {
                    return true;
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
            synchronized (triggerStates2Combinations) {
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
                triggerStates2Combinations.put(clazz, combination);
                // a combination might have been registered from outside, however, remember it
                combinations.add(combination);

                trigger = triggers.get(triggerState.getTriggerClass());
                if (trigger == null) {
                    trigger = triggerState.getTriggerClass().newInstance();
                    triggers.put(triggerState.getTriggerClass(), trigger);
                }
            }
            // moved outside the synchronized to avoid deadlock by foreign trigger code
            if (!trigger.isActive()) {
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
        synchronized (triggerStates2Combinations) {
            // check here to avoid endless loop with setActive method ob AbstractCombination
            if (triggerStates2Combinations.containsEntry(clazz, combination)) {
                triggerStates2Combinations.remove(clazz, combination);
                combination.setActive(false);
            }
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

    private void printCombinations() {
        for (ICombination combination : this.combinations) {
            if (combination.isActive()) {
                System.out.println("active " + combination);
            } else {
                System.out.println(" off   " + combination);
            }
        }
    }
}
