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

import java.net.URI;
import java.util.LinkedList;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramCommandStack;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.handlers.HandlerUtil;

import de.cau.cs.kieler.ksbase.KSBasEPlugin;

/**
 * Manages the creation of ExecuteTransformationCommands and the list of
 * currently registered editors
 * 
 * @author Michael Matzen
 * 
 */
public class TransformationManager {

    private LinkedList<EditorTransformationSettings> registeredEditors;// The
                                                                       // currently
                                                                       // registered
                                                                       // editors

    // Thread-safe initialization
    private static TransformationManager instance = new TransformationManager();

    private TransformationManager() {

    }

    public static TransformationManager getInstance() {
        return instance;
    }

    public void createAndExecuteTransformationCommand(ExecutionEvent event,
            EditorTransformationSettings editor, Transformation t) {
        IEditorPart activeEditor = HandlerUtil.getActiveEditor(event);

        ISelection selection = HandlerUtil.getActiveWorkbenchWindow(event)
                .getSelectionService().getSelection();

        if (selection instanceof StructuredSelection && !selection.isEmpty()) {
            EditPart selectedElement = (EditPart) ((StructuredSelection) selection)
                    .getFirstElement();

            // Create request
            ExecuteTransformationRequest request = new ExecuteTransformationRequest(
                    activeEditor, t.getTransformationName(), editor
                            .getExtFile(), selection, t.getNumSelections(),
                    editor.getModelPackageClass());
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

    public LinkedList<EditorTransformationSettings> getEditors() {
        return registeredEditors;
    }

    public EditorTransformationSettings getEditorByName(String editor) {
        for (EditorTransformationSettings settings : registeredEditors) {
            if (settings.getEditor().equals(editor))
                return settings;
        }
        return null;
    }

    public void addEditor(EditorTransformationSettings editor) {
        if (registeredEditors == null)
            registeredEditors = new LinkedList<EditorTransformationSettings>();
        registeredEditors.add(editor);
    }

    public EditorTransformationSettings addEditor(String editorName) {
        if (registeredEditors == null)
            registeredEditors = new LinkedList<EditorTransformationSettings>();
        EditorTransformationSettings editor = new EditorTransformationSettings(
                editorName);
        registeredEditors.add(editor);
        return editor;
    }

    public void removeEditor(String editor) {
        for (int i = 0; i < registeredEditors.size(); ++i) {
            if (registeredEditors.get(i).getEditor().equals(editor))
                registeredEditors.remove(i);
        }
    }

    /**
     * Loads the editor settings from preference store
     */
    public void initializeTransformations() {
        registeredEditors = new LinkedList<EditorTransformationSettings>();
        IPreferenceStore store = KSBasEPlugin.getDefault().getPreferenceStore();

        String[] editors = store.getString("registertedEditors").split(",");
        for (String editor : editors) {
            EditorTransformationSettings settings = new EditorTransformationSettings(
                    editor);
            settings.setModelPackageClass(store.getString(editor
                    + ".ModelPackageClass"));
            settings.setExtFile(store.getString(editor + ".ExtFile"), false);
            settings.setMenuName(store.getString(editor + ".MenuName"));
            settings.setMenuLocation(store.getString(editor + ".MenuLocation"));
            settings.setToolbarLocation(store.getString(editor
                    + ".ToolbarLocation"));
            settings.setVisibilityFlags(store.getInt(editor + ".Visibility"));
            settings.setDefaultIconURI(URI.create(store.getString(editor
                    + ".DefaultIcon")));
            String[] transformations = store.getString(
                    editor + ".Transformations").split(",");
            for (String transformation : transformations) {
                String prefix = editor + "." + transformation;
                Transformation t = new Transformation(store.getString(prefix
                        + ".Name"), transformation);
                t.setNumSelections(store.getInt(prefix + ".NumSelections"));
                t.setIconURI(URI.create(store.getString(prefix + ".Icon")));
                t.setKeyboardShortcut(store.getString(prefix + ".Shortcut"));
                t.setPartConfig(store.getString(prefix + ".PartConfig").split(
                        ","));
                settings.addTransformation(t);
            }
            registeredEditors.add(settings);
        }
    }

    /**
     * Stores the currently registered editor settings to the preference store
     * Stores the values as strings: EditorName.ModelPackageName
     * EditorName.MenuName etc.
     */
    public void storeTransformations() {

        IPreferenceStore store = KSBasEPlugin.getDefault().getPreferenceStore();
        String editors = "";
        for (EditorTransformationSettings settings : registeredEditors) {
            String prefix = settings.getEditor();
            editors += prefix + ",";
            store.setValue(prefix + ".ModelPackageClass", settings
                    .getModelPackageClass());
            store.setValue(prefix + ".ExtFile", settings.getExtFile());
            store.setValue(prefix + ".MenuName", settings.getMenuName());
            store
                    .setValue(prefix + ".MenuLocation", settings
                            .getMenuLocation());
            store.setValue(prefix + ".ToolbarLocation", settings
                    .getToolbarLocation());
            store.setValue(prefix + ".Visibility", settings.getVisibility());
            store.setValue(prefix + ".DefaultIcon", settings
                    .getDefaultIconURI().toString());
            String transformations = "";
            for (Transformation t : settings.getTransformations()) {
                String tprefix = prefix + "." + t.getName();
                transformations += t.getName() + ",";
                store.setValue(tprefix + ".Transformation", t
                        .getTransformationName());
                store
                        .setValue(tprefix + ".NumSelections", t
                                .getNumSelections());
                store.setValue(tprefix + ".PartConfig", t.getPartConfigList());
                store.setValue(tprefix + ".Icon", t.getIconString());
                store.setValue(tprefix + ".Shortcut", t.getKeyboardShortcut());
            }
            store.setValue(prefix + ".Transformations", transformations
                    .substring(0, transformations.length() - 1));
        }
        // trunc the last ','
        store.setValue("registertedEditors", editors.substring(0, editors
                .length() - 1));
    }
}
