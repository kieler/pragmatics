/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package net.ogdf.bin.preferences;

import net.ogdf.bin.OgdfServer;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

/**
 * The OGDF layouter preferences initializer.
 * 
 * @author mri
 */
public class OgdfPreferenceInitializer extends AbstractPreferenceInitializer {

    /**
     * {@inheritDoc}
     */
    @Override
    public void initializeDefaultPreferences() {
        IPreferenceStore store = OgdfPreferenceStore.getInstance().getPreferenceStore();
        store.setDefault(OgdfServer.PREF_TIMEOUT, OgdfServer.PROCESS_DEF_TIMEOUT);
    }
}
