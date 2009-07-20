package de.cau.cs.kieler.kiml.zest.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import de.cau.cs.kieler.kiml.zest.Activator;


/**
 * Initializer for the Zest layouter plugin preferences.
 * 
 * @author <a href="mailto:msp@informatik.uni-kiel.de">Miro Sp&ouml;nemann</a>
 */
public class PreferenceInitializer extends AbstractPreferenceInitializer {

	/* (non-Javadoc)
	 * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#initializeDefaultPreferences()
	 */
	public void initializeDefaultPreferences() {
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		
		/* initialize the layout options */
		store.setDefault(ZestLayouterPreferencePage.PREF_SCALE_BASE, 40.0f);
	}

}
