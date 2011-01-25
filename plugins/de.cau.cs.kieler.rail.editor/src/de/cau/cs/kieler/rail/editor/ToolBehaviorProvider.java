/**
 * 
 */
package de.cau.cs.kieler.rail.editor;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.validation.internal.service.GetBatchConstraintsOperation;
import org.eclipse.graphiti.DiagramScrollingBehavior;
import org.eclipse.graphiti.IExecutionInfo;
import org.eclipse.graphiti.features.ICreateConnectionFeature;
import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.context.IDoubleClickContext;
import org.eclipse.graphiti.features.context.IPictogramElementContext;
import org.eclipse.graphiti.features.context.impl.CreateConnectionContext;
import org.eclipse.graphiti.features.context.impl.CustomContext;
import org.eclipse.graphiti.features.custom.ICustomFeature;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.styles.Orientation;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.AnchorContainer;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.palette.IPaletteCompartmentEntry;
import org.eclipse.graphiti.palette.impl.ConnectionCreationToolEntry;
import org.eclipse.graphiti.palette.impl.ObjectCreationToolEntry;
import org.eclipse.graphiti.palette.impl.PaletteCompartmentEntry;
import org.eclipse.graphiti.palette.impl.StackEntry;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.tb.ContextButtonEntry;
import org.eclipse.graphiti.tb.ContextEntryHelper;
import org.eclipse.graphiti.tb.DefaultToolBehaviorProvider;
import org.eclipse.graphiti.tb.IContextButtonEntry;
import org.eclipse.graphiti.tb.IContextButtonPadData;
import org.eclipse.graphiti.tb.IContextMenuEntry;
import org.eclipse.graphiti.tb.IDecorator;
import org.eclipse.graphiti.tb.ISelectionInfo;
import org.eclipse.graphiti.tb.IToolBehaviorProvider;
import org.eclipse.graphiti.util.ILocationInfo;

import de.cau.cs.kieler.rail.Topologie.SpecializedVertices.EOrientation;
import de.cau.cs.kieler.rail.Topologie.SpecializedVertices.Weichenknoten;
import de.cau.cs.kieler.rail.editor.features.CreatePortFeature;
import de.cau.cs.kieler.rail.editor.features.ToggleSwitchFeature;

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
    @Override
    public GraphicsAlgorithm[] getClickArea(PictogramElement pe) {
    	//maybe later I will use it.
    	//System.out.println(pe);
		return super.getClickArea(pe);
    	
    }
    
    @Override
    public IContextButtonPadData getContextButtonPad(
                                       IPictogramElementContext context) {
        IContextButtonPadData data = super.getContextButtonPad(context);
        PictogramElement pe = context.getPictogramElement();
        Object bo = context.getPictogramElement().getLink().getBusinessObjects();
        
        // 1. set the generic context buttons
        // note, that we do not add 'remove' (just as an example)
        setGenericContextButtons(data, pe, CONTEXT_BUTTON_DELETE |
                                               CONTEXT_BUTTON_UPDATE);
       
        // 2. set the collapse button
        // simply use the first custom feature (senseless example)
        CustomContext cc = new CustomContext(new PictogramElement[] { pe });
        ICustomFeature[] cf = getFeatureProvider().getCustomFeatures(cc);
        if (cf.length >= 1) {
            IContextButtonEntry collapseButton = ContextEntryHelper
                        .createCollapseContextButton(true, cf[0], cc);
            data.setCollapseContextButton(collapseButton);
          }
        
        System.out.println(bo);
        System.out.println(bo instanceof Weichenknoten);
        CustomContext ccToogle = new CustomContext(new PictogramElement[] { pe });
        if(bo instanceof Weichenknoten){
        	
        	//ContextButtonEntry toogleButton = new ContextButtonEntry(null, context);
        	
        	for(int i = 0; i < cf.length;i++){
        		if (cf[i].getName() ==ToggleSwitchFeature.NAME){
        			IContextButtonEntry toogleButton = ContextEntryHelper
                	.createCollapseContextButton(true, cf[i], ccToogle);
        			
        			if(((Weichenknoten)bo).getAbzweigendeLage() == EOrientation.LINKS){
                		toogleButton.setText("links Weiche -> rechts Weiche");
                	}else{
                			toogleButton.setText("rechts Weiche -> links Weiche");
                		}
                	toogleButton.setIconId(ImageProvider.IMG_TOGGLE);
                	
                	data.setCollapseContextButton(toogleButton);
        		}
        	}
        	
        	//ICustomFeature[] cf = getFeatureProvider().getCustomFeatures(cc);
        	
        }
        
     /*
        // 3. add one domain specific context-button, which offers all
        // available connection-features as drag&drop features...
 
        // 3.a. create new CreateConnectionContext
        CreateConnectionContext ccc = new CreateConnectionContext();
        ccc.setSourcePictogramElement(pe);
        Anchor anchor = null;
        if (pe instanceof Anchor) {
            anchor = (Anchor) pe;
        } else if (pe instanceof AnchorContainer) {
            // assume, that our shapes always have chopbox anchors
            anchor = Graphiti.getPeService()
                     .getChopboxAnchor((AnchorContainer) pe);
        }
        ccc.setSourceAnchor(anchor);
       
        // 3.b. create context button and add all applicable features
        ContextButtonEntry button = new ContextButtonEntry(null, context);
        button.setText("Create connection");
        button.setIconId(ImageProvider.IMG_TOGGLE);
        ICreateConnectionFeature[] features =
            getFeatureProvider().getCreateConnectionFeatures();
        for (ICreateConnectionFeature feature : features) {
            if (feature.isAvailable(ccc) && feature.canStartConnection(ccc))
                button.addDragAndDropFeature(feature);
        }
 
        // 3.c. add context button, if it contains at least one feature
        if (button.getDragAndDropFeatures().size() > 0) {
           data.getDomainSpecificContextButtons().add(button);
        }*/
   
        return data;
    }
}
