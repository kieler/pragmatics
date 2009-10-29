/**
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
 * 
 *****************************************************************************/
package de.cau.cs.kieler.ksbase.viewmanagement.triggers;

import org.eclipse.gef.EditPart;

import de.cau.cs.kieler.viewmanagement.ATrigger;
import de.cau.cs.kieler.viewmanagement.TriggerEventObject;
import de.cau.cs.kieler.ksbase.ui.TransformationUIManager;
import de.cau.cs.kieler.ksbase.ui.listener.ITransformationEventListener;

/**
 * A viewmanagement trigger which is called when a transformation command has
 * been executed.
 * 
 * @author Michael Matzen - mim AT informatik.uni-kiel.de
 * 
 */
public class KSBasETrigger extends ATrigger implements
        ITransformationEventListener {

    /** The trigger event object. **/
    private TriggerEventObject triggerObject;

    /**
     * Creates a new KSbasETrigger and adds itself to the list of
     * postTransformationListeners in the TransformationUIManager.
     */
    public KSBasETrigger() {
        // Add to post transformation queue
        TransformationUIManager.INSTANCE.addPostTransformationListener(this);
    }

    /**
     * Unregisters from the postTransformationListener.
     */
    @Override
    public void finalize() {
        super.finalize();
        // Remove from post transformation queue
        TransformationUIManager.INSTANCE.removePostTransformationListener(this);
    }

    /**
     * Called when a transformation is executed.
     * @param args The arguments for this event. Needs be the Array : [EditPart,EditorPart]
     */
    public void transformationExecuted(final Object[] args) {
        if (args.length == 2 && args[0] instanceof EditPart) {
            this.triggerObject = new TriggerEventObject();
            triggerObject.setAffectedObject(translateToURI(args[0]));
            triggerObject.setParameters(args[1]);
            triggerObject.setTriggerActive(true);
            notifyTrigger(triggerObject);
        }
    }

}
