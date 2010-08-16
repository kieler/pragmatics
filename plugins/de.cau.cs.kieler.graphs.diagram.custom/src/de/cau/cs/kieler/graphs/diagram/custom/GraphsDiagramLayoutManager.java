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
package de.cau.cs.kieler.graphs.diagram.custom;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.notation.View;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.kiml.gmf.GmfDiagramLayoutManager;
import de.cau.cs.kieler.kiml.gmf.GmfLayoutInspector;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KInsets;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortConstraints;
import de.cau.cs.kieler.kiml.ui.util.KimlUiUtil;
import de.cau.cs.kieler.kiml.util.KimlLayoutUtil;

/**
 * Specialized Layout Manager for Graphs diagrams.
 *
 * @author msp
 */
public class GraphsDiagramLayoutManager extends GmfDiagramLayoutManager {
    
    /** map of original graph elements to corresponding layout graph elements. */
    private Map<KGraphElement, KGraphElement> graphMap = new HashMap<KGraphElement, KGraphElement>();
    /** map of original graph elements to notation views. */
    private Map<KGraphElement, View> graphElem2ViewMap = new HashMap<KGraphElement, View>();
    /** edges of the original graph. */
    private List<KEdge> edges = new LinkedList<KEdge>();
    
    /**
     * Builds the view map by traversing the notation view tree.
     * 
     * @param view a notation view
     */
    private void buildViewMap(final View view) {
        if (view.getElement() instanceof KGraphElement) {
            KGraphElement graphElem = (KGraphElement) view.getElement();
            if (!graphElem2ViewMap.containsKey(graphElem)) {
                graphElem2ViewMap.put(graphElem, view);
            }
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
            // traverse the children of the layout root
            KNode topNode = buildLayoutGraphRecursively((KNode) rootNode, rootPart);
            // transform all connections in the selected area
            processConnections(rootPart);
    
            return cleanupAncestryPath(topNode);
        } else {
            return super.doBuildLayoutGraph(rootPart);
        }
    }
    
    private KNode buildLayoutGraphRecursively(final KNode graphNode,
            final IGraphicalEditPart editPart) {
        Map<?, ?> editPartRegistry = editPart.getViewer().getEditPartRegistry();
        KNode layoutNode = KimlLayoutUtil.createInitializedNode();
        // store the connection to process them later
        edges.addAll(graphNode.getOutgoingEdges());
        
        // set location and size
        IFigure nodeFigure = editPart.getFigure();
        KShapeLayout nodeLayout = KimlLayoutUtil.getShapeLayout(layoutNode);
        Rectangle nodeBounds = KimlUiUtil.getAbsoluteBounds(nodeFigure);
        Rectangle containerBounds = KimlUiUtil.getAbsoluteBounds(nodeFigure.getParent());
        nodeLayout.setXpos(nodeBounds.x - containerBounds.x);
        nodeLayout.setYpos(nodeBounds.y - containerBounds.y);
        nodeLayout.setHeight(nodeBounds.height);
        nodeLayout.setWidth(nodeBounds.width);
        Dimension minSize = nodeFigure.getMinimumSize();
        LayoutOptions.setFloat(nodeLayout, LayoutOptions.MIN_WIDTH, minSize.width);
        LayoutOptions.setFloat(nodeLayout, LayoutOptions.MIN_HEIGHT, minSize.height);
        
        getGraphElem2EditPartMap().put(layoutNode, editPart);
        graphMap.put(graphNode, layoutNode);
        
        // process ports
        for (KPort port : graphNode.getPorts()) {
            Object obj = editPartRegistry.get(graphElem2ViewMap.get(port));
            if (obj instanceof IGraphicalEditPart) {
                IGraphicalEditPart portEP = (IGraphicalEditPart) obj;
                KPort layoutPort = KimlLayoutUtil.createInitializedPort();
                layoutPort.setNode(layoutNode);
                getGraphElem2EditPartMap().put(layoutPort, portEP);
                graphMap.put(port, layoutPort);
                
                // set the port's layout, relative to the node position
                KShapeLayout portLayout = KimlLayoutUtil.getShapeLayout(layoutPort);
                Rectangle portBounds = KimlUiUtil.getAbsoluteBounds(portEP.getFigure());
                portLayout.setXpos(portBounds.x - nodeBounds.x);
                portLayout.setYpos(portBounds.y - nodeBounds.y);
                portLayout.setWidth(portBounds.width);
                portLayout.setHeight(portBounds.height);
                
                // set user defined layout options for the port
                GmfLayoutInspector.setLayoutOptions(portEP, portLayout, true);
            }
        }
        
        // process child nodes
        KInsets kinsets = null;
        for (KNode child : graphNode.getChildren()) {
            Object obj = editPartRegistry.get(graphElem2ViewMap.get(child));
            if (obj instanceof IGraphicalEditPart) {
                IGraphicalEditPart childEP = (IGraphicalEditPart) obj;
                KNode layoutChild = buildLayoutGraphRecursively(child, childEP);
                layoutChild.setParent(layoutNode);
                if (kinsets == null) {
                    kinsets = LayoutOptions.getObject(nodeLayout, KInsets.class);
                    Insets insets = KimlUiUtil.calcInsets(nodeFigure, childEP.getFigure());
                    kinsets.setLeft(insets.left);
                    kinsets.setTop(insets.top);
                    kinsets.setRight(insets.right);
                    kinsets.setBottom(insets.bottom);
                }
            }
        }

        // set all layout options for the node
        if (graphNode.getChildren().isEmpty()) {
            LayoutOptions.setBoolean(nodeLayout, LayoutOptions.FIXED_SIZE, true);
        }
        if (!graphNode.getPorts().isEmpty()) {
            if (graphNode.getChildren().isEmpty()) {
                LayoutOptions.setEnum(nodeLayout, PortConstraints.FIXED_POS);
            } else {
                LayoutOptions.setEnum(nodeLayout, PortConstraints.FREE);
            }
        }
        GmfLayoutInspector.setLayoutOptions(editPart, nodeLayout, true);
        return layoutNode;
    }
    
    private void processConnections(final IGraphicalEditPart rootPart) {
        Map<?, ?> editPartRegistry = rootPart.getViewer().getEditPartRegistry();
        for (KEdge graphEdge : edges) {
            Object obj = editPartRegistry.get(graphElem2ViewMap.get(graphEdge));
            if (obj instanceof ConnectionEditPart) {
                KEdge layoutEdge = KimlLayoutUtil.createInitializedEdge();
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
                IGraphicalEditPart sourceParentEP = getGraphElem2EditPartMap().get(sourceParent);
                Rectangle sourceParentBounds = KimlUiUtil.getAbsoluteBounds(sourceParentEP.getFigure());
                KInsets insets = LayoutOptions.getObject(KimlLayoutUtil.getShapeLayout(
                        sourceParent), KInsets.class);
                float offsetx = sourceParentBounds.x + insets.getLeft();
                float offsety = sourceParentBounds.y + insets.getTop();
    
                // store the current coordinates of the edge
                KEdgeLayout edgeLayout = KimlLayoutUtil.getEdgeLayout(layoutEdge);
                setEdgeLayout(edgeLayout, connection, offsetx, offsety);
    
                // set user defined layout options for the edge
                GmfLayoutInspector.setLayoutOptions(connection, edgeLayout, true);
            }
        }
    }

}
