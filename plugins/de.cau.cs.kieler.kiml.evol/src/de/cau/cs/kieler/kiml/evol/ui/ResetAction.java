package de.cau.cs.kieler.kiml.evol.ui;

import org.eclipse.jface.action.Action;

/**
 * Action for resetting the view.
 * 
 * @author bdu
 * @deprecated
 */
@Deprecated
public class ResetAction extends Action {
    public ResetAction(final EvolView theView) {
        setText("Reset");
        setToolTipText("Restart with a new population.");
        this.view = theView;
    }

    private final EvolView view;

    @Override
    public void run() {
        view.reset();
    }
}

