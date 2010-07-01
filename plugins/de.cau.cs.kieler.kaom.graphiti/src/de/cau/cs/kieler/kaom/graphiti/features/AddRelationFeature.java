package de.cau.cs.kieler.kaom.graphiti.features;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.impl.AbstractAddShapeFeature;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Polygon;
import org.eclipse.graphiti.mm.pictograms.Rectangle;
import org.eclipse.graphiti.mm.pictograms.RoundedRectangle;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeCreateService;
import org.eclipse.graphiti.util.ColorConstant;

import de.cau.cs.kieler.kaom.Entity;
import de.cau.cs.kieler.kaom.KaomFactory;
import de.cau.cs.kieler.kaom.Relation;
import de.cau.cs.kieler.kaom.graphiti.util.TopParentEntity;

public class AddRelationFeature extends AbstractAddShapeFeature {

    public AddRelationFeature(final IFeatureProvider fp) {
        super(fp);
        // TODO Auto-generated constructor stub
    }

    public PictogramElement add(final IAddContext context) {
        // TODO Auto-generated method stub
     //   KaomFactory kaomFactory=KaomFactory.eINSTANCE;
       
     //   Relation relation=kaomFactory.createRelation();
       Relation relation = (Relation) context.getNewObject();
        IPeCreateService peCreateService = Graphiti.getPeCreateService();
        Diagram targetDiagram;
        ContainerShape containerShape;
        IGaService gaService = Graphiti.getGaService();
        if (context.getTargetContainer() instanceof Diagram) {
            targetDiagram = (Diagram) context.getTargetContainer();
            containerShape = peCreateService.createContainerShape(targetDiagram, true); 
           // PropertyUtil.setEClassShape(containerShape);
          //  flag = true;
            
           // Polygon polygon = gaService.createPolygon(containerShape, 
           //         new int[]{context.getX() - 9 , context.getY() , context.getX(),
           //         context.getY() + 12 , context.getX() + 9 , context.getY(),
           //         context.getX(), context.getY() - 12});    
            
            Polygon polygon = gaService.createPolygon(containerShape, 
                     new int[]{- 9 , 0 , 0, 12 , 9 , 0 , 0 , - 12});    
            
             //       new int[]{0 , -12 , 9, 0 , 18 , -12 , 9 , - 24});    
            
                    
            polygon.setStyle(StyleUtil.getStyleForEClass(getDiagram()));
            polygon.setBackground(manageColor(new ColorConstant(70, 70, 70)));
         //   gaService.setLocationAndSize(polygon,context.getX(),context.getY(),70,80);
            System.out.println("X::" + context.getX() + "Y::" + context.getY());
            Graphiti.getGaService()
            .setLocation(containerShape.getGraphicsAlgorithm(), 
                    context.getX(), context.getY(), false);
           
            addToDiagram(relation, context);
       
            peCreateService.createChopboxAnchor(containerShape);          
            
            link(containerShape, relation);
           
            }
           
       else {
            containerShape = context.getTargetContainer();
             //PictogramElement pe=(PictogramElement) parentContainerShape.getGraphicsAlgorithm();
            // parentEntity=(Entity) getBusinessObjectForPictogramElement(pe);
          //   parentEntity = (Entity) getBusinessObjectForPictogramElement(parentContainerShape);
           //  containerShape = peCreateService.createContainerShape(parentContainerShape, true);  
          //   PropertyUtil.setEClassShape(containerShape);
           //  flag = false;
            ContainerShape childcontainershape = peCreateService.createContainerShape(containerShape, true);   
            //  Entity parentEntity = null;
              //Shape shape = peCreateService.createShape(containerShape, true);
            
             // RoundedRectangle polygon=gaService.createRoundedRectangle(containerShape, 5, 5);
              Polygon polygon = gaService.createPolygon(childcontainershape, 
            //          new int[]{context.getX() - 9 , context.getY() , context.getX(),
            //          context.getY() + 12 , context.getX() + 9 , context.getY(),
            //          context.getX(), context.getY() - 12});        
            
                      new int[]{- 9 , 0 , 0, 12 , 9 , 0 , 0 , - 12});    
                      
                      polygon.setStyle(StyleUtil.getStyleForEClass(getDiagram()));
              polygon.setBackground(manageColor(new ColorConstant(70, 70, 70)));
              Graphiti.getGaService()
              .setLocation(childcontainershape.getGraphicsAlgorithm(), 
                      context.getX(), context.getY(), false);

             
              addToDiagram(relation, context);
         
              peCreateService.createChopboxAnchor(childcontainershape);          
              
               link(childcontainershape, relation);
               
       }
        
        
        
   /*     Shape shape = peCreateService.createShape(containerShape, true);   
      //  Entity parentEntity = null;
        //Shape shape = peCreateService.createShape(containerShape, true);
      
       // RoundedRectangle polygon=gaService.createRoundedRectangle(containerShape, 5, 5);
        Polygon polygon = gaService.createPolygon(shape, 
                new int[]{context.getX() - 9 , context.getY() , context.getX(),
                context.getY() + 12 , context.getX() + 9 , context.getY(),
                context.getX(), context.getY() - 12});        
       
         
      //   layoutPictogramElement(containerShape);        */       
       
         return containerShape;
    }

    public boolean canAdd(IAddContext context) {
        // TODO Auto-generated method stub
        if (context.getNewObject() instanceof Relation) {
            
            if (context.getTargetContainer() instanceof ContainerShape
                    || context.getTargetContainer() instanceof Diagram) {
                return true;
            }
        }
        return false;
    }

    private void addToDiagram(final Relation newRelation , final IAddContext context) {
        List<EObject> contents = getDiagram().eResource().getContents();
        Entity topEntity = null;
        if (context.getTargetContainer() instanceof Diagram) {
        for (EObject obj : contents) {
            if (obj instanceof Entity) {
                topEntity = (Entity) obj;
                 break;
                }
            }
        }
        else {
                    Object ob = getBusinessObjectForPictogramElement(context.getTargetContainer());
                    if (ob instanceof Entity)  {
                        topEntity = (Entity) ob;
                      }
               
                    }         
        
        if (topEntity == null) {
                topEntity = TopParentEntity.createParentEntity(getDiagram());
              }
       
        topEntity.getChildRelations().add(newRelation);
    }

}
