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

import org.eclipse.core.expressions.EvaluationResult;
import org.eclipse.core.expressions.Expression;
import org.eclipse.core.expressions.IEvaluationContext;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.text.TextSelection;
import org.eclipse.swt.SWT;
import org.eclipse.xtext.nodemodel.ILeafNode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.XtextEditor;
import org.eclipse.xtext.util.concurrent.IUnitOfWork;

import de.cau.cs.kieler.core.kivi.AbstractCombination;
import de.cau.cs.kieler.core.kivi.menu.ButtonTrigger.ButtonState;
import de.cau.cs.kieler.core.kivi.menu.KiviMenuContributionService;
import de.cau.cs.kieler.core.kivi.menu.KiviMenuContributionService.LocationScheme;
import de.cau.cs.kieler.klighd.effects.KlighdDiagramEffect;

/**
 * A combination for initializing/refreshing of KLighD views of Xtext-based models.
 * 
 * @author chsch
 */
public class VisualizeSelectedModelElementCombination extends AbstractCombination {

    static String condClassId = "de.menges.logic.logic.Conditional";
    static String smClassId = "de.menges.logic.logic.StateMachine";

    static String condButtonId = "de.cau.cs.kieler.visualizeModelElementC";
    static String smButtonId = "de.cau.cs.kieler.visualizeModelElementSM";

    String editorId;
    Object resource;

    /**
     * Constructor.
     */
    public VisualizeSelectedModelElementCombination() {
        Expression visibilityExpression;
        try {
            visibilityExpression = new TestModelElementExpression(Class.forName(condClassId));

            KiviMenuContributionService.INSTANCE.addToolbarButton(this, condButtonId,
                    "Draw Conditional", "Test", null, SWT.PUSH, LocationScheme.POPUP,
                    visibilityExpression, "de.menges.logic.Logic");

            visibilityExpression = new TestModelElementExpression(Class.forName(smClassId));

            KiviMenuContributionService.INSTANCE.addToolbarButton(this, smButtonId,
                    "Draw StateMachine", "Test", null, SWT.PUSH, LocationScheme.POPUP,
                    visibilityExpression, "de.menges.logic.Logic");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void setEditorId(String id) {
        this.editorId = id;
    }

    public void setResource(Object resource) {
        this.resource = resource;
    }

    /**
     * The execute method revealed and invoked by KIVi.
     * 
     * @param button
     *            A {@link de.cau.cs.kieler.core.kivi.ITriggerState} carrying the necessary
     *            information.
     * 
     */
    public void execute(final ButtonState button) {
        // TODO name angeben
        if (editorId != null && resource != null) {
            this.schedule(new KlighdDiagramEffect(editorId, resource));
        }
    }

    /**
     * 
     * @author chsch
     */
    private class TestModelElementExpression extends Expression {

        private Class<?> clazz;

        TestModelElementExpression(final Class<?> theClazz) {
            this.clazz = theClazz;
        }

        @Override
        public EvaluationResult evaluate(final IEvaluationContext context) throws CoreException {
            Boolean result = false;
            try {
                Object part = context.getVariable("activePart");
                if(!(part instanceof XtextEditor)){
                    return EvaluationResult.valueOf(result);
                }
                final XtextEditor editor = (XtextEditor) part;
                // FileEditorInput input = (FileEditorInput)
                // context.getVariable("activeEditorInput");
                // final TextSelection selection = (TextSelection) context.getVariable("selection");
                result = editor.getDocument().readOnly(new IUnitOfWork<Boolean, XtextResource>() {

                    public Boolean exec(final XtextResource state) throws Exception {
                        setEditorId(state.getURI().toPlatformString(false));
                        TextSelection selection = (TextSelection) editor.getSelectionProvider()
                                .getSelection();
                        ILeafNode leaf = NodeModelUtils.findLeafNodeAtOffset(state.getParseResult()
                                .getRootNode(), selection.getOffset());
                        EObject eo = leaf.getSemanticElement();
                        setResource(eo);
                        return TestModelElementExpression.this.clazz.isInstance(eo);
                    }

                });
            } catch (ClassCastException e) {
                /* nothing */
            }
            return EvaluationResult.valueOf(result);
        }
    }
}