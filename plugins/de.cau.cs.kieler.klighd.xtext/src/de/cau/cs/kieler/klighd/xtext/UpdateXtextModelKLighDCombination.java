/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse Rich Client
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.xtext;

import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.xbase.lib.IterableExtensions;

import de.cau.cs.kieler.core.kivi.AbstractCombination;
import de.cau.cs.kieler.klighd.kivi.effects.KlighdCloseDiagramEffect;
import de.cau.cs.kieler.klighd.kivi.effects.KlighdUpdateDiagramEffect;
import de.cau.cs.kieler.klighd.krendering.SimpleUpdateStrategy;
import de.cau.cs.kieler.klighd.util.KlighdSynthesisProperties;
import de.cau.cs.kieler.klighd.xtext.triggers.XtextBasedEditorActivationChangeTrigger.XtextModelChangeState;
import de.cau.cs.kieler.klighd.xtext.triggers.XtextBasedEditorActivationChangeTrigger.XtextModelChangeState.EventType;
// SUPPRESS CHECKSTYLE PREVIOUS 2 LineLength

/**
 * A combination for initializing/refreshing of KLighD views of Xtext-based models.
 * Views initiated by this combination are shared with {@link VisualizeChosenElementCombination}
 * since they use the compute the (secondary) view ids the same way.
 * 
 * @author chsch
 * @author cds
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
        String id = state.getEditorInputPath().toPortableString().replace(":", "");
          // the replacement is needed since the secondary view ids seem to be required
          //  to be free of ':', which will be violated on windows determining them this way. 
        
        if (state.getEventType().equals(EventType.CLOSED)) {
            // Make sure the associated KLighD view gets closed
            this.schedule(new KlighdCloseDiagramEffect(id));
        } else {
            // Open / Update associated KLighD view
            XtextResource resource = state.getResource();
            if (resource == null || IterableExtensions.isNullOrEmpty(resource.getContents())) {
                return;
            }
            
            // Create the update effect and set basic properties on it
            KlighdUpdateDiagramEffect effect = new KlighdUpdateDiagramEffect(
                    id, state.getEditorInputPath().lastSegment(),
                    KLighDXtextPlugin.getXtextModelAccessProxy(state.getEditor()),
                    state.getEditor());
            effect.setProperty(KlighdSynthesisProperties.REQUESTED_UPDATE_STRATEGY,
                    getRequestedUpdateStrategy(state));

            
            // Subclasses may specify IDs of transformations that must explicitly be used to display
            // the model in the KLighD view
            String requestedTransformations = getRequestedDiagramSynthesis(state);
            if (requestedTransformations != null) {
                effect.setProperty(KlighdSynthesisProperties.REQUESTED_DIAGRAM_SYNTHESIS,
                        requestedTransformations);
            }
            
            // Schedule the effect for execution
            this.schedule(effect);
        }
    }
    
    /**
     * Returns diagram synthesis to be used when visualizing a given model. Default
     * implementation returns {@code null}. May be overridden by subclasses.
     * 
     * @param state a {@link de.cau.cs.kieler.core.kivi.ITriggerState} carrying the necessary
     *              information.
     * @return diagram synthesis id or {@code null}.
     */
    protected String getRequestedDiagramSynthesis(final XtextModelChangeState state) {
        return null;
    }
    
    /**
     * Returns the id of {@link de.cau.cs.kieler.klighd.IUpdateStrategy IUpdateStrategy} to be used
     * when visualizing a given model. Default implementation returns
     * {@code SimpleUpdateStrategy.ID}. May be overridden by subclasses.
     * 
     * @param state
     *            a {@link de.cau.cs.kieler.core.kivi.ITriggerState} carrying the necessary
     *            information.
     * @return a registered {@link de.cau.cs.kieler.klighd.IUpdateStrategy IUpdateStrategy}'s id.
     */
    protected String getRequestedUpdateStrategy(final XtextModelChangeState state) {
        return SimpleUpdateStrategy.ID;
    }
}
