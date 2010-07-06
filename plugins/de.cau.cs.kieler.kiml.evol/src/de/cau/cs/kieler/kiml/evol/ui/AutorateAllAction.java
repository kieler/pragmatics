package de.cau.cs.kieler.kiml.evol.ui;

import org.eclipse.jface.action.Action;

import de.cau.cs.kieler.kiml.evol.ui.EvolView.TargetIndividuals;

/**
 * An action that assigns an automatic rating to all individuals.
 * 
 * @author bdu
 * @deprecated
 */
@Deprecated
public class AutorateAllAction extends Action {
    /**
     * Creates a new instance.
     *
     * @param theView
     *            the view this action is applied to.
     */
    public AutorateAllAction(final EvolView theView) {
        this.setText("Auto-rate");
        this.setToolTipText("Auto-rate all individuals");
        this.view = theView;
    }

    private final EvolView view;

    @Override
    public void run() {
        this.view.autorateIndividuals(this.view.getPopulation(), TargetIndividuals.ALL, null);
    }
}
