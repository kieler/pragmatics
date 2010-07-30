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
    public static final class AutoRateAllJob extends Job {
        /**
         * The population to be rated.
         */
        private final Population population;

        /**
         * The editor in which the individuals shall be layouted.
         */
        private final IEditorPart editor;

        /**
         * @param name
         * @param pop
         * @param theEditor
         */
        AutoRateAllJob(final String name, final Population pop, final IEditorPart theEditor) {
            super(name);
            Assert.isLegal(pop != null);
            this.population = pop;
            this.editor = theEditor;
        }

        @Override
        protected IStatus run(final IProgressMonitor monitor) {
            try {
                Thread.sleep(300);

                monitor.beginTask("Performing Auto-rating", this.population.size() + 1);

                Thread.sleep(300);
                monitor.subTask("Determining Individual Rating");

                // Do the rating.
                EvolUtil.autoRateIndividuals(this.population, this.editor, monitor);

                if (monitor.isCanceled()) {
                    return new Status(IStatus.CANCEL, EvolPlugin.PLUGIN_ID,
                            "The auto-rating was cancelled.");
                }

                Thread.sleep(200);
                monitor.subTask("Determining the average rating");

                System.out.println("Average rating: " + this.population.getAverageRating());
                monitor.worked(1);

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

        if (view != null) {
            final Population pop = view.getPopulation();

            final IEditorPart editor = view.getEvolModel().getLastEditor();

            // Create a job for auto-rating.
            final Job autoRateAllJob = new AutoRateAllJob("auto-rating", pop, editor);

            // Process the job.
            final IProgressMonitor monitor = Job.getJobManager().createProgressGroup();
            final int size = pop.size();
            autoRateAllJob.setProgressGroup(monitor, size + 1);
            autoRateAllJob.setPriority(Job.SHORT);
            autoRateAllJob.setUser(true);
            autoRateAllJob.schedule();

            try {
                Thread.sleep(300);
            } catch (final InterruptedException e) {
                e.printStackTrace();
            }

            view.getTableViewer().refresh();

        } else {
            throw new ExecutionException("The Evolution View could not be found.");
        }

        return null;
    }
}
