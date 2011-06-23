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
import com.google.common.collect.HashBiMap;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.model.IGraphicalFrameworkBridge;
import de.cau.cs.kieler.core.model.graphiti.GraphitiFrameworkBridge;
import de.cau.cs.kieler.kiml.ILayoutConfig;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KInsets;
import de.cau.cs.kieler.kiml.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.EdgeLabelPlacement;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.ui.layout.DiagramLayoutManager;
import de.cau.cs.kieler.kiml.ui.layout.ICachedLayout;
import de.cau.cs.kieler.kiml.util.KimlUtil;

/**
 * Generic layout manager implementation for Graphiti diagrams.
 * 
 * @author atr
 * @author soh
 */
@SuppressWarnings("restriction")
public class GraphitiDiagramLayoutManager extends DiagramLayoutManager {

    /** diagram editor of the currently layouted diagram. */
    private DiagramEditor diagramEditor;
    /** the last created layout graph. */
    private KNode layoutGraph;
    /** Root element of the current selection. */
    private EditPart layoutRootPart;
    /** the command that is executed for applying automatic layout. */
    private Command applyLayoutCommand;

    /** map of pictogram elements to KGraph elements. */
    private BiMap<PictogramElement, KGraphElement> pictElem2graphElemMap = HashBiMap.create();
    /** list of all connections in the diagram. */
    private List<Connection> connections = new LinkedList<Connection>();

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean supports(final IWorkbenchPart workbenchPart) {
        return workbenchPart instanceof DiagramEditor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean supports(final EditPart editPart) {
        return editPart instanceof IPictogramElementEditPart;
    }

    /** the framework bridge for this layout manager. */
    private GraphitiFrameworkBridge graphitiBridge = new GraphitiFrameworkBridge();

    /**
     * {@inheritDoc}
     */
    @Override
    public IGraphicalFrameworkBridge getBridge() {
        return graphitiBridge;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ILayoutConfig getLayoutConfig(final EditPart editPart) {
        GraphitiLayoutConfig config = new GraphitiLayoutConfig();
        if (editPart instanceof IPictogramElementEditPart) {
            config.initialize((IPictogramElementEditPart) editPart);
        } else {
            IPictogramElementEditPart rootPart = GraphitiFrameworkBridge
                    .getEditPartFromDiagramEditorInternal2(editPart);
            if (rootPart != null) {
                config.initialize(rootPart);
            }
        }
        return config;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public KNode buildLayoutGraph(final IWorkbenchPart workbenchPart, final EditPart editPart,
            final boolean layoutAncestors) {
        connections.clear();
        pictElem2graphElemMap.clear();

        if (workbenchPart instanceof DiagramEditor) {
            diagramEditor = (DiagramEditor) workbenchPart;
        } else {
            diagramEditor = null;
        }

        if (editPart instanceof IPictogramElementEditPart) {
            layoutRootPart = editPart;
        } else if (diagramEditor != null) {
            layoutRootPart = diagramEditor.getGraphicalViewer().getContents();
        }
        if (!(layoutRootPart instanceof IPictogramElementEditPart)) {
            throw new UnsupportedOperationException("Not supported by this layout manager: Editor "
                    + workbenchPart + ", Edit part " + editPart);
        }
        PictogramElement element = ((IPictogramElementEditPart) layoutRootPart)
                .getPictogramElement();

        GraphitiLayoutConfig layoutConfig;
        if (getExternalConfig() == null) {
            layoutConfig = new GraphitiLayoutConfig();
        } else {
            layoutConfig = new GraphitiLayoutConfig(getExternalConfig());
        }
        if (element instanceof Diagram) {
            KNode topNode = KimlUtil.createInitializedNode();
            KShapeLayout shapeLayout = topNode.getData(KShapeLayout.class);
            GraphicsAlgorithm ga = element.getGraphicsAlgorithm();
            shapeLayout.setPos(ga.getX(), ga.getY());
            shapeLayout.setSize(ga.getWidth(), ga.getHeight());
            pictElem2graphElemMap.put(element, topNode);

            buildLayoutGraphRecursively((Diagram) element, topNode, layoutConfig);
            
            // set user defined layout options for the diagram
            layoutConfig.setFocus(layoutRootPart);
            shapeLayout.copyProperties(layoutConfig);
            layoutGraph = topNode;
        } else if (element instanceof Shape) {
            layoutGraph = createNode(null, (Shape) element, layoutConfig);
        }

        processConnections(layoutConfig);

        return layoutGraph;
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
    private boolean buildLayoutGraphRecursively(final ContainerShape parentElement,
            final KNode parentNode, final GraphitiLayoutConfig layoutConfig) {
        boolean parentHasChildren = false;
        for (Shape shape : parentElement.getChildren()) {
            // relevant shapes are those that can be connected
            if (!shape.getAnchors().isEmpty()) {
                parentHasChildren = true;
                createNode(parentNode, shape, layoutConfig);
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
    private KNode createNode(final KNode parentNode, final Shape shape,
            final GraphitiLayoutConfig layoutConfig) {
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

        pictElem2graphElemMap.put(shape, childnode);

        boolean shapeHasChildren = false;
        if (shape instanceof ContainerShape) {
            shapeHasChildren = buildLayoutGraphRecursively((ContainerShape) shape,
                    childnode, layoutConfig);

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

        boolean shapeHasPorts = false;
        for (Anchor anchor : shape.getAnchors()) {
            // box-relative anchors are interpreted as ports
            if (anchor instanceof BoxRelativeAnchor) {
                shapeHasPorts = true;
                createPort(childnode, (BoxRelativeAnchor) anchor, layoutConfig);
            }
            // gather all connections in the diagram
            for (Connection c : anchor.getOutgoingConnections()) {
                connections.add(c);
            }
        }

        // set user defined layout options
        layoutConfig.setFocus(diagramEditor.getEditPartForPictogramElement(shape));
        layoutConfig.setPorts(shapeHasPorts);
        layoutConfig.setChildren(shapeHasChildren);
        nodeLayout.copyProperties(layoutConfig);
        
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
    private KPort createPort(final KNode parentNode, final BoxRelativeAnchor bra,
            final GraphitiLayoutConfig layoutConfig) {
        KPort port = KimlUtil.createInitializedPort();
        port.setNode(parentNode);
        KShapeLayout portLayout = port.getData(KShapeLayout.class);

        GraphicsAlgorithm referencedGa = bra.getReferencedGraphicsAlgorithm();
        if (referencedGa == null) {
            return null;
        }
        pictElem2graphElemMap.put(bra, port);

        double relWidth = bra.getRelativeWidth();
        double relHeight = bra.getRelativeHeight();

        double parentWidth = referencedGa.getWidth();
        double parentHeight = referencedGa.getHeight();
        GraphicsAlgorithm portGa = bra.getGraphicsAlgorithm(); 
        float xPos = (float) (relWidth * parentWidth) + portGa.getX();
        float yPos = (float) (relHeight * parentHeight) + portGa.getY();
        
        // node insets need to be considered
        KInsets insets = parentNode.getData(KShapeLayout.class).getInsets();
        xPos += insets.getLeft();
        yPos += insets.getTop();
        float offset = 0;
        if (relWidth + relHeight <= 1 && relWidth - relHeight <= 0) {
            // port is on the left
            offset = -insets.getLeft();
        } else if (relWidth + relHeight >= 1 && relWidth - relHeight >= 0) {
            // port is on the right
            offset = -insets.getRight();
        } else if (relHeight < 1.0f / 2) {
            // port is on the top
            offset = -insets.getTop();
        } else {
            // port is on the bottom
            offset = -insets.getBottom();
        }
        portLayout.setProperty(LayoutOptions.OFFSET, offset);

        portLayout.setPos(xPos, yPos);

        portLayout.setSize(portGa.getWidth(), portGa.getHeight());

        // set user defined layout options
        layoutConfig.setFocus(diagramEditor.getEditPartForPictogramElement(bra));
        portLayout.copyProperties(layoutConfig);
        
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
     * Creates new edges and takes care of the labels for each connection identified in the
     * {@code buildLayoutGraphRecursively} method.
     * 
     * @param layoutConfig
     *            the layout configuration
     */
    private void processConnections(final GraphitiLayoutConfig layoutConfig) {
        for (Connection entry : connections) {
            createEdge(entry, layoutConfig);
        }
    }

    /**
     * Create an edge for the layout graph.
     * 
     * @param connection a connection
     * @param layoutConfig the layout configuration
     */
    private void createEdge(final Connection connection,
            final GraphitiLayoutConfig layoutConfig) {
        KEdge edge = KimlUtil.createInitializedEdge();

        // set target node and port
        KNode targetNode;
        Anchor targetAnchor = connection.getEnd();
        KPort targetPort = (KPort) pictElem2graphElemMap.get(targetAnchor);
        if (targetPort != null) {
            edge.setTargetPort(targetPort);
            targetPort.getEdges().add(edge);
            targetNode = targetPort.getNode();
        } else {
            targetNode = (KNode) pictElem2graphElemMap.get(targetAnchor.getParent());
        }
        edge.setTarget(targetNode);

        // set source node and port
        KNode sourceNode;
        Anchor sourceAnchor = connection.getStart();
        KPort sourcePort = (KPort) pictElem2graphElemMap.get(sourceAnchor);
        if (sourcePort != null) {
            edge.setSourcePort(sourcePort);
            sourcePort.getEdges().add(edge);
            sourceNode = sourcePort.getNode();
        } else {
            sourceNode = (KNode) pictElem2graphElemMap.get(sourceAnchor.getParent());
        }
        edge.setSource(sourceNode);

        // set source and target point
        KEdgeLayout edgeLayout = edge.getData(KEdgeLayout.class);
        calculateAnchorEnds(edgeLayout.getSourcePoint(), sourceAnchor, sourceNode, sourcePort);
        calculateAnchorEnds(edgeLayout.getTargetPoint(), targetAnchor, targetNode, targetPort);
        // TODO set bend points for the new edge

        pictElem2graphElemMap.put(connection, edge);

        // find labels for the connection
        for (ConnectionDecorator decorator : connection.getConnectionDecorators()) {
            GraphicsAlgorithm ga = decorator.getGraphicsAlgorithm();
            if (ga instanceof Text) {
                Text text = (Text) ga;
                String labelText = text.getValue();
                KLabel label = KimlUtil.createInitializedLabel(edge);
                label.setText(labelText);
                edge.getLabels().add(label);
                pictElem2graphElemMap.put(decorator, label);

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

        // set user defined layout options for the edge
        layoutConfig.setFocus(diagramEditor.getEditPartForPictogramElement(connection));
        edgeLayout.copyProperties(layoutConfig);

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

    /**
     * {@inheritDoc}
     */
    @Override
    protected void transferLayout(final boolean cacheLayout) {
        // TODO support caching of layout
        GraphitiLayoutCommand command = new GraphitiLayoutCommand(diagramEditor.getEditingDomain(),
                diagramEditor.getDiagramTypeProvider().getFeatureProvider());
        for (Entry<PictogramElement, KGraphElement> entry : pictElem2graphElemMap.entrySet()) {
            command.add(entry.getValue(), entry.getKey());
        }
        applyLayoutCommand = command;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void applyLayout() {
        TransactionalEditingDomain editingDomain = diagramEditor.getEditingDomain();
        editingDomain.getCommandStack().execute(applyLayoutCommand);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public KNode getLayoutGraph() {
        return layoutGraph;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected ICachedLayout getCachedLayout() {
        // TODO support caching of layouts
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EditPart getEditPart(final KNode knode) {
        PictogramElement pe = pictElem2graphElemMap.inverse().get(knode);
        return diagramEditor.getEditPartForPictogramElement(pe);
    }
    
}
