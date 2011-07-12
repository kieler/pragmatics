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
package de.cau.cs.kieler.kiml.gmf.combinations;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.NotificationFilter;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.jface.preference.IPreferenceStore;

import de.cau.cs.kieler.core.kivi.AbstractCombination;
import de.cau.cs.kieler.core.model.gmf.triggers.ModelChangeTrigger.DiagramChangeState;
import de.cau.cs.kieler.kiml.ui.KimlUiPlugin;
import de.cau.cs.kieler.kiml.ui.diagram.LayoutEffect;

/**
 * Applies automatic layout after the collapsed state of a compartment of a 
 * diagram has changed.
 * 
 * @author haf
 * 
 */
public class LayoutAfterCollapseExpandCombination extends AbstractCombination {

    private NotificationFilter diagramFilter = NotificationFilter
            .createFeatureFilter(NotationPackage.eINSTANCE.getDrawerStyle_Collapsed());

    
    /** parameter id for animation. */
    private static final String ANIMATE = "de.cau.cs.kieler.kiml.animate";
    /** parameter id for zoom to fit. */
    private static final String ZOOM_TO_FIT = "de.cau.cs.kieler.kiml.zoomToFit";
    /** parameter id for progress bar. */
    private static final String PROGRESS_BAR = "de.cau.cs.kieler.kiml.progressBar";
    
    /**
     * Apply automatic layout every time the model changed state is updated.
     * 
     * @param diagramState
     *            diagram changed
     */
    public void execute(final DiagramChangeState diagramState) {
        IPreferenceStore preferenceStore = getPreferenceStore();
        boolean animate = preferenceStore.getBoolean(ANIMATE);
        boolean zoom = preferenceStore.getBoolean(ZOOM_TO_FIT);
        boolean progressBar = preferenceStore.getBoolean(PROGRESS_BAR);
        //dontUndo();
        // diagram changed
        for (Notification notification : diagramState.getChange().getNotifications()) {
            if (diagramFilter.matches(notification)
                    && notification.getNotifier() instanceof EObject) {
                schedule(new LayoutEffect(diagramState.getWorkbenchPart(),
                        (EObject) notification.getNotifier(), zoom, progressBar, true, animate));
            }
        }
    }
    
    /**
     * Return the preference store for the KIML UI plugin.
     * 
     * @return the preference store
     */
    private static IPreferenceStore getPreferenceStore() {
        return KimlUiPlugin.getDefault().getPreferenceStore();
    }
    
}
