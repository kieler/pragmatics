package de.cau.cs.kieler.kaom.graphiti.features;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IPasteContext;
import org.eclipse.graphiti.features.context.impl.AddContext;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.ui.features.AbstractPasteFeature;

import de.cau.cs.kieler.kaom.Entity;

public class PasteEntityFeature extends AbstractPasteFeature {

    public PasteEntityFeature(IFeatureProvider fp) {
        super(fp);
        // TODO Auto-generated constructor stub
    }

    public boolean canPaste(final IPasteContext context) {

        PictogramElement[] pes = context.getPictogramElements();
        if (pes.length != 1 || !(pes[0] instanceof Diagram)) {
            return false;
            }

 
        Object[] fromClipboard = getFromClipboard();
        if (fromClipboard == null || fromClipboard.length == 0) {
            return false;
            }
        for (Object object : fromClipboard) {
            if (!(object instanceof Entity)) {
                return false;
                }

            }
        return true;
        }

 

    public void paste(IPasteContext context) {

        PictogramElement[] pes = context.getPictogramElements();
        Diagram diagram = (Diagram) pes[0];
        Object[] objects = getFromClipboard();

        for (Object object : objects) {
            AddContext ac = new AddContext();
            ac.setLocation(0, 0); // for simplicity paste at (0, 0)
            ac.setTargetContainer(diagram);
            addGraphicalRepresentation(ac, object);
        }
    }
}


