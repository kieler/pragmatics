package de.cau.cs.kieler.kiml.evol.ui;

import org.eclipse.jface.action.Action;

/**
 * Action for performing a step of the evolutionary algorithm. This creates a
 * new generation.
 *
 * @author bdu
 * @deprecated
 *
 */
@Deprecated
public class EvolveAction extends Action {
    public EvolveAction(final EvolView theView) {
        setText("Evolve");
        this.view = theView;
    }

    @Override
    public void run() {
        view.evolve();
    }

    private final EvolView view;
}