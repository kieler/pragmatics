package de.cau.cs.kieler.ksbase.layout;

import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeEditPart;
import org.eclipse.ui.IEditorPart;

import de.cau.cs.kieler.kiml.ui.layout.DiagramLayoutManager;
import de.cau.cs.kieler.viewmanagement.AEffect;

public class AutoLayoutEffect extends AEffect {

    
    EditPart editPart;
    IEditorPart editorPart;
    
    @Override
    public void execute() {
        DiagramLayoutManager.layout(editorPart, editPart , true, false);
    }

    @Override
    public void setParameters(Object parameters) {
        if (parameters instanceof IEditorPart)
            this.editorPart = (IEditorPart)parameters;
    }

    @Override
    public void setTarget(ShapeEditPart target) {
        this.editPart = (EditPart)target;
    }
    

}
