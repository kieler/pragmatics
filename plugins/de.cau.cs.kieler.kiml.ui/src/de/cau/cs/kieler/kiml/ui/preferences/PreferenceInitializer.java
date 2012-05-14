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
package de.cau.cs.kieler.kiml.ui.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import de.cau.cs.kieler.kiml.ui.KimlUiPlugin;
import de.cau.cs.kieler.kiml.ui.diagram.LayoutHandler;
import de.cau.cs.kieler.kiml.ui.service.EclipseLayoutInfoService;
import de.cau.cs.kieler.kiml.ui.views.LayoutViewPart;

/**
 * Preference initializer for the KIML plugins.
 *
 * @kieler.rating 2010-04-28 proposed yellow msp
 * @author msp
 */
public class PreferenceInitializer extends AbstractPreferenceInitializer {

    /**
     * {@inheritDoc}
     */
    @Override
    public void initializeDefaultPreferences() {
        IPreferenceStore preferenceStore = KimlUiPlugin.getDefault().getPreferenceStore();
        
        preferenceStore.setDefault(LayoutHandler.PREF_ANIMATION, true);
        preferenceStore.setDefault(LayoutHandler.PREF_ZOOM, false);
        preferenceStore.setDefault(LayoutHandler.PREF_PROGRESS, false);
        preferenceStore.setDefault(EclipseLayoutInfoService.PREF_OBLIQUE_ROUTE, true);
        preferenceStore.setDefault(LayoutViewPart.PREF_CATEGORIES, true);
        preferenceStore.setDefault(LayoutViewPart.PREF_ADVANCED, false);
    }

}
