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
 * 
 *****************************************************************************/
package de.cau.cs.kieler.graphs.diagram.custom;

import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.notation.View;


/**
 * Command used to change the hypernodes structure.
 * 
 * @author msp
 */
public class HypernodesCommand extends AbstractTransactionalCommand {

    /** adapter for the view of the base diagram. */
    private IAdaptable diagramViewAdapter;
    
    /**
     * Creates a command to update the hypernodes structure.
     * 
     * @param domain the editing domain through which model changes are made
     * @param label the command label
     * @param adapter an adapter to the {@code View} of the base diagram
     */
    public HypernodesCommand(final TransactionalEditingDomain domain, final String label,
            final IAdaptable adapter) {
        super(domain, label, null);
        this.diagramViewAdapter = adapter;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected CommandResult doExecuteWithResult(final IProgressMonitor monitor,
            final IAdaptable info) throws ExecutionException {
        monitor.beginTask(getLabel(), 1);

        
        
        monitor.done();
        return CommandResult.newOKCommandResult();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<?> getAffectedFiles() {
        if (diagramViewAdapter != null) {
            View view = (View) diagramViewAdapter.getAdapter(View.class);
            if (view != null) {
                return getWorkspaceFiles(view);
            }
        }
        return super.getAffectedFiles();
    }

}
