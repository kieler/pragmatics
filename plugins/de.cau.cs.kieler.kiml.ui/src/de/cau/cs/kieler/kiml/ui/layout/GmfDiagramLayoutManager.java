/******************************************************************************
 * KIELER - Kiel Integrated Environment for Layout for the Eclipse RCP
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
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Map;

import org.eclipse.draw2d.ConnectionLocator;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.ScrollPane;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gmf.runtime.diagram.ui.editparts.AbstractBorderItemEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.CompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.LabelEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.figures.ResizableCompartmentFigure;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramCommandStack;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.swt.graphics.Font;
import org.eclipse.ui.IEditorPart;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KInsets;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KLayoutData;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KOption;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.layout.options.EdgeLabelPlacement;
import de.cau.cs.kieler.kiml.layout.options.LayoutDirection;
import de.cau.cs.kieler.kiml.layout.options.LayoutOptions;
import de.cau.cs.kieler.kiml.layout.options.PortConstraints;
import de.cau.cs.kieler.kiml.layout.services.LayoutServices;
import de.cau.cs.kieler.kiml.layout.util.KimlLayoutUtil;
import de.cau.cs.kieler.kiml.ui.layout.layoutoptions.LayoutOptionStyle;
import de.cau.cs.kieler.kiml.ui.layout.layoutoptions.LayoutOptionsPackage;

/**
 * Diagram layout manager that is able to generically layout diagrams generated
 * by GMF. The internal KGraph graph structure is built from the structure of edit
 * parts in the diagram. The new layout is applied to the diagram using
 * {@link GmfLayoutEditPolicy}, which creates a {@link GmfLayoutCommand} to
 * directly manipulate data in the GMF notation model, where layout information
 * is stored persistently.
 * 
 * @author <a href="mailto:ars@informatik.uni-kiel.de">Arne Schipper</a>
 * @author <a href="mailto:msp@informatik.uni-kiel.de">Miro Sp&ouml;nemann</a>
 */
public class GmfDiagramLayoutManager extends DiagramLayoutManager {

    /** map of nodes in the layout graph to edit parts */
    private Map<KNode, GraphicalEditPart> node2EditPartMap = new LinkedHashMap<KNode, GraphicalEditPart>();
    /** map of edges in the layout graph to edit parts */
    private Map<KEdge, ConnectionEditPart> edge2EditPartMap = new LinkedHashMap<KEdge, ConnectionEditPart>();
    /** map of ports in the layout graph to edit parts */
    private Map<KPort, ShapeNodeEditPart> port2EditPartMap = new LinkedHashMap<KPort, ShapeNodeEditPart>();
    /** map of edge labels in the layout graph to edit parts */
    private Map<KLabel, LabelEditPart> edgeLabel2EditPartMap = new LinkedHashMap<KLabel, LabelEditPart>();
    /** map of edit parts to nodes in the layout graph */
    private Map<GraphicalEditPart, KNode> editPart2NodeMap = new HashMap<GraphicalEditPart, KNode>();
    /** map of edit parts to ports in the layout graph */
    private Map<GraphicalEditPart, KPort> editPart2PortMap = new HashMap<GraphicalEditPart, KPort>();
    /** list of connection edit parts that were found in the diagram */
    private LinkedList<ConnectionEditPart> connections = new LinkedList<ConnectionEditPart>();
    /** editor part of the currently layouted diagram */
    private DiagramEditor diagramEditorPart;
    /** top edit part of the currently layouted diagram */
    private DiagramEditPart diagramEditPart;
    
    /**
     * Sets all layout options that are stored in the notation view of the given edit
     * part.
     * 
     * @param editPart edit part for which the notation view is queried for layout options
     * @param layoutData layout data to which the layout options are added
     */
    public void setNotationLayoutOptions(IGraphicalEditPart editPart, KLayoutData layoutData) {
        LayoutOptionStyle optionStyle = (LayoutOptionStyle)editPart.getNotationView()
                .getStyle(LayoutOptionsPackage.eINSTANCE.getLayoutOptionStyle());
        if (optionStyle != null) {
            for (KOption option : optionStyle.getOptions()) {
                ListIterator<KOption> optionIter = layoutData.getOptions().listIterator();
                while (optionIter.hasNext()) {
                    if (optionIter.next().getKey().equals(option.getKey())) {
                        optionIter.remove();
                        break;
                    }
                }
                optionIter.add(option);
            }
        }
    }
    
    /*
     * (non-Javadoc)
     * @see de.cau.cs.kieler.kiml.ui.layout.DiagramLayoutManager#supports(org.eclipse.ui.IEditorPart)
     */
    protected boolean supports(IEditorPart editorPart) {
        return editorPart instanceof DiagramEditor;
    }

    /*
     * (non-Javadoc)
     * @see de.cau.cs.kieler.kiml.ui.layout.DiagramLayoutManager#supports(org.eclipse.gef.EditPart)
     */
    protected boolean supports(EditPart editPart) {
        return editPart instanceof IGraphicalEditPart;
    }
    
    /*
     * (non-Javadoc)
     * @see de.cau.cs.kieler.kiml.ui.layout.DiagramLayoutManager#buildLayoutGraph(org.eclipse.ui.IEditorPart, org.eclipse.gef.EditPart, de.cau.cs.kieler.core.alg.IKielerProgressMonitor)
     */
    protected KNode buildLayoutGraph(IEditorPart editorPart, EditPart editPart,
    		IKielerProgressMonitor progressMonitor) {
    	progressMonitor.begin("Build layout graph", 10);
        node2EditPartMap.clear();
        edge2EditPartMap.clear();
        port2EditPartMap.clear();
        edgeLabel2EditPartMap.clear();
        editPart2NodeMap.clear();
        editPart2PortMap.clear();
        connections.clear();
        
        // get the diagram editor part
        if (editorPart instanceof DiagramEditor)
            diagramEditorPart = (DiagramEditor)editorPart;
        else
            diagramEditorPart = null;
        
        // choose the layout root edit part
        GraphicalEditPart layoutRootPart = null;
        if (editPart instanceof ShapeNodeEditPart || editPart instanceof DiagramEditPart)
            layoutRootPart = (GraphicalEditPart) editPart;
        else {
            if (editPart instanceof IGraphicalEditPart)
                layoutRootPart = ((IGraphicalEditPart)editPart).getTopGraphicEditPart();
            if (!(layoutRootPart instanceof ShapeNodeEditPart)
                    && diagramEditorPart != null) {
                EditPart contentEditPart = diagramEditorPart.getDiagramGraphicalViewer().getContents();
                if (contentEditPart instanceof GraphicalEditPart)
                    layoutRootPart = (GraphicalEditPart) contentEditPart;
                else
                    layoutRootPart = null;
            }
        }
        if (layoutRootPart == null)
            throw new UnsupportedOperationException("Not supported by this layout manager: Editor "
                    + editorPart + ", Edit part " + editPart);
        
        // find the diagram edit part
        EditPart ep = layoutRootPart;
        while (ep != null && !(ep instanceof DiagramEditPart)){
            ep = ep.getParent();
        }
        diagramEditPart = (DiagramEditPart)ep;
        
        KNode topNode = doBuildLayoutGraph(layoutRootPart);
        progressMonitor.done();
        return topNode;
    }
    
    /*
     * (non-Javadoc)
     * @see de.cau.cs.kieler.kiml.ui.layout.DiagramLayoutManager#applyLayout(de.cau.cs.kieler.core.alg.IKielerProgressMonitor)
     */
    protected void applyLayout(IKielerProgressMonitor progressMonitor) {
    	progressMonitor.begin("Apply layout to the diagram", 10);
        // create a new request to change the layout
        ApplyLayoutRequest applyLayoutRequest = new ApplyLayoutRequest();
        for (KNode knode : node2EditPartMap.keySet())
            applyLayoutRequest.addElement(knode, node2EditPartMap.get(knode));
        for (KEdge kedge : edge2EditPartMap.keySet())
            applyLayoutRequest.addElement(kedge, edge2EditPartMap.get(kedge));
        for (KPort kport : port2EditPartMap.keySet())
            applyLayoutRequest.addElement(kport, port2EditPartMap.get(kport));
        for (KLabel klabel : edgeLabel2EditPartMap.keySet())
            applyLayoutRequest.addElement(klabel, edgeLabel2EditPartMap.get(klabel));
        
        // retrieve a command for the request; the command is created by GmfLayoutEditPolicy
        Command applyLayoutCommand = diagramEditPart.getCommand(applyLayoutRequest);
        progressMonitor.worked(3);
        
        // gets a command stack to execute the command
        DiagramCommandStack commandStack = null;
        if (diagramEditorPart != null) {
            Object adapter = diagramEditorPart.getAdapter(CommandStack.class);
            if (adapter instanceof DiagramCommandStack)
                commandStack = (DiagramCommandStack) adapter;
        }
        if (commandStack == null)
            commandStack = new DiagramCommandStack(null);
        commandStack.execute(applyLayoutCommand);
        progressMonitor.done();
    }

	/**
	 * Builds the layout graph for the given root edit part.
	 * 
	 * @param layoutRootPart edit part for which the layout graph is built
	 * @return layout graph layout graph that represents the structure contained in
	 *     the root edit part
	 */
	private KNode doBuildLayoutGraph(GraphicalEditPart layoutRootPart) {
        KNode layoutGraph = KimlLayoutUtil.createInitializedNode();
        // start with a specific node as root for layout
		if (layoutRootPart instanceof ShapeNodeEditPart) {
			ShapeNodeEditPart rootEditPart = (ShapeNodeEditPart) layoutRootPart;
			KShapeLayout shapeLayout = KimlLayoutUtil.getShapeLayout(layoutGraph);

			// set location and size
			Rectangle rootBounds = rootEditPart.getFigure().getBounds();
			shapeLayout.setXpos(rootBounds.x);
			shapeLayout.setYpos(rootBounds.y);
			shapeLayout.setHeight(rootBounds.height);
			shapeLayout.setWidth(rootBounds.width);

			// map the root EditPart to the top KNode
			editPart2NodeMap.put(rootEditPart, layoutGraph);
			node2EditPartMap.put(layoutGraph, rootEditPart);

			// wander recursively through the diagram
			buildLayoutGraphRecursively(rootEditPart, layoutGraph, 0.0f, 0.0f);
			
			// set preconfigured layout options for the root node
			LayoutServices.INSTANCE.setLayoutOptions(rootEditPart.getClass(), shapeLayout);
			// set user defined layout options for the root node
			setNotationLayoutOptions(rootEditPart, shapeLayout);
		}
		// start with the whole diagram as root for layout
		else if (layoutRootPart instanceof DiagramEditPart) {
		    KShapeLayout shapeLayout = KimlLayoutUtil.getShapeLayout(layoutGraph);
		    shapeLayout.setWidth(layoutRootPart.getFigure().getBounds().width);
		    shapeLayout.setHeight(layoutRootPart.getFigure().getBounds().height);
			layoutGraph.getLabel().setText(((DiagramEditPart)layoutRootPart)
			        .getDiagramView().getName());
			editPart2NodeMap.put(layoutRootPart, layoutGraph);
			node2EditPartMap.put(layoutGraph, layoutRootPart);
			buildLayoutGraphRecursively(layoutRootPart, layoutGraph, 0.0f, 0.0f);
			
			// set preconfigured layout options for the diagram
			LayoutServices.INSTANCE.setLayoutOptions(layoutRootPart.getClass(), shapeLayout);
			// set user defined layout options for the diagram
			setNotationLayoutOptions(layoutRootPart, shapeLayout);
		}
		else throw new UnsupportedOperationException("Not supported by this layout manager: "
		        + layoutRootPart);
		
	    // transform all connections in the selected area
        processConnections();
        
        return layoutGraph;
	}

	/**
	 * Recursively builds a layout graph by analyzing the children of the given
	 * edit part.
	 * 
	 * @param currentEditPart the edit part whose children will be processed
	 * @param currentLayoutNode the corresponding KNode
	 * @param insetTop inset value to be set on the top side of the parent node
	 * @param insetLeft inset value to be set on the left, right, and bottom side
	 *     of the parent node
	 */
	private void buildLayoutGraphRecursively(GraphicalEditPart currentEditPart,
			KNode currentLayoutNode, float insetTop, float insetLeft) {
	    boolean hasChildNodes = false, hasChildCompartments = false, hasPorts = false,
	        isCollapsed = false;
		// iterate through the children of the element
		for (Object obj : currentEditPart.getChildren()) {
			
			// check visibility of the child
			if (obj instanceof GraphicalEditPart) {
				IFigure figure = ((GraphicalEditPart)obj).getFigure();
				if (!figure.isVisible())
					continue;
			}

		    // process ports (border items)
		    if (obj instanceof AbstractBorderItemEditPart) {
		        AbstractBorderItemEditPart borderItem = (AbstractBorderItemEditPart)obj;
                KPort port = KimlLayoutUtil.createInitializedPort();
                port2EditPartMap.put(port, borderItem);
                editPart2PortMap.put(borderItem, port);
                port.setNode(currentLayoutNode);
                // set the port's layout, relative to the node position
                KShapeLayout portLayout = KimlLayoutUtil.getShapeLayout(port);
                Rectangle portBounds = borderItem.getFigure().getBounds();
                KShapeLayout nodeLayout = KimlLayoutUtil.getShapeLayout(currentLayoutNode);
                portLayout.setXpos(portBounds.x - nodeLayout.getXpos());
                portLayout.setYpos(portBounds.y - nodeLayout.getYpos());
                portLayout.setWidth(portBounds.width);
                portLayout.setHeight(portBounds.height);
                hasPorts = true;
                // set preconfigured layout options for the port
                LayoutServices.INSTANCE.setLayoutOptions(borderItem.getClass(), portLayout);
                // set user defined layout options for the port
                setNotationLayoutOptions(borderItem, portLayout);
                
                // store all the connections to process them later
                for (Object connectionObj : borderItem.getTargetConnections()) {
                    if (connectionObj instanceof ConnectionEditPart) {
                        connections.add((ConnectionEditPart)connectionObj);
                    }
                }
                
                // set the port label
                for (Object portChildObj : borderItem.getChildren()) {
                    if (portChildObj instanceof GraphicalEditPart) {
                        IFigure labelFigure = ((GraphicalEditPart)portChildObj).getFigure();
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
		    }
		    
			// process compartments, which may contain other elements
			else if (obj instanceof CompartmentEditPart
					&& ((CompartmentEditPart) obj).getChildren().size() != 0) {
				CompartmentEditPart compartment = (CompartmentEditPart)obj;
			    if (!LayoutServices.INSTANCE.isNolayout(compartment.getClass())) {
    			    Rectangle parentBounds = currentEditPart.getFigure().getBounds();
    			    IFigure compartmentFigure = compartment.getFigure();
    			    // calculate insets of the child compartment
    			    Rectangle compartmentBounds = compartmentFigure.getBounds();
    			    Insets compartmentInsets = compartmentFigure.getInsets();
    			    insetTop = compartmentBounds.y - parentBounds.y + compartmentInsets.top;
    			    insetLeft = compartmentBounds.x - parentBounds.x + compartmentInsets.left;
    			    if (compartmentFigure instanceof ResizableCompartmentFigure) {
    			    	ResizableCompartmentFigure resizableCompartmentFigure = (ResizableCompartmentFigure)compartmentFigure;
    			    	ScrollPane scrollPane = resizableCompartmentFigure.getScrollPane();
			            Insets scrollPaneInsets = scrollPane.getInsets();
			            insetTop += scrollPaneInsets.top;
			            insetLeft += scrollPaneInsets.left;
    			    
			            // check whether the compartment is collapsed
			    		if (!resizableCompartmentFigure.isExpanded()) {
			    			isCollapsed = true;
			    			continue;
			    		}
    			    }
    			    
    			    hasChildCompartments = true;
    				buildLayoutGraphRecursively(compartment, currentLayoutNode,
    				        insetTop, insetLeft);
    				
    				// set preconfigured layout options for the compartment
    				LayoutServices.INSTANCE.setLayoutOptions(compartment.getClass(),
    				        KimlLayoutUtil.getShapeLayout(currentLayoutNode));
                }
			}
		    
            // process nodes, which may be parents of compartments
            else if (obj instanceof ShapeNodeEditPart) {
                ShapeNodeEditPart childNodeEditPart = (ShapeNodeEditPart) obj;
                KNode childLayoutNode = KimlLayoutUtil.createInitializedNode();
                Rectangle childBounds = childNodeEditPart.getFigure().getBounds();
                KShapeLayout nodeLayout = KimlLayoutUtil.getShapeLayout(childLayoutNode);

                // store all the connections to process them later
                for (Object conn : childNodeEditPart.getTargetConnections()) {
                    if (conn instanceof ConnectionEditPart) {
                        connections.add((ConnectionEditPart) conn);
                    }
                }

                // set location and size
                nodeLayout.setXpos(childBounds.x);
                nodeLayout.setYpos(childBounds.y);
                nodeLayout.setHeight(childBounds.height);
                nodeLayout.setWidth(childBounds.width);

                currentLayoutNode.getChildren().add(childLayoutNode);
                editPart2NodeMap.put(childNodeEditPart, childLayoutNode);
                node2EditPartMap.put(childLayoutNode, childNodeEditPart);
                hasChildNodes = true;
                // process the child as new current edit part, as it may contain other elements
                buildLayoutGraphRecursively(childNodeEditPart,
                        childLayoutNode, 0.0f, 0.0f);
                
                // set preconfigured layout options for the node
                LayoutServices.INSTANCE.setLayoutOptions(childNodeEditPart.getClass(),
                        nodeLayout);
                // set user defined layout options for the node
                setNotationLayoutOptions(childNodeEditPart, nodeLayout);
            }
		    
			// process labels of nodes
            else if (obj instanceof GraphicalEditPart) {

				GraphicalEditPart graphicalEditPart = (GraphicalEditPart) obj;
				IFigure labelFigure = graphicalEditPart.getFigure();
				String text = null;
				Font font = null;
				if (labelFigure instanceof WrappingLabel) {
					WrappingLabel wrappingLabel = (WrappingLabel)labelFigure;
					text = wrappingLabel.getText();
					font = wrappingLabel.getFont();
				}
				else if (labelFigure instanceof Label) {
					Label label = (Label)labelFigure;
					text = label.getText();
					font = label.getFont();
				}
				if (text != null) {
				    KNode parent = editPart2NodeMap.get(
                            graphicalEditPart.getParent());
				    KShapeLayout parentLayout = KimlLayoutUtil.getShapeLayout(parent);
                    KLabel label = parent.getLabel();
                    label.setText(text);
                    Rectangle labelBounds = labelFigure.getBounds();
                    KShapeLayout labelLayout = KimlLayoutUtil.getShapeLayout(label);
                    labelLayout.setXpos(labelBounds.x - parentLayout.getXpos());
                    labelLayout.setYpos(labelBounds.y - parentLayout.getYpos());
                    labelLayout.setWidth(labelFigure.getPreferredSize().width);
                    labelLayout.setHeight(labelFigure.getPreferredSize().height);
					LayoutOptions.setFontName(labelLayout, font.getFontData()[0].getName());
					LayoutOptions.setFontSize(labelLayout, font.getFontData()[0].getHeight());
				}
			}
		}
		
        KShapeLayout nodeLayout = KimlLayoutUtil.getShapeLayout(currentLayoutNode);
        // set default layout direction option
		if (hasChildNodes) {
            LayoutOptions.setLayoutDirection(nodeLayout, LayoutDirection.HORIZONTAL);
		}
		// set default fixed size option
		if (!hasChildNodes && !hasChildCompartments && !isCollapsed) {
		    LayoutOptions.setFixedSize(nodeLayout, true);
		}
		// set default insets option
		if (hasChildNodes || isCollapsed) {
            KInsets insets = LayoutOptions.getInsets(nodeLayout);
            insets.setTop(insetTop);
            insets.setLeft(insetLeft);
            insets.setRight(insetLeft);
            insets.setBottom(insetLeft);
		}
		// set default port constraints option
	    if (hasPorts) {
	    	if (hasChildNodes || hasChildCompartments)
	    		LayoutOptions.setPortConstraints(nodeLayout, PortConstraints.FREE_PORTS);
	    	else
		        LayoutOptions.setPortConstraints(nodeLayout, PortConstraints.FIXED_POS);
		}
	}

	/**
	 * Creates new edges and takes care of the labels for each connection
	 * identified in the {@code buildLayoutGraphRecursively} method.
	 */
    private void processConnections() {
        for (ConnectionEditPart connection : connections) {
            KEdge edge = KimlLayoutUtil.createInitializedEdge();
            KNode sourceNode, targetNode;
            KPort sourcePort = null, targetPort = null;

            EditPart sourceObj = connection.getSource();
            if (sourceObj instanceof AbstractBorderItemEditPart) {
                sourcePort = editPart2PortMap.get(sourceObj);
                if (sourcePort == null) continue;
                edge.setSourcePort(sourcePort);
                sourcePort.getEdges().add(edge);
                sourceNode = sourcePort.getNode();
            }
            else {
                sourceNode = editPart2NodeMap.get(sourceObj);
                if (sourceNode == null) continue;
            }

            EditPart targetObj = connection.getTarget();
            if (targetObj instanceof AbstractBorderItemEditPart) {
                targetPort = editPart2PortMap.get(targetObj);
                if (targetPort == null) continue;
                edge.setTargetPort(targetPort);
                targetPort.getEdges().add(edge);
                targetNode = targetPort.getNode();
            }
            else {
                targetNode = editPart2NodeMap.get(targetObj);
                if (targetNode == null) continue;
            }

            edge.setSource(sourceNode);
            edge.setTarget(targetNode);
            edge2EditPartMap.put(edge, connection);

            KEdgeLayout edgeLayout = KimlLayoutUtil.getEdgeLayout(edge);
            KPoint sourcePoint = edgeLayout.getSourcePoint();
            KShapeLayout sourceLayout = KimlLayoutUtil.getShapeLayout(sourceNode);
            if (sourcePort != null) {
                KShapeLayout sourcePortLayout = KimlLayoutUtil.getShapeLayout(sourcePort);
                sourcePoint.setX(sourcePortLayout.getXpos() + sourcePortLayout.getWidth() / 2
                        + sourceLayout.getXpos());
                sourcePoint.setY(sourcePortLayout.getYpos() + sourcePortLayout.getHeight() / 2
                        + sourceLayout.getYpos());
            }
            else {
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
            }
            else {
                targetPoint.setX(targetLayout.getXpos() + targetLayout.getWidth() / 2);
                targetPoint.setY(targetLayout.getYpos() + targetLayout.getHeight() / 2);
            }
            
            // set the preconfigured layout options for the edge
            LayoutServices.INSTANCE.setLayoutOptions(connection.getClass(), edgeLayout);
            // set the user defined layout options for the edge
            setNotationLayoutOptions(connection, edgeLayout);

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
                    Rectangle labelBounds = labelEditPart.getFigure().getBounds();
                    String labelText = "";
                    Font font = null;
                    if (labelEditPart.getFigure() instanceof WrappingLabel) {
                        WrappingLabel wrappingLabel = (WrappingLabel) labelEditPart.getFigure();
                        labelText = wrappingLabel.getText();
                        font = wrappingLabel.getFont();
                    }
                    if (labelText.length() > 0) {
                        KLabel label = KimlLayoutUtil.createInitializedLabel(edge);
                        KShapeLayout labelLayout = KimlLayoutUtil.getShapeLayout(label);
                        switch (labelEditPart.getKeyPoint()) {
                        case ConnectionLocator.SOURCE:
                            LayoutOptions.setEdgeLabelPlacement(labelLayout,
                                    EdgeLabelPlacement.HEAD);
                            break;
                        case ConnectionLocator.MIDDLE:
                            LayoutOptions.setEdgeLabelPlacement(labelLayout,
                                    EdgeLabelPlacement.CENTER);
                            break;
                        case ConnectionLocator.TARGET:
                            LayoutOptions.setEdgeLabelPlacement(labelLayout,
                                    EdgeLabelPlacement.TAIL);
                            break;
                        }
                        LayoutOptions.setFontName(labelLayout, font.getFontData()[0].getName());
                        LayoutOptions.setFontSize(labelLayout, font.getFontData()[0].getHeight());
                        labelLayout.setXpos(labelBounds.x);
                        labelLayout.setYpos(labelBounds.y);
                        labelLayout.setWidth(labelBounds.width);
                        labelLayout.setHeight(labelBounds.height);
                        label.setText(labelText);
                        edge.getLabels().add(label);
                        edgeLabel2EditPartMap.put(label, labelEditPart);
                    }
                }
            }
        }
    }

}
