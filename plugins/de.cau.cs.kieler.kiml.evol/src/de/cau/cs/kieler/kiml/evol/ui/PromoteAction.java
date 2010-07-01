package de.cau.cs.kieler.kiml.evol.ui;

/**
 * Action for giving a positive rating to an individual.
 * 
 * @author bdu
 * 
 */
public class PromoteAction extends ChangeRatingAction {
    private static final int AMOUNT = +30;
    
    public PromoteAction(final EvolView theView) {
        super(theView, AMOUNT);
        setText("Favor");
        setToolTipText("Promote the selected individual.");
    }
}
