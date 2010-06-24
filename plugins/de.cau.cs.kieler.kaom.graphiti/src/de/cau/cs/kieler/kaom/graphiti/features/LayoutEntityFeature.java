package de.cau.cs.kieler.kaom.graphiti.features;

import java.awt.Container;
import de.cau.cs.kieler.kaom.Entity;
import java.util.Iterator;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.datatypes.IDimension;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ILayoutContext;
import org.eclipse.graphiti.features.impl.AbstractLayoutFeature;
import org.eclipse.graphiti.mm.datatypes.Point;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Polyline;
import org.eclipse.graphiti.mm.pictograms.Rectangle;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.mm.pictograms.Text;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;

public class LayoutEntityFeature extends AbstractLayoutFeature {

    private final int MIN_HEIGHT=30;
    private final int MIN_WIDTH=50;
    
    public LayoutEntityFeature(IFeatureProvider fp) {
        super(fp);
        // TODO Auto-generated constructor stub
    }

    public boolean canLayout(ILayoutContext context) {
        // TODO Auto-generated method stub
        PictogramElement pe=context.getPictogramElement();
        if(pe instanceof ContainerShape)
        {
            EList<EObject> ob=pe.getLink().getBusinessObjects();
            return ob.size()==1 && (ob.get(0) instanceof Entity);
        }
        
        return false;
      
      //  System.out.println("Value:"+PropertyUtil.isEClassShape(pe));
       // return PropertyUtil.isEClassShape(pe);
    }

    public boolean layout(ILayoutContext context) {
        // TODO Auto-generated method stub
        
        boolean changed=false;
        ContainerShape containerShape=(ContainerShape)context.getPictogramElement();
        GraphicsAlgorithm containerGa=containerShape.getGraphicsAlgorithm();
        
        if(containerGa.getHeight()<MIN_HEIGHT)
        {
            containerGa.setHeight(MIN_HEIGHT);
            changed=true;
        }
        
        if(containerGa.getWidth()<MIN_WIDTH)
        {
            containerGa.setWidth(MIN_WIDTH);
            changed=true;
        }
        
        int containerWidth = containerGa.getWidth()-2*AddPortFeature.INVISIBLE_RECTANGLE_WIDTH;
        Iterator iter=containerShape.getChildren().iterator();
        while(iter.hasNext())
        {
            Shape shape=(Shape)iter.next();
            GraphicsAlgorithm ga=shape.getGraphicsAlgorithm();
            IGaService gaService=Graphiti.getGaService();
            IDimension size=gaService.calculateSize(ga);
            if(size.getWidth()!=containerWidth){
                if (ga instanceof Polyline)
                {
                    Polyline polyline=(Polyline)ga;
                    Point firstPoint=polyline.getPoints().get(0);
                    Point newfirstPoint=gaService.createPoint(AddPortFeature.INVISIBLE_RECTANGLE_WIDTH, firstPoint.getY());                    
                    Point secondpoint=polyline.getPoints().get(1);
                    Point newsecondpoint=gaService.createPoint(AddPortFeature.INVISIBLE_RECTANGLE_WIDTH+containerWidth, secondpoint.getY());
                    polyline.getPoints().set(0, newfirstPoint);
                    polyline.getPoints().set(1, newsecondpoint);
                    changed =true;
                }
                else if(ga instanceof Text)
                {
                  
                   Text text=(Text)ga;
                   gaService.setLocationAndSize(ga, AddPortFeature.INVISIBLE_RECTANGLE_WIDTH,text.getY(), containerWidth, text.getHeight());//, avoidNegativeCoordinates)
                   changed=true;
                   }
                else if(ga instanceof Rectangle)
                {
                    System.out.println("hello i cam here");
                    Rectangle rectangle=(Rectangle)ga;
                    gaService.setLocationAndSize(ga, AddPortFeature.INVISIBLE_RECTANGLE_WIDTH,rectangle.getY(), containerWidth, rectangle.getHeight());//, avoidNegativeCoordinates)
                    changed=true;
                }
            }
        }
         return changed;       
     }

}
