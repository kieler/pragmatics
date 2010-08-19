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
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.ui.PlatformUI;

import de.cau.cs.kieler.kiml.evol.EvolModel;
import de.cau.cs.kieler.kiml.evol.EvolPlugin;
import de.cau.cs.kieler.kiml.evol.EvolUtil;
import de.cau.cs.kieler.kiml.evol.genetic.Genome;
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
     * @author bdu
     *
     */
    private static final class EvolveJob extends Job {
        boolean isAutoRatingEnabled() {
            return this.isAutoRatingEnabled;
        }

        void setAutoRatingEnabled(final boolean theIsAutoRatingEnabled) {
            this.isAutoRatingEnabled = theIsAutoRatingEnabled;
        }

        /**
         *
         */
        private final EvolModel model;
        private boolean isAutoRatingEnabled;

        /**
         * Creates a new {@link EvolveJob} instance.
         *
         * @param theName
         * @param theModel
         */
        EvolveJob(final String theName, final EvolModel theModel, final boolean wantAutoRating) {
            super(theName);
            this.model = theModel;
            this.isAutoRatingEnabled = wantAutoRating;
        }

        @Override
        protected IStatus run(final IProgressMonitor theMonitor) {
            // final double before =
            // model.getPopulation().getAverageRating().doubleValue();

            this.model.evolve(new SubProgressMonitor(theMonitor, 100));

            return new Status(IStatus.OK, EvolPlugin.PLUGIN_ID,
                    "Evolve job completed successfully.");
        }
    }

    // Parameter identifiers.
    /**
     *
     */
    private static final String PARAM_STEPS_PER_AUTO_RATING =
            "de.cau.cs.kieler.kiml.evol.stepsBeforeAutoRating";

    /**
     *
     */
    private static final String PARAM_MAX_STEPS = "de.cau.cs.kieler.kiml.evol.steps";

    /**
     * Number of evolution steps.
     */
    private static final int NUMBER_OF_STEPS = 5;
    /**
     * Auto-rate all individuals after how many steps?
     */
    private static final int STEPS_PER_AUTO_RATING = 5;
    /**
     * Percentual increase still considered as non-steady.
     */
    private static final double MIN_INCREASE = 0.005;

    /**
     * After this number of steady steps, the execution is stopped.
     */
    private static final int STEADY_STEPS = 100;
    /**
     * Total maximum steps. Execution is stopped in any case after this number
     * of steps.
     */
    private static final int MAX_STEPS = 20;

    /**
     * {@inheritDoc}
     */
    public Object execute(final ExecutionEvent event) throws ExecutionException {
        final EvolView view =
                (EvolView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
                        .findView(EvolView.ID);

        if ((view == null)) {
            return null;
        }

        final EvolModel model = view.getEvolModel();
        if (((model == null) || !model.isValid())) {
            return null;
        }

        final String maxStepsAttr = event.getParameter(PARAM_MAX_STEPS);
        final int maxSteps = (maxStepsAttr == null ? MAX_STEPS : Integer.parseInt(maxStepsAttr));
        final String stepsBeforeAutoRatingAttr = event.getParameter(PARAM_STEPS_PER_AUTO_RATING);
        final int stepsBeforeAutoRating =
                (stepsBeforeAutoRatingAttr == null ? STEPS_PER_AUTO_RATING : Integer
                        .parseInt(stepsBeforeAutoRatingAttr));

        final int steady = 0;

        final EvolveJob evolveJob = new EvolveJob("Evolving", model, false);

        final IProgressMonitor monitor = Job.getJobManager().createProgressGroup();

        evolveJob.setProgressGroup(monitor, IProgressMonitor.UNKNOWN);
        evolveJob.setPriority(Job.SHORT);
        evolveJob.setUser(true);

        for (int steps = 0; steps < maxSteps; steps++) {
            final boolean wantAutoRating = isAutoRatingStep(steps, stepsBeforeAutoRating);

            evolveJob.setAutoRatingEnabled(wantAutoRating);

            evolveJob.addJobChangeListener(new JobChangeAdapter() {
                @Override
                public void done(final IJobChangeEvent event) {
                    if (event.getResult().isOK()) {
                        // The current individual has changed.

                        // Get the current individual from the model.
                        final Genome individual = model.getCurrentIndividual();
                        Assert.isNotNull(individual);

                        // Get the expected layout provider id.
                        final String expectedLayoutProviderId = model.getLayoutProviderId();
                        Assert.isNotNull(expectedLayoutProviderId);

                        // Adopt and layout the current individual.
                        EvolUtil.syncApplyIndividual(individual, expectedLayoutProviderId);

                        // Refresh the layout view.
                        EvolUtil.asyncRefreshLayoutView();

                        if (evolveJob.isAutoRatingEnabled()) {
                            // Calculate auto-rating in the current editor for
                            // all individuals.
                            model.autoRateAll(new SubProgressMonitor(monitor, 100));

                            System.out.println(model.getPopulation());
                        }

                        // BasicNetwork b = new BasicNetwork();
                        // b.addLayer(new BasicLayer(2));
                        // b.addLayer(new BasicLayer(3));
                        // b.addLayer(new BasicLayer(6));
                        // b.addLayer(new BasicLayer(1));
                        // b.getStructure().finalizeStructure();
                        // System.out.println(b.calculateNeuronCount());

                        Assert.isNotNull(model.getPopulation());
                    } else {
                        System.err.println("The evolve job did not complete successfully.");
                    }
                }
            });

            evolveJob.schedule();

            try {
                evolveJob.join();
            } catch (final InterruptedException exception) {
                exception.printStackTrace();
            }

            // // Examine difference.
            // final double after =
            // model.getPopulation().getAverageRating().doubleValue();
            // final double relDiff = (after - before) / after;
            // System.out.println("Average rating before: " + before);
            // System.out.println("Average rating now: " + after);
            // final double relDiffPercent = (relDiff * 100);
            // System.out.println("rel. Diff (%): " + relDiffPercent);

            // // Do we have significant improvement?
            // if (relDiff < MIN_INCREASE) {
            // steady++;
            // System.out.println("Steady: " + steady);
            // } else {
            // if (steady > 0) {
            // steady--;
            // }
            // }
            if (steady >= STEADY_STEPS) {
                break;
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
        return (((i + 1) % p) == 0);
    }
}
