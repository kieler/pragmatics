package de.cau.cs.kieler.kiml.evol;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

/**
 * @author bdu
 * 
 */
public class EvolPreferenceInitializer extends AbstractPreferenceInitializer {
    /**
     *
     */
    public EvolPreferenceInitializer() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void initializeDefaultPreferences() {
        final IPreferenceStore store = EvolPlugin.getDefault().getPreferenceStore();
        
        store.setDefault(EvolPlugin.PREF_POPULATION_SIZE, EvolPlugin.DEF_POPULATION_SIZE);
    }
}
