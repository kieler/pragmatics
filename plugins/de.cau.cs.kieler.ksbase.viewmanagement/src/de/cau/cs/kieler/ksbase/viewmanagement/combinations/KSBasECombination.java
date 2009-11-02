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

import org.eclipse.gef.EditPart;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;

import de.cau.cs.kieler.kiml.ui.layout.DiagramLayoutManager;
import de.cau.cs.kieler.ksbase.viewmanagement.triggers.KSBasETrigger;
import de.cau.cs.kieler.viewmanagement.ACombination;
import de.cau.cs.kieler.viewmanagement.ATrigger;
import de.cau.cs.kieler.viewmanagement.RunLogic;
import de.cau.cs.kieler.viewmanagement.TriggerEventObject;

/**
 * The KSBasE combination used to execute a set of effects, selected by the user
 * via the preference pages.
 * 
 * ILL NEED A CHANGE IN KIELER.Viewmanagement to work !!
 * 
 * @author Michael Matzen - mim AT informatik.uni-kiel.de
 * 
 */
public class KSBasECombination extends ACombination {

    /** List of effect names to execute. **/
    protected static HashMap<Integer, LinkedList<String>> effects =
            new HashMap<Integer, LinkedList<String>>();
    /** Trigger object. **/
    private KSBasETrigger trigger;

    /** Target editPart. **/
    private EditPart editPart;
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

        editPart = translateToEditPart(triggerEvent.getAffectedObject(), getRootEPAsParent());
        parameter = triggerEvent.getParameters();

        return true;
    }

    /**
     * Executes the registered effects.
     */
    @Override
    public void execute() {
        // FIXME: This is just a temporary hack....
        if (parameter != null && parameter instanceof IEditorPart) {
            final IWorkbench workbench = PlatformUI.getWorkbench();
            workbench.getDisplay().asyncExec(new Runnable() {
                public void run() {
                    DiagramLayoutManager.layout(
                            (IEditorPart) parameter, editPart.getRoot(), true, false);
                }
            });
        }
        // Thats how it should be done if vm is fixed
        /*
         * for (int prio : KSBasECombination.effects.keySet()) { for (String
         * effectName : KSBasECombination.effects.get(prio)) { AEffect effect =
         * RunLogic.getEffect(effectName); if (effect != null) { // Set effect
         * target and parameter effect.setTarget(editPart);
         * effect.setParameters(parameter); // Execute effect effect.execute();
         * } } }
         */

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
        KSBasECombination.effects.get(priority).remove(effectName);
        if (KSBasECombination.effects.get(priority).isEmpty()) {
            KSBasECombination.effects.remove(priority);
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
        for (int prio : KSBasECombination.effects.keySet()) {
            for (String effect : KSBasECombination.effects.get(prio)) {
                if (effect.contains(";")) {
                    // FIXME: ignore, because we are using ; as a separator, is
                    // there a 'non valid' name char ?
                    continue;
                }
                effectString.append(effect + ";");
                prefStore.setValue(effect, prio);
            }
        }
        // removing last ';' when storing effects
        prefStore.setValue("storedEffects", effectString.substring(0, effectString.length() - 1));
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
        if (storedEffects != null && storedEffects.length() > 0) {
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
        }
    }
}
