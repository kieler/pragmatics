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
package de.cau.cs.kieler.kiml.graphviz.layouter.preferences;

import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.preferences.ScopedPreferenceStore;

import de.cau.cs.kieler.kiml.graphviz.layouter.GraphvizLayouterPlugin;

/**
 * In order to avoid extending {@link org.eclipse.ui.plugin.AbstractUIPlugin AbstractUIPlugin} by
 * the {@link GraphvizLayouterPreferenceStore} plugin, we maintain our own preference store here.
 * Usually the preference store is accessed using
 * {@link org.eclipse.ui.plugin.AbstractUIPlugin#getPreferenceStore()
 * AbstractUIPlugin#getPreferenceStore()}. The initialization of the store here is taken from the
 * just mentioned ui plugin.
 * 
 * @author uru
 */
public final class GraphvizLayouterPreferenceStore {

    private IPreferenceStore preferenceStore;

    private static GraphvizLayouterPreferenceStore instance;

    private GraphvizLayouterPreferenceStore() {
    }

    /**
     * @return an instance of {@link OgdfPreferenceStore}.
     */
    public static GraphvizLayouterPreferenceStore getInstance() {
        if (instance == null) {
            instance = new GraphvizLayouterPreferenceStore();
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
                    new ScopedPreferenceStore(new InstanceScope(), GraphvizLayouterPlugin
                            .getDefault().getBundle().getSymbolicName());
        }
        return preferenceStore;
    }
}
