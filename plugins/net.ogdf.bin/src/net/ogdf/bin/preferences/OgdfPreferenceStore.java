/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2015 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package net.ogdf.bin.preferences;

import net.ogdf.bin.OgdfPlugin;

import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.preferences.ScopedPreferenceStore;

/**
 * In order to avoid extending {@link org.eclipse.ui.plugin.AbstractUIPlugin AbstractUIPlugin} by
 * the {@link OgdfPlugin} plugin, we maintain our own preference store here. Usually the preference
 * store is accessed using {@link org.eclipse.ui.plugin.AbstractUIPlugin#getPreferenceStore()
 * AbstractUIPlugin#getPreferenceStore()}. The initialization of the store here is taken from the
 * just mentioned ui plugin.
 * 
 * @author uru
 */
public final class OgdfPreferenceStore {

    private IPreferenceStore preferenceStore;

    private static OgdfPreferenceStore instance;

    private OgdfPreferenceStore() {
    }

    /**
     * @return an instance of {@link OgdfPreferenceStore}.
     */
    public static OgdfPreferenceStore getInstance() {
        if (instance == null) {
            instance = new OgdfPreferenceStore();
        }
        return instance;
    }

    /**
     * Functionally equal to {@link org.eclipse.ui.plugin.AbstractUIPlugin#getPreferenceStore()}.
     * 
     * @return the preference store
     */
    @SuppressWarnings("deprecation")
    public IPreferenceStore getPreferenceStore() {
        if (preferenceStore == null) {
            preferenceStore =
                    new ScopedPreferenceStore(new InstanceScope(), OgdfPlugin.getDefault()
                            .getBundle().getSymbolicName());
        }
        return preferenceStore;
    }

}
