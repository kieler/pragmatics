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

import java.util.Collections;
import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayoutManager;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.RotatableDecoration;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.Transaction;
import org.eclipse.emf.workspace.AbstractEMFOperation;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IResizableCompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.figures.BorderItemLocator;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.RoutingStyle;
import org.eclipse.gmf.runtime.notation.Smoothness;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.ui.statushandlers.StatusManager;
import org.w3c.dom.Document;

import ptolemy.kernel.util.NamedObj;
import ptolemy.vergil.icon.EditorIcon;
import de.cau.cs.kieler.core.model.gmf.figures.SplineConnection;
import de.cau.cs.kieler.kaom.custom.EntityLayout;
import de.cau.cs.kieler.kaom.karma.ptolemy.figurecreation.FigureProvider;
import de.cau.cs.kieler.kaom.karma.ptolemy.figurecreation.PtolemyFetcher;
import de.cau.cs.kieler.karma.AdvancedRenderingBorderedShapeEditPart;
import de.cau.cs.kieler.karma.IRenderingProvider;

/**
 * Karma rendering provider for rendering ptolemy diagrams in kaom.
 * 
 * @author ckru
 * @kieler.ignore (excluded from review process)
 */
public class KaomRenderingProvider implements IRenderingProvider {

    /**
     * Width of connection line.
     */
    private static final float LINE_WIDTH = 1.5f;

    /**
     * Radius of the rounded edges.
     */
    private static final int ROUNDED_BENDPOINTS_RADIUS = 5;

    /**
     * a class that holds logic to build the actual figures. Main purpose is to keep this class
     * smaller.
     */
    private FigureProvider figureProvider = new FigureProvider();

    /**
     * {@inheritDoc}
     */
    public IFigure getFigureByString(final String input, final IFigure oldFigure,
            final EObject object, final EditPart part) {

        /*
        if (part instanceof TopGraphicEditPart) {
            TopGraphicEditPart ep = (TopGraphicEditPart) part;
            List<EditPart> resizeableCompartments = ep.getResizableCompartments();
            for (EditPart compartment : resizeableCompartments) {
                if (compartment instanceof IResizableCompartmentEditPart) {
                IResizableCompartmentEditPart resizeComp = (IResizableCompartmentEditPart) compartment;
                    ResizableCompartmentEditPolicy rcep = (ResizableCompartmentEditPolicy) resizeComp
                            .getEditPolicy("PrimaryDrag Policy");
                    rcep.deactivate();
                }
            }
        }
        */
        if (input.equals("_IconDescription")) {
            this.hideCompartment(part, true);
            return createPtolemyFigure(PtolemyFetcher.getPtolemyInstance(object));
        } else if (input.equals("MonitorValue")) {
            this.hideCompartment(part, true);
            return figureProvider.createMonitorValue(object);
        } else if (input.equals("compound")) {
            //this.unhideCompartment(part);
            return figureProvider.getDefaultFigure();
        } else if (input.equals("compoundCollapsed")) {
            //this.hideCompartment(part, false);
            IFigure figure = createPtolemyFigure(PtolemyFetcher.getPtolemyInstance(object));
            return figure;
        } else if (input.startsWith("valueDisplay")) {
            this.hideCompartment(part, true);
            String[] parts = input.split("//");
            return figureProvider.createValueFigure(object, parts[1], part);
        } else if (input.equals("Director")) {
            this.hideCompartment(part, true);
            return figureProvider.createDirector();
        } else if (input.equals("accumulator")) {
            this.hideCompartment(part, true);
            return figureProvider.createAccumulator();
        } else if (input.equals("connection")) {
            if (oldFigure instanceof SplineConnection) {
                // This is a connection between common actors. Switch the Splinedrawing off,
                // set line width and remove decoration
                SplineConnection connection = ((SplineConnection) oldFigure);
                connection.setTargetDecoration(null);
                connection.setLineWidthFloat(LINE_WIDTH);
                connection.setSplineMode(SplineConnection.SPLINE_OFF);
                final ConnectionEditPart cPart = (ConnectionEditPart) part;

                // Encapsulate setting the BendpointRadius and Smoothness in an emf operation.
                // Eclipse needs it that way.
                AbstractEMFOperation emfOp = new AbstractEMFOperation(cPart.getEditingDomain(),
                        "line routing setting", Collections.singletonMap(
                                Transaction.OPTION_UNPROTECTED, true)) {
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
                    // execute above operation
                    OperationHistoryFactory.getOperationHistory().execute(emfOp, null, null);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

                return oldFigure;
            } else {
                return null;
            }
        } else if (input.equals("stateConnection")) {
            // This is a connection between states so turn spline drawing on,
            // set line width and use an arrow as decoration.
            if (oldFigure instanceof SplineConnection) {
                SplineConnection connection = ((SplineConnection) oldFigure);
                connection.setTargetDecoration(createArrowDecoration());
                connection.setLineWidthFloat(LINE_WIDTH);
                connection.setSplineMode(SplineConnection.SPLINE_CUBIC);
                return oldFigure;

            } else {
                return null;
            }
        } else if (input.equals("inputPort")) {
            IFigure f = figureProvider.createInputPort();
            //They just have to have thoses sizes to be displayed correctly.
            // SUPPRESS CHECKSTYLE NEXT Magic Number
            f.setMinimumSize(new Dimension(30, 30));
            // SUPPRESS CHECKSTYLE NEXT Magic Number
            f.setPreferredSize(new Dimension(30, 30));
            return f;         
        } else if (input.equals("outputPort")) {
            return figureProvider.createOutputPort();
        } else {
            return figureProvider.getDefaultFigure();
        }
    }
    
    /* Might be reused later on.
    private void unhideCompartment(EditPart part) {
        if (part instanceof AdvancedRenderingBorderedShapeEditPart) {
        AdvancedRenderingBorderedShapeEditPart arbsep = (AdvancedRenderingBorderedShapeEditPart) part;
            List<EditPart> resizeableCompartments = arbsep.getResizableCompartments();
            for (EditPart compartment : resizeableCompartments) {
                if (compartment instanceof IResizableCompartmentEditPart) {
             IResizableCompartmentEditPart resizeComp = (IResizableCompartmentEditPart) compartment;
                    resizeComp.getFigure().setVisible(true);
                }
            }
            
            arbsep.setCollapseExpandSize(new Dimension(8,8));
            
        }
    }
    */
    
    /**
     * Hides a compartment so that you don't accidently click it.
     * @param part editpart that owns the compartment to hide.
     * @param hideExpandCollapse should the object still be expandable.
     */
    private void hideCompartment(final EditPart part, final boolean hideExpandCollapse) {
        if (part instanceof AdvancedRenderingBorderedShapeEditPart) {
            AdvancedRenderingBorderedShapeEditPart arbsep = 
                    (AdvancedRenderingBorderedShapeEditPart) part;
            //Can't do check here cause generics are badly designed.
            @SuppressWarnings("unchecked")
            List<EditPart> resizeableCompartments = arbsep.getResizableCompartments();
            for (EditPart compartment : resizeableCompartments) {
                if (compartment instanceof IResizableCompartmentEditPart) {
                    IResizableCompartmentEditPart resizeComp = 
                            (IResizableCompartmentEditPart) compartment;
                    resizeComp.getFigure().setVisible(false);
                }
            }
            if (hideExpandCollapse) {
                arbsep.setCollapseExpandSize(new Dimension(0, 0));
            } else {
                //Thats just how big the button is.
                // SUPPRESS CHECKSTYLE NEXT Magic Number
                arbsep.setCollapseExpandSize(new Dimension(8, 8));
            }
        }
        
    }
    
    // minimum size for compound entites. Important if they are collapsed
    private static final int DEFAULT_SIZE_X = 63;
    private static final int DEFAULT_SIZE_Y = 43;

    /**
     * {@inheritDoc}
     */
    public LayoutManager getLayoutManagerByString(final String input,
            final LayoutManager oldLayoutManager, final EObject object) {
        // enforce some minimum sizes to optimize the support of focus and contact
        // non compound entities can just use default of the layoutmanager so do nothing in that
        // case
        if (input.equals("compound")) {
            if (oldLayoutManager instanceof EntityLayout) {
                EntityLayout el = (EntityLayout) oldLayoutManager;
                el.setFixedMinSize(DEFAULT_SIZE_X, DEFAULT_SIZE_Y);
            }
            return oldLayoutManager;
        } else if (input.equals("compoundCollapsed")) {
            if (oldLayoutManager instanceof EntityLayout) {
                EntityLayout el = (EntityLayout) oldLayoutManager;
                el.setFixedMinSize(DEFAULT_SIZE_X, DEFAULT_SIZE_Y);
            }
            return oldLayoutManager;
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public BorderItemLocator getBorderItemLocatorByString(final String input, final IFigure parent,
            final Object locator, final EObject object, final CollapseStatus collapseStatus) {
        // normal entities are not borderitems so they don't need a locator
        return null;
    }

    // some constants that describe the arrow decoration
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
            return figureProvider.getErrorFigure();
        } else {
            // get all icons for this element
            List<EditorIcon> icons = PtolemyFetcher.fetchIcons(nObj);
            // if there is none use svg description
            if (icons.isEmpty()) {
                Document doc = PtolemyFetcher.fetchSvgDoc(nObj);
                if (doc != null) {
                    IFigure figure = figureProvider.createFigureFromSvg(doc);
                    return figure;
                } else {
                    Status myStatus = new Status(IStatus.WARNING, "de.cau.cs.kieler.kaom.karma.ptolemy",
                            "couldn't get svg document from ptolemy");
                    StatusManager.getManager().handle(myStatus, StatusManager.SHOW);
                    return figureProvider.getErrorFigure();
                }

                // else use the first icon (usually there should be only one anyway)
            } else {
                EditorIcon icon = icons.get(0);
                IFigure figure = figureProvider.createFigureFromIcon(icon);
                return figure;
            }

        }

    }

    /**
     * {@inheritDoc}
     */
    public Dimension getSizeByString(final String input, final EObject object, final EditPart part) {
        return null;
    }

}
