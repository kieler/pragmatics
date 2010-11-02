package de.cau.cs.kieler.keg.diagram.edit.policies;

import java.util.Iterator;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand;
import org.eclipse.gmf.runtime.emf.commands.core.command.CompositeTransactionalCommand;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRelationshipRequest;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.View;

import de.cau.cs.kieler.keg.diagram.edit.commands.Edge3CreateCommand;
import de.cau.cs.kieler.keg.diagram.edit.commands.Edge3ReorientCommand;
import de.cau.cs.kieler.keg.diagram.edit.commands.Edge4CreateCommand;
import de.cau.cs.kieler.keg.diagram.edit.commands.Edge4ReorientCommand;
import de.cau.cs.kieler.keg.diagram.edit.commands.Edge5CreateCommand;
import de.cau.cs.kieler.keg.diagram.edit.commands.Edge5ReorientCommand;
import de.cau.cs.kieler.keg.diagram.edit.commands.Edge6CreateCommand;
import de.cau.cs.kieler.keg.diagram.edit.commands.Edge6ReorientCommand;
import de.cau.cs.kieler.keg.diagram.edit.commands.Edge7CreateCommand;
import de.cau.cs.kieler.keg.diagram.edit.commands.Edge7ReorientCommand;
import de.cau.cs.kieler.keg.diagram.edit.commands.Edge8CreateCommand;
import de.cau.cs.kieler.keg.diagram.edit.commands.Edge8ReorientCommand;
import de.cau.cs.kieler.keg.diagram.edit.parts.Edge3EditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.Edge4EditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.Edge5EditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.Edge6EditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.Edge7EditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.Edge8EditPart;
import de.cau.cs.kieler.keg.diagram.part.GraphsVisualIDRegistry;
import de.cau.cs.kieler.keg.diagram.providers.GraphsElementTypes;

/**
 * @generated
 */
public class PortItemSemanticEditPolicy extends GraphsBaseItemSemanticEditPolicy {

    /**
     * @generated
     */
    public PortItemSemanticEditPolicy() {
        super(GraphsElementTypes.Port_3002);
    }

    /**
     * @generated
     */
    protected Command getDestroyElementCommand(DestroyElementRequest req) {
        View view = (View) getHost().getModel();
        CompositeTransactionalCommand cmd = new CompositeTransactionalCommand(getEditingDomain(), null);
        cmd.setTransactionNestingEnabled(false);
        for (Iterator<?> it = view.getTargetEdges().iterator(); it.hasNext();) {
            Edge incomingLink = (Edge) it.next();
            if (GraphsVisualIDRegistry.getVisualID(incomingLink) == Edge3EditPart.VISUAL_ID) {
                DestroyElementRequest r = new DestroyElementRequest(incomingLink.getElement(), false);
                cmd.add(new DestroyElementCommand(r));
                cmd.add(new DeleteCommand(getEditingDomain(), incomingLink));
                continue;
            }
            if (GraphsVisualIDRegistry.getVisualID(incomingLink) == Edge4EditPart.VISUAL_ID) {
                DestroyElementRequest r = new DestroyElementRequest(incomingLink.getElement(), false);
                cmd.add(new DestroyElementCommand(r));
                cmd.add(new DeleteCommand(getEditingDomain(), incomingLink));
                continue;
            }
            if (GraphsVisualIDRegistry.getVisualID(incomingLink) == Edge7EditPart.VISUAL_ID) {
                DestroyElementRequest r = new DestroyElementRequest(incomingLink.getElement(), false);
                cmd.add(new DestroyElementCommand(r));
                cmd.add(new DeleteCommand(getEditingDomain(), incomingLink));
                continue;
            }
            if (GraphsVisualIDRegistry.getVisualID(incomingLink) == Edge8EditPart.VISUAL_ID) {
                DestroyElementRequest r = new DestroyElementRequest(incomingLink.getElement(), false);
                cmd.add(new DestroyElementCommand(r));
                cmd.add(new DeleteCommand(getEditingDomain(), incomingLink));
                continue;
            }
        }
        for (Iterator<?> it = view.getSourceEdges().iterator(); it.hasNext();) {
            Edge outgoingLink = (Edge) it.next();
            if (GraphsVisualIDRegistry.getVisualID(outgoingLink) == Edge3EditPart.VISUAL_ID) {
                DestroyElementRequest r = new DestroyElementRequest(outgoingLink.getElement(), false);
                cmd.add(new DestroyElementCommand(r));
                cmd.add(new DeleteCommand(getEditingDomain(), outgoingLink));
                continue;
            }
            if (GraphsVisualIDRegistry.getVisualID(outgoingLink) == Edge4EditPart.VISUAL_ID) {
                DestroyElementRequest r = new DestroyElementRequest(outgoingLink.getElement(), false);
                cmd.add(new DestroyElementCommand(r));
                cmd.add(new DeleteCommand(getEditingDomain(), outgoingLink));
                continue;
            }
            if (GraphsVisualIDRegistry.getVisualID(outgoingLink) == Edge5EditPart.VISUAL_ID) {
                DestroyElementRequest r = new DestroyElementRequest(outgoingLink.getElement(), false);
                cmd.add(new DestroyElementCommand(r));
                cmd.add(new DeleteCommand(getEditingDomain(), outgoingLink));
                continue;
            }
            if (GraphsVisualIDRegistry.getVisualID(outgoingLink) == Edge6EditPart.VISUAL_ID) {
                DestroyElementRequest r = new DestroyElementRequest(outgoingLink.getElement(), false);
                cmd.add(new DestroyElementCommand(r));
                cmd.add(new DeleteCommand(getEditingDomain(), outgoingLink));
                continue;
            }
        }
        EAnnotation annotation = view.getEAnnotation("Shortcut"); //$NON-NLS-1$
        if (annotation == null) {
            // there are indirectly referenced children, need extra commands: false
            addDestroyShortcutsCommand(cmd, view);
            // delete host element
            cmd.add(new DestroyElementCommand(req));
        } else {
            cmd.add(new DeleteCommand(getEditingDomain(), view));
        }
        return getGEFWrapper(cmd.reduce());
    }

    /**
     * @generated
     */
    protected Command getCreateRelationshipCommand(CreateRelationshipRequest req) {
        Command command = req.getTarget() == null ? getStartCreateRelationshipCommand(req)
                : getCompleteCreateRelationshipCommand(req);
        return command != null ? command : super.getCreateRelationshipCommand(req);
    }

    /**
     * @generated
     */
    protected Command getStartCreateRelationshipCommand(CreateRelationshipRequest req) {
        if (GraphsElementTypes.Edge_4003 == req.getElementType()) {
            return getGEFWrapper(new Edge3CreateCommand(req, req.getSource(), req.getTarget()));
        }
        if (GraphsElementTypes.Edge_4004 == req.getElementType()) {
            return getGEFWrapper(new Edge4CreateCommand(req, req.getSource(), req.getTarget()));
        }
        if (GraphsElementTypes.Edge_4005 == req.getElementType()) {
            return getGEFWrapper(new Edge5CreateCommand(req, req.getSource(), req.getTarget()));
        }
        if (GraphsElementTypes.Edge_4006 == req.getElementType()) {
            return getGEFWrapper(new Edge6CreateCommand(req, req.getSource(), req.getTarget()));
        }
        if (GraphsElementTypes.Edge_4007 == req.getElementType()) {
            return null;
        }
        if (GraphsElementTypes.Edge_4008 == req.getElementType()) {
            return null;
        }
        return null;
    }

    /**
     * @generated
     */
    protected Command getCompleteCreateRelationshipCommand(CreateRelationshipRequest req) {
        if (GraphsElementTypes.Edge_4003 == req.getElementType()) {
            return getGEFWrapper(new Edge3CreateCommand(req, req.getSource(), req.getTarget()));
        }
        if (GraphsElementTypes.Edge_4004 == req.getElementType()) {
            return getGEFWrapper(new Edge4CreateCommand(req, req.getSource(), req.getTarget()));
        }
        if (GraphsElementTypes.Edge_4005 == req.getElementType()) {
            return null;
        }
        if (GraphsElementTypes.Edge_4006 == req.getElementType()) {
            return null;
        }
        if (GraphsElementTypes.Edge_4007 == req.getElementType()) {
            return getGEFWrapper(new Edge7CreateCommand(req, req.getSource(), req.getTarget()));
        }
        if (GraphsElementTypes.Edge_4008 == req.getElementType()) {
            return getGEFWrapper(new Edge8CreateCommand(req, req.getSource(), req.getTarget()));
        }
        return null;
    }

    /**
     * Returns command to reorient EClass based link. New link target or source
     * should be the domain model element associated with this node.
     * 
     * @generated
     */
    protected Command getReorientRelationshipCommand(ReorientRelationshipRequest req) {
        switch (getVisualID(req)) {
        case Edge3EditPart.VISUAL_ID:
            return getGEFWrapper(new Edge3ReorientCommand(req));
        case Edge4EditPart.VISUAL_ID:
            return getGEFWrapper(new Edge4ReorientCommand(req));
        case Edge5EditPart.VISUAL_ID:
            return getGEFWrapper(new Edge5ReorientCommand(req));
        case Edge6EditPart.VISUAL_ID:
            return getGEFWrapper(new Edge6ReorientCommand(req));
        case Edge7EditPart.VISUAL_ID:
            return getGEFWrapper(new Edge7ReorientCommand(req));
        case Edge8EditPart.VISUAL_ID:
            return getGEFWrapper(new Edge8ReorientCommand(req));
        }
        return super.getReorientRelationshipCommand(req);
    }

}
