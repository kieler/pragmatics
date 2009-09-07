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

import org.eclipse.gef.Request;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorPart;

/**
 * Request for the execution of a model transformation
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
    private int selectionCount;
    private String modelPackage;

    /**
     * Creates a transformation request
     */
    public ExecuteTransformationRequest(IEditorPart ePart, String command,
            String fileName, ISelection selection, int selectionCount,
            String modelPackage) {
        super(REQ_EXEC_TRANS);
        this.editPart = ePart;
        this.command = command;
        this.fileName = fileName;
        this.modelPackage = modelPackage;
        this.selection = selection;
        this.selectionCount = selectionCount;
    }
    
    /**
     * Sets the model package
     * @param modelPackage
     */
    public void setModelPackage(String modelPackage) {
        this.modelPackage = modelPackage;
    }

    /**
     * Gets the model package
     * @return modelPackage
     */
    public String getModelPackage() {
        return this.modelPackage;
    }
    
    /**
     * Sets the edit part
     * @param part
     */
    public void setEditPart(IEditorPart part) {
        this.editPart = part;
    }

    /**
     * Gets the edit part
     * @return the editPart
     */
    public IEditorPart getEditPart() {
        return this.editPart;
    }

    /**
     * Sets the transformation command
     * @param command
     */
    public void setCommand(String command) {
        this.command = command;
    }

    /**
     * Gets the transformation command
     * @return A Xtend method name
     */
    public String getCommand() {
        return this.command;
    }

    /**
     * Sets the filename
     * @param fileName
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Gets the filename
     * @return A Xtend file name 
     */
    public String getFileName() {
        return this.fileName;
    }

    /**
     * Sets the current selection
     * @param selection
     */
    public void setSelection(ISelection selection) {
        this.selection = selection;
    }

    /**
     * The current selection
     * @return an ISelection object
     */
    public ISelection getSelection() {
        return this.selection;
    }

    /**
     * Sets the number of selections
     * @param count
     */
    public void setSelectionCount(int count) {
        this.selectionCount = count;
    }

    /**
     * Gets the number of selections
     * @return
     */
    public int getSelectionCount() {
        return this.selectionCount;
    }
}
