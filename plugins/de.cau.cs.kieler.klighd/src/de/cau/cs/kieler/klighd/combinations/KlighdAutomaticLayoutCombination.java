/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse Rich Client
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.combinations;

import org.eclipse.jface.preference.IPreferenceStore;

import de.cau.cs.kieler.core.kivi.AbstractCombination;
import de.cau.cs.kieler.kiml.ui.KimlUiPlugin;
import de.cau.cs.kieler.kiml.ui.diagram.LayoutHandler;
import de.cau.cs.kieler.klighd.effects.KlighdLayoutEffect;
import de.cau.cs.kieler.klighd.triggers.KlighdStatusTrigger.KlighdStatusState;
import de.cau.cs.kieler.klighd.triggers.KlighdStatusTrigger.KlighdStatusState.Status;

/**
 * A combination for applying automatic layout on a KLighD view after a {@code KlighdDiagramEffect}.
 * 
 * @author mri
 */
public class KlighdAutomaticLayoutCombination extends AbstractCombination {

    /**
     * Applies automatic layout to a KLighD view after it has been created or updated.
     * 
     * @param state
     *            the KLighD status state
     */
    public void execute(final KlighdStatusState state) {
        if (state.getStatus() == Status.CREATE_SUCCESS || state.getStatus() == Status.UPDATE) {
            IPreferenceStore preferenceStore = KimlUiPlugin.getDefault().getPreferenceStore();
            boolean animation = preferenceStore.getBoolean(LayoutHandler.PREF_ANIMATION);
            boolean zoomToFit = preferenceStore.getBoolean(LayoutHandler.PREF_ZOOM);
            boolean progressDialog = preferenceStore.getBoolean(LayoutHandler.PREF_PROGRESS);
            
            schedule(new KlighdLayoutEffect(state.getViewId(), state.getViewer(), zoomToFit,
                    progressDialog, false, animation));
        }
    }

}
