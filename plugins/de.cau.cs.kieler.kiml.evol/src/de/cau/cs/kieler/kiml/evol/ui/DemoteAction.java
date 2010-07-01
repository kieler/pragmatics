package de.cau.cs.kieler.kiml.evol.ui;

/**
 * Action for giving a negative rating to an individual.
 *
 * @author bdu
 *
 */
public class DemoteAction extends ChangeRatingAction {
    private static final int AMOUNT = -10;

    public DemoteAction(final EvolView theView) {
        super(theView, AMOUNT);
        setText("Disregard");
        setToolTipText("Demote the selected individual.");
    }
}
