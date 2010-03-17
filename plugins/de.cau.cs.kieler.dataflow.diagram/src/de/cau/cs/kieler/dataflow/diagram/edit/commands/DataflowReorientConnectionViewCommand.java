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
package de.cau.cs.kieler.dataflow.diagram.edit.commands;

import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @generated
 */
public class DataflowReorientConnectionViewCommand extends
        AbstractTransactionalCommand {

    /**
     * @generated
     */
    private IAdaptable edgeAdaptor;

    /**
     * @generated
     */
    public DataflowReorientConnectionViewCommand(
            TransactionalEditingDomain editingDomain, String label) {
        super(editingDomain, label, null);
    }

    /**
     * @generated
     */
    public List getAffectedFiles() {
        View view = (View) edgeAdaptor.getAdapter(View.class);
        if (view != null) {
            return getWorkspaceFiles(view);
        }
        return super.getAffectedFiles();
    }

    /**
     * @generated
     */
    public IAdaptable getEdgeAdaptor() {
        return edgeAdaptor;
    }

    /**
     * @generated
     */
    public void setEdgeAdaptor(IAdaptable edgeAdaptor) {
        this.edgeAdaptor = edgeAdaptor;
    }

    /**
     * @generated
     */
    protected CommandResult doExecuteWithResult(
            IProgressMonitor progressMonitor, IAdaptable info) {
        assert null != edgeAdaptor : "Null child in DataflowReorientConnectionViewCommand"; //$NON-NLS-1$
        Edge edge = (Edge) getEdgeAdaptor().getAdapter(Edge.class);
        assert null != edge : "Null edge in DataflowReorientConnectionViewCommand"; //$NON-NLS-1$
        View tempView = edge.getSource();
        edge.setSource(edge.getTarget());
        edge.setTarget(tempView);
        return CommandResult.newOKCommandResult();
    }
}
