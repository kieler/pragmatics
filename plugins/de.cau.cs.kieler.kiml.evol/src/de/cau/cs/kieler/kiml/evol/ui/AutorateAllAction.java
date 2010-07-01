package de.cau.cs.kieler.kiml.evol.ui;

import org.eclipse.jface.action.Action;

import de.cau.cs.kieler.kiml.evol.ui.EvolView.TargetIndividuals;

/**
 * An action that assigns an automatic rating to all individuals.
 *
 * @author bdu
 *
 */
public class AutorateAllAction extends Action {
    public AutorateAllAction(final EvolView theView) {
        this.setText("Auto-rate");
        this.setToolTipText("Auto-rate all individuals");
        view = theView;
    }

    private final EvolView view;

    @Override
    public void run() {
        view.autorateIndividuals(TargetIndividuals.ALL);
    }
}
