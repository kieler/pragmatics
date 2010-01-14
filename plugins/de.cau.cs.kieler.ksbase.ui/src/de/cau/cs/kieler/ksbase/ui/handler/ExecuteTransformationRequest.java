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

import org.eclipse.gef.Request;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorPart;

import de.cau.cs.kieler.core.model.transformation.ITransformationFramework;

/**
 * Request for the execution of a model transformation.
 * 
 * @author mim
 * 
 * @kieler.rating 2009-12-15 proposed yellow
 */
public class ExecuteTransformationRequest extends Request {

    /** The request, used to initiate a transformation. */
    public static final String REQ_EXEC_TRANS = "execute transformation";

    /** Variables used to initialize the command. **/
    private IEditorPart editPart;
    private String command;
    private String fileName;
    private ISelection selection;
    private String modelPackage;
    private String[] parameter;
    private ITransformationFramework framework;

    /**
     * Creates a transformation request.
     * 
     * @param ePart
     *            The active EditPart
     * @param cmd
     *            The transformation command
     * @param file
     *            Name of the transformation file
     * @param sel
     *            The active selection
     * @param modelPackageClass
     *            The model package for the active editor
     * @param parameters
     *            The parameters of the transformation
     * @param fframework
     *            The ITransformationFramework to use for execution
     */
    public ExecuteTransformationRequest(final IEditorPart ePart, final String cmd,
            final String file, final ISelection sel, final String modelPackageClass,
            final String[] parameters, final ITransformationFramework fframework) {
        super(REQ_EXEC_TRANS);
        this.editPart = ePart;
        this.command = cmd;
        this.fileName = file;
        this.modelPackage = modelPackageClass;
        this.selection = sel;
        this.framework = fframework;
        this.parameter = parameters.clone();
    }

    /**
     * Sets the model package.
     * 
     * @param modelPackageClass
     *            The model package class.
     */
    public final void setModelPackage(final String modelPackageClass) {
        this.modelPackage = modelPackageClass;
    }

    /**
     * Gets the model package.
     * 
     * @return modelPackage
     */
    public final String getModelPackage() {
        return this.modelPackage;
    }

    /**
     * Sets the edit part.
     * 
     * @param epart
     *            The target edit part.
     */
    public final void setEditPart(final IEditorPart epart) {
        this.editPart = epart;
    }

    /**
     * Gets the edit part.
     * 
     * @return the editPart
     */
    public final IEditorPart getEditPart() {
        return this.editPart;
    }

    /**
     * Sets the transformation command.
     * 
     * @param cmd
     *            The transformation command.
     */
    public final void setCommand(final String cmd) {
        this.command = cmd;
    }

    /**
     * Gets the transformation command.
     * 
     * @return An method name
     */
    public final String getCommand() {
        return this.command;
    }

    /**
     * Sets the filename.
     * 
     * @param file
     *            Name of the file.
     */
    public final void setFileName(final String file) {
        this.fileName = file;
    }

    /**
     * Gets the filename.
     * 
     * @return A file name
     */
    public final String getFileName() {
        return this.fileName;
    }

    /**
     * Sets the current selection.
     * 
     * @param sel
     *            The active selection object
     */
    public final void setSelection(final ISelection sel) {
        this.selection = sel;
    }

    /**
     * The current selection.
     * 
     * @return an ISelection object
     */
    public final ISelection getSelection() {
        return this.selection;
    }

    /**
     * Gets the list of parameters.
     * 
     * @return a string array of parameters
     */
    public final String[] getParameter() {
        return parameter.clone();
    }

    /**
     * Sets the parameters for this transformation request .
     * 
     * @param parameters
     *            The transformation parameters
     */
    public final void setParameter(final String[] parameters) {
        this.parameter = parameters.clone();
    }

    /**
     * Gets the assigned transformation framework.
     * 
     * @return An implementation of ITransformationFramework
     */
    public ITransformationFramework getFramework() {
        return framework;
    }

    /**
     * Sets the transformation framework.
     * 
     * @param fframework
     *            An implementation of ITransformationFramework
     */
    public void setFramework(final ITransformationFramework fframework) {
        this.framework = fframework;
    }

}
