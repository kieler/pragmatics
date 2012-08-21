/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
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
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.text.TextSelection;
import org.eclipse.swt.SWT;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.statushandlers.StatusManager;
import org.eclipse.xtext.nodemodel.ILeafNode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.XtextEditor;
import org.eclipse.xtext.util.concurrent.IUnitOfWork;

import de.cau.cs.kieler.core.kivi.AbstractCombination;
import de.cau.cs.kieler.core.kivi.menu.KiviMenuContributionService;
import de.cau.cs.kieler.core.kivi.menu.ButtonTrigger.ButtonState;
import de.cau.cs.kieler.core.kivi.menu.KiviMenuContributionService.LocationScheme;
import de.cau.cs.kieler.klighd.effects.KlighdDiagramEffect;

/**
 * An abstract {@link de.cau.cs.kieler.core.kivi.ICombination} for visualizing a chosen element in
 * an Xtext based editor. This class is to be subclassed for concrete  
 * Views initiated by this combination are shared with
 * {@link UpdateXtextModelKLighDCombination} since they use the compute the (secondary) view ids the
 * same way.
 * 
 * @author chsch
 */
public abstract class VisualizeChosenElementCombination extends AbstractCombination {

    private String className = null;
    private String elementName = null;
    private String buttonId = null;
    private ModelExpression visibilityExpression;
    
    /**
     * Default constructor. Must be overridden in concrete subclasses like:<br>
     * <br>
     * public VisualizeConcreteContentCombination() {<br>
     * &nbsp; &nbsp; &nbsp; super(className, elementName, validEditors...);<br>
     * }<br>
     */
    protected VisualizeChosenElementCombination() {
    }
    
    /**
     * Constructor.
     * 
     * @param theClassName class name of elements to be visualized
     * @param theElementName a human friendly name of the elements
     * @param theValidEditors ids of editors for which this menu contribution are intended
     * (id is defined in the editor introducing extension) 
     */
    public VisualizeChosenElementCombination(final String theClassName, final String theElementName,
            final String... theValidEditors) {
        this.className = theClassName;
        this.elementName = theElementName;
        this.buttonId = KLighDXtextPlugin.PLUGIN_ID + ".visualizeChosenElement." + theClassName;
        
        try {
            visibilityExpression = new ModelExpression(Class.forName(this.className));

            KiviMenuContributionService.INSTANCE.addToolbarButton(this, this.buttonId, "Draw "
                    + this.elementName + " diagram", "draws the selected " + this.elementName
                    + " in a diagram", null, SWT.PUSH, LocationScheme.POPUP, visibilityExpression,
                    null, null, theValidEditors);

        } catch (ClassNotFoundException e) {
            final String msg = "Class " + theClassName + " mentioned in "
                    + this.getClass().getCanonicalName()
                    + " in a KIVi menu contribution definition could not be found."
                    + " Is the (generated) code available on classpath?";
            StatusManager.getManager().handle(
                    new Status(IStatus.ERROR, KLighDXtextPlugin.PLUGIN_ID, msg, e));
        }
    }

    /**
     * The execute method revealed and invoked by KIVi.
     * 
     * @param buttonState
     *            A {@link de.cau.cs.kieler.core.kivi.ITriggerState} carrying the necessary
     *            information.
     * 
     */
    public void execute(final ButtonState buttonState) {
        if (buttonState.getButtonId().equals(buttonId)) {
            if (visibilityExpression.getEditorInputPath() != null
                    && visibilityExpression.getElement() != null) {
                this.schedule(new KlighdDiagramEffect(visibilityExpression.getEditorInputPath()
                        .toOSString().replace(":", ""), visibilityExpression.getEditorInputPath()
                        .lastSegment(), visibilityExpression.getElement(), buttonState.getEditor()));
            } else {
                // this case is needed if the buttonSate was fired by an Xtext quickfix provider
                if (buttonState.getEditor() != null
                        && buttonState.getParameters().get("element") != null) {
                    IPath editorInputPath = ((FileEditorInput) buttonState.getEditor()
                            .getEditorInput()).getPath();
                    this.schedule(new KlighdDiagramEffect(editorInputPath.toOSString().replace(":",
                            ""), editorInputPath.lastSegment(), (EObject) buttonState
                            .getParameters().get("element"), buttonState.getEditor()));
                }
            }
        }
    }
    
    
    /**
     * Expression for Xtext-based models.
     * 
     * @author chsch, pkl
     */
    private static class ModelExpression extends Expression {

        private Class<?> clazz = null;
        private EObject element = null;
        private IPath editorInputPath = null;

        ModelExpression(final Class<?> theClazz) {
            this.clazz = theClazz;
        }

        /**
         * Has some sideeffects, resource and editor are filtered and saved at the combination.
         * 
         * @param context
         *            , {@link IEvaluationContext}
         * @throws CoreException
         * @return {@link EvaluationResult}
         */
        @Override
        public EvaluationResult evaluate(final IEvaluationContext context) throws CoreException {
            Boolean result = false;
            try {
                Object part = context.getVariable("activePart");
                if (!(part instanceof XtextEditor)) {
                    return EvaluationResult.valueOf(false);
                }
                final XtextEditor editor = (XtextEditor) part;
                
                // old:
                // FileEditorInput input = (FileEditorInput)
                // context.getVariable("activeEditorInput");
                // final TextSelection selection = (TextSelection) context.getVariable("selection");

                if (editor.getEditorInput().getClass().equals(FileEditorInput.class)
                        && ((FileEditorInput) editor.getEditorInput()).getFile() != null
                        && ((FileEditorInput) editor.getEditorInput()).getFile()
                                .getLocationURI() != null) {
                    this.editorInputPath = ((FileEditorInput) editor.getEditorInput()).getPath();
                }
                final TextSelection selection = (TextSelection) editor.getSelectionProvider()
                        .getSelection();
                
                result = editor.getDocument().readOnly(new IUnitOfWork<Boolean, XtextResource>() {
                    public Boolean exec(final XtextResource state) throws Exception {
                        ILeafNode leaf = NodeModelUtils.findLeafNodeAtOffset(state.getParseResult()
                                .getRootNode(), selection.getOffset());
                        if (leaf == null) {
                            return false;
                        } else {
                            EObject o = leaf.getSemanticElement();
                            setElement(o);
                            return ModelExpression.this.clazz.isInstance(o);
                        }
                    }
                });
            } catch (ClassCastException e) {
                StatusManager.getManager().handle(
                        new Status(IStatus.ERROR, KLighDXtextPlugin.PLUGIN_ID,
                                "Semantic element could not be identified", e));
            }
            return EvaluationResult.valueOf(result);
        }

        /**
         * Setter for resource.
         * 
         * @param eo
         *            , {@link EObject}
         */
        public void setElement(final EObject eo) {
            this.element = eo;
        }

        /**
         * Reveals the path of the denoted editor's {@link org.eclipse.ui.IEditorInput}. That
         * {@link org.eclipse.ui.IEditorInput} is assumed to be a
         * {@link org.eclipse.ui.part.FileEditorInput}.
         * 
         * @return the {@link org.eclipse.core.runtime.IPath} of the denoted editor's
         *         {@link org.eclipse.ui.IEditorInput}.
         */
        public IPath getEditorInputPath() {
            return this.editorInputPath;
        }

        /**
         * Getter for the resource.
         * 
         * @return resource, {@link EObject}
         */
        public EObject getElement() {
            return this.element;
        }
    }
}
