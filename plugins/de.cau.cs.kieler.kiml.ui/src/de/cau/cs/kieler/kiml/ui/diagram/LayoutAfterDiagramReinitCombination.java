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
package de.cau.cs.kieler.kiml.ui.diagram;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.IEditorPart;

import de.cau.cs.kieler.core.kivi.AbstractCombination;
import de.cau.cs.kieler.core.model.triggers.ReInitDiagramTriggerState;
import de.cau.cs.kieler.kiml.ui.KimlUiPlugin;

/**
 * This combination triggers an autolayout with the given user preferences when
 * a diagram editor was reinitialized.
 * 
 * @author soh
 */
public class LayoutAfterDiagramReinitCombination extends AbstractCombination {

    /** parameter id for animation. */
    private static final String ANIMATE = "de.cau.cs.kieler.kiml.animate";
    /** parameter id for zoom to fit. */
    private static final String ZOOM_TO_FIT = "de.cau.cs.kieler.kiml.zoomToFit";
    /** parameter id for progress bar. */
    private static final String PROGRESS_BAR =
            "de.cau.cs.kieler.kiml.progressBar";

    /**
     * Perform the autolayout.
     * 
     * @param trigger
     *            the trigger
     */
    public void execute(final ReInitDiagramTriggerState trigger) {
        IEditorPart editor = trigger.getEditor();
        if (editor != null) {
            IPreferenceStore preferenceStore =
                    KimlUiPlugin.getDefault().getPreferenceStore();
            boolean animate = preferenceStore.getBoolean(ANIMATE);
            boolean zoom = preferenceStore.getBoolean(ZOOM_TO_FIT);
            boolean progressBar = preferenceStore.getBoolean(PROGRESS_BAR);

            schedule(new LayoutEffect(trigger.getEditor(), null, zoom,
                    progressBar, false, animate)); 
        }
    }
    
}
