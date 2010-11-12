package de.cau.cs.kieler.karma.util;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.diagram.ui.figures.BorderItemLocator;


/**
 * Custom BorderItemLocator used by the ptolemy port position mechanism. 
 * Works exactly the same as the normal BorderItemLocator just added a method for public access
 * of the constraint attribute. 
 * @author ckru
 *
 */
public class CustomPortLocator extends BorderItemLocator {

    public CustomPortLocator(IFigure parentFigure) {
        super(parentFigure);
    }

    public CustomPortLocator(int interval, IFigure parentFigure) {
        super(interval, parentFigure);
    }

    public CustomPortLocator(IFigure parentFigure, int preferredSide) {
        super(parentFigure, preferredSide);
    }

    public CustomPortLocator(IFigure parentFigure, int preferredSide, int interval) {
        super(parentFigure, preferredSide, interval);
    }

    public CustomPortLocator(IFigure borderItem, IFigure parentFigure, Rectangle constraint) {
        super(borderItem, parentFigure, constraint);
    }

    public CustomPortLocator(IFigure borderItem, IFigure parentFigure, Rectangle constraint,
            int interval) {
        super(borderItem, parentFigure, constraint, interval);
    }

    /**
     * public getter for the constraint attribute while the normal one is protected.
     * @return the constraint
     */
    public Rectangle getPublicConstraint() {
        return this.getConstraint();
    }
    
}
