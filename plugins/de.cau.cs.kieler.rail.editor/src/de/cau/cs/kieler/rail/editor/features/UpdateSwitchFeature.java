package de.cau.cs.kieler.rail.editor.features;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IReason;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.features.impl.AbstractUpdateFeature;
import org.eclipse.graphiti.features.impl.Reason;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.Polyline;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.BoxRelativeAnchor;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import de.cau.cs.kieler.rail.Topologie.Basegraph.Port;
import de.cau.cs.kieler.rail.Topologie.SpecializedVertices.Weichenknoten;

/**
 * 
 * @author hdw
 *	Update feature for the switch
 *	It updates the lines for the ports.
 */
public class UpdateSwitchFeature extends AbstractUpdateFeature {
	 
	/**
	 * Updater for the switch
	 * @param fp
	 */
    public UpdateSwitchFeature(IFeatureProvider fp) {
        super(fp);
    }
 
    /**
     * {@inheritDoc}
     */
    public boolean canUpdate(IUpdateContext context) {
        // return true, if linked business object is a EClass
        Object bo =
            getBusinessObjectForPictogramElement(context.getPictogramElement());
        return (bo instanceof Weichenknoten);
    }
    /**
     * {@inheritDoc}
     */
    public IReason updateNeeded(IUpdateContext context) {
        // retrieve name from pictogram model
    	
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
        	return Reason.createFalseReason();
            //return Reason.createTrueReason("Name is out of date");
        } else {
            return Reason.createFalseReason();
        }
    }

    /**
     * {@inheritDoc}
     */
    public boolean update(IUpdateContext context) {
    	int[] spitzeStammXY={0,0,0,0};
    	int[] mitteAbzweigXY={25,25,0,0};
    	List<Polyline> polylines = new LinkedList<Polyline>();
    	
        PictogramElement pictogramElement = context.getPictogramElement();
 
        int width = pictogramElement.getGraphicsAlgorithm().getWidth();
        int height = pictogramElement.getGraphicsAlgorithm().getHeight();
        
        //Polylines
        ContainerShape containerShape = (ContainerShape) context.getPictogramElement();
        Iterator iter = containerShape.getChildren().iterator();
        while (iter.hasNext()) {
        	Shape shape = (Shape) iter.next();
        	System.out.println(shape);
            GraphicsAlgorithm graphicsAlgorithm = shape.getGraphicsAlgorithm();
            System.out.println(graphicsAlgorithm);
            if(graphicsAlgorithm instanceof Polyline){
            	polylines.add((Polyline)graphicsAlgorithm);
            }
        }
        //Polylines end
        
        if (pictogramElement instanceof ContainerShape) {
            ContainerShape cs = (ContainerShape) pictogramElement;
            
            for(Anchor anchor : cs.getAnchors()){
            	if (anchor instanceof BoxRelativeAnchor){
            		Port port = (Port)getBusinessObjectForPictogramElement(anchor);
            		BoxRelativeAnchor box = (BoxRelativeAnchor) anchor.getGraphicsAlgorithm().getPictogramElement();
            		int boxWidth = anchor.getGraphicsAlgorithm().getWidth();
            		int boxHeight = anchor.getGraphicsAlgorithm().getWidth();
            		switch (port.getName()){
	            		case SPITZE:
	            			spitzeStammXY[0] = (int) (width*(box.getRelativeWidth())-boxWidth/2);
	            			spitzeStammXY[1] = (int) (height*(box.getRelativeHeight())+boxHeight/2);
	            			break;
	            		case STAMM:
	            			spitzeStammXY[2] = (int) (width*(box.getRelativeWidth())+boxWidth/2);
	            			spitzeStammXY[3] = (int) (height*(box.getRelativeHeight())+boxHeight/2);
	            			break;
	            		case ABZWEIG:
	            			System.out.println("Abzweig");
	            			mitteAbzweigXY[2] = (int) (width*(box.getRelativeWidth())+boxWidth/2);
	            			System.out.println("relativ width: "+ box.getRelativeWidth());
	            			mitteAbzweigXY[3] = (int) (height*(box.getRelativeHeight())+boxHeight/2);
	            			System.out.println("relativ height: "+ box.getRelativeHeight());
            		}
            	}
            }
            System.out.println("width: "+width);
            System.out.println("height: "+height);
            System.out.println("spitzeStammXY");
            for(int i : spitzeStammXY){
            	System.out.println(i);
            }
            System.out.println("mitteAbzweigXY");
            for(int i : mitteAbzweigXY){
            	System.out.println(i);
            }
            System.out.println();
            
            mitteAbzweigXY[1] = getY_from_Array(mitteAbzweigXY,25);
            
            for(int i = 0; i < 2;i++){
            	for(int j=0; j < 2; j++){
            		System.out.println("PolyLine x: " + (i==0 ? spitzeStammXY[0+j*2] : mitteAbzweigXY[0+j*2]));
		            polylines.get(i).getPoints().get(j).setX(i==0 ? spitzeStammXY[0+j*2] : mitteAbzweigXY[0+j*2]);
		            System.out.println("PolyLine y: " + (i==0 ? spitzeStammXY[1+j*2] : mitteAbzweigXY[1+j*2]));
		            polylines.get(i).getPoints().get(j).setY(i==0 ? spitzeStammXY[1+j*2] : mitteAbzweigXY[1+j*2]);
		            /*if(j==1){
		            	polylines.get(i).getPoints().get(j).setY(0);
		            	polylines.get(i).getPoints().get(j).setX(0);
		            }*/
            	}
            }
        }
 
        return false;
    }
    //TODO better comment
    /**
     * Calculate the Y pos for the straight line (port Stamm to port Ende) for a x pos
     * @param mitteAbzweigXY  
     * @param x
     * @return
     */
	private int getY_from_Array(int[] mitteAbzweigXY, int x) {
		double m = (mitteAbzweigXY[3] - mitteAbzweigXY[1])/(mitteAbzweigXY[2] - mitteAbzweigXY[0]);
		double b = mitteAbzweigXY[1]-m*mitteAbzweigXY[0];
		return (int) (m*x+b);
		
	}
}
