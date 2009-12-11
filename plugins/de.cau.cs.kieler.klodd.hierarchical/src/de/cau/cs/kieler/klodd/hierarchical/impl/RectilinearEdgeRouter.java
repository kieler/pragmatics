/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2008 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klodd.hierarchical.impl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KLayoutDataFactory;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.layout.options.LayoutDirection;
import de.cau.cs.kieler.kiml.layout.options.LayoutOptions;
import de.cau.cs.kieler.kiml.layout.options.PortConstraints;
import de.cau.cs.kieler.kiml.layout.options.PortSide;
import de.cau.cs.kieler.kiml.layout.util.KimlLayoutUtil;
import de.cau.cs.kieler.klodd.hierarchical.modules.IEdgeRouter;
import de.cau.cs.kieler.klodd.hierarchical.modules.ILayerwiseEdgePlacer;
import de.cau.cs.kieler.klodd.hierarchical.structures.ElementLoop;
import de.cau.cs.kieler.klodd.hierarchical.structures.Layer;
import de.cau.cs.kieler.klodd.hierarchical.structures.LayerConnection;
import de.cau.cs.kieler.klodd.hierarchical.structures.LayerElement;
import de.cau.cs.kieler.klodd.hierarchical.structures.LayeredGraph;
import de.cau.cs.kieler.klodd.hierarchical.structures.RoutingSlot;

/**
 * Edge routing algorithm that makes rectilinear edges.
 * 
 * @kieler.rating 2009-12-11 proposed yellow msp
 * @author <a href="mailto:msp@informatik.uni-kiel.de">Miro Sp&ouml;nemann</a>
 */
public class RectilinearEdgeRouter extends AbstractAlgorithm implements IEdgeRouter {

    /**
     * Enumeration for types of routing of connections to or from external ports
     * of a layered graph.
     */
    private enum ExternalRouting {
        NORMAL, FIRST, LAST, AROUND
    }

    /** layerwise edge placer used to route layer connections. */
    private ILayerwiseEdgePlacer layerwiseEdgePlacer;
    /** minimal distance between elements of the layered graph. */
    private float objSpacing;
    /** spacing to the border. */
    private float borderSpacing;
    /** lengthwise position of the currently processed layer. */
    private float layerPos;
    /** maximal crosswise layout position of routed edges to external ports. */
    private float maxCrosswisePos;
    /** maximal lengthwise layout position to be added to the total size. */
    private float maxLengthwiseAddPos;

    /**
     * Creates a rectilinear edge router using a layerwise edge placer.
     * 
     * @param thelayerwiseEdgePlacer layerwise edge placer used to route layer
     *            connections
     */
    public RectilinearEdgeRouter(final ILayerwiseEdgePlacer thelayerwiseEdgePlacer) {
        this.layerwiseEdgePlacer = thelayerwiseEdgePlacer;
    }

    /**
     * {@inheritDoc}
     */
    public void routeEdges(final LayeredGraph layeredGraph, final float theobjSpacing,
            final float theborderSpacing) {
        getMonitor().begin("Rectilinear edge routing", layeredGraph.getLayers().size() - 1);

        this.objSpacing = theobjSpacing;
        this.borderSpacing = theborderSpacing;
        this.layerPos = 0.0f;
        this.maxCrosswisePos = layeredGraph.getCrosswiseDim();
        this.maxLengthwiseAddPos = 0.0f;

        // add a left border if there are no input ports
        Layer firstLayer = layeredGraph.getLayers().get(0);
        if (firstLayer.getRank() != 0) {
            layerPos = theborderSpacing;
        }
        // layout elements of the first layer
        firstLayer.layoutElements(layerPos, theobjSpacing);

        // process all outgoing connections from each layer
        ListIterator<Layer> layerIter = layeredGraph.getLayers().listIterator();
        while (layerIter.hasNext()) {
            Layer layer = layerIter.next();
            if (layerIter.hasNext()) {
                // layout all outgoing connections of the current layer
                processOutgoing(layer);
            }
            // layout element loops of the current layer
            processLoops(layer);
        }

        // update dimension of the whole graph
        Layer lastLayer = layeredGraph.getLayers().get(layeredGraph.getLayers().size() - 1);
        layerPos = lastLayer.getLengthwisePos() + lastLayer.getLengthwiseDim() + theborderSpacing;
        if (layeredGraph.getLayoutDirection() == LayoutDirection.DOWN) {
            layeredGraph.setCrosswiseDim(maxCrosswisePos + layeredGraph.getPosition().getX());
            layeredGraph.setLengthwiseDim(Math.max(layeredGraph.getLengthwiseDim(), layerPos
                    + maxLengthwiseAddPos + layeredGraph.getPosition().getY()));
        } else {
            layeredGraph.setCrosswiseDim(maxCrosswisePos + layeredGraph.getPosition().getY());
            layeredGraph.setLengthwiseDim(Math.max(layeredGraph.getLengthwiseDim(), layerPos
                    + maxLengthwiseAddPos + layeredGraph.getPosition().getX()));
        }

        // process external ports
        processExternalPorts(layeredGraph);
        getMonitor().done();
    }

    /**
     * Processes connections from a given layer to the subsequent one.
     * 
     * @param layer layer to be processed
     */
    private void processOutgoing(final Layer layer) {
        LayoutDirection layoutDirection = layer.getLayeredGraph().getLayoutDirection();

        // determine external routing options
        Map<LayerConnection, ExternalRouting> routingMap
                = new HashMap<LayerConnection, ExternalRouting>();
        int firstExtEdges = 0, lastExtEdges = 0, aroundExtEdges = 0;
        for (LayerElement element : layer.getElements()) {
            for (LayerConnection connection : element.getOutgoingConnections()) {
                // determine source and target positions
                ExternalRouting externalRouting = ExternalRouting.NORMAL;
                if (layer.getRank() == 0) {
                    PortSide placement = LayoutOptions.getPortSide(KimlLayoutUtil
                            .getShapeLayout(connection.getSourcePort()));
                    if (layoutDirection == LayoutDirection.DOWN) {
                        if (placement == PortSide.WEST) {
                            externalRouting = ExternalRouting.FIRST;
                            firstExtEdges++;
                        } else if (placement == PortSide.EAST) {
                            externalRouting = ExternalRouting.LAST;
                            lastExtEdges++;
                        } else if (placement == PortSide.SOUTH) {
                            externalRouting = ExternalRouting.AROUND;
                            aroundExtEdges++;
                        }
                    } else {
                        if (placement == PortSide.NORTH) {
                            externalRouting = ExternalRouting.FIRST;
                            firstExtEdges++;
                        } else if (placement == PortSide.SOUTH) {
                            externalRouting = ExternalRouting.LAST;
                            lastExtEdges++;
                        } else if (placement == PortSide.EAST) {
                            externalRouting = ExternalRouting.AROUND;
                            aroundExtEdges++;
                        }
                    }
                } else if (layer.getHeight() == 1) {
                    PortSide placement = LayoutOptions.getPortSide(KimlLayoutUtil
                            .getShapeLayout(connection.getTargetPort()));
                    if (layoutDirection == LayoutDirection.DOWN) {
                        if (placement == PortSide.WEST) {
                            externalRouting = ExternalRouting.FIRST;
                            firstExtEdges++;
                        } else if (placement == PortSide.EAST) {
                            externalRouting = ExternalRouting.LAST;
                            lastExtEdges++;
                        } else if (placement == PortSide.NORTH) {
                            externalRouting = ExternalRouting.AROUND;
                            aroundExtEdges++;
                        }
                    } else {
                        if (placement == PortSide.NORTH) {
                            externalRouting = ExternalRouting.FIRST;
                            firstExtEdges++;
                        } else if (placement == PortSide.SOUTH) {
                            externalRouting = ExternalRouting.LAST;
                            lastExtEdges++;
                        } else if (placement == PortSide.WEST) {
                            externalRouting = ExternalRouting.AROUND;
                            aroundExtEdges++;
                        }
                    }
                }
                routingMap.put(connection, externalRouting);
            }
        }

        // determine placement for all layer connections
        layerwiseEdgePlacer.reset(getMonitor().subTask(1));
        int slotRanks = layerwiseEdgePlacer.placeEdges(layer, objSpacing);
        Map<Object, RoutingSlot> slotMap = layerwiseEdgePlacer.getSlotMap();

        // layout all movable elements of the next layer
        KPoint layeredGraphPos = layer.getLayeredGraph().getPosition();
        float connectionsPos = layer.getLengthwisePos() + layer.getLengthwiseDim();
        if (slotRanks == 0 && (layer.getRank() == 0 || layer.getHeight() == 1)) {
            connectionsPos += borderSpacing;
        } else {
            connectionsPos += objSpacing;
        }
        layerPos = connectionsPos + slotRanks * objSpacing;
        layer.getNext().layoutElements(layerPos, objSpacing);
        int firstExtIndex = 0, lastExtIndex = 0, aroundExtIndex = 0;

        // route connections from the current layer to the next one
        for (LayerElement element : layer.getElements()) {
            KPoint sourcePos = element.getPosition();
            KPoint sourceOffset = element.getPosOffset();
            float sourceXbase = sourcePos.getX() + sourceOffset.getX();
            float sourceYbase = sourcePos.getY() + sourceOffset.getY();
            for (LayerConnection connection : element.getOutgoingConnections()) {
                // add back routing at source if needed
                if (connection.getSourceBackPos() > 0) {
                    KPoint point1 = KLayoutDataFactory.eINSTANCE.createKPoint();
                    KPoint point2 = KLayoutDataFactory.eINSTANCE.createKPoint();
                    KShapeLayout portLayout = KimlLayoutUtil.getShapeLayout(connection.getSourcePort());
                    if (layoutDirection == LayoutDirection.DOWN) {
                        point1.setX(sourceXbase + portLayout.getXpos() + portLayout.getWidth() / 2);
                        point1.setY(sourcePos.getY() - connection.getSourceBackPos() * objSpacing);
                        point2.setX(connection.getSourceAnchorPos());
                        point2.setY(point1.getY());
                    } else {
                        point1.setY(sourceYbase + portLayout.getYpos() + portLayout.getHeight() / 2);
                        point1.setX(sourcePos.getX() - connection.getSourceBackPos() * objSpacing);
                        point2.setY(connection.getSourceAnchorPos());
                        point2.setX(point1.getX());
                    }
                    connection.getBendPoints().add(point1);
                    connection.getBendPoints().add(point2);

                // add side routing at source if needed
                } else if (connection.getSourceSidePos() != 0) {
                    KPoint point = KLayoutDataFactory.eINSTANCE.createKPoint();
                    KShapeLayout portLayout = KimlLayoutUtil.getShapeLayout(connection.getSourcePort());
                    if (layoutDirection == LayoutDirection.DOWN) {
                        point.setX(connection.getSourceAnchorPos());
                        point.setY(sourceYbase + portLayout.getYpos() + portLayout.getHeight() / 2);
                    } else {
                        point.setY(connection.getSourceAnchorPos());
                        point.setX(sourceXbase + portLayout.getXpos() + portLayout.getWidth() / 2);
                    }
                    connection.getBendPoints().add(point);
                }

                Object key = connection.getSourcePort();
                if (key == null) {
                    key = element;
                }
                RoutingSlot slot = slotMap.get(key);
                if (slot == null) {
                    key = connection.getTargetPort();
                    if (key == null) {
                        key = connection.getTargetElement();
                    }
                    slot = slotMap.get(key);
                }
                ExternalRouting externalRouting = routingMap.get(connection);
                if (externalRouting == ExternalRouting.FIRST
                        || externalRouting == ExternalRouting.LAST) {
                    // perform routing to external port beside the layered graph
                    KPoint point1 = KLayoutDataFactory.eINSTANCE.createKPoint();
                    KPoint point2 = KLayoutDataFactory.eINSTANCE.createKPoint();
                    KPoint point3 = KLayoutDataFactory.eINSTANCE.createKPoint();
                    if (layoutDirection == LayoutDirection.DOWN) {
                        if (externalRouting == ExternalRouting.FIRST) {
                            point1.setX(-(layeredGraphPos.getX() + firstExtIndex * objSpacing));
                            firstExtIndex++;
                        } else {
                            point1.setX(maxCrosswisePos + (lastExtEdges - lastExtIndex - 1)
                                    * objSpacing);
                            lastExtIndex++;
                        }
                        point2.setX(point1.getX());
                        point2.setY(connectionsPos + slot.getRank() * objSpacing);
                        point3.setX(layer.getRank() == 0 ? connection.getTargetAnchorPos()
                                : connection.getSourceAnchorPos());
                        point3.setY(point2.getY());
                    } else {
                        if (externalRouting == ExternalRouting.FIRST) {
                            point1.setY(-(layeredGraphPos.getY() + firstExtIndex * objSpacing));
                            firstExtIndex++;
                        } else {
                            point1.setY(maxCrosswisePos + (lastExtEdges - lastExtIndex - 1)
                                    * objSpacing);
                            lastExtIndex++;
                        }
                        point2.setY(point1.getY());
                        point2.setX(connectionsPos + slot.getRank() * objSpacing);
                        point3.setY(layer.getRank() == 0 ? connection.getTargetAnchorPos()
                                : connection.getSourceAnchorPos());
                        point3.setX(point2.getX());
                    }
                    if (layer.getRank() == 0) {
                        connection.getBendPoints().add(point1);
                        connection.getBendPoints().add(point2);
                        connection.getBendPoints().add(point3);
                    } else if (layer.getHeight() == 1) {
                        connection.getBendPoints().add(point3);
                        connection.getBendPoints().add(point2);
                        connection.getBendPoints().add(point1);
                    }
                } else if (externalRouting == ExternalRouting.AROUND) {
                    // perform routing to external port on the opposite side
                    KPoint point1 = KLayoutDataFactory.eINSTANCE.createKPoint();
                    KPoint point2 = KLayoutDataFactory.eINSTANCE.createKPoint();
                    KPoint point3 = KLayoutDataFactory.eINSTANCE.createKPoint();
                    KPoint point4 = KLayoutDataFactory.eINSTANCE.createKPoint();
                    if (layoutDirection == LayoutDirection.DOWN) {
                        point1.setY(-aroundExtIndex * objSpacing);
                        point2.setY(point1.getY());
                        if (layer.getRank() == 0) {
                            point4.setX(connection.getTargetAnchorPos());
                        } else {
                            point4.setX(connection.getSourceAnchorPos());
                        }
                        point2.setX(maxCrosswisePos
                                + (lastExtEdges + aroundExtEdges - aroundExtIndex - 1) * objSpacing);
                        point3.setX(point2.getX());
                        point3.setY(connectionsPos + slot.getRank() * objSpacing);
                        point4.setY(point3.getY());
                    } else {
                        point1.setX(-aroundExtIndex * objSpacing);
                        point2.setX(point1.getX());
                        if (layer.getRank() == 0) {
                            point4.setY(connection.getTargetAnchorPos());
                        } else {
                            point4.setY(connection.getSourceAnchorPos());
                        }
                        point2.setY(maxCrosswisePos
                                + (lastExtEdges + aroundExtEdges - aroundExtIndex - 1) * objSpacing);
                        point3.setY(point2.getY());
                        point3.setX(connectionsPos + slot.getRank() * objSpacing);
                        point4.setX(point3.getX());
                    }
                    aroundExtIndex++;
                    if (layer.getRank() == 0) {
                        connection.getBendPoints().add(point1);
                        connection.getBendPoints().add(point2);
                        connection.getBendPoints().add(point3);
                        connection.getBendPoints().add(point4);
                    } else if (layer.getHeight() == 1) {
                        connection.getBendPoints().add(point4);
                        connection.getBendPoints().add(point3);
                        connection.getBendPoints().add(point2);
                        connection.getBendPoints().add(point1);
                    }

                } else if (connection.getSourceAnchorPos() != connection.getTargetAnchorPos()) {
                    // perform normal routing between anchor points
                    KPoint point1 = KLayoutDataFactory.eINSTANCE.createKPoint();
                    KPoint point2 = KLayoutDataFactory.eINSTANCE.createKPoint();
                    if (layoutDirection == LayoutDirection.DOWN) {
                        point1.setX(connection.getSourceAnchorPos());
                        point1.setY(connectionsPos + slot.getRank() * objSpacing);
                        point2.setX(connection.getTargetAnchorPos());
                        point2.setY(point1.getY());
                    } else {
                        point1.setY(connection.getSourceAnchorPos());
                        point1.setX(connectionsPos + slot.getRank() * objSpacing);
                        point2.setY(connection.getTargetAnchorPos());
                        point2.setX(point1.getX());
                    }
                    connection.getBendPoints().add(point1);
                    connection.getBendPoints().add(point2);
                }

                // add front routing at target if needed
                KPoint targetPos = connection.getTargetElement().getPosition();
                KPoint targetOffset = connection.getTargetElement().getPosOffset();
                float targetXbase = targetPos.getX() + targetOffset.getX();
                float targetYbase = targetPos.getY() + targetOffset.getY();
                if (connection.getTargetFrontPos() > 0) {
                    KPoint point1 = KLayoutDataFactory.eINSTANCE.createKPoint();
                    KPoint point2 = KLayoutDataFactory.eINSTANCE.createKPoint();
                    KShapeLayout portLayout = KimlLayoutUtil.getShapeLayout(connection.getTargetPort());
                    if (layoutDirection == LayoutDirection.DOWN) {
                        point1.setX(connection.getTargetAnchorPos());
                        point1.setY(targetPos.getY() + connection.getTargetElement().getRealHeight()
                                + connection.getTargetFrontPos() * objSpacing);
                        point2.setX(targetXbase + portLayout.getXpos() + portLayout.getWidth() / 2);
                        point2.setY(point1.getY());
                    } else {
                        point1.setY(connection.getTargetAnchorPos());
                        point1.setX(targetPos.getX() + connection.getTargetElement().getRealWidth()
                                + connection.getTargetFrontPos() * objSpacing);
                        point2.setY(targetYbase + portLayout.getYpos() + portLayout.getHeight() / 2);
                        point2.setX(point1.getX());
                    }
                    connection.getBendPoints().add(point1);
                    connection.getBendPoints().add(point2);

                // add side routing at target if needed
                } else if (connection.getTargetSidePos() != 0) {
                    KPoint point = KLayoutDataFactory.eINSTANCE.createKPoint();
                    KShapeLayout portLayout = KimlLayoutUtil.getShapeLayout(connection.getTargetPort());
                    if (layoutDirection == LayoutDirection.DOWN) {
                        point.setX(connection.getTargetAnchorPos());
                        point.setY(targetYbase + portLayout.getYpos() + portLayout.getHeight() / 2);
                    } else {
                        point.setY(connection.getTargetAnchorPos());
                        point.setX(targetXbase + portLayout.getXpos() + portLayout.getWidth() / 2);
                    }
                    connection.getBendPoints().add(point);
                }
            }
        }

        // update dimension of the layered graph
        if (layoutDirection == LayoutDirection.DOWN) {
            layeredGraphPos.setX(layeredGraphPos.getX() + firstExtEdges * objSpacing);
            if (layer.getHeight() == 0) {
                layeredGraphPos.setY(layeredGraphPos.getY() + aroundExtEdges * objSpacing);
            }
        } else {
            layeredGraphPos.setY(layeredGraphPos.getY() + firstExtEdges * objSpacing);
            if (layer.getHeight() == 0) {
                layeredGraphPos.setX(layeredGraphPos.getX() + aroundExtEdges * objSpacing);
            }
        }
        maxCrosswisePos += (lastExtEdges + aroundExtEdges) * objSpacing;
        if (layer.getRank() == 0) {
            maxLengthwiseAddPos = aroundExtEdges * objSpacing;
        }
    }

    /**
     * Routes element loops in a given layer.
     * 
     * @param layer layer to process
     */
    private void processLoops(final Layer layer) {
        for (LayerElement element : layer.getElements()) {
            for (ElementLoop loop : element.getLoops()) {
                KPoint point1 = createPointFor(loop.getSourcePort(), element, loop);
                KPoint point4 = createPointFor(loop.getTargetPort(), element, loop);
                loop.getBendPoints().add(point1);

                PortSide placement1 = LayoutOptions.getPortSide(KimlLayoutUtil.getShapeLayout(loop
                        .getSourcePort()));
                PortSide placement2 = LayoutOptions.getPortSide(KimlLayoutUtil.getShapeLayout(loop
                        .getTargetPort()));
                if (placement1 != placement2) {
                    KPoint point2 = KLayoutDataFactory.eINSTANCE.createKPoint();
                    KPoint point3 = KLayoutDataFactory.eINSTANCE.createKPoint();
                    if (placement1 == PortSide.NORTH && placement2 == PortSide.SOUTH
                            || placement1 == PortSide.SOUTH && placement2 == PortSide.NORTH) {
                        point2.setX(element.getPosition().getX() + element.getRealWidth()
                                + loop.getRoutePos(PortSide.EAST) * objSpacing);
                        point2.setY(point1.getY());
                        point3.setX(point2.getX());
                        point3.setY(point4.getY());
                        loop.getBendPoints().add(point2);
                    } else if (placement1 == PortSide.EAST && placement2 == PortSide.WEST
                            || placement1 == PortSide.WEST && placement2 == PortSide.EAST) {
                        point2.setX(point1.getX());
                        point2.setY(element.getPosition().getY() + element.getRealHeight()
                                + loop.getRoutePos(PortSide.SOUTH) * objSpacing);
                        point3.setX(point4.getX());
                        point3.setY(point2.getY());
                        loop.getBendPoints().add(point2);
                    } else if (placement1 == PortSide.NORTH || placement1 == PortSide.SOUTH) {
                        point3.setX(point4.getX());
                        point3.setY(point1.getY());
                    } else {
                        point3.setX(point1.getX());
                        point3.setY(point4.getY());
                    }
                    loop.getBendPoints().add(point3);
                }
                loop.getBendPoints().add(point4);
            }
        }
    }

    /**
     * Process the external ports of a given layered graph by assigning missing
     * coordinates.
     * 
     * @param layeredGraph layered graph to process
     */
    private void processExternalPorts(final LayeredGraph layeredGraph) {
        LayoutDirection layoutDirection = layeredGraph.getLayoutDirection();
        Layer inputLayer = layeredGraph.getLayers().get(0);
        Layer outputLayer = layeredGraph.getLayers().get(layeredGraph.getLayers().size() - 1);

        // determine maximal crosswise positions of input and output ports
        float maxInputPos = 0.0f;
        float maxOutputPos = 0.0f;
        if (inputLayer.getRank() == 0) {
            if (layoutDirection == LayoutDirection.DOWN) {
                for (LayerElement element : inputLayer.getElements()) {
                    KPort port = (KPort) element.getElemObj();
                    if (LayoutOptions.getPortSide(KimlLayoutUtil.getShapeLayout(port))
                            == PortSide.NORTH) {
                        maxInputPos = Math.max(maxInputPos, element.getPosition().getX());
                    }
                }
            } else {
                for (LayerElement element : inputLayer.getElements()) {
                    KPort port = (KPort) element.getElemObj();
                    if (LayoutOptions.getPortSide(KimlLayoutUtil.getShapeLayout(port))
                            == PortSide.WEST) {
                        maxInputPos = Math.max(maxInputPos, element.getPosition().getY());
                    }
                }
            }
        }
        if (outputLayer.getHeight() == 0) {
            if (layoutDirection == LayoutDirection.DOWN) {
                for (LayerElement element : outputLayer.getElements()) {
                    KPort port = (KPort) element.getElemObj();
                    if (LayoutOptions.getPortSide(KimlLayoutUtil.getShapeLayout(port))
                            == PortSide.SOUTH) {
                        maxOutputPos = Math.max(maxOutputPos, element.getPosition().getX());
                    }
                }
            } else {
                for (LayerElement element : outputLayer.getElements()) {
                    KPort port = (KPort) element.getElemObj();
                    if (LayoutOptions.getPortSide(KimlLayoutUtil.getShapeLayout(port))
                            == PortSide.EAST) {
                        maxOutputPos = Math.max(maxOutputPos, element.getPosition().getY());
                    }
                }
            }
        }

        // process input and output ports
        if (inputLayer.getRank() == 0) {
            processExternalLayer(layeredGraph, inputLayer, maxInputPos, maxOutputPos);
        }
        if (outputLayer.getHeight() == 0) {
            processExternalLayer(layeredGraph, outputLayer, maxInputPos, maxOutputPos);
        }
    }

    /**
     * Process a layer with external ports.
     * 
     * @param layeredGraph the layered graph
     * @param layer external layer
     * @param maxInputPos maximal position of an input port on its home side
     * @param maxOutputPos maximal position of an output port on its home side
     */
    private void processExternalLayer(final LayeredGraph layeredGraph,
            final Layer layer, final float maxInputPos, final float maxOutputPos) {
        LayoutDirection layoutDirection = layeredGraph.getLayoutDirection();
        float offsetX = -layeredGraph.getPosition().getX();
        float offsetY = -layeredGraph.getPosition().getY();

        // choose a placement for non-fixed ports
        if (layeredGraph.getExternalPortConstraints() != PortConstraints.FIXED_POS) {
            List<KPoint> northPorts = new LinkedList<KPoint>();
            List<KPoint> eastPorts = new LinkedList<KPoint>();
            List<KPoint> southPorts = new LinkedList<KPoint>();
            List<KPoint> westPorts = new LinkedList<KPoint>();
            for (LayerElement element : layer.getElements()) {
                KPort port = (KPort) element.getElemObj();
                switch (LayoutOptions.getPortSide(KimlLayoutUtil.getShapeLayout(port))) {
                case NORTH:
                    northPorts.add(element.getPosition());
                    break;
                case EAST:
                    eastPorts.add(element.getPosition());
                    break;
                case SOUTH:
                    southPorts.add(element.getPosition());
                    break;
                case WEST:
                    westPorts.add(element.getPosition());
                    break;
                }
            }
            if (layoutDirection == LayoutDirection.DOWN) {
                if (layer.getRank() == 0) {
                    KimlLayoutUtil.placePoints(westPorts, 0.0f, layeredGraph.getLengthwiseDim() / 2,
                            offsetY, true, true);
                    KimlLayoutUtil.placePoints(eastPorts, 0.0f, layeredGraph.getLengthwiseDim() / 2,
                            offsetY, true, false);
                    KimlLayoutUtil.placePoints(southPorts, maxOutputPos, layeredGraph.getCrosswiseDim()
                            + offsetX, 0.0f, false, true);
                } else {
                    KimlLayoutUtil.placePoints(westPorts, layeredGraph.getLengthwiseDim() / 2,
                            layeredGraph.getLengthwiseDim(), offsetY, true, true);
                    KimlLayoutUtil.placePoints(eastPorts, layeredGraph.getLengthwiseDim() / 2,
                            layeredGraph.getLengthwiseDim(), offsetY, true, false);
                    KimlLayoutUtil.placePoints(northPorts, maxInputPos, layeredGraph.getCrosswiseDim()
                            + offsetX, 0.0f, false, true);
                }
            } else {
                if (layer.getRank() == 0) {
                    KimlLayoutUtil.placePoints(northPorts, 0.0f, layeredGraph.getLengthwiseDim() / 2,
                            offsetX, false, false);
                    KimlLayoutUtil.placePoints(southPorts, 0.0f, layeredGraph.getLengthwiseDim() / 2,
                            offsetX, false, true);
                    KimlLayoutUtil.placePoints(eastPorts, maxOutputPos, layeredGraph.getCrosswiseDim()
                            + offsetY, 0.0f, true, false);
                } else {
                    KimlLayoutUtil.placePoints(northPorts, layeredGraph.getLengthwiseDim() / 2,
                            layeredGraph.getLengthwiseDim(), offsetX, false, true);
                    KimlLayoutUtil.placePoints(southPorts, layeredGraph.getLengthwiseDim() / 2,
                            layeredGraph.getLengthwiseDim(), offsetX, false, false);
                    KimlLayoutUtil.placePoints(westPorts, maxInputPos, layeredGraph.getCrosswiseDim()
                            + offsetY, 0.0f, true, false);
                }
            }
        }

        // adjust the position of each external port
        if (layoutDirection == LayoutDirection.DOWN) {
            for (LayerElement element : layer.getElements()) {
                KPort port = (KPort) element.getElemObj();
                KShapeLayout portLayout = KimlLayoutUtil.getShapeLayout(port);
                KPoint position = element.getPosition();
                switch (LayoutOptions.getPortSide(portLayout)) {
                case NORTH:
                    position.setY(offsetY - portLayout.getHeight());
                    if (layer.getHeight() == 0) {
                        float posX = position.getX() + portLayout.getWidth() / 2;
                        for (LayerConnection connection : element.getIncomingConnections()) {
                            connection.getBendPoints().get(connection.getBendPoints().size() - 1)
                                    .setX(posX);
                        }
                    }
                    break;
                case EAST:
                    position.setX(layeredGraph.getCrosswiseDim() + offsetX);
                    float posY = position.getY() + portLayout.getHeight() / 2;
                    if (layer.getRank() == 0) {
                        for (LayerConnection connection : element.getOutgoingConnections()) {
                            connection.getBendPoints().get(0).setY(posY);
                        }
                    } else {
                        for (LayerConnection connection : element.getIncomingConnections()) {
                            connection.getBendPoints().get(connection.getBendPoints().size() - 1)
                                    .setY(posY);
                        }
                    }
                    break;
                case SOUTH:
                    position.setY(layeredGraph.getLengthwiseDim() + offsetY);
                    if (layer.getRank() == 0) {
                        float posX = position.getX() + portLayout.getWidth() / 2;
                        for (LayerConnection connection : element.getOutgoingConnections()) {
                            KPoint point1 = connection.getBendPoints().get(0);
                            KPoint point2 = connection.getBendPoints().get(1);
                            point1.setX(posX);
                            point1.setY(position.getY() + point1.getY());
                            point2.setY(point1.getY());
                        }
                    }
                    break;
                case WEST:
                    position.setX(offsetX - portLayout.getWidth());
                    posY = position.getY() + portLayout.getHeight() / 2;
                    if (layer.getRank() == 0) {
                        for (LayerConnection connection : element.getOutgoingConnections()) {
                            connection.getBendPoints().get(0).setY(posY);
                        }
                    } else {
                        for (LayerConnection connection : element.getIncomingConnections()) {
                            connection.getBendPoints().get(connection.getBendPoints().size() - 1)
                                    .setY(posY);
                        }
                    }
                    break;
                }
            }
        } else {
            for (LayerElement element : layer.getElements()) {
                KPort port = (KPort) element.getElemObj();
                KPoint position = element.getPosition();
                KShapeLayout portLayout = KimlLayoutUtil.getShapeLayout(port);
                switch (LayoutOptions.getPortSide(portLayout)) {
                case NORTH:
                    position.setY(offsetY - portLayout.getHeight());
                    float posX = position.getX() + portLayout.getWidth() / 2;
                    if (layer.getRank() == 0) {
                        for (LayerConnection connection : element.getOutgoingConnections()) {
                            connection.getBendPoints().get(0).setX(posX);
                        }
                    } else {
                        for (LayerConnection connection : element.getIncomingConnections()) {
                            connection.getBendPoints().get(connection.getBendPoints().size() - 1)
                                    .setX(posX);
                        }
                    }
                    break;
                case EAST:
                    position.setX(layeredGraph.getLengthwiseDim() + offsetX);
                    if (layer.getRank() == 0) {
                        float posY = position.getY() + portLayout.getHeight() / 2;
                        for (LayerConnection connection : element.getOutgoingConnections()) {
                            KPoint point1 = connection.getBendPoints().get(0);
                            KPoint point2 = connection.getBendPoints().get(1);
                            point1.setY(posY);
                            point1.setX(position.getX() + point1.getX());
                            point2.setX(point1.getX());
                        }
                    }
                    break;
                case SOUTH:
                    position.setY(layeredGraph.getCrosswiseDim() + offsetY);
                    posX = position.getX() + portLayout.getWidth() / 2;
                    if (layer.getRank() == 0) {
                        for (LayerConnection connection : element.getOutgoingConnections()) {
                            connection.getBendPoints().get(0).setX(posX);
                        }
                    } else {
                        for (LayerConnection connection : element.getIncomingConnections()) {
                            connection.getBendPoints().get(connection.getBendPoints().size() - 1)
                                    .setX(posX);
                        }
                    }
                    break;
                case WEST:
                    position.setX(offsetX - portLayout.getWidth());
                    if (layer.getHeight() == 0) {
                        float posY = position.getY() + portLayout.getHeight() / 2;
                        for (LayerConnection connection : element.getIncomingConnections()) {
                            connection.getBendPoints().get(connection.getBendPoints().size() - 1)
                                    .setY(posY);
                        }
                    }
                    break;
                }
            }
        }
    }

    /**
     * Creates a point for routing of an element loop.
     * 
     * @param port port where a point shall be created
     * @param element the layer element
     * @param loop element loop to be routed
     * @return a point for edges starting or ending at the specified port
     */
    private KPoint createPointFor(final KPort port, final LayerElement element,
            final ElementLoop loop) {
        KPoint elemPos = element.getPosition();
        KPoint elemOffset = element.getPosOffset();
        float xbase = elemPos.getX() + elemOffset.getX();
        float ybase = elemPos.getY() + elemOffset.getY();
        KPoint point = KLayoutDataFactory.eINSTANCE.createKPoint();
        KShapeLayout portLayout = KimlLayoutUtil.getShapeLayout(port);
        switch (LayoutOptions.getPortSide(portLayout)) {
        case NORTH:
            point.setX(xbase + portLayout.getXpos() + portLayout.getWidth() / 2);
            point.setY(elemPos.getY() - loop.getRoutePos(PortSide.NORTH) * objSpacing);
            break;
        case EAST:
            point.setX(elemPos.getX() + element.getRealWidth()
                    + loop.getRoutePos(PortSide.EAST) * objSpacing);
            point.setY(ybase + portLayout.getYpos() + portLayout.getHeight() / 2);
            break;
        case SOUTH:
            point.setX(xbase + portLayout.getXpos() + portLayout.getWidth() / 2);
            point.setY(elemPos.getY() + element.getRealHeight()
                    + loop.getRoutePos(PortSide.SOUTH) * objSpacing);
            break;
        case WEST:
            point.setX(elemPos.getX() - loop.getRoutePos(PortSide.WEST) * objSpacing);
            point.setY(ybase + portLayout.getYpos() + portLayout.getHeight() / 2);
            break;
        }
        return point;
    }

}
