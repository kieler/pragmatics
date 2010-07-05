package de.cau.cs.kieler.kiml.evol.ui;

import org.eclipse.jface.action.Action;

import de.cau.cs.kieler.kiml.evol.Individual;

/**
 * Action for rating an individual.
 * 
 * @author bdu
 * @deprecated
 */
@Deprecated
public class ChangeRatingAction extends Action {

    public ChangeRatingAction(final EvolView theView, final int theDelta) {
        this.delta = theDelta;
        this.view = theView;
    }

    private final int delta;
    private final EvolView view;

    @Override
    public void run() {
        if (this.view.getPopulation() != null) {
            final Individual ind = this.view.getCurrentIndividual();
            final int rating = ind.getRating() + this.delta;
            ind.setRating(rating);
            // this.view.getTableViewer().refresh();
        }
    }
}

