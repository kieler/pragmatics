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
     * A job for refreshing the evolution view.
     * 
     * @author bdu
     * 
     */
    private static final class EvolutionViewRefreshJob extends Job {
        public EvolView getView() {
            return this.view;
        }

        /**
         *
         */
        private final EvolView view;

        /**
         * Creates a new {@link EvolutionViewRefreshJob} instance.
         *
         * @param theName
         * @param theView
         */
        public EvolutionViewRefreshJob(final String theName, final EvolView theView) {
            super(theName);
            this.view = theView;
        }

        @Override
        protected IStatus run(final IProgressMonitor theMonitor) {
            Display.getDefault().asyncExec(new Runnable() {
                public void run() {
                    final EvolView evolView = EvolutionViewRefreshJob.this.getView();
                    evolView.getTableViewer().refresh();
                    evolView.refresh(false);
                }
            });
            return new Status(IStatus.INFO, EvolPlugin.PLUGIN_ID, 0, "OK", null);
        }
    }

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

        if ((view != null) && (view.getPopulation() != null)) {
            final String maxStepsAttr = event.getParameter("de.cau.cs.kieler.kiml.evol.steps");
            final int maxSteps =
                    (maxStepsAttr == null ? MAX_STEPS : Integer.parseInt(maxStepsAttr));

            final String stepsBeforeAutoRatingAttr =
                    event.getParameter("de.cau.cs.kieler.kiml.evol.stepsBeforeAutoRating");

            final int stepsBeforeAutoRating =
                    (stepsBeforeAutoRatingAttr == null ? STEPS_PER_AUTO_RATING : Integer
                            .parseInt(stepsBeforeAutoRatingAttr));
            int steady = 0;
            int steps = 0;
            do {
                final Double before = view.getPopulation().getAverageRating();
                System.out.println("Average rating before: " + before);
                for (int i = 0; (i < NUMBER_OF_STEPS) && (steps < maxSteps); i++) {
                    view.evolve();
                    final boolean wantAutoRating;
                    wantAutoRating = wantAutoRatingForStep(i, stepsBeforeAutoRating);
                    if (wantAutoRating) {
                        final IEditorPart editor = EvolUtil.getCurrentEditor();
                        Assert.isNotNull(editor);

                        // Calculate auto-rating for all individuals.
                        EvolUtil.autoRateIndividuals(view.getPopulation(), editor, null);
                        System.out.println(view.getPopulation());
                    }
                    steps++;
                    Assert.isNotNull(view.getPopulation());

                    final Double after = view.getPopulation().getAverageRating();
                    final Double relDiff = (after - before) / after;
                    System.out.println("Average rating now: " + after);
                    System.out.println("rel. Diff (%): " + relDiff * 100);
                    if (relDiff < MIN_INCREASE) {
                        steady++;
                        System.out.println("Steady: " + steady);
                    } else {
                        if (steady > 0) {
                            steady--;
                        }
                        System.out.println(relDiff);
                    }
                }

            } while ((steady < STEADY_STEPS) && (steps < maxSteps));


            final Job refreshJob = new EvolutionViewRefreshJob("Refresh table viewer", view);

            refreshJob.setUser(false);
            refreshJob.setPriority(Job.DECORATE);
            refreshJob.schedule(500);

        }
        return null;
    }

    private boolean wantAutoRatingForStep(final int i, final int p) {
        if (p == 1) {
            return true;
        }
        return (((i + 1) % p) == 0);
    }
}
