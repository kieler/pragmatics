/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.ui.util;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Ellipse;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.Polyline;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.ui.PlatformUI;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.ui.layout.DiagramLayoutManager;
import de.cau.cs.kieler.kiml.ui.layout.ILayoutInspector;
import de.cau.cs.kieler.kiml.util.IDebugCanvas;
import de.cau.cs.kieler.kiml.util.KimlUtil;

/**
 * A canvas for drawing debug information on the active diagram.
 * 
 * @author mri
 */
public class DebugCanvas implements IDebugCanvas {

    /** the layer that is drawn on. */
    private IFigure layer;
    /** whether the canvas has currently any figures on it. */
    private boolean isDirty;
    /** the x-Offset of this canvas. */
    private float xOffset;
    /** the y-Offset of this canvas. */
    private float yOffset;
    /** whether buffered mode is active. */
    private boolean isBuffered;
    /** the buffer of figures to be drawn. */
    private List<IFigure> figureBuffer = new LinkedList<IFigure>();

    /**
     * Sets the canvas up for the given layout manager. This Method must be called
     * before any drawing can be done.
     * 
     * @param layoutManager the current diagram layout manager
     */
    public void setManager(final DiagramLayoutManager layoutManager) {
        clear();
        ILayoutInspector inspector = layoutManager.getInspector(layoutManager.getCurrentEditPart());
        layer = inspector.getDrawingLayer();
    }

    /**
     * {@inheritDoc}
     */
    public void setOffset(final KNode parentNode, final float addx, final float addy) {
        xOffset = addx;
        yOffset = addy;
        KNode currentNode = parentNode;
        while (currentNode != null) {
            KShapeLayout nodeLayout = currentNode.getData(KShapeLayout.class);
            xOffset += nodeLayout.getXpos();
            yOffset += nodeLayout.getYpos();
            currentNode = currentNode.getParent();
        }
    }

    /**
     * Translates colors into swt colors.
     * 
     * @param color the color
     * @return the swt color
     */
    private org.eclipse.swt.graphics.Color translateColor(final Color color) {
        switch (color) {
        case BLUE:
            return ColorConstants.blue;
        case CYAN:
            return ColorConstants.cyan;
        case GRAY:
            return ColorConstants.gray;
        case GREEN:
            return ColorConstants.green;
        case ORANGE:
            return ColorConstants.orange;
        case RED:
            return ColorConstants.red;
        case WHITE:
            return ColorConstants.white;
        case YELLOW:
            return ColorConstants.yellow;
        case BLACK:
        default:
            return ColorConstants.black;
        }
    }
    
    /**
     * Draws the given figure depending on the buffered mode.
     * 
     * @param figure the figure to be drawn
     */
    private void drawFigure(final IFigure figure) {
        if (isBuffered) {
            figureBuffer.add(figure);
        } else {
            PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
                public void run() {
                    layer.add(figure);
                }
            });
        }
    }

    /**
     * {@inheritDoc}
     */
    public void drawFilledRectangle(final float x, final float y,
            final float w, final float h, final Color color) {
        if (layer != null) {
            Rectangle bounds = new Rectangle((int) (xOffset + x),
                    (int) (yOffset + y), (int) w, (int) h);
            final RectangleFigure rect = new RectangleFigure();
            rect.setForegroundColor(translateColor(color));
            rect.setBackgroundColor(translateColor(color));
            rect.setFill(true);
            rect.setBounds(bounds);
            drawFigure(rect);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void drawRectangle(final float x, final float y, final float w,
            final float h, final Color color) {
        if (layer != null) {
            Rectangle bounds = new Rectangle((int) (xOffset + x),
                    (int) (yOffset + y), (int) w, (int) h);
            final RectangleFigure rect = new RectangleFigure();
            rect.setForegroundColor(translateColor(color));
            rect.setFill(false);
            rect.setBounds(bounds);
            drawFigure(rect);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void drawFilledEllipse(final float x, final float y, final float w,
            final float h, final Color color) {
        if (layer != null) {
            Rectangle bounds = new Rectangle((int) (xOffset + x),
                    (int) (yOffset + y), (int) w, (int) h);
            final Ellipse ellipse = new Ellipse();
            ellipse.setForegroundColor(translateColor(color));
            ellipse.setBackgroundColor(translateColor(color));
            ellipse.setFill(true);
            ellipse.setBounds(bounds);
            drawFigure(ellipse);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void drawEllipse(final float x, final float y, final float w,
            final float h, final Color color) {
        if (layer != null) {
            Rectangle bounds = new Rectangle((int) (xOffset + x),
                    (int) (yOffset + y), (int) w, (int) h);
            final Ellipse ellipse = new Ellipse();
            ellipse.setForegroundColor(translateColor(color));
            ellipse.setFill(false);
            ellipse.setBounds(bounds);
            drawFigure(ellipse);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void drawFilledCircle(final float x, final float y, final float d,
            final Color color) {
        if (layer != null) {
            Rectangle bounds = new Rectangle((int) (xOffset + x),
                    (int) (yOffset + y), (int) d, (int) d);
            final Ellipse ellipse = new Ellipse();
            ellipse.setForegroundColor(translateColor(color));
            ellipse.setBackgroundColor(translateColor(color));
            ellipse.setFill(true);
            ellipse.setBounds(bounds);
            drawFigure(ellipse);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void drawCircle(final float x, final float y, final float d,
            final Color color) {
        if (layer != null) {
            Rectangle bounds = new Rectangle((int) (xOffset + x),
                    (int) (yOffset + y), (int) d, (int) d);
            final Ellipse ellipse = new Ellipse();
            ellipse.setForegroundColor(translateColor(color));
            ellipse.setFill(false);
            ellipse.setBounds(bounds);
            drawFigure(ellipse);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void drawLine(final float x1, final float y1, final float x2,
            final float y2, final Color color) {
        if (layer != null) {
            final Polyline line = new Polyline();
            line.addPoint(new Point(x1 + xOffset, y1 + yOffset));
            line.addPoint(new Point(x2 + xOffset, y2 + yOffset));
            line.setForegroundColor(translateColor(color));
            drawFigure(line);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void drawString(final String string, final float x, final float y,
            final Color color) {
        if (layer != null) {
            final Label label = new Label();
            label.setText(string);
            label.setForegroundColor(translateColor(color));
            label.setLocation(new Point(x + xOffset, y + yOffset));
            drawFigure(label);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void clear() {
        if (layer != null && isDirty) {
            PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
                public void run() {
                    int i = 0;
                    while (layer.getChildren().size() > i) {
                        Object obj = layer.getChildren().get(i);
                        if (obj instanceof IFigure) {
                            IFigure figure = (IFigure) obj;
                            layer.remove(figure);
                        } else {
                            ++i;
                        }
                    }
                }
            });

            isDirty = false;
        }
        figureBuffer.clear();
    }

    /**
     * {@inheritDoc}
     */
    public void drawBuffer() {
        if (layer != null) {
            PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
                public void run() {
                    for (IFigure figure : figureBuffer) {
                        layer.add(figure);
                        // getPreferredSize can only be called after the label
                        // is attached to a parent due to a swt bug
                        if (figure instanceof Label) {
                            Label label = (Label) figure;
                            label.setSize(label.getPreferredSize());
                        }
                    }
                    figureBuffer.clear();
                }
            });
        }
    }

    /**
     * {@inheritDoc}
     */
    public void setBuffered(final boolean buffered) {
        this.isBuffered = buffered;
    }
    
}
