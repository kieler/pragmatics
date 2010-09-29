package de.cau.cs.kieler.kaom.karma.ptolemy;

import java.lang.reflect.Constructor;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayoutManager;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.figures.BorderItemLocator;

import ptolemy.kernel.CompositeEntity;
import ptolemy.kernel.Entity;

import de.cau.cs.kieler.core.annotations.Annotation;
import de.cau.cs.kieler.core.annotations.StringAnnotation;
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

    public BorderItemLocator getBorderItemLocatorByString(String input, IFigure parentFigure, Object locator, EObject object) {
        
        if (input.equals("NORTH")) {
            BorderItemLocator newlocator = new BorderItemLocator(parentFigure, PositionConstants.NORTH);
            return newlocator;
        } else if (input.equals("EAST")) {
            BorderItemLocator newlocator = new BorderItemLocator(parentFigure, PositionConstants.EAST);
            return newlocator;
        } else if (input.equals("SOUTH")) {
            BorderItemLocator newlocator = new BorderItemLocator(parentFigure, PositionConstants.SOUTH);
            return newlocator;
        } else if (input.equals("WEST")) {
            BorderItemLocator newlocator = new BorderItemLocator(parentFigure, PositionConstants.WEST);
            return newlocator;    
        }
        
        return null;
    }

}
