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

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Vector;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.preference.IPreferenceStore;

import de.cau.cs.kieler.ksbase.ui.KSBasEUIPlugin;
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
 * @author mim
 * 
 * @kieler.rating 2010-03-22 proposed yellow
 */

public class KSBasECombination extends ACombination {

    /**
     * Vector of effects. The effects will be executed in the order of
     * appearance in this vector.
     **/
    private static Vector<String> effects = new Vector<String>();

    /** Trigger object. **/
    private KSBasETrigger trigger;

    /** Target editPart. **/
    private EObject affectedObject;
    /** Additional parameter. **/
    private Object parameter;

    /** Separation character for storage of effect names. **/
    private static final String SEPARATOR = "\u00AB";

    /**
     * Creates a new KSBasECombination.
     */
    public KSBasECombination() {
        setActive(true);
        initialize();
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
        for (String effectName : effects) {
            AEffect effect = RunLogic.getInstance().getEffect(
                    effectName.toLowerCase(Locale.getDefault()));
            if (effect != null) { // Set effect target and parameter
                effect.setTarget(getEditPart(affectedObject));
                effect.setParameters(parameter); // Execute effect
                effect.execute();
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
        this.trigger = (KSBasETrigger) RunLogic.getInstance().getTrigger(
                "KSBasETrigger");
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
     * @param index
     *            Index of the effect
     */
    public static final void addEffect(final String effectName, final int index) {
        if (KSBasECombination.effects != null) {
            if (KSBasECombination.effects.contains(effectName)) {
                KSBasECombination.effects.removeElement(effectName);
            }
            KSBasECombination.effects.insertElementAt(effectName, index);
        }
    }

    /**
     * Removes an effect from the list of effects to execute.
     * 
     * @param effectName
     *            Effect to remove
     */
    public static final void removeEffect(final String effectName) {
        if (KSBasECombination.effects != null) {
            if (KSBasECombination.effects.contains(effectName)) {
                KSBasECombination.effects.remove(effectName);
            }
        }

    }

    /**
     * Changes the index of an effect.
     * 
     * @param effectName
     *            Name of the effect
     * @param newIndex
     *            New index
     */
    public static final void changeIndex(final String effectName,
            final int newIndex) {
        if (KSBasECombination.effects != null) {
            removeEffect(effectName);
            addEffect(effectName, newIndex);
        }
    }

    /**
     * Stores the effects in the given preference store.
     * 
     * @param prefStore
     *            The store to read from
     */
    public static final void storeEffects(final IPreferenceStore prefStore) {
        StringBuffer effectString = new StringBuffer();
        if (KSBasECombination.effects != null
                && KSBasECombination.effects.size() > 0) {
            for (String effect : KSBasECombination.effects) {
                if (effect.contains(KSBasECombination.SEPARATOR)) {
                    KSBasEUIPlugin.getDefault().logWarning(
                            "Could not store effect (" + effect
                                    + "). Name contains invalid character:"
                                    + KSBasECombination.SEPARATOR);
                    continue;
                }
                effectString.append(effect + KSBasECombination.SEPARATOR);
            }
        }

        // removing last separator when storing effects
        if (effectString.length() > 1) {
            prefStore.setValue("storedEffects", effectString.substring(0,
                    effectString.length() - 1));
        } else {
            prefStore.setValue("storedEffects", ";");
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
        Vector<String> effectList = new Vector<String>();
        if (!RunLogic.getInstance().getState()) {
            RunLogic.getInstance().registerListeners();
        }
        List<String> availableEffects = RunLogic.getInstance()
                .getEffectsAsText();
        // First: read all stored effects
        String storedEffects = prefStore.getString("storedEffects");
        if (storedEffects.length() > 0) {
            // The effects are separated by ';'
            for (String effect : storedEffects
                    .split(KSBasECombination.SEPARATOR)) {
                if (effect.length() > 0 && availableEffects.contains(effect)) {
                    effectList.add(effect);
                }
            }
        } else {
            // No effects have been stored, so we are trying to add
            // layout as the default

            if (availableEffects
                    .contains("de.cau.cs.kieler.viewmanagement.effects.layouteffect")) {
                effectList
                        .add("de.cau.cs.kieler.viewmanagement.effects.layouteffect");
            }
            if (availableEffects
                    .contains("de.cau.cs.kieler.viewmanagement.effects.zoomeffect")) {
                effectList
                        .add("de.cau.cs.kieler.viewmanagement.effects.zoomeffect");
            }

        }
        KSBasECombination.effects = effectList;
    }

    /**
     * Gets the registered effects.
     * 
     * @return A vector containing the transformations ordered by their priority
     */
    public static Vector<String> getEffects() {
        // We are cloning the vector, so the inner data structures will not be
        // changed
        Vector<String> result = new Vector<String>();
        result.addAll(KSBasECombination.effects);
        return result;
    }
}
