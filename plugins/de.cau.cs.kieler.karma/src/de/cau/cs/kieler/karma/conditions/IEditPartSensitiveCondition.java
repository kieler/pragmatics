package de.cau.cs.kieler.karma.conditions;

import org.eclipse.gef.EditPart;

import de.cau.cs.kieler.karma.ICustomCondition;

public abstract class IEditPartSensitiveCondition<T> extends ICustomCondition<T> {

    private EditPart editPart;
    
    public void setEditPart(EditPart editPart) {
        this.editPart = editPart;
    }
    
    public EditPart getEditPart() {
        return this.editPart;
    }
    
}
