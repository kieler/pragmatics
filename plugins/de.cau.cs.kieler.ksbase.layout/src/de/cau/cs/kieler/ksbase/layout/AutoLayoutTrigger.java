package de.cau.cs.kieler.ksbase.layout;
import org.eclipse.gef.EditPart;
import org.eclipse.ui.IEditorPart;

import de.cau.cs.kieler.viewmanagement.ATrigger;
import de.cau.cs.kieler.viewmanagement.TriggerEventObject;


public class AutoLayoutTrigger extends ATrigger {

    TriggerEventObject triggerEvent;

    public void triggerAutoLayout(EditPart editPart, IEditorPart editorPart) {
        triggerEvent = new TriggerEventObject();
        triggerEvent.setTriggerActive(true);
        triggerEvent.setAffectedObject( translateToURI(editPart) );
        triggerEvent.setParameters(editorPart );
        notifyTrigger(triggerEvent);
    }

    public void finalize()
    {
        //have to implement this, but it's useless here
    }
}
