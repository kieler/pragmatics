package de.cau.cs.kieler.kaom.graphiti.features;

import javax.swing.text.html.parser.Entity;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IResizeShapeContext;
import org.eclipse.graphiti.features.impl.DefaultResizeShapeFeature;
import org.eclipse.graphiti.mm.pictograms.Shape;

public class ResizeEntityFeature extends DefaultResizeShapeFeature {

    public ResizeEntityFeature(IFeatureProvider fp) {
        super(fp);
        // TODO Auto-generated constructor stub
    }
    
    public boolean canResizeShape(IResizeShapeContext context)
    {
        boolean canResize=super.canResizeShape(context);
        
        if(canResize)
        {
            Shape shape=context.getShape();
            Object obj=getBusinessObjectForPictogramElement(shape);
            if(obj instanceof Entity)
            {
                Entity en=(Entity)obj;
                if(en.getName()==null || en.getName().length()==1)
                    canResize=false;
            }
        }
        
        return canResize;
    }

}
