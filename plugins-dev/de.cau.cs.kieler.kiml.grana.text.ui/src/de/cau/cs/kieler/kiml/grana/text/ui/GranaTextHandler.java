/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2014 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.grana.text.ui;

import java.util.Collections;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
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

import de.cau.cs.kieler.kiml.grana.GranaPlugin;
import de.cau.cs.kieler.kiml.grana.text.GranaStandaloneSetup;
import de.cau.cs.kieler.kiml.grana.text.GranaTextToBatchJob;
import de.cau.cs.kieler.kiml.grana.text.grana.Grana;

/**
 * @author uru
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
                                new GranaTextToBatchJob().execute(grana, monitor);
                                monitor.done();
                                return Status.OK_STATUS;
                            }
                        };
                        job.setUser(true);
                        job.schedule();
                        
                    }
                } catch (Exception e) {
                    StatusManager.getManager().handle(
                            new Status(IStatus.ERROR, GranaPlugin.PLUGIN_ID, MESSAGE_BATCH_FAILED,
                                    e));
                }
            }
        }

        return null;
    }
}
