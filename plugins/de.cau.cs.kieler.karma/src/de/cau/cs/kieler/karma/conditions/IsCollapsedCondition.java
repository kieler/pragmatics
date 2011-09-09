/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2009 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */

package de.cau.cs.kieler.karma.conditions;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IResizableCompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.TopGraphicEditPart;
import org.eclipse.gmf.runtime.diagram.ui.figures.ResizableCompartmentFigure;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.ui.IEditorPart;

import de.cau.cs.kieler.core.model.gmf.GmfFrameworkBridge;
import de.cau.cs.kieler.core.ui.util.EditorUtils;

/**
 * Condition for evaluating whether a model element is in a collapsed state in the opened diagram. 
 * 
 * @author ckru
 */
public class IsCollapsedCondition extends IEditPartSensitiveCondition<EObject> {
    
    /**
     * {@inheritDoc}
     */
    public boolean evaluate(final EObject object) {
        EditPart objectEditPart = this.getEditPart();
        if (objectEditPart == null) {
            GmfFrameworkBridge gmfBridge = new GmfFrameworkBridge();
            objectEditPart = gmfBridge.getEditPart(object);
            if (objectEditPart == null) {
                IEditorPart editorPart = EditorUtils.getLastActiveEditor();
                if ((editorPart != null) && (editorPart instanceof DiagramDocumentEditor)) {
                    DiagramDocumentEditor dde = (DiagramDocumentEditor) editorPart;
                    objectEditPart = dde.getDiagramEditPart().findEditPart(
                            dde.getDiagramEditPart(), object);
                }
            }
        }
        
        boolean collapsed = this.checkCollapsed(objectEditPart);
        return collapsed;
    }

    /**
     * Method to check if all of the compartments are collapsed.
     * @param part the editPart whose compartments to check
     * @return true if all compartments are collapsed else false
     */
    private boolean checkCollapsed(final EditPart part) {
        if (part instanceof TopGraphicEditPart) {
            TopGraphicEditPart ep = (TopGraphicEditPart) part;
            @SuppressWarnings("unchecked")
            List<EditPart> resizeableCompartments = ep.getResizableCompartments();
            for (EditPart compartment : resizeableCompartments) {
                if (compartment instanceof IResizableCompartmentEditPart) {
                    IResizableCompartmentEditPart resizeComp = (IResizableCompartmentEditPart)
                            compartment;
                    if (resizeComp.getFigure() instanceof ResizableCompartmentFigure) {
                        ResizableCompartmentFigure f = (ResizableCompartmentFigure)
                                resizeComp.getFigure();
                        boolean expanded = f.isExpanded();
                        if (expanded) {
                            return false;
                        }
                    }
                }
            }
            return true;
        }
        return false;
    }

    
}
