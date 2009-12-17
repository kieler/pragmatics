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
package de.cau.cs.kieler.kivik.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import de.cau.cs.kieler.kivik.KivikPlugin;


/**
 * Class used to initialize default preference values.
 * @author ars
 */
public class PreferenceInitializer extends AbstractPreferenceInitializer {

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#
	 * initializeDefaultPreferences()
	 */
	public void initializeDefaultPreferences() {
		IPreferenceStore store = KivikPlugin.getDefault().getPreferenceStore();

		store.setDefault(PreferenceConstants.PREF_KIVIK_ENABLE_AUTO_LAYOUT,
				false);
		store
				.setDefault(
						PreferenceConstants.PREF_KIVIK_ENABLE_COLLAPSING_OF_UNCHANGED_ELEMENTS,
						false);
		store
				.setDefault(
						PreferenceConstants.PREF_KIVIK_ENABLE_SCROLLING_ANIMATION,
						true);
		store.setDefault(
				PreferenceConstants.PREF_KIVIK_ENABLE_SELECTING_IN_DIAGRAM,
				true);
		store
				.setDefault(
						PreferenceConstants.PREF_KIVIK_ENABLE_ZOOMING_TO_CHANGED_ELEMENTS,
						true);
		store.setDefault(PreferenceConstants.PREF_KIVIK_INITIAL_ZOOM_FACTOR,
				70);
	}

}
