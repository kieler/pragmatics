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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.graphiti.features.context.impl.LayoutContext;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.algorithms.styles.Point;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.AnchorContainer;
import org.eclipse.graphiti.mm.pictograms.BoxRelativeAnchor;
import org.eclipse.graphiti.mm.pictograms.ChopboxAnchor;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ConnectionDecorator;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.FreeFormConnection;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.ui.editor.DiagramEditor;
import org.eclipse.graphiti.ui.internal.editor.GraphitiScrollingGraphicalViewer;
import org.eclipse.graphiti.ui.internal.parts.DiagramEditPart;
import org.eclipse.graphiti.ui.internal.parts.IPictogramElementEditPart;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.ui.IEditorPart;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.kiml.ILayoutConfig;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortConstraints;
import de.cau.cs.kieler.kiml.ui.IEditorChangeListener;
import de.cau.cs.kieler.kiml.ui.layout.DiagramLayoutManager;
import de.cau.cs.kieler.kiml.ui.layout.ICachedLayout;
import de.cau.cs.kieler.kiml.ui.layout.ILayoutInspector;
import de.cau.cs.kieler.kiml.ui.util.KimlUiUtil;
import de.cau.cs.kieler.kiml.util.KimlUtil;

/**
 * @author atr
 */
@SuppressWarnings("restriction")
public class GraphitiDiagramLayoutManager extends DiagramLayoutManager {

    /** diagram editor of the currently layouted diagram. */
    private DiagramEditor diagramEditor;

    /** the last created layout graph. */
    private KNode layoutGraph;

    /** Root element of the current selection. */
    private EditPart layoutRootPart;

    /** map of pictogram Element to KGraph Element. */
    private Map<PictogramElement, KGraphElement> pictElem2GraphElemMap = new HashMap<PictogramElement, KGraphElement>();
    /** map of KGraph Element to pictogram element. */
    private Map<KGraphElement, PictogramElement> graphElem2PictElemMap = new HashMap<KGraphElement, PictogramElement>();

    /** map of Connection to Kedge. */
    private Map<Connection, KEdge> reference2EdgeMap = new HashMap<Connection, KEdge>();

    /** Link list of all connections present in the diagram. */
    private LinkedList<Connection> connections = new LinkedList<Connection>();

    /**
     * {@inheritDoc}
     */
    @Override
    public EditPart getCurrentEditPart() {
        return layoutRootPart;
    }

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

    /**
     * {@inheritDoc}
     */
    @Override
    public KNode buildLayoutGraph(final IEditorPart editorPart,
            final EditPart editPart, final boolean layoutAncestors) {

        connections.clear();
        reference2EdgeMap.clear();
        pictElem2GraphElemMap.clear();
        graphElem2PictElemMap.clear();

        if (editorPart instanceof DiagramEditor) {
            diagramEditor = (DiagramEditor) editorPart;

            layoutRootPart = diagramEditor.getGraphicalViewer().getContents();
            if (layoutRootPart instanceof IPictogramElementEditPart) {
                PictogramElement element = ((IPictogramElementEditPart) layoutRootPart)
                        .getPictogramElement();
                if (element != null) {
                    KNode topNode = KimlUtil.createInitializedNode();

                    KShapeLayout shapeLayout = topNode
                            .getData(KShapeLayout.class);
                    shapeLayout.setXpos(element.getGraphicsAlgorithm().getX());
                    shapeLayout.setYpos(element.getGraphicsAlgorithm().getY());
                    shapeLayout.setHeight(element.getGraphicsAlgorithm()
                            .getHeight());
                    shapeLayout.setWidth(element.getGraphicsAlgorithm()
                            .getWidth());

                    pictElem2GraphElemMap.put(element, topNode);
                    graphElem2PictElemMap.put(topNode, element);
                    layoutGraph = topNode;

                    GraphitiLayoutConfig layoutConfig;
                    if (getExternalConfig() == null) {
                        layoutConfig = new GraphitiLayoutConfig();
                    } else {
                        layoutConfig = new GraphitiLayoutConfig();
                        // FIXME: implement external config support
                        // layoutConfig = new
                        // GraphitiLayoutConfig(getExternalConfig());
                    }

                    buildLayoutGraphRecursively(element, topNode, layoutConfig);

                    // set user defined layout options for the diagram
                    layoutConfig.setFocus(layoutRootPart);
                    shapeLayout.copyProperties(layoutConfig);

                    processConnections(layoutConfig);
                }
            }
        }

        return layoutGraph;
    }

    /**
     * Recursively builds a layout graph by analyzing the children of the given
     * current pictogram Element.
     * 
     * @param topNode
     *            the corresponding KNode
     * @param currentElement
     *            the currently analyzed element
     * @param layoutConfig
     *            the layout config
     * @return returns true if current pictogram element has no further children
     */
    private boolean buildLayoutGraphRecursively(
            final PictogramElement currentElement, final KNode topNode,
            final GraphitiLayoutConfig layoutConfig) {
        EList<Shape> list = null;
        boolean returnstate = true;

        if (topNode != null) {
            if (currentElement instanceof ContainerShape) {
                list = ((ContainerShape) currentElement).getChildren();
            } else {
                // no more children
                return true;
            }

            for (Shape shape : list) {
                boolean relevantShape = false;
                // all relevant shapes have to be layouted not just containers
                // e.g. KAOM Relations
                for (Anchor a : shape.getAnchors()) {
                    if (a instanceof ChopboxAnchor) {
                        relevantShape = true;
                        returnstate = false;
                        break;
                    }
                }
                if (relevantShape) {
                    GraphicsAlgorithm containerGa = shape
                            .getGraphicsAlgorithm();

                    KNode childnode = KimlUtil.createInitializedNode();

                    childnode.setParent(topNode);
                    KShapeLayout shapeLayout = childnode
                            .getData(KShapeLayout.class);
                    float containerGaX = containerGa.getX();
                    shapeLayout.setXpos(containerGaX);
                    float containerGaY = containerGa.getY();
                    shapeLayout.setYpos(containerGaY);
                    float containerGaHeight = containerGa.getHeight();
                    shapeLayout.setHeight(containerGaHeight);
                    float containerGaWidth = containerGa.getWidth();
                    shapeLayout.setWidth(containerGaWidth);

                    // TODO how to get minimal size of the shape
                    shapeLayout.setProperty(LayoutOptions.MIN_WIDTH, 40.0f);
                    shapeLayout.setProperty(LayoutOptions.MIN_HEIGHT, 40.0f);

                    pictElem2GraphElemMap.put(shape, childnode);
                    graphElem2PictElemMap.put(childnode, shape);

                    boolean state = true;
                    if (shape instanceof ContainerShape) {
                        state = buildLayoutGraphRecursively(shape, childnode,
                                layoutConfig);

                        if (state) {
                            shapeLayout.setProperty(
                                    LayoutOptions.PORT_CONSTRAINTS,
                                    PortConstraints.FIXED_POS);
                        }

                        // find a label for the container shape
                        for (Shape child : ((ContainerShape) shape)
                                .getChildren()) {
                            GraphicsAlgorithm ga = child.getGraphicsAlgorithm();
                            if (ga instanceof Text) {
                                Text text = (Text) ga;
                                String labelText = text.getValue();

                                KLabel label = childnode.getLabel();
                                label.setText(labelText);
                                shapeLayout.setProperty(
                                        LayoutOptions.MIN_WIDTH,
                                        text.getWidth());
                                shapeLayout.setProperty(
                                        LayoutOptions.MIN_HEIGHT,
                                        text.getWidth());
                                break;
                            }
                        }
                    }

                    // set user defined layout options
                    layoutConfig.setFocus(diagramEditor
                            .getEditPartForPictogramElement(shape));
                    shapeLayout.copyProperties(layoutConfig);

                    // set fixed size = true if there are no further children
                    shapeLayout.setProperty(LayoutOptions.FIXED_SIZE, state);

                    if (shape.getAnchors().size() != 0) {
                        EList<Anchor> childAnchors = shape.getAnchors();
                        for (Anchor anchor : childAnchors) {
                            if (anchor instanceof BoxRelativeAnchor) {
                                KPort port = KimlUtil.createInitializedPort();
                                pictElem2GraphElemMap.put(anchor, port);
                                graphElem2PictElemMap.put(port, anchor);
                                port.setNode(childnode);
                                KShapeLayout portLayout = port
                                        .getData(KShapeLayout.class);

                                BoxRelativeAnchor bra = (BoxRelativeAnchor) anchor;
                                double relWidth = bra.getRelativeWidth();
                                double relHeight = bra.getRelativeHeight();
                                double portWidth = anchor
                                        .getGraphicsAlgorithm().getX();
                                double portHeight = anchor
                                        .getGraphicsAlgorithm().getY();
                                double parentWidth = shapeLayout.getWidth();
                                double parentHeight = shapeLayout.getHeight();
                                float x = (float) (relWidth * parentWidth + portWidth);
                                float y = (float) (relHeight * parentHeight + portHeight);
                                portLayout.setXpos(x);
                                portLayout.setYpos(y);

                                portLayout.setWidth(anchor
                                        .getGraphicsAlgorithm().getWidth());
                                portLayout.setHeight(anchor
                                        .getGraphicsAlgorithm().getHeight());
                            }
                            EList<Connection> conn = anchor
                                    .getOutgoingConnections();
                            for (Connection connection : conn) {
                                connections.add(connection);
                            }
                        }
                    }
                }
            }
        }
        return returnstate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ILayoutInspector getInspector(final EditPart editPart) {
        if (editPart instanceof IPictogramElementEditPart) {
            return new GraphitiLayoutInspector(
                    (IPictogramElementEditPart) editPart);
        }
        EditPart part = getEditPartFromDiagramEditorInternal2(editPart);
        if (part != null) {
            return getInspector(part);
        }
        return null;
    }

    /**
     * In some cases the EditPart passed to the methods is the mysterious
     * DiagramEditorInternal$2. This method tries to get the rootedit part from
     * the corresponding diagram.
     * 
     * @param editPart
     * @return
     */
    private EditPart getEditPartFromDiagramEditorInternal2(
            final EditPart editPart) {
        if (editPart.getParent() == null) {
            EditPartViewer viewer = editPart.getViewer();
            if (viewer instanceof GraphitiScrollingGraphicalViewer) {
                GraphitiScrollingGraphicalViewer graphitiViewer = null;
                graphitiViewer = (GraphitiScrollingGraphicalViewer) viewer;
                EditPart contents = graphitiViewer.getContents();
                if (contents instanceof DiagramEditPart) {
                    return contents;
                }
            }
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ILayoutInspector getInspector(final IEditorPart editorPart) {
        if (editorPart instanceof DiagramEditor) {
            DiagramEditor ed = (DiagramEditor) editorPart;
            return getInspector(ed.getGraphicalViewer().getRootEditPart());
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void transferLayout(final boolean cacheLayout) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void applyLayout() {
        TransactionalEditingDomain editingDomain = diagramEditor
                .getEditingDomain();
        KimlUiUtil.runModelChange(new Runnable() {
            public void run() {
                kShape2Diag(layoutGraph);
                kEdge2Conn();
            }
        }, editingDomain, "Automatic Layout");
    }

    /**
     * Replaces the new coordinates calculated by the layout algorithm back to
     * the pictogram element.
     * 
     * @param root
     *            the topNode
     */
    private void kShape2Diag(final KNode root) {
        for (KNode node : root.getChildren()) {
            kShape2Diag(node);
        }
        PictogramElement pelem = graphElem2PictElemMap.get(root);
        kShape2Diag(pelem, root);
        for (KPort port : root.getPorts()) {
            pelem = graphElem2PictElemMap.get(port);
            kShape2Diag(pelem, port);
        }
    }

    /**
     * 
     * @param pelem
     * @param kelem
     */
    private void kShape2Diag(final PictogramElement pelem,
            final KGraphElement kelem) {
        KShapeLayout shapeLayout = kelem.getData(KShapeLayout.class);
        GraphicsAlgorithm ga = pelem.getGraphicsAlgorithm();

        if (pelem instanceof BoxRelativeAnchor) {
            BoxRelativeAnchor anchor = (BoxRelativeAnchor) pelem;
            double x = shapeLayout.getXpos();
            double y = shapeLayout.getYpos();
            KNode knode = (KNode) pictElem2GraphElemMap.get(anchor.getParent());
            KShapeLayout parentLayout = knode.getData(KShapeLayout.class);

            double portWidth = anchor.getGraphicsAlgorithm().getX();
            double portHeight = anchor.getGraphicsAlgorithm().getY();

            double parentWidth = parentLayout.getWidth();
            double parentHeight = parentLayout.getHeight();

            double relWidth = ((x - portWidth) / parentWidth);
            double relHeight = ((y - portHeight) / parentHeight);

            anchor.setRelativeWidth(relWidth);
            anchor.setRelativeHeight(relHeight);
        } else {
            ga.setX((int) shapeLayout.getXpos());
            ga.setY((int) shapeLayout.getYpos());
        }
        ga.setHeight((int) shapeLayout.getHeight());
        ga.setWidth((int) shapeLayout.getWidth());

        diagramEditor.getDiagramTypeProvider().getFeatureProvider()
                .layoutIfPossible(new LayoutContext(pelem));
    }

    /**
     * Replaces the new bendpoints calculated by the layout algorithm back to
     * the Connection.
     */
    private void kEdge2Conn() {
        for (Map.Entry<Connection, KEdge> entryLink : reference2EdgeMap
                .entrySet()) {
            KEdge edge = entryLink.getValue();
            Connection conn = entryLink.getKey();

            KEdgeLayout edgeLayout = edge.getData(KEdgeLayout.class);
            EList<Point> pointList = ((FreeFormConnection) conn)
                    .getBendpoints();

            pointList.clear();

            EList<KPoint> points = edgeLayout.getBendPoints();
            List<KPoint> allPoints = new LinkedList<KPoint>();

            int startXOffset = 0;
            int startYOffset = 0;
            if (conn.getStart() instanceof BoxRelativeAnchor) {
                if (conn.getStart().getParent().eContents()
                        .contains(conn.getEnd().getParent())) {
                    // special rule for connections inside of the node
                    startXOffset = conn.getStart().getParent()
                            .getGraphicsAlgorithm().getX();
                    startYOffset = conn.getStart().getParent()
                            .getGraphicsAlgorithm().getY();
                }
            }
            Pair<Integer, Integer> coords = calcCoordOffset(conn);
            int xOffset = coords.getFirst();
            int yOffset = coords.getSecond();

            KPoint source = edgeLayout.getSourcePoint();
            KPoint target = edgeLayout.getTargetPoint();
            // allPoints.add(source);
            allPoints.addAll(points);
            // allPoints.add(target);
            // if (conn.getStart() instanceof ChopboxAnchor) {
            // moveBendPointOutofNode(edge.getSource(), allPoints.get(0),
            // allPoints.get(1));
            // }
            // if (conn.getEnd() instanceof ChopboxAnchor) {
            // int size = allPoints.size();
            // moveBendPointOutofNode(edge.getTarget(),
            // allPoints.get(size - 1), allPoints.get(size - 2));
            // }
            removeRedundantBendpoints(allPoints);

            for (KPoint pnt : allPoints) {
                Point point = Graphiti.getGaService().createPoint(
                        (int) pnt.getX() + startXOffset + xOffset,
                        (int) pnt.getY() + startYOffset + yOffset);
                pointList.add(point);
            }
        }
    }

    /**
     * 
     * @param node
     * @param point
     * @param target
     */
    private void moveBendPointOutofNode(final KNode node, final KPoint point,
            final KPoint target) {
        KShapeLayout nodeLayout = node.getData(KShapeLayout.class);
        if (target.getX() < nodeLayout.getXpos()) {
            point.setX(nodeLayout.getXpos());
        } else if (target.getX() > nodeLayout.getXpos() + nodeLayout.getWidth()) {
            point.setX(nodeLayout.getXpos() + nodeLayout.getWidth());
        }
        if (target.getY() < nodeLayout.getYpos()) {
            point.setY(nodeLayout.getYpos());
        } else if (target.getY() > nodeLayout.getYpos()
                + nodeLayout.getHeight()) {
            point.setY(nodeLayout.getYpos() + nodeLayout.getHeight());
        }
    }

    /**
     * @param allPoints
     */
    private void removeRedundantBendpoints(final List<KPoint> allPoints) {
        if (allPoints.size() > 2) {
            List<KPoint> removable = new LinkedList<KPoint>();

            KPoint vOld = allPoints.get(0);
            KPoint old = allPoints.get(1);
            for (int i = 2; i < allPoints.size(); i++) {
                KPoint newPoint = allPoints.get(i);
                Double oldDif = (double) ((vOld.getY() - old.getY()) / (vOld
                        .getX() - old.getX()));
                Double newDif = (double) ((old.getY() - newPoint.getY()) / (old
                        .getX() - newPoint.getX()));
                if (oldDif.equals(newDif)) {
                    removable.add(old);
                } else {
                    vOld = old;
                }
                old = newPoint;
            }
            for (KPoint rem : removable) {
                allPoints.remove(rem);
            }
        }
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
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * map of editor change listeners to all editors for which they have
     * registered.
     */
    private Map<IEditorChangeListener, List<Pair<DiagramEditor, ISelectionChangedListener>>> listenerMap = new HashMap<IEditorChangeListener, List<Pair<DiagramEditor, ISelectionChangedListener>>>();

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
            List<Pair<DiagramEditor, ISelectionChangedListener>> editorList = listenerMap
                    .get(listener);
            if (editorList == null) {
                editorList = new LinkedList<Pair<DiagramEditor, ISelectionChangedListener>>();
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
        List<Pair<DiagramEditor, ISelectionChangedListener>> editorList = listenerMap
                .remove(listener);
        if (editorList != null) {
            for (Pair<DiagramEditor, ISelectionChangedListener> pair : editorList) {
                pair.getFirst().getGraphicalViewer()
                        .removeSelectionChangedListener(pair.getSecond());
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ISelection getSelection(final IEditorPart editorPart) {
        if (editorPart instanceof DiagramEditor) {
            return ((DiagramEditor) editorPart).getGraphicalViewer()
                    .getSelection();
        }
        return null;
    }

    /**
     * Creates new edges and takes care of the labels for each connection
     * identified in the {@code buildLayoutGraphRecursively} method.
     * 
     * @param layoutConfig
     */
    private void processConnections(final GraphitiLayoutConfig layoutConfig) {

        for (Connection connection : connections) {
            KEdge edge = KimlUtil.createInitializedEdge();
            reference2EdgeMap.put(connection, edge);

            KNode sourceNode = null, targetNode = null;
            KPort sourcePort = null, targetPort = null;

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
                }
            }

            if (connection.getEnd().getReferencedGraphicsAlgorithm() == null) {
                if (pictElem2GraphElemMap.containsKey(connection.getEnd()
                        .getParent())) {
                    targetNode = (KNode) pictElem2GraphElemMap.get(connection
                            .getEnd().getParent());
                    edge.setTarget(targetNode);
                }
            } else {
                if (pictElem2GraphElemMap.containsKey(connection.getEnd())) {
                    targetPort = (KPort) pictElem2GraphElemMap.get(connection
                            .getEnd());
                    edge.setTargetPort(targetPort);
                    List<KEdge> targetPortEdges = targetPort.getEdges();
                    targetPortEdges.add(edge);
                    targetNode = targetPort.getNode();
                    edge.setTarget(targetNode);
                }
            }

            if (connection.getStart().getReferencedGraphicsAlgorithm() == null) {
                if (pictElem2GraphElemMap.containsKey(connection.getStart()
                        .getParent())) {
                    sourceNode = (KNode) pictElem2GraphElemMap.get(connection
                            .getStart().getParent());
                    edge.setSource(sourceNode);
                }
            } else {
                if (pictElem2GraphElemMap.containsKey(connection.getStart())) {
                    sourcePort = (KPort) pictElem2GraphElemMap.get(connection
                            .getStart());
                    edge.setSourcePort(sourcePort);
                    List<KEdge> sourcePortEdges = sourcePort.getEdges();
                    sourcePortEdges.add(edge);
                    sourceNode = sourcePort.getNode();
                    edge.setSource(sourceNode);
                }
            }

            KEdgeLayout edgeLayout = edge.getData(KEdgeLayout.class);

            KPoint sourcePoint = edgeLayout.getSourcePoint();
            Anchor start = connection.getStart();
            AnchorContainer startParent = start.getParent();

            KPoint targetPoint = edgeLayout.getTargetPoint();
            Anchor end = connection.getEnd();
            AnchorContainer endParent = end.getParent();

            boolean ignoreXY = false;
            if (start instanceof BoxRelativeAnchor) {
                // special rule for connections leaving a port towards the
                // inside of the node
                if (startParent.eContents().contains(endParent)) {
                    ignoreXY = true;
                }
            }

            calculateAnchorEnds(sourcePoint, start, ignoreXY);
            calculateAnchorEnds(targetPoint, end, false);

            // set user defined layout options for the edge
            layoutConfig.setFocus(diagramEditor
                    .getEditPartForPictogramElement(connection));
            edgeLayout.copyProperties(layoutConfig);

        }

    }

    /**
     * 
     * @param point
     * @param anchor
     * @param parentGa
     * @param ignoreXY
     */
    private void calculateAnchorEnds(final KPoint point, final Anchor anchor,
            final boolean ignoreXY) {
        if (anchor instanceof BoxRelativeAnchor) {
            BoxRelativeAnchor port = (BoxRelativeAnchor) anchor;
            KPort kPort = (KPort) pictElem2GraphElemMap.get(port);
            KNode kNode = kPort.getNode();
            KShapeLayout portLayout = kPort.getData(KShapeLayout.class);
            KShapeLayout nodeLayout = kNode.getData(KShapeLayout.class);
            float x = portLayout.getXpos()
                    + (ignoreXY ? 0 : nodeLayout.getXpos())
                    + portLayout.getWidth() / 2;
            float y = portLayout.getYpos()
                    + (ignoreXY ? 0 : nodeLayout.getYpos())
                    + portLayout.getHeight() / 2;
            point.setX(x);
            point.setY(y);
        } else if (anchor instanceof ChopboxAnchor) {
            KNode kNode = (KNode) pictElem2GraphElemMap.get(anchor.getParent());
            KShapeLayout nodeLayout = kNode.getData(KShapeLayout.class);
            point.setX(nodeLayout.getXpos() + nodeLayout.getWidth() / 2);
            point.setY(nodeLayout.getYpos() + nodeLayout.getHeight() / 2);
        }
        // TODO: FixPointAnchors
    }

    /**
     * Accumulate offsets from the connection start to the root canvas.
     * 
     * @param conn
     *            the connection
     * @return (x,y) - Offsets
     */
    private Pair<Integer, Integer> calcCoordOffset(final Connection conn) {
        Pair<Integer, Integer> result = new Pair<Integer, Integer>(0, 0);
        Anchor start = conn.getStart();
        AnchorContainer container = start.getParent();
        EObject parent = container.eContainer();
        while (parent != null && parent instanceof ContainerShape) {
            ContainerShape cs = (ContainerShape) parent;
            GraphicsAlgorithm ga = cs.getGraphicsAlgorithm();
            result.setFirst(result.getFirst() + ga.getX());
            result.setSecond(result.getSecond() + ga.getY());
            parent = parent.eContainer();
        }
        return result;
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
            EditPart part = getEditPartFromDiagramEditorInternal2(editPart);
            if (part != null) {
                config.setFocus(part);
            }
        }
        return config;
    }

}
