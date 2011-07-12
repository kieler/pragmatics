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
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.BoxRelativeAnchor;
import org.eclipse.graphiti.mm.pictograms.ChopboxAnchor;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ConnectionDecorator;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.ui.editor.DiagramEditor;
import org.eclipse.graphiti.ui.internal.parts.IPictogramElementEditPart;
import org.eclipse.ui.IWorkbenchPart;

import com.google.common.collect.BiMap;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.kiml.config.IMutableLayoutConfig;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KInsets;
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

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean supports(final Object object) {
        return object instanceof DiagramEditor || object instanceof IPictogramElementEditPart;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LayoutMapping<PictogramElement> buildLayoutGraph(final IWorkbenchPart workbenchPart,
            final Object diagramPart) {
        LayoutMapping<PictogramElement> mapping = new LayoutMapping<PictogramElement>();
        mapping.setProperty(CONNECTIONS, new LinkedList<Connection>());

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
    @Override
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
     * @return true if the node has any children
     */
    private boolean buildLayoutGraphRecursively(final LayoutMapping<PictogramElement> mapping,
            final ContainerShape parentElement, final KNode parentNode) {
        boolean parentHasChildren = false;
        for (Shape shape : parentElement.getChildren()) {
            // relevant shapes are those that can be connected
            if (!shape.getAnchors().isEmpty()) {
                parentHasChildren = true;
                createNode(mapping, parentNode, shape);
            }
        }

        return parentHasChildren;
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
        GraphicsAlgorithm nodeGa = shape.getGraphicsAlgorithm();

        KNode childnode = KimlUtil.createInitializedNode();
        childnode.setParent(parentNode);
        KShapeLayout nodeLayout = childnode.getData(KShapeLayout.class);
        KInsets parentInsets = parentNode.getData(KShapeLayout.class).getInsets();
        graphicsAlg2ShapeLayout(nodeGa, nodeLayout, -parentInsets.getLeft(), -parentInsets.getTop());
        setInsets(nodeLayout, nodeGa);

        // FIXME find a way to specify the minimal size dynamically
        nodeLayout.setProperty(LayoutOptions.MIN_WIDTH, MIN_SIZE);
        nodeLayout.setProperty(LayoutOptions.MIN_HEIGHT, MIN_SIZE);

        mapping.getGraphMap().put(childnode, shape);

        if (shape instanceof ContainerShape) {
            // find a label for the container shape
            for (Shape child : ((ContainerShape) shape).getChildren()) {
                GraphicsAlgorithm textGa = child.getGraphicsAlgorithm();
                if (textGa instanceof Text) {
                    String labelText = ((Text) textGa).getValue();
                    KLabel label = childnode.getLabel();
                    label.setText(labelText);
                    graphicsAlg2ShapeLayout(textGa, label.getData(KShapeLayout.class), 0, 0);
                    break;
                }
            }
        }

        for (Anchor anchor : shape.getAnchors()) {
            // box-relative anchors are interpreted as ports
            if (anchor instanceof BoxRelativeAnchor) {
                createPort(mapping, childnode, (BoxRelativeAnchor) anchor);
            }
            // gather all connections in the diagram
            for (Connection c : anchor.getOutgoingConnections()) {
                mapping.getProperty(CONNECTIONS).add(c);
            }
        }
        
        return childnode;
    }
    
    /**
     * Transfer layout information from a pictogram graphics algorithm to a shape layout.
     * 
     * @param ga a graphics algorithm
     * @param shapeLayout a shape layout
     * @param xoffset x coordinate offset
     * @param yoffset y coordinate offset
     */
    private void graphicsAlg2ShapeLayout(final GraphicsAlgorithm ga, final KShapeLayout shapeLayout,
            final float xoffset, final float yoffset) {
        shapeLayout.setPos(ga.getX() + xoffset, ga.getY() + yoffset);
        shapeLayout.setSize(ga.getWidth(), ga.getHeight());
    }

    /**
     * Create a port for the layout graph.
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
        GraphicsAlgorithm portGa = bra.getGraphicsAlgorithm(); 

        float xPos = (float) (relWidth * parentWidth) + portGa.getX();
        float yPos = (float) (relHeight * parentHeight) + portGa.getY();
        portLayout.setPos(xPos, yPos);
        portLayout.setSize(portGa.getWidth(), portGa.getHeight());
        
        return port;
    }

    /**
     * Calculate insets from an invisible rectangle to the visible shape.
     * 
     * @param shapeLayout the shape layout
     * @param containerGa the container's graphics algorithm
     */
    private void setInsets(final KShapeLayout shapeLayout, final GraphicsAlgorithm containerGa) {
        GraphicsAlgorithm visibleGa = findVisibleGa(containerGa);
        int left = 0;
        int top = 0;
        int right = 0;
        int bottom = 0;
        while (visibleGa != containerGa) {
            left += visibleGa.getX();
            top += visibleGa.getY();
            GraphicsAlgorithm parentGa = visibleGa.getParentGraphicsAlgorithm();
            right += parentGa.getWidth() - visibleGa.getX() - visibleGa.getWidth();
            bottom += parentGa.getHeight() - visibleGa.getY() - visibleGa.getHeight();
            visibleGa = parentGa;
        }
        KInsets insets = shapeLayout.getInsets();
        insets.setLeft(left);
        insets.setRight(right);
        insets.setBottom(bottom);
        insets.setTop(top);
    }

    /**
     * Given a graphics algorithm, find the first child that is not invisible. If the GA itself
     * is visible, it is returned
     * 
     * @param graphicsAlgorithm the parent graphics algorithm
     * @return a visible graphics algorithm
     */
    private GraphicsAlgorithm findVisibleGa(final GraphicsAlgorithm graphicsAlgorithm) {
        if (graphicsAlgorithm.getLineVisible() || graphicsAlgorithm.getFilled()) {
            return graphicsAlgorithm;
        }
        for (GraphicsAlgorithm ga : graphicsAlgorithm.getGraphicsAlgorithmChildren()) {
            GraphicsAlgorithm result = findVisibleGa(ga);
            if (result != null) {
                return result;
            }
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

        // set source and target point
        KEdgeLayout edgeLayout = edge.getData(KEdgeLayout.class);
        calculateAnchorEnds(edgeLayout.getSourcePoint(), sourceAnchor, sourceNode, sourcePort);
        calculateAnchorEnds(edgeLayout.getTargetPoint(), targetAnchor, targetNode, targetPort);
        // TODO set bend points for the new edge

        graphMap.put(edge, connection);

        // find labels for the connection
        for (ConnectionDecorator decorator : connection.getConnectionDecorators()) {
            GraphicsAlgorithm ga = decorator.getGraphicsAlgorithm();
            if (ga instanceof Text) {
                Text text = (Text) ga;
                String labelText = text.getValue();
                KLabel label = KimlUtil.createInitializedLabel(edge);
                label.setText(labelText);
                edge.getLabels().add(label);
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
                labelLayout.setProperty(LayoutOptions.EDGE_LABEL_PLACEMENT, placement);
            }
        }
    }

    /**
     * Write the coordinates of the given anchor into the given point.
     * 
     * @param point
     *            a start or end point of an edge
     * @param anchor
     *            the corresponding pictogram anchor
     */
    private void calculateAnchorEnds(final KPoint point, final Anchor anchor, final KNode node,
            final KPort port) {
        if (anchor instanceof BoxRelativeAnchor && port != null) {
            KShapeLayout portLayout = port.getData(KShapeLayout.class);
            float x = portLayout.getXpos() + portLayout.getWidth() / 2.0f;
            float y = portLayout.getYpos() + portLayout.getHeight() / 2.0f;
            KShapeLayout nodeLayout = node.getData(KShapeLayout.class);
            x += nodeLayout.getXpos();
            y += nodeLayout.getYpos();
            point.setPos(x, y);
        } else if (anchor instanceof ChopboxAnchor) {
            KShapeLayout nodeLayout = node.getData(KShapeLayout.class);
            float x = nodeLayout.getWidth() / 2 + nodeLayout.getXpos();
            float y = nodeLayout.getHeight() / 2 + nodeLayout.getYpos();
            point.setPos(x, y);
        }
        // TODO handle FixPointAnchors
    }
    
}
