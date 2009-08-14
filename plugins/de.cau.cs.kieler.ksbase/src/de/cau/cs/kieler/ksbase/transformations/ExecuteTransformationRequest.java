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
package de.cau.cs.kieler.ksbase.transformations;

import org.eclipse.gef.Request;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorPart;

/**
 * Request for the execution of a model transformation
 * 
 * @author <a href="mailto:mim@informatik.uni-kiel.de">Michael Matzen</a>
 */
public class ExecuteTransformationRequest extends Request {

	/** The request, used to initiate a transformation*/
	public static final String REQ_EXEC_TRANS = "execute transformation";
	
	//Variables used to initialize the command
	private IEditorPart editPart;
	private String command;
	private String fileName;
	private ISelection selection;
	private int selectionCount;
	
	/**
	 * Creates a transformation request
	 */
	public ExecuteTransformationRequest(IEditorPart ePart, String command, String fileName, ISelection selection, int selectionCount) {
		super(REQ_EXEC_TRANS);
		this.editPart = ePart;
		this.command = command;
		this.fileName = fileName;
		this.selection = selection;
		this.selectionCount = selectionCount; 
	}
	
	public void setEditPart(IEditorPart part) {
		this.editPart = part;
	}
	
	public IEditorPart getEditPart() {
		return this.editPart;
	}
	
	public void setCommand(String command) {
		this.command = command;
	}
	
	public String getCommand() {
		return this.command;
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileName() {
		return this.fileName;
	}
	
	public void setSelection(ISelection selection) {
		this.selection = selection;
	}
	
	public ISelection getSelection() {
		return this.selection;
	}
	
	public void setSelectionCount(int count) {
		this.selectionCount = count;
	}
	
	public int getSelectionCount() {
		return this.selectionCount;
	}
}
