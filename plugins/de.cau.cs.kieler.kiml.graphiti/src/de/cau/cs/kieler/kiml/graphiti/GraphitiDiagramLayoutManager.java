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
package de.cau.cs.kieler.kiml.graphiti;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;


import org.eclipse.graphiti.mm.datatypes.Point;
//import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.ConnectionLocator;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.CommandStack;
//import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
//import org.eclipse.gmf.runtime.diagram.ui.editparts.AbstractBorderItemEditPart;
//import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart;
//import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.graphiti.features.ILayoutFeature;
import org.eclipse.graphiti.features.context.ILayoutContext;
import org.eclipse.graphiti.features.context.impl.LayoutContext;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ConnectionDecorator;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.FreeFormConnection;
import org.eclipse.graphiti.mm.pictograms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Polygon;
import org.eclipse.graphiti.mm.pictograms.RoundedRectangle;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.mm.pictograms.Text;
import org.eclipse.graphiti.mm.pictograms.impl.RectangleImpl;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.ui.internal.editor.DiagramEditor;
import org.eclipse.graphiti.ui.internal.parts.IPictogramElementEditPart;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Link;
import org.eclipse.ui.IEditorPart;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
//import de.cau.cs.kieler.kiml.gmf.GmfLayoutInspector;
//import de.cau.cs.kieler.kiml.gmf.GmfLayoutInspector;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutDataFactory;
import de.cau.cs.kieler.kiml.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.EdgeLabelPlacement;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.ui.IEditorChangeListener;
import de.cau.cs.kieler.kiml.ui.layout.DiagramLayoutManager;
import de.cau.cs.kieler.kiml.ui.layout.ICachedLayout;
import de.cau.cs.kieler.kiml.ui.layout.ILayoutInspector;
import de.cau.cs.kieler.kiml.ui.util.KimlUiUtil;
import de.cau.cs.kieler.kiml.util.KimlLayoutUtil;



/**
 * @author atr
 *
 */
public class GraphitiDiagramLayoutManager extends DiagramLayoutManager {

    private DiagramEditor diagramEditor;
    
    private KNode layoutGraph;
    
    private Map<PictogramElement, KGraphElement> editPart2GraphElemMap
    = new HashMap<PictogramElement, KGraphElement>();
    
    Map<Connection, KEdge> reference2EdgeMap = new HashMap<Connection, KEdge>();
    
    private Map<PictogramElement, int[]> GraphElemHeightWidth
    = new HashMap<PictogramElement, int[]>();  
    
    private LinkedList<Connection> connections = new LinkedList<Connection>();

    
    /**
     * {@inheritDoc}
     */
    @Override
    public EditPart getCurrentEditPart() {
        // TODO Auto-generated method stub
        return null;
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
    public KNode buildLayoutGraph(final IEditorPart editorPart, final EditPart editPart, 
            final boolean layoutAncestors) {
        
        connections.clear();
        reference2EdgeMap.clear();
        editPart2GraphElemMap.clear();
        
        
        if (editorPart instanceof DiagramEditor) {
            diagramEditor = (DiagramEditor) editorPart;
         
            EditPart topEditPart = diagramEditor.getGraphicalViewer().getContents();
            if (topEditPart instanceof IPictogramElementEditPart) {
                PictogramElement element = ((IPictogramElementEditPart) topEditPart)
                        .getPictogramElement();
                if (element != null) {
                    System.out.println("Element not null");
                    KNode topNode = KimlLayoutUtil.createInitializedNode();
                    KShapeLayout shapeLayout = KimlLayoutUtil.getShapeLayout(topNode);
                    shapeLayout.setXpos(element.getGraphicsAlgorithm().getX());
                    shapeLayout.setYpos(element.getGraphicsAlgorithm().getY());
                    shapeLayout.setHeight(element.getGraphicsAlgorithm().getHeight());
                    shapeLayout.setWidth(element.getGraphicsAlgorithm().getWidth());
                   

                 editPart2GraphElemMap.put(element, topNode);
                 GraphElemHeightWidth.put(element, new int[]{element.getGraphicsAlgorithm().getHeight(),
                        element.getGraphicsAlgorithm().getWidth()});
                 boolean state = buildLayoutGraphRecursively(element, topNode, element);
                 layoutGraph = topNode;
                }
                
                
            }
        }
        
        processConnections();
        printout();
        return layoutGraph;
    }
    
    
    private boolean buildLayoutGraphRecursively(final PictogramElement currentElement, 
            final KNode topNode, 
            final PictogramElement topElement) {
        EList<Shape> list = null;
        boolean returnstate = true;
        if (topNode != null) {
           
            
            
            
            if (currentElement instanceof Diagram) {
            list = ((Diagram) currentElement).getChildren();
            } else if (currentElement instanceof ContainerShape) {
                                
                list = ((ContainerShape) currentElement).getChildren();
            }
            
           if (list != null) {
            System.out.println("SIZE:" + list.size());
           }
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) instanceof ContainerShape) {
                    ContainerShape cs = (ContainerShape) list.get(i);
                    GraphicsAlgorithm containerGa = cs.getGraphicsAlgorithm();

                    
//                    if (containerGa instanceof Polygon) {
//                        KNode relation = KimlLayoutUtil.createInitializedNode();
//                        editPart2GraphElemMap.put(cs , relation);
//                        relation.setParent(topNode);
//                        KShapeLayout shapeLayout = KimlLayoutUtil.getShapeLayout(topNode);
//                        shapeLayout.setXpos(cs.getGraphicsAlgorithm().getX());
//                        shapeLayout.setYpos(cs.getGraphicsAlgorithm().getY());
//                        shapeLayout.setHeight(cs.getGraphicsAlgorithm().getHeight());
//                        shapeLayout.setWidth(cs.getGraphicsAlgorithm().getWidth());
//                        
//                    } else if (cs instanceof RoundedRectangle 
//                            || containerGa.getPictogramElement() instanceof Rectangle) {
                     
                        KNode childnode = KimlLayoutUtil.createInitializedNode();
                        returnstate = false; 
                        
                        childnode.setParent(topNode);
                        KShapeLayout shapeLayout = KimlLayoutUtil.getShapeLayout(childnode);
                        shapeLayout.setXpos(containerGa.getX());
                        shapeLayout.setYpos(containerGa.getY());
                        shapeLayout.setHeight(containerGa.getHeight());
                        shapeLayout.setWidth(containerGa.getWidth());
                        
                        // TODO how to get minimal size of the shape
                        LayoutOptions.setFloat(shapeLayout, LayoutOptions.MIN_WIDTH, 20.0f);
                        LayoutOptions.setFloat(shapeLayout, LayoutOptions.MIN_HEIGHT, 20.0f);
                        
                        System.out.println("Width:" + shapeLayout.getWidth());
                        System.out.println("Height:" + shapeLayout.getHeight());
                        editPart2GraphElemMap.put(cs , childnode);
                        GraphElemHeightWidth.put(cs, new int[]{containerGa.getHeight(), 
                               containerGa.getWidth()});
                        boolean state = buildLayoutGraphRecursively(cs, childnode, topElement);
                        
                        LayoutOptions.setBoolean(shapeLayout, LayoutOptions.FIXED_SIZE, state);
//                    }
                    if (cs.getAnchors().size() != 0) {
                        EList<Anchor> childAnchors = cs.getAnchors();
                        for (int j = 0; j < childAnchors.size(); j++) {
                            if (childAnchors.get(j).getReferencedGraphicsAlgorithm() != null) {
                                KPort port = KimlLayoutUtil.createInitializedPort();
                                editPart2GraphElemMap.put(childAnchors.get(j), port);
                                port.setNode(topNode);
                                KShapeLayout portLayout = KimlLayoutUtil.getShapeLayout(port);
                                // Rectangle portBounds = borderItem.getFigure().getBounds();
                    //  KShapeLayout nodeLayout = KimlLayoutUtil.getShapeLayout(parentLayoutNode);
                                portLayout.setXpos(childAnchors.get(j).getGraphicsAlgorithm().getX());
                                portLayout.setYpos(childAnchors.get(j).getGraphicsAlgorithm().getY());
                                portLayout.setWidth(childAnchors.get(j)
                                        .getGraphicsAlgorithm().getWidth());
                                portLayout.setHeight(childAnchors.get(j)
                                        .getGraphicsAlgorithm().getHeight());
                                EList<Connection> conn = 
                                    childAnchors.get(j).getOutgoingConnections();
                                     for (int k = 0; k < conn.size(); k++) {
                                         connections.add(conn.get(k));
                                         
                                     }
                                } else {
                                   EList<Connection> conn = 
                                   childAnchors.get(j).getOutgoingConnections();
                                    for (int k = 0; k < conn.size(); k++) {
                                        connections.add(conn.get(k));
                                    }
                                }
                                
                            }
                        //  hasPorts = true;
                        // set user defined layout options for the port
                      //  GmfLayoutInspector.setLayoutOptions(borderItem, portLayout, true);
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
        // TODO Auto-generated method stub
        if (editPart instanceof IPictogramElementEditPart) {
            return new GraphitiLayoutInspector((IPictogramElementEditPart) editPart);
       } else {
            return null;
           }
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
      printout();
        TransactionalEditingDomain editingDomain = diagramEditor.getTransactionalEditingDomain();
        KimlUiUtil.runModelChange(new Runnable() {
            public void run() {
                // TODO Auto-generated method stub
                  for (Map.Entry<PictogramElement, KGraphElement> entry 
                        : editPart2GraphElemMap.entrySet()) {
                    PictogramElement pelem = entry.getKey();
                    KGraphElement kelem = entry.getValue();
                    KShapeLayout shapeLayout = KimlLayoutUtil.getShapeLayout(kelem);
                    pelem.getGraphicsAlgorithm().setX((int) shapeLayout.getXpos());
                    pelem.getGraphicsAlgorithm().setY((int) shapeLayout.getYpos());
                    pelem.getGraphicsAlgorithm().setHeight((int) shapeLayout.getHeight());
                    pelem.getGraphicsAlgorithm().setWidth((int) shapeLayout.getWidth());
                    
      if (pelem instanceof ContainerShape || pelem instanceof Diagram) {
         if (GraphElemHeightWidth.containsKey(pelem)) {
             int[] olddimension = GraphElemHeightWidth.get(pelem);
             if (olddimension[0] != pelem.getGraphicsAlgorithm().getHeight() 
                     || olddimension[1] != pelem.getGraphicsAlgorithm().getWidth()) {
                 
        /*     EList<GraphicsAlgorithm> listgrapAlgoChild = 
                 pelem.getGraphicsAlgorithm().getGraphicsAlgorithmChildren();
            
                 int heightIncr = pelem.getGraphicsAlgorithm().getHeight() - olddimension[0];
                 int widthIncr = pelem.getGraphicsAlgorithm().getWidth() - olddimension[1];
                
                 for (int i = 0; i < listgrapAlgoChild.size(); i++) {
                   listgrapAlgoChild.get(i).setHeight(listgrapAlgoChild.get(i).getHeight() + heightIncr);
                   listgrapAlgoChild.get(i).setWidth(listgrapAlgoChild.get(i).getWidth() + widthIncr);
                 
                 }
                EList<Shape> listchild = ((ContainerShape) pelem).getChildren();
                if (pelem instanceof Diagram) {
                    listchild = ((Diagram) pelem).getChildren();
                    } else if (pelem instanceof ContainerShape) {
                                        
                        listchild = ((ContainerShape) pelem).getChildren();
                    }
                
                for(int i = 0; i < listchild.size() ; i++)
                {
               //     if (!(listchild.get(i) instanceof ContainerShape)) {
              //          if (listchild.get(i).getGraphicsAlgorithm().getHeight()!=null)
                            
                        
                    
               // }
                   
                
             }
             */
                 
        //  ILayoutFeature layoutFeature = diagramEditor.getDiagramTypeProvider().
         //     getFeatureProvider().getLayoutFeature(new LayoutContext(pelem));
                 
          diagramEditor.getDiagramTypeProvider().getFeatureProvider().layoutIfPossible(new LayoutContext(pelem));
         // layoutFeature.       
                 
                 }
             
          
             
                 }
             
                } 
         }  
                  
                  
                  System.out.println("size;;;;;;;:"+reference2EdgeMap.size());
                  for (Map.Entry<Connection, KEdge> entryLink 
                          : reference2EdgeMap.entrySet()) {
                      KEdge edge = entryLink.getValue();
                      Connection conn = entryLink.getKey();
                      
                      KEdgeLayout edgeLayout = KimlLayoutUtil.getEdgeLayout(edge);
                      //((FreeFormConnection)connection).getStart().
                       EList<org.eclipse.graphiti.mm.datatypes.Point> pointList = 
                           ((FreeFormConnection) conn).getBendpoints();
                    /*   KPoint sourcePoint = edgeLayout.getSourcePoint();
                       Point firstPoint = pointList.getFirstPoint();
                       sourcePoint.setX(firstPoint.x);
                       sourcePoint.setY(firstPoint.y);
                                        
                      */ 
                       pointList.clear();
                       
                       EList<KPoint> points = edgeLayout.getBendPoints();
                       
                       for (int i = 0; i < points.size(); i++) {
                          Point point = Graphiti.getGaService().createPoint(
                                  (int) points.get(i).getX(), (int) points.get(i).getY());
                          System.out.println("I:" + i + "X:" + points.get(i).getX() + "Y:"
                                  + points.get(i).getY());
                          pointList.add(point);
                       }
                       
                  }      
     
                  
            } 
        }, editingDomain, "Automatic Layout");
        // TODO Auto-generated method stub
        // get a command stack to execute the command
      /*  CommandStack commandStack = null;
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
    */ }
    
    private void printout()  {
        for (Map.Entry<PictogramElement, KGraphElement> entry 
                : editPart2GraphElemMap.entrySet()) {
         
            KGraphElement kelem = entry.getValue();
            KShapeLayout shapeLayout = KimlLayoutUtil.getShapeLayout(kelem);
            System.out.println("Width::::" + shapeLayout.getWidth());
            System.out.println("Height::::" + shapeLayout.getHeight());
            System.out.println("X::::" + shapeLayout.getXpos() + "Y::::" + shapeLayout.getYpos());
            
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public KNode getLayoutGraph() {
        // TODO Auto-generated method stub
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
     * {@inheritDoc}
     */
    @Override
    public void addChangeListener(final IEditorPart editorPart, final IEditorChangeListener listener) {
        // TODO Auto-generated method stub
        
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeChangeListener(final IEditorChangeListener listener) {
        // TODO Auto-generated method stub
        
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ISelection getSelection(final IEditorPart editorPart) {
        // TODO Auto-generated method stub
        return null;
    }
    
   
    /**
     * Adds all target connections and connected connections to the list of connections
     * that must be processed later.
     * 
     * @param editPart an edit part
     */
   /*   private void addConnections(final PictogramElement editPart) {
        for (Object targetConn : editPart.getTargetConnections()) {
            if (targetConn instanceof ConnectionEditPart) {
                ConnectionEditPart connectionEditPart = (ConnectionEditPart) targetConn;
                connections.add(connectionEditPart);
                addConnections(connectionEditPart);
            }
        }
    }
*/
    /**
     * Creates new edges and takes care of the labels for each connection
     * identified in the {@code buildLayoutGraphRecursively} method.
     */
    private void processConnections() {
       
        for (Connection connection : connections) {
            KEdge edge = KimlLayoutUtil.createInitializedEdge();
            reference2EdgeMap.put(connection, edge);
         /*  // EObject modelObject = connection.getNotationView().getElement();
           // if (modelObject instanceof EReference) {
                EReference reference = (EReference) modelObject;
                edge = reference2EdgeMap.get(reference.getEOpposite());
                if (edge != null) {
                    processLabels(connection, edge, EdgeLabelPlacement.TAIL);
                    continue;
                }
                edge = KimlLayoutUtil.createInitializedEdge();
                reference2EdgeMap.put(reference, edge);
            } else {
                edge = KimlLayoutUtil.createInitializedEdge();
            }
           */ 
            KNode sourceNode = null, targetNode = null;
            KPort sourcePort = null, targetPort = null;

         /*   EditPart sourceObj = (EditPart) ((KEdge) connection).getSource();
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
          */
            if (connection.getEnd().getReferencedGraphicsAlgorithm() == null) {
              
                    if (editPart2GraphElemMap.containsKey(connection.getEnd().getParent())) {
                        targetNode = (KNode) editPart2GraphElemMap.get(connection.getEnd().getParent());
                        edge.setTarget(targetNode);
                    }
            } else {
                    if (editPart2GraphElemMap.containsKey(connection.getEnd())) {
                        targetPort = (KPort) editPart2GraphElemMap.get(connection.getEnd());
                        edge.setTargetPort(targetPort);
                    }
            }
            
            if (connection.getStart().getReferencedGraphicsAlgorithm() == null) {
           
                    if (editPart2GraphElemMap.containsKey(connection.getStart().getParent())) {
                       sourceNode = (KNode) editPart2GraphElemMap.get(connection.getStart().getParent());
                       edge.setSource(sourceNode);
                    }
            } else {
                
                 if (editPart2GraphElemMap.containsKey(connection.getStart())) {
                    sourcePort = (KPort) editPart2GraphElemMap.get(connection.getStart());
                    edge.setSourcePort(sourcePort);
                }
            }
                
            /* EditPart targetObj = connection.getTarget();
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
            }*/

          
           // graphElem2EditPartMap.put(edge, connection);
  

            KEdgeLayout edgeLayout = KimlLayoutUtil.getEdgeLayout(edge);
           //((FreeFormConnection)connection).getStart().
            EList<Point> pointList = ((FreeFormConnection) connection).getBendpoints();
          
           KPoint sourcePoint = edgeLayout.getSourcePoint();
           GraphicsAlgorithm ga = connection.getStart().getParent().getGraphicsAlgorithm();
            sourcePoint.setX(ga.getX() + ga.getWidth() / 2);
            sourcePoint.setY(ga.getY() + ga.getHeight() / 2);
            
            KPoint targetPoint = edgeLayout.getTargetPoint();
            ga = connection.getEnd().getParent().getGraphicsAlgorithm();
             targetPoint.setX(ga.getX() + ga.getWidth() / 2);
             targetPoint.setY(ga.getY() + ga.getHeight() / 2);
         
            for (int i = 0; i < pointList.size(); i++) {
                
                KPoint kpoint = KLayoutDataFactory.eINSTANCE.createKPoint();
                kpoint.setX(pointList.get(i).getX());
                kpoint.setY(pointList.get(i).getY());
                edgeLayout.getBendPoints().add(kpoint);
                System.out.println("I:"+i+"X:"+pointList.get(i).getX()+"Y:"+pointList.get(i).getY());
            }
            /*
            KPoint targetPoint = edgeLayout.getTargetPoint();
            Point lastPoint = pointList.getLastPoint();
            targetPoint.setX(lastPoint.x);
            targetPoint.setY(lastPoint.y);
             */
            // set user defined layout options for the edge
            // GmfLayoutInspector.setLayoutOptions(connection, edgeLayout, true);
        //    LayoutOptions.setBoolean(connection, edgeLayout, true);
            
            // processLabels(connection, edge, EdgeLabelPlacement.UNDEFINED);
            //   }

         // process edge labels
            processLabels(connection,edge,EdgeLabelPlacement.UNDEFINED);
        }  
    
    System.out.println("size b4 ;;;;;;" + reference2EdgeMap.size());
    
    }
    
    private void processLabels(final Connection conn, final KEdge edge, 
            final EdgeLabelPlacement placement) {
        
        EList<ConnectionDecorator> connDecorator = conn.getConnectionDecorators();
        for (int i = 0; i < connDecorator.size(); i++) {
            if (connDecorator.get(i).getGraphicsAlgorithm() instanceof Text) {
                
            }
        }
       /* for (Object obj : connection.getChildren()) {
            if (obj instanceof LabelEditPart) {
                LabelEditPart labelEditPart = (LabelEditPart) obj;
                IFigure labelFigure = labelEditPart.getFigure();
                Rectangle labelBounds = labelFigure.getBounds();
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
                if (labelText != null && labelText.length() > 0) {
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
                    labelLayout.setXpos(labelBounds.x);
                    labelLayout.setYpos(labelBounds.y);
                    if (iconBounds != null) {
                        labelLayout.setWidth(labelBounds.width + iconBounds.width);
                    }
                    labelLayout.setWidth(labelBounds.width);
                    labelLayout.setHeight(labelBounds.height);
                    label.setText(labelText);
                    edge.getLabels().add(label);
                    graphElem2EditPartMap.put(label, labelEditPart);
                }
            }
        }
        */
    }
    
}
