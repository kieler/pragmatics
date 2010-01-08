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
package de.cau.cs.kieler.ksbase.ui.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

import de.cau.cs.kieler.ksbase.core.EditorTransformationSettings;
import de.cau.cs.kieler.ksbase.core.TransformationManager;
import de.cau.cs.kieler.ksbase.ui.KSBasEUIPlugin;
import de.cau.cs.kieler.ksbase.ui.TransformationUIManager;

/**
 * The generic transformation handler used for all UI actions.
 * 
 * @author mim
 * 
 * @kieler.rating 2009-12-15 proposed yellow
 */
public class TransformationCommandHandler extends AbstractHandler {

    /**
     * The list of classes an editor has to implement/extend to be valid for this framework.
     **/
    public static final String EDITOR_PARAM = "de.cau.cs.kieler.ksbase.editorParameter";
    /**
     * The list of classes a diagram element has to implement/extend to be valid for this framework.
     **/
    public static final String TRANSFORMATION_PARAM = "de.cau.cs.kieler.ksbase.transformationParameter";

    /**
     * Creates a new command handler.
     */
    public TransformationCommandHandler() {
    }

    /**
     * Executes a transformation. The editor and transformation settings are given by the extension
     * point parameters Uses the TransformationUI manager to create and execute the transformation.
     * 
     * @param event
     *            The source event
     * @throws ExecutionException
     *             If the execution failed.
     * @return Nothing
     */
    public Object execute(final ExecutionEvent event) throws ExecutionException {

        EditorTransformationSettings editor = TransformationManager.INSTANCE.getEditorById(event
                .getParameter(EDITOR_PARAM));
        if (editor != null) {
            TransformationUIManager.INSTANCE.createAndExecuteTransformationCommand(event, editor,
                    editor.getTransformationByName(event.getParameter(TRANSFORMATION_PARAM)));
        } else {
            KSBasEUIPlugin.getDefault().logError(
                    "Could not find " + event.getParameter(EDITOR_PARAM)
                            + ". Please check transformation settings for "
                            + event.getParameter(TRANSFORMATION_PARAM));
        }
        return null;
    }
}
