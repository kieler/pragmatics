/******************************************************************************
 * KIELER - Kiel Integrated Environment for Layout for the Eclipse RCP
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

	/** List of effect names to execute **/
	public static HashMap<Integer, LinkedList<String>> effects = new HashMap<Integer, LinkedList<String>>();
	/** Trigger object **/
	private KSBasETrigger trigger;
	/** Target editPart **/
	private EditPart editPart;
	/** Additional parameter **/
	private Object parameter;

	/**
	 * Creates a new KSBasECombination.
	 */
	public KSBasECombination() {
	}

	/**
	 * Evaluates if triggerEventObject is valid.
	 */
	@Override
	public boolean evaluate(TriggerEventObject triggerEvent) {
		editPart = translateToEditPart(triggerEvent.getAffectedObject(),
				getRootEPAsParent());
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
				AEffect effect = RunLogic.getEffect(effectName);
				if (effect != null) {
					// Set effect target and parameter
					effect.setTarget(editPart);
					effect.setParameters(parameter);
					// Execute effect
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
	 * @param effectName Name of the effect
	 * @param priority Priority of the effect
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
	 * @param effectName Effect to remove
	 * @param priority Priority of the effect
	 */
	public static final void removeEffect(final String effectName, final int priority) {
		KSBasECombination.effects.get(priority).remove(effectName);
		if (KSBasECombination.effects.get(priority).isEmpty()) {
			KSBasECombination.effects.remove(priority);
		}
	}

	/**
	 * Changes the priority of an effect.
	 * @param effectName Name of the effect
	 * @param oldPrio Old priority
	 * @param newPrio New priority
	 */
	public static final void changePriority(final String effectName,
			final int oldPrio, final int newPrio) {
		KSBasECombination.removeEffect(effectName, oldPrio);
		KSBasECombination.addEffect(effectName, newPrio);
	}
	
	/**
	 * Stores the effects in the given preference store.
	 * @param prefStore The store to read from
	 */
	public static final void storeEffects(IPreferenceStore prefStore) {
		String effects = "";
    	for (int prio : KSBasECombination.effects.keySet()) {
    		for ( String effect : KSBasECombination.effects.get(prio)) {
    			if ( effect.contains(";")) {
    				//FIXME: ignore, because we are using ; as a separator, is there a 'non valid' name char ?
    				continue;
    			}
    			effects += effect + ";";
    			prefStore.setValue(effect, prio);
    		}
    	}
    	//removing last ';' when storing effects
    	prefStore.setValue("storedEffects", effects.substring(0, effects.length()-1));
	}
	
	/**
	 * Reads the settings from the given preference store.
	 * Although it may seem odd to do this here, it is necessary 
	 * because we want to use stored settings even if the 
	 * preference page has not been opened yet.
	 * @param prefStore
	 */
	public static final void initalizeEffects(IPreferenceStore prefStore) {
		HashMap<Integer,LinkedList<String>> effects = new HashMap<Integer, LinkedList<String>>();
		//First: read all stored effects
		String storedEffects = prefStore.getString("storedEffects");
		if (storedEffects != null && storedEffects.length() > 0) {
			//The effects are separated by ';'
			for ( String effect : storedEffects.split(";")) {
				int prio = prefStore.getInt(effect);
				LinkedList<String> list;
				if ( effects.containsKey(prio)) {
					list = effects.get(prio);
				}
				else {
					list = new LinkedList<String>();
				}
				list.add(effect);
				effects.put(prio, list);
			}
			KSBasECombination.effects = effects;
		}
	}
} 
