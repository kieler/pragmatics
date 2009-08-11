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
 */
package de.cau.cs.kieler.dataflow.ui.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import de.cau.cs.kieler.dataflow.ui.Activator;
import de.cau.cs.kieler.dataflow.ui.wizards.CreateRandomModelWizardPage;

/**
 * Preference initializer for the Dataflow UI plugin.
 *
 * @author <a href="mailto:msp@informatik.uni-kiel.de">Miro Sp&ouml;nemann</a>
 */
public class PreferenceInitializer extends AbstractPreferenceInitializer {

    /* (non-Javadoc)
     * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#initializeDefaultPreferences()
     */
    public void initializeDefaultPreferences() {
        IPreferenceStore preferenceStore = Activator.getDefault().getPreferenceStore();
        
        preferenceStore.setDefault(CreateRandomModelWizardPage.PREF_NODES,
                CreateRandomModelWizardPage.DEF_NODES);
        preferenceStore.setDefault(CreateRandomModelWizardPage.PREF_MIN_CONNECTIONS,
                CreateRandomModelWizardPage.DEF_MIN_CONNECTIONS);
        preferenceStore.setDefault(CreateRandomModelWizardPage.PREF_MAX_CONNECTIONS,
                CreateRandomModelWizardPage.DEF_MAX_CONNECTIONS);
        preferenceStore.setDefault(CreateRandomModelWizardPage.PREF_HIERARCHY,
                CreateRandomModelWizardPage.DEF_HIERARCHY);
    }

}
