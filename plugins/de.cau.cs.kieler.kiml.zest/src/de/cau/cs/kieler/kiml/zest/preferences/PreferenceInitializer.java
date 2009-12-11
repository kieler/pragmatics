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
package de.cau.cs.kieler.kiml.zest.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import de.cau.cs.kieler.kiml.zest.ZestLayouterPlugin;

/**
 * Initializer for the Zest layouter plugin preferences.
 * 
 * @kieler.rating 2009-12-11 proposed yellow msp
 * @author <a href="mailto:msp@informatik.uni-kiel.de">Miro Sp&ouml;nemann</a>
 */
public class PreferenceInitializer extends AbstractPreferenceInitializer {

    private static final float DEF_SCALE_BASE = 40.0f;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void initializeDefaultPreferences() {
        IPreferenceStore store = ZestLayouterPlugin.getDefault().getPreferenceStore();

        /* initialize the layout options */
        store.setDefault(ZestLayouterPreferencePage.PREF_SCALE_BASE, DEF_SCALE_BASE);
    }

}
