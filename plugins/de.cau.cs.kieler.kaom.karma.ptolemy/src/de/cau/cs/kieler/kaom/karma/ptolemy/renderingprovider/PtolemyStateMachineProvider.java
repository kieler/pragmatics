/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2009 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */

package de.cau.cs.kieler.kaom.karma.ptolemy.renderingprovider;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayoutManager;
import org.eclipse.draw2d.RoundedRectangle;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.draw2d.ui.figures.IBorderItemLocator;

import de.cau.cs.kieler.core.model.figures.DoubleRoundedRectangle;
import de.cau.cs.kieler.karma.IRenderingProvider;

/**
 * RenderingProvider for ptolemy state machines.
 * 
 * @author ckru
 * 
 */
public class PtolemyStateMachineProvider implements IRenderingProvider {

    /** line width for initial states. */
    private static final int INIT_LINE_WIDTH = 4;
    /** line width for initial final states. */
    private static final float INIT_FINAL_LINE_WIDTH = 2.1f;

    /**
     * {@inheritDoc}
     */
    public IFigure getFigureByString(final String input, final IFigure oldFigure,
            final EObject object, final EditPart part) {
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

    /**
     * {@inheritDoc}
     */
    public LayoutManager getLayoutManagerByString(final String input,
            final LayoutManager oldLayoutManager, final EObject object) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public IBorderItemLocator getBorderItemLocatorByString(final String input,
            final IFigure parentFigure, final Object locator, final EObject object,
            final CollapseStatus collapseStatus) {
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

    private static final int STATE_SIZE = 30;

    /**
     * Apply the normal state properties to a rounded rectangle figure.
     * 
     * @param figure
     *            the figure to change
     */
    private void makeNormalState(final RoundedRectangle figure) {
        figure.setCornerDimensions(new Dimension(STATE_SIZE, STATE_SIZE));
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

    /**
     * A rounded rectangle with a border.
     * 
     */
    private static class RoundedRectangleWithBorder extends RoundedRectangle {

        /**
         * @see Shape#outlineShape(Graphics)
         */
        @Override
        protected void fillShape(final Graphics graphics) {
            Dimension corner = this.getCornerDimensions();
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

    /**
     * {@inheritDoc}
     */
    public Dimension getSizeByString(final String input, final EObject object, final EditPart part) {
        return null;
    }

}
