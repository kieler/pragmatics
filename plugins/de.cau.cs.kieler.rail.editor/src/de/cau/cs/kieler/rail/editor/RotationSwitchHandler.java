/**
 * 
 */
package de.cau.cs.kieler.rail.editor;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.mm.MmFactory;
import org.eclipse.graphiti.mm.Property;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.BoxRelativeAnchor;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;

import de.menges.topologie.Topologie.Basegraph.EPort;
import de.menges.topologie.Topologie.Basegraph.Port;
import de.menges.topologie.Topologie.SpecializedVertices.EOrientation;
import de.menges.topologie.Topologie.SpecializedVertices.Weichenknoten;

/**
 * 
 * @author hdw
 *
 */
public class RotationSwitchHandler {
	/**
	 * How many x and y pos has got a switch (3 Ports * 2 (x & y) = 6
	 */
	private static final int NUMBER_OF_XY_SW = 6;

	/**
	 * there are 6 switch types for each orientation (left & right)
	 */
	private static final int MAX_MULTIOLEANGLE = 6;

	/**
	 * The Key for the MULTIPLEANGLE witch is saved in the switch
	 */
	public static final String MULTIPLEANGLE_KEY =
	"layout:de.cau.cs.kieler.krail.editor.multipleAngle";

	private static final double MAX_RELATIV_SIZE = 0.5;

	//1.=SPITZE,2.=STAMM,3.=ABZWEIG
	private static double[] portPosL = {
	0   , 0.5 , 1   , 0.5 , 0.75, 0,    //L0
	0   , 0   , 0   , 0   , 0   , 0,    //L1
	0.75, 1   , 0.25, 0   , 0   , 0.5,  //L2
	1   , 0.5 , 0   , 0.5 , 0.25, 1,    //L3
	0   , 0   , 0   , 0   , 0   , 0,    //L4
	0.25, 0   , 0.75, 1   , 1   , 0.5,  //L5
	};

	//1.=SPITZE,2.=STAMM,3.=ABZWEIG         
	private static double[] portPosR = {
	0   , 0.5 , 1   , 0.5 , 0.75, 1  ,  //R0
	0.25, 1   , 0.75, 0   , 1   , 0.5,  //R1
	0   , 0   , 0   , 0   , 0   , 0  ,  //R2
	1   , 0.5 , 0   , 0.5 , 0.25, 0  ,  //R3
	0.75, 0   , 0.25, 1   , 0   , 0.5,  //R4
	0   , 0   , 0   , 0   , 0   , 0  ,  //R5
	};

	/**
	 * A standard constructor.
	 */
	public RotationSwitchHandler() {

	}

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
                    	ports.add((BoxRelativeAnchor) anchor);
                    }
                }
        	}
		}
		return ports;
	}
	/**
	 * Get the MultipleAngle of this PE
	 * @param pictogramElement from witch
	 *  pictogramElement the MultipleAngle is get from
	 * @return the MultipleAngle
	 */
	public static int getMultipleAngle(
	final PictogramElement pictogramElement) {
		for (Property property : pictogramElement.getProperties()) {
			if (property.getKey() == MULTIPLEANGLE_KEY) {
				return Integer.getInteger(property.getValue());
			}
		}
		return 0;
	}
	/**
	 * Set the ports for this switch at the right positions.
	 * In an constant array are the relative (double 0-1) x,y positions
	 * @param pictogramElement the pictogramElement witch the switch is
	 * @param fp FeatureProvider
	 *        (to get the getBusinessObjectForPictogramElement)
	 * @param multipleAngle int 0-5
	 * @param offset
	 * @param offset
	 */
	public static void setMultipleAngle(
	final PictogramElement pictogramElement,
	final IFeatureProvider fp, final int multipleAngle,
	final double offset) {
		List<BoxRelativeAnchor> ports =
		getPortsFromPE(pictogramElement, fp);

		Object bo =
		fp.getBusinessObjectForPictogramElement(pictogramElement);
		if ((bo instanceof Weichenknoten)
		&& (multipleAngle < MAX_MULTIOLEANGLE)) {
			Weichenknoten wk = (Weichenknoten) bo;

			double x = 0;
			double y =  0;
			for (BoxRelativeAnchor port : ports) {
				Port portBO = (Port)
				fp.getBusinessObjectForPictogramElement(port);
				if (wk.getAbzweigendeLage()
				== EOrientation.LINKS) {
					x = portPosL[NUMBER_OF_XY_SW
					 * multipleAngle
					 + portTypeToInt(portBO.getName()) * 2];
					y = portPosL[NUMBER_OF_XY_SW
					* multipleAngle
					+ portTypeToInt(portBO.getName())
					* 2 + 1];
				}
				else if (wk.getAbzweigendeLage()
				== EOrientation.RECHTS) {
					x = portPosR[NUMBER_OF_XY_SW
					* multipleAngle
					+ portTypeToInt(portBO.getName()) * 2];
					y = portPosR[NUMBER_OF_XY_SW
					* multipleAngle
					+ portTypeToInt(portBO.getName())
					* 2 + 1];
				}
				port.setRelativeWidth(validValue(x, offset));
				port.setRelativeHeight(validValue(y, offset));
			}
			boolean changed = false;
			for (Property property
			: pictogramElement.getProperties()) {
				if (property.getKey() == MULTIPLEANGLE_KEY) {
					property.setValue(
					   Integer.toString(multipleAngle));
					changed = true;
				}
			}
		}
	}
	/**  TODO Comment
	 *  Checks the value to not be greater then MAX_RELATIV_SIZE
	 * @param value
	 * @param offset
	 * @return
	 */
	private static double validValue(final double value, final double offset) {
		if (value >= MAX_RELATIV_SIZE) {
			return value - offset * (value / MAX_RELATIV_SIZE);
		} else {
			return value;
		}
	}

	/**
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
	/**
	 * Translate the EPort (Porttype) to a nummber
	 * @param portType portType  (SPITZE, STAMM, ABZWEIG)
	 * @return Number 0-2
	 */
	private static int portTypeToInt(final EPort portType) {
		switch (portType) {
		case SPITZE:
			return 0;
		case STAMM:
			return 1;
		case ABZWEIG:
			return 2;
		default:
			break;
		}
		return -1;
	}
	/**
	 * Set the ports for this switch at the right positions.
	 * In an constant array are the relative (double 0-1) x,y positions
	 * Use the the same multipleAngle as in source
	 * @param pictogramElement the pictogramElement witch the switch is
	 * @param featureProvider FeatureProvider
	 *  (to get the getBusinessObjectForPictogramElement)
	 * @param offset 
	 */
	public static void setMultipleAngle(
	final PictogramElement pictogramElement,
	final IFeatureProvider featureProvider, final double offset) {
		setMultipleAngle(pictogramElement
		, featureProvider, getMultipleAngle(pictogramElement), offset);
	}
	/**
	 * for Left switch are only  0 2 3 5 valid
	 * for Right switch are only 0 1 3 4 valid
	 * @param value value witch is to check
	 * @param abzweigendeLage The oration for this switch.
	 * @return valid and between 0-5
	 */
	public static int getValidMultipleAngle(final int value,
			final EOrientation abzweigendeLage) {
		if (abzweigendeLage == EOrientation.LINKS) {
			//0 2  3  5
			if (value == 1) {
				return 2;
			} else if (value == 4) {
				return 5;
			}
		} else {
			//0 1 3 4
			if (value == 2) {
				return 3;
			} else if (value == 5) {
				return 0;
			}
		}
		return (value % MAX_MULTIOLEANGLE);
	}
}