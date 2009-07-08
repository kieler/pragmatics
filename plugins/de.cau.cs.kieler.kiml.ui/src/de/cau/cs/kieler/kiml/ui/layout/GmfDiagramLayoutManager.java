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
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
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
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramCommandStack;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.ui.IEditorPart;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KInsets;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.layout.options.EdgeLabelPlacement;
import de.cau.cs.kieler.kiml.layout.options.LayoutDirection;
import de.cau.cs.kieler.kiml.layout.options.LayoutOptions;
import de.cau.cs.kieler.kiml.layout.options.PortConstraints;
import de.cau.cs.kieler.kiml.layout.services.LayoutServices;
import de.cau.cs.kieler.kiml.layout.util.KimlLayoutUtil;

/**
 * A generic implementation of the {@link GmfDiagramLayoutManager}.
 * 
 * @author <a href="mailto:ars@informatik.uni-kiel.de">Arne Schipper</a>,
 *     msp
 */
public class GmfDiagramLayoutManager extends DiagramLayoutManager {

    /* the mappings of KLayoutGraph LAYOUT elements to EditParts */
    private Map<KNode, GraphicalEditPart> node2EditPartMap = new LinkedHashMap<KNode, GraphicalEditPart>();
    private Map<KEdge, ConnectionEditPart> edge2EditPartMap = new LinkedHashMap<KEdge, ConnectionEditPart>();
    private Map<KPort, ShapeNodeEditPart> port2EditPartMap = new LinkedHashMap<KPort, ShapeNodeEditPart>();
    private Map<KLabel, LabelEditPart> edgeLabel2EditPartMap = new LinkedHashMap<KLabel, LabelEditPart>();

    /** map of edit parts to nodes in the layout graph */
    private Map<GraphicalEditPart, KNode> editPart2NodeMap = new HashMap<GraphicalEditPart, KNode>();
    private Map<GraphicalEditPart, KPort> editPart2PortMap = new HashMap<GraphicalEditPart, KPort>();
    private LinkedList<ConnectionEditPart> connections = new LinkedList<ConnectionEditPart>();
    
    private DiagramEditor diagramEditorPart;
    private DiagramEditPart diagramEditPart;
    
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
     * @see de.cau.cs.kieler.kiml.ui.layout.DiagramLayoutManager#buildLayoutGraph(org.eclipse.ui.IEditorPart, org.eclipse.gef.EditPart)
     */
    protected KNode buildLayoutGraph(IEditorPart editorPart, EditPart editPart) {
        node2EditPartMap.clear();
        edge2EditPartMap.clear();
        port2EditPartMap.clear();
        edgeLabel2EditPartMap.clear();
        editPart2NodeMap.clear();
        editPart2PortMap.clear();
        connections.clear();
        
        // choose the layout root edit part
        GraphicalEditPart layoutRootPart = null;
        if (editPart instanceof ShapeNodeEditPart || editPart instanceof DiagramEditPart)
            layoutRootPart = (GraphicalEditPart) editPart;
        else {
            if (editPart instanceof IGraphicalEditPart)
                layoutRootPart = ((IGraphicalEditPart)editPart).getTopGraphicEditPart();
            if (!(layoutRootPart instanceof ShapeNodeEditPart)
                    && editorPart instanceof DiagramEditor) {
                DiagramEditor diagramEditor = (DiagramEditor) editorPart;
                EditPart contentEditPart = diagramEditor.getDiagramGraphicalViewer().getContents();
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
        
        return doBuildLayoutGraph(layoutRootPart);
    }
    
    /*
     * (non-Javadoc)
     * @see de.cau.cs.kieler.kiml.ui.layout.DiagramLayoutManager#applyLayout()
     */
    protected void applyLayout() {
        // create a new GEF Request to change the layout
        // the request will be filled with the new layout specification
        // piece by piece in the following
        ApplyLayoutRequest applyLayoutRequest = new ApplyLayoutRequest();
        for (KNode knode : node2EditPartMap.keySet())
            applyLayoutRequest.addElement(knode, node2EditPartMap.get(knode));
        for (KEdge kedge : edge2EditPartMap.keySet())
            applyLayoutRequest.addElement(kedge, edge2EditPartMap.get(kedge));
        for (KPort kport : port2EditPartMap.keySet())
            applyLayoutRequest.addElement(kport, port2EditPartMap.get(kport));
        for (KLabel klabel : edgeLabel2EditPartMap.keySet())
            applyLayoutRequest.addElement(klabel, edgeLabel2EditPartMap.get(klabel));
        
        // create a new GEF Command from the Request we just set up
        // the command is received from a custom GmfLayoutEditPolicy that is
        // registered with the DiagramEditPart. So we get the command from
        // the diagram. This way we only have exactly one Request/Command pair which
        // should result in massive performance gains.
        Command applyLayoutCommand = diagramEditPart.getCommand(applyLayoutRequest);
        
        /* gets the diagram command stack */
        DiagramCommandStack commandStack = null;
        if (diagramEditorPart != null) {
            Object adapter = diagramEditorPart.getAdapter(CommandStack.class);
            if (adapter instanceof DiagramCommandStack)
                commandStack = (DiagramCommandStack) adapter;
        }
        if (commandStack == null)
            commandStack = new DiagramCommandStack(null);
        commandStack.execute(applyLayoutCommand);
    }

	/**
	 * 
	 * @param layoutRootPart
	 * @return
	 */
	private KNode doBuildLayoutGraph(GraphicalEditPart layoutRootPart) {
        KNode layoutGraph = KimlLayoutUtil.createInitializedNode();
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
		}
		/*
		 * if the rootPart was a complete Diagram, that is when clicked into the
		 * empty diagram background space, start with its children.
		 */
		else if (layoutRootPart instanceof DiagramEditPart) {
		    KShapeLayout shapeLayout = KimlLayoutUtil.getShapeLayout(layoutGraph);
		    shapeLayout.setWidth(layoutRootPart.getFigure().getBounds().width);
		    shapeLayout.setHeight(layoutRootPart.getFigure().getBounds().height);
			layoutGraph.getLabel().setText(((DiagramEditPart)layoutRootPart)
			        .getDiagramView().getName());
			editPart2NodeMap.put(layoutRootPart, layoutGraph);
			node2EditPartMap.put(layoutGraph, layoutRootPart);
			buildLayoutGraphRecursively(layoutRootPart, layoutGraph, 0.0f, 0.0f);
		}
		else throw new UnsupportedOperationException("Not supported by this layout manager: "
		        + layoutRootPart);
		
	    /*
         * Finally process all the connections, as Emma has build all the needed
         * KNodes which act as source and target.
         */
        processConnections();
        
        return layoutGraph;
	}

	/**
	 * Recursive helper function for the sub elements of a composite element.
	 * 
	 * @param currentEditPart
	 *            The GraphicalEditPart which children will be processed
	 * @param currentLayoutNode
	 *            The corresponding KNode
	 */
	private void buildLayoutGraphRecursively(GraphicalEditPart currentEditPart,
			KNode currentLayoutNode, float insetTop, float insetLeft) {
	    boolean hasChildNodes = false, hasChildCompartments = false, hasPorts = false;
		/* iterate through the children of the element */
		for (Object obj : currentEditPart.getChildren()) {

		    // process ports
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
			/*
			 * If it is ShapeCompartmentEditPart, Emma needs the children of it
			 * to add to new KNodes. She handles possible insets, which
			 * may result from labels and other stuff.
			 */
			else if (obj instanceof CompartmentEditPart
					&& ((CompartmentEditPart) obj).getChildren().size() != 0) {

			    GraphicalEditPart compartment = (GraphicalEditPart)obj;
			    String diagramType = LayoutServices.INSTANCE.getDiagramTypeFor(compartment.getClass());
                if (!LayoutServices.DIAGRAM_TYPE_NOLAYOUT.equals(diagramType)) {
                    hasChildCompartments = true;
    			    Rectangle parentBounds = currentEditPart.getFigure().getBounds();
    			    IFigure compartmentFigure = compartment.getFigure();
    			    Rectangle compartmentBounds = compartmentFigure.getBounds();
    			    Insets compartmentInsets = compartmentFigure.getInsets();
    			    int compartmentTopInset = compartmentInsets.top;
    			    int compartmentLeftInset = compartmentInsets.left;
    			    Iterator<?> figureIter = compartmentFigure.getChildren().iterator();
    			    while (figureIter.hasNext()) {
    			        Object next = figureIter.next();
    			        if (next instanceof ScrollPane) {
    			            Insets scrollPaneInsets = ((IFigure)next).getInsets();
    			            compartmentTopInset += scrollPaneInsets.top;
    			            compartmentLeftInset += scrollPaneInsets.left;
    			            break;
    			        }
    			    }
    				buildLayoutGraphRecursively(compartment, currentLayoutNode,
    				        compartmentBounds.y - parentBounds.y + compartmentTopInset,
    				        compartmentBounds.x - parentBounds.x + compartmentLeftInset);
                }
			}
            /* if true, Emma has a real EditPart with contents. */
            else if (obj instanceof ShapeNodeEditPart) {

                ShapeNodeEditPart childNodeEditPart = (ShapeNodeEditPart) obj;
                KNode childLayoutNode = KimlLayoutUtil
                        .createInitializedNode();
                Rectangle childBounds = childNodeEditPart.getFigure()
                        .getBounds();
                KShapeLayout nodeLayout = KimlLayoutUtil.getShapeLayout(childLayoutNode);

                /* store all the connections to process them later */
                for (Object conn : childNodeEditPart.getTargetConnections()) {
                    if (conn instanceof ConnectionEditPart) {
                        connections.add((ConnectionEditPart) conn);
                    }
                }

                /* set location */
                nodeLayout.setXpos(childBounds.x);
                nodeLayout.setYpos(childBounds.y);

                /* set size */
                nodeLayout.setHeight(childBounds.height);
                nodeLayout.setWidth(childBounds.width);

                /* add node */
                currentLayoutNode.getChildren().add(childLayoutNode);

                /* keep track of mapping between elements */
                editPart2NodeMap.put(childNodeEditPart,
                        childLayoutNode);
                node2EditPartMap.put(childLayoutNode, childNodeEditPart);

                hasChildNodes = true;
                /* and process the child as new current */
                buildLayoutGraphRecursively(childNodeEditPart,
                        childLayoutNode, 0.0f, 0.0f);
            }
			/* label handling */
            else if (obj instanceof GraphicalEditPart) {

				GraphicalEditPart graphicalEditPart = (GraphicalEditPart) obj;
				IFigure labelFigure = graphicalEditPart.getFigure();
				String text = null;
				if (labelFigure instanceof WrappingLabel) {
					text = ((WrappingLabel) labelFigure).getText();
				} else if (labelFigure instanceof Label) {
					text = ((Label) labelFigure).getText();
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
				}
			}
		}
		
		// set layout options for the parent node
        // TODO make this customizable
        KShapeLayout nodeLayout = KimlLayoutUtil.getShapeLayout(currentLayoutNode);
		if (hasChildNodes) {
		    GraphicalEditPart parentEditPart = node2EditPartMap.get(currentLayoutNode);
    		if (parentEditPart instanceof IGraphicalEditPart) {
    	        String layoutHint = GmfLayoutHints.getStringValue(
                        (IGraphicalEditPart)parentEditPart,
                        LayoutOptions.LAYOUT_HINT);
    	        if (layoutHint != null)
    	            LayoutOptions.setLayoutHint(nodeLayout, layoutHint);
    	        String diagramType = LayoutServices.INSTANCE.getDiagramTypeFor(
    	                currentEditPart.getClass());
    	        if (diagramType != null)
    	            LayoutOptions.setDiagramType(nodeLayout, diagramType);
    		}
            LayoutOptions.setLayoutDirection(nodeLayout, LayoutDirection.HORIZONTAL);
            KInsets insets = LayoutOptions.getInsets(nodeLayout);
            insets.setTop(insetTop);
            insets.setLeft(insetLeft);
            insets.setRight(insetLeft);
            insets.setBottom(insetLeft);
		}
		else if (!hasChildCompartments){
		    if (hasPorts)
		        LayoutOptions.setPortConstraints(nodeLayout, PortConstraints.FIXED_POS);
		    LayoutOptions.setFixedSize(nodeLayout, true);
		}
		else if (hasPorts) {
            LayoutOptions.setPortConstraints(nodeLayout, PortConstraints.FREE_PORTS);
		}
	}

	/**
	 * This function creates new KEdges and takes care of the labels for each
	 * connection identified in the <code>buildLayoutGraphRecursively</code>
	 * step before.
	 * 
	 * @param connections
	 *            The connections that were build for a certain Node before.
	 */
	private void processConnections() {

		/* iterate through all the connections of an compartment element */
		for (ConnectionEditPart connection : connections) {

			/*
			 * create the KEdge. The KEdge does not need to be added
			 * explicitly to the KLayoutGraph, but exists in it through the
			 * EOppositeReference of EMF.
			 */
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
			else
			    sourceNode = editPart2NodeMap.get(sourceObj);
			
			EditPart targetObj = connection.getTarget();
			if (targetObj instanceof AbstractBorderItemEditPart) {
			    targetPort = editPart2PortMap.get(targetObj);
			    if (targetPort == null) continue;
			    edge.setTargetPort(targetPort);
			    targetPort.getEdges().add(edge);
			    targetNode = targetPort.getNode();
			}
			else
			    targetNode = editPart2NodeMap.get(targetObj);
			
            if (sourceNode == null || targetNode == null) continue;
			
			edge.setSource(sourceNode);
			edge.setTarget(targetNode);
			edge2EditPartMap.put(edge, connection);
			
		     KEdgeLayout edgeLayout = KimlLayoutUtil.getEdgeLayout(edge);
		     KPoint sourcePoint = edgeLayout.getSourcePoint();
		     KShapeLayout sourceLayout = KimlLayoutUtil.getShapeLayout(sourceNode);
		     if (sourcePort != null) {
    		     KShapeLayout sourcePortLayout = KimlLayoutUtil.getShapeLayout(sourcePort);
    		     sourcePoint.setX(sourcePortLayout.getXpos()
    		             + sourcePortLayout.getWidth() / 2 + sourceLayout.getXpos());
    		     sourcePoint.setY(sourcePortLayout.getYpos()
    		             + sourcePortLayout.getHeight() / 2 + sourceLayout.getYpos());
		     }
		     else {
		         sourcePoint.setX(sourceLayout.getXpos() + sourceLayout.getWidth() / 2);
		         sourcePoint.setY(sourceLayout.getYpos() + sourceLayout.getHeight() / 2);
		     }
		     KPoint targetPoint = edgeLayout.getTargetPoint();
		     KShapeLayout targetLayout = KimlLayoutUtil.getShapeLayout(targetNode);
		     if (targetPort != null) {
    		     KShapeLayout targetPortLayout = KimlLayoutUtil.getShapeLayout(targetPort);
    		     targetPoint.setX(targetPortLayout.getXpos()
    		             + targetPortLayout.getWidth() / 2 + targetLayout.getXpos());
    		     targetPoint.setY(targetPortLayout.getYpos()
    		             + targetPortLayout.getHeight() / 2 + targetLayout.getYpos());
		     }
		     else {
		         targetPoint.setX(targetLayout.getXpos() + targetLayout.getWidth() / 2);
                 targetPoint.setY(targetLayout.getYpos() + targetLayout.getHeight() / 2);
		     }

			/*
			 * Process the label, at the moment 3 are hard coded.
			 * 
			 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			 * SOURCE and TARGET is exchanged when defining it in the.gmfgen
			 * file. So if Emma sets a label to be placed as TARGET on a
			 * connection (transition in the SSM case), then the label will show
			 * up next to the source node in the diagram editor. So correct it
			 * here, very ugly.
			 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			 */
			for (Object obj : connection.getChildren()) {
				if (obj instanceof LabelEditPart) {
					LabelEditPart labelEditPart = (LabelEditPart) obj;
					Rectangle labelBounds = labelEditPart.getFigure().getBounds();

					// head label
					if (labelEditPart.getKeyPoint() == ConnectionLocator.SOURCE) {
						String headLabel = "";

						if ((headLabel.equals(""))
								&& (labelEditPart.getFigure() instanceof WrappingLabel))
							headLabel = ((WrappingLabel) labelEditPart
									.getFigure()).getText();

						if (!headLabel.equals("")) {
							KLabel hLabel = KimlLayoutUtil.createInitializedLabel(edge);
							KShapeLayout labelLayout = KimlLayoutUtil.getShapeLayout(hLabel);
							LayoutOptions.setEdgeLabelPlacement(labelLayout,
									EdgeLabelPlacement.HEAD);
							labelLayout.setXpos(labelBounds.x);
							labelLayout.setYpos(labelBounds.y);
							labelLayout.setWidth(labelBounds.width);
							labelLayout.setHeight(labelBounds.height);
							hLabel.setText(headLabel);
							edge.getLabels().add(hLabel);
							edgeLabel2EditPartMap.put(hLabel, labelEditPart);
						}
					}

					// middle label
					if (labelEditPart.getKeyPoint() == ConnectionLocator.MIDDLE) {
						String midLabel = "";

						if ((midLabel.equals(""))
								&& (labelEditPart.getFigure() instanceof WrappingLabel))
							midLabel = ((WrappingLabel) labelEditPart
									.getFigure()).getText();

						if (!midLabel.equals("")) {
							KLabel mLabel = KimlLayoutUtil.createInitializedLabel(edge);
							KShapeLayout labelLayout = KimlLayoutUtil.getShapeLayout(mLabel);
                            LayoutOptions.setEdgeLabelPlacement(labelLayout,
									EdgeLabelPlacement.CENTER);
                            labelLayout.setXpos(labelBounds.x);
                            labelLayout.setYpos(labelBounds.y);
                            labelLayout.setWidth(labelBounds.width);
                            labelLayout.setHeight(labelBounds.height);
                            mLabel.setText(midLabel);
							edge.getLabels().add(mLabel);
							edgeLabel2EditPartMap.put(mLabel, labelEditPart);
						}
					}

					// tail label
					if (labelEditPart.getKeyPoint() == ConnectionLocator.TARGET) {
						String tailLabel = "";

						if ((tailLabel.equals(""))
								&& (labelEditPart.getFigure() instanceof WrappingLabel))
							tailLabel = ((WrappingLabel) labelEditPart
									.getFigure()).getText();

						if (!tailLabel.equals("")) {
							KLabel tLabel = KimlLayoutUtil.createInitializedLabel(edge);
							KShapeLayout labelLayout = KimlLayoutUtil.getShapeLayout(tLabel);
                            LayoutOptions.setEdgeLabelPlacement(labelLayout,
									EdgeLabelPlacement.TAIL);
                            labelLayout.setXpos(labelBounds.x);
                            labelLayout.setYpos(labelBounds.y);
                            labelLayout.setWidth(labelBounds.width);
                            labelLayout.setHeight(labelBounds.height);
                            tLabel.setText(tailLabel);
							edge.getLabels().add(tLabel);
							edgeLabel2EditPartMap.put(tLabel, labelEditPart);
						}
					}
				}
			}
		}
	}

}
