package de.cau.cs.kieler.kaom.diagram.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;

import de.cau.cs.kieler.kaom.diagram.providers.KaomElementTypes;

/**
 * @generated
 */
public class LinkItemSemanticEditPolicy extends KaomBaseItemSemanticEditPolicy {

    /**
     * @generated
     */
    public LinkItemSemanticEditPolicy() {
        super(KaomElementTypes.Link_4001);
    }

    /**
     * @generated
     */
    protected Command getDestroyElementCommand(DestroyElementRequest req) {
        return getGEFWrapper(new DestroyElementCommand(req));
    }

}
