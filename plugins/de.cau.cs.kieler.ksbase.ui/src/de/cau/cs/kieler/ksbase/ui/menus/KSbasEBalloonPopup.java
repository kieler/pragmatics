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
import java.util.Map.Entry;

import org.eclipse.core.runtime.Platform;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.PopupBarEditPolicy;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageLoader;
import org.osgi.framework.Bundle;

import de.cau.cs.kieler.core.ui.CoreUIPlugin;
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
                    return ImageDescriptor.createFromURL(imageURL).createImage();
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
            TransformationUIManager.INSTANCE.createAndExecuteTransformationCommand(editor,
                    transformation);
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
     * @param editPart
     *            The edit part to use.
     * @return true
     */
    public boolean setEditPart(final EditPart editPart) {
        // Is valid for transformation ?
        return TESTER.test(null, null, new String[] {editor.getEditorId(),
                transformation.getTransformationId() }, null);
    }

}
