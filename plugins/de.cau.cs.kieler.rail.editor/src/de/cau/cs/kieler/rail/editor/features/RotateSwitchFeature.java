package de.cau.cs.kieler.rail.editor.features;

import org.eclipse.graphiti.examples.common.ExampleUtil;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.custom.AbstractCustomFeature;
import org.eclipse.graphiti.mm.Property;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.BoxRelativeAnchor;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;

import de.cau.cs.kieler.rail.Topologie.Basegraph.EPort;
import de.cau.cs.kieler.rail.Topologie.Basegraph.Port;
import de.cau.cs.kieler.rail.Topologie.SpecializedVertices.EOrientation;
import de.cau.cs.kieler.rail.Topologie.SpecializedVertices.Weichenknoten;
import de.cau.cs.kieler.rail.editor.RotationSwitchHandler;

public class RotateSwitchFeature extends AbstractCustomFeature {
 
    public static final String NAME = "Rotate Switch";
	public RotateSwitchFeature(IFeatureProvider fp) {
        super(fp);
    }
    /**
     * {@inheritDoc}
     */
    @Override
	public final String getName() {
        return NAME;
    }
    /**
     * {@inheritDoc}
     */
    @Override
	public final String getDescription() {
        return "Ändert den Winkel der Weiche";
    }
    /**
     * {@inheritDoc}
     */
    @Override
	public final boolean canExecute(final ICustomContext context) {
        // allow rename if exactly one pictogram element
        // representing a EClass is selected
        boolean ret = false;
        PictogramElement[] pes = context.getPictogramElements();
        if (pes != null && pes.length == 1) {
            Object bo = getBusinessObjectForPictogramElement(pes[0]);
            if (bo instanceof Weichenknoten) {
                ret = true;
            }
        }
        return ret;
    }
    /**
     * {@inheritDoc}
     */
    //RotationSwitchHandler.MULTIPLEANGLE_KEY
    public final void execute(final ICustomContext context) {
    	Property propertyNodeType = null;
    	Property propertyAngle =  null;
    	PictogramElement[] pes = context.getPictogramElements();
        if (pes != null && pes.length == 1) {
            Object bo = getBusinessObjectForPictogramElement(pes[0]);
            if (bo instanceof Weichenknoten) {
                Weichenknoten wk = (Weichenknoten) bo;
                //I don't know the right order
                for (Property property : pes[0].getProperties()) {
                	if (AddVertexFeature.KLAY_NODETYPE_KEY
                	== property.getKey()) {
                		propertyNodeType = property;
                	} else if (RotationSwitchHandler.MULTIPLEANGLE_KEY
                	== property.getKey()) {
                		propertyAngle = property;
                	}
                }
                int multipleangle = RotationSwitchHandler.getValidMultipleAngle(
                Integer.parseInt(propertyAngle.getValue())
                + 1 , wk.getAbzweigendeLage());
                RotationSwitchHandler.setMultipleAngle(pes[0]
                , getFeatureProvider() , multipleangle , 0.1);

	            updatePictogramElement(pes[0]);
	        }
        }
    }
    /*
    public void execute(final ICustomContext context) {

        PictogramElement[] pes = context.getPictogramElements();
        if (pes != null && pes.length == 1) {
            Object bo = getBusinessObjectForPictogramElement(pes[0]);
            if (bo instanceof Weichenknoten) {
                Weichenknoten weichenknoten = (Weichenknoten) bo;
                String currentName = weichenknoten.getName();

                PictogramElement pictogramElement =
                        context.getInnerPictogramElement();

                // rotate
                if (pictogramElement instanceof ContainerShape) {
                    ContainerShape cs = (ContainerShape) pictogramElement;
                    
                    for (Anchor anchor : cs.getAnchors()) {
                        if (anchor instanceof BoxRelativeAnchor) {
                            Port port =
                                    (Port) getBusinessObjectForPictogramElement(anchor);
                            BoxRelativeAnchor box =
                                    (BoxRelativeAnchor) anchor
                                            .getGraphicsAlgorithm()
                                            .getPictogramElement();
                            int boxWidth =
                                    anchor.getGraphicsAlgorithm().getWidth();
                            int boxHeight =
                                    anchor.getGraphicsAlgorithm().getWidth();
                            if (port.getName() == EPort.ABZWEIG) {
                                box.setRelativeHeight(Math.abs(1 - box
                                        .getRelativeHeight()));
                            }
                        }
                    }
                    
                    for(Shape shape : cs.getChildren()){
                    	GraphicsAlgorithm ga = shape.getGraphicsAlgorithm();
                    	if(ga instanceof Text){
                    		((Text) ga).setY(Math.abs(30
                    		- ((Text) ga).getY()));     //TODO add const
                    	}
                    }
                   
                    updatePictogramElement(pictogramElement);
                }
            }
        }
    }*/
}
