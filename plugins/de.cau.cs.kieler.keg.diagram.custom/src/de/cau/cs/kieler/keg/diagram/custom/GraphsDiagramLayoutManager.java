/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.keg.diagram.custom;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.AbstractBorderItemEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.LabelEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.SWTException;
import org.eclipse.ui.IWorkbenchPart;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.keg.Node;
import de.cau.cs.kieler.keg.Port;
import de.cau.cs.kieler.keg.diagram.part.GraphsDiagramEditor;
import de.cau.cs.kieler.kiml.ILayoutConfig;
import de.cau.cs.kieler.kiml.gmf.GmfDiagramLayoutManager;
import de.cau.cs.kieler.kiml.gmf.GmfLayoutConfig;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KInsets;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.EdgeLabelPlacement;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.ui.util.KimlUiUtil;
import de.cau.cs.kieler.kiml.util.KimlUtil;

/**
 * Specialized Layout Manager for Graphs diagrams.
 *
 * @author msp
 */
public class GraphsDiagramLayoutManager extends GmfDiagramLayoutManager {
    
    /** map of original graph elements to corresponding layout graph elements. */
    private Map<KGraphElement, KGraphElement> graphMap = new HashMap<KGraphElement, KGraphElement>();
    /** map of original graph elements to notation views. */
    private Multimap<KGraphElement, View> graphElem2ViewMap = HashMultimap.create();
    /** edges of the original graph. */
    private List<KEdge> edges = new LinkedList<KEdge>();

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean supports(final IWorkbenchPart workbenchPart) {
        return workbenchPart instanceof GraphsDiagramEditor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean supports(final EditPart editPart) {
        return false;
    }
    
    /**
     * Builds the view map by traversing the notation view tree.
     * 
     * @param view a notation view
     */
    private void buildViewMap(final View view) {
        if (view.getElement() instanceof KGraphElement) {
            KGraphElement graphElem = (KGraphElement) view.getElement();
            graphElem2ViewMap.put(graphElem, view);
        }
        for (Object child : view.getChildren()) {
            if (child instanceof View) {
                buildViewMap((View) child);
            }
        }
        for (Object edge : view.getSourceEdges()) {
            if (edge instanceof View) {
                buildViewMap((View) edge);
            }
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected KNode doBuildLayoutGraph(final IGraphicalEditPart rootPart) {
        edges.clear();
        graphMap.clear();
        graphElem2ViewMap.clear();
        
        // build the notation view map
        buildViewMap(rootPart.getNotationView());
        
        EObject rootNode = rootPart.getNotationView().getElement();
        if (rootNode instanceof KNode) {
            // create a layout configuration
            GmfLayoutConfig layoutConfig;
            if (getExternalConfig() == null) {
                layoutConfig = new GmfLayoutConfig();
            } else {
                layoutConfig = new GmfLayoutConfig(getExternalConfig());
            }
            try {
                org.eclipse.swt.graphics.Point size = rootPart.getViewer().getControl().getSize();
                if (size.x > 0 && size.y > 0) {
                    layoutConfig.setAspectRatio((float) size.x / size.y);
                }
            } catch (SWTException exception) {
                // ignore exception
            }
            
            // traverse the children of the layout root
            KNode topNode = buildLayoutGraphRecursively((KNode) rootNode, rootPart, layoutConfig);
            // transform all connections in the selected area
            processConnections(rootPart, layoutConfig);
    
            cleanupAncestryPath(topNode);
            return topNode;
        } else {
            return super.doBuildLayoutGraph(rootPart);
        }
    }
    
    /**
     * Builds the layout graph from the original graph structure.
     * 
     * @param graphNode a node from the original graph
     * @param editPart the corresponding edit part
     * @param layoutConfig layout configuration handler
     * @return a layout node that is linked with the given graph node
     */
    private KNode buildLayoutGraphRecursively(final KNode graphNode,
            final IGraphicalEditPart editPart, final GmfLayoutConfig layoutConfig) {
        Map<?, ?> editPartRegistry = editPart.getViewer().getEditPartRegistry();
        KNode layoutNode = KimlUtil.createInitializedNode();
        // store the connections to process them later
        edges.addAll(graphNode.getOutgoingEdges());
        
        // set location and size
        IFigure nodeFigure = editPart.getFigure();
        KShapeLayout nodeLayout = layoutNode.getData(KShapeLayout.class);
        Rectangle nodeBounds = KimlUiUtil.getAbsoluteBounds(nodeFigure);
        Rectangle containerBounds = KimlUiUtil.getAbsoluteBounds(nodeFigure.getParent());
        nodeLayout.setXpos(nodeBounds.x - containerBounds.x);
        nodeLayout.setYpos(nodeBounds.y - containerBounds.y);
        nodeLayout.setHeight(nodeBounds.height);
        nodeLayout.setWidth(nodeBounds.width);
        Dimension minSize = nodeFigure.getMinimumSize();
        nodeLayout.setProperty(LayoutOptions.MIN_WIDTH, (float) minSize.width);
        nodeLayout.setProperty(LayoutOptions.MIN_HEIGHT, (float) minSize.height);
        
        getGraphElem2EditPartMap().put(layoutNode, editPart);
        graphMap.put(graphNode, layoutNode);
        
        // set node label
        String label = ((Node) graphNode).getNodeLabel();
        if (label != null) {
            layoutNode.getLabel().setText(label);
        }
        
        // process ports
        for (KPort port : graphNode.getPorts()) {
            for (View view : graphElem2ViewMap.get(port)) {
                Object obj = editPartRegistry.get(view);
                if (obj instanceof AbstractBorderItemEditPart) {
                    createPort(port, layoutNode, (IGraphicalEditPart) obj, nodeBounds, layoutConfig);
                    break;
                }
            }
        }
        
        // process child nodes
        KInsets kinsets = null;
        for (KNode child : graphNode.getChildren()) {
            for (View view : graphElem2ViewMap.get(child)) {
                Object obj = editPartRegistry.get(view);
                if (obj instanceof ShapeNodeEditPart) {
                    IGraphicalEditPart childEP = (IGraphicalEditPart) obj;
                    KNode layoutChild = buildLayoutGraphRecursively(child, childEP, layoutConfig);
                    layoutChild.setParent(layoutNode);
                    if (kinsets == null) {
                        kinsets = nodeLayout.getInsets();
                        Insets insets = KimlUiUtil.calcInsets(nodeFigure, childEP.getFigure());
                        kinsets.setLeft(insets.left);
                        kinsets.setTop(insets.top);
                        kinsets.setRight(insets.right);
                        kinsets.setBottom(insets.bottom);
                    }
                    break;
                }
            }
        }

        // set all layout options for the node
        layoutConfig.setFocus(editPart);
        layoutConfig.setChildren(!graphNode.getChildren().isEmpty());
        layoutConfig.setPorts(!graphNode.getPorts().isEmpty());
        nodeLayout.copyProperties(layoutConfig);
        return layoutNode;
    }
    
    /**
     * Create a port for the layout graph.
     * 
     * @param port the original port of the graphs model
     * @param layoutNode the parent node in the layout graph
     * @param portEP the port's edit part
     * @param nodeBounds the bounds of the layout node
     * @param layoutConfig a layout configuration
     * @return
     */
    private KPort createPort(final KPort port, final KNode layoutNode, final IGraphicalEditPart portEP,
            final Rectangle nodeBounds, final GmfLayoutConfig layoutConfig) {
        KPort layoutPort = KimlUtil.createInitializedPort();
        layoutPort.setNode(layoutNode);
        getGraphElem2EditPartMap().put(layoutPort, portEP);
        graphMap.put(port, layoutPort);
        
        // set the port's layout, relative to the node position
        KShapeLayout portLayout = layoutPort.getData(KShapeLayout.class);
        Rectangle portBounds = KimlUiUtil.getAbsoluteBounds(portEP.getFigure());
        portLayout.setXpos(portBounds.x - nodeBounds.x);
        portLayout.setYpos(portBounds.y - nodeBounds.y);
        portLayout.setWidth(portBounds.width);
        portLayout.setHeight(portBounds.height);
        
        // set the port label
        String label = ((Port) port).getPortLabel();
        if (label != null && label.length() > 0) {
            layoutPort.getLabel().setText(label);
            Map<?, ?> editPartRegistry = portEP.getViewer().getEditPartRegistry();
            for (View view : graphElem2ViewMap.get(port)) {
                Object obj = editPartRegistry.get(view);
                if (obj instanceof LabelEditPart) {
                    IFigure labelFigure = ((LabelEditPart) obj).getFigure();
                    Rectangle labelBounds = KimlUiUtil.getAbsoluteBounds(labelFigure);
                    KShapeLayout labelLayout = layoutPort.getLabel().getData(KShapeLayout.class);
                    labelLayout.setXpos(labelBounds.x - portBounds.x);
                    labelLayout.setYpos(labelBounds.y - portBounds.y);
                    try {
                        Dimension size = labelFigure.getPreferredSize();
                        labelLayout.setWidth(size.width);
                        labelLayout.setHeight(size.height);
                    } catch (SWTException exception) {
                        // ignore exception and leave the label size to (0, 0)
                    }
                    layoutConfig.setFocus(obj);
                    labelLayout.copyProperties(layoutConfig);
                    // port labels are excluded from layout by default
                    labelLayout.setProperty(LayoutOptions.NO_LAYOUT, true);
                    getGraphElem2EditPartMap().put(layoutPort.getLabel(), (IGraphicalEditPart) obj);
                }
            }
        }
        
        // set user defined layout options for the port
        layoutConfig.setFocus(portEP);
        portLayout.copyProperties(layoutConfig);
        return layoutPort;
    }
    
    /**
     * Process all the connections in the graph.
     * 
     * @param rootPart the root edit part for layout
     * @param layoutConfig layout configuration handler
     */
    private void processConnections(final IGraphicalEditPart rootPart,
            final ILayoutConfig layoutConfig) {
        Map<?, ?> editPartRegistry = rootPart.getViewer().getEditPartRegistry();
        for (KEdge graphEdge : edges) {
            for (View view : graphElem2ViewMap.get(graphEdge)) {
                Object obj = editPartRegistry.get(view);
                if (obj instanceof ConnectionEditPart) {
                    KEdge layoutEdge = KimlUtil.createInitializedEdge();
                    KPort sourcePort = (KPort) graphMap.get(graphEdge.getSourcePort());
                    if (sourcePort == null) {
                        layoutEdge.setSource((KNode) graphMap.get(graphEdge.getSource()));
                    } else {
                        layoutEdge.setSourcePort(sourcePort);
                        sourcePort.getEdges().add(layoutEdge);
                        layoutEdge.setSource(sourcePort.getNode());
                    }
                    KPort targetPort = (KPort) graphMap.get(graphEdge.getTargetPort());
                    if (targetPort == null) {
                        layoutEdge.setTarget((KNode) graphMap.get(graphEdge.getTarget()));
                    } else {
                        layoutEdge.setTargetPort(targetPort);
                        targetPort.getEdges().add(layoutEdge);
                        layoutEdge.setTarget(targetPort.getNode());
                    }
                    
                    ConnectionEditPart connection = (ConnectionEditPart) obj;
                    getGraphElem2EditPartMap().put(layoutEdge, connection);
        
                    // calculate offset for edge and label coordinates
                    KNode sourceParent = layoutEdge.getSource().getParent();
                    if (sourceParent != null) {
                        IGraphicalEditPart sourceParentEP = getGraphElem2EditPartMap().get(sourceParent);
                        Rectangle sourceParentBounds = sourceParentEP == null ? new Rectangle()
                                : KimlUiUtil.getAbsoluteBounds(sourceParentEP.getFigure());
                        KInsets insets = sourceParent.getData(KShapeLayout.class).getInsets();
                        float offsetx = sourceParentBounds.x + insets.getLeft();
                        float offsety = sourceParentBounds.y + insets.getTop();
            
                        // store the current coordinates of the edge
                        KEdgeLayout edgeLayout = layoutEdge.getData(KEdgeLayout.class);
                        setEdgeLayout(edgeLayout, connection, offsetx, offsety);
                        
                        // set edge labels
                        processEdgeLabels(connection, layoutEdge, EdgeLabelPlacement.UNDEFINED,
                                offsetx, offsety, layoutConfig);
            
                        // set user defined layout options for the edge
                        layoutConfig.setFocus(connection);
                        edgeLayout.copyProperties(layoutConfig);
                    }
                    break;
                }
            }
        }
    }

}
