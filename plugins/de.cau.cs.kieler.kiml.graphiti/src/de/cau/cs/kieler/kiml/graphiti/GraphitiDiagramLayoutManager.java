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
import org.eclipse.graphiti.ui.internal.parts.IPictogramElementEditPart;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.ui.IEditorPart;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KGraphData;
import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.model.graphiti.GraphitiFrameworkBridge;
import de.cau.cs.kieler.core.ui.IGraphicalFrameworkBridge;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.kiml.ILayoutConfig;
import de.cau.cs.kieler.kiml.graphiti.ElementInfo.PortInfo;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KInsets;
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
    private Map<PictogramElement, ElementInfo> pictElem2ElemInfoMap =
            new HashMap<PictogramElement, ElementInfo>();
    /** map of KGraph elements to pictogram elements. */
    private Map<KGraphElement, ElementInfo> graphElem2ElemInfoMap =
            new HashMap<KGraphElement, ElementInfo>();
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

    /** the framework bridge for this layout manager. */
    private GraphitiFrameworkBridge graphitiBridge =
            new GraphitiFrameworkBridge();

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
            IPictogramElementEditPart rootPart =
                    GraphitiFrameworkBridge
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
    public KNode buildLayoutGraph(final IEditorPart editorPart,
            final EditPart editPart, final boolean layoutAncestors) {
        connections.clear();
        pictElem2ElemInfoMap.clear();
        graphElem2ElemInfoMap.clear();

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
            throw new UnsupportedOperationException(
                    "Not supported by this layout manager: Editor "
                            + editorPart + ", Edit part " + editPart);
        }
        PictogramElement element =
                ((IPictogramElementEditPart) layoutRootPart)
                        .getPictogramElement();

        KNode topNode = KimlUtil.createInitializedNode();
        if (element instanceof ContainerShape) {
            KShapeLayout shapeLayout = topNode.getData(KShapeLayout.class);
            shapeLayout.setXpos(element.getGraphicsAlgorithm().getX());
            shapeLayout.setYpos(element.getGraphicsAlgorithm().getY());
            shapeLayout.setHeight(element.getGraphicsAlgorithm().getHeight());
            shapeLayout.setWidth(element.getGraphicsAlgorithm().getWidth());

            ElementInfo info = new ElementInfo(topNode, element);
            pictElem2ElemInfoMap.put(element, info);
            graphElem2ElemInfoMap.put(topNode, info);

            GraphitiLayoutConfig layoutConfig;
            if (getExternalConfig() == null) {
                layoutConfig = new GraphitiLayoutConfig();
            } else {
                layoutConfig = new GraphitiLayoutConfig(getExternalConfig());
            }

            buildLayoutGraphRecursively((ContainerShape) element, topNode,
                    layoutConfig);

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
    private boolean buildLayoutGraphRecursively(
            final ContainerShape parentElement, final KNode parentNode,
            final GraphitiLayoutConfig layoutConfig) {
        boolean parentHasChildren = false;
        for (Shape shape : parentElement.getChildren()) {
            boolean relevantShape = !shape.getAnchors().isEmpty();
            // all relevant shapes have to be layouted, not just containers
            // (e.g. KAOM Relations)
            if (relevantShape) {
                parentHasChildren = true;
                GraphicsAlgorithm containerGa = shape.getGraphicsAlgorithm();

                KNode childnode = KimlUtil.createInitializedNode();
                childnode.setParent(parentNode);
                KShapeLayout shapeLayout =
                        childnode.getData(KShapeLayout.class);
                shapeLayout.setXpos(containerGa.getX());
                shapeLayout.setYpos(containerGa.getY());
                shapeLayout.setHeight(containerGa.getHeight());
                shapeLayout.setWidth(containerGa.getWidth());

                setInsets(shapeLayout, containerGa);

                // TODO how to get minimal size of the shape
                shapeLayout.setProperty(LayoutOptions.MIN_WIDTH, 40.0f);
                shapeLayout.setProperty(LayoutOptions.MIN_HEIGHT, 40.0f);

                ElementInfo info = new ElementInfo(childnode, shape);
                pictElem2ElemInfoMap.put(shape, info);
                graphElem2ElemInfoMap.put(childnode, info);

                boolean shapeHasChildren = false;
                if (shape instanceof ContainerShape) {
                    shapeHasChildren =
                            buildLayoutGraphRecursively((ContainerShape) shape,
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
                        addPort(containerGa, childnode,
                                (BoxRelativeAnchor) anchor);

                    }
                    for (Connection c : anchor.getIncomingConnections()) {
                        ElementInfo.ConnectionInfo conInfo =
                                (ElementInfo.ConnectionInfo) pictElem2ElemInfoMap
                                        .get(c);
                        if (conInfo == null) {
                            conInfo = new ElementInfo.ConnectionInfo(null, c);
                            pictElem2ElemInfoMap.put(c, conInfo);
                        }
                        conInfo.setTarget(anchor);
                    }
                    for (Connection c : anchor.getOutgoingConnections()) {
                        ElementInfo.ConnectionInfo conInfo =
                                (ElementInfo.ConnectionInfo) pictElem2ElemInfoMap
                                        .get(c);
                        if (conInfo == null) {
                            conInfo = new ElementInfo.ConnectionInfo(null, c);
                            pictElem2ElemInfoMap.put(c, conInfo);
                        }
                        conInfo.setSrc(anchor);
                        connections.add(c);
                    }
                }

                // set port constraints to fixed if there are no further
                // children
                if (shapeHasPorts) {
                    shapeLayout.setProperty(LayoutOptions.PORT_CONSTRAINTS,
                            shapeHasChildren ? PortConstraints.FREE
                                    : PortConstraints.FIXED_POS);
                }

                // set user defined layout options
                layoutConfig.setFocus(diagramEditor
                        .getEditPartForPictogramElement(shape));
                shapeLayout.copyProperties(layoutConfig);
            }
        }

        // set fixed size to true if there are no further children
        KShapeLayout parentLayout = parentNode.getData(KShapeLayout.class);
        parentLayout.setProperty(LayoutOptions.FIXED_SIZE, !parentHasChildren);
        return parentHasChildren;
    }

    /**
     * @param containerGa
     *            the containerGa
     * @param childnode
     *            the parent node
     * @param bra
     *            the anchor
     */
    private void addPort(final GraphicsAlgorithm containerGa,
            final KNode childnode, final BoxRelativeAnchor bra) {
        KPort port = KimlUtil.createInitializedPort();
        ElementInfo.PortInfo portInfo = new ElementInfo.PortInfo(port, bra);
        pictElem2ElemInfoMap.put(bra, portInfo);
        graphElem2ElemInfoMap.put(port, portInfo);

        port.setNode(childnode);
        KShapeLayout portLayout = port.getData(KShapeLayout.class);

        GraphicsAlgorithm ga = bra.getReferencedGraphicsAlgorithm();
        double xoffset = bra.getGraphicsAlgorithm().getX();
        double yoffset = bra.getGraphicsAlgorithm().getY();
        if (containerGa != findVisibleGa(containerGa)) {
            xoffset += ga.getX();
            yoffset += ga.getY();
            portInfo.setContainerHasInvisibleParent(true);
        }
        double relWidth = bra.getRelativeWidth();
        double relHeight = bra.getRelativeHeight();

        double parentWidth = ga.getWidth();
        double parentHeight = ga.getHeight();
        float xPos = (float) (relWidth * parentWidth + xoffset);
        float yPos = (float) (relHeight * parentHeight + yoffset);

        Pair<Float, Float> offset = new Pair<Float, Float>(0f, 0f);
        // place port center directly on outer bounds line
        if (new Double(0.0).equals(relWidth)) {
            offset.setFirst((float) bra.getGraphicsAlgorithm().getX());
        } else if (new Double(1.0).equals(relWidth)) {
            offset.setFirst((float) -bra.getGraphicsAlgorithm().getX());
        }
        if (new Double(0.0).equals(relHeight)) {
            offset.setSecond((float) bra.getGraphicsAlgorithm().getY());
        } else if (new Double(1.0).equals(relHeight)) {
            offset.setSecond((float) -bra.getGraphicsAlgorithm().getY());
        }
        xPos += offset.getFirst();
        yPos += offset.getSecond();
        portInfo.setOffset(offset);

        portLayout.setXpos(xPos);
        portLayout.setYpos(yPos);

        portLayout.setWidth(bra.getGraphicsAlgorithm().getWidth());
        portLayout.setHeight(bra.getGraphicsAlgorithm().getHeight());
    }

    /**
     * Calculate insets from the invisible rectangle to the visible shape.
     * 
     * @param shapeLayout
     *            the shape layout
     * @param containerGa
     *            the invisible GA
     */
    private void setInsets(final KShapeLayout shapeLayout,
            final GraphicsAlgorithm containerGa) {
        GraphicsAlgorithm ga = findVisibleGa(containerGa);
        int left = 0;
        int top = 0;
        int right = 0;
        int bottom = 0;
        if (ga != containerGa) {
            left = ga.getX();
            top = ga.getY();
            right = containerGa.getWidth() - ga.getX() - ga.getWidth();
            bottom = containerGa.getHeight() - ga.getY() - ga.getHeight();
        }
        KInsets insets = shapeLayout.getProperty(LayoutOptions.INSETS);
        insets.setLeft(left);
        insets.setRight(right);
        insets.setBottom(bottom);
        insets.setTop(top);

    }

    /**
     * Find a visible GraphicsAlgorithm to stick the ports on.
     * 
     * @param graphicsAlgorithm
     *            parent GA
     * @return a visible GA
     */
    private GraphicsAlgorithm findVisibleGa(
            final GraphicsAlgorithm graphicsAlgorithm) {
        if (graphicsAlgorithm.getLineVisible()) {
            return graphicsAlgorithm;
        }
        GraphicsAlgorithm result = graphicsAlgorithm;
        for (GraphicsAlgorithm ga : graphicsAlgorithm
                .getGraphicsAlgorithmChildren()) {
            GraphicsAlgorithm returned = findVisibleGa(ga);
            if (returned.getLineVisible()) {
                result = returned;
                break;
            }
        }
        return result;
    }

    /** minimal value for the relative location of head labels. */
    private static final double HEAD_LOCATION = 0.7;
    /** maximal value for the relative location of tail labels. */
    private static final double TAIL_LOCATION = 0.3;

    /**
     * Creates new edges and takes care of the labels for each connection
     * identified in the {@code buildLayoutGraphRecursively} method.
     * 
     * @param layoutConfig
     *            the layout configuration
     */
    private void processConnections(final GraphitiLayoutConfig layoutConfig) {
        for (Connection entry : connections) {
            processConnection(entry, layoutConfig);
        }
    }

    /**
     * @param connection
     * @param layoutConfig
     */
    private void processConnection(final Connection connection,
            final GraphitiLayoutConfig layoutConfig) {
        ElementInfo.ConnectionInfo info =
                (ElementInfo.ConnectionInfo) pictElem2ElemInfoMap
                        .get(connection);
        KEdge edge = KimlUtil.createInitializedEdge();

        // set target node and port
        KNode targetNode;
        Anchor targetAnchor = info.getTarget();
        if (targetAnchor == null) {
            // connection leads nowhere, ignore
            return;
        }
        ElementInfo targetInfo = pictElem2ElemInfoMap.get(targetAnchor);
        if (targetInfo != null) {
            KPort targetPort = (KPort) targetInfo.getGraphElem();
            edge.setTargetPort(targetPort);
            targetPort.getEdges().add(edge);
            targetNode = targetPort.getNode();
        } else {
            targetInfo = pictElem2ElemInfoMap.get(targetAnchor.getParent());
            targetNode = (KNode) targetInfo.getGraphElem();
        }
        edge.setTarget(targetNode);

        // set source node and port
        KNode sourceNode;
        Anchor sourceAnchor = info.getSrc();
        ElementInfo sourceInfo = pictElem2ElemInfoMap.get(sourceAnchor);
        if (sourceInfo != null) {
            KPort sourcePort = (KPort) sourceInfo.getGraphElem();
            edge.setSourcePort(sourcePort);
            sourcePort.getEdges().add(edge);
            sourceNode = sourcePort.getNode();
        } else {
            sourceInfo = pictElem2ElemInfoMap.get(sourceAnchor.getParent());
            sourceNode = (KNode) sourceInfo.getGraphElem();
        }
        edge.setSource(sourceNode);

        // set source and target point
        KEdgeLayout edgeLayout = edge.getData(KEdgeLayout.class);
        try {
            calculateAnchorEnds(edgeLayout.getSourcePoint(), sourceAnchor);
            calculateAnchorEnds(edgeLayout.getTargetPoint(), targetAnchor);
        } catch (ElementMissingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return;
        }

        // FIXME: find a way to determine whether or not a graph is directed.
        String name = diagramEditor.getClass().getCanonicalName();
        if (name.equals("de.cau.cs.kieler.rail.editor.KrailDiagramEditor")) {
            info.setUndirected(true);
        }
        info.setGraphElem(edge);
        graphElem2ElemInfoMap.put(edge, info);

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
                ElementInfo labelInfo = new ElementInfo(label, decorator);
                graphElem2ElemInfoMap.put(label, labelInfo);

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
                labelLayout.setProperty(LayoutOptions.EDGE_LABEL_PLACEMENT,
                        placement);
            }
        }

        // set user defined layout options for the edge
        layoutConfig.setFocus(diagramEditor
                .getEditPartForPictogramElement(connection));
        edgeLayout.copyProperties(layoutConfig);

    }

    /**
     * @author soh
     */
    private static class ElementMissingException extends Exception {

        /**
         *
         */
        private static final long serialVersionUID = 1L;

    }

    /**
     * Write the coordinates of the given anchor into the given point.
     * 
     * @param point
     *            a start or end point of an edge
     * @param anchor
     *            the corresponding pictogram anchor
     * @throws ElementMissingException
     */
    private void calculateAnchorEnds(final KPoint point, final Anchor anchor)
            throws ElementMissingException {
        if (anchor instanceof BoxRelativeAnchor) {
            BoxRelativeAnchor port = (BoxRelativeAnchor) anchor;
            ElementInfo.PortInfo info =
                    (PortInfo) pictElem2ElemInfoMap.get(port);
            if (info == null || info.getGraphElem() == null) {
                throw new ElementMissingException();
            }
            KPort kPort = info.getGraphElem();
            KShapeLayout portLayout = kPort.getData(KShapeLayout.class);
            float x = portLayout.getXpos() + portLayout.getWidth() / 2.0f;
            float y = portLayout.getYpos() + portLayout.getHeight() / 2.0f;
            if (info.containerHasInvisibleParent()) {
                KShapeLayout nodeLayout =
                        kPort.getNode().getData(KShapeLayout.class);
                x += nodeLayout.getXpos();
                y += nodeLayout.getYpos();
            }
            point.setX(x);
            point.setY(y);
        } else if (anchor instanceof ChopboxAnchor) {
            KNode kNode =
                    (KNode) pictElem2ElemInfoMap.get(anchor.getParent())
                            .getGraphElem();
            KShapeLayout nodeLayout = kNode.getData(KShapeLayout.class);
            float x = nodeLayout.getWidth() / 2.0f + nodeLayout.getXpos();
            float y = nodeLayout.getHeight() / 2.0f + nodeLayout.getYpos();
            point.setX(x);
            point.setY(y);
        }
        // TODO handle FixPointAnchors
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void transferLayout(final boolean cacheLayout) {
        // TODO support caching of layout
        GraphitiLayoutCommand command =
                new GraphitiLayoutCommand(diagramEditor.getEditingDomain(),
                        diagramEditor.getDiagramTypeProvider()
                                .getFeatureProvider(), pictElem2ElemInfoMap,
                        graphElem2ElemInfoMap);
        for (Entry<KGraphElement, ElementInfo> entry : graphElem2ElemInfoMap
                .entrySet()) {
            KGraphElement element = entry.getKey();
            KGraphData layoutData =
                    element.getData(element instanceof KEdge ? KEdgeLayout.class
                            : KShapeLayout.class);
            if (!layoutData.getProperty(LayoutOptions.NO_LAYOUT)) {
                command.add(element, entry.getValue().getPicElem());
            }
        }
        applyLayoutCommand = command;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void applyLayout() {
        TransactionalEditingDomain editingDomain =
                diagramEditor.getEditingDomain();
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
     * map of editor change listeners to all editors for which they have
     * registered.
     */
    private Map<IEditorChangeListener, List<Pair<DiagramEditor, ISelectionChangedListener>>> listenerMap =
            new HashMap<IEditorChangeListener, List<Pair<DiagramEditor, ISelectionChangedListener>>>();

    /**
     * {@inheritDoc}
     */
    @Override
    public void addChangeListener(final IEditorPart editorPart,
            final IEditorChangeListener listener) {
        if (editorPart instanceof DiagramEditor) {
            final DiagramEditor diagEditor = (DiagramEditor) editorPart;
            diagEditor.getGraphicalViewer().addSelectionChangedListener(
                    listener);
            List<Pair<DiagramEditor, ISelectionChangedListener>> editorList =
                    listenerMap.get(listener);
            if (editorList == null) {
                editorList =
                        new LinkedList<Pair<DiagramEditor, ISelectionChangedListener>>();
                listenerMap.put(listener, editorList);
            }
            editorList.add(new Pair<DiagramEditor, ISelectionChangedListener>(
                    diagEditor, listener));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeChangeListener(final IEditorChangeListener listener) {
        List<Pair<DiagramEditor, ISelectionChangedListener>> editorList =
                listenerMap.remove(listener);
        if (editorList != null) {
            for (Pair<DiagramEditor, ISelectionChangedListener> pair : editorList) {
                pair.getFirst().getGraphicalViewer()
                        .removeSelectionChangedListener(pair.getSecond());
            }
        }
    }

    /**
     * Returns the edit part associated with the given layout node. This is only
     * valid after {@link #buildLayoutGraph(IEditorPart, EditPart, boolean)} was
     * called.
     * 
     * @param knode
     *            a node from the layout graph
     * @return the corresponding edit part, or {@code null}
     */
    @Override
    public EditPart getEditPart(final KNode knode) {
        PictogramElement pe = graphElem2ElemInfoMap.get(knode).getPicElem();
        return diagramEditor.getEditPartForPictogramElement(pe);
    }
}
