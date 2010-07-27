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
import org.eclipse.graphiti.ui.editor.DiagramEditor;
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
    
    private Map<Connection, KEdge> reference2EdgeMap = new HashMap<Connection, KEdge>();
    
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
        TransactionalEditingDomain editingDomain = diagramEditor.getEditingDomain();
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
                 
                     
             
          diagramEditor.getDiagramTypeProvider().getFeatureProvider()
              .layoutIfPossible(new LayoutContext(pelem));
            
                 
                 }
         }
      } 
    }  
                  
                  
                 
                  for (Map.Entry<Connection, KEdge> entryLink 
                          : reference2EdgeMap.entrySet()) {
                      KEdge edge = entryLink.getValue();
                      Connection conn = entryLink.getKey();
                      
                      KEdgeLayout edgeLayout = KimlLayoutUtil.getEdgeLayout(edge);
                       EList<org.eclipse.graphiti.mm.datatypes.Point> pointList = 
                           ((FreeFormConnection) conn).getBendpoints();
                  
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
       }
    
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
          //  processLabels(connection, edge,EdgeLabelPlacement.UNDEFINED);
        }  
    
   
    
    }
    
       
}
