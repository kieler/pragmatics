package de.cau.cs.kieler.karma.util;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.diagram.ui.figures.BorderItemLocator;

public class CustomPortLocator extends BorderItemLocator {

    public CustomPortLocator(IFigure parentFigure) {
        super(parentFigure);
        // TODO Auto-generated constructor stub
    }

    public CustomPortLocator(int interval, IFigure parentFigure) {
        super(interval, parentFigure);
        // TODO Auto-generated constructor stub
    }

    public CustomPortLocator(IFigure parentFigure, int preferredSide) {
        super(parentFigure, preferredSide);
        // TODO Auto-generated constructor stub
    }

    public CustomPortLocator(IFigure parentFigure, int preferredSide, int interval) {
        super(parentFigure, preferredSide, interval);
        // TODO Auto-generated constructor stub
    }

    public CustomPortLocator(IFigure borderItem, IFigure parentFigure, Rectangle constraint) {
        super(borderItem, parentFigure, constraint);
        // TODO Auto-generated constructor stub
    }

    public CustomPortLocator(IFigure borderItem, IFigure parentFigure, Rectangle constraint,
            int interval) {
        super(borderItem, parentFigure, constraint, interval);
        // TODO Auto-generated constructor stub
    }

    public Rectangle getPublicConstraint() {
        return this.getConstraint();
    }
    
}
