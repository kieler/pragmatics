/**
 * 
 */
package de.cau.cs.kieler.rail.editor;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.graphiti.DiagramScrollingBehavior;
import org.eclipse.graphiti.IExecutionInfo;
import org.eclipse.graphiti.features.ICreateConnectionFeature;
import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.context.IDoubleClickContext;
import org.eclipse.graphiti.features.context.IPictogramElementContext;
import org.eclipse.graphiti.features.custom.ICustomFeature;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.palette.IPaletteCompartmentEntry;
import org.eclipse.graphiti.palette.impl.ConnectionCreationToolEntry;
import org.eclipse.graphiti.palette.impl.ObjectCreationToolEntry;
import org.eclipse.graphiti.palette.impl.PaletteCompartmentEntry;
import org.eclipse.graphiti.palette.impl.StackEntry;
import org.eclipse.graphiti.tb.DefaultToolBehaviorProvider;
import org.eclipse.graphiti.tb.IContextButtonPadData;
import org.eclipse.graphiti.tb.IContextMenuEntry;
import org.eclipse.graphiti.tb.IDecorator;
import org.eclipse.graphiti.tb.ISelectionInfo;
import org.eclipse.graphiti.tb.IToolBehaviorProvider;
import org.eclipse.graphiti.util.ILocationInfo;

/**
 * @author hdw
 *
 */
public class ToolBehaviorProvider extends DefaultToolBehaviorProvider {

	public ToolBehaviorProvider(DiagramTypeProvider diagramTypeProvider) {
		super(diagramTypeProvider);
	}
	/**
     * {@inheritDoc}
     */
	@Override
    public IPaletteCompartmentEntry[] getPalette() {
        List<IPaletteCompartmentEntry> ret =
            new ArrayList<IPaletteCompartmentEntry>();
 
        // add compartments from super class
        IPaletteCompartmentEntry[] superCompartments =
            super.getPalette();
        for (int i = 0; i < superCompartments.length; i++)
            ret.add(superCompartments[i]);
 
        // add new compartment at the end of the existing compartments
        PaletteCompartmentEntry compartmentEntry =
            new PaletteCompartmentEntry("Stacked", null);
        ret.add(compartmentEntry);
 
        // add new stack entry to new compartment
        StackEntry stackEntry = new StackEntry("EObject", "EObject", null);
        compartmentEntry.addToolEntry(stackEntry);
 
        // add all create-features to the new stack-entry
        IFeatureProvider featureProvider = getFeatureProvider();
        ICreateFeature[] createFeatures = featureProvider.getCreateFeatures();
        for (ICreateFeature cf : createFeatures) {
            ObjectCreationToolEntry objectCreationToolEntry =
                   new ObjectCreationToolEntry(cf.getCreateName(),
                     cf.getCreateDescription(), cf.getCreateImageId(),
                        cf.getCreateLargeImageId(), cf);
            stackEntry.addCreationToolEntry(objectCreationToolEntry);
        }
       
        // add all create-connection-features to the new stack-entry
        ICreateConnectionFeature[] createConnectionFeatures =
             featureProvider.getCreateConnectionFeatures();
        for (ICreateConnectionFeature cf : createConnectionFeatures) {
            ConnectionCreationToolEntry connectionCreationToolEntry =
                new ConnectionCreationToolEntry(cf.getCreateName(), cf
                  .getCreateDescription(), cf.getCreateImageId(),
                    cf.getCreateLargeImageId());
                        connectionCreationToolEntry.addCreateConnectionFeature(cf);
            stackEntry.addCreationToolEntry(connectionCreationToolEntry);
        }
 
        return ret.toArray(new IPaletteCompartmentEntry[ret.size()]);
    }
}
