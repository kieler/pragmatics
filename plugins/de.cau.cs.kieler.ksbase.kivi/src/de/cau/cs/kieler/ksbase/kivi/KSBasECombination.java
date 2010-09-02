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
package de.cau.cs.kieler.ksbase.kivi;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;

import de.cau.cs.kieler.kivi.core.Viewmanagement;
import de.cau.cs.kieler.kivi.core.impl.AbstractCombination;

/**
 * A Combination handling KSBasE-related effects after transformations.
 * 
 * @author mmu
 * 
 */
public class KSBasECombination extends AbstractCombination {
    
    private EObject selection;
    
    private DiagramEditor diagramEditor;

    @Override
    public void execute() {
        EditPart eP = Viewmanagement.findEditPart(diagramEditor.getDiagramEditPart(), selection);
        Viewmanagement.getInstance().executeEffect(new LayoutEffect(diagramEditor, eP));
    }
    
    /**
     * Listen to KSBasETriggers and save the data of such an event.
     * 
     * @param trigger the trigger containing selection and editor
     * @return true, always execute after trigger
     */
    public boolean evaluate(final KSBasETrigger trigger) {
        if (trigger.getEditorPart() instanceof DiagramEditor) {
            selection = trigger.getSelection();
            diagramEditor = (DiagramEditor) trigger.getEditorPart();
            return true;            
        } else {
            return false;
        }
        
    }

}
