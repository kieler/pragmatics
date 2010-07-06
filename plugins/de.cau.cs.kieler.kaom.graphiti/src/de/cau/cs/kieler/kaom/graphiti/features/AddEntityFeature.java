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
package de.cau.cs.kieler.kaom.graphiti.features;



//import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.features.IDirectEditingInfo;
import org.eclipse.graphiti.features.IFeatureProvider;
import de.cau.cs.kieler.kaom.Entity;
import de.cau.cs.kieler.kaom.KaomFactory;
import de.cau.cs.kieler.kaom.graphiti.util.PropertyUtil;
import de.cau.cs.kieler.kaom.graphiti.util.StyleUtil;
import de.cau.cs.kieler.kaom.graphiti.util.TopParentEntity;

import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.impl.AbstractAddShapeFeature;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.Orientation;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Polyline;
import org.eclipse.graphiti.mm.pictograms.Rectangle;
import org.eclipse.graphiti.mm.pictograms.RoundedRectangle;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.mm.pictograms.Text;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeCreateService;

/**
 * 
 * @author atr
 * Adds a new Entity which is contained within the parent Entity
 *
 */
public class AddEntityFeature extends AbstractAddShapeFeature {

  //  private static final IColorConstant Entity_Text_Foreground=new ColorConstant(100,100,100);
//    private static final IColorConstant Entity_Foreground = new ColorConstant(40,55,180);
  //  private static final IColorConstant Entity_Background= new ColorConstant(255,255,150);
    
//  public static boolean topEntityFlag;
    
    private static final int MIN_WIDTH = 100;
    private static final int MIN_HEIGHT = 50;
    private static final int RECTANGLE_CURVE = 5;
    private static final int BOUNDARY_DISTANCE = 6;
    private static final int DISTANCE_FROM_TOP = 20;
    
 
    
    
    /**
     * @param fp
     * Constructor.
     */
    public AddEntityFeature(final IFeatureProvider fp) {
        super(fp);
      //  topEntityFlag=false;
        // TODO Auto-generated constructor stub
    }

    /**
     * 
     * {@inheritDoc}
     */
    public PictogramElement add(final IAddContext context) {
  
        // TODO Auto-generated method stub
        Diagram targetdiagram;
        KaomFactory kaomFactory = KaomFactory.eINSTANCE;
        Entity entity = kaomFactory.createEntity();
        entity = (Entity) context.getNewObject();  
        ContainerShape containerShape;
   //    Entity parentEntity = null;
        IPeCreateService peCreateService = Graphiti.getPeCreateService();
       
        addToDiagram(entity, context);
       if (context.getTargetContainer() instanceof Diagram) {
            targetdiagram = (Diagram) context.getTargetContainer();
            containerShape = peCreateService.createContainerShape(targetdiagram, true); 
            PropertyUtil.setEClassShape(containerShape);
  
            } else {
             ContainerShape parentContainerShape = context.getTargetContainer();
             //PictogramElement pe=(PictogramElement) parentContainerShape.getGraphicsAlgorithm();
            // parentEntity=(Entity) getBusinessObjectForPictogramElement(pe);
       //      parentEntity = (Entity) getBusinessObjectForPictogramElement(parentContainerShape);
             containerShape = peCreateService.createContainerShape(parentContainerShape, true);  
             PropertyUtil.setEClassShape(containerShape);

           }
       
             
          int width = context.getWidth() <= 0 ? MIN_WIDTH : context.getWidth();
          int height = context.getHeight() <= 0 ? MIN_HEIGHT : context.getHeight();
           IGaService gaService = Graphiti.getGaService();
           {
            Rectangle invisibleRectangle = gaService.createInvisibleRectangle(containerShape);
            
        //   if(topEntityFlag==false)
            gaService.setLocationAndSize(invisibleRectangle, context.getX(), context.getY(), width
                             + 2 * AddPortFeature.INVISIBLE_RECTANGLE_WIDTH, 
                             height + AddPortFeature.INVISIBLE_RECTANGLE_WIDTH);
      /*     else
           {
               Iterator iter = containerShape.getChildren().iterator();
               while (iter.hasNext()) {
                   Shape shape = (Shape) iter.next();
                   GraphicsAlgorithm ga = shape.getGraphicsAlgorithm();
                   IGaService gaService1 = Graphiti.getGaService();
                   IDimension size = gaService.calculateSize(ga);
                   width1=size.getWidth();
                   height1=size.getHeight();
               gaService.setLocationAndSize(invisibleRectangle, 0, 0,size.getWidth(),size.getHeight());
               }
           }*/
       ////      RoundedRectangle roundedRectangle 
       ////          = gaService.createRoundedRectangle(invisibleRectangle, 5, 5);
              
           //     RoundedRectangle roundedRectangle=gaService.
          //  createRoundedRectangle(containerShape, 5, 5);
           //     roundedRectangle.setStyle(StyleUtil.getStyleForEClass(getDiagram()));
               
               
               
               
               // roundedRectangle.setBackground(manageColor(Entity_Background));
             //  roundedRectangle.setForeground(manageColor(Entity_Foreground));
             //  roundedRectangle.setLineWidth(3);
             
           //   if (topEntityFlag==false)
              {
              RoundedRectangle roundedRectangle = gaService.
                  createRoundedRectangle(invisibleRectangle, RECTANGLE_CURVE, RECTANGLE_CURVE);
              gaService.setLocationAndSize(roundedRectangle, BOUNDARY_DISTANCE , 0, width, height);
              roundedRectangle.setStyle(StyleUtil.getStyleForEClass(getDiagram()));
              roundedRectangle.setParentGraphicsAlgorithm(invisibleRectangle);
              }
           /*   else
                  {
                  Rectangle invisibleRectangle1 = gaService.createInvisibleRectangle(containerShape);
                  gaService.setLocationAndSize(invisibleRectangle1, 6 , 0,800, 600);
            //      invisibleRectangle1.setParentGraphicsAlgorithm(invisibleRectangle);
               //     roundedRectangle.setBackground(manageColor(new ColorConstant(255,255,255)));
                  }
                  //   if (entity.eResource() == null){
          //       getDiagram().eResource().getContents().add(entity);
                               
           //   }
               
             */
              peCreateService.createChopboxAnchor(containerShape);
               link(containerShape, entity);
           //    if (parentEntity != null)
           //        parentEntity.getChildEntities().add(entity);
                   

           } 
           
           {
               Shape shape = peCreateService.createShape(containerShape, false);
          //     if (topEntityFlag==false) {
               Polyline polyline = gaService.createPolyline(
                       shape, new int[]{BOUNDARY_DISTANCE , DISTANCE_FROM_TOP 
                               , width , DISTANCE_FROM_TOP});
             //  polyline.setForeground(manageColor(Entity_Foreground));
             //  polyline.setLineWidth(2);
               polyline.setStyle(StyleUtil.getStyleForEClass(getDiagram()));               
          //     }
           }
           
         ////  if (topEntityFlag==false)
           {
               Shape shape = peCreateService.createShape(containerShape, false);
               Text text = gaService.createDefaultText(shape, entity.getName());
            //   text.setBackground(manageColor(Entity_Text_Foreground));
               text.setStyle(StyleUtil.getStyleForEClassText(getDiagram()));
               text.setHorizontalAlignment(Orientation.ALIGNMENT_CENTER);
               text.setVerticalAlignment(Orientation.ALIGNMENT_CENTER);
               text.getFont().setBold(true);
            //   if (topEntityFlag==false)
               gaService.setLocationAndSize(text, BOUNDARY_DISTANCE, 0, width, DISTANCE_FROM_TOP);
             
                   
               link(shape, entity);
               
               IDirectEditingInfo directEditingInfo = getFeatureProvider().getDirectEditingInfo();

               // set container shape for direct editing after object creation

               directEditingInfo.setMainPictogramElement(containerShape);

               // set shape and graphics algorithm where the editor for
               // direct editing shall be opened after object creation

               directEditingInfo.setPictogramElement(shape);
               directEditingInfo.setGraphicsAlgorithm(text);
                          }
         
           
           layoutPictogramElement(containerShape);
           containerShape.setActive(true);
           return containerShape;
    
    }

    /**
     * {@inheritDoc}
     */
    public boolean canAdd(final IAddContext context) {
        // TODO Auto-generated method stub
        if (context.getNewObject() instanceof Entity) {
            
            if (context.getTargetContainer() instanceof Diagram 
                    || context.getTargetContainer() instanceof ContainerShape) {
                return true;
            }
        }
        return false;
       }
    
    /**
     * 
     * @param newEntity
     * @param context
     * Adds the new entity formed to its container
     */
    private void addToDiagram(final Entity newEntity, final IAddContext context) {
        List<EObject> contents = getDiagram().eResource().getContents();
        Entity topEntity = null;
        if (context.getTargetContainer() instanceof Diagram) {
        for (EObject obj : contents) {
            if (obj instanceof Entity) {
                topEntity = (Entity) obj;
                   break;
                }
            } 
        } else {
       
            topEntity = (Entity) getBusinessObjectForPictogramElement(context.getTargetContainer());
        }
        
        
        if (topEntity == null) {
      //    if(TopParentEntity.parentEntity == null)
              topEntity = TopParentEntity.createParentEntity(getDiagram());
          //  topEntity = KaomFactory.eINSTANCE.createEntity();
          //  contents.add(topEntity);
            //topEntity=DiagramTypeProvider.topEntity;
          //  topEntityFlag = true;
        }
      
        topEntity.getChildEntities().add(newEntity);
    }
    

}
