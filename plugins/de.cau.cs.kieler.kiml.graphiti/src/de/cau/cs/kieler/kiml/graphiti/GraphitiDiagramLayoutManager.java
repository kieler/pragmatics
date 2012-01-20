/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * Copyright 2010 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.graphiti;

import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.graphiti.datatypes.IDimension;
import org.eclipse.graphiti.mm.algorithms.AbstractText;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.styles.Font;
import org.eclipse.graphiti.mm.algorithms.styles.Point;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.BoxRelativeAnchor;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ConnectionDecorator;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.FixPointAnchor;
import org.eclipse.graphiti.mm.pictograms.FreeFormConnection;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.ui.editor.DiagramEditor;
import org.eclipse.graphiti.ui.internal.parts.IPictogramElementEditPart;
import org.eclipse.graphiti.ui.services.GraphitiUi;
import org.eclipse.swt.SWTException;
import org.eclipse.ui.IWorkbenchPart;

import com.google.common.collect.BiMap;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KLabeledGraphElement;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.core.math.KVectorChain;
import de.cau.cs.kieler.core.model.graphiti.GraphitiUtil;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.kiml.LayoutContext;
import de.cau.cs.kieler.kiml.config.IMutableLayoutConfig;
import de.cau.cs.kieler.kiml.config.VolatileLayoutConfig;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KInsets;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutDataFactory;
import de.cau.cs.kieler.kiml.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.EdgeLabelPlacement;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.ui.diagram.GefDiagramLayoutManager;
import de.cau.cs.kieler.kiml.ui.diagram.LayoutMapping;
import de.cau.cs.kieler.kiml.util.KimlUtil;

/**
 * Generic layout manager implementation for Graphiti diagrams.
 * 
 * @author atr
 * @author soh
 */
@SuppressWarnings("restriction")
public class GraphitiDiagramLayoutManager extends GefDiagramLayoutManager<PictogramElement> {

    /** diagram editor of the currently layouted diagram. */
    public static final IProperty<DiagramEditor> DIAGRAM_EDITOR = new Property<DiagramEditor>(
            "graphiti.diagramEditor");
    
    /** the command that is executed for applying automatic layout. */
    public static final IProperty<Command> LAYOUT_COMMAND = new Property<Command>(
            "graphiti.applyLayoutCommand");

    /** list of all connections in the diagram. */
    public static final IProperty<List<Connection>> CONNECTIONS = new Property<List<Connection>>(
            "graphiti.connections");
    
    /** the volatile layout config for static properties such as minimal node sizes. */
    public static final IProperty<VolatileLayoutConfig> STATIC_CONFIG
            = new Property<VolatileLayoutConfig>("graphiti.staticLayoutConfig");

    /**
     * {@inheritDoc}
     */
    public boolean supports(final Object object) {
        return object instanceof DiagramEditor || object instanceof IPictogramElementEditPart;
    }

    /**
     * {@inheritDoc}
     */
    public LayoutMapping<PictogramElement> buildLayoutGraph(final IWorkbenchPart workbenchPart,
            final Object diagramPart) {
        LayoutMapping<PictogramElement> mapping = new LayoutMapping<PictogramElement>();
        mapping.setProperty(CONNECTIONS, new LinkedList<Connection>());
        mapping.setProperty(STATIC_CONFIG, new VolatileLayoutConfig());

        if (workbenchPart instanceof DiagramEditor) {
            mapping.setProperty(DIAGRAM_EDITOR, (DiagramEditor) workbenchPart);
        }

        EditPart layoutRootPart = null;
        if (diagramPart instanceof IPictogramElementEditPart) {
            layoutRootPart = (EditPart) diagramPart;
        } else if (mapping.getProperty(DIAGRAM_EDITOR) != null) {
            layoutRootPart = mapping.getProperty(DIAGRAM_EDITOR).getGraphicalViewer().getContents();
        }
        if (!(layoutRootPart instanceof IPictogramElementEditPart)) {
            throw new UnsupportedOperationException(
                    "Not supported by this layout manager: Workbench part "
                    + workbenchPart + ", Edit part " + diagramPart);
        }
        PictogramElement element = ((IPictogramElementEditPart) layoutRootPart)
                .getPictogramElement();
        mapping.setParentElement(element);

        if (element instanceof Diagram) {
            KNode topNode = KimlUtil.createInitializedNode();
            KShapeLayout shapeLayout = topNode.getData(KShapeLayout.class);
            GraphicsAlgorithm ga = element.getGraphicsAlgorithm();
            shapeLayout.setPos(ga.getX(), ga.getY());
            shapeLayout.setSize(ga.getWidth(), ga.getHeight());
            mapping.getGraphMap().put(topNode, element);

            buildLayoutGraphRecursively(mapping, (Diagram) element, topNode);
            
            mapping.setLayoutGraph(topNode);
        } else if (element instanceof Shape) {
            mapping.setLayoutGraph(createNode(mapping, null, (Shape) element));
        }

        for (Connection entry : mapping.getProperty(CONNECTIONS)) {
            createEdge(mapping, entry);
        }
        
        // create a layout configuration
        mapping.getLayoutConfigs().add(mapping.getProperty(STATIC_CONFIG));
        mapping.getLayoutConfigs().add(getLayoutConfig());

        return mapping;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void transferLayout(final LayoutMapping<PictogramElement> mapping) {
        DiagramEditor diagramEditor = mapping.getProperty(DIAGRAM_EDITOR);
        GraphitiLayoutCommand command = new GraphitiLayoutCommand(diagramEditor.getEditingDomain(),
                diagramEditor.getDiagramTypeProvider().getFeatureProvider());
        for (Entry<KGraphElement, PictogramElement> entry : mapping.getGraphMap().entrySet()) {
            command.add(entry.getKey(), entry.getValue());
        }
        mapping.setProperty(LAYOUT_COMMAND, command);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void applyLayout(final LayoutMapping<PictogramElement> mapping) {
        TransactionalEditingDomain editingDomain = mapping.getProperty(DIAGRAM_EDITOR)
                .getEditingDomain();
        editingDomain.getCommandStack().execute(mapping.getProperty(LAYOUT_COMMAND));
    }
    
    /** the cached layout configuration for Graphiti. */
    private GraphitiLayoutConfig layoutConfig = new GraphitiLayoutConfig();

    /**
     * {@inheritDoc}
     */
    public IMutableLayoutConfig getLayoutConfig() {
        return layoutConfig;
    }

    /** the fixed minimal size of shapes. */
    private static final float MIN_SIZE = 15.0f;
    
    /**
     * Recursively builds a layout graph by analyzing the children of the given current pictogram
     * Element.
     * 
     * @param parentElement
     *            the currently analyzed element
     * @param parentNode
     *            the corresponding KNode
     * @param layoutConfig
     *            the layout configuration
     */
    private void buildLayoutGraphRecursively(final LayoutMapping<PictogramElement> mapping,
            final ContainerShape parentElement, final KNode parentNode) {
        for (Shape shape : parentElement.getChildren()) {
            // relevant shapes are those that can be connected
            if (!shape.getAnchors().isEmpty()) {
                createNode(mapping, parentNode, shape);
            }
        }
    }
    
    /**
     * Create a node for the layout graph.
     * 
     * @param parentNode the parent node
     * @param shape the shape for a new node
     * @param layoutConfig the layout configuration
     */
    private KNode createNode(final LayoutMapping<PictogramElement> mapping,
            final KNode parentNode, final Shape shape) {
        KNode childNode = KimlUtil.createInitializedNode();
        childNode.setParent(parentNode);
        VolatileLayoutConfig staticConfig = mapping.getProperty(STATIC_CONFIG);

        // set the node's layout
        KShapeLayout nodeLayout = childNode.getData(KShapeLayout.class);
        GraphicsAlgorithm nodeGa = shape.getGraphicsAlgorithm();
        KInsets nodeInsets = calcInsets(nodeGa);
        nodeLayout.setProperty(GraphitiLayoutCommand.INVIS_INSETS, nodeInsets);
        staticConfig.setValue(GraphitiLayoutCommand.INVIS_INSETS, childNode, LayoutContext.GRAPH_ELEM,
                nodeInsets);
        KInsets parentInsets = parentNode == null ? null : parentNode.getData(KShapeLayout.class)
                .getProperty(GraphitiLayoutCommand.INVIS_INSETS);
        if (parentInsets == null) {
            nodeLayout.setPos(nodeGa.getX() + nodeInsets.getLeft(),
                    nodeGa.getY() + nodeInsets.getTop());
        } else {
            nodeLayout.setPos(nodeGa.getX() + nodeInsets.getLeft() - parentInsets.getLeft(),
                    nodeGa.getY() + nodeInsets.getTop() - parentInsets.getTop());
        }
        nodeLayout.setSize(nodeGa.getWidth() - nodeInsets.getLeft() - nodeInsets.getRight(),
                nodeGa.getHeight() - nodeInsets.getTop() - nodeInsets.getBottom());

        // FIXME find a way to specify the minimal size dynamically
        staticConfig.setValue(LayoutOptions.MIN_WIDTH, childNode, LayoutContext.GRAPH_ELEM, MIN_SIZE);
        staticConfig.setValue(LayoutOptions.MIN_HEIGHT, childNode, LayoutContext.GRAPH_ELEM, MIN_SIZE);

        mapping.getGraphMap().put(childNode, shape);

        if (shape instanceof ContainerShape) {
            // find a label for the container shape
            for (Shape child : ((ContainerShape) shape).getChildren()) {
                GraphicsAlgorithm childGa = child.getGraphicsAlgorithm();
                if (childGa instanceof AbstractText) {
                    createLabel(childNode, (AbstractText) childGa,
                            -nodeInsets.getLeft(), -nodeInsets.getTop());
                }
            }
            
            // process the children of the container shape
            buildLayoutGraphRecursively(mapping, (ContainerShape) shape, childNode);
        }

        for (Anchor anchor : shape.getAnchors()) {
            // box-relative anchors and fixed-position anchors are interpreted as ports
            if (anchor.getGraphicsAlgorithm() != null) {
                if (anchor instanceof BoxRelativeAnchor) {
                    createPort(mapping, childNode, (BoxRelativeAnchor) anchor);
                } else if (anchor instanceof FixPointAnchor) {
                    createPort(mapping, childNode, (FixPointAnchor) anchor);
                }
            }
            // gather all connections in the diagram
            mapping.getProperty(CONNECTIONS).addAll(anchor.getOutgoingConnections());
        }
        
        return childNode;
    }

    /**
     * Create a port for the layout graph using a box-relative anchor. The referenced graphics
     * algorithm of the anchor is assumed to be the same as the one returned by
     * {@link GraphitiUtil#findVisibleGa(GraphicsAlgorithm)}.
     * 
     * @param parentNode the parent node
     * @param bra the anchor
     * @param layoutConfig the layout configuration
     */
    private KPort createPort(final LayoutMapping<PictogramElement> mapping,
            final KNode parentNode, final BoxRelativeAnchor bra) {
        KPort port = KimlUtil.createInitializedPort();
        port.setNode(parentNode);
        KShapeLayout portLayout = port.getData(KShapeLayout.class);

        GraphicsAlgorithm referencedGa = bra.getReferencedGraphicsAlgorithm();
        if (referencedGa == null) {
            return null;
        }
        mapping.getGraphMap().put(port, bra);

        double relWidth = bra.getRelativeWidth();
        double relHeight = bra.getRelativeHeight();

        double parentWidth = referencedGa.getWidth();
        double parentHeight = referencedGa.getHeight();
        float xPos = (float) (relWidth * parentWidth);
        float yPos = (float) (relHeight * parentHeight);
        
        GraphicsAlgorithm portGa = bra.getGraphicsAlgorithm(); 
        if (portGa != null) {
            xPos += portGa.getX();
            yPos += portGa.getY();
            portLayout.setSize(portGa.getWidth(), portGa.getHeight());
        }
        portLayout.setPos(xPos, yPos);
        
        return port;
    }

    /**
     * Create a port for the layout graph using a fixed-position anchor.
     * 
     * @param parentNode the parent node
     * @param fpa the anchor
     * @param layoutConfig the layout configuration
     */
    private KPort createPort(final LayoutMapping<PictogramElement> mapping,
            final KNode parentNode, final FixPointAnchor fpa) {
        KPort port = KimlUtil.createInitializedPort();
        port.setNode(parentNode);
        KShapeLayout portLayout = port.getData(KShapeLayout.class);
        mapping.getGraphMap().put(port, fpa);
        
        float xPos = fpa.getLocation().getX();
        float yPos = fpa.getLocation().getY();

        GraphicsAlgorithm portGa = fpa.getGraphicsAlgorithm(); 
        if (portGa != null) {
            xPos += portGa.getX();
            yPos += portGa.getY();
            portLayout.setSize(portGa.getWidth(), portGa.getHeight());
        }
        portLayout.setPos(xPos, yPos);
        
        return port;
    }
    
    /**
     * Set up a label for a node or a port.
     * 
     * @param element the graph element to which the label is added
     * @param abstractText the text graphics algorithm to set up the label
     * @param offsetx the x coordinate offset
     * @param offsety the y coordinate offset
     * @return the new label
     */
    private KLabel createLabel(final KLabeledGraphElement element, final AbstractText abstractText,
            final float offsetx, final float offsety) {
        String labelText = abstractText.getValue();
        if (labelText != null) {
            KLabel label = KimlUtil.createInitializedLabel(element);
            label.setText(labelText);
            IGaService gaService = Graphiti.getGaService();
            Font font = gaService.getFont(abstractText, true);
            
            IDimension textSize = null;
            try {
                textSize = GraphitiUi.getUiLayoutService().calculateTextSize(labelText, font);
            } catch (SWTException exception) {
                // ignore exception
            }
            int xpos = abstractText.getX(), ypos = abstractText.getY();
            int width = abstractText.getWidth(), height = abstractText.getHeight();
            if (textSize != null) {
                if (textSize.getWidth() < width) {
                    int diff = width - textSize.getWidth();
                    switch (gaService.getHorizontalAlignment(abstractText, true)) {
                    case ALIGNMENT_CENTER:
                        xpos += diff / 2;
                        break;
                    case ALIGNMENT_RIGHT:
                        xpos += diff;
                        break;
                    }
                    width -= diff;
                }
                if (textSize.getHeight() < height) {
                    int diff = height - textSize.getHeight();
                    switch (gaService.getVerticalAlignment(abstractText, true)) {
                    case ALIGNMENT_MIDDLE:
                        ypos += diff / 2;
                        break;
                    case ALIGNMENT_BOTTOM:
                        ypos += diff;
                        break;
                    }
                    height -= diff;
                }
            }
            
            KShapeLayout labelLayout = label.getData(KShapeLayout.class);
            labelLayout.setPos(xpos + offsetx, ypos + offsety);
            labelLayout.setSize(width, height);
            return label;
        }
        return null;
    }

    /** minimal value for the relative location of head labels. */
    private static final double HEAD_LOCATION = 0.7;
    /** maximal value for the relative location of tail labels. */
    private static final double TAIL_LOCATION = 0.3;

    /**
     * Create an edge for the layout graph.
     * 
     * @param connection a connection
     * @param layoutConfig the layout configuration
     */
    private void createEdge(final LayoutMapping<PictogramElement> mapping,
            final Connection connection) {
        KEdge edge = KimlUtil.createInitializedEdge();
        BiMap<KGraphElement, PictogramElement> graphMap = mapping.getGraphMap();
        VolatileLayoutConfig staticConfig = mapping.getProperty(STATIC_CONFIG);

        // set target node and port
        KNode targetNode;
        Anchor targetAnchor = connection.getEnd();
        KPort targetPort = (KPort) graphMap.inverse().get(targetAnchor);
        if (targetPort != null) {
            edge.setTargetPort(targetPort);
            targetPort.getEdges().add(edge);
            targetNode = targetPort.getNode();
        } else {
            targetNode = (KNode) graphMap.inverse().get(targetAnchor.getParent());
        }
        edge.setTarget(targetNode);

        // set source node and port
        KNode sourceNode;
        Anchor sourceAnchor = connection.getStart();
        KPort sourcePort = (KPort) graphMap.inverse().get(sourceAnchor);
        if (sourcePort != null) {
            edge.setSourcePort(sourcePort);
            sourcePort.getEdges().add(edge);
            sourceNode = sourcePort.getNode();
        } else {
            sourceNode = (KNode) graphMap.inverse().get(sourceAnchor.getParent());
        }
        edge.setSource(sourceNode);
        
        if (sourceNode == null || targetNode == null) {
            return;
        }

        // calculate offset for bend points and labels
        KNode referenceNode = sourceNode;
        if (!KimlUtil.isDescendant(targetNode, sourceNode)) {
            referenceNode = sourceNode.getParent();
        }
        KVector offset = new KVector();
        KimlUtil.toAbsolute(offset, referenceNode);
        
        // set source and target point
        KEdgeLayout edgeLayout = edge.getData(KEdgeLayout.class);
        KVector sourcePoint = calculateAnchorEnds(sourceNode, sourcePort, referenceNode);
        edgeLayout.getSourcePoint().applyVector(sourcePoint);
        KVector targetPoint = calculateAnchorEnds(targetNode, targetPort, referenceNode);
        edgeLayout.getTargetPoint().applyVector(targetPoint);
        // set bend points for the new edge
        KVectorChain allPoints = new KVectorChain();
        allPoints.add(sourcePoint);
        if (connection instanceof FreeFormConnection) {
            for (Point point : ((FreeFormConnection) connection).getBendpoints()) {
                KVector v = new KVector(point.getX(), point.getY());
                v.sub(offset);
                allPoints.add(v);
                KPoint kpoint = KLayoutDataFactory.eINSTANCE.createKPoint();
                kpoint.applyVector(v);
                edgeLayout.getBendPoints().add(kpoint);
            }
        }
        allPoints.add(targetPoint);

        graphMap.put(edge, connection);

        // find labels for the connection
        for (ConnectionDecorator decorator : connection.getConnectionDecorators()) {
            GraphicsAlgorithm ga = decorator.getGraphicsAlgorithm();
            if (ga instanceof AbstractText) {
                AbstractText text = (AbstractText) ga;
                String labelText = text.getValue();
                KLabel label = KimlUtil.createInitializedLabel(edge);
                label.setText(labelText);
                graphMap.put(label, decorator);

                // set label placement
                KShapeLayout labelLayout = label.getData(KShapeLayout.class);
                EdgeLabelPlacement placement = EdgeLabelPlacement.CENTER;
                if (decorator.isLocationRelative()) {
                    if (decorator.getLocation() >= HEAD_LOCATION) {
                        placement = EdgeLabelPlacement.HEAD;
                    } else if (decorator.getLocation() <= TAIL_LOCATION) {
                        placement = EdgeLabelPlacement.TAIL;
                    }
                }
                staticConfig.setValue(LayoutOptions.EDGE_LABEL_PLACEMENT, label,
                        LayoutContext.GRAPH_ELEM, placement);
                
                // set label position
                KVector labelPos;
                if (decorator.isLocationRelative()) {
                    labelPos = allPoints.getPointOnLine(decorator.getLocation()
                                    * allPoints.getLength());
                } else {
                    labelPos = allPoints.getPointOnLine(decorator.getLocation());
                }
                labelPos.x += ga.getX();
                labelPos.y += ga.getY();
                labelLayout.applyVector(labelPos);
            }
        }
    }

    /**
     * Returns an end point for an anchor.
     * 
     * @param node
     *            the node that owns the anchor
     * @param port
     *            the port that represents the anchor
     */
    private KVector calculateAnchorEnds(final KNode node, final KPort port, final KNode referenceNode) {
        KVector pos = new KVector();
        if (port != null) {
            // the anchor end is represented by a port (box-relative anchor or fix-point anchor)
            KShapeLayout portLayout = port.getData(KShapeLayout.class);
            pos.x = portLayout.getXpos() + portLayout.getWidth() / 2;
            pos.y = portLayout.getYpos() + portLayout.getHeight() / 2;
            KShapeLayout nodeLayout = node.getData(KShapeLayout.class);
            pos.x += nodeLayout.getXpos();
            pos.y += nodeLayout.getYpos();
        } else {
            // the anchor end is calculated by a chopbox anchor
            KShapeLayout nodeLayout = node.getData(KShapeLayout.class);
            pos.x = nodeLayout.getWidth() / 2 + nodeLayout.getXpos();
            pos.y = nodeLayout.getHeight() / 2 + nodeLayout.getYpos();
        }
        KimlUtil.toAbsolute(pos, node.getParent());
        KimlUtil.toRelative(pos, referenceNode);
        return pos;
    }
    


    /**
     * Calculate insets from an invisible rectangle to the first visible shape.
     * 
     * @param graphicsAlgorithm the parent graphics algorithm
     * @return the insets
     */
    public static KInsets calcInsets(final GraphicsAlgorithm graphicsAlgorithm) {
        GraphicsAlgorithm visibleGa = GraphitiUtil.findVisibleGa(graphicsAlgorithm);
        int left = 0;
        int top = 0;
        int right = 0;
        int bottom = 0;
        while (visibleGa != graphicsAlgorithm) {
            left += visibleGa.getX();
            top += visibleGa.getY();
            GraphicsAlgorithm parentGa = visibleGa.getParentGraphicsAlgorithm();
            right += parentGa.getWidth() - visibleGa.getX() - visibleGa.getWidth();
            bottom += parentGa.getHeight() - visibleGa.getY() - visibleGa.getHeight();
            visibleGa = parentGa;
        }
        KInsets insets = KLayoutDataFactory.eINSTANCE.createKInsets();
        insets.setLeft(left);
        insets.setRight(right);
        insets.setTop(top);
        insets.setBottom(bottom);
        return insets;
    }
    
}
