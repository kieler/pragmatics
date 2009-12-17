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
package de.cau.cs.kieler.klodd.ui.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import de.cau.cs.kieler.klodd.hierarchical.HierarchicalDataflowLayoutProvider;
import de.cau.cs.kieler.klodd.ui.KloddUIPlugin;

/**
 * Initializer for the KLoDD layouter plugin preferences.
 * 
 * @kieler.rating 2009-12-11 proposed yellow msp
 * @author msp
 */
public class PreferenceInitializer extends AbstractPreferenceInitializer {

    /**
     * {@inheritDoc}
     */
    public void initializeDefaultPreferences() {
        IPreferenceStore store = KloddUIPlugin.getDefault().getPreferenceStore();

        // initialize options for hierarchical layout
        store.setDefault(HierarchicalDataflowLayoutProvider.PREF_CYCLE_REM,
                HierarchicalDataflowLayoutProvider.VAL_GREEDY_CYCLE_REM);
        store.setDefault(HierarchicalDataflowLayoutProvider.PREF_LAYER_ASS,
                HierarchicalDataflowLayoutProvider.VAL_BAL_LAYER_ASS);
        store.setDefault(HierarchicalDataflowLayoutProvider.PREF_LAYER_EDGEROUTER,
                HierarchicalDataflowLayoutProvider.VAL_TOPO_LAYER_EDGEROUTER);
        store.setDefault(HierarchicalDataflowLayoutProvider.PREF_CROSSRED_PASSES,
                HierarchicalDataflowLayoutProvider.DEF_CROSSRED_PASSES);
        store.setDefault(HierarchicalDataflowLayoutProvider.PREF_BALANCE_VS_SIZE, true);
    }

}
