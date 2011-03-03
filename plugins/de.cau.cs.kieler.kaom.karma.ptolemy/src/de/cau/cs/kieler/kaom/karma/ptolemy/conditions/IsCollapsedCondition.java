package de.cau.cs.kieler.kaom.karma.ptolemy.conditions;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IResizableCompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.TopGraphicEditPart;
import org.eclipse.gmf.runtime.diagram.ui.figures.ResizableCompartmentFigure;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.ui.IEditorPart;

import de.cau.cs.kieler.core.ui.util.EditorUtils;
import de.cau.cs.kieler.karma.ICustomCondition;

public class IsCollapsedCondition extends ICustomCondition<EObject> {

    public boolean evaluate(EObject object) {
        IEditorPart editorPart = EditorUtils.getLastActiveEditor();
        if ((editorPart != null) && (editorPart instanceof DiagramDocumentEditor)) {
            DiagramDocumentEditor dde = (DiagramDocumentEditor) editorPart;
            EditPart objectEditPart = dde.getDiagramEditPart().findEditPart(
                    dde.getDiagramEditPart(), object);
            boolean collapsed = this.checkCollapsed(objectEditPart);
            return collapsed;
        }
        
        return false;
    }

    /**
     * Method to check if all of the compartments are collapsed.
     * @param part the editPart whose compartments to check
     * @return true if all compartments are collapsed else false
     */
    private boolean checkCollapsed(final EditPart part) {
        if (part instanceof TopGraphicEditPart) {
            TopGraphicEditPart ep = (TopGraphicEditPart) part;
            List<EditPart> resizeableCompartments = ep.getResizableCompartments();
            for (EditPart compartment : resizeableCompartments) {
                if (compartment instanceof IResizableCompartmentEditPart) {
                    IResizableCompartmentEditPart resizeComp = (IResizableCompartmentEditPart) compartment;
                    if(resizeComp.getFigure() instanceof ResizableCompartmentFigure) {
                        ResizableCompartmentFigure f = (ResizableCompartmentFigure) resizeComp.getFigure();
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
