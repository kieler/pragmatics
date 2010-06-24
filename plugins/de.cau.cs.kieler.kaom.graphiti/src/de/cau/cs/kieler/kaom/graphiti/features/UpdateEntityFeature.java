package de.cau.cs.kieler.kaom.graphiti.features;




//import java.util.Iterator;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IReason;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.features.impl.AbstractUpdateFeature;
import org.eclipse.graphiti.features.impl.Reason;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.mm.pictograms.Text;

import de.cau.cs.kieler.kaom.Entity;

public class UpdateEntityFeature extends AbstractUpdateFeature {

    public UpdateEntityFeature(IFeatureProvider fp) {
        super(fp);
        // TODO Auto-generated constructor stub
    }

    public boolean canUpdate(IUpdateContext context) {
        // TODO Auto-generated method stub
       Object obj=getBusinessObjectForPictogramElement(context.getPictogramElement());
       return(obj instanceof Entity);
    }

    public boolean update(IUpdateContext context) {
        // TODO Auto-generated method stub
        String businessName=null;
        PictogramElement pictogramElement= context.getPictogramElement();
        Object obj=getBusinessObjectForPictogramElement(pictogramElement);
        if(obj instanceof Entity)
           businessName=((Entity)obj).getName();
        
        if(pictogramElement instanceof ContainerShape)
        {
            ContainerShape cs=(ContainerShape)pictogramElement;
      //      for(Iterator iter = cs.getChildren().iterator();iter.hasNext())
            for(int i=0;i<cs.getChildren().size();i++)
            {
                Shape shape=(Shape)cs.getChildren().get(i);
     
                if(shape.getGraphicsAlgorithm() instanceof Text)
                {
                    ((Text)shape.getGraphicsAlgorithm()).setValue(businessName);
                    return true;
                }
            }
               
        }
                        
        return false;
    }

    public IReason updateNeeded(IUpdateContext context) {
        // TODO Auto-generated method stub
        String pictogramName=null;
        PictogramElement pictogramElement=context.getPictogramElement();
        if(pictogramElement instanceof ContainerShape)
            {
                ContainerShape cs=(ContainerShape)pictogramElement;
                for(int i=0;i<cs.getChildren().size();i++)
                {
                    Shape shape=(Shape)cs.getChildren().get(i);                    
                    if(shape.getGraphicsAlgorithm() instanceof Text)
                    {
                        Text text=(Text)shape.getGraphicsAlgorithm();
                        pictogramName=text.getValue();
                        System.out.println("The pictogram name is "+pictogramName);
                    }
                }
            }
            
            String businessName=null;
            Object obj=getBusinessObjectForPictogramElement(pictogramElement);
            if(obj instanceof Entity)
                businessName=((Entity)obj).getName();
            
            boolean updateRequired = false;
            if((pictogramName==null && !(businessName==null)) || (!(pictogramName==null) && !pictogramName.equals(businessName)))
                updateRequired=true;
            if(updateRequired)
                return Reason.createTrueReason("Name is out of Date");
            else
                return Reason.createFalseReason();
        }

  
}


