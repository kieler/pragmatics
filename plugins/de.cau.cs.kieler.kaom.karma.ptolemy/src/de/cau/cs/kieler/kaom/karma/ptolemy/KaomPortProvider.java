package de.cau.cs.kieler.kaom.karma.ptolemy;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayoutManager;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.figures.BorderItemLocator;

import de.cau.cs.kieler.kaom.Port;
import de.cau.cs.kieler.karma.IRenderingProvider;

public class KaomPortProvider implements IRenderingProvider {

    public IFigure getFigureByString(String input, IFigure oldFigure, EObject object) {
        // TODO Auto-generated method stub
        return null;
    }

    public IFigure getDefaultFigure() {
        // TODO Auto-generated method stub
        return null;
    }

    public LayoutManager getLayoutManagerByString(String input, LayoutManager oldLayoutManager,
            EObject object) {
        // TODO Auto-generated method stub
        return null; //new KaomPortLayout();
    }

    public LayoutManager getDefaultLayoutManager() {
        // TODO Auto-generated method stub
        return null;
    }

    public BorderItemLocator getBorderItemLocatorByString(String input, IFigure parentFigure, Object locator) {
        BorderItemLocator newlocator = new BorderItemLocator(parentFigure);
        if (input.equals("NORTH")) {
            newlocator.setPreferredSideOfParent(PositionConstants.NORTH);
            newlocator.setCurrentSideOfParent(PositionConstants.NORTH);
            return newlocator;
        }
        
        return null;
    }

}
