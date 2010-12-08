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
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.graphiti.features.context.impl.LayoutContext;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.styles.Point;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
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
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.kiml.ILayoutConfig;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutDataFactory;
import de.cau.cs.kieler.kiml.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
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

    /** map of Connection to Kedge. */
    private Map<Connection, KEdge> reference2EdgeMap = new HashMap<Connection, KEdge>();
    /** map of PictogramElement and its coordinates. */
    private Map<PictogramElement, int[]> graphElemHeightWidth = new HashMap<PictogramElement, int[]>();

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
                    graphElemHeightWidth.put(element, new int[] {
                            element.getGraphicsAlgorithm().getHeight(),
                            element.getGraphicsAlgorithm().getWidth() });
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
     * @return returns true if current pictogram element has further children
     */
    private boolean buildLayoutGraphRecursively(
            final PictogramElement currentElement, final KNode topNode,
            final GraphitiLayoutConfig layoutConfig) {
        EList<Shape> list = null;
        boolean returnstate = true;

        if (topNode != null) {
            if (currentElement instanceof Diagram) {
                list = ((Diagram) currentElement).getChildren();
            } else if (currentElement instanceof ContainerShape) {
                list = ((ContainerShape) currentElement).getChildren();
            }

            for (Shape shape : list) {
                if (shape instanceof ContainerShape) {
                    ContainerShape cs = (ContainerShape) shape;
                    GraphicsAlgorithm containerGa = cs.getGraphicsAlgorithm();

                    KNode childnode = KimlUtil.createInitializedNode();
                    returnstate = false;

                    childnode.setParent(topNode);
                    KShapeLayout shapeLayout = childnode
                            .getData(KShapeLayout.class);
                    shapeLayout.setXpos(containerGa.getX());
                    shapeLayout.setYpos(containerGa.getY());
                    shapeLayout.setHeight(containerGa.getHeight());
                    shapeLayout.setWidth(containerGa.getWidth());

                    // TODO how to get minimal size of the shape
                    shapeLayout.setProperty(LayoutOptions.MIN_WIDTH, 20.0f);
                    shapeLayout.setProperty(LayoutOptions.MIN_HEIGHT, 20.0f);

                    // set user defined layout options
                    layoutConfig.setFocus(diagramEditor
                            .getEditPartForPictogramElement(cs));
                    shapeLayout.copyProperties(layoutConfig);

                    pictElem2GraphElemMap.put(cs, childnode);
                    graphElemHeightWidth.put(
                            cs,
                            new int[] { containerGa.getHeight(),
                                    containerGa.getWidth() });
                    boolean state = buildLayoutGraphRecursively(cs, childnode,
                            layoutConfig);

                    shapeLayout.setProperty(LayoutOptions.FIXED_SIZE, state);
                    // }
                    if (cs.getAnchors().size() != 0) {
                        EList<Anchor> childAnchors = cs.getAnchors();
                        for (Anchor anchor : childAnchors) {
                            if (anchor.getReferencedGraphicsAlgorithm() != null) {
                                KPort port = KimlUtil.createInitializedPort();
                                pictElem2GraphElemMap.put(anchor, port);
                                port.setNode(topNode);
                                KShapeLayout portLayout = port
                                        .getData(KShapeLayout.class);
                                portLayout.setXpos(anchor
                                        .getGraphicsAlgorithm().getX());
                                portLayout.setYpos(anchor
                                        .getGraphicsAlgorithm().getY());
                                portLayout.setWidth(anchor
                                        .getGraphicsAlgorithm().getWidth());
                                portLayout.setHeight(anchor
                                        .getGraphicsAlgorithm().getHeight());
                                EList<Connection> conn = anchor
                                        .getOutgoingConnections();
                                for (Connection connection : conn) {
                                    connections.add(connection);

                                }
                            } else {
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
                GraphitiScrollingGraphicalViewer graphitiViewer = (GraphitiScrollingGraphicalViewer) viewer;
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
                kShape2Diag();
                kEdge2Conn();
            }
        }, editingDomain, "Automatic Layout");
    }

    /**
     * Replaces the new coordinates calculated by the layout algorithm back to
     * the pictogram element.
     */
    private void kShape2Diag() {
        for (Map.Entry<PictogramElement, KGraphElement> entry : pictElem2GraphElemMap
                .entrySet()) {
            PictogramElement pelem = entry.getKey();
            KGraphElement kelem = entry.getValue();
            KShapeLayout shapeLayout = kelem.getData(KShapeLayout.class);
            pelem.getGraphicsAlgorithm().setX((int) shapeLayout.getXpos());
            pelem.getGraphicsAlgorithm().setY((int) shapeLayout.getYpos());
            pelem.getGraphicsAlgorithm().setHeight(
                    (int) shapeLayout.getHeight());
            pelem.getGraphicsAlgorithm().setWidth((int) shapeLayout.getWidth());

            if (pelem instanceof ContainerShape || pelem instanceof Diagram) {
                if (graphElemHeightWidth.containsKey(pelem)) {
                    int[] olddimension = graphElemHeightWidth.get(pelem);
                    if (olddimension[0] != pelem.getGraphicsAlgorithm()
                            .getHeight()
                            || olddimension[1] != pelem.getGraphicsAlgorithm()
                                    .getWidth()) {

                        diagramEditor.getDiagramTypeProvider()
                                .getFeatureProvider()
                                .layoutIfPossible(new LayoutContext(pelem));

                    }
                }
            }
        }
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
            for (KPoint pnt : points) {
                Point point = Graphiti.getGaService().createPoint(
                        (int) pnt.getX(), (int) pnt.getY());
                pointList.add(point);
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
                    edge.setSource(sourceNode);
                }
            }

            KEdgeLayout edgeLayout = edge.getData(KEdgeLayout.class);

            EList<Point> pointList = ((FreeFormConnection) connection)
                    .getBendpoints();

            KPoint sourcePoint = edgeLayout.getSourcePoint();
            GraphicsAlgorithm ga = connection.getStart().getParent()
                    .getGraphicsAlgorithm();
            sourcePoint.setX(ga.getX() + ga.getWidth() / 2);
            sourcePoint.setY(ga.getY() + ga.getHeight() / 2);

            KPoint targetPoint = edgeLayout.getTargetPoint();
            ga = connection.getEnd().getParent().getGraphicsAlgorithm();
            targetPoint.setX(ga.getX() + ga.getWidth() / 2);
            targetPoint.setY(ga.getY() + ga.getHeight() / 2);

            for (Point point : pointList) {

                KPoint kpoint = KLayoutDataFactory.eINSTANCE.createKPoint();
                kpoint.setX(point.getX());
                kpoint.setY(point.getY());
                edgeLayout.getBendPoints().add(kpoint);
            }

            // set user defined layout options for the edge
            layoutConfig.setFocus(diagramEditor
                    .getEditPartForPictogramElement(connection));
            edgeLayout.copyProperties(layoutConfig);

        }

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
