package de.cau.cs.kieler.kaom.diagram.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;

import de.cau.cs.kieler.kaom.diagram.edit.commands.Entity2CreateCommand;
import de.cau.cs.kieler.kaom.diagram.edit.commands.Relation2CreateCommand;
import de.cau.cs.kieler.kaom.diagram.providers.KaomElementTypes;

/**
 * @generated
 */
public class EntityEntityCompartment2ItemSemanticEditPolicy extends KaomBaseItemSemanticEditPolicy {

    /**
     * @generated
     */
    public EntityEntityCompartment2ItemSemanticEditPolicy() {
        super(KaomElementTypes.Entity_3002);
    }

    /**
     * @generated
     */
    protected Command getCreateCommand(CreateElementRequest req) {
        if (KaomElementTypes.Entity_3002 == req.getElementType()) {
            return getGEFWrapper(new Entity2CreateCommand(req));
        }
        if (KaomElementTypes.Relation_3003 == req.getElementType()) {
            return getGEFWrapper(new Relation2CreateCommand(req));
        }
        return super.getCreateCommand(req);
    }

}
