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
package de.cau.cs.kieler.klighd.xtext;

import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.util.concurrent.IUnitOfWork;

import de.cau.cs.kieler.core.kivi.AbstractCombination;
import de.cau.cs.kieler.core.model.xtext.triggers.XtextBasedEditorActivationChangeTrigger.XtextModelChangeState; // SUPPRESS CHECKSTYLE LineLength
import de.cau.cs.kieler.core.model.xtext.triggers.XtextBasedEditorActivationChangeTrigger.XtextModelChangeState.EventType; // SUPPRESS CHECKSTYLE LineLength
import de.cau.cs.kieler.klighd.LightDiagramServices;
import de.cau.cs.kieler.klighd.effects.KlighdDiagramEffect;
import de.cau.cs.kieler.klighd.effects.KlighdDiagramEffect.KLighDCloseDiagramEffect;

/**
 * A combination for initializing/refreshing of KLighD views of Xtext-based models.
 * 
 * @author chsch
 */
public class UpdateXtextModelKLighDCombination extends AbstractCombination {

    /**
     * The execute method revealed and invoked by KIVi.
     * 
     * @param state
     *            The {@link de.cau.cs.kieler.core.kivi.ITriggerState} for denoting the related EMF
     *            resource.
     */
    public void execute(final XtextModelChangeState state) {
        XtextResource resource = state.getResource();
        if (resource != null) {
            this.schedule(state.getEventType(), resource);
        } else {
            state.getEditor().getDocument().readOnly(new IUnitOfWork.Void<XtextResource>() {

                @Override
                public void process(final XtextResource resource) throws Exception {
                    UpdateXtextModelKLighDCombination.this.schedule(state.getEventType(), resource);
                }

            });
        }
    }

    private void schedule(final EventType eventType, final XtextResource resource) {
        if (resource == null || resource.getContents() == null || resource.getContents().isEmpty()) {
            return;
        }
        if (!LightDiagramServices.getInstance().maybeSupports(resource.getContents().get(0))) {
            return;
        }
        String id = resource.getURI().toPlatformString(false);
        if (eventType.equals(EventType.CLOSED)) {
            this.schedule(new KLighDCloseDiagramEffect(id));
        } else {
            this.schedule(new KlighdDiagramEffect(id, resource.getURI().lastSegment(), resource
                    .getContents().get(0)));
        }
    }

}
