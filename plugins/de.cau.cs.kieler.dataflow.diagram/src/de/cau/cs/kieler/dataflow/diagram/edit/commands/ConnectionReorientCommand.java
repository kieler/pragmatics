package de.cau.cs.kieler.dataflow.diagram.edit.commands;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.type.core.commands.EditElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRelationshipRequest;

import de.cau.cs.kieler.dataflow.Box;
import de.cau.cs.kieler.dataflow.Connection;
import de.cau.cs.kieler.dataflow.Port;
import de.cau.cs.kieler.dataflow.diagram.edit.policies.DataflowBaseItemSemanticEditPolicy;

/**
 * @generated
 */
public class ConnectionReorientCommand extends EditElementCommand {

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
    public ConnectionReorientCommand(ReorientRelationshipRequest request) {
        super(request.getLabel(), request.getRelationship(), request);
        reorientDirection = request.getDirection();
        oldEnd = request.getOldRelationshipEnd();
        newEnd = request.getNewRelationshipEnd();
    }

    /**
     * @generated
     */
    public boolean canExecute() {
        if (false == getElementToEdit() instanceof Connection) {
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
        if (!(oldEnd instanceof Port && newEnd instanceof Port)) {
            return false;
        }
        Port target = getLink().getTargetPort();
        if (!(getLink().eContainer() instanceof Box)) {
            return false;
        }
        Box container = (Box) getLink().eContainer();
        return DataflowBaseItemSemanticEditPolicy.LinkConstraints.canExistConnection_4001(
                container, getNewSource(), target);
    }

    /**
     * @generated
     */
    protected boolean canReorientTarget() {
        if (!(oldEnd instanceof Port && newEnd instanceof Port)) {
            return false;
        }
        Port source = getLink().getSourcePort();
        if (!(getLink().eContainer() instanceof Box)) {
            return false;
        }
        Box container = (Box) getLink().eContainer();
        return DataflowBaseItemSemanticEditPolicy.LinkConstraints.canExistConnection_4001(
                container, source, getNewTarget());
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
        getLink().setSourcePort(getNewSource());
        return CommandResult.newOKCommandResult(getLink());
    }

    /**
     * @generated
     */
    protected CommandResult reorientTarget() throws ExecutionException {
        getLink().setTargetPort(getNewTarget());
        return CommandResult.newOKCommandResult(getLink());
    }

    /**
     * @generated
     */
    protected Connection getLink() {
        return (Connection) getElementToEdit();
    }

    /**
     * @generated
     */
    protected Port getOldSource() {
        return (Port) oldEnd;
    }

    /**
     * @generated
     */
    protected Port getNewSource() {
        return (Port) newEnd;
    }

    /**
     * @generated
     */
    protected Port getOldTarget() {
        return (Port) oldEnd;
    }

    /**
     * @generated
     */
    protected Port getNewTarget() {
        return (Port) newEnd;
    }
}
