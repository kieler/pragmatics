package de.cau.cs.kieler.graphs.diagram.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;

import de.cau.cs.kieler.graphs.diagram.providers.GraphsElementTypes;

/**
 * @generated
 */
public class Edge7ItemSemanticEditPolicy extends
        GraphsBaseItemSemanticEditPolicy {

    /**
     * @generated
     */
    public Edge7ItemSemanticEditPolicy() {
        super(GraphsElementTypes.Edge_4007);
    }

    /**
     * @generated
     */
    protected Command getDestroyElementCommand(DestroyElementRequest req) {
        return getGEFWrapper(new DestroyElementCommand(req));
    }

}
