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
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.osgi.framework.Bundle;

import de.cau.cs.kieler.core.ui.policies.IBalloonContribution;
import de.cau.cs.kieler.ksbase.core.EditorTransformationSettings;
import de.cau.cs.kieler.ksbase.core.KSBasETransformation;
import de.cau.cs.kieler.ksbase.core.TransformationManager;
import de.cau.cs.kieler.ksbase.ui.TransformationUIManager;
import de.cau.cs.kieler.ksbase.ui.test.ModelObjectTester;

/**
 * Edit policy for the.
 * 
 * @author mim
 * 
 */
public class KSbasEBalloonPopup implements IBalloonContribution {

    private KSBasETransformation transformation;
    private EditorTransformationSettings editor;

    /** An array containing the single edit part for the policy. */
    private LinkedList<EObject> modelElements;
    private static final ModelObjectTester TESTER = new ModelObjectTester();

    /**
     * Creates an empty balloon popup.
     */
    public KSbasEBalloonPopup() {
        transformation = null;
        editor = null;
    }

    /**
     * Returns the image for the balloon.
     * 
     * @return The image, may return null if no images is defined
     */
    public Image getImage() {
        if (transformation != null && transformation.getIcon().length() > 0) {

            Bundle b = Platform.getBundle(editor.getContributor().getName());
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
            return transformation.getTransformation();
        }
        return null;
    }

    /**
     * Executes the transformation by using the {@link TransformationUIManager}.
     */
    public void run() {
        if (editor != null && transformation != null) {
            TransformationUIManager.INSTANCE
                    .createAndExecuteTransformationCommand(editor,
                            transformation, modelElements);
        }

    }

    /**
     * Sets the balloon attributes.
     * 
     * @param map
     *            A map of key-value pairs.
     */
    public void setAttributes(final HashMap<String, String> map) {
        String editorID = null;
        String tid = null;

        for (Entry<String, String> val : map.entrySet()) {
            if (val.getKey().equals("editorId")) {
                editorID = val.getValue();
            }
            if (val.getKey().equals("transformationId")) {
                tid = val.getValue();
            }
        }
        if (editorID != null && tid != null) {
            editor = TransformationManager.INSTANCE.getEditorById(editorID);
            transformation = editor.getTransformationById(tid);
        }
    }

    /**
     * Sets the corresponding edit part, this is unused here.
     * 
     * @param editPartParam
     *            The edit part to use.
     * @return true
     */
    public boolean setEditPart(final EditPart editPartParam) {
        modelElements = new LinkedList<EObject>();
        modelElements.add(((View) (editPartParam).getModel()).getElement());
        // Is valid for transformation ?
        if (transformation != null) {
            // Convert selection to model elements:
            boolean executable = false;
            for (List<String> params : transformation.getParameterList()) {
                if (ModelObjectTester.evaluateTransformation(editor,
                        transformation.getTransformation(), params
                                .toArray(new String[params.size()]), false)) {
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
                    if (!ModelObjectTester.evaluateTransformation(editor,
                            valid, modelElements
                                    .toArray(new Object[modelElements.size()]),
                            true)) {
                        return false;
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
