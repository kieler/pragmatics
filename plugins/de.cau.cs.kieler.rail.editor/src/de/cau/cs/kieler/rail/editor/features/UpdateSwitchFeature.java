/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
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
import org.eclipse.graphiti.mm.algorithms.Polygon;
import org.eclipse.graphiti.mm.algorithms.Polyline;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.BoxRelativeAnchor;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;

import de.cau.cs.kieler.rail.Topologie.Basegraph.Port;
import de.cau.cs.kieler.rail.Topologie.Basegraph.Vertex;
import de.cau.cs.kieler.rail.Topologie.SpecializedVertices.EOrientation;
import de.cau.cs.kieler.rail.Topologie.SpecializedVertices.Weichenknoten;
import de.cau.cs.kieler.rail.editor.Geometry;

/**
 * Update feature for the switch It updates the lines for the ports.
 * 
 * @author hdw
 */
public class UpdateSwitchFeature extends AbstractUpdateFeature {

/**The default values SPITZE STAMM */
    private static final int[] SPITZE_STAMM_DEFAULT = {0, 0, 0, 0 };
/**The default values MITTE ABZWEIG */
    private static final int[] MITTE_ABZWEIG_DEFAULT = {25, 25, 0, 0 };

    private static final int SPITZE_X = 0;
    private static final int SPITZE_Y = 1;
    private static final int STAMM_X = 2;
    private static final int STAMM_Y = 3;

    private static final int MITTE_X = 0;
    private static final int MITTE_Y = 1;
    private static final int ABZWEIG_X = 2;
    private static final int ABZWEIG_Y = 3;

    /**
     * Updater for the switch.
     * 
     * @param fp
     *            the feature provider
     */
    public UpdateSwitchFeature(final IFeatureProvider fp) {
        super(fp);
    }

    /**
     * {@inheritDoc}
     */
    public boolean canUpdate(final IUpdateContext context) {
        // return true, if linked business object is a EClass
        Object bo =
                getBusinessObjectForPictogramElement(context
                        .getPictogramElement());
        return (bo instanceof Weichenknoten);
    }

    /**
     * {@inheritDoc}
     */
    public IReason updateNeeded(final IUpdateContext context) {
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
        boolean noPicNameButBusName =
                pictogramName == null && businessName != null;
        boolean picNameButOutOfDate =
                pictogramName != null && !pictogramName.equals(businessName);
        boolean updateNameNeeded = noPicNameButBusName || picNameButOutOfDate;
        if (updateNameNeeded) {
            return Reason.createFalseReason();
            // return Reason.createTrueReason("Name is out of date");
        }
        return Reason.createFalseReason();
    }

    /**
     * {@inheritDoc}
     */
    public boolean update(final IUpdateContext context) {
    	int[] spitzeStammXY = SPITZE_STAMM_DEFAULT.clone();
        int[] mitteAbzweigXY = MITTE_ABZWEIG_DEFAULT.clone();
        List<Polyline> polylines = new LinkedList<Polyline>();
        Polygon trianglePolygon = null;

        PictogramElement pictogramElement = context.getPictogramElement();
        Vertex bo = (Vertex)
        	getBusinessObjectForPictogramElement(pictogramElement);

        int width = pictogramElement.getGraphicsAlgorithm().getWidth();
        int height = pictogramElement.getGraphicsAlgorithm().getHeight();

        // Polylines
        ContainerShape containerShape =
                (ContainerShape) context.getPictogramElement();
        Iterator<Shape> iter = containerShape.getChildren().iterator();
        while (iter.hasNext()) {
            Shape shape = iter.next();
            System.out.println(shape);
            GraphicsAlgorithm graphicsAlgorithm = shape.getGraphicsAlgorithm();
            System.out.println(graphicsAlgorithm);
            if (graphicsAlgorithm instanceof Polygon) {
                trianglePolygon = (Polygon) graphicsAlgorithm;
            } else if (graphicsAlgorithm instanceof Polyline) {
                polylines.add((Polyline) graphicsAlgorithm);
            }  else if (graphicsAlgorithm instanceof Text) {
            	((Text) graphicsAlgorithm).setValue(bo.getName());
            }
        }
        // Polylines end
        
        
        if (pictogramElement instanceof ContainerShape) {
        	
        	ContainerShape cs = (ContainerShape) pictogramElement;
            int[][] SpitzeStammMitteAbzweigXY = getSpitzeStammMitteAbzweigXY(cs.getAnchors(),height,width, getFeatureProvider());
            spitzeStammXY = SpitzeStammMitteAbzweigXY[0];
            mitteAbzweigXY = SpitzeStammMitteAbzweigXY[1];

            System.out.println("width: " + width);
            System.out.println("height: " + height);
            System.out.println("spitzeStammXY");
            for (int i : spitzeStammXY) {
                System.out.println(i);
            }
            System.out.println("mitteAbzweigXY");
            for (int i : mitteAbzweigXY) {
                System.out.println(i);
            }
            System.out.println();

            mitteAbzweigXY[MITTE_Y] =
                    Geometry.getYFromArray(mitteAbzweigXY,
                            MITTE_ABZWEIG_DEFAULT[MITTE_X]);
            
            for (int i = 0; i < 2; i++) {//set the new position for the Polylines
                for (int j = 0; j < 2; j++) {
                    System.out.println("PolyLine x: "
                            + (i == 0 ? spitzeStammXY[0 + j * 2]
                                    : mitteAbzweigXY[0 + j * 2]));
                    polylines
                            .get(i)
                            .getPoints()
                            .get(j)
                            .setX(i == 0 ? spitzeStammXY[0 + j * 2]
                                    : mitteAbzweigXY[0 + j * 2]);
                    System.out.println("PolyLine y: "
                            + (i == 0 ? spitzeStammXY[1 + j * 2]
                                    : mitteAbzweigXY[1 + j * 2]));
                    polylines
                            .get(i)
                            .getPoints()
                            .get(j)
                            .setY(i == 0 ? spitzeStammXY[1 + j * 2]
                                    : mitteAbzweigXY[1 + j * 2]);
                }
            }
            //triangle refresh
            if (trianglePolygon != null) {
            	setPolygonPoints(trianglePolygon, getPolygonXY(spitzeStammXY,mitteAbzweigXY,((Weichenknoten) bo).getAbzweigendeLage()));  //not nice
            //triangle was refreshed

            getDiagramEditor().refresh();
        }
    }
    return false;
}
    
    public static int[] getPolygonXY(int[] spitzeStammXY, int[] mitteAbzweigXY, EOrientation orientation ) {
    	int dx, dy;
    	
    	dx = mitteAbzweigXY[MITTE_X] - mitteAbzweigXY[ABZWEIG_X];
    	dy = spitzeStammXY[STAMM_Y] - mitteAbzweigXY[ABZWEIG_Y];
    	
    	double angle = dy/dx;//Math.atan2(dy, dx);
    	
    	if (orientation == EOrientation.RECHTS) {
    		angle = - angle;
    	}
    	
    	int[] polyXY = new int[] {mitteAbzweigXY[0],
                mitteAbzweigXY[1], (int) (mitteAbzweigXY[MITTE_X]
                + dx * angle * 0.25), 0, 0, 0};

    	System.out.println("dx: " + dx);
    	System.out.println("dy: " + dy);
    	System.out.println("angle " + angle);

        polyXY[3] = Geometry.getYFromArray(mitteAbzweigXY, polyXY[2]);
    	polyXY[4] = polyXY[2];
        polyXY[5] = Geometry.getYFromArray(spitzeStammXY, polyXY[2]);

    	return polyXY;
    }

    /**
     * 
     * @param anchors
     * @param height
     * @param width
     * @return spitzeStammXY, mitteAbzweigXY
     */
    public static int[][] getSpitzeStammMitteAbzweigXY(List<Anchor> anchors, double height, double width, IFeatureProvider fp) {
    	int[] spitzeStammXY = {0, 0, 0, 0};
    	int[] mitteAbzweigXY = MITTE_ABZWEIG_DEFAULT.clone();
    	for (Anchor anchor : anchors) {
            if (anchor instanceof BoxRelativeAnchor) {
                
            	Port port =
                        (Port) fp.getBusinessObjectForPictogramElement(anchor);
                BoxRelativeAnchor box =
                        (BoxRelativeAnchor) anchor.getGraphicsAlgorithm()
                                .getPictogramElement();
                int boxWidth = anchor.getGraphicsAlgorithm().getWidth();
                int boxHeight = anchor.getGraphicsAlgorithm().getWidth();
                switch (port.getName()) {
                case SPITZE:
                    spitzeStammXY[SPITZE_X] =
                            (int) (width * (box.getRelativeWidth())
                                     + boxWidth / 2);
                    spitzeStammXY[SPITZE_Y] =
                            (int) (height * (box.getRelativeHeight())
                                  + boxHeight / 2);

                    System.out.println("SPITZE");

                    System.out.println("relativ width: "
                            + box.getRelativeWidth());
                    System.out.println("relativ height: "
                            + box.getRelativeHeight());
                    break;
                case STAMM:
                    spitzeStammXY[STAMM_X] =
                            (int) (width * (box.getRelativeWidth())
                                   + boxWidth / 2);
                    spitzeStammXY[STAMM_Y] =
                            (int) (height * (box.getRelativeHeight())
                            + boxHeight / 2);

                    System.out.println("STAMM");

                    System.out.println("relativ width: "
                            + box.getRelativeWidth());
                    System.out.println("relativ height: "
                            + box.getRelativeHeight());
                    break;
                case ABZWEIG:
                    System.out.println("Abzweig");
                    mitteAbzweigXY[ABZWEIG_X] =
                            (int) (width * (box.getRelativeWidth())
                            + boxWidth / 2);
                    System.out.println("relativ width: "
                            + box.getRelativeWidth());
                    System.out.println("relativ height: "
                            + box.getRelativeHeight());
                    mitteAbzweigXY[ABZWEIG_Y] =
                            (int) (height * (box.getRelativeHeight())
                            + boxHeight / 2);
                    break;
                case ENDE:
                default:
                    break;
                }
            }
    	}
    	return new int[][] {spitzeStammXY , mitteAbzweigXY};
    }
    /**
     *makes a string out of an array
     * @param polyXY the array
     * @return The numbers of the array divided by a ,
     */
    private String arrayToString(final int[] polyXY) {
		String ret = "";
		for (int i : polyXY) {
			ret += (i + ", ");
		}
		return ret;
	}
    /**
     * Set a polygon with the XY pos from the array.
     * @param polygon The polygon witch the pos are set for.
     * @param polyXY The points in an array
     */
	private void setPolygonPoints(final Polygon polygon,
			final int[] polyXY) {
    	for (int i = 0; i < polygon.getPoints().size(); i++) {
    		polygon.getPoints().get(i).setX(polyXY[i * 2]);
    		polygon.getPoints().get(i).setY(polyXY[i * 2 + 1]);
    	}
	}


}
