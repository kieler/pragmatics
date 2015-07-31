/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.ui.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import de.cau.cs.kieler.kiml.service.DiagramLayoutEngine;
import de.cau.cs.kieler.kiml.service.KimlServicePlugin;
import de.cau.cs.kieler.kiml.service.LayoutManagersService;
import de.cau.cs.kieler.kiml.ui.KimlUiPlugin;
import de.cau.cs.kieler.kiml.ui.LayoutHandler;
import de.cau.cs.kieler.kiml.ui.views.LayoutViewPart;

/**
 * Preference initializer for the KIML plugins.
 *
 * @author msp
 * @kieler.design proposed by msp
 * @kieler.rating proposed yellow by msp
 */
public class PreferenceInitializer extends AbstractPreferenceInitializer {

    /**
     * {@inheritDoc}
     */
    @Override
    public void initializeDefaultPreferences() {
        IPreferenceStore servicePrefStore = KimlServicePlugin.getDefault().getPreferenceStore();
        IPreferenceStore uiPrefStore = KimlUiPlugin.getDefault().getPreferenceStore();
        
        uiPrefStore.setDefault(LayoutHandler.PREF_ANIMATION, true);
        uiPrefStore.setDefault(LayoutHandler.PREF_ZOOM, false);
        uiPrefStore.setDefault(LayoutHandler.PREF_PROGRESS, false);
        uiPrefStore.setDefault(LayoutViewPart.PREF_CATEGORIES, true);
        uiPrefStore.setDefault(LayoutViewPart.PREF_ADVANCED, false);
        servicePrefStore.setDefault(LayoutManagersService.PREF_OBLIQUE_ROUTE, true);
        servicePrefStore.setDefault(DiagramLayoutEngine.PREF_DEBUG_OUTPUT, false);
        servicePrefStore.setDefault(DiagramLayoutEngine.PREF_EXEC_TIME_MEASUREMENT, false);
    }

}
