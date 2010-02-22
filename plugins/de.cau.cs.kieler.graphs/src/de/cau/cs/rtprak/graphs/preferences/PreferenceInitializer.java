/*
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
 */
package de.cau.cs.rtprak.graphs.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import de.cau.cs.rtprak.graphs.GraphsPlugin;
import de.cau.cs.rtprak.graphs.wizards.CreateRandomModelWizardPage;

/**
 * Preference initializer for the Graphs plugin.
 *
 * @author msp
 */
public class PreferenceInitializer extends AbstractPreferenceInitializer {

    /**
     * {@inheritDoc}
     */
    @Override
    public void initializeDefaultPreferences() {
        IPreferenceStore preferenceStore = GraphsPlugin.getDefault().getPreferenceStore();
        
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
