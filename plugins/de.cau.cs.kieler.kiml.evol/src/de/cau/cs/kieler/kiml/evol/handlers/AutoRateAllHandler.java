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
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;

import de.cau.cs.kieler.kiml.evol.EvolPlugin;
import de.cau.cs.kieler.kiml.evol.EvolUtil;
import de.cau.cs.kieler.kiml.evol.genetic.Population;
import de.cau.cs.kieler.kiml.evol.ui.EvolView;

/**
 * Handler for auto-rating all individuals.
 *
 * @author bdu
 *
 */
public class AutoRateAllHandler extends AbstractHandler {
    /**
     * @author bdu
     *
     */
    private static final class RefreshEvolutionViewJob extends Job {
        /**
         *
         */
        private final EvolView view;

        /**
         * Creates a new {@link RefreshEvolutionViewJob} instance.
         *
         * @param theName
         * @param theMonitor
         * @param theView
         */
        RefreshEvolutionViewJob(final String theName, final EvolView theView) {
            super(theName);
            this.view = theView;
        }

        /**
         * @return the evolution view
         */
        protected EvolView getView() {
            return this.view;
        }

        @Override
        protected IStatus run(final IProgressMonitor monitor) {

            monitor.subTask("Refreshing the evolution view");
            Display.getDefault().asyncExec(new Runnable() {
                public void run() {
                    final EvolView evolView = getView();
                    evolView.getTableViewer().refresh();
                    evolView.refresh(false);
                }
            });
            monitor.worked(1);

            return new Status(IStatus.INFO, EvolPlugin.PLUGIN_ID, 0, "OK", null);
        }
    }

    /**
     * A job that does the auto-rating.
     *
     * @author bdu
     *
     */
    public static final class AutoRateAllJob extends Job {
        /**
         * The population to be rated.
         */
        private final Population population;

        /**
         * The editor in which the individuals shall be layouted.
         */
        private final IEditorPart editor;

        private final EvolView view;

        /**
         * @param name
         * @param pop
         * @param theEditor
         * @param theView
         */
        AutoRateAllJob(
                final String name,
                final Population pop,
                final IEditorPart theEditor,
                final EvolView theView) {
            super(name);
            Assert.isLegal(pop != null);
            this.population = pop;
            this.editor = theEditor;
            this.view = theView;
        }

        @Override
        protected IStatus run(final IProgressMonitor monitor) {
            try {
                monitor.subTask("Determining Individual Rating");

                // Do the rating.
                EvolUtil.autoRateIndividuals(this.population, this.editor, monitor);

                if (monitor.isCanceled()) {
                    return new Status(IStatus.CANCEL, EvolPlugin.PLUGIN_ID,
                            "The auto-rating was cancelled.");
                }

                monitor.subTask("Determining the average rating");

                System.out.println("Average rating: " + this.population.getAverageRating());
                monitor.worked(1);

                return new Status(IStatus.INFO, EvolPlugin.PLUGIN_ID, 0, "OK", null);

            } catch (final Exception exception) {
                return new Status(IStatus.ERROR, EvolPlugin.PLUGIN_ID,
                        "The auto-rating has failed.", exception);
            }
        }

        /**
         * @return the evolution view
         */
        protected EvolView getView() {
            return this.view;
        }
    }

    /**
     * {@inheritDoc}
     */
    public Object execute(final ExecutionEvent event) throws ExecutionException {

        final EvolView view =
                (EvolView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
                        .findView(EvolView.ID);

        if (view == null) {
            throw new ExecutionException("The Evolution View could not be found.");
        }

        final Population pop = view.getPopulation();
        final IEditorPart editor = view.getEvolModel().getLastEditor();

        // Create jobs for auto-rating and refreshing.
        final Job autoRateAllJob = new AutoRateAllJob("auto-rating", pop, editor, view);
        final Job refreshViewJob = new RefreshEvolutionViewJob("refreshing the view", view);

        // Process the jobs.
        final IProgressMonitor monitor = Job.getJobManager().createProgressGroup();

        try {
            final int size = pop.size();

            monitor.beginTask("Performing Auto-rating", size + 1 + 1);

            autoRateAllJob.setProgressGroup(monitor, size + 1);
            autoRateAllJob.setUser(true);
            autoRateAllJob.schedule();

            refreshViewJob.setProgressGroup(monitor, 1);
            refreshViewJob.setUser(true);
            refreshViewJob.schedule();

            // XXX: Auto-rating and refreshing are divided into two separate
            // jobs. We want the autorating to be finished before the view is
            // being refreshed. This is done by joining on the auto-rating job,
            // but this way for some unknown reason the diligently notified
            // progress bar is not shown. Where is the progress bar? :(

            autoRateAllJob.join();
            refreshViewJob.join();

        } catch (final InterruptedException exception) {
            exception.printStackTrace();
        } finally {
            monitor.done();
        }

        return null;
    }
}
