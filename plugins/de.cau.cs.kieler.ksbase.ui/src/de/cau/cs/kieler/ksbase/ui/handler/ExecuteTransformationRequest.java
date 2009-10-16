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

import org.eclipse.gef.Request;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorPart;

/**
 * Request for the execution of a model transformation.
 * 
 * @author Michael Matzen
 */
public class ExecuteTransformationRequest extends Request {

    /** The request, used to initiate a transformation */
    public static final String REQ_EXEC_TRANS = "execute transformation";

    // Variables used to initialize the command
    private IEditorPart editPart;
    private String command;
    private String fileName;
    private ISelection selection;
    private String modelPackage;
    private String[] parameter;

    /**
     * Creates a transformation request.
     * 
     * @param ePart The active EditPart
     * @param command The transformation command
     * @param fileName Name of the Xtend transformation file
     * @param selection The active selection
     * @param modelPackage The model package for the active editor
     * @param parameter[] The parameters of the Xtend transformation
     */
    public ExecuteTransformationRequest(final IEditorPart ePart, final String command,
    		final String fileName, final ISelection selection,
    		final String modelPackage, final String[] parameter) {
        super(REQ_EXEC_TRANS);
        this.editPart = ePart;
        this.command = command;
        this.fileName = fileName;
        this.modelPackage = modelPackage;
        this.selection = selection;
        this.parameter = parameter.clone();
    }
    
    /**
     * Sets the model package.
     * @param modelPackage
     */
    public final void setModelPackage(final String modelPackage) {
        this.modelPackage = modelPackage;
    }

    /**
     * Gets the model package.
     * @return modelPackage 
     */
    public final String getModelPackage() {
        return this.modelPackage;
    }
    
    /**
     * Sets the edit part.
     * @param part
     */
    public final void setEditPart(final IEditorPart part) {
        this.editPart = part;
    }

    /**
     * Gets the edit part.
     * @return the editPart
     */
    public final IEditorPart getEditPart() {
        return this.editPart;
    }

    /**
     * Sets the transformation command.
     * @param command
     */
    public final void setCommand(final String command) {
        this.command = command;
    }

    /**
     * Gets the transformation command.
     * @return A Xtend method name
     */
    public final String getCommand() {
        return this.command;
    }

    /**
     * Sets the filename.
     * @param fileName
     */
    public final void setFileName(final String fileName) {
        this.fileName = fileName;
    }

    /**
     * Gets the filename.
     * @return A Xtend file name 
     */
    public final String getFileName() {
        return this.fileName;
    }

    /**
     * Sets the current selection.
     * @param selection
     */
    public final void setSelection(final ISelection selection) {
        this.selection = selection;
    }

    /**
     * The current selection.
     * @return an ISelection object
     */
    public final ISelection getSelection() {
        return this.selection;
    }

    /**
     * Gets the list of parameters
     * @return a string array of parameters
     */
    public final String[] getParameter() {
		return parameter.clone();
	}

	/**
	 * Sets the parameters for this transformation request .
	 * @param parameter
	 */
    public final void setParameter(final String[] parameter) {
		this.parameter = parameter.clone();
	}
}
