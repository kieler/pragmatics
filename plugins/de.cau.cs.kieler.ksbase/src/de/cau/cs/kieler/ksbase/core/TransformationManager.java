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
package de.cau.cs.kieler.ksbase.core;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URI;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.CanonicalEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramCommandStack;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.part.FileEditorInput;

import de.cau.cs.kieler.ksbase.KSBasEPlugin;
import de.cau.cs.kieler.ksbase.ui.AutoLayoutTrigger;
import de.cau.cs.kieler.ksbase.ui.handler.ExecuteTransformationRequest;
import de.cau.cs.kieler.viewmanagement.ATrigger;
import de.cau.cs.kieler.viewmanagement.RunLogic;

/**
 * The main storage and management class. Contains a list of currently
 * registered editors. Manages the creation of ExecuteTransformationCommands.
 * Handles import, export and Xtend file parsing.
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
    public static TransformationManager instance = new TransformationManager();

    /**
     * Since this is a singleton class the constructor is private
     */
    private TransformationManager() {
        registeredEditors = new LinkedList<EditorTransformationSettings>();
    }

    /**
     * Creates and executes a transformation command by creating a request and
     * execute the resulting command on the diagram command stack
     * 
     * @param event
     *            Execution event for which this command should be created
     * @param editor
     *            The editor for which this transformation is
     * @param transformation
     *            The transformation that should be executed
     */
    public void createAndExecuteTransformationCommand(ExecutionEvent event,
            EditorTransformationSettings editor, Transformation transformation) {
        IEditorPart activeEditor = HandlerUtil.getActiveEditor(event);

        ISelection selection = HandlerUtil.getActiveWorkbenchWindow(event)
                .getSelectionService().getSelection();

        if (selection instanceof StructuredSelection && !selection.isEmpty()) {

            // First, we have to store the transformation file because Xtend
            // doesn't execute strings
            // We have to do this every time, because we can't be sure that the
            // file we wrote last time is still valid. We will write it
            // to the meta-inf folder:

            EditPart selectedElement = (EditPart) ((StructuredSelection) selection)
                    .getFirstElement();
            File file = null;
            try {
                IPath path = ResourcesPlugin.getPlugin().getStateLocation();
                file = File.createTempFile("extension", ".ext", new File(path
                        .toOSString()));
                FileOutputStream out = new FileOutputStream(file);
                if (!file.exists()) {
                    file.createNewFile();
                }
                out.write(editor.getExtFile().getBytes());
                out.flush();
                out.close();

                // IProject p =
                // ((FileEditorInput)activeEditor.getEditorInput()).getFile().getProject();
                // System.out.println(p.getName());

                // Create request
                ExecuteTransformationRequest request = new ExecuteTransformationRequest(
                        activeEditor, transformation.getTransformationName(),
                        file.getAbsolutePath(), selection, transformation
                                .getNumSelections(), editor
                                .getModelPackageClass());
                Command transformationCommand = selectedElement
                        .getCommand(request);

                // gets a command stack to execute the command
                DiagramCommandStack commandStack = null;
                Object adapter = activeEditor.getAdapter(CommandStack.class);
                if (adapter instanceof DiagramCommandStack)
                    commandStack = (DiagramCommandStack) adapter;
                if (commandStack == null)
                    commandStack = new DiagramCommandStack(null);
                commandStack.execute(transformationCommand);
            } catch (FileNotFoundException e) {

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                // Remove temporary Xtend file
                if (file != null)
                    file.delete();
                // update edit policies, so gmf will generate diagram elements
                // for model elements which have been generated during the
                // transformation but
                // not translated to gmf now:
                if (activeEditor instanceof DiagramEditor) {
                    EObject obj = ((View) ((DiagramEditor) activeEditor)
                            .getDiagramEditPart().getModel()).getElement();
                    List<?> editPolicies = CanonicalEditPolicy
                            .getRegisteredEditPolicies(obj);
                    for (Iterator<?> it = editPolicies.iterator(); it.hasNext();) {
                        CanonicalEditPolicy nextEditPolicy = (CanonicalEditPolicy) it
                                .next();
                        nextEditPolicy.refresh();
                    }

                    // If auto-layout is activated, execute now:
                    if (editor.isPerformAutoLayout()) {
                        EditPart e = ((DiagramEditor) activeEditor)
                                .getDiagramEditPart().getRoot().getContents();
                        while (!(e instanceof ShapeEditPart)) {
                            e = (EditPart) e.getChildren().get(0);
                        }
                        ((AutoLayoutTrigger) RunLogic
                                .getTrigger("de.cau.cs.kieler.ksbase.AutoLayoutTrigger"))
                                .triggerAutoLayout(e, activeEditor);

                    }
                }

            }
        }
    }

    /**
     * Get the currently registered editors
     * 
     * @return A list of the registered editors
     */
    public LinkedList<EditorTransformationSettings> getEditors() {
        return registeredEditors;
    }

    /**
     * Tries to find an editor with it's name.
     * 
     * @param editor
     *            The editor's name.
     * @return The first editor in the list of registered editors which has the
     *         given name
     */
    public EditorTransformationSettings getEditorByName(String editor) {
        for (EditorTransformationSettings settings : registeredEditors) {
            if (settings.getEditor().equals(editor))
                return settings;
        }
        return null;
    }

    /**
     * Adds a new editor to the list of registered editors.
     * 
     * @param editor
     *            The EditorTransformationSetting that describes the editor
     */
    public void addEditor(EditorTransformationSettings editor) {
        if (registeredEditors == null)
            registeredEditors = new LinkedList<EditorTransformationSettings>();
        if (editor.getEditor().length() > 0)
            registeredEditors.add(editor);
    }

    /**
     * Adds a new editor to the list of registered editors. This class creates
     * an empty EditorTransformationSetting with the given editorName.
     * 
     * @param editorName
     *            The name of the new editor
     * @return The newly created EditorTransformationSettings
     */
    public EditorTransformationSettings addEditor(String editorName) {
        if (registeredEditors == null)
            registeredEditors = new LinkedList<EditorTransformationSettings>();
        if (editorName.length() > 0) {
            EditorTransformationSettings editor = new EditorTransformationSettings(
                    editorName);
            registeredEditors.add(editor);
            return editor;
        } else
            return null;
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

        String[] editors = store.getString(
                Messages.Preferences_RegisteredEditors).split(","); //$NON-NLS-2$
        for (String editor : editors) {
            if (editor.length() > 0) {
                EditorTransformationSettings settings = new EditorTransformationSettings(
                        editor);
                settings.setModelPackageClass(store.getString(editor
                        + Messages.Preferences_ModelPackageClass));
                settings.setExtFile(store.getString(editor
                        + Messages.Preferences_ExtFile));
                settings.setMenuName(store.getString(editor
                        + Messages.Preferences_MenuName));
                settings.setMenuLocation(store.getString(editor
                        + Messages.Preferences_MenuLocation));
                settings.setToolbarLocation(store.getString(editor
                        + Messages.Preferences_ToolbarLocation));
                settings.setVisibilityFlags(store.getInt(editor
                        + Messages.Preferences_Visibility));
                settings.setDefaultIconURI(URI.create(store.getString(editor
                        + Messages.Preferences_DefaultIcon)));
                String[] transformations = store.getString(
                        editor + Messages.Preferences_Transformations).split(
                        ","); //$NON-NLS-2$
                for (String transformation : transformations) {
                    if (transformation.length() > 0) {
                        String prefix = editor + "." + transformation; //$NON-NLS-1$
                        Transformation t = new Transformation(store
                                .getString(prefix + ".Name"), transformation);
                        t
                                .setNumSelections(store
                                        .getInt(prefix
                                                + Messages.Preferences_Transformation_Selections));
                        t.setIconURI(URI.create(store.getString(prefix
                                + Messages.Preferences_Transformation_Icon)));
                        t
                                .setKeyboardShortcut(store
                                        .getString(prefix
                                                + Messages.Preferences_Transformation_Shortcut));
                        t
                                .setPartConfig(store
                                        .getString(
                                                prefix
                                                        + Messages.Preferences_Transformation_PartConfig)
                                        .split(",")); //$NON-NLS-1$
                        settings.addTransformation(t);
                    }
                }
                registeredEditors.add(settings);
            }
        }
    }

    /**
     * Imports settings from an external file
     * 
     * @param file
     *            The absolute file name
     */
    @SuppressWarnings("unchecked")
    public void importSettings(String file) {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(
                    file));
            registeredEditors = new LinkedList<EditorTransformationSettings>();
            registeredEditors = (LinkedList<EditorTransformationSettings>) in
                    .readObject();
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Stores the currently registered editor settings to the preference store
     * Stores the values as strings: EditorName.ModelPackageName
     * EditorName.MenuName etc.
     */
    public void storeTransformations() {
        IPreferenceStore store = KSBasEPlugin.getDefault().getPreferenceStore();
        String editors = ""; //$NON-NLS-1$
        for (EditorTransformationSettings settings : registeredEditors) {
            String prefix = settings.getEditor();
            editors += prefix + ","; //$NON-NLS-1$
            store.setValue(prefix + Messages.Preferences_ModelPackageClass,
                    settings.getModelPackageClass());
            store.setValue(prefix + Messages.Preferences_ExtFile, settings
                    .getExtFile());
            store.setValue(prefix + Messages.Preferences_MenuName, settings
                    .getMenuName());
            store.setValue(prefix + Messages.Preferences_MenuLocation, settings
                    .getMenuLocation());
            store.setValue(prefix + Messages.Preferences_ToolbarLocation,
                    settings.getToolbarLocation());
            store.setValue(prefix + Messages.Preferences_Visibility, settings
                    .getVisibility());
            store.setValue(prefix + Messages.Preferences_DefaultIcon, settings
                    .getDefaultIconURI().toString());
            String transformations = ""; //$NON-NLS-1$
            for (Transformation t : settings.getTransformations()) {
                String tprefix = prefix + "." + t.getTransformationName(); //$NON-NLS-1$
                transformations += t.getTransformationName() + ","; //$NON-NLS-1$
                store.setValue(tprefix + ".Name", t.getName());
                store.setValue(tprefix
                        + Messages.Preferences_Transformation_Selections, t
                        .getNumSelections());
                store.setValue(tprefix
                        + Messages.Preferences_Transformation_PartConfig, t
                        .getPartConfigList());
                store.setValue(tprefix
                        + Messages.Preferences_Transformation_Icon, t
                        .getIconString());
                store.setValue(tprefix
                        + Messages.Preferences_Transformation_Shortcut, t
                        .getKeyboardShortcut());
            }
            if (transformations.length() > 0) {
                store.setValue(prefix + Messages.Preferences_Transformations,
                        transformations.substring(0,
                                transformations.length() - 1));
            }
        }
        // trunc the last ','
        store.setValue(Messages.Preferences_RegisteredEditors, editors
                .substring(0, editors.length() - 1));

    }

    /**
     * Exports the settings to an external file
     * 
     * @param file
     *            The absolute file name
     */
    public void exportSettings(String file) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream(file));
            out.writeObject(registeredEditors);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
