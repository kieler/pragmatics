/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2008 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.graphviz.layouter.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import de.cau.cs.kieler.kiml.graphviz.layouter.GraphvizLayoutProvider;
import de.cau.cs.kieler.kiml.graphviz.layouter.GraphvizTool;
import de.cau.cs.kieler.kiml.graphviz.layouter.GraphvizLayouterPlugin;

/**
 * Class used to initialize default preference values for the GraphViz layouter
 * plug-in.
 * 
 * @author ars
 * @kieler.design proposed by msp
 * @kieler.rating proposed yellow by msp
 */
public class PreferenceInitializer extends AbstractPreferenceInitializer {

    /**
     * {@inheritDoc}
     */
    @Override
    public void initializeDefaultPreferences() {
        IPreferenceStore store = GraphvizLayouterPlugin.getDefault().getPreferenceStore();

        store.setDefault(GraphvizTool.PREF_TIMEOUT, GraphvizTool.PROCESS_DEF_TIMEOUT);
        store.setDefault(GraphvizLayoutProvider.PREF_GRAPHVIZ_REUSE_PROCESS,
                GraphvizLayoutProvider.REUSE_PROCESS_DEFAULT);
    }
}
