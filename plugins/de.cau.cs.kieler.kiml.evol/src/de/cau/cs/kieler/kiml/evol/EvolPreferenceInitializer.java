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
package de.cau.cs.kieler.kiml.evol;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

/**
 * Preference initializer for the KIML Evolutionary Plugin. This class
 * initializes the preferences for the plugin.
 * 
 * @author bdu
 * 
 */
public class EvolPreferenceInitializer extends AbstractPreferenceInitializer {

    @Override
    public void initializeDefaultPreferences() {
        IPreferenceStore store = EvolPlugin.getDefault().getPreferenceStore();

        // Set default values for preferences.
        store.setDefault(EvolPlugin.PREF_POPULATION_SIZE, EvolPlugin.DEF_POPULATION_SIZE);
        store.setDefault(EvolPlugin.PREF_EDITORS, EvolPlugin.DEF_EDITORS);
        store.setDefault(EvolPlugin.PREF_USE_LAYOUT_HINT_FROM_GENOME, true);
        store.setDefault(EvolPlugin.PREF_USE_DIFFERENT_TYPE_LAYOUT_HINT, false);
        store.setDefault(EvolPlugin.PREF_IS_PARTHENOGENESIS_ALLOWED, true);
    }
}
