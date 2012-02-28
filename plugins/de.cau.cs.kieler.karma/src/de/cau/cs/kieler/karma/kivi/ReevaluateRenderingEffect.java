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
 * 
 *****************************************************************************/
package de.cau.cs.kieler.karma.kivi;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;

import de.cau.cs.kieler.core.kivi.AbstractEffect;
import de.cau.cs.kieler.core.model.gmf.GmfFrameworkBridge;
import de.cau.cs.kieler.core.model.gmf.IAdvancedRenderingEditPart;

/**
 *
 * This effect requests a reevaluation of the rendering associated with a given model element.
 * 
 * @author ckru
 *
 */
public class ReevaluateRenderingEffect extends AbstractEffect {

    IAdvancedRenderingEditPart part = null;
    
    EObject modelElement = null;
    
    public ReevaluateRenderingEffect(EObject modelElement) {
        this.modelElement = modelElement;
        EditPart p = new GmfFrameworkBridge().getEditPart(modelElement);
        if (p instanceof IAdvancedRenderingEditPart) {
            this.part = (IAdvancedRenderingEditPart) p;
        }
    }
    
    public void execute() {
        part.updateFigure(part.getPrimaryShape());
    }

}
