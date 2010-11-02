/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2010 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.evol.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.ui.PlatformUI;

import de.cau.cs.kieler.kiml.evol.EvolModel;
import de.cau.cs.kieler.kiml.evol.EvolPlugin;
import de.cau.cs.kieler.kiml.evol.ui.EvolView;

/**
 * Handler that performs a reset.
 *
 * @author bdu
 *
 */
public class ResetHandler extends AbstractHandler {

    /**
     * @author bdu
     *
     */
    private static final class ResetJob extends Job {

        /**
         * The associated model.
         */
        private final EvolModel model;

        /**
         * Creates a new {@link ResetJob} instance.
         *
         * @param name
         *            the name of the job
         * @param theModel
         *            the associated model
         */
        public ResetJob(final String name, final EvolModel theModel) {
            super(name);
            this.model = theModel;
        }

        /* (non-Javadoc)
         * @see org.eclipse.core.runtime.jobs.Job#run(org.eclipse.core.runtime.IProgressMonitor)
         */
        @Override
        protected IStatus run(final IProgressMonitor monitor) {

            final int scale = 1000;
            final int resettingWork = 1;
            final int totalWork = resettingWork;

            try {
                monitor.beginTask("Resetting the model.", totalWork * scale);

                // Reset the model. SubProgressMonitor manages the work.
                this.model.reset(new SubProgressMonitor(monitor, resettingWork * scale));

                return new Status(IStatus.INFO, EvolPlugin.PLUGIN_ID, 0, "OK", null);

            } catch (final OperationCanceledException exception) {
                // Canceled by the user.
                return new Status(IStatus.CANCEL, EvolPlugin.PLUGIN_ID,
                        "The reset job was cancelled.", exception);

            } catch (Exception exception) {
                // Failure.
                return new Status(IStatus.ERROR, EvolPlugin.PLUGIN_ID,
                        "The reset job has failed.", exception);
            } finally {
                monitor.done();
            }
        }

    }

    /**
     * {@inheritDoc}
     */
    public Object execute(final ExecutionEvent event) throws ExecutionException {

        // Get the EvolView (must be in UI thread).
        EvolView view =
                (EvolView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
                        .findView(EvolView.ID);

        if (view == null) {
            throw new ExecutionException("The Evolution View could not be found.");
        }

        // Get the evolution model.
        EvolModel model = view.getEvolModel();
        assert model != null;

        // Create job for resetting.
        Job resetJob = new ResetJob("Resetting", model);

        // Process the job.
        IProgressMonitor monitor = Job.getJobManager().createProgressGroup();

        resetJob.setProgressGroup(monitor, IProgressMonitor.UNKNOWN);
        resetJob.setUser(true);
        resetJob.setPriority(Job.SHORT);
        resetJob.schedule();

        return null;
    }
}
