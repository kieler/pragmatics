package de.cau.cs.kieler.kiml.evol.handlers;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.PlatformUI;

import de.cau.cs.kieler.kiml.evol.genetic.Genome;
import de.cau.cs.kieler.kiml.evol.ui.EvolView;
import de.cau.cs.kieler.kiml.evol.ui.EvolView.SelectorTableViewer;

/**
 * Command for changing the rating of an individual.
 *
 * @author bdu
 *
 */
public class ChangeRatingHandler extends AbstractHandler {

    public Object execute(final ExecutionEvent event) throws ExecutionException {
        final String amount = event.getParameter("de.cau.cs.kieler.kiml.evol.amount");
        final int delta = Integer.parseInt(amount);
        final IViewPart view =
                PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
                        .findView(EvolView.ID);
        if (view instanceof EvolView) {
            if (((EvolView) view).getPopulation() != null) {
                final Genome ind = ((EvolView) view).getCurrentIndividual();
                final int rating = ind.getRating() + delta;
                ind.setRating(rating);
                final SelectorTableViewer tv = ((EvolView) view).getTableViewer();
                tv.refresh();
            }
        }
        return null;
    }

}
