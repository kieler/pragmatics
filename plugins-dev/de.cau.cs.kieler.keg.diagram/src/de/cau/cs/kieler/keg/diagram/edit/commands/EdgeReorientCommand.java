package de.cau.cs.kieler.keg.diagram.edit.commands;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.type.core.commands.EditElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRelationshipRequest;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.keg.Edge;
import de.cau.cs.kieler.keg.diagram.edit.policies.GraphsBaseItemSemanticEditPolicy;

/**
 * @generated
 */
public class EdgeReorientCommand extends EditElementCommand {

    /**
     * @generated
     */
    private final int reorientDirection;

    /**
     * @generated
     */
    private final EObject oldEnd;

    /**
     * @generated
     */
    private final EObject newEnd;

    /**
     * @generated
     */
    public EdgeReorientCommand(ReorientRelationshipRequest request) {
        super(request.getLabel(), request.getRelationship(), request);
        reorientDirection = request.getDirection();
        oldEnd = request.getOldRelationshipEnd();
        newEnd = request.getNewRelationshipEnd();
    }

    /**
     * @generated
     */
    public boolean canExecute() {
        if (false == getElementToEdit() instanceof Edge) {
            return false;
        }
        if (reorientDirection == ReorientRelationshipRequest.REORIENT_SOURCE) {
            return canReorientSource();
        }
        if (reorientDirection == ReorientRelationshipRequest.REORIENT_TARGET) {
            return canReorientTarget();
        }
        return false;
    }

    /**
     * @generated
     */
    protected boolean canReorientSource() {
        if (!(oldEnd instanceof KNode && newEnd instanceof KNode)) {
            return false;
        }
        KNode target = getLink().getTarget();
        if (!(getLink().eContainer() instanceof KNode)) {
            return false;
        }
        KNode container = (KNode) getLink().eContainer();
        return GraphsBaseItemSemanticEditPolicy.getLinkConstraints().canExistEdge_4001(container,
                getLink(), getNewSource(), target);
    }

    /**
     * @generated
     */
    protected boolean canReorientTarget() {
        if (!(oldEnd instanceof KNode && newEnd instanceof KNode)) {
            return false;
        }
        KNode source = getLink().getSource();
        if (!(getLink().eContainer() instanceof KNode)) {
            return false;
        }
        KNode container = (KNode) getLink().eContainer();
        return GraphsBaseItemSemanticEditPolicy.getLinkConstraints().canExistEdge_4001(container,
                getLink(), source, getNewTarget());
    }

    /**
     * @generated
     */
    protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info)
            throws ExecutionException {
        if (!canExecute()) {
            throw new ExecutionException("Invalid arguments in reorient link command"); //$NON-NLS-1$
        }
        if (reorientDirection == ReorientRelationshipRequest.REORIENT_SOURCE) {
            return reorientSource();
        }
        if (reorientDirection == ReorientRelationshipRequest.REORIENT_TARGET) {
            return reorientTarget();
        }
        throw new IllegalStateException();
    }

    /**
     * @generated
     */
    protected CommandResult reorientSource() throws ExecutionException {
        getLink().setSource(getNewSource());
        return CommandResult.newOKCommandResult(getLink());
    }

    /**
     * @generated
     */
    protected CommandResult reorientTarget() throws ExecutionException {
        getLink().setTarget(getNewTarget());
        return CommandResult.newOKCommandResult(getLink());
    }

    /**
     * @generated
     */
    protected Edge getLink() {
        return (Edge) getElementToEdit();
    }

    /**
     * @generated
     */
    protected KNode getOldSource() {
        return (KNode) oldEnd;
    }

    /**
     * @generated
     */
    protected KNode getNewSource() {
        return (KNode) newEnd;
    }

    /**
     * @generated
     */
    protected KNode getOldTarget() {
        return (KNode) oldEnd;
    }

    /**
     * @generated
     */
    protected KNode getNewTarget() {
        return (KNode) newEnd;
    }
}
