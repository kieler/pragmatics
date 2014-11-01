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

package de.cau.cs.kieler.kiml.grana.headless;

import java.io.File;
import java.io.IOException;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.xtext.util.Arrays;

import de.cau.cs.kieler.kiml.grana.text.GranaStandaloneSetup;
import de.cau.cs.kieler.kiml.grana.text.GranaTextToBatchJob;
import de.cau.cs.kieler.kiml.grana.text.grana.Grana;

/**
 * Headless application used to execute Grana Batch Jobs from the command line.
 * 
 * @author uru
 */
public class HeadlessGranaExecutor implements IApplication {

    private final ResourceSet set = new ResourceSetImpl();

    /**
     * {@inheritDoc}
     */
    public Object start(final IApplicationContext context) throws Exception {
        final String[] appArgs =
                (String[]) context.getArguments().get(IApplicationContext.APPLICATION_ARGS);

        if (Arrays.contains(appArgs, "--help")) {
            System.out.println("Usage: grana -ws [WORKSPACE_LOCATION] [GRANA_FILES...]");
            System.in.read();
            return IApplication.EXIT_OK;
        }
        
        GranaStandaloneSetup.doSetup();
        set.getLoadOptions().put(XMIResource.OPTION_RECORD_UNKNOWN_FEATURE, true);

        System.out.print("Specified Workspace Location: ");
        System.out.println(ResourcesPlugin.getWorkspace().getRoot().getLocation());

        for (final String arg : appArgs) {
            if (!new File(arg).exists()) {
                continue;
            }

            System.out.println("\nExecuting Batch Job: " + arg);
            try {
                this.executeBatch(arg);
                System.out.println("\tDone ...");
            } catch (Throwable t) {
                System.out.println("Failed with ... " + t.getMessage());
            }
        }

        System.out.println("Finished all specified jobs.");
        return IApplication.EXIT_OK;
    }

    private void executeBatch(final String fileName) throws IOException {

        // load the graph model
        final Resource res = set.getResource(URI.createFileURI(fileName), true);
        if (res.getContents().isEmpty()) {
            return;
        }

        final EObject eo = res.getContents().get(0);
        if (eo == null) {
            return;
        }

        try {

            if (eo instanceof Grana) {
                final Grana grana = (Grana) eo;

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

                // executing from the command line we want to wait until
                // one job finished before we start the next job
                job.join();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * {@inheritDoc}
     */
    public void stop() {
    }
}
