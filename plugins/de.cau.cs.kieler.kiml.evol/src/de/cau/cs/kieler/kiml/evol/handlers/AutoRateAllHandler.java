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
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;

import de.cau.cs.kieler.kiml.evol.EvolModel;
import de.cau.cs.kieler.kiml.evol.EvolPlugin;
import de.cau.cs.kieler.kiml.evol.EvolUtil;
import de.cau.cs.kieler.kiml.evol.ui.EvolView;

/**
 * Handler for auto-rating all individuals.
 *
 * @author bdu
 *
 */
public class AutoRateAllHandler extends AbstractHandler {

    /**
     * A job that does the auto-rating.
     *
     * @author bdu
     *
     */
    private static final class AutoRateAllJob extends Job {
        /**
         * Creates a new {@link AutoRateAllJob} instance. It rates all
         * individuals of the specified model in the specified editor.
         *
         * @param name
         * @param theEditor
         * @param theModel
         */
        AutoRateAllJob(final String name, final IEditorPart theEditor, final EvolModel theModel) {
            super(name);
            this.editor = theEditor;
            this.model = theModel;
        }

        private final EvolModel model;

        private final IEditorPart editor;

        @Override
        protected IStatus run(final IProgressMonitor monitor) {

            final int scale = 1000;
            final int autoRatingWork = 4;
            final int averageRatingWork = 1;
            final int total = autoRatingWork + averageRatingWork;

            try {
                monitor.beginTask("Performing Auto-rating", total * scale);

                // Rate all individuals in the given editor. SubProgressMonitor
                // manages the work.
                monitor.subTask("Determining Individual Rating");

                this.model.autoRateAll(this.editor,
                        new SubProgressMonitor(monitor, autoRatingWork * scale));

                if (monitor.isCanceled()) {
                    return new Status(IStatus.CANCEL, EvolPlugin.PLUGIN_ID,
                            "The auto-rating was cancelled.");
                }

                // final task
                monitor.subTask("Determining the average rating");
                System.out.println("Average rating: "
                        + this.model.getPopulation().getAverageRating());
                monitor.worked(averageRatingWork * scale);

                return new Status(IStatus.INFO, EvolPlugin.PLUGIN_ID, 0, "OK", null);

            } catch (final OperationCanceledException exception) {
                // Cancelled by the user.
                return new Status(IStatus.CANCEL, EvolPlugin.PLUGIN_ID,
                        "The auto-rating was cancelled.", exception);

            } catch (final Exception exception) {
                // Failure.
                return new Status(IStatus.ERROR, EvolPlugin.PLUGIN_ID,
                        "The auto-rating has failed.", exception);

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
        final EvolView view =
                (EvolView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
                        .findView(EvolView.ID);
        if ((view == null)) {
            throw new ExecutionException("The Evolution View could not be found.");
        }

        // Get the evolution model.
        final EvolModel model = view.getEvolModel();
        Assert.isNotNull(model);

        if (model.isValid()) {
            // Get the current editor.
            final IEditorPart editor = EvolUtil.getCurrentEditor();
            Assert.isNotNull(editor);

            // Create job for auto-rating.
            final Job autoRateAllJob = new AutoRateAllJob("Auto-Rating", editor, model);

            // Process the job.
            final IProgressMonitor monitor = Job.getJobManager().createProgressGroup();

            autoRateAllJob.setProgressGroup(monitor, IProgressMonitor.UNKNOWN);
            autoRateAllJob.setUser(true);
            autoRateAllJob.setPriority(Job.SHORT);
            autoRateAllJob.schedule();
        }

        return null;
    }

}
