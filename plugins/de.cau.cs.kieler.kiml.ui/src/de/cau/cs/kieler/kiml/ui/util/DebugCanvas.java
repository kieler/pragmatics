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

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Polyline;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.Ellipse;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramRootEditPart;
import org.eclipse.ui.PlatformUI;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.layout.util.KimlLayoutUtil;
import de.cau.cs.kieler.kiml.ui.layout.DiagramLayoutManager;

/**
 * A canvas for drawing debug information on the active diagram.
 * 
 * @author mri
 */
public class DebugCanvas {

    /** color definitions. */
    public enum Color {
        /** the color black. */
        BLACK,
        /** the color blue. */
        BLUE,
        /** the color cyan. */
        CYAN,
        /** the color gray. */
        GRAY,
        /** the color green. */
        GREEN,
        /** the color orange. */
        ORANGE,
        /** the color red. */
        RED,
        /** the color white. */
        WHITE,
        /** the color yellow. */
        YELLOW

    }

    /** the layer that is drawn on. */
    private IFigure layer = null;

    /** the x-Offset of this canvas. */
    private float xOffset = 0;

    /** the y-Offset of this canvas. */
    private float yOffset = 0;

    /**
     * Constructs a debug canvas.
     * 
     * @param parentNode
     *            the parentNode this canvas is relative to
     */
    public DebugCanvas(final KNode parentNode) {
        DiagramEditPart editPart = KimlUiUtil
                .getDiagramEditPart(DiagramLayoutManager.getLastManager()
                        .getCurrentEditPart());
        if (editPart != null) {
            layer = editPart.getLayer(DiagramRootEditPart.PAGE_BREAKS_LAYER);

            // calculate the offset
            KNode currentNode = parentNode;
            while (currentNode != null) {
                KShapeLayout currentLayout = KimlLayoutUtil
                        .getShapeLayout(currentNode);
                xOffset += currentLayout.getXpos();
                yOffset += currentLayout.getYpos();
                currentNode = currentNode.getParent();
            }
        }
    }

    /**
     * Translates colors into swt colors.
     * 
     * @param color
     *            the color
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
     * Draws a filled rectangle on the canvas.
     * 
     * @param x
     *            the x-coordinate
     * @param y
     *            the y-coordinate
     * @param w
     *            the width
     * @param h
     *            the height
     * @param color
     *            the color
     */
    public void drawFilledRectangle(final float x, final float y,
            final float w, final float h, final Color color) {
        if (layer != null) {
            PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
                public void run() {
                    Rectangle bounds = new Rectangle((int) (xOffset + x),
                            (int) (yOffset + y), (int) w, (int) h);
                    RectangleFigure rect = new RectangleFigure();
                    rect.setForegroundColor(translateColor(color));
                    rect.setBackgroundColor(translateColor(color));
                    rect.setFill(true);
                    rect.setBounds(bounds);
                    layer.add(rect);
                }
            });
        }
    }

    /**
     * 
     * Draws a non-filled rectangle one the canvas.
     * 
     * @param x
     *            the x-coordinate
     * @param y
     *            the y-coordinate
     * @param w
     *            the width
     * @param h
     *            the height
     * @param color
     *            the color
     */
    public void drawRectangle(final float x, final float y, final float w,
            final float h, final Color color) {
        if (layer != null) {
            PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
                public void run() {
                    Rectangle bounds = new Rectangle((int) (xOffset + x),
                            (int) (yOffset + y), (int) w, (int) h);
                    RectangleFigure rect = new RectangleFigure();
                    rect.setForegroundColor(translateColor(color));
                    rect.setBackgroundColor(translateColor(color));
                    rect.setFill(false);
                    rect.setBounds(bounds);
                    layer.add(rect);
                }
            });
        }
    }

    /**
     * Draws a filled ellipse on the canvas.
     * 
     * @param x
     *            the x-coordinate
     * @param y
     *            the y-coordinate
     * @param w
     *            the width
     * @param h
     *            the height
     * @param color
     *            the color
     */
    public void drawFilledEllipse(final float x, final float y, final float w,
            final float h, final Color color) {
        if (layer != null) {
            PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
                public void run() {
                    Rectangle bounds = new Rectangle((int) (xOffset + x),
                            (int) (yOffset + y), (int) w, (int) h);
                    Ellipse ellipse = new Ellipse();
                    ellipse.setForegroundColor(translateColor(color));
                    ellipse.setBackgroundColor(translateColor(color));
                    ellipse.setFill(true);
                    ellipse.setBounds(bounds);
                    layer.add(ellipse);
                }
            });
        }
    }

    /**
     * Draws a non-filled ellipse on the canvas.
     * 
     * @param x
     *            the x-coordinate
     * @param y
     *            the y-coordinate
     * @param w
     *            the width
     * @param h
     *            the height
     * @param color
     *            the color
     */
    public void drawEllipse(final float x, final float y, final float w,
            final float h, final Color color) {
        if (layer != null) {
            PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
                public void run() {
                    Rectangle bounds = new Rectangle((int) (xOffset + x),
                            (int) (yOffset + y), (int) w, (int) h);
                    Ellipse ellipse = new Ellipse();
                    ellipse.setForegroundColor(translateColor(color));
                    ellipse.setBackgroundColor(translateColor(color));
                    ellipse.setFill(false);
                    ellipse.setBounds(bounds);
                    layer.add(ellipse);
                }
            });
        }
    }

    /**
     * Draws a filled circle on the canvas.
     * 
     * @param x
     *            the x-coordinate
     * @param y
     *            the y-coordinate
     * @param d
     *            the diameter
     * @param color
     *            the color
     */
    public void drawFilledCircle(final float x, final float y, final float d,
            final Color color) {
        if (layer != null) {
            PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
                public void run() {
                    Rectangle bounds = new Rectangle((int) (xOffset + x),
                            (int) (yOffset + y), (int) d, (int) d);
                    Ellipse ellipse = new Ellipse();
                    ellipse.setForegroundColor(translateColor(color));
                    ellipse.setBackgroundColor(translateColor(color));
                    ellipse.setFill(true);
                    ellipse.setBounds(bounds);
                    layer.add(ellipse);
                }
            });
        }
    }

    /**
     * Draws a non-filled circle on the canvas.
     * 
     * @param x
     *            the x-coordinate
     * @param y
     *            the y-coordinate
     * @param d
     *            the diameter
     * @param color
     *            the color
     */
    public void drawCircle(final float x, final float y, final float d,
            final Color color) {
        if (layer != null) {
            PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
                public void run() {
                    Rectangle bounds = new Rectangle((int) (xOffset + x),
                            (int) (yOffset + y), (int) d, (int) d);
                    Ellipse ellipse = new Ellipse();
                    ellipse.setForegroundColor(translateColor(color));
                    ellipse.setBackgroundColor(translateColor(color));
                    ellipse.setFill(false);
                    ellipse.setBounds(bounds);
                    layer.add(ellipse);
                }
            });
        }
    }

    /**
     * Draws a line on the canvas.
     * 
     * @param x1
     *            the x-coordinate of the start point
     * @param y1
     *            the y-coordinate of the start point
     * @param x2
     *            the x-coordinate of the end point
     * @param y2
     *            the y-coordinate of the end point
     * @param color
     *            the color
     */
    public void drawLine(final float x1, final float y1, final float x2,
            final float y2, final Color color) {
        if (layer != null) {
            PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
                public void run() {
                    Polyline line = new Polyline();
                    line.addPoint(new Point(x1, y1));
                    line.addPoint(new Point(x2, y2));
                    line.setForegroundColor(translateColor(color));
                    layer.add(line);
                }
            });
        }
    }

    /**
     * Draws a string on the canvas.
     * 
     * @param string
     *            the string
     * @param x
     *            the x-coordinate
     * @param y
     *            the y-coordinate
     * @param color
     *            the color
     */
    //TODO make this working
    /*public void drawString(final String string, final float x, final float y,
            final Color color) {
        if (layer != null) {
            PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
                public void run() {
                    Label label = new Label();
                    label.setText(string);
                    Rectangle rect = new Rectangle((int) (xOffset + x),
                            (int) (yOffset + y), 100, 50);
                    label.setBounds(rect);
                    label.setForegroundColor(translateColor(color));
                    layer.add(label);
                }
            });
        }
    }*/

    /**
     * Clears the canvas.
     */
    public void clear() {
        if (layer != null) {
            PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
                public void run() {
                    while (layer.getChildren().size() > 0) {
                        Object obj = layer.getChildren().get(0);
                        if (obj instanceof IFigure) {
                            IFigure figure = (IFigure) obj;
                            layer.remove(figure);
                        }
                    }
                }
            });
        }
    }
}
