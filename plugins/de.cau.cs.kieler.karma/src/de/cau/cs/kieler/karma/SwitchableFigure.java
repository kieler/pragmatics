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

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;

/**
 * 
 * @author ckru
 * 
 */
public class SwitchableFigure extends Shape { // implements IAttributeAwareFigure {

    /**
     * The figure that will actually be displayed.
     */
    private IFigure currentFigure;

    /**
     * The constructor. Sets default figure.
     */
    public SwitchableFigure() {
        super();
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
     * @return the current figure
     */
    public IFigure getCurrentFigure() {
        return currentFigure;
    }

    /**
     * Setter for the currently displayed figure.
     * @param figure new figure to be displayed
     */
    public void setCurrentFigure(final IFigure figure) {
        currentFigure = figure;
        currentFigure.setBounds(super.getBounds());
        //this.repaint();
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
        // Notifier target = getTarget();
        /*
        LayoutManager layoutManager = getLayoutManager();
        if (((StateLayout) layoutManager).getNotifier() instanceof State
                && layoutManager instanceof StateLayout) {
            ((StateLayout) layoutManager).checkNewSize(this,
                    (State) ((StateLayout) layoutManager).getNotifier(), rect);
        }
         */
        super.setBounds(rect);
        if (currentFigure != null) {
            this.currentFigure.setBounds(rect);
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
    }

    @Override
    protected void fillShape(Graphics graphics) {
       
    }

    @Override
    protected void outlineShape(Graphics graphics) {
        
    }
    
    @Override
    public void setLineWidth(int w) {
        if (currentFigure instanceof Shape) {
            ((Shape)currentFigure).setLineWidth(w);
        }
    }
    
    @Override
    public int getLineWidth() {
        if (currentFigure instanceof Shape) {
            return ((Shape)currentFigure).getLineWidth();
        } else {
            return -1;
        }
    }
    
    @Override
    public void setLineWidthFloat(float value) {
        if (currentFigure instanceof Shape) {
            ((Shape)currentFigure).setLineWidthFloat(value);
        }
    }
    
    @Override
    public float getLineWidthFloat() {
        if (currentFigure instanceof Shape) {
            return ((Shape)currentFigure).getLineWidthFloat();
        } else {
            return -1;
        }
    }
    
    
}
