package de.cau.cs.kieler.graphs.diagram.edit.policies;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.eclipse.gmf.runtime.diagram.ui.editpolicies.CanonicalEditPolicy;
import org.eclipse.gmf.runtime.notation.View;

import de.cau.cs.kieler.core.kgraph.KGraphPackage;
import de.cau.cs.kieler.graphs.diagram.edit.parts.PortEditPart;
import de.cau.cs.kieler.graphs.diagram.part.GraphsDiagramUpdater;
import de.cau.cs.kieler.graphs.diagram.part.GraphsNodeDescriptor;
import de.cau.cs.kieler.graphs.diagram.part.GraphsVisualIDRegistry;

/**
 * @generated
 */
public class Node4CanonicalEditPolicy extends CanonicalEditPolicy {

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
        for (Iterator it =
                GraphsDiagramUpdater.getNode_3001SemanticChildren(viewObject)
                        .iterator(); it.hasNext();) {
            result.add(((GraphsNodeDescriptor) it.next()).getModelElement());
        }
        return result;
    }

    /**
     * @generated
     */
    protected boolean isOrphaned(Collection semanticChildren, final View view) {
        int visualID = GraphsVisualIDRegistry.getVisualID(view);
        switch (visualID) {
        case PortEditPart.VISUAL_ID:
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
            myFeaturesToSynchronize.add(KGraphPackage.eINSTANCE
                    .getKNode_Ports());
        }
        return myFeaturesToSynchronize;
    }

}
