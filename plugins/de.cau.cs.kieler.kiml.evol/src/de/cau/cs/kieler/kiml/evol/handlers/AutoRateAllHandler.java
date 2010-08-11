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
     * A job that does the auto-rating.
     *
     * @author bdu
     *
     */
    private static final class AutoRateAllJob extends Job {
        /**
         * @author bdu
         *
         */
        private static final class AutoRateAllRunnable implements Runnable {
            /**
             *
             */
            private final IProgressMonitor monitor;
            private final EvolView view;
            private final IEditorPart editor;

            /**
             * Creates a new {@link AutoRateAllRunnable} instance.
             *
             * @param theMonitor
             * @param theEditor
             * @param thePopulation
             */
            AutoRateAllRunnable(
                    final EvolView theView,
                    final IEditorPart theEditor,
                    final IProgressMonitor theMonitor) {
                this.editor = theEditor;
                this.monitor = theMonitor;
                this.view = theView;
            }

            public void run() {
                try {
                    final long delay = 200;
                    Thread.sleep(delay);
                } catch (final InterruptedException exception) {
                    exception.printStackTrace();
                }

                // Rate all individuals in the given editor.
                this.view.getEvolModel().autoRate(this.editor, this.monitor);
            }
        }

        private final EvolView view;

        private final IEditorPart editor;

        /**
         * Creates a new {@link AutoRateAllJob} instance.
         *
         * @param name
         * @param theEditor
         * @param theView
         */
        AutoRateAllJob(
                final String name,
                final IEditorPart theEditor,
                final EvolView theView) {
            super(name);

            this.editor = theEditor;
            this.view = theView;
        }

        @Override
        protected IStatus run(final IProgressMonitor monitor) {

            final int total = this.view.getPopulation().size() + 1;

            try {
                monitor.beginTask("Performing Auto-rating", total);
                final int delay = 400;

                monitor.subTask("Determining Individual Rating");

                Thread.sleep(delay + delay);

                // Do the rating.
                // new AutoRateAllRunnable(this.view, this.editor,
                // monitor).run();

                // Rate all individuals in the given editor.
                this.view.getEvolModel().autoRate(this.editor, monitor);

                Thread.sleep(delay + delay);

                if (monitor.isCanceled()) {
                    return new Status(IStatus.CANCEL, EvolPlugin.PLUGIN_ID,
                            "The auto-rating was cancelled.");
                }

                monitor.subTask("Determining the average rating");
                System.out.println("Average rating: "
                        + this.view.getPopulation().getAverageRating());
                monitor.worked(1);

                Thread.sleep(delay);

                return new Status(IStatus.INFO, EvolPlugin.PLUGIN_ID, 0, "OK", null);

            } catch (final Exception exception) {
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

        final EvolView view =
                (EvolView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
                        .findView(EvolView.ID);

        if (view == null) {
            throw new ExecutionException("The Evolution View could not be found.");
        }

        final Population pop = view.getPopulation();
        Assert.isNotNull(pop);

        final IEditorPart editor = EvolUtil.getCurrentEditor();
        Assert.isNotNull(editor);

        // Create job for auto-rating.
        final Job autoRateAllJob = new AutoRateAllJob("auto-rating", editor, view);

        // Process the job.
        final IProgressMonitor monitor = Job.getJobManager().createProgressGroup();

        final int size = pop.size();

        autoRateAllJob.setProgressGroup(monitor, size + 1);
        autoRateAllJob.setUser(true);
        autoRateAllJob.setPriority(Job.SHORT);
        autoRateAllJob.schedule();
        // XXX: Where is the progress bar? :(

        try {
            final int delay = 200;
            Thread.sleep(delay);

        } catch (final InterruptedException exception) {
            exception.printStackTrace();
        }

        return null;
    }

}
