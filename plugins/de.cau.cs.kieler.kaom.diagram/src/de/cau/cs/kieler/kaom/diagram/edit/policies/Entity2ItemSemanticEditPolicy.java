package de.cau.cs.kieler.kaom.diagram.edit.policies;

import java.util.Iterator;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.common.core.command.ICompositeCommand;
import org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand;
import org.eclipse.gmf.runtime.emf.commands.core.command.CompositeTransactionalCommand;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRelationshipRequest;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;

import de.cau.cs.kieler.kaom.diagram.edit.commands.LinkCreateCommand;
import de.cau.cs.kieler.kaom.diagram.edit.commands.LinkReorientCommand;
import de.cau.cs.kieler.kaom.diagram.edit.commands.PortCreateCommand;
import de.cau.cs.kieler.kaom.diagram.edit.parts.Entity3EditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.EntityEntityCompartmentEditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.LinkEditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.PortEditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.Relation2EditPart;
import de.cau.cs.kieler.kaom.diagram.part.KaomVisualIDRegistry;
import de.cau.cs.kieler.kaom.diagram.providers.KaomElementTypes;

/**
 * @generated
 */
public class Entity2ItemSemanticEditPolicy extends KaomBaseItemSemanticEditPolicy {

    /**
     * @generated
     */
    public Entity2ItemSemanticEditPolicy() {
        super(KaomElementTypes.Entity_2001);
    }

    /**
     * @generated
     */
    protected Command getCreateCommand(CreateElementRequest req) {
        if (KaomElementTypes.Port_3001 == req.getElementType()) {
            return getGEFWrapper(new PortCreateCommand(req));
        }
        return super.getCreateCommand(req);
    }

    /**
     * @generated
     */
    protected Command getDestroyElementCommand(DestroyElementRequest req) {
        View view = (View) getHost().getModel();
        CompositeTransactionalCommand cmd = new CompositeTransactionalCommand(getEditingDomain(),
                null);
        cmd.setTransactionNestingEnabled(false);
        for (Iterator<?> it = view.getTargetEdges().iterator(); it.hasNext();) {
            Edge incomingLink = (Edge) it.next();
            if (KaomVisualIDRegistry.getVisualID(incomingLink) == LinkEditPart.VISUAL_ID) {
                DestroyElementRequest r = new DestroyElementRequest(incomingLink.getElement(),
                        false);
                cmd.add(new DestroyElementCommand(r));
                cmd.add(new DeleteCommand(getEditingDomain(), incomingLink));
                continue;
            }
        }
        for (Iterator<?> it = view.getSourceEdges().iterator(); it.hasNext();) {
            Edge outgoingLink = (Edge) it.next();
            if (KaomVisualIDRegistry.getVisualID(outgoingLink) == LinkEditPart.VISUAL_ID) {
                DestroyElementRequest r = new DestroyElementRequest(outgoingLink.getElement(),
                        false);
                cmd.add(new DestroyElementCommand(r));
                cmd.add(new DeleteCommand(getEditingDomain(), outgoingLink));
                continue;
            }
        }
        EAnnotation annotation = view.getEAnnotation("Shortcut"); //$NON-NLS-1$
        if (annotation == null) {
            // there are indirectly referenced children, need extra commands: false
            addDestroyChildNodesCommand(cmd);
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
    private void addDestroyChildNodesCommand(ICompositeCommand cmd) {
        View view = (View) getHost().getModel();
        for (Iterator<?> nit = view.getChildren().iterator(); nit.hasNext();) {
            Node node = (Node) nit.next();
            switch (KaomVisualIDRegistry.getVisualID(node)) {
            case PortEditPart.VISUAL_ID:
                for (Iterator<?> it = node.getTargetEdges().iterator(); it.hasNext();) {
                    Edge incomingLink = (Edge) it.next();
                    if (KaomVisualIDRegistry.getVisualID(incomingLink) == LinkEditPart.VISUAL_ID) {
                        DestroyElementRequest r = new DestroyElementRequest(
                                incomingLink.getElement(), false);
                        cmd.add(new DestroyElementCommand(r));
                        cmd.add(new DeleteCommand(getEditingDomain(), incomingLink));
                        continue;
                    }
                }
                for (Iterator<?> it = node.getSourceEdges().iterator(); it.hasNext();) {
                    Edge outgoingLink = (Edge) it.next();
                    if (KaomVisualIDRegistry.getVisualID(outgoingLink) == LinkEditPart.VISUAL_ID) {
                        DestroyElementRequest r = new DestroyElementRequest(
                                outgoingLink.getElement(), false);
                        cmd.add(new DestroyElementCommand(r));
                        cmd.add(new DeleteCommand(getEditingDomain(), outgoingLink));
                        continue;
                    }
                }
                cmd.add(new DestroyElementCommand(new DestroyElementRequest(getEditingDomain(),
                        node.getElement(), false))); // directlyOwned: true
                // don't need explicit deletion of node as parent's view deletion would clean child views as well 
                // cmd.add(new org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand(getEditingDomain(), node));
                break;
            case EntityEntityCompartmentEditPart.VISUAL_ID:
                for (Iterator<?> cit = node.getChildren().iterator(); cit.hasNext();) {
                    Node cnode = (Node) cit.next();
                    switch (KaomVisualIDRegistry.getVisualID(cnode)) {
                    case Entity3EditPart.VISUAL_ID:
                        for (Iterator<?> it = cnode.getTargetEdges().iterator(); it.hasNext();) {
                            Edge incomingLink = (Edge) it.next();
                            if (KaomVisualIDRegistry.getVisualID(incomingLink) == LinkEditPart.VISUAL_ID) {
                                DestroyElementRequest r = new DestroyElementRequest(
                                        incomingLink.getElement(), false);
                                cmd.add(new DestroyElementCommand(r));
                                cmd.add(new DeleteCommand(getEditingDomain(), incomingLink));
                                continue;
                            }
                        }
                        for (Iterator<?> it = cnode.getSourceEdges().iterator(); it.hasNext();) {
                            Edge outgoingLink = (Edge) it.next();
                            if (KaomVisualIDRegistry.getVisualID(outgoingLink) == LinkEditPart.VISUAL_ID) {
                                DestroyElementRequest r = new DestroyElementRequest(
                                        outgoingLink.getElement(), false);
                                cmd.add(new DestroyElementCommand(r));
                                cmd.add(new DeleteCommand(getEditingDomain(), outgoingLink));
                                continue;
                            }
                        }
                        cmd.add(new DestroyElementCommand(new DestroyElementRequest(
                                getEditingDomain(), cnode.getElement(), false))); // directlyOwned: true
                        // don't need explicit deletion of cnode as parent's view deletion would clean child views as well 
                        // cmd.add(new org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand(getEditingDomain(), cnode));
                        break;
                    case Relation2EditPart.VISUAL_ID:
                        for (Iterator<?> it = cnode.getTargetEdges().iterator(); it.hasNext();) {
                            Edge incomingLink = (Edge) it.next();
                            if (KaomVisualIDRegistry.getVisualID(incomingLink) == LinkEditPart.VISUAL_ID) {
                                DestroyElementRequest r = new DestroyElementRequest(
                                        incomingLink.getElement(), false);
                                cmd.add(new DestroyElementCommand(r));
                                cmd.add(new DeleteCommand(getEditingDomain(), incomingLink));
                                continue;
                            }
                        }
                        for (Iterator<?> it = cnode.getSourceEdges().iterator(); it.hasNext();) {
                            Edge outgoingLink = (Edge) it.next();
                            if (KaomVisualIDRegistry.getVisualID(outgoingLink) == LinkEditPart.VISUAL_ID) {
                                DestroyElementRequest r = new DestroyElementRequest(
                                        outgoingLink.getElement(), false);
                                cmd.add(new DestroyElementCommand(r));
                                cmd.add(new DeleteCommand(getEditingDomain(), outgoingLink));
                                continue;
                            }
                        }
                        cmd.add(new DestroyElementCommand(new DestroyElementRequest(
                                getEditingDomain(), cnode.getElement(), false))); // directlyOwned: true
                        // don't need explicit deletion of cnode as parent's view deletion would clean child views as well 
                        // cmd.add(new org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand(getEditingDomain(), cnode));
                        break;
                    }
                }
                break;
            }
        }
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
        if (KaomElementTypes.Link_4001 == req.getElementType()) {
            return getGEFWrapper(new LinkCreateCommand(req, req.getSource(), req.getTarget()));
        }
        return null;
    }

    /**
     * @generated
     */
    protected Command getCompleteCreateRelationshipCommand(CreateRelationshipRequest req) {
        if (KaomElementTypes.Link_4001 == req.getElementType()) {
            return getGEFWrapper(new LinkCreateCommand(req, req.getSource(), req.getTarget()));
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
        case LinkEditPart.VISUAL_ID:
            return getGEFWrapper(new LinkReorientCommand(req));
        }
        return super.getReorientRelationshipCommand(req);
    }

}
