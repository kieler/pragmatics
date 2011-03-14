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
import org.eclipse.graphiti.mm.pictograms.PictogramElement;

import de.menges.topologie.Topologie.SpecializedVertices.*;
import de.cau.cs.kieler.rail.editor.RotationSwitchHandler;

/**
 * Toggles a switch between left and right switch.
 *
 * @author hdw
 */
public class ToggleSwitchFeature extends AbstractCustomFeature {

	/**
	 * Name of this Custom Feature
	 */
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
	public final String getName() {
        return NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
	public final String getDescription() {
        return "Wandelt Weiche von "
        + "links Weiche nach rechts Weiche oder umgekert";
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
                Weichenknoten wk = (Weichenknoten) bo;
                Property propertyNodeType = null;
                Property propertyAngle = null;
                for (Property property : pes[0].getProperties()) {
                    if (AddVertexFeature.KLAY_NODETYPE_KEY
                    == property.getKey()) {
                       propertyNodeType = property;
                    } else if (RotationSwitchHandler.MULTIPLEANGLE_KEY
                    == property.getKey()) {
                        propertyAngle = property;
                    }
                }
                if (wk.getAbzweigendeLage() == EOrientation.LINKS) {
	                wk.setAbzweigendeLage(EOrientation.RECHTS);
	                propertyNodeType.setValue("SWITCH_RIGHT");
	              //0 2  3  5
	            } else {
	                wk.setAbzweigendeLage(EOrientation.LINKS);
	                propertyNodeType.setValue("SWITCH_LEFT");
	              //0 1 3 4
	            }
                int multipleangle = 0;
        		multipleangle =
        		RotationSwitchHandler.getValidMultipleAngle(
        		Integer.parseInt(propertyAngle.getValue()),
        		wk.getAbzweigendeLage());

        		RotationSwitchHandler.setMultipleAngle(pes[0],
        		getFeatureProvider(), multipleangle, 0.1);

	            updatePictogramElement(pes[0]);
	        }
        }
    }
}
