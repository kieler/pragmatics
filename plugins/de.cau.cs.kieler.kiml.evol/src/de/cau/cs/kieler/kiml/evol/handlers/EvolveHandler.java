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
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.ui.PlatformUI;

import de.cau.cs.kieler.kiml.evol.EvolModel;
import de.cau.cs.kieler.kiml.evol.EvolPlugin;
import de.cau.cs.kieler.kiml.evol.EvolUtil;
import de.cau.cs.kieler.kiml.evol.ui.EvolView;

/**
 * Command for performing {@code NUMBER_OF_STEPS} steps of the evolutionary
 * algorithm. Every step creates a new generation.
 *
 * @author bdu
 *
 */
public class EvolveHandler extends AbstractHandler {
    /**
     * A job change adapter that can be used to react on an evolution job.
     *
     * @author bdu
     *
     */
    private static final class EvolutionJobChangeAdapter extends JobChangeAdapter {
        /**
         * The progress monitor.
         */
        private final IProgressMonitor monitor;
        /**
         * The evolution model.
         */
        private final EvolModel model;
        private final boolean isAutoRatingEnabled;

        /** Creates a new {@link EvolutionJobChangeAdapter} instance.
         *
         * @param theEvolveJob
         * @param theMonitor
         * @param theModel
         */
        EvolutionJobChangeAdapter(
                final IProgressMonitor theMonitor,
                final EvolModel theModel,
                final boolean wantAutoRating) {
            this.monitor = theMonitor;
            this.model = theModel;
            this.isAutoRatingEnabled = wantAutoRating;
        }

        @Override
        public void done(final IJobChangeEvent event) {
            if (event.getResult().isOK()) {
                // The current individual has changed --> apply it.

                EvolUtil.applyCurrentIndividual(this.model);

                if (this.isAutoRatingEnabled) {
                    // Calculate auto-rating in the current editor for
                    // all individuals.
                    final int ticks = 100;
                    this.model.autoRateAll(new SubProgressMonitor(this.monitor, ticks));
                }

                assert this.model.getPopulation() != null;

            } else {
                EvolPlugin.showError("The evolution job did not complete successfully.", null);
            }
        }
    }

    /**
     *
     * @author bdu
     *
     */
    private static final class EvolveJob extends Job {

        /**
         * The evolution model.
         */
        private final EvolModel model;

        /**
         * Creates a new {@link EvolveJob} instance. This job can perform an
         * evolutionary step on the evolution model it is initialized with.
         *
         * @param theName
         *            the name of the job
         * @param theModel
         *            the evolution model
         */
        EvolveJob(final String theName, final EvolModel theModel) {
            super(theName);
            this.model = theModel;
        }

        @Override
        protected IStatus run(final IProgressMonitor theMonitor) {

            final int work = 100;
            this.model.evolve(new SubProgressMonitor(theMonitor, work));

            return new Status(IStatus.OK, EvolPlugin.PLUGIN_ID,
                    "Evolve job completed successfully.");
        }
    }

    // Parameter identifiers.
    /**
     * Identifier for the parameter "Steps before auto rating".
     */
    private static final String PARAM_STEPS_PER_AUTO_RATING =
            "de.cau.cs.kieler.kiml.evol.stepsBeforeAutoRating";

    /**
     * Identifier for the parameter "maximum steps".
     */
    private static final String PARAM_MAX_STEPS = "de.cau.cs.kieler.kiml.evol.steps";

    /**
     * Auto-rate all individuals after how many steps?
     */
    private static final int STEPS_PER_AUTO_RATING = 5;

    /**
     * Total maximum steps. Execution is stopped in any case after this number
     * of steps.
     */
    private static final int MAX_STEPS = 20;

    /**
     * {@inheritDoc}
     */
    public Object execute(final ExecutionEvent executionEvent) throws ExecutionException {
        EvolView view =
                (EvolView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
                        .findView(EvolView.ID);

        if (view == null) {
            return null;
        }

        EvolModel model = view.getEvolModel();
        if ((model == null) || !model.isValid()) {
            return null;
        }

        String maxStepsAttr = executionEvent.getParameter(PARAM_MAX_STEPS);
        int maxSteps = maxStepsAttr == null ? MAX_STEPS : Integer.parseInt(maxStepsAttr);

        String stepsBeforeAutoRatingAttr =
                executionEvent.getParameter(PARAM_STEPS_PER_AUTO_RATING);
        int stepsBeforeAutoRating =
                stepsBeforeAutoRatingAttr == null ? STEPS_PER_AUTO_RATING : Integer
                        .parseInt(stepsBeforeAutoRatingAttr);

        EvolveJob evolveJob = new EvolveJob("Evolving", model);

        IProgressMonitor monitor = Job.getJobManager().createProgressGroup();

        evolveJob.setProgressGroup(monitor, IProgressMonitor.UNKNOWN);
        evolveJob.setPriority(Job.LONG);
        evolveJob.setUser(true);

        for (int steps = 0; steps < maxSteps; steps++) {
            boolean wantAutoRating = isAutoRatingStep(steps, stepsBeforeAutoRating);

            evolveJob.addJobChangeListener(new EvolutionJobChangeAdapter(monitor,
                    model, wantAutoRating));

            evolveJob.schedule();

            try {
                evolveJob.join();
            } catch (final InterruptedException exception) {
                exception.printStackTrace();
                EvolPlugin.showError("The evolution job was interrupted.", exception);
            }
        }

        return null;
    }

    /**
     *
     * @param i
     * @param p
     * @return {@code true} iff step i is an auto-rating step.
     */
    private boolean isAutoRatingStep(final int i, final int p) {
        if (p == 1) {
            return true;
        }
        return ((i + 1) % p) == 0;
    }
}
