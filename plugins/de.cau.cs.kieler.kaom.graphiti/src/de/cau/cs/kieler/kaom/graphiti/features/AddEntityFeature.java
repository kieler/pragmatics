package de.cau.cs.kieler.kaom.graphiti.features;



import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.features.IDirectEditingInfo;
import org.eclipse.graphiti.features.IFeatureProvider;
import de.cau.cs.kieler.kaom.Entity;
import de.cau.cs.kieler.kaom.KaomFactory;

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
import org.eclipse.graphiti.util.ColorConstant;
import org.eclipse.graphiti.util.IColorConstant;

public class AddEntityFeature extends AbstractAddShapeFeature {

  //  private static final IColorConstant Entity_Text_Foreground=new ColorConstant(100,100,100);
//    private static final IColorConstant Entity_Foreground = new ColorConstant(40,55,180);
  //  private static final IColorConstant Entity_Background= new ColorConstant(255,255,150);
    
    private boolean topEntityFlag;
    
    public AddEntityFeature(IFeatureProvider fp) {
        super(fp);
        topEntityFlag=false;
        // TODO Auto-generated constructor stub
    }

    public PictogramElement add(IAddContext context) {
        boolean flag;
        // TODO Auto-generated method stub
        Diagram targetdiagram;
        KaomFactory kaomFactory = KaomFactory.eINSTANCE;
        Entity entity = kaomFactory.createEntity();
        entity = (Entity) context.getNewObject();  
        ContainerShape containerShape;
        Entity parentEntity = null;
        IPeCreateService peCreateService = Graphiti.getPeCreateService();
       
        addToDiagram(entity);
       if (context.getTargetContainer() instanceof Diagram) {
            targetdiagram = (Diagram) context.getTargetContainer();
            containerShape = peCreateService.createContainerShape(targetdiagram, true); 
            PropertyUtil.setEClassShape(containerShape);
            flag = true;
            }
           
       else {
             ContainerShape parentContainerShape = context.getTargetContainer();
             //PictogramElement pe=(PictogramElement) parentContainerShape.getGraphicsAlgorithm();
            // parentEntity=(Entity) getBusinessObjectForPictogramElement(pe);
             parentEntity = (Entity) getBusinessObjectForPictogramElement(parentContainerShape);
             containerShape = peCreateService.createContainerShape(parentContainerShape, true);  
             PropertyUtil.setEClassShape(containerShape);
             flag = false;
           }
     
          int width = context.getWidth() <= 0 ? 100 : context.getWidth();
          int height = context.getHeight() <= 0 ? 50 : context.getHeight();
           IGaService gaService = Graphiti.getGaService();
           {
            Rectangle invisibleRectangle = gaService.createInvisibleRectangle(containerShape);
           if(topEntityFlag==false)
            gaService.setLocationAndSize(invisibleRectangle, context.getX(), context.getY(), width
                             + 2 * AddPortFeature.INVISIBLE_RECTANGLE_WIDTH, 
                             height + AddPortFeature.INVISIBLE_RECTANGLE_WIDTH);
           else
               gaService.setLocationAndSize(invisibleRectangle, 0, 0, 812, 800);
  //         System.out.println("hehehehehehehehe  width::::"+containerShape.getGraphicsAlgorithm().getWidth()+":::height::" +containerShape.getGraphicsAlgorithm().getHeight());
             RoundedRectangle roundedRectangle 
                 = gaService.createRoundedRectangle(invisibleRectangle, 5, 5);
              
           //     RoundedRectangle roundedRectangle=gaService.createRoundedRectangle(containerShape, 5, 5);
           //     roundedRectangle.setStyle(StyleUtil.getStyleForEClass(getDiagram()));
               
               
               
               
               // roundedRectangle.setBackground(manageColor(Entity_Background));
             //  roundedRectangle.setForeground(manageColor(Entity_Foreground));
             //  roundedRectangle.setLineWidth(3);
              roundedRectangle.setStyle(StyleUtil.getStyleForEClass(getDiagram()));
              roundedRectangle.setParentGraphicsAlgorithm(invisibleRectangle);
              if (topEntityFlag==false)
               gaService.setLocationAndSize(roundedRectangle, 6 , 0, width, height);
              else
                  {
             //     Rectangle invisibleRectangle1 = gaService.createInvisibleRectangle(containerShape);
                  gaService.setLocationAndSize(roundedRectangle, 6 , 0, 800, 800);
            //      invisibleRectangle1.setParentGraphicsAlgorithm(invisibleRectangle);
                    roundedRectangle.setBackground(manageColor(new ColorConstant(255,255,255)));
                  }
                  //   if (entity.eResource() == null){
          //       getDiagram().eResource().getContents().add(entity);
                               
           //   }
               
               peCreateService.createChopboxAnchor(containerShape);
               link(containerShape, entity);
           //    if (parentEntity != null)
           //        parentEntity.getChildEntities().add(entity);
                   

           }
           
           {
               Shape shape = peCreateService.createShape(containerShape, false);
               Polyline polyline = gaService.createPolyline(shape, new int[]{6 , 20 , width , 20});
             //  polyline.setForeground(manageColor(Entity_Foreground));
             //  polyline.setLineWidth(2);
               polyline.setStyle(StyleUtil.getStyleForEClass(getDiagram()));               
           }
           
           {
               Shape shape = peCreateService.createShape(containerShape, false);
               Text text = gaService.createDefaultText(shape, entity.getName());
            //   text.setBackground(manageColor(Entity_Text_Foreground));
               text.setStyle(StyleUtil.getStyleForEClassText(getDiagram()));
               text.setHorizontalAlignment(Orientation.ALIGNMENT_CENTER);
               text.setVerticalAlignment(Orientation.ALIGNMENT_CENTER);
               text.getFont().setBold(true);
               gaService.setLocationAndSize(text, 6, 0, width, 20);
               
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

    public boolean canAdd(IAddContext context) {
        // TODO Auto-generated method stub
        if (context.getNewObject() instanceof Entity) {
            
            if (context.getTargetContainer() instanceof Diagram 
                    || context.getTargetContainer() instanceof ContainerShape) {
                return true;
            }
        }
        return false;
       }
    
    private void addToDiagram(final Entity newEntity) {
        List<EObject> contents = getDiagram().eResource().getContents();
        Entity topEntity = null;
        for (EObject obj : contents) {
            if (obj instanceof Entity) {
                topEntity = (Entity) obj;
                topEntityFlag=false;
                break;
            }
        }
        if (topEntity == null) {
            topEntity = KaomFactory.eINSTANCE.createEntity();
            contents.add(topEntity);
            topEntityFlag=true;
        }
        topEntity.getChildEntities().add(newEntity);
    }
    

}
