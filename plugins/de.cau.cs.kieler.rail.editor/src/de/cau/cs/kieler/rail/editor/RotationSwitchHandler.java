/**
 * 
 */
package de.cau.cs.kieler.rail.editor;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.BoxRelativeAnchor;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;

import de.cau.cs.kieler.rail.Topologie.Basegraph.EPort;
import de.cau.cs.kieler.rail.Topologie.Basegraph.Port;
import de.cau.cs.kieler.rail.Topologie.SpecializedVertices.EOrientation;
import de.cau.cs.kieler.rail.Topologie.SpecializedVertices.Weichenknoten;

/**
 * 
 * @author hdw
 *
 */
public class RotationSwitchHandler {
	
	private static final int NUMBER_OF_XY_SW=6;

	private static final int MAX_MULTIOLEANGLE = 6;

	//1.=SPITZE,2.=STAMM,3.=ABZWEIG         
	private static double[] portPosL = {
	0   ,0.5 ,1   ,0.5 ,3/4 ,0,    //L0
	0   ,0   ,0   ,0   ,0   ,0,    //L1
	3/4 ,1   ,1/4 ,0   ,0   ,0.5,  //L2
	1   ,0.5 ,0   ,0.5 ,1/4 ,1,    //L3
	0   ,0   ,0   ,0   ,0   ,0,    //L4
	1/4 ,0   ,3/4 ,1   ,0   ,0.5,  //L5
	};
	
	//1.=SPITZE,2.=STAMM,3.=ABZWEIG         
	private static double[] portPosR = {
	0   ,0.5 ,1   ,0.5 ,3/4 ,1,    //R0
	1/4 ,1   ,3/4 ,0   ,1   ,0.5,  //R1
	0   ,0   ,0   ,0   ,0   ,0  ,  //R2
	1   ,0.5 ,0   ,0.5 ,1/4 ,0,    //R3
	3/4 ,0   ,1/4 ,1   ,0   ,0.5,  //R4
	0   ,0   ,0   ,0   ,0   ,0  ,  //R5
	};
	
	/**
	 * Calculate the angle from the pictogramelement.
	 * @param pictogramElement from witch pictogramelement
	 * @param fp the FeatureProvider
	 * @return angle
	 */
	public static List<BoxRelativeAnchor> getPortsFromPE(
	final PictogramElement pictogramElement, final IFeatureProvider fp) {
		List<BoxRelativeAnchor> ports = null;
		Object bo =
		fp.getBusinessObjectForPictogramElement(pictogramElement);
		if (bo instanceof Weichenknoten) {
			ports = new LinkedList<BoxRelativeAnchor>();
        	if (pictogramElement instanceof ContainerShape) {
        		//this are the right objekts?
        		ContainerShape cs = (ContainerShape) pictogramElement;
                for (Anchor anchor : cs.getAnchors()) {
                    if (anchor instanceof BoxRelativeAnchor) {
                    	ports.add((BoxRelativeAnchor)anchor);
                    }
                }
        	}
		}
		return ports;
	}
	/**
	 * 
	 * @param pictogramElement
	 * @param fp
	 * @return
	 */
	public static int getMultipleAngle(
	final PictogramElement pictogramElement, final IFeatureProvider fp) {
		return -1;
	}
	/**
	 * Set the ports for this switch at the right positions.
	 * In an constant array are the relative (double 0-1) x,y positions
	 * @param pictogramElement the pictogramElement witch the switch is
	 * @param fp FeatureProvider (to get the getBusinessObjectForPictogramElement)
	 * @param multipleAngle int 0-5
	 */
	public static void setMultipleAngle(
	final PictogramElement pictogramElement,
	final IFeatureProvider fp, final int multipleAngle) {
		List<BoxRelativeAnchor> ports =
		getPortsFromPE(pictogramElement, fp);
		
		Object bo = fp.getBusinessObjectForPictogramElement(pictogramElement);
		if((bo instanceof Weichenknoten)&&(multipleAngle < MAX_MULTIOLEANGLE)){
			Weichenknoten wk = (Weichenknoten) bo;
	
			double x=0;
			double y=0;
			for(BoxRelativeAnchor port: ports){
				Port portBO = (Port) fp.getBusinessObjectForPictogramElement(port);
				if (wk.getAbzweigendeLage() == EOrientation.LINKS) {
					x = portPosL[NUMBER_OF_XY_SW*multipleAngle+portTypeToInt(portBO.getName())*2];
					y = portPosL[NUMBER_OF_XY_SW*multipleAngle+portTypeToInt(portBO.getName())*2+1];
				}
				else if(wk.getAbzweigendeLage() == EOrientation.RECHTS){
					x = portPosR[NUMBER_OF_XY_SW*multipleAngle+portTypeToInt(portBO.getName())*2];
					y = portPosR[NUMBER_OF_XY_SW*multipleAngle+portTypeToInt(portBO.getName())*2+1];
				}
				port.setRelativeWidth(x);
				port.setRelativeHeight(y);
			}
		}
	}
	/**
	 * 
	 * @param portType
	 * @param ports
	 * @param fp
	 * @return
	 */
	private Anchor getAnchor(EPort portType, List<BoxRelativeAnchor> ports, final IFeatureProvider fp){
		for(BoxRelativeAnchor port: ports){
			Object bo = fp.getBusinessObjectForPictogramElement(port);
			if(bo instanceof Port){
				if(((Port)bo).getName() == portType){
					return port;
				}
			}
		}
		return null;
	}
	private static int portTypeToInt(EPort portType){
		switch (portType) {
		case SPITZE:
			return 0;
		case STAMM:
			return 1;
		case ENDE:
			return 2;
		}
		return -1;
	}
}