package de.cau.cs.kieler.kaom.graphiti.features;

import org.eclipse.graphiti.examples.common.SampleUtil;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.custom.AbstractCustomFeature;
import org.eclipse.graphiti.mm.datatypes.Color;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Style;

import de.cau.cs.kieler.kaom.Entity;

public class ChangeColorEntityFeature extends AbstractCustomFeature {

    private boolean background;
    
    
    public ChangeColorEntityFeature(IFeatureProvider fp, final boolean background) {
        super(fp);
        // TODO Auto-generated constructor stub
        this.background = background;
    }

    @Override
    public String getName() {
        String colorType = background ? "&background" : "&foreground";
        return "Change " + colorType + " color";
    }

    @Override
    public String getDescription() {
        String colorType = background ? "background" : "foreground";
        return "Change the " + colorType + " color";
    }

    @Override
    public boolean canExecute(final ICustomContext context) {
        PictogramElement[] pes = context.getPictogramElements();
        if (pes == null || pes.length == 0) { // nothing selected
            return false;
        }

        // return true, if all elements are EClasses
        // note, that in execute() the selected elements are not even accessed,
        // so theoretically it would be possible that canExecute() always
        // returns true. But for usability reasons it is better to check
        // if the selected elements are EClasses.

        for (PictogramElement pe : pes) {

            final Object bo = getBusinessObjectForPictogramElement(pe);
            if (!(bo instanceof Entity)) {
                return false;
            }
        }
        return true;
    }

 
    
    public void execute(ICustomContext context) {

        Style style = StyleUtil.getStyleForEClass(getDiagram());
         // let the user choose the new color
        Color currentColor;
        if (background) {
            currentColor = style.getBackground();
        } else {
            currentColor = style.getForeground();
        }
        Color newColor = SampleUtil.editColor(currentColor);

        if (newColor == null) { // user did not choose new color
            return;
        }


       // set new color
        if (background) {
            style.setBackground(newColor);
        } else {
            style.setForeground(newColor);

        }

    }

}
