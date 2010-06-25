package de.cau.cs.kieler.kaom.graphiti.features;

import java.util.Iterator;

import org.eclipse.emf.common.util.EList;
import org.eclipse.graphiti.datatypes.IDimension;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.impl.AbstractAddShapeFeature;
import org.eclipse.graphiti.mm.datatypes.Color;
import org.eclipse.graphiti.mm.pictograms.BoxRelativeAnchor;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.Ellipse;
import org.eclipse.graphiti.mm.pictograms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.pictograms.GraphicsAlgorithmContainer;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Polyline;
import org.eclipse.graphiti.mm.pictograms.Rectangle;
import org.eclipse.graphiti.mm.pictograms.RoundedRectangle;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeCreateService;
import org.eclipse.graphiti.util.IColorConstant;

import de.cau.cs.kieler.kaom.Entity;
import de.cau.cs.kieler.kaom.Port;

public class AddPortFeature extends AbstractAddShapeFeature{
    
    public static final int INVISIBLE_RECTANGLE_WIDTH=6;
    
    public AddPortFeature(IFeatureProvider fp)
    {
        super(fp);
    }
    
  public PictogramElement add(IAddContext context) {
        // TODO Auto-generated method stub
     
   ContainerShape containerShape = context.getTargetContainer();
   IPeCreateService peCreateService = Graphiti.getPeCreateService();
   peCreateService.createChopboxAnchor(containerShape);

   Port port = (Port) context.getNewObject();
   
   float widthPercent;
   float heightPercent;

    BoxRelativeAnchor boxAnchor = peCreateService.createBoxRelativeAnchor(containerShape);
    if (context.getX() < 10) {
     //   widthPercent=(float)8.0/(float)containerShape.getGraphicsAlgorithm().getWidth();
        widthPercent = (float) 0;
        }
        else if (Math.abs(context.getX() 
                - (float) containerShape.getGraphicsAlgorithm().getWidth()) < 10){
     //   widthPercent=(float)1-((float)8.0/(float)containerShape.getGraphicsAlgorithm().getWidth());
            widthPercent = (float) 1;
        }
            else {
                widthPercent = ((float) context.getX()) 
                / containerShape.getGraphicsAlgorithm().getWidth();
            }
    
    if (Math.abs(context.getY() - (float) containerShape.getGraphicsAlgorithm().getHeight()) < 10) {
     //   heightPercent=(float)1-((float)8.0/(float)containerShape.getGraphicsAlgorithm().getHeight());
        heightPercent = (float) 1;
    }
        else {
                heightPercent = ((float) context.getY()) 
                / containerShape.getGraphicsAlgorithm().getHeight();
        }
   
    boxAnchor.setRelativeWidth(widthPercent);
    boxAnchor.setRelativeHeight(heightPercent);
  
       boxAnchor.setActive(true);
  //      boxAnchor.setRelativeHeight(0.38);
  //      boxAnchor.setRelativeWidth(1.0);
        
        IGaService gaService = Graphiti.getGaService();
        
     //   Rectangle invisibleRectangle=gaService.createInvisibleRectangle(containerShape);
        Iterator iter = containerShape.getChildren().iterator();
        PictogramElement pe = (PictogramElement) containerShape.getLink().getPictogramElement();
      
   
        if (pe.getGraphicsAlgorithm() instanceof Rectangle)
        {
            Rectangle invisibleRectangle = (Rectangle) pe.getGraphicsAlgorithm();
           // System.out.println("I came here also");
            for (int i = 0; i < invisibleRectangle.getGraphicsAlgorithmChildren().size(); i++) {
                if (invisibleRectangle.getGraphicsAlgorithmChildren().get(i) instanceof RoundedRectangle)
                    {
                    boxAnchor.setReferencedGraphicsAlgorithm((RoundedRectangle) 
                            (invisibleRectangle.getGraphicsAlgorithmChildren().get(i)));
             //       System.out.println("Hello I cam here!!");
                    }
              }
            } 
     
   // assign a rectangle graphics algorithm for the box relative anchor

    // note, that the rectangle is inside the border of the rectangle shape

    Ellipse boxEllipse = gaService.createEllipse(boxAnchor);
    boxEllipse.setFilled(true);
    boxEllipse.setStyle(StyleUtil.getStyleForEClass(getDiagram()));
    System.out.println("X:" + context.getX() + "Y:" + context.getY());
   // gaService.setLocationAndSize(boxEllipse,-8, -4, 8, 8);
   
    final int w = INVISIBLE_RECTANGLE_WIDTH;
    gaService.setLocationAndSize(boxEllipse, -w, -w, 2 * w, 2 * w);
    
 //   Color c = gaService.manageColor(getDiagram(),IColorConstant.DARK_BLUE);

   // boxEllipse.setBackground(c);
//    return (PictogramElement) boxRect;
 //   layoutPictogramElement(containerShape);
    
    Entity entity = (Entity) getBusinessObjectForPictogramElement(context.getTargetContainer());
    entity.getChildPorts().add(port);
    
    link(boxAnchor, port);
    return containerShape;
  }

    public boolean canAdd(IAddContext context) {
        // TODO Auto-generated method stub
        if (context.getNewObject() instanceof Port) {
            if (context.getTargetContainer() instanceof ContainerShape) {
                ContainerShape containerShape = context.getTargetContainer();
             
                if (getBusinessObjectForPictogramElement(containerShape) instanceof Entity) {
                    if (Math.abs(context.getX() - containerShape.getGraphicsAlgorithm().getWidth()) < 10 
                        || Math.abs(context.getY() - containerShape.getGraphicsAlgorithm().getHeight()) < 10
                        || context.getX() < 10) {
                            return true;
                    }
                }
            }
        }
        return false;
    }


}
