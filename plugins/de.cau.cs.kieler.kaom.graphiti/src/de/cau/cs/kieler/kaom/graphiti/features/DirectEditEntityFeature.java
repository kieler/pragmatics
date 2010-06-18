package de.cau.cs.kieler.kaom.graphiti.features;

import org.eclipse.graphiti.features.IFeatureProvider;
import de.cau.cs.kieler.kaom.Entity;
import org.eclipse.graphiti.features.context.IDirectEditingContext;
import org.eclipse.graphiti.features.impl.AbstractDirectEditingFeature;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;

public class DirectEditEntityFeature extends AbstractDirectEditingFeature {

    public DirectEditEntityFeature(IFeatureProvider fp) {
        super(fp);
        // TODO Auto-generated constructor stub
    }

    public int getEditingType() {
        // TODO Auto-generated method stub
        return TYPE_TEXT;
    }

    public String getInitialValue(IDirectEditingContext context) {
        // TODO Auto-generated method stub
     PictogramElement pe=context.getPictogramElement();
     return((Entity)getBusinessObjectForPictogramElement(pe)).getName();
    }

    public void setValue(String value, IDirectEditingContext context) {
        // TODO Auto-generated method stub
        PictogramElement pe=context.getPictogramElement();
        Object obj=getBusinessObjectForPictogramElement(pe);
        if(obj instanceof Entity)
        {
        Entity entity=(Entity)obj;
        entity.setName(value);
        }
        updatePictogramElement(((Shape) pe).getContainer());
    }
    

    @Override
    public String checkValueValid(String value, IDirectEditingContext context) {
        if (value.length() < 1)
            return "Please enter any text as class name.";
        if (value.contains(" "))
            return "Spaces are not allowed in class names.";
        if (value.contains("\n"))
            return "Line breakes are not allowed in class names.";

        return null;
    }

}
