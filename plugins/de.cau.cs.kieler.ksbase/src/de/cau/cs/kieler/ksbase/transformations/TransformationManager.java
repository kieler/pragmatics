package de.cau.cs.kieler.ksbase.transformations;

import java.net.URI;
import java.util.LinkedList;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.draw2d.geometry.Transform;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramCommandStack;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchSite;
import org.eclipse.ui.handlers.HandlerUtil;

import de.cau.cs.kieler.ksbase.KSBasEPlugin;

//import org.eclipse.xtend.ui.editor.XtendEditor;

public class TransformationManager {
	
    //TODO: Why is everything static in here ? Because we dont want to init it every time  
    //=> Change to singleton pattern, 
    
	//public static XtendEditor xtendEditor;
	public static IWorkbenchSite activeSite;
	
	private static LinkedList<Transformation> transformations; //The currently defined transformations 
	private static String modelURI;
	private static String menuName;
	private static int showIn;
	private static URI defaultIconURI;
	private static String editorRestrictions;
	
	public static void createAndExecuteTransformationCommand(ExecutionEvent event, String command, String fileName, int numberOfSelections) {
	        TransformationManager.activeSite = HandlerUtil.getActiveSite(event);
		IEditorPart activeEditor = HandlerUtil.getActiveEditor(event);
		ISelection selection = HandlerUtil.getActiveWorkbenchWindow(event)
				.getSelectionService().getSelection();
		if (selection instanceof StructuredSelection && !selection.isEmpty()) {
			EditPart selectedElement = (EditPart) ((StructuredSelection) selection)
					.getFirstElement();
				
				ExecuteTransformationRequest request = new ExecuteTransformationRequest(
						activeEditor, command, fileName,
						selection, numberOfSelections);
				Command transformationCommand = selectedElement.getCommand(request);

				// gets a command stack to execute the command
				DiagramCommandStack commandStack = null;
				Object adapter = activeEditor.getAdapter(CommandStack.class);
				if (adapter instanceof DiagramCommandStack)
					commandStack = (DiagramCommandStack) adapter;
				if (commandStack == null)
					commandStack = new DiagramCommandStack(null);
				commandStack.execute(transformationCommand);
				
			}
	}
	
	public static LinkedList<Transformation> getTransformations() {
	    return transformations;
	}
	
	public static void setTransformations(LinkedList<Transformation> newVals) {
	    if ( transformations != null)
	        transformations.clear();
	    else
	        transformations = new LinkedList<Transformation>();
	    
	    transformations.addAll(newVals);
	}
	
	/**
	 * Loads the transformations from the preference store
	 */
	public static void initializeTransformations() {
	    transformations = new LinkedList<Transformation>();
	    IPreferenceStore store = KSBasEPlugin.getDefault().getPreferenceStore();
	    
	}
	
	/**
	 * Stores the currently defined transformations to the preference store
	 */
	public static void storeTransformations() {
	    
	}
}

/*
TransformationManager.xtendEditor = new XtendEditor();
try {
	TransformationManager.xtendEditor.init(HandlerUtil.getActiveEditor(event).getEditorSite(), HandlerUtil.getActiveEditor(event).getEditorInput());
	
} catch (PartInitException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
*/