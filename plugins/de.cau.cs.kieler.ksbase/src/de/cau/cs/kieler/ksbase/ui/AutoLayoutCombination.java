package de.cau.cs.kieler.ksbase.ui;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeEditPart;
import org.eclipse.ui.IEditorPart;

import de.cau.cs.kieler.viewmanagement.ACombination;
import de.cau.cs.kieler.viewmanagement.ATrigger;
import de.cau.cs.kieler.viewmanagement.RunLogic;
import de.cau.cs.kieler.viewmanagement.TriggerEventObject;

public class AutoLayoutCombination extends ACombination {

    AutoLayoutTrigger trigger;
    AutoLayoutEffect effect;
    ShapeEditPart targetEditPart;
    IEditorPart activeEditorPart;
    
    @Override
    public boolean evaluate(TriggerEventObject triggerEvent) {
        EditPart affectedObject = translateToEditPart(triggerEvent
                .getAffectedObject(), parent);
        if (affectedObject instanceof ShapeEditPart) {
            this.targetEditPart = (ShapeEditPart)affectedObject;
            //Add editorPart if supplied
            if (triggerEvent.getParameters() instanceof IEditorPart)
                this.activeEditorPart = (IEditorPart) triggerEvent.getParameters();
            
            return true;
        } else
            return false;
    }

    @Override
    public void execute() {
        if (effect == null)
            effect = new AutoLayoutEffect();
        effect.setTarget( targetEditPart );
        effect.setParameters(activeEditorPart);
        effect.execute();
        
    }

    @Override
    public List<ATrigger> getTriggers() {
        this.trigger = (AutoLayoutTrigger) RunLogic
                .getTrigger("de.cau.cs.kieler.ksbase.AutoLayoutTrigger");
        List<ATrigger> triggerList = new ArrayList<ATrigger>();
        triggerList.add(trigger);
        return triggerList;
    }

    @Override
    public void undoLastEffect() {
        //nothing we can undo here
    }

}
