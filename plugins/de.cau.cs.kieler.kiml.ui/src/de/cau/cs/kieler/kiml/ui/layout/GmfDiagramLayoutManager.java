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
package de.cau.cs.kieler.kiml.ui.layout;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.eclipse.draw2d.ConnectionLocator;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
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
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.LabelEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeCompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.figures.ResizableCompartmentFigure;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.swt.graphics.Font;
import org.eclipse.ui.IEditorPart;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KInsets;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.layout.options.EdgeLabelPlacement;
import de.cau.cs.kieler.kiml.layout.options.LayoutOptions;
import de.cau.cs.kieler.kiml.layout.options.PortConstraints;
import de.cau.cs.kieler.kiml.layout.util.KimlLayoutUtil;

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
    private CachedLayout cachedLayout;
    /** the command that applies the transferred layout to the diagram. */
    private Command applyLayoutCommand;

    /**
     * {@inheritDoc}
     */
    protected boolean supports(final IEditorPart editorPart) {
        return editorPart instanceof DiagramEditor;
    }

    /**
     * {@inheritDoc}
     */
    protected boolean supports(final EditPart editPart) {
        return editPart instanceof IGraphicalEditPart;
    }

    /**
     * {@inheritDoc}
     */
    protected KNode buildLayoutGraph(final IEditorPart editorPart, final EditPart editPart,
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
        diagramEditPart = KimlUiUtil.getDiagramEditPart(layoutRootPart);

        layoutGraph = doBuildLayoutGraph();
        return layoutGraph;
    }

    /**
     * {@inheritDoc}
     */
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
            cachedLayout = new CachedLayout(graphElem2EditPartMap.size());
            for (Entry<KGraphElement, IGraphicalEditPart> entry : graphElem2EditPartMap.entrySet()) {
                cachedLayout.addLayout(entry.getValue(), entry.getKey());
            }
        }
    }
    
    /**
     * {@inheritDoc}
     */
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
    }
    
    /**
     * {@inheritDoc}
     */
    protected KNode getLayoutGraph() {
        return layoutGraph;
    }
    
    /**
     * {@inheritDoc}
     */
    protected CachedLayout getCachedLayout() {
        return cachedLayout;
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
        KimlUiUtil.setLayoutOptions(layoutRootPart, shapeLayout, true);
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
        Insets insets = null;
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
                Rectangle portBounds = borderItem.getFigure().getBounds();
                KShapeLayout nodeLayout = KimlLayoutUtil.getShapeLayout(parentLayoutNode);
                portLayout.setXpos(portBounds.x - nodeLayout.getXpos());
                portLayout.setYpos(portBounds.y - nodeLayout.getYpos());
                portLayout.setWidth(portBounds.width);
                portLayout.setHeight(portBounds.height);
                hasPorts = true;
                // set user defined layout options for the port
                KimlUiUtil.setLayoutOptions(borderItem, portLayout, true);

                // store all the connections to process them later
                for (Object connectionObj : borderItem.getTargetConnections()) {
                    if (connectionObj instanceof ConnectionEditPart) {
                        connections.add((ConnectionEditPart) connectionObj);
                    }
                }

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
                            float xoffset = nodeLayout.getXpos() + portLayout.getXpos();
                            float yoffset = nodeLayout.getYpos() + portLayout.getYpos();
                            labelLayout.setXpos(labelFigure.getBounds().x - xoffset);
                            labelLayout.setYpos(labelFigure.getBounds().y - yoffset);
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

                    // set preconfigured layout options for the compartment
//                    KimlUiUtil.setLayoutOptions(compartment,
//                            KimlLayoutUtil.getShapeLayout(parentLayoutNode), false);
                }

            // process nodes, which may be parents of compartments
            } else if (obj instanceof ShapeNodeEditPart) {
                ShapeNodeEditPart childNodeEditPart = (ShapeNodeEditPart) obj;
                //if (childNodeEditPart instanceof NoteEditPart) {
                //    System.out.print(".");
                if (!KimlUiUtil.isNoLayout(childNodeEditPart)) {
                    IFigure nodeFigure = childNodeEditPart.getFigure();
                    KNode childLayoutNode = KimlLayoutUtil.createInitializedNode();
                    Rectangle childBounds = nodeFigure.getBounds();
                    KShapeLayout nodeLayout = KimlLayoutUtil.getShapeLayout(childLayoutNode);
    
                    // store all the connections to process them later
                    for (Object conn : childNodeEditPart.getTargetConnections()) {
                        if (conn instanceof ConnectionEditPart) {
                            connections.add((ConnectionEditPart) conn);
                        }
                    }
    
                    // set location and size
                    if (KimlUiUtil.isRelative(parentFigure, nodeFigure)) {
                        nodeLayout.setXpos(childBounds.x);
                        nodeLayout.setYpos(childBounds.y);
                    } else {
                        Rectangle parentBounds = parentFigure.getBounds();
                        nodeLayout.setXpos(childBounds.x - parentBounds.x);
                        nodeLayout.setYpos(childBounds.y - parentBounds.y);
                    }
                    nodeLayout.setHeight(childBounds.height);
                    nodeLayout.setWidth(childBounds.width);
                    Dimension minSize = nodeFigure.getMinimumSize();
                    LayoutOptions.setFloat(nodeLayout, LayoutOptions.MIN_WIDTH, minSize.width);
                    LayoutOptions.setFloat(nodeLayout, LayoutOptions.MIN_HEIGHT, minSize.height);
                    
                    // set insets if not yet defined
                    if (insets == null) {
                        insets = KimlUiUtil.calcInsets(parentFigure, nodeFigure);
                    }
    
                    parentLayoutNode.getChildren().add(childLayoutNode);
                    editPart2GraphElemMap.put(childNodeEditPart, childLayoutNode);
                    graphElem2EditPartMap.put(childLayoutNode, childNodeEditPart);
                    hasChildNodes = true;
                    // process the child as new current edit part
                    buildLayoutGraphRecursively(childNodeEditPart, childLayoutNode,
                            childNodeEditPart);
    
                    // set user defined layout options for the node
                    KimlUiUtil.setLayoutOptions(childNodeEditPart, nodeLayout, true);
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
                    label.setText(text);
                    KShapeLayout labelLayout = KimlLayoutUtil.getShapeLayout(label);
//                  KShapeLayout parentLayout = KimlLayoutUtil.getShapeLayout(parent);
//                  Rectangle labelBounds = labelFigure.getBounds();
//                    int xpos = labelBounds.x, ypos = labelBounds.y;
//                    if (!KimlUiUtil.isRelative(parentFigure, labelFigure)) {
//                        xpos -= parentLayout.getXpos();
//                        ypos -= parentLayout.getYpos();
//                    }
//                    labelLayout.setXpos(xpos);
//                    labelLayout.setYpos(ypos);
                    labelLayout.setWidth(labelFigure.getPreferredSize().width);
                    labelLayout.setHeight(labelFigure.getPreferredSize().height);
                    LayoutOptions.setString(labelLayout, LayoutOptions.FONT_NAME,
                            font.getFontData()[0].getName());
                    LayoutOptions.setInt(labelLayout, LayoutOptions.FONT_SIZE,
                            font.getFontData()[0].getHeight());
                }
            }
        }

        KShapeLayout nodeLayout = KimlLayoutUtil.getShapeLayout(parentLayoutNode);
        // set default fixed size option
        if (!hasChildNodes && !hasChildCompartments && !isCollapsed) {
            LayoutOptions.setBoolean(nodeLayout, LayoutOptions.FIXED_SIZE, true);
        }
        // set default insets option
        if ((hasChildNodes || isCollapsed) && insets != null) {
            KInsets kinsets = LayoutOptions.getObject(nodeLayout, KInsets.class);
            kinsets.setTop(insets.top);
            kinsets.setLeft(insets.left);
            kinsets.setRight(insets.right);
            kinsets.setBottom(insets.bottom);
        }
        // set default port constraints option
        if (hasPorts) {
            if (hasChildNodes || hasChildCompartments) {
                LayoutOptions.setEnum(nodeLayout, PortConstraints.FREE_PORTS);
            } else {
                LayoutOptions.setEnum(nodeLayout, PortConstraints.FIXED_POS);
            }
        }
    }

    /**
     * Creates new edges and takes care of the labels for each connection
     * identified in the {@code buildLayoutGraphRecursively} method.
     */
    private void processConnections() {
        Set<EReference> referenceSet = new HashSet<EReference>();
        for (ConnectionEditPart connection : connections) {
            EObject modelObject = connection.getNotationView().getElement();
            if (modelObject instanceof EReference) {
                EReference reference = (EReference) modelObject;
                if (referenceSet.contains(reference.getEOpposite())) {
                    continue;
                }
                referenceSet.add(reference);
            }
            
            KEdge edge = KimlLayoutUtil.createInitializedEdge();
            KNode sourceNode, targetNode;
            KPort sourcePort = null, targetPort = null;

            EditPart sourceObj = connection.getSource();
            if (sourceObj instanceof AbstractBorderItemEditPart) {
                sourcePort = (KPort) editPart2GraphElemMap.get(sourceObj);
                if (sourcePort == null) {
                    continue;
                }
                edge.setSourcePort(sourcePort);
                sourcePort.getEdges().add(edge);
                sourceNode = sourcePort.getNode();
            } else {
                sourceNode = (KNode) editPart2GraphElemMap.get(sourceObj);
                if (sourceNode == null) {
                    continue;
                }
            }

            EditPart targetObj = connection.getTarget();
            if (targetObj instanceof AbstractBorderItemEditPart) {
                targetPort = (KPort) editPart2GraphElemMap.get(targetObj);
                if (targetPort == null) {
                    continue;
                }
                edge.setTargetPort(targetPort);
                targetPort.getEdges().add(edge);
                targetNode = targetPort.getNode();
            } else {
                targetNode = (KNode) editPart2GraphElemMap.get(targetObj);
                if (targetNode == null) {
                    continue;
                }
            }

            edge.setSource(sourceNode);
            edge.setTarget(targetNode);
            graphElem2EditPartMap.put(edge, connection);

            KEdgeLayout edgeLayout = KimlLayoutUtil.getEdgeLayout(edge);
            KPoint sourcePoint = edgeLayout.getSourcePoint();
            KShapeLayout sourceLayout = KimlLayoutUtil.getShapeLayout(sourceNode);
            if (sourcePort != null) {
                KShapeLayout sourcePortLayout = KimlLayoutUtil.getShapeLayout(sourcePort);
                sourcePoint.setX(sourcePortLayout.getXpos() + sourcePortLayout.getWidth() / 2
                        + sourceLayout.getXpos());
                sourcePoint.setY(sourcePortLayout.getYpos() + sourcePortLayout.getHeight() / 2
                        + sourceLayout.getYpos());
            } else {
                sourcePoint.setX(sourceLayout.getXpos() + sourceLayout.getWidth() / 2);
                sourcePoint.setY(sourceLayout.getYpos() + sourceLayout.getHeight() / 2);
            }
            KPoint targetPoint = edgeLayout.getTargetPoint();
            KShapeLayout targetLayout = KimlLayoutUtil.getShapeLayout(targetNode);
            if (targetPort != null) {
                KShapeLayout targetPortLayout = KimlLayoutUtil.getShapeLayout(targetPort);
                targetPoint.setX(targetPortLayout.getXpos() + targetPortLayout.getWidth() / 2
                        + targetLayout.getXpos());
                targetPoint.setY(targetPortLayout.getYpos() + targetPortLayout.getHeight() / 2
                        + targetLayout.getYpos());
            } else {
                targetPoint.setX(targetLayout.getXpos() + targetLayout.getWidth() / 2);
                targetPoint.setY(targetLayout.getYpos() + targetLayout.getHeight() / 2);
            }

            // set user defined layout options for the edge
            KimlUiUtil.setLayoutOptions(connection, edgeLayout, true);

            /*
             * process the labels
             * 
             * ars: source and target is exchanged when defining it in the
             * gmfgen file. So if Emma sets a label to be placed as target on a
             * connection, then the label will show up next to the source node
             * in the diagram editor. So correct it here, very ugly.
             */
            for (Object obj : connection.getChildren()) {
                if (obj instanceof LabelEditPart) {
                    LabelEditPart labelEditPart = (LabelEditPart) obj;
                    IFigure labelFigure = labelEditPart.getFigure();
                    Rectangle labelBounds = labelFigure.getBounds();
                    String labelText = null;
                    Font font = null;
                    if (labelFigure instanceof WrappingLabel) {
                        WrappingLabel wrappingLabel = (WrappingLabel) labelFigure;
                        labelText = wrappingLabel.getText();
                        font = wrappingLabel.getFont();
                    } else if (labelFigure instanceof Label) {
                        Label label = (Label) labelFigure;
                        labelText = label.getText();
                        font = label.getFont();
                    }
                    if (labelText != null && labelText.length() > 0) {
                        KLabel label = KimlLayoutUtil.createInitializedLabel(edge);
                        KShapeLayout labelLayout = KimlLayoutUtil.getShapeLayout(label);
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
                        LayoutOptions.setString(labelLayout, LayoutOptions.FONT_NAME,
                                font.getFontData()[0].getName());
                        LayoutOptions.setInt(labelLayout, LayoutOptions.FONT_SIZE,
                                font.getFontData()[0].getHeight());
                        labelLayout.setXpos(labelBounds.x);
                        labelLayout.setYpos(labelBounds.y);
                        labelLayout.setWidth(labelBounds.width);
                        labelLayout.setHeight(labelBounds.height);
                        label.setText(labelText);
                        edge.getLabels().add(label);
                        graphElem2EditPartMap.put(label, labelEditPart);
                    }
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

}
