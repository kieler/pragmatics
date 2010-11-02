package de.cau.cs.kieler.keg.diagram.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;

import de.cau.cs.kieler.keg.diagram.providers.GraphsElementTypes;

/**
 * @generated
 */
public class Edge2ItemSemanticEditPolicy extends GraphsBaseItemSemanticEditPolicy {

    /**
     * @generated
     */
    public Edge2ItemSemanticEditPolicy() {
        super(GraphsElementTypes.Edge_4002);
    }

    /**
     * @generated
     */
    protected Command getDestroyElementCommand(DestroyElementRequest req) {
        return getGEFWrapper(new DestroyElementCommand(req));
    }

}
