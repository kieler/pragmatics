package de.cau.cs.kieler.kaom.graphiti.features;

import javax.swing.text.html.parser.Entity;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IMoveShapeContext;
import org.eclipse.graphiti.features.impl.DefaultMoveShapeFeature;
import org.eclipse.graphiti.mm.pictograms.Shape;

public class MoveEntityFeature extends DefaultMoveShapeFeature {

    public MoveEntityFeature(IFeatureProvider fp) {
        super(fp);
        // TODO Auto-generated constructor stub
    }

    public boolean canMoveShape(IMoveShapeContext context)
    {
        boolean canMove=super.canMoveShape(context);
        
        if(canMove)
        {
            Shape shape=context.getShape();
            Object obj=getBusinessObjectForPictogramElement(shape);
            if(obj instanceof Entity)
            {
                Entity en=(Entity)obj;
                if(en.getName()==null || en.getName().length()==1)
                    canMove=false;
            }
        }
        
        return canMove;
    }
    
}
