package de.cau.cs.kieler.kaom.karma.ptolemy;

import org.eclipse.draw2d.IFigure;

import de.cau.cs.kieler.core.ui.figures.layout.AbstractTableLayout;
import de.cau.cs.kieler.core.ui.figures.layout.container.ExtendedTable;

public class PtolemyEntityLayout extends AbstractTableLayout {
    IFigure figure;
    
    ExtendedTable ramplayout = ExtendedTable.build();
    
    public ExtendedTable getCorrespondingLayout(final IFigure figure) {
        return ramplayout;
    }
}