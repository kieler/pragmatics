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
 * Update feature for the switch It updates the lines for the ports.
 * 
 * @author hdw
 */
public class UpdateSwitchFeature extends AbstractUpdateFeature {

    private static final int[] SPITZE_STAMM_DEFAULT = { 0, 0, 0, 0 };

    private static final int[] MITTE_ABZWEIG_DEFAULT = { 25, 25, 0, 0 };

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

        PictogramElement pictogramElement = context.getPictogramElement();

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
            if (graphicsAlgorithm instanceof Polyline) {
                polylines.add((Polyline) graphicsAlgorithm);
            }
        }
        // Polylines end

        if (pictogramElement instanceof ContainerShape) {
            ContainerShape cs = (ContainerShape) pictogramElement;

            for (Anchor anchor : cs.getAnchors()) {
                if (anchor instanceof BoxRelativeAnchor) {
                    Port port =
                            (Port) getBusinessObjectForPictogramElement(anchor);
                    BoxRelativeAnchor box =
                            (BoxRelativeAnchor) anchor.getGraphicsAlgorithm()
                                    .getPictogramElement();
                    int boxWidth = anchor.getGraphicsAlgorithm().getWidth();
                    int boxHeight = anchor.getGraphicsAlgorithm().getWidth();
                    switch (port.getName()) {
                    case SPITZE:
                        spitzeStammXY[SPITZE_X] =
                                (int) (width * (box.getRelativeWidth()) - boxWidth / 2);
                        spitzeStammXY[SPITZE_Y] =
                                (int) (height * (box.getRelativeHeight()) + boxHeight / 2);
                        break;
                    case STAMM:
                        spitzeStammXY[STAMM_X] =
                                (int) (width * (box.getRelativeWidth()) + boxWidth / 2);
                        spitzeStammXY[STAMM_Y] =
                                (int) (height * (box.getRelativeHeight()) + boxHeight / 2);
                        break;
                    case ABZWEIG:
                        System.out.println("Abzweig");
                        mitteAbzweigXY[ABZWEIG_X] =
                                (int) (width * (box.getRelativeWidth()) + boxWidth / 2);
                        System.out.println("relativ width: "
                                + box.getRelativeWidth());
                        mitteAbzweigXY[ABZWEIG_Y] =
                                (int) (height * (box.getRelativeHeight()) + boxHeight / 2);
                        System.out.println("relativ height: "
                                + box.getRelativeHeight());
                        break;
                    case ENDE:
                    default:
                        break;
                    }
                }
            }
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
                    getYFromArray(mitteAbzweigXY,
                            MITTE_ABZWEIG_DEFAULT[MITTE_X]);

            for (int i = 0; i < 2; i++) {
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
                    // if (j == 1) {
                    // polylines.get(i).getPoints().get(j).setY(0);
                    // polylines.get(i).getPoints().get(j).setX(0);
                    // }
                }
            }
            getDiagramEditor().refresh();
        }

        return false;
    }

    // TODO better comment
    /**
     * Calculate the Y pos for the straight line (port Stamm to port Ende) for a
     * x pos.
     * 
     * @param mitteAbzweigXY
     *            the position array
     * @param x
     *            the x position
     * @return the y position
     */
    private int getYFromArray(final int[] mitteAbzweigXY, final int x) {
        double m =
                (mitteAbzweigXY[ABZWEIG_Y] - mitteAbzweigXY[MITTE_Y])
                        / (mitteAbzweigXY[ABZWEIG_X] - mitteAbzweigXY[MITTE_X]);
        double b = mitteAbzweigXY[MITTE_Y] - m * mitteAbzweigXY[MITTE_X];
        return (int) (m * x + b);

    }
}
