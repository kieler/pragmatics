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

import de.cau.cs.kieler.core.kivi.AbstractCombination;
import de.cau.cs.kieler.core.model.gmf.triggers.ModelChangeTrigger.DiagramChangeState;
import de.cau.cs.kieler.kiml.ui.layout.LayoutEffect;

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

    /**
     * Apply automatic layout every time the model changed state is updated.
     * 
     * @param modelState
     *            model changed
     * @param diagramState
     *            diagram changed
     */
    public void execute(final DiagramChangeState diagramState) {
        dontUndo();
        // diagram changed
        for (Notification notification : diagramState.getChange().getNotifications()) {
            if (diagramFilter.matches(notification)
                    && notification.getNotifier() instanceof EObject) {
                schedule(new LayoutEffect(diagramState.getWorkbenchPart(),
                        (EObject) notification.getNotifier(), true, false, true));
            }
        }
    }
}
