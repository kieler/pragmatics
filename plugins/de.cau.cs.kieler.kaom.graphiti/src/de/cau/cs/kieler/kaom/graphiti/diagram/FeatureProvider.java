package de.cau.cs.kieler.kaom.graphiti.diagram;

import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.IAddFeature;
import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.IDirectEditingFeature;
import org.eclipse.graphiti.features.ILayoutFeature;
import org.eclipse.graphiti.features.IMoveShapeFeature;
import org.eclipse.graphiti.features.IResizeShapeFeature;
import org.eclipse.graphiti.features.IUpdateFeature;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.context.IDirectEditingContext;
import org.eclipse.graphiti.features.context.ILayoutContext;
import org.eclipse.graphiti.features.context.IMoveShapeContext;
import org.eclipse.graphiti.features.context.IResizeShapeContext;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.features.custom.ICustomFeature;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.ui.features.DefaultFeatureProvider;

import de.cau.cs.kieler.kaom.Entity;
import de.cau.cs.kieler.kaom.graphiti.features.AddEntityFeature;
import de.cau.cs.kieler.kaom.graphiti.features.CreateEntityFeature;
import de.cau.cs.kieler.kaom.graphiti.features.DirectEditEntityFeature;
import de.cau.cs.kieler.kaom.graphiti.features.LayoutEntityFeature;
import de.cau.cs.kieler.kaom.graphiti.features.MoveEntityFeature;
import de.cau.cs.kieler.kaom.graphiti.features.RenameEntityFeature;
import de.cau.cs.kieler.kaom.graphiti.features.ResizeEntityFeature;
import de.cau.cs.kieler.kaom.graphiti.features.UpdateEntityFeature;

public class FeatureProvider extends DefaultFeatureProvider {

    public FeatureProvider(IDiagramTypeProvider dtp) {
        super(dtp);
        // TODO Auto-generated constructor stub
    }

    @Override
    public ICreateFeature[] getCreateFeatures() {
        return new ICreateFeature[] { new CreateEntityFeature(this) };
    }
    
    public IAddFeature getAddFeature(IAddContext context)
    {
        if (context.getNewObject() instanceof Entity)
            return new AddEntityFeature(this);
    return super.getAddFeature(context);
        
    }
    
    @Override
    public IUpdateFeature getUpdateFeature(IUpdateContext context)
    {
        if(context.getPictogramElement() instanceof ContainerShape)
        {
            Object obj=getBusinessObjectForPictogramElement(context.getPictogramElement());
            if(obj instanceof Entity)
            {
                return new UpdateEntityFeature(this);
            }
            
        }
                         
         return super.getUpdateFeature(context);
    }


    @Override
    public IMoveShapeFeature getMoveShapeFeature(IMoveShapeContext context)
    {
        Shape shape=context.getShape();
        Object ob=getBusinessObjectForPictogramElement(shape);
        if(ob instanceof Entity)
            return new MoveEntityFeature(this);
        return super.getMoveShapeFeature(context);
        
        }

    @Override
    public IResizeShapeFeature getResizeShapeFeature(IResizeShapeContext context)
    {
        Shape shape=context.getShape();
        Object ob=getBusinessObjectForPictogramElement(shape);
        if(ob instanceof Entity)
            return new ResizeEntityFeature(this);
        return super.getResizeShapeFeature(context);
        
        }
    
    public ILayoutFeature getLayoutFeature(ILayoutContext context)
    {
        PictogramElement pe= context.getPictogramElement();
        Object obj=getBusinessObjectForPictogramElement(pe);
        if(obj instanceof Entity)
            return new LayoutEntityFeature(this);
        return super.getLayoutFeature(context);
    }
    
    @Override
    public ICustomFeature[] getCustomFeatures(ICustomContext context) {
        return new ICustomFeature[] { new RenameEntityFeature(this) };

    }
    
    @Override
    public IDirectEditingFeature getDirectEditingFeature(IDirectEditingContext context)
    {
        PictogramElement pe=context.getPictogramElement();
        Object ob=getBusinessObjectForPictogramElement(pe);
        if(ob instanceof Entity)
        {
            return new DirectEditEntityFeature(this);
        }
        return super.getDirectEditingFeature(context);
    }
        
    

}
