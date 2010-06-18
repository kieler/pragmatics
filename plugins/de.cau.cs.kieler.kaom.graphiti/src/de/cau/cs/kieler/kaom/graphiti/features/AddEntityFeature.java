package de.cau.cs.kieler.kaom.graphiti.features;


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
import org.eclipse.graphiti.mm.pictograms.RoundedRectangle;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.mm.pictograms.Text;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeCreateService;
import org.eclipse.graphiti.util.ColorConstant;
import org.eclipse.graphiti.util.IColorConstant;

public class AddEntityFeature extends AbstractAddShapeFeature {

    private static final IColorConstant Entity_Text_Foreground=new ColorConstant(100,100,100);
    private static final IColorConstant Entity_Foreground = new ColorConstant(40,55,180);
    private static final IColorConstant Entity_Background= new ColorConstant(255,255,150);
    
    public AddEntityFeature(IFeatureProvider fp) {
        super(fp);
        // TODO Auto-generated constructor stub
    }

    public PictogramElement add(IAddContext context) {
        boolean flag;
        // TODO Auto-generated method stub
        Diagram targetdiagram;
        KaomFactory kaomFactory=KaomFactory.eINSTANCE;
        Entity entity=kaomFactory.createEntity();
        entity = (Entity)context.getNewObject();  
        ContainerShape containerShape;
        IPeCreateService peCreateService=Graphiti.getPeCreateService();
        
       if(context.getTargetContainer() instanceof Diagram)
        {
            targetdiagram=(Diagram)context.getTargetContainer();
            containerShape=peCreateService.createContainerShape(targetdiagram, true); 
            flag=true;
        }
       else //if(context.getNewObject() instanceof ContainerShape){
           {
          
             containerShape=peCreateService.createContainerShape((ContainerShape)context.getTargetContainer(), true);  
             flag=false;
           }
     
          
          int width = context.getWidth() <= 0 ? 100 : context.getWidth();
          int height = context.getHeight() <= 0 ? 50 : context.getHeight();
           IGaService gaService=Graphiti.getGaService();
           {
               
               RoundedRectangle roundedRectangle=gaService.createRoundedRectangle(containerShape, 6, 6);
               roundedRectangle.setBackground(manageColor(Entity_Background));
               roundedRectangle.setForeground(manageColor(Entity_Foreground));
               roundedRectangle.setLineWidth(3);
               gaService.setLocationAndSize(roundedRectangle, context.getX(),context.getY(), width, height);
            
              if(entity.eResource()==null)
                 getDiagram().eResource().getContents().add(entity);
                               
              
               link(containerShape, entity);

           }
           
           {
               Shape shape=peCreateService.createShape(containerShape, false);
               Polyline polyline=gaService.createPolyline(shape,new int[]{0,20,width,20});
               polyline.setForeground(manageColor(Entity_Foreground));
               polyline.setLineWidth(2);
                              
           }
           
           {
               Shape shape=peCreateService.createShape(containerShape, false);
               Text text=gaService.createDefaultText(shape, entity.getName());
               text.setBackground(manageColor(Entity_Text_Foreground));
               text.setHorizontalAlignment(Orientation.ALIGNMENT_CENTER);
               text.setVerticalAlignment(Orientation.ALIGNMENT_CENTER);
               text.getFont().setBold(true);
               gaService.setLocationAndSize(text, 0, 0, width, 20);
               
               link(shape,entity);
               
               IDirectEditingInfo directEditingInfo=getFeatureProvider().getDirectEditingInfo();

               // set container shape for direct editing after object creation

               directEditingInfo.setMainPictogramElement(containerShape);

               // set shape and graphics algorithm where the editor for

               // direct editing shall be opened after object creation

               directEditingInfo.setPictogramElement(shape);
               directEditingInfo.setGraphicsAlgorithm(text);
                          }
           layoutPictogramElement(containerShape);
           
           return containerShape;
    
    }

    public boolean canAdd(IAddContext context) {
        // TODO Auto-generated method stub
       
        if (context.getNewObject() instanceof Entity)
        {
            
            if(context.getTargetContainer() instanceof Diagram)
                return true;
           else if(context.getTargetContainer() instanceof ContainerShape)
                return true;
            
        }
        return false;
       }

}
