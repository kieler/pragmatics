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
import java.util.Set;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.ui.IEditorPart;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.keg.Node;
import de.cau.cs.kieler.keg.Port;
import de.cau.cs.kieler.keg.diagram.part.GraphsDiagramEditor;
import de.cau.cs.kieler.kiml.gmf.GmfDiagramLayoutManager;
import de.cau.cs.kieler.kiml.gmf.GmfLayoutConfig;
import de.cau.cs.kieler.kiml.gmf.GmfLayoutInspector;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KInsets;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.EdgeLabelPlacement;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortConstraints;
import de.cau.cs.kieler.kiml.ui.Messages;
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
    private Map<KGraphElement, View> graphElem2ViewMap = new HashMap<KGraphElement, View>();
    /** edges of the original graph. */
    private List<KEdge> edges = new LinkedList<KEdge>();
    /** map of hyperedges to the representing hypernodes in the original graph. */
    private Map<Set<KNode>, List<KNode>> hyperedgeMap;

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean supports(final IEditorPart editorPart) {
        return editorPart instanceof GraphsDiagramEditor;
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
            GmfLayoutConfig layoutConfig = new GmfLayoutConfig();
            KNode topNode = buildLayoutGraphRecursively((KNode) rootNode, rootPart, layoutConfig);
            // transform all connections in the selected area
            processConnections(rootPart, layoutConfig);
    
            topNode = cleanupAncestryPath(topNode);
            hyperedgeMap = HypernodesEditPolicy.createHyperedgeMap(topNode);
            return topNode;
        } else {
            return super.doBuildLayoutGraph(rootPart);
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void transferLayout(final boolean cacheLayout) {
        // first update the hypernodes structure in the domain model
        HypernodesRequest request = new HypernodesRequest(getLayoutGraph(), hyperedgeMap);
        Command updateHypernodesCommand = null;//getDiagramEditPart().getCommand(request);
        
        // then layout the rest of the diagram
        super.transferLayout(cacheLayout);
        if (updateHypernodesCommand != null) {
            CompoundCommand compoundCommand = new CompoundCommand(Messages.getString("kiml.ui.5"));
            compoundCommand.add(updateHypernodesCommand);
            Command layoutCommand = getLayoutCommand();
            compoundCommand.add(layoutCommand);
            setLayoutCommand(compoundCommand);
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
        // store the connection to process them later
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
        
        // set label text
        String label = ((Node) graphNode).getNodeLabel();
        if (label != null) {
            layoutNode.getLabel().setText(label);
        }
        
        // process ports
        for (KPort port : graphNode.getPorts()) {
            Object obj = editPartRegistry.get(graphElem2ViewMap.get(port));
            if (obj instanceof IGraphicalEditPart) {
                IGraphicalEditPart portEP = (IGraphicalEditPart) obj;
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
                
                // set label text
                layoutPort.getLabel().setText(((Port) port).getPortLabel());
                
                // set user defined layout options for the port
                layoutConfig.setFocusElement(portEP);
                portLayout.copyProperties(layoutConfig);
            }
        }
        
        // process child nodes
        KInsets kinsets = null;
        for (KNode child : graphNode.getChildren()) {
            Object obj = editPartRegistry.get(graphElem2ViewMap.get(child));
            if (obj instanceof IGraphicalEditPart) {
                IGraphicalEditPart childEP = (IGraphicalEditPart) obj;
                KNode layoutChild = buildLayoutGraphRecursively(child, childEP, layoutConfig);
                layoutChild.setParent(layoutNode);
                if (kinsets == null) {
                    kinsets = nodeLayout.getProperty(LayoutOptions.INSETS);
                    Insets insets = KimlUiUtil.calcInsets(nodeFigure, childEP.getFigure());
                    kinsets.setLeft(insets.left);
                    kinsets.setTop(insets.top);
                    kinsets.setRight(insets.right);
                    kinsets.setBottom(insets.bottom);
                }
            }
        }

        // set all layout options for the node
        nodeLayout.setProperty(LayoutOptions.FIXED_SIZE, graphNode.getChildren().isEmpty());
        if (!graphNode.getPorts().isEmpty()) {
            if (graphNode.getChildren().isEmpty()) {
                nodeLayout.setProperty(LayoutOptions.PORT_CONSTRAINTS, PortConstraints.FIXED_POS);
            } else {
                nodeLayout.setProperty(LayoutOptions.PORT_CONSTRAINTS, PortConstraints.FREE);
            }
        }
        if (((Node) graphNode).isIsHypernode()) {
            nodeLayout.setProperty(LayoutOptions.HYPERNODE, true);
        }
        layoutConfig.setFocusElement(editPart);
        nodeLayout.copyProperties(layoutConfig);
        return layoutNode;
    }
    
    /**
     * Process all the connections in the graph.
     * 
     * @param rootPart the root edit part for layout
     * @param layoutConfig layout configuration handler
     */
    private void processConnections(final IGraphicalEditPart rootPart,
            final GmfLayoutConfig layoutConfig) {
        Map<?, ?> editPartRegistry = rootPart.getViewer().getEditPartRegistry();
        for (KEdge graphEdge : edges) {
            Object obj = editPartRegistry.get(graphElem2ViewMap.get(graphEdge));
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
                IGraphicalEditPart sourceParentEP = getGraphElem2EditPartMap().get(sourceParent);
                Rectangle sourceParentBounds = sourceParentEP == null ? new Rectangle()
                        : KimlUiUtil.getAbsoluteBounds(sourceParentEP.getFigure());
                KInsets insets = sourceParent.getData(KShapeLayout.class)
                        .getProperty(LayoutOptions.INSETS);
                float offsetx = sourceParentBounds.x + insets.getLeft();
                float offsety = sourceParentBounds.y + insets.getTop();
    
                // store the current coordinates of the edge
                KEdgeLayout edgeLayout = layoutEdge.getData(KEdgeLayout.class);
                setEdgeLayout(edgeLayout, connection, offsetx, offsety);
                
                // set edge labels
                processLabels(connection, layoutEdge, EdgeLabelPlacement.UNDEFINED,
                        offsetx, offsety);
    
                // set user defined layout options for the edge
                layoutConfig.setFocusElement(connection);
                edgeLayout.copyProperties(layoutConfig);
            }
        }
    }

}
