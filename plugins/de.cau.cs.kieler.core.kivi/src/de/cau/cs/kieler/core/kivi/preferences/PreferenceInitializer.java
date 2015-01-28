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
package de.cau.cs.kieler.core.kivi.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import de.cau.cs.kieler.core.kivi.KiVi;
import de.cau.cs.kieler.core.kivi.KiViPlugin;

/**
 * Preference initializer for the KiVi plugin.
 *
 * @author mmu
 */
public class PreferenceInitializer extends AbstractPreferenceInitializer {

    /**
     * {@inheritDoc}
     */
    @Override
    public void initializeDefaultPreferences() {
        final IPreferenceStore preferenceStore = KiViPlugin.getDefault().getPreferenceStore();

        // chsch: deactivated initial kivi activation as it is prone to
        //  impair the memory management (garbage collection) due
        //  to its nature of retaining triggered trigger states
        preferenceStore.setDefault(KiVi.PROPERTY_ACTIVE, false);
    }
}
