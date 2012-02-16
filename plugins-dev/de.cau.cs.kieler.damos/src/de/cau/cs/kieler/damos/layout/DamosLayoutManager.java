/* 
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.damos.layout;

import java.util.LinkedList;
import java.util.Map.Entry;

import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayoutManager;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.ui.editparts.AbstractBorderedShapeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.BorderedBorderItemEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.CompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramRootEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IBorderedShapeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ResizableCompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.label.ILabelDelegate;
import org.eclipse.gmf.runtime.draw2d.ui.mapmode.IMapMode;
import org.eclipse.swt.SWTException;
import org.eclipselabs.damos.diagram.ui.editparts.ComponentEditPart;
import org.eclipselabs.damos.diagram.ui.editparts.ITextualContentEditPart;
import org.eclipselabs.damos.diagram.ui.editparts.PortEditPart;
import org.eclipselabs.damos.diagram.ui.figures.ComponentFigure;
import org.eclipselabs.damos.diagram.ui.figures.StandardComponentLayout;
import org.eclipselabs.damos.diagram.ui.figures.StandardComponentLayoutData;
import org.eclipselabs.damos.diagram.ui.parts.BlockDiagramEditor;

import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.core.model.gmf.GmfFrameworkBridge;
import de.cau.cs.kieler.kiml.LayoutContext;
import de.cau.cs.kieler.kiml.config.IMutableLayoutConfig;
import de.cau.cs.kieler.kiml.config.VolatileLayoutConfig;
import de.cau.cs.kieler.kiml.gmf.GmfDiagramLayoutManager;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutDataFactory;
import de.cau.cs.kieler.kiml.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.impl.KEdgeLayoutImpl;
import de.cau.cs.kieler.kiml.klayoutdata.impl.KShapeLayoutImpl;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.kiml.ui.diagram.ApplyLayoutRequest;
import de.cau.cs.kieler.kiml.ui.diagram.LayoutMapping;
import de.cau.cs.kieler.kiml.util.KimlUtil;

/**
 * A specialized layout manager for Damos.
 *
 * @author msp
 */
public class DamosLayoutManager extends GmfDiagramLayoutManager {
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean supports(final Object object) {
        return object instanceof BlockDiagramEditor || object instanceof ComponentEditPart;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected LayoutMapping<IGraphicalEditPart> buildLayoutGraph(
            final IGraphicalEditPart layoutRootPart) {
        LayoutMapping<IGraphicalEditPart> mapping = new LayoutMapping<IGraphicalEditPart>();
        mapping.setProperty(CONNECTIONS, new LinkedList<ConnectionEditPart>());
        mapping.setProperty(STATIC_CONFIG, new VolatileLayoutConfig());

        // set the parent element
        mapping.setParentElement(layoutRootPart);

        // find the diagram edit part
        mapping.setProperty(DIAGRAM_EDIT_PART, GmfFrameworkBridge.getDiagramEditPart(layoutRootPart));

        IMapMode mapMode = ((DiagramRootEditPart) layoutRootPart.getRoot()).getMapMode();
        KNode topNode = KimlUtil.createInitializedNode();
        KShapeLayout shapeLayout = topNode.getData(KShapeLayout.class);
        Rectangle rootBounds = new PrecisionRectangle(layoutRootPart.getFigure().getBounds());
        mapMode.LPtoDP(rootBounds);
        if (layoutRootPart instanceof DiagramEditPart) {
            // start with the whole diagram as root for layout
            String labelText = ((DiagramEditPart) layoutRootPart).getDiagramView().getName();
            if (labelText.length() > 0) {
                KLabel label = KimlUtil.createInitializedLabel(topNode);
                label.setText(labelText);
            }
        } else {
            // start with a specific node as root for layout
            shapeLayout.setPos((float) rootBounds.preciseX(), (float) rootBounds.preciseY());
        }
        shapeLayout.setSize((float) rootBounds.preciseWidth(), (float) rootBounds.preciseHeight());
        mapping.getGraphMap().put(topNode, layoutRootPart);
        mapping.setLayoutGraph(topNode);

        // traverse the children of the layout root part
        buildLayoutGraphRecursively(mapping, layoutRootPart, topNode, layoutRootPart);
        // transform all connections in the selected area
        processConnections(mapping);

        return mapping;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void transferLayout(final LayoutMapping<IGraphicalEditPart> mapping) {
        DiagramEditPart diagramEditPart = mapping.getProperty(DIAGRAM_EDIT_PART);
        IMapMode mapMode = ((DiagramRootEditPart) diagramEditPart.getRoot()).getMapMode();
        
        // create a new request to change the layout
        ApplyLayoutRequest applyLayoutRequest = new ApplyLayoutRequest();
        applyLayoutRequest.setScale(mapMode.DPtoLP(1));
        for (Entry<KGraphElement, IGraphicalEditPart> entry : mapping.getGraphMap().entrySet()) {
            if (!(entry.getValue() instanceof DiagramEditPart)) {
                applyLayoutRequest.addElement(entry.getKey(), entry.getValue());
            }
        }
        KShapeLayout graphLayout = mapping.getLayoutGraph().getData(KShapeLayout.class);
        applyLayoutRequest.setUpperBound(graphLayout.getWidth(), graphLayout.getHeight());

        // retrieve a command for the request; the command is created by GmfLayoutEditPolicy
        Command applyLayoutCommand = diagramEditPart.getCommand(applyLayoutRequest);
        mapping.setProperty(LAYOUT_COMMAND, applyLayoutCommand);
    }

    /** the cached layout configuration for Damos. */
    private DamosLayoutConfig layoutConfig = new DamosLayoutConfig();

    /**
     * {@inheritDoc}
     */
    @Override
    public IMutableLayoutConfig getLayoutConfig() {
        return layoutConfig;
    }
    
    /**
     * Recursively builds a layout graph by analyzing the children of the given edit part.
     * 
     * @param mapping
     *            the layout mapping
     * @param parentEditPart
     *            the parent edit part of the current elements
     * @param parentLayoutNode
     *            the corresponding KNode
     * @param currentEditPart
     *            the currently analyzed edit part
     */
    private void buildLayoutGraphRecursively(final LayoutMapping<IGraphicalEditPart> mapping,
            final IGraphicalEditPart parentEditPart, final KNode parentLayoutNode,
            final IGraphicalEditPart currentEditPart) {
        // iterate through the children of the element
        for (Object obj : currentEditPart.getChildren()) {

            // check visibility of the child
            if (obj instanceof IGraphicalEditPart) {
                IFigure figure = ((IGraphicalEditPart) obj).getFigure();
                if (!figure.isVisible()) {
                    continue;
                }
            }

                // process a component port or compound port
            if (obj instanceof PortEditPart || obj instanceof BorderedBorderItemEditPart) {
                createPort(mapping, (ShapeNodeEditPart) obj, parentEditPart, parentLayoutNode);

                // process a compartment, which may contain other elements
            } else if (obj instanceof ResizableCompartmentEditPart
                    && ((CompartmentEditPart) obj).getChildren().size() > 0) {
                CompartmentEditPart compartment = (CompartmentEditPart) obj;
                buildLayoutGraphRecursively(mapping, parentEditPart, parentLayoutNode, compartment);

                // process a component or compound element
            } else if (obj instanceof AbstractBorderedShapeEditPart) {
                createNode(mapping, (AbstractBorderedShapeEditPart) obj, parentLayoutNode);
            }
        }
    }
    
    /**
     * Create a node while building the layout graph.
     * 
     * @param mapping
     *            the layout mapping
     * @param nodeEditPart
     *            the node edit part
     * @param parentKNode
     *            the corresponding parent layout node
     */
    private void createNode(final LayoutMapping<IGraphicalEditPart> mapping,
            final ShapeNodeEditPart nodeEditPart, final KNode parentKNode) {
        IFigure nodeFigure = nodeEditPart.getFigure();
        KNode childLayoutNode = KimlUtil.createInitializedNode();
        DiagramRootEditPart rootEditPart = (DiagramRootEditPart) nodeEditPart.getRoot();

        // set location and size
        PrecisionRectangle childBounds = new PrecisionRectangle(nodeFigure.getBounds());
        IMapMode mapMode = rootEditPart.getMapMode();
        mapMode.LPtoDP(childBounds);
        KShapeLayout nodeLayout = childLayoutNode.getData(KShapeLayout.class);
        nodeLayout.setPos((float) childBounds.preciseX(), (float) childBounds.preciseY());
        nodeLayout.setSize((float) childBounds.preciseWidth(), (float) childBounds.preciseHeight());
        // the modification flag must initially be false
        ((KShapeLayoutImpl) nodeLayout).resetModificationFlag();
        
        // determine minimal size of the node
        try {
            Dimension minSize = nodeFigure.getMinimumSize();
            mapMode.LPtoDP(minSize);
            VolatileLayoutConfig staticConfig = mapping.getProperty(STATIC_CONFIG);
            staticConfig.setValue(LayoutOptions.MIN_WIDTH, childLayoutNode, LayoutContext.GRAPH_ELEM,
                    (float) minSize.width);
            staticConfig.setValue(LayoutOptions.MIN_HEIGHT, childLayoutNode, LayoutContext.GRAPH_ELEM,
                    (float) minSize.height);
        } catch (SWTException exception) {
            // ignore exception and leave the default minimal size
        }

        parentKNode.getChildren().add(childLayoutNode);
        mapping.getGraphMap().put(childLayoutNode, nodeEditPart);
        // process the child as new current edit part
        buildLayoutGraphRecursively(mapping, nodeEditPart, childLayoutNode, nodeEditPart);
        
        // create node label
        if (nodeEditPart instanceof ITextualContentEditPart) {
            ILabelDelegate delegate = ((ITextualContentEditPart) nodeEditPart).getContentLabel();
            if (delegate != null) {
                KLabel label = KimlUtil.createInitializedLabel(childLayoutNode);
                label.setText(delegate.getText());
                KShapeLayout labelLayout = label.getData(KShapeLayout.class);
                Rectangle textBounds = delegate.getTextBounds();
                Rectangle nodeBounds = new Rectangle(nodeFigure.getBounds());
                nodeFigure.translateToAbsolute(nodeBounds);
                textBounds.translate(-nodeBounds.x, -nodeBounds.y);
                textBounds.scale(1 / rootEditPart.getZoomManager().getZoom());
                labelLayout.setPos(textBounds.x, textBounds.y);
                labelLayout.setSize(textBounds.width(), textBounds.height());
            }
        }
        
        // store all the connections to process them later
        addConnections(mapping, nodeEditPart);
    }
    
    /**
     * Create a port while building the layout graph.
     * 
     * @param mapping
     *            the layout mapping
     * @param portEditPart
     *            the port edit part
     * @param nodeEditPart
     *            the parent node edit part
     * @param knode
     *            the corresponding layout node
     * @param layoutConfig
     *            a layout configuration
     */
    private void createPort(final LayoutMapping<IGraphicalEditPart> mapping,
            final ShapeNodeEditPart portEditPart, final IGraphicalEditPart nodeEditPart,
            final KNode knode) {
        IFigure portFigure = portEditPart.getFigure();
        KPort layoutPort = KimlUtil.createInitializedPort();
        layoutPort.setNode(knode);

        // set the port's layout, relative to the node position
        KShapeLayout portLayout = layoutPort.getData(KShapeLayout.class);
        Rectangle portBounds = new PrecisionRectangle(portFigure.getBounds());
        Rectangle nodeBounds = new PrecisionRectangle(nodeEditPart.getFigure().getBounds());
        IMapMode mapMode = ((DiagramRootEditPart) nodeEditPart.getRoot()).getMapMode();
        mapMode.LPtoDP(portBounds);
        mapMode.LPtoDP(nodeBounds);
        portLayout.setPos((float) (portBounds.preciseX() - nodeBounds.preciseX()),
                (float) (portBounds.preciseY() - nodeBounds.preciseY()));
        portLayout.setSize((float) portBounds.preciseWidth(), (float) portBounds.preciseHeight());
        // the modification flag must initially be false
        ((KShapeLayoutImpl) portLayout).resetModificationFlag();

        mapping.getGraphMap().put(layoutPort, portEditPart);
        
        // determine the port side from the figure constraint
        if (nodeEditPart instanceof IBorderedShapeEditPart) {
            IFigure nodeFigure = ((IBorderedShapeEditPart) nodeEditPart).getMainFigure();
            LayoutManager layout = nodeFigure.getLayoutManager();
            if (layout instanceof StandardComponentLayout) {
                Object layoutData = (StandardComponentLayoutData) layout.getConstraint(portFigure);
                if (layoutData instanceof StandardComponentLayoutData) {
                    int sideValue = ((StandardComponentLayoutData) layoutData).sideHint;
                    if (nodeFigure instanceof ComponentFigure) {
                        ComponentFigure componentFigure = (ComponentFigure) nodeFigure;
                        sideValue = transformSide(sideValue, componentFigure.isFlipped(),
                                componentFigure.getRotation());
                    }
                    PortSide portSide = PortSide.UNDEFINED;
                    switch (sideValue) {
                    case PositionConstants.NORTH:
                        portSide = PortSide.NORTH;
                        break;
                    case PositionConstants.EAST:
                        portSide = PortSide.EAST;
                        break;
                    case PositionConstants.SOUTH:
                        portSide = PortSide.SOUTH;
                        break;
                    case PositionConstants.WEST:
                        portSide = PortSide.WEST;
                        break;
                    }
                    if (portSide != PortSide.UNDEFINED) {
                        VolatileLayoutConfig staticConfig = mapping.getProperty(STATIC_CONFIG);
                        staticConfig.setValue(LayoutOptions.PORT_SIDE, layoutPort,
                                LayoutContext.GRAPH_ELEM, portSide);
                    }
                }
            }
        }

        // store all the connections to process them later
        addConnections(mapping, portEditPart);
    }

    /**
     * Transform a port side according to flipping and rotation.
     * Code copied from {@code org.eclipselabs.damos.diagram.ui.internal.geometry.Geometry}.
     * 
     * @param side the default side
     * @param flipped whether the figure is flipped
     * @param rotation whether the figure is rotated
     * @return the transformed side
     */
    public static int transformSide(final int side, final boolean flipped, final int rotation) {
        int s = side;
        if (flipped) {
            switch (side) {
            case PositionConstants.EAST:
                s = PositionConstants.WEST;
                break;
            case PositionConstants.WEST:
                s = PositionConstants.EAST;
                break;
            }
        }
        switch (rotation) {
        case 90: // SUPPRESS CHECKSTYLE MagicNumber
            switch (s) {
            case PositionConstants.EAST:
                return PositionConstants.NORTH;
            case PositionConstants.WEST:
                return PositionConstants.SOUTH;
            case PositionConstants.NORTH:
                return PositionConstants.WEST;
            case PositionConstants.SOUTH:
                return PositionConstants.EAST;
            }
            break;
        case 180: // SUPPRESS CHECKSTYLE MagicNumber
            switch (s) {
            case PositionConstants.EAST:
                return PositionConstants.WEST;
            case PositionConstants.WEST:
                return PositionConstants.EAST;
            case PositionConstants.NORTH:
                return PositionConstants.SOUTH;
            case PositionConstants.SOUTH:
                return PositionConstants.NORTH;
            }
            break;
        case 270: // SUPPRESS CHECKSTYLE MagicNumber
            switch (s) {
            case PositionConstants.EAST:
                return PositionConstants.SOUTH;
            case PositionConstants.WEST:
                return PositionConstants.NORTH;
            case PositionConstants.NORTH:
                return PositionConstants.EAST;
            case PositionConstants.SOUTH:
                return PositionConstants.WEST;
            }
            break;
        }
        return s;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void setEdgeLayout(final KEdgeLayout edgeLayout, final ConnectionEditPart connection,
            final KVector offset) {
        Connection figure = connection.getConnectionFigure();
        IMapMode mapMode = ((DiagramRootEditPart) connection.getRoot()).getMapMode();
        PointList pointList = figure.getPoints();

        KPoint sourcePoint = edgeLayout.getSourcePoint();
        PrecisionPoint firstPoint = new PrecisionPoint(pointList.getFirstPoint());
        mapMode.LPtoDP(firstPoint);
        sourcePoint.setX((float) (firstPoint.preciseX() - offset.x));
        sourcePoint.setY((float) (firstPoint.preciseY() - offset.y));

        for (int i = 1; i < pointList.size() - 1; i++) {
            PrecisionPoint point = new PrecisionPoint(pointList.getPoint(i));
            mapMode.LPtoDP(point);
            KPoint kpoint = KLayoutDataFactory.eINSTANCE.createKPoint();
            kpoint.setX((float) (point.preciseX() - offset.x));
            kpoint.setY((float) (point.preciseY() - offset.y));
            edgeLayout.getBendPoints().add(kpoint);
        }
        KPoint targetPoint = edgeLayout.getTargetPoint();
        PrecisionPoint lastPoint = new PrecisionPoint(pointList.getLastPoint());
        mapMode.LPtoDP(lastPoint);
        targetPoint.setX((float) (lastPoint.preciseX() - offset.x));
        targetPoint.setY((float) (lastPoint.preciseY() - offset.y));
        
        // the modification flag must initially be false
        ((KEdgeLayoutImpl) edgeLayout).resetModificationFlag();
    }

}
