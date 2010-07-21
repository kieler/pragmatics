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
import org.eclipse.ui.PlatformUI;

import de.cau.cs.kieler.kiml.evol.ui.EvolView;
import de.cau.cs.kieler.kiml.evol.ui.EvolView.TargetIndividuals;

/**
 * Command for performing {@code NUMBER_OF_STEPS} steps of the evolutionary
 * algorithm. Every step creates a new generation.
 *
 * @author bdu
 *
 */
public class EvolveHandler extends AbstractHandler {
    /**
     * Number of evolution steps.
     */
    private static final int NUMBER_OF_STEPS = 5;
    /**
     * Auto-rate all individuals after how many steps?
     */
    private static final int STEPS_PER_AUTO_RATING = 1;
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
    private static final int MAX_STEPS = 10;

    /**
     * {@inheritDoc}
     */
    public Object execute(final ExecutionEvent event) throws ExecutionException {
        final EvolView view =
                (EvolView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
                        .findView(EvolView.ID);

        if ((view != null) && (view.getPopulation() != null)) {
            int steady = 0;
            int steps = 0;
            do {
                final Double before = view.getPopulation().getAverageRating();
                System.out.println("Average rating before: " + before);
                for (int i = 0; i < NUMBER_OF_STEPS; i++) {
                    view.evolve();
                    final boolean wantAutoRating;
                    wantAutoRating = wantAutoRatingForStep(i, STEPS_PER_AUTO_RATING);
                    if (wantAutoRating) {
                        view.autorateIndividuals(view.getPopulation(), TargetIndividuals.ALL, null);
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

            } while ((steady < STEADY_STEPS) && (steps < MAX_STEPS));
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
