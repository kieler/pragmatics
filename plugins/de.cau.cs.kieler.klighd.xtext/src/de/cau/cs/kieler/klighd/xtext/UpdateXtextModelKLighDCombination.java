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

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.XtextEditor;
import org.eclipse.xtext.ui.editor.model.IXtextDocument;
import org.eclipse.xtext.util.concurrent.IUnitOfWork;
import org.eclipse.xtext.xbase.lib.IterableExtensions;

import de.cau.cs.kieler.core.kivi.AbstractCombination;
import de.cau.cs.kieler.core.util.RunnableWithResult;
import de.cau.cs.kieler.klighd.LightDiagramServices;
import de.cau.cs.kieler.klighd.effects.KlighdCloseDiagramEffect;
import de.cau.cs.kieler.klighd.effects.KlighdUpdateDiagramEffect;
import de.cau.cs.kieler.klighd.krendering.SimpleUpdateStrategy;
import de.cau.cs.kieler.klighd.util.KlighdProperties;
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
                    id,
                    state.getEditorInputPath().lastSegment(),
                    resource.getContents().get(0),
                    state.getEditor());
            effect.setProperty(LightDiagramServices.REQUESTED_UPDATE_STRATEGY,
                    getRequestedUpdateStrategy(state));
            effect.setProperty(KlighdProperties.MODEL_ACCESS, new RunnableWithResult<EObject>() {

                private EObject result = null;
                
                public void run() {
                    XtextEditor editor = state.getEditor();
                    if (editor == null) {
                        return;
                    }
                    IXtextDocument document = editor.getDocument();
                    if (document == null) {
                        return;
                    }
                    state.getEditor().getDocument().readOnly(new IUnitOfWork.Void<XtextResource>() {

                        @Override
                        public void process(final XtextResource state) throws Exception {
                            result = state.getContents().get(0);
                        }
                    });
                }

                public EObject getResult() {
                    return result;
                }
                
            });
            
            // Subclasses may specify IDs of transformations that must explicitly be used to display
            // the model in the KLighD view
            List<String> requestedTransformations = getRequestedTransformations(state);
            if (requestedTransformations != null) {
                effect.setProperty(
                        LightDiagramServices.REQUESTED_TRANSFORMATIONS,
                        requestedTransformations);
            }
            
            // Schedule the effect for execution
            this.schedule(effect);
        }
    }
    
    /**
     * Returns a list of transformations to be used when visualizing a given model. Default
     * implementation returns {@code null}. May be overridden by subclasses.
     * 
     * @param state a {@link de.cau.cs.kieler.core.kivi.ITriggerState} carrying the necessary
     *              information.
     * @return list of transformation IDs or {@code null}.
     */
    protected List<String> getRequestedTransformations(final XtextModelChangeState state) {
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
