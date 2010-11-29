package de.cau.cs.kieler.kaom.karma.renderingprovider;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayoutManager;
import org.eclipse.draw2d.RoundedRectangle;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gmf.runtime.draw2d.ui.figures.IBorderItemLocator;

import de.cau.cs.kieler.core.ui.figures.DoubleRoundedRectangle;
import de.cau.cs.kieler.kaom.Entity;
import de.cau.cs.kieler.kaom.Link;
import de.cau.cs.kieler.kaom.diagram.edit.parts.Entity3EditPart;
import de.cau.cs.kieler.karma.IRenderingProvider;

public class PtolemyStateMachineProvider implements IRenderingProvider {

    /** line width for initial states. */
    private static final int INIT_LINE_WIDTH = 4;
    /** line width for initial final states. */
    private static final float INIT_FINAL_LINE_WIDTH = 2.1f;
    
    public IFigure getFigureByString(String input, IFigure oldFigure, EObject object, EditPart part) {
        IFigure stateFigure = null;
        if (input.equals("normalState")) {
            stateFigure = createNormalFigure();
        } else if (input.equals("initialState")) {
            stateFigure = createInitialFigure();
        } else if (input.equals("finalState")) {
            stateFigure = createFinalFigure();
        } else if (input.equals("initialFinalStateFigure")) {
            stateFigure = createInitialFinalFigure();
        } else {
            stateFigure = createNormalFigure();
        }     
        return stateFigure;
    }

    public LayoutManager getLayoutManagerByString(String input, LayoutManager oldLayoutManager,
            EObject object) {
        // TODO Auto-generated method stub
        return null;
    }

    public IBorderItemLocator getBorderItemLocatorByString(String input, IFigure parentFigure,
            Object locator, EObject object) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Create a figure for normal states.
     * 
     * @return a figure for normal states
     */
    private IFigure createNormalFigure() {
        RoundedRectangle figure = new RoundedRectangleWithBorder();
        makeNormalState(figure);
        return figure;
    }
    
    /**
     * Create a figure for initial states.
     * 
     * @return a figure for initial states.
     */
    private IFigure createInitialFigure() {
        RoundedRectangle figure = new RoundedRectangleWithBorder();
        makeInitialState(figure);
        return figure;
    }
    
    /**
     * Create a figure for final states.
     * 
     * @return a figure for final states
     */
    private IFigure createFinalFigure() {
        RoundedRectangle figure = new DoubleRoundedRectangle();
        makeFinalState(figure);
        return figure;
    }
    
    /**
     * Create a figure for final states.
     * 
     * @return a figure for final states
     */
    private IFigure createInitialFinalFigure() {
        RoundedRectangle figure = new DoubleRoundedRectangle();
        makeInitialFinalState(figure);
        return figure;
    }
    
    /**
     * Apply the normal state properties to a rounded rectangle figure.
     * 
     * @param figure
     *            the figure to change
     */
    private void makeNormalState(final RoundedRectangle figure) {
        figure.setCornerDimensions(new Dimension(30,30));
        figure.setFill(true);
        figure.setOpaque(false);
        figure.setLineWidth(1);
        figure.setForegroundColor(ColorConstants.black);
    }
    
    /**
     * Apply the initial state properties to a rounded rectangle figure.
     * 
     * @param figure
     *            the figure to change
     */
    private void makeInitialState(final RoundedRectangle figure) {
        makeNormalState(figure);
        figure.setLineWidth(INIT_LINE_WIDTH);
    }
    
    /**
     * Apply the final state properties to a rounded rectangle figure.
     * 
     * @param figure
     *            the figure to change
     */
    private void makeFinalState(final RoundedRectangle figure) {
        makeNormalState(figure);
    }
    
    /**
     * Apply the initialFinal state properties to a rounded rectangle figure.
     * 
     * @param figure
     *            the figure to change
     */
    private void makeInitialFinalState(final RoundedRectangle figure) {
        makeNormalState(figure);
        figure.setLineWidthFloat(INIT_FINAL_LINE_WIDTH);
    }
    
    private static class RoundedRectangleWithBorder extends RoundedRectangle {

        /**
         * @see Shape#outlineShape(Graphics)
         */
        @Override
        protected void fillShape(final Graphics graphics) {
            graphics.fillRoundRectangle(getBounds(), corner.width, corner.height);

            float lineInset = Math.max(1.0f, getLineWidthFloat()) / 2.0f;
            int inset1 = (int) Math.floor(lineInset);
            int inset2 = (int) Math.ceil(lineInset);

            Rectangle r = Rectangle.SINGLETON.setBounds(getBounds());
            r.x += inset1;
            r.y += inset1;
            r.width -= inset1 + inset2;
            r.height -= inset1 + inset2;

            graphics.drawRoundRectangle(r, Math.max(0, corner.width - (int) lineInset),
                    Math.max(0, corner.height - (int) lineInset));
        }

    }
    
}
