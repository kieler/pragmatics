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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequest;

import de.cau.cs.kieler.core.kivi.AbstractCombination;
//import de.cau.cs.kieler.core.model.trigger.ModelChangeTrigger.ModelChangeState;
import de.cau.cs.kieler.ksbase.ui.kivi.KSBasETrigger.KSBasEState;

/**
 * A Combination handling KSBasE-related effects after transformations.
 * 
 * @author mmu
 * 
 */
public class KSBasECombination extends AbstractCombination {

    public void execute(final KSBasEState ksbase/*, ModelChangeState change*/) {
//        System.out.println("== NEW KSBASE COMBINATION ==");
//        System.out.println("Event: "+change.getChange());
//        for (Notification notification : change.getChange().getNotifications()) {
//            System.out.println(notification);
//            if(notification.getEventType() == Notification.ADD && notification.getNewValue() instanceof EObject){
//                EObject newObject = (EObject) notification.getNewValue();
//                System.out.println("NEW: "+newObject);
//             //   CreateViewRequest.ViewDescriptor viewDesc = new CreateViewRequest.ViewDescriptor();
//             //   CreateViewRequest request = new CreateViewRequest();
//            }
//        }
    }

}
