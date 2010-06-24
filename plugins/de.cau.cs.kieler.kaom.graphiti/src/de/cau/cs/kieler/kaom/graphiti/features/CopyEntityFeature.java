package de.cau.cs.kieler.kaom.graphiti.features;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICopyContext;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.ui.features.AbstractCopyFeature;

import de.cau.cs.kieler.kaom.Entity;

public class CopyEntityFeature extends AbstractCopyFeature {

    public CopyEntityFeature(IFeatureProvider fp) {
        super(fp);
        // TODO Auto-generated constructor stub
    }

    public boolean  canCopy(ICopyContext context) {
        final PictogramElement[] pes = context.getPictogramElements();

        if (pes == null || pes.length == 0) {  // nothing selected
            return false;
        }

       
        // return true, if all selected elements are a EClasses
        for (PictogramElement pe : pes) {
            final Object bo = getBusinessObjectForPictogramElement(pe);
            if (!(bo instanceof Entity)) {
                return false;
            }
        }
        return true;
    }
 

    public void copy(ICopyContext context) {

        PictogramElement[] pes = context.getPictogramElements();
        Object[] bos = new Object[pes.length];

        for (int i = 0; i < pes.length; i++) {
            PictogramElement pe = pes[i];
            bos[i] = getBusinessObjectForPictogramElement(pe);
        }

         putToClipboard(bos);

    }

}

