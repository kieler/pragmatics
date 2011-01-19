package de.cau.cs.kieler.rail.editor.features;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IReason;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.features.impl.AbstractUpdateFeature;
import org.eclipse.graphiti.features.impl.Reason;
import org.eclipse.graphiti.mm.algorithms.Rectangle;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.BoxRelativeAnchor;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;

import de.cau.cs.kieler.rail.Topologie.Basegraph.Port;
import de.cau.cs.kieler.rail.Topologie.SpecializedVertices.Weichenknoten;
import de.cau.cs.kieler.rail.editor.StyleProvider;

public class UpdateSwitchFeature extends AbstractUpdateFeature {
	 
    public UpdateSwitchFeature(IFeatureProvider fp) {
        super(fp);
    }
 
    public boolean canUpdate(IUpdateContext context) {
        // return true, if linked business object is a EClass
        Object bo =
            getBusinessObjectForPictogramElement(context.getPictogramElement());
        return (bo instanceof Weichenknoten);
    }
 
    public IReason updateNeeded(IUpdateContext context) {
        // retrieve name from pictogram model
    	System.out.println("updateNeeded");
    	
        String pictogramName = null;
        PictogramElement pictogramElement = context.getPictogramElement();
        if (pictogramElement instanceof ContainerShape) {
            ContainerShape cs = (ContainerShape) pictogramElement;
            for (Shape shape : cs.getChildren()) {
                if (shape.getGraphicsAlgorithm() instanceof Text) {
                    Text text = (Text) shape.getGraphicsAlgorithm();
                    pictogramName = text.getValue();
                }
            }
        }
 
        // retrieve name from business model
        String businessName = null;
        Object bo = getBusinessObjectForPictogramElement(pictogramElement);
        if (bo instanceof Weichenknoten) {
        	Weichenknoten weichenknoten = (Weichenknoten) bo;
            businessName = weichenknoten.getName();
        }
 
        // update needed, if names are different
        boolean updateNameNeeded =
            ((pictogramName == null && businessName != null) ||
                (pictogramName != null && !pictogramName.equals(businessName)));
        if (updateNameNeeded) {
            return Reason.createTrueReason("Name is out of date");
        } else {
            return Reason.createFalseReason();
        }
    }


    public boolean update(IUpdateContext context) {
    	int[] spitzeStammXY={0,0,0,0};
    	int[] mitteAbzweigXY={0,0,0,0};
    	System.out.println("update");
    	
        // retrieve name from business model
        String businessName = null;
        PictogramElement pictogramElement = context.getPictogramElement();
        Object bo = getBusinessObjectForPictogramElement(pictogramElement);
        if (bo instanceof Weichenknoten) {
        	Weichenknoten weichenknoten = (Weichenknoten) bo;
            businessName = weichenknoten.getName();
        }
 
        
        // Set name in pictogram model
        if (pictogramElement instanceof ContainerShape) {
            ContainerShape cs = (ContainerShape) pictogramElement;
            
            for(Anchor anchor : cs.getAnchors()){
            	if (anchor instanceof BoxRelativeAnchor){
            		Port port = (Port)getBusinessObjectForPictogramElement(anchor);
            		switch (port.getName()){
	            		case SPITZE:
	            			spitzeStammXY[0] = anchor.getGraphicsAlgorithm().getX();
	            			spitzeStammXY[1] = anchor.getGraphicsAlgorithm().getY();
	            			break;
	            		case STAMM:
	            			spitzeStammXY[2] = anchor.getGraphicsAlgorithm().getX();
	            			spitzeStammXY[3] = anchor.getGraphicsAlgorithm().getY();
	            			break;
	            		case ABZWEIG:
	            			mitteAbzweigXY[2] = anchor.getGraphicsAlgorithm().getX();
	            			mitteAbzweigXY[3] = anchor.getGraphicsAlgorithm().getY();
            		}
            	}
            }
            
            IGaService gaService = Graphiti.getGaService();
          //virtual Rectangle
    		Rectangle R = gaService.createRectangle(pictogramElement);
    		//R.setStyle(styleProvider.getStyle(StyleProvider.DEFAULT_STYLE));
    		R.setForeground(manageColor(255, 255, 255));
            
            for (Shape shape : cs.getChildren()) {
                if (shape.getGraphicsAlgorithm() instanceof Text) {
                    Text text = (Text) shape.getGraphicsAlgorithm();
                    text.setValue(businessName);
                    return true;
                }
            }
        }
 
        return false;
    }
}
