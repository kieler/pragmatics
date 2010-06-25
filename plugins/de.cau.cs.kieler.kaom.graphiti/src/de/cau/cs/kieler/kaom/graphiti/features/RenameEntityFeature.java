package de.cau.cs.kieler.kaom.graphiti.features;

import org.eclipse.graphiti.examples.common.SampleUtil;
import de.cau.cs.kieler.kaom.Entity;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.custom.AbstractCustomFeature;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;

public class RenameEntityFeature extends AbstractCustomFeature {

    public RenameEntityFeature(final IFeatureProvider fp) {
        super(fp);
        // TODO Auto-generated constructor stub
    }

      @Override
    public String getName() {
        return "Rename Entity";
    }

    @Override
    public String getDescription() {
        return "Change the name of the Entity";
    }
 

    @Override
    public boolean canExecute(final ICustomContext context) {
        boolean ret = false;
        PictogramElement[] pes = context.getPictogramElements();
        if (pes != null && pes.length == 1) {
            Object bo = getBusinessObjectForPictogramElement(pes[0]);
            if (bo instanceof Entity) {
                ret = true;
            }
        }
        return ret;
    }
 
    
    public void execute(final ICustomContext context) {
        PictogramElement[] pes = context.getPictogramElements();
        if (pes != null && pes.length == 1) {
            Object bo = getBusinessObjectForPictogramElement(pes[0]);
            if (bo instanceof Entity) {
               Entity entity = (Entity) bo;
                String currentName = entity.getName();
                String newName = SampleUtil.askString(getName(), getDescription(), currentName);

                if (newName != null) {
                    entity.setName(newName);
                }

            }

        }

    }



}
