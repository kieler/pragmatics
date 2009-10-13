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
package de.cau.cs.kieler.dataflow.diagram.edit.policies;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.eclipse.gmf.runtime.diagram.ui.editpolicies.CanonicalEditPolicy;
import org.eclipse.gmf.runtime.notation.View;

import de.cau.cs.kieler.dataflow.DataflowPackage;
import de.cau.cs.kieler.dataflow.diagram.edit.parts.InputPortEditPart;
import de.cau.cs.kieler.dataflow.diagram.edit.parts.OutputPortEditPart;
import de.cau.cs.kieler.dataflow.diagram.part.DataflowDiagramUpdater;
import de.cau.cs.kieler.dataflow.diagram.part.DataflowNodeDescriptor;
import de.cau.cs.kieler.dataflow.diagram.part.DataflowVisualIDRegistry;

/**
 * @generated
 */
public class BoxCanonicalEditPolicy extends CanonicalEditPolicy {

    /**
     * @generated
     */
    Set myFeaturesToSynchronize;

    /**
     * @generated
     */
    protected List getSemanticChildrenList() {
        View viewObject = (View) getHost().getModel();
        List result = new LinkedList();
        for (Iterator it = DataflowDiagramUpdater.getBox_2001SemanticChildren(viewObject)
                .iterator(); it.hasNext();) {
            result.add(((DataflowNodeDescriptor) it.next()).getModelElement());
        }
        return result;
    }

    /**
     * @generated
     */
    protected boolean isOrphaned(Collection semanticChildren, final View view) {
        int visualID = DataflowVisualIDRegistry.getVisualID(view);
        switch (visualID) {
        case InputPortEditPart.VISUAL_ID:
        case OutputPortEditPart.VISUAL_ID:
            if (!semanticChildren.contains(view.getElement())) {
                return true;
            }
        }
        return false;
    }

    /**
     * @generated
     */
    protected String getDefaultFactoryHint() {
        return null;
    }

    /**
     * @generated
     */
    protected Set getFeaturesToSynchronize() {
        if (myFeaturesToSynchronize == null) {
            myFeaturesToSynchronize = new HashSet();
            myFeaturesToSynchronize.add(DataflowPackage.eINSTANCE.getBox_Inputs());
            myFeaturesToSynchronize.add(DataflowPackage.eINSTANCE.getBox_Outputs());
        }
        return myFeaturesToSynchronize;
    }

}
