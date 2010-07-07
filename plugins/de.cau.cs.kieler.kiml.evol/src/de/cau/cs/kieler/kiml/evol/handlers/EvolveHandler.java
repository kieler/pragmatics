package de.cau.cs.kieler.kiml.evol.handlers;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.PlatformUI;

import de.cau.cs.kieler.kiml.evol.ui.EvolView;
import de.cau.cs.kieler.kiml.evol.ui.EvolView.TargetIndividuals;

/**
 * Command for performing a step of the evolutionary algorithm. This creates a
 * new generation.
 *
 * @author bdu
 *
 */
public class EvolveHandler extends AbstractHandler {
    /**
     * Number of evolution steps.
     */
    private static final int NUMBER_OF_STEPS = 12;
    /**
     * Auto-rate all individuals after how many steps?
     */
    private static final int AUTO_RATE_EVERY = 3;

    public Object execute(final ExecutionEvent event) throws ExecutionException {
        final EvolView view =
                (EvolView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
                        .findView(EvolView.ID);
        if (view != null) {
            for (int i = 0; i < NUMBER_OF_STEPS; i++) {
                view.evolve();
                if (((i + 1) % AUTO_RATE_EVERY) == 0) {
                    System.out.println("auto-rate");
                    view.autorateIndividuals(view.getPopulation(), TargetIndividuals.ALL, null);
                }
            }
        }
        return null;
    }
}
