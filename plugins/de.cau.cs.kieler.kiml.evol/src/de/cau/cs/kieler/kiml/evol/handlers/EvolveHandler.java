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
    private static final int NUMBER_OF_STEPS = 9;
    /**
     * Auto-rate all individuals after how many steps?
     */
    private static final int STEPS_PER_AUTO_RATING = 1;

    /**
     * {@inheritDoc}
     */
    public Object execute(final ExecutionEvent event) throws ExecutionException {
        final EvolView view =
                (EvolView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
                        .findView(EvolView.ID);
        if (view != null) {
            for (int i = 0; i < NUMBER_OF_STEPS; i++) {
                view.evolve();
                final boolean wantAutoRating;
                wantAutoRating = (((i + 1) % STEPS_PER_AUTO_RATING) == 0);
                if (wantAutoRating) {
                    view.autorateIndividuals(view.getPopulation(), TargetIndividuals.ALL, null);
                }
            }
            if (view.getPopulation() != null) {
                System.out.println("Average rating: " + view.getPopulation().getAverageRating());
            }
        }
        return null;
    }
}
