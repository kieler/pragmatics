/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2014 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.grana.text.ui;

import java.util.Collections;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.statushandlers.StatusManager;

import com.google.common.collect.FluentIterable;

import de.cau.cs.kieler.grana.GranaPlugin;
import de.cau.cs.kieler.grana.text.GranaStandaloneSetup;
import de.cau.cs.kieler.grana.text.GranaTextPlugin;
import de.cau.cs.kieler.grana.text.GranaTextToBatchJob;
import de.cau.cs.kieler.grana.text.grana.Grana;
import de.cau.cs.kieler.grana.ui.batch.BatchJobResult;
import de.cau.cs.kieler.grana.ui.batch.BatchResult;

/**
 * @author uru
 * @kieler.ignore (excluded from review process)
 */
public class GranaTextHandler extends AbstractHandler {

    /** the error message for a failed batch analysis. */
    private static final String MESSAGE_BATCH_FAILED = "The batch analysis failed.";

    /**
     * {@inheritDoc}
     */
    public Object execute(final ExecutionEvent event) throws ExecutionException {

        ISelection selection = HandlerUtil.getCurrentSelection(event);
        if (selection instanceof IStructuredSelection) {
            final Object element = ((IStructuredSelection) selection).getFirstElement();

            if (element instanceof IFile) {
                GranaStandaloneSetup.doSetup();
                try {
                    // load the file into a resource
                    ResourceSet resourceSet = new ResourceSetImpl();
                    Resource r = resourceSet.createResource(URI.createFileURI("dummy.grana"));
                    r.load(((IFile) element).getContents(), Collections.emptyMap());

                    if (!r.getContents().isEmpty() && r.getContents().get(0) instanceof Grana) {
                        final Grana grana = (Grana) r.getContents().get(0);

                        // analyses may take some time ...
                        Job job = new Job("Execute Batch Analysis") {
                            protected IStatus run(final IProgressMonitor monitor) {
                                monitor.beginTask("Execute Batch Analysis", 1);
                                try {
                                    Iterable<BatchResult> results =
                                            GranaTextToBatchJob.execute(grana, monitor);
                                    displayProblems(results);
                                } catch (Exception e) {
                                    IStatus status =
                                            new Status(IStatus.ERROR, GranaPlugin.PLUGIN_ID, 0,
                                                    MESSAGE_BATCH_FAILED, e);
                                    StatusManager.getManager().handle(status,
                                            StatusManager.SHOW | StatusManager.LOG);
                                } finally {
                                    monitor.done();
                                }
                                return Status.OK_STATUS;
                            }
                        };
                        job.setUser(true);
                        job.schedule();

                    }
                } catch (Exception e) {
                    StatusManager.getManager().handle(
                            new Status(IStatus.ERROR, GranaPlugin.PLUGIN_ID, MESSAGE_BATCH_FAILED, e));
                }
            }
        }

        return null;
    }

    private void displayProblems(final Iterable<BatchResult> results) {
        for (BatchResult result : results) {
            List<BatchJobResult.Failed> fails = FluentIterable.from(result.getJobResults()).filter(BatchJobResult.Failed.class).toList();
            if (!fails.isEmpty()) {
                IStatus[] stati = new IStatus[fails.size()];
                int i = 0;
                for (BatchJobResult.Failed fail : fails) {
                    stati[i++] =
                            new Status(IStatus.ERROR, GranaTextPlugin.PLUGIN_ID,
                                    "Failed analysis of " + fail.getJob().getParameter(),
                                    fail.getThrowable());
                }
                StatusManager.getManager().handle(
                        new MultiStatus(GranaTextPlugin.PLUGIN_ID, 0, stati, MESSAGE_BATCH_FAILED,
                                null), StatusManager.SHOW | StatusManager.LOG);
            }
        }
    }

}
