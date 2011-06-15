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

import de.cau.cs.kieler.core.kivi.AbstractCombination;
import de.cau.cs.kieler.core.kivi.ITriggerState;
import de.cau.cs.kieler.core.model.xtext.XtextBasedEditorActivationChangeTrigger.XtextModelChangeState;
import de.cau.cs.kieler.core.model.xtext.XtextBasedEditorActivationChangeTrigger.XtextModelChangeState.EventType;
import de.cau.cs.kieler.klighd.effects.KlighdDiagramEffect;
import de.cau.cs.kieler.klighd.effects.KlighdDiagramEffect.KLighDCloseDiagramEffect;

import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.util.concurrent.IUnitOfWork;


/**
 * A combination for initializing/refreshing of KLighD views of Xtext-based models.
 * 
 * @author chsch
 */
public class UpdateXtextModelKLighDCombination extends AbstractCombination {
    
    
    /**
     * The execute method revealed and invoked by KIVi.
     * 
     * @param state The {@link ITriggerState} for denoting the related EMF resource 
     */
    public void execute(final XtextModelChangeState state) {
        XtextResource resource = state.getResource();
        if (resource != null) {
            this.schedule(state.getEventType(), resource);
        } else {
            state.getEditor().getDocument().readOnly(new IUnitOfWork.Void<XtextResource>() {

                @Override
                public void process(XtextResource resource) throws Exception {
                    UpdateXtextModelKLighDCombination.this.schedule(state.getEventType(), resource);
                }

            });
        }
    }
    
    private void schedule(EventType eventType, XtextResource resource) {
        String id = resource.getURI().toPlatformString(false);
        if (eventType.equals(EventType.CLOSED)) {
            this.schedule(new KLighDCloseDiagramEffect(id));
        } else { 
            this.schedule(new KlighdDiagramEffect(id, resource.getContents().get(0)));
        }
    }

}
