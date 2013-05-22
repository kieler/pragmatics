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

package de.cau.cs.kieler.core.model.gmf.effects;

import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.ui.parts.ScrollingGraphicalViewer;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IBorderItemEditPart;
import org.eclipse.gmf.runtime.diagram.ui.render.editparts.RenderedDiagramRootEditPart;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.swt.widgets.Display;

import de.cau.cs.kieler.core.kivi.AbstractEffect;

/**
 * 
 * This effect scrolls to the given eobject or coordinates.
 * 
 * @author ckru
 * 
 */
public class PanningEffect extends AbstractEffect {

    private EObject target = null;
    private Point coordinates = null;
    private FigureCanvas canvas = null;
    private DiagramDocumentEditor editor = null;
    private boolean animate = false;
    private double zoomLevel = 1.0;
    private boolean zoom = false;
    private boolean center = true;

    /**
     * Constructor of the PanningEffect with coordinates as scrolling target.
     * 
     * @param editor
     *            the editor
     * @param coordinates
     *            the coordinates to be scrolled to
     * @param animate
     *            true if scrolling should be animated
     * @param zoom
     *            true if zooming should be done
     * @param zoomLevel
     *            the desired zoom level (i.e. 1.5 = 150%)
     */
    public PanningEffect(final DiagramDocumentEditor editor, final Point coordinates,
            final boolean animate, final boolean zoom, final double zoomLevel) {
        this.coordinates = coordinates;
        this.editor = editor;
        this.animate = animate;
        this.zoom = zoom;
        this.zoomLevel = zoomLevel;

        if (editor.getDiagramGraphicalViewer() instanceof ScrollingGraphicalViewer) {
            ScrollingGraphicalViewer scrollViewer = (ScrollingGraphicalViewer) editor
                    .getDiagramGraphicalViewer();
            if (scrollViewer.getControl() instanceof FigureCanvas) {
                canvas = (FigureCanvas) scrollViewer.getControl();
            }
        }
    }

    /**
     * Constructor of the PanningEffect with coordinates as scrolling target.
     * 
     * @param editor
     *            the editor
     * @param coordinates
     *            the coordinates to be scrolled to
     * @param animate
     *            true if scrolling should be animated
     * @param zoom
     *            true if zooming should be done
     * @param zoomLevel
     *            the desired zoom level (i.e. 1.5 = 150%)
     * @param center
     *            true if the panning effect should center the object false for panning to upper
     *            left corner.
     */
    public PanningEffect(final DiagramDocumentEditor editor, final Point coordinates,
            final boolean animate, final boolean zoom, final double zoomLevel, final boolean center) {
        this(editor, coordinates, animate, zoom, zoomLevel);
        this.center = center;
    }

    /**
     * Constructor of the PanningEffect with EObject as scrolling target.
     * 
     * @param editor
     *            the editor
     * @param target
     *            the coordinates to be scrolled to
     * @param animate
     *            true if scrolling should be animated
     * @param zoom
     *            true if zooming should be done
     * @param zoomLevel
     *            the desired zoom level (i.e. 1.5 = 150%)
     */
    public PanningEffect(final DiagramDocumentEditor editor, final EObject target,
            final boolean animate, final boolean zoom, final double zoomLevel) {
        this.target = target;
        this.editor = editor;
        this.animate = animate;
        this.zoom = zoom;
        this.zoomLevel = zoomLevel;

        if (editor.getDiagramGraphicalViewer() instanceof ScrollingGraphicalViewer) {
            ScrollingGraphicalViewer scrollViewer = (ScrollingGraphicalViewer) editor
                    .getDiagramGraphicalViewer();
            if (scrollViewer.getControl() instanceof FigureCanvas) {
                canvas = (FigureCanvas) scrollViewer.getControl();
            }
        }
    }

    /**
     * Constructor of the PanningEffect with EObject as scrolling target.
     * 
     * @param editor
     *            the editor
     * @param target
     *            the coordinates to be scrolled to
     * @param animate
     *            true if scrolling should be animated
     * @param zoom
     *            true if zooming should be done
     * @param zoomLevel
     *            the desired zoom level (i.e. 1.5 = 150%)
     * @param center
     *            true if the panning effect should center the object false for panning to upper
     *            left corner.
     */
    public PanningEffect(final DiagramDocumentEditor editor, final EObject target,
            final boolean animate, final boolean zoom, final double zoomLevel, final boolean center) {
        this(editor, target, animate, zoom, zoomLevel);
        this.center = center;
    }

    /**
     * Helper method to get the absolute coordinates of an model element to scroll to that location.
     * 
     * @param part
     *            the edit part of the modelelement
     * @param point
     *            the relative location of the model element
     * @return the absolute location of the model element
     */
    private Point getAbsoluteCoords(final GraphicalEditPart part, final Point point) {
        int x = point.x;
        int y = point.y;
        EObject parentObject = null;
        if (part instanceof IBorderItemEditPart) {
            parentObject = part.getNotationView().getElement().eContainer().eContainer();
        } else {
            parentObject = part.getNotationView().getElement().eContainer();
        }
        if (parentObject != null) {
            EditPart parent = this.editor.getDiagramEditPart().findEditPart(
                    editor.getDiagramEditPart(), parentObject);
            if (parent instanceof GraphicalEditPart) {
                Point parentLocation = ((GraphicalEditPart) parent).getFigure().getBounds()
                        .getLocation();
                x += parentLocation.x;
                y += parentLocation.y;
            }
            while (parentObject.eContainer() != null) {
                parentObject = parentObject.eContainer();
                parent = this.editor.getDiagramEditPart().findEditPart(editor.getDiagramEditPart(),
                        parentObject);
                if (parent instanceof GraphicalEditPart) {
                    Point parentLocation = ((GraphicalEditPart) parent).getFigure().getBounds()
                            .getLocation();
                    x += parentLocation.x;
                    y += parentLocation.y;
                }
            }
        }
        return new Point(x, y);
    }

    /**
     * {@inheritDoc}
     */
    public void execute() {
        final ZoomManager zoomManager = ((RenderedDiagramRootEditPart) editor.getDiagramEditPart()
                .getRoot()).getZoomManager();
        Display.getDefault().asyncExec(new Runnable() {
            public void run() {

                Point coords = null;
                GraphicalEditPart graphicalPart = null;
                if (target != null) {
                    EditPart part = editor.getDiagramEditPart().findEditPart(
                            editor.getDiagramEditPart(), target);
                    if (part instanceof GraphicalEditPart) {
                        graphicalPart = (GraphicalEditPart) part;
                        coords = graphicalPart.getFigure().getBounds().getLocation().getCopy();
                        // graphicalPart.getFigure().translateToAbsolute(coords);
                        coords = PanningEffect.this.getAbsoluteCoords(graphicalPart, coords);
                    }
                } else if (coordinates != null) {
                    coords = coordinates;
                }
                if (coords != null) {
                    if (zoom) {
                        zoomManager.setZoom(zoomLevel);
                    }
                    System.out.println("coords" + coords.toString());
                    int vcenteroffset = 0;
                    int hcenteroffset = 0;
                    if (center) {
                        vcenteroffset = canvas.getBounds().height / 2;
                        hcenteroffset = canvas.getBounds().width / 2;
                    }

                    if (animate) {
                        canvas.scrollSmoothTo(
                                (int) ((coords.x * zoomManager.getZoom()) - hcenteroffset),
                                (int) ((coords.y * zoomManager.getZoom()) - vcenteroffset));

                    } else {
                        canvas.scrollTo((int) ((coords.x * zoomManager.getZoom()) - hcenteroffset),
                                (int) ((coords.y * zoomManager.getZoom()) - vcenteroffset));
                    }
                }
            }
        });
    }

    @Override
    public String getName() {
        return "Panning";
    }

    /**
     * @return true if the panning effect should center the object false for panning to upper left
     *         corner.
     */
    public boolean getCenter() {
        return this.center;
    }

    /**
     * @param value
     *            true if the panning effect should center the object false for panning to upper
     *            left corner.
     */
    public void setCenter(final boolean value) {
        this.center = value;
    }

}
