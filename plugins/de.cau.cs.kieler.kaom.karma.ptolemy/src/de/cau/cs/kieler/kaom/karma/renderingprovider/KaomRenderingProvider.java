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

package de.cau.cs.kieler.kaom.karma.renderingprovider;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayoutManager;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.RotatableDecoration;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.workspace.AbstractEMFOperation;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.figures.BorderItemLocator;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.RoutingStyle;
import org.eclipse.gmf.runtime.notation.Smoothness;
import org.eclipse.gmf.runtime.notation.View;
import org.w3c.dom.Document;

import ptolemy.kernel.util.NamedObj;
import ptolemy.vergil.icon.EditorIcon;
import de.cau.cs.kieler.core.annotations.Annotatable;
import de.cau.cs.kieler.core.annotations.Annotation;
import de.cau.cs.kieler.core.annotations.AnnotationsFactory;
import de.cau.cs.kieler.core.annotations.StringAnnotation;
import de.cau.cs.kieler.core.ui.figures.SplineConnection;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.kaom.Entity;
import de.cau.cs.kieler.kaom.custom.EntityLayout;
import de.cau.cs.kieler.kaom.diagram.edit.parts.Entity3EditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.EntityEntityCompartment2EditPart;
import de.cau.cs.kieler.kaom.impl.EntityImpl;
import de.cau.cs.kieler.karma.IRenderingProvider;
import de.cau.cs.kieler.karma.SwitchableFigure;
import de.cau.cs.kieler.karma.ptolemy.figurecreation.FigureParser;
import de.cau.cs.kieler.karma.ptolemy.figurecreation.FigureProvider;
import de.cau.cs.kieler.karma.ptolemy.figurecreation.PtolemyFetcher;
import de.cau.cs.kieler.karma.util.AdvancedRenderingEditPartUtil;

/**
 * Karma rendering provider for rendering ptolemy diagrams in kaom.
 * 
 * @author ckru
 * 
 */
public class KaomRenderingProvider implements IRenderingProvider {

    /**
     * Width of connection line.
     */
    private static final float LINE_WIDTH = 1.5f;

    /**
     * Radius of the rounded edges.
     */
    private static final int ROUNDED_BENDPOINTS_RADIUS = 0;

    /**
     * debug variable. If true svg graphics will be discarded. Simple rectangles are drawn instead.
     */
    private static boolean lightweightGraphics = false;

    private FigureProvider figureProvider = new FigureProvider(lightweightGraphics);

    /**
     * {@inheritDoc}
     */
    public IFigure getFigureByString(final String input, final IFigure oldFigure,
            final EObject object, final EditPart part) {
        if (input.equals("_IconDescription")) {
            return createPtolemyFigure(PtolemyFetcher.getPtolemyInstance(object));
        } else if (input.equals("MonitorValue")) {
            return figureProvider.createMonitorValue(object);
        } else if (input.equals("compound")) {
            return figureProvider.getDefaultFigure();
        } else if (input.equals("compoundCollapsed")) {
            IFigure figure = createPtolemyFigure(PtolemyFetcher.getPtolemyInstance(object));
            return figure;
        } else if (input.startsWith("valueDisplay")) {
            String[] parts = input.split("//");
            return figureProvider.createValueFigure(object, parts[1], part);
        } else if (input.equals("Director")) {
            return figureProvider.createDirector();
        } else if (input.equals("accumulator")) {
            return figureProvider.createAccumulator();
        } else if (input.equals("connection")) {
            if (oldFigure instanceof SplineConnection) {
                SplineConnection connection = ((SplineConnection) oldFigure);
                connection.setTargetDecoration(null);
                connection.setLineWidthFloat(LINE_WIDTH);
                connection.setSplineMode(SplineConnection.SPLINE_OFF);
                final ConnectionEditPart cPart = (ConnectionEditPart) part;
                AbstractEMFOperation emfOp = new AbstractEMFOperation(cPart.getEditingDomain(),
                        "line routing setting") {
                    @Override
                    protected IStatus doExecute(final IProgressMonitor monitor,
                            final IAdaptable info) throws ExecutionException {
                        RoutingStyle style = (RoutingStyle) ((View) cPart.getModel())
                                .getStyle(NotationPackage.Literals.ROUTING_STYLE);
                        style.setRoundedBendpointsRadius(ROUNDED_BENDPOINTS_RADIUS);
                        style.setSmoothness(Smoothness.NONE_LITERAL);
                        return Status.OK_STATUS;
                    }
                };

                try {
                    OperationHistoryFactory.getOperationHistory().execute(emfOp, null, null);
                } catch (ExecutionException e) {
                    // e.printStackTrace();
                }

                return oldFigure;
            } else {
                return null;
            }
        } else if (input.equals("stateConnection")) {
            if (oldFigure instanceof SplineConnection) {
                SplineConnection connection = ((SplineConnection) oldFigure);
                connection.setTargetDecoration(createArrowDecoration());
                connection.setLineWidthFloat(LINE_WIDTH);
                connection.setSplineMode(SplineConnection.SPLINE_CUBIC);
                return oldFigure;

            } else {
                return null;
            }
        } else {
            return getDefaultFigure();
        }
    }

    /**
     * builds a default figure for this diagram.
     * 
     * @return the default figure
     */
    public static IFigure getDefaultFigure() {
        RectangleFigure defaultFigure = new RectangleFigure();
        defaultFigure.setLineWidth(1);
        defaultFigure.setForegroundColor(ColorConstants.black);
        defaultFigure.setBackgroundColor(ColorConstants.white);
        return defaultFigure;
    }

    /**
     * {@inheritDoc}
     */
    public LayoutManager getLayoutManagerByString(final String input,
            final LayoutManager oldLayoutManager, final EObject object) {
        if (input.equals("compound")) {
            if (oldLayoutManager instanceof EntityLayout) {
                EntityLayout el = (EntityLayout) oldLayoutManager;
                el.setFixedMinSize(63, 43);
            }
            return oldLayoutManager;
        } else if (input.equals("compoundCollapsed")) {
            if (oldLayoutManager instanceof EntityLayout) {
                EntityLayout el = (EntityLayout) oldLayoutManager;
                el.setFixedMinSize(63, 43);
            }
            return oldLayoutManager;
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public LayoutManager getDefaultLayoutManager() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public BorderItemLocator getBorderItemLocatorByString(final String input, final IFigure parent,
            final Object locator, final EObject object) {
        return null;
    }

    private static final int ARROW_SIZE = 10;
    private static final double ARROW_X_SCALE = 1.0;
    private static final double ARROW_Y_SCALE = 0.5;

    /**
     * Create the arrow decoration.
     * 
     * @return The decoration.
     */
    private RotatableDecoration createArrowDecoration() {
        PolygonDecoration arrowDecoration = new PolygonDecoration();
        PointList arrowDecorationPoints = new PointList();
        arrowDecorationPoints.addPoint(-ARROW_SIZE, ARROW_SIZE);
        arrowDecorationPoints.addPoint(1, 0);
        arrowDecorationPoints.addPoint(-ARROW_SIZE, -ARROW_SIZE);
        arrowDecorationPoints.addPoint(-ARROW_SIZE / 2, 0);
        arrowDecoration.setTemplate(arrowDecorationPoints);
        arrowDecoration.setScale(ARROW_X_SCALE, ARROW_Y_SCALE);
        return arrowDecoration;
    }

    /**
     * creates an appropriate figure according to the _IconDescription attribute of a ptolemy actor.
     * 
     * @param object
     *            the model element
     * @return the figure
     */
    private IFigure createPtolemyFigure(final NamedObj nObj) {
        if (nObj == null) {
            return getDefaultFigure();
        } else {
            List<EditorIcon> icons = PtolemyFetcher.fetchIcons(nObj);
            if (icons.isEmpty()) {
                Dimension lightweightSize = new Dimension();
                Document doc = PtolemyFetcher.fetchSvgDoc(nObj, lightweightSize);
                IFigure figure = FigureParser.createFigure(doc);
                // IFigure figure = figureProvider.createFigureFromSvg(doc, lightweightSize);
                return figure;
            } else {
                EditorIcon icon = icons.get(0);
                IFigure figure = figureProvider.createFigureFromIcon(icon);
                return figure;
            }

        }

    }


}
