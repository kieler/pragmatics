/******************************************************************************
 * KIELER - Kiel Integrated Environment for Layout for the Eclipse RCP
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
import org.eclipse.core.commands.IHandler;

import de.cau.cs.kieler.ksbase.core.EditorTransformationSettings;
import de.cau.cs.kieler.ksbase.core.Transformation;
import de.cau.cs.kieler.ksbase.core.TransformationManager;
import de.cau.cs.kieler.ksbase.ui.TransformationUIManager;

/**
 * The generic transformation handler used for all UI elements
 * @author Michael Matzen
 *
 */
public class TransformationCommandHandler extends AbstractHandler implements
        IHandler {

    private EditorTransformationSettings editor; //The editor that contains the settings for this handler
    private Transformation transformation; //The transformation that should be executed

    public TransformationCommandHandler() {
        editor = null;
        transformation = null;
    }
    /**
     * Creates a new command handler
     * @param editor The editor that contains the settings for this handler
     * @param transformation The transformation that should be executed
     */
    public TransformationCommandHandler(String editorAndTransformation) {
        String[] parameters = editorAndTransformation.split(":");
        this.editor = TransformationManager.instance.getEditorByName(parameters[0]);
        this.transformation = editor.getTransformationByName(parameters[1]);
    }

    /**
     * Execute the transformation.
     * Uses the TransformationUI manager to create and execute the transformation
     */
    public Object execute(ExecutionEvent event) throws ExecutionException {
        TransformationUIManager.instance
                .createAndExecuteTransformationCommand(event, editor,
                        transformation);
        return null;
    }
}
 