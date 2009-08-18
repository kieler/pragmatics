package de.cau.cs.kieler.ksbase.transformations;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.runtime.IPath;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramCommandStack;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchSite;
import org.eclipse.ui.handlers.HandlerUtil;

import de.cau.cs.kieler.ksbase.KSBasEPlugin;

//import org.eclipse.xtend.ui.editor.XtendEditor;

public class TransformationManager {

    // TODO: Why is everything static in here ? Because we dont want to init it
    // every time
    // => Change to singleton pattern,

    // public static XtendEditor xtendEditor;
    public static IWorkbenchSite activeSite;
    private static LinkedList<EditorTransformationSettings>  registeredEditors;// The currently registered editors

    public static void createAndExecuteTransformationCommand(
            ExecutionEvent event, String command, String fileName,
            int numberOfSelections) {
        TransformationManager.activeSite = HandlerUtil.getActiveSite(event);
        IEditorPart activeEditor = HandlerUtil.getActiveEditor(event);
        ISelection selection = HandlerUtil.getActiveWorkbenchWindow(event)
                .getSelectionService().getSelection();
        if (selection instanceof StructuredSelection && !selection.isEmpty()) {
            EditPart selectedElement = (EditPart) ((StructuredSelection) selection)
                    .getFirstElement();

            ExecuteTransformationRequest request = new ExecuteTransformationRequest(
                    activeEditor, command, fileName, selection,
                    numberOfSelections);
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

    public static LinkedList<EditorTransformationSettings> getEditors() {
        return registeredEditors;
    }
    
    public static EditorTransformationSettings getEditorByName(String editor) {
        for (EditorTransformationSettings settings : registeredEditors) {
            if (settings.getEditor().equals(editor))
                return settings;
        }
        return null;
    }
    
    public static void addEditor(EditorTransformationSettings editor) {
        if (registeredEditors == null)
            registeredEditors = new LinkedList<EditorTransformationSettings>();
        registeredEditors.add(editor);
    }
    
    public static EditorTransformationSettings addEditor(String editorName) {
        if (registeredEditors == null)
            registeredEditors = new LinkedList<EditorTransformationSettings>();
        EditorTransformationSettings editor = new EditorTransformationSettings(editorName);
        registeredEditors.add(editor);
        return editor;
    }

    /**
     * Loads the editor settings from ksbase config file
     */
    @SuppressWarnings("unchecked")
    public static void initializeTransformations() {
        //TODO: 
        registeredEditors = new LinkedList<EditorTransformationSettings>();
        IPath path = KSBasEPlugin.getDefault().getStateLocation();
        FileInputStream fos;
        try {
            fos = new FileInputStream(path.toOSString()+"/ksbase.config");
            ObjectInputStream oos = new ObjectInputStream(fos);
            registeredEditors = (LinkedList<EditorTransformationSettings>) oos.readObject();
        } catch (FileNotFoundException e) {
        	registeredEditors = new LinkedList<EditorTransformationSettings>();
        } catch (IOException e) {
        	registeredEditors = new LinkedList<EditorTransformationSettings>();
        } catch (ClassNotFoundException e) {
        	registeredEditors = new LinkedList<EditorTransformationSettings>();
        }
    }

    /**
     * Stores the currently registered editor settings to the preference store
     */
    public static void storeTransformations() {
        IPath path = KSBasEPlugin.getDefault().getStateLocation();
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(path.toOSString()+"/ksbase.config");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(registeredEditors);
            oos.flush();
            oos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}


