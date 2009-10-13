/******************************************************************************
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

/**
 * The generic transformation handler used for all UI elements
 * 
 * @author Michael Matzen
 * 
 */
public class TransformationCommandHandler extends AbstractHandler{

	public static final String EDITOR_PARAM = "de.cau.cs.kieler.ksbase.editorParameter";
	public static final String TRANSFORMATION_PARAM = "de.cau.cs.kieler.ksbase.transformationParameter";

	/**
	 * Creates a new command handler.
	 */
	public TransformationCommandHandler() {
	}

	/**
	 * Execute a transformation. The editor and transformation settings are
	 * given by the extension point parameters Uses the TransformationUI manager
	 * to create and execute the transformation.
	 * @param event The source event 
	 */
	public Object execute(final ExecutionEvent event) throws ExecutionException {

		if (!TransformationManager.instance.isInitialized()) {
			TransformationManager.instance.initializeTransformations();
		}
		EditorTransformationSettings editor = TransformationManager.instance
				.getEditorByName(event.getParameter(EDITOR_PARAM));
		if (editor != null) {
			TransformationUIManager.instance
					.createAndExecuteTransformationCommand(event, editor,
							editor.getTransformationByName(event
									.getParameter(TRANSFORMATION_PARAM)));
		}
		else {
			System.err.println("error no transformation defined!");
		}
		return null;
	}
}
