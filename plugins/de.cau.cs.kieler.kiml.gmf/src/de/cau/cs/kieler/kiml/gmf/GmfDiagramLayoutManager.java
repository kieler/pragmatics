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
package de.cau.cs.kieler.kiml.gmf;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionLocator;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gmf.runtime.diagram.ui.editparts.AbstractBorderItemEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.CompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramRootEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.LabelEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeCompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.figures.ResizableCompartmentFigure;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramWorkbenchPart;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.ui.IEditorPart;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KInsets;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutDataFactory;
import de.cau.cs.kieler.kiml.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.EdgeLabelPlacement;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortConstraints;
import de.cau.cs.kieler.kiml.ui.IEditorChangeListener;
import de.cau.cs.kieler.kiml.ui.KimlUiPlugin;
import de.cau.cs.kieler.kiml.ui.layout.ApplyLayoutRequest;
import de.cau.cs.kieler.kiml.ui.layout.DiagramLayoutManager;
import de.cau.cs.kieler.kiml.ui.layout.ICachedLayout;
import de.cau.cs.kieler.kiml.ui.layout.ILayoutInspector;
import de.cau.cs.kieler.kiml.ui.util.KimlUiUtil;
import de.cau.cs.kieler.kiml.util.KimlLayoutUtil;

/**
 * Diagram layout manager that is able to generically layout diagrams generated
 * by GMF. The internal KGraph graph structure is built from the structure of
 * edit parts in the diagram. The new layout is applied to the diagram using
 * {@link GmfLayoutEditPolicy}, which creates a {@link GmfLayoutCommand} to
 * directly manipulate data in the GMF notation model, where layout information
 * is stored persistently.
 * 
 * @kieler.rating 2009-12-11 proposed yellow msp
 * @author ars
 * @author msp
 */
public class GmfDiagramLayoutManager extends DiagramLayoutManager {

    /** map of layout graph elements to edit parts. */
    private Map<KGraphElement, IGraphicalEditPart> graphElem2EditPartMap
            = new LinkedHashMap<KGraphElement, IGraphicalEditPart>();
    /** map of edit parts to layout graph elements. */
    private Map<IGraphicalEditPart, KGraphElement> editPart2GraphElemMap
            = new HashMap<IGraphicalEditPart, KGraphElement>();
    /** list of connection edit parts that were found in the diagram. */
    private LinkedList<ConnectionEditPart> connections = new LinkedList<ConnectionEditPart>();
    /** editor part of the currently layouted diagram. */
    private DiagramEditor diagramEditorPart;
    /** diagram edit part of the currently layouted diagram. */
    private DiagramEditPart diagramEditPart;
    /** root of the currently layouted selection. */
    private IGraphicalEditPart layoutRootPart;
    /** target edit part that is layouted recursively. */
    private IGraphicalEditPart ancestryTargetPart;
    /** the last created layout graph. */
    private KNode layoutGraph;
    /** target layout node that is layouted recursively. */
    private KNode ancestryTargetNode;
    /** the cached layout result. */
    private GmfCachedLayout cachedLayout;
    /** the command that applies the transferred layout to the diagram. */
    private Command applyLayoutCommand;

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
        return editPart instanceof IGraphicalEditPart;
    }

    /** map of editor change listeners to all editors for which they have registered. */
    private Map<IEditorChangeListener, List<Pair<DiagramEditor, ISelectionChangedListener>>> listenerMap
            = new HashMap<IEditorChangeListener, List<Pair<DiagramEditor, ISelectionChangedListener>>>();
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void addChangeListener(final IEditorPart editorPart,
            final IEditorChangeListener listener) {
        if (editorPart instanceof DiagramEditor) {
            final DiagramEditor diagramEditor = (DiagramEditor) editorPart;
            diagramEditor.getDiagramGraphicalViewer().addSelectionChangedListener(listener);
            List<Pair<DiagramEditor, ISelectionChangedListener>> editorList
                    = listenerMap.get(listener);
            if (editorList == null) {
                editorList = new LinkedList<Pair<DiagramEditor, ISelectionChangedListener>>();
                listenerMap.put(listener, editorList);
            }
            editorList.add(new Pair<DiagramEditor, ISelectionChangedListener>(
                    diagramEditor, listener));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeChangeListener(final IEditorChangeListener listener) {
        List<Pair<DiagramEditor, ISelectionChangedListener>> editorList
                = listenerMap.remove(listener);
        if (editorList != null) {
            for (Pair<DiagramEditor, ISelectionChangedListener> pair : editorList) {
                pair.getFirst().getDiagramGraphicalViewer()
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
            return ((DiagramEditor) editorPart).getDiagramGraphicalViewer().getSelection();
        } else {
            return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ILayoutInspector getInspector(final EditPart editPart) {
        if (editPart instanceof IGraphicalEditPart) {
            return new GmfLayoutInspector((IGraphicalEditPart) editPart);
        } else if (editPart instanceof DiagramRootEditPart) {
            return new GmfLayoutInspector((IGraphicalEditPart)
                    ((DiagramRootEditPart) editPart).getContents());
        } else {
            return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public KNode buildLayoutGraph(final IEditorPart editorPart, final EditPart editPart,
            final boolean layoutAncestors) {
        graphElem2EditPartMap.clear();
        editPart2GraphElemMap.clear();
        connections.clear();

        // get the diagram editor part
        if (editorPart instanceof DiagramEditor) {
            diagramEditorPart = (DiagramEditor) editorPart;
        } else {
            diagramEditorPart = null;
        }

        // choose the layout root edit part
        layoutRootPart = null;
        ancestryTargetNode = null;
        cachedLayout = null;
        if (layoutAncestors && editPart instanceof IGraphicalEditPart) {
            ancestryTargetPart = (IGraphicalEditPart) editPart;
        } else {
            ancestryTargetPart = null;
            if (editPart instanceof ShapeNodeEditPart || editPart instanceof DiagramEditPart) {
                layoutRootPart = (IGraphicalEditPart) editPart;
            } else if (editPart instanceof IGraphicalEditPart) {
                EditPart tgEditPart = ((IGraphicalEditPart) editPart).getTopGraphicEditPart();
                if (tgEditPart instanceof ShapeNodeEditPart) {
                    layoutRootPart = (IGraphicalEditPart) tgEditPart;
                }
            }
        }
        if (layoutRootPart == null && diagramEditorPart != null) {
            layoutRootPart = diagramEditorPart.getDiagramEditPart();
        }
        if (layoutRootPart == null) {
            throw new UnsupportedOperationException("Not supported by this layout manager: Editor "
                    + editorPart + ", Edit part " + editPart);
        }

        // find the diagram edit part
        diagramEditPart = GmfLayoutInspector.getDiagramEditPart(layoutRootPart);

        layoutGraph = doBuildLayoutGraph();
        return layoutGraph;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void transferLayout(final boolean cacheLayout) {
        // create a new request to change the layout
        ApplyLayoutRequest applyLayoutRequest = new ApplyLayoutRequest();
        for (Entry<KGraphElement, IGraphicalEditPart> entry : graphElem2EditPartMap.entrySet()) {
            applyLayoutRequest.addElement(entry.getKey(), entry.getValue());
        }

        // retrieve a command for the request; the command is created by GmfLayoutEditPolicy
        applyLayoutCommand = diagramEditPart.getCommand(applyLayoutRequest);
        
        // store the layout data into a cache
        if (cacheLayout) {
            cachedLayout = new GmfCachedLayout(graphElem2EditPartMap.size());
            for (Entry<KGraphElement, IGraphicalEditPart> entry : graphElem2EditPartMap.entrySet()) {
                cachedLayout.addLayout(entry.getValue(), entry.getKey());
            }
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void applyLayout() {
        // get a command stack to execute the command
        CommandStack commandStack = null;
        if (diagramEditorPart != null) {
            Object adapter = diagramEditorPart.getAdapter(CommandStack.class);
            if (adapter instanceof CommandStack) {
                commandStack = (CommandStack) adapter;
            }
        }
        if (commandStack == null) {
            commandStack = layoutRootPart.getDiagramEditDomain().getDiagramCommandStack();
        }
        
        // execute the command
        commandStack.execute(applyLayoutCommand);
        // refresh the labels in the diagram
        
        // FIXME this workaround should be eliminated
        refreshDiagram(diagramEditorPart, layoutRootPart);
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
        return cachedLayout;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EditPart getCurrentEditPart() {
        return layoutRootPart;
    }

    /**
     * Builds the layout graph for the given root edit part.
     * 
     * @return layout graph layout graph that represents the structure contained
     *         in the root edit part
     */
    private KNode doBuildLayoutGraph() {
        KNode topNode = KimlLayoutUtil.createInitializedNode();
        KShapeLayout shapeLayout = KimlLayoutUtil.getShapeLayout(topNode);
        Rectangle rootBounds = layoutRootPart.getFigure().getBounds();
        // start with the whole diagram as root for layout
        if (layoutRootPart instanceof DiagramEditPart) {
            topNode.getLabel()
                    .setText(((DiagramEditPart) layoutRootPart).getDiagramView().getName());
            // start with a specific node as root for layout
        } else {
            shapeLayout.setXpos(rootBounds.x);
            shapeLayout.setYpos(rootBounds.y);
        }

        shapeLayout.setHeight(rootBounds.height);
        shapeLayout.setWidth(rootBounds.width);
        editPart2GraphElemMap.put(layoutRootPart, topNode);
        graphElem2EditPartMap.put(topNode, layoutRootPart);
        // traverse the children of the layout root part
        buildLayoutGraphRecursively(layoutRootPart, topNode, layoutRootPart);
        // set user defined layout options for the diagram
        GmfLayoutInspector.setLayoutOptions(layoutRootPart, shapeLayout, true);
        // transform all connections in the selected area
        processConnections();

        if (ancestryTargetNode != null) {
            cleanupAncestryPath();
            return ancestryTargetNode;
        } else {
            return topNode;
        }
    }

    /**
     * Recursively builds a layout graph by analyzing the children of the given
     * edit part.
     * 
     * @param parentEditPart the parent edit part of the current elements
     * @param parentLayoutNode the corresponding KNode
     * @param currentEditPart the currently analyzed edit part
     */
    private void buildLayoutGraphRecursively(final IGraphicalEditPart parentEditPart,
            final KNode parentLayoutNode, final IGraphicalEditPart currentEditPart) {
        boolean hasChildNodes = false, hasChildCompartments = false,
                hasPorts = false, isCollapsed = false;
        KInsets insets = null;
        IFigure parentFigure = parentEditPart.getFigure();
        
        // set the target of layout ancestry if it was found
        if (ancestryTargetNode == null && ancestryTargetPart == currentEditPart) {
            ancestryTargetNode = parentLayoutNode;
        }
        
        // iterate through the children of the element
        for (Object obj : currentEditPart.getChildren()) {

            // check visibility of the child
            if (obj instanceof IGraphicalEditPart) {
                IFigure figure = ((IGraphicalEditPart) obj).getFigure();
                if (!figure.isVisible()) {
                    continue;
                }
            }

            // process ports (border items)
            if (obj instanceof AbstractBorderItemEditPart) {
                AbstractBorderItemEditPart borderItem = (AbstractBorderItemEditPart) obj;
                KPort port = KimlLayoutUtil.createInitializedPort();
                graphElem2EditPartMap.put(port, borderItem);
                editPart2GraphElemMap.put(borderItem, port);
                port.setNode(parentLayoutNode);
                
                // set the port's layout, relative to the node position
                KShapeLayout portLayout = KimlLayoutUtil.getShapeLayout(port);
                Rectangle portBounds = KimlUiUtil.getAbsoluteBounds(borderItem.getFigure());
                Rectangle nodeBounds = KimlUiUtil.getAbsoluteBounds(currentEditPart.getFigure());
                portLayout.setXpos(portBounds.x - nodeBounds.x);
                portLayout.setYpos(portBounds.y - nodeBounds.y);
                portLayout.setWidth(portBounds.width);
                portLayout.setHeight(portBounds.height);
                hasPorts = true;
                // set user defined layout options for the port
                GmfLayoutInspector.setLayoutOptions(borderItem, portLayout, true);

                // store all the connections to process them later
                addConnections(borderItem);

                // set the port label
                for (Object portChildObj : borderItem.getChildren()) {
                    if (portChildObj instanceof IGraphicalEditPart) {
                        IFigure labelFigure = ((IGraphicalEditPart) portChildObj).getFigure();
                        String text = null;
                        if (labelFigure instanceof WrappingLabel) {
                            text = ((WrappingLabel) labelFigure).getText();
                        } else if (labelFigure instanceof Label) {
                            text = ((Label) labelFigure).getText();
                        }
                        if (text != null) {
                            KLabel portLabel = port.getLabel();
                            portLabel.setText(text);
                            // set the port label's layout
                            KShapeLayout labelLayout = KimlLayoutUtil.getShapeLayout(portLabel);
                            Rectangle labelBounds = KimlUiUtil.getAbsoluteBounds(labelFigure);
                            labelLayout.setXpos(labelBounds.x - portBounds.x);
                            labelLayout.setYpos(labelBounds.y - portBounds.y);
                            labelLayout.setWidth(labelFigure.getPreferredSize().width);
                            labelLayout.setHeight(labelFigure.getPreferredSize().height);
                        }
                    }
                }

            // process compartments, which may contain other elements
            } else if (obj instanceof ShapeCompartmentEditPart
                    && ((CompartmentEditPart) obj).getChildren().size() > 0) {
                CompartmentEditPart compartment = (CompartmentEditPart) obj;
                if (!KimlUiUtil.isNoLayout(compartment)) {
                    IFigure compartmentFigure = compartment.getFigure();
                    if (compartmentFigure instanceof ResizableCompartmentFigure) {
                        ResizableCompartmentFigure resizableCompartmentFigure
                                = (ResizableCompartmentFigure) compartmentFigure;
                        // check whether the compartment is collapsed
                        if (!resizableCompartmentFigure.isExpanded()) {
                            isCollapsed = true;
                            continue;
                        }
                    }

                    hasChildCompartments = true;
                    buildLayoutGraphRecursively(parentEditPart, parentLayoutNode, compartment);
                }

            // process nodes, which may be parents of compartments
            } else if (obj instanceof ShapeNodeEditPart) {
                ShapeNodeEditPart childNodeEditPart = (ShapeNodeEditPart) obj;
                if (!KimlUiUtil.isNoLayout(childNodeEditPart)) {
                    IFigure nodeFigure = childNodeEditPart.getFigure();
                    KNode childLayoutNode = KimlLayoutUtil.createInitializedNode();
    
                    // store all the connections to process them later
                    addConnections(childNodeEditPart);
    
                    // set location and size
                    Rectangle childBounds = KimlUiUtil.getAbsoluteBounds(nodeFigure);
                    Rectangle containerBounds = KimlUiUtil.getAbsoluteBounds(nodeFigure.getParent());
                    KShapeLayout nodeLayout = KimlLayoutUtil.getShapeLayout(childLayoutNode);
                    nodeLayout.setXpos(childBounds.x - containerBounds.x);
                    nodeLayout.setYpos(childBounds.y - containerBounds.y);
                    nodeLayout.setHeight(childBounds.height);
                    nodeLayout.setWidth(childBounds.width);
                    Dimension minSize = nodeFigure.getMinimumSize();
                    LayoutOptions.setFloat(nodeLayout, LayoutOptions.MIN_WIDTH, minSize.width);
                    LayoutOptions.setFloat(nodeLayout, LayoutOptions.MIN_HEIGHT, minSize.height);
                    
                    // set insets if not yet defined
                    if (insets == null) {
                        Rectangle parentBounds = KimlUiUtil.getAbsoluteBounds(parentFigure);
                        insets = LayoutOptions.getObject(KimlLayoutUtil.getShapeLayout(
                                parentLayoutNode), KInsets.class);
                        insets.setLeft(containerBounds.x - parentBounds.x);
                        insets.setTop(containerBounds.y - parentBounds.y);
                        insets.setRight(insets.getLeft());
                        insets.setBottom(insets.getLeft());
                    }
    
                    parentLayoutNode.getChildren().add(childLayoutNode);
                    editPart2GraphElemMap.put(childNodeEditPart, childLayoutNode);
                    graphElem2EditPartMap.put(childLayoutNode, childNodeEditPart);
                    hasChildNodes = true;
                    // process the child as new current edit part
                    buildLayoutGraphRecursively(childNodeEditPart, childLayoutNode,
                            childNodeEditPart);
    
                    // set user defined layout options for the node
                    GmfLayoutInspector.setLayoutOptions(childNodeEditPart, nodeLayout, true);
                }

            // process labels of nodes
            } else if (obj instanceof IGraphicalEditPart) {

                IGraphicalEditPart graphicalEditPart = (IGraphicalEditPart) obj;
                IFigure labelFigure = graphicalEditPart.getFigure();
                String text = null;
                Font font = null;
                if (labelFigure instanceof WrappingLabel) {
                    WrappingLabel wrappingLabel = (WrappingLabel) labelFigure;
                    text = wrappingLabel.getText();
                    font = wrappingLabel.getFont();
                } else if (labelFigure instanceof Label) {
                    Label label = (Label) labelFigure;
                    text = label.getText();
                    font = label.getFont();
                }
                if (text != null) {
                    KNode parent = (KNode) editPart2GraphElemMap.get(graphicalEditPart.getParent());
                    KLabel label = parent.getLabel();
                    if (label.getText() == null || label.getText().length() == 0) {
                        label.setText(text);
                        KShapeLayout labelLayout = KimlLayoutUtil.getShapeLayout(label);
                        labelLayout.setWidth(labelFigure.getPreferredSize().width);
                        labelLayout.setHeight(labelFigure.getPreferredSize().height);
                        LayoutOptions.setString(labelLayout, LayoutOptions.FONT_NAME,
                                font.getFontData()[0].getName());
                        LayoutOptions.setInt(labelLayout, LayoutOptions.FONT_SIZE,
                                font.getFontData()[0].getHeight());
                    }
                }
            }
        }

        KShapeLayout nodeLayout = KimlLayoutUtil.getShapeLayout(parentLayoutNode);
        // set default fixed size option
        if (!hasChildNodes && !hasChildCompartments && !isCollapsed) {
            LayoutOptions.setBoolean(nodeLayout, LayoutOptions.FIXED_SIZE, true);
        }
        // set default port constraints option
        if (hasPorts) {
            if (hasChildNodes || hasChildCompartments) {
                LayoutOptions.setEnum(nodeLayout, PortConstraints.FREE);
            } else {
                LayoutOptions.setEnum(nodeLayout, PortConstraints.FIXED_POS);
            }
        }
    }
    
    /**
     * Adds all target connections and connected connections to the list of connections
     * that must be processed later.
     * 
     * @param editPart an edit part
     */
    private void addConnections(final IGraphicalEditPart editPart) {
        for (Object targetConn : editPart.getTargetConnections()) {
            if (targetConn instanceof ConnectionEditPart) {
                ConnectionEditPart connectionEditPart = (ConnectionEditPart) targetConn;
                connections.add(connectionEditPart);
                addConnections(connectionEditPart);
            }
        }
    }

    /**
     * Creates new edges and takes care of the labels for each connection
     * identified in the {@code buildLayoutGraphRecursively} method.
     */
    private void processConnections() {
        Map<EReference, KEdge> reference2EdgeMap = new HashMap<EReference, KEdge>();
        for (ConnectionEditPart connection : connections) {
            boolean isOppositeEdge = false;
            EdgeLabelPlacement edgeLabelPlacement = EdgeLabelPlacement.UNDEFINED;
            KEdge edge;
            
            // check whether the edge belongs to an Ecore reference, which may have opposites
            EObject modelObject = connection.getNotationView().getElement();
            if (modelObject instanceof EReference) {
                EReference reference = (EReference) modelObject;
                edge = reference2EdgeMap.get(reference.getEOpposite());
                if (edge != null) {
                    edgeLabelPlacement = EdgeLabelPlacement.TAIL;
                    isOppositeEdge = true;
                } else {
                    edge = KimlLayoutUtil.createInitializedEdge();
                    reference2EdgeMap.put(reference, edge);
                }
            } else {
                edge = KimlLayoutUtil.createInitializedEdge();
            }
            
            KNode sourceNode, targetNode;
            KPort sourcePort = null, targetPort = null;

            // find a proper source node and source port
            EditPart sourceObj = connection.getSource();
            if (sourceObj instanceof AbstractBorderItemEditPart) {
                sourcePort = (KPort) editPart2GraphElemMap.get(sourceObj);
                if (sourcePort == null) {
                    continue;
                }
                edge.setSourcePort(sourcePort);
                sourcePort.getEdges().add(edge);
                sourceNode = sourcePort.getNode();
            } else if (sourceObj instanceof ConnectionEditPart) {
                KGraphElement connElem = editPart2GraphElemMap.get(
                        ((ConnectionEditPart) sourceObj).getSource());
                if (!(connElem instanceof KNode)) {
                    connElem = editPart2GraphElemMap.get(
                            ((ConnectionEditPart) sourceObj).getTarget());
                }
                if (!(connElem instanceof KNode)) {
                    continue;
                }
                sourceNode = (KNode) connElem;
            } else {
                sourceNode = (KNode) editPart2GraphElemMap.get(sourceObj);
                if (sourceNode == null) {
                    continue;
                }
            }
            
            // calculate offset for edge and label coordinates
            IGraphicalEditPart sourceParent = graphElem2EditPartMap.get(sourceNode.getParent());
            Rectangle sourceParentBounds = KimlUiUtil.getAbsoluteBounds(sourceParent.getFigure());
            KInsets insets = LayoutOptions.getObject(KimlLayoutUtil.getShapeLayout(
                    sourceNode.getParent()), KInsets.class);
            float offsetx = sourceParentBounds.x + insets.getLeft();
            float offsety = sourceParentBounds.y + insets.getTop();

            if (!isOppositeEdge) {
                // find a proper target node and target port
                EditPart targetObj = connection.getTarget();
                if (targetObj instanceof AbstractBorderItemEditPart) {
                    targetPort = (KPort) editPart2GraphElemMap.get(targetObj);
                    if (targetPort == null) {
                        continue;
                    }
                    edge.setTargetPort(targetPort);
                    targetPort.getEdges().add(edge);
                    targetNode = targetPort.getNode();
                } else if (targetObj instanceof ConnectionEditPart) {
                    KGraphElement connElem = editPart2GraphElemMap.get(
                            ((ConnectionEditPart) targetObj).getTarget());
                    if (!(connElem instanceof KNode)) {
                        connElem = editPart2GraphElemMap.get(
                                ((ConnectionEditPart) targetObj).getSource());
                    }
                    if (!(connElem instanceof KNode)) {
                        continue;
                    }
                    targetNode = (KNode) connElem;
                } else {
                    targetNode = (KNode) editPart2GraphElemMap.get(targetObj);
                    if (targetNode == null) {
                        continue;
                    }
                }
    
                edge.setSource(sourceNode);
                edge.setTarget(targetNode);
                graphElem2EditPartMap.put(edge, connection);
                editPart2GraphElemMap.put(connection, edge);
    
                // store the current coordinates of the edge
                KEdgeLayout edgeLayout = KimlLayoutUtil.getEdgeLayout(edge);
                Connection figure = connection.getConnectionFigure();
                PointList pointList = figure.getPoints();
                KPoint sourcePoint = edgeLayout.getSourcePoint();
                Point firstPoint = KimlUiUtil.getAbsolutePoint(figure, 0);
                sourcePoint.setX(firstPoint.x - offsetx);
                sourcePoint.setY(firstPoint.y - offsety);
                for (int i = 1; i < pointList.size() - 1; i++) {
                    Point point = KimlUiUtil.getAbsolutePoint(figure, i);
                    KPoint kpoint = KLayoutDataFactory.eINSTANCE.createKPoint();
                    kpoint.setX(point.x - offsetx);
                    kpoint.setY(point.y - offsety);
                    edgeLayout.getBendPoints().add(kpoint);
                }
                KPoint targetPoint = edgeLayout.getTargetPoint();
                Point lastPoint = KimlUiUtil.getAbsolutePoint(figure, pointList.size() - 1);
                targetPoint.setX(lastPoint.x - offsetx);
                targetPoint.setY(lastPoint.y - offsety);
    
                // set user defined layout options for the edge
                GmfLayoutInspector.setLayoutOptions(connection, edgeLayout, true);
            }

            // process edge labels
            processLabels(connection, edge, edgeLabelPlacement, offsetx, offsety);
        }
    }
    
    /**
     * Process the labels of an edge.
     * 
     * @param connection the connection edit part
     * @param edge the layout edge
     * @param placement predefined placement for all labels, or {@code UNDEFINED}
     *     if the placement shall be derived from the edit part
     * @param offsetx the offset for horizontal coordinates
     * @param offsety the offset for vertical coordinates
     */
    private void processLabels(final ConnectionEditPart connection, final KEdge edge,
            final EdgeLabelPlacement placement, final float offsetx, final float offsety) {
        /* ars: source and target is exchanged when defining it in the
         * gmfgen file. So if Emma sets a label to be placed as target on a
         * connection, then the label will show up next to the source node
         * in the diagram editor. So correct it here, very ugly.
         */
        for (Object obj : connection.getChildren()) {
            if (obj instanceof LabelEditPart) {
                LabelEditPart labelEditPart = (LabelEditPart) obj;
                IFigure labelFigure = labelEditPart.getFigure();
                Rectangle labelBounds = KimlUiUtil.getAbsoluteBounds(labelFigure);
                String labelText = null;
                Font font = null;
                Dimension iconBounds = null;
                if (labelFigure instanceof WrappingLabel) {
                    WrappingLabel wrappingLabel = (WrappingLabel) labelFigure;
                    labelText = wrappingLabel.getText();
                    if (wrappingLabel.getIcon() != null) {
                        iconBounds = new Dimension();
                        iconBounds.width = wrappingLabel.getIcon().getBounds().width
                            + wrappingLabel.getIconTextGap();
                        iconBounds.height = wrappingLabel.getIcon().getBounds().height;
                        labelText = "O " + labelText;
                    }
                    font = wrappingLabel.getFont();
                } else if (labelFigure instanceof Label) {
                    Label label = (Label) labelFigure;
                    labelText = label.getText();
                    if (label.getIcon() != null) {
                        iconBounds = label.getIconBounds().getSize();
                        iconBounds.width += label.getIconTextGap();
                        labelText = "O " + labelText;
                    }
                    font = label.getFont();
                }
                if (labelText != null) {
                    if (labelText.length() == 0) {
                        labelText = ".";
                    }
                    KLabel label = KimlLayoutUtil.createInitializedLabel(edge);
                    KShapeLayout labelLayout = KimlLayoutUtil.getShapeLayout(label);
                    if (placement == EdgeLabelPlacement.UNDEFINED) {
                        switch (labelEditPart.getKeyPoint()) {
                        case ConnectionLocator.SOURCE:
                            LayoutOptions.setEnum(labelLayout, EdgeLabelPlacement.HEAD);
                            break;
                        case ConnectionLocator.MIDDLE:
                            LayoutOptions.setEnum(labelLayout, EdgeLabelPlacement.CENTER);
                            break;
                        case ConnectionLocator.TARGET:
                            LayoutOptions.setEnum(labelLayout, EdgeLabelPlacement.TAIL);
                            break;
                        }
                    } else {
                        LayoutOptions.setEnum(labelLayout, placement);
                    }
                    LayoutOptions.setString(labelLayout, LayoutOptions.FONT_NAME,
                            font.getFontData()[0].getName());
                    LayoutOptions.setInt(labelLayout, LayoutOptions.FONT_SIZE,
                            font.getFontData()[0].getHeight());
                    labelLayout.setXpos(labelBounds.x - offsetx);
                    labelLayout.setYpos(labelBounds.y - offsety);
                    if (iconBounds != null) {
                        labelLayout.setWidth(labelBounds.width + iconBounds.width);
                    } else {
                        labelLayout.setWidth(labelBounds.width);
                    }
                    labelLayout.setHeight(labelBounds.height);
                    label.setText(labelText);
                    edge.getLabels().add(label);
                    graphElem2EditPartMap.put(label, labelEditPart);
                }
            }
        }
    }
    
    /**
     * Cleans the path from the ancestry target node to the root node, including all parallel paths.
     */
    private void cleanupAncestryPath() {
        KNode previousNode = ancestryTargetNode;
        KNode parent = ancestryTargetNode.getParent();
        while (parent != null) {
            for (KNode child : parent.getChildren()) {
                if (child != previousNode) {
                    KShapeLayout childLayout = KimlLayoutUtil.getShapeLayout(child);
                    LayoutOptions.setBoolean(childLayout, LayoutOptions.FIXED_SIZE, true);
                    removeFromLayout(child);
                }
            }
            previousNode = parent;
            parent = parent.getParent();
        }
    }
    
    /**
     * Removes the given node and all its children from layout.
     * 
     * @param node a layout node
     */
    private void removeFromLayout(final KNode node) {
        for (KNode child : node.getChildren()) {
            graphElem2EditPartMap.remove(child);
            for (KPort port : child.getPorts()) {
                graphElem2EditPartMap.remove(port);
            }
            for (KEdge edge : child.getOutgoingEdges()) {
                graphElem2EditPartMap.remove(edge);
                for (KLabel edgeLabel : edge.getLabels()) {
                    graphElem2EditPartMap.remove(edgeLabel);
                }
            }
            removeFromLayout(child);
        }
    }

    /**
     * Refreshes all LabelEditParts in the diagram. This is necessary in order
     * to prevent Transition labels from vanishing.
     * 
     * author: soh FIXME: Someone should look into this. What causes transition
     * labels to fly out of alignment.
     * 
     * @param editor
     *            the editor
     * @param editPart
     *            the root edit part
     */
    private static void refreshDiagram(final IEditorPart editor,
            final EditPart editPart) {
        Job worker = new Job("Diagram refresh") {

            @Override
            protected IStatus run(final IProgressMonitor monitor) {
                if (editor instanceof IDiagramWorkbenchPart) {
                    EditPart part = editPart;

                    if (part == null) {
                        part = ((IDiagramWorkbenchPart) editor)
                                .getDiagramEditPart();
                    }

                    @SuppressWarnings("unchecked")
                    Collection<EditPart> editParts = new ArrayList<EditPart>(
                            part.getViewer().getEditPartRegistry().values());

                    for (EditPart obj : editParts) {
                        if (obj instanceof LabelEditPart) {
                            ((LabelEditPart) obj).refresh();
                        }
                    }
                }
                return new Status(Status.OK, KimlUiPlugin.PLUGIN_ID, "Refresh done");
            }
        };
        worker.schedule(1000);
    }

}
