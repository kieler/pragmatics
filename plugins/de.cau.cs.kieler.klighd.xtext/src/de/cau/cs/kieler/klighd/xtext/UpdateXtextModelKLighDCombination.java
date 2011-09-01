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
     *            A {@link de.cau.cs.kieler.core.kivi.ITriggerState} carrying the necessary
     *            information.
     */
    public void execute(final XtextModelChangeState state) {
        XtextResource resource = state.getResource();
        String id = resource.getURI().toPlatformString(false);
        if (state.getEventType().equals(EventType.CLOSED)) {
            this.schedule(new KLighDCloseDiagramEffect(id));
        } else {
            if (resource == null || resource.getContents() == null
                    || resource.getContents().isEmpty()) {
                return;
            }
            if (!LightDiagramServices.getInstance().maybeSupports(resource.getContents().get(0))) {
                return;
            }
            this.schedule(new KlighdDiagramEffect(id, state.getEditorInputPath().lastSegment(), resource
                    .getContents().get(0)));
        }
    }
}
