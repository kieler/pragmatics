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
 * 
 *****************************************************************************/
package de.cau.cs.kieler.karma;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.draw2d.ui.render.figures.ScalableImageFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.swt.graphics.Color;

/**
 * 
 * @author ckru
 * Class for encapsulating the actual drawn figure to avoid inheritance problems.
 * Also has some additional methods for example changing resizeability. 
 * 
 */
public class SwitchableFigure extends Shape {

    /**
     * The figure that will actually be displayed.
     */
    private IFigure currentFigure;

    /**
     * Trigger for the figure being resizeable or not.
     */
    private boolean resizeable = true;

    /**
     * The constructor. Sets default figure.
     */
    public SwitchableFigure() {
        super();
        currentFigure = defaultFigure();
    }

    private IFigure defaultFigure() {
        RectangleFigure rectangle = new RectangleFigure();
        rectangle.setLineWidth(1);
        rectangle.setForegroundColor(ColorConstants.black);
        rectangle.setBackgroundColor(ColorConstants.white);
        return rectangle;
    }

    @Override
    public void paint(final Graphics graphics) {
        super.paint(graphics);
        if (currentFigure != null) {
            Rectangle newBounds = new Rectangle();
            newBounds.x = this.getBounds().x + 1;
            newBounds.y = this.getBounds().y + 1;
            newBounds.width = this.getBounds().width - 1;
            newBounds.height = this.getBounds().height - 1;
            currentFigure.setBounds(newBounds);
            currentFigure.paint(graphics);
        }
    }

    /**
     * Getter for the currently displayed figure.
     * 
     * @return the current figure
     */
    public IFigure getCurrentFigure() {
        return currentFigure;
    }

    /**
     * Setter for the currently displayed figure.
     * 
     * @param figure
     *            new figure to be displayed
     */
    public void setCurrentFigure(final IFigure figure) {
        currentFigure = figure;
        currentFigure.setParent(this.getParent());
        currentFigure.setBounds(super.getBounds());

        if (this.getParent() instanceof DefaultSizeNodeFigure
                && figure instanceof ScalableImageFigure) {
            ((DefaultSizeNodeFigure) this.getParent()).setDefaultSize(
                    ((ScalableImageFigure) figure).getImage().getBounds().width + 2,
                    ((ScalableImageFigure) figure).getImage().getBounds().height + 2);
        }
        // this.repaint();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setForegroundColor(final Color fg) {
        if (currentFigure != null) {
            super.setForegroundColor(fg);
            currentFigure.setForegroundColor(fg);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBackgroundColor(final Color bg) {
        if (currentFigure != null) {
            super.setBackgroundColor(bg);
            currentFigure.setBackgroundColor(bg);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBounds(final Rectangle rect) {
        if (resizeable) {
            super.setBounds(rect);
            if (currentFigure != null) {
                this.currentFigure.setBounds(rect);
            }
        }
    }

    /**
     * Directly set the bounds of this figure, without further checks.
     * 
     * @param rect
     *            the new bounds
     */
    public void setBoundsDirect(final Rectangle rect) {
        super.setBounds(rect);
        if (currentFigure != null) {
            this.currentFigure.setBounds(rect);
        }
    }

    @Override
    public void setMinimumSize(final Dimension d) {
        if (resizeable) {
            super.setMinimumSize(d);
        }
    }

    @Override
    public void setMaximumSize(final Dimension d) {
        if (resizeable) {
            super.setMaximumSize(d);
        }
    }

    @Override
    public void setSize(final int w, final int h) {
        if (resizeable) {
            super.setSize(w, h);
        }
    }

    @Override
    public void setPreferredSize(final Dimension d) {
        if (resizeable) {
            super.setPreferredSize(d);
        }
    }

    @Override
    protected void fillShape(final Graphics graphics) {

    }

    @Override
    protected void outlineShape(final Graphics graphics) {

    }

    @Override
    public void setLineWidth(final int w) {
        if (currentFigure instanceof Shape) {
            ((Shape) currentFigure).setLineWidth(w);
        }
    }

    @Override
    public int getLineWidth() {
        if (currentFigure instanceof Shape) {
            return ((Shape) currentFigure).getLineWidth();
        } else {
            return -1;
        }
    }

    @Override
    public void setLineWidthFloat(final float value) {
        if (currentFigure instanceof Shape) {
            ((Shape) currentFigure).setLineWidthFloat(value);
        }
    }

    @Override
    public float getLineWidthFloat() {
        if (currentFigure instanceof Shape) {
            return ((Shape) currentFigure).getLineWidthFloat();
        } else {
            return -1;
        }
    }

    @Override
    public void setLineStyle(final int style) {
        if (currentFigure instanceof Shape) {
            ((Shape) currentFigure).setLineStyle(style);
        }
    }

    @Override
    public int getLineStyle() {
        if (currentFigure instanceof Shape) {
            return ((Shape) currentFigure).getLineStyle();
        } else {
            return -1;
        }
    }
    
    @Override
    public void setLineDash(final float[] dash) {
        if (currentFigure instanceof Shape) {
            ((Shape) currentFigure).setLineDash(dash);
        }
    }
    
    @Override
    public float[] getLineDash() {
        if (currentFigure instanceof Shape) {
            return ((Shape) currentFigure).getLineDash();
        } else {
            return null;
        }
    }

    /**
     * check if the figure may be be resized.
     * 
     * @return true if resizeable else false
     */
    public boolean getResizeable() {
        return resizeable;
    }

    /**
     * set the figure to be resizeable or not.
     * 
     * @param b false for not resizeable
     */
    public void setResizeable(final Boolean b) {
        resizeable = b;
    }

}
