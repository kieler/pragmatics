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
package de.cau.cs.kieler.ksbase.ui.kivi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.CanonicalEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.ui.IEditorPart;

import de.cau.cs.kieler.core.kivi.AbstractCombination;
import de.cau.cs.kieler.core.kivi.AbstractEffect;
import de.cau.cs.kieler.core.kivi.menu.ButtonTrigger.ButtonState;
import de.cau.cs.kieler.core.model.m2m.TransformationDescriptor;
import de.cau.cs.kieler.core.model.xtend.m2m.XtendTransformationContext;
import de.cau.cs.kieler.core.model.xtend.m2m.XtendTransformationEffect;
import de.cau.cs.kieler.kiml.ui.diagram.LayoutEffect;
import de.cau.cs.kieler.ksbase.core.EditorTransformationSettings;
import de.cau.cs.kieler.ksbase.core.KSBasETransformation;
import de.cau.cs.kieler.ksbase.core.TransformationFrameworkFactory;

/**
 * A Combination triggering the KSBasE transformations from kivi menu contributions.
 * 
 * @author ckru
 * 
 */
public class KSBasECombination extends AbstractCombination {

    private EditorTransformationSettings editorSettings;

    private HashMap<String, KSBasETransformation> transformations = 
            new HashMap<String, KSBasETransformation>();

    /**
     * @param editorSettings the KSBasE editor settings used as a context for the transformation.
     */
    public KSBasECombination(final EditorTransformationSettings editorSettings) {
        this.editorSettings = editorSettings;
        this.setActive(true);
    }

    /**
     * Add a transformation to the combination.
     * @param buttonID the id to identify the transformation
     * @param transformation the transformation to add
     */
    public void addTransformation(final String buttonID, final KSBasETransformation transformation) {
        transformations.put(buttonID, transformation);
    }

    /**
     * {@inheritDoc}
     */
    public void execute(final ButtonState button) {
        KSBasETransformation transformation = transformations.get(button.getButtonId());
        if (transformation != null) {
            IEditorPart editor = button.getEditor();
            final DiagramDocumentEditor diagramEditor = (DiagramDocumentEditor) editor;
            List<EditPart> selectedParts = diagramEditor.getDiagramGraphicalViewer()
                    .getSelectedEditParts();
            EditPart root = diagramEditor.getDiagramGraphicalViewer().getRootEditPart();
            IGraphicalEditPart groot = (IGraphicalEditPart) root.getChildren().get(0);
            EObject rootObject = groot.getNotationView().getElement();
            
            //get the current selection
            List<EObject> selectionList = new ArrayList<EObject>();
            for (EditPart part : selectedParts) {
                if (part instanceof IGraphicalEditPart) {
                    IGraphicalEditPart gpart = (IGraphicalEditPart) part;
                    selectionList.add(gpart.getNotationView().getElement());
                }
            }
            //if the selection is empty assume the root object as selected
            if (selectionList.isEmpty()) {
                selectionList.add(rootObject);
            }
            
            //map the selection to the parameters of this transformation
            List<Object> selectionMapping = null;
            for (List<String> parameters : transformation.getParameterList()) {
                selectionMapping = TransformationFrameworkFactory
                    .getDefaultTransformationFramework()
                    .createParameterMapping(selectionList, parameters.toArray(new String[parameters.size()]));
            }
            
            if (selectionMapping != null) {
                TransformationDescriptor descriptor = new TransformationDescriptor(
                        transformation.getTransformation(), selectionMapping.toArray());
                XtendTransformationContext context = new XtendTransformationContext(
                        editorSettings.getTransformationFile(), editorSettings.getModelPackages()
                                .toArray(new String[editorSettings.getModelPackages().size()]), null,
                        diagramEditor.getEditingDomain());
                XtendTransformationEffect effect = new XtendTransformationEffect(context, descriptor);
                effect.schedule();
    
                //refresh edit policy to display the new stuff
                AbstractEffect refresh = new AbstractEffect() {
                    public void execute() {
                        CanonicalEditPolicy policy = (CanonicalEditPolicy) diagramEditor
                                .getDiagramEditPart().getEditPolicy("Canonical");
                        if (policy != null) {
                            policy.refresh();
                        }
                    }
                };
                refresh.schedule();
    

                //in the end do some layout
                LayoutEffect layout = null;
                if (selectionList.get(0) == rootObject) {
                    layout = new LayoutEffect(button.getEditor(),
                            ((EObject) rootObject), false);
                } else {
                    layout = new LayoutEffect(button.getEditor(),
                            selectionList.get(0).eContainer(), false);
                }
                layout.schedule();
                
            }
        }
    }

}
