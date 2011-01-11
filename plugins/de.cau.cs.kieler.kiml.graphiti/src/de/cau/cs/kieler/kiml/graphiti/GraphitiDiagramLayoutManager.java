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

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.BoxRelativeAnchor;
import org.eclipse.graphiti.mm.pictograms.ChopboxAnchor;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ConnectionDecorator;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.ui.editor.DiagramEditor;
import org.eclipse.graphiti.ui.internal.editor.GraphitiScrollingGraphicalViewer;
import org.eclipse.graphiti.ui.internal.parts.IPictogramElementEditPart;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.ui.IEditorPart;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KGraphData;
import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.model.graphiti.GraphitiEditingProvider;
import de.cau.cs.kieler.core.ui.IEditingProvider;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.kiml.ILayoutConfig;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.EdgeLabelPlacement;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortConstraints;
import de.cau.cs.kieler.kiml.ui.IEditorChangeListener;
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
    private Map<PictogramElement, KGraphElement> pictElem2GraphElemMap
            = new HashMap<PictogramElement, KGraphElement>();
    /** map of KGraph elements to pictogram elements. */
    private Map<KGraphElement, PictogramElement> graphElem2PictElemMap
            = new HashMap<KGraphElement, PictogramElement>();
    /** list of all connections in the diagram. */
    private List<Connection> connections = new LinkedList<Connection>();

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean supports(final IEditorPart editorPart) {
        return editorPart instanceof DiagramEditor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean supports(final EditPart editPart) {
        return editPart instanceof IPictogramElementEditPart;
    }
    
    /** the editing provider for this layout manager. */
    private GraphitiEditingProvider editingProvider = new GraphitiEditingProvider();

    /**
     * {@inheritDoc}
     */
    @Override
    public IEditingProvider getProvider() {
        return editingProvider;
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
            IPictogramElementEditPart rootPart = getEditPartFromDiagramEditorInternal2(editPart);
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
    public KNode buildLayoutGraph(final IEditorPart editorPart,
            final EditPart editPart, final boolean layoutAncestors) {
        connections.clear();
        pictElem2GraphElemMap.clear();
        graphElem2PictElemMap.clear();

        if (editorPart instanceof DiagramEditor) {
            diagramEditor = (DiagramEditor) editorPart;
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
                + editorPart + ", Edit part " + editPart);
        }
        PictogramElement element = ((IPictogramElementEditPart) layoutRootPart)
                .getPictogramElement();

        KNode topNode = KimlUtil.createInitializedNode();
        if (element instanceof ContainerShape) {
            KShapeLayout shapeLayout = topNode.getData(KShapeLayout.class);
            shapeLayout.setXpos(element.getGraphicsAlgorithm().getX());
            shapeLayout.setYpos(element.getGraphicsAlgorithm().getY());
            shapeLayout.setHeight(element.getGraphicsAlgorithm().getHeight());
            shapeLayout.setWidth(element.getGraphicsAlgorithm().getWidth());

            pictElem2GraphElemMap.put(element, topNode);
            graphElem2PictElemMap.put(topNode, element);

            GraphitiLayoutConfig layoutConfig;
            if (getExternalConfig() == null) {
                layoutConfig = new GraphitiLayoutConfig();
            } else {
                layoutConfig = new GraphitiLayoutConfig(getExternalConfig());
            }

            buildLayoutGraphRecursively((ContainerShape) element, topNode, layoutConfig);

            // set user defined layout options for the diagram
            layoutConfig.setFocus(layoutRootPart);
            shapeLayout.copyProperties(layoutConfig);

            processConnections(layoutConfig);
        }
        layoutGraph = topNode;

        return layoutGraph;
    }

    /**
     * Recursively builds a layout graph by analyzing the children of the given
     * current pictogram Element.
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
            boolean relevantShape = false;
            // all relevant shapes have to be layouted, not just containers (e.g. KAOM Relations)
            for (Anchor anchor : shape.getAnchors()) {
                if (anchor instanceof ChopboxAnchor) {
                    relevantShape = true;
                    parentHasChildren = true;
                    break;
                }
            }
            if (relevantShape) {
                GraphicsAlgorithm containerGa = shape.getGraphicsAlgorithm();

                KNode childnode = KimlUtil.createInitializedNode();
                childnode.setParent(parentNode);
                KShapeLayout shapeLayout = childnode.getData(KShapeLayout.class);
                shapeLayout.setXpos(containerGa.getX());
                shapeLayout.setYpos(containerGa.getY());
                shapeLayout.setHeight(containerGa.getHeight());
                shapeLayout.setWidth(containerGa.getWidth());

                // TODO how to get minimal size of the shape
                shapeLayout.setProperty(LayoutOptions.MIN_WIDTH, 40.0f);
                shapeLayout.setProperty(LayoutOptions.MIN_HEIGHT, 40.0f);

                pictElem2GraphElemMap.put(shape, childnode);
                graphElem2PictElemMap.put(childnode, shape);

                boolean shapeHasChildren = false;
                if (shape instanceof ContainerShape) {
                    shapeHasChildren = buildLayoutGraphRecursively((ContainerShape) shape,
                            childnode, layoutConfig);

                    // find a label for the container shape
                    for (Shape child : ((ContainerShape) shape).getChildren()) {
                        GraphicsAlgorithm ga = child.getGraphicsAlgorithm();
                        if (ga instanceof Text) {
                            String labelText = ((Text) ga).getValue();
                            KLabel label = childnode.getLabel();
                            label.setText(labelText);
                            break;
                        }
                    }
                }

                boolean shapeHasPorts = false;
                for (Anchor anchor : shape.getAnchors()) {
                    if (anchor instanceof BoxRelativeAnchor) {
                        shapeHasPorts = true;
                        KPort port = KimlUtil.createInitializedPort();
                        pictElem2GraphElemMap.put(anchor, port);
                        graphElem2PictElemMap.put(port, anchor);
                        port.setNode(childnode);
                        KShapeLayout portLayout = port.getData(KShapeLayout.class);

                        BoxRelativeAnchor bra = (BoxRelativeAnchor) anchor;
                        double relWidth = bra.getRelativeWidth();
                        double relHeight = bra.getRelativeHeight();
                        int xoffset = anchor.getGraphicsAlgorithm().getX();
                        int yoffset = anchor.getGraphicsAlgorithm().getY();
                        double parentWidth = shapeLayout.getWidth();
                        double parentHeight = shapeLayout.getHeight();
                        portLayout.setXpos((float) (relWidth * parentWidth + xoffset));
                        portLayout.setYpos((float) (relHeight * parentHeight + yoffset));

                        portLayout.setWidth(anchor.getGraphicsAlgorithm().getWidth());
                        portLayout.setHeight(anchor.getGraphicsAlgorithm().getHeight());
                    }
                    connections.addAll(anchor.getOutgoingConnections());
                }
                
                // set port constraints to fixed if there are no further children
                if (shapeHasPorts) {
                    shapeLayout.setProperty(LayoutOptions.PORT_CONSTRAINTS,
                            shapeHasChildren ? PortConstraints.FREE : PortConstraints.FIXED_POS);
                }

                // set user defined layout options
                layoutConfig.setFocus(diagramEditor.getEditPartForPictogramElement(shape));
                shapeLayout.copyProperties(layoutConfig);
            }
        }

        // set fixed size to true if there are no further children
        KShapeLayout parentLayout = parentNode.getData(KShapeLayout.class);
        parentLayout.setProperty(LayoutOptions.FIXED_SIZE, !parentHasChildren);
        return parentHasChildren;
    }

    /** minimal value for the relative location of head labels. */
    private static final double HEAD_LOCATION = 0.7;
    /** maximal value for the relative location of tail labels. */
    private static final double TAIL_LOCATION = 0.3;
    
    /**
     * Creates new edges and takes care of the labels for each connection
     * identified in the {@code buildLayoutGraphRecursively} method.
     * 
     * @param layoutConfig the layout configuration
     */
    private void processConnections(final GraphitiLayoutConfig layoutConfig) {
        for (Connection connection : connections) {
            KEdge edge = KimlUtil.createInitializedEdge();
            pictElem2GraphElemMap.put(connection, edge);
            graphElem2PictElemMap.put(edge, connection);

            // set target node and port
            KNode targetNode;
            KPort targetPort = (KPort) pictElem2GraphElemMap.get(connection.getEnd());
            if (targetPort == null) {
                targetNode = (KNode) pictElem2GraphElemMap.get(connection.getEnd().getParent());
            } else {
                edge.setTargetPort(targetPort);
                targetPort.getEdges().add(edge);
                targetNode = targetPort.getNode();
            }
            edge.setTarget(targetNode);
            
            // set source node and port
            KNode sourceNode;
            KPort sourcePort = (KPort) pictElem2GraphElemMap.get(connection.getStart());
            if (sourcePort == null) {
                sourceNode = (KNode) pictElem2GraphElemMap.get(connection.getStart().getParent());
            } else {
                edge.setSourcePort(sourcePort);
                sourcePort.getEdges().add(edge);
                sourceNode = sourcePort.getNode();
            }
            edge.setSource(sourceNode);
            
            // set source and target point
            KEdgeLayout edgeLayout = edge.getData(KEdgeLayout.class);
            calculateAnchorEnds(edgeLayout.getSourcePoint(), connection.getStart());
            calculateAnchorEnds(edgeLayout.getTargetPoint(), connection.getEnd());

            // find labels for the connection
            for (ConnectionDecorator decorator : connection
                    .getConnectionDecorators()) {
                GraphicsAlgorithm ga = decorator.getGraphicsAlgorithm();
                if (ga instanceof Text) {
                    Text text = (Text) ga;
                    String labelText = text.getValue();
                    KLabel label = KimlUtil.createInitializedLabel(edge);
                    label.setText(labelText);
                    edge.getLabels().add(label);
                    graphElem2PictElemMap.put(label, decorator);
                    
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
    }

    /**
     * Write the coordinates of the given anchor into the given point.
     * 
     * @param point a start or end point of an edge
     * @param anchor the corresponding pictogram anchor
     */
    private void calculateAnchorEnds(final KPoint point, final Anchor anchor) {
        if (anchor instanceof BoxRelativeAnchor) {
            BoxRelativeAnchor port = (BoxRelativeAnchor) anchor;
            KPort kPort = (KPort) pictElem2GraphElemMap.get(port);
            KShapeLayout portLayout = kPort.getData(KShapeLayout.class);
            float x = portLayout.getXpos() + portLayout.getWidth() / 2;
            float y = portLayout.getYpos() + portLayout.getHeight() / 2;
            KShapeLayout nodeLayout = kPort.getNode().getData(KShapeLayout.class);
            x += nodeLayout.getXpos();
            y += nodeLayout.getYpos();
            point.setX(x);
            point.setY(y);
        } else if (anchor instanceof ChopboxAnchor) {
            KNode kNode = (KNode) pictElem2GraphElemMap.get(anchor.getParent());
            KShapeLayout nodeLayout = kNode.getData(KShapeLayout.class);
            float x = nodeLayout.getWidth() / 2 + nodeLayout.getXpos();
            float y = nodeLayout.getHeight() / 2 + nodeLayout.getYpos();
            point.setX(x);
            point.setY(y);
        }
        // TODO handle FixPointAnchors
    }

    /**
     * In some cases the EditPart passed to the methods is the mysterious
     * DiagramEditorInternal$2. This method tries to get the root edit part from
     * the corresponding diagram.
     * 
     * @param editPart the diagram's top-level edit part
     * @return the root pictogram element edit part
     */
    private IPictogramElementEditPart getEditPartFromDiagramEditorInternal2(final EditPart editPart) {
        EditPartViewer viewer = editPart.getViewer();
        if (viewer instanceof GraphitiScrollingGraphicalViewer) {
            EditPart contents = viewer.getContents();
            if (contents instanceof IPictogramElementEditPart) {
                return (IPictogramElementEditPart) contents;
            }
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void transferLayout(final boolean cacheLayout) {
        // TODO support caching of layout
        GraphitiLayoutCommand command = new GraphitiLayoutCommand(diagramEditor.getEditingDomain(),
                diagramEditor.getDiagramTypeProvider().getFeatureProvider());
        for (Entry<KGraphElement, PictogramElement> entry : graphElem2PictElemMap.entrySet()) {
            KGraphElement element = entry.getKey();
            KGraphData layoutData = element.getData(element instanceof KEdge
                    ? KEdgeLayout.class : KShapeLayout.class);
            if (!layoutData.getProperty(LayoutOptions.NO_LAYOUT)) {
                command.add(element, entry.getValue());
            }
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

    /**  map of editor change listeners to all editors for which they have registered. */
    private Map<IEditorChangeListener, List<Pair<DiagramEditor, ISelectionChangedListener>>> listenerMap
            = new HashMap<IEditorChangeListener, List<Pair<DiagramEditor, ISelectionChangedListener>>>();

    /**
     * {@inheritDoc}
     */
    @Override
    public void addChangeListener(final IEditorPart editorPart,
            final IEditorChangeListener listener) {
        if (editorPart instanceof DiagramEditor) {
            final DiagramEditor diagEditor = (DiagramEditor) editorPart;
            diagEditor.getGraphicalViewer().addSelectionChangedListener(listener);
            List<Pair<DiagramEditor, ISelectionChangedListener>> editorList = listenerMap.get(listener);
            if (editorList == null) {
                editorList = new LinkedList<Pair<DiagramEditor, ISelectionChangedListener>>();
                listenerMap.put(listener, editorList);
            }
            editorList.add(new Pair<DiagramEditor, ISelectionChangedListener>(diagEditor, listener));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeChangeListener(final IEditorChangeListener listener) {
        List<Pair<DiagramEditor, ISelectionChangedListener>> editorList = listenerMap
                .remove(listener);
        if (editorList != null) {
            for (Pair<DiagramEditor, ISelectionChangedListener> pair : editorList) {
                pair.getFirst().getGraphicalViewer()
                        .removeSelectionChangedListener(pair.getSecond());
            }
        }
    }

}
