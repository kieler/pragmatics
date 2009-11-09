/**
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2009 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 * 
 *****************************************************************************/
package de.cau.cs.kieler.ksbase.viewmanagement.combinations;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map.Entry;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.preference.IPreferenceStore;

import de.cau.cs.kieler.ksbase.viewmanagement.triggers.KSBasETrigger;
import de.cau.cs.kieler.viewmanagement.ACombination;
import de.cau.cs.kieler.viewmanagement.AEffect;
import de.cau.cs.kieler.viewmanagement.ATrigger;
import de.cau.cs.kieler.viewmanagement.RunLogic;
import de.cau.cs.kieler.viewmanagement.TriggerEventObject;

/**
 * The KSBasE combination used to execute a set of effects, selected by the user
 * via the preference pages.
 * 
 * @author Michael Matzen - mim AT informatik.uni-kiel.de
 * 
 */
public class KSBasECombination extends ACombination {

    /** List of effect names to execute. **/
    private static HashMap<Integer, LinkedList<String>> effects =
            new HashMap<Integer, LinkedList<String>>();
    /** Trigger object. **/
    private KSBasETrigger trigger;

    /** Target editPart. **/
    private EObject affectedObject;
    /** Additional parameter. **/
    private Object parameter;

    /**
     * Creates a new KSBasECombination.
     */
    public KSBasECombination() {
    }

    /**
     * Evaluates if triggerEventObject is valid.
     * 
     * @param triggerEvent
     *            The event to evaluate.
     * @return True if the eventObject is valid
     */
    @Override
    public boolean evaluate(final TriggerEventObject triggerEvent) {

        affectedObject = triggerEvent.getAffectedObject();
        parameter = triggerEvent.getParameters();

        return true;
    }

    /**
     * Executes the registered effects.
     */
    @Override
    public void execute() {

        for (int prio : KSBasECombination.effects.keySet()) {
            for (String effectName : KSBasECombination.effects.get(prio)) {
                AEffect effect = RunLogic.getEffect(effectName.toLowerCase(Locale.getDefault()));
                if (effect != null) { // Set effect target and parameter
                    effect.setTarget(getEditPart(affectedObject));
                    effect.setParameters(parameter); // Execute effect
                    effect.execute();
                }
            }
        }

    }

    /**
     * Returns the list of triggers for this combination.
     * 
     * @return a list of ATrigger objects
     */
    @Override
    public List<ATrigger> getTriggers() {
        // Get trigger
        this.trigger = (KSBasETrigger) RunLogic.getTrigger("KSBasETrigger");
        // return trigger
        List<ATrigger> triggers = new LinkedList<ATrigger>();
        triggers.add(trigger);
        return triggers;
    }

    /**
     * Adds an effect to the list of effects to execute.
     * 
     * @param effectName
     *            Name of the effect
     * @param priority
     *            Priority of the effect
     */
    public static final void addEffect(final String effectName, final int priority) {

        LinkedList<String> list;
        if (!KSBasECombination.effects.containsKey(priority)) {
            list = new LinkedList<String>();
        } else {
            list = KSBasECombination.effects.get(priority);
        }
        list.add(effectName);
        KSBasECombination.effects.put(priority, list);

    }

    /**
     * Removes an effect from the list of effects to execute.
     * 
     * @param effectName
     *            Effect to remove
     * @param priority
     *            Priority of the effect
     */
    public static final void removeEffect(final String effectName, final int priority) {

        if (KSBasECombination.effects != null) {
            KSBasECombination.effects.get(priority).remove(effectName);
            if (KSBasECombination.effects.get(priority).isEmpty()) {
                KSBasECombination.effects.remove(priority);
            }
        }

    }

    /**
     * Changes the priority of an effect.
     * 
     * @param effectName
     *            Name of the effect
     * @param oldPrio
     *            Old priority
     * @param newPrio
     *            New priority
     */
    public static final void changePriority(
            final String effectName, final int oldPrio, final int newPrio) {
        KSBasECombination.removeEffect(effectName, oldPrio);
        KSBasECombination.addEffect(effectName, newPrio);
    }

    /**
     * Stores the effects in the given preference store.
     * 
     * @param prefStore
     *            The store to read from
     */
    public static final void storeEffects(final IPreferenceStore prefStore) {
        StringBuffer effectString = new StringBuffer();
        if (KSBasECombination.effects != null && KSBasECombination.effects.size() > 0) {
            for (Entry<Integer, LinkedList<String>> entries : KSBasECombination.effects.entrySet()) {
                for (String effect : entries.getValue()) {
                    if (effect.contains(";")) {
                        // FIXME: ignore, because we are using ; as a separator,
                        // is
                        // there a 'non valid' name char ?
                        continue;
                    }
                    effectString.append(effect + ";");
                    prefStore.setValue(effect, entries.getKey());
                }
            }
        }
        // removing last ';' when storing effects
        if (effectString.length() > 1) {
            prefStore.setValue("storedEffects", effectString
                    .substring(0, effectString.length() - 1));
        } else {
            prefStore.setValue("storedEffects", "");
        }
    }

    /**
     * Reads the settings from the given preference store. Although it may seem
     * odd to do this here, it is necessary because we want to use stored
     * settings even if the preference page has not been opened yet.
     * 
     * @param prefStore
     *            The preference store that contains the stored objects
     */
    public static final void initalizeEffects(final IPreferenceStore prefStore) {
        HashMap<Integer, LinkedList<String>> effectList =
                new HashMap<Integer, LinkedList<String>>();
        // First: read all stored effects
        String storedEffects = prefStore.getString("storedEffects");
        if (storedEffects != null) {
            // The effects are separated by ';'
            for (String effect : storedEffects.split(";")) {
                int prio = prefStore.getInt(effect);
                LinkedList<String> list;
                if (effectList.containsKey(prio)) {
                    list = effectList.get(prio);
                } else {
                    list = new LinkedList<String>();
                }
                list.add(effect);
                effectList.put(prio, list);
            }
            KSBasECombination.effects = effectList;
        } else {
            // No effects have been stored, so we are trying to add
            // two defaults: layout & zoom:
            LinkedList<String> list = new LinkedList<String>();
            AEffect layoutEffect =
                    RunLogic.getEffect("de.cau.cs.kieler.viewmanagement.effects.layouteffect");
            if (layoutEffect != null) {
                list.add(layoutEffect.getClass().getCanonicalName());
            }
            AEffect zoomEffect =
                    RunLogic.getEffect("de.cau.cs.kieler.viewmanagement.effects.zoomeffect");
            if (zoomEffect != null) {
                list.add(zoomEffect.getClass().getCanonicalName());
            }
            if (list.size() > 0) {
                effectList.put(0, list);
                KSBasECombination.effects = effectList;
            }
        }
    }

    /**
     * Gets the registered effects.
     * 
     * @return A hash map containing the transformations ordered by their
     *         priority
     */
    public static HashMap<Integer, LinkedList<String>> getEffects() {
        return effects;
    }
}
