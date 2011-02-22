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

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.custom.AbstractCustomFeature;
import org.eclipse.graphiti.mm.Property;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.BoxRelativeAnchor;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;

import de.cau.cs.kieler.rail.Topologie.Basegraph.EPort;
import de.cau.cs.kieler.rail.Topologie.Basegraph.Port;
import de.cau.cs.kieler.rail.Topologie.SpecializedVertices.EOrientation;
import de.cau.cs.kieler.rail.Topologie.SpecializedVertices.Weichenknoten;

/**
 * Toggles a switch between left and right switch.
 * 
 * @author hdw
 */
public class ToggleSwitchFeature extends AbstractCustomFeature {

    public static final String NAME = "ToggleSwitch";

    /**
     * 
     * Creates a new ToggleSwitchFeature.
     * 
     * @param fp
     *            the feature provider
     */
    public ToggleSwitchFeature(final IFeatureProvider fp) {
        super(fp);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
        return "Wandelt Weiche von links Weiche nach rechts Weiche oder umgekert";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canExecute(final ICustomContext context) {
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

        // System.out.println("canExecute" + (ret ? " true" : " false"));
        // Exception e = new Exception();
        // e.printStackTrace();
        return ret;
    }

    /**
     * {@inheritDoc}
     */
    public final void execute(final ICustomContext context) {
        PictogramElement[] pes = context.getPictogramElements();
        if (pes != null && pes.length == 1) {
            Object bo = getBusinessObjectForPictogramElement(pes[0]);
            if (bo instanceof Weichenknoten) {
                Weichenknoten weichenknoten = (Weichenknoten) bo;
                String currentName = weichenknoten.getName();

                PictogramElement pictogramElement =
                        context.getInnerPictogramElement();

                // toggeln
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
                                box.setRelativeWidth(Math.abs(1 - box
                                        .getRelativeWidth()));
                                // box.setRelativeWidth(0.5);
                            }
                        }
                    }
                    // toggeln
                    Weichenknoten wk = ((Weichenknoten) bo);

                    Property property =
                            context.getInnerPictogramElement().getProperties()
                                    .get(0);
                    if (wk.getAbzweigendeLage() == EOrientation.LINKS) {
                        wk.setAbzweigendeLage(EOrientation.RECHTS);
                        property.setValue("SWITCH_RIGHT");
                    } else {
                        wk.setAbzweigendeLage(EOrientation.LINKS);
                        property.setValue("SWITCH_LEFT");
                    }
                    updatePictogramElement(pictogramElement);
                }
            }
        }
    }
}
