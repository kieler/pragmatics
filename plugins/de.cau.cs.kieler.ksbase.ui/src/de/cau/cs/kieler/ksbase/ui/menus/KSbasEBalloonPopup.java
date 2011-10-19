/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2009 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 * 
 *****************************************************************************/
package de.cau.cs.kieler.ksbase.ui.menus;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.osgi.framework.Bundle;

import de.cau.cs.kieler.core.model.gmf.policies.IBalloonContribution;
import de.cau.cs.kieler.core.model.xtend.transformation.AbstractTransformation;
import de.cau.cs.kieler.ksbase.core.EditorTransformationSettings;
import de.cau.cs.kieler.ksbase.core.KSBasETransformation;
import de.cau.cs.kieler.ksbase.core.TransformationManager;
import de.cau.cs.kieler.ksbase.ui.TransformationUIManager;
import de.cau.cs.kieler.ksbase.ui.test.ModelObjectTester;

/**
 * Edit policy for the KSBasE popup balloons.
 * 
 * @author mim
 * 
 * @kieler.rating 2010-03-22 proposed yellow
 */
public class KSbasEBalloonPopup implements IBalloonContribution {

    private KSBasETransformation transformation;
    private EditorTransformationSettings editorSettings;

    /** An array containing the single edit part for the policy. */
    private LinkedList<EObject> modelElements;

    // private static final ModelObjectTester TESTER = new ModelObjectTester();

    /**
     * Creates an empty balloon popup.
     */
    public KSbasEBalloonPopup() {
        transformation = null;
        editorSettings = null;
    }

    /**
     * Returns the image for the balloon.
     * 
     * @return The image, may return null if no images is defined
     */
    public Image getImage() {
        // FIXME: assert editorSettings != null
        if (editorSettings.getContributor() != null && transformation != null
                && transformation.getIcon().length() > 0) {

            Bundle b = Platform.getBundle(editorSettings.getContributor()
                    .getName());
            if (b != null) {
                URL imageURL = b.getResource(transformation.getIcon());
                if (imageURL != null) {
                    return ImageDescriptor.createFromURL(imageURL)
                            .createImage();
                }
            }
        }
        return null;
    }

    /**
     * Returns the name of the transformation for the popup tooltip.
     * 
     * @return The name of the transformation
     */
    public String getTooltip() {
        if (transformation != null) {
            return transformation.getToolTip();
        }
        return null;
    }

    /**
     * Executes the transformation by using the {@link TransformationUIManager}.
     */
    public void run() {
        if (editorSettings != null && transformation != null) {
            TransformationUIManager.INSTANCE
                    .createAndExecuteTransformationCommand(editorSettings,
                            transformation, modelElements);
        }

    }

    /**
     * Sets the balloon attributes. *
     * 
     * @param map
     *            A map of key-value pairs.
     */
    public void init(final Map<String, String> map) {
        String editorId = null;
        String tId = null;
 
        // FIXME: avoid loop, use hashmap!
        for (Entry<String, String> val : map.entrySet()) {
            if (val.getKey().equals("editorId")) {
                editorId = val.getValue();
            }
            if (val.getKey().equals("transformationId")) {
                tId = val.getValue();
            }
        }
        if (editorId != null && tId != null) {
            editorSettings = TransformationManager.INSTANCE
                    .getEditorSettingsById(editorId);
            assert (editorSettings != null);
            transformation = editorSettings.getTransformationById(tId);
        }

    }

    /**
     * Sets the balloon attributes.
     * 
     * @param editPartParam
     *            The edit part to use.
     * 
     */
    public void init(final EditPart editPartParam) {
        modelElements = new LinkedList<EObject>();
        modelElements.add(((View) (editPartParam).getModel()).getElement());

    }

    /**
     * Sets the corresponding edit part that is target for this balloon.
     * 
     * FIXME: seperate setter and tester
     * 
     * @return true iff the given EditPart is target for the balloon
     */
    public boolean isValid() {
        // Is valid for transformation ?
        if (transformation != null) {
            // Convert selection to model elements:
            boolean executable = false;
            for (List<String> params : transformation.getParameterList()) {

                if (ModelObjectTester.evaluateTransformation(editorSettings,
                        transformation.getTransformation(),
                        params.toArray(new String[params.size()]),
                        modelElements, false)) {
                    // Could the transformation be executed?
                    executable = true;
                }
            }
            // No parameter set could be mapped, so exit now
            if (!executable) {
                return false;
            }
            // First we will evaluate the validation transformation
            // This is a fast operation, test took between 0ms and 30ms (on
            // context switch) , so
            // it can be assumed as 'fast enough'.
            // But the actual time depends on the transformation to be executed
            // here, so better
            // use simple & fast ones :)
            String validation = transformation.getValidation();
            if (validation != null && validation.length() > 0) {
                for (String valid : validation.split(",")) {
                    AbstractTransformation at = editorSettings
                            .getOutPlaceTransformationByName(valid);
                    if (at != null) {
                        boolean isValid = false;
                        for (List<String> params : at.getParameterList()) {
                            if (ModelObjectTester.evaluateTransformation(
                                    editorSettings, valid,
                                    params.toArray(new String[params.size()]),
                                    modelElements, true)) {
                                isValid = true;
                            }
                        }
                        if (!isValid) {
                            return false;
                        }
                    }
                }
            }
            return true;
        }
        return false;
        // return TESTER.test(null, null, new String[] { editor.getEditorId(),
        // transformation.getTransformationId() }, null);

    }
}
